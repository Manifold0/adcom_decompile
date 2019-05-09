// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.util.Collections;
import java.util.List;

final class zzbdq extends zzbdp<Object, Object>
{
    zzbdq(final int n) {
        super(n, null);
    }
    
    @Override
    public final void zzaaz() {
        if (!this.isImmutable()) {
            for (int i = 0; i < this.zzafs(); ++i) {
                final Entry<Object, Object> zzcy = (Entry<Object, Object>)((zzbdp<Object, List<Object>>)this).zzcy(i);
                if (zzcy.getKey().zzada()) {
                    zzcy.setValue(Collections.unmodifiableList((List<?>)zzcy.getValue()));
                }
            }
            for (final Map.Entry<zzbbi, V> entry : this.zzaft()) {
                if (entry.getKey().zzada()) {
                    entry.setValue((V)Collections.unmodifiableList((List<?>)entry.getValue()));
                }
            }
        }
        super.zzaaz();
    }
}
