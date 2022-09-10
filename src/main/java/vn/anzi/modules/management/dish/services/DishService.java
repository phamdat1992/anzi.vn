package vn.anzi.modules.management.dish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.dish.repository.DishRepository;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @Transactional(rollbackFor = Exception.class)
    public void deleteDishByCategoryId(Long categoryId) {
        dishRepository.deleteDishByCategoryId(categoryId);
    }
}
