// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzik extends zzbfc<zzik>
{
    private int[] zzans;
    private Integer zzanu;
    
    public zzik() {
        this.zzanu = null;
        this.zzans = zzbfl.zzeby;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzik zzm(final zzbez zzbez) throws IOException {
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
                case 8: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzanu = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 16: {
                    final int zzb = zzbfl.zzb(zzbez, 16);
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
                case 18: {
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
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzanu != null) {
            zzbfa.zzm(1, (int)this.zzanu);
        }
        if (this.zzans != null && this.zzans.length > 0) {
            for (int i = 0; i < this.zzans.length; ++i) {
                zzbfa.zzm(2, this.zzans[i]);
            }
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        final int n = 0;
        int zzr;
        final int n2 = zzr = super.zzr();
        if (this.zzanu != null) {
            zzr = n2 + zzbfa.zzq(1, (int)this.zzanu);
        }
        int n3 = zzr;
        if (this.zzans != null) {
            n3 = zzr;
            if (this.zzans.length > 0) {
                int n4 = 0;
                for (int i = n; i < this.zzans.length; ++i) {
                    n4 += zzbfa.zzce(this.zzans[i]);
                }
                n3 = zzr + n4 + this.zzans.length * 1;
            }
        }
        return n3;
    }
}
