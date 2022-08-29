package vn.anzi.modules.management.dish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDishRequestDTO {
    @NotNull
    @NotEmpty
    private Long eateryId;
    @NotNull
    @NotEmpty
    private Long dishId;
}
