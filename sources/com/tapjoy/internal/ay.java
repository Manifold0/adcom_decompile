package com.tapjoy.internal;

import java.util.AbstractQueue;
import java.util.Iterator;

public abstract class ay extends AbstractQueue implements bc {

    /* renamed from: com.tapjoy.internal.ay$1 */
    class C28401 implements Iterator {
        /* renamed from: a */
        final /* synthetic */ ay f7214a;
        /* renamed from: b */
        private int f7215b = 0;

        C28401(ay ayVar) {
            this.f7214a = ayVar;
        }

        public final boolean hasNext() {
            return this.f7215b < this.f7214a.size();
        }

        public final Object next() {
            ay ayVar = this.f7214a;
            int i = this.f7215b;
            this.f7215b = i + 1;
            return ayVar.mo6182a(i);
        }

        public final void remove() {
            if (this.f7215b == 1) {
                this.f7214a.mo6183b(1);
                this.f7215b = 0;
                return;
            }
            throw new UnsupportedOperationException("For the first element only");
        }
    }

    public Iterator iterator() {
        return new C28401(this);
    }
}
