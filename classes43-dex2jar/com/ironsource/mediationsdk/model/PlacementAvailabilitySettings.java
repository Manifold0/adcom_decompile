// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public class PlacementAvailabilitySettings
{
    private PlacementCappingType cappingType;
    private int cappingValue;
    private boolean isCappingEnabled;
    private boolean isDeliveryEnabled;
    private boolean isPacingEnabled;
    private int pacingValue;
    
    private PlacementAvailabilitySettings(final boolean isDeliveryEnabled, final boolean isCappingEnabled, final boolean isPacingEnabled, final PlacementCappingType cappingType, final int cappingValue, final int pacingValue) {
        this.isDeliveryEnabled = isDeliveryEnabled;
        this.isCappingEnabled = isCappingEnabled;
        this.isPacingEnabled = isPacingEnabled;
        this.cappingType = cappingType;
        this.cappingValue = cappingValue;
        this.pacingValue = pacingValue;
    }
    
    public PlacementCappingType getCappingType() {
        return this.cappingType;
    }
    
    public int getCappingValue() {
        return this.cappingValue;
    }
    
    public int getPacingValue() {
        return this.pacingValue;
    }
    
    public boolean isCappingEnabled() {
        return this.isCappingEnabled;
    }
    
    public boolean isDeliveryEnabled() {
        return this.isDeliveryEnabled;
    }
    
    public boolean isPacingEnabled() {
        return this.isPacingEnabled;
    }
    
    public static class PlacementAvailabilitySettingsBuilder
    {
        private PlacementCappingType cappingType;
        private int cappingValue;
        private boolean isCappingEnabled;
        private boolean isDeliveryEnabled;
        private boolean isPacingEnabled;
        private int pacingValue;
        
        public PlacementAvailabilitySettingsBuilder() {
            this.isDeliveryEnabled = true;
            this.isCappingEnabled = false;
            this.isPacingEnabled = false;
            this.cappingType = null;
            this.cappingValue = 0;
            this.pacingValue = 0;
        }
        
        public PlacementAvailabilitySettings build() {
            return new PlacementAvailabilitySettings(this.isDeliveryEnabled, this.isCappingEnabled, this.isPacingEnabled, this.cappingType, this.cappingValue, this.pacingValue, null);
        }
        
        public PlacementAvailabilitySettingsBuilder capping(final boolean isCappingEnabled, final PlacementCappingType placementCappingType, final int cappingValue) {
            this.isCappingEnabled = isCappingEnabled;
            PlacementCappingType per_DAY = placementCappingType;
            if (placementCappingType == null) {
                per_DAY = PlacementCappingType.PER_DAY;
            }
            this.cappingType = per_DAY;
            this.cappingValue = cappingValue;
            return this;
        }
        
        public PlacementAvailabilitySettingsBuilder delivery(final boolean isDeliveryEnabled) {
            this.isDeliveryEnabled = isDeliveryEnabled;
            return this;
        }
        
        public PlacementAvailabilitySettingsBuilder pacing(final boolean isPacingEnabled, final int pacingValue) {
            this.isPacingEnabled = isPacingEnabled;
            this.pacingValue = pacingValue;
            return this;
        }
    }
}
