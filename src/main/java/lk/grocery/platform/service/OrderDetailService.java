package lk.grocery.platform.service;

import lk.grocery.platform.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {

    Boolean addItemsToGoodsList(Long orderId, List<OrderDetailDTO> orderDetailDTOList);
}
