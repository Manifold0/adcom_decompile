// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.tasks.Task;
import android.util.Pair;
import java.util.Map;
import java.util.concurrent.Executor;

final class zzaq
{
    private final Executor zzbj;
    @GuardedBy("this")
    private final Map<Pair<String, String>, Task<String>> zzco;
    
    zzaq(final Executor zzbj) {
        this.zzco = (Map<Pair<String, String>, Task<String>>)new ArrayMap();
        this.zzbj = zzbj;
    }
    
    final Task<String> zza(String s, final String s2, final zzas zzas) {
        synchronized (this) {
            final Pair pair = new Pair((Object)s, (Object)s2);
            final Task<String> task = this.zzco.get(pair);
            Task continueWithTask;
            if (task != null) {
                continueWithTask = task;
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    s = String.valueOf(pair);
                    Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(s).length() + 29).append("Joining ongoing request for: ").append(s).toString());
                    continueWithTask = task;
                }
            }
            else {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    s = String.valueOf(pair);
                    Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(s).length() + 24).append("Making new request for: ").append(s).toString());
                }
                continueWithTask = zzas.zzs().continueWithTask(this.zzbj, (Continuation)new zzar(this, pair));
                this.zzco.put((Pair<String, String>)pair, (Task<String>)continueWithTask);
            }
            return (Task<String>)continueWithTask;
        }
    }
}
