// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziu extends zzbfc<zziu>
{
    private static volatile zziu[] zzaow;
    private zziy zzaox;
    private zzja zzaoy;
    private zzjb zzaoz;
    private zzjc zzapa;
    private zziv zzapb;
    private zziz zzapc;
    private zzix zzapd;
    private Integer zzape;
    private Integer zzapf;
    private zzis zzapg;
    private Integer zzaph;
    private Integer zzapi;
    private Integer zzapj;
    private Integer zzapk;
    private Integer zzapl;
    private Long zzapm;
    
    public zziu() {
        this.zzaox = null;
        this.zzaoy = null;
        this.zzaoz = null;
        this.zzapa = null;
        this.zzapb = null;
        this.zzapc = null;
        this.zzapd = null;
        this.zzape = null;
        this.zzapf = null;
        this.zzapg = null;
        this.zzaph = null;
        this.zzapi = null;
        this.zzapj = null;
        this.zzapk = null;
        this.zzapl = null;
        this.zzapm = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public static zziu[] zzhu() {
        Label_0027: {
            if (zziu.zzaow != null) {
                break Label_0027;
            }
            synchronized (zzbfg.zzebs) {
                if (zziu.zzaow == null) {
                    zziu.zzaow = new zziu[0];
                }
                return zziu.zzaow;
            }
        }
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzaox != null) {
            zzbfa.zza(5, (zzbfi)this.zzaox);
        }
        if (this.zzaoy != null) {
            zzbfa.zza(6, (zzbfi)this.zzaoy);
        }
        if (this.zzaoz != null) {
            zzbfa.zza(7, (zzbfi)this.zzaoz);
        }
        if (this.zzapa != null) {
            zzbfa.zza(8, (zzbfi)this.zzapa);
        }
        if (this.zzapb != null) {
            zzbfa.zza(9, (zzbfi)this.zzapb);
        }
        if (this.zzapc != null) {
            zzbfa.zza(10, (zzbfi)this.zzapc);
        }
        if (this.zzapd != null) {
            zzbfa.zza(11, (zzbfi)this.zzapd);
        }
        if (this.zzape != null) {
            zzbfa.zzm(12, (int)this.zzape);
        }
        if (this.zzapf != null) {
            zzbfa.zzm(13, (int)this.zzapf);
        }
        if (this.zzapg != null) {
            zzbfa.zza(14, (zzbfi)this.zzapg);
        }
        if (this.zzaph != null) {
            zzbfa.zzm(15, (int)this.zzaph);
        }
        if (this.zzapi != null) {
            zzbfa.zzm(16, (int)this.zzapi);
        }
        if (this.zzapj != null) {
            zzbfa.zzm(17, (int)this.zzapj);
        }
        if (this.zzapk != null) {
            zzbfa.zzm(18, (int)this.zzapk);
        }
        if (this.zzapl != null) {
            zzbfa.zzm(19, (int)this.zzapl);
        }
        if (this.zzapm != null) {
            zzbfa.zza(20, (long)this.zzapm);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzaox != null) {
            zzr = n + zzbfa.zzb(5, (zzbfi)this.zzaox);
        }
        int n2 = zzr;
        if (this.zzaoy != null) {
            n2 = zzr + zzbfa.zzb(6, (zzbfi)this.zzaoy);
        }
        int n3 = n2;
        if (this.zzaoz != null) {
            n3 = n2 + zzbfa.zzb(7, (zzbfi)this.zzaoz);
        }
        int n4 = n3;
        if (this.zzapa != null) {
            n4 = n3 + zzbfa.zzb(8, (zzbfi)this.zzapa);
        }
        int n5 = n4;
        if (this.zzapb != null) {
            n5 = n4 + zzbfa.zzb(9, (zzbfi)this.zzapb);
        }
        int n6 = n5;
        if (this.zzapc != null) {
            n6 = n5 + zzbfa.zzb(10, (zzbfi)this.zzapc);
        }
        int n7 = n6;
        if (this.zzapd != null) {
            n7 = n6 + zzbfa.zzb(11, (zzbfi)this.zzapd);
        }
        int n8 = n7;
        if (this.zzape != null) {
            n8 = n7 + zzbfa.zzq(12, (int)this.zzape);
        }
        int n9 = n8;
        if (this.zzapf != null) {
            n9 = n8 + zzbfa.zzq(13, (int)this.zzapf);
        }
        int n10 = n9;
        if (this.zzapg != null) {
            n10 = n9 + zzbfa.zzb(14, (zzbfi)this.zzapg);
        }
        int n11 = n10;
        if (this.zzaph != null) {
            n11 = n10 + zzbfa.zzq(15, (int)this.zzaph);
        }
        int n12 = n11;
        if (this.zzapi != null) {
            n12 = n11 + zzbfa.zzq(16, (int)this.zzapi);
        }
        int n13 = n12;
        if (this.zzapj != null) {
            n13 = n12 + zzbfa.zzq(17, (int)this.zzapj);
        }
        int n14 = n13;
        if (this.zzapk != null) {
            n14 = n13 + zzbfa.zzq(18, (int)this.zzapk);
        }
        int n15 = n14;
        if (this.zzapl != null) {
            n15 = n14 + zzbfa.zzq(19, (int)this.zzapl);
        }
        int n16 = n15;
        if (this.zzapm != null) {
            n16 = n15 + zzbfa.zze(20, (long)this.zzapm);
        }
        return n16;
    }
}
