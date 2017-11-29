package gov.ca.cwds.cm.service.mapper;

import gov.ca.cwds.cm.service.dto.SystemCodeDTO;
import gov.ca.cwds.cm.service.mapper.tool.LegacyBooleanToStringMapper;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SystemCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS TPT-3 Team
 */

@Mapper(uses = { LegacyBooleanToStringMapper.class })
@FunctionalInterface
public interface SystemCodeMapper {

  @Mapping(target = "messages", ignore = true)
  @Mapping(source = "fkMeta", target = "metaCode")
  SystemCodeDTO toDto(SystemCode address);

}
