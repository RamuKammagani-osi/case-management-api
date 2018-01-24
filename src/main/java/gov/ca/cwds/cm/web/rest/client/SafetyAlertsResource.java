package gov.ca.cwds.cm.web.rest.client;

import static gov.ca.cwds.cm.Constants.API.CLIENTS;
import static gov.ca.cwds.cm.Constants.API.ID;
import static gov.ca.cwds.cm.Constants.UnitOfWork.CMS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cm.Constants;
import gov.ca.cwds.cm.service.SafetyAlertService;
import gov.ca.cwds.cm.service.dto.SafetyAlertDTO;
import gov.ca.cwds.cm.service.mapper.SafetyAlertMapper;
import gov.ca.cwds.cm.web.rest.ResponseUtil;
import gov.ca.cwds.data.legacy.cms.entity.SafetyAlert;
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
@Api(tags = CLIENTS, value = CLIENTS)
@Path(value = CLIENTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SafetyAlertsResource {

  private final SafetyAlertService safetyAlertService;
  private final SafetyAlertMapper safetyAlertMapper;

  @Inject
  public SafetyAlertsResource(SafetyAlertService safetyAlertService,
      SafetyAlertMapper safetyAlertMapper) {
    this.safetyAlertService = safetyAlertService;
    this.safetyAlertMapper = safetyAlertMapper;
  }

  @GET
  @Path("/{" + ID + "}/" + Constants.API.SAFETY_ALERTS)
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authenticated"),
      @ApiResponse(code = 403, message = "Unauthorized"),
      @ApiResponse(code = 404, message = "Not found")
    }
  )
  @ApiOperation(value = "Find safety alerts by client ID", response = SafetyAlertDTO.class)
  @UnitOfWork(CMS)
  @Timed
  public Response get(
      @PathParam("id")
      @ApiParam(required = true, value = "The unique client ID", example = "0YIPkZU0S0")
      final String id) {
    final Collection<SafetyAlert> safetyAlerts = safetyAlertService.findSafetyAlertsByClientId(id);
    final Collection<SafetyAlertDTO> results = safetyAlertMapper.toDto(safetyAlerts);
    return ResponseUtil.responseOrNotFound(results);
  }
}
