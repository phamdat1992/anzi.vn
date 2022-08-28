package vn.anzi.modules.diner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.DishImageEntity;

@Repository
public interface DishImageRepository extends JpaRepository<DishImageEntity, Integer> {
}
