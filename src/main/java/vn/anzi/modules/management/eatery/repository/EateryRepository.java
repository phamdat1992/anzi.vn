package vn.anzi.modules.management.eatery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.eatery.entity.EateryEntity;

@Repository
public interface EateryRepository extends JpaRepository<EateryEntity, Long> {
    @Modifying
    @Query(value = "update management_eatery " +
            "set is_active=0 " +
            "where id=:id ; ",
            nativeQuery = true
    )
    void deleteEateryById(@Param("id")Long eateryId);
}
