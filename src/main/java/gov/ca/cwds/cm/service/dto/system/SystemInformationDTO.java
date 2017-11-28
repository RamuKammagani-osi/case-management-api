package gov.ca.cwds.cm.service.dto.system;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cm.service.dto.BaseDTO;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S2160")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SystemInformationDTO extends BaseDTO {

  private static final long serialVersionUID = 2548070376186176867L;

  private String application;
  private String version;
  private String buildNumber;
  private HealthCheckResultDTO cwscms;
  private HealthCheckResultDTO deadlocks;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getBuildNumber() {
    return buildNumber;
  }

  public void setBuildNumber(String buildNumber) {
    this.buildNumber = buildNumber;
  }

  public HealthCheckResultDTO getCwscms() {
    return cwscms;
  }

  public void setCwscms(HealthCheckResultDTO cwscms) {
    this.cwscms = cwscms;
  }

  public HealthCheckResultDTO getDeadlocks() {
    return deadlocks;
  }

  public void setDeadlocks(HealthCheckResultDTO deadlocks) {
    this.deadlocks = deadlocks;
  }
}
