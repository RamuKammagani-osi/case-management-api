package gov.ca.cwds.cm.service;

import com.google.inject.Inject;
import gov.ca.cwds.cm.service.dto.CSECHistoryDTO;
import gov.ca.cwds.cm.service.mapper.CSECHistoryMapper;
import gov.ca.cwds.data.legacy.cms.dao.CSECHistoryDao;
import gov.ca.cwds.data.legacy.cms.entity.CSECHistory;
import java.util.Arrays;
import java.util.List;

/** @author CWDS TPT-3 Team */
public class CSECHistoryService {

  private CSECHistoryDao csecHistoryDao;
  private CSECHistoryMapper mapper;

  @Inject
  public CSECHistoryService(CSECHistoryDao csecHistoryDao,
      CSECHistoryMapper mapper) {
    this.csecHistoryDao = csecHistoryDao;
    this.mapper = mapper;
  }

  public List<CSECHistoryDTO> findByClientId(String clientId) {
    List<CSECHistory> csecHistories = csecHistoryDao.findByClientId(clientId);
    return Arrays.asList(mapper.to(csecHistories));
  }
}
