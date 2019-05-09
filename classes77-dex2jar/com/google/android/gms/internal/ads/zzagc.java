// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import android.content.Context;
import java.util.WeakHashMap;

@zzadh
public final class zzagc
{
    private WeakHashMap<Context, zzage> zzckj;
    
    public zzagc() {
        this.zzckj = new WeakHashMap<Context, zzage>();
    }
    
    public final Future<zzaga> zzq(final Context context) {
        return (Future<zzaga>)zzaki.zza((Callable<Object>)new zzagd(this, context));
    }
}
