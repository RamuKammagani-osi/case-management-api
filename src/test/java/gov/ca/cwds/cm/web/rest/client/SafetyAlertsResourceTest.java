package gov.ca.cwds.cm.web.rest.client;

import static gov.ca.cwds.cm.test.util.AssertFixtureUtils.assertResponseByFixturePath;
import static org.junit.Assert.assertEquals;
import static gov.ca.cwds.cm.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import gov.ca.cwds.cm.Constants.API;
import gov.ca.cwds.cm.web.rest.AbstractIntegrationTest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import liquibase.exception.LiquibaseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class SafetyAlertsResourceTest extends AbstractIntegrationTest {

  public static final String CLIENT_WITH_ALERTS = "0Kk7CHj000";
  public static final String CLIENT_WITH_NO_ALERTS = "0abcde000";
  public static final String NON_EXISTENT_CLIENT = "nonExistentClient";
  private static final String SLASH = "/";
  private static final String LIQUIBASE_SCRIPT = "liquibase/client/safety-alert/client-safety-alert-get.xml";

  @BeforeClass
  public static void init() throws Exception {
    setUpCms();
    getCmsDatabaseHelper().runScript(LIQUIBASE_SCRIPT);
  }

  @AfterClass
  public static void cleanUp() throws LiquibaseException {
    getCmsDatabaseHelper().rollback(LIQUIBASE_SCRIPT);
  }

  @Test
  public void getSafetyAlertsByClientId_success_whenClientWithAlerts() throws Exception {
    final Response response = clientTestRule
            .target(API.CLIENTS + SLASH + CLIENT_WITH_ALERTS + SLASH + API.SAFETY_ALERTS)
            .request(MediaType.APPLICATION_JSON)
            .get();
    assertResponseByFixturePath(response, "fixtures/safety-alerts-by-clientid-response.json");
  }

  @Test
  public void getSafetyAlertsByClientId_notFound_whenClientWithNoAlerts() {
    final Response response = clientTestRule
            .target(API.CLIENTS + SLASH + CLIENT_WITH_NO_ALERTS + SLASH + API.SAFETY_ALERTS)
            .request(MediaType.APPLICATION_JSON)
            .get();
    assertThat(response.getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
  }

  @Test
  public void getSafetyAlertsByClientId_notFound_whenNonExistentClient() {
    final Response response = clientTestRule
            .target(API.CLIENTS + SLASH + NON_EXISTENT_CLIENT + SLASH + API.SAFETY_ALERTS)
            .request(MediaType.APPLICATION_JSON)
            .get();
    assertThat(response.getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
  }
}
