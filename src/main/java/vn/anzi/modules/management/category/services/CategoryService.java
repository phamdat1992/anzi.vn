package vn.anzi.modules.management.category.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.anzi.modules.management.category.dto.NewCategoryRequestDTO;
import vn.anzi.modules.management.category.dto.UpdateCategoryRequestDTO;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.category.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity createCategory(NewCategoryRequestDTO newCategoryRequestDTO) {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setEateryId(newCategoryRequestDTO.getEateryId());
        newCategory.setName(newCategoryRequestDTO.getName());
        newCategory.setIsActive(true);

        return categoryRepository.save(newCategory);
    }

    public CategoryEntity updateCategory(UpdateCategoryRequestDTO updateCategoryRequestDTO) {
        CategoryEntity category = new CategoryEntity();
        category.setEateryId(updateCategoryRequestDTO.getEateryId());
        category.setName(updateCategoryRequestDTO.getName());
        category.setId(updateCategoryRequestDTO.getCategoryId());
        category.setIsActive(true);

        return categoryRepository.save(category);
    }
}
