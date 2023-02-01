package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.DishBucketEntity;

import java.util.List;

@Repository
public interface DishBucketRepository extends JpaRepository<DishBucketEntity, Integer> {
    @Query(value = "select management_order_dish.id as id, " +
            "   management_dish.name as name, " +
            "   management_dish.price as price, " +
            "   management_order.is_confirmed as status, " +
            "   management_order_dish.quantity as quantity, " +
            "   management_dish.image as image " +
            "from management_order_dish " +
            "left join management_order on management_order_dish.fk_management_order=management_order.id " +
            "left join management_dish on management_dish.id=management_order_dish.fk_management_dish " +
            "where management_order.fk_diner=:dinerId " +
            "and management_order.fk_management_table=:tableId " +
            ";",
            nativeQuery = true
    )
    List<DishBucketEntity> getAllDishBucketByTableId(Long dinerId, Long tableId);
}
