package vn.anzi.modules.management.dish.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.dish.dto.*;
import vn.anzi.modules.management.table.dto.NewTableRequestDTO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/dish")
public class DishController {

    @PostMapping("")
    public ResponseEntity<NewDishResponseDTO> createNewDish(@RequestBody NewDishRequestDTO newEateryRequestDTO, HttpServletRequest request) {

        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updateDish(@RequestBody UpdateDishRequestDTO dish, HttpServletRequest request) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteDish(@RequestBody DeleteDishRequestDTO dish, HttpServletRequest request) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{eateryId}")
    public ResponseEntity<GetAllDishResponseDTO> getAllDish(@PathVariable Long eateryId, HttpServletRequest request) {

        return ResponseEntity.ok().build();
    }
}
