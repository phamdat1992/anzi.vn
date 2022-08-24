package vn.anzi.dinner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.anzi.dinner.request.DishInfoRequest;
import vn.anzi.dinner.request.OrderRequest;
import vn.anzi.dinner.response.DishInfoResponse;
import vn.anzi.entities.*;
import vn.anzi.repository.*;

import java.util.List;

@Service
public class DinnerOrderService {
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

    public DishInfoResponse getDishedInfo(DishInfoRequest dishInfoRequest) {
        long dinnerId = dishInfoRequest.getDinnerId();
        int eateryId = dishInfoRequest.getEateryId();

        //TODO: ? new list?
        DishInfoResponse dishInfoResponse = new DishInfoResponse();
        List<CategoryEntity> categoryEntityList = categoryRepository.getAllByFkEatery(eateryId);
        for (CategoryEntity cate: categoryEntityList) {
            int cateId = cate.getId();
            String cateName = cate.getName();
            dishInfoResponse.getCategoryId().add(cateId);
            dishInfoResponse.getCategoryName().add(cateName);

            List<DishEntity> dishEntityList = dishRepository.getAllByFkCate(cateId);
            for (DishEntity dishEntity: dishEntityList) {
                if (dishEntity.getDishStatus() == 1) {//TODO: config status
                    dishInfoResponse.getDishId().add(dishEntity.getId());
                    dishInfoResponse.getDishName().add(dishEntity.getName());
                    dishInfoResponse.getDishImage().add(getImageUrlByDish(dishEntity.getId()));
                    dishInfoResponse.getDishPrice().add(dishEntity.getPrice());
                    dishInfoResponse.getDishCate().add(cateId);
                }
            }
        }

        getHistoryOrder(dinnerId, eateryId, dishInfoResponse);
        return dishInfoResponse;
    }

    private void getHistoryOrder(long dinnerId, int eateryId, DishInfoResponse dishInfoResponse) {

    }

    private String getImageUrlByDish(Long dishId) {
        DishImageEntity dishImageEntity = dishImageRepository.findActiveImageByDishId(dishId);
        long imageId = dishImageEntity.getImage();

        ImageEntity imageEntity = imageRepository.findImageById(imageId);
        String imageUrl = imageEntity.getFile_name();
        return imageUrl;
    }

    public int order(String dinnerId, OrderRequest orderRequest) {
        int orderType = orderRequest.getOrderType();
        long tableId = orderRequest.getTableId();
        int eateryId = orderRequest.getEateryId();
    }
}
