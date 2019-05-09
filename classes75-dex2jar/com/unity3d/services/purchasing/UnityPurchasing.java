// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing;

import com.unity3d.services.purchasing.core.properties.ClientProperties;
import com.unity3d.services.purchasing.core.IPurchasingAdapter;

public class UnityPurchasing
{
    public static IPurchasingAdapter getAdapter() {
        return ClientProperties.getAdapter();
    }
    
    public static void setAdapter(final IPurchasingAdapter adapter) {
        ClientProperties.setAdapter(adapter);
    }
}
