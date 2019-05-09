// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzij extends zzbfc<zzij>
{
    private String zzanq;
    private Integer zzanr;
    private int[] zzans;
    private zzis zzant;
    
    public zzij() {
        this.zzanq = null;
        this.zzanr = null;
        this.zzans = zzbfl.zzeby;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzij zzl(final zzbez zzbez) throws IOException {
    Label_0073:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0073;
                    }
                    continue;
                }
                case 0: {
                    break Label_0073;
                }
                case 10: {
                    this.zzanq = zzbez.readString();
                    continue;
                }
                case 16: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzanr = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 24: {
                    final int zzb = zzbfl.zzb(zzbez, 24);
                    int length;
                    if (this.zzans == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzans.length;
                    }
                    final int[] zzans = new int[zzb + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.zzans, 0, zzans, 0, length);
                        i = length;
                    }
                    while (i < zzans.length - 1) {
                        zzans[i] = zzbez.zzacc();
                        zzbez.zzabk();
                        ++i;
                    }
                    zzans[i] = zzbez.zzacc();
                    this.zzans = zzans;
                    continue;
                }
                case 26: {
                    final int zzbr = zzbez.zzbr(zzbez.zzacc());
                    final int position2 = zzbez.getPosition();
                    int n = 0;
                    while (zzbez.zzagn() > 0) {
                        zzbez.zzacc();
                        ++n;
                    }
                    zzbez.zzdc(position2);
                    int length2;
                    if (this.zzans == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zzans.length;
                    }
                    final int[] zzans2 = new int[n + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.zzans, 0, zzans2, 0, length2);
                        j = length2;
                    }
                    while (j < zzans2.length) {
                        zzans2[j] = zzbez.zzacc();
                        ++j;
                    }
                    this.zzans = zzans2;
                    zzbez.zzbs(zzbr);
                    continue;
                }
                case 34: {
                    if (this.zzant == null) {
                        this.zzant = new zzis();
                    }
                    zzbez.zza((zzbfi)this.zzant);
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzanq != null) {
            zzbfa.zzf(1, this.zzanq);
        }
        if (this.zzanr != null) {
            zzbfa.zzm(2, (int)this.zzanr);
        }
        if (this.zzans != null && this.zzans.length > 0) {
            for (int i = 0; i < this.zzans.length; ++i) {
                zzbfa.zzm(3, this.zzans[i]);
            }
        }
        if (this.zzant != null) {
            zzbfa.zza(4, (zzbfi)this.zzant);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        final int n = 0;
        int zzr;
        final int n2 = zzr = super.zzr();
        if (this.zzanq != null) {
            zzr = n2 + zzbfa.zzg(1, this.zzanq);
        }
        int n3 = zzr;
        if (this.zzanr != null) {
            n3 = zzr + zzbfa.zzq(2, (int)this.zzanr);
        }
        int n4 = n3;
        if (this.zzans != null) {
            n4 = n3;
            if (this.zzans.length > 0) {
                int n5 = 0;
                for (int i = n; i < this.zzans.length; ++i) {
                    n5 += zzbfa.zzce(this.zzans[i]);
                }
                n4 = n3 + n5 + this.zzans.length * 1;
            }
        }
        int n6 = n4;
        if (this.zzant != null) {
            n6 = n4 + zzbfa.zzb(4, (zzbfi)this.zzant);
        }
        return n6;
    }
}
