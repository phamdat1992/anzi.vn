package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.OrderDishEntity;
import vn.anzi.modules.management.order.entity.OrderEventEntity;
import vn.anzi.modules.management.order.entity.OrderInfoConfirmedEntity;

import java.util.Optional;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Integer> {
    Optional<OrderEventEntity> findById(Long orderId);
}
