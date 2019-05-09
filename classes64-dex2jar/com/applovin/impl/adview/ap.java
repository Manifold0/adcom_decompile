// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class ap
{
    private final String a;
    private final ao b;
    private final long c;
    
    private ap(final String a, final long c, final ao b) {
        this.a = a;
        this.c = c;
        this.b = b;
    }
    
    private String a() {
        return this.a;
    }
    
    private long b() {
        return this.c;
    }
    
    private ao c() {
        return this.b;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ap)) {
                return false;
            }
            final ap ap = (ap)o;
            if (this.a != null) {
                return this.a.equalsIgnoreCase(ap.a);
            }
            if (ap.a != null) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        if (this.a != null) {
            return this.a.hashCode();
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "CountdownProxy{identifier='" + this.a + '\'' + ", countdownStepMillis=" + this.c + '}';
    }
}
