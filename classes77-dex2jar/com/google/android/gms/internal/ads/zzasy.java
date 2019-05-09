// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.ValueCallback;
import com.google.android.gms.common.util.PlatformVersion;
import android.webkit.WebView;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
final class zzasy
{
    @VisibleForTesting
    @GuardedBy("InvokeJavascriptWorkaround.class")
    private static Boolean zzdfk;
    
    private zzasy() {
    }
    
    @TargetApi(19)
    static void zza(final WebView webView, String s) {
        if (PlatformVersion.isAtLeastKitKat() && zzb(webView)) {
            webView.evaluateJavascript(s, (ValueCallback)null);
            return;
        }
        s = String.valueOf(s);
        if (s.length() != 0) {
            s = "javascript:".concat(s);
        }
        else {
            s = new String("javascript:");
        }
        webView.loadUrl(s);
    }
    
    @TargetApi(19)
    private static boolean zzb(final WebView webView) {
        synchronized (zzasy.class) {
            Label_0025: {
                if (zzasy.zzdfk != null) {
                    break Label_0025;
                }
                try {
                    webView.evaluateJavascript("(function(){})()", (ValueCallback)null);
                    zzasy.zzdfk = true;
                    return zzasy.zzdfk;
                }
                catch (IllegalStateException ex) {
                    zzasy.zzdfk = false;
                }
            }
        }
    }
}
