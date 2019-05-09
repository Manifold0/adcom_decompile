package com.facebook.ads;

import android.content.Context;
import android.support.annotation.UiThread;
import com.facebook.ads.internal.p034c.C1985i;
import com.facebook.ads.internal.p034c.C1986j;

@UiThread
public class RewardedVideoAd implements Ad {
    public static final int UNSET_VIDEO_DURATION = -1;
    /* renamed from: a */
    private final C1986j f3821a;
    /* renamed from: b */
    private final C1985i f3822b = new C1985i(this.f3821a);

    public RewardedVideoAd(Context context, String str) {
        this.f3821a = new C1986j(context.getApplicationContext(), str, this);
    }

    /* renamed from: a */
    private void m4128a(String str, boolean z) {
        this.f3822b.m4739a(this, str, z);
    }

    public void destroy() {
        this.f3822b.mo5458d();
    }

    protected void finalize() {
        this.f3822b.m4675e();
    }

    public String getPlacementId() {
        return this.f3821a.f4364b;
    }

    public int getVideoDuration() {
        return this.f3821a.f4370h;
    }

    public boolean isAdInvalidated() {
        return this.f3822b.m4744g();
    }

    public boolean isAdLoaded() {
        return this.f3822b.m4743f();
    }

    public void loadAd() {
        m4128a(null, true);
    }

    public void loadAd(boolean z) {
        m4128a(null, z);
    }

    public void loadAdFromBid(String str) {
        m4128a(str, true);
    }

    public void loadAdFromBid(String str, boolean z) {
        m4128a(str, z);
    }

    public void setAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.f3821a.f4365c = rewardedVideoAdListener;
    }

    public void setExtraHints(ExtraHints extraHints) {
        this.f3821a.f4366d = extraHints.getHints();
    }

    public void setRewardData(RewardData rewardData) {
        this.f3822b.m4738a(rewardData);
    }

    public boolean show() {
        return show(-1);
    }

    public boolean show(int i) {
        return this.f3822b.m4740a(this, i);
    }
}
