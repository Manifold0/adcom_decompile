// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.util.Log;
import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzak<T>
{
    final int what;
    final int zzcf;
    final TaskCompletionSource<T> zzcg;
    final Bundle zzch;
    
    zzak(final int zzcf, final int what, final Bundle zzch) {
        this.zzcg = (TaskCompletionSource<T>)new TaskCompletionSource();
        this.zzcf = zzcf;
        this.what = what;
        this.zzch = zzch;
    }
    
    final void finish(final T result) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            final String value = String.valueOf(this);
            final String value2 = String.valueOf(result);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(value).length() + 16 + String.valueOf(value2).length()).append("Finishing ").append(value).append(" with ").append(value2).toString());
        }
        this.zzcg.setResult((Object)result);
    }
    
    @Override
    public String toString() {
        return new StringBuilder(55).append("Request { what=").append(this.what).append(" id=").append(this.zzcf).append(" oneWay=").append(this.zzab()).append("}").toString();
    }
    
    final void zza(final zzal exception) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            final String value = String.valueOf(this);
            final String value2 = String.valueOf(exception);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(value).length() + 14 + String.valueOf(value2).length()).append("Failing ").append(value).append(" with ").append(value2).toString());
        }
        this.zzcg.setException((Exception)exception);
    }
    
    abstract boolean zzab();
    
    abstract void zzb(final Bundle p0);
}
