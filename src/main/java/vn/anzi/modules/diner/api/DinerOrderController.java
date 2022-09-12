package vn.anzi.modules.diner.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.diner.dto.*;
import vn.anzi.modules.diner.services.AuthenticateDinerService;
import vn.anzi.modules.diner.services.DinerOrderService;
import vn.anzi.modules.management.category.services.CategoryService;
import vn.anzi.modules.management.dish.services.DishService;
import vn.anzi.modules.management.order.entity.OrderEntity;
import vn.anzi.modules.management.order.services.OrderService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/diner")
public class DinerOrderController {

    @Autowired
    private AuthenticateDinerService authenticateService;

    @Autowired
    private DinerOrderService dinerOrderService;

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @PostMapping("/dish-info")
    public ResponseEntity<DishInfoResponseDTO> getDishInfo(@RequestBody DishInfoRequestDTO dish, HttpServletRequest request) {
        Long userId = authenticateService.getUserIdFromCookie(request);
        DishInfoResponseDTO response = new DishInfoResponseDTO();
        response.setDish(dishService.getAllDishByEateryId(dish.getEateryId()));
        response.setCategory(categoryService.getAllCategory(dish.getEateryId()));
        response.setQuantity(orderService.getTotalDishByUser(userId, dish.getTableId()));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/order")
    public ResponseEntity<Void> order(@RequestBody OrderRequestDTO orderRequest, HttpServletRequest request) {
        Long userId = authenticateService.getUserIdFromCookie(request);
        OrderEntity order = dinerOrderService.createNewOrderEntity(orderRequest, userId);
        dinerOrderService.createListOrder(orderRequest, order);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @PostMapping("/bucket")
    public ResponseEntity<DishBucketResponseDTO> bucket(@RequestBody DishBucketRequestDTO bucket, HttpServletRequest request) {
        Long userId = authenticateService.getUserIdFromCookie(request);
        DishBucketResponseDTO response = new DishBucketResponseDTO();
        response.setBucket(orderService.getAllDishBucketByTableId(userId, bucket.getTableId()));
        return ResponseEntity.ok().body(response);
    }
}
