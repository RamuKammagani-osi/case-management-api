package gov.ca.cwds.cm;

/** @author CWDS TPT-3 Team */
public final class Constants {

  public static final String Y = "Y";

  public static final String N = "N";

  public static final String SPACE = " ";

  public static final String SQL_TYPE = "sqlType";

  public static final String RETURNED_CLASS_NAME_PARAM = "returnedClassName";

  private Constants() {}

  public static class API {

    public static final String SYSTEM_INFORMATION_PATH = "system-information";

    public static final String CM_PATH = "cm";

    public static final String CM_TAG = "cm";

    public static final String CHILD_CLIENT = "child-clients";

    public static final String ADDRESSES = "addresses";

    public static final String SYSTEM_CODES = "system-codes";

    public static final String CASE = "case";

    public static final String STAFF = "staff";

    private API() {}

    public static class PathParams {

      public static final String CM_NAME_PARAMETER = "caseName";
      public static final String CM_ID_PARAMETER = "caseId";

      private PathParams() {}
    }
  }

  public static class ExpectedExceptionMessages {

    private ExpectedExceptionMessages() {}
  }

  public static class Validation {

    private Validation() {}
  }

  public static class UnitOfWork {

    public static final String CMS = "cwscms";

    public static final String XA_CMS = "xa_cwscms";

    private UnitOfWork() {}
  }
}
