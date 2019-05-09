// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@zzadh
@TargetApi(17)
public final class zzaro<WebViewT extends zzarr>
{
    private final zzarq zzdem;
    private final WebViewT zzden;
    
    private zzaro(final WebViewT zzden, final zzarq zzdem) {
        this.zzdem = zzdem;
        this.zzden = zzden;
    }
    
    public static zzaro<zzaqw> zzk(final zzaqw zzaqw) {
        return new zzaro<zzaqw>(zzaqw, (zzarq)new zzarp(zzaqw));
    }
}
