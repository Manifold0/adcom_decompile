// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.zzv;

final class zzat extends zzv
{
    private final ListenerHolder<LocationCallback> zzda;
    
    zzat(final ListenerHolder<LocationCallback> zzda) {
        this.zzda = zzda;
    }
    
    @Override
    public final void onLocationAvailability(final LocationAvailability locationAvailability) {
        this.zzda.notifyListener((ListenerHolder$Notifier)new zzav(this, locationAvailability));
    }
    
    @Override
    public final void onLocationResult(final LocationResult locationResult) {
        this.zzda.notifyListener((ListenerHolder$Notifier)new zzau(this, locationResult));
    }
    
    public final void release() {
        synchronized (this) {
            this.zzda.clear();
        }
    }
}
