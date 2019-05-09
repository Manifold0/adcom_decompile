package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzak<T> {
    final int what;
    final int zzcf;
    final TaskCompletionSource<T> zzcg = new TaskCompletionSource();
    final Bundle zzch;

    zzak(int i, int i2, Bundle bundle) {
        this.zzcf = i;
        this.what = i2;
        this.zzch = bundle;
    }

    abstract boolean zzab();

    abstract void zzb(Bundle bundle);

    final void finish(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(t);
            Log.d("MessengerIpcClient", new StringBuilder((String.valueOf(valueOf).length() + 16) + String.valueOf(valueOf2).length()).append("Finishing ").append(valueOf).append(" with ").append(valueOf2).toString());
        }
        this.zzcg.setResult(t);
    }

    final void zza(zzal zzal) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzal);
            Log.d("MessengerIpcClient", new StringBuilder((String.valueOf(valueOf).length() + 14) + String.valueOf(valueOf2).length()).append("Failing ").append(valueOf).append(" with ").append(valueOf2).toString());
        }
        this.zzcg.setException(zzal);
    }

    public String toString() {
        int i = this.what;
        int i2 = this.zzcf;
        return "Request { what=" + i + " id=" + i2 + " oneWay=" + zzab() + "}";
    }
}
