// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;
import android.os.Bundle;
import com.facebook.ads.internal.w.b.r;

public abstract class n implements AdAdapter, r<Bundle>
{
    public abstract boolean e();
    
    @Override
    public AdPlacementType getPlacementType() {
        return AdPlacementType.INSTREAM;
    }
}
