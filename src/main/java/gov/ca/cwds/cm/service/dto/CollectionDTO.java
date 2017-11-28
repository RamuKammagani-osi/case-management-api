package gov.ca.cwds.cm.service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.rest.api.Response;
import java.util.Collection;

/**
 * @author CWDS TPT-3 Team
 */

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@SuppressWarnings({"squid:S3437"})
public class CollectionDTO<T extends BaseDTO> extends BaseDTO implements Response {

  private static final long serialVersionUID = -8348328384818361345L;

  private Collection<T> items;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Collection<T> getItems() {
    return items;
  }

  public void setItems(Collection<T> items) {
    this.items = items;
  }
}
