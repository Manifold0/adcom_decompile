// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.model;

public class CurrentPlaceFeedbackRequestParams
{
    private final String placeId;
    private final String tracking;
    private final Boolean wasHere;
    
    private CurrentPlaceFeedbackRequestParams(final Builder builder) {
        this.tracking = builder.tracking;
        this.placeId = builder.placeId;
        this.wasHere = builder.wasHere;
    }
    
    public String getPlaceId() {
        return this.placeId;
    }
    
    public String getTracking() {
        return this.tracking;
    }
    
    public Boolean wasHere() {
        return this.wasHere;
    }
    
    public static class Builder
    {
        private String placeId;
        private String tracking;
        private Boolean wasHere;
        
        public CurrentPlaceFeedbackRequestParams build() {
            return new CurrentPlaceFeedbackRequestParams(this, null);
        }
        
        public Builder setPlaceId(final String placeId) {
            this.placeId = placeId;
            return this;
        }
        
        public Builder setTracking(final String tracking) {
            this.tracking = tracking;
            return this;
        }
        
        public Builder setWasHere(final boolean b) {
            this.wasHere = b;
            return this;
        }
    }
}
