package lk.grocery.platform.service;

import lk.grocery.platform.dto.PaginatedEntity;
import lk.grocery.platform.dto.PartyDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface PartyService {

    PartyDTO createParty(PartyDTO partyDTO);

    PartyDTO getPartyByPartyCode(String partyCode);

    PartyDTO updateParty(String partyCode, PartyDTO partyDTO);

    Boolean removeParty(String partyCode);

    PaginatedEntity partyPaginatedSearch(String name, String partyType, Integer page, Integer size);

    List<PartyDTO> getPartyListByType(String partyType);
}
