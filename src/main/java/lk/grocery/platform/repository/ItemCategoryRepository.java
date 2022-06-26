package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TRfItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCategoryRepository extends JpaRepository<TRfItemCategory, Long> {

    List<TRfItemCategory> findByItctStatus(Short itctStatus);

    List<TRfItemCategory> findByItctStatusAndItctCategoryType(Short itctStatus, String itctCategoryType);
}
