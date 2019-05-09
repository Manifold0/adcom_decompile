// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.ipc;

import android.os.Handler;
import android.os.IBinder;
import android.content.Intent;
import android.os.Messenger;
import android.app.Service;

public class AdsProcessPriorityService extends Service
{
    private Messenger a;
    
    public IBinder onBind(final Intent intent) {
        return this.a.getBinder();
    }
    
    public void onCreate() {
        super.onCreate();
        this.a = new Messenger(new Handler());
    }
}
