package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.DinnerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DinnerRepository extends JpaRepository<DinnerEntity, Integer> {
    Optional<DinnerEntity> findByAuthenName(String authenName);
}
