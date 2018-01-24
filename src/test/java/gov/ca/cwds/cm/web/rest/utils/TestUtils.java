package gov.ca.cwds.cm.web.rest.utils;

/**
 * @author CWDS TPT-3 Team
 */
public class TestUtils {

  public static final String API_URL = "api.url";
  private static final String SLASH = "/";

  private TestUtils() {
  }

  public static String getApiUrl() {
    final String apiUrlRaw = System.getProperty(API_URL);
    return addTrailingSlashIfNeeded(apiUrlRaw);
  }

  private static String addTrailingSlashIfNeeded(String url) {
    return url == null || url.endsWith(SLASH) ? url : url + SLASH;
  }
}
