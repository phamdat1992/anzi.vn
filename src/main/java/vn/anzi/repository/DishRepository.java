//package vn.anzi.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import vn.anzi.entities.DinerEntity;
//import vn.anzi.entities.DishEntity;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface DishRepository extends JpaRepository<DishEntity, Long> {
//    Optional<DishEntity> findById(Long dishId);
//    List<DishEntity> getAllByFkEateryId(Integer eateryId);
//    List<DishEntity> getAllByFkCateId(Integer cateId);
//}
