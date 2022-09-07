package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishHistoryResponse {
    private ArrayList<Integer> dishId;
    private ArrayList<Integer> quantity;
    private ArrayList<Boolean> status;
}
