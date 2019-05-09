package com.facebook.ads.internal.p034c;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.facebook.ads.internal.p050r.C2078a;
import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.c.j */
public class C1986j {
    /* renamed from: a */
    public final Context f4363a;
    /* renamed from: b */
    public final String f4364b;
    @Nullable
    /* renamed from: c */
    public RewardedVideoAdListener f4365c;
    @Nullable
    /* renamed from: d */
    public String f4366d;
    @Nullable
    /* renamed from: e */
    public RewardData f4367e;
    @Nullable
    /* renamed from: f */
    public String f4368f;
    /* renamed from: g */
    public boolean f4369g;
    /* renamed from: h */
    public int f4370h = -1;
    /* renamed from: i */
    public long f4371i;
    @Nullable
    /* renamed from: j */
    private RewardedVideoAd f4372j;
    /* renamed from: k */
    private WeakReference<RewardedVideoAd> f4373k;

    public C1986j(Context context, String str, @Nullable RewardedVideoAd rewardedVideoAd) {
        this.f4363a = context;
        this.f4364b = str;
        this.f4372j = rewardedVideoAd;
        this.f4373k = new WeakReference(rewardedVideoAd);
        this.f4371i = -1;
    }

    @Nullable
    /* renamed from: a */
    RewardedVideoAd m4745a() {
        return this.f4372j != null ? this.f4372j : (RewardedVideoAd) this.f4373k.get();
    }

    /* renamed from: a */
    public void m4746a(@Nullable RewardedVideoAd rewardedVideoAd) {
        if (rewardedVideoAd != null || C2078a.m5087Z(this.f4363a)) {
            this.f4372j = rewardedVideoAd;
        }
    }
}
