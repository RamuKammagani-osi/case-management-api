package gov.ca.cwds.cm.service.dto.system;

import com.codahale.metrics.health.HealthCheck;
import gov.ca.cwds.cm.service.dto.BaseDTO;
import java.util.HashMap;

/**
 * @author CWDS CALS API Team
 */

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

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public boolean isHealthy() {
    return healthy;
  }

  public void setHealthy(boolean healthy) {
    this.healthy = healthy;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Throwable getError() {
    return error;
  }

  public void setError(Throwable error) {
    this.error = error;
  }

  public HashMap<String, Object> getDetails() {
    return details;
  }

  public void setDetails(HashMap<String, Object> details) {
    this.details = details;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }
}
