// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.webkit.WebView;
import android.webkit.ValueCallback;

final class zzgm implements Runnable
{
    final /* synthetic */ zzgk zzaik;
    private ValueCallback<String> zzail;
    final /* synthetic */ zzge zzaim;
    final /* synthetic */ WebView zzain;
    final /* synthetic */ boolean zzaio;
    
    zzgm(final zzgk zzaik, final zzge zzaim, final WebView zzain, final boolean zzaio) {
        this.zzaik = zzaik;
        this.zzaim = zzaim;
        this.zzain = zzain;
        this.zzaio = zzaio;
        this.zzail = (ValueCallback<String>)new zzgn(this);
    }
    
    @Override
    public final void run() {
        if (!this.zzain.getSettings().getJavaScriptEnabled()) {
            return;
        }
        try {
            this.zzain.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", (ValueCallback)this.zzail);
        }
        catch (Throwable t) {
            this.zzail.onReceiveValue((Object)"");
        }
    }
}
