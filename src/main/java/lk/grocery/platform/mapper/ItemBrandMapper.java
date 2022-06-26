package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.ItemBrandDTO;
import lk.grocery.platform.entity.TRfItemBrand;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemBrandMapper {

    ItemBrandMapper INSTANCE = Mappers.getMapper(ItemBrandMapper.class);

    @Mappings({
            @Mapping(source = "itbdId", target = "itemBrandId"),
            @Mapping(source = "itbdName", target = "itemBrandName"),
            @Mapping(source = "itbdDescription", target = "description"),
            @Mapping(source = "itbdStatus", target = "status")
    })
    ItemBrandDTO entityToDTO(TRfItemBrand entity);

    @InheritInverseConfiguration
    TRfItemBrand dtoToEntity(ItemBrandDTO dto);
}
