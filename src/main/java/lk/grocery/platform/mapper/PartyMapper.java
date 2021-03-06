package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.PartyDTO;
import lk.grocery.platform.entity.TMsParty;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartyMapper {

    PartyMapper INSTANCE = Mappers.getMapper(PartyMapper.class);

    @Mappings({
            @Mapping(source = "prtyCode", target = "partyCode"),
            @Mapping(source = "prtyName", target = "name"),
            @Mapping(source = "prtyFirstName", target = "firstName"),
            @Mapping(source = "prtyLastName", target = "lastName"),
            @Mapping(source = "prtyDob", target = "dob"),
            @Mapping(source = "prtyAddress1", target = "address1"),
            @Mapping(source = "prtyAddress2", target = "address2"),
            @Mapping(source = "prtyAddress3", target = "address3"),
            @Mapping(source = "prtyGender", target = "gender"),
            @Mapping(source = "prtyNic", target = "nic"),
            @Mapping(source = "prtyType", target = "type"),
            @Mapping(source = "prtyManagedBy", target = "managedBy"),
            @Mapping(source = "prtyStatus", target = "status"),
            @Mapping(source = "createdDate", target = "createdDate"),
            @Mapping(source = "lastModDate", target = "lastUpdatedDate"),
            @Mapping(source = "createdUserCode", target = "createdUserCode"),
            @Mapping(source = "lastModUserCode", target = "lastUpdatedUserCode"),
            @Mapping(source = "prtyLongitude", target = "longitude"),
            @Mapping(source = "prtyLatitude", target = "latitude")
    })
    PartyDTO entityToDTO(TMsParty entity);

    @InheritInverseConfiguration
    TMsParty dtoToEntity(PartyDTO dto);
}
