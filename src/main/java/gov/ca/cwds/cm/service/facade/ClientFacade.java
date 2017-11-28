package gov.ca.cwds.cm.service.facade;

import com.google.inject.Inject;
import gov.ca.cwds.cm.ClientsService;
import gov.ca.cwds.cm.service.ChildClientService;
import gov.ca.cwds.cm.service.StaffService;
import gov.ca.cwds.cm.service.dictionaries.ClientType;
import gov.ca.cwds.cm.service.dto.BaseClientDTO;
import gov.ca.cwds.cm.service.dto.CaseDTO;
import gov.ca.cwds.cm.service.dto.CaseLoadDTO;
import gov.ca.cwds.rest.api.Response;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author CWDS TPT-3 Team
 */
public class ClientFacade {

    @Inject
    private ChildClientService childClientService;
    @Inject
    private StaffService staffService;
    @Inject
    private ClientsService clientsService;

    public Response find(Serializable serializable, ClientType clientType) {
        switch (clientType) {
            case CHILD_CLIENT:
                return childClientService.find(serializable);
            default:
                return null;
        }
    }

    /**
     *
     * @param staffId
     * @return Response with List of {@link gov.ca.cwds.cm.service.dto.BaseClientDTO} by staffId
     */
    public Collection<? extends BaseClientDTO> find(String staffId) {
        Collection<CaseLoadDTO> caseLoads = staffService.getCaseLoads(staffId);
        Collection<CaseDTO> cases = getCasesFromCaseLoads(caseLoads);
        Collection<BaseClientDTO> clients = clientsService.getClientsByCases(cases);
        return clients;
    }

    private Collection<CaseDTO> getCasesFromCaseLoads(Collection<CaseLoadDTO> caseLoads) {
        return null;
    }


}
