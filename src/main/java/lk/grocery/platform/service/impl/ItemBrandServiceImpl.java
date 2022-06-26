package lk.grocery.platform.service.impl;

import lk.grocery.platform.config.EntityValidator;
import lk.grocery.platform.dto.ItemBrandDTO;
import lk.grocery.platform.entity.TRfItemBrand;
import lk.grocery.platform.mapper.ItemBrandMapper;
import lk.grocery.platform.repository.ItemBrandRepository;
import lk.grocery.platform.service.ItemBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;

@Slf4j
@Service
public class ItemBrandServiceImpl extends EntityValidator implements ItemBrandService {

    private final ItemBrandRepository itemBrandRepository;

    public ItemBrandServiceImpl(ItemBrandRepository itemBrandRepository) {
        this.itemBrandRepository = itemBrandRepository;
    }

    @Override
    public List<ItemBrandDTO> getAllItemBrands() {

        List<ItemBrandDTO> itemBrandDTOList = new ArrayList<>();

        List<TRfItemBrand> tRfItemBrandList = itemBrandRepository.findByItbdStatus(STATUS_ACTIVE.getShortValue());

        for(TRfItemBrand tRfItemBrand : tRfItemBrandList) {
            itemBrandDTOList.add(ItemBrandMapper.INSTANCE.entityToDTO(tRfItemBrand));
        }

        return itemBrandDTOList;
    }
}
