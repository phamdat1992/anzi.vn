package vn.anzi.modules.management.order.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.anzi.modules.management.order.dto.response.ManagementOrderResponse;

@RestController
public class ManagementOrderController {
    @ResponseBody
    @GetMapping("/management/order")
    public ManagementOrderResponse order(int eateryId) {
        return new ManagementOrderResponse();
    }

}
