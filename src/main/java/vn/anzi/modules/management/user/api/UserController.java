package vn.anzi.modules.management.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.anzi.modules.management.user.dto.GetAllStaffResponseDTO;
import vn.anzi.modules.management.user.dto.GetUserResponseDTO;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.services.AuthenticateUserService;
import vn.anzi.modules.management.user.services.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticateUserService authenticateUserService;

    @GetMapping("")
    public ResponseEntity<GetUserResponseDTO> getUser(HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        UserEntity userInfo = userService.getUserById(user.getId());
        GetUserResponseDTO response = new GetUserResponseDTO();
        response.setUser(userInfo);
        return ResponseEntity.ok().body(response);
    }
}
