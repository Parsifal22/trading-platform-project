package com.crypto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.modal.Coin;
import com.crypto.modal.User;
import com.crypto.modal.Watchlist;
import com.crypto.repository.WatchlistRepository;

@Service
public class WatchlistServiceImpl implements WatchlistService{

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Override
    public Watchlist findUserWatchlist(Long userId) throws Exception {
        Watchlist watchlist = watchlistRepository.findByUserId(userId);

        if(watchlist==null){
            throw new Exception("Watchlist not found");
        }

        return watchlist;
    }

    @Override
    public Watchlist createWatchList(User user) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(user);

        return watchlistRepository.save(watchlist);
    }

    @Override
    public Watchlist findById(Long id) throws Exception {
        Optional<Watchlist> optionalWatchlist = watchlistRepository.findById(id);

        if(optionalWatchlist.isEmpty()){
            throw new Exception("Watchlist not found");
        }

        return optionalWatchlist.get();

    }

    @Override
    public Coin addItemToWatchlist(Coin coin, User user) throws Exception {

        Watchlist watchlist = findUserWatchlist(user.getId());

        if (watchlist.getCoins().contains(coin)){
            watchlist.getCoins().remove(coin);
        } else {
            watchlist.getCoins().add(coin);
        }
        
        watchlistRepository.save(watchlist);
        return coin;
        
    }
}
