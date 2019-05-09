// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import javax.annotation.Nullable;
import android.annotation.TargetApi;
import android.webkit.WebResourceRequest;
import java.util.Map;
import android.net.Uri;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzasu
{
    private final String method;
    public final Uri uri;
    public final String url;
    public final Map<String, String> zzab;
    
    @TargetApi(21)
    public zzasu(final WebResourceRequest webResourceRequest) {
        this(webResourceRequest.getUrl().toString(), webResourceRequest.getUrl(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders());
    }
    
    public zzasu(final String s) {
        this(s, Uri.parse(s), null, null);
    }
    
    private zzasu(String s, final Uri uri, @Nullable final String s2, @Nullable final Map<String, String> map) {
        this.url = s;
        this.uri = uri;
        s = s2;
        if (s2 == null) {
            s = "GET";
        }
        this.method = s;
        Map<String, String> emptyMap;
        if ((emptyMap = map) == null) {
            emptyMap = Collections.emptyMap();
        }
        this.zzab = emptyMap;
    }
}
