package vn.anzi.dinner.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DishInfoRequest {
    private long dinnerId;
    private long tableId;
    private int eateryId;
}
