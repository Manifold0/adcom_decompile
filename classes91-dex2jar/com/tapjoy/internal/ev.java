// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Observable;

public final class ev
{
    public static final a a;
    public static final a b;
    public static final a c;
    public static final a d;
    public static final a e;
    
    static {
        a = new a();
        b = new a();
        c = new a();
        d = new a();
        e = new a();
    }
    
    public static final class a extends Observable
    {
        @Override
        public final void notifyObservers() {
            this.setChanged();
            super.notifyObservers();
        }
        
        @Override
        public final void notifyObservers(final Object o) {
            this.setChanged();
            super.notifyObservers(o);
        }
    }
}
