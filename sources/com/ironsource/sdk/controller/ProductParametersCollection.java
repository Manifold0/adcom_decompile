package com.ironsource.sdk.controller;

import com.ironsource.sdk.data.ProductParameters;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import java.util.HashMap;
import java.util.Map;

public class ProductParametersCollection {
    private Map<ProductType, ProductParameters> mProductParameters = new HashMap();

    public ProductParameters setProductParameters(ProductType type, String appKey, String userId) {
        ProductParameters productParameters = new ProductParameters(appKey, userId);
        this.mProductParameters.put(type, productParameters);
        return productParameters;
    }

    public ProductParameters getProductParameters(ProductType type) {
        if (type != null) {
            return (ProductParameters) this.mProductParameters.get(type);
        }
        return null;
    }
}
