// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.ref.WeakReference;

class w implements ba
{
    final /* synthetic */ WeakReference a;
    final /* synthetic */ ap b;
    final /* synthetic */ v c;
    
    w(final v c, final WeakReference a, final ap b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public a a() {
        final WebView webView = (WebView)this.a.get();
        final boolean b = this.b.b();
        if (webView == null) {
            if (b) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return com.moat.analytics.mobile.tjy.base.functional.a.a();
        }
        if (b) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + webView.getClass().getSimpleName() + "@" + webView.hashCode());
        }
        return com.moat.analytics.mobile.tjy.base.functional.a.a(new bj(webView, this.c.b, this.b));
    }
}
