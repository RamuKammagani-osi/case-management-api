package gov.ca.cwds.cm.web.rest.system;

import static gov.ca.cwds.cm.Constants.API.SYSTEM_INFORMATION_PATH;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;
import com.google.inject.Inject;
import gov.ca.cwds.cm.CmApiConfiguration;
import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.service.dto.system.HealthCheckResultDTO;
import gov.ca.cwds.cm.service.dto.system.SystemInformationDTO;
import gov.ca.cwds.rest.api.ApiException;
import io.dropwizard.setup.Environment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author CWDS TPT-3 Team
 */
@Api(value = SYSTEM_INFORMATION_PATH)
@Path(SYSTEM_INFORMATION_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class SystemInformationResource {

  private static final String VERSION_PROPERTIES_FILE = "version.properties";
  private static final String BUILD_NUMBER = "build.number";

  private final String applicationName;
  private final String applicationVersion;
  private final Environment environment;
  private final String buildNumber;

  @Inject
  public SystemInformationResource(
      final CmApiConfiguration configuration,
      final Environment environment) {
    this.applicationName = configuration.getApplicationName();
    this.applicationVersion = configuration.getVersion();
    this.environment = environment;
    final Properties versionProperties = getVersionProperties();
    this.buildNumber = versionProperties.getProperty(BUILD_NUMBER);
  }

  private Properties getVersionProperties() {
    Properties versionProperties = new Properties();
    try {
      InputStream is = ClassLoader.getSystemResourceAsStream(VERSION_PROPERTIES_FILE);
      versionProperties.load(is);
    } catch (IOException e) {
      throw new ApiException("Can't read version.properties", e);
    }
    return versionProperties;
  }

  /**
   * Get the name of the application.
   *
   * @return the application data
   */
  @GET
  @ApiOperation(value = "Returns System Information", response = SystemInformationDTO.class)
  public SystemInformationDTO get() {
    final SystemInformationDTO systemInformation = new SystemInformationDTO();
    systemInformation.setApplication(applicationName);
    systemInformation.setVersion(applicationVersion);
    systemInformation.setBuildNumber(buildNumber);

    final Map<String, Result> healthChecks = environment.healthChecks().runHealthChecks();
    systemInformation.setCwscms(getHealthCheckResultDTO(healthChecks.get(Constants.UnitOfWork.CMS)));
    systemInformation.setDeadlocks(getHealthCheckResultDTO(healthChecks.get("deadlocks")));

    return systemInformation;
  }

  public HealthCheckResultDTO getHealthCheckResultDTO(HealthCheck.Result result) {
    HealthCheckResultDTO healthCheckResultDTO = new HealthCheckResultDTO();
    healthCheckResultDTO.setResult(result);
    return healthCheckResultDTO;
  }
}
