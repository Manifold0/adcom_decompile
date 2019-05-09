// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import android.content.Context;
import com.google.android.gms.auth.api.signin.internal.zzw;
import android.util.Log;
import android.os.IBinder;
import android.content.Intent;
import android.app.Service;

public final class RevocationBoundService extends Service
{
    public final IBinder onBind(final Intent intent) {
        if ("com.google.android.gms.auth.api.signin.RevocationBoundService.disconnect".equals(intent.getAction()) || "com.google.android.gms.auth.api.signin.RevocationBoundService.clearClientState".equals(intent.getAction())) {
            if (Log.isLoggable("RevocationService", 2)) {
                final String value = String.valueOf(intent.getAction());
                String concat;
                if (value.length() != 0) {
                    concat = "RevocationBoundService handling ".concat(value);
                }
                else {
                    concat = new String("RevocationBoundService handling ");
                }
                Log.v("RevocationService", concat);
            }
            return (IBinder)new zzw((Context)this);
        }
        final String value2 = String.valueOf(intent.getAction());
        String concat2;
        if (value2.length() != 0) {
            concat2 = "Unknown action sent to RevocationBoundService: ".concat(value2);
        }
        else {
            concat2 = new String("Unknown action sent to RevocationBoundService: ");
        }
        Log.w("RevocationService", concat2);
        return null;
    }
}
