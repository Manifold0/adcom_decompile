package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@zzadh
@TargetApi(21)
public final class zzarv extends zzaru {
    public zzarv(zzaqw zzaqw, boolean z) {
        super(zzaqw, z);
    }

    @Nullable
    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return (webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : zza(webView, webResourceRequest.getUrl().toString(), webResourceRequest.getRequestHeaders());
    }
}
