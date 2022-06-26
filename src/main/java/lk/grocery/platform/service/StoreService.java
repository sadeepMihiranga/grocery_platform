package lk.grocery.platform.service;

import lk.grocery.platform.dto.PaginatedEntity;
import lk.grocery.platform.dto.StoreDTO;

public interface StoreService {

    Long createStore(StoreDTO storeDTO);

    StoreDTO getStoreById(Long storeId);

    Boolean updateStore(Long storeId, StoreDTO storeDTO);

    Boolean removeStore(Long storeId);

    PaginatedEntity storePaginatedSearch(String name, String regNo, Integer page, Integer size);
}
