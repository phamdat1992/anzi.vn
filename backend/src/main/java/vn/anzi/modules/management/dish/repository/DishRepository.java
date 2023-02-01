package vn.anzi.modules.management.dish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.dish.entity.DishEntity;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {
    @Query(value = "select * " +
            "from management_dish " +
            "where fk_management_eatery=:eateryId " +
            "and fk_management_category=:cateId " +
            "and is_active=1 ; ",
            nativeQuery = true
    )
    List<DishEntity> getAllByFkCateIdAndEateryId(Long cateId, Long eateryId);

    @Modifying
    @Query(value = "update management_dish " +
            "set is_active=0 " +
            "where fk_management_category=:id ; ",
            nativeQuery = true
    )
    void deleteDishByCategoryId(@Param("id") Long categoryId);

    @Query(value = "select * " +
            "from management_dish " +
            "where fk_management_eatery=:eateryId " +
            "and is_active=1 ; ",
            nativeQuery = true
    )
    List<DishEntity> getAllDishByEateryId(Long eateryId);

    @Modifying
    @Query(value = "update management_dish " +
            "set name=:name , price=:price , code=:code, fk_management_eatery=:eateryId , fk_management_category=:categoryId , fk_management_dish_status=:status " +
            "where id=:id ; ",
            nativeQuery = true
    )
    void updateDishWithoutImage(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("price") Long price,
            @Param("code") String code,
            @Param("eateryId") Long eateryId,
            @Param("categoryId") Long categoryId,
            @Param("status") Long status
    );

    @Modifying
    @Query(value = "update management_dish " +
            "set is_active=0 " +
            "where id=:id ; ",
            nativeQuery = true
    )
    void deleteDishById(@Param("id") Long id);

    @Modifying
    @Query(value = "update management_dish " +
            "set is_active=0 " +
            "where fk_management_eatery=:id ; ",
            nativeQuery = true
    )
    void deleteDishByEateryId(@Param("id") Long eateryId);
}
