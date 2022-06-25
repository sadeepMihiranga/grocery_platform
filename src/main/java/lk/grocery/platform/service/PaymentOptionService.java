package lk.grocery.platform.service;

import lk.grocery.platform.dto.PaymentOptionDTO;

import java.util.List;

public interface PaymentOptionService {

    List<PaymentOptionDTO> getPaymentOptions();
}
