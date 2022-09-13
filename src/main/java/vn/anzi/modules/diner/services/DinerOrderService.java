package vn.anzi.modules.diner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.diner.dto.DishInfoResponseDTO;
import vn.anzi.modules.diner.dto.OrderItemDTO;
import vn.anzi.modules.diner.dto.OrderRequestDTO;
import vn.anzi.modules.management.category.repository.CategoryRepository;
import vn.anzi.modules.management.dish.repository.DishRepository;
import vn.anzi.modules.management.image.reporitory.DishImageRepository;
import vn.anzi.modules.management.image.reporitory.ImageRepository;
import vn.anzi.modules.management.order.entity.OrderDishEntity;
import vn.anzi.modules.management.order.entity.OrderEntity;
import vn.anzi.modules.management.order.repository.OrderDishRepository;
import vn.anzi.modules.management.order.repository.OrderRepository;
import vn.anzi.modules.management.table.repository.TableRepository;

@Service
public class DinerOrderService {
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DishImageRepository dishImageRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDishRepository orderDishRepository;

    public void createListOrder(OrderRequestDTO orderRequest, OrderEntity orderEntity) {
        for (OrderItemDTO order: orderRequest.getDishInfo()) {
            createNewDishInOrderEntity(orderEntity, order);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void createNewDishInOrderEntity(OrderEntity orderEntity, OrderItemDTO order) {
        OrderDishEntity orderDishEntity = new OrderDishEntity();
        orderDishEntity.setOrderId(orderEntity.getId());
        orderDishEntity.setDishId(order.getDishId());
        orderDishEntity.setQuantity(order.getQuantity());

        orderDishRepository.save(orderDishEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public OrderEntity createNewOrderEntity(OrderRequestDTO orderRequest, Long userId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDinerId(userId);
        orderEntity.setOrderTypeId(orderRequest.getOrderTypeId());
        orderEntity.setIsConfirmed(false);
        orderEntity.setTableId(orderRequest.getTableId());

        return orderRepository.save(orderEntity);
    }
}
