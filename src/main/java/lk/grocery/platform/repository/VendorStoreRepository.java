package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsVendorStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorStoreRepository extends JpaRepository<TMsVendorStore, Long> {

    TMsVendorStore findByVendor_PrtyCodeAndVnstStatus(String prtyCode, Short vnstStatus);

    List<TMsVendorStore> findByStore_StorIdAndVnstStatus(Long storId, Short vnstStatus);
}
