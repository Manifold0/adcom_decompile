package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzbv;
import java.io.File;
import java.util.Collections;
import java.util.Map;

@zzadh
@TargetApi(11)
public class zzaru extends zzaqx {
    public zzaru(zzaqw zzaqw, boolean z) {
        super(zzaqw, z);
    }

    @Nullable
    protected final WebResourceResponse zza(WebView webView, String str, @Nullable Map<String, String> map) {
        if (webView instanceof zzaqw) {
            zzaqw zzaqw = (zzaqw) webView;
            if (this.zzxd != null) {
                this.zzxd.zza(str, map, 1);
            }
            if ("mraid.js".equalsIgnoreCase(new File(str).getName())) {
                String str2;
                if (zzaqw.zzuf() != null) {
                    zzaqw.zzuf().zznk();
                }
                if (zzaqw.zzud().zzvs()) {
                    str2 = (String) zzkb.zzik().zzd(zznk.zzawe);
                } else if (zzaqw.zzuj()) {
                    str2 = (String) zzkb.zzik().zzd(zznk.zzawd);
                } else {
                    str2 = (String) zzkb.zzik().zzd(zznk.zzawc);
                }
                zzbv.zzek();
                return zzakk.zzf(zzaqw.getContext(), zzaqw.zztq().zzcw, str2);
            }
            Map emptyMap;
            if (map == null) {
                emptyMap = Collections.emptyMap();
            }
            return super.zzd(str, emptyMap);
        }
        zzakb.zzdk("Tried to intercept request from a WebView that wasn't an AdWebView.");
        return null;
    }
}
