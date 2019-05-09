// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

public class LocationPackageRequestParams
{
    private static final boolean DEFAULT_BLUETOOTH_ENABLED = true;
    private static final long DEFAULT_BLUETOOTH_FLUSH_RESULTS_TIMEOUT_MS = 300L;
    private static final int DEFAULT_BLUETOOTH_MAX_SCAN_RESULTS = 25;
    private static final long DEFAULT_BLUETOOTH_SCAN_DURATION_MS = 500L;
    private static final long DEFAULT_LAST_LOCATION_MAX_AGE_MS = 60000L;
    private static final boolean DEFAULT_LOCATION_ENABLED = true;
    private static final float DEFAULT_LOCATION_MAX_ACCURACY_METERS = 100.0f;
    private static final String[] DEFAULT_LOCATION_PROVIDERS;
    private static final long DEFAULT_LOCATION_REQUEST_TIMEOUT_MS = 30000L;
    private static final boolean DEFAULT_WIFI_ACTIVE_SCAN_ALLOWED = true;
    private static final boolean DEFAULT_WIFI_ACTIVE_SCAN_FORCED = false;
    private static final boolean DEFAULT_WIFI_ENABLED = true;
    private static final int DEFAULT_WIFI_MAX_SCAN_RESULTS = 25;
    private static final long DEFAULT_WIFI_SCAN_MAX_AGE_MS = 30000L;
    private static final long DEFAULT_WIFI_SCAN_TIMEOUT_MS = 6000L;
    private long bluetoothFlushResultsTimeoutMs;
    private int bluetoothMaxScanResults;
    private long bluetoothScanDurationMs;
    private boolean isBluetoothScanEnabled;
    private boolean isLocationScanEnabled;
    private boolean isWifiActiveScanAllowed;
    private boolean isWifiActiveScanForced;
    private boolean isWifiScanEnabled;
    private long lastLocationMaxAgeMs;
    private float locationMaxAccuracyMeters;
    private final String[] locationProviders;
    private long locationRequestTimeoutMs;
    private int wifiMaxScanResults;
    private long wifiScanMaxAgeMs;
    private long wifiScanTimeoutMs;
    
    static {
        DEFAULT_LOCATION_PROVIDERS = new String[] { "network", "gps" };
    }
    
    private LocationPackageRequestParams(final Builder builder) {
        this.isLocationScanEnabled = builder.isLocationScanEnabled;
        this.locationProviders = builder.locationProviders;
        this.locationMaxAccuracyMeters = builder.locationMaxAccuracyMeters;
        this.locationRequestTimeoutMs = builder.locationRequestTimeoutMs;
        this.lastLocationMaxAgeMs = builder.lastLocationMaxAgeMs;
        this.isWifiScanEnabled = builder.isWifiScanEnabled;
        this.wifiScanMaxAgeMs = builder.wifiScanMaxAgeMs;
        this.wifiMaxScanResults = builder.wifiMaxScanResults;
        this.wifiScanTimeoutMs = builder.wifiScanTimeoutMs;
        this.isWifiActiveScanAllowed = builder.isWifiActiveScanAllowed;
        this.isWifiActiveScanForced = builder.isWifiActiveScanForced;
        this.isBluetoothScanEnabled = builder.isBluetoothScanEnabled;
        this.bluetoothScanDurationMs = builder.bluetoothScanDurationMs;
        this.bluetoothMaxScanResults = builder.bluetoothMaxScanResults;
        this.bluetoothFlushResultsTimeoutMs = builder.bluetoothFlushResultsTimeoutMs;
    }
    
    public long getBluetoothFlushResultsTimeoutMs() {
        return this.bluetoothFlushResultsTimeoutMs;
    }
    
    public int getBluetoothMaxScanResults() {
        return this.bluetoothMaxScanResults;
    }
    
    public long getBluetoothScanDurationMs() {
        return this.bluetoothScanDurationMs;
    }
    
    public long getLastLocationMaxAgeMs() {
        return this.lastLocationMaxAgeMs;
    }
    
    public float getLocationMaxAccuracyMeters() {
        return this.locationMaxAccuracyMeters;
    }
    
    public String[] getLocationProviders() {
        return this.locationProviders;
    }
    
    public long getLocationRequestTimeoutMs() {
        return this.locationRequestTimeoutMs;
    }
    
    public int getWifiMaxScanResults() {
        return this.wifiMaxScanResults;
    }
    
    public long getWifiScanMaxAgeMs() {
        return this.wifiScanMaxAgeMs;
    }
    
    public long getWifiScanTimeoutMs() {
        return this.wifiScanTimeoutMs;
    }
    
    public boolean isBluetoothScanEnabled() {
        return this.isBluetoothScanEnabled;
    }
    
    public boolean isLocationScanEnabled() {
        return this.isLocationScanEnabled;
    }
    
