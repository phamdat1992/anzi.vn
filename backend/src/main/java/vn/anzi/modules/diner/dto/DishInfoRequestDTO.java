package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishInfoRequestDTO {
    @NotNull
    @NotEmpty
    private Long tableId;
    @NotNull
    @NotEmpty
    private Long eateryId;
}
