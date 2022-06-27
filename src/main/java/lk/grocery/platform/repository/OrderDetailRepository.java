package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<TMsOrderDetail, Long> {

    List<TMsOrderDetail> findByOrder_OderIdAndItem_ItemIdAndOddtStatus(Long oderId, Long itemId, Short oddtStatus);

    List<TMsOrderDetail> findByOrder_OderIdAndOddtStatus(Long oderId, Short oddtStatus);
}
