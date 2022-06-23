package lk.grocery.platform.repository;

import lk.grocery.platform.entity.TMsFunction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionRepository extends JpaRepository<TMsFunction, String> {
}
