package gov.ca.cwds.cm;

import gov.ca.cwds.cm.web.rest.RestClientTestRule;
import io.dropwizard.db.DataSourceFactory;
import org.junit.Rule;

/** @author CWDS TPT-3 Team */
public abstract class BaseIntegrationTest {

  private static final CmApiConfiguration CONFIGURATION = ConfigurationProvider.CONFIGURATION;

  @Rule
  public RestClientTestRule clientTestRule = new RestClientTestRule();

  protected static DatabaseHelper getCmsDatabaseHelper() {
    return createDatabaseHelper(CONFIGURATION.getCmsDataSourceFactory());
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

}
