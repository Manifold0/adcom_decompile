// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzic extends zzbfc<zzic>
{
    private static volatile zzic[] zzame;
    private Integer zzamf;
    private zziq zzamg;
    
    public zzic() {
        this.zzamf = null;
        this.zzamg = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzic zzf(final zzbez zzbez) throws IOException {
    Label_0049:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0049;
                    }
                    continue;
                }
                case 0: {
                    break Label_0049;
                }
                case 8: {
                    final int position = zzbez.getPosition();
                    try {
                        final int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 10) {
                            throw new IllegalArgumentException(new StringBuilder(44).append(zzacc).append(" is not a valid enum AdFormatType").toString());
                        }
                        this.zzamf = zzacc;
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 18: {
                    if (this.zzamg == null) {
                        this.zzamg = new zziq();
                    }
                    zzbez.zza((zzbfi)this.zzamg);
                    continue;
                }
            }
        }
        return this;
    }
    
    public static zzic[] zzhr() {
        Label_0027: {
            if (zzic.zzame != null) {
                break Label_0027;
            }
            synchronized (zzbfg.zzebs) {
                if (zzic.zzame == null) {
                    zzic.zzame = new zzic[0];
                }
                return zzic.zzame;
            }
        }
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzamf != null) {
            zzbfa.zzm(1, (int)this.zzamf);
        }
        if (this.zzamg != null) {
            zzbfa.zza(2, (zzbfi)this.zzamg);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzamf != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzamf);
        }
        int n2 = zzr;
        if (this.zzamg != null) {
            n2 = zzr + zzbfa.zzb(2, (zzbfi)this.zzamg);
        }
        return n2;
    }
}
