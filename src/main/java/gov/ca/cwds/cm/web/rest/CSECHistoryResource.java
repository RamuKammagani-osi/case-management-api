package gov.ca.cwds.cm.web.rest;

import com.google.inject.Inject;
import gov.ca.cwds.cm.Constants.API;
import gov.ca.cwds.cm.service.CSECHistoryService;
import io.swagger.annotations.Api;

import static gov.ca.cwds.cm.Constants.API.CHILD_CLIENTS;
import static gov.ca.cwds.cm.Constants.API.ID;
import static gov.ca.cwds.cm.Constants.UnitOfWork.CMS;

import com.codahale.metrics.annotation.Timed;
import gov.ca.cwds.cm.service.dto.CSECHistoryDTO;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
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
public class CSECHistoryResource {

  private CSECHistoryService csecHistoryService;

  @Inject
  public CSECHistoryResource(CSECHistoryService csecHistoryService) {
    this.csecHistoryService = csecHistoryService;
  }

  @GET
  @Path("/{" + ID + "}/" + API.CSEC_HISTORY)
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @UnitOfWork(CMS)
  @Timed
  @ApiOperation(
    value = "Find CSEC information pertaining to a specific child client",
    response = CSECHistoryDTO.class
  )
  public Response getCSECByClientId(
      @PathParam(ID) @ApiParam(required = true, value = "Child Client ID", example = "BKk7CHj01Y")
          final String clientId) {

    List<CSECHistoryDTO> csecHistories = csecHistoryService.findByClientId(clientId);
    return ResponseUtil.responseOrNotFound(csecHistories);
  }
}
