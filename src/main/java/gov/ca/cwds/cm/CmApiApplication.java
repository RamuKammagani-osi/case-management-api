package gov.ca.cwds.cm;

import com.google.inject.Module;
import com.google.inject.Provides;
import gov.ca.cwds.cm.inject.ApplicationModule;
import gov.ca.cwds.cm.inject.DataAccessModule;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;

/** @author CWDS TPT-3 Team */
public class CmApiApplication extends BaseCmApiApplication<CmApiConfiguration> {

  public static void main(String[] args) throws Exception {
    new CmApiApplication().run(args);
  }

  @Override
  public Module applicationModule(Bootstrap<CmApiConfiguration> bootstrap) {
    return new ApplicationModule<CmApiConfiguration>(bootstrap) {

      @Override
      protected void configure() {
        super.configure();
        install(
            new DataAccessModule(bootstrap) {

              @Provides
              UnitOfWorkAwareProxyFactory provideUnitOfWorkAwareProxyFactory() {
                return new UnitOfWorkAwareProxyFactory(getCmsHibernateBundle());
              }
            });
      }
    };
  }
}
