package gov.ca.cwds.cm.service.mapper;

import gov.ca.cwds.cm.service.dto.CaseDTO;
import gov.ca.cwds.cm.service.mapper.tool.TrailingSpacesRemovalPostMappingProcessor;
import gov.ca.cwds.data.legacy.cms.entity.Case;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** @author CWDS TPT-3 Team */
@Mapper(
  uses = TrailingSpacesRemovalPostMappingProcessor.class
)
@FunctionalInterface
public interface CaseMapper {

  @Mapping(target = "messages", ignore = true)
  @Mapping(source = "identifier", target = "id")
  @Mapping(source = "approvalStatusType.systemId", target = "approvalStatus")
  @Mapping(source = "caseClosureReasonType.systemId", target = "caseClosureReason")
  @Mapping(source = "caseplanChildrenDetailIndVar", target = "caseplanChildrenDetail")
  @Mapping(source = "icpcOutgngPlcmtStatusIndVar", target = "icpcOutgoingPlacementStatus")
  @Mapping(source = "icpcOutgoingRequestIndVar", target = "icpcOutgoingRequest")
  @Mapping(source = "specialProjectCaseIndVar", target = "specialProjectCase")
  @Mapping(source = "activeServiceComponentType.systemId", target = "activeServiceComponent")
  @Mapping(source = "activeSvcComponentStartDate", target = "activeServiceComponentStartDate")
  @Mapping(source = "tickleIndVar", target = "tickle")
  @Mapping(source = "country.systemId", target = "country")
  @Mapping(source = "county.systemId", target = "county")
  @Mapping(source = "limitedAccessCounty.systemId", target = "limitedAccessCounty")
  @Mapping(source = "limitedAccess.description", target = "limitedAccess")
  @Mapping(source = "responsibleAgency.description", target = "responsibleAgency")
  @Mapping(source = "state.systemId", target = "state")
  @Mapping(source = "staffPerson.identifier", target = "staffPerson")
  @Mapping(source = "childClient.victimClientId", target = "childClient")
  @Mapping(source = "alertText.identifier", target = "alertText")
  @Mapping(source = "closureStatementText.identifier", target = "closureStatementText")
  CaseDTO toCaseDTO(Case caseEntity);
}
