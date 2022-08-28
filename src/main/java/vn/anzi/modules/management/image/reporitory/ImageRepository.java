package vn.anzi.modules.management.image.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.modules.management.image.entity.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    ImageEntity findImageById(Long imageId);
}
