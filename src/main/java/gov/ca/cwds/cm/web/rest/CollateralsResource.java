package gov.ca.cwds.cm.web.rest;
import java.time.LocalDate;

import static gov.ca.cwds.cm.Constants.API.CASES;
import static gov.ca.cwds.cm.Constants.API.ID;
import static gov.ca.cwds.cm.Constants.UnitOfWork.CMS;

import com.codahale.metrics.annotation.Timed;
import gov.ca.cwds.cm.service.dto.CollateralDTO;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/** @author CWDS TPT-3 Team */
@Api(tags = CASES, value = CASES)
@Path(value = CASES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollateralsResource {

  @GET
  @Path("/{" + ID + "}/collaterals")
  @ApiResponses(
    value = {
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 406, message = "Accept Header not supported")
    }
  )
  @UnitOfWork(CMS)
  @Timed
  @ApiOperation(value = "Find Collaterals by case ID", response = CollateralDTO.class)
  public Response getCollaterals(
      @PathParam(ID)
          @ApiParam(required = true, value = "The unique case ID", example = "AadfKnG07n")
          final String id) {
    if ("-111111111".equals(id)) {
      return Response.status(Status.NOT_FOUND).build();
    }
    return ResponseUtil.responseOrNotFound(getMockedData());
  }

  private List<CollateralDTO> getMockedData() {
    String name = "Sandy";
    LocalDate date = LocalDate.of(2001, 02, 21);

    CollateralDTO collateral1 = new CollateralDTO();
    collateral1.setBadgeNumber("654654");
    collateral1.setBirthDate(date);
    collateral1.setCityName("Winnepeg");
    collateral1.setCommentDescription("Description");
    collateral1.setEmail("test@mail.ru");
    collateral1.setEmployerName("County Mental Health");
    collateral1.setEstablishedForCode("S");
    collateral1.setEstablishedForId("ffr1033der4");
    collateral1.setFaxNumber("4654564654");
    collateral1.setFirstName(name);
    collateral1.setForeignAddressIndicator(Boolean.TRUE);
    collateral1.setGenderCode("M");
    collateral1.setId("0YIPkZU0S0");
    collateral1.setLastName(name);
    collateral1.setMaterialStatusType("1122");
    collateral1.setMiddleInitialName("C");
    collateral1.setNamePrefixDescription("Mr.");
    collateral1.setPrimaryPhoneExtensionNumber("6666666");
    collateral1.setPrimaryPhoneNumber("9161111111");
    collateral1.setResidedOutOfStateIndicator(Boolean.TRUE);
    collateral1.setStateCodeType("1828");
    collateral1.setStreetName("Double Dice Lane");
    collateral1.setStreetNumber("5602");
    collateral1.setSuffixTitleDescription("LCSW");
    collateral1.setZipNumber("31040");
    collateral1.setZipSuffixNumber("2233");

    CollateralDTO collateral2 = new CollateralDTO();
    collateral2.setBadgeNumber("654655");
    collateral2.setBirthDate(date);
    collateral2.setCityName("Winnepeg");
    collateral2.setCommentDescription("Description");
    collateral2.setEmail("test@mail.ru");
    collateral2.setEmployerName("County Mental Health");
    collateral2.setEstablishedForCode("S");
    collateral2.setEstablishedForId("ffr1033der4");
    collateral2.setFaxNumber("4654564654");
    collateral2.setFirstName(name);
    collateral2.setForeignAddressIndicator(Boolean.TRUE);
    collateral2.setGenderCode("M");
    collateral2.setId("0YIPkZU0S0");
    collateral2.setLastName(name);
    collateral2.setMaterialStatusType("1122");
    collateral2.setMiddleInitialName("C");
    collateral2.setNamePrefixDescription("Mr.");
    collateral2.setPrimaryPhoneExtensionNumber("6666666");
    collateral2.setPrimaryPhoneNumber("9161111111");
    collateral2.setResidedOutOfStateIndicator(Boolean.TRUE);
    collateral2.setStateCodeType("1828");
    collateral2.setStreetName("Double Dice Lane");
    collateral2.setStreetNumber("5602");
    collateral2.setSuffixTitleDescription("LCSW");
    collateral2.setZipNumber("31040");
    collateral2.setZipSuffixNumber("2233");

    return Arrays.asList(collateral1, collateral2);
  }
}
