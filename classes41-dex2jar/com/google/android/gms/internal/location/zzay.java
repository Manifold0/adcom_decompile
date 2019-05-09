// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;

final class zzay implements ListenerHolder$Notifier<LocationListener>
{
    private final /* synthetic */ Location zzdd;
    
    zzay(final zzax zzax, final Location zzdd) {
        this.zzdd = zzdd;
    }
    
    public final void onNotifyListenerFailed() {
    }
}
