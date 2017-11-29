package gov.ca.cwds.cm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import gov.ca.cwds.ObjectMapperUtils;
import gov.ca.cwds.cm.service.dto.LandingDTO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.testing.FixtureHelpers;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static gov.ca.cwds.cm.Constants.API.STAFF;
import static gov.ca.cwds.cm.Constants.API.CASELOADS;
import static gov.ca.cwds.cm.Constants.API.PathParams.STAFF_ID;

/**
 * @author CWDS TPT-3 Team
 */

@Api(tags = STAFF, value = STAFF)
@Path(value = STAFF + "/{" + STAFF_ID + "}/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LandingMockResource {

  @GET
  @Path(CASELOADS)
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @ApiOperation(value = "Find caseloads with cases/referrals by staff person ID", response = LandingDTO[].class)
  @UnitOfWork
  @Timed
  public Response getCaseLoads(
      @PathParam(STAFF_ID)
          @ApiParam(required = true, value = "The unique staff person ID", example = "s99")
          String staffId)
      throws IOException {
    if (!"s99".equals(staffId)) {
      return Response.ok().status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(getMockedData()).build();
  }

  private List<LandingDTO> getMockedData() throws IOException {
    ObjectMapper objectMapper = ObjectMapperUtils.createObjectMapper();
    objectMapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
    String json = FixtureHelpers.fixture("fixtures/list_of_related_caseloads_by_staff_id.json");
    LandingDTO[] array = objectMapper.readValue(json, LandingDTO[].class);
    return Arrays.asList(array);
  }
}
