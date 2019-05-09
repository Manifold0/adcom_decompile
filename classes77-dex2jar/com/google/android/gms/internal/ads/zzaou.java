// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver;
import android.view.View;
import java.lang.ref.WeakReference;

@zzadh
abstract class zzaou
{
    private final WeakReference<View> zzcwn;
    
    public zzaou(final View view) {
        this.zzcwn = new WeakReference<View>(view);
    }
    
    private final ViewTreeObserver getViewTreeObserver() {
        final View view = this.zzcwn.get();
        if (view != null) {
            final ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver != null) {
                final ViewTreeObserver viewTreeObserver2 = viewTreeObserver;
                if (viewTreeObserver.isAlive()) {
                    return viewTreeObserver2;
                }
            }
            return null;
        }
        return null;
    }
    
    public final void attach() {
        final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
        if (viewTreeObserver != null) {
            this.zza(viewTreeObserver);
        }
    }
    
    public final void detach() {
        final ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
        if (viewTreeObserver != null) {
            this.zzb(viewTreeObserver);
        }
    }
    
    protected abstract void zza(final ViewTreeObserver p0);
    
    protected abstract void zzb(final ViewTreeObserver p0);
}
