package gov.ca.cwds.cm;

import static gov.ca.cwds.cm.test.util.ConfigurationProvider.CONFIG_FILE_PATH;

import gov.ca.cwds.cm.test.util.DatabaseHelper;
import gov.ca.cwds.cm.test.util.IntegrationTestContextHolder;
import gov.ca.cwds.cm.web.rest.AddressResourceTest;
import gov.ca.cwds.cm.test.RestClientTestRule;
import gov.ca.cwds.cm.web.rest.CSECHistoryResourceTest;
import gov.ca.cwds.cm.web.rest.CaseResourceTest;
import gov.ca.cwds.cm.web.rest.StaffPersonResourceTest;
import gov.ca.cwds.cm.web.rest.SystemCodeResourceTest;
import gov.ca.cwds.cm.web.rest.client.ChildClientResourceTest;
import gov.ca.cwds.cm.web.rest.client.ClientResourceTest;
import gov.ca.cwds.cm.web.rest.client.SafetyAlertsResourceTest;
import gov.ca.cwds.cm.web.rest.system.SystemInformationResourceTest;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import javax.ws.rs.client.Client;
import liquibase.exception.LiquibaseException;
import org.glassfish.jersey.client.JerseyClient;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author CWDS TPT-3 Team
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AddressResourceTest.class,
    CaseResourceTest.class,
    ChildClientResourceTest.class,
    ClientResourceTest.class,
    CSECHistoryResourceTest.class,
    SafetyAlertsResourceTest.class,
    StaffPersonResourceTest.class,
    SystemCodeResourceTest.class,
    SystemInformationResourceTest.class,
})
public class InMemoryIntegrationTestSuite {

  @ClassRule
  public static final DropwizardAppRule<CmApiConfiguration> DROPWIZARD_APP_RULE =
      new DropwizardAppRule<CmApiConfiguration>(
          CmApiApplication.class,
          ResourceHelpers.resourceFilePath(CONFIG_FILE_PATH)
      ) {
        @Override
        public Client client() {
          Client client = super.client();
          if (((JerseyClient) client).isClosed()) {
            client = clientBuilder().build();
          }
          return client;
        }
      };

  @BeforeClass
  public static void init() throws Exception {
    IntegrationTestContextHolder.cmApiConfiguration = DROPWIZARD_APP_RULE.getConfiguration();
    IntegrationTestContextHolder.clientTestRule = new RestClientTestRule(DROPWIZARD_APP_RULE);
    initCmsDb();
    initCwsRs1Db();
  }

  private static void initCwsRs1Db() throws LiquibaseException {
    final DatabaseHelper cwsRs1DatabaseHelper = new DatabaseHelper(
        IntegrationTestContextHolder.cmApiConfiguration.getCwsRsDataSourceFactory()
    );
    cwsRs1DatabaseHelper.runScript("liquibase/cwsrs1-database-master.xml");
  }

  private static void initCmsDb() throws LiquibaseException {
    final DatabaseHelper cmsDatabaseHelper = new DatabaseHelper(
        IntegrationTestContextHolder.cmApiConfiguration.getCmsDataSourceFactory()
    );
    cmsDatabaseHelper.runScript("liquibase/cwscms_database_master.xml");
  }

}
