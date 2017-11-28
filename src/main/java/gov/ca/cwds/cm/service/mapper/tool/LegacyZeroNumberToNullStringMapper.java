package gov.ca.cwds.cm.service.mapper.tool;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.math.BigDecimal;

/**
 * The mapper to map legacy db number columns to String fields in DTO and vice versa.
 *
 * @author CWDS TPT-3 Team
 */
@ZeroNumberToNullStringField
public class LegacyZeroNumberToNullStringMapper {

  public BigDecimal toBigDecimal(final String phone) {
    return isEmpty(phone) ? BigDecimal.ZERO : new BigDecimal(phone);
  }

  public Long toLong(final String phone) {
    return isEmpty(phone) ? 0L : Long.parseLong(phone);
  }

  public Integer toInteger(final String phone) {
    return isEmpty(phone) ? 0 : Integer.parseInt(phone);
  }

  public Short toShort(final String phone) {
    return isEmpty(phone) ? 0 : Short.parseShort(phone);
  }

  public String toStringFromDecimal(final BigDecimal number) {
    return (number == null || BigDecimal.ZERO.compareTo(number) == 0) ? null : number.toString();
  }

  public String toStringFromLong(final Long number) {
    return (number == null || number == 0L) ? null : number.toString();
  }

  public String toStringFromInteger(final Integer number) {
    return (number == null || number == 0) ? null : number.toString();
  }

  public String toStringFromShort(final Short number) {
    return (number == null || number == 0) ? null : number.toString();
  }
}
