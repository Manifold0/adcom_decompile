// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.IBinder;
import android.content.Intent;
import android.app.Service;

public class SyncService extends Service
{
    @Nullable
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        OneSignalSyncServiceUtils.doBackgroundSync((Context)this, (OneSignalSyncServiceUtils.SyncRunnable)new OneSignalSyncServiceUtils.LegacySyncRunnable(this));
        return 1;
    }
}
