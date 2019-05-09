// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.content.BroadcastReceiver$PendingResult;
import android.os.IBinder;
import android.content.ComponentName;
import com.google.android.gms.common.stats.ConnectionTracker;
import android.util.Log;
import android.support.annotation.VisibleForTesting;
import java.util.ArrayDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ScheduledExecutorService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.GuardedBy;
import java.util.Queue;
import android.content.ServiceConnection;

public final class zzh implements ServiceConnection
{
    private final Queue<zzd> zzaa;
    private zzf zzab;
    @GuardedBy("this")
    private boolean zzac;
    private final Context zzx;
    private final Intent zzy;
    private final ScheduledExecutorService zzz;
    
    public zzh(final Context context, final String s) {
        this(context, s, new ScheduledThreadPoolExecutor(0, (ThreadFactory)new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection")));
    }
    
    @VisibleForTesting
    private zzh(final Context context, final String s, final ScheduledExecutorService zzz) {
        this.zzaa = new ArrayDeque<zzd>();
        this.zzac = false;
        this.zzx = context.getApplicationContext();
        this.zzy = new Intent(s).setPackage(this.zzx.getPackageName());
        this.zzz = zzz;
    }
    
    private final void zzc() {
        Label_0190: {
            Label_0111: {
                synchronized (this) {
                    if (Log.isLoggable("EnhancedIntentService", 3)) {
                        Log.d("EnhancedIntentService", "flush queue called");
                    }
                    while (!this.zzaa.isEmpty()) {
                        if (Log.isLoggable("EnhancedIntentService", 3)) {
                            Log.d("EnhancedIntentService", "found intent to be delivered");
                        }
                        if (this.zzab == null || !this.zzab.isBinderAlive()) {
                            break Label_0111;
                        }
                        if (Log.isLoggable("EnhancedIntentService", 3)) {
                            Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
                        }
                        this.zzab.zza(this.zzaa.poll());
                    }
                    break Label_0190;
                }
            }
        Label_0206_Outer:
            while (true) {
                Label_0156: {
                    if (!Log.isLoggable("EnhancedIntentService", 3)) {
                        break Label_0156;
                    }
                    if (this.zzac) {
                        break Label_0206_Outer;
                    }
                    final boolean b = true;
                    Log.d("EnhancedIntentService", new StringBuilder(39).append("binder is dead. start connection? ").append(b).toString());
                }
                if (this.zzac) {
                    break Label_0190;
                }
                this.zzac = true;
                while (true) {
                    try {
                        if (!ConnectionTracker.getInstance().bindService(this.zzx, this.zzy, (ServiceConnection)this, 65)) {
                            Log.e("EnhancedIntentService", "binding to the service failed");
                            this.zzac = false;
                            this.zzd();
                        }
                        // monitorexit(this)
                        return;
                        final boolean b = false;
                        continue Label_0206_Outer;
                    }
                    catch (SecurityException ex) {
                        Log.e("EnhancedIntentService", "Exception while binding the service", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    @GuardedBy("this")
    private final void zzd() {
        while (!this.zzaa.isEmpty()) {
            this.zzaa.poll().finish();
        }
    }
    
    public final void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (this) {
            this.zzac = false;
            this.zzab = (zzf)binder;
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                final String value = String.valueOf(componentName);
                Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(value).length() + 20).append("onServiceConnected: ").append(value).toString());
            }
            if (binder == null) {
                Log.e("EnhancedIntentService", "Null service connection");
                this.zzd();
            }
            else {
                this.zzc();
            }
        }
    }
    
    public final void onServiceDisconnected(final ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            final String value = String.valueOf(componentName);
            Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(value).length() + 23).append("onServiceDisconnected: ").append(value).toString());
        }
        this.zzc();
    }
    
    public final void zza(final Intent intent, final BroadcastReceiver$PendingResult broadcastReceiver$PendingResult) {
        synchronized (this) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
            }
            this.zzaa.add(new zzd(intent, broadcastReceiver$PendingResult, this.zzz));
            this.zzc();
        }
    }
}
