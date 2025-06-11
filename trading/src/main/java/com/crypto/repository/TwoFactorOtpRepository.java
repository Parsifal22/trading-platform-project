package com.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crypto.modal.TwoFactorOTP;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOTP, String>{
    TwoFactorOTP findByUserId(Long userId);
} 
