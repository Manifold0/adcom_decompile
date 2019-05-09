// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import java.util.HashMap;
import com.ironsource.sdk.data.ProductParameters;
import com.ironsource.sdk.data.SSAEnums;
import java.util.Map;

public class ProductParametersCollection
{
    private Map<SSAEnums.ProductType, ProductParameters> mProductParameters;
    
    public ProductParametersCollection() {
        this.mProductParameters = new HashMap<SSAEnums.ProductType, ProductParameters>();
    }
    
    public ProductParameters getProductParameters(final SSAEnums.ProductType productType) {
        ProductParameters productParameters = null;
        if (productType != null) {
            productParameters = this.mProductParameters.get(productType);
        }
        return productParameters;
    }
    
    public ProductParameters setProductParameters(final SSAEnums.ProductType productType, final String s, final String s2) {
        final ProductParameters productParameters = new ProductParameters(s, s2);
        this.mProductParameters.put(productType, productParameters);
        return productParameters;
    }
}
