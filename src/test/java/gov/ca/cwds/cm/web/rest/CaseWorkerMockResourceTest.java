package gov.ca.cwds.cm.web.rest;

import gov.ca.cwds.cm.BaseApiIntegrationTest;
import gov.ca.cwds.cm.Constants;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static gov.ca.cwds.cm.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/** @author CWDS TPT-3 Team */
public class CaseWorkerMockResourceTest extends BaseApiIntegrationTest {

  public static final String CASEWORKER_ID = "q48";
  public static final String WRONG_CASEWORKER_ID = "-1";

  @BeforeClass
  public static void beforeClass() throws Exception {
    // setUpCms();
    // setUpDb();
  }

  @Test
  public void testGetCaseById() throws Exception {
    WebTarget target =
        clientTestRule.target(Constants.API.STAFF + "/" + CASEWORKER_ID + "/clients");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertResponseByFixturePath(response, "fixtures/list_of_related_clients_by_staff_id.json");
  }

  @Test
  public void testGetBaseByWrongId() {
    WebTarget target =
        clientTestRule.target(Constants.API.STAFF + "/" + WRONG_CASEWORKER_ID + "/clients");
    Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);
    try {
      invocation.get(List.class);
      fail("Correct response is not expected");
    } catch (NotFoundException e) {
      assertThat("HTTP 404 Not Found").isEqualTo(e.getMessage());
    }
  }
}
