package gov.ca.cwds.cm.service.mapper.tool;

/**
 * The mapper to map legacy db number columns to number fields in DTO and vice versa
 * using zero value in legacy entity and null value in DTO.
 *
 * @author CWDS TPT-3 Team
 */
public class LegacyZeroNumberToNullNumberMapper {

  private static final Short SHORT_ZERO = 0;
  private static final Long LONG_ZERO = 0L;

  /**
   * Mapper method to nullify zero values of Short instance. Returns null when zero value on input
   *
   * @param input - Short instance
   * @return - null when zero value on input or input in other cases
   */
  @ZeroNumberToNullNumberField
  public Short zeroToNull(final Short input) {
    return input == null || input == 0
        ? null
        : input;
  }

  /**
   * Mapper method to nullify zero values of Long instance. Returns null when zero value on input
   *
   * @param input - Long instance
   * @return - null when zero value on input or input in other cases
   */
  @ZeroNumberToNullNumberField
  public Long zeroToNull(final Long input) {
    return input == null || input == 0L
        ? null
        : input;
  }

  /**
   * Mapper method to make zero from null value of Short type.
   * @param input - Short instance
   * @return - zero when null input or input in other cases
   */
  @NullNumberToZeroNumberField
  public Short nullToZero(final Short input) {
    return input == null ? SHORT_ZERO : input;
  }

  /**
   * Mapper method to make zero from null value of Long type.
   * @param input - Long instance
   * @return - zero when null input or input in other cases
   */
  @NullNumberToZeroNumberField
  public Long nullToZero(final Long input) {
    return input == null ? LONG_ZERO : input;
  }

}
