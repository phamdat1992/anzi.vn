package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.DishBucketEntity;
import vn.anzi.modules.management.order.entity.OrderDetailEntity;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    @Query(value = "select management_dish.id as id, " +
            "   management_dish.name as name, " +
            "   management_dish.price as price, " +
            "   management_dish.code as code, " +
            "   management_order_dish.quantity as quantity, " +
            "   management_dish.image as image " +
            "from management_order " +
            "left join management_order_dish on management_order_dish.fk_management_order=management_order.id " +
            "left join management_dish on management_dish.id=management_order_dish.fk_management_dish " +
            "where management_order.id=:id " +
            ";",
            nativeQuery = true
    )
    List<OrderDetailEntity> getOrderDetail(Long id);
}
