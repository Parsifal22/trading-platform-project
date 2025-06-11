package com.crypto.service;

import com.crypto.modal.TwoFactorOTP;
import com.crypto.modal.User;

public interface TwoFactorOtpService {

    TwoFactorOTP createTwoFactorOTP(User user, String otp, String jwt);
    
    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp);

    void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);
} 
