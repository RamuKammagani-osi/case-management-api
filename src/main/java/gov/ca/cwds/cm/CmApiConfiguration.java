package gov.ca.cwds.cm;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.BaseApiConfiguration;

public class CmApiConfiguration extends BaseApiConfiguration {

  private boolean upgardeDbOnStart = false;

  @JsonProperty
  public boolean isUpgradeDbOnStart() {
    return upgardeDbOnStart;
  }

  public void setUpgardeDbOnStart(boolean upgardeDbOnStart) {
    this.upgardeDbOnStart = upgardeDbOnStart;
  }
}
