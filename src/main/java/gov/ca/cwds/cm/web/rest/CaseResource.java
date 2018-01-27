package gov.ca.cwds.cm.web.rest;

import static gov.ca.cwds.cm.Constants.API.CASES;
import static gov.ca.cwds.cm.Constants.API.ID;
import static gov.ca.cwds.cm.Constants.UnitOfWork.CMS;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import gov.ca.cwds.cm.service.CaseService;
import gov.ca.cwds.cm.service.dto.CaseDTO;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.validator.constraints.NotEmpty;

/** @author CWDS TPT-3 Team */
@Api(tags = CASES, value = CASES)
@Path(value = CASES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CaseResource {

  private final CaseService caseService;

  @Inject
  public CaseResource(CaseService caseService) {
    this.caseService = caseService;
  }

  @GET
  @Path("/{" + ID + "}")
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @UnitOfWork(CMS)
  @Timed
  @ApiOperation(value = "Find Case by case ID", response = CaseDTO.class)
  public Response get(
      @PathParam(ID)
          @ApiParam(required = true, value = "The unique case ID", example = "AadfKnG07n")
          final String id) {
    CaseDTO caseDTO = caseService.find(id);
    return ResponseUtil.responseOrNotFound(caseDTO);
  }

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  @ApiResponses(
    value = {
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 406, message = "Accept Header not supported"),
      @ApiResponse(code = 422, message = "Unprocessable Entity")
    }
  )
  @ApiOperation(value = "Update Case", response = CaseDTO.class)
  public Response update(
      @PathParam("id")
          @ApiParam(required = true, value = "The unique case ID", example = "AadfKnG07n")
          @NotEmpty
          final String id,
      @ApiParam(name = "caseData", value = "The Case data") @NotNull @Valid CaseDTO caseDTO) {

    caseDTO.setId(id);
    return ResponseUtil.responseOrNotFound(caseDTO);
  }
}
