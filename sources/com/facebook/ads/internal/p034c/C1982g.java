package com.facebook.ads.internal.p034c;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.internal.p050r.C2078a;
import java.lang.ref.WeakReference;
import java.util.EnumSet;

/* renamed from: com.facebook.ads.internal.c.g */
public class C1982g {
    /* renamed from: a */
    public final Context f4344a;
    /* renamed from: b */
    public final String f4345b;
    @Nullable
    /* renamed from: c */
    public InterstitialAdListener f4346c;
    @Nullable
    /* renamed from: d */
    public String f4347d;
    /* renamed from: e */
    public EnumSet<CacheFlag> f4348e;
    /* renamed from: f */
    public String f4349f;
    /* renamed from: g */
    public long f4350g = -1;
    @Nullable
    /* renamed from: h */
    private InterstitialAd f4351h;
    /* renamed from: i */
    private WeakReference<InterstitialAd> f4352i;

    public C1982g(Context context, @Nullable InterstitialAd interstitialAd, String str) {
        this.f4344a = context;
        this.f4345b = str;
        this.f4351h = interstitialAd;
        this.f4352i = new WeakReference(interstitialAd);
    }

    @Nullable
    /* renamed from: a */
    InterstitialAd m4727a() {
        return this.f4351h != null ? this.f4351h : (InterstitialAd) this.f4352i.get();
    }

    /* renamed from: a */
    public void m4728a(@Nullable InterstitialAd interstitialAd) {
        if (interstitialAd != null || C2078a.m5087Z(this.f4344a)) {
            this.f4351h = interstitialAd;
        }
    }
}
