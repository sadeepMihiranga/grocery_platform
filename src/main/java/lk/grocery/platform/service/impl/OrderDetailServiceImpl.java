package lk.grocery.platform.service.impl;

import lk.grocery.platform.config.EntityValidator;
import lk.grocery.platform.dto.OrderDetailDTO;
import lk.grocery.platform.entity.TMsOrder;
import lk.grocery.platform.entity.TMsOrderDetail;
import lk.grocery.platform.exception.*;
import lk.grocery.platform.mapper.OrderDetailMapper;
import lk.grocery.platform.repository.OrderDetailRepository;
import lk.grocery.platform.repository.OrderRepository;
import lk.grocery.platform.service.CommonReferenceService;
import lk.grocery.platform.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lk.grocery.platform.util.constant.CommonReferenceTypeCodes.MEASUREMENT_TYPES;
import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;
import static lk.grocery.platform.util.constant.Constants.STATUS_INACTIVE;

@Slf4j
@Service
public class OrderDetailServiceImpl extends EntityValidator implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final CommonReferenceService commonReferenceService;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository,
                                  OrderRepository orderRepository,
                                  CommonReferenceService commonReferenceService) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
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

    @Override
    public Boolean removeItemFromList(Long orderId, Long itemId) {

        if(itemId == null)
            throw new NoRequiredInfoException("Item Id is required");

        validateOrderById(orderId);

        List<TMsOrderDetail> tMsOrderDetailList = orderDetailRepository
                .findByOrder_OderIdAndItem_ItemIdAndOddtStatus(orderId, itemId, STATUS_ACTIVE.getShortValue());

        if(tMsOrderDetailList.isEmpty())
            throw new DataNotFoundException("Order Item not found");

        for(TMsOrderDetail tMsOrderDetail : tMsOrderDetailList) {
            tMsOrderDetail.setOddtStatus(STATUS_INACTIVE.getShortValue());
        }

        persistEntities(tMsOrderDetailList);

        return true;
    }

    @Override
    public Boolean removeItemsFromList(Long orderId) {

        validateOrderById(orderId);

        List<TMsOrderDetail> tMsOrderDetailList = orderDetailRepository
                .findByOrder_OderIdAndOddtStatus(orderId, STATUS_ACTIVE.getShortValue());

        if(tMsOrderDetailList.isEmpty())
            return true;

        for(TMsOrderDetail tMsOrderDetail : tMsOrderDetailList) {
            tMsOrderDetail.setOddtStatus(STATUS_INACTIVE.getShortValue());
        }

        persistEntities(tMsOrderDetailList);

        return true;
    }

    @Override
    public List<OrderDetailDTO> getGoodsList(Long orderId) {

        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();

        List<TMsOrderDetail> tMsOrderDetailList = orderDetailRepository
                .findByOrder_OderIdAndOddtStatus(orderId, STATUS_ACTIVE.getShortValue());

        for(TMsOrderDetail tMsOrderDetail : tMsOrderDetailList) {
            orderDetailDTOList.add(OrderDetailMapper.INSTANCE.entityToDTO(tMsOrderDetail));
        }

        return orderDetailDTOList;
    }

    private TMsOrder validateOrderById(Long orderId) {
        if(orderId == null)
            throw new NoRequiredInfoException("Order Id is required");

        Optional<TMsOrder> tMsOrder = orderRepository.findById(orderId);

        if(!tMsOrder.isPresent())
            throw new DataNotFoundException("Order not found for Id " + orderId);

        return tMsOrder.get();
    }

    private TMsOrderDetail persistEntity(TMsOrderDetail tMsOrderDetail) {
        try {
            return orderDetailRepository.save(tMsOrderDetail);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new TransactionConflictException("Transaction Updated by Another User.");
        } catch (Exception e) {
            log.error("Error while persisting : " + e.getMessage());
            throw new OperationException(e.getMessage());
        }
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
