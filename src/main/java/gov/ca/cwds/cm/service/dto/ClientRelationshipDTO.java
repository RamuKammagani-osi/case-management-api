package gov.ca.cwds.cm.service.dto;

import static gov.ca.cwds.data.persistence.cms.CmsPersistentObject.CMS_ID_LEN;
import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cm.RequestResponse;
import io.dropwizard.validation.OneOf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel(description = "A definition of family roles and other significant connections which links one CLIENT to another.")
@SuppressWarnings({"squid:S3437"})
public class ClientRelationshipDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = 7455548566030460582L;

  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(value = "ID. String of size 10", example = "AcB3Wu00Rx")
  private String relationshipId;

  @NotNull
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(value = "Client ID. String of size 10", example = "FplTfDs0Rx", required = true)
  private String clientId;

  @NotNull
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(
      value = "Related Client ID. String of size 10", example = "MHH2zjk0Rx", required = true
  )
  private String relatedClientId;

  @NotNull
  @Size(max = Short.MAX_VALUE)
  @ApiModelProperty(
      value = "SYS_ID number designated for each type of relationship between two CLIENTs "
          + "(e.g.,  Son/Father, Daughter/Father, Sister/Brother, etc.)."
      + "Additional info can be reached from system-codes resource by 'CLNTRELC' key. Numeric",
      example = "285", required = true)
  private Short relationshipTypeCode;

  @NotNull
  @ApiModelProperty(
      value = "Indicates if the parent client is absent for the related child client. Boolean",
      example = "false",
      required = true
  )
  private Boolean absentParentIndicator;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(
      value = "The date the relationship began",
      example = "2000-10-20"
  )
  private LocalDate relationshipStartDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(
      value = "The date the relationship ended",
      example = "2018-10-20"
  )
  private LocalDate relationshipEndDate;

  @NotNull
  @OneOf(
      value = {"NO", "UNKNOWN", "YES"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  @ApiModelProperty(
      required = true,
      value = "Indicates whether the two CLIENTs live in the same home, permitted values are : 'NO', 'UNKNOWN', 'YES'",
      example = "YES"
  )
  private String sameHomeStatus;

  @ApiModelProperty(value = "Related Client")
  private ClientDTO relatedClient;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ClientRelationshipDTO that = (ClientRelationshipDTO) o;
    return Objects.equals(clientId, that.clientId) &&
        Objects.equals(relatedClientId, that.relatedClientId) &&
        Objects.equals(relationshipTypeCode, that.relationshipTypeCode) &&
        Objects.equals(relationshipStartDate, that.relationshipStartDate) &&
        Objects.equals(relationshipEndDate, that.relationshipEndDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), clientId, relatedClientId, relationshipTypeCode,
        relationshipStartDate, relationshipEndDate);
  }
}
