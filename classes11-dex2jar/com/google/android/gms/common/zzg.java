// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzg extends zze
{
    private static final WeakReference<byte[]> zzw;
    private WeakReference<byte[]> zzv;
    
    static {
        zzw = new WeakReference<byte[]>(null);
    }
    
    zzg(final byte[] array) {
        super(array);
        this.zzv = zzg.zzw;
    }
    
    @Override
    final byte[] getBytes() {
        synchronized (this) {
            byte[] zzd;
            if ((zzd = this.zzv.get()) == null) {
                zzd = this.zzd();
                this.zzv = new WeakReference<byte[]>(zzd);
            }
            return zzd;
        }
    }
    
    protected abstract byte[] zzd();
}
