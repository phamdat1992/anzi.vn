package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.TableEntity;

@Repository
public class TableRepository extends JpaRepository<TableEntity, Long> {
    TableEntity getById(Long id);
}
