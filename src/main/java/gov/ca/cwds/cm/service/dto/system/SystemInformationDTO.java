package gov.ca.cwds.cm.service.dto.system;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cm.service.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author CWDS CALS API Team
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("squid:S2160")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SystemInformationDTO extends BaseDTO {

  private static final long serialVersionUID = 2548070376186176867L;

  private String application;
  private String version;
  private String buildNumber;
  private HealthCheckResultDTO cwscms;
  private HealthCheckResultDTO deadlocks;
}
