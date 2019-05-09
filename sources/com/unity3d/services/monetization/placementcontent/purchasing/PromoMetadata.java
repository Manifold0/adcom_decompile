package com.unity3d.services.monetization.placementcontent.purchasing;

import com.unity3d.services.purchasing.core.Product;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PromoMetadata {
    private List<Item> costs;
    private Map<String, Object> customInfo;
    private Date impressionDate;
    private long offerDuration;
    private List<Item> payouts;
    private Product premiumProduct;

    public static final class Builder {
        private List<Item> costs;
        private Map<String, Object> customInfo;
        private Date impressionDate;
        private long offerDuration;
        private List<Item> payouts;
        private Product premiumProduct;

        private Builder() {
        }

        public PromoMetadata build() {
            return new PromoMetadata();
        }

        public Builder withImpressionDate(Date impressionDate) {
            this.impressionDate = impressionDate;
            return this;
        }

        public Builder withOfferDuration(long val) {
            this.offerDuration = val;
            return this;
        }

        public Builder withPremiumProduct(Product product) {
            this.premiumProduct = product;
            return this;
        }

        public Builder withCosts(List<Item> costs) {
            this.costs = costs;
            return this;
        }

        public Builder withPayouts(List<Item> payouts) {
            this.payouts = payouts;
            return this;
        }

        public Builder withCustomInfo(Map<String, Object> info) {
            this.customInfo = info;
            return this;
        }
    }

    private PromoMetadata(Builder builder) {
        this.impressionDate = builder.impressionDate;
        this.offerDuration = builder.offerDuration;
        this.premiumProduct = builder.premiumProduct;
        this.costs = builder.costs;
        this.payouts = builder.payouts;
        this.customInfo = builder.customInfo;
    }

    public Date getImpressionDate() {
        return this.impressionDate;
    }

    public long getOfferDuration() {
        return this.offerDuration;
    }

    public List<Item> getCosts() {
        return this.costs;
    }

    public List<Item> getPayouts() {
        return this.payouts;
    }

    public Product getPremiumProduct() {
        return this.premiumProduct;
    }

    public Map<String, Object> getCustomInfo() {
        return this.customInfo;
    }

    public Item getCost() {
        if (this.costs == null || this.costs.size() <= 0) {
            return null;
        }
        return (Item) this.costs.get(0);
    }

    public Item getPayout() {
        if (this.payouts == null || this.payouts.size() <= 0) {
            return null;
        }
        return (Item) this.payouts.get(0);
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
