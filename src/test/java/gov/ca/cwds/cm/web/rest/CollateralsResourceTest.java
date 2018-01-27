package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.test.util.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cm.Constants.API;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class CollateralsResourceTest extends AbstractIntegrationTest {

  private static final String CASE_ID = "0tttyyy000";
  private static final String INVALID_CASE_ID = "-111111111";

  @Before
  public void setup() {}

  @Test
  public void getCollateralsByValidCaseId() throws Exception {
    WebTarget target = clientTestRule.target(API.CASES + "/" + CASE_ID + "/" + "collaterals");
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertResponseByFixturePath(response, "fixtures/collaterals-by-case.json");
  }

  @Test
  public void getCollateralsByInvalidCaseId() {
    WebTarget target =
        clientTestRule.target(API.CASES + "/" + INVALID_CASE_ID + "/" + "collaterals");
    Response response = target.request((MediaType.APPLICATION_JSON)).get();
    assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }
}
