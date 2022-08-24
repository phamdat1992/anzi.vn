package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.CategoryEntity;
import vn.anzi.entities.DishEntity;
import vn.anzi.entities.DishImageEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class DishImageRepository extends JpaRepository<DishImageEntity, Integer> {
    DishImageEntity findActiveImageByDishId(Integer dishId); //return image with active relationship
    Optional<DishImageEntity> findByImageId(Integer imageId);
}
