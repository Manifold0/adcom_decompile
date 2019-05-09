// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.model;

import java.util.Collection;
import java.util.HashSet;
import android.location.Location;
import java.util.Set;

public class CurrentPlaceRequestParams
{
    private final Set<String> fields;
    private final int limit;
    private final Location location;
    private final ConfidenceLevel minConfidenceLevel;
    private final ScanMode scanMode;
    
    private CurrentPlaceRequestParams(final Builder builder) {
        this.fields = new HashSet<String>();
        this.location = builder.location;
        this.scanMode = builder.scanMode;
        this.minConfidenceLevel = builder.minConfidenceLevel;
        this.limit = builder.limit;
        this.fields.addAll(builder.fields);
    }
    
    public Set<String> getFields() {
        return this.fields;
    }
    
    public int getLimit() {
        return this.limit;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public ConfidenceLevel getMinConfidenceLevel() {
        return this.minConfidenceLevel;
    }
    
    public ScanMode getScanMode() {
        return this.scanMode;
    }
    
    public static class Builder
    {
        private final Set<String> fields;
        private int limit;
        private Location location;
        private ConfidenceLevel minConfidenceLevel;
        private ScanMode scanMode;
        
        public Builder() {
            this.scanMode = ScanMode.HIGH_ACCURACY;
            this.fields = new HashSet<String>();
        }
        
        public Builder addField(final String s) {
            this.fields.add(s);
            return this;
        }
        
        public CurrentPlaceRequestParams build() {
            return new CurrentPlaceRequestParams(this, null);
        }
        
        public Builder setLimit(final int limit) {
            this.limit = limit;
            return this;
        }
        
        public Builder setLocation(final Location location) {
            this.location = location;
            return this;
        }
        
        public Builder setMinConfidenceLevel(final ConfidenceLevel minConfidenceLevel) {
            this.minConfidenceLevel = minConfidenceLevel;
            return this;
        }
        
        public Builder setScanMode(final ScanMode scanMode) {
            this.scanMode = scanMode;
            return this;
        }
    }
    
    public enum ConfidenceLevel
    {
        HIGH, 
        LOW, 
        MEDIUM;
    }
    
    public enum ScanMode
    {
        HIGH_ACCURACY, 
        LOW_LATENCY;
    }
}
