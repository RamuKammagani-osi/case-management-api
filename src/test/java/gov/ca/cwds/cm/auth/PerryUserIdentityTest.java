package gov.ca.cwds.cm.auth;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

/**
 * @author CWDS TPT-3 Team
 */
public class PerryUserIdentityTest {

  @Test
  public void staffIdField_success() {
    final PerryUserIdentity testSubject = new PerryUserIdentity();
    assertThat(testSubject.getStaffId(), is(nullValue()));
    testSubject.setStaffId("welcome");
    assertThat(testSubject.getStaffId(), is(equalTo("welcome")));
  }

}