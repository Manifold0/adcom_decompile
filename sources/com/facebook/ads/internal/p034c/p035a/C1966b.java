package com.facebook.ads.internal.p034c.p035a;

import android.os.Bundle;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.PointerIconCompat;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.facebook.ads.internal.p034c.C1977d;
import com.facebook.ads.internal.p036e.C1992a;

/* renamed from: com.facebook.ads.internal.c.a.b */
public final class C1966b extends C1965a implements InterstitialAdExtendedListener {
    /* renamed from: c */
    private final C1977d f4311c;

    public C1966b(String str, C1967c c1967c, C1977d c1977d) {
        super(str, c1967c);
        this.f4311c = c1977d;
    }

    public void onAdClicked(Ad ad) {
        this.b.mo5445a(1024, this.a, null);
    }

    public void onAdLoaded(Ad ad) {
        Bundle bundle = new Bundle();
        bundle.putLong("LONG_INVALIDATION_TIME_KEY", this.f4311c.m4693b());
        this.b.mo5445a(PointerIconCompat.TYPE_GRAB, this.a, bundle);
    }

    public void onError(Ad ad, AdError adError) {
        Bundle bundle = new Bundle();
        bundle.putString("STR_ERROR_MESSAGE_KEY", adError.getErrorMessage());
        bundle.putInt("INT_ERROR_CODE_KEY", adError.getErrorCode());
        this.b.mo5445a(1023, this.a, bundle);
    }

    public void onInterstitialActivityDestroyed() {
        this.b.mo5445a(1026, this.a, null);
        C1992a.m4771a().m4779c(this.a);
    }

    public void onInterstitialDismissed(Ad ad) {
        this.b.mo5445a(1022, this.a, null);
    }

    public void onInterstitialDisplayed(Ad ad) {
        this.b.mo5445a(PointerIconCompat.TYPE_GRABBING, this.a, null);
    }

    public void onLoggingImpression(Ad ad) {
        this.b.mo5445a(InputDeviceCompat.SOURCE_GAMEPAD, this.a, null);
    }
}
