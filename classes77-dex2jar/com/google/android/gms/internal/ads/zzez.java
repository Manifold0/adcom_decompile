// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.ref.WeakReference;

public final class zzez implements zzgd
{
    private final WeakReference<View> zzafo;
    private final WeakReference<zzajh> zzafp;
    
    public zzez(final View view, final zzajh zzajh) {
        this.zzafo = new WeakReference<View>(view);
        this.zzafp = new WeakReference<zzajh>(zzajh);
    }
    
    @Override
    public final View zzgh() {
        return this.zzafo.get();
    }
    
    @Override
    public final boolean zzgi() {
        return this.zzafo.get() == null || this.zzafp.get() == null;
    }
    
    @Override
    public final zzgd zzgj() {
        return new zzey(this.zzafo.get(), this.zzafp.get());
    }
}
