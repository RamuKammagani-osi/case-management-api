package gov.ca.cwds.cm.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.rest.api.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author CWDS TPT-3 Team
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel(
    description = "The collection of all logical code tables identified during various analysis "
        + "tracks into one standard format physical table."
)
public class SystemCodeDTO extends BaseDTO implements Response {
  private static final long serialVersionUID = -1020610874863016499L;

  @ApiModelProperty(
      value = "SYSTEM_ID - The system generated unique number used to identify each permitted "
          + "values within the entire physical code table.",
      example = "612"
  )
  private Short systemId;

  @ApiModelProperty(
      value = "FKS_META_T - Mandatory Foreign key that IS_THE_CONTENT_OF a SYSTEM_META_TABLE.",
      example = "CRTFNDGC"
  )
  private String metaCode;

  @ApiModelProperty(
      value = "CATEGORY_ID - For tables with categories, the 4 digit identifier of the category "
          + "entry for which a specific table value is subordinate to.  For example, in the Client "
          + "Condition Type Logical Table, the three categories (Behavioral, Emotional, Physical "
          + "Health) might each have a unique System ID of 1, 2, and 3.  All the  subordinate "
          + "values to each one of these 3 categories will have a Category ID of 1, 2, or 3.",
      example = "0"
  )
  private Short categoryId;

  @ApiModelProperty(
      value = "USER_DEFINED_LOGICAL_ID - A 4 character code which can be used to identify "
          + "a user-defined characteristic of the code table row to which it belongs. In the event "
          + "that the user has no specific value requirements for this field, it will be generated "
          + "by the system. Each code with a length less than 4 should be left-justified and padded "
          + "to the right with blanks. This code can be used for sorting purpose or for quick "
          + "reference of each value within the logical grouping of code tables identified during "
          + "the various analysis tracks such as 01 through 58 for counties, CA, FL, for States, etc.",
      example = "0650"
  )
  private String logicalId;

  @ApiModelProperty(
      value = "THIRD_ID - This is a system generated unique number that supplements user supplied "
          + "data in the primary key. The Third ID attribute will be used as part of the new "
          + "Primary Key in combination with user supplied data. It will also be used alone as "
          + "a separate key for direct access. This Third ID is composed of a base 62 Creation "
          + "Timestamp and the STAFF_PERSON ID (a sequential 3 digit base 62 number generated "
          + "by the system).",
      example = "0000000000"
  )
  private String thirdId;

  @ApiModelProperty(
      value = "OTHER_CODE - A 2 character code that can be used to specify additional information "
          + "for a logical code table. This is the user recognizable name which will primarily be "
          + "used for display purposes in code table maintenance. For example, the Government "
          + "Entity Type logical code table, which contains a list of counties, and a CDS County "
          + "Indicator. This 'CDS County Indicator' will be entered as the title for the "
          + "'Other Code' on the form. For all logical code tables which have an additional code "
          + "attribute (exclude the higher level Category code), the name of that attribute will "
          + "be entered as the value of 'Other Code Name' in the Physical System Code Table entity, "
          + "and will be displayed as the title on the code table maintenance form.",
      example = "01"
  )
  private String otherCode;

  @ApiModelProperty(
      value = "INACTIVE_IND - An indicator to specify whether a value in the logical table is "
          + "inactive and can only be used for either Conversion or Interface batch update (Y) "
          + "or it is active and available for drop down selection on the screen (N).",
      example = "N"
  )
  private Boolean inactiveIndicator;

  @ApiModelProperty(
      value = "SHORT_DESCRIPTION - A mandatory 40 character text field that can be used in screen "
          + "list boxes, drop-down boxes, etc. (e.g., Butte, Fresno, Los Angeles, etc. for "
          + "the various counties within the state of California).",
      example = "Notice Not Given As Required By Law"
  )
  private String shortDescription;

  @ApiModelProperty(
      value = "LONG_DESCRIPTION - A 160 character text field that can appear on screens as "
          + "read-only text fields and can be used to populate MS Word documents (case plans, "
          + "Court documents, etc.).",
      example = "Notice Not Given As Required By Law"
  )
  private String longDescription;

}
