package gov.ca.cwds.cm.service.mapper.facade;

import gov.ca.cwds.cm.service.dto.facade.CaseByStaff;
import gov.ca.cwds.data.legacy.cms.entity.Case;
import gov.ca.cwds.data.legacy.cms.entity.CaseAssignment;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author CWDS TPT-3 Team
 */
@Mapper
@FunctionalInterface
public interface CaseByStaffMapper {

  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "assignmentType", ignore = true)
  @Mapping(target = "identifier", source = "theCase.identifier")
  @Mapping(target = "clientIdentifier", source = "theCase.childClient.identifier")
  @Mapping(target = "clientFirstName", source = "theCase.childClient.commonFirstName")
  @Mapping(target = "clientLastName", source = "theCase.childClient.commonLastName")
  @Mapping(target = "activeServiceComponent", source = "theCase.activeServiceComponentType.shortDescription")
  CaseByStaff toView(Case theCase, String staffId);

  @AfterMapping
  default CaseByStaff mapAssignmentType(@MappingTarget final CaseByStaff target,
      final Case source, final String staffId) {
    if (staffId == null) {
      return target;
    }

    // find assignment for particular Staff Person and set it's assignmentType to targets one
    final List<CaseAssignment> assignments = source.getAssignments();
    for (final CaseAssignment caseAssignment : assignments) {
      if (caseAssignment.getCaseLoad() == null
          || caseAssignment.getCaseLoad().getCaseLoadWeighting() == null) {
        continue;
      }

      final String fkstfperst = caseAssignment.getCaseLoad().getCaseLoadWeighting().getFkstfperst();
      if (staffId.equals(fkstfperst)) {
        target.setAssignmentIdentifier(caseAssignment.getIdentifier());
        target.setAssignmentType(caseAssignment.getAssignmentType());
        target.setAssignmentStartDate(caseAssignment.getStartDate());
        target.setAssignmentStartTime(caseAssignment.getStartTime());
        target.setAssignmentEndDate(caseAssignment.getEndDate());
        target.setAssignmentEndTime(caseAssignment.getEndTime());
        break;
      }
    }
    return target;
  }

}
