// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzid extends zzbfc<zzid>
{
    private String zzacp;
    private zzic[] zzamh;
    private Integer zzami;
    
    public zzid() {
        this.zzacp = null;
        this.zzamh = zzic.zzhr();
        this.zzami = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzid zzg(final zzbez zzbez) throws IOException {
    Label_0057:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0057;
                    }
                    continue;
                }
                case 0: {
                    break Label_0057;
                }
                case 10: {
                    this.zzacp = zzbez.readString();
                    continue;
                }
                case 18: {
                    final int zzb = zzbfl.zzb(zzbez, 18);
                    int length;
                    if (this.zzamh == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzamh.length;
                    }
                    final zzic[] zzamh = new zzic[zzb + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.zzamh, 0, zzamh, 0, length);
                        i = length;
                    }
                    while (i < zzamh.length - 1) {
                        zzbez.zza((zzbfi)(zzamh[i] = new zzic()));
                        zzbez.zzabk();
                        ++i;
                    }
                    zzbez.zza((zzbfi)(zzamh[i] = new zzic()));
                    this.zzamh = zzamh;
                    continue;
                }
                case 24: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzami = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzacp != null) {
            zzbfa.zzf(1, this.zzacp);
        }
        if (this.zzamh != null && this.zzamh.length > 0) {
            for (int i = 0; i < this.zzamh.length; ++i) {
                final zzic zzic = this.zzamh[i];
                if (zzic != null) {
                    zzbfa.zza(2, (zzbfi)zzic);
                }
            }
        }
        if (this.zzami != null) {
            zzbfa.zzm(3, (int)this.zzami);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzacp != null) {
            zzr = n + zzbfa.zzg(1, this.zzacp);
        }
        int n2 = zzr;
        if (this.zzamh != null) {
            n2 = zzr;
            if (this.zzamh.length > 0) {
                int n3;
                for (int i = 0; i < this.zzamh.length; ++i, zzr = n3) {
                    final zzic zzic = this.zzamh[i];
                    n3 = zzr;
                    if (zzic != null) {
                        n3 = zzr + zzbfa.zzb(2, (zzbfi)zzic);
                    }
                }
                n2 = zzr;
            }
        }
        int n4 = n2;
        if (this.zzami != null) {
            n4 = n2 + zzbfa.zzq(3, (int)this.zzami);
        }
        return n4;
    }
}
