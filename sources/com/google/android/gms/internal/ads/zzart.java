package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@zzadh
@TargetApi(11)
public final class zzart extends zzaru {
    public zzart(zzaqw zzaqw, boolean z) {
        super(zzaqw, z);
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, null);
    }
}
