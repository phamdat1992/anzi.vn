package vn.anzi.modules.management.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.eatery.entity.UserEateryEntity;
import vn.anzi.modules.management.eatery.services.EateryService;
import vn.anzi.modules.management.role.services.RoleService;
import vn.anzi.modules.management.user.dto.*;
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

    @GetMapping("/{eateryId}")
    public ResponseEntity<GetAllStaffResponseDTO> getAllStaff(@PathVariable Long eateryId, HttpServletRequest request) {
        GetAllStaffResponseDTO response = new GetAllStaffResponseDTO();
        response.setStaffs(staffService.getAllStaffByEateryId(eateryId));
        response.setRoles(roleService.getAllRole());

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteStaff(@RequestBody DeleteStaffRequestDTO deleteStaffRequest, HttpServletRequest request) {
        eateryService.removeUserByUserId(deleteStaffRequest.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updateStaff(@RequestBody UpdateStaffRequestDTO staff, HttpServletRequest request) {
        eateryService.updateUser(staff.getStaffId(), staff.getName(), staff.getRoleId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<NewStaffResponseDTO> createStaff(@RequestBody NewStaffRequestDTO newStaffRequestDTO, HttpServletRequest request) {
        UserEateryEntity userEateryEntity = staffService.createNewStaff(newStaffRequestDTO);
        NewStaffResponseDTO newStaffResponseDTO = new NewStaffResponseDTO();
        newStaffResponseDTO.setId(userEateryEntity.getId());

        return ResponseEntity.ok().body(newStaffResponseDTO);
    }
}
