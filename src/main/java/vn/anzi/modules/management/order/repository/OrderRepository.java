package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findById(int orderId);

    @Query(value = "select * " +
            "from management_order " +
            "where fk_management_diner=:dinerId " +
            "and fk_management_table=:tableId " +
            ";",
            nativeQuery = true
    )
    List<OrderEntity> getAllByDinerTable(int dinerId, int tableId);
}
