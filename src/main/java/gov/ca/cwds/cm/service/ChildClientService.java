package gov.ca.cwds.cm.service;

import com.google.inject.Inject;
import com.sun.org.apache.regexp.internal.RE;
import gov.ca.cwds.cm.service.mapper.ChildClientMapper;
import gov.ca.cwds.cm.web.rest.parameter.ChildClientParameterObject;
import gov.ca.cwds.data.legacy.cms.dao.ChildClientDao;
import gov.ca.cwds.data.legacy.cms.entity.ChildClient;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/** @author CWDS TPT-3 Team */
public class ChildClientService implements CrudsService {

  private ChildClientDao childClientDao;
  private ChildClientMapper childClientMapper;

  @Inject
  public ChildClientService(ChildClientDao childClientDao, ChildClientMapper childClientMapper) {
    this.childClientDao = childClientDao;
    this.childClientMapper = childClientMapper;
  }

  @Override
  public Response find(Serializable serializable) {
    ChildClient childClient = childClientDao.find(((ChildClientParameterObject) serializable).getChildClientId());
    return childClientMapper.toChildClientDTO(childClient);
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
