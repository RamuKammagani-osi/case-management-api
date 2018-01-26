package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.test.util.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cm.Constants.API;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import liquibase.exception.LiquibaseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-3 Team
 */
public class CSECHistoryResourceTest extends AbstractIntegrationTest {

  public static final String CLIENT_ID_2 = "0Kk7CHj000";
  public static final String CLIENT_ID = "1Kk7CHj000";
  public static final String WRONG_CLIENT_ID = "0000000000";
  private static final String[] LIQUIBASE_SCRIPTS = {
      "liquibase/client/client_test_get.xml",
      "liquibase/client/child_client_test_get.xml",
      "liquibase/csec/csechistory_get.xml",
  };

  @BeforeClass
  public static void beforeClass() throws Exception {
    DATABASE_HELPER_CMS.runScripts(LIQUIBASE_SCRIPTS);
  }

  @AfterClass
  public static void cleanUp() throws LiquibaseException {
    DATABASE_HELPER_CMS.rollbackScripts(LIQUIBASE_SCRIPTS);
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
