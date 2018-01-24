package gov.ca.cwds.cm;

import gov.ca.cwds.cm.test.RestClientTestRule;
import gov.ca.cwds.cm.test.util.ConfigurationProvider;
import gov.ca.cwds.cm.test.util.IntegrationTestContextHolder;
import gov.ca.cwds.cm.web.rest.AddressResourceTest;
import gov.ca.cwds.cm.web.rest.CaseResourceTest;
import gov.ca.cwds.cm.web.rest.StaffPersonResourceTest;
import gov.ca.cwds.cm.web.rest.SystemCodeResourceTest;
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
    AddressResourceTest.class,
    CaseResourceTest.class,
    StaffPersonResourceTest.class,
    SystemCodeResourceTest.class,
})
public class IntegrationTestSuite {

  @BeforeClass
  public static void init() {
    IntegrationTestContextHolder.cmApiConfiguration = ConfigurationProvider.CONFIGURATION;
    IntegrationTestContextHolder.clientTestRule = new RestClientTestRule();
  }

}
