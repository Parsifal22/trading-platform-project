package com.crypto.repository;

import com.crypto.modal.Coin;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CoinRepository extends JpaRepository<Coin, String> {


}
