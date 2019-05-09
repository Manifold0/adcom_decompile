package com.moat.analytics.mobile.vng;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.p013a.p015b.C0820a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class ab {

    /* renamed from: com.moat.analytics.mobile.vng.ab$a */
    private static class C0825a implements Iterable<View> {
        /* renamed from: a */
        private final ViewGroup f1355a;

        /* renamed from: com.moat.analytics.mobile.vng.ab$a$a */
        private class C0824a implements Iterator<View> {
            /* renamed from: a */
            final /* synthetic */ C0825a f1353a;
            /* renamed from: b */
            private int f1354b;

            private C0824a(C0825a c0825a) {
                this.f1353a = c0825a;
                this.f1354b = -1;
            }

            /* renamed from: a */
            public View m1456a() {
                this.f1354b++;
                return this.f1353a.f1355a.getChildAt(this.f1354b);
            }

            public boolean hasNext() {
                return this.f1354b + 1 < this.f1353a.f1355a.getChildCount();
            }

            public /* synthetic */ Object next() {
                return m1456a();
            }

            public void remove() {
                throw new UnsupportedOperationException("Not implemented. Under development.");
            }
        }

        private C0825a(ViewGroup viewGroup) {
            this.f1355a = viewGroup;
        }

        public Iterator<View> iterator() {
            return new C0824a();
        }
    }

    ab() {
    }

    @NonNull
    /* renamed from: a */
    static C0820a<WebView> m1458a(ViewGroup viewGroup) {
        if (viewGroup instanceof WebView) {
            return C0820a.m1433a((WebView) viewGroup);
        }
        Queue linkedList = new LinkedList();
        linkedList.add(viewGroup);
        Set hashSet = new HashSet();
        int i = 0;
        Object obj = null;
        while (!linkedList.isEmpty() && i < 100) {
            int i2 = i + 1;
            Iterator it = new C0825a((ViewGroup) linkedList.poll()).iterator();
            while (it.hasNext()) {
                Object obj2 = (View) it.next();
                if (obj2 instanceof WebView) {
                    if (obj != null) {
                        C0858p.m1577a(3, "WebViewHound", obj2, "Ambiguous ad container: multiple WebViews reside within it.");
                        C0858p.m1579a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                        obj = null;
                        break;
                    }
                    obj = (WebView) obj2;
                }
                if (obj2 instanceof ViewGroup) {
                    ViewGroup viewGroup2 = (ViewGroup) obj2;
                    if (!hashSet.contains(viewGroup2)) {
                        hashSet.add(viewGroup2);
                        linkedList.add(viewGroup2);
                    }
                }
            }
            i = i2;
        }
        return C0820a.m1434b(obj);
    }
}
