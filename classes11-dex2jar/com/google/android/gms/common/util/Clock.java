// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@ShowFirstParty
public interface Clock
{
    @KeepForSdk
    long currentThreadTimeMillis();
    
    @KeepForSdk
    long currentTimeMillis();
    
    @KeepForSdk
    long elapsedRealtime();
    
    @KeepForSdk
    long nanoTime();
}
