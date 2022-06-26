package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<TMsOrder, Long> {
}
