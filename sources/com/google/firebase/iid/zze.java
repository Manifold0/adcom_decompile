package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

final /* synthetic */ class zze implements Runnable {
    private final zzd zzs;
    private final Intent zzt;

    zze(zzd zzd, Intent intent) {
        this.zzs = zzd;
        this.zzt = intent;
    }

    public final void run() {
        zzd zzd = this.zzs;
        String action = this.zzt.getAction();
        Log.w("EnhancedIntentService", new StringBuilder(String.valueOf(action).length() + 61).append("Service took too long to process intent: ").append(action).append(" App may get closed.").toString());
        zzd.finish();
    }
}
