// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class ae extends WebViewClient
{
    final /* synthetic */ ad a;
    
    ae(final ad a) {
        this.a = a;
    }
    
    public void onPageFinished(final WebView webView, final String s) {
        if (this.a.i) {
            return;
        }
        try {
            this.a.i = true;
            (this.a.c = new bi((View)this.a.g.get(), this.a.h, true, this.a.a, this.a.b)).c();
            this.a.a();
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
}
