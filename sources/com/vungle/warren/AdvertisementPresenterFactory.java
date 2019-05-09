package com.vungle.warren;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.presenter.LocalAdPresenter;
import com.vungle.warren.presenter.WebAdPresenter;
import java.io.File;

public class AdvertisementPresenterFactory {
    private static final String EXTRA_ADVERTISEMENT = "ADV_FACTORY_ADVERTISEMENT";
    private static final String TAG = AdvertisementPresenterFactory.class.getSimpleName();
    private Advertisement advertisement;
    private final Storage storage = Vungle._instance.storage;

    public AdvertisementPresenter getAdPresenter(String placementId, Bundle savedInstanceState, String userId) throws IllegalArgumentException, IllegalStateException {
        if (TextUtils.isEmpty(placementId)) {
            throw new IllegalArgumentException("Missing placementID! Cannot play advertisement.");
        } else if (this.storage == null || !Vungle.isInitialized()) {
            throw new IllegalStateException("Vungle SDK is not initialized");
        } else {
            Placement placement = (Placement) this.storage.load(placementId, Placement.class);
            if (placement == null) {
                throw new IllegalArgumentException("No placement for ID " + placementId + " found. Please use a valid placement ID");
            }
            if (savedInstanceState == null) {
                this.advertisement = this.storage.findValidAdvertisementForPlacement(placementId);
            } else {
                String adId = savedInstanceState.getString(EXTRA_ADVERTISEMENT);
                if (!TextUtils.isEmpty(adId)) {
                    this.advertisement = (Advertisement) this.storage.load(adId, Advertisement.class);
                }
            }
            if (Vungle.canPlayAd(this.advertisement)) {
                File assetDir = this.storage.getAdvertisementAssetDirectory(this.advertisement.getId());
                if (assetDir.isDirectory()) {
                    switch (this.advertisement.getAdType()) {
                        case 0:
                            return new LocalAdPresenter(this.advertisement, placement, this.storage, assetDir, userId);
                        case 1:
                            return new WebAdPresenter(this.advertisement, placement, this.storage, assetDir, userId);
                        default:
                            throw new IllegalArgumentException("No presenter available for ad type!");
                    }
                }
                throw new IllegalStateException("No asset directory for " + placementId + " exists!");
            }
            Log.e(TAG, "Advertisement is null or assets are missing");
            return null;
        }
    }

    public void saveState(Bundle bundle) {
        bundle.putString(EXTRA_ADVERTISEMENT, this.advertisement == null ? null : this.advertisement.getId());
    }
}
