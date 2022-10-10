package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.OrderEventEntity;

import java.util.Optional;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Integer> {
    Optional<OrderEventEntity> findById(Long orderId);
}
