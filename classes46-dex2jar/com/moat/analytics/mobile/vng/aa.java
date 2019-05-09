// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.view.View;
import android.support.annotation.Nullable;
import android.webkit.WebView;

class aa extends b implements WebAdTracker
{
    aa(@Nullable final WebView webView) {
        super((View)webView, false, false);
        p.a(3, "WebAdTracker", this, "In initialization method.");
        super.a(webView);
        p.a("[SUCCESS] ", this.a() + " created for " + this.e());
    }
    
    @Override
    String a() {
        return "WebAdTracker";
    }
}
