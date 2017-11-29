package gov.ca.cwds.cm.service.facade;

import com.google.inject.Inject;
import gov.ca.cwds.cm.service.ChildClientService;
import gov.ca.cwds.cm.service.ClientService;
import gov.ca.cwds.cm.service.dictionaries.ClientType;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;

/** @author CWDS TPT-3 Team */
public class ClientFacade {

  private ChildClientService childClientService;
  private ClientService clientService;

  @Inject
  public ClientFacade(ChildClientService childClientService, ClientService clientService) {
    this.childClientService = childClientService;
    this.clientService = clientService;
  }

  public Response find(Serializable serializable, ClientType clientType) {
    if (clientType == null) {
      return null;
    }

    switch (clientType) {
      case BASE_CLIENT:
        return clientService.find(serializable);
      case CHILD_CLIENT:
        return childClientService.find(serializable);
      default:
        return null;
    }
  }
}
