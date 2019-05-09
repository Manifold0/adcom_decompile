// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;

final class zzav implements ListenerHolder$Notifier<LocationCallback>
{
    private final /* synthetic */ LocationAvailability zzdc;
    
    zzav(final zzat zzat, final LocationAvailability zzdc) {
        this.zzdc = zzdc;
    }
    
    public final void onNotifyListenerFailed() {
    }
}
