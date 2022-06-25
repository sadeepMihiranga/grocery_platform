package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.VendorStoreDTO;
import lk.grocery.platform.entity.TMsVendorStore;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorStoreMapper {

    VendorStoreMapper INSTANCE = Mappers.getMapper(VendorStoreMapper.class);

    @Mappings({
            @Mapping(source = "vnstId", target = "vendorStoreId"),
            @Mapping(source = "vendor.prtyCode", target = "vendorCode"),
            @Mapping(source = "store.storId", target = "storeId"),
            @Mapping(source = "vnstStatus", target = "status"),
            @Mapping(source = "createdDate", target = "createdDate"),
            @Mapping(source = "lastModDate", target = "lastUpdatedDate"),
            @Mapping(source = "createdUserCode", target = "createdUserCode"),
            @Mapping(source = "lastModUserCode", target = "lastUpdatedUserCode")
    })
    VendorStoreDTO entityToDTO(TMsVendorStore entity);

    @InheritInverseConfiguration
    TMsVendorStore dtoToEntity(VendorStoreDTO dto);
}
