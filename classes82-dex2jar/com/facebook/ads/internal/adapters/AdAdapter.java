// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;
import android.support.annotation.Nullable;

public interface AdAdapter
{
    @Nullable
    String getClientToken();
    
    AdPlacementType getPlacementType();
    
    void onDestroy();
}
