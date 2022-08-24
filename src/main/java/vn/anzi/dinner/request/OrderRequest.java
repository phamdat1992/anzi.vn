package vn.anzi.dinner.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OrderRequest {
    private int orderType;
    private long tableId;
    private int eateryId;

    private ArrayList<Integer> orderDishId;
    private ArrayList<Integer> quantity;
}
