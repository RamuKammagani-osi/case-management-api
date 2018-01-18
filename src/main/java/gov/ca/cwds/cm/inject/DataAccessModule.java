package gov.ca.cwds.cm.inject;

import static gov.ca.cwds.cm.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cm.Constants.UnitOfWork.CWSRS;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cm.CmApiConfiguration;
import gov.ca.cwds.data.legacy.cms.entity.Address;
import gov.ca.cwds.data.legacy.cms.entity.BackgroundCheck;
import gov.ca.cwds.data.legacy.cms.entity.Case;
import gov.ca.cwds.data.legacy.cms.entity.CaseAssignment;
import gov.ca.cwds.data.legacy.cms.entity.CaseLoad;
import gov.ca.cwds.data.legacy.cms.entity.CaseLoadWeighting;
import gov.ca.cwds.data.legacy.cms.entity.ChildClient;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.data.legacy.cms.entity.ClientAddress;
import gov.ca.cwds.data.legacy.cms.entity.ClientScpEthnicity;
import gov.ca.cwds.data.legacy.cms.entity.CountyLicenseCase;
import gov.ca.cwds.data.legacy.cms.entity.DeliveredService;
import gov.ca.cwds.data.legacy.cms.entity.FCEligibility;
import gov.ca.cwds.data.legacy.cms.entity.HealthInterventionPlan;
import gov.ca.cwds.data.legacy.cms.entity.LicensingVisit;
import gov.ca.cwds.data.legacy.cms.entity.LongText;
import gov.ca.cwds.data.legacy.cms.entity.MedicalEligibilityApplication;
import gov.ca.cwds.data.legacy.cms.entity.OtherAdultsInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherChildrenInPlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.OtherPeopleScpRelationship;
import gov.ca.cwds.data.legacy.cms.entity.OutOfHomePlacement;
import gov.ca.cwds.data.legacy.cms.entity.OutOfStateCheck;
import gov.ca.cwds.data.legacy.cms.entity.PlacementEpisode;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHome;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeNotes;
import gov.ca.cwds.data.legacy.cms.entity.PlacementHomeProfile;
import gov.ca.cwds.data.legacy.cms.entity.Referral;
import gov.ca.cwds.data.legacy.cms.entity.ReferralAssignment;
import gov.ca.cwds.data.legacy.cms.entity.ReferralClient;
import gov.ca.cwds.data.legacy.cms.entity.StaffPerson;
import gov.ca.cwds.data.legacy.cms.entity.StaffPersonCaseLoad;
import gov.ca.cwds.data.legacy.cms.entity.SubstituteCareProvider;
import gov.ca.cwds.data.legacy.cms.entity.enums.AssignmentRecipient;
import gov.ca.cwds.data.legacy.cms.entity.enums.AssignmentType;
import gov.ca.cwds.data.legacy.cms.entity.enums.IndividualType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ActiveServiceComponentType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ApprovalStatusType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.CaseClosureReasonType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Country;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.County;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.DeathCircumstancesType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Ethnicity;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.FcEligibilityDentalReason;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.FcEligibilityTermReason;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.HealthPlanTerminationType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ImmigrationStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Language;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.MaritalStatus;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.NameType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.Religion;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SecondaryAssignmentRoleType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.ServiceContactType;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.State;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SystemCode;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.VisitType;
import gov.ca.cwds.inject.CmsHibernateBundle;
import gov.ca.cwds.inject.CwsRsHibernateBundle;
import gov.ca.cwds.inject.CwsRsSessionFactory;
import gov.ca.cwds.inject.CmsSessionFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
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
              AssignmentRecipient.class,
              AssignmentType.class,
              CaseAssignment.class,
              BackgroundCheck.class,
              Case.class,
              CaseClosureReasonType.class,
              CaseLoad.class,
              CaseLoadWeighting.class,
              ChildClient.class,
              Client.class,
              ClientAddress.class,
              Country.class,
              County.class,
              CountyLicenseCase.class,
              DeathCircumstancesType.class,
              LicensingVisit.class,
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
              SecondaryAssignmentRoleType.class,
              StaffPerson.class,
              StaffPersonCaseLoad.class,
              State.class,
              SubstituteCareProvider.class,
              SystemCode.class,
              VisitType.class,
              CaseAssignment.class,
              ReferralAssignment.class,
              CaseLoad.class,
              SecondaryAssignmentRoleType.class,
              Referral.class,
              ReferralClient.class,
              Case.class,
              StaffPersonCaseLoad.class,
              CaseLoadWeighting.class,
              ImmigrationStatus.class,
              MaritalStatus.class,
              NameType.class,
              Ethnicity.class,
              Language.class,
              Religion.class,
              ClientScpEthnicity.class,
              HealthInterventionPlan.class,
              HealthPlanTerminationType.class,
              FCEligibility.class,
              FcEligibilityDentalReason.class,
              FcEligibilityTermReason.class,
              MedicalEligibilityApplication.class,
              ServiceContactType.class,
              DeliveredService.class,
              IndividualType.class
          )
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

  private final HibernateBundle<CmApiConfiguration> cwsRsHibernateBundle
      = new HibernateBundle<CmApiConfiguration>(ImmutableList.of(), new SessionFactoryFactory()) {
    @Override
    public PooledDataSourceFactory getDataSourceFactory(CmApiConfiguration configuration) {
      return configuration.getCwsRsDataSourceFactory();
    }

    @Override
    public String name() {
      return CWSRS;
    }
  };

  public DataAccessModule(Bootstrap<? extends CmApiConfiguration> bootstrap) {
    bootstrap.addBundle(cmsHibernateBundle);
    bootstrap.addBundle(cwsRsHibernateBundle);
  }

  @Override
  protected void configure() {
    //do nothing
  }

  @Provides
  UnitOfWorkAwareProxyFactory provideUnitOfWorkAwareProxyFactory() {
    return new UnitOfWorkAwareProxyFactory(
        getCmsHibernateBundle(),
        getCwsRsHibernateBundle()
    );
  }

  @Provides
  @CmsSessionFactory
  SessionFactory cmsSessionFactory() {
    return cmsHibernateBundle.getSessionFactory();
  }

  @Provides
  @CwsRsSessionFactory
  SessionFactory cwsRsSessionFactory() {
    return cwsRsHibernateBundle.getSessionFactory();
  }

  @Provides
  @CmsHibernateBundle
  public HibernateBundle<CmApiConfiguration> getCmsHibernateBundle() {
    return cmsHibernateBundle;
  }

  @Provides
  @CwsRsHibernateBundle
  public HibernateBundle<CmApiConfiguration> getCwsRsHibernateBundle() {
    return cwsRsHibernateBundle;
  }

}
