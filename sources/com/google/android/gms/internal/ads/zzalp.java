package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzalp {
    private final String[] zzcsu;
    private final double[] zzcsv;
    private final double[] zzcsw;
    private final int[] zzcsx;
    private int zzcsy;

    private zzalp(zzals zzals) {
        int size = zzals.zzctd.size();
        this.zzcsu = (String[]) zzals.zzctc.toArray(new String[size]);
        this.zzcsv = zzo(zzals.zzctd);
        this.zzcsw = zzo(zzals.zzcte);
        this.zzcsx = new int[size];
        this.zzcsy = 0;
    }

    private static double[] zzo(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = ((Double) list.get(i)).doubleValue();
        }
        return dArr;
    }

    public final void zza(double d) {
        this.zzcsy++;
        int i = 0;
        while (i < this.zzcsw.length) {
            if (this.zzcsw[i] <= d && d < this.zzcsv[i]) {
                int[] iArr = this.zzcsx;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.zzcsw[i]) {
                i++;
            } else {
                return;
            }
        }
    }

    public final List<zzalr> zzry() {
        List<zzalr> arrayList = new ArrayList(this.zzcsu.length);
        for (int i = 0; i < this.zzcsu.length; i++) {
            arrayList.add(new zzalr(this.zzcsu[i], this.zzcsw[i], this.zzcsv[i], ((double) this.zzcsx[i]) / ((double) this.zzcsy), this.zzcsx[i]));
        }
        return arrayList;
    }
}
