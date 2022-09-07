package vn.anzi.modules.diner.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.diner.dto.*;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.category.repository.CategoryRepository;
import vn.anzi.modules.management.dish.entity.DishEntity;
import vn.anzi.modules.management.dish.repository.DishRepository;
import vn.anzi.modules.management.image.reporitory.DishImageRepository;
import vn.anzi.modules.management.image.reporitory.ImageRepository;
import vn.anzi.modules.management.order.entity.OrderDishEntity;
import vn.anzi.modules.management.order.entity.OrderEntity;
import vn.anzi.modules.management.order.repository.OrderDishRepository;
import vn.anzi.modules.management.order.repository.OrderRepository;
import vn.anzi.modules.management.table.repository.TableRepository;

import java.util.ArrayList;
import java.util.List;

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
    private ImageRepository imageRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDishRepository orderDishRepository;

    public DishInfoResponse getDishedInfo(DishInfoRequest dishInfoRequest) {
        Long dinerId = 1L;
        Long eateryId = dishInfoRequest.getEateryId();

        DishInfoResponse dishInfoResponse = new DishInfoResponse();
        List<CategoryEntity> categoryEntityList = categoryRepository.getAllByEateryId(eateryId);
        for (CategoryEntity cate: categoryEntityList) {
            Long cateId = cate.getId();
            String cateName = cate.getName();
            dishInfoResponse.getCategoryId().add(cateId);
            dishInfoResponse.getCategoryName().add(cateName);

            List<DishEntity> dishEntityList = dishRepository.getAllByFkCateIdAndEateryId(cateId, eateryId);
            for (DishEntity dishEntity: dishEntityList) {
                dishInfoResponse.getDishId().add(dishEntity.getId());
                dishInfoResponse.getDishName().add(dishEntity.getName());
                dishInfoResponse.getDishImage().add(getImageUrlByDish(dishEntity.getId()));
                dishInfoResponse.getDishPrice().add(dishEntity.getPrice());
                dishInfoResponse.getDishCate().add(cateId);
            }
        }

        getHistoryOrder(dinerId, eateryId, dishInfoResponse);
        return dishInfoResponse;
    }

    private void getHistoryOrder(Long dinerId, long eateryId, DishInfoResponse dishInfoResponse) {
        //TODO: history order
    }

    private String getImageUrlByDish(long dishId) {
//        DishImageEntity dishImageEntity = dishImageRepository.findActiveImageByDishId(dishId);
//        DishImageEntity dishImageEntity = new DishImageEntity();
//        long imageId = dishImageEntity.getFkManagementImage();
//
//        ImageEntity imageEntity = imageRepository.findImageById(imageId);
//        String imageUrl = imageEntity.getFileName();
//        return imageUrl;
        return "imageUrl " + dishId;
    }

    @Transactional(rollbackFor = Exception.class)
    public void order(OrderRequest orderRequest) {
        int dinerId = orderRequest.getDinerId();
        int orderType = orderRequest.getOrderType();
        int tableId = orderRequest.getTableId();
        int eateryId = orderRequest.getEateryId();
        ArrayList<Integer> orderDishList = orderRequest.getOrderDishId();
        ArrayList<Integer> quantityList = orderRequest.getQuantity();

        //TODO: check dish quantity
        OrderEntity orderEntity = createNewOrderEntity(dinerId, tableId, orderType);
        for (int i = 0; i < orderDishList.size(); i++)
            createNewDishInOrder(orderEntity, orderDishList.get(i), quantityList.get(i));
    }

    private void createNewDishInOrder(OrderEntity orderEntity, int dishId, int quantity) {
        OrderDishEntity orderDishEntity = new OrderDishEntity();
        orderDishEntity.setFkManagementOrder(orderEntity.getId());
        orderDishEntity.setFkManagementDish(dishId);
        orderDishEntity.setQuantity(quantity);
        orderEntity.setCreatedTime(new DateTime());
        orderEntity.setUpdatedTime(orderEntity.getCreatedTime());

        orderDishRepository.save(orderDishEntity);
    }

    private OrderEntity createNewOrderEntity(int dinerId, int tableId, int orderType) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setFkDiner(dinerId);
        orderEntity.setFkManagementTable(tableId);
        orderEntity.setFkManagementOrderType(orderType);
        orderEntity.setIsConfirmed(false);
        orderEntity.setCreatedTime(new DateTime());
        orderEntity.setUpdatedTime(orderEntity.getCreatedTime());

        orderRepository.save(orderEntity);
        return orderEntity;
    }

    //TODO: One diner can have more 1 order or not?
    public DishHistoryResponse getDishHistory(DishHistoryRequest dishHistoryRequest) {
        int dinerId = dishHistoryRequest.getDinerId();
        int tableId = dishHistoryRequest.getTableId();
        List<OrderEntity> orderList = orderRepository.getAllByDinerTable(dinerId, tableId);

        DishHistoryResponse dishHistoryResponse = new DishHistoryResponse();
        for (OrderEntity orderEntity: orderList) {
            List<OrderDishEntity> orderDishEntityList =  orderDishRepository.getAllByFkManagementOrder(orderEntity.getId());
            for (OrderDishEntity orderDishEntity: orderDishEntityList) {
                dishHistoryResponse.getDishId().add(orderDishEntity.getFkManagementDish());
                dishHistoryResponse.getQuantity().add(orderDishEntity.getQuantity());
                dishHistoryResponse.getStatus().add(orderEntity.getIsConfirmed());
            }
        }

        return new DishHistoryResponse();
    }
}
