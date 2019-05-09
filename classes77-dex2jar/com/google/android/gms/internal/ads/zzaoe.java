// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

@zzadh
public final class zzaoe
{
    public static final Executor zzcvy;
    public static final Executor zzcvz;
    private static final zzaod zzcwa;
    private static final zzaod zzcwb;
    
    static {
        zzcvy = new zzaof();
        zzcvz = new zzaog();
        zzcwa = zza(zzaoe.zzcvy);
        zzcwb = zza(zzaoe.zzcvz);
    }
    
    public static zzaod zza(final Executor executor) {
        return new zzaoh(executor, null);
    }
}
