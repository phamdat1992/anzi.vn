package vn.anzi.modules.management.image.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.image.entity.DishImageEntity;

@Repository
public interface DishImageRepository extends JpaRepository<DishImageEntity, Integer> {
}
