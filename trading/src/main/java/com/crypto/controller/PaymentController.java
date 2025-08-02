package com.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.domain.PaymentMethod;
import com.crypto.modal.PaymentOrder;
import com.crypto.modal.User;
import com.crypto.response.PaymentResponse;
import com.crypto.service.PaymentService;
import com.crypto.service.UserService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/api/payment/{paymentMathod}/amount/{amount}")
    public ResponseEntity<PaymentResponse> paymentHandler(
        @RequestHeader("Authorization") String jwt,
        @PathVariable Long amount,
        @PathVariable PaymentMethod paymentMethod) throws Exception, RazorpayException, StripeException {
        
        User user = userService.findUserProfileByJwt(jwt);

        PaymentResponse paymentResponse;

        PaymentOrder order = paymentService.createOrder(user, amount, paymentMethod);

        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            paymentResponse=paymentService.createRazorpayPaymentLink(user, amount);
        } 
        else {
            paymentResponse = paymentService.createStripePaymentLink(user, amount, order.getId());
        }

        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }
    

}
