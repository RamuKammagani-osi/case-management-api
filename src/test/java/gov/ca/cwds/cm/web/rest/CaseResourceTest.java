package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.test.util.AssertFixtureUtils.assertResponseByFixturePath;
import static gov.ca.cwds.cm.test.util.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.Constants.API;
import gov.ca.cwds.cm.service.dto.CaseDTO;
import gov.ca.cwds.cm.test.util.TestUtils;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import liquibase.exception.LiquibaseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CaseResourceTest extends AbstractIntegrationTest {

  private static final int UNPROCESSABLE_ENTITY_STATUS_CODE = 422;
  private static final String CASE_ID = "0rgnUzi000";
  private static final String[] LIQUIBASE_SCRIPTS = {
      "liquibase/client/client_test_get.xml",
      "liquibase/client/child_client_test_get.xml",
      "liquibase/case/case_test.xml",
  };

  @BeforeClass
  public static void onBeforeClass() throws LiquibaseException {
    DATABASE_HELPER_CMS.runScripts(LIQUIBASE_SCRIPTS);
  }

  @AfterClass
  public static void onAfterClass() throws LiquibaseException {
    DATABASE_HELPER_CMS.rollbackScripts(LIQUIBASE_SCRIPTS);
  }

  @Test
  public void testGetCaseById() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.CASES + "/" + CASE_ID);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertResponseByFixturePath(
        response,
        "fixtures/case-by-id-response.json"
    );
  }

  @Test
  public void testUpdateCaseSuccess() throws Exception {
    CaseDTO caseDTO = validCaseDto();

    WebTarget target = clientTestRule.target(API.CASES + "/" + CASE_ID);
    CaseDTO response = updateCase(target, caseDTO);
    String fixture = fixture("fixtures/case-after-update-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(response));
  }

  @Test
  public void testUpdateCaseEmptyId() throws Exception {
    CaseDTO caseDTO = validCaseDto();

    WebTarget target = clientTestRule.target(API.CASES + "/");

    try {
      updateCase(target, caseDTO);
      fail();
    } catch (InternalServerErrorException e) {
      Response response = e.getResponse();
      assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
  }

  @Test
  public void testUpdateCaseInvalidLimitedAccessCode() throws Exception {
    CaseDTO caseDTO = validCaseDto();
    caseDTO.setLimitedAccess("X");

    WebTarget target = clientTestRule.target(
        API.CASES + "/" + CASE_ID);
    try {
      updateCase(target, caseDTO);
      fail();
    } catch (ClientErrorException e) {
      Response response = e.getResponse();
      assertEquals(UNPROCESSABLE_ENTITY_STATUS_CODE, response.getStatus());
    }
  }

  @Test
  public void testUpdateCaseInvalidLimitedForeignKey() throws Exception {
    CaseDTO caseDTO = validCaseDto();
    caseDTO.setAlertTextId("123");

    WebTarget target = clientTestRule.target(API.CASES + "/" + CASE_ID);
    try {
      updateCase(target, caseDTO);
      fail();
    } catch (ClientErrorException e) {
      Response response = e.getResponse();
      assertEquals(UNPROCESSABLE_ENTITY_STATUS_CODE, response.getStatus());
    }
  }

  private static CaseDTO validCaseDto() {
    CaseDTO caseDTO = new CaseDTO();

    caseDTO.setId("CWulvpU0JY");//may be not equal to parameter ID
    caseDTO.setAlertTextId("EssaJ4W0Js");
    caseDTO.setApprovalStatusCode((short) 118);
    caseDTO.setCaseClosureReasonCode((short) 306);
    caseDTO.setClosureStatementTextId("Fn3lUdY00F");
    caseDTO.setCountryCode((short) 563);
    caseDTO.setCountySpecificCode("10");
    caseDTO.setDrmsNotesDocId("At9HoSn0WJ");
    caseDTO.setEmancipationDate(TestUtils.localDate("2018-10-20"));
    caseDTO.setEndDate(TestUtils.localDate("2018-10-20"));
    caseDTO.setChildClientId("8m7hS7i07n");
    caseDTO.setReferralId("7Rgxy9S00T");
    caseDTO.setStaffPersonId("07n");
    caseDTO.setCountyCode((short) 1068);
    caseDTO.setLimitedAccess("NO_RESTRICTION");
    caseDTO.setLimitedAccessDesc("Text");
    caseDTO.setLimitedAccessCountyCode((short) 1068);
    caseDTO.setCaseName("Sibling Hanson");
    caseDTO.setNextTilpDueDate(TestUtils.localDate("2018-10-24"));
    caseDTO.setProjectedEndDate(TestUtils.localDate("2018-10-23"));
    caseDTO.setResponsibleAgency("PRIVATE_ADOPTION_AGENCY");
    caseDTO.setStartDate(TestUtils.localDate("2016-10-23"));
    caseDTO.setStateCode((short) 1828);
    caseDTO.setActiveServiceComponentCode((short)1692);
    caseDTO.setActiveServiceComponentStartDate(TestUtils.localDate("2016-10-23"));

    return caseDTO;
  }

  private CaseDTO updateCase(WebTarget target, CaseDTO caseDTO) {
    return target
        .request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.entity(caseDTO, MediaType.APPLICATION_JSON_TYPE), CaseDTO.class);
  }
}
