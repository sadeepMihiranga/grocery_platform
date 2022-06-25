package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.DeliveryOptionDTO;
import lk.grocery.platform.entity.TRfDeliveryOption;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryOptionMapper {

    DeliveryOptionMapper INSTANCE = Mappers.getMapper(DeliveryOptionMapper.class);

    @Mappings({
            @Mapping(source = "dvopId", target = "deliveryOptionId"),
            @Mapping(source = "dvopName", target = "deliveryOptionName"),
            @Mapping(source = "dvopDescription", target = "description"),
            @Mapping(source = "dvopStatus", target = "status")
    })
    DeliveryOptionDTO entityToDTO(TRfDeliveryOption entity);

    @InheritInverseConfiguration
    TRfDeliveryOption dtoToEntity(DeliveryOptionDTO dto);
}
