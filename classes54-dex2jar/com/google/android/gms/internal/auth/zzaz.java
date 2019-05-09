// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import java.io.UnsupportedEncodingException;
import android.util.Log;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;

public abstract class zzaz extends FastSafeParcelableJsonResponse
{
    private static String zzem;
    
    static {
        zzaz.zzem = "AUTH";
    }
    
    public byte[] toByteArray() {
        try {
            return this.toString().getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            Log.e(zzaz.zzem, "Error serializing object.", (Throwable)ex);
            return null;
        }
    }
}
