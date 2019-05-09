package com.google.android.gms.internal.auth;

import android.util.Log;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;

public abstract class zzaz extends FastSafeParcelableJsonResponse {
    private static String zzem = "AUTH";

    public byte[] toByteArray() {
        try {
            return toString().getBytes("UTF-8");
        } catch (Throwable e) {
            Log.e(zzem, "Error serializing object.", e);
            return null;
        }
    }
}
