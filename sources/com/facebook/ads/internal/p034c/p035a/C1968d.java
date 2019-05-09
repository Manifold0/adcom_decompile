package com.facebook.ads.internal.p034c.p035a;

import android.os.Bundle;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.S2SRewardedVideoAdExtendedListener;
import com.facebook.ads.internal.p034c.C1980e;
import com.facebook.ads.internal.p034c.C1986j;

/* renamed from: com.facebook.ads.internal.c.a.d */
public class C1968d extends C1965a implements S2SRewardedVideoAdExtendedListener {
    /* renamed from: c */
    private final C1980e f4312c;
    /* renamed from: d */
    private final C1986j f4313d;

    public C1968d(String str, C1967c c1967c, C1980e c1980e, C1986j c1986j) {
        super(str, c1967c);
        this.f4312c = c1980e;
        this.f4313d = c1986j;
    }

    public void onAdClicked(Ad ad) {
        this.b.mo5445a(2104, this.a, null);
    }

    public void onAdLoaded(Ad ad) {
        Bundle bundle = new Bundle();
        bundle.putLong("LONG_INVALIDATION_TIME_KEY", this.f4312c.m4715b());
        bundle.putInt("INT_RV_VIDEO_DURATION_KEY", this.f4313d.f4370h);
        this.b.mo5445a(AdError.BROKEN_MEDIA_ERROR_CODE, this.a, bundle);
    }

    public void onError(Ad ad, AdError adError) {
        Bundle bundle = new Bundle();
        bundle.putString("STR_ERROR_MESSAGE_KEY", adError.getErrorMessage());
        bundle.putInt("INT_ERROR_CODE_KEY", adError.getErrorCode());
        this.b.mo5445a(2103, this.a, bundle);
    }

    public void onLoggingImpression(Ad ad) {
        this.b.mo5445a(2105, this.a, null);
    }

    public void onRewardServerFailed() {
        this.b.mo5445a(2109, this.a, null);
    }

    public void onRewardServerSuccess() {
        this.b.mo5445a(2108, this.a, null);
    }

    public void onRewardedVideoActivityDestroyed() {
        this.b.mo5445a(2106, this.a, null);
    }

    public void onRewardedVideoClosed() {
        this.b.mo5445a(2110, this.a, null);
    }

    public void onRewardedVideoCompleted() {
        this.b.mo5445a(2107, this.a, null);
    }
}
