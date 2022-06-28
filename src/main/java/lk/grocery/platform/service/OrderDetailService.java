package lk.grocery.platform.service;

import lk.grocery.platform.dto.OrderDTO;
import lk.grocery.platform.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {

    Boolean addItemsToGoodsList(Long orderId, List<OrderDetailDTO> orderDetailDTOList);

    Boolean removeItemFromList(Long orderId, Long itemId);

    Boolean removeItemsFromList(Long orderId);

    List<OrderDetailDTO> getGoodsList(Long orderId);
}
