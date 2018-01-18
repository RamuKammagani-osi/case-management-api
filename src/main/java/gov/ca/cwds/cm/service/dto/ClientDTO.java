package gov.ca.cwds.cm.service.dto;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cm.RequestResponse;
import io.dropwizard.validation.OneOf;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** @author CWDS TPT-3 Team */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@SuppressWarnings({"squid:S3437"})
public class ClientDTO extends BaseDTO implements RequestResponse {

  private static final long serialVersionUID = -4098613951159302301L;

  @NotNull
  @ApiModelProperty(
      value =
          "ID - A system generated number used to uniquely  identify each CLIENT.    This ID has an internal "
              + "10 digit alpha-numeric and an external 19 digit numeric representation.",
      example = "0YIPkZU0S0"
  )
  private String identifier;

  @NotNull
  @OneOf(
      value = {"TOTALLY_FREE", "PARTIALLY_FREE", "NOT_FREE", "NOT_APPLICABLE"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  @ApiModelProperty(
      value =
          "ADOPTION STATUS CODE - Indicates whether a child CLIENT is \"totally\" free (T), or \"partially\" free (P),"
              + " \"not free\" (N), or \"Not Applicable (A) from all parents.  This indicator will simplify the SOC 158"
              + " reporting process.  However, this is a user maintain attribute and it may not be up-to-date at all "
              + "time.",
      example = "TOTALLY_FREE"
  )
  private String adoptionStatusCode;

  @NotNull
  @ApiModelProperty(
      value =
          "ALIEN REGISTRATION NUMBER - A unique identification number issued by the INS for each person with a "
              + "non-citizen status",
      example = "111483672"
  )
  private String alienRegistrationNumber;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(value = "BIRTH_DATE - Date of birth of the CLIENT.", example = "1944-01-30")
  private LocalDate birthDt;

  @NotNull
  @ApiModelProperty(
      value = "BIRTH FACILITY NAME - The name of the facility where the CLIENT was born.",
      example = "San Jose Medical Center"
  )
  private String birthFacilityName;

  @NotNull
  @ApiModelProperty(
      value =
          "BIRTH COUNTRY CODE TYPE - The system number which identifies the Country where the CLIENT was born ",
      example = " "
  )
  private Short birthCountryCode;

  @NotNull
  @ApiModelProperty(
      value =
          "BIRTH STATE CODE TYPE - The system number which identifies the State where the CLIENT "
              + "was born (e.g., California, Hawaii, Texas, etc.).",
      example = "1828"
  )
  private Short birthStateCodeType;

  @NotNull
  @ApiModelProperty(
      value =
          "CHILD CLIENT IND VAR - This indicator variable is used to indicate if there are any "
              + "occurrences of CHILD CLIENTs related to this CLIENT.    This will save unnecessary "
              + "processing time from  searching for information that does not exist in the data base.",
      example = "N"
  )
  private Boolean childClientIndVar;

  @Size(max = 20)
  @NotNull
  @ApiModelProperty(
      value =
          "COMMON FIRST NAME - The first name commonly used to refer to a CLIENT. This is not necessarily "
              + "the CLIENT's legal name, it is simply the name by which the CLIENT is referred to by "
              + "family members, friends, and CWS staff.",
      example = "Monica"
  )
  private String commonFirstName;

  @Size(max = 25)
  @NotNull
  @ApiModelProperty(
      value =
          "CCOMMON LAST NAME - The last name commonly used to refer to a CLIENT.  This is not necessarily "
              + "the CLIENT's legal name, it is simply the name by which the CLIENT is referred to by "
              + "family members, friends, and CWS staff.",
      example = "Pain"
  )
  private String commonLastName;

  @Size(max = 20)
  @NotNull
  @ApiModelProperty(
      value =
          "COMMON MIDDLE NAME - The middle name commonly used to refer to a CLIENT.  This is not "
              + "necessarily the CLIENT's legal name, it is simply the name by which the CLIENT is "
              + "referred to by family members, friends, and CWS staff.",
      example = "Jacobson"
  )
  private String commonMiddleName;

  @NotNull
  @ApiModelProperty(
      value =
          "CONFIDENTIALITY IN EFFECT IND - The date that confidentiality goes into effect or is no longer "
              + "in effect for a CLIENT.  This may include the date that a Non Disclosure Order is "
              + "granted, denied, or rescinded, but does not always have to be initiated by a "
              + "Non Disclosure Order request.",
      example = "N"
  )
  private Boolean confidentialityInEffectInd;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(
      value =
          "CONFIDENTIALITY ACTION DATE - This indicates whether or not confidentiality is currently in effect for "
              + "this CLIENT. The confidentiality may be a result of a non disclosure order, or a social worker's "
              + "judgment call on an individual case to protect a CLIENT.",
      example = "2004-06-14"
  )
  private LocalDate confidentialityActionDate;

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(
      value =
          "CREATION DATE - The date when a new client entry is entered into the CWS system.  "
              + "This should be defaulted to the system date.",
      example = "1999-08-15"
  )
  private LocalDate creationDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(value = "DEATH_DATE - The date the CLIENT passed away.", example = "1999-08-15")
  private LocalDate deathDate;

  @Size(max = 10)
  @ApiModelProperty(
      value =
          "DEATH_REASON_TEXT - Cause of death for the CLIENT.  This is the ID from the LONG TEXT entity "
              + "type which uniquely identifies a specific occurrence of user defined text.",
      example = "U8SxGBF00c"
  )
  private String deathReason;

  @NotNull
  @Size(max = 20)
  @ApiModelProperty(
      value = "DRIVER_LICENSE_NUMBER - The driver license number of the CLIENT.",
      example = "987654321"
  )
  private String driverLicensNumber;

  @NotNull
  @ApiModelProperty(
      value =
          "DRIVER LICENSE STATE CODE TYPE - The system generated number which identifies the State where "
              + "the CLIENT's Driver License was issued (e.g. California, Hawaii, Texas, etc).",
      example = "1828"
  )
  private Short driverLicenseStateCodeType;

  @NotNull
  @ApiModelProperty(
      value =
          "GENDER_CODE - Indicates the gender of a CLIENT",
      example = "MALE"
  )
  @OneOf(
      value = {
          "FEMALE",
          "MALE",
          "UNKNOWN"
      },
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String genderCode;

  @NotNull
  @ApiModelProperty(
      value =
          "IMMIGRATION COUNTRY CODE TYPE - The system generated number which identifies the  Country of "
              + "origin for a specific CLIENT (e.g., United States, China, Mexico, etc.).",
      example = "563"
  )
  private Short immigrationCountryCodeType;

  @NotNull
  @ApiModelProperty(
      value =
          "IMMIGRATION STATUS TYPE - The system generated number which identifies the type of residency "
              + "status for the CLIENT (e.g., refugee, alien, or US citizen, etc.).",
      example = "1199"
  )
  private Short immigrationStatusType;

  @NotNull
  @ApiModelProperty(
      value =
          "INCAPACITATED PARENT CODE - This indicates if the parent CLIENT is incapacitated.  This "
              + "information is passed along to the eligibility worker when determining eligibility of "
              + "the child for federal aid.",
      example = "NO"
  )
  @OneOf(
      value = {
          "NO",
          "NOT_APPLICABLE",
          "UNKNOWN",
          "YES"
      },
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String incapacitatedParentCode;

  @NotNull
  @ApiModelProperty(
      value =
          "LITERATE_CODE - Indicates whether the CLIENT is in his or her primary language literate (Y), "
              + "illiterate (N), or the social worker doesn't know for sure (U), "
              + "or it is not applicable (D).",
      example = "NOT_APPLICABLE"
  )
  @OneOf(
      value = {"NOT_APPLICABLE", "ILLITERATE", "UNKNOWN", "LITERATE"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String litrateCode;

  @NotNull
  @ApiModelProperty(
      value =
          "MARITAL COHABITATN HSTRY IND VAR - This indicator variable is used to indicate if there are any "
              + "occurrences of MARITAL COHABITATION HISTORY related to this CLIENT.    "
              + "This will save unnecessary processing time from  searching for information that "
              + "does not exist in the data base.",
      example = "N"
  )
  private Boolean maritalCohabitatnIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "MARITAL STATUS TYPE - The system generated number which identifies the type of marital status for "
              + "the CLIENT (e.g., single, married, divorced, etc.).",
      example = "1308"
  )
  private Short materialStatusType;

  @NotNull
  @ApiModelProperty(
      value =
          "MILITARY STATUS CODE - This indicates whether a child CLIENT is military dependent (D), or an "
              + "adult CLIENT is military active (A), or an adult CLIENT is a veteran (V), or the CLIENT "
              + "has no military involvement (N), or no information are available (U).",
      example = "NO_INFORMATION_AVAILABLE"
  )
  @OneOf(
      value = {
          "NO_INFORMATION_AVAILABLE",
          "MILITARY_ACTIVE",
          "MILITARY_DEPENDENT",
          "NO_MILITARY_INVOLVEMENT",
          "VETERAN"
      },
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String militaryStatusCode;

  @Size(max = 6)
  @NotNull
  @ApiModelProperty(
      value =
          "NAME PREFIX DESCRIPTION - The salutation form to be used in the mailing address of a "
              + "CLIENT (e.g., Mr., Ms., Mrs., Dr., Miss, Rev., etc.).",
      example = "Mr"
  )
  private String namePrefixDescription;

  @NotNull
  @ApiModelProperty(
      value =
          "NAME TYPE - The system generated number which identifies the type of NAME "
              + "for a CLIENT (e.g., legal, AKA, etc.).",
      example = "1313"
  )
  private Short nameType;

  @NotNull
  @ApiModelProperty(
      value =
          "OUTSTANDING WARRANT IND - Indicates whether there is an outstanding warrant "
              + "for this particular child (Y) or not (N).",
      example = "Y"
  )
  private Boolean outstandingWarrantIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "PRIMARY ETHNICITY TYPE - The system generated number which identifies the  primary "
              + "ethnicity for the CLIENT (e.g., Native  American, Asian, Hispanic, Black, etc.).",
      example = "841"
  )
  private Short primaryEthnicityType;

  @NotNull
  @ApiModelProperty(
      value =
          "PRIMARY LANGUAGE TYPE - The system generated number which identifies the  primary language "
              + "spoken by a specific CLIENT (e.g., English, Spanish, Chinese, etc.).",
      example = "33"
  )
  private Short primaryLanguageType;

  @NotNull
  @ApiModelProperty(
      value =
          "RELIGION TYPE - The system generated number which identifies the  specific religion group the "
              + "CLIENT is affiliated with (e.g., Baptist, Christian, Jewish, etc.).",
      example = "33"
  )
  private Short religionType;

  @NotNull
  @ApiModelProperty(
      value =
          "SECONDARY LANGUAGE TYPE - The system generated number which identifies the  secondary language "
              + "spoken by a specific CLIENT (e.g., English, Spanish, Chinese, etc.).",
      example = "11"
  )
  private Short secondaryLanguageType;

  @NotNull
  @ApiModelProperty(
      value =
          "SENSITIVITY INDICATOR - This indicates whether or not the associated information for this CLIENT "
              + "is sensitive.  This means that for various reasons information about this client is "
              + "restricted to authorized individuals only.",
      example = "NOT_APPLICABLE"
  )
  @OneOf(
      value = {"NOT_APPLICABLE", "SENSITIVE", "SEALED"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String sensivityIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "SENSITIVE HLTH INFO ON FILE IND - Indicates whether sensitive health information is being stored "
              + "in a social worker's file rather than on the system.",
      example = "Y"
  )
  private Boolean sensivityHealthInfoOnFileIndicator;

  @Size(max = 9)
  @NotNull
  @ApiModelProperty(
      value = "SOCIAL SECURITY NUMBER - The Social Security Number for the CLIENT.",
      example = "1111111111"
  )
  private String socialSecurityNumber;

  @Size(max = 1)
  @NotNull
  @ApiModelProperty(
      value = "SOCIAL SECURITY NUM CHANGED CODE - This is an unused attribute. The default is N.",
      example = "N"
  )
  private String socialSecurityNumberChangedCode;

  @Size(max = 4)
  @NotNull
  @ApiModelProperty(
      value =
          "SUFFIX TITLE DESCRIPTION - The suffix name of a CLIENT (e.g., Esq., M.D., Ph.D., D.D.S., etc.).",
      example = "D.D.S."
  )
  private String suffixTitleDescription;

  @NotNull
  @ApiModelProperty(
      value =
          "UNEMPLOYED PARENT CODE - This indicates if the parent CLIENT is unemployed.  This information "
              + "is passed along to the eligibility worker when determining eligibility of "
              + "the child for federal aid.",
      example = "UNKNOWN"
  )
  @OneOf(
      value = {"UNKNOWN", "NO", "NOT_APPLICABLE", "YES"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String unemployedParentCode;

  @NotNull
  @Size(max = 120)
  @ApiModelProperty(
      value =
          "COMMENT DESCRIPTION - A brief description of any unusual circumstances  concerning the CLIENT "
              + "which can not be captured  through the CLIENT CONDITION entity type. "
              + " (e.g., communication difficulty, special religion practices, or any other relevant "
              + "information which could be used in planning services for the CLIENT).",
      example = "Primary Comments for the Primary Client."
  )
  private String commentDescription;

  @NotNull
  @ApiModelProperty(
      value =
          "ESTIMATED DOB CODE - Indicates if the date of birth in the CLIENT entity is estimated (Y) or "
              + "if a birth date was actually entered (N) or has not been provided (U).",
      example = "ESTIMATED"
  )
  @OneOf(
      value = {"ESTIMATED", "NOT_PROVIDED", "ACTUALLY_ENTERED"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String estimatedDobCode;

  @NotNull
  @ApiModelProperty(
      value =
          "BIRTHPLACE VERIFIED IND - Records that the birthplace of the client has been verified by "
              + "means of a birth certificate or other legal document acceptable to the court.",
      example = "N"
  )
  private Boolean birthplaceVerifiedIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "HISPANIC ORIGIN CODE - Records Hispanic origin of the individual client. An 'X' indicates no user "
              + "selection has been made.",
      example = "U"
  )
  @OneOf(
      value = {"DECLINES_TO_STATE", "NO", "UNDETERMINED", "NO_USER_SELECTION", "YES"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String hispanicOriginCode;

  @NotNull
  @ApiModelProperty(
      value =
          "CURR CA CHILDREN SERV IND - This indicator tells the worker if the child was receiving care "
              + "from a California Children Services center. With Release 6.8, this attribute and the "
              + "data contained within are historical, and new values will be stored in "
              + "the DUAL_AGENCY_SERVICES_HISTORY entity.",
      example = "N"
  )
  private Boolean childrenServIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "CURRENTLY REGIONAL CENTER IND - This indicator tells the worker if the child was receiving care "
              + "from a Regional Center. With Release 6.8, this attribute and the data contained within "
              + "are historical, and new valueswill be stored in the DUAL_AGENCY_SERVICES_HISTORY entity.",
      example = "Y"
  )
  private Boolean currentlyRegionalCenteerIndicator;

  @NotNull
  @Size(max = 25)
  @ApiModelProperty(
      value =
          "CURRENTLY OTHER DESCRIPTION - This is a description of where the child was receiving care when "
              + "it is not a CA Children Services Center or Regional Center. With Release 6.8, this "
              + "attribute and the data contained within are historical, and new values will be stored "
              + "in the DUAL_AGENCY_SERVICES_HISTORY entity.",
      example = "QWREQWER"
  )
  private String currentlyOtherDescription;

  @NotNull
  @ApiModelProperty(
      value =
          "PREV CA CHILDREN SERV IND - This indicator tells the worker if the child has previously "
              + "received care from a California Children Services center. With Release 6.8, "
              + "this attribute and the data contained within are historical, and new values will "
              + "be stored in the DUAL_AGENCY_SERVICES_HISTORY entity.",
      example = "N"
  )
  private Boolean previouslyReceivedIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "PREV REGIONAL CENTER IND   - This indicator tells the worker if the child has previously "
              + "received care from a Regional Center. With Release 6.8, this attribute and the data "
              + "contained within are historical, and new values will be stored in the "
              + "DUAL_AGENCY_SERVICES_HISTORY entity.",
      example = "N"
  )
  private Boolean previouslyRegionalCenterIndicator;

  @NotNull
  @Size(max = 25)
  @ApiModelProperty(
      value =
          "PREV OTHER DESCRIPTION  - This is a description of where the child has been previously"
              + "receiving care when it is not a CA Children Services Center or Regional Center. With "
              + "Release 6.8, this attribute and the data contained within are historical, and new values "
              + "will be stored in the DUAL_AGENCY_SERVICES_HISTORY entity.",
      example = " "
  )
  private String previouslyOtherDescription;

  @NotNull
  @ApiModelProperty(
      value =
          "INDIVIDUAL HEALTH CARE PLAN IND - This indicator tells the worker that there is an Individual "
              + "Health Care Plan  on File for a Special Needs Child.",
      example = "N"
  )
  private Boolean healthCarePlanIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "LIMITATION ON SCP HEALTH IND - This indicator tells the worker that there is a limitation put on "
              + "Substitute Care Provider's Ability to make Health Care Decisions for this child.",
      example = "N"
  )
  private Boolean limitationOnSCPHealthIndicator;

  @NotNull
  @Size(max = 35)
  @ApiModelProperty(
      value = "BIRTH CITY - The name of the city or reservation where the CLIENT was born.",
      example = "San Jose"
  )
  private String birthCity;

  @Size(max = 10)
  @ApiModelProperty(
      value =
          "HEALTH SUMMARY TEXT - A brief description of a CLIENT's overall health condition and/or "
              + "information about the location of a CHILD CLIENT's health records.  This is the ID from the LONG "
              + "TEXT entity type which uniquely identifies a specific occurrence of user defined text.",
      example = "0Wqj8l901w"
  )
  private String healthTxt;

  @ApiModelProperty(
      value =
          "MOTHER PARENTAL RIGHT TERM DATE - The date the mothers parental rights were terminated.  "
              + "This is only used by placement episodes created in the SOC158 application. "
              + "The Agency Responsible Types for these episodes include - County Probation Department, "
              + "Indian Child Welfare, Out of State Agency, Private Adoption Agency, and State Adoptions "
              + "District Office.",
      example = " "
  )
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  private LocalDate motherParentalTermDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
  @ApiModelProperty(
      value =
          "FATHER PARENTAL RIGHT TERM DATE - The date the father's parental rights were terminated. "
              + "This is only used by placement episodes created in the SOC158 application. The Agency"
              + "Responsible Types for these episodes include - County Probation Department, Indian Child Welfare"
              + ", Out of State Agency, Private Adoption Agency, and State Adoptions District Office.",
      example = "1999-08-15"
  )
  private LocalDate fatherParentalTermDate;

  @NotNull
  @ApiModelProperty(
      value =
          "ZIPPY CREATED IND - Indicates whether or not this CLIENT was created from within the Zippy "
              + "Referral notebook as opposed to the traditional client notebook.",
      example = "N"
  )
  private Boolean zippyIndicator;

  @Size(max = 35)
  @ApiModelProperty(
      value = "DEATH PLACE - The place of death for the CLIENT.",
      example = "San Jose"
  )
  private String deathPlace;

  @ApiModelProperty(
      value =
          "TRIBAL MEMBRSHP VERIFCTN IND VAR - This indicator variable is used to indicate if there are "
              + "any occurrences of TRIBAL MEMBERSHIP VERIFICATIONs related to this CLIENT. This will save "
              + "unnecessary processing time from searching for information that does not exist in the data base.",
      example = "N"
  )
  private Boolean tribalMemberVerificationIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "TRIBAL ANCESTRY CLIENT IND VAR - This indicator variable is used to indicate if there are any "
              + "occurrences of TRIBAL ANCESTRY CLIENT related to this CLIENT. This will save "
              + "unnecessary processing time from searching for information that does "
              + "not exist in the data base.",
      example = "N"
  )
  private Boolean tribalAncestryClientIndicator;

  @NotNull
  @ApiModelProperty(
      value =
          "SOC158 SEALED CLIENT IND - This indicates whether or not the client's record has been sealed "
              + "in the SOC 158 application. The valid values are Yes (Y), No (N).",
      example = "Y"
  )
  private Boolean soc158Ind;

  @NotNull
  @ApiModelProperty(
      value =
          "DEATH DATE VERIFIED IND - This indicates whether the date of death has been verified. "
              + "The valid values are Yes (Y) and No (N)",
      example = "N"
  )
  private Boolean deathDateVerifiedIndicator;

  @Size(max = 50)
  @ApiModelProperty(
      value = "EMAIL ADDRESS - The e-mail address for the CLIENT.",
      example = "test@test.com"
  )
  private String emailAddr;

  @ApiModelProperty(
      value = "ADJUDICATED DELINQUENT IND - Records that a client has been an adjudicated delinquent",
      example = " "
  )
  private Boolean adjudicatedDelinquentIndicator;

  @ApiModelProperty(
      value =
          "ETH UNABLE TO DET REASON CODE - Records the reason that 'Unable to Determine' was selected as "
              + "the Race/Ethnicity for an individual client."
  )
  private String ethUnableToDeterminCode;

  @ApiModelProperty(
      value =
          "HISP UNABLE TO DET REASON CODE - Records the reason that 'Unable to Determine' was selected as "
              + "the Hispanic or Latino Origin for an individual client.",
      example = "ABANDONMENT"
  )
  @OneOf(
      value = {"ABANDONMENT", "INCAPACITATION", "INDIVIDUAL_DOES_NOT_KNOW"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String hispUnableToDeterminateReasonCode;

  @ApiModelProperty(
      value =
          "SOC_158_PLACEMENT_CODE - This code represents whether the CLIENT has placements that were "
              + "created in the SOC 158 application (Y), has SOC 158 application-created placements that "
              + "were migrated to the Client Services application (M), or does not have any placements "
              + "that were created in the SOC 158 application (N).",
      example = "MIGRATED_TO_CLIENT_SERVICES"
  )
  @OneOf(
      value = {"MIGRATED_TO_CLIENT_SERVICES", "NO_SOC_158_PLACEMENTS", "NOT_YET_MIGRATED"},
      ignoreCase = true,
      ignoreWhitespace = true
  )
  private String soc158PlacementCode;

  @Size(max = 12)
  @ApiModelProperty(
      value = "This number is assigned by the Statewide Client Index (SCI).",
      example = " "
  )
  private String sciIndexNumber;
}
