package gov.ca.cwds.cm.service.mapper;

import gov.ca.cwds.cm.service.dto.CSECHistoryDTO;
import gov.ca.cwds.data.legacy.cms.entity.CSECHistory;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

/**
 * @author CWDS TPT-3 Team
 */
@Mapper(config = CSECHistoryMapper.class)
@MapperConfig(mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface CSECHistoryMapper {

  @Mapping(source = "startDate", target = "startDate")
  @Mapping(source = "endDate", target = "endDate")
  @Mapping(source = "childClient", target = "childClientId")
  @Mapping(source = "sexualExploitationType.systemId", target = "sexualExploitationType")
  CSECHistoryDTO to(CSECHistory csecHistory);

  CSECHistoryDTO[] to(List<CSECHistory> csecHistoryList);
}
