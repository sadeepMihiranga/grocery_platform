package lk.grocery.platform.service;

import lk.grocery.platform.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    Long createGoodsList(OrderDTO orderDTO);

    Boolean clearGoodsList(Long orderId, boolean isRemoveRequest);

    List<OrderDTO> getGoodsListByCustomer(String customerCode);

    OrderDTO getGoodsList(Long orderId);
}
