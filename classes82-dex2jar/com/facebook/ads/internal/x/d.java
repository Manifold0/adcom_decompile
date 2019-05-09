// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.x;

import java.util.Set;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.view.ViewGroup;
import java.util.Stack;
import java.util.LinkedList;
import android.view.View;

class d
{
    @Nullable
    @VisibleForTesting
    static Float a(View view, View view2) {
        final int n = 0;
        if (view == null || view2 == null) {
            return null;
        }
        if (view2.getVisibility() != 0) {
            return 0.0f;
        }
        final LinkedList<View> list = new LinkedList<View>();
        final Stack<View> stack = new Stack<View>();
        stack.push(view);
        boolean b = false;
        while (!stack.empty()) {
            view = stack.pop();
            if (view.getVisibility() == 0) {
                if (view == view2) {
                    b = true;
                }
                else if (!(view instanceof ViewGroup)) {
                    if (!b && (Build$VERSION.SDK_INT < 21 || view.getZ() <= view2.getZ())) {
                        continue;
                    }
                    list.add(view);
                }
                else {
                    final ViewGroup viewGroup = (ViewGroup)view;
                    for (int i = viewGroup.getChildCount() - 1; i >= 0; --i) {
                        stack.push(viewGroup.getChildAt(i));
                    }
                }
            }
        }
        if (list.isEmpty()) {
            return 1.0f;
        }
        final Rect rect = new Rect();
        if (!view2.getGlobalVisibleRect(rect)) {
            return 0.0f;
        }
        final int width = rect.width();
        final int height = rect.height();
        HashSet set = new HashSet();
        set.add(rect);
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            view2 = iterator.next();
            final Rect rect2 = new Rect();
            if (view2.getGlobalVisibleRect(rect2)) {
                final HashSet set2 = new HashSet();
                final Iterator iterator2 = set.iterator();
                while (iterator2.hasNext()) {
                    set2.addAll(a((Rect)iterator2.next(), rect2));
                }
                set = set2;
            }
        }
        final Iterator iterator3 = set.iterator();
        int n2 = n;
        while (iterator3.hasNext()) {
            final Rect rect3 = (Rect)iterator3.next();
            n2 += rect3.height() * rect3.width();
        }
        return n2 / (float)(width * height);
    }
    
    @VisibleForTesting
    static Set<Rect> a(final Rect rect, final Rect rect2) {
        final HashSet<Rect> set = new HashSet<Rect>();
        if (!rect2.intersect(rect)) {
            set.add(rect);
            return set;
        }
        set.add(new Rect(rect.left, rect.top, rect2.left, rect.bottom));
        set.add(new Rect(rect2.left, rect.top, rect2.right, rect2.top));
        set.add(new Rect(rect2.right, rect.top, rect.right, rect.bottom));
        set.add(new Rect(rect2.left, rect2.bottom, rect2.right, rect.bottom));
        final HashSet<Rect> set2 = new HashSet<Rect>();
        for (final Rect rect3 : set) {
            if (rect3.width() > 0 && rect3.height() > 0) {
                set2.add(rect3);
            }
        }
        return set2;
    }
}
