package com.crypto.service;

import java.util.List;

import com.crypto.domain.OrderType;
import com.crypto.modal.Coin;
import com.crypto.modal.Order;
import com.crypto.modal.OrderItem;
import com.crypto.modal.User;

public interface OrderService {

    Order createOrder(User user, OrderItem orderItem, OrderType orderType);

    Order getOrderById(Long orderId) throws Exception;

    List<Order> getAllOrdersByUser(Long userId, OrderType orderType, String assetSymbol);

    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;

}
