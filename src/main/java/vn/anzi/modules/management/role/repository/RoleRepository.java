package vn.anzi.modules.management.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.role.entity.RoleEntity;
import vn.anzi.modules.management.role.entity.UserEateryRoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
