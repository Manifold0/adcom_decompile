// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import android.view.ViewGroup;
import android.content.Context;

@zzadh
public final class zzapn
{
    private final Context zzcyf;
    private final zzapw zzcyg;
    private final ViewGroup zzcyh;
    private zzapi zzcyi;
    
    @VisibleForTesting
    private zzapn(final Context context, final ViewGroup zzcyh, final zzapw zzcyg, final zzapi zzapi) {
        Context applicationContext = context;
        if (context.getApplicationContext() != null) {
            applicationContext = context.getApplicationContext();
        }
        this.zzcyf = applicationContext;
        this.zzcyh = zzcyh;
        this.zzcyg = zzcyg;
        this.zzcyi = null;
    }
    
    public zzapn(final Context context, final ViewGroup viewGroup, final zzaqw zzaqw) {
        this(context, viewGroup, zzaqw, null);
    }
    
    public final void onDestroy() {
        Preconditions.checkMainThread("onDestroy must be called from the UI thread.");
        if (this.zzcyi != null) {
            this.zzcyi.destroy();
            this.zzcyh.removeView((View)this.zzcyi);
            this.zzcyi = null;
        }
    }
    
    public final void onPause() {
        Preconditions.checkMainThread("onPause must be called from the UI thread.");
        if (this.zzcyi != null) {
            this.zzcyi.pause();
        }
    }
    
    public final void zza(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final zzapv zzapv) {
        if (this.zzcyi != null) {
            return;
        }
        zznq.zza(this.zzcyg.zztp().zzji(), this.zzcyg.zztn(), "vpr2");
        this.zzcyi = new zzapi(this.zzcyf, this.zzcyg, n5, b, this.zzcyg.zztp().zzji(), zzapv);
        this.zzcyh.addView((View)this.zzcyi, 0, new ViewGroup$LayoutParams(-1, -1));
        this.zzcyi.zzd(n, n2, n3, n4);
        this.zzcyg.zzah(false);
    }
    
    public final void zze(final int n, final int n2, final int n3, final int n4) {
        Preconditions.checkMainThread("The underlay may only be modified from the UI thread.");
        if (this.zzcyi != null) {
            this.zzcyi.zzd(n, n2, n3, n4);
        }
    }
    
    public final zzapi zzth() {
        Preconditions.checkMainThread("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzcyi;
    }
}
