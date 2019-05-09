// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c.a;

import com.facebook.ads.AdError;
import android.os.Bundle;
import com.facebook.ads.Ad;
import com.facebook.ads.internal.c.j;
import com.facebook.ads.internal.c.e;
import com.facebook.ads.S2SRewardedVideoAdExtendedListener;

public class d extends a implements S2SRewardedVideoAdExtendedListener
{
    private final e c;
    private final j d;
    
    public d(final String s, final c c, final e c2, final j d) {
        super(s, c);
        this.c = c2;
        this.d = d;
    }
    
    @Override
    public void onAdClicked(final Ad ad) {
        this.b.a(2104, this.a, null);
    }
    
    @Override
    public void onAdLoaded(final Ad ad) {
        final Bundle bundle = new Bundle();
        bundle.putLong("LONG_INVALIDATION_TIME_KEY", this.c.b());
        bundle.putInt("INT_RV_VIDEO_DURATION_KEY", this.d.h);
        this.b.a(2100, this.a, bundle);
    }
    
    @Override
    public void onError(final Ad ad, final AdError adError) {
        final Bundle bundle = new Bundle();
        bundle.putString("STR_ERROR_MESSAGE_KEY", adError.getErrorMessage());
        bundle.putInt("INT_ERROR_CODE_KEY", adError.getErrorCode());
        this.b.a(2103, this.a, bundle);
    }
    
    @Override
    public void onLoggingImpression(final Ad ad) {
        this.b.a(2105, this.a, null);
    }
    
    @Override
    public void onRewardServerFailed() {
        this.b.a(2109, this.a, null);
    }
    
    @Override
    public void onRewardServerSuccess() {
        this.b.a(2108, this.a, null);
    }
    
    @Override
    public void onRewardedVideoActivityDestroyed() {
        this.b.a(2106, this.a, null);
    }
    
    @Override
    public void onRewardedVideoClosed() {
        this.b.a(2110, this.a, null);
    }
    
    @Override
    public void onRewardedVideoCompleted() {
        this.b.a(2107, this.a, null);
    }
}
