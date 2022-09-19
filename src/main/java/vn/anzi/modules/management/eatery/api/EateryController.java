package vn.anzi.modules.management.eatery.api;

import com.amazonaws.services.dynamodbv2.model.TableAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.category.services.CategoryService;
import vn.anzi.modules.management.dish.services.DishService;
import vn.anzi.modules.management.eatery.dto.DeleteEateryRequestDTO;
import vn.anzi.modules.management.eatery.dto.GetAllEateryResponseDTO;
import vn.anzi.modules.management.eatery.dto.NewEateryRequestDTO;
import vn.anzi.modules.management.eatery.dto.NewEateryResponseDTO;
import vn.anzi.modules.management.eatery.entity.EateryEntity;
import vn.anzi.modules.management.eatery.services.EateryService;
import vn.anzi.modules.management.role.model.RoleModel;
import vn.anzi.modules.management.table.services.TableService;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.services.AuthenticateUserService;
import vn.anzi.modules.management.user.services.StaffService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/eatery")
public class EateryController {
    @Autowired
    private EateryService eateryService;

    @Autowired
    private AuthenticateUserService authenticateUserService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private TableService tableService;

    @Autowired
    private StaffService staffService;

    @PostMapping("")
    public ResponseEntity<NewEateryResponseDTO> createNewEatery(@RequestBody NewEateryRequestDTO newEateryRequestDTO, HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        EateryEntity eater = new EateryEntity();
        eater.setAddress(newEateryRequestDTO.getAddress());
        eater.setName(newEateryRequestDTO.getName());
        eater = eateryService.createEatery(eater);
        eateryService.createUserEatery(eater, user, (long) RoleModel.MANAGER.getValue(), null);

        NewEateryResponseDTO newEateryResponseDTO = new NewEateryResponseDTO();
        newEateryResponseDTO.setId(eater.getId());

        return ResponseEntity.ok().body(newEateryResponseDTO);
    }

    @GetMapping("")
    public ResponseEntity<GetAllEateryResponseDTO> getAllEatery(HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        GetAllEateryResponseDTO eatery = new GetAllEateryResponseDTO();
        eatery.setEatery(eateryService.getAllEateryByUserId(user.getId()));

        return ResponseEntity.ok().body(eatery);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteEatery(@RequestBody DeleteEateryRequestDTO eatery, HttpServletRequest request) {
        categoryService.deleteCategoryByEateryId(eatery.getId());
        dishService.deleteDishByCategoryId(eatery.getId());
        eateryService.deleteEateryById(eatery.getId());
        tableService.deleteTableByEateryId(eatery.getId());
        staffService.deleteStaffByEateryId(eatery.getId());

        return ResponseEntity.ok().build();
    }
}
