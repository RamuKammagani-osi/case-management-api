package gov.ca.cwds.cm;

import io.dropwizard.configuration.ConfigurationException;
import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.FileConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.configuration.YamlConfigurationFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jersey.validation.Validators;
import java.io.IOException;

public class ConfigurationProvider {

  public static final String CONFIG_FILE_PATH = "config/case-management-api.yml";
  public static final CmApiConfiguration CONFIGURATION = parseConfiguration();

  private static CmApiConfiguration parseConfiguration() {
    final ConfigurationFactory<CmApiConfiguration> configurationFactory = new YamlConfigurationFactory<>(
        CmApiConfiguration.class,
        Validators.newValidatorFactory().getValidator(),
        Jackson.newObjectMapper(),
        "dw"
    );
    try {
      final SubstitutingSourceProvider substitutingSourceProvider = new SubstitutingSourceProvider(
          new FileConfigurationSourceProvider(),
          new EnvironmentVariableSubstitutor(false)
      );
      return configurationFactory.build(substitutingSourceProvider, CONFIG_FILE_PATH);
    } catch (IOException | ConfigurationException ignored) {
      ignored.printStackTrace();
    }
    return null;
  }
}
