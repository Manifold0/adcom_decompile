package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaqw;

@zzadh
@VisibleForTesting
public final class zzi {
    public final int index;
    public final ViewGroup parent;
    public final LayoutParams zzbyi;
    public final Context zzrt;

    public zzi(zzaqw zzaqw) throws zzg {
        this.zzbyi = zzaqw.getLayoutParams();
        ViewParent parent = zzaqw.getParent();
        this.zzrt = zzaqw.zzua();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new zzg("Could not get the parent of the WebView for an overlay.");
        }
        this.parent = (ViewGroup) parent;
        this.index = this.parent.indexOfChild(zzaqw.getView());
        this.parent.removeView(zzaqw.getView());
        zzaqw.zzai(true);
    }
}
