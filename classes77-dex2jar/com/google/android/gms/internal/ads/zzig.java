// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzig extends zzbfc<zzig>
{
    public String zzamu;
    private zzis zzamv;
    private Integer zzamw;
    public zzit zzamx;
    private Integer zzamy;
    private Integer zzamz;
    private Integer zzana;
    private Integer zzanb;
    
    public zzig() {
        this.zzamu = null;
        this.zzamv = null;
        this.zzamw = null;
        this.zzamx = null;
        this.zzamy = null;
        this.zzamz = null;
        this.zzana = null;
        this.zzanb = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzig zzi(final zzbez zzbez) throws IOException {
    Label_0097:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0097;
                    }
                    continue;
                }
                case 0: {
                    break Label_0097;
                }
                case 10: {
                    this.zzamu = zzbez.readString();
                    continue;
                }
                case 18: {
                    if (this.zzamv == null) {
                        this.zzamv = new zzis();
                    }
                    zzbez.zza((zzbfi)this.zzamv);
                    continue;
                }
                case 24: {
                    this.zzamw = zzbez.zzacc();
                    continue;
                }
                case 34: {
                    if (this.zzamx == null) {
                        this.zzamx = new zzit();
                    }
                    zzbez.zza((zzbfi)this.zzamx);
                    continue;
                }
                case 40: {
                    this.zzamy = zzbez.zzacc();
                    continue;
                }
                case 48: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzamz = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 56: {
                    final int position2 = zzbez.getPosition();
                    try {
                        this.zzana = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex2) {
                        zzbez.zzdc(position2);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 64: {
                    final int position3 = zzbez.getPosition();
                    try {
                        this.zzanb = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex3) {
                        zzbez.zzdc(position3);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzamu != null) {
            zzbfa.zzf(1, this.zzamu);
        }
        if (this.zzamv != null) {
            zzbfa.zza(2, (zzbfi)this.zzamv);
        }
        if (this.zzamw != null) {
            zzbfa.zzm(3, (int)this.zzamw);
        }
        if (this.zzamx != null) {
            zzbfa.zza(4, (zzbfi)this.zzamx);
        }
        if (this.zzamy != null) {
            zzbfa.zzm(5, (int)this.zzamy);
        }
        if (this.zzamz != null) {
            zzbfa.zzm(6, (int)this.zzamz);
        }
        if (this.zzana != null) {
            zzbfa.zzm(7, (int)this.zzana);
        }
        if (this.zzanb != null) {
            zzbfa.zzm(8, (int)this.zzanb);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzamu != null) {
            zzr = n + zzbfa.zzg(1, this.zzamu);
        }
        int n2 = zzr;
        if (this.zzamv != null) {
            n2 = zzr + zzbfa.zzb(2, (zzbfi)this.zzamv);
        }
        int n3 = n2;
        if (this.zzamw != null) {
            n3 = n2 + zzbfa.zzq(3, (int)this.zzamw);
        }
        int n4 = n3;
        if (this.zzamx != null) {
            n4 = n3 + zzbfa.zzb(4, (zzbfi)this.zzamx);
        }
        int n5 = n4;
        if (this.zzamy != null) {
            n5 = n4 + zzbfa.zzq(5, (int)this.zzamy);
        }
        int n6 = n5;
        if (this.zzamz != null) {
            n6 = n5 + zzbfa.zzq(6, (int)this.zzamz);
        }
        int n7 = n6;
        if (this.zzana != null) {
            n7 = n6 + zzbfa.zzq(7, (int)this.zzana);
        }
        int n8 = n7;
        if (this.zzanb != null) {
            n8 = n7 + zzbfa.zzq(8, (int)this.zzanb);
        }
        return n8;
    }
}
