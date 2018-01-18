package gov.ca.cwds.cm;

import static gov.ca.cwds.cm.ConfigurationProvider.CONFIG_FILE_PATH;

import gov.ca.cwds.cm.web.rest.RestClientTestRule;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.client.Client;
import org.glassfish.jersey.client.JerseyClient;
import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author CWDS TPT-3 Team
 */
public abstract class BaseResourceTest {

  public static final int UNPROCESSABLE_ENTITY_STATUS_CODE = 422;
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @ClassRule
  public static final DropwizardAppRule<CmApiConfiguration> DROPWIZARD_APP_RULE =
      new DropwizardAppRule<CmApiConfiguration>(
          CmApiApplication.class, ResourceHelpers.resourceFilePath(CONFIG_FILE_PATH)) {

        @Override
        public Client client() {
          Client client = super.client();
          if (((JerseyClient) client).isClosed()) {
            client = clientBuilder().build();
          }
          return client;
        }
      };

  @Rule
  public RestClientTestRule clientTestRule = new RestClientTestRule(DROPWIZARD_APP_RULE);

  public static void setUpCms() throws Exception {
    getCmsDatabaseHelper().runScript("liquibase/cwscms_database_master.xml");
  }

  public static void setUpCwsRs1() throws Exception {
    getRsDatabaseHelper().runScript("liquibase/cwsrs1-database-master.xml");
  }

  public static void setUpDb() throws Exception {
    getCmsDatabaseHelper().runScript("liquibase/migration_master.xml");
  }

  protected static DatabaseHelper getCmsDatabaseHelper() {
    return createDatabaseHelper(DROPWIZARD_APP_RULE.getConfiguration().getCmsDataSourceFactory());
  }

  protected static DatabaseHelper getRsDatabaseHelper() {
    return createDatabaseHelper(DROPWIZARD_APP_RULE.getConfiguration().getCwsRsDataSourceFactory());
  }

  private static DatabaseHelper createDatabaseHelper(DataSourceFactory dataSourceFactory) {
    return new DatabaseHelper(
        dataSourceFactory.getUrl(),
        dataSourceFactory.getUser(),
        dataSourceFactory.getPassword(),
        dataSourceFactory.getProperties().get("hibernate.default_schema")
    );
  }

  public static void runScripts(final String... scriptPaths) throws Exception {
    final DatabaseHelper databaseHelper = getCmsDatabaseHelper();
    for (String path : scriptPaths) {
      databaseHelper.runScript(path);
    }
  }

  public String transformDTOtoJSON(Object o) throws Exception {
    return clientTestRule.getMapper().writeValueAsString(o);
  }

  public static LocalDate localDate(String dateStr) {
    return LocalDate.parse(dateStr, DATE_FORMATTER);
  }
}
