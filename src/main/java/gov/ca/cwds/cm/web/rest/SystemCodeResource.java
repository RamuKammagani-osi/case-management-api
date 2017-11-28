package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.Constants.API.SYSTEM_CODES;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cm.service.SystemCodeService;
import gov.ca.cwds.cm.service.dto.SystemCodeDTO;
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
@Api(value = SYSTEM_CODES, tags = SYSTEM_CODES)
@Path(value = SYSTEM_CODES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SystemCodeResource {

  private final SystemCodeService systemCodeService;

  @Inject
  public SystemCodeResource(final SystemCodeService systemCodeService) {
    this.systemCodeService = systemCodeService;
  }

  @UnitOfWork
  @GET
  @Path("/{metaCode}")
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not Found")
    }
  )
  @ApiOperation(value = "Find System Codes by meta code", response = SystemCodeDTO.class)
  @Timed
  public Response get(
      @PathParam("metaCode")
          @ApiParam(
            required = true,
            name = "metaCode",
            value = "The meta code to find System Codes by",
            example = "GVR_ENTC"
          )
          final String metaCode) {
    final Collection<SystemCodeDTO> serviceCodes = systemCodeService.findByMetaCode(metaCode);
    return ResponseUtil.responseOrNotFound(serviceCodes);
  }
}
