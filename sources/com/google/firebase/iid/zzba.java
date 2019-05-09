package com.google.firebase.iid;

import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

final class zzba {
    @GuardedBy("itself")
    private final zzaw zzaj;
    @GuardedBy("this")
    private int zzdl = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdm = new ArrayMap();

    zzba(zzaw zzaw) {
        this.zzaj = zzaw;
    }

    final synchronized Task<Void> zza(String str) {
        TaskCompletionSource taskCompletionSource;
        int i;
        synchronized (this.zzaj) {
            Object zzak = this.zzaj.zzak();
            this.zzaj.zzf(new StringBuilder((String.valueOf(zzak).length() + 1) + String.valueOf(str).length()).append(zzak).append(",").append(str).toString());
        }
        taskCompletionSource = new TaskCompletionSource();
        Map map = this.zzdm;
        if (TextUtils.isEmpty(zzak)) {
            i = 0;
        } else {
            i = zzak.split(",").length - 1;
        }
        map.put(Integer.valueOf(i + this.zzdl), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    final synchronized boolean zzaq() {
        return zzar() != null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    final boolean zzc(com.google.firebase.iid.FirebaseInstanceId r4) {
        /*
        r3 = this;
    L_0x0000:
        monitor-enter(r3);
        r1 = r3.zzar();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0017;
    L_0x0007:
        r0 = com.google.firebase.iid.FirebaseInstanceId.zzl();	 Catch:{ all -> 0x0020 }
        if (r0 == 0) goto L_0x0014;
    L_0x000d:
        r0 = "FirebaseInstanceId";
        r1 = "topic sync succeeded";
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x0020 }
    L_0x0014:
        r0 = 1;
        monitor-exit(r3);	 Catch:{ all -> 0x0020 }
    L_0x0016:
        return r0;
    L_0x0017:
        monitor-exit(r3);	 Catch:{ all -> 0x0020 }
        r0 = zza(r4, r1);
        if (r0 != 0) goto L_0x0023;
    L_0x001e:
        r0 = 0;
        goto L_0x0016;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0020 }
        throw r0;
    L_0x0023:
        monitor-enter(r3);
        r0 = r3.zzdm;	 Catch:{ all -> 0x0043 }
        r2 = r3.zzdl;	 Catch:{ all -> 0x0043 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x0043 }
        r0 = r0.remove(r2);	 Catch:{ all -> 0x0043 }
        r0 = (com.google.android.gms.tasks.TaskCompletionSource) r0;	 Catch:{ all -> 0x0043 }
        r3.zzk(r1);	 Catch:{ all -> 0x0043 }
        r1 = r3.zzdl;	 Catch:{ all -> 0x0043 }
        r1 = r1 + 1;
        r3.zzdl = r1;	 Catch:{ all -> 0x0043 }
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0000;
    L_0x003e:
        r1 = 0;
        r0.setResult(r1);
        goto L_0x0000;
    L_0x0043:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzba.zzc(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }

    @Nullable
    @GuardedBy("this")
    private final String zzar() {
        synchronized (this.zzaj) {
            Object zzak = this.zzaj.zzak();
        }
        if (!TextUtils.isEmpty(zzak)) {
            String[] split = zzak.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
        }
        return null;
    }

    private final synchronized boolean zzk(String str) {
        boolean z;
        synchronized (this.zzaj) {
            String zzak = this.zzaj.zzak();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (zzak.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                valueOf = String.valueOf(",");
                valueOf2 = String.valueOf(str);
                this.zzaj.zzf(zzak.substring((valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).length()));
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    @WorkerThread
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) {
        String str2;
        String[] split = str.split("!");
        String valueOf;
        if (split.length != 2) {
            return true;
        }
        String str3;
        str2 = split[0];
        String str4 = split[1];
        int i = -1;
        try {
            switch (str2.hashCode()) {
                case 83:
                    if (str2.equals("S")) {
                        i = 0;
                        break;
                    }
                    break;
                case 85:
                    if (str2.equals("U")) {
                        boolean z = true;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    firebaseInstanceId.zzb(str4);
                    if (!FirebaseInstanceId.zzl()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                    return true;
                case 1:
                    firebaseInstanceId.zzc(str4);
                    if (!FirebaseInstanceId.zzl()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                    return true;
                default:
                    return true;
            }
        } catch (IOException e) {
            str3 = "FirebaseInstanceId";
            str2 = "Topic sync failed: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
                valueOf = new String(str2);
            } else {
                valueOf = str2.concat(valueOf);
            }
            Log.e(str3, valueOf);
            return false;
        }
        str3 = "FirebaseInstanceId";
        str2 = "Topic sync failed: ";
        valueOf = String.valueOf(e.getMessage());
        if (valueOf.length() == 0) {
            valueOf = str2.concat(valueOf);
        } else {
            valueOf = new String(str2);
        }
        Log.e(str3, valueOf);
        return false;
    }
}
