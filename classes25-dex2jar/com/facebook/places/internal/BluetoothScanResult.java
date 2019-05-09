// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

public class BluetoothScanResult
{
    public String payload;
    public int rssi;
    public long timestampNanos;
    
    public BluetoothScanResult(final String payload, final int rssi, final long timestampNanos) {
        this.payload = payload;
        this.rssi = rssi;
        this.timestampNanos = timestampNanos;
    }
}
