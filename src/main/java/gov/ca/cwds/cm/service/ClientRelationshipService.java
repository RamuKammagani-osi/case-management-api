package gov.ca.cwds.cm.service;

import com.google.common.collect.ImmutableList;
import gov.ca.cwds.cm.service.dto.ClientRelationshipDTO;
import gov.ca.cwds.cm.service.mapper.ClientRelationshipMapper;
import gov.ca.cwds.data.legacy.cms.dao.ClientRelationshipDao;
import gov.ca.cwds.data.legacy.cms.entity.ClientRelationship;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.apache.commons.collections4.ListUtils;

/**
 * @author CWDS TPT-3 Team
 */
public class ClientRelationshipService extends CrudServiceAdapter {

  private final ClientRelationshipDao clientRelationshipDao;
  private final ClientRelationshipMapper clientRelationshipMapper;

  @Inject
  public ClientRelationshipService(
      final ClientRelationshipDao clientRelationshipDao,
      final ClientRelationshipMapper clientRelationshipMapper) {
    this.clientRelationshipDao = clientRelationshipDao;
    this.clientRelationshipMapper = clientRelationshipMapper;
  }

  public List<ClientRelationshipDTO> findByClientId(final String clientId) {

    List<ClientRelationship> rightRelationships = clientRelationshipDao
        .findCurrentRightRelationships(clientId);

    List<ClientRelationshipDTO> rightRelationshipDtos =
        rightRelationships.stream().map(clientRelationshipMapper::fromRightRelationshipToDto)
            .collect(Collectors.toList());

    List<ClientRelationship> leftRelationships = clientRelationshipDao
        .findCurrentLeftRelationships(clientId);

    List<ClientRelationshipDTO>  leftRelationshipDtos =
        leftRelationships.stream().map(clientRelationshipMapper::fromLeftRelationshipToDto)
            .collect(Collectors.toList());

    List<ClientRelationshipDTO> result = ListUtils.union(rightRelationshipDtos,leftRelationshipDtos);

    return ImmutableList.copyOf(result);
  }
}
