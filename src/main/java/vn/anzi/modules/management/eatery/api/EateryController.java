package vn.anzi.modules.management.eatery.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.eatery.dto.GetAllEateryResponseDTO;
import vn.anzi.modules.management.eatery.dto.NewEateryRequestDTO;
import vn.anzi.modules.management.eatery.dto.NewEateryResponseDTO;
import vn.anzi.modules.management.eatery.entity.EateryEntity;
import vn.anzi.modules.management.eatery.services.EateryService;
import vn.anzi.modules.management.user.dto.AuthenticateRequestDTO;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.services.AuthenticateUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/eatery")
public class EateryController {
    @Autowired
    private EateryService eateryService;

    @Autowired
    private AuthenticateUserService authenticateUserService;

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
        eater = eateryService.createEatery(eater, user);

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

        return null;
    }
}
