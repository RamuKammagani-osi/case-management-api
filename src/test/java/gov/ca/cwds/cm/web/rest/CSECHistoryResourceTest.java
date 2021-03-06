package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.test.util.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cm.Constants.API;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class CSECHistoryResourceTest extends AbstractIntegrationTest {

  public static final String CLIENT_ID_2 = "BKk7CHj02Y";
  public static final String CLIENT_ID = "BKk7CHj01Y";
  public static final String WRONG_CLIENT_ID = "0000000000";

  @BeforeClass
  public static void beforeClass() throws Exception {
    DATABASE_HELPER_CMS.runScripts("liquibase/csec/csechistory_get.xml");
  }

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
  public void getCSECHistoryByChildClientId2Success() throws Exception {
    Response response =
        clientTestRule
            .target(API.CHILD_CLIENTS + "/" + CLIENT_ID_2 + "/" + API.CSEC_HISTORY)
            .request(MediaType.APPLICATION_JSON)
            .get();

    assertResponseByFixturePath(response, "fixtures/csechistories-by-clientid-response.json");
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
