package com.tapjoy.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class cw {
    /* renamed from: a */
    static final cz f7300a = new C28521();
    /* renamed from: b */
    private static final Iterator f7301b = new C28532();

    /* renamed from: com.tapjoy.internal.cw$1 */
    static class C28521 extends cz {
        C28521() {
        }

        public final boolean hasNext() {
            return false;
        }

        public final Object next() {
            throw new NoSuchElementException();
        }
    }

    /* renamed from: com.tapjoy.internal.cw$2 */
    static class C28532 implements Iterator {
        C28532() {
        }

        public final boolean hasNext() {
            return false;
        }

        public final Object next() {
            throw new NoSuchElementException();
        }

        public final void remove() {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public static Object m7343a(Iterator it) {
        return it.hasNext() ? it.next() : null;
    }
}
