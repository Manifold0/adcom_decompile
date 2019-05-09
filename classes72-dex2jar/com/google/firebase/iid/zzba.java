// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import com.google.android.gms.tasks.Task;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.support.annotation.WorkerThread;
import java.io.IOException;
import android.util.Log;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import android.support.annotation.GuardedBy;

final class zzba
{
    @GuardedBy("itself")
    private final zzaw zzaj;
    @GuardedBy("this")
    private int zzdl;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdm;
    
    zzba(final zzaw zzaj) {
        this.zzdl = 0;
        this.zzdm = (Map<Integer, TaskCompletionSource<Void>>)new ArrayMap();
        this.zzaj = zzaj;
    }
    
    @WorkerThread
    private static boolean zza(final FirebaseInstanceId firebaseInstanceId, String s) {
        final String[] split = s.split("!");
        if (split.length == 2) {
            String s2 = null;
        Label_0092_Outer:
            while (true) {
                s = split[0];
                s2 = split[1];
                int n2;
                final int n = n2 = -1;
                while (true) {
                    Label_0183: {
                        Label_0181: {
                            while (true) {
                                Label_0168: {
                                    try {
                                        switch (s.hashCode()) {
                                            case 83: {
                                                n2 = n;
                                                if (s.equals("S")) {
                                                    n2 = 0;
                                                }
                                                break Label_0183;
                                            }
                                            case 85: {
                                                n2 = n;
                                                if (s.equals("U")) {
                                                    n2 = 1;
                                                }
                                                break Label_0183;
                                            }
                                            default: {
                                                break Label_0181;
                                            }
                                            case 84: {
                                                break Label_0183;
                                            }
                                        }
                                        while (true) {
                                            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                                            return true;
                                            firebaseInstanceId.zzb(s2);
                                            continue Label_0092_Outer;
                                        }
                                    }
                                    // iftrue(Label_0208:, !FirebaseInstanceId.zzl())
                                    catch (IOException ex) {
                                        final String value = String.valueOf(ex.getMessage());
                                        if (value.length() != 0) {
                                            final String concat = "Topic sync failed: ".concat(value);
                                            Log.e("FirebaseInstanceId", concat);
                                            return false;
                                        }
                                        break Label_0168;
                                    }
                                    break;
                                }
                                final String concat = new String("Topic sync failed: ");
                                continue;
                            }
                        }
                        n2 = n;
                    }
                    switch (n2) {
                        case 0: {
                            continue;
                        }
                        case 1: {
                            break Label_0092_Outer;
                        }
                        default: {
                            return true;
                        }
                    }
                    break;
                }
            }
            firebaseInstanceId.zzc(s2);
            if (FirebaseInstanceId.zzl()) {
                Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                return true;
            }
        }
        Label_0208: {
            return true;
        }
    }
    
    @GuardedBy("this")
    @Nullable
    private final String zzar() {
        Object o = this.zzaj;
        synchronized (o) {
            final String zzak = this.zzaj.zzak();
            // monitorexit(o)
            if (!TextUtils.isEmpty((CharSequence)zzak)) {
                o = zzak.split(",");
                if (o.length > 1 && !TextUtils.isEmpty(o[1])) {
                    return o[1];
                }
            }
        }
        return null;
    }
    
    private final boolean zzk(String o) {
        while (true) {
            Label_0142: {
                while (true) {
                    String s;
                    synchronized (this) {
                        synchronized (this.zzaj) {
                            final String zzak = this.zzaj.zzak();
                            s = String.valueOf(",");
                            final String value = String.valueOf(o);
                            if (value.length() != 0) {
                                s = s.concat(value);
                            }
                            else {
                                s = new String(s);
                            }
                            if (!zzak.startsWith(s)) {
                                break Label_0142;
                            }
                            s = String.valueOf(",");
                            o = String.valueOf(o);
                            if (((String)o).length() != 0) {
                                o = s.concat((String)o);
                                o = zzak.substring(((String)o).length());
                                this.zzaj.zzf((String)o);
                                return true;
                            }
                        }
                    }
                    o = new String(s);
                    continue;
                }
            }
            // monitorexit(zzaw)
            return false;
        }
    }
    
    final Task<Void> zza(final String s) {
        while (true) {
            while (true) {
                final String zzak;
                synchronized (this) {
                    Object o = this.zzaj;
                    synchronized (o) {
                        zzak = this.zzaj.zzak();
                        this.zzaj.zzf(new StringBuilder(String.valueOf(zzak).length() + 1 + String.valueOf(s).length()).append(zzak).append(",").append(s).toString());
                        // monitorexit(o)
                        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                        o = this.zzdm;
                        if (TextUtils.isEmpty((CharSequence)zzak)) {
                            final int n = 0;
                            ((Map<Integer, TaskCompletionSource>)o).put(Integer.valueOf(n + this.zzdl), taskCompletionSource);
                            return (Task<Void>)taskCompletionSource.getTask();
                        }
                    }
                }
                final int n = zzak.split(",").length - 1;
                continue;
            }
        }
    }
    
    final boolean zzaq() {
        synchronized (this) {
            return this.zzar() != null;
        }
    }
    
    @WorkerThread
    final boolean zzc(final FirebaseInstanceId firebaseInstanceId) {
        while (true) {
            final String zzar;
            synchronized (this) {
                zzar = this.zzar();
                if (zzar == null) {
                    if (FirebaseInstanceId.zzl()) {
                        Log.d("FirebaseInstanceId", "topic sync succeeded");
                    }
                    return true;
                }
                // monitorexit(this)
                if (!zza(firebaseInstanceId, zzar)) {
                    return false;
                }
            }
            synchronized (this) {
                final TaskCompletionSource<Void> taskCompletionSource = this.zzdm.remove(this.zzdl);
                this.zzk(zzar);
                ++this.zzdl;
                // monitorexit(this)
                if (taskCompletionSource == null) {
                    continue;
                }
                taskCompletionSource.setResult((Object)null);
            }
        }
    }
}
