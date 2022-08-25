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
            "and is_active='1' " +
            "limit 1;",
            nativeQuery = true
    )
    Optional<UserEateryRoleEntity> findByUserEateryId(Long userEateryId);
}
