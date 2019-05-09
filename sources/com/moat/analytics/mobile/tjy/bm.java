package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class bm implements bl {
    bm() {
    }

    /* renamed from: a */
    public C2749a mo6124a(ViewGroup viewGroup) {
        if (viewGroup instanceof WebView) {
            return C2749a.m6884a((WebView) viewGroup);
        }
        Queue linkedList = new LinkedList();
        linkedList.add(viewGroup);
        Set hashSet = new HashSet();
        int i = 0;
        Object obj = null;
        while (!linkedList.isEmpty() && i < 100) {
            int i2 = i + 1;
            Iterator it = new bo((ViewGroup) linkedList.poll()).iterator();
            while (it.hasNext()) {
                View view = (View) it.next();
                if (view instanceof WebView) {
                    if (obj != null) {
                        Log.e("MoatWebViewHound", "Ambiguous ad container: multiple WebViews reside within it.");
                        i = i2;
                        obj = null;
                        break;
                    }
                    obj = (WebView) view;
                }
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup2 = (ViewGroup) view;
                    if (!hashSet.contains(viewGroup2)) {
                        hashSet.add(viewGroup2);
                        linkedList.add(viewGroup2);
                    }
                }
            }
            i = i2;
        }
        return C2749a.m6885b(obj);
    }
}
