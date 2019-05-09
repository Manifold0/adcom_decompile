// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class PlaceSearchRequestParams
{
    private final Set<String> categories;
    private final int distance;
    private final Set<String> fields;
    private final int limit;
    private final String searchText;
    
    private PlaceSearchRequestParams(final Builder builder) {
        this.categories = new HashSet<String>();
        this.fields = new HashSet<String>();
        this.distance = builder.distance;
        this.limit = builder.limit;
        this.searchText = builder.searchText;
        this.categories.addAll(builder.categories);
        this.fields.addAll(builder.fields);
    }
    
    public Set<String> getCategories() {
        return this.categories;
    }
    
    public int getDistance() {
        return this.distance;
    }
    
    public Set<String> getFields() {
        return this.fields;
    }
    
    public int getLimit() {
        return this.limit;
    }
    
    public String getSearchText() {
        return this.searchText;
    }
    
    public static class Builder
    {
        private final Set<String> categories;
        private int distance;
        private final Set<String> fields;
        private int limit;
        private String searchText;
        
        public Builder() {
            this.categories = new HashSet<String>();
            this.fields = new HashSet<String>();
        }
        
        public Builder addCategory(final String s) {
            this.categories.add(s);
            return this;
        }
        
        public Builder addField(final String s) {
            this.fields.add(s);
            return this;
        }
        
        public PlaceSearchRequestParams build() {
            return new PlaceSearchRequestParams(this, null);
        }
        
        public Builder setDistance(final int distance) {
            this.distance = distance;
            return this;
        }
        
        public Builder setLimit(final int limit) {
            this.limit = limit;
            return this;
        }
        
        public Builder setSearchText(final String searchText) {
            this.searchText = searchText;
            return this;
        }
    }
}
