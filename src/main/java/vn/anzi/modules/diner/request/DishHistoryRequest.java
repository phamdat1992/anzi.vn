package vn.anzi.modules.diner.request;

import lombok.Data;

@Data
public class DishHistoryRequest {
    private int dinerId;
    private int tableId;
    private int eateryId;
}
