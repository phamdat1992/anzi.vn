package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishInfoResponse {
    private ArrayList<Long> categoryId;
    private ArrayList<String> categoryName;

    private ArrayList<Long> dishId;
    private ArrayList<String> dishName;
    private ArrayList<String> dishImage;
    private ArrayList<Float> dishPrice;
    private ArrayList<Long> dishCate;

    private ArrayList<Long> historyDishId;
    private ArrayList<Integer> historyQuantity;
    private ArrayList<Integer> status;
}
