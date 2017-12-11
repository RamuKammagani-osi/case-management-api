package gov.ca.cwds.cm.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cm.service.dictionaries.AssignmentType;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.validation.Date;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** @author CWDS TPT-3 Team */
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReferralDTO extends BaseDTO implements Response {

  private static final long serialVersionUID = 4504908645901963401L;

  @NotNull
  @ApiModelProperty(
      value =
          "ID - A system generated number used to uniquely  identify each Referral.    This ID has an internal "
              + "10 digit alpha-numeric and an external 19 digit numeric representation.",
      example = "0YIPkZU0S0"
  )
  private String identifier;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value =
          "ADDITIONAL_INFO_INCLUDED_CODE - Indicates that additional obtained information  "
              + "invalidates the allegation.  This attribute is part of the ER Protocol.",
      example = "a"
  )
  private Boolean additionalInfoIncludedCode;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "ANONYMOUS_REPORTER_IND - Indicates whether the REPORTER refuses to identify"
          + " him/herself. If yes, no REPORTER (e.g., name, address, etc.) is recorded.",
      example = "N"
  )
  private Boolean anonymousReporterIndicator;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "APPLICATION_FOR_PETITION_IND - This indicates whether the REFERRAL was generated "
          + "as a result of an Application for Petition.  If so, the corresponding Feedback "
          + "Required Code attribute value from the REPORTER entity type should be set to 'Y'",
      example = "Y"
  )
  private Boolean applicationForPetitionIndicator;

  @Size(max = 10)
  @ApiModelProperty(
      value = "APPROVAL_NUMBER - A non-unique number generated by the system when each Referral "
          + "Response was submitted for approval for the first time. This number will be stored "
          + "(uniquely) in this entity as a cross reference ID to the SUPERVISOR_APPROVAL entity."
          + "For example, when a particular Referral Response was submitted for approval the first"
          + "time, an occurrence of SUPERVISOR_APPROVAL will be created with the Approval Number "
          + "of '100'. This number will also be stored in the REFERRAL entity for this particular "
          + "instance. When the supervisor disapproves this request due to further work required, "
          + "the social worker may re-submit this request after modification. At that time, "
          + "another occurrence of SUPERVISOR_APPROVAL will be generated with this same number "
          + "'100'. Based on the action taken by the supervisor, the request may need to be "
          + "re-submitted and new occurrences created with the same number '100' for the "
          + "SUPERVISOR_APPROVAL over and over again, until the final Approval Status is set "
          + "to 'Approved' on the REFERRAL entity. This is a physical implementation, which will "
          + "save storage space over a logical implementation, by not storing all the different "
          + "columns of foreign keys (relationships to all entity types which requires approval) "
          + "in the SUPERVISOR_APPROVAL entity type. In reality, only one of the total number of "
          + "foreign keys in the SUPERVISOR_APPROVAL entity type will have a value, when the "
          + "remaining columns will be blank. This number will be generated by the same routine "
          + "which generates all IDs for other entity types in the CWS system.",
      example = "D5YRVOm0Hj"
  )
  private String approvalNumber;

  @NotNull
  @Size(max = 4)
  @ApiModelProperty(
      value = "APPROVAL_STATUS_TYPE - The system generated number assigned to each type of status "
          + "associated with the approval request (e.g. Pending Approval, Requires Modification, "
          + "Rejected, etc). This status type can be set by either the social worker or the "
          + "supervisor depending on the stage of approval process the request is in. For example, "
          + "when the social worker submits the request for approval, the Approval Status Type "
          + "will be changed to 'Pending Approval' from 'Request Not Submitted', at which point no "
          + "social worker modification can be made to the object submitted for approval. If the "
          + "supervisor disapproves the request due to further work required, the Approval Status "
          + "Type will then be changed to 'Modification Required'. The social worker may then "
          + "modify the object before re-submission.",
      example = "118"
  )
  private Short approvalStatusType;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "CARETAKERS_PERPETRATOR_CODE - Indicates screener believes that the caretaker was "
          + "the perpetrator or provided perpetrator access to the child.  This attribute is part "
          + "of the ER Protocol.",
      example = "Y"
  )
  private Boolean caretakersPerpetratorCode;

  @NotNull
  @Date
  @ApiModelProperty(
      value = "CLOSURE_DATE - The date when the referral is considered closed.  (e.g., when all "
          + "allegations have had dispositions, and each referral client under 18 has had a "
          + "recommendation for further (or no) services).",
      example = "1999-07-19"
  )
  private LocalDate closureDate;

  @NotNull
  @Size(max = 4)
  @ApiModelProperty(
      value = "COMMUNICATION_METHOD_TYPE - The system generated number assigned to each means of "
          + "communication between the CWS OFFICE and the REPORTER, or the method upon which "
          + "CONTACT was made or attempted (e.g., written, telephone, or in-person).",
      example = "409"
  )
  private Short communicationMethodType;

  @NotNull
  @Size(max = 2)
  @ApiModelProperty(
      value = "COUNTY_SPECIFIC_CODE - A code, with values between '01' and '58' or '99' that "
          + "indicates which county has primary assignment responsibility for the CASE or REFERRAL "
          + "that this row belongs to. The value for each county is identical to the Logical ID "
          + "value for the county in the Government_Entity_Type code table.",
      example = "57"
  )
  private String countySpecificCode;

  @Size(max = 10)
  @ApiModelProperty(
      value = "CURRNT_LOC_OF_CHILDREN_TEXT - A short narrative describing the reporter's knowledge "
          + "of the location(s) of the \"at risk\" children at the time of the report.  This is "
          + "the ID from the LONG TEXT entity type which uniquely identifies a specific occurrence "
          + "of user defined text.",
      example = "4rne9jh0Ki"
  )
  private String currentLocationOfChildren;

  @Size(max = 10)
  @ApiModelProperty(
      value = "DRMS_ALLEGATION_DESCRIPTION_DOC - A description of the allegation(s) as provided by "
          + "the REPORTER.  This could include the date of the alleged incident(s) and where the "
          + "incidents occurred if known by the REPORTER, and any COLLATERAL INDIVIDUALs who may "
          + "have additional information.    This is the ID from the DOCUMENT entity type which "
          + "identifies a unique Document within DRMS.",
      example = "C6pexHk0Ki"
  )
  private String drmsAllegationDescriptionDoc;

  @Size(max = 10)
  @ApiModelProperty(
      value = "DRMS_ER_REFERRAL_DOC - A report which is an organized listing of the  REPORTER "
          + "details, Victim CLIENT details, ALLEGATION details, History of Prior REFERRALs or "
          + "CASEs, and the Screener's notes.    This is the ID from the DOCUMENT entity type "
          + "which identifies a unique Document within DRMS.",
      example = "BVKKzjE0Ki"
  )
  private String drmsErReferralDoc;

  @Size(max = 10)
  @ApiModelProperty(
      value = "DRMS_INVESTIGATION_DOC - A free form narrative describing the social,  cultural, "
          + "psychological and physical factors relevant to the endangerment, including a history "
          + "of previous intervention by the CWS agency and its outcome; family strengths and "
          + "skills; alternatives to removal of the child from the home; and whether appropriate "
          + "services are available which could make removal of the child unnecessary.  This can "
          + "include the  preliminary evaluation of risk following the use of a Risk Assessment "
          + "Tool.This is the ID from the DOCUMENT entity type which identifies a unique Document "
          + "within DRMS.",
      example = "Atp12Ai0Ki"
  )
  private String drmsInvestigationDoc;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "FAMILY_AWARENESS_IND - Regulatory requirement to indicate whether the  reporter "
          + "elieves the family is aware of the  REFERRAL.",
      example = "N"
  )
  private Boolean familyAwarenessIndicator;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "FAMILY_REFUSED_SERVICES_IND - Indicates whether the family refused services for"
          + "the REFERRAL.",
      example = "N"
  )
  private Boolean familyRefusedServicesIndicator;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "FILED_CRSS_RPT_WITH_LAW_ENF_IND - Indicates whether the reporting party has already "
          + "filed a Suspected Child Abuse Report to LAW ENFORCEMENT, et. al., relieving the "
          + "Child Welfare Department of its obligation to file that CROSS REPORT",
      example = "N"
  )
  private Boolean filedSuspectedChildAbuseReporttoLawEnforcementIndicator;

  @Date
  @ApiModelProperty(
      value = "FIRST_EO_APPROVAL_DATE - The date the referral was first closed as Evaluated Out.",
      example = "2000-05-05"
  )
  private LocalDate firstEvaluatedOutApprovalDate;

  @Size(max = 10)
  @ApiModelProperty(
      value = "FKADDRS_T - Optional Foreign key that ALLEGES_ABUSE_OCCURRED_AT a ADDRESS.",
      example = "7tLiCl30Ki"
  )
  private String allegesAbuseOccurredAtAddressId;

  @Size(max = 10)
  @ApiModelProperty(
      value = "FKADDRS_T - Optional Foreign key that ALLEGES_ABUSE_OCCURRED_AT a ADDRESS.",
      example = "732iCl30Ki"
  )
  private String linkToPrimaryReferralId;

  @Size(max = 3)
  @ApiModelProperty(
      value = "FKSTFPERS0 - Optional Foreign key that HAS_FIRST_RESPONSE_DETERMINED_BY a "
          + "STAFF_PERSON.",
      example = "abc"
  )
  private String firstResponseDeterminedByStaffPersonId;

  @NotNull
  @Size(max = 3)
  @ApiModelProperty(
      value = "FKSTFPERST - Mandatory Foreign key that HAS_AS_THE_PRIMARY_CONTACT a STAFF_PERSON.",
      example = "04Z"
  )
  private String primaryContactStaffPersonId;

  @NotNull
  @Size(max = 4)
  @ApiModelProperty(
      value = "GOVERNMENT_ENTITY_TYPE - The system generated number which represents  the specific "
          + "county (e.g., Alameda, Fresno,  Merced, Sacramento, etc.) within the state of  "
          + "California to which a specific REFERRAL is  assigned.",
      example = "1092"
  )
  private Short govtEntityType;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "HOMELESS_INDICATOR - Indicates whether the REFERRAL's address is homeless.",
      example = "Y"
  )
  private Boolean homelessIndicator;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "LEGAL_DEFINITION_CODE - Indicates that at least one allegation meets the  legal "
          + "definition of abuse.  This attribute is part of the ER Protocol.",
      example = "a"
  )
  private String legalDefinitionCode;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "LEGAL_RIGHTS_NOTICE_IND - Indicates that the parents/guardians of the REFERRAL "
          + "children have been notified \"Y\" of their legal rights as recipients of child "
          + "welfare services upon the determination that further services has been recommended "
          + "for one or more of their children.",
      example = "Y"
  )
  private Boolean legalRightsNoticeIndicator;

  @Size(max = 1)
  @ApiModelProperty(
      value = "LIMITED_ACCESS_CODE - Indicates whether a REFERRAL is marked as sensitive (S), "
          + "sealed (R), or no restriction (N).  This will be used to determine the security "
          + "access level.",
      example = "S"
  )
  private String limitedAccessCode;

  @Date
  @ApiModelProperty(
      value = "LIMITED_ACCESS_DATE - The date that a case or referral was marked sealed, sensitive "
          + "or non-limited.",
      example = "2000-05-05"
  )
  private LocalDate limitedAccessDate;

  @Size(max = 254)
  @ApiModelProperty(
      value = "LIMITED_ACCESS_DESC - General narrative text which records details of a case or "
          + "referral which has been marked sealed, sensitive or non-limited.",
      example = "description"
  )
  private String limitedAccessDesc;

  @Size(max = 4)
  @ApiModelProperty(
      value = "LIMITED_ACCESS_GOVERNMENT_ENTITY_TYPE - The system generated number which "
          + "represents the specific county (e.g. Sacramento, Yolo, Butte, etc) within the State "
          + "of California of the logged in user that changed the Access Rights for Case/Referral. "
          + "The selection choices will be provided by the Government Entity Type code table, "
          + "therefore this attribute will store the SysId of the chosen value.",
      example = "1234"
  )
  private Short limitedAccessGovtAgencyType;

  @Date
  @ApiModelProperty(
      value = "MANDATED_CROSS_RPT_RECEIVED_DATE - The date that a written Suspected Child Abuse  "
          + "Report (form SS8572) was received from a  mandated REPORTER by the CWS agency.",
      example = "2000-05-05"
  )
  private LocalDate mandatedCrossReportReceivedDate;

  @NotNull
  @Size(max = 35)
  @ApiModelProperty(
      value = "NAME - The name which can be used for easy retrieval of a REFERRAL instead of "
          + "the ID.",
      example = "Name"
  )
  private String referralName;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "OPEN_ADEQUATE_CASE_CODE - Indicates an open service case adequately addresses the "
          + "problem described in the allegation. This attribute is part of the ER Protocol.",
      example = "b"
  )
  private String openAdequateCaseCode;

  @Date
  @ApiModelProperty(
      value = "ORIGINAL_CLOSURE_DATE - The original date this referral was closed. Subsequent "
          + "closure of this referral will set the Closure Date in REFERRAL_REOPEN_HISTORY, "
          + "instead of this attribute.",
      example = "2000-05-05"
  )
  private LocalDate originalClosureDate;

  @NotNull
  @Date
  @ApiModelProperty(
      value = "RECEIVED_DATE - The date the reported incident (REFERRAL) was  received by a "
          + "STAFF PERSON.  This will be defaulted to the system date.",
      example = "2000-05-05"
  )
  private LocalDate receivedDate;

  @Size(max = 8)
  @NotNull
  @ApiModelProperty(
      value = "RECEIVED_TIME - The time of day the reported incident (REFERRAL)  "
          + "was received by a STAFF PERSON.  This will be  defaulted to the system tim",
      example = "16:41:49"
  )
  private Instant receivedTime;

  @Size(max = 4)
  @NotNull
  @ApiModelProperty(
      value = "REFERRAL_RESPONSE_TYPE - The system generated number which identifies the  first "
          + "determined response (e.g., immediate, 10 days, etc.) assigned to the REFERRAL.",
      example = "1620"
  )
  private Short referralResponseType;

  @Size(max = 4)
  @NotNull
  @ApiModelProperty(
      value = "REFERRED_TO_RESOURCE_TYPE - The system generated number assigned to each type of "
          + "public or private agency to which a REFERRAL can be referred when the REFERRAL is "
          + "evaluated out (e.g., Adoption, Eating Disorders, Law Enforcement, Licensing, etc.).",
      example = "1620"
  )
  private Short referredToResourceType;

  @Date
  @ApiModelProperty(
      value = "RESPONSE_DETERMINATION_DATE - The date the screener assigns a specific "
          + "response type to a REFERRAL.",
      example = "2000-05-05"
  )
  private LocalDate responseDeterminationDate;

  @Size(max = 8)
  @NotNull
  @ApiModelProperty(
      value = "RESPONSE_DETERMINATION_TIME - The time of day when the screener assigns a specific "
          + "response type to a REFERRAL.",
      example = "16:41:49"
  )
  private Instant responseDeterminationTime;

  @Size(max = 10)
  @ApiModelProperty(
      value = "RESPONSE_RATIONALE_TEXT - A narrative description of the reason for assigning a "
          + "selected response type to a particular REFERRAL and is mandatory for an evaluated out "
          + "response type.  This is the ID from the LONG TEXT entity type which uniquely "
          + "identifies a specific occurrence of user defined text.",
      example = "Rsdasdc33"
  )
  private String responseRationaleText;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "RESPONSIBLE_AGENCY_CODE - This code represents the agency or department that is "
          + "responsible for the REFERRAL. The valid values are County Welfare Department (C), "
          + "Probation (P), Out of State Agency (O), Private Adoption Agency (A), State Adoption "
          + "District Office (S), Indian Child Welfare (I), Kin-Gap (K), or Mental Health (M).",
      example = "I"
  )
  private String responsibleAgencyCode;

  @Size(max = 10)
  @ApiModelProperty(
      value = "SCREENER_NOTE_TEXT - A narrative description related to this specific incident "
          + "of abuse.  This is the additional information that was not documented in the "
          + "Allegation Description and is recorded here by the screener as additional details "
          + "for the assigned worker.  (e.g., the screener's assessment of  the creditability of "
          + "the REPORTER.)  This is the ID from the LONG TEXT entity type which uniquely "
          + "identifies a specific occurrence of user defined text.",
      example = "II9ssYqu0Ki"
  )
  private String screenerNoteText;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "SPECIAL_PROJECT_REFERRAL_IND - This indicator variable is used to indicate if "
          + "there are any occurrences of SPECIAL_PROJECTs related to this REFERRAL. This will "
          + "save unnecessary processing time from searching for information that does not exist "
          + "in the database.",
      example = "Y"
  )
  private Boolean specialProjectReferralIndicator;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "SPECIFICS_INCLUDED_CODE - Indicates specific acts and/or behavior were "
          + "included in allegation. This attribute is part of the ER Protocol.",
      example = "A"
  )
  private String specificsIncludedCode;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "SUFFICIENT_INFORMATION_CODE - Indicates if there were sufficient information to "
          + "locate the family.  This attribute is part of the ER Protocol.",
      example = "A"
  )
  private String sufficientInformationCode;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "UNFOUNDED_SERIES_CODE - Indicates allegation is one in a series of previously "
          + "investigated, non-substantiated reports from the same party with no new allegations "
          + "made.  This attribute is part of the ER Protocol.",
      example = "A"
  )
  private String unfoundedSeriesCode;

  @NotNull
  @Size(max = 1)
  @ApiModelProperty(
      value = "ZIPPY_CREATED_IND - Indicates whether or not this REFERRAL was created from within "
          + "the Zippy Referral notebook as opposed to the traditional REFERRAL notebook.",
      example = "N"
  )
  private Boolean zippyCreatedIndicator;

  @NotNull
  @ApiModelProperty(
      value = "Referral type from ASSIGNMENT one of Primary or Secondary",
      example = "PRIMARY"
  )
  private AssignmentType assignmentType;

}
