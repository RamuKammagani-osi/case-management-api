package gov.ca.cwds.cm.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "The place where a CLIENT may be located and/or communicated with.")
public class AddressDTO extends BaseDTO implements Response {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(
    value =
        "ID - A system generated number used to uniquely identify each ADDRESS. This ID is "
            + "composed of a base 62 Creation Timestamp and the STAFF_PERSON ID (a sequential 3 "
            + "digit base 62 number generated by the system). This value eliminates the need for "
            + "an additional set of Creation Timestamp and Creation User ID which is needed to satisfy "
            + "the Audit Trail requirement.",
    example = "AaQshqm0Mb"
  )
  private String id;

  @ApiModelProperty(
    value =
        "DESCRIPTION - Any additional information pertaining to the  ADDRESS which may "
            + "include directions for getting  to the ADDRESS, description of the residence, or "
            + "foreign phone number, as well as the name and hours (e.g., 3pm to 6pm) of the day care "
            + "for the child CLIENT who is at the day care center. If the correspondent ADDRESS TYPE "
            + "value for this  ADDRESS is 'Homeless', the location of where the  CLIENT lives must "
            + "be enter here (e.g., shelter care).",
    example = "Third street on the left"
  )
  private String description;

  @ApiModelProperty(
    value = "CITY_NAME - The name of the city for the ADDRESS.",
    example = "Sacramento"
  )
  private String city;

  @ApiModelProperty(
    value = "EMERGENCY_PHONE_NUMBER - An alternate or emergency phone number for the CLIENT.",
    example = "9168765876"
  )
  private String emergencyPhone;

  @ApiModelProperty(
    value =
        "EMERGENCY_PHONE_EXTENSION_NUMBER - The emergency phone extension number of the CLIENT.",
    example = "523523"
  )
  private String emergencyPhoneExtension;

  @ApiModelProperty(
    value =
        "FOREIGN_ADDRESS_IND_VAR - This indicator variable is used to indicate if there are "
            + "any occurrences of FOREIGN_ADDRESSs related to this ADDRESS. This will save unnecessary "
            + "processing time from searching for information that does not exist in the database.",
    example = "false"
  )
  private Boolean foreignAddressExists;

  @ApiModelProperty(
    value =
        "GOVERNMENT_ENTITY_TYPE - The system generated number which represents the  specific "
            + "county (e.g., Napa, Sacramento, Fresno,  etc.) within the state of California for a "
            + "specific CLIENT's ADDRESS. "
            + "Additional info can be reached via dictionaries resource by 'GVR_ENTC' key.",
    example = "1070"
  )
  private Short governmentEntityCode;

  @ApiModelProperty(
    value = "MESSAGE_PHONE_NUMBER - An alternate or message phone number for the CLIENT.",
    example = "9163452435"
  )
  private String messagePhone;

  @ApiModelProperty(
    value = "MESSAGE_PHONE_EXTENSION_NUMBER - The message phone extension number of the CLIENT.",
    example = "1234"
  )
  private String messagePhoneExtension;

  @ApiModelProperty(
    value =
        "OTHER_HEADER_ADDRESS - Any additional address information which will be placed above "
            + "the standard postal Delivery Address Line. This may include the name of the In Care Of "
            + "person, firm, institution or building to which the piece of mail is directed.",
    example = "PO Box 901234"
  )
  private String otherHeaderAddress;

  @ApiModelProperty(
    value =
        "POST_DIRECTION_TEXT_CODE - The two-letter postal abbreviation following the Street "
            + "Name and Street Suffix which gives directional information about a specific CLIENT's "
            + "ADDRESS for delivery purposes. For example NE (Northeast), SW (Southwest), etc.",
    example = "SW"
  )
  private String postDirectionTextCode;

  @ApiModelProperty(
    value =
        "PRE_DIRECTION_TEXT_CODE - The two-letter postal abbreviation preceding the Street "
            + "Name and Street Suffix which gives directional information about a specific CLIENT's "
            + "ADDRESS for delivery purposes. For example NE (Northeast), SW (Southwest), etc.",
    example = "NE"
  )
  private String preDirectionTextCode;

  @ApiModelProperty(
    value = "PRIMARY_PHONE_NUMBER - The primary phone number including area code of the CLIENT.",
    example = "9167654321"
  )
  private String primaryPhone;

  @ApiModelProperty(
    value = "PRIMARY_PHONE_EXTENSION_NUMBER - The primary phone extension number of the CLIENT.",
    example = "4321"
  )
  private String primaryPhoneExtension;

  @ApiModelProperty(
    value =
        "STATE_CODE_TYPE - The system generated number which identifies the State for a "
            + "specific CLIENT's ADDRESS (e.g. California, Hawaii, Texas, etc). "
            + "Additional info can be reached via dictionaries resource by 'STATE_C' key.",
    example = "1828"
  )
  private Short stateCode;

  @ApiModelProperty(
    name = "street_name",
    value = "Street Name",
    notes =
        "STREET_NAME - The actual name of the street associated with a specific "
            + "CLIENT's ADDRESS. Do not abbreviate if at all possible for matching purposes.",
    example = "Coleman Road"
  )
  private String streetName;

  @ApiModelProperty(
    value =
        "STREET_NUMBER - The street or house number associated with the street name as part "
            + "of the ADDRESS. This may include the fractional or alphabetic modifier "
            + "(e.g. A-17, 119-10, 39.2, 100 1/2, etc).",
    example = "100 1/2"
  )
  private String streetNumber;

  @ApiModelProperty(
    value =
        "STREET_SUFFIX_TYPE - The system generated number assigned to each postal standard "
            + "abbreviation following the Street Name which further identifies the uniqueness "
            + "of the ADDRESS. E.g. Road, Plaza, Square, etc. "
            + "Additional info can be reached via dictionaries resource by 'ST_SFX_C' key.",
    example = "1988"
  )
  private Short streetSuffixCode;

  @ApiModelProperty(
    value =
        "UNIT_DESIGNATOR_TYPE - The system generated number assigned for each standard postal "
            + "abbreviation which further defines the location of a specific CLIENT's ADDRESS "
            + "(e.g. APT (Apartment), BLDG (Building), FL (floor), etc)."
            + "Additional info can be reached via dictionaries resource by 'UNT_DSGC' key.",
    example = "2066"
  )
  private Short unitDesignatorCode;

  @ApiModelProperty(
    value =
        "UNIT_NUMBER - The actual alphanumeric ID following the standard postal abbreviation "
            + "(UNIT DESIGNATOR) which further defines the position of ADDRESS (e.g. 200A, 2C, etc).",
    example = "301"
  )
  private String unitNumber;

  @ApiModelProperty(
    value = "ZIP_NUMBER - The first five digits of the zip code for ADDRESS.",
    example = "95834"
  )
  private String zip;

  @ApiModelProperty(
    value = "ZIP_SUFFIX_NUMBER - The last four digits of the zip code for an ADDRESS.",
    example = "4321"
  )
  private String zipSuffix;
}
