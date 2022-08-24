package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.DinnerEntity;
import vn.anzi.entities.DishEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class DishRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findById(Long dishId);
    List<DishEntity> getAllByFkEatery(Long eateryId);
    List<DishEntity> getAllByFkCate(Long cateId);
}
