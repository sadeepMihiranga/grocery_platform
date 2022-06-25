package lk.grocery.platform.service.impl;

import lk.grocery.platform.dto.VendorStoreDTO;
import lk.grocery.platform.entity.TMsParty;
import lk.grocery.platform.entity.TMsStore;
import lk.grocery.platform.entity.TMsVendorStore;
import lk.grocery.platform.exception.DataNotFoundException;
import lk.grocery.platform.exception.NoRequiredInfoException;
import lk.grocery.platform.exception.OperationException;
import lk.grocery.platform.exception.TransactionConflictException;
import lk.grocery.platform.mapper.VendorStoreMapper;
import lk.grocery.platform.repository.PartyRepository;
import lk.grocery.platform.repository.StoreRepository;
import lk.grocery.platform.repository.VendorStoreRepository;
import lk.grocery.platform.service.VendorStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;
import static lk.grocery.platform.util.constant.Constants.STATUS_INACTIVE;

@Slf4j
@Service
public class VendorStoreServiceImpl implements VendorStoreService {

    private final VendorStoreRepository vendorStoreRepository;
    private final StoreRepository storeRepository;
    private final PartyRepository partyRepository;

    public VendorStoreServiceImpl(VendorStoreRepository vendorStoreRepository,
                                  StoreRepository storeRepository,
                                  PartyRepository partyRepository) {
        this.vendorStoreRepository = vendorStoreRepository;
        this.storeRepository = storeRepository;
        this.partyRepository = partyRepository;
    }

    @Override
    public Boolean addVendorsToStore(Long storeId, List<String> vendorsCodeList) {

        List<TMsVendorStore> tMsVendorStoreList = new ArrayList<>();

        TMsStore tMsStore = validateStoreById(storeId);

        for (String vendorCode : vendorsCodeList) {
            TMsParty vendor = partyRepository.findByPrtyCodeAndPrtyStatus(vendorCode, STATUS_ACTIVE.getShortValue());

            if(vendor == null)
                throw new DataNotFoundException("Vendor not found for code " + vendorCode);

            TMsVendorStore vendorStore = new TMsVendorStore();
            vendorStore.setVendor(vendor);
            vendorStore.setStore(tMsStore);
            vendorStore.setVnstStatus(STATUS_ACTIVE.getShortValue());

            tMsVendorStoreList.add(vendorStore);
        }

        if(!tMsVendorStoreList.isEmpty())
            persistEntities(tMsVendorStoreList);

        return true;
    }

    @Override
    public Boolean removeVendorsFromAStore(Long storeId, List<String> vendorsCodeList) {

        validateStoreById(storeId);

        for (String vendorCode : vendorsCodeList) {
            TMsVendorStore tMsVendorStore = vendorStoreRepository.findByVendor_PrtyCodeAndVnstStatus(vendorCode, STATUS_ACTIVE.getShortValue());

            if(tMsVendorStore == null)
                throw new DataNotFoundException("Vendor not found for code " + vendorCode);

            tMsVendorStore.setVnstStatus( STATUS_INACTIVE.getShortValue());
            persistEntity(tMsVendorStore);
        }

        return true;
    }

    @Override
    public List<VendorStoreDTO> getVendorsByStoreId(Long storeId) {

        validateStoreById(storeId);

        List<VendorStoreDTO> vendorStoreDTOList = new ArrayList<>();

        List<TMsVendorStore> tMsVendorStoreList = vendorStoreRepository.findByStore_StorIdAndVnstStatus(storeId, STATUS_ACTIVE.getShortValue());

        for(TMsVendorStore vendorStore : tMsVendorStoreList) {

            VendorStoreDTO vendorStoreDTO = VendorStoreMapper.INSTANCE.entityToDTO(vendorStore);
            vendorStoreDTO.setVendorName(vendorStore.getVendor().getPrtyName());
            vendorStoreDTO.setVendorType(vendorStore.getVendor().getPrtyType());

            vendorStoreDTOList.add(vendorStoreDTO);
        }

        return vendorStoreDTOList;
    }

    private TMsStore validateStoreById(Long storeId) {
        if(storeId == null)
            throw new NoRequiredInfoException("Store Id is required");

        final TMsStore tMsStore = storeRepository.findByStorIdAndStorStatus(storeId, STATUS_ACTIVE.getShortValue());

        if(tMsStore == null)
            throw new DataNotFoundException("Store not found for Id " + storeId);

        return tMsStore;
    }

    private List<TMsVendorStore> persistEntities(List<TMsVendorStore> tMsVendorStoreList) {
        try {
            return vendorStoreRepository.saveAll(tMsVendorStoreList);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new TransactionConflictException("Transaction Updated by Another User.");
        } catch (Exception e) {
            log.error("Error while persisting : " + e.getMessage());
            throw new OperationException(e.getMessage());
        }
    }

    private TMsVendorStore persistEntity(TMsVendorStore tMsVendorStore) {
        try {
            return vendorStoreRepository.save(tMsVendorStore);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new TransactionConflictException("Transaction Updated by Another User.");
        } catch (Exception e) {
            log.error("Error while persisting : " + e.getMessage());
            throw new OperationException(e.getMessage());
        }
    }
}
