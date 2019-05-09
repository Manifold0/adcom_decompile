package com.facebook.places.internal;

public class BluetoothScanResult {
    public String payload;
    public int rssi;
    public long timestampNanos;

    public BluetoothScanResult(String payload, int rssi, long timestampNanos) {
        this.payload = payload;
        this.rssi = rssi;
        this.timestampNanos = timestampNanos;
    }
}
