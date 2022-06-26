package lk.grocery.platform.service;

import lk.grocery.platform.dto.ItemCategoryDTO;

import java.util.List;

public interface ItemCategoryService {

    List<ItemCategoryDTO> getAllItemCategories();

    List<ItemCategoryDTO> getItemCategoriesByType(String itemCategoryTypeCode);
}
