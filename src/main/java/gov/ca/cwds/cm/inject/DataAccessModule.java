package gov.ca.cwds.cm.inject;

import static gov.ca.cwds.cm.Constants.UnitOfWork.CMS;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cm.CmApiConfiguration;
import gov.ca.cwds.data.legacy.cms.entity.Address;
import gov.ca.cwds.data.legacy.cms.entity.BackgroundCheck;
import gov.ca.cwds.data.legacy.cms.entity.Case;
import gov.ca.cwds.data.legacy.cms.entity.ChildClient;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.data.legacy.cms.entity.ClientAddress;
import gov.ca.cwds.data.legacy.cms.entity.CountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.LicensingVisit;
import gov.ca.cwds.data.legacy.cms.entity.LongText;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherChildrenInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherPeopleScpRelationship;
import gov.ca.cwds.data.legacy.cms.entity.OutOfHomePlacement;
import gov.ca.cwds.data.legacy.cms.entity.OutOfStateCheck;
import gov.ca.cwds.data.legacy.cms.entity.PlacementEpisode;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeNotes;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeProfile;
import gov.ca.cwds.data.legacy.cms.entity.StaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ActiveServiceComponentType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ApprovalStatusType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.CaseClosureReasonType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Country;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.DeathCircumstancesType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.State;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SystemCode;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.VisitType;
import gov.ca.cwds.inject.CmsHibernateBundle;
import gov.ca.cwds.inject.CmsSessionFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import org.hibernate.SessionFactory;

/** @author CWDS TPT-3 Team */
public class DataAccessModule extends AbstractModule {

  private final ImmutableList<Class<?>> cmsEntities =
      ImmutableList.<Class<?>>builder()
          .add(
              ActiveServiceComponentType.class,
              Address.class,
              ApprovalStatusType.class,
              BackgroundCheck.class,
              Case.class,
              CaseClosureReasonType.class,
              ChildClient.class,
              Client.class,
              ClientAddress.class,
              Country.class,
              County.class,
              CountyLicenseCase.class,
              DeathCircumstancesType.class,
              LicensingVisit.class,
              LongText.class,
              LongText.class,
              OtherAdultsInPlacementHome.class,
              OtherChildrenInPlacementHome.class,
              OtherPeopleScpRelationship.class,
              OutOfHomePlacement.class,
              OutOfStateCheck.class,
              PlacementEpisode.class,
              PlacementHome.class,
              PlacementHomeNotes.class,
              PlacementHomeProfile.class,
              StaffPerson.class,
              State.class,
              SubstituteCareProvider.class,
              SystemCode.class,
              VisitType.class)
          .build();

  private final HibernateBundle<CmApiConfiguration> cmsHibernateBundle =
      new HibernateBundle<CmApiConfiguration>(cmsEntities, new SessionFactoryFactory()) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CmApiConfiguration configuration) {
          return configuration.getCmsDataSourceFactory();
        }

        @Override
        public String name() {
          return CMS;
        }
      };

  public DataAccessModule(Bootstrap<? extends CmApiConfiguration> bootstrap) {
    bootstrap.addBundle(cmsHibernateBundle);
  }

  @Override
  protected void configure() {
    // do nothing
  }

  @Provides
  @CmsSessionFactory
  SessionFactory cmsSessionFactory() {
    return cmsHibernateBundle.getSessionFactory();
  }

  @Provides
  @CmsHibernateBundle
  public HibernateBundle<CmApiConfiguration> getCmsHibernateBundle() {
    return cmsHibernateBundle;
  }
}
