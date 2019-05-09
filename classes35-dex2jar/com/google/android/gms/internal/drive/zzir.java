// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.io.IOException;

public abstract class zzir<M extends zzir<M>> extends zzix
{
    protected zzit zzmw;
    
    @Override
    public void zza(final zzip zzip) throws IOException {
        if (this.zzmw != null) {
            for (int i = 0; i < this.zzmw.size(); ++i) {
                this.zzmw.zzs(i).zza(zzip);
            }
        }
    }
    
    protected final boolean zza(final zzio zzio, final int n) throws IOException {
        final int position = zzio.getPosition();
        if (!zzio.zzk(n)) {
            return false;
        }
        final int n2 = n >>> 3;
        final zziz zziz = new zziz(n, zzio.zza(position, zzio.getPosition() - position));
        zziu zzr = null;
        if (this.zzmw == null) {
            this.zzmw = new zzit();
        }
        else {
            zzr = this.zzmw.zzr(n2);
        }
        zziu zziu = zzr;
        if (zzr == null) {
            zziu = new zziu();
            this.zzmw.zza(n2, zziu);
        }
        zziu.zza(zziz);
        return true;
    }
    
    @Override
    protected int zzaq() {
        int n = 0;
        int n3;
        if (this.zzmw != null) {
            int n2 = 0;
            while (true) {
                n3 = n2;
                if (n >= this.zzmw.size()) {
                    break;
                }
                n2 += this.zzmw.zzs(n).zzaq();
                ++n;
            }
        }
        else {
            n3 = 0;
        }
        return n3;
    }
}
