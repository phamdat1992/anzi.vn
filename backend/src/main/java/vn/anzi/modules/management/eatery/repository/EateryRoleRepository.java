package vn.anzi.modules.management.eatery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.eatery.entity.EateryRoleEntity;

import java.util.List;

@Repository
public interface EateryRoleRepository extends JpaRepository<EateryRoleEntity, Long> {
    @Query(value = "select " +
            "   management_eatery.id as id, " +
            "   management_eatery.name as name, " +
            "   management_eatery.address as address, " +
            "   management_user_eatery.fk_management_role as role_id " +
            "from management_user_eatery " +
            "left join management_eatery on management_eatery.id = management_user_eatery.fk_management_eatery " +
            "where management_user_eatery.fk_management_user = :userId " +
            "   and (management_user_eatery.is_active = 1); ",
            nativeQuery = true
    )
    List<EateryRoleEntity> getAllEateryByUserId(Long userId);
}
