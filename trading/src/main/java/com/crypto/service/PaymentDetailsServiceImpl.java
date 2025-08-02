package com.crypto.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.crypto.modal.PaymentDetails;
import com.crypto.modal.User;
import com.crypto.repository.PaymentDetailsRepository;

public class PaymentDetailsServiceImpl implements PaymentDetailsService{

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public PaymentDetails addPaymentDetails(String accountNumber, String accountName, String ifc, String bankName,
            User user) {

        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setAccountNumber(accountNumber);
        paymentDetails.setAccountHolderName(accountName);
        paymentDetails.setIfsc(ifc);
        paymentDetails.setBankName(bankName);
        paymentDetails.setUser(user);

        return paymentDetailsRepository.save(paymentDetails);
        
    }

    @Override
    public PaymentDetails getUserPaymentDetails(User user) {
        return paymentDetailsRepository.findByUserId(user.getId());
    }

}
