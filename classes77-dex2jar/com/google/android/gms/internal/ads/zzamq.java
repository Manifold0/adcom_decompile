// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.content.Intent;
import java.util.WeakHashMap;
import android.content.Context;
import android.content.IntentFilter;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import android.content.BroadcastReceiver;

@zzadh
public final class zzamq
{
    @GuardedBy("this")
    private final BroadcastReceiver zzcuc;
    @GuardedBy("this")
    private final Map<BroadcastReceiver, IntentFilter> zzcud;
    private boolean zzcue;
    private boolean zzsh;
    private Context zzsz;
    
    public zzamq() {
        this.zzsh = false;
        this.zzcud = new WeakHashMap<BroadcastReceiver, IntentFilter>();
        this.zzcuc = new zzamr(this);
    }
    
    private final void zzc(final Context context, final Intent intent) {
        synchronized (this) {
            for (final Map.Entry<BroadcastReceiver, IntentFilter> entry : this.zzcud.entrySet()) {
                if (entry.getValue().hasAction(intent.getAction())) {
                    entry.getKey().onReceive(context, intent);
                }
            }
        }
    }
    // monitorexit(this)
    
    public final void initialize(final Context zzsz) {
        synchronized (this) {
            if (!this.zzsh) {
                this.zzsz = zzsz.getApplicationContext();
                if (this.zzsz == null) {
                    this.zzsz = zzsz;
                }
                zznk.initialize(this.zzsz);
                this.zzcue = (boolean)zzkb.zzik().zzd(zznk.zzbcc);
                final IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                this.zzsz.registerReceiver(this.zzcuc, intentFilter);
                this.zzsh = true;
            }
        }
    }
    
    public final void zza(final Context context, final BroadcastReceiver broadcastReceiver) {
        synchronized (this) {
            if (this.zzcue) {
                this.zzcud.remove(broadcastReceiver);
            }
            else {
                context.unregisterReceiver(broadcastReceiver);
            }
        }
    }
    
    public final void zza(final Context context, final BroadcastReceiver broadcastReceiver, final IntentFilter intentFilter) {
        synchronized (this) {
            if (this.zzcue) {
                this.zzcud.put(broadcastReceiver, intentFilter);
            }
            else {
                context.registerReceiver(broadcastReceiver, intentFilter);
            }
        }
    }
}
