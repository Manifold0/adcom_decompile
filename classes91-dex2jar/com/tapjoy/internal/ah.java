// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import android.view.ViewGroup;

public final class ah
{
    public static Iterable a(final ViewGroup viewGroup) {
        return new Iterable() {
            final /* synthetic */ Iterator a = new a(viewGroup);
            
            @Override
            public final Iterator iterator() {
                return this.a;
            }
        };
    }
    
    static final class a implements Iterator
    {
        private final ViewGroup a;
        private int b;
        private int c;
        
        public a(final ViewGroup a) {
            this.c = 0;
            this.a = a;
            this.b = a.getChildCount();
        }
        
        @Override
        public final boolean hasNext() {
            return this.c < this.b;
        }
        
        @Override
        public final void remove() {
            this.a.removeViewAt(this.c - 1);
        }
    }
}
