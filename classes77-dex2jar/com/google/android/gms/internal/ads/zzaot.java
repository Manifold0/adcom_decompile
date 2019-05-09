// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver;
import android.view.View;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;

@zzadh
final class zzaot extends zzaou implements ViewTreeObserver$OnScrollChangedListener
{
    private final WeakReference<ViewTreeObserver$OnScrollChangedListener> zzcwm;
    
    public zzaot(final View view, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        super(view);
        this.zzcwm = new WeakReference<ViewTreeObserver$OnScrollChangedListener>(viewTreeObserver$OnScrollChangedListener);
    }
    
    public final void onScrollChanged() {
        final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener = this.zzcwm.get();
        if (viewTreeObserver$OnScrollChangedListener != null) {
            viewTreeObserver$OnScrollChangedListener.onScrollChanged();
            return;
        }
        this.detach();
    }
    
    @Override
    protected final void zza(final ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
    }
    
    @Override
    protected final void zzb(final ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.removeOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
    }
}
