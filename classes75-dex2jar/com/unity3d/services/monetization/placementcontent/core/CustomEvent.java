// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.core;

import java.util.HashMap;
import java.util.Map;

public class CustomEvent
{
    private String category;
    private Map<String, Object> data;
    private String type;
    
    public CustomEvent() {
    }
    
    private CustomEvent(final Builder builder) {
        this.setCategory(builder.category);
        this.setType(builder.type);
        this.setData(builder.data);
    }
    
    public CustomEvent(final String type) {
        this.type = type;
    }
    
    public CustomEvent(final String category, final String type, final Map<String, Object> data) {
        this.category = category;
        this.type = type;
        this.data = data;
    }
    
    public CustomEvent(final String type, final Map<String, Object> data) {
        this.type = type;
        this.data = data;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public String getCategory() {
        return this.category;
    }
    
    public Map<String, Object> getData() {
        return this.data;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setCategory(final String category) {
        this.category = category;
    }
    
    public void setData(final Map<String, Object> data) {
        this.data = data;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public static final class Builder
    {
        private String category;
        private Map<String, Object> data;
        private String type;
        
        private Builder() {
        }
        
        public CustomEvent build() {
            return new CustomEvent(this, null);
        }
        
        public Builder putAllData(final Map<String, Object> map) {
            if (this.data == null) {
                this.data = new HashMap<String, Object>(map);
                return this;
            }
            this.data.putAll(map);
            return this;
        }
        
        public Builder putData(final String s, final Object o) {
            if (this.data == null) {
                this.data = new HashMap<String, Object>();
            }
            this.data.put(s, o);
            return this;
        }
        
        public Builder withCategory(final String category) {
            this.category = category;
            return this;
        }
        
        public Builder withData(final Map<String, Object> data) {
            this.data = data;
            return this;
        }
        
        public Builder withType(final String type) {
            this.type = type;
            return this;
        }
    }
}
