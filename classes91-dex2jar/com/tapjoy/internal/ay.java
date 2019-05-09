// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import java.util.AbstractQueue;

public abstract class ay extends AbstractQueue implements bc
{
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int b = 0;
            
            @Override
            public final boolean hasNext() {
                return this.b < ay.this.size();
            }
            
            @Override
            public final Object next() {
                return ay.this.a(this.b++);
            }
            
            @Override
            public final void remove() {
                if (this.b == 1) {
                    ay.this.b(1);
                    this.b = 0;
                    return;
                }
                throw new UnsupportedOperationException("For the first element only");
            }
        };
    }
}
