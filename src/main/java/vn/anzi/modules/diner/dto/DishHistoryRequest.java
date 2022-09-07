package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishHistoryRequest {
    private int dinerId;
    private int tableId;
    private int eateryId;
}
