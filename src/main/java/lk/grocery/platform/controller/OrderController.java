package lk.grocery.platform.controller;

import lk.grocery.platform.dto.OrderDTO;
import lk.grocery.platform.dto.OrderDetailDTO;
import lk.grocery.platform.response.SuccessResponse;
import lk.grocery.platform.response.SuccessResponseHandler;
import lk.grocery.platform.service.OrderDetailService;
import lk.grocery.platform.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public OrderController(OrderService orderService,
                           OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createGoodList(@RequestBody OrderDTO orderDTO) {
        return SuccessResponseHandler.generateResponse(orderService.createGoodsList(orderDTO));
    }

    @PostMapping("/{orderId}/item")
    public ResponseEntity<SuccessResponse> createGoodList(@PathVariable("orderId") Long orderId, @RequestBody List<OrderDetailDTO> orderDetailDTOList) {
        return SuccessResponseHandler.generateResponse(orderDetailService.addItemsToGoodsList(orderId, orderDetailDTOList));
    }

    @DeleteMapping("/{orderId}/item/{itemId}")
    public ResponseEntity<SuccessResponse> removeItemFromGoodsList(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId) {
        return SuccessResponseHandler.generateResponse(orderDetailService.removeItemFromList(orderId, itemId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<SuccessResponse> removeOrder(@PathVariable("orderId") Long orderId) {
        return SuccessResponseHandler.generateResponse(orderService.clearGoodsList(orderId, true));
    }
}
