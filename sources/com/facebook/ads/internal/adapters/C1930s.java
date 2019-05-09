package com.facebook.ads.internal.adapters;

import com.facebook.ads.RewardData;
import com.facebook.ads.internal.protocol.AdPlacementType;

/* renamed from: com.facebook.ads.internal.adapters.s */
public abstract class C1930s implements AdAdapter {
    /* renamed from: a */
    RewardData f4181a;
    /* renamed from: b */
    int f4182b;

    /* renamed from: a */
    public abstract int mo5412a();

    /* renamed from: a */
    public void m4535a(int i) {
        this.f4182b = i;
    }

    /* renamed from: a */
    public void m4536a(RewardData rewardData) {
        this.f4181a = rewardData;
    }

    /* renamed from: b */
    public abstract boolean mo5413b();

    public AdPlacementType getPlacementType() {
        return AdPlacementType.REWARDED_VIDEO;
    }
}
