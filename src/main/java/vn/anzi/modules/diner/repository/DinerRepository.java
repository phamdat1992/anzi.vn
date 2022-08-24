package vn.anzi.modules.diner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.diner.entity.DinerEntity;

@Repository
public interface DinerRepository extends JpaRepository<DinerEntity, Long> {
}
