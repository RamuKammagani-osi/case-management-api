package gov.ca.cwds.cm.web.rest.system;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import gov.ca.cwds.cm.BaseApiIntegrationTest;
import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.service.dto.system.HealthCheckResultDTO;
import gov.ca.cwds.cm.service.dto.system.SystemInformationDTO;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import org.junit.Test;

/**
 * Created by CWDS TPT-3 Team
 */
public class SystemInformationResourceTest extends BaseApiIntegrationTest {

  @Test
  public void testSystemInformationGet() throws IOException {
    final SystemInformationDTO systemInformation = clientTestRule
        .target(Constants.API.SYSTEM_INFORMATION_PATH)
        .request(MediaType.APPLICATION_JSON)
        .get(SystemInformationDTO.class);

    assertThat(systemInformation.getApplication(), is(equalTo("CWDS CASE MANAGEMENT API")));
    assertThat(systemInformation.getVersion(), is(notNullValue()));

    assertHealthCheck(systemInformation.getDeadlocks());
    assertHealthCheck(systemInformation.getCwscms());
  }

  private void assertHealthCheck(final HealthCheckResultDTO healthCheckResult) {
    assertThat(healthCheckResult, is(notNullValue()));
    assertThat(healthCheckResult.isHealthy(), is(equalTo(true)));
  }

}
