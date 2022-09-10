package vn.anzi.modules.management.category.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.category.dto.*;
import vn.anzi.modules.management.category.entity.CategoryEntity;
import vn.anzi.modules.management.category.services.CategoryService;
import vn.anzi.modules.management.dish.services.DishService;
import vn.anzi.modules.management.eatery.dto.NewEateryRequestDTO;
import vn.anzi.modules.management.eatery.dto.NewEateryResponseDTO;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.services.AuthenticateUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/category")
public class CategoryController {

    @Autowired
    private AuthenticateUserService authenticateUserService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @PostMapping("")
    public ResponseEntity<NewCategoryResponseDTO> createEatery(@RequestBody NewCategoryRequestDTO newCategoryRequestDTO, HttpServletRequest request) {
        UserEntity user = this.authenticateUserService.getUserFromCookie(request);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        CategoryEntity category = this.categoryService.createCategory(newCategoryRequestDTO);
        NewCategoryResponseDTO categoryResponse = new NewCategoryResponseDTO();
        categoryResponse.setId(category.getId());

        return ResponseEntity.ok().body(categoryResponse);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateCategory(@RequestBody UpdateCategoryRequestDTO updateCategoryRequestDTO, HttpServletRequest request) {
        this.categoryService.updateCategory(updateCategoryRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{eateryId}")
    public ResponseEntity<GetAllCategoryResponse> getAllCategory(@PathVariable Long eateryId, HttpServletRequest request) {
        GetAllCategoryResponse response = new GetAllCategoryResponse();
        response.setCategory(this.categoryService.getAllCategory(eateryId));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteCategory (@RequestBody DeleteCategoryRequestDTO category, HttpServletRequest request) {
        categoryService.deleteCategory(category.getId());
        dishService.deleteDishByCategoryId(category.getId());
        return ResponseEntity.ok().build();
    }
}
