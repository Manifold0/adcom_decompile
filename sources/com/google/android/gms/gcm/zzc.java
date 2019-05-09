package com.google.android.gms.gcm;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzc extends Handler {
    private /* synthetic */ GoogleCloudMessaging zzhzw;

    zzc(GoogleCloudMessaging googleCloudMessaging, Looper looper) {
        this.zzhzw = googleCloudMessaging;
        super(looper);
    }

    public final void handleMessage(Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("GCM", "Dropping invalid message");
        }
        Intent intent = (Intent) message.obj;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            this.zzhzw.zzhzu.add(intent);
        } else if (!this.zzhzw.zzf(intent)) {
            intent.setPackage(this.zzhzw.zzaif.getPackageName());
            this.zzhzw.zzaif.sendBroadcast(intent);
        }
    }
}
