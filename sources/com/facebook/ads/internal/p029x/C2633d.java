package com.facebook.ads.internal.p029x;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/* renamed from: com.facebook.ads.internal.x.d */
class C2633d {
    @Nullable
    @VisibleForTesting
    /* renamed from: a */
    static Float m6781a(View view, View view2) {
        int i = 0;
        if (view == null || view2 == null) {
            return null;
        }
        if (view2.getVisibility() != 0) {
            return Float.valueOf(0.0f);
        }
        List<View> linkedList = new LinkedList();
        Stack stack = new Stack();
        stack.push(view);
        int i2 = 0;
        while (!stack.empty()) {
            View view3 = (View) stack.pop();
            if (view3.getVisibility() == 0) {
                if (view3 == view2) {
                    i2 = 1;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                        stack.push(viewGroup.getChildAt(childCount));
                    }
                } else if (i2 != 0 || (VERSION.SDK_INT >= 21 && view3.getZ() > view2.getZ())) {
                    linkedList.add(view3);
                }
            }
        }
        if (linkedList.isEmpty()) {
            return Float.valueOf(1.0f);
        }
        Rect rect = new Rect();
        if (!view2.getGlobalVisibleRect(rect)) {
            return Float.valueOf(0.0f);
        }
        int width = rect.width() * rect.height();
        Set hashSet = new HashSet();
        hashSet.add(rect);
        Set set = hashSet;
        for (View view32 : linkedList) {
            Rect rect2 = new Rect();
            if (view32.getGlobalVisibleRect(rect2)) {
                Set hashSet2 = new HashSet();
                for (Rect a : r1) {
                    hashSet2.addAll(C2633d.m6782a(a, rect2));
                }
                set = hashSet2;
            }
        }
        for (Rect a2 : r1) {
            i += a2.height() * a2.width();
        }
        return Float.valueOf(((float) i) / ((float) width));
    }

    @VisibleForTesting
    /* renamed from: a */
    static Set<Rect> m6782a(Rect rect, Rect rect2) {
        Set<Rect> hashSet = new HashSet();
        if (rect2.intersect(rect)) {
            hashSet.add(new Rect(rect.left, rect.top, rect2.left, rect.bottom));
            hashSet.add(new Rect(rect2.left, rect.top, rect2.right, rect2.top));
            hashSet.add(new Rect(rect2.right, rect.top, rect.right, rect.bottom));
            hashSet.add(new Rect(rect2.left, rect2.bottom, rect2.right, rect.bottom));
            Set<Rect> hashSet2 = new HashSet();
            for (Rect rect3 : hashSet) {
                if (rect3.width() > 0 && rect3.height() > 0) {
                    hashSet2.add(rect3);
                }
            }
            return hashSet2;
        }
        hashSet.add(rect);
        return hashSet;
    }
}
