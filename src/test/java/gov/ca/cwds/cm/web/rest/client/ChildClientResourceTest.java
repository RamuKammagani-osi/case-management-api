package gov.ca.cwds.cm.web.rest.client;

import static gov.ca.cwds.cm.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static gov.ca.cwds.cm.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static gov.ca.cwds.security.test.TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import gov.ca.cwds.cm.BaseApiIntegrationTest;
import gov.ca.cwds.cm.Constants.API;
import gov.ca.cwds.cm.service.dto.ChildClientDTO;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class ChildClientResourceTest extends BaseApiIntegrationTest {

  public static final String CLIENT_ID = "BKk7CHj00Z";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCwsRs1();
    setUpCms();
    setUpDb();
    runScripts(
        "liquibase/address/dml_address_test_data.xml",
        "liquibase/client/address/dml_client_address_test_data.xml",
        "liquibase/client/child_client_test_update.xml");
  }

  @Test
  public void getChildClientById_success_whenAuthorizedUser() throws Exception {
    // given
    // when
    final ChildClientDTO actual =
        clientTestRule
            .target(API.CHILD_CLIENTS + "/" + CLIENT_ID)
            .queryParam(PATH_TO_PRINCIPAL_FIXTURE, "fixtures/perry-account/0Ki-all-authorized.json")
            .request(MediaType.APPLICATION_JSON)
            .get()
            .readEntity(ChildClientDTO.class);

    // then
    final String expectedFixture = fixture("fixtures/child-client-by-id-response.json");
    assertEqualsResponse(expectedFixture, transformDTOtoJSON(actual));
  }

  @Test
  public void getChildClientById_notAuthorized_whenUnauthorizedUser() throws Exception {
    // given
    // when
    final Response response =
        clientTestRule
            .target(API.CHILD_CLIENTS + "/" + CLIENT_ID)
            .request(MediaType.APPLICATION_JSON)
            .get();

    // then
    assertThat(response.getStatus(), is(403));
  }

  @Test
  public void testUpdateChildClient() throws Exception {
    ChildClientDTO childClientDTO = getChildClientDTO("BKk7CHj00A");
    enrichForUpdate(childClientDTO);

    WebTarget target = clientTestRule.target(API.CHILD_CLIENTS + "/BKk7CHj00A");
    target
        .queryParam(PATH_TO_PRINCIPAL_FIXTURE, "fixtures/perry-account/0Ki-all-authorized.json")
        .request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.entity(childClientDTO, MediaType.APPLICATION_JSON_TYPE), ChildClientDTO.class);

    String fixture = fixture("fixtures/child-client-after-update-response.json");
    ChildClientDTO clientAfterUpdate = getChildClientDTO("BKk7CHj00A");
    assertEqualsResponse(fixture, transformDTOtoJSON(clientAfterUpdate));
  }

  private void enrichForUpdate(ChildClientDTO childClientDTO) {
    childClientDTO.setAdoptableCode("ADOPTABLE");
    childClientDTO.setAdoptedAge((short) 22);
    childClientDTO.setAfdcFcEligibilityIndicatorVar(true);
    childClientDTO.setAllHealthInfoOnFileIndicator(true);
    childClientDTO.setAllEducationInfoOnFileIndicator(true);
    childClientDTO.setAttemptToAcquireEducInfoDesc("Educ info test");
    childClientDTO.setAwolAbductedCode("NOT_APPLICABLE");
    childClientDTO.setBirthHistoryIndicatorVar(true);
    childClientDTO.setCurrentCaseId("ABC1234333");
    childClientDTO.setCollegeIndicator(true);
    childClientDTO.setChildIndianAncestryIndicator(true);
    childClientDTO.setDeathCircumstancesType("3321");
    childClientDTO.setDisabilityDiagnosedCode("NOT_YET_DETERMINED");
    childClientDTO.setMinorNmdParentIndicator(true);
    childClientDTO.setIcwaEligibilityCode("NOT_ELIGIBLE");
    childClientDTO.setPreviouslyAdopted("NO");
    childClientDTO.setAdoptionStatusCode("NOT_FREE");
    childClientDTO.setGenderCode("FEMALE");
    childClientDTO.setIncapacitatedParentCode("YES");
    childClientDTO.setMaterialStatusType((short) 1309);
    childClientDTO.setDriverLicenseNumber("987654321");
  }

  @Test
  public void getAddressesByClientId_success_whenAddressesExist() throws Exception {
    // given
    final String path = API.CHILD_CLIENTS + "/GmNMeSx0Hy/" + API.ADDRESSES;

    // when
    final Response actualResult =
        clientTestRule.target(path).request(MediaType.APPLICATION_JSON_TYPE).get(Response.class);

    // then
    assertResponseByFixturePath(
        actualResult,
        "fixtures/child-client/getAddressesByClientId_success_whenAddressesExist.json");
  }

  @Test
  public void getAddressesByClientId_code404_whenNoAddress() throws Exception {
    // given
    final String path = API.CHILD_CLIENTS + "/NotExistingId/" + API.ADDRESSES;

    // when
    final Response actualResult =
        clientTestRule.target(path).request(MediaType.APPLICATION_JSON_TYPE).get(Response.class);

    // then
    assertThat(actualResult.getStatus(), is(equalTo(404)));
  }

  @Test
  public void getRelationshipsByClientId_success_whenRelationshipsExist() throws Exception {
    // given
    final String path = API.CHILD_CLIENTS + "/" + ChildClientResource.MOCK_CLIENT_ID +"/" + API.RELATIONSHIPS;

    // when
    final Response actualResult =
        clientTestRule.target(path).request(MediaType.APPLICATION_JSON_TYPE).get(Response.class);

    // then
    assertResponseByFixturePath(
        actualResult,
        "fixtures/child-client/getRelationshipsByClientId_success_whenRelationshipsExist.json");
  }

  private ChildClientDTO getChildClientDTO(String clientId) {
    WebTarget target =
        clientTestRule
            .target(API.CHILD_CLIENTS + "/" + clientId)
            .queryParam(
                PATH_TO_PRINCIPAL_FIXTURE, "fixtures/perry-account/0Ki-all-authorized.json");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    return response.readEntity(ChildClientDTO.class);
  }
}
