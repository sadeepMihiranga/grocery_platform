package lk.grocery.platform.mapper;

import lk.grocery.platform.dto.PaymentOptionDTO;
import lk.grocery.platform.entity.TRfPaymentOption;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentOptionMapper {

    PaymentOptionMapper INSTANCE = Mappers.getMapper(PaymentOptionMapper.class);

    @Mappings({
            @Mapping(source = "pyopId", target = "paymentOptionId"),
            @Mapping(source = "pyopName", target = "paymentOptionName"),
            @Mapping(source = "pyopDescription", target = "description"),
            @Mapping(source = "pyopStatus", target = "status")
    })
    PaymentOptionDTO entityToDTO(TRfPaymentOption entity);

    @InheritInverseConfiguration
    TRfPaymentOption dtoToEntity(PaymentOptionDTO dto);
}
