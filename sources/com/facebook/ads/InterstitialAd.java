package com.facebook.ads;

import android.content.Context;
import android.support.annotation.UiThread;
import com.facebook.ads.internal.p034c.C1981f;
import com.facebook.ads.internal.p034c.C1982g;
import java.util.EnumSet;

@UiThread
public class InterstitialAd implements Ad {
    /* renamed from: a */
    private final C1982g f3757a;
    /* renamed from: b */
    private final C1981f f3758b = new C1981f(this.f3757a);

    public InterstitialAd(Context context, String str) {
        this.f3757a = new C1982g(context.getApplicationContext(), this, str);
    }

    /* renamed from: a */
    private void m4027a(EnumSet<CacheFlag> enumSet, String str) {
        this.f3758b.m4721a(this, enumSet, str);
    }

    public void destroy() {
        this.f3758b.mo5458d();
    }

    protected void finalize() {
        this.f3758b.m4675e();
    }

    public String getPlacementId() {
        return this.f3757a.f4345b;
    }

    public boolean isAdInvalidated() {
        return this.f3758b.m4726g();
    }

    public boolean isAdLoaded() {
        return this.f3758b.m4725f();
    }

    public void loadAd() {
        loadAd(CacheFlag.ALL);
    }

    public void loadAd(EnumSet<CacheFlag> enumSet) {
        m4027a(enumSet, null);
    }

    public void loadAdFromBid(String str) {
        m4027a(CacheFlag.ALL, str);
    }

    public void loadAdFromBid(EnumSet<CacheFlag> enumSet, String str) {
        m4027a(enumSet, str);
    }

    public void setAdListener(InterstitialAdListener interstitialAdListener) {
        this.f3757a.f4346c = interstitialAdListener;
    }

    public void setExtraHints(ExtraHints extraHints) {
        this.f3757a.f4347d = extraHints.getHints();
    }

    public boolean show() {
        return this.f3758b.m4722a(this);
    }
}
