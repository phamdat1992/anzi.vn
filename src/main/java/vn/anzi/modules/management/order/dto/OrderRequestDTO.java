package vn.anzi.modules.management.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    @NotNull
    @NotEmpty
    private Long eateryId;

    private Long offsetOrder;
    private Long offsetCallHostess;
}
