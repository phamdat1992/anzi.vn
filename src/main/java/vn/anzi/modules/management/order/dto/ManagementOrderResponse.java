package vn.anzi.modules.management.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementOrderResponse {
    ArrayList<Integer> orderType;
    ArrayList<Integer> tableId;
    ArrayList<String> tableName;
    ArrayList<String> tableLocation;
    ArrayList<Integer> totalDish;
    ArrayList<Float> totalPrice;
}
