// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbft extends zzbfc<zzbft>
{
    public String mimeType;
    public Integer zzamf;
    public byte[] zzedl;
    
    public zzbft() {
        this.zzamf = null;
        this.mimeType = null;
        this.zzedl = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzbft zzab(final zzbez zzbez) throws IOException {
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
                        final int zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 1) {
                            throw new IllegalArgumentException(new StringBuilder(36).append(zzabn).append(" is not a valid enum Type").toString());
                        }
                        this.zzamf = zzabn;
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 18: {
                    this.mimeType = zzbez.readString();
                    continue;
                }
                case 26: {
                    this.zzedl = zzbez.readBytes();
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzamf != null) {
            zzbfa.zzm(1, (int)this.zzamf);
        }
        if (this.mimeType != null) {
            zzbfa.zzf(2, this.mimeType);
        }
        if (this.zzedl != null) {
            zzbfa.zza(3, this.zzedl);
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
        if (this.mimeType != null) {
            n2 = zzr + zzbfa.zzg(2, this.mimeType);
        }
        int n3 = n2;
        if (this.zzedl != null) {
            n3 = n2 + zzbfa.zzb(3, this.zzedl);
        }
        return n3;
    }
}
