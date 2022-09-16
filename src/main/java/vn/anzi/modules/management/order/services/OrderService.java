package vn.anzi.modules.management.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.order.entity.*;
import vn.anzi.modules.management.order.repository.*;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private TotalDishByUserRepository totalDishByUserRepository;

    @Autowired
    private DishBucketRepository dishBucketRepository;

    @Autowired
    private OrderInfoNotConfirmRepository orderInfoNotConfirmRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderInfoConfirmedRepository orderInfoConfirmedRepository;

    @Autowired
    private OrderEventRepository orderEventRepository;

    public Long getTotalDishByUser(Long userId, Long tableId) {
        try {
            return totalDishByUserRepository.getTotalDishByUser(userId, tableId).getQuantity();
        } catch (Exception e) {
            return 0L;
        }
    }

    public List<OrderInfoNotConfirmEntity>getOrderFromOffset(Long eateryId, Long tableId) {
        return orderInfoNotConfirmRepository.getOrderFromOffset(eateryId, tableId);
    }

    public List<OrderInfoNotConfirmEntity>getOrderHostessFromOffset(Long eateryId, Long orderId) {
        return orderInfoNotConfirmRepository.getOrderHostessFromOffset(eateryId, orderId);
    }

    public List<DishBucketEntity> getAllDishBucketByTableId(Long dinerId, Long orderId) {
        return dishBucketRepository.getAllDishBucketByTableId(dinerId, orderId);
    }

    public List<OrderDetailEntity> getOrderDetail(Long orderId) {
        return orderDetailRepository.getOrderDetail(orderId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmOrder(Long orderId) {
        orderRepository.confirmOrder(orderId);
    }

    public List<OrderInfoConfirmedEntity> getOrderConfirmedByOffset(Long eateryId, Long orderId) {
        return orderInfoConfirmedRepository.getOrderFromOffset(eateryId, orderId);
    }

    public OrderEventEntity getOrderByOrderId(Long orderId) {
        return orderEventRepository.findById(orderId).orElse(null);
    }
}
