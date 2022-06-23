package lk.grocery.platform.service;

import lk.grocery.platform.dto.CommonReferenceDTO;

import java.util.List;

public interface CommonReferenceService {

    List<CommonReferenceDTO> getAllByCmrtCode(String cmrtCode);

    CommonReferenceDTO getByCmrfCodeAndCmrtCode(String cmrtCode, String cmrfCode);
}
