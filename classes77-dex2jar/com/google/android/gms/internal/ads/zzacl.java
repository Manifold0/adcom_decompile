// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import android.view.ViewTreeObserver$OnScrollChangedListener;

final class zzacl implements ViewTreeObserver$OnScrollChangedListener
{
    private final /* synthetic */ zzace zzcbi;
    private final /* synthetic */ WeakReference zzcbj;
    
    zzacl(final zzace zzcbi, final WeakReference zzcbj) {
        this.zzcbi = zzcbi;
        this.zzcbj = zzcbj;
    }
    
    public final void onScrollChanged() {
        this.zzcbi.zza(this.zzcbj, true);
    }
}
