// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.purchasing;

public class Item
{
    private String itemId;
    private long quantity;
    private String type;
    
    private Item(final Builder builder) {
        this.itemId = builder.itemId;
        this.quantity = builder.quantity;
        this.type = builder.type;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public String getItemId() {
        return this.itemId;
    }
    
    public long getQuantity() {
        return this.quantity;
    }
    
    public String getType() {
        return this.type;
    }
    
    public static final class Builder
    {
        private String itemId;
        private long quantity;
        private String type;
        
        private Builder() {
        }
        
        public Item build() {
            return new Item(this, null);
        }
        
        public Builder withItemId(final String itemId) {
            this.itemId = itemId;
            return this;
        }
        
        public Builder withQuantity(final long quantity) {
            this.quantity = quantity;
            return this;
        }
        
        public Builder withType(final String type) {
            this.type = type;
            return this;
        }
    }
}
