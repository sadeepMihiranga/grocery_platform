package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TRfDeliveryOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryOptionRepository extends JpaRepository<TRfDeliveryOption, Long> {

    List<TRfDeliveryOption> findByDvopStatus(Short dvopStatus);
}
