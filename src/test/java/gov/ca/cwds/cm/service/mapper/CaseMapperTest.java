package gov.ca.cwds.cm.service.mapper;

import static org.junit.Assert.assertNull;

import gov.ca.cwds.cm.service.dto.CaseDTO;
import gov.ca.cwds.data.legacy.cms.entity.Case;
import org.junit.Before;
import org.junit.Test;

public class CaseMapperTest {

  private CaseMapperImpl caseMapper;

  @Before
  public void before(){
    caseMapper = new CaseMapperImpl();
  }

  @Test
  public void testZeroNumberToNullNumberMapping() {
    Case caseEntity = new Case();
    caseEntity.setCaseClosureReasonTypeCode((short)0);
    caseEntity.setStateCode((short)0);

    CaseDTO caseDTO = caseMapper.toCaseDTO(caseEntity);
    assertNull(caseDTO.getCaseClosureReasonCode());
    assertNull(caseDTO.getStateCode());
  }
}
