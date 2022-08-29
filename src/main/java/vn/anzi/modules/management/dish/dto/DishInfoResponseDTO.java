package vn.anzi.modules.management.dish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.dish.entity.DishInfoEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishInfoResponseDTO {
    private List<CategoryEntity> category;
    private List<DishInfoEntity> dish;
}
