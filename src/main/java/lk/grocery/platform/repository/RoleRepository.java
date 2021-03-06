package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<TMsRole, Long> {

    TMsRole findByRoleNameAndRoleStatus(String roleName, Short roleStatus);
}
