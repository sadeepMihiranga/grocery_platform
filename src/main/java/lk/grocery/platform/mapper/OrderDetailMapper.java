package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.OrderDetailDTO;
import lk.grocery.platform.entity.TMsOrderDetail;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDetailMapper {

    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    @Mappings({
            @Mapping(source = "oddtId", target = "orderDetailId"),
            @Mapping(source = "order.oderId", target = "orderId"),
            @Mapping(source = "item.itemId", target = "itemId"),
            @Mapping(source = "oddtRequestedQty", target = "requestedQty"),
            @Mapping(source = "oddtUom", target = "uom"),
            @Mapping(source = "oddtShowAltFlag", target = "showAltFlag"),
            @Mapping(source = "oddtStatus", target = "status"),
            @Mapping(source = "createdDate", target = "createdDate"),
            @Mapping(source = "lastModDate", target = "lastUpdatedDate"),
            @Mapping(source = "createdUserCode", target = "createdUserCode"),
            @Mapping(source = "lastModUserCode", target = "lastUpdatedUserCode")
    })
    OrderDetailDTO entityToDTO(TMsOrderDetail entity);

    @InheritInverseConfiguration
    TMsOrderDetail dtoToEntity(OrderDetailDTO dto);
}
