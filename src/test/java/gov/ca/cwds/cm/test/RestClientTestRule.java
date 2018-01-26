package gov.ca.cwds.cm.test;

import static gov.ca.cwds.cm.web.rest.AbstractIntegrationTest.AUTHORIZED_ACCOUNT_FIXTURE;
import static io.dropwizard.testing.FixtureHelpers.fixture;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.ObjectMapperUtils;
import gov.ca.cwds.cm.CmApiConfiguration;
import gov.ca.cwds.cm.test.util.TestUtils;
import gov.ca.cwds.security.jwt.JwtConfiguration;
import gov.ca.cwds.security.jwt.JwtService;
import gov.ca.cwds.util.Require;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS TPT-3 Team
 */
public class RestClientTestRule implements TestRule {

  private static final Logger LOG = LoggerFactory.getLogger(RestClientTestRule.class);

  private static final String QUERY_PARAM_TOKEN = "token";
  private final static String DEFAULT_IDENTITY_JSON = fixture(AUTHORIZED_ACCOUNT_FIXTURE);

  private static final TrustManager[] TRUST_ALL_CERTS = new TrustManager[]{new X509TrustManager() {
    public X509Certificate[] getAcceptedIssuers() {
      return null;
    }
    public void checkClientTrusted(X509Certificate[] certs, String authType) {}

    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
  }};

  private final ObjectMapper mapper;
  private final String apiUrl;
  private String token;
  private Client client;

  private JwtConfiguration jwtConfiguration;


  public RestClientTestRule() {
    token = initToken();
    mapper = Jackson.newObjectMapper();
    ObjectMapperUtils.configureObjectMapper(mapper);
    apiUrl = TestUtils.getApiUrl();
    Require.requireNotNullAndNotEmpty(apiUrl);
  }

  public RestClientTestRule(DropwizardAppRule<CmApiConfiguration> dropWizardApplication) {
    token = initToken();
    mapper = dropWizardApplication.getObjectMapper();
    apiUrl = String.format("http://localhost:%s/", dropWizardApplication.getLocalPort());
  }

  public RestClientTestRule withSecurityToken(String identityJsonFilePath) throws IOException {
    final String identity = identityJsonFilePath != null
        ? fixture(identityJsonFilePath)
        : DEFAULT_IDENTITY_JSON;
    this.token = generateToken(identity);
    return this;
  }

  private String initToken() {
    try {
      return generateToken(DEFAULT_IDENTITY_JSON);
    } catch (Exception e) {
      LOG.warn("Cannot generate token");
      return null;
    }
  }

  private String generateToken(String identity) throws IOException {
    JwtConfiguration configuration = getJwtConfiguration();
    JwtService jwtService = new JwtService(configuration);
    return jwtService.generate("id", "subject", identity);
  }

  private JwtConfiguration getJwtConfiguration() throws IOException {
    if (jwtConfiguration != null) {
      return jwtConfiguration;
    }

    Properties properties = new Properties();
    properties.load(new FileInputStream("config/shiro.ini"));

    jwtConfiguration = new JwtConfiguration();
    //JWT
    jwtConfiguration.setTimeout(30);
    jwtConfiguration.setIssuer(properties.getProperty("perryRealm.tokenIssuer"));
    jwtConfiguration.setKeyStore(new JwtConfiguration.KeyStoreConfiguration());
    //KeyStore
    jwtConfiguration.getKeyStore()
        .setPath(new File(properties.getProperty("perryRealm.keyStorePath")).getPath());
    jwtConfiguration.getKeyStore().setPassword(properties.getProperty("perryRealm.keyStorePassword"));
    //Sign/Validate Key
    jwtConfiguration.getKeyStore().setAlias(properties.getProperty("perryRealm.keyStoreAlias"));
    jwtConfiguration.getKeyStore()
        .setKeyPassword(properties.getProperty("perryRealm.keyStoreKeyPassword"));
    //Enc Key
    jwtConfiguration
        .setEncryptionEnabled(Boolean.valueOf(properties.getProperty("perryRealm.useEncryption")));
    jwtConfiguration.getKeyStore()
        .setEncKeyPassword(properties.getProperty("perryRealm.encKeyPassword"));
    jwtConfiguration.getKeyStore().setEncAlias(properties.getProperty("perryRealm.encKeyAlias"));
    jwtConfiguration.setEncryptionMethod(properties.getProperty("perryRealm.encryptionMethod"));
    return jwtConfiguration;
  }

  public WebTarget target(String pathInfo) {
    String restUrl = apiUrl + pathInfo;
    return client.target(restUrl)
        .queryParam(QUERY_PARAM_TOKEN, token)
        .register(new LoggingFilter());
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  @Override
  public Statement apply(Statement statement, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {

        JerseyClientBuilder clientBuilder = new JerseyClientBuilder()
            .property(ClientProperties.CONNECT_TIMEOUT, 5000)
            .property(ClientProperties.READ_TIMEOUT, 20000)
            .hostnameVerifier((hostName, sslSession) -> {
              // Just ignore host verification for test purposes
              return true;
            });

        client = clientBuilder.build();
        client.register(new JacksonJsonProvider(mapper));
        client.getSslContext().init(null, TRUST_ALL_CERTS, new SecureRandom());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        statement.evaluate();
      }
    };
  }
}
