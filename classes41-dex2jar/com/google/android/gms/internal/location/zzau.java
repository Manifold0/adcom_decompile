// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;

final class zzau implements ListenerHolder$Notifier<LocationCallback>
{
    private final /* synthetic */ LocationResult zzdb;
    
    zzau(final zzat zzat, final LocationResult zzdb) {
        this.zzdb = zzdb;
    }
    
    public final void onNotifyListenerFailed() {
    }
}
