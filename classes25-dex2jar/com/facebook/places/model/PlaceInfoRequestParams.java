// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class PlaceInfoRequestParams
{
    private final Set<String> fields;
    private final String placeId;
    
    private PlaceInfoRequestParams(final Builder builder) {
        this.fields = new HashSet<String>();
        this.placeId = builder.placeId;
        this.fields.addAll(builder.fields);
    }
    
    public Set<String> getFields() {
        return this.fields;
    }
    
    public String getPlaceId() {
        return this.placeId;
    }
    
    public static class Builder
    {
        private final Set<String> fields;
        private String placeId;
        
        public Builder() {
            this.fields = new HashSet<String>();
        }
        
        public Builder addField(final String s) {
            this.fields.add(s);
            return this;
        }
        
        public Builder addFields(final String[] array) {
            for (int length = array.length, i = 0; i < length; ++i) {
                this.fields.add(array[i]);
            }
            return this;
        }
        
        public PlaceInfoRequestParams build() {
            return new PlaceInfoRequestParams(this, null);
        }
        
        public Builder setPlaceId(final String placeId) {
            this.placeId = placeId;
            return this;
        }
    }
}
