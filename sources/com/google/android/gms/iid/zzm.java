package com.google.android.gms.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class zzm extends Handler {
    private /* synthetic */ zzl zzidh;

    zzm(zzl zzl, Looper looper) {
        this.zzidh = zzl;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.zzidh.zzc(message);
    }
}
