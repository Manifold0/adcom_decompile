package com.facebook.ads.internal.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;

public class AdsProcessPriorityService extends Service {
    /* renamed from: a */
    private Messenger f4479a;

    public IBinder onBind(Intent intent) {
        return this.f4479a.getBinder();
    }

    public void onCreate() {
        super.onCreate();
        this.f4479a = new Messenger(new Handler());
    }
}
