package gov.ca.cwds.cm.service;

import com.google.inject.Inject;
import gov.ca.cwds.data.legacy.cms.dao.AddressDao;
import gov.ca.cwds.data.legacy.cms.entity.Address;
import gov.ca.cwds.cm.service.mapper.AddressMapper;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;

/** @author CWDS TPT-3 Team */
public class AddressService extends CrudServiceAdapter {

  private final AddressDao addressDao;
  private final AddressMapper addressMapper;

  @Inject
  public AddressService(final AddressDao addressDao, final AddressMapper addressMapper) {
    this.addressDao = addressDao;
    this.addressMapper = addressMapper;
  }

  @Override
  public Response find(final Serializable serializable) {
    final Address address = addressDao.find(serializable);
    return addressMapper.toDto(address);
  }
}
