package gov.ca.cwds.cm.service.dto;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import java.time.LocalDate;
import org.junit.Test;

/**
 * @author CWDS TPT-3 Team
 */
public class ChildClientDTOTest {

  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  @Test
  public void testSerializationToJson() throws Exception {
    String json = MAPPER.writeValueAsString(getClient());
    String jFixture = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/dto/child-client-dto.json"), ChildClientDTO.class));
    assertThat(json, is(jFixture));
  }

  @Test
  public void testDeserializationFromJson() throws Exception {
    ChildClientDTO clientDeserialized = MAPPER.readValue(fixture("fixtures/dto/child-client-dto.json"), ChildClientDTO.class);
    assertThat(clientDeserialized, equalTo(getClient()));
  }

  private static ChildClientDTO getClient() {
    ChildClientDTO dto = new ChildClientDTO();
    dto.setVictimClientId("ABC1234567");
    dto.setAdoptableCode("NOT_ADOPTABLE");
    dto.setAdoptedAge((short) 22);
    dto.setAfdcFcEligibilityIndicatorVar(false);
    dto.setAllEducationInfoOnFileIndicator(false);
    dto.setAllHealthInfoOnFileIndicator(false);
    dto.setAttemptToAcquireEducInfoDesc("Education");
    dto.setAttemptToAcquireHlthInfoDesc("Health");
    dto.setAwolAbductedCode("AWOL_BASED_ON_SYSTEM_SETTING");
    dto.setBirthHistoryIndicatorVar(false);
    dto.setChildIndianAncestryIndicator(false);
    dto.setCollegeIndicator(false);
    dto.setCurrentCaseId("ABC1234567");
    dto.setDeathCircumstancesType("1234");
    dto.setDisabilityDiagnosedCode("NOT_YET_DETERMINED");
    dto.setDrmsHePassportDocOld("ABC1234567");
    dto.setDrmsHealthEducPassportDoc("ABC1234567");
    dto.setDrmsVoluntaryPlcmntAgrmntDoc("ABC1234567");
    dto.setFc2EligApplicationIndicatorVar(false);
    dto.setFoodStampsApplicationDate(LocalDate.of(2010, 10, 10));
    dto.setFoodStampsApplicationIndicator(false);
    dto.setIcwaEligibilityCode("NOT_ELIGIBLE");
    dto.setIntercountryAdoptDisruptedIndicator(false);
    dto.setIntercountryAdoptDissolvedIndicator(false);
    dto.setMedEligibilityApplicationIndicatorVar(false);
    dto.setMinorNmdParentIndicator(false);
    dto.setParentalRightsLimitedIndicator(false);
    dto.setParentalRightsTermintnIndicatorVar(false);
    dto.setPaternityIndividualIndicatorVar(false);
    dto.setPostsecVocIndicator(false);
    dto.setPreviouslyAdopted("NO_USER_SELECTION");
    dto.setSafelySurrendedBabiesIndicatorVar(false);
    dto.setSaw1EligApplicationIndicatorVar(false);
    dto.setSawsCaseSerialNumber(233);
    dto.setSijsScheduledInterviewDate(LocalDate.of(2010, 10, 10));
    dto.setSiiNextScreeningDueDate(LocalDate.of(2010, 10, 10));
    dto.setSsiSspApplicationIndicator(false);
    dto.setTribalAncestryNotifctnIndicatorVar(false);
    dto.setTribalCustomaryAdoptionDate(LocalDate.of(2010, 10, 10));
    dto.setTribalCustomaryAdoptionIndicator(false);
    dto.setIdentifier("0YIPkZU0S0");
    dto.setAdoptionStatusCode("TOTALLY_FREE");
    dto.setAlienRegistrationNumber("111483672");
    dto.setBirthDt(LocalDate.of(2010, 10, 10));
    dto.setBirthFacilityName("San Jose Medical Center");
    dto.setBirthCountryCode((short) 33);
    dto.setBirthStateCodeType((short) 1828);
    dto.setChildClientIndVar(false);
    dto.setCommonFirstName("Monica");
    dto.setCommonLastName("Monicaa");
    dto.setCommonMiddleName("Monicovna");
    dto.setConfidentialityInEffectInd(false);
    dto.setConfidentialityActionDate(LocalDate.of(2010, 10, 10));
    dto.setCreationDate(LocalDate.of(2010, 10, 10));
    dto.setDeathDate(LocalDate.of(2010, 10, 10));
    dto.setDeathReason("U8SxGBF00c");
    dto.setDriverLicenseNumber("987654321");
    dto.setDriverLicenseStateCodeType((short) 1828);
    dto.setGenderCode("UNKNOWN");
    dto.setImmigrationCountryCodeType((short) 563);
    dto.setImmigrationStatusType((short) 1199);
    dto.setIncapacitatedParentCode("NOT_APPLICABLE");
    dto.setLitrateCode("NOT_APPLICABLE");
    dto.setMaritalCohabitatnIndicator(false);
    dto.setMaterialStatusType((short) 1308);
    dto.setMilitaryStatusCode("NO_MILITARY_INVOLVEMENT");
    dto.setNamePrefixDescription("Mr");
    dto.setNameType((short) 1313);
    dto.setOutstandingWarrantIndicator(false);
    dto.setPrimaryEthnicityType((short) 841);
    dto.setPrimaryLanguageType((short) 33);
    dto.setReligionType((short) 2);
    dto.setSecondaryLanguageType((short) 12);
    dto.setSensivityIndicator("NOT_APPLICABLE");
    dto.setSensivityHealthInfoOnFileIndicator(false);
    dto.setSocialSecurityNumber("1111111111");
    dto.setSocialSecurityNumberChangedCode("N");
    dto.setSuffixTitleDescription("D.D.S.");
    dto.setUnemployedParentCode("UNKNOWN");
    dto.setCommentDescription("Primary Comments for the Primary Client.");
    dto.setEstimatedDobCode("ESTIMATED");
    dto.setBirthplaceVerifiedIndicator(false);
    dto.setHispanicOriginCode("DECLINES_TO_STATE");
    dto.setChildrenServIndicator(false);
    dto.setCurrentlyRegionalCenteerIndicator(false);
    dto.setCurrentlyOtherDescription("QWREQWER");
    dto.setPreviouslyReceivedIndicator(false);
    dto.setPreviouslyRegionalCenterIndicator(false);
    dto.setPreviouslyOtherDescription(" ");
    dto.setHealthCarePlanIndicator(false);
    dto.setLimitationOnSCPHealthIndicator(false);
    dto.setBirthCity("San Jose");
    dto.setHealthTxt("0Wqj8l901w");
    dto.setMotherParentalTermDate(LocalDate.of(2010, 10, 10));
    dto.setFatherParentalTermDate(LocalDate.of(2010, 10, 10));
    dto.setZippyIndicator(false);
    dto.setDeathPlace("San Jose");
    dto.setTribalMemberVerificationIndicator(false);
    dto.setTribalAncestryClientIndicator(false);
    dto.setSoc158Ind(false);
    dto.setDeathDateVerifiedIndicator(false);
    dto.setEmailAddr("test@test.com");
    dto.setAdjudicatedDelinquentIndicator(false);
    dto.setEthUnableToDeterminCode("V");
    dto.setHispUnableToDeterminateReasonCode("ABANDONMENT");
    dto.setSoc158PlacementCode("MIGRATED_TO_CLIENT_SERVICES");
    dto.setSciIndexNumber(" ");
    return dto;
  }

}
