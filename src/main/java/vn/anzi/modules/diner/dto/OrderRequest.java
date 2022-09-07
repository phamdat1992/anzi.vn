package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private int dinerId;
    private int orderType;
    private int tableId;
    private int eateryId;

    private ArrayList<Integer> orderDishId;
    private ArrayList<Integer> quantity;
}
