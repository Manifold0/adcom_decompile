package com.moat.analytics.mobile.cha;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

/* renamed from: com.moat.analytics.mobile.cha.x */
final class C1544x {
    /* renamed from: ˋ */
    private static final LinkedHashSet<String> f3637 = new LinkedHashSet();

    C1544x() {
    }

    @NonNull
    /* renamed from: ˊ */
    static Optional<WebView> m3910(ViewGroup viewGroup, boolean z) {
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
                        C1487a.m3715(3, "WebViewHound", childAt, "Found WebView");
                        if (z || C1544x.m3911(String.valueOf(childAt.hashCode()))) {
                            if (obj != null) {
                                C1487a.m3715(3, "WebViewHound", childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                C1487a.m3712("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
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

    /* renamed from: ˊ */
    private static boolean m3911(String str) {
        try {
            boolean add = f3637.add(str);
            if (f3637.size() > 50) {
                Iterator it = f3637.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            C1487a.m3715(3, "WebViewHound", null, add ? "Newly Found WebView" : "Already Found WebView");
            return add;
        } catch (Exception e) {
            C1518o.m3840(e);
            return false;
        }
    }
}
