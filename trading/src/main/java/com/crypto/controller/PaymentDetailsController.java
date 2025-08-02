package com.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.modal.PaymentDetails;
import com.crypto.modal.User;
import com.crypto.service.PaymentDetailsService;
import com.crypto.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class PaymentDetailsController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentDetailsService paymentDetailsService;

    @PostMapping("/payment-details")
    public ResponseEntity<PaymentDetails> addPaymentDetails(
        @RequestBody PaymentDetails paymentDetailsRequest,
        @RequestHeader("Authorization") String jwt) throws Exception{
        
        User user = userService.findUserProfileByJwt(jwt);
        
        PaymentDetails paymentDetails = paymentDetailsService.addPaymentDetails(
                                            paymentDetailsRequest.getAccountNumber(),
                                            paymentDetailsRequest.getAccountHolderName(),
                                            paymentDetailsRequest.getIfsc(),
                                            paymentDetailsRequest.getBankName(),
                                            user
        );

        return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
    }

    @GetMapping("/payment-details")
    public ResponseEntity<PaymentDetails> getUserPaymnetDetails(
        @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);

        PaymentDetails paymentDetails = paymentDetailsService.getUserPaymentDetails(user);

        return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
    }
    
    
}
