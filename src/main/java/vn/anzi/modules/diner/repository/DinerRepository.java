package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.diner.entity.DinerEntity;
import java.util.Optional;

@Repository
public interface DinerRepository extends JpaRepository<DinerEntity, Integer> {
    Optional<DinerEntity> findByAuthenName(String authenName);
}
