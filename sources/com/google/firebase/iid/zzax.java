package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class zzax {
    private static final long zzdf = TimeUnit.DAYS.toMillis(7);
    private final long timestamp;
    final String zzbq;
    private final String zzdg;

    private zzax(String str, String str2, long j) {
        this.zzbq = str;
        this.zzdg = str2;
        this.timestamp = j;
    }

    static zzax zzi(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!str.startsWith("{")) {
            return new zzax(str, null, 0);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzax(jSONObject.getString("token"), jSONObject.getString(RequestParameters.APPLICATION_VERSION_NAME), jSONObject.getLong("timestamp"));
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Failed to parse token: ").append(valueOf).toString());
            return null;
        }
    }

    static String zza(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("token", str);
            jSONObject.put(RequestParameters.APPLICATION_VERSION_NAME, str2);
            jSONObject.put("timestamp", j);
            return jSONObject.toString();
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Failed to encode token: ").append(valueOf).toString());
            return null;
        }
    }

    static String zza(@Nullable zzax zzax) {
        if (zzax == null) {
            return null;
        }
        return zzax.zzbq;
    }

    final boolean zzj(String str) {
        return System.currentTimeMillis() > this.timestamp + zzdf || !str.equals(this.zzdg);
    }
}
