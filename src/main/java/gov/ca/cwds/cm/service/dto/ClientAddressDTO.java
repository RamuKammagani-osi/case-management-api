package gov.ca.cwds.cm.service.dto;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** @author CWDS TPT-3 Team */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel(
  description =
      "A specific ADDRESS (e.g., home, business, school, prison, etc.) defined for a "
          + "specific CLIENT during a specific period of time."
)
@SuppressWarnings({"squid:S3437"})
public class ClientAddressDTO extends BaseDTO implements Response {

  private static final long serialVersionUID = 6712297861267946418L;

  @ApiModelProperty(
    value =
        "ID - A system generated number used to uniquely identify each CLIENT_ADDRESS. "
            + "This ID is composed of a base 62 Creation Timestamp and the STAFF_PERSON ID "
            + "(a sequential 3 digit base 62 number generated by the system). This value eliminates "
            + "the need for an additional set of Creation Timestamp and Creation User ID which is "
            + "needed to satisfy the Audit Trail requirement.",
    example = "Ab7lSwF0Hy"
  )
  private String id;

  @ApiModelProperty(
    value = "Address resource. The place where a CLIENT may be located and/or communicated with."
  )
  private AddressDTO address;

  @ApiModelProperty(
    value =
        "ADDRESS_TYPE - The system generated number which identifies the type of ADDRESS "
            + "(e.g., business, home, mailing, etc.) at which a CLIENT may be contacted."
            + "Additional info can be reached via dictionaries resource by 'ADDR_TPC' key.",
    example = "32"
  )
  private Short addressType;

  @ApiModelProperty(
    value =
        "BOOKING_OR_INMATE_ID - The Booking ID associated with CLIENTs who are in short term "
            + "prisons (e.g. County Jail) or the Inmate ID associated with CLIENTs who are in longer "
            + "term prisons (e.g. State/Federal prisons).",
    example = "2190132741"
  )
  private String bookingOrInmateId;

  @ApiModelProperty(
    value =
        "EFFECTIVE_END_DATE - The date the CLIENT moved out of or stopped using the "
            + "specified ADDRESS.",
    example = "2005-12-09"
  )
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate effectiveEndDate;

  @ApiModelProperty(
    value = "EFFECTIVE_START_DATE - The date the CLIENT occupied the specific ADDRESS.",
    example = "1999-09-14"
  )
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate effectiveStartDate;

  @ApiModelProperty(
    value =
        "HOMELESS_INDICATOR - Indicates this CLIENT_ADDRESS originated from a REFERRAL that "
            + "identified the referral's address as homeless.",
    example = "false"
  )
  private Boolean homelessIndicator;

  @ApiModelProperty(
    value = "FKCLIENT_T - Mandatory Foreign key that IS_OCCUPIED_BY a CLIENT.",
    example = "GmNMeSx0Hy"
  )
  private String clientId;

  @ApiModelProperty(
    value = "FKREFERL_T - Optional Foreign key that IS_DERIVED_FROM a REFERRAL.",
    example = "GHnnNq70Hy"
  )
  private String referralId;
}
