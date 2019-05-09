// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core.properties;

import com.unity3d.services.purchasing.core.IPurchasingAdapter;

public class ClientProperties
{
    private static IPurchasingAdapter adapter;
    
    public static IPurchasingAdapter getAdapter() {
        return ClientProperties.adapter;
    }
    
    public static void setAdapter(final IPurchasingAdapter adapter) {
        ClientProperties.adapter = adapter;
    }
}
