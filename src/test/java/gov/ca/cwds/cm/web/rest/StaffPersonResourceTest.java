package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.test.util.AssertFixtureUtils.assertResponseByFixturePath;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.Constants.API;
import gov.ca.cwds.cm.service.dto.facade.CaseByStaff;
import java.io.IOException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.assertj.core.api.AssertionsForClassTypes;
import org.hamcrest.MatcherAssert;
import org.json.JSONException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class StaffPersonResourceTest extends AbstractIntegrationTest {

  public static final String WRONG_STAFF_PERSON_ID = "-1";
  private static final String[] LIQUIBASE_SCRIPTS = {
    "liquibase/staff-person/staff-person.xml",
    "liquibase/case/get-cases-by-staff-id_test-data.xml",
    "liquibase/referral/get_referrals_by_staff_id.xml"
  };

  @BeforeClass
  public static void beforeClass() throws Exception {
    DATABASE_HELPER_CMS.runScripts(LIQUIBASE_SCRIPTS);
  }

  @AfterClass
  public static void afterClass() throws Exception {
    DATABASE_HELPER_CMS.rollbackScripts(LIQUIBASE_SCRIPTS);
  }

  @Test
  public void getClientsByStaffId() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.STAFF + "/000/clients");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertResponseByFixturePath(response, "fixtures/list_of_related_clients_by_staff_id.json");
  }

  @Test
  public void getClientsByWrongStaffId() {
    WebTarget target =
        clientTestRule.target(Constants.API.STAFF + "/" + WRONG_STAFF_PERSON_ID + "/clients");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    AssertionsForClassTypes.assertThat(response.getStatus())
        .isEqualTo(Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void getActiveReferralByStaffId() throws Exception {
    WebTarget target = clientTestRule.target(API.STAFF + "/000/" + API.REFERRALS);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertResponseByFixturePath(response, "fixtures/referral_by_staff_id.json");
  }

  @Test
  public void getActiveReferralsByStaffId() throws IOException, JSONException {
    WebTarget target = clientTestRule.target(API.STAFF + "/zzz/" + API.REFERRALS);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertResponseByFixturePath(response, "fixtures/referrals_by_staff_id.json");
  }

  @Test
  public void getActiveReferralsByWrongStaffId() {
    WebTarget target =
        clientTestRule.target(API.STAFF + "/" + WRONG_STAFF_PERSON_ID + "/" + API.REFERRALS);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertThat(response.getStatus()).isEqualTo(Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void getActiveCasesByStaffId_success() throws IOException, JSONException {
    // given
    final String inputUri = API.STAFF + "/000/" + API.CASES;

    // when
    final Response actualResult =
        clientTestRule.target(inputUri).request(MediaType.APPLICATION_JSON).get();

    // then
    MatcherAssert.assertThat(actualResult.getStatus(), is(equalTo(Status.OK.getStatusCode())));
    assertResponseByFixturePath(actualResult, "fixtures/case-by-staff-id-response.json");
  }

  @Test
  public void getActiveCasesByStaffId_emptyArray_whenNothingFound() {
    // given
    final String inputUri = API.STAFF + "/" + WRONG_STAFF_PERSON_ID + "/" + API.CASES;

    // when
    final Response response =
        clientTestRule.target(inputUri).request(MediaType.APPLICATION_JSON).get();
    final CaseByStaff[] actualArray = response.readEntity(CaseByStaff[].class);

    // then
    MatcherAssert.assertThat(response.getStatus(), is(equalTo(Status.OK.getStatusCode())));
    MatcherAssert.assertThat(actualArray.length, is(equalTo(0)));
  }
}
