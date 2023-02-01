package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    @NotNull
    @NotEmpty
    private Long dishId;
    @NotNull
    @NotEmpty
    private Long quantity;
}