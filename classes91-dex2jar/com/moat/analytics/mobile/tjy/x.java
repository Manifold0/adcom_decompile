// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.webkit.WebView;
import android.util.Log;
import android.view.ViewGroup;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.ref.WeakReference;

class x implements ba
{
    final /* synthetic */ WeakReference a;
    final /* synthetic */ ap b;
    final /* synthetic */ v c;
    
    x(final v c, final WeakReference a, final ap b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public a a() {
        final ViewGroup viewGroup = (ViewGroup)this.a.get();
        final boolean b = this.b.b();
        if (viewGroup == null) {
            if (b) {
                Log.e("MoatFactory", "Target ViewGroup is null. Not creating WebAdTracker.");
            }
            return com.moat.analytics.mobile.tjy.base.functional.a.a();
        }
        if (b) {
            Log.d("MoatFactory", "Creating WebAdTracker for " + viewGroup.getClass().getSimpleName() + "@" + viewGroup.hashCode());
        }
        final a a = this.c.a.a(viewGroup);
        final boolean c = a.c();
        if (b) {
            final StringBuilder sb = new StringBuilder("WebView ");
            String s;
            if (c) {
                s = "";
            }
            else {
                s = "not ";
            }
            Log.e("MoatFactory", sb.append(s).append("found inside of ad container.").toString());
        }
        return com.moat.analytics.mobile.tjy.base.functional.a.a(new bj((WebView)a.c(null), this.c.b, this.b));
    }
}
