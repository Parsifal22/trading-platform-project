package com.crypto.service;

import com.crypto.domain.VerificationType;
import com.crypto.modal.User;
import com.crypto.modal.VerificationCode;

public interface VerificationCodeService {

    VerificationCode sendVerificationCode(User user, VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;
    
    VerificationCode getVerificationCodeByUser(Long userId);


    void deleteVerificationCodeById(VerificationCode verificationCode); 


}
