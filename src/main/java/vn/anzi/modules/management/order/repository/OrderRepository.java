package vn.anzi.modules.management.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.order.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findById(Long orderId);

    @Query(value = "select * " +
            "from management_order " +
            "where fk_management_diner=:dinerId " +
            "and fk_management_table=:tableId " +
            ";",
            nativeQuery = true
    )
    List<OrderEntity> getAllByDinerTable(Long dinerId, Long tableId);

    @Modifying
    @Query(value = "update management_order " +
            "set is_confirmed=1 " +
            "where id=:id " +
            "; ",
            nativeQuery = true
    )
    void confirmOrder(Long id);
}
