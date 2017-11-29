package gov.ca.cwds.cm.service;

import gov.ca.cwds.cm.service.dto.CaseDTO;
import org.junit.Test;

/**
 * @author CWDS TPT-3 Team
 */
public class TypedCrudServiceAdapterTest {

  private final TypedCrudServiceAdapter testSubject = new TypedCrudServiceAdapter();

  @Test(expected = UnsupportedOperationException.class)
  public void create_exception_whenAnyInput() {
    testSubject.create(new CaseDTO());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void find_exception_whenAnyInput() {
    testSubject.find(123);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void update_exception_whenAnyInput() {
    testSubject.update("any", null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void delete_exception_whenAnyInput() {
    testSubject.delete(0L);
  }

}