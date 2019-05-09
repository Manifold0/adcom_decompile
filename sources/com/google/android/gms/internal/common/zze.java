package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;

public class zze extends Handler {
    private static volatile zzf zziu = null;

    public zze(Looper looper) {
        super(looper);
    }

    public zze(Looper looper, Callback callback) {
        super(looper, callback);
    }

    public final void dispatchMessage(Message message) {
        super.dispatchMessage(message);
    }
}
