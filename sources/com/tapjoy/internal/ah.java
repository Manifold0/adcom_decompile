package com.tapjoy.internal;

import android.view.ViewGroup;
import java.util.Iterator;

public final class ah {

    /* renamed from: com.tapjoy.internal.ah$a */
    static class C2836a implements Iterator {
        /* renamed from: a */
        private final ViewGroup f7164a;
        /* renamed from: b */
        private int f7165b;
        /* renamed from: c */
        private int f7166c = 0;

        public C2836a(ViewGroup viewGroup) {
            this.f7164a = viewGroup;
            this.f7165b = viewGroup.getChildCount();
        }

        public final boolean hasNext() {
            return this.f7166c < this.f7165b;
        }

        public final void remove() {
            this.f7164a.removeViewAt(this.f7166c - 1);
        }

        public final /* synthetic */ Object next() {
            ViewGroup viewGroup = this.f7164a;
            int i = this.f7166c;
            this.f7166c = i + 1;
            return viewGroup.getChildAt(i);
        }
    }

    /* renamed from: a */
    public static Iterable m7159a(ViewGroup viewGroup) {
        final Iterator c2836a = new C2836a(viewGroup);
        return new Iterable() {
            public final Iterator iterator() {
                return c2836a;
            }
        };
    }
}
