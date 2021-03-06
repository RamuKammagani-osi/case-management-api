package gov.ca.cwds.cm;

import gov.ca.cwds.cm.integration.AddressResourceIT;
import gov.ca.cwds.cm.integration.CaseResourceIT;
import gov.ca.cwds.cm.integration.StaffPersonResourceIT;
import gov.ca.cwds.cm.integration.SystemCodeResourceIT;
import gov.ca.cwds.cm.test.RestClientTestRule;
import gov.ca.cwds.cm.test.util.ConfigurationProvider;
import gov.ca.cwds.cm.test.util.IntegrationTestContextHolder;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author CWDS TPT-3 Team
 *
 * The suite is run with "integrationTest" gradle task.
 * It requires "api.url" system property to be set.
 * The suite is used to test a remote environment.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AddressResourceIT.class,
    CaseResourceIT.class,
    StaffPersonResourceIT.class,
    SystemCodeResourceIT.class,
})
public class IntegrationTestSuite {

  @BeforeClass
  public static void init() {
    IntegrationTestContextHolder.cmApiConfiguration = ConfigurationProvider.CONFIGURATION;
    IntegrationTestContextHolder.clientTestRule = new RestClientTestRule();
  }

}
