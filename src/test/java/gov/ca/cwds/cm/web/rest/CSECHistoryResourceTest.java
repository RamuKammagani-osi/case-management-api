package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cm.BaseResourceTest;
import gov.ca.cwds.cm.Constants.API;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class CSECHistoryResourceTest extends BaseResourceTest {

  public static final String CLIENT_ID = "BKk7CHj01Y";
  public static final String WRONG_CLIENT_ID = "0000000000";

  @Test
  public void getCSECHistoryByChildClientIdSuccess() throws Exception {
    Response response =
        clientTestRule
            .target(API.CHILD_CLIENTS + "/" + CLIENT_ID + "/" + API.CSEC_HISTORY)
            .request(MediaType.APPLICATION_JSON)
            .get();

    assertResponseByFixturePath(response, "fixtures/csechistory-by-clientid-response.json");
  }

  @Test
  public void getCSECHistoryByChildClientIdFailure() {
    Response response =
        clientTestRule
            .target(API.CHILD_CLIENTS + "/" + WRONG_CLIENT_ID + "/" + API.CSEC_HISTORY)
            .request(MediaType.APPLICATION_JSON)
            .get();

    assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }
}
