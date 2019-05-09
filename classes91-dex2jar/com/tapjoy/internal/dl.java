// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.IOException;
import java.io.Serializable;

public abstract class dl implements Serializable
{
    transient int a;
    protected transient int b;
    private final transient dn c;
    private final transient hx d;
    
    protected dl(final dn c, final hx d) {
        this.a = 0;
        this.b = 0;
        if (c == null) {
            throw new NullPointerException("adapter == null");
        }
        if (d == null) {
            throw new NullPointerException("unknownFields == null");
        }
        this.c = c;
        this.d = d;
    }
    
    public final hx a() {
        final hx d = this.d;
        if (d != null) {
            return d;
        }
        return hx.b;
    }
    
    @Override
    public String toString() {
        return dn.c(this);
    }
    
    public abstract static class a
    {
        hu a;
        dp b;
        
        protected a() {
        }
        
        public final a a(final int n, final dk dk, final Object o) {
            if (this.b == null) {
                this.a = new hu();
                this.b = new dp(this.a);
            }
            try {
                dk.a().a(this.b, n, o);
                return this;
            }
            catch (IOException ex) {
                throw new AssertionError();
            }
        }
        
        public final a a(final hx hx) {
            if (hx.c() <= 0) {
                return this;
            }
            if (this.b == null) {
                this.a = new hu();
                this.b = new dp(this.a);
            }
            try {
                this.b.a(hx);
                return this;
            }
            catch (IOException ex) {
                throw new AssertionError();
            }
        }
        
        public final hx a() {
            if (this.a != null) {
                return new hx(this.a.h().g());
            }
            return hx.b;
        }
    }
}
