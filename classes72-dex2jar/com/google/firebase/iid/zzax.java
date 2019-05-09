// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.text.TextUtils;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.support.annotation.Nullable;
import java.util.concurrent.TimeUnit;

final class zzax
{
    private static final long zzdf;
    private final long timestamp;
    final String zzbq;
    private final String zzdg;
    
    static {
        zzdf = TimeUnit.DAYS.toMillis(7L);
    }
    
    private zzax(final String zzbq, final String zzdg, final long timestamp) {
        this.zzbq = zzbq;
        this.zzdg = zzdg;
        this.timestamp = timestamp;
    }
    
    static String zza(@Nullable final zzax zzax) {
        if (zzax == null) {
            return null;
        }
        return zzax.zzbq;
    }
    
    static String zza(String s, final String s2, final long n) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", (Object)s);
            jsonObject.put("appVersion", (Object)s2);
            jsonObject.put("timestamp", n);
            s = jsonObject.toString();
            return s;
        }
        catch (JSONException ex) {
            s = String.valueOf(ex);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(s).length() + 24).append("Failed to encode token: ").append(s).toString());
            return null;
        }
    }
    
    static zzax zzi(String value) {
        if (TextUtils.isEmpty((CharSequence)value)) {
            return null;
        }
        if (value.startsWith("{")) {
            try {
                final JSONObject jsonObject = new JSONObject(value);
                return new zzax(jsonObject.getString("token"), jsonObject.getString("appVersion"), jsonObject.getLong("timestamp"));
            }
            catch (JSONException ex) {
                value = String.valueOf(ex);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(value).length() + 23).append("Failed to parse token: ").append(value).toString());
                return null;
            }
        }
        return new zzax(value, null, 0L);
    }
    
    final boolean zzj(final String s) {
        return System.currentTimeMillis() > this.timestamp + zzax.zzdf || !s.equals(this.zzdg);
    }
}
