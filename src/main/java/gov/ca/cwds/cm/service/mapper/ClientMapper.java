package gov.ca.cwds.cm.service.mapper;

import gov.ca.cwds.cm.service.dto.ClientDTO;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

/** @author CWDS TPT-3 Team */
@Mapper
@MapperConfig(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface ClientMapper {

  @Mapping(target = "messages", ignore = true)
  @Mapping(target = "commentDescription", source = "commentDescription")
  @Mapping(target = "estimatedDobCode", source = "dateOfBirthStatus")
  @Mapping(target = "birthplaceVerifiedIndicator", source = "birthplaceVerifiedIndicator")
  @Mapping(target = "hispanicOriginCode", source = "hispanicOrigin")
  @Mapping(target = "healthTxt", source = "healthSummaryText")
  @Mapping(target = "birthCity", source = "birthCity")
  @Mapping(target = "limitationOnSCPHealthIndicator", source = "limitationOnScpHealthIndicator")
  @Mapping(target = "healthCarePlanIndicator", source = "individualHealthCarePlanIndicator")
  @Mapping(target = "previouslyOtherDescription", source = "previousOtherDescription")
  @Mapping(target = "previouslyRegionalCenterIndicator", source = "previousRegionalCenterIndicator")
  @Mapping(target = "previouslyReceivedIndicator", source = "previousCaChildrenServiceIndicator")
  @Mapping(target = "currentlyOtherDescription", source = "currentlyOtherDescription")
  @Mapping(target = "childrenServIndicator", source = "currentCaChildrenServiceIndicator")
  @Mapping(
      target = "currentlyRegionalCenteerIndicator",
      source = "currentlyRegionalCenterIndicator"
  )
  @Mapping(target = "adjudicatedDelinquentIndicator", source = "adjudicatedDelinquentIndicator")
  @Mapping(target = "deathDateVerifiedIndicator", source = "deathDateVerifiedIndicator")
  @Mapping(target = "emailAddr", source = "emailAddress")
  @Mapping(target = "soc158Ind", source = "soc158SealedClientIndicator")
  @Mapping(target = "tribalAncestryClientIndicator", source = "tribalAncestryClientIndicator")
  @Mapping(
      target = "tribalMemberVerificationIndicator",
      source = "tribalMembershipVerifcationIndicator"
  )
  @Mapping(target = "deathPlace", source = "deathPlace")
  @Mapping(target = "zippyIndicator", source = "zippyCreatedIndicator")
  @Mapping(target = "fatherParentalTermDate", source = "fatherParentalRightTermDate")
  @Mapping(target = "sciIndexNumber", source = "clientIndexNumber")
  @Mapping(target = "motherParentalTermDate", source = "motherParentalRightTermDate")
  @Mapping(target = "soc158PlacementCode", source = "soc158placementsStatus")
  @Mapping(
      target = "hispUnableToDeterminateReasonCode",
      source = "hispanicUnableToDetermineReason"
  )
  @Mapping(target = "ethUnableToDeterminCode", source = "ethnicityUnableToDetermineReason")
  @Mapping(target = "unemployedParentCode", source = "parentUnemployedStatus")
  @Mapping(target = "suffixTitleDescription", source = "suffixTitleDescription")
  @Mapping(target = "socialSecurityNumberChangedCode", source = "socialSecurityNumberChangedCode")
  @Mapping(target = "socialSecurityNumber", source = "socialSecurityNumber")
  @Mapping(
      target = "sensivityHealthInfoOnFileIndicator",
      source = "sensitiveHealthInfoOnFileIndicator"
  )
  @Mapping(target = "sensivityIndicator", source = "sensitivity")
  @Mapping(target = "secondaryLanguageType", source = "secondaryLanguageCode")
  @Mapping(target = "religionType", source = "religionCode")
  @Mapping(target = "primaryLanguageType", source = "primaryLanguageCode")
  @Mapping(target = "primaryEthnicityType", source = "primaryEthnicityCode")
  @Mapping(target = "outstandingWarrantIndicator", source = "outstandingWarrantIndicator")
  @Mapping(target = "nameType", source = "nameType.systemId")
  @Mapping(target = "namePrefixDescription", source = "namePrefixDescription")
  @Mapping(target = "militaryStatusCode", source = "militaryStatus")
  @Mapping(target = "materialStatusType", source = "maritalStatusCode")
  @Mapping(target = "maritalCohabitatnIndicator", source = "maritalCohabitationHistoryIndicator")
  @Mapping(target = "litrateCode", source = "literateStatus")
  @Mapping(target = "incapacitatedParentCode", source = "incapacitatedParentStatus")
  @Mapping(target = "immigrationStatusType", source = "immigrationStatusCode")
  @Mapping(target = "immigrationCountryCodeType", source = "immigrationCountryCode")
  @Mapping(target = "genderCode", source = "gender")
  @Mapping(target = "driverLicenseStateCodeType", source = "driverLicenseStateCode")
  @Mapping(target = "driverLicenseNumber", source = "driverLicenseNumber")
  @Mapping(target = "deathReason", source = "deathReasonText")
  @Mapping(target = "deathDate", source = "deathDate")
  @Mapping(target = "creationDate", source = "creationDate")
  @Mapping(target = "confidentialityActionDate", source = "confidentialityActionDate")
  @Mapping(target = "confidentialityInEffectInd", source = "confidentialityInEffectIndicator")
  @Mapping(target = "commonMiddleName", source = "commonMiddleName")
  @Mapping(target = "commonLastName", source = "commonLastName")
  @Mapping(target = "commonFirstName", source = "commonFirstName")
  @Mapping(target = "childClientIndVar", source = "childClientIndicator")
  @Mapping(target = "birthCountryCode", source = "birthCountryCode")
  @Mapping(target = "birthStateCodeType", source = "birthStateCode")
  @Mapping(target = "birthFacilityName", source = "birthFacilityName")
  @Mapping(target = "birthDt", source = "birthDate")
  @Mapping(target = "alienRegistrationNumber", source = "alienRegistrationNumber")
  @Mapping(target = "adoptionStatusCode", source = "adoptionStatus")
  @Mapping(target = "identifier", source = "identifier")
  ClientDTO toClientDTO(Client client);

  @Mapping(source = "commentDescription", target = "commentDescription")
  @Mapping(
      source = "estimatedDobCode",
      target = "dateOfBirthStatus"
  )
  @Mapping(source = "birthplaceVerifiedIndicator", target = "birthplaceVerifiedIndicator")
  @Mapping(
      source = "hispanicOriginCode",
      target = "hispanicOrigin"
  )
  @Mapping(source = "healthTxt", target = "healthSummaryText")
  @Mapping(source = "birthCity", target = "birthCity")
  @Mapping(source = "limitationOnSCPHealthIndicator", target = "limitationOnScpHealthIndicator")
  @Mapping(source = "healthCarePlanIndicator", target = "individualHealthCarePlanIndicator")
  @Mapping(source = "previouslyOtherDescription", target = "previousOtherDescription")
  @Mapping(source = "previouslyRegionalCenterIndicator", target = "previousRegionalCenterIndicator")
  @Mapping(source = "previouslyReceivedIndicator", target = "previousCaChildrenServiceIndicator")
  @Mapping(source = "currentlyOtherDescription", target = "currentlyOtherDescription")
  @Mapping(source = "childrenServIndicator", target = "currentCaChildrenServiceIndicator")
  @Mapping(
      source = "currentlyRegionalCenteerIndicator",
      target = "currentlyRegionalCenterIndicator"
  )
  @Mapping(source = "adjudicatedDelinquentIndicator", target = "adjudicatedDelinquentIndicator")
  @Mapping(source = "deathDateVerifiedIndicator", target = "deathDateVerifiedIndicator")
  @Mapping(source = "emailAddr", target = "emailAddress")
  @Mapping(source = "soc158Ind", target = "soc158SealedClientIndicator")
  @Mapping(source = "tribalAncestryClientIndicator", target = "tribalAncestryClientIndicator")
  @Mapping(
      source = "tribalMemberVerificationIndicator",
      target = "tribalMembershipVerifcationIndicator"
  )
  @Mapping(source = "deathPlace", target = "deathPlace")
  @Mapping(source = "zippyIndicator", target = "zippyCreatedIndicator")
  @Mapping(source = "fatherParentalTermDate", target = "fatherParentalRightTermDate")
  @Mapping(source = "sciIndexNumber", target = "clientIndexNumber")
  @Mapping(source = "motherParentalTermDate", target = "motherParentalRightTermDate")
  @Mapping(
      source = "soc158PlacementCode",
      target = "soc158placementsStatus"
  )
  @Mapping(
      source =
          "hispUnableToDeterminateReasonCode",
      target = "hispanicUnableToDetermineReason"
  )
  @Mapping(
      source = "ethUnableToDeterminCode",
      target = "ethnicityUnableToDetermineReason"
  )
  @Mapping(
      source = "unemployedParentCode",
      target = "parentUnemployedStatus"
  )
  @Mapping(source = "suffixTitleDescription", target = "suffixTitleDescription")
  @Mapping(source = "socialSecurityNumberChangedCode", target = "socialSecurityNumberChangedCode")
  @Mapping(source = "socialSecurityNumber", target = "socialSecurityNumber")
  @Mapping(
      source = "sensivityHealthInfoOnFileIndicator",
      target = "sensitiveHealthInfoOnFileIndicator"
  )
  @Mapping(
      source = "sensivityIndicator",
      target = "sensitivity"
  )
  @Mapping(source = "secondaryLanguageType", target = "secondaryLanguageCode")
  @Mapping(source = "religionType", target = "religionCode")
  @Mapping(source = "primaryLanguageType", target = "primaryLanguageCode")
  @Mapping(source = "primaryEthnicityType", target = "primaryEthnicityCode")
  @Mapping(source = "outstandingWarrantIndicator", target = "outstandingWarrantIndicator")
  @Mapping(source = "nameType", target = "nameType.systemId")
  @Mapping(source = "namePrefixDescription", target = "namePrefixDescription")
  @Mapping(
      source = "militaryStatusCode",
      target = "militaryStatus"
  )
  @Mapping(source = "maritalCohabitatnIndicator", target = "maritalCohabitationHistoryIndicator")
  @Mapping(
      source = "litrateCode",
      target = "literateStatus"
  )
  @Mapping(
      source = "incapacitatedParentCode",
      target = "incapacitatedParentStatus"
  )
  @Mapping(source = "immigrationStatusType", target = "immigrationStatusCode")
  @Mapping(source = "immigrationCountryCodeType", target = "immigrationCountryCode")
  @Mapping(source = "genderCode", target = "gender")
  @Mapping(source = "driverLicenseStateCodeType", target = "driverLicenseStateCode")
  @Mapping(source = "driverLicenseNumber", target = "driverLicenseNumber")
  @Mapping(source = "deathReason", target = "deathReasonText")
  @Mapping(source = "deathDate", target = "deathDate")
  @Mapping(source = "creationDate", target = "creationDate")
  @Mapping(source = "confidentialityActionDate", target = "confidentialityActionDate")
  @Mapping(source = "confidentialityInEffectInd", target = "confidentialityInEffectIndicator")
  @Mapping(source = "commonMiddleName", target = "commonMiddleName")
  @Mapping(source = "commonLastName", target = "commonLastName")
  @Mapping(source = "commonFirstName", target = "commonFirstName")
  @Mapping(source = "childClientIndVar", target = "childClientIndicator")
  @Mapping(source = "birthCountryCode", target = "birthCountryCode")
  @Mapping(source = "birthStateCodeType", target = "birthStateCode")
  @Mapping(source = "birthFacilityName", target = "birthFacilityName")
  @Mapping(source = "birthDt", target = "birthDate")
  @Mapping(source = "alienRegistrationNumber", target = "alienRegistrationNumber")
  @Mapping(
      source = "adoptionStatusCode",
      target = "adoptionStatus"
  )
  @Mapping(source = "identifier", target = "identifier")
  @Mapping(target = "placementEpisodes", ignore = true)
  @Mapping(source = "materialStatusType", target = "maritalStatusCode")
  Client toClient(ClientDTO clientDTO);
}
