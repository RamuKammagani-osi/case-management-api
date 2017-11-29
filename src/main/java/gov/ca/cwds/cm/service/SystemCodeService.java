package gov.ca.cwds.cm.service;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cm.service.dto.SystemCodeDTO;
import gov.ca.cwds.cm.service.mapper.SystemCodeMapper;
import gov.ca.cwds.data.legacy.cms.dao.SystemCodeDao;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SystemCode;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CWDS TPT-3 Team
 */
public class SystemCodeService extends CrudServiceAdapter {

  private final SystemCodeDao systemCodeDao;
  private final SystemCodeMapper systemCodeMapper;

  @Inject
  public SystemCodeService(final SystemCodeDao systemCodeDao, final SystemCodeMapper systemCodeMapper) {
    this.systemCodeDao = systemCodeDao;
    this.systemCodeMapper = systemCodeMapper;
  }

  public Collection<SystemCodeDTO> findByMetaCode(final String metaCode) {
    final Collection<SystemCode> systemCodes = systemCodeDao.findByMetaCode(metaCode);

    final List<SystemCodeDTO> dtos = systemCodes.stream()
        .map(systemCodeMapper::toDto)
        .collect(Collectors.toList());

    return ImmutableList.<SystemCodeDTO>builder().addAll(dtos).build();
  }
}
