package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.OrderDTO;
import lk.grocery.platform.entity.TMsOrder;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(source = "oderId", target = "oderId"),
            @Mapping(source = "customer.prtyCode", target = "customerCode"),
            @Mapping(source = "oderOrderedDate", target = "orderedDate"),
            @Mapping(source = "oderStatus", target = "status"),
            @Mapping(source = "oderUrgencyLevel", target = "urgencyLevel"),
            @Mapping(source = "createdDate", target = "createdDate"),
            @Mapping(source = "lastModDate", target = "lastUpdatedDate"),
            @Mapping(source = "createdUserCode", target = "createdUserCode"),
            @Mapping(source = "lastModUserCode", target = "lastUpdatedUserCode")
    })
    OrderDTO entityToDTO(TMsOrder entity);

    @InheritInverseConfiguration
    TMsOrder dtoToEntity(OrderDTO dto);
}
