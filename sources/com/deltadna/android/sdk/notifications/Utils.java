package com.deltadna.android.sdk.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class Utils {
    static Bundle convert(Map<String, String> payload) {
        Bundle result = new Bundle(payload.size());
        for (Entry<String, String> entry : payload.entrySet()) {
            result.putString((String) entry.getKey(), (String) entry.getValue());
        }
        return result;
    }

    static String convert(Bundle payload) {
        JSONObject result = new JSONObject();
        for (String key : payload.keySet()) {
            try {
                if (VERSION.SDK_INT < 19) {
                    result.put(key, payload.get(key));
                } else {
                    result.put(key, JSONObject.wrap(payload.get(key)));
                }
            } catch (JSONException e) {
                Log.w(BuildConfig.LOG_TAG, e);
            }
        }
        return result.toString();
    }

    static Intent wrapWithReceiver(Context context, Intent intent) {
        synchronized (DDNANotifications.class) {
            if (DDNANotifications.receiver != null) {
                intent.setClass(context, DDNANotifications.receiver);
            }
        }
        return intent;
    }

    private Utils() {
    }
}
