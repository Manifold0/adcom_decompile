// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.RewardData;

public abstract class s implements AdAdapter
{
    RewardData a;
    int b;
    
    public abstract int a();
    
    public void a(final int b) {
        this.b = b;
    }
    
    public void a(final RewardData a) {
        this.a = a;
    }
    
    public abstract boolean b();
    
    @Override
    public AdPlacementType getPlacementType() {
        return AdPlacementType.REWARDED_VIDEO;
    }
}
