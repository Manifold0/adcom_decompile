// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class zzals
{
    private final List<String> zzctc;
    private final List<Double> zzctd;
    private final List<Double> zzcte;
    
    public zzals() {
        this.zzctc = new ArrayList<String>();
        this.zzctd = new ArrayList<Double>();
        this.zzcte = new ArrayList<Double>();
    }
    
    public final zzals zza(final String s, final double n, final double n2) {
        int i;
        for (i = 0; i < this.zzctc.size(); ++i) {
            final double doubleValue = this.zzcte.get(i);
            final double doubleValue2 = this.zzctd.get(i);
            if (n < doubleValue || (doubleValue == n && n2 < doubleValue2)) {
                break;
            }
        }
        this.zzctc.add(i, s);
        this.zzcte.add(i, n);
        this.zzctd.add(i, n2);
        return this;
    }
    
    public final zzalp zzrz() {
        return new zzalp(this, null);
    }
}
