package gov.ca.cwds.cm.service.dto;

import static gov.ca.cwds.data.persistence.cms.CmsPersistentObject.CMS_ID_LEN;
import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cm.RequestResponse;
import gov.ca.cwds.cm.service.mapper.tool.RemoveTrailingSpaces;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/** @author CWDS TPT-3 Team */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CSECHistoryDto extends BaseDTO implements RequestResponse {

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(value = "The Date the sexual abuse began.", example = "2004-06-14")
  private LocalDate startDate;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(value = "The Date the sexual abuse ended.", example = "2004-06-14")
  private LocalDate endDate;

  @RemoveTrailingSpaces
  @NotNull
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(required = true, value = "Child Client ID", example = "BKk7CHj01Y")
  private String childClientId;

  @RemoveTrailingSpaces
  @NotNull
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(required = true, value = "Child Client ID", example = "BKk7CHj01Y")
  private String sexualExploitationType;
}