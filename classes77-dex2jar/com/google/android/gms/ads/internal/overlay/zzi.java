// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewParent;
import com.google.android.gms.internal.ads.zzaqw;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@VisibleForTesting
public final class zzi
{
    public final int index;
    public final ViewGroup parent;
    public final ViewGroup$LayoutParams zzbyi;
    public final Context zzrt;
    
    public zzi(final zzaqw zzaqw) throws zzg {
        this.zzbyi = zzaqw.getLayoutParams();
        final ViewParent parent = zzaqw.getParent();
        this.zzrt = zzaqw.zzua();
        if (parent != null && parent instanceof ViewGroup) {
            this.parent = (ViewGroup)parent;
            this.index = this.parent.indexOfChild(zzaqw.getView());
            this.parent.removeView(zzaqw.getView());
            zzaqw.zzai(true);
            return;
        }
        throw new zzg("Could not get the parent of the WebView for an overlay.");
    }
}
