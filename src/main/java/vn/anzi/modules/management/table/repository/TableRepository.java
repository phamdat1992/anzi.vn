package vn.anzi.modules.management.table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
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

    @Modifying
    @Query(value = "update management_table " +
            "set name=:name , location=:location " +
            "where id=:id ; ",
            nativeQuery = true
    )
    void updateTable(@Param("id")Long id, @Param("name")String name, @Param("location")String location);

    @Modifying
    @Query(value = "update management_table " +
            "set is_active=0 " +
            "where id=:id ; ",
            nativeQuery = true
    )
    void deleteTable(@Param("id")Long id);
}
