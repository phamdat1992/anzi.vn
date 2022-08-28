package vn.anzi.diner.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DishInfoRequest {
    private long dinerId;
    private long tableId;
    private int eateryId;
}
