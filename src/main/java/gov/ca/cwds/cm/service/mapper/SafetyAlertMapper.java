package gov.ca.cwds.cm.service.mapper;

import gov.ca.cwds.cm.service.dto.SafetyAlertDTO;
import gov.ca.cwds.cm.service.mapper.tool.LegacyZeroNumberToNullNumberMapper;
import gov.ca.cwds.cm.service.mapper.tool.ZeroNumberToNullNumberField;
import gov.ca.cwds.data.legacy.cms.entity.SafetyAlert;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS TPT-3 Team
 */

@Mapper(uses = {
    LegacyZeroNumberToNullNumberMapper.class
})
public interface SafetyAlertMapper {

  @Mapping(source = "fkClientId", target = "clientId")
  @Mapping(
      source = "activationGovernmentEntityType.systemId",
      target = "activationGovernmentEntityCode",
      qualifiedBy = ZeroNumberToNullNumberField.class
  )
  @Mapping(source = "activationExplanationText.identifier", target = "activationExplanationTextId")
  @Mapping(
      source = "activationReasonType.systemId",
      target = "activationReasonCode",
      qualifiedBy = ZeroNumberToNullNumberField.class
  )
  @Mapping(
      source = "deactivationGovernmentEntityType.systemId",
      target = "deactivationGovernmentEntityCode",
      qualifiedBy = ZeroNumberToNullNumberField.class
  )
  @Mapping(source = "deactivationExplanationText.identifier", target = "deactivationExplanationTextId")
  SafetyAlertDTO toDto(SafetyAlert entity);

  Collection<SafetyAlertDTO> toDto(Collection<SafetyAlert> entities);

}
