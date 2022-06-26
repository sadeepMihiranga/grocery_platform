package lk.grocery.platform.controller;

import lk.grocery.platform.dto.OrderDTO;
import lk.grocery.platform.response.SuccessResponse;
import lk.grocery.platform.response.SuccessResponseHandler;
import lk.grocery.platform.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createGoodList(@RequestBody OrderDTO orderDTO) {
        return SuccessResponseHandler.generateResponse(orderService.createGoodsList(orderDTO));
    }
}
