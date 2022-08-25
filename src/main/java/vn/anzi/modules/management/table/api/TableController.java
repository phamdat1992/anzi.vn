package vn.anzi.modules.management.table.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.eatery.dto.NewEateryRequestDTO;
import vn.anzi.modules.management.eatery.dto.NewEateryResponseDTO;
import vn.anzi.modules.management.table.dto.*;
import vn.anzi.modules.management.table.entity.TableEntity;
import vn.anzi.modules.management.table.services.TableService;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.services.AuthenticateUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/table")
public class TableController {

    @Autowired
    private AuthenticateUserService authenticateUserService;

    @Autowired
    private TableService tableService;

    @PostMapping("")
    public ResponseEntity<NewTableResponseDTO> createNewTable(@RequestBody NewTableRequestDTO newEateryRequestDTO, HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);
        if (!authenticateUserService.isManager(user, newEateryRequestDTO.getEateryId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        TableEntity table = tableService.createTable(newEateryRequestDTO);
        NewTableResponseDTO tableResponse = new NewTableResponseDTO();
        tableResponse.setId(table.getId());

        return ResponseEntity.ok().body(tableResponse);
    }

    @DeleteMapping("")
    public  ResponseEntity<Void> deleteTable(@RequestBody DeleteTableRequestDTO table, HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);
        if (!authenticateUserService.isManager(user, table.getEateryId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        tableService.deleteTable(table);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<GetAllTableResponseDTO> getAllTable(@RequestBody GetAllTableRequestDTO tableRequest, HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);
        if (!authenticateUserService.isManager(user, tableRequest.getEateryId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        GetAllTableResponseDTO response = new GetAllTableResponseDTO();
        response.setTable(this.tableService.getAllTableByEateryId(tableRequest.getEateryId()));

        return ResponseEntity.ok().body(response);
    }
}
