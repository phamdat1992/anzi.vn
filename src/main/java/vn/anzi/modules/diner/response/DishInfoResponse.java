package vn.anzi.modules.diner.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;

@Data
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
