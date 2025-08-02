package com.crypto.service;

import com.crypto.modal.PaymentDetails;
import com.crypto.modal.User;

public interface PaymentDetailsService {

    public PaymentDetails addPaymentDetails(String accountNumber,
                                            String accountName,
                                            String ifc,
                                            String bankName,
                                            User user);

    public PaymentDetails getUserPaymentDetails(User user);

}
