// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import java.io.File;
import com.vungle.warren.presenter.WebAdPresenter;
import com.vungle.warren.presenter.LocalAdPresenter;
import android.util.Log;
import com.vungle.warren.model.Placement;
import android.text.TextUtils;
import com.vungle.warren.presenter.AdvertisementPresenter;
import android.os.Bundle;
import com.vungle.warren.model.Advertisement;

public class AdvertisementPresenterFactory
{
    private static final String EXTRA_ADVERTISEMENT = "ADV_FACTORY_ADVERTISEMENT";
    private static final String TAG;
    private Advertisement advertisement;
    private final Storage storage;
    
    static {
        TAG = AdvertisementPresenterFactory.class.getSimpleName();
    }
    
    public AdvertisementPresenterFactory() {
        this.storage = Vungle._instance.storage;
    }
    
    public AdvertisementPresenter getAdPresenter(final String s, final Bundle bundle, final String s2) throws IllegalArgumentException, IllegalStateException {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Missing placementID! Cannot play advertisement.");
        }
        if (this.storage == null || !Vungle.isInitialized()) {
            throw new IllegalStateException("Vungle SDK is not initialized");
        }
        final Placement placement = this.storage.load(s, Placement.class);
        if (placement == null) {
            throw new IllegalArgumentException("No placement for ID " + s + " found. Please use a valid placement ID");
        }
        if (bundle == null) {
            this.advertisement = this.storage.findValidAdvertisementForPlacement(s);
        }
        else {
            final String string = bundle.getString("ADV_FACTORY_ADVERTISEMENT");
            if (!TextUtils.isEmpty((CharSequence)string)) {
                this.advertisement = this.storage.load(string, Advertisement.class);
            }
        }
        if (!Vungle.canPlayAd(this.advertisement)) {
            Log.e(AdvertisementPresenterFactory.TAG, "Advertisement is null or assets are missing");
            return null;
        }
        final File advertisementAssetDirectory = this.storage.getAdvertisementAssetDirectory(this.advertisement.getId());
        if (!advertisementAssetDirectory.isDirectory()) {
            throw new IllegalStateException("No asset directory for " + s + " exists!");
        }
        switch (this.advertisement.getAdType()) {
            default: {
                throw new IllegalArgumentException("No presenter available for ad type!");
            }
            case 0: {
                return new LocalAdPresenter(this.advertisement, placement, this.storage, advertisementAssetDirectory, s2);
            }
            case 1: {
                return new WebAdPresenter(this.advertisement, placement, this.storage, advertisementAssetDirectory, s2);
            }
        }
    }
    
    public void saveState(final Bundle bundle) {
        String id;
        if (this.advertisement == null) {
            id = null;
        }
        else {
            id = this.advertisement.getId();
        }
        bundle.putString("ADV_FACTORY_ADVERTISEMENT", id);
    }
}
