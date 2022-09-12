package vn.anzi.modules.management.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.anzi.modules.management.category.entity.CategoryEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategoryResponse {
    private List<CategoryEntity> category;
}
