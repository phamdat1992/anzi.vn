package vn.anzi.modules.management.dish.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.category.services.CategoryService;
import vn.anzi.modules.management.dish.dto.*;
import vn.anzi.modules.management.dish.services.DishService;
import vn.anzi.modules.management.image.services.FileManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(path="/management/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileManageService fileManageService;

    @PostMapping("")
    public ResponseEntity<NewDishResponseDTO> createNewDish(@RequestBody NewDishRequestDTO newEateryRequestDTO, HttpServletRequest request) throws Exception {
        String imageName = UUID.randomUUID().toString();
        fileManageService.uploadFile(imageName, newEateryRequestDTO.getImage());
        dishService.createNewDish(newEateryRequestDTO, imageName);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updateDish(@RequestBody UpdateDishRequestDTO dish, HttpServletRequest request) throws Exception {
        if (dish.getImage().isEmpty()) {
            dishService.updateDishWithoutImage(dish);
        } else {
            String imageName = UUID.randomUUID().toString();
            fileManageService.uploadFile(imageName, dish.getImage());
            dishService.updateDish(dish, imageName);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteDish(@RequestBody DeleteDishRequestDTO dish, HttpServletRequest request) {
        dishService.deleteDishById(dish.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{eateryId}")
    public ResponseEntity<GetAllDishResponseDTO> getAllDish(@PathVariable Long eateryId, HttpServletRequest request) {
        GetAllDishResponseDTO response = new GetAllDishResponseDTO();
        response.setDish(dishService.getAllDishByEateryId(eateryId));
        response.setCategory(categoryService.getAllCategory(eateryId));
        return ResponseEntity.ok().body(response);
    }
}
