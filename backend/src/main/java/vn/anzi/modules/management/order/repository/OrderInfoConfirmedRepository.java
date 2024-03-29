package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.OrderInfoConfirmedEntity;

import java.util.List;

@Repository
public interface OrderInfoConfirmedRepository extends JpaRepository<OrderInfoConfirmedEntity, Integer> {
    @Query(value = "select management_table.name as name, " +
            "   management_table.location as location, " +
            "   management_order.id as id, " +
            "   management_order.created_time as created_time, " +
            "   sum(management_order_dish.quantity) as total_dish, " +
            "   sum(management_order_dish.quantity*management_dish.price) as total_price " +
            "from management_order " +
            "left join management_table on management_table.id=management_order.fk_management_table " +
            "left join management_order_dish on management_order.id=management_order_dish.fk_management_order " +
            "left join management_dish on management_order_dish.fk_management_dish=management_dish.id " +
            "where management_order.fk_management_order_type=1 " +
            "and management_order.id<:orderId " +
            "and management_table.fk_management_eatery=:eateryId " +
            "and management_order.is_confirmed=1 " +
            "group by management_order.id " +
            "order by management_order.id DESC " +
            "limit 10 " +
            ";",
            nativeQuery = true
    )
    List<OrderInfoConfirmedEntity> getOrderFromOffset(Long eateryId, Long orderId);
}
