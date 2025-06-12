package com.crypto.service;

import com.crypto.domain.VerificationType;
import com.crypto.modal.ForgotPasswordToken;
import com.crypto.modal.User;

public interface ForgotPasswordService {

    ForgotPasswordToken creatToken(User user, 
                                    String id, 
                                    String otp, 
                                    VerificationType verificationType, 
                                    String sendTo);

    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(Long userId);

    void deleteToken(ForgotPasswordToken token);
}
