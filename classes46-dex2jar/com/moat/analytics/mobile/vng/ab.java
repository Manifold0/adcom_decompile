// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.support.annotation.NonNull;
import java.util.Iterator;
import android.view.View;
import java.util.HashSet;
import java.util.LinkedList;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.a.b.a;
import android.view.ViewGroup;

class ab
{
    @NonNull
    static com.moat.analytics.mobile.vng.a.b.a<WebView> a(final ViewGroup viewGroup) {
        if (viewGroup instanceof WebView) {
            return com.moat.analytics.mobile.vng.a.b.a.a((WebView)viewGroup);
        }
        final LinkedList<ViewGroup> list = new LinkedList<ViewGroup>();
        list.add(viewGroup);
        final HashSet<ViewGroup> set = new HashSet<ViewGroup>();
        int n = 0;
        WebView webView = null;
        while (!list.isEmpty() && n < 100) {
            final Iterator<View> iterator = new a((ViewGroup)list.poll()).iterator();
            WebView webView2;
            while (true) {
                webView2 = webView;
                if (!iterator.hasNext()) {
                    break;
                }
                final View view = iterator.next();
                WebView webView3 = webView;
                if (view instanceof WebView) {
                    if (webView != null) {
                        p.a(3, "WebViewHound", view, "Ambiguous ad container: multiple WebViews reside within it.");
                        p.a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                        webView2 = null;
                        break;
                    }
                    webView3 = (WebView)view;
                }
                webView = webView3;
                if (!(view instanceof ViewGroup)) {
                    continue;
                }
                final ViewGroup viewGroup2 = (ViewGroup)view;
                webView = webView3;
                if (set.contains(viewGroup2)) {
                    continue;
                }
                set.add(viewGroup2);
                list.add(viewGroup2);
                webView = webView3;
            }
            ++n;
            webView = webView2;
        }
        return com.moat.analytics.mobile.vng.a.b.a.b(webView);
    }
    
    private static class a implements Iterable<View>
    {
        private final ViewGroup a;
        
        private a(final ViewGroup a) {
            this.a = a;
        }
        
        @Override
        public Iterator<View> iterator() {
            return new ab.a.a();
        }
        
        private class a implements Iterator<View>
        {
            private int b;
            
            private a() {
                this.b = -1;
            }
            
            public View a() {
                ++this.b;
                return ab.a.this.a.getChildAt(this.b);
            }
            
            @Override
            public boolean hasNext() {
                return this.b + 1 < ab.a.this.a.getChildCount();
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not implemented. Under development.");
            }
        }
    }
}
