package gov.ca.cwds.cm.service.dto.system;

import com.codahale.metrics.health.HealthCheck;
import gov.ca.cwds.cm.service.dto.BaseDTO;
import java.util.HashMap;
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
public class HealthCheckResultDTO extends BaseDTO {

  private static final long serialVersionUID = 6340795706320750307L;

  private boolean healthy;
  private String message;
  private Throwable error;
  private HashMap<String, Object> details;
  private String timestamp;

  public void setResult(HealthCheck.Result result) {
    setHealthy(result.isHealthy());
    setMessage(result.getMessage());
    setError(result.getError());
    setDetails((HashMap<String, Object>) result.getDetails());
    setTimestamp(result.getTimestamp());
  }
}
