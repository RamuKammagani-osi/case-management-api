package gov.ca.cwds.cm.service.mapper.tool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.Qualifier;

/**
 * Qualifies mapstruct mappings to use {@link LegacyZeroNumberToNullStringMapper}
 *
 * @author CWDS TPT-3 Team
 */
@Qualifier
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ZeroNumberToNullStringField {}
