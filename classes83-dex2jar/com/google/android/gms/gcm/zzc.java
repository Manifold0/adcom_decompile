// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.util.Log;
import android.content.Intent;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class zzc extends Handler
{
    private /* synthetic */ GoogleCloudMessaging zzhzw;
    
    zzc(final GoogleCloudMessaging zzhzw, final Looper looper) {
        this.zzhzw = zzhzw;
        super(looper);
    }
    
    public final void handleMessage(final Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("GCM", "Dropping invalid message");
        }
        final Intent intent = (Intent)message.obj;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            this.zzhzw.zzhzu.add(intent);
        }
        else if (!this.zzhzw.zzf(intent)) {
            intent.setPackage(this.zzhzw.zzaif.getPackageName());
            this.zzhzw.zzaif.sendBroadcast(intent);
        }
    }
}
