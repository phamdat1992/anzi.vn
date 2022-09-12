package vn.anzi.modules.management.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.anzi.modules.management.order.entity.DishBucketEntity;
import vn.anzi.modules.management.order.repository.DishBucketRepository;
import vn.anzi.modules.management.order.repository.TotalDishByUserRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private TotalDishByUserRepository totalDishByUserRepository;

    @Autowired
    private DishBucketRepository dishBucketRepository;

    public Long getTotalDishByUser(Long userId, Long tableId) {
        return totalDishByUserRepository.getTotalDishByUser(userId, tableId).getQuantity();
    }

    public List<DishBucketEntity> getAllDishBucketByTableId(Long dinerId, Long tableId) {
        return dishBucketRepository.getAllDishBucketByTableId(dinerId, tableId);
    }
}
