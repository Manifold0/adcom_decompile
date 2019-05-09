// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

@zzadh
final class zzst
{
    private final List<zzts> zzxo;
    
    zzst() {
        this.zzxo = new ArrayList<zzts>();
    }
    
    final void zza(final zztt zztt) {
        final Handler zzcrm = zzakk.zzcrm;
        final Iterator<zzts> iterator = this.zzxo.iterator();
        while (iterator.hasNext()) {
            zzcrm.post((Runnable)new zztr(this, iterator.next(), zztt));
        }
        this.zzxo.clear();
    }
}
