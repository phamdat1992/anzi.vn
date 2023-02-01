package vn.anzi.modules.diner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.dish.entity.DishEntity;
import vn.anzi.modules.management.table.entity.TableEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishInfoResponseDTO {
    @NotNull
    @NotEmpty
    private List<CategoryEntity> category;
    @NotNull
    @NotEmpty
    private List<DishEntity> dish;
    @NotNull
    @NotEmpty
    private Long quantity;
    @NotNull
    @NotEmpty
    private TableEntity table;
}
