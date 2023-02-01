package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    @NotNull
    @NotEmpty
    private Long orderTypeId;
    @NotNull
    @NotEmpty
    private Long tableId;
    @NotNull
    @NotEmpty
    private String tableName;
    @NotNull
    @NotEmpty
    private String tableLocation;
    @NotNull
    @NotEmpty
    private Long eateryId;

    private Long totalPrice;

    private Long totalDish;

    private List<OrderItemDTO> dishInfo;
}
