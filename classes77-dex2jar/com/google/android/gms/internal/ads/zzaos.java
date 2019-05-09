// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.view.ViewTreeObserver;
import android.view.View;
import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zzadh
final class zzaos extends zzaou implements ViewTreeObserver$OnGlobalLayoutListener
{
    private final WeakReference<ViewTreeObserver$OnGlobalLayoutListener> zzcwm;
    
    public zzaos(final View view, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        super(view);
        this.zzcwm = new WeakReference<ViewTreeObserver$OnGlobalLayoutListener>(viewTreeObserver$OnGlobalLayoutListener);
    }
    
    public final void onGlobalLayout() {
        final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener = this.zzcwm.get();
        if (viewTreeObserver$OnGlobalLayoutListener != null) {
            viewTreeObserver$OnGlobalLayoutListener.onGlobalLayout();
            return;
        }
        this.detach();
    }
    
    @Override
    protected final void zza(final ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
    }
    
    @Override
    protected final void zzb(final ViewTreeObserver viewTreeObserver) {
        zzbv.zzem().zza(viewTreeObserver, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
