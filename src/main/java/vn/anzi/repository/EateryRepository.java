package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.EateryEntity;

@Repository
public class EateryRepository extends JpaRepository<EateryEntity, Integer> {
    EateryEntity getById(Integer id);
}
