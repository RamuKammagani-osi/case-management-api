package gov.ca.cwds.cm.web.rest.client;

import static gov.ca.cwds.cm.test.util.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.service.dto.ClientDTO;
import gov.ca.cwds.cm.web.rest.AbstractIntegrationTest;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class ClientResourceTest extends AbstractIntegrationTest {

  private static final String CLIENT_ID = "0Kk7CHj000";
  private static final String LIQUIBASE_SCRIPT = "liquibase/client/client_test_get_for_client_endpoint.xml";
  private static final String LIQUIBASE_SCRIPT_RS = "liquibase/client/client-permissions.xml";

  @BeforeClass
  public static void init() throws Exception {
    DATABASE_HELPER_CMS.runScripts(LIQUIBASE_SCRIPT);
    DATABASE_HELPER_RS1.runScripts(LIQUIBASE_SCRIPT_RS);
  }

  @AfterClass
  public static void cleanUp() throws Exception {
    DATABASE_HELPER_CMS.rollbackScripts(LIQUIBASE_SCRIPT);
    DATABASE_HELPER_RS1.rollbackScripts(LIQUIBASE_SCRIPT_RS);
  }

  @Test
  public void testGetClientById_success_whenAuthorizedUser() throws Exception {
    // given
    final String expectedFixture = fixture("fixtures/client-by-id-response.json");

    // when
    final Response response = clientTestRule
        .withSecurityToken(AUTHORIZED_ACCOUNT_FIXTURE)
        .target(Constants.API.CLIENTS + "/" + CLIENT_ID)
        .request(MediaType.APPLICATION_JSON)
        .get();
    final ClientDTO client = response.readEntity(ClientDTO.class);

    // then
    assertEqualsResponse(expectedFixture, transformDTOtoJSON(client));
  }

  @Test
  public void testGetClientById_notAuthorized_whenUnauthorizedUser() throws IOException {
    // given
    // when
    final Response response = clientTestRule
        .withSecurityToken(NOT_AUTHORIZED_ACCOUNT_FIXTURE)
        .target(Constants.API.CLIENTS + "/" + CLIENT_ID)
        .request(MediaType.APPLICATION_JSON)
        .get();

    // then
    assertThat(response.getStatus(), is(403));
  }
}
