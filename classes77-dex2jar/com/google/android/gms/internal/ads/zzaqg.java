// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzaqg implements Iterable<zzaqe>
{
    private final List<zzaqe> zzday;
    
    public zzaqg() {
        this.zzday = new ArrayList<zzaqe>();
    }
    
    public static boolean zzb(final zzapw zzapw) {
        final zzaqe zzc = zzc(zzapw);
        if (zzc != null) {
            zzc.zzdav.abort();
            return true;
        }
        return false;
    }
    
    static zzaqe zzc(final zzapw zzapw) {
        for (final zzaqe zzaqe : zzbv.zzff()) {
            if (zzaqe.zzcyg == zzapw) {
                return zzaqe;
            }
        }
        return null;
    }
    
    @Override
    public final Iterator<zzaqe> iterator() {
        return this.zzday.iterator();
    }
    
    public final void zza(final zzaqe zzaqe) {
        this.zzday.add(zzaqe);
    }
    
    public final void zzb(final zzaqe zzaqe) {
        this.zzday.remove(zzaqe);
    }
    
    public final int zztx() {
        return this.zzday.size();
    }
}
