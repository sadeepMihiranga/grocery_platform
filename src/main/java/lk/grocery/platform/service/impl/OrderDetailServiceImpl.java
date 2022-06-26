package lk.grocery.platform.service.impl;

import lk.grocery.platform.config.EntityValidator;
import lk.grocery.platform.dto.OrderDetailDTO;
import lk.grocery.platform.entity.TMsOrderDetail;
import lk.grocery.platform.exception.InvalidDataException;
import lk.grocery.platform.exception.NoRequiredInfoException;
import lk.grocery.platform.exception.OperationException;
import lk.grocery.platform.exception.TransactionConflictException;
import lk.grocery.platform.mapper.OrderDetailMapper;
import lk.grocery.platform.repository.OrderDetailRepository;
import lk.grocery.platform.service.CommonReferenceService;
import lk.grocery.platform.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static lk.grocery.platform.util.constant.CommonReferenceTypeCodes.MEASUREMENT_TYPES;
import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;

@Slf4j
@Service
public class OrderDetailServiceImpl extends EntityValidator implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final CommonReferenceService commonReferenceService;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository,
                                  CommonReferenceService commonReferenceService) {
        this.orderDetailRepository = orderDetailRepository;
        this.commonReferenceService = commonReferenceService;
    }

    @Override
    public Boolean addItemsToGoodsList(Long orderId, List<OrderDetailDTO> orderDetailDTOList) {

        if(orderId == null)
            throw new NoRequiredInfoException("Order id is required");

        List<TMsOrderDetail> tMsOrderDetailList = new ArrayList<>();

        for(OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            validateEntity(orderDetailDTO);

            if(orderDetailDTO.getShowAltFlag().shortValue() != 0 && orderDetailDTO.getShowAltFlag().shortValue()  != 1)
                throw new InvalidDataException("ShowAltFlag is invalid");

            commonReferenceService
                    .getByCmrfCodeAndCmrtCode(MEASUREMENT_TYPES.getValue(), orderDetailDTO.getUom());

            orderDetailDTO.setOrderId(orderId);
            orderDetailDTO.setStatus(STATUS_ACTIVE.getShortValue());

            TMsOrderDetail tMsOrderDetail = OrderDetailMapper.INSTANCE.dtoToEntity(orderDetailDTO);

            tMsOrderDetailList.add(tMsOrderDetail);
        }

        persistEntities(tMsOrderDetailList);

        return true;
    }

    private List<TMsOrderDetail> persistEntities(List<TMsOrderDetail> tMsOrderDetailList) {
        try {
            return orderDetailRepository.saveAll(tMsOrderDetailList);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new TransactionConflictException("Transaction Updated by Another User.");
        } catch (Exception e) {
            log.error("Error while persisting : " + e.getMessage());
            throw new OperationException(e.getMessage());
        }
    }
}
