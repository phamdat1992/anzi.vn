package vn.anzi.modules.management.order.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.anzi.modules.management.order.dto.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/management/order")
public class OrderController {
    @PostMapping("/history")
    public ResponseEntity<HistoryResponseDTO> getHistory(@RequestBody HistoryRequestDTO history, HttpServletRequest request) {
        return null;
    }

    @PostMapping("/history/{orderId}")
    public ResponseEntity<HistoryOrderDetailResponseDTO> getHistoryOrderDetail(@PathVariable Long orderId, HttpServletRequest request) {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<OrderResponseDTO> getAllOrder(@RequestBody OrderRequestDTO order, HttpServletRequest request) {
        return null;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponseDTO> getOrderDetail(@PathVariable Long orderId, HttpServletRequest request) {
        return null;
    }

    @PostMapping("/confirm/{orderId}")
    public ResponseEntity<Void> confirmOrder(@PathVariable Long orderId, HttpServletRequest request) {
        return null;
    }
}
