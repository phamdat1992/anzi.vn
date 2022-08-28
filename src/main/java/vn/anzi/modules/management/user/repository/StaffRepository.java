package vn.anzi.modules.management.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.user.entity.StaffEntity;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    @Query(value = "select management_user_eatery.id as id, " +
            "   manager_user.email as email, " +
            "   management_user_eatery.name as name, " +
            "   management_user_eatery_role.fk_management_role as fk_management_role " +
            "from management_user_eatery " +
            "left join management_user  on manager_user.id=management_user_eatery.fk_management_user " +
            "left join management_user_eatery_role on management_user_eatery_role.fk_management_user_eatery=management_user_eatery.id" +
            "where management_user_eatery.fk_management_eatery=:eateryId " +
            "and management_user_eatery_role.is_active=1 " +
            "and management_user_eatery.is_active=1 ; ",
            nativeQuery = true
    )
    List<StaffEntity> getAllStaffByEateryId(Long eateryId);
}
