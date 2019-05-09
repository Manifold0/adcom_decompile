// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import com.amazon.device.iap.model.UserDataResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import java.util.Set;
import java.util.HashSet;
import com.amazon.device.iap.model.PurchaseResponse$RequestStatus;
import com.amazon.device.iap.model.PurchaseResponse;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
import com.amazon.device.iap.model.Product;
import org.json.JSONArray;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.PurchasingListener;
import java.lang.reflect.Field;
import android.content.Context;

class TrackAmazonPurchase
{
    private boolean canTrack;
    private Context context;
    private Field listenerHandlerField;
    private Object listenerHandlerObject;
    private OSPurchasingListener osPurchasingListener;
    
    TrackAmazonPurchase(final Context context) {
        this.canTrack = false;
        this.context = context;
        try {
            final Class<?> forName = Class.forName("com.amazon.device.iap.internal.d");
            this.listenerHandlerObject = forName.getMethod("d", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
            (this.listenerHandlerField = forName.getDeclaredField("f")).setAccessible(true);
            this.osPurchasingListener = new OSPurchasingListener();
            this.osPurchasingListener.orgPurchasingListener = (PurchasingListener)this.listenerHandlerField.get(this.listenerHandlerObject);
            this.canTrack = true;
            this.setListener();
        }
        catch (Throwable t) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error adding Amazon IAP listener.", t);
        }
    }
    
    private void setListener() {
        PurchasingService.registerListener(this.context, (PurchasingListener)this.osPurchasingListener);
    }
    
    void checkListener() {
        if (this.canTrack) {
            try {
                final PurchasingListener orgPurchasingListener = (PurchasingListener)this.listenerHandlerField.get(this.listenerHandlerObject);
                if (orgPurchasingListener != this.osPurchasingListener) {
                    this.osPurchasingListener.orgPurchasingListener = orgPurchasingListener;
                    this.setListener();
                }
            }
            catch (Throwable t) {}
        }
    }
    
    private class OSPurchasingListener implements PurchasingListener
    {
        private String currentMarket;
        private RequestId lastRequestId;
        PurchasingListener orgPurchasingListener;
        
        private String marketToCurrencyCode(final String s) {
            switch (s) {
                default: {
                    return "";
                }
                case "US": {
                    return "USD";
                }
                case "GB": {
                    return "GBP";
                }
                case "DE":
                case "FR":
                case "ES":
                case "IT": {
                    return "EUR";
                }
                case "JP": {
                    return "JPY";
                }
                case "CA": {
                    return "CDN";
                }
                case "BR": {
                    return "BRL";
                }
                case "AU": {
                    return "AUD";
                }
            }
        }
        
        public void onProductDataResponse(final ProductDataResponse productDataResponse) {
            if (this.lastRequestId != null && this.lastRequestId.toString().equals(productDataResponse.getRequestId().toString())) {
                JSONArray jsonArray = null;
                try {
                    switch (productDataResponse.getRequestStatus()) {
                        case SUCCESSFUL: {
                            jsonArray = new JSONArray();
                            final Map productData = productDataResponse.getProductData();
                            final Iterator<String> iterator = productData.keySet().iterator();
                            while (iterator.hasNext()) {
                                final Product product = productData.get(iterator.next());
                                final JSONObject jsonObject = new JSONObject();
                                jsonObject.put("sku", (Object)product.getSku());
                                jsonObject.put("iso", (Object)this.marketToCurrencyCode(this.currentMarket));
                                String s2;
                                final String s = s2 = product.getPrice();
                                if (!s.matches("^[0-9]")) {
                                    s2 = s.substring(1);
                                }
                                jsonObject.put("amount", (Object)s2);
                                jsonArray.put((Object)jsonObject);
                            }
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                    return;
                }
                OneSignal.sendPurchases(jsonArray, false, null);
                return;
            }
            if (this.orgPurchasingListener != null) {
                this.orgPurchasingListener.onProductDataResponse(productDataResponse);
            }
        }
        
        public void onPurchaseResponse(final PurchaseResponse purchaseResponse) {
            while (true) {
                try {
                    if (purchaseResponse.getRequestStatus() == PurchaseResponse$RequestStatus.SUCCESSFUL) {
                        this.currentMarket = purchaseResponse.getUserData().getMarketplace();
                        final HashSet<String> set = new HashSet<String>();
                        set.add(purchaseResponse.getReceipt().getSku());
                        this.lastRequestId = PurchasingService.getProductData((Set)set);
                    }
                    if (this.orgPurchasingListener != null) {
                        this.orgPurchasingListener.onPurchaseResponse(purchaseResponse);
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                    continue;
                }
                break;
            }
        }
        
        public void onPurchaseUpdatesResponse(final PurchaseUpdatesResponse purchaseUpdatesResponse) {
            if (this.orgPurchasingListener != null) {
                this.orgPurchasingListener.onPurchaseUpdatesResponse(purchaseUpdatesResponse);
            }
        }
        
        public void onUserDataResponse(final UserDataResponse userDataResponse) {
            if (this.orgPurchasingListener != null) {
                this.orgPurchasingListener.onUserDataResponse(userDataResponse);
            }
        }
    }
}
