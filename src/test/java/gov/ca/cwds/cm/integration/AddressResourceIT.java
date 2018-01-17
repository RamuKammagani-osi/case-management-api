package gov.ca.cwds.cm.integration;

import static gov.ca.cwds.cm.web.rest.utils.AssertFixtureUtils.assertResponseByFixturePath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import gov.ca.cwds.cm.BaseIntegrationTest;
import gov.ca.cwds.cm.Constants.API;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CWDS TPT-3 Team
 */
public class AddressResourceIT extends BaseIntegrationTest {

  private static final String LIQUIBASE_SCRIPT = "liquibase/address/dml_address_test_data.xml";

  @BeforeClass
  public static void beforeClass() throws Exception {
    runScripts(LIQUIBASE_SCRIPT);
  }

  @AfterClass
  public static void afterClass() throws Exception {
    rollbackScripts(LIQUIBASE_SCRIPT);
  }

  @Test
  public void getAddress_success_whenAddressExists() throws Exception {
    // when
    final Response actualResult = clientTestRule.target(API.ADDRESSES + "/Ab7mue101c")
        .request(MediaType.APPLICATION_JSON_TYPE)
        .get(Response.class);

    // then
    assertResponseByFixturePath(
        actualResult,
        "fixtures/address/getAddress_success_response.json"
    );
  }

  @Test
  public void getAddress_code404_whenAddressDoesNotExist() {
    // when
    final Response actualResult = clientTestRule.target(API.ADDRESSES + "/NotExistingId")
        .request(MediaType.APPLICATION_JSON_TYPE)
        .get(Response.class);

    // then
    assertThat(actualResult.getStatus(), is(equalTo(404)));
  }

}
