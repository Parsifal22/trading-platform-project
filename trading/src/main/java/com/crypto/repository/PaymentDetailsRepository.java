package com.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crypto.modal.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long>{
    PaymentDetails findByUserId(Long userId);
}
