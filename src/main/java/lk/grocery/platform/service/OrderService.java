package lk.grocery.platform.service;

import lk.grocery.platform.dto.OrderDTO;

public interface OrderService {

    Long createGoodsList(OrderDTO orderDTO);

    Boolean clearGoodsList(Long orderId, boolean isRemoveRequest);

    OrderDTO getGoodsListByCustomer(String customerCode);
}
