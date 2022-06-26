package lk.grocery.platform.service.impl;

import lk.grocery.platform.config.EntityValidator;
import lk.grocery.platform.dto.ItemCategoryDTO;
import lk.grocery.platform.entity.TRfItemCategory;
import lk.grocery.platform.mapper.ItemCategoryMapper;
import lk.grocery.platform.repository.ItemCategoryRepository;
import lk.grocery.platform.service.ItemCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;

@Slf4j
@Service
public class ItemCategoryServiceImpl extends EntityValidator implements ItemCategoryService {

    private final ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryServiceImpl(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    @Override
    public List<ItemCategoryDTO> getAllItemCategories() {

        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();

        List<TRfItemCategory> tRfItemCategoryList = itemCategoryRepository.findByItctStatus(STATUS_ACTIVE.getShortValue());

        for(TRfItemCategory tRfItemCategory : tRfItemCategoryList) {
            itemCategoryDTOList.add(ItemCategoryMapper.INSTANCE.entityToDTO(tRfItemCategory));
        }

        return itemCategoryDTOList;
    }

    @Override
    public List<ItemCategoryDTO> getItemCategoriesByType(String itemCategoryTypeCode) {
        return null;
    }
}
