package com.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crypto.modal.PaymentOrder;
import com.crypto.service.PaymentService;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {

}
