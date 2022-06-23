package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TRfBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<TRfBranch, Long> {

    TRfBranch findByBrnhIdAndBrnhStatus(Long brnhId, Short brnhStatus);

    List<TRfBranch> findAllByBrnhStatus(Short brnhStatus);
}
