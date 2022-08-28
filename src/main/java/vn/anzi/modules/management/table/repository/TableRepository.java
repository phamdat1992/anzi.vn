package vn.anzi.modules.management.table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.eatery.entity.EateryEntity;
import vn.anzi.modules.management.table.entity.TableEntity;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
    @Query(value = "select * " +
            "from management_table " +
            "where fk_management_eatery=:eateryId " +
            "and is_active=1; ",
            nativeQuery = true
    )
    List<TableEntity> getAllTableByEateryId(Long eateryId);
}
