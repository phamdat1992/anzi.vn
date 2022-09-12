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

    private List<OrderItemDTO> dishInfo;
}
