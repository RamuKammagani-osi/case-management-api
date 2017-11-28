package gov.ca.cwds.cm.service.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** @author CWDS TPT-3 Team */
public class BaseClientDTO extends BaseDTO implements Serializable {

  private static final long serialVersionUID = 6306525014938361213L;

  @NotNull
  @Valid
  @ApiModelProperty(required = true, readOnly = false)
  private ClientDTO client;

  public ClientDTO getClient() {
    return client;
  }

  public void setClient(ClientDTO client) {
    this.client = client;
  }
}
