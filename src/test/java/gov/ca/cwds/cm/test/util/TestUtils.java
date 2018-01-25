package gov.ca.cwds.cm.test.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author CWDS TPT-3 Team
 */
public class TestUtils {

  public static final String API_URL = "api.url";
  public static final String SLASH = "/";
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private TestUtils() {
  }

  public static String getApiUrl() {
    final String apiUrlRaw = System.getProperty(API_URL);
    return addTrailingSlashIfNeeded(apiUrlRaw);
  }

  private static String addTrailingSlashIfNeeded(String url) {
    return url == null || url.endsWith(SLASH) ? url : url + SLASH;
  }

  public static LocalDate localDate(String dateStr) {
    return LocalDate.parse(dateStr, DATE_FORMATTER);
  }
}
