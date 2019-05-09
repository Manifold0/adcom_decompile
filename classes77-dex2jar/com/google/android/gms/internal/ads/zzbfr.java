// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfr extends zzbfc<zzbfr>
{
    private zzbfo[] zzeda;
    private byte[] zzedb;
    private byte[] zzedc;
    private Integer zzedd;
    private zzbfs zzedh;
    private byte[] zzedi;
    
    public zzbfr() {
        this.zzedh = null;
        this.zzeda = zzbfo.zzagt();
        this.zzedb = null;
        this.zzedc = null;
        this.zzedd = null;
        this.zzedi = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzedh != null) {
            zzbfa.zza(1, (zzbfi)this.zzedh);
        }
        if (this.zzeda != null && this.zzeda.length > 0) {
            for (int i = 0; i < this.zzeda.length; ++i) {
                final zzbfo zzbfo = this.zzeda[i];
                if (zzbfo != null) {
                    zzbfa.zza(2, (zzbfi)zzbfo);
                }
            }
        }
        if (this.zzedb != null) {
            zzbfa.zza(3, this.zzedb);
        }
        if (this.zzedc != null) {
            zzbfa.zza(4, this.zzedc);
        }
        if (this.zzedd != null) {
            zzbfa.zzm(5, (int)this.zzedd);
        }
        if (this.zzedi != null) {
            zzbfa.zza(6, this.zzedi);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzedh != null) {
            zzr = n + zzbfa.zzb(1, (zzbfi)this.zzedh);
        }
        int n2 = zzr;
        if (this.zzeda != null) {
            n2 = zzr;
            if (this.zzeda.length > 0) {
                int n3;
                for (int i = 0; i < this.zzeda.length; ++i, zzr = n3) {
                    final zzbfo zzbfo = this.zzeda[i];
                    n3 = zzr;
                    if (zzbfo != null) {
                        n3 = zzr + zzbfa.zzb(2, (zzbfi)zzbfo);
                    }
                }
                n2 = zzr;
            }
        }
        int n4 = n2;
        if (this.zzedb != null) {
            n4 = n2 + zzbfa.zzb(3, this.zzedb);
        }
        int n5 = n4;
        if (this.zzedc != null) {
            n5 = n4 + zzbfa.zzb(4, this.zzedc);
        }
        int n6 = n5;
        if (this.zzedd != null) {
            n6 = n5 + zzbfa.zzq(5, (int)this.zzedd);
        }
        int n7 = n6;
        if (this.zzedi != null) {
            n7 = n6 + zzbfa.zzb(6, this.zzedi);
        }
        return n7;
    }
}
