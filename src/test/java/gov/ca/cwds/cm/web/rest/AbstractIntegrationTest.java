package gov.ca.cwds.cm.web.rest;

import gov.ca.cwds.cm.test.RestClientTestRule;
import gov.ca.cwds.cm.test.util.IntegrationTestContextHolder;
import gov.ca.cwds.cm.test.util.DatabaseHelper;
import org.junit.Rule;

/**
 * @author CWDS TPT-3 Team
 */
public abstract class AbstractIntegrationTest {

  protected static final DatabaseHelper DATABASE_HELPER_CMS = new DatabaseHelper(
      IntegrationTestContextHolder.cmApiConfiguration.getCmsDataSourceFactory()
  );

  protected static final DatabaseHelper DATABASE_HELPER_RS1 = new DatabaseHelper(
      IntegrationTestContextHolder.cmApiConfiguration.getCwsRsDataSourceFactory()
  );

  @Rule
  public RestClientTestRule clientTestRule = IntegrationTestContextHolder.clientTestRule;

  public String transformDTOtoJSON(Object o) throws Exception {
    return clientTestRule.getMapper().writeValueAsString(o);
  }

}
