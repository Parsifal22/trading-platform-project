package com.crypto.service;

import java.util.List;

import com.crypto.modal.Asset;
import com.crypto.modal.Coin;
import com.crypto.modal.User;

public interface AssetService {

    Asset creatAsset(User user, Coin coin, double quantity);

    Asset getAssetById(Long assetId) throws Exception;

    Asset getAssetByUserIdAndId(Long userId, Long assetId);

    List<Asset> getUsersAssets(Long userId);

    Asset updateAsset(Long assetId, double quantity) throws Exception;

    Asset findAssetByUserIdAndCoinId(Long userId, String coinId);

    void deleteAsset(Long assetId);

}
