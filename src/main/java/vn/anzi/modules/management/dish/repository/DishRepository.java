package vn.anzi.modules.management.dish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.anzi.constant.DishStatus;
import vn.anzi.modules.management.dish.entity.DishEntity;
import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {
    @Query(value = "select * " +
            "from management_dish " +
            "where fk_management_eatery=:eateryId " +
            "and fk_management_category=:cateId " +
            "and fk_disk_status=" + DishStatus.AVAILABLE +
            ";",
            nativeQuery = true
    )
    List<DishEntity> getAllByFkCateIdAndEateryId(Long cateId, Long eateryId);

    @Modifying
    @Query(value = "update management_dish " +
            "set is_active=0 " +
            "where fk_management_category=:id ; ",
            nativeQuery = true
    )
    void deleteDishByCategoryId(@Param("id")Long categoryId);
}
