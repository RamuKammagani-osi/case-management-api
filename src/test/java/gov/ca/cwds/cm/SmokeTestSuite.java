package gov.ca.cwds.cm;

import gov.ca.cwds.cm.test.RestClientTestRule;
import gov.ca.cwds.cm.test.util.ConfigurationProvider;
import gov.ca.cwds.cm.test.util.IntegrationTestContextHolder;
import gov.ca.cwds.cm.web.rest.system.SystemInformationResourceTest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author CWDS TPT-3 Team
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    SystemInformationResourceTest.class
})
public class SmokeTestSuite {

  @BeforeClass
  public static void init() {
    IntegrationTestContextHolder.cmApiConfiguration = ConfigurationProvider.CONFIGURATION;
    IntegrationTestContextHolder.clientTestRule = new RestClientTestRule();
  }
}
