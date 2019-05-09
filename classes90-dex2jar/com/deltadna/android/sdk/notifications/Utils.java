// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import android.content.Intent;
import android.content.Context;
import org.json.JSONException;
import android.util.Log;
import android.os.Build$VERSION;
import org.json.JSONObject;
import java.util.Iterator;
import android.os.Bundle;
import java.util.Map;

class Utils
{
    private Utils() {
    }
    
    static Bundle convert(final Map<String, String> map) {
        final Bundle bundle = new Bundle(map.size());
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString((String)entry.getKey(), (String)entry.getValue());
        }
        return bundle;
    }
    
    static String convert(final Bundle bundle) {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            Label_0070: {
                try {
                    if (Build$VERSION.SDK_INT >= 19) {
                        break Label_0070;
                    }
                    jsonObject.put(s, bundle.get(s));
                }
                catch (JSONException ex) {
                    Log.w("deltaDNA", (Throwable)ex);
                }
                continue;
            }
            jsonObject.put(s, JSONObject.wrap(bundle.get(s)));
        }
        return jsonObject.toString();
    }
    
    static Intent wrapWithReceiver(final Context context, final Intent intent) {
        synchronized (DDNANotifications.class) {
            if (DDNANotifications.receiver != null) {
                intent.setClass(context, (Class)DDNANotifications.receiver);
            }
            return intent;
        }
    }
}
