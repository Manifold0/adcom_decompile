// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c.a;

import com.facebook.ads.AdError;
import android.os.Bundle;
import com.facebook.ads.Ad;
import com.facebook.ads.internal.c.d;
import com.facebook.ads.InterstitialAdExtendedListener;

public final class b extends a implements InterstitialAdExtendedListener
{
    private final d c;
    
    public b(final String s, final c c, final d c2) {
        super(s, c);
        this.c = c2;
    }
    
    @Override
    public void onAdClicked(final Ad ad) {
        this.b.a(1024, this.a, null);
    }
    
    @Override
    public void onAdLoaded(final Ad ad) {
        final Bundle bundle = new Bundle();
        bundle.putLong("LONG_INVALIDATION_TIME_KEY", this.c.b());
        this.b.a(1020, this.a, bundle);
    }
    
    @Override
    public void onError(final Ad ad, final AdError adError) {
        final Bundle bundle = new Bundle();
        bundle.putString("STR_ERROR_MESSAGE_KEY", adError.getErrorMessage());
        bundle.putInt("INT_ERROR_CODE_KEY", adError.getErrorCode());
        this.b.a(1023, this.a, bundle);
    }
    
    @Override
    public void onInterstitialActivityDestroyed() {
        this.b.a(1026, this.a, null);
        com.facebook.ads.internal.e.a.a().c(this.a);
    }
    
    @Override
    public void onInterstitialDismissed(final Ad ad) {
        this.b.a(1022, this.a, null);
    }
    
    @Override
    public void onInterstitialDisplayed(final Ad ad) {
        this.b.a(1021, this.a, null);
    }
    
    @Override
    public void onLoggingImpression(final Ad ad) {
        this.b.a(1025, this.a, null);
    }
}
