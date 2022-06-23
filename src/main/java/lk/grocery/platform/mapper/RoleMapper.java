package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.RoleDTO;
import lk.grocery.platform.entity.TMsRole;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mappings({
            @Mapping(source = "roleId", target = "id"),
            @Mapping(source = "roleName", target = "name"),
            @Mapping(source = "roleDescription", target = "description"),
            @Mapping(source = "roleStatus", target = "status")
    })
    RoleDTO entityToDTO(TMsRole entity);

    @InheritInverseConfiguration
    TMsRole dtoToEntity(RoleDTO dto);
}
