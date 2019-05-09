// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.util.Log;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;

public class InstanceIDListenerService extends zzb
{
    static void zza(final Context context, final zzo zzo) {
        zzo.zzaux();
        final Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra("CMD", "RST");
        intent.setClassName(context, "com.google.android.gms.gcm.GcmReceiver");
        context.sendBroadcast(intent);
    }
    
    @Override
    public void handleIntent(final Intent intent) {
        if ("com.google.android.gms.iid.InstanceID".equals(intent.getAction())) {
            Bundle bundle = null;
            final String stringExtra = intent.getStringExtra("subtype");
            if (stringExtra != null) {
                bundle = new Bundle();
                bundle.putString("subtype", stringExtra);
            }
            final InstanceID instance = InstanceID.getInstance((Context)this, bundle);
            final String stringExtra2 = intent.getStringExtra("CMD");
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", new StringBuilder(String.valueOf(stringExtra).length() + 34 + String.valueOf(stringExtra2).length()).append("Service command. subtype:").append(stringExtra).append(" command:").append(stringExtra2).toString());
            }
            if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
                InstanceID.zzauu().zzhu(stringExtra);
                this.onTokenRefresh();
                return;
            }
            if ("RST".equals(stringExtra2)) {
                instance.zzaut();
                this.onTokenRefresh();
                return;
            }
            if ("RST_FULL".equals(stringExtra2)) {
                if (!InstanceID.zzauu().isEmpty()) {
                    InstanceID.zzauu().zzaux();
                    this.onTokenRefresh();
                }
            }
            else if ("SYNC".equals(stringExtra2)) {
                InstanceID.zzauu().zzhu(stringExtra);
                this.onTokenRefresh();
            }
        }
    }
    
    public void onTokenRefresh() {
    }
}
