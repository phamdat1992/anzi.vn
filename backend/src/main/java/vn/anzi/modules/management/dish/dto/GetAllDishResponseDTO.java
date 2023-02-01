package vn.anzi.modules.management.dish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.dish.entity.DishEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllDishResponseDTO {
    private List<CategoryEntity> category;
    private List<DishEntity> dish;
}
