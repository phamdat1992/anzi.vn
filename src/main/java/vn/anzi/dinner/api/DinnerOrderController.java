package vn.anzi.dinner.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.dinner.request.DishInfoRequest;
import vn.anzi.dinner.request.OrderRequest;
import vn.anzi.dinner.response.DishInfoResponse;
import vn.anzi.dinner.service.DinnerOrderService;
import vn.anzi.utils.TimeUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class DinnerOrderController {
//    @Autowired
//    private DinnerOrderService dinnerOrderService;
//
//    @ResponseBody
//    @GetMapping("/dinner/dish-info")
//    public DishInfoResponse getDishInfo(@RequestBody DishInfoRequest dishInfoRequest, HttpServletResponse servletResponse) {
//        long tableId = dishInfoRequest.getTableId();
//        Cookie cookie = new Cookie("tableId", String.valueOf(tableId));
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        servletResponse.addCookie(cookie);
//
//        DishInfoResponse dishInfoResponse = dinnerOrderService.getDishedInfo(dishInfoRequest);
//        return dishInfoResponse;
//    }
//
//    @ResponseBody
//    @PostMapping("/diner/order")
//    public ResponseEntity<Void> order(@RequestHeader("dinnerId") int dinnerId, @RequestBody OrderRequest orderRequest) {
//        return dinnerOrderService.order(dinnerId, orderRequest);
//    }
}
