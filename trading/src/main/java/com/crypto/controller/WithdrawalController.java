package com.crypto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.domain.WalletTransactionType;
import com.crypto.modal.User;
import com.crypto.modal.Wallet;
import com.crypto.modal.WalletTransaction;
import com.crypto.modal.Withdrawal;
import com.crypto.service.TransactionService;
import com.crypto.service.UserService;
import com.crypto.service.WalletService;
import com.crypto.service.WithdrawalService;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    // @Autowired
    // private WalletTransactionService WalletTransactionService;

    @PostMapping("/api/withdrawal/{amount}")
    public ResponseEntity<?> withdrawalRequest(
        @PathVariable Long amount,
        @RequestHeader("Authorization") String jwt 
        ) throws Exception {
        
        User user = userService.findUserProfileByJwt(jwt);
        Wallet userWallet = walletService.getUserWallet(user);

        Withdrawal withdrawal = withdrawalService.requestWithdrawal(amount, user);
        walletService.addBalance(userWallet, -withdrawal.getAmount());

        WalletTransaction walletTransaction = transactionService.createTransaction(userWallet, 
            WalletTransactionType.WITHDRAWAL, 
            null, 
            "Bank Account Withdrawal", 
            withdrawal.getAmount());

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
        
    }

    @PatchMapping("api/admin/withdrawal/{id}/proceed/{accept}")
    public ResponseEntity<?> proceedWithdrawal(
        @PathVariable Long id,
        @PathVariable boolean accept,
        @RequestHeader("Authorization") String jwt) throws Exception{

            User user = userService.findUserProfileByJwt(jwt);

            Withdrawal withdrawal = withdrawalService.proccedWithdrawal(id, accept);

            Wallet userWallet = walletService.getUserWallet(user);

            if(!accept){
                walletService.addBalance(userWallet, withdrawal.getAmount());
            }

            return new ResponseEntity<>(withdrawal, HttpStatus.OK);

        }

        @GetMapping("api/withdrawal")
        public ResponseEntity<List<Withdrawal>> getWithdrawalHistory(
            @RequestHeader("Authorization") String jwt) throws Exception {

                User user = userService.findUserProfileByJwt(jwt);

                List<Withdrawal> withdrawal = withdrawalService.getUsersWithdrawalHistory(user);
                return new ResponseEntity<>(withdrawal, HttpStatus.OK);
        }

        @GetMapping("api/admin/withdrawal")
        public ResponseEntity<List<Withdrawal>> getAllWithdrawalRequst(
            @RequestHeader("Authorization") String jwt) throws Exception {

                User user = userService.findUserProfileByJwt(jwt);

                List<Withdrawal> withdrawal = withdrawalService.getAllWithdrawalRequest();
                return new ResponseEntity<>(withdrawal, HttpStatus.OK);
        }
        
    
}
