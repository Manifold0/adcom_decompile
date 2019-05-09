// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.StrictMode;
import java.util.concurrent.Callable;
import android.content.Context;

@zzadh
public final class zzaml
{
    public static <T> T zza(final Context context, final Callable<T> callable) {
        final StrictMode$ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            return callable.call();
        }
        catch (Throwable t) {
            zzane.zzb("Unexpected exception.", t);
            zzadb.zzl(context).zza(t, "StrictModeUtil.runWithLaxStrictMode");
            return null;
        }
        finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
    
    public static <T> T zzb(final Callable<T> callable) throws Exception {
        final StrictMode$ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            return callable.call();
        }
        finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
}
