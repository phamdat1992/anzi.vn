package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.OrderDishEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDishEntity, Integer> {
    Optional<OrderDishEntity> findById(int orderId);
    List<OrderDishEntity> getAllByFkManagementOrder(int orderId);
}
