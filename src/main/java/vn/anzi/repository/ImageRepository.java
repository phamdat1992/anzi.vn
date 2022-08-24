package vn.anzi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.anzi.entities.DishImageEntity;
import vn.anzi.entities.ImageEntity;

import java.util.Optional;

@Repository
public class ImageRepository extends JpaRepository<ImageEntity, Integer> {
    ImageEntity findImageById(Integer imageId);
}
