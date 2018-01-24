package gov.ca.cwds.cm.test.util;

import gov.ca.cwds.cm.CmApiConfiguration;
import gov.ca.cwds.cm.test.RestClientTestRule;

/**
 * @author CWDS TPT-3 Team
 */
public final class IntegrationTestContextHolder {

  private IntegrationTestContextHolder() {
  }

  public static CmApiConfiguration cmApiConfiguration = null;

  public static RestClientTestRule clientTestRule = null;
}
