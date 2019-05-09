// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.content.ServiceConnection;
import android.content.ComponentName;
import android.util.Log;
import android.os.Message;
import com.google.android.gms.common.stats.ConnectionTracker;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import java.util.HashMap;
import android.os.Handler;
import android.os.Handler$Callback;

final class zze extends GmsClientSupervisor implements Handler$Callback
{
    private final Handler mHandler;
    @GuardedBy("mConnectionStatus")
    private final HashMap<zza, zzf> zzdu;
    private final Context zzdv;
    private final ConnectionTracker zzdw;
    private final long zzdx;
    private final long zzdy;
    
    zze(final Context context) {
        this.zzdu = new HashMap<zza, zzf>();
        this.zzdv = context.getApplicationContext();
        this.mHandler = new com.google.android.gms.internal.common.zze(context.getMainLooper(), (Handler$Callback)this);
        this.zzdw = ConnectionTracker.getInstance();
        this.zzdx = 5000L;
        this.zzdy = 300000L;
    }
    
    public final boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                synchronized (this.zzdu) {
                    final zza zza = (zza)message.obj;
                    final zzf zzf = this.zzdu.get(zza);
                    if (zzf != null && zzf.zzr()) {
                        if (zzf.isBound()) {
                            zzf.zzf("GmsClientSupervisor");
                        }
                        this.zzdu.remove(zza);
                    }
                    return true;
                }
            }
            case 1: {
                while (true) {
                    while (true) {
                        Label_0243: {
                            synchronized (this.zzdu) {
                                final zza zza2 = (zza)message.obj;
                                final zzf zzf2 = this.zzdu.get(zza2);
                                if (zzf2 != null && zzf2.getState() == 3) {
                                    final String value = String.valueOf(zza2);
                                    Log.e("GmsClientSupervisor", new StringBuilder(String.valueOf(value).length() + 47).append("Timeout waiting for ServiceConnection callback ").append(value).toString(), (Throwable)new Exception());
                                    ComponentName componentName;
                                    if ((componentName = zzf2.getComponentName()) == null) {
                                        componentName = zza2.getComponentName();
                                    }
                                    if (componentName != null) {
                                        break Label_0243;
                                    }
                                    final ComponentName componentName2 = new ComponentName(zza2.getPackage(), "unknown");
                                    zzf2.onServiceDisconnected(componentName2);
                                }
                                return true;
                            }
                        }
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    @Override
    protected final boolean zza(final zza zza, final ServiceConnection serviceConnection, final String s) {
        while (true) {
            Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
            while (true) {
                zzf zzf;
                synchronized (this.zzdu) {
                    zzf = this.zzdu.get(zza);
                    if (zzf == null) {
                        zzf = new zzf(this, zza);
                        zzf.zza(serviceConnection, s);
                        zzf.zze(s);
                        this.zzdu.put(zza, zzf);
                        final zzf zzf2 = zzf;
                        return zzf2.isBound();
                    }
                    this.mHandler.removeMessages(0, (Object)zza);
                    if (zzf.zza(serviceConnection)) {
                        final String value = String.valueOf(zza);
                        throw new IllegalStateException(new StringBuilder(String.valueOf(value).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(value).toString());
                    }
                }
                zzf.zza(serviceConnection, s);
                switch (zzf.getState()) {
                    case 1: {
                        serviceConnection.onServiceConnected(zzf.getComponentName(), zzf.getBinder());
                        final zzf zzf2 = zzf;
                        continue;
                    }
                    case 2: {
                        zzf.zze(s);
                        final zzf zzf2 = zzf;
                        continue;
                    }
                    default: {
                        final zzf zzf2 = zzf;
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    @Override
    protected final void zzb(final zza zza, final ServiceConnection serviceConnection, final String s) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        final zzf zzf;
        synchronized (this.zzdu) {
            zzf = this.zzdu.get(zza);
            if (zzf == null) {
                final String value = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(value).length() + 50).append("Nonexistent connection status for service config: ").append(value).toString());
            }
        }
        final Throwable t;
        if (!zzf.zza(serviceConnection)) {
            final String value2 = String.valueOf(t);
            throw new IllegalStateException(new StringBuilder(String.valueOf(value2).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(value2).toString());
        }
        zzf.zzb(serviceConnection, s);
        if (zzf.zzr()) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, (Object)t), this.zzdx);
        }
    }
    // monitorexit(hashMap)
}
