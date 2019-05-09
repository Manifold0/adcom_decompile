// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.content.BroadcastReceiver$PendingResult;
import android.os.IBinder;
import android.content.ComponentName;
import com.google.android.gms.common.stats.zza;
import android.util.Log;
import android.support.annotation.VisibleForTesting;
import java.util.LinkedList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import android.content.Intent;
import android.content.Context;
import android.content.ServiceConnection;

public final class zzh implements ServiceConnection
{
    private final Context zzaif;
    private final Intent zzicf;
    private final ScheduledExecutorService zzicg;
    private final Queue<zzd> zzich;
    private zzf zzici;
    private boolean zzicj;
    
    public zzh(final Context context, final String s) {
        this(context, s, new ScheduledThreadPoolExecutor(0));
    }
    
    @VisibleForTesting
    private zzh(final Context context, final String s, final ScheduledExecutorService zzicg) {
        this.zzich = new LinkedList<zzd>();
        this.zzicj = false;
        this.zzaif = context.getApplicationContext();
        this.zzicf = new Intent(s).setPackage(this.zzaif.getPackageName());
        this.zzicg = zzicg;
    }
    
    private final void zzaur() {
    Label_0206:
        while (true) {
            Label_0190: {
                Label_0111: {
                    synchronized (this) {
                        if (Log.isLoggable("EnhancedIntentService", 3)) {
                            Log.d("EnhancedIntentService", "flush queue called");
                        }
                        while (!this.zzich.isEmpty()) {
                            if (Log.isLoggable("EnhancedIntentService", 3)) {
                                Log.d("EnhancedIntentService", "found intent to be delivered");
                            }
                            if (this.zzici == null || !this.zzici.isBinderAlive()) {
                                break Label_0111;
                            }
                            if (Log.isLoggable("EnhancedIntentService", 3)) {
                                Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
                            }
                            this.zzici.zza(this.zzich.poll());
                        }
                        break Label_0190;
                    }
                }
                Label_0193: {
                    while (true) {
                        Label_0156: {
                            if (!Log.isLoggable("EnhancedIntentService", 3)) {
                                break Label_0156;
                            }
                            if (this.zzicj) {
                                break Label_0193;
                            }
                            final boolean b = true;
                            Log.d("EnhancedIntentService", new StringBuilder(39).append("binder is dead. start connection? ").append(b).toString());
                        }
                        if (this.zzicj) {
                            break Label_0190;
                        }
                        this.zzicj = true;
                        try {
                            if (!zza.zzalq().zza(this.zzaif, this.zzicf, (ServiceConnection)this, 65)) {
                                Log.e("EnhancedIntentService", "binding to the service failed");
                                while (!this.zzich.isEmpty()) {
                                    this.zzich.poll().finish();
                                }
                            }
                            // monitorexit(this)
                            return;
                            final boolean b = false;
                            continue;
                        }
                        catch (SecurityException ex) {
                            Log.e("EnhancedIntentService", "Exception while binding the service", (Throwable)ex);
                            continue Label_0206;
                        }
                        break;
                    }
                }
            }
            continue Label_0206;
        }
    }
    
    public final void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (this) {
            this.zzicj = false;
            this.zzici = (zzf)binder;
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                final String value = String.valueOf(componentName);
                Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(value).length() + 20).append("onServiceConnected: ").append(value).toString());
            }
            this.zzaur();
        }
    }
    
    public final void onServiceDisconnected(final ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            final String value = String.valueOf(componentName);
            Log.d("EnhancedIntentService", new StringBuilder(String.valueOf(value).length() + 23).append("onServiceDisconnected: ").append(value).toString());
        }
        this.zzaur();
    }
    
    public final void zza(final Intent intent, final BroadcastReceiver$PendingResult broadcastReceiver$PendingResult) {
        synchronized (this) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
            }
            this.zzich.add(new zzd(intent, broadcastReceiver$PendingResult, this.zzicg));
            this.zzaur();
        }
    }
}
