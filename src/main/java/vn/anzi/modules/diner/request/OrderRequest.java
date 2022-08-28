package vn.anzi.modules.diner.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OrderRequest {
    private int dinerId;
    private int orderType;
    private int tableId;
    private int eateryId;

    private ArrayList<Integer> orderDishId;
    private ArrayList<Integer> quantity;
}
