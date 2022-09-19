package vn.anzi.modules.management.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.category.entity.CategoryEntity;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> getAllByEateryId(Long cateId);

    @Query(value = "select * " +
            "from management_category " +
            "where fk_management_eatery=:eateryId " +
            "and is_active=1; ",
            nativeQuery = true
    )
    List<CategoryEntity> getAllCategory(Long eateryId);

    @Modifying
    @Query(value = "update management_category " +
            "set is_active=0 " +
            "where id=:id ; ",
            nativeQuery = true
    )
    void deleteCategory(@Param("id")Long id);

    @Modifying
    @Query(value = "update management_category " +
            "set is_active=0 " +
            "where fk_management_eatery=:id ; ",
            nativeQuery = true
    )
    void deleteCategoryByEateryId(@Param("id")Long eateryId);
}
