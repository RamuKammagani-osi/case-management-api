package gov.ca.cwds.cm.web.rest.parameter;

import java.io.Serializable;

/** @author CWDS TPT-3 Team */
public class ClientParameterObject implements Serializable {

  private static final long serialVersionUID = 1235598994686585318L;

  private String clientId;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
}
