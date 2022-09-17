package vn.anzi.modules.management.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.order.dto.*;
import vn.anzi.modules.management.order.entity.OrderInfoNotConfirmEntity;
import vn.anzi.modules.management.order.model.EventHandler;
import vn.anzi.modules.management.order.services.OrderService;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path="/management/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EventHandler eventHandler;

    @GetMapping("/register-client/{eateryId}")
    public SseEmitter sseEmitter(@PathVariable Long eateryId, HttpServletRequest request) {
        return eventHandler.registerClient(eateryId);
    }

    @PostMapping("/history")
    public ResponseEntity<HistoryResponseDTO> getHistory(@RequestBody HistoryRequestDTO history, HttpServletRequest request) {
        HistoryResponseDTO responseDTO = new HistoryResponseDTO();
        responseDTO.setHistory(orderService.getOrderConfirmedByOffset(history.getEateryId(), history.getOffset()));
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("")
    public ResponseEntity<OrderResponseDTO> getAllOrder(@RequestBody OrderRequestDTO order, HttpServletRequest request) {
        OrderResponseDTO response = new OrderResponseDTO();
        List<OrderInfoNotConfirmEntity> orderList = orderService.getOrderFromOffset(order.getEateryId(), order.getOffsetOrder());
        List<OrderInfoNotConfirmEntity> result = orderService.getOrderHostessFromOffset(order.getEateryId(), order.getOffsetCallHostess());

        result.addAll(orderList.subList(0, Math.min(10 - result.size(), orderList.size())));
        response.setOrder(result);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponseDTO> getOrderDetail(@PathVariable Long orderId, HttpServletRequest request) {
        OrderDetailResponseDTO response = new OrderDetailResponseDTO();
        response.setOrder(orderService.getOrderDetail(orderId));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/confirm/{orderId}")
    public ResponseEntity<Void> confirmOrder(@PathVariable Long orderId, HttpServletRequest request) {
        orderService.confirmOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
