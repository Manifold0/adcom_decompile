package com.unity3d.services.monetization.placementcontent.purchasing;

public class Item {
    private String itemId;
    private long quantity;
    private String type;

    public static final class Builder {
        private String itemId;
        private long quantity;
        private String type;

        private Builder() {
        }

        public Builder withItemId(String val) {
            this.itemId = val;
            return this;
        }

        public Builder withQuantity(long val) {
            this.quantity = val;
            return this;
        }

        public Builder withType(String val) {
            this.type = val;
            return this;
        }

        public Item build() {
            return new Item();
        }
    }

    private Item(Builder builder) {
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
}
