package vn.anzi.modules.management.eatery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.eatery.entity.UserEateryEntity;

import java.util.Optional;

@Repository
public interface UserEateryRepository extends JpaRepository<UserEateryEntity, Long> {
    @Modifying
    @Query(value = "update management_user_eatery " +
            "set is_active=0 " +
            "where is_active=1 " +
            "and id=:id ; ",
            nativeQuery = true
    )
    void removeUserByUserId(@Param("id")Long id);

    @Query(value = "select * " +
            "from management_user_eatery " +
            "where is_active=1 " +
            "and fk_management_eatery=:eateryId " +
            "and fk_management_user=:userId; ",
            nativeQuery = true
    )
    Optional<UserEateryEntity> findByUserIdAndEateryId(Long userId, Long eateryId);

    @Modifying
    @Query(value = "update management_user_eatery " +
            "set name=:name , fk_management_role=:roleId " +
            "where id=:id ; ",
            nativeQuery = true
    )
    void updateUser(Long id, String name, Long roleId);
}
