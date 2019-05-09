// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.bluetooth.BluetoothDevice;

public final class DiscoveredEndpointInfo
{
    private final String zzq;
    private final String zzu;
    @Nullable
    private final BluetoothDevice zzv;
    
    public DiscoveredEndpointInfo(final String zzu, final BluetoothDevice zzv) {
        this.zzu = zzu;
        this.zzq = "__UNRECOGNIZED_BLUETOOTH_DEVICE__";
        this.zzv = zzv;
    }
    
    @Deprecated
    public DiscoveredEndpointInfo(final String zzu, final String zzq) {
        this.zzu = zzu;
        this.zzq = zzq;
        this.zzv = null;
    }
    
    @NonNull
    public final String getEndpointName() {
        return this.zzq;
    }
    
    @NonNull
    public final String getServiceId() {
        return this.zzu;
    }
}
