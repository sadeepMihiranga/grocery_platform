package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<TMsOrder, Long> {

    List<TMsOrder> findByCustomer_PrtyCode(String prtyCode);
}
