package gov.ca.cwds.cm.service.mapper;

import gov.ca.cwds.cm.service.dto.ClientRelationshipDTO;
import gov.ca.cwds.data.legacy.cms.entity.ClientRelationship;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ClientMapper.class)
public interface ClientRelationshipMapper {

  @Mapping(source = "identifier", target = "id")
  @Mapping(source = "absentParentIndicator", target = "absentParentIndicator")
  @Mapping(source = "startDate", target = "startDate")
  @Mapping(source = "endDate", target = "endDate")
  @Mapping(source = "sameHomeStatus", target = "sameHomeStatus")
  ClientRelationshipDTO fromRelationshipToDtoBase(ClientRelationship clientRelationship);

  @InheritConfiguration(name = "fromRelationshipToDtoBase")
  @Mapping(source = "leftSide.identifier", target = "clientId")
  @Mapping(source = "rightSide.identifier", target = "relatedClientId")
  @Mapping(
      expression = "java(Short.valueOf(clientRelationship.getType().getLongDescription()))",
      target = "typeCode")
  @Mapping(source = "rightSide", target = "relatedClient")
  ClientRelationshipDTO fromRelationshipByLeftSideToDto(ClientRelationship clientRelationship);

  @InheritConfiguration(name = "fromRelationshipToDtoBase")
  @Mapping(source = "rightSide.identifier", target = "clientId")
  @Mapping(source = "leftSide.identifier", target = "relatedClientId")
  @Mapping(source = "type.systemId", target = "typeCode")
  @Mapping(source = "leftSide", target = "relatedClient")
  ClientRelationshipDTO fromRelationshipByRightSideToDto(ClientRelationship clientRelationship);
}
