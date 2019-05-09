package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@zzadh
@TargetApi(17)
public final class zzaro<WebViewT extends zzarr & zzarz & zzasb> {
    private final zzarq zzdem;
    private final WebViewT zzden;

    private zzaro(WebViewT webViewT, zzarq zzarq) {
        this.zzdem = zzarq;
        this.zzden = webViewT;
    }

    public static zzaro<zzaqw> zzk(zzaqw zzaqw) {
        return new zzaro(zzaqw, new zzarp(zzaqw));
    }
}
