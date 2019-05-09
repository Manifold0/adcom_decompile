// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.KeyEvent;
import android.annotation.TargetApi;
import android.webkit.WebResourceRequest;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebResourceResponse;
import javax.annotation.ParametersAreNonnullByDefault;
import android.webkit.WebViewClient;

@zzadh
@ParametersAreNonnullByDefault
final class zzast extends WebViewClient
{
    private final zzasx zzdfc;
    private final zzatb zzdfd;
    private final zzasz zzdfe;
    private final zzata zzdff;
    private final zzatc zzdfg;
    
    zzast(final zzasx zzdfc, final zzatb zzdfd, final zzasz zzdfe, final zzata zzdff) {
        this.zzdfg = new zzatc();
        this.zzdfc = zzdfc;
        this.zzdfd = zzdfd;
        this.zzdfe = zzdfe;
        this.zzdff = zzdff;
    }
    
    private final boolean zzf(final zzasu zzasu) {
        return this.zzdfc.zza(zzasu);
    }
    
    private final WebResourceResponse zzg(final zzasu zzasu) {
        return this.zzdfd.zzd(zzasu);
    }
    
    public final void onLoadResource(final WebView webView, final String s) {
        if (s == null) {
            return;
        }
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "Loading resource: ".concat(value);
        }
        else {
            concat = new String("Loading resource: ");
        }
        zzakb.v(concat);
        this.zzdfe.zzb(new zzasu(s));
    }
    
    public final void onPageFinished(final WebView webView, final String s) {
        if (s == null) {
            return;
        }
        this.zzdff.zzc(new zzasu(s));
    }
    
    public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
        this.zzdfg.zze(n, s2);
    }
    
    public final void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
        this.zzdfg.zzb(sslError);
    }
    
    @TargetApi(24)
    public final WebResourceResponse shouldInterceptRequest(final WebView webView, final WebResourceRequest webResourceRequest) {
        if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
            return null;
        }
        return this.zzg(new zzasu(webResourceRequest));
    }
    
    public final WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
        if (s == null) {
            return null;
        }
        return this.zzg(new zzasu(s));
    }
    
    public final boolean shouldOverrideKeyEvent(final WebView webView, final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            default: {
                return false;
            }
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 222: {
                return true;
            }
        }
    }
    
    @TargetApi(24)
    public final boolean shouldOverrideUrlLoading(final WebView webView, final WebResourceRequest webResourceRequest) {
        return webResourceRequest != null && webResourceRequest.getUrl() != null && this.zzf(new zzasu(webResourceRequest));
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        return s != null && this.zzf(new zzasu(s));
    }
}
