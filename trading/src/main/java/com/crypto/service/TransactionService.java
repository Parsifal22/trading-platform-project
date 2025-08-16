package com.crypto.service;

import com.crypto.domain.WalletTransactionType;
import com.crypto.modal.Wallet;
import com.crypto.modal.WalletTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    List<WalletTransaction> getTransactionsByWallet(Wallet wallet);

    WalletTransaction createTransaction(Wallet wallet,
                                        WalletTransactionType type,
                                        Long refId,
                                        String purpose,
                                        Long amount);
}
