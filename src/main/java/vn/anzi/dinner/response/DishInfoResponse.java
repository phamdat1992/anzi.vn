package vn.anzi.dinner.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;

@Data
public class DishInfoResponse {
    private ArrayList<Integer> categoryId;
    private ArrayList<String> categoryName;

    private ArrayList<Long> dishId;
    private ArrayList<String> dishName;
    private ArrayList<String> dishImage;
    private ArrayList<Double> dishPrice;
    private ArrayList<Integer> dishCate;

    private ArrayList<Integer> historyDishId;
    private ArrayList<Integer> historyQuantity;
    private ArrayList<Integer> status;
}
