package vn.anzi.modules.management.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.eatery.dto.NewEateryResponseDTO;
import vn.anzi.modules.management.eatery.entity.EateryEntity;
import vn.anzi.modules.management.eatery.entity.UserEateryEntity;
import vn.anzi.modules.management.eatery.services.EateryService;
import vn.anzi.modules.management.role.entity.UserEateryRoleEntity;
import vn.anzi.modules.management.role.services.RoleService;
import vn.anzi.modules.management.table.dto.GetAllTableResponseDTO;
import vn.anzi.modules.management.user.dto.*;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.services.AuthenticateUserService;
import vn.anzi.modules.management.user.services.StaffService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/staff")
public class StaffController {

    @Autowired
    private AuthenticateUserService authenticateUserService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EateryService eateryService;

    @GetMapping("")
    public ResponseEntity<GetAllStaffResponseDTO> getAllStaff(@RequestBody GetAllStaffRequestDTO staffRequest, HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);
        if (!authenticateUserService.isManager(user, staffRequest.getEateryId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        GetAllStaffResponseDTO response = new GetAllStaffResponseDTO();
        response.setStaff(staffService.getAllStaffByEateryId(staffRequest.getEateryId()));
        response.setRole(roleService.getAllRole());

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteStaff(@RequestBody DeleteStaffRequestDTO deleteStaffRequest, HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);
        if (!authenticateUserService.isManager(user, deleteStaffRequest.getEateryId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        roleService.removeUserByUserEateryId(deleteStaffRequest.getStaffId());
        eateryService.removeUserByUserEateryId(deleteStaffRequest.getStaffId(), deleteStaffRequest.getEateryId());

        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<NewStaffResponseDTO> createStaff(@RequestBody NewStaffRequestDTO newStaffRequestDTO, HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);
        if (!authenticateUserService.isManager(user, newStaffRequestDTO.getEateryId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        UserEateryEntity userEateryEntity = staffService.createNewStaff(newStaffRequestDTO);
        NewStaffResponseDTO newStaffResponseDTO = new NewStaffResponseDTO();
        newStaffResponseDTO.setId(userEateryEntity.getId());

        return ResponseEntity.ok().body(newStaffResponseDTO);
    }
}
