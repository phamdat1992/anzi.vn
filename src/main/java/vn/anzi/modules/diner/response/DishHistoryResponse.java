package vn.anzi.modules.diner.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DishHistoryResponse {
    private ArrayList<Integer> dishId;
    private ArrayList<Integer> quantity;
    private ArrayList<Boolean> status;
}
