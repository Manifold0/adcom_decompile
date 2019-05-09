// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import android.location.Location;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.zzy;

final class zzax extends zzy
{
    private final ListenerHolder<LocationListener> zzda;
    
    zzax(final ListenerHolder<LocationListener> zzda) {
        this.zzda = zzda;
    }
    
    @Override
    public final void onLocationChanged(final Location location) {
        synchronized (this) {
            this.zzda.notifyListener((ListenerHolder$Notifier)new zzay(this, location));
        }
    }
    
    public final void release() {
        synchronized (this) {
            this.zzda.clear();
        }
    }
}
