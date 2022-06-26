package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<TMsOrderDetail, Long> {
}
