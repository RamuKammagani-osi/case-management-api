package gov.ca.cwds.cm.web.rest;

import java.util.Collection;
import javax.ws.rs.core.Response;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Common Response methods
 *
 * @author CWDS TPT-3 Team
 */
public final class ResponseUtil {

  private static final int HTTP_CODE_NOT_FOUND = Response.Status.NOT_FOUND.getStatusCode();
  private static final int HTTP_CODE_OK = Response.Status.OK.getStatusCode();

  private ResponseUtil() {}

  /**
   * Returns JAX-RS Response with body and 200 (OK) HTTP code or simply 404 (Not Found) HTTP code
   *
   * @param dto - payload of the response
   * @return Response with HTTP OK code and dto as a payload, or response with HTTP Not Found code
   *     with no payload
   */
  public static Response responseOrNotFound(final gov.ca.cwds.rest.api.Response dto) {
    return Response.status(dto == null ? HTTP_CODE_NOT_FOUND : HTTP_CODE_OK).entity(dto).build();
  }

  /**
   * Returns JAX-RS Response with body and 200 (OK) HTTP code or simply 404 (Not Found) HTTP code
   *
   * @param collection - payload of the response
   * @return Response with HTTP OK code and dto as a payload, or response with HTTP Not Found code
   *     with no payload
   */
  public static Response responseOrNotFound(
      final Collection<? extends gov.ca.cwds.rest.api.Response> collection) {
    final boolean isCollectionEmpty = CollectionUtils.isEmpty(collection);
    return Response.status(isCollectionEmpty ? HTTP_CODE_NOT_FOUND : HTTP_CODE_OK)
        .entity(isCollectionEmpty ? null : collection)
        .build();
  }

  public static Response responseOk(
      final Collection<? extends gov.ca.cwds.rest.api.Response> collection) {
    return Response.status(HTTP_CODE_OK).entity(collection).build();
  }

  public static Response responseOrNotFound(final gov.ca.cwds.rest.api.Response[] response) {
    boolean isCollectionEmpty = response == null || response.length == 0;
    return Response.status(isCollectionEmpty ? HTTP_CODE_NOT_FOUND : HTTP_CODE_OK)
        .entity(isCollectionEmpty ? null : response)
        .build();
  }
}
