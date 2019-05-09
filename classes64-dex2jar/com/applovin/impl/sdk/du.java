// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;

public class du
{
    private final String a;
    private final Map<String, String> b;
    private final long c;
    private final String d;
    
    public du(final String a, final Map<String, String> b, final long c, final String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public String a() {
        return this.a;
    }
    
    public Map<String, String> b() {
        return this.b;
    }
    
    public long c() {
        return this.c;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (this.getClass() == o.getClass()) {
                    final du du = (du)o;
                    b3 = b2;
                    if (this.c == du.c) {
                        if (this.a != null) {
                            b3 = b2;
                            if (!this.a.equals(du.a)) {
                                return b3;
                            }
                        }
                        else if (du.a != null) {
                            return false;
                        }
                        if (this.b != null) {
                            b3 = b2;
                            if (!this.b.equals(du.b)) {
                                return b3;
                            }
                        }
                        else if (du.b != null) {
                            return false;
                        }
                        if (this.d != null) {
                            final boolean b4 = b;
                            if (this.d.equals(du.d)) {
                                return b4;
                            }
                        }
                        else if (du.d == null) {
                            return b;
                        }
                        return false;
                    }
                }
            }
        }
        return b3;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.a != null) {
            hashCode2 = this.a.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int hashCode3;
        if (this.b != null) {
            hashCode3 = this.b.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final int n = (int)(this.c ^ this.c >>> 32);
        if (this.d != null) {
            hashCode = this.d.hashCode();
        }
        return ((hashCode3 + hashCode2 * 31) * 31 + n) * 31 + hashCode;
    }
    
    @Override
    public String toString() {
        return "SdkEvent{eventType='" + this.a + '\'' + ", parameters=" + this.b + ", creationTsMillis=" + this.c + ", uniqueIdentifier='" + this.d + '\'' + '}';
    }
}
