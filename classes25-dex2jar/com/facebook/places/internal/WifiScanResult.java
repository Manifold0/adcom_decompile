// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

public class WifiScanResult
{
    public String bssid;
    public int frequency;
    public int rssi;
    public String ssid;
    public long timestampMs;
    
    public WifiScanResult() {
    }
    
    public WifiScanResult(final String ssid, final String bssid, final int rssi, final int frequency, final long timestampMs) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rssi = rssi;
        this.frequency = frequency;
        this.timestampMs = timestampMs;
    }
}
