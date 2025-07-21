package com.crypto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.domain.OrderType;
import com.crypto.modal.Coin;
import com.crypto.modal.Order;
import com.crypto.modal.User;
import com.crypto.modal.WalletTransaction;
import com.crypto.request.CreateOrderRequest;
import com.crypto.service.CoinService;
import com.crypto.service.OrderService;
import com.crypto.service.UserService;
import com.crypto.service.WalletService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CoinService coinService;

    // @Autowired
    // private WalletTransactionService walletTransactionService;    

    @PostMapping("/pay")
    public ResponseEntity<Order> payOrderPayment(
        @RequestHeader("Autorization") String jwt,
        @RequestBody CreateOrderRequest req
    ) throws Exception  {

        User user = userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(req.getCoinId());

        Order order = orderService.processOrder(coin, req.getQuantity(), req.getOrderType(), user);

        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
        @RequestHeader("Authorization") String jwtToken,
        @PathVariable Long orderId
    ) throws Exception {

        // if(jwtToken == null) {
        //     throw new Exception("token missing...");
        // }

        User user = userService.findUserProfileByJwt(jwtToken);

        Order order = orderService.getOrderById(orderId);

        if(order.getUser().getId().equals(user.getId())) {
            return ResponseEntity.ok(order);
        } else {
            throw new Exception("You don't have access");
        }
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersForUser(
        @RequestHeader("Authorization") String jwt,
        @RequestParam(required = false) OrderType order_type,
        @RequestParam(required = false) String asset_symbol
    ) throws Exception
    {
        Long userId = userService.findUserProfileByJwt(jwt).getId();

        List<Order> userOrders = orderService.getAllOrdersByUser(userId, order_type, asset_symbol);

        return ResponseEntity.ok(userOrders);
    }
    

    

}
