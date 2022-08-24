package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.DinnerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class DinnerRepository extends JpaRepository<DinnerEntity, String> {
    Optional<DinnerEntity> findByAuthenName(String authenName);
}
