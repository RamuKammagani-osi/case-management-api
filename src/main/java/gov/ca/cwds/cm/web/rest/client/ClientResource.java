package gov.ca.cwds.cm.web.rest.client;

import static gov.ca.cwds.cm.Constants.API.CLIENTS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cm.service.dictionaries.ClientType;
import gov.ca.cwds.cm.service.dto.ClientDTO;
import gov.ca.cwds.cm.service.facade.ClientFacade;
import gov.ca.cwds.cm.web.rest.ResponseUtil;
import gov.ca.cwds.cm.web.rest.parameter.ClientParameterObject;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** @author CWDS TPT-3 Team */
@Api(tags = CLIENTS, value = CLIENTS)
@Path(value = CLIENTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

  private ClientFacade clientFacade;

  @Inject
  public ClientResource(ClientFacade clientFacade) {
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
  @ApiOperation(value = "Find client by client ID", response = ClientDTO.class)
  @UnitOfWork
  @Timed
  public Response get(
      @PathParam("id")
          @ApiParam(required = true, value = "The unique client ID", example = "DSC1233117")
          final String id) {
    ClientParameterObject clientParameterObject = new ClientParameterObject();
    clientParameterObject.setClientId(id);
    return ResponseUtil.responseOrNotFound(
        clientFacade.find(clientParameterObject, ClientType.BASE_CLIENT));
  }
}
