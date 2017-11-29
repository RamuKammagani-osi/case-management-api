package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.web.rest.utils.AssertResponseHelper.assertEqualsResponse;
import static io.dropwizard.testing.FixtureHelpers.fixture;

import gov.ca.cwds.cm.BaseApiIntegrationTest;
import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.service.dto.ClientDTO;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-3 Team
 */
public class ClientResourceTest extends BaseApiIntegrationTest {

  public static final String CLIENT_ID = "22223312D";

  @BeforeClass
  public static void beforeClass() throws Exception {
    setUpCms();
    setUpDb();
    runScripts(
        "liquibase/client/client_test_get_for_client_endpoint.xml"
    );
  }

  @Test
  public void testGetChildClientById() throws Exception {
    WebTarget target = clientTestRule.target(Constants.API.CLIENTS + "/" + CLIENT_ID);
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    ClientDTO clientDTO = response.readEntity(ClientDTO.class);

    String fixture = fixture("fixtures/client-by-id-response.json");
    assertEqualsResponse(fixture, transformDTOtoJSON(clientDTO));
  }
}
