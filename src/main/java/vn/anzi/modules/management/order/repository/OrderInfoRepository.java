package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.OrderDishEntity;
import vn.anzi.modules.management.order.entity.OrderInfoEntity;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Integer> {
}
