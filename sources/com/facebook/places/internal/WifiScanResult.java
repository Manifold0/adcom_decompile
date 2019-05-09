package com.facebook.places.internal;

public class WifiScanResult {
    public String bssid;
    public int frequency;
    public int rssi;
    public String ssid;
    public long timestampMs;

    public WifiScanResult(String ssid, String bssid, int rssi, int frequency, long timestampMs) {
        this.ssid = ssid;
        this.bssid = bssid;
        this.rssi = rssi;
        this.frequency = frequency;
        this.timestampMs = timestampMs;
    }
}
