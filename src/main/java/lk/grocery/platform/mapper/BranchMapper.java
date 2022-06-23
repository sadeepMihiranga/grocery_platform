package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.BranchDTO;
import lk.grocery.platform.entity.TRfBranch;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    @Mappings({
            @Mapping(source = "brnhId", target = "branchId"),
            @Mapping(source = "brnhName", target = "mame"),
            @Mapping(source = "brnhDescription", target = "description"),
            @Mapping(source = "brnhStatus", target = "status")
    })
    BranchDTO entityToDTO(TRfBranch entity);

    @InheritInverseConfiguration
    TRfBranch dtoToEntity(BranchDTO dto);
}
