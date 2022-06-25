package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TRfPaymentOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentOptionRepository extends JpaRepository<TRfPaymentOption, Long> {

    List<TRfPaymentOption> findByPyopStatus(Short pyopStatus);
}
