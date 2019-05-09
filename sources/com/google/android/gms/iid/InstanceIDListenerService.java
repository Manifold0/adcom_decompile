package com.google.android.gms.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class InstanceIDListenerService extends zzb {
    static void zza(Context context, zzo zzo) {
        zzo.zzaux();
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra("CMD", "RST");
        intent.setClassName(context, "com.google.android.gms.gcm.GcmReceiver");
        context.sendBroadcast(intent);
    }

    public void handleIntent(Intent intent) {
        if ("com.google.android.gms.iid.InstanceID".equals(intent.getAction())) {
            Bundle bundle = null;
            String stringExtra = intent.getStringExtra("subtype");
            if (stringExtra != null) {
                bundle = new Bundle();
                bundle.putString("subtype", stringExtra);
            }
            InstanceID instance = InstanceID.getInstance(this, bundle);
            String stringExtra2 = intent.getStringExtra("CMD");
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", new StringBuilder((String.valueOf(stringExtra).length() + 34) + String.valueOf(stringExtra2).length()).append("Service command. subtype:").append(stringExtra).append(" command:").append(stringExtra2).toString());
            }
            if ("gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
                InstanceID.zzauu().zzhu(stringExtra);
                onTokenRefresh();
            } else if ("RST".equals(stringExtra2)) {
                instance.zzaut();
                onTokenRefresh();
            } else if ("RST_FULL".equals(stringExtra2)) {
                if (!InstanceID.zzauu().isEmpty()) {
                    InstanceID.zzauu().zzaux();
                    onTokenRefresh();
                }
            } else if ("SYNC".equals(stringExtra2)) {
                InstanceID.zzauu().zzhu(stringExtra);
                onTokenRefresh();
            }
        }
    }

    public void onTokenRefresh() {
    }
}
