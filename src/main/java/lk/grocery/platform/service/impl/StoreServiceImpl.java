package lk.grocery.platform.service.impl;

import lk.grocery.platform.config.EntityValidator;
import lk.grocery.platform.dto.PaginatedEntity;
import lk.grocery.platform.dto.StoreDTO;
import lk.grocery.platform.entity.TMsStore;
import lk.grocery.platform.exception.DataNotFoundException;
import lk.grocery.platform.exception.NoRequiredInfoException;
import lk.grocery.platform.exception.OperationException;
import lk.grocery.platform.exception.TransactionConflictException;
import lk.grocery.platform.mapper.StoreMapper;
import lk.grocery.platform.repository.StoreRepository;
import lk.grocery.platform.service.StoreService;
import lk.grocery.platform.service.VendorStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;
import static lk.grocery.platform.util.constant.Constants.STATUS_INACTIVE;

@Slf4j
@Service
public class StoreServiceImpl extends EntityValidator implements StoreService {

    private final StoreRepository storeRepository;
    private final VendorStoreService vendorStoreService;

    public StoreServiceImpl(StoreRepository storeRepository,
                            VendorStoreService vendorStoreService) {
        this.storeRepository = storeRepository;
        this.vendorStoreService = vendorStoreService;
    }

    @Transactional
    @Override
    public StoreDTO createStore(StoreDTO storeDTO) {

        validateEntity(storeDTO);

        TMsStore tMsStore = StoreMapper.INSTANCE.dtoToEntity(storeDTO);
        tMsStore.setStorStatus(STATUS_ACTIVE.getShortValue());

        TMsStore createdStore = persistEntity(tMsStore);

        return getStoreById(createdStore.getStorId());
    }

    @Override
    public StoreDTO getStoreById(Long storeId) {

        TMsStore tMsStore = validateStoreById(storeId);

        return StoreMapper.INSTANCE.entityToDTO(tMsStore);
    }

    @Transactional
    @Override
    public StoreDTO updateStore(Long storeId, StoreDTO storeDTO) {

        TMsStore tMsStore = validateStoreById(storeId);

        validateEntity(storeDTO);

        tMsStore.setStorName(storeDTO.getStoreName());
        tMsStore.setStorRegNo(storeDTO.getRegNo());
        tMsStore.setStorAddress1(storeDTO.getAddress1());
        tMsStore.setStorAddress2(storeDTO.getAddress2());
        tMsStore.setStorAddress3(storeDTO.getAddress3());
        tMsStore.setStorContactNo(storeDTO.getContactNo());
        tMsStore.setStorEmail(storeDTO.getEmail());
        tMsStore.setStorLongitude(storeDTO.getLongitude());
        tMsStore.setStorLatitude(storeDTO.getLatitude());

        persistEntity(tMsStore);

        return getStoreById(storeId);
    }

    @Transactional
    @Override
    public Boolean removeStore(Long storeId) {

        TMsStore tMsStore = validateStoreById(storeId);

        tMsStore.setStorStatus(STATUS_INACTIVE.getShortValue());
        persistEntity(tMsStore);

        return true;
    }

    @Override
    public PaginatedEntity storePaginatedSearch(String name, String regNo, Integer page, Integer size) {

        PaginatedEntity paginatedStoreList = null;
        List<StoreDTO> storeList = null;

        validatePaginateIndexes(page, size);

        Page<TMsStore> tMsStores = storeRepository
                .searchStores(name, regNo, STATUS_ACTIVE.getShortValue(), PageRequest.of(page - 1, size));

        if(tMsStores.getSize() == 0)
            return null;

        paginatedStoreList = new PaginatedEntity();
        storeList = new ArrayList<>();

        for (TMsStore tMsStore : tMsStores) {
            storeList.add(StoreMapper.INSTANCE.entityToDTO(tMsStore));
        }

        paginatedStoreList.setTotalNoOfPages(tMsStores.getTotalPages());
        paginatedStoreList.setTotalNoOfRecords(tMsStores.getTotalElements());
        paginatedStoreList.setEntities(storeList);

        return paginatedStoreList;
    }

    private TMsStore validateStoreById(Long storeId) {
        if(storeId == null)
            throw new NoRequiredInfoException("Store Id is required");

        final TMsStore tMsStore = storeRepository.findByStorIdAndStorStatus(storeId, STATUS_ACTIVE.getShortValue());

        if(tMsStore == null)
            throw new DataNotFoundException("Store not found for Id " + storeId);

        return tMsStore;
    }

    private TMsStore persistEntity(TMsStore tMsStore) {
        try {
            return storeRepository.save(tMsStore);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new TransactionConflictException("Transaction Updated by Another User.");
        } catch (Exception e) {
            log.error("Error while persisting : " + e.getMessage());
            throw new OperationException(e.getMessage());
        }
    }
}
