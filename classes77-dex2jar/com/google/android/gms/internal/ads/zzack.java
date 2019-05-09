// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

final class zzack implements ViewTreeObserver$OnGlobalLayoutListener
{
    private final /* synthetic */ zzace zzcbi;
    private final /* synthetic */ WeakReference zzcbj;
    
    zzack(final zzace zzcbi, final WeakReference zzcbj) {
        this.zzcbi = zzcbi;
        this.zzcbj = zzcbj;
    }
    
    public final void onGlobalLayout() {
        this.zzcbi.zza(this.zzcbj, false);
    }
}
