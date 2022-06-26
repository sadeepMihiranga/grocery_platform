package lk.grocery.platform.service.impl;

import lk.grocery.platform.config.EntityValidator;
import lk.grocery.platform.dto.ItemCategoryDTO;
import lk.grocery.platform.entity.TRfItemCategory;
import lk.grocery.platform.exception.NoRequiredInfoException;
import lk.grocery.platform.mapper.ItemCategoryMapper;
import lk.grocery.platform.repository.ItemCategoryRepository;
import lk.grocery.platform.service.CommonReferenceService;
import lk.grocery.platform.service.ItemCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static lk.grocery.platform.util.constant.CommonReferenceTypeCodes.ITEM_CATEGORY_TYPE;
import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;

@Slf4j
@Service
public class ItemCategoryServiceImpl extends EntityValidator implements ItemCategoryService {

    private final CommonReferenceService commonReferenceService;

    private final ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryServiceImpl(CommonReferenceService commonReferenceService,
                                   ItemCategoryRepository itemCategoryRepository) {
        this.commonReferenceService = commonReferenceService;
        this.itemCategoryRepository = itemCategoryRepository;
    }

    @Override
    public List<ItemCategoryDTO> getAllItemCategories() {

        List<TRfItemCategory> tRfItemCategoryList = itemCategoryRepository.findByItctStatus(STATUS_ACTIVE.getShortValue());

        return mapEntityListToDtoList(tRfItemCategoryList);
    }

    @Override
    public List<ItemCategoryDTO> getItemCategoriesByType(String itemCategoryTypeCode) {

        if(Strings.isNullOrEmpty(itemCategoryTypeCode))
            throw new NoRequiredInfoException("Item category type code is required");

        commonReferenceService.getByCmrfCodeAndCmrtCode(ITEM_CATEGORY_TYPE.getValue(), itemCategoryTypeCode);

        List<TRfItemCategory> tRfItemCategoryList = itemCategoryRepository.findByItctStatusAndItctCategoryType(STATUS_ACTIVE.getShortValue(), itemCategoryTypeCode);

        return mapEntityListToDtoList(tRfItemCategoryList);
    }

    private  List<ItemCategoryDTO> mapEntityListToDtoList(List<TRfItemCategory> tRfItemCategoryList) {

        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();

        for(TRfItemCategory tRfItemCategory : tRfItemCategoryList) {
            itemCategoryDTOList.add(ItemCategoryMapper.INSTANCE.entityToDTO(tRfItemCategory));
        }

        return itemCategoryDTOList;
    }
}
