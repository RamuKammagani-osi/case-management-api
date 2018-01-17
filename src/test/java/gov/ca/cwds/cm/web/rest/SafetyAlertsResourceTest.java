package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cm.BaseResourceTest;
import gov.ca.cwds.cm.Constants.API;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class SafetyAlertsResourceTest extends BaseResourceTest {

  public static final String CLIENT_ID = "BKk7CHj01Y";
  public static final String WRONG_CLIENT_ID = "0000000000";

  @Test
  public void getSafetyAlertsByClientIdSuccess() throws Exception {
    Response response =
        clientTestRule
            .target(API.CLIENTS + "/" + CLIENT_ID + "/" + API.SAFETY_ALERTS)
            .request(MediaType.APPLICATION_JSON)
            .get();

    assertResponseByFixturePath(response, "fixtures/safety-alerts-by-clientid-responce.json");
  }

  @Test
  public void getSafetyAlertsByClientIdFailure() {
    Response response =
        clientTestRule
            .target(API.CLIENTS + "/" + WRONG_CLIENT_ID + "/" + API.SAFETY_ALERTS)
            .request(MediaType.APPLICATION_JSON)
            .get();

    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }
}
