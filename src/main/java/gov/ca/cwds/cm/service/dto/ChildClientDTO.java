package gov.ca.cwds.cm.service.dto;

import static gov.ca.cwds.data.persistence.cms.CmsPersistentObject.CMS_ID_LEN;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cm.RequestResponse;
import io.dropwizard.validation.OneOf;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author CWDS TPT-3 Team
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@SuppressWarnings({"squid:S3437"})
public class ChildClientDTO extends BaseClientDTO implements RequestResponse {

  private static final long serialVersionUID = 7569314519640349923L;

  @NotNull
  @Size(min = CMS_ID_LEN, max = CMS_ID_LEN)
  @ApiModelProperty(
      required = true,
      readOnly = true,
      value = "CLIENT ID of victim",
      example = "ABC1234567"
  )
  private String victimClientId;

  @NotEmpty
  @Size(min = 1, max = 2)
  @OneOf(
      value = {"N", "Y", "NA"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  @ApiModelProperty(
      required = true,
      value = "Adoptable Code",
      example = "ADOPTABLE"
  )
  private String adoptableCode;

  @NotNull
  @ApiModelProperty(value = "Adpoted Age", example = "34")
  private Short adoptedAge;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean afdcFcEligibilityIndicatorVar;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean allEducationInfoOnFileIndicator;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean allHealthInfoOnFileIndicator;

  @NotNull
  @Size(max = 254)
  @ApiModelProperty(example = "Education")
  private String attemptToAcquireEducInfoDesc;

  @NotNull
  @Size(max = 254)
  @ApiModelProperty(example = "Health")
  private String attemptToAcquireHlthInfoDesc;

  @NotNull
  @Size(max = 1, message = "size must be 1")
  @ApiModelProperty(required = true, example = "N")
  private String awolAbductedCode;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean birthHistoryIndicatorVar;

  @NotNull
  @ApiModelProperty
  private Boolean childIndianAncestryIndicator;

  @ApiModelProperty
  private Boolean collegeIndicator;

  @Size(max = CMS_ID_LEN)
  @ApiModelProperty(example = "ABC1234567")
  private String currentCaseId;

  @NotNull
  @ApiModelProperty(example = "1234")
  private String deathCircumstancesType;

  @Size(min = 1, max = 1)
  @OneOf(
      value = {"N", "Y", "D"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  @ApiModelProperty(
      value = "Disablility Diagnosed",
      example = "N"
  )
  private String disabilityDiagnosedCode;

  @Size(max = CMS_ID_LEN)
  @ApiModelProperty()
  private String drmsHePassportDocOld;

  @Size(max = CMS_ID_LEN)
  @ApiModelProperty(example = "Document")
  private String drmsHealthEducPassportDoc;

  @Size(max = CMS_ID_LEN)
  @ApiModelProperty(example = "Agreement")
  private String drmsVoluntaryPlcmntAgrmntDoc;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean fc2EligApplicationIndicatorVar;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @gov.ca.cwds.rest.validation.Date
  @ApiModelProperty(
      value = "yyyy-MM-dd",
      example = "2000-01-01"
  )
  private LocalDateTime foodStampsApplicationDate;

  @NotNull
  @ApiModelProperty(required = true, example = "true")
  private Boolean foodStampsApplicationIndicator;

  @NotEmpty
  @Size(min = 1, max = 1, message = "size must be 1")
  @OneOf(
      value = {"Y", "N", "U", "P"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  @ApiModelProperty(required = true, example = "N")
  private String icwaEligibilityCode;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean intercountryAdoptDisruptedIndicator;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean intercountryAdoptDissolvedIndicator;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean medEligibilityApplicationIndicatorVar;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean minorNmdParentIndicator;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean parentalRightsLimitedIndicator;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean parentalRightsTermintnIndicatorVar;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean paternityIndividualIndicatorVar;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean postsecVocIndicator;

  @NotEmpty
  @Size(min = 1, max = 1, message = "size must be 1")
  @OneOf(
      value = {"Y", "N", "U", "X"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  @ApiModelProperty(required = true, example = "N")
  private String previouslyAdopted;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean safelySurrendedBabiesIndicatorVar;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean saw1EligApplicationIndicatorVar;

  @NotNull
  @ApiModelProperty(example = "0")
  private Integer sawsCaseSerialNumber;

  @ApiModelProperty(
      value = "yyyy-MM-dd",
      example = "2000-01-01"
  )
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDateTime sijsScheduledInterviewDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @ApiModelProperty(
      value = "yyyy-MM-dd",
      example = "2000-01-01"
  )
  @gov.ca.cwds.rest.validation.Date
  private LocalDateTime siiNextScreeningDueDate;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean ssiSspApplicationIndicator;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean tribalAncestryNotifctnIndicatorVar;

  @ApiModelProperty(
      value = "yyyy-MM-dd",
      example = "2000-01-01"
  )
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDateTime tribalCustomaryAdoptionDate;

  @NotNull
  @ApiModelProperty(required = true)
  private Boolean tribalCustomaryAdoptionIndicator;

  @NotNull
  @ApiModelProperty(required = true)
  private ClientDTO client;

}
