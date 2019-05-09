// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.b;
import android.view.View;
import android.util.Log;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;

class bj implements WebAdTracker
{
    private final a a;
    private final ap b;
    
    bj(final WebView webView, final com.moat.analytics.mobile.tjy.a a, final ap b) {
        this.b = b;
        if (b.b()) {
            Log.d("MoatWebAdTracker", "In initialization method.");
        }
        if (webView == null) {
            if (b.b()) {
                Log.e("MoatWebAdTracker", "WebView is null. Will not track.");
            }
            this.a = com.moat.analytics.mobile.tjy.base.functional.a.a();
            return;
        }
        this.a = com.moat.analytics.mobile.tjy.base.functional.a.a(new bi((View)webView, webView, false, a, b));
    }
    
    @Override
    public boolean track() {
        final boolean b = this.b.b();
        boolean c = false;
        Label_0024: {
            if (!b) {
                break Label_0024;
            }
            while (true) {
            Label_0069_Outer:
                while (true) {
                    Label_0130: {
                        while (true) {
                        Label_0123:
                            while (true) {
                                try {
                                    Log.d("MoatWebAdTracker", "In track method.");
                                    if (!this.a.c()) {
                                        if (!b) {
                                            break Label_0130;
                                        }
                                        Log.e("MoatWebAdTracker", "Internal tracker not available. Not tracking.");
                                    }
                                    else {
                                        c = ((bh)this.a.b()).c();
                                    }
                                    if (b) {
                                        final StringBuilder sb = new StringBuilder("Attempt to start tracking ad was ");
                                        if (!c) {
                                            break Label_0123;
                                        }
                                        final String s = "";
                                        Log.d("MoatWebAdTracker", sb.append(s).append("successful.").toString());
                                    }
                                    return c;
                                }
                                catch (b b2) {
                                    com.moat.analytics.mobile.tjy.base.exception.a.a(b2);
                                    continue Label_0069_Outer;
                                }
                                continue Label_0069_Outer;
                            }
                            final String s = "un";
                            continue;
                        }
                    }
                    c = false;
                    continue;
                }
            }
        }
    }
}
