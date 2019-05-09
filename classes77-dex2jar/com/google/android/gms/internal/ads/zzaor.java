// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View;

@zzadh
public final class zzaor
{
    public static void zza(final View view, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        new zzaos(view, viewTreeObserver$OnGlobalLayoutListener).attach();
    }
    
    public static void zza(final View view, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        new zzaot(view, viewTreeObserver$OnScrollChangedListener).attach();
    }
}
