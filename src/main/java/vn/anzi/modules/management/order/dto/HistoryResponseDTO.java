package vn.anzi.modules.management.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.order.entity.HistoryEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponseDTO {
    @NotNull
    @NotEmpty
    private List<HistoryEntity> history;
}
