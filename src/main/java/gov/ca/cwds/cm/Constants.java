package gov.ca.cwds.cm;

/**
 * @author CWDS TPT-3 Team
 */
public final class Constants {

  public static final String Y = "Y";

  public static final String N = "N";

  private Constants() {
  }

  public static class API {

    public static final String SYSTEM_INFORMATION_PATH = "system-information";

    public static final String ID = "id";

    public static final String CHILD_CLIENTS = "child-clients";

    public static final String CLIENTS = "clients";

    public static final String ADDRESSES = "addresses";

    public static final String SYSTEM_CODES = "system-codes";

    public static final String CASES = "cases";

    public static final String STAFF = "staff";

    public static final String REFERRALS = "referrals";

    public static final String SAFETY_ALERTS = "safety-alerts";

    public static final String CSEC_HISTORY = "csec";

    public static final String RELATIONSHIPS = "relationships";

    private API() {
    }
  }

  public static class ExpectedExceptionMessages {

    private ExpectedExceptionMessages() {
    }

  }

  public static class Validation {

    private Validation() {
    }
  }

  public static class UnitOfWork {

    public static final String CMS = "cwscms";
    public static final String CWSRS = "cwsrs";

    public static final String XA_CMS = "xa_cwscms";

    private UnitOfWork() {
    }
  }

}
