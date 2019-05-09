// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzajy implements Runnable
{
    private final /* synthetic */ zzajx zzcqt;
    
    zzajy(final zzajx zzcqt) {
        this.zzcqt = zzcqt;
    }
    
    @Override
    public final void run() {
        this.zzcqt.zzcqr = Thread.currentThread();
        this.zzcqt.zzdn();
    }
}
