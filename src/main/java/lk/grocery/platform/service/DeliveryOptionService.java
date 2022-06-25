package lk.grocery.platform.service;

import lk.grocery.platform.dto.DeliveryOptionDTO;

import java.util.List;

public interface DeliveryOptionService {

    List<DeliveryOptionDTO> getDeliveryOptions();
}
