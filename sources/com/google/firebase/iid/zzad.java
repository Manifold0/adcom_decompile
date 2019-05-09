package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.firebase_messaging.zza;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

final class zzad implements ServiceConnection {
    @GuardedBy("this")
    int state;
    final Messenger zzbx;
    zzai zzby;
    @GuardedBy("this")
    final Queue<zzak<?>> zzbz;
    @GuardedBy("this")
    final SparseArray<zzak<?>> zzca;
    final /* synthetic */ zzab zzcb;

    private zzad(zzab zzab) {
        this.zzcb = zzab;
        this.state = 0;
        this.zzbx = new Messenger(new zza(Looper.getMainLooper(), new zzae(this)));
        this.zzbz = new ArrayDeque();
        this.zzca = new SparseArray();
    }

    final synchronized boolean zzb(zzak zzak) {
        boolean z = false;
        boolean z2 = true;
        synchronized (this) {
            switch (this.state) {
                case 0:
                    this.zzbz.add(zzak);
                    if (this.state == 0) {
                        z = true;
                    }
                    Preconditions.checkState(z);
                    if (Log.isLoggable("MessengerIpcClient", 2)) {
                        Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                    }
                    this.state = 1;
                    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                    intent.setPackage("com.google.android.gms");
                    if (!ConnectionTracker.getInstance().bindService(this.zzcb.zzx, intent, this, 1)) {
                        zza(0, "Unable to bind to service");
                        break;
                    }
                    this.zzcb.zzbu.schedule(new zzaf(this), 30, TimeUnit.SECONDS);
                    break;
                case 1:
                    this.zzbz.add(zzak);
                    break;
                case 2:
                    this.zzbz.add(zzak);
                    zzy();
                    break;
                case 3:
                case 4:
                    z2 = false;
                    break;
                default:
                    throw new IllegalStateException("Unknown state: " + this.state);
            }
        }
        return z2;
    }

    final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            Log.d("MessengerIpcClient", "Received response to request: " + i);
        }
        synchronized (this) {
            zzak zzak = (zzak) this.zzca.get(i);
            if (zzak == null) {
                Log.w("MessengerIpcClient", "Received response for unknown request: " + i);
            } else {
                this.zzca.remove(i);
                zzz();
                Bundle data = message.getData();
                if (data.getBoolean("unsupported", false)) {
                    zzak.zza(new zzal(4, "Not supported by GmsCore"));
                } else {
                    zzak.zzb(data);
                }
            }
        }
        return true;
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zza(0, "Null service connection");
        } else {
            try {
                this.zzby = new zzai(iBinder);
                this.state = 2;
                zzy();
            } catch (RemoteException e) {
                zza(0, e.getMessage());
            }
        }
    }

    private final void zzy() {
        this.zzcb.zzbu.execute(new zzag(this));
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zza(2, "Service disconnected");
    }

    final synchronized void zza(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String str2 = "MessengerIpcClient";
            String str3 = "Disconnected: ";
            String valueOf = String.valueOf(str);
            Log.d(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
        switch (this.state) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                ConnectionTracker.getInstance().unbindService(this.zzcb.zzx, this);
                zzal zzal = new zzal(i, str);
                for (zzak zza : this.zzbz) {
                    zza.zza(zzal);
                }
                this.zzbz.clear();
                for (int i2 = 0; i2 < this.zzca.size(); i2++) {
                    ((zzak) this.zzca.valueAt(i2)).zza(zzal);
                }
                this.zzca.clear();
                break;
            case 3:
                this.state = 4;
                break;
            case 4:
                break;
            default:
                throw new IllegalStateException("Unknown state: " + this.state);
        }
    }

    final synchronized void zzz() {
        if (this.state == 2 && this.zzbz.isEmpty() && this.zzca.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker.getInstance().unbindService(this.zzcb.zzx, this);
        }
    }

    final synchronized void zzaa() {
        if (this.state == 1) {
            zza(1, "Timed out while binding");
        }
    }

    final synchronized void zza(int i) {
        zzak zzak = (zzak) this.zzca.get(i);
        if (zzak != null) {
            Log.w("MessengerIpcClient", "Timing out request: " + i);
            this.zzca.remove(i);
            zzak.zza(new zzal(3, "Timed out waiting for response"));
            zzz();
        }
    }
}