    public boolean isWifiActiveScanAllowed() {
        return this.isWifiActiveScanAllowed;
    }
    
    public boolean isWifiActiveScanForced() {
        return this.isWifiActiveScanForced;
    }
    
    public boolean isWifiScanEnabled() {
        return this.isWifiScanEnabled;
    }
    
    public static class Builder
    {
        private long bluetoothFlushResultsTimeoutMs;
        private int bluetoothMaxScanResults;
        private long bluetoothScanDurationMs;
        private boolean isBluetoothScanEnabled;
        private boolean isLocationScanEnabled;
        private boolean isWifiActiveScanAllowed;
        private boolean isWifiActiveScanForced;
        private boolean isWifiScanEnabled;
        private long lastLocationMaxAgeMs;
        private float locationMaxAccuracyMeters;
        private String[] locationProviders;
        private long locationRequestTimeoutMs;
        private int wifiMaxScanResults;
        private long wifiScanMaxAgeMs;
        private long wifiScanTimeoutMs;
        
        public Builder() {
            this.isLocationScanEnabled = true;
            this.locationProviders = LocationPackageRequestParams.DEFAULT_LOCATION_PROVIDERS;
            this.locationMaxAccuracyMeters = 100.0f;
            this.locationRequestTimeoutMs = 30000L;
            this.lastLocationMaxAgeMs = 60000L;
            this.isWifiScanEnabled = true;
            this.wifiScanMaxAgeMs = 30000L;
            this.wifiMaxScanResults = 25;
            this.wifiScanTimeoutMs = 6000L;
            this.isWifiActiveScanAllowed = true;
            this.isWifiActiveScanForced = false;
            this.isBluetoothScanEnabled = true;
            this.bluetoothScanDurationMs = 500L;
            this.bluetoothMaxScanResults = 25;
            this.bluetoothFlushResultsTimeoutMs = 300L;
        }
        
        public LocationPackageRequestParams build() {
            return new LocationPackageRequestParams(this, null);
        }
        
        public Builder setBluetoothFlushResultsTimeoutMs(final long bluetoothFlushResultsTimeoutMs) {
            this.bluetoothFlushResultsTimeoutMs = bluetoothFlushResultsTimeoutMs;
            return this;
        }
        
        public Builder setBluetoothMaxScanResults(final int bluetoothMaxScanResults) {
            this.bluetoothMaxScanResults = bluetoothMaxScanResults;
            return this;
        }
        
        public Builder setBluetoothScanDurationMs(final long bluetoothScanDurationMs) {
            this.bluetoothScanDurationMs = bluetoothScanDurationMs;
            return this;
        }
        
        public Builder setBluetoothScanEnabled(final boolean isBluetoothScanEnabled) {
            this.isBluetoothScanEnabled = isBluetoothScanEnabled;
            return this;
        }
        
        public Builder setLastLocationMaxAgeMs(final long lastLocationMaxAgeMs) {
            this.lastLocationMaxAgeMs = lastLocationMaxAgeMs;
            return this;
        }
        
        public Builder setLocationMaxAccuracyMeters(final float locationMaxAccuracyMeters) {
            this.locationMaxAccuracyMeters = locationMaxAccuracyMeters;
            return this;
        }
        
        public Builder setLocationProviders(final String[] locationProviders) {
            this.locationProviders = locationProviders;
            return this;
        }
        
        public Builder setLocationRequestTimeoutMs(final long locationRequestTimeoutMs) {
            this.locationRequestTimeoutMs = locationRequestTimeoutMs;
            return this;
        }
        
        public Builder setLocationScanEnabled(final boolean isLocationScanEnabled) {
            this.isLocationScanEnabled = isLocationScanEnabled;
            return this;
        }
        
        public Builder setWifiActiveScanAllowed(final boolean isWifiActiveScanAllowed) {
            this.isWifiActiveScanAllowed = isWifiActiveScanAllowed;
            return this;
        }
        
        public Builder setWifiActiveScanForced(final boolean isWifiActiveScanForced) {
            this.isWifiActiveScanForced = isWifiActiveScanForced;
            return this;
        }
        
        public Builder setWifiMaxScanResults(final int wifiMaxScanResults) {
            this.wifiMaxScanResults = wifiMaxScanResults;
            return this;
        }
        
        public Builder setWifiScanEnabled(final boolean isWifiScanEnabled) {
            this.isWifiScanEnabled = isWifiScanEnabled;
            return this;
        }
        
        public Builder setWifiScanMaxAgeMs(final long wifiScanMaxAgeMs) {
            this.wifiScanMaxAgeMs = wifiScanMaxAgeMs;
            return this;
        }
        
        public Builder setWifiScanTimeoutMs(final long wifiScanTimeoutMs) {
            this.wifiScanTimeoutMs = wifiScanTimeoutMs;
            return this;
        }
    }
}
