package gov.ca.cwds.cm.service;

import gov.ca.cwds.data.legacy.cms.dao.SafetyAlertDao;
import gov.ca.cwds.data.legacy.cms.entity.SafetyAlert;
import java.util.Collection;
import javax.inject.Inject;

public class SafetyAlertService {

  private final SafetyAlertDao safetyAlertDao;

  @Inject
  public SafetyAlertService(SafetyAlertDao safetyAlertDao) {
    this.safetyAlertDao = safetyAlertDao;
  }

  public Collection<SafetyAlert> findSafetyAlertsByClientId(final String clientId) {
    return safetyAlertDao.findByClientId(clientId);
  }
}
