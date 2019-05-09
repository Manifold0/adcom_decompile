// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzhe implements Runnable
{
    private final /* synthetic */ zzhd zzajt;
    
    zzhe(final zzhd zzajt) {
        this.zzajt = zzajt;
    }
    
    @Override
    public final void run() {
        this.zzajt.disconnect();
    }
}
