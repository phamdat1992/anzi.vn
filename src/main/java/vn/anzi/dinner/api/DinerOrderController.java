//package vn.anzi.diner.api;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import vn.anzi.diner.request.DishInfoRequest;
//import vn.anzi.diner.request.OrderRequest;
//import vn.anzi.diner.response.DishInfoResponse;
//import vn.anzi.diner.service.DinerOrderService;
//import vn.anzi.utils.TimeUtil;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//public class DinerOrderController {
//    @Autowired
//    private DinerOrderService dinerOrderService;
//
//    @ResponseBody
//    @GetMapping("/diner/dish-info")
//    public DishInfoResponse getDishInfo(@RequestBody DishInfoRequest dishInfoRequest, HttpServletResponse servletResponse) {
//        long tableId = dishInfoRequest.getTableId();
//        Cookie cookie = new Cookie("tableId", String.valueOf(tableId));
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//        servletResponse.addCookie(cookie);
//
//        DishInfoResponse dishInfoResponse = dinerOrderService.getDishedInfo(dishInfoRequest);
//        return dishInfoResponse;
//    }
//
//    @ResponseBody
//    @PostMapping("/diner/order")
//    public ResponseEntity<Void> order(@RequestHeader("dinerId") int dinerId, @RequestBody OrderRequest orderRequest) {
//        return dinerOrderService.order(dinerId, orderRequest);
//    }
//}
