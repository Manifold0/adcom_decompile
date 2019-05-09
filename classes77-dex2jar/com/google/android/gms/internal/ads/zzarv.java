// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.Map;
import android.webkit.WebResourceResponse;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.annotation.TargetApi;

@zzadh
@TargetApi(21)
public final class zzarv extends zzaru
{
    public zzarv(final zzaqw zzaqw, final boolean b) {
        super(zzaqw, b);
    }
    
    @Nullable
    public final WebResourceResponse shouldInterceptRequest(final WebView webView, final WebResourceRequest webResourceRequest) {
        if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
            return null;
        }
        return this.zza(webView, webResourceRequest.getUrl().toString(), webResourceRequest.getRequestHeaders());
    }
}
