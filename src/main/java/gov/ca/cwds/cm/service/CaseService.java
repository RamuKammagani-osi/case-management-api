package gov.ca.cwds.cm.service;

import com.google.inject.Inject;
import gov.ca.cwds.cm.service.dto.CaseDTO;
import gov.ca.cwds.cm.service.mapper.CaseMapper;
import gov.ca.cwds.data.legacy.cms.dao.CaseDao;
import gov.ca.cwds.data.legacy.cms.entity.Case;

import java.io.Serializable;

public class CaseService extends TypedCrudServiceAdapter {

  @Inject private CaseDao caseDao;

  @Inject private CaseMapper caseMapper;

  @Override
  public CaseDTO find(Serializable param) {
    Case caseEntity = caseDao.find(param);
    return caseMapper.toCaseDTO(caseEntity);
  }
}
