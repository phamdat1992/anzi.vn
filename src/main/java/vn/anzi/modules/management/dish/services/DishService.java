package vn.anzi.modules.management.dish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.dish.dto.NewDishRequestDTO;
import vn.anzi.modules.management.dish.dto.UpdateDishRequestDTO;
import vn.anzi.modules.management.dish.entity.DishEntity;
import vn.anzi.modules.management.dish.repository.DishRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @Transactional(rollbackFor = Exception.class)
    public void deleteDishByCategoryId(Long categoryId) {
        dishRepository.deleteDishByCategoryId(categoryId);
    }

    public List<DishEntity> getAllDishByEateryId(Long eateryId) {
        return dishRepository.getAllDishByEateryId(eateryId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createNewDish(NewDishRequestDTO dish, String imageName) {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setCode(dish.getCode());
        dishEntity.setCategoryId(dish.getCategoryId());
        dishEntity.setEateryId(dish.getEateryId());
        dishEntity.setName(dish.getName());
        dishEntity.setPrice(dish.getPrice());
        dishEntity.setDishStatusId((long) (dish.getStatus()? 1 : 2));
        dishEntity.setImage(imageName);

        dishRepository.save(dishEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateDish(UpdateDishRequestDTO dish, String imageName) {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setCode(dish.getCode());
        dishEntity.setCategoryId(dish.getCategoryId());
        dishEntity.setEateryId(dish.getEateryId());
        dishEntity.setName(dish.getName());
        dishEntity.setPrice(dish.getPrice());
        dishEntity.setDishStatusId((long) (dish.getStatus()? 1 : 2));
        dishEntity.setImage(imageName);
        dishEntity.setId(dish.getId());

        dishRepository.save(dishEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateDishWithoutImage(UpdateDishRequestDTO dish) {
        dishRepository.updateDishWithoutImage(
            dish.getId(),
            dish.getName(),
            dish.getPrice(),
            dish.getCode(),
            dish.getEateryId(),
            dish.getCategoryId(),
            dish.getStatus()? 1L : 2L
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteDishById(Long id) {
        dishRepository.deleteDishById(id);
    }
}
