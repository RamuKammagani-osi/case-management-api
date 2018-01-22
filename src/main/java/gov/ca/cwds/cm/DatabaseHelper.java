package gov.ca.cwds.cm;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import liquibase.util.StringUtils;

/**
 * @author CWDS TPT-3 Team
 */
public class DatabaseHelper {

  private Database database;
  private final String url;
  private final String user;
  private final String password;
  private final String schema;

  public DatabaseHelper(String url, String user, String password, String schema) {
    this.url = url;
    this.user = user;
    this.password = password;
    this.schema = schema;
  }

  public void runScript(String script) throws LiquibaseException {
    try {
      Liquibase liquibase = new Liquibase(script, new ClassLoaderResourceAccessor(), getDatabase());
      liquibase.update((String) null);
    } catch (Exception e) {
      throw new LiquibaseException(e);
    }
  }

  public void rollback(String script) throws LiquibaseException {
    try {
      Liquibase liquibase = new Liquibase(script, new ClassLoaderResourceAccessor(), getDatabase());
      liquibase.rollback(Integer.MAX_VALUE, null);
    } catch (Exception e) {
      throw new LiquibaseException(e);
    }
  }

  public void runScript(String script, Map<String, Object> parameters, String schema)
      throws LiquibaseException {
    try {
      String defaultSchema = getDatabase().getDefaultSchemaName();
      getDatabase().setDefaultSchemaName(schema);
      Liquibase liquibase = new Liquibase(script, new ClassLoaderResourceAccessor(), getDatabase());
      parameters.forEach(liquibase::setChangeLogParameter);
      liquibase.update((String) null);
      getDatabase().setDefaultSchemaName(defaultSchema);
    } catch (Exception e) {
      throw new LiquibaseException(e);
    }
  }

  public void runScript(String script, String schema) throws LiquibaseException {
    try {
      String defaultSchema = getDatabase().getDefaultSchemaName();
      getDatabase().setDefaultSchemaName(schema);
      runScript(script);
      getDatabase().setDefaultSchemaName(defaultSchema);
    } catch (Exception e) {
      throw new LiquibaseException(e);
    }
  }

  private Database getDatabase() throws SQLException, DatabaseException {
    if (database == null) {
      Connection connection = DriverManager.getConnection(url, user, password);
      database = DatabaseFactory.getInstance()
          .findCorrectDatabaseImplementation(new JdbcConnection(connection));
      if (StringUtils.isNotEmpty(schema)) {
        database.setDefaultSchemaName(schema);
      }
    }

    return database;
  }
}
