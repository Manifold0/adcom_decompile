package com.moat.analytics.mobile.iro;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

/* renamed from: com.moat.analytics.mobile.iro.v */
final class C0804v {
    /* renamed from: ˎ */
    private static LinkedHashSet<String> f1307 = new LinkedHashSet();

    C0804v() {
    }

    @NonNull
    /* renamed from: ˋ */
    static Optional<WebView> m1404(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            try {
                return Optional.empty();
            } catch (Exception e) {
                return Optional.empty();
            }
        } else if (viewGroup instanceof WebView) {
            return Optional.of((WebView) viewGroup);
        } else {
            Queue linkedList = new LinkedList();
            linkedList.add(viewGroup);
            int i = 0;
            Object obj = null;
            while (!linkedList.isEmpty() && i < 100) {
                int i2 = i + 1;
                ViewGroup viewGroup2 = (ViewGroup) linkedList.poll();
                int childCount = viewGroup2.getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = viewGroup2.getChildAt(i3);
                    if (childAt instanceof WebView) {
                        C0756b.m1234(3, "WebViewHound", childAt, "Found WebView");
                        if (z || C0804v.m1405(String.valueOf(childAt.hashCode()))) {
                            if (obj != null) {
                                C0756b.m1234(3, "WebViewHound", childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                C0756b.m1232("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                                i = i2;
                                obj = null;
                                break;
                            }
                            obj = (WebView) childAt;
                        }
                    }
                    if (childAt instanceof ViewGroup) {
                        linkedList.add((ViewGroup) childAt);
                    }
                }
                i = i2;
            }
            return Optional.ofNullable(obj);
        }
    }

    /* renamed from: ॱ */
    private static boolean m1405(String str) {
        try {
            boolean add = f1307.add(str);
            if (f1307.size() > 50) {
                Iterator it = f1307.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            C0756b.m1234(3, "WebViewHound", null, add ? "Newly Found WebView" : "Already Found WebView");
            return add;
        } catch (Exception e) {
            C0785o.m1351(e);
            return false;
        }
    }
}
