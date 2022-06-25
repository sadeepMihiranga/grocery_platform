package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<TMsStore, Long> {

    TMsStore findByStorIdAndStorStatus(Long storId, Short storStatus);

    @Query("SELECT t FROM TMsStore t " +
            "WHERE t.storStatus = :status " +
            "AND UPPER(t.storName) LIKE CONCAT('%', UPPER(:name), '%') " +
            "AND UPPER(t.storRegNo) LIKE CONCAT('%', UPPER(:regNo), '%') " +
            "ORDER BY t.storId DESC")
    Page<TMsStore> searchStores(@Param("name") String name,
                                   @Param("regNo") String regNo,
                                   @Param("status") Short status,
                                   Pageable pageable);
}
