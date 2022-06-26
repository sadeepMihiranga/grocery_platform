package lk.grocery.platform.service;

import lk.grocery.platform.dto.OrderDTO;
import lk.grocery.platform.dto.OrderDetailDTO;

public interface OrderService {

    Long createGoodsList(OrderDTO orderDTO);

    Boolean addItemToGoodsList(OrderDetailDTO orderDetailDTO);

    Boolean removeItemFromList(Long itemId, String customerCode);

    Boolean clearGoodsList(String customerCode);

    OrderDTO getGoodsListByCustomer(String customerCode);
}
