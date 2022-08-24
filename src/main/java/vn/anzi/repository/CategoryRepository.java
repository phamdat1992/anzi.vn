package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.CategoryEntity;
import vn.anzi.entities.DinnerEntity;
import vn.anzi.entities.DishEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findById(Integer cateId);
    List<CategoryEntity> getAllByFkEatery(Long eateryId);
}
