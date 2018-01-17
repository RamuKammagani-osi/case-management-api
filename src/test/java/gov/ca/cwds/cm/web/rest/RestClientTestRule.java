package gov.ca.cwds.cm.web.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import gov.ca.cwds.cm.CmApiConfiguration;
import gov.ca.cwds.cm.web.rest.utils.TestModeUtils;
import gov.ca.cwds.security.jwt.JwtConfiguration;
import gov.ca.cwds.security.jwt.JwtService;
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

  private final ObjectMapper mapper;
  private final String apiUrl;
  private final String token;
  private Client client;

  public RestClientTestRule() {
    token = initToken();
    mapper = Jackson.newObjectMapper();
    apiUrl = System.getProperty(TestModeUtils.API_URL);
  }

  public RestClientTestRule(DropwizardAppRule<CmApiConfiguration> dropWizardApplication) {
    token = initToken();
    mapper = dropWizardApplication.getObjectMapper();
    apiUrl = String.format("http://localhost:%s/", dropWizardApplication.getLocalPort());
  }

  private String initToken() {
    try {
      return generateToken();
    } catch (Exception e) {
      LOG.warn("Cannot generate token");
      return null;
    }
  }

  private String generateToken() throws Exception {
    JwtConfiguration configuration = getJwtConfiguration();
    JwtService jwtService = new JwtService(configuration);
    return jwtService.generate("id", "subject", "identity");
  }

  private JwtConfiguration getJwtConfiguration() throws IOException {
    Properties properties = new Properties();
    properties.load(new FileInputStream("config/shiro.ini"));

    JwtConfiguration configuration = new JwtConfiguration();
    //JWT
    configuration.setTimeout(30);
    configuration.setIssuer(properties.getProperty("perryRealm.tokenIssuer"));
    configuration.setKeyStore(new JwtConfiguration.KeyStoreConfiguration());
    //KeyStore
    configuration.getKeyStore()
        .setPath(new File(properties.getProperty("perryRealm.keyStorePath")).getPath());
    configuration.getKeyStore().setPassword(properties.getProperty("perryRealm.keyStorePassword"));
    //Sign/Validate Key
    configuration.getKeyStore().setAlias(properties.getProperty("perryRealm.keyStoreAlias"));
    configuration.getKeyStore()
        .setKeyPassword(properties.getProperty("perryRealm.keyStoreKeyPassword"));
    //Enc Key
    configuration
        .setEncryptionEnabled(Boolean.valueOf(properties.getProperty("perryRealm.useEncryption")));
    configuration.getKeyStore()
        .setEncKeyPassword(properties.getProperty("perryRealm.encKeyPassword"));
    configuration.getKeyStore().setEncAlias(properties.getProperty("perryRealm.encKeyAlias"));
    configuration.setEncryptionMethod(properties.getProperty("perryRealm.encryptionMethod"));
    return configuration;
  }

  public WebTarget target(String pathInfo) {
    String restUrl = apiUrl + pathInfo;
    return client.target(restUrl).queryParam("token", token).register(new LoggingFilter());
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

        // Trust All certificates for test purposes
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }

          public void checkClientTrusted(X509Certificate[] certs, String authType) {
          }

          public void checkServerTrusted(X509Certificate[] certs, String authType) {
          }
        }};

        client.getSslContext().init(null, trustAllCerts, new SecureRandom());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        client.register(new JacksonJsonProvider(mapper));
        statement.evaluate();
      }
    };
  }
}
