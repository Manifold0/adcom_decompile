// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziw extends zzbfc<zziw>
{
    private Integer zzapp;
    
    public zziw() {
        this.zzapp = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zziw zzt(final zzbez zzbez) throws IOException {
    Label_0041:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0041;
                    }
                    continue;
                }
                case 0: {
                    break Label_0041;
                }
                case 8: {
                    final int position = zzbez.getPosition();
                    try {
                        final int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 3) {
                            throw new IllegalArgumentException(new StringBuilder(46).append(zzacc).append(" is not a valid enum VideoErrorCode").toString());
                        }
                        this.zzapp = zzacc;
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
        if (this.zzapp != null) {
            zzbfa.zzm(1, (int)this.zzapp);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzapp != null) {
            zzr += zzbfa.zzq(1, (int)this.zzapp);
        }
        return zzr;
    }
}
