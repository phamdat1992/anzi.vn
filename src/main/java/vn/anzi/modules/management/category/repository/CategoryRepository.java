package vn.anzi.modules.management.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.eatery.entity.EateryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
