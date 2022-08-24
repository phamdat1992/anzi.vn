package vn.anzi.dinner.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.anzi.dinner.request.DishInfoRequest;
import vn.anzi.dinner.request.OrderRequest;
import vn.anzi.dinner.response.DishInfoResponse;
import vn.anzi.entities.*;
import vn.anzi.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class DinnerOrderService {
//    @Autowired
//    private TableRepository tableRepository;
//    @Autowired
//    private DishRepository dishRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;
////    @Autowired
////    private DishImageRepository dishImageRepository;
//    @Autowired
//    private ImageRepository imageRepository;
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private OrderDishRepository orderDishRepository;
//
//    public DishInfoResponse getDishedInfo(DishInfoRequest dishInfoRequest) {
//        long dinnerId = dishInfoRequest.getDinnerId();
//        int eateryId = dishInfoRequest.getEateryId();
//
//        //TODO: ? new list?
//        DishInfoResponse dishInfoResponse = new DishInfoResponse();
//        List<CategoryEntity> categoryEntityList = categoryRepository.getAllByFkEateryId(eateryId);
//        for (CategoryEntity cate: categoryEntityList) {
//            int cateId = cate.getId();
//            String cateName = cate.getName();
//            dishInfoResponse.getCategoryId().add(cateId);
//            dishInfoResponse.getCategoryName().add(cateName);
//
//            List<DishEntity> dishEntityList = dishRepository.getAllByFkCateId(cateId);
//            for (DishEntity dishEntity: dishEntityList) {
//                if (dishEntity.getDishStatus() == 1) {//TODO: config status
//                    dishInfoResponse.getDishId().add(dishEntity.getId());
//                    dishInfoResponse.getDishName().add(dishEntity.getName());
//                    dishInfoResponse.getDishImage().add(getImageUrlByDish(dishEntity.getId()));
//                    dishInfoResponse.getDishPrice().add(dishEntity.getPrice());
//                    dishInfoResponse.getDishCate().add(cateId);
//                }
//            }
//        }
//
//        getHistoryOrder(dinnerId, eateryId, dishInfoResponse);
//        return dishInfoResponse;
//    }
//
//    private void getHistoryOrder(long dinnerId, int eateryId, DishInfoResponse dishInfoResponse) {
//
//    }
//
//    private String getImageUrlByDish(Integer dishId) {
////        DishImageEntity dishImageEntity = dishImageRepository.findActiveImageByDishId(dishId);
//        DishImageEntity dishImageEntity = new DishImageEntity();
//        long imageId = dishImageEntity.getFkManagementImage();
//
//        ImageEntity imageEntity = imageRepository.findImageById(imageId);
//        String imageUrl = imageEntity.getFileName();
//        return imageUrl;
//    }
//
//    public ResponseEntity<Void> order(int dinnerId, OrderRequest orderRequest) {
//        int orderType = orderRequest.getOrderType();
//        int tableId = orderRequest.getTableId();
//        int eateryId = orderRequest.getEateryId();
//        ArrayList<Integer> orderDishList = orderRequest.getOrderDishId();
//        ArrayList<Integer> quantityList = orderRequest.getQuantity();
//
//        //TODO: check dish quantity
//        OrderEntity orderEntity = createNewOrderEntity(dinnerId, tableId, orderType);
//        for (int i = 0; i < orderDishList.size(); i++)
//            createNewDishInOrder(orderEntity, orderDishList.get(i), quantityList.get(i));
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .build();
//    }
//
//    private void createNewDishInOrder(OrderEntity orderEntity, int dishId, int quantity) {
//        OrderDishEntity orderDishEntity = new OrderDishEntity();
//        orderDishEntity.setFkManagementOrder(orderEntity.getId());
//        orderDishEntity.setFkManagementDish(dishId);
//        orderDishEntity.setQuantity(quantity);
//        orderEntity.setCreatedTime(new DateTime());
//        orderEntity.setUpdatedTime(orderEntity.getCreatedTime());
//
//        orderDishRepository.save(orderDishEntity);
//    }
//
//    private OrderEntity createNewOrderEntity(int dinnerId, int tableId, int orderType) {
//        OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setFkDiner(dinnerId);
//        orderEntity.setFkManagementTable(tableId);
//        orderEntity.setFkManagementOrderType(orderType);
//        orderEntity.setIsConfirmed(false);
//        orderEntity.setCreatedTime(new DateTime());
//        orderEntity.setUpdatedTime(orderEntity.getCreatedTime());
//
//        orderRepository.save(orderEntity);
//        return orderEntity;
//    }
}
