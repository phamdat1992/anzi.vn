package vn.anzi.modules.diner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.diner.request.DishHistoryRequest;
import vn.anzi.modules.diner.request.DishInfoRequest;
import vn.anzi.modules.diner.request.OrderRequest;
import vn.anzi.modules.diner.response.DishHistoryResponse;
import vn.anzi.modules.diner.response.DishInfoResponse;
import vn.anzi.modules.diner.service.DinerOrderService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class DinerOrderController {
    @Autowired
    private DinerOrderService dinerOrderService;

    @ResponseBody
    @GetMapping("/diner/dish-info")
    public DishInfoResponse getDishInfo(@RequestBody DishInfoRequest dishInfoRequest, HttpServletResponse servletResponse) {
        long tableId = dishInfoRequest.getTableId();
        Cookie cookie = new Cookie("tableId", String.valueOf(tableId));
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);

        DishInfoResponse dishInfoResponse = dinerOrderService.getDishedInfo(dishInfoRequest);
        return dishInfoResponse;
    }

    @PostMapping("/diner/order")
    public ResponseEntity<Void> order(@RequestBody OrderRequest orderRequest) {
        return dinerOrderService.order(orderRequest);
    }

    @ResponseBody
    @PostMapping("/diner/history")
    public DishHistoryResponse order(@RequestBody DishHistoryRequest dishHistoryRequest) {
        DishHistoryResponse dishHistoryResponse = dinerOrderService.getDishHistory(dishHistoryRequest);
        return dishHistoryResponse;
    }
}
