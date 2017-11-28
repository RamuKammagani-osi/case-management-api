package gov.ca.cwds.cm.service.mapper;

import gov.ca.cwds.cm.service.dto.AddressDTO;
import gov.ca.cwds.cm.service.mapper.tool.LegacyBooleanToStringMapper;
import gov.ca.cwds.cm.service.mapper.tool.LegacyZeroNumberToNullNumberMapper;
import gov.ca.cwds.cm.service.mapper.tool.LegacyZeroNumberToNullStringMapper;
import gov.ca.cwds.cm.service.mapper.tool.NullNumberToZeroNumberField;
import gov.ca.cwds.cm.service.mapper.tool.ZeroNumberToNullNumberField;
import gov.ca.cwds.cm.service.mapper.tool.ZeroNumberToNullStringField;
import gov.ca.cwds.data.legacy.cms.entity.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** @author CWDS TPT-3 Team */
@Mapper(
  uses = {
    LegacyBooleanToStringMapper.class,
    LegacyZeroNumberToNullStringMapper.class,
    LegacyZeroNumberToNullNumberMapper.class
  }
)
public interface AddressMapper {

  @Mapping(target = "messages", ignore = true)
  @Mapping(
    source = "emergencyPhone",
    target = "emergencyPhone",
    qualifiedBy = ZeroNumberToNullStringField.class
  )
  @Mapping(
    source = "emergencyPhoneExtension",
    target = "emergencyPhoneExtension",
    qualifiedBy = ZeroNumberToNullStringField.class
  )
  @Mapping(
    source = "messagePhone",
    target = "messagePhone",
    qualifiedBy = ZeroNumberToNullStringField.class
  )
  @Mapping(
    source = "messagePhoneExtension",
    target = "messagePhoneExtension",
    qualifiedBy = ZeroNumberToNullStringField.class
  )
  @Mapping(
    source = "primaryPhone",
    target = "primaryPhone",
    qualifiedBy = ZeroNumberToNullStringField.class
  )
  @Mapping(
    source = "primaryPhoneExtension",
    target = "primaryPhoneExtension",
    qualifiedBy = ZeroNumberToNullStringField.class
  )
  @Mapping(
    source = "streetSuffixCode",
    target = "streetSuffixCode",
    qualifiedBy = ZeroNumberToNullNumberField.class
  )
  @Mapping(
    source = "unitDesignatorCode",
    target = "unitDesignatorCode",
    qualifiedBy = ZeroNumberToNullNumberField.class
  )
  @Mapping(
    source = "zipSuffix",
    target = "zipSuffix",
    qualifiedBy = ZeroNumberToNullStringField.class
  )
  AddressDTO toDto(Address address);

  @InheritInverseConfiguration
  @Mapping(
    source = "streetSuffixCode",
    target = "streetSuffixCode",
    qualifiedBy = NullNumberToZeroNumberField.class
  )
  @Mapping(
    source = "unitDesignatorCode",
    target = "unitDesignatorCode",
    qualifiedBy = NullNumberToZeroNumberField.class
  )
  @Mapping(target = "lastUpdatedId", ignore = true)
  @Mapping(target = "lastUpdatedTime", ignore = true)
  Address toEntity(AddressDTO dto);
}
