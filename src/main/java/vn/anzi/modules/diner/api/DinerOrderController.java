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
import vn.anzi.modules.management.order.entity.OrderEventEntity;
import vn.anzi.modules.management.order.entity.OrderInfoNotConfirmEntity;
import vn.anzi.modules.management.order.model.EventHandler;
import vn.anzi.modules.management.order.services.OrderService;
import vn.anzi.modules.management.table.entity.TableEntity;
import vn.anzi.modules.management.table.services.TableService;

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

    @Autowired
    private EventHandler eventHandler;

    @Autowired
    private TableService tableService;

    @ResponseBody
    @PostMapping("/dish-info")
    public ResponseEntity<DishInfoResponseDTO> getDishInfo(@RequestBody DishInfoRequestDTO dish, HttpServletRequest request) {
        Long userId = authenticateService.getUserIdFromCookie(request);
        DishInfoResponseDTO response = new DishInfoResponseDTO();
        response.setDish(dishService.getAllDishByEateryId(dish.getEateryId()));
        response.setCategory(categoryService.getAllCategory(dish.getEateryId()));
        response.setQuantity(orderService.getTotalDishByUser(userId, dish.getTableId()));
        response.setTable(tableService.getTableById(dish.getTableId()));

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/order")
    public ResponseEntity<Void> order(@RequestBody OrderRequestDTO orderRequest, HttpServletRequest request) {
        Long userId = authenticateService.getUserIdFromCookie(request);
        OrderEntity order = dinerOrderService.createNewOrderEntity(orderRequest, userId);

        if (orderRequest.getDishInfo() != null) {
            dinerOrderService.createListOrder(orderRequest, order);
        }
        OrderEventEntity orderEvent = orderService.getOrderByOrderId(order.getId());
        if (orderEvent != null) {
            OrderInfoNotConfirmEntity orderInfoNotConfirmEntity = new OrderInfoNotConfirmEntity();
            orderInfoNotConfirmEntity.setId(order.getId());
            orderInfoNotConfirmEntity.setName(orderRequest.getTableName());
            orderInfoNotConfirmEntity.setLocation(orderRequest.getTableLocation());
            orderInfoNotConfirmEntity.setCreatedTime(orderEvent.getCreatedTime());
            orderInfoNotConfirmEntity.setTotalDish(orderRequest.getTotalDish());
            orderInfoNotConfirmEntity.setTotalPrice(orderRequest.getTotalPrice());
            orderInfoNotConfirmEntity.setTypeId(orderRequest.getOrderTypeId());

            eventHandler.broadcast(orderInfoNotConfirmEntity, orderRequest.getEateryId(), false);
        }
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
