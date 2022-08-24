package vn.anzi.modules.management.eatery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.eatery.entity.EateryEntity;


@Repository
public interface EateryRepository extends JpaRepository<EateryEntity, Long> {
}
