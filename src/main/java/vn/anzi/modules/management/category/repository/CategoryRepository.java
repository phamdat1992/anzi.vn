package vn.anzi.modules.management.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.DishEntity;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.eatery.entity.EateryEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> getAllByEateryId(long cateId);
}
