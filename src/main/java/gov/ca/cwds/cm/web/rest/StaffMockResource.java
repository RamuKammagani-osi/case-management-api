package gov.ca.cwds.cm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import gov.ca.cwds.ObjectMapperUtils;
import gov.ca.cwds.cm.service.dto.ClientDTO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.testing.FixtureHelpers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import static gov.ca.cwds.cm.Constants.API.STAFF;

/**
 * @author CWDS TPT-3 Team
 */
@Api(tags = STAFF, value = STAFF)
@Path(value = STAFF)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffMockResource {

  @GET
  @Path("/{id}/clients")
  @ApiResponses(
      value = {
          @ApiResponse(code = 401, message = "Not Authorized"),
          @ApiResponse(code = 404, message = "Not found"),
          @ApiResponse(code = 406, message = "Accept Header not supported")
      }
  )
  @ApiOperation(
      value = "Find clients by caseworker ID from cases",
      response = ClientDTO[].class
  )
  @UnitOfWork
  @Timed
  public Response getClients(
          @PathParam("id")
          @ApiParam(
              required = true,
              value = "The unique caseworker(staff person) ID",
              example = "q48"
          )
          String caseworkerId)
      throws IOException {

    if (!"q48".equals(caseworkerId)) {
      return Response.ok().status(Response.Status.NOT_FOUND).build();
    }

    return Response.ok().entity(getMockedData()).build();
  }

  private List<ClientDTO> getMockedData() throws IOException {
    ObjectMapper objectMapper = ObjectMapperUtils.createObjectMapper();
    objectMapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
    String json = FixtureHelpers.fixture("fixtures/list_of_related_clients_by_staff_id.json");
    ClientDTO[] array = objectMapper.readValue(json, ClientDTO[].class);
    return Arrays.asList(array);
  }
}
