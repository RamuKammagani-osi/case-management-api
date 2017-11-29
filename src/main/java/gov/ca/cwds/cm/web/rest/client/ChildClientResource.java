package gov.ca.cwds.cm.web.rest.client;

import static gov.ca.cwds.cm.Constants.API.CHILD_CLIENTS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cm.Constants.API;
import gov.ca.cwds.cm.service.dictionaries.ClientType;
import gov.ca.cwds.cm.service.ClientAddressService;
import gov.ca.cwds.cm.service.dto.ChildClientDTO;
import gov.ca.cwds.cm.service.facade.ClientFacade;
import gov.ca.cwds.cm.service.dto.ClientAddressDTO;
import gov.ca.cwds.cm.web.rest.ResponseUtil;
import gov.ca.cwds.cm.web.rest.parameter.ClientParameterObject;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** @author CWDS TPT-3 Team */
@Api(tags = CHILD_CLIENTS, value = CHILD_CLIENTS)
@Path(value = CHILD_CLIENTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChildClientResource {

  private ClientFacade clientFacade;
  private ClientAddressService clientAddressService;

  @Inject
  public ChildClientResource(ClientFacade clientFacade, ClientAddressService clientAddressService) {
    this.clientAddressService = clientAddressService;
    this.clientFacade = clientFacade;
  }

  @GET
  @Path("/{id}")
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Find childClient by client ID", response = ChildClientDTO.class)
  @UnitOfWork
  @Timed
  public Response get(
      @PathParam("id")
          @ApiParam(required = true, value = "The unique client ID", example = "DSC1233117")
          final String id) {
    ClientParameterObject clientParameterObject = new ClientParameterObject();
    clientParameterObject.setClientId(id);
    return ResponseUtil.responseOrNotFound(
        clientFacade.find(clientParameterObject, ClientType.CHILD_CLIENT));
  }

  @GET
  @Path("/{id}/" + API.ADDRESSES)
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found")
    }
  )
  @ApiOperation(
    value = "ClientAddresses of ChildClient by client Id",
    response = ClientAddressDTO.class
  )
  @UnitOfWork
  @Timed
  public Response getAddressesByClientId(
      @PathParam("id")
          @ApiParam(required = true, value = "The unique client ID", example = "GmNMeSx0Hy")
          final String id) {
    final Collection<ClientAddressDTO> addresses = clientAddressService.findByClientId(id);
    return ResponseUtil.responseOrNotFound(addresses);
  }
}
