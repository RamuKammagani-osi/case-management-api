package gov.ca.cwds.cm.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cm.service.mapper.tool.RemoveTrailingSpaces;
import io.dropwizard.validation.OneOf;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static gov.ca.cwds.cm.Constants.DATE_FORMAT;
import static gov.ca.cwds.cm.Constants.TIME_FORMAT;

/**
 * @author CWDS TPT-3 Team
 */


@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({"squid:S3437"})
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LandingItemDTO {
  @ApiModelProperty(value = "Local or not indicator.", example = "true")
  private Boolean local;

  @Size(max = 10)
  @ApiModelProperty(
    value =
        "Case/referral ID. A system generated unique number identifying the CASE/REFERRAL. "
            + "This ID has an internal 10 digit alpha-numeric representation[sic] and an external 19 digit numeric representation.",
    example = "0YIPkZU0S0"
  )
  private String id;

  @Size(max = 19)
  @ApiModelProperty(
    value =
        "Case/referral External representation of the Id to be displayed at the User Interface or on "
            + "Reports and  Documents in the following format: 1234-1234-1234-1234567. The inclusion of the "
            + "hyphens used in the formatting of this string results in a 22 byte display area.",
    example = "1234123412341234567"
  )
  private String idExternal;

  @Size(max = 35)
  @RemoveTrailingSpaces
  @ApiModelProperty(value = "Case/referral/Reminder Name", example = "Pedro L.")
  private String name;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(
    value =
        "The date on which an uninterrupted period of  services on behalf of a CHILD CLIENT begins.",
    example = "12/20/2018"
  )
  private LocalDate startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(
    value =
        "The date on which an uninterrupted period of services on behalf of a child CLIENT ends.",
    example = "12/20/2018"
  )
  private LocalDate endDate;

  @RemoveTrailingSpaces
  @ApiModelProperty(
    value = "Service component being referenced  for a child's case.",
    example = "Emergency Response"
  )
  private String serviceComponent;

  @RemoveTrailingSpaces
  @ApiModelProperty(
    value = "This describes the circumstances under which the case is closed.",
    example = "adoption"
  )
  private String closure;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(
    value = "The referral creation date extracted from the ID.",
    example = "12/20/2018"
  )
  private String date;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIME_FORMAT)
  @ApiModelProperty(
    value = "The referral creation time extracted from the ID.",
    example = "07:11 AM"
  )
  private String time;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(value = "The reminder due date.", example = "12/20/2018")
  private LocalDate dateDue;

  @RemoveTrailingSpaces
  @ApiModelProperty(value = "The reminder message.", example = "adoption")
  private String message;

  @NotNull
  @OneOf(
    value = {"case", "referral", "reminder"},
    ignoreCase = true,
    ignoreWhitespace = true
  )
  @ApiModelProperty(value = "Type of caseload item: case, referral, reminder", example = "case")
  private String itemType;
}
