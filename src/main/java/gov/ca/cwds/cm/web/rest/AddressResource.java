package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.Constants.API.ADDRESSES;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cm.service.AddressService;
import gov.ca.cwds.cm.service.dto.AddressDTO;
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
@Api(value = ADDRESSES, tags = ADDRESSES)
@Path(value = ADDRESSES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

  private final AddressService addressService;

  @Inject
  public AddressResource(final AddressService addressService) {
    this.addressService = addressService;
  }

  @UnitOfWork
  @GET
  @Path("/{id}")
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found")
    }
  )
  @ApiOperation(value = "Find Address by id", response = AddressDTO.class)
  @Timed
  public Response get(
      @PathParam("id")
          @ApiParam(
            required = true,
            name = "id",
            value = "The id of the Address to find",
            example = "AaQshqm0Mb"
          )
          final String id) {
    final gov.ca.cwds.rest.api.Response response = addressService.find(id);
    return ResponseUtil.responseOrNotFound(response);
  }
}
