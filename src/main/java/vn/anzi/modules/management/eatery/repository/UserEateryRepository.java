package vn.anzi.modules.management.eatery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.eatery.entity.EateryEntity;
import vn.anzi.modules.management.eatery.entity.UserEateryEntity;
import vn.anzi.modules.management.role.entity.UserEateryRoleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEateryRepository extends JpaRepository<UserEateryEntity, Long> {
}
