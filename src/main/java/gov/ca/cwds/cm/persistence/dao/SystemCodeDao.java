package gov.ca.cwds.cm.persistence.dao;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cm.util.Require;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.persistence.cms.SystemCode;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.Collection;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 * @author CWDS TPT-3 Team
 */
public class SystemCodeDao extends BaseDaoImpl<SystemCode> {

  private static final String SYSTEM_CODE_CACHE_REGION = "query.SystemCode";

  @Inject
  public SystemCodeDao(@CmsSessionFactory final SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public Collection<SystemCode> findByMetaCode(final String metaCode) {
    Require.requireNotNullAndNotEmpty(metaCode);

    final List<SystemCode> systemCodes = this.getSessionFactory().getCurrentSession()
        .createNamedQuery(SystemCode.NQ_FIND_BY_META, SystemCode.class)
        .setParameter(SystemCode.NQ_PARAM_META, metaCode)
        .setCacheable(true)
        .setCacheRegion(SYSTEM_CODE_CACHE_REGION)
        .list();

    return ImmutableList.<SystemCode>builder()
        .addAll(systemCodes)
        .build();
  }

}
