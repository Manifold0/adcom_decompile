package com.unity.purchasing.common;

public class ProductDefinition {
    public String id;
    public String storeSpecificId;
    public ProductType type;

    public ProductDefinition(String id, String storeSpecificId, ProductType type) {
        this.id = id;
        this.storeSpecificId = storeSpecificId;
        this.type = type;
    }

    public ProductDefinition(String id, ProductType type) {
        this(id, id, type);
    }
}
