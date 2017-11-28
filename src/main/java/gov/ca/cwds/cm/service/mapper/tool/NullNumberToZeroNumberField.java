package gov.ca.cwds.cm.service.mapper.tool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.Qualifier;

/**
 * Qualifies mapstruct mappings to use {@link LegacyZeroNumberToNullNumberMapper}
 *
 * @author CWDS TPT-3 Team
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface NullNumberToZeroNumberField {}
