// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.annotation.TargetApi;

@zzadh
@TargetApi(11)
public final class zzart extends zzaru
{
    public zzart(final zzaqw zzaqw, final boolean b) {
        super(zzaqw, b);
    }
    
    @Override
    public final WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        return this.zza(webView, s, null);
    }
}
