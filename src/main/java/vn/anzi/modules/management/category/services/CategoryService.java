package vn.anzi.modules.management.category.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anzi.modules.management.category.dto.NewCategoryRequestDTO;
import vn.anzi.modules.management.category.dto.UpdateCategoryRequestDTO;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.category.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public CategoryEntity createCategory(NewCategoryRequestDTO newCategoryRequestDTO) {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setEateryId(newCategoryRequestDTO.getEateryId());
        newCategory.setName(newCategoryRequestDTO.getName());
        newCategory.setIsActive(true);

        return categoryRepository.save(newCategory);
    }

    @Transactional(rollbackFor = Exception.class)
    public CategoryEntity updateCategory(UpdateCategoryRequestDTO updateCategoryRequestDTO) {
        CategoryEntity category = new CategoryEntity();
        category.setEateryId(updateCategoryRequestDTO.getEateryId());
        category.setName(updateCategoryRequestDTO.getName());
        category.setId(updateCategoryRequestDTO.getCategoryId());
        category.setIsActive(true);

        return categoryRepository.save(category);
    }

    public List<CategoryEntity> getAllCategory(Long eateryId) {
        return categoryRepository.getAllCategory(eateryId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteCategory(categoryId);
    }
}
