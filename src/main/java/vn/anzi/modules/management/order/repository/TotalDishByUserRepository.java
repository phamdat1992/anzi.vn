package vn.anzi.modules.management.order.repository;

import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.OrderEntity;
import vn.anzi.modules.management.order.entity.TotalDishByUserEntity;

import java.util.List;

@Repository
public interface TotalDishByUserRepository extends JpaRepository<TotalDishByUserEntity, Integer> {
    @Query(value = "select sum(management_order_dish.quantity) as quantity " +
            "from management_order " +
            "left join management_order_dish on management_order.id=management_order_dish.fk_management_order " +
            "where management_order.fk_diner=:dinerId " +
            "and management_order.fk_management_table=:tableId " +
            "and management_order.fk_management_order_type=1 " +
            ";",
            nativeQuery = true
    )
    TotalDishByUserEntity  getTotalDishByUser(Long dinerId, Long tableId);
}
