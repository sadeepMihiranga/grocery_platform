package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.RoleDTO;
import lk.grocery.platform.dto.StoreDTO;
import lk.grocery.platform.entity.TMsRole;
import lk.grocery.platform.entity.TMsStore;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mappings({
            @Mapping(source = "storId", target = "storeId"),
            @Mapping(source = "storName", target = "storeName"),
            @Mapping(source = "storRegNo", target = "regNo"),
            @Mapping(source = "storAddress1", target = "address1"),
            @Mapping(source = "storAddress2", target = "address2"),
            @Mapping(source = "storAddress3", target = "address3"),
            @Mapping(source = "storContactNo", target = "contactNo"),
            @Mapping(source = "storEmail", target = "email"),
            @Mapping(source = "storLongitude", target = "longitude"),
            @Mapping(source = "storLatitude", target = "latitude"),
            @Mapping(source = "storStatus", target = "status"),
            @Mapping(source = "createdDate", target = "createdDate"),
            @Mapping(source = "lastModDate", target = "lastUpdatedDate"),
            @Mapping(source = "createdUserCode", target = "createdUserCode"),
            @Mapping(source = "lastModUserCode", target = "lastUpdatedUserCode")
    })
    StoreDTO entityToDTO(TMsStore entity);

    @InheritInverseConfiguration
    TMsStore dtoToEntity(StoreDTO dto);
}
