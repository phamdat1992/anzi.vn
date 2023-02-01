package vn.anzi.modules.management.table.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.anzi.modules.management.table.dto.*;
import vn.anzi.modules.management.table.entity.TableEntity;
import vn.anzi.modules.management.table.services.TableService;
import vn.anzi.modules.management.user.entity.UserEntity;
import vn.anzi.modules.management.user.services.AuthenticateUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/management/table")
public class TableController implements WebMvcConfigurer {

    @Autowired
    private AuthenticateUserService authenticateUserService;

    @Autowired
    private TableService tableService;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configure) {
        configure.setDefaultTimeout(86_400_000L);
    }

    @PostMapping("")
    public ResponseEntity<NewTableResponseDTO> createNewTable(@RequestBody NewTableRequestDTO newEateryRequestDTO, HttpServletRequest request) {
        UserEntity user = authenticateUserService.getUserFromCookie(request);
        TableEntity table = tableService.createTable(newEateryRequestDTO);
        NewTableResponseDTO tableResponse = new NewTableResponseDTO();
        tableResponse.setId(table.getId());

        return ResponseEntity.ok().body(tableResponse);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateTable(@RequestBody UpdateTableRequestDTO table, HttpServletRequest request) {
        tableService.updateTable(table);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteTable(@RequestBody DeleteTableRequestDTO table, HttpServletRequest request) {
        tableService.deleteTable(table);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{eateryId}")
    public ResponseEntity<GetAllTableResponseDTO> getAllTable(@PathVariable Long eateryId, HttpServletRequest request) {
        GetAllTableResponseDTO response = new GetAllTableResponseDTO();
        response.setTables(this.tableService.getAllTableByEateryId(eateryId));

        return ResponseEntity.ok().body(response);
    }
}
