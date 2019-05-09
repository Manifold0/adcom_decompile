// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Collections;
import java.io.File;
import android.webkit.WebResourceResponse;
import android.support.annotation.Nullable;
import java.util.Map;
import android.webkit.WebView;
import android.annotation.TargetApi;

@zzadh
@TargetApi(11)
public class zzaru extends zzaqx
{
    public zzaru(final zzaqw zzaqw, final boolean b) {
        super(zzaqw, b);
    }
    
    @Nullable
    protected final WebResourceResponse zza(final WebView webView, final String s, @Nullable final Map<String, String> map) {
        if (!(webView instanceof zzaqw)) {
            zzakb.zzdk("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        final zzaqw zzaqw = (zzaqw)webView;
        if (this.zzxd != null) {
            this.zzxd.zza(s, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(s).getName())) {
            Map<String, String> emptyMap;
            if ((emptyMap = map) == null) {
                emptyMap = Collections.emptyMap();
            }
            return super.zzd(s, emptyMap);
        }
        if (zzaqw.zzuf() != null) {
            zzaqw.zzuf().zznk();
        }
        String s2;
        if (zzaqw.zzud().zzvs()) {
            s2 = (String)zzkb.zzik().zzd(zznk.zzawe);
        }
        else if (zzaqw.zzuj()) {
            s2 = (String)zzkb.zzik().zzd(zznk.zzawd);
        }
        else {
            s2 = (String)zzkb.zzik().zzd(zznk.zzawc);
        }
        zzbv.zzek();
        return zzakk.zzf(zzaqw.getContext(), zzaqw.zztq().zzcw, s2);
    }
}
