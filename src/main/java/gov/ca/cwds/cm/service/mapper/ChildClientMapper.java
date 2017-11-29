package gov.ca.cwds.cm.service.mapper;

import gov.ca.cwds.cm.service.dto.ChildClientDTO;
import gov.ca.cwds.data.legacy.cms.entity.ChildClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** @author CWDS TPT-3 Team */
@Mapper(uses = {ClientMapper.class})
@FunctionalInterface
public interface ChildClientMapper {

  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "victimClientId", source = "victimClientId")
  @Mapping(target = "adoptableCode", source = "adoptable")
  @Mapping(target = "adoptedAge", source = "adoptedAge")
  @Mapping(target = "afdcFcEligibilityIndicatorVar", source = "afdcFcEligibilityIndicatorVar")
  @Mapping(target = "allEducationInfoOnFileIndicator", source = "allEducationInfoOnFileIndicator")
  @Mapping(target = "allHealthInfoOnFileIndicator", source = "allHealthInfoOnFileIndicator")
  @Mapping(target = "attemptToAcquireEducInfoDesc", source = "attemptToAcquireEducInfoDesc")
  @Mapping(target = "attemptToAcquireHlthInfoDesc", source = "attemptToAcquireHlthInfoDesc")
  @Mapping(target = "awolAbductedCode", source = "awolAbducted")
  @Mapping(target = "birthHistoryIndicatorVar", source = "birthHistoryIndicatorVar")
  @Mapping(target = "childIndianAncestryIndicator", source = "childIndianAncestryIndicator")
  @Mapping(target = "collegeIndicator", source = "collegeIndicator")
  @Mapping(target = "currentCaseId", source = "currentCaseId")
  @Mapping(target = "disabilityDiagnosedCode", source = "disabilityDiagnosed")
  @Mapping(target = "drmsHePassportDocOld", source = "drmsHePassportDocOld")
  @Mapping(target = "foodStampsApplicationIndicator", source = "foodStampsApplicationIndicator")
  @Mapping(target = "icwaEligibilityCode", source = "icwaEligibility")
  @Mapping(
    target = "intercountryAdoptDisruptedIndicator",
    source = "intercountryAdoptDisruptedIndicator"
  )
  @Mapping(
    target = "medEligibilityApplicationIndicatorVar",
    source = "medEligibilityApplicationIndicatorVar"
  )
  @Mapping(target = "minorNmdParentIndicator", source = "minorNmdParentIndicator")
  @Mapping(target = "parentalRightsLimitedIndicator", source = "parentalRightsLimitedIndicator")
  @Mapping(
    target = "parentalRightsTermintnIndicatorVar",
    source = "parentalRightsTermintnIndicatorVar"
  )
  @Mapping(target = "paternityIndividualIndicatorVar", source = "paternityIndividualIndicatorVar")
  @Mapping(target = "postsecVocIndicator", source = "postsecVocIndicator")
  @Mapping(target = "previouslyAdopted", source = "previouslyAdopted")
  @Mapping(
    target = "safelySurrendedBabiesIndicatorVar",
    source = "safelySurrendedBabiesIndicatorVar"
  )
  @Mapping(target = "sawsCaseSerialNumber", source = "sawsCaseSerialNumber")
  @Mapping(target = "deathCircumstancesType", source = "deathCircumstancesType.systemId")
  @Mapping(target = "sijsScheduledInterviewDate", ignore = true)
  @Mapping(target = "siiNextScreeningDueDate", ignore = true)
  @Mapping(target = "ssiSspApplicationIndicator", source = "ssiSspApplicationIndicator")
  @Mapping(
    target = "tribalAncestryNotifctnIndicatorVar",
    source = "tribalAncestryNotifctnIndicatorVar"
  )
  @Mapping(target = "tribalCustomaryAdoptionDate", ignore = true)
  @Mapping(target = "tribalCustomaryAdoptionIndicator", source = "tribalCustomaryAdoptionIndicator")
  @Mapping(target = "foodStampsApplicationDate", ignore = true)
  @Mapping(target = "client", source = "client")
  ChildClientDTO toChildClientDTO(ChildClient childClient);
}
