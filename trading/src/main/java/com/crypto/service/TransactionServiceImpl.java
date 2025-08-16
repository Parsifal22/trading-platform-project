package com.crypto.service;

import com.crypto.domain.WalletTransactionType;
import com.crypto.modal.Wallet;
import com.crypto.modal.WalletTransaction;
import com.crypto.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private WalletTransactionRepository walletTransactionRepository;

    @Override
    public List<WalletTransaction> getTransactionsByWallet(Wallet wallet) {
        return walletTransactionRepository.findByWallet(wallet);
    }

    @Override
    public WalletTransaction createTransaction(Wallet wallet, WalletTransactionType type, Long refId, String purpose, Long amount) {
        WalletTransaction transaction = new WalletTransaction();
        transaction.setWallet(wallet);
        transaction.setType(type);
        transaction.setTransferId(String.valueOf(refId));
        transaction.setPurpose(purpose);
        transaction.setAmount(amount.longValue());
        transaction.setDate(LocalDate.now());
        return walletTransactionRepository.save(transaction);
    }
}
