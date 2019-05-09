// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbfc<M extends zzbfc<M>> extends zzbfi
{
    protected zzbfe zzebk;
    
    @Override
    public void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzebk != null) {
            for (int i = 0; i < this.zzebk.size(); ++i) {
                this.zzebk.zzdg(i).zza(zzbfa);
            }
        }
    }
    
    protected final boolean zza(final zzbez zzbez, final int n) throws IOException {
        final int position = zzbez.getPosition();
        if (!zzbez.zzbq(n)) {
            return false;
        }
        final int n2 = n >>> 3;
        final zzbfk zzbfk = new zzbfk(n, zzbez.zzab(position, zzbez.getPosition() - position));
        zzbff zzdf = null;
        if (this.zzebk == null) {
            this.zzebk = new zzbfe();
        }
        else {
            zzdf = this.zzebk.zzdf(n2);
        }
        zzbff zzbff = zzdf;
        if (zzdf == null) {
            zzbff = new zzbff();
            this.zzebk.zza(n2, zzbff);
        }
        zzbff.zza(zzbfk);
        return true;
    }
    
    @Override
    protected int zzr() {
        int n = 0;
        int n3;
        if (this.zzebk != null) {
            int n2 = 0;
            while (true) {
                n3 = n2;
                if (n >= this.zzebk.size()) {
                    break;
                }
                n2 += this.zzebk.zzdg(n).zzr();
                ++n;
            }
        }
        else {
            n3 = 0;
        }
        return n3;
    }
}
