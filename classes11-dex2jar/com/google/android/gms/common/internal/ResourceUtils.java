// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.net.Uri$Builder;
import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ResourceUtils
{
    private static final Uri zzet;
    
    static {
        zzet = new Uri$Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();
    }
    
    private ResourceUtils() {
    }
}
