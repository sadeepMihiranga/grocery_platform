package lk.grocery.platform.service.impl;

import lk.grocery.platform.config.EntityValidator;
import lk.grocery.platform.dto.OrderDTO;
import lk.grocery.platform.entity.TMsOrder;
import lk.grocery.platform.entity.TMsParty;
import lk.grocery.platform.exception.DataNotFoundException;
import lk.grocery.platform.exception.NoRequiredInfoException;
import lk.grocery.platform.exception.OperationException;
import lk.grocery.platform.exception.TransactionConflictException;
import lk.grocery.platform.mapper.OrderMapper;
import lk.grocery.platform.repository.OrderRepository;
import lk.grocery.platform.repository.PartyRepository;
import lk.grocery.platform.service.OrderDetailService;
import lk.grocery.platform.service.OrderService;
import lk.grocery.platform.util.constant.status.OrderUrgencyLevel;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static lk.grocery.platform.util.constant.Constants.STATUS_ACTIVE;
import static lk.grocery.platform.util.constant.Constants.STATUS_INACTIVE;
import static lk.grocery.platform.util.constant.status.OrderStatus.*;

@Slf4j
@Service
public class OrderServiceImpl extends EntityValidator implements OrderService {

    private final OrderDetailService orderDetailService;
    private final OrderRepository orderRepository;
    private final PartyRepository partyRepository;

    public OrderServiceImpl(OrderDetailService orderDetailService,
                            OrderRepository orderRepository,
                            PartyRepository partyRepository) {
        this.orderDetailService = orderDetailService;
        this.orderRepository = orderRepository;
        this.partyRepository = partyRepository;
    }

    @Transactional
    @Override
    public Long createGoodsList(OrderDTO orderDTO) {

        validateEntity(orderDTO);

        TMsParty customer = validatePartyCode(orderDTO.getCustomerCode());

        if(orderDTO.getGoodsList().isEmpty())
            throw new NoRequiredInfoException("Goods list cannot be empty");

        TMsOrder tMsOrder = new TMsOrder();

        if(orderDTO.getUrgencyLevel() != null) {
            OrderUrgencyLevel urgencyLevel = OrderUrgencyLevel.getNameByNo(orderDTO.getUrgencyLevel());

            if(urgencyLevel == null)
                throw new DataNotFoundException("Invalid urgency level : " + orderDTO.getUrgencyLevel());

            tMsOrder.setOderUrgencyLevel(orderDTO.getUrgencyLevel());
        }

        tMsOrder.setCustomer(customer);
        tMsOrder.setOderOrderedDate(LocalDateTime.now());
        tMsOrder.setOderStatus(CREATED.toString());

        OrderDTO createdOrder = OrderMapper.INSTANCE.entityToDTO(persistEntity(tMsOrder));

        orderDetailService.addItemsToGoodsList(createdOrder.getOderId(), orderDTO.getGoodsList());

        return createdOrder.getOderId();
    }

    @Override
    public Boolean clearGoodsList(Long orderId, boolean isRemoveRequest) {

        TMsOrder tMsOrder = validateOrderById(orderId);

        if(!tMsOrder.getOderStatus().equals(CREATED.toString()) && isRemoveRequest)
            throw new OperationException("Cannot remove order at this stage");

        if(isRemoveRequest) {
            tMsOrder.setOderStatus(DELETED.toString());
        } else {
            tMsOrder.setOderStatus(COMPLETED.toString());
        }

        tMsOrder.setOderActiveStatus(STATUS_INACTIVE.getShortValue());

        orderDetailService.removeItemsFromList(tMsOrder.getOderId());

        persistEntity(tMsOrder);

        return null;
    }

    @Override
    public List<OrderDTO> getGoodsListByCustomer(String customerCode) {
        return null;
    }

    @Override
    public OrderDTO getGoodsList(Long orderId) {

        OrderDTO orderDTO = OrderMapper.INSTANCE.entityToDTO(validateOrderById(orderId));

        orderDTO.setGoodsList(orderDetailService.getGoodsList(orderId));

        return orderDTO;
    }

    private TMsParty validatePartyCode(String partyCode) {

        if(Strings.isNullOrEmpty(partyCode))
            throw new NoRequiredInfoException("Customer code cannot be empty");

        TMsParty party = partyRepository.findByPrtyCodeAndPrtyStatus(partyCode, STATUS_ACTIVE.getShortValue());

        if(party == null)
            throw new DataNotFoundException("Party not found for the Code : " + partyCode);

        return party;
    }

    private TMsOrder validateOrderById(Long orderId) {
        if(orderId == null)
            throw new NoRequiredInfoException("Order Id is required");

        Optional<TMsOrder> tMsOrder = orderRepository.findById(orderId);

        if(!tMsOrder.isPresent())
            throw new DataNotFoundException("Order not found for Id " + orderId);

        if(tMsOrder.get().getOderActiveStatus() != 1)
            throw new OperationException("Order is in Inactive state");

        return tMsOrder.get();
    }

    private TMsOrder persistEntity(TMsOrder tMsOrder) {
        try {
            return orderRepository.save(tMsOrder);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new TransactionConflictException("Transaction Updated by Another User.");
        } catch (Exception e) {
            log.error("Error while persisting : " + e.getMessage());
            throw new OperationException(e.getMessage());
        }
    }
}
