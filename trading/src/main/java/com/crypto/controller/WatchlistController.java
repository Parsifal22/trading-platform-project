package com.crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.modal.Coin;
import com.crypto.modal.User;
import com.crypto.modal.Watchlist;
import com.crypto.service.CoinService;
import com.crypto.service.UserService;
import com.crypto.service.WatchlistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoinService coinService;

    @GetMapping("/user")
    public ResponseEntity<Watchlist> getUserWatchlist(
        @RequestHeader("Authorization") String jwt) throws Exception {
        
            User user = userService.findUserProfileByJwt(jwt);
            Watchlist watchlist = watchlistService.findUserWatchlist(user.getId());

            return ResponseEntity.ok(watchlist);

    }

    @GetMapping("/{watchlistId}")
    public ResponseEntity<Watchlist> getWatchlistById(
        @PathVariable Long watchlistId) throws Exception {
        
            Watchlist watchlist = watchlistService.findById(watchlistId);
            return ResponseEntity.ok(watchlist);

    }


    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<Coin> addItemToWatchlist(
        @RequestHeader("Authorization") String jwt,
        @PathVariable String coinId) throws Exception {

            User user = userService.findUserProfileByJwt(jwt);
            Coin coin = coinService.findById(coinId);
            Coin addedCoin = watchlistService.addItemToWatchlist(coin, user);

            return ResponseEntity.ok(addedCoin);
    }
    

}
