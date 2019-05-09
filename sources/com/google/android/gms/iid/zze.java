package com.google.android.gms.iid;

import android.content.Intent;
import android.util.Log;

final class zze implements Runnable {
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ zzd zzicb;

    zze(zzd zzd, Intent intent) {
        this.zzicb = zzd;
        this.val$intent = intent;
    }

    public final void run() {
        String action = this.val$intent.getAction();
        Log.w("EnhancedIntentService", new StringBuilder(String.valueOf(action).length() + 61).append("Service took too long to process intent: ").append(action).append(" App may get closed.").toString());
        this.zzicb.finish();
    }
}
