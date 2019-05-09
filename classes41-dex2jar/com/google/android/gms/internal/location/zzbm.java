// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;
import android.os.Looper;

public final class zzbm
{
    public static Looper zza(@Nullable final Looper looper) {
        if (looper != null) {
            return looper;
        }
        return zzc();
    }
    
    public static Looper zzc() {
        Preconditions.checkState(Looper.myLooper() != null, (Object)"Can't create handler inside thread that has not called Looper.prepare()");
        return Looper.myLooper();
    }
}
