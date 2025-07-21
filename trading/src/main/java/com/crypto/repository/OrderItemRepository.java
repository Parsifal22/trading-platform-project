package com.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crypto.modal.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
