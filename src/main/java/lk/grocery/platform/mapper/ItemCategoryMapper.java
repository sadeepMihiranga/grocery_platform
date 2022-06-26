package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.ItemCategoryDTO;
import lk.grocery.platform.entity.TRfItemCategory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemCategoryMapper {

    ItemCategoryMapper INSTANCE = Mappers.getMapper(ItemCategoryMapper.class);

    @Mappings({
            @Mapping(source = "itctId", target = "itemCategoryId"),
            @Mapping(source = "itctName", target = "itemCategoryName"),
            @Mapping(source = "itctCategoryType", target = "itemCategoryType"),
            @Mapping(source = "itctDescription", target = "itemCategoryTypeName"),
            @Mapping(source = "itctStatus", target = "status")
    })
    ItemCategoryDTO entityToDTO(TRfItemCategory entity);

    @InheritInverseConfiguration
    TRfItemCategory dtoToEntity(ItemCategoryDTO dto);
}
