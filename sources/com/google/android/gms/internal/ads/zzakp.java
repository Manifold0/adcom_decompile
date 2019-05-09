package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzakp extends BroadcastReceiver {
    private final /* synthetic */ zzakk zzcru;

    private zzakp(zzakk zzakk) {
        this.zzcru = zzakk;
    }

    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            this.zzcru.zzcrn = true;
        } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            this.zzcru.zzcrn = false;
        }
    }
}
