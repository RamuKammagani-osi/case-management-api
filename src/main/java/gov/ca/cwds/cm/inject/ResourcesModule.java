package gov.ca.cwds.cm.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import gov.ca.cwds.cm.service.ChildClientService;
import gov.ca.cwds.cm.web.rest.ChildClientResource;
import gov.ca.cwds.cm.web.rest.LandingMockResource;
import gov.ca.cwds.cm.web.rest.system.SystemInformationResource;
import gov.ca.cwds.rest.resources.ResourceDelegate;
import gov.ca.cwds.rest.resources.ServiceBackedResourceDelegate;

/**
 * Identifies all CM_UNIT_OF_WORK API domain resource classes available for dependency injection
 * by Guice.
 *
 * @author CWDS TPT-3 Team
 */
public class ResourcesModule extends AbstractModule {

  /**
   * Default constructor
   */
  public ResourcesModule() {
    // Do nothing - Default Constructor
  }

  @Override
  protected void configure() {
    bind(SystemInformationResource.class);
    bind(ChildClientResource.class);
    bind(LandingMockResource.class);
  }

  @Provides
  @ChildClientServiceBackedResource
  public ResourceDelegate childClientBackedResource(Injector injector) {
    return new ServiceBackedResourceDelegate(
            injector.getInstance(ChildClientService.class));
  }

}
