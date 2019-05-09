// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.purchasing;

import com.unity3d.services.purchasing.core.Product;
import java.util.Date;
import java.util.Map;
import java.util.List;

public class PromoMetadata
{
    private List<Item> costs;
    private Map<String, Object> customInfo;
    private Date impressionDate;
    private long offerDuration;
    private List<Item> payouts;
    private Product premiumProduct;
    
    private PromoMetadata(final Builder builder) {
        this.impressionDate = builder.impressionDate;
        this.offerDuration = builder.offerDuration;
        this.premiumProduct = builder.premiumProduct;
        this.costs = builder.costs;
        this.payouts = builder.payouts;
        this.customInfo = builder.customInfo;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public Item getCost() {
        if (this.costs != null && this.costs.size() > 0) {
            return this.costs.get(0);
        }
        return null;
    }
    
    public List<Item> getCosts() {
        return this.costs;
    }
    
    public Map<String, Object> getCustomInfo() {
        return this.customInfo;
    }
    
    public Date getImpressionDate() {
        return this.impressionDate;
    }
    
    public long getOfferDuration() {
        return this.offerDuration;
    }
    
    public Item getPayout() {
        if (this.payouts != null && this.payouts.size() > 0) {
            return this.payouts.get(0);
        }
        return null;
    }
    
    public List<Item> getPayouts() {
        return this.payouts;
    }
    
    public Product getPremiumProduct() {
        return this.premiumProduct;
    }
    
    public static final class Builder
    {
        private List<Item> costs;
        private Map<String, Object> customInfo;
        private Date impressionDate;
        private long offerDuration;
        private List<Item> payouts;
        private Product premiumProduct;
        
        private Builder() {
        }
        
        public PromoMetadata build() {
            return new PromoMetadata(this, null);
        }
        
        public Builder withCosts(final List<Item> costs) {
            this.costs = costs;
            return this;
        }
        
        public Builder withCustomInfo(final Map<String, Object> customInfo) {
            this.customInfo = customInfo;
            return this;
        }
        
        public Builder withImpressionDate(final Date impressionDate) {
            this.impressionDate = impressionDate;
            return this;
        }
        
        public Builder withOfferDuration(final long offerDuration) {
            this.offerDuration = offerDuration;
            return this;
        }
        
        public Builder withPayouts(final List<Item> payouts) {
            this.payouts = payouts;
            return this;
        }
        
        public Builder withPremiumProduct(final Product premiumProduct) {
            this.premiumProduct = premiumProduct;
            return this;
        }
    }
}
