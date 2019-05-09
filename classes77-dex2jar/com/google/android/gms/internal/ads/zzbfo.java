// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfo extends zzbfc<zzbfo>
{
    private static volatile zzbfo[] zzecw;
    public byte[] zzecx;
    public byte[] zzecy;
    
    public zzbfo() {
        this.zzecx = null;
        this.zzecy = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public static zzbfo[] zzagt() {
        Label_0027: {
            if (zzbfo.zzecw != null) {
                break Label_0027;
            }
            synchronized (zzbfg.zzebs) {
                if (zzbfo.zzecw == null) {
                    zzbfo.zzecw = new zzbfo[0];
                }
                return zzbfo.zzecw;
            }
        }
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        zzbfa.zza(1, this.zzecx);
        if (this.zzecy != null) {
            zzbfa.zza(2, this.zzecy);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int n = super.zzr() + zzbfa.zzb(1, this.zzecx);
        if (this.zzecy != null) {
            n += zzbfa.zzb(2, this.zzecy);
        }
        return n;
    }
}
