package vn.anzi.modules.diner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.diner.dto.*;
import vn.anzi.modules.diner.service.DinerOrderService;

@RestController
@RequestMapping(path="/diner")
public class DinerOrderController {
    @Autowired
    private DinerOrderService dinerOrderService;

    @ResponseBody
    @GetMapping("/dish-info")
    public ResponseEntity<DishInfoResponse> getDishInfo(@RequestBody DishInfoRequest dishInfoRequest) {
        return ResponseEntity.ok().body(dinerOrderService.getDishedInfo(dishInfoRequest));
    }

    @PostMapping("/order")
    public ResponseEntity<Void> order(@RequestBody OrderRequest orderRequest) {
        dinerOrderService.order(orderRequest);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @PostMapping("/diner/history")
    public DishHistoryResponse order(@RequestBody DishHistoryRequest dishHistoryRequest) {
        DishHistoryResponse dishHistoryResponse = dinerOrderService.getDishHistory(dishHistoryRequest);
        return dishHistoryResponse;
    }
}
