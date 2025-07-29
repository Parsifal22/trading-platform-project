package com.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crypto.modal.Watchlist;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    Watchlist findByUserId(Long userId);
} 
