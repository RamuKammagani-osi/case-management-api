package gov.ca.cwds.cm.util;

/**
 * @author CWDS TPT-3 Team
 */
public class NullOrEmptyException extends NullPointerException {

  private static final long serialVersionUID = 6453078047667415734L;

  public NullOrEmptyException() {
  }

  public NullOrEmptyException(String message) {
    super(message);
  }
}
