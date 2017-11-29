package gov.ca.cwds.cm.service;

import com.google.inject.Inject;
import gov.ca.cwds.cm.service.dto.ChildClientDTO;
import gov.ca.cwds.cm.service.mapper.ChildClientMapper;
import gov.ca.cwds.cm.web.rest.parameter.ClientParameterObject;
import gov.ca.cwds.data.legacy.cms.dao.ChildClientDao;
import gov.ca.cwds.data.legacy.cms.entity.ChildClient;

import java.io.Serializable;

/** @author CWDS TPT-3 Team */
public class ChildClientService extends CrudServiceAdapter {

  private ChildClientDao childClientDao;
  private ChildClientMapper childClientMapper;

  @Inject
  public ChildClientService(ChildClientDao childClientDao, ChildClientMapper childClientMapper) {
    this.childClientDao = childClientDao;
    this.childClientMapper = childClientMapper;
  }

  @Override
  public ChildClientDTO find(Serializable serializable) {
    ChildClient childClient = childClientDao.find(((ClientParameterObject) serializable).getClientId());
    return childClientMapper.toChildClientDTO(childClient);
  }
}
