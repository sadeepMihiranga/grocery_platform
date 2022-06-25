package lk.grocery.platform.service.impl;

import lk.grocery.platform.dto.PaymentOptionDTO;
import lk.grocery.platform.entity.TRfPaymentOption;
import lk.grocery.platform.mapper.PaymentOptionMapper;
import lk.grocery.platform.repository.PaymentOptionRepository;
import lk.grocery.platform.service.PaymentOptionService;
import lk.grocery.platform.util.constant.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentOptionServiceImpl implements PaymentOptionService {

    private final PaymentOptionRepository paymentOptionRepository;

    public PaymentOptionServiceImpl(PaymentOptionRepository paymentOptionRepository) {
        this.paymentOptionRepository = paymentOptionRepository;
    }

    @Override
    public List<PaymentOptionDTO> getPaymentOptions() {

        List<PaymentOptionDTO> paymentOptionDTOList = new ArrayList<>();

        List<TRfPaymentOption> tRfPaymentOptionList = paymentOptionRepository.findByPyopStatus(Constants.STATUS_ACTIVE.getShortValue());

        for(TRfPaymentOption tRfPaymentOption : tRfPaymentOptionList) {
            paymentOptionDTOList.add(PaymentOptionMapper.INSTANCE.entityToDTO(tRfPaymentOption));
        }

        return paymentOptionDTOList;
    }
}
