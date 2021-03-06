package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<TMsUser, Long> {

    TMsUser findByUserUsernameAndUserStatus(String userUsername, Short userStatus);

    TMsUser findByParty_PrtyCodeAndUserStatus(String prtyCode, Short userStatus);

    TMsUser findByUserIdAndUserStatus(Long userId, Short userStatus);

    @Query("SELECT t FROM TMsUser t " +
            "WHERE t.userStatus = :userStatus " +
            "AND (:prtyCode IS NULL OR (:prtyCode IS NOT NULL AND t.party.prtyCode = :prtyCode)) " +
            "AND UPPER(t.userUsername) LIKE CONCAT('%', UPPER(:userUsername), '%') " +
            "ORDER BY t.userId DESC")
    Page<TMsUser> getActiveUsers(@Param("userUsername") String username,
                                 @Param("prtyCode") String partyCode,
                                 @Param("userStatus") Short status,
                                 Pageable pageable);

    List<TMsUser> findAllByUserStatus(Short userStatus);
}
