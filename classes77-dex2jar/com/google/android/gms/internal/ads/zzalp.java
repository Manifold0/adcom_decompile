// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzalp
{
    private final String[] zzcsu;
    private final double[] zzcsv;
    private final double[] zzcsw;
    private final int[] zzcsx;
    private int zzcsy;
    
    private zzalp(final zzals zzals) {
        final int size = zzals.zzctd.size();
        this.zzcsu = zzals.zzctc.toArray(new String[size]);
        this.zzcsv = zzo(zzals.zzctd);
        this.zzcsw = zzo(zzals.zzcte);
        this.zzcsx = new int[size];
        this.zzcsy = 0;
    }
    
    private static double[] zzo(final List<Double> list) {
        final double[] array = new double[list.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = list.get(i);
        }
        return array;
    }
    
    public final void zza(final double n) {
        ++this.zzcsy;
        for (int i = 0; i < this.zzcsw.length; ++i) {
            if (this.zzcsw[i] <= n && n < this.zzcsv[i]) {
                final int[] zzcsx = this.zzcsx;
                ++zzcsx[i];
            }
            if (n < this.zzcsw[i]) {
                break;
            }
        }
    }
    
    public final List<zzalr> zzry() {
        final ArrayList<zzalr> list = new ArrayList<zzalr>(this.zzcsu.length);
        for (int i = 0; i < this.zzcsu.length; ++i) {
            list.add(new zzalr(this.zzcsu[i], this.zzcsw[i], this.zzcsv[i], this.zzcsx[i] / (double)this.zzcsy, this.zzcsx[i]));
        }
        return list;
    }
}
