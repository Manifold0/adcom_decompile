package com.facebook.internal;

import android.content.Intent;
import android.util.Log;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class CallbackManagerImpl implements CallbackManager {
    private static final String INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    private static final String TAG = CallbackManagerImpl.class.getSimpleName();
    private static Map<Integer, Callback> staticCallbacks = new HashMap();
    private Map<Integer, Callback> callbacks = new HashMap();

    public interface Callback {
        boolean onActivityResult(int i, Intent intent);
    }

    public enum RequestCodeOffset {
        Login(0),
        Share(1),
        Message(2),
        Like(3),
        GameRequest(4),
        AppGroupCreate(5),
        AppGroupJoin(6),
        AppInvite(7),
        DeviceShare(8),
        InAppPurchase(9);
        
        private final int offset;

        private RequestCodeOffset(int offset) {
            this.offset = offset;
        }

        public int toRequestCode() {
            return FacebookSdk.getCallbackRequestCodeOffset() + this.offset;
        }
    }

    public static synchronized void registerStaticCallback(int requestCode, Callback callback) {
        synchronized (CallbackManagerImpl.class) {
            Validate.notNull(callback, "callback");
            if (!staticCallbacks.containsKey(Integer.valueOf(requestCode))) {
                staticCallbacks.put(Integer.valueOf(requestCode), callback);
            }
        }
    }

    private static synchronized Callback getStaticCallback(Integer requestCode) {
        Callback callback;
        synchronized (CallbackManagerImpl.class) {
            callback = (Callback) staticCallbacks.get(requestCode);
        }
        return callback;
    }

    private static boolean runStaticCallback(int requestCode, int resultCode, Intent data) {
        Callback callback = getStaticCallback(Integer.valueOf(requestCode));
        if (callback != null) {
            return callback.onActivityResult(resultCode, data);
        }
        return false;
    }

    public void registerCallback(int requestCode, Callback callback) {
        Validate.notNull(callback, "callback");
        this.callbacks.put(Integer.valueOf(requestCode), callback);
    }

    public void unregisterCallback(int requestCode) {
        this.callbacks.remove(Integer.valueOf(requestCode));
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (isPurchaseIntent(data)) {
            requestCode = RequestCodeOffset.InAppPurchase.toRequestCode();
        }
        Callback callback = (Callback) this.callbacks.get(Integer.valueOf(requestCode));
        if (callback != null) {
            return callback.onActivityResult(resultCode, data);
        }
        return runStaticCallback(requestCode, resultCode, data);
    }

    private static boolean isPurchaseIntent(Intent data) {
        if (data == null) {
            return false;
        }
        String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
        if (purchaseData == null) {
            return false;
        }
        try {
            JSONObject jo = new JSONObject(purchaseData);
            if (jo.has("orderId") && jo.has("packageName") && jo.has("productId") && jo.has("purchaseTime") && jo.has("purchaseState") && jo.has("developerPayload") && jo.has("purchaseToken")) {
                return true;
            }
            return false;
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing intent data.", e);
            return false;
        }
    }
}
