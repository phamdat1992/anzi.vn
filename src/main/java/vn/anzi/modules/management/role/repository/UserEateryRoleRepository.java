package vn.anzi.modules.management.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.role.entity.UserEateryRoleEntity;
import vn.anzi.modules.management.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEateryRoleRepository extends JpaRepository<UserEateryRoleEntity, Long> {
    @Query(value = "select * " +
            "from management_user_eatery_role " +
            "where fk_management_user_eatery=:userEateryId " +
            "and is_active=1 " +
            "limit 1;",
            nativeQuery = true
    )
    Optional<UserEateryRoleEntity> findByUserEateryId(Long userEateryId);

    @Query(value = "select  " +
            "   management_user_eatery_role.id as id, " +
            "   management_user_eatery_role.fk_management_user_eatery as fk_management_user_eatery," +
            "   management_user_eatery_role.fk_management_role as fk_management_role" +
            "   management_user_eatery_role.is_active as is_active " +
            "from management_user " +
            "left join management_user_eatery on management_user_eatery.fk_management_user=management_user.id " +
            "left join management_eatery on management_user_eatery.fk_management_eatery=management_eatery.id " +
            "left join management_user_eatery_role on management_user_eatery_role.fk_management_user_eatery=management_user_eatery.id" +
            "where management_user.id=:userId and management_eatery.id=:eateryId " +
            "and management_user_eatery_role.is_active=1 " +
            "limit 1;",
            nativeQuery = true
    )
    Optional<UserEateryRoleEntity> findByUserIdAndEateryId(Long userId, Long eateryId);
}
