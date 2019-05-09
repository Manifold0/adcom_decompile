// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.view.View;
import java.lang.ref.WeakReference;

public final class zzev implements zzgd
{
    private WeakReference<zzoz> zzafl;
    
    public zzev(final zzoz zzoz) {
        this.zzafl = new WeakReference<zzoz>(zzoz);
    }
    
    @Nullable
    @Override
    public final View zzgh() {
        final zzoz zzoz = this.zzafl.get();
        if (zzoz != null) {
            return zzoz.zzkr();
        }
        return null;
    }
    
    @Override
    public final boolean zzgi() {
        return this.zzafl.get() == null;
    }
    
    @Override
    public final zzgd zzgj() {
        return new zzex(this.zzafl.get());
    }
}
