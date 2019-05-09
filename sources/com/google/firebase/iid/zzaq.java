package com.google.firebase.iid;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzaq {
    private final Executor zzbj;
    @GuardedBy("this")
    private final Map<Pair<String, String>, Task<String>> zzco = new ArrayMap();

    zzaq(Executor executor) {
        this.zzbj = executor;
    }

    final synchronized Task<String> zza(String str, String str2, zzas zzas) {
        Task<String> task;
        Pair pair = new Pair(str, str2);
        task = (Task) this.zzco.get(pair);
        if (task == null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(pair);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 24).append("Making new request for: ").append(valueOf).toString());
            }
            task = zzas.zzs().continueWithTask(this.zzbj, new zzar(this, pair));
            this.zzco.put(pair, task);
        } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf2 = String.valueOf(pair);
            Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf2).length() + 29).append("Joining ongoing request for: ").append(valueOf2).toString());
        }
        return task;
    }

    final /* synthetic */ Task zza(Pair pair, Task task) throws Exception {
        synchronized (this) {
            this.zzco.remove(pair);
        }
        return task;
    }
}
