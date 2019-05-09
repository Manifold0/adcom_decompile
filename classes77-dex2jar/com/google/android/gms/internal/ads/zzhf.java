// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzhf implements zzgj
{
    private final /* synthetic */ zzhd zzajt;
    
    zzhf(final zzhd zzajt) {
        this.zzajt = zzajt;
    }
    
    @Override
    public final void zzh(final boolean b) {
        if (b) {
            this.zzajt.connect();
            return;
        }
        this.zzajt.disconnect();
    }
}
