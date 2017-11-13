package gov.ca.cwds.cm.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

/**
 * @author CWDS TPT-3 Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CaseDTO extends BaseDTO{

	private static final long serialVersionUID = 3787452961716324977L;

	private String id;

	@ApiModelProperty(value = "Alert Text", example = "Text")
	private String alertText;

	@Size(max = 10)
	@ApiModelProperty(value = "Approval Number", example = "100")
	private String approvalNumber;

	//approvalStatusType
	@NotNull
	@ApiModelProperty(required = true, value = "Approval Status", example = "100")
	private String approvalStatus;

	//caseClosureReasonType
	@ApiModelProperty(required = true, value = "Approval Status", example = "Child Abducted")
	private String caseClosureReason;

	//caseplanChildrenDetailIndVar
	@NotNull
	@ApiModelProperty(required = true, value = "Caseplan children detail existing indicator.", example = "true")
	private Boolean isCaseplanChildrenDetail;

	@NotNull
	@ApiModelProperty(required = true, value = "Case closure statement text", example = "Text")
	private String closureStatementText;

	//countryCodeType
	@NotNull
	@ApiModelProperty(required = true, value = "Country", example = "US")
	private String country;

	@NotNull
	@ApiModelProperty(required = true, value = "County", example = "Alameda")
	private String countySpecificCode;

	@ApiModelProperty(value = "DRMS notes", example = "Text")
	private String drmsNotesDoc;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
	@ApiModelProperty(value = "The anticipated date the child client will become emancipated.", example = "2018-10-20")
	private LocalDate emancipationDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
	@ApiModelProperty(value = "End date.", example = "2018-10-20")
	private LocalDate endDate;

	//fkchldClt
	@NotNull
	@ApiModelProperty(required = true, value = "Child")
	private String childClient;

	@ApiModelProperty(value = "Referrer")
	private String fkreferlt;

	@NotNull
	@ApiModelProperty(required = true, value = "Staff Person")
	private String staffPerson;

	//governmentEntityType
	@ApiModelProperty(value = "County within the state of California to which a specific CASE is assigned.", example = "Alameda")
	private Short governmentEntityType;

	//icpcOutgngPlcmtStatusIndVar
	@NotNull
	@ApiModelProperty(required = true, value = "ICPC outgoing placement status indicator.", example = "true")
	private Boolean isIcpcOutgoingPlacementStatus;

	//icpcOutgoingRequestIndVar
	@NotNull
	@ApiModelProperty(required = true, value = "ICPC outgoing request", example = "true")
	private Boolean isIcpcOutgoingRequest;

	@NotNull
	@ApiModelProperty(required = true, value = "Determine the security access level.", example = "S")
	private String limitedAccessCode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
	@ApiModelProperty(value = "Limited access date", example = "2016-10-25")
	private LocalDate limitedAccessDate;

	@ApiModelProperty(value = "Limited access description", example = "Text")
	private String limitedAccessDesc;

	//limitedAccessGovernmentEntityType
	@ApiModelProperty(value = "Limited access county", example = "Alameda")
	private Short limitedAccessChangedCounty;

	@NotNull
	@ApiModelProperty(required = true, value = "Case name")
	private String caseName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
	@ApiModelProperty(value = "Next Transitional Independent Living Plan due date", example = "2018-10-24")
	private LocalDate nextTILPDueDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
	@ApiModelProperty(value = "Next Transitional Independent Living Plan due date", example = "2018-10-23")
	private LocalDate projectedEndDate;

	@ApiModelProperty(value = "Responsible agency")
	private String responsibleAgencyCode;

	@NotNull
	@ApiModelProperty(required = true, value = "Special case project indicator", example = "true")
	private Boolean isSpecialProjectCase;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
	@NotNull
	@ApiModelProperty(required = true, value = "Start date", example = "2016-10-23")
	private LocalDate startDate;

	//stateCodeType
	@NotNull
	@ApiModelProperty(required = true, value = "State", example = "CA")
	private Short state;

	//activeServiceComponentType
	@NotNull
	@ApiModelProperty(required = true, value = "Service component being referenced  for a child's case", example = "Emergency Response")
	private Short activeServiceComponent;

	//activeSvcComponentStartDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
	@gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT)
	@NotNull
	@ApiModelProperty(required = true, value = "Service component start date", example = "2016-10-23")
	private LocalDate activeServiceComponentStartDate;

	//tickleIndVar
	@NotNull
	@ApiModelProperty(required = true, value = "Tickle indicator", example = "true")
	private Boolean isTickle;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlertText() {
		return alertText;
	}

	public void setAlertText(String alertText) {
		this.alertText = alertText;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getCaseClosureReason() {
		return caseClosureReason;
	}

	public void setCaseClosureReason(String caseClosureReason) {
		this.caseClosureReason = caseClosureReason;
	}

	public Boolean getCaseplanChildrenDetail() {
		return isCaseplanChildrenDetail;
	}

	public void setCaseplanChildrenDetail(Boolean caseplanChildrenDetail) {
		isCaseplanChildrenDetail = caseplanChildrenDetail;
	}

	public String getClosureStatementText() {
		return closureStatementText;
	}

	public void setClosureStatementText(String closureStatementText) {
		this.closureStatementText = closureStatementText;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountySpecificCode() {
		return countySpecificCode;
	}

	public void setCountySpecificCode(String countySpecificCode) {
		this.countySpecificCode = countySpecificCode;
	}

	public String getDrmsNotesDoc() {
		return drmsNotesDoc;
	}

	public void setDrmsNotesDoc(String drmsNotesDoc) {
		this.drmsNotesDoc = drmsNotesDoc;
	}

	public LocalDate getEmancipationDate() {
		return emancipationDate;
	}

	public void setEmancipationDate(LocalDate emancipationDate) {
		this.emancipationDate = emancipationDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getChildClient() {
		return childClient;
	}

	public void setChildClient(String childClient) {
		this.childClient = childClient;
	}

	public String getFkreferlt() {
		return fkreferlt;
	}

	public void setFkreferlt(String fkreferlt) {
		this.fkreferlt = fkreferlt;
	}

	public String getStaffPerson() {
		return staffPerson;
	}

	public void setStaffPerson(String staffPerson) {
		this.staffPerson = staffPerson;
	}

	public Short getGovernmentEntityType() {
		return governmentEntityType;
	}

	public void setGovernmentEntityType(Short governmentEntityType) {
		this.governmentEntityType = governmentEntityType;
	}

	public Boolean getIcpcOutgoingPlacementStatus() {
		return isIcpcOutgoingPlacementStatus;
	}

	public void setIcpcOutgoingPlacementStatus(Boolean icpcOutgoingPlacementStatus) {
		isIcpcOutgoingPlacementStatus = icpcOutgoingPlacementStatus;
	}

	public Boolean getIcpcOutgoingRequest() {
		return isIcpcOutgoingRequest;
	}

	public void setIcpcOutgoingRequest(Boolean icpcOutgoingRequest) {
		isIcpcOutgoingRequest = icpcOutgoingRequest;
	}

	public String getLimitedAccessCode() {
		return limitedAccessCode;
	}

	public void setLimitedAccessCode(String limitedAccessCode) {
		this.limitedAccessCode = limitedAccessCode;
	}

	public LocalDate getLimitedAccessDate() {
		return limitedAccessDate;
	}

	public void setLimitedAccessDate(LocalDate limitedAccessDate) {
		this.limitedAccessDate = limitedAccessDate;
	}

	public String getLimitedAccessDesc() {
		return limitedAccessDesc;
	}

	public void setLimitedAccessDesc(String limitedAccessDesc) {
		this.limitedAccessDesc = limitedAccessDesc;
	}

	public Short getLimitedAccessChangedCounty() {
		return limitedAccessChangedCounty;
	}

	public void setLimitedAccessChangedCounty(Short limitedAccessChangedCounty) {
		this.limitedAccessChangedCounty = limitedAccessChangedCounty;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public LocalDate getNextTILPDueDate() {
		return nextTILPDueDate;
	}

	public void setNextTILPDueDate(LocalDate nextTILPDueDate) {
		this.nextTILPDueDate = nextTILPDueDate;
	}

	public LocalDate getProjectedEndDate() {
		return projectedEndDate;
	}

	public void setProjectedEndDate(LocalDate projectedEndDate) {
		this.projectedEndDate = projectedEndDate;
	}

	public String getResponsibleAgencyCode() {
		return responsibleAgencyCode;
	}

	public void setResponsibleAgencyCode(String responsibleAgencyCode) {
		this.responsibleAgencyCode = responsibleAgencyCode;
	}

	public Boolean getSpecialProjectCase() {
		return isSpecialProjectCase;
	}

	public void setSpecialProjectCase(Boolean specialProjectCase) {
		isSpecialProjectCase = specialProjectCase;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Short getActiveServiceComponent() {
		return activeServiceComponent;
	}

	public void setActiveServiceComponent(Short activeServiceComponent) {
		this.activeServiceComponent = activeServiceComponent;
	}

	public LocalDate getActiveServiceComponentStartDate() {
		return activeServiceComponentStartDate;
	}

	public void setActiveServiceComponentStartDate(LocalDate activeServiceComponentStartDate) {
		this.activeServiceComponentStartDate = activeServiceComponentStartDate;
	}

	public Boolean getTickle() {
		return isTickle;
	}

	public void setTickle(Boolean tickle) {
		isTickle = tickle;
	}
}

