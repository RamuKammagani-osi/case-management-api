package gov.ca.cwds.cm.service;

import com.google.common.collect.ImmutableList;
import gov.ca.cwds.cm.service.dto.ClientRelationshipDTO;
import gov.ca.cwds.cm.service.mapper.ClientRelationshipMapper;
import gov.ca.cwds.data.legacy.cms.dao.ClientRelationshipDao;
import gov.ca.cwds.data.legacy.cms.entity.ClientRelationship;
import java.time.LocalDate;
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

    LocalDate localDate = LocalDate.now();

    List<ClientRelationship> relationshipsByLeftSide = clientRelationshipDao
        .findRelationshipsByLeftSide(clientId, localDate);

    List<ClientRelationshipDTO> relationshipByLeftSideDtos =
        relationshipsByLeftSide.stream().map(clientRelationshipMapper::fromRelationshipByLeftSideToDto)
            .collect(Collectors.toList());

    List<ClientRelationship> relationshipsByRightSide = clientRelationshipDao
        .findRelationshipsByRightSide(clientId, localDate);

    List<ClientRelationshipDTO>  relationshipByRightSideDtos =
        relationshipsByRightSide.stream().map(clientRelationshipMapper::fromRelationshipByRightSideToDto)
            .collect(Collectors.toList());

    List<ClientRelationshipDTO> result = ListUtils.union(relationshipByLeftSideDtos,relationshipByRightSideDtos);

    return ImmutableList.copyOf(result);
  }
}
