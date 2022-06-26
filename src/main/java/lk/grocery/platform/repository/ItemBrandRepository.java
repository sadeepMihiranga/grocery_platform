package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TRfItemBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemBrandRepository extends JpaRepository<TRfItemBrand, Long> {

    List<TRfItemBrand> findByItbdStatus(Short itbdStatus);
}
