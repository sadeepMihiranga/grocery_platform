package lk.grocery.platform.service;

import lk.grocery.platform.dto.DropDownDTO;

import java.util.List;
import java.util.Map;

public interface DropDownService {

    List<DropDownDTO> getDropDownByCode(String code, String subCode);

    Map<String, String> getDropDownCodes();
}
