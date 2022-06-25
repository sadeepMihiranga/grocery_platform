package lk.grocery.platform.service.impl;

import lk.grocery.platform.dto.DeliveryOptionDTO;
import lk.grocery.platform.entity.TRfDeliveryOption;
import lk.grocery.platform.mapper.DeliveryOptionMapper;
import lk.grocery.platform.repository.DeliveryOptionRepository;
import lk.grocery.platform.service.DeliveryOptionService;
import lk.grocery.platform.util.constant.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryOptionServiceImpl implements DeliveryOptionService {

    private final DeliveryOptionRepository deliveryOptionRepository;

    public DeliveryOptionServiceImpl(DeliveryOptionRepository deliveryOptionRepository) {
        this.deliveryOptionRepository = deliveryOptionRepository;
    }

    @Override
    public List<DeliveryOptionDTO> getDeliveryOptions() {

        List<DeliveryOptionDTO> deliveryOptionDTOList = new ArrayList<>();

        List<TRfDeliveryOption> tRfDeliveryOptionList = deliveryOptionRepository.findByDvopStatus(Constants.STATUS_ACTIVE.getShortValue());

        for(TRfDeliveryOption tRfDeliveryOption: tRfDeliveryOptionList) {
            deliveryOptionDTOList.add(DeliveryOptionMapper.INSTANCE.entityToDTO(tRfDeliveryOption));
        }

        return deliveryOptionDTOList;
    }
}
