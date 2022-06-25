package lk.grocery.platform.service;

import lk.grocery.platform.dto.VendorStoreDTO;

import java.util.List;

public interface VendorStoreService {

    Boolean addVendorsToStore(Long storeId, List<String> vendorsCodeList);

    Boolean removeVendorsFromAStore(Long storeId, List<String> vendorsCodeList);

    List<VendorStoreDTO> getVendorsByStoreId(Long storeId);
}
