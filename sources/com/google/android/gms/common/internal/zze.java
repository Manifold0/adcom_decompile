package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

final class zze extends GmsClientSupervisor implements Callback {
    private final Handler mHandler;
    @GuardedBy("mConnectionStatus")
    private final HashMap<zza, zzf> zzdu = new HashMap();
    private final Context zzdv;
    private final ConnectionTracker zzdw;
    private final long zzdx;
    private final long zzdy;

    zze(Context context) {
        this.zzdv = context.getApplicationContext();
        this.mHandler = new com.google.android.gms.internal.common.zze(context.getMainLooper(), this);
        this.zzdw = ConnectionTracker.getInstance();
        this.zzdx = 5000;
        this.zzdy = 300000;
    }

    protected final boolean zza(zza zza, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzdu) {
            zzf zzf = (zzf) this.zzdu.get(zza);
            if (zzf != null) {
                this.mHandler.removeMessages(0, zza);
                if (!zzf.zza(serviceConnection)) {
                    zzf.zza(serviceConnection, str);
                    switch (zzf.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzf.getComponentName(), zzf.getBinder());
                            break;
                        case 2:
                            zzf.zze(str);
                            break;
                        default:
                            break;
                    }
                }
                String valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
            }
            zzf = new zzf(this, zza);
            zzf.zza(serviceConnection, str);
            zzf.zze(str);
            this.zzdu.put(zza, zzf);
            isBound = zzf.isBound();
        }
        return isBound;
    }

    protected final void zzb(zza zza, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzdu) {
            zzf zzf = (zzf) this.zzdu.get(zza);
            String valueOf;
            if (zzf == null) {
                valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (zzf.zza(serviceConnection)) {
                zzf.zzb(serviceConnection, str);
                if (zzf.zzr()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zza), this.zzdx);
                }
            } else {
                valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf).toString());
            }
        }
    }

    public final boolean handleMessage(Message message) {
        zza zza;
        zzf zzf;
        switch (message.what) {
            case 0:
                synchronized (this.zzdu) {
                    zza = (zza) message.obj;
                    zzf = (zzf) this.zzdu.get(zza);
                    if (zzf != null && zzf.zzr()) {
                        if (zzf.isBound()) {
                            zzf.zzf("GmsClientSupervisor");
                        }
                        this.zzdu.remove(zza);
                    }
                }
                return true;
            case 1:
                synchronized (this.zzdu) {
                    zza = (zza) message.obj;
                    zzf = (zzf) this.zzdu.get(zza);
                    if (zzf != null && zzf.getState() == 3) {
                        ComponentName componentName;
                        String valueOf = String.valueOf(zza);
                        Log.e("GmsClientSupervisor", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Timeout waiting for ServiceConnection callback ").append(valueOf).toString(), new Exception());
                        ComponentName componentName2 = zzf.getComponentName();
                        if (componentName2 == null) {
                            componentName2 = zza.getComponentName();
                        }
                        if (componentName2 == null) {
                            componentName = new ComponentName(zza.getPackage(), "unknown");
                        } else {
                            componentName = componentName2;
                        }
                        zzf.onServiceDisconnected(componentName);
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
