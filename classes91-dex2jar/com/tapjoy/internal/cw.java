// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.NoSuchElementException;
import java.util.Iterator;

public final class cw
{
    static final cz a;
    private static final Iterator b;
    
    static {
        a = new cz() {
            @Override
            public final boolean hasNext() {
                return false;
            }
            
            @Override
            public final Object next() {
                throw new NoSuchElementException();
            }
        };
        b = new Iterator() {
            @Override
            public final boolean hasNext() {
                return false;
            }
            
            @Override
            public final Object next() {
                throw new NoSuchElementException();
            }
            
            @Override
            public final void remove() {
                throw new IllegalStateException();
            }
        };
    }
    
    public static Object a(final Iterator iterator) {
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
}
