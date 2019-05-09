// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.Iterator;
import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.LinkedList;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.a;
import android.view.ViewGroup;

class bm implements bl
{
    @Override
    public a a(final ViewGroup viewGroup) {
        if (viewGroup instanceof WebView) {
            return a.a(viewGroup);
        }
        final LinkedList<ViewGroup> list = new LinkedList<ViewGroup>();
        list.add(viewGroup);
        final HashSet<ViewGroup> set = new HashSet<ViewGroup>();
        int n = 0;
        WebView webView = null;
        while (!list.isEmpty() && n < 100) {
            ++n;
            for (final View view : new bo(list.poll(), null)) {
                WebView webView2 = webView;
                if (view instanceof WebView) {
                    if (webView != null) {
                        Log.e("MoatWebViewHound", "Ambiguous ad container: multiple WebViews reside within it.");
                        webView = null;
                        break;
                    }
                    webView2 = (WebView)view;
                }
                webView = webView2;
                if (view instanceof ViewGroup) {
                    final ViewGroup viewGroup2 = (ViewGroup)view;
                    webView = webView2;
                    if (set.contains(viewGroup2)) {
                        continue;
                    }
                    set.add(viewGroup2);
                    list.add(viewGroup2);
                    webView = webView2;
                }
            }
        }
        return a.b(webView);
    }
}
