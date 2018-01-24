package gov.ca.cwds.cm.web.rest.client;

import static gov.ca.cwds.cm.test.util.AssertResponseHelper.assertEqualsResponse;
import static gov.ca.cwds.security.test.TestSecurityFilter.PATH_TO_PRINCIPAL_FIXTURE;
import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.service.dto.ClientDTO;
import gov.ca.cwds.cm.web.rest.AbstractIntegrationTest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author CWDS TPT-3 Team */
public class ClientResourceTest extends AbstractIntegrationTest {

  private static final String CLIENT_ID = "0Kk7CHj000";
  private static final String LIQUIBASE_SCRIPT = "liquibase/client/client_test_get_for_client_endpoint.xml";

  @BeforeClass
  public static void init() throws Exception {
    DATABASE_HELPER_CMS.runScripts(LIQUIBASE_SCRIPT);
  }

  @AfterClass
  public static void cleanUp() throws Exception {
    DATABASE_HELPER_CMS.rollbackScripts(LIQUIBASE_SCRIPT);
  }

  @Test
  public void testGetClientById_success_whenAuthorizedUser() throws Exception {
    // given
    final String expectedFixture = fixture("fixtures/client-by-id-response.json");

    // when
    final Response response = clientTestRule.target(Constants.API.CLIENTS + "/" + CLIENT_ID)
        .queryParam(PATH_TO_PRINCIPAL_FIXTURE, "fixtures/perry-account/000-all-authorized.json")
        .request(MediaType.APPLICATION_JSON)
        .get();
    final ClientDTO client = response.readEntity(ClientDTO.class);

    // then
    assertEqualsResponse(expectedFixture, transformDTOtoJSON(client));
  }

  @Test
  public void testGetClientById_notAuthorized_whenUnauthorizedUser() throws Exception {
    // given
    // when
    final Response response = clientTestRule.target(Constants.API.CLIENTS + "/" + CLIENT_ID)
        .request(MediaType.APPLICATION_JSON)
        .get();

    // then
    assertThat(response.getStatus(), is(403));
  }
}
