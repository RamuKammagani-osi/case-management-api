package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import gov.ca.cwds.cm.BaseResourceTest;
import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.Constants.API;
import gov.ca.cwds.cm.service.dto.CaseDTO;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.BeforeClass;
import org.junit.Test;

public class CaseResourceTest extends BaseResourceTest {

  public static final String CASE_ID = "ArgnUzi09L";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCms();
    setUpDb();
  }

  @Test
  public void testGetCaseById() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.CASES + "/" + CASE_ID);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    CaseDTO caseDTO = response.readEntity(CaseDTO.class);
    String fixture = fixture("fixtures/case-by-id-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(caseDTO));
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
    caseDTO.setEmancipationDate(localDate("2018-10-20"));
    caseDTO.setEndDate(localDate("2018-10-20"));
    caseDTO.setChildClientId("8m7hS7i07n");
    caseDTO.setReferralId("7Rgxy9S00T");
    caseDTO.setStaffPersonId("07n");
    caseDTO.setCountyCode((short) 1068);
    caseDTO.setLimitedAccess("NO_RESTRICTION");
    caseDTO.setLimitedAccessDesc("Text");
    caseDTO.setLimitedAccessCountyCode((short) 1068);
    caseDTO.setCaseName("Sibling Hanson");
    caseDTO.setNextTilpDueDate(localDate("2018-10-24"));
    caseDTO.setProjectedEndDate(localDate("2018-10-23"));
    caseDTO.setResponsibleAgency("PRIVATE_ADOPTION_AGENCY");
    caseDTO.setStartDate(localDate("2016-10-23"));
    caseDTO.setStateCode((short) 1828);
    caseDTO.setActiveServiceComponentCode((short)1692);
    caseDTO.setActiveServiceComponentStartDate(localDate("2016-10-23"));

    return caseDTO;
  }

  private CaseDTO updateCase(WebTarget target, CaseDTO caseDTO) {
    return target
        .request(MediaType.APPLICATION_JSON_TYPE)
        .put(Entity.entity(caseDTO, MediaType.APPLICATION_JSON_TYPE), CaseDTO.class);
  }
}
