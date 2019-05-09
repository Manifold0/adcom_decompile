package com.unity3d.services.purchasing.core;

import java.util.Map;

public interface IPurchasingAdapter {
    void onPurchase(String str, ITransactionListener iTransactionListener, Map<String, Object> map);

    void retrieveProducts(IRetrieveProductsListener iRetrieveProductsListener);
}
