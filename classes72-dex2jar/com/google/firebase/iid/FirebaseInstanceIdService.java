// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.util.Log;
import android.content.Intent;
import android.support.annotation.WorkerThread;

@Deprecated
public class FirebaseInstanceIdService extends zzb
{
    @Deprecated
    @WorkerThread
    public void onTokenRefresh() {
    }
    
    @Override
    protected final Intent zzb(final Intent intent) {
        return zzav.zzai().zzda.poll();
    }
    
    @Override
    public final void zzd(final Intent intent) {
        if ("com.google.firebase.iid.TOKEN_REFRESH".equals(intent.getAction())) {
            this.onTokenRefresh();
        }
        else {
            final String stringExtra = intent.getStringExtra("CMD");
            if (stringExtra != null) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    final String value = String.valueOf(intent.getExtras());
                    Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(stringExtra).length() + 21 + String.valueOf(value).length()).append("Received command: ").append(stringExtra).append(" - ").append(value).toString());
                }
                if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
                    FirebaseInstanceId.getInstance().zzm();
                    return;
                }
                if ("SYNC".equals(stringExtra)) {
                    FirebaseInstanceId.getInstance().zzq();
                }
            }
        }
    }
}
