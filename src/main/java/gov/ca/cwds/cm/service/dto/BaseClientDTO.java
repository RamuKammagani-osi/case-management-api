package gov.ca.cwds.cm.service.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** @author CWDS TPT-3 Team */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BaseClientDTO extends BaseDTO implements Serializable {

  private static final long serialVersionUID = 6306525014938361213L;

  @NotNull
  @Valid
  @ApiModelProperty(required = true, readOnly = false)
  private ClientDTO client;
}
