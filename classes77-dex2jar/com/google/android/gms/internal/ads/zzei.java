// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzei implements Callable
{
    private final String TAG;
    private final String className;
    protected final zzcz zzps;
    protected final zzba zztq;
    private final String zztx;
    protected Method zztz;
    private final int zzud;
    private final int zzue;
    
    public zzei(final zzcz zzps, final String className, final String zztx, final zzba zztq, final int zzud, final int zzue) {
        this.TAG = this.getClass().getSimpleName();
        this.zzps = zzps;
        this.className = className;
        this.zztx = zztx;
        this.zztq = zztq;
        this.zzud = zzud;
        this.zzue = zzue;
    }
    
    protected abstract void zzar() throws IllegalAccessException, InvocationTargetException;
    
    public Void zzat() throws Exception {
        try {
            final long nanoTime = System.nanoTime();
            this.zztz = this.zzps.zza(this.className, this.zztx);
            if (this.zztz == null) {
                return null;
            }
            this.zzar();
            final zzcc zzag = this.zzps.zzag();
            if (zzag != null && this.zzud != Integer.MIN_VALUE) {
                zzag.zza(this.zzue, this.zzud, (System.nanoTime() - nanoTime) / 1000L);
                return null;
            }
        }
        catch (IllegalAccessException ex) {
            return null;
        }
        catch (InvocationTargetException ex2) {}
        return null;
    }
}
