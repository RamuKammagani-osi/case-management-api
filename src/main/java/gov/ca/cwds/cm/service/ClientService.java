package gov.ca.cwds.cm.service;

import com.google.inject.Inject;
import gov.ca.cwds.cm.service.dto.ClientDTO;
import gov.ca.cwds.cm.service.mapper.ClientMapper;
import gov.ca.cwds.cm.web.rest.parameter.ClientParameterObject;
import gov.ca.cwds.data.legacy.cms.dao.ClientDao;
import gov.ca.cwds.data.legacy.cms.entity.Client;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import java.io.Serializable;

/**
 * @author CWDS TPT-3 Team
 */
public class ClientService implements CrudsService {

  private ClientDao clientDao;
  private ClientMapper clientMapper;

  @Inject
  public ClientService(ClientDao clientDao, ClientMapper clientMapper) {
    this.clientMapper = clientMapper;
    this.clientDao = clientDao;
  }


  @Override
  public ClientDTO find(Serializable serializable) {
    Client client = clientDao.find(((ClientParameterObject) serializable).getClientId());
    return clientMapper.toClientDTO(client);
  }

  @Override
  public Response delete(Serializable serializable) {
    return null;
  }

  @Override
  public Response create(Request request) {
    return null;
  }

  @Override
  public Response update(Serializable serializable, Request request) {
    return null;
  }
}
