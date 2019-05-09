// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import com.facebook.FacebookSdk;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;
import com.facebook.CallbackManager;

public final class CallbackManagerImpl implements CallbackManager
{
    private static final String INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    private static final String TAG;
    private static Map<Integer, Callback> staticCallbacks;
    private Map<Integer, Callback> callbacks;
    
    static {
        TAG = CallbackManagerImpl.class.getSimpleName();
        CallbackManagerImpl.staticCallbacks = new HashMap<Integer, Callback>();
    }
    
    public CallbackManagerImpl() {
        this.callbacks = new HashMap<Integer, Callback>();
    }
    
    private static Callback getStaticCallback(final Integer n) {
        synchronized (CallbackManagerImpl.class) {
            return CallbackManagerImpl.staticCallbacks.get(n);
        }
    }
    
    private static boolean isPurchaseIntent(final Intent intent) {
        if (intent != null) {
            final String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
            if (stringExtra != null) {
                try {
                    final JSONObject jsonObject = new JSONObject(stringExtra);
                    if (jsonObject.has("orderId") && jsonObject.has("packageName") && jsonObject.has("productId") && jsonObject.has("purchaseTime") && jsonObject.has("purchaseState") && jsonObject.has("developerPayload") && jsonObject.has("purchaseToken")) {
                        return true;
                    }
                }
                catch (JSONException ex) {
                    Log.e(CallbackManagerImpl.TAG, "Error parsing intent data.", (Throwable)ex);
                    return false;
                }
            }
        }
        return false;
    }
    
    public static void registerStaticCallback(final int n, final Callback callback) {
        synchronized (CallbackManagerImpl.class) {
            Validate.notNull(callback, "callback");
            if (!CallbackManagerImpl.staticCallbacks.containsKey(n)) {
                CallbackManagerImpl.staticCallbacks.put(n, callback);
            }
        }
    }
    
    private static boolean runStaticCallback(final int n, final int n2, final Intent intent) {
        final Callback staticCallback = getStaticCallback(n);
        return staticCallback != null && staticCallback.onActivityResult(n2, intent);
    }
    
    @Override
    public boolean onActivityResult(int requestCode, final int n, final Intent intent) {
        if (isPurchaseIntent(intent)) {
            requestCode = RequestCodeOffset.InAppPurchase.toRequestCode();
        }
        final Callback callback = this.callbacks.get(requestCode);
        if (callback != null) {
            return callback.onActivityResult(n, intent);
        }
        return runStaticCallback(requestCode, n, intent);
    }
    
    public void registerCallback(final int n, final Callback callback) {
        Validate.notNull(callback, "callback");
        this.callbacks.put(n, callback);
    }
    
    public void unregisterCallback(final int n) {
        this.callbacks.remove(n);
    }
    
    public interface Callback
    {
        boolean onActivityResult(final int p0, final Intent p1);
    }
    
    public enum RequestCodeOffset
    {
        AppGroupCreate(5), 
        AppGroupJoin(6), 
        AppInvite(7), 
        DeviceShare(8), 
        GameRequest(4), 
        InAppPurchase(9), 
        Like(3), 
        Login(0), 
        Message(2), 
        Share(1);
        
        private final int offset;
        
        private RequestCodeOffset(final int offset) {
            this.offset = offset;
        }
        
        public int toRequestCode() {
            return FacebookSdk.getCallbackRequestCodeOffset() + this.offset;
        }
    }
}
