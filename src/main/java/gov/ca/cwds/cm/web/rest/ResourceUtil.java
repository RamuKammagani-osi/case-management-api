package gov.ca.cwds.cm.web.rest;

import gov.ca.cwds.cm.service.dto.CollectionDTO;
import javax.ws.rs.core.Response;

/**
 * Common Resource methods
 *
 * @author CWDS TPT-3 Team
 */
public final class ResourceUtil {

  private static final int HTTP_CODE_NOT_FOUND = Response.Status.NOT_FOUND.getStatusCode();
  private static final int HTTP_CODE_OK = Response.Status.OK.getStatusCode();

  private ResourceUtil() {
  }

  /**
   * Returns JAX-RS Response with body and 200 (OK) HTTP code or simply 404 (Not Found) HTTP code
   *
   * @param dto - payload of the response
   * @return Response with HTTP OK code and dto as a payload,
   * or response with HTTP Not Found code with no payload
   */
  public static Response responseOrNotFound(final gov.ca.cwds.rest.api.Response dto) {
    return Response
        .status(dto == null ? HTTP_CODE_NOT_FOUND : HTTP_CODE_OK)
        .entity(dto)
        .build();
  }

  /**
   * Returns JAX-RS Response with body and 200 (OK) HTTP code or simply 404 (Not Found) HTTP code
   *
   * @param collectionDTO - payload as collection of items
   * @return Response with HTTP OK code and dto as a payload,
   * or response with HTTP Not Found code with no payload
   */
  public static Response responseOrNotFound(final CollectionDTO collectionDTO) {
    final boolean isCollectionDtoEmpty = isCollectionDtoEmpty(collectionDTO);
    return Response
        .status(isCollectionDtoEmpty ? HTTP_CODE_NOT_FOUND : HTTP_CODE_OK)
        .entity(isCollectionDtoEmpty ? null : collectionDTO)
        .build();
  }

  private static boolean isCollectionDtoEmpty(final CollectionDTO<?> collectionDTO) {
    return collectionDTO == null
        || collectionDTO.getItems() == null
        || collectionDTO.getItems().isEmpty();
  }
}
