// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;

final class dq
{
    private int a;
    private String b;
    private String c;
    private Map<String, String> d;
    
    dq(final String b, final Map<String, String> d, final int a, final String c) {
        this.a = a;
        this.d = d;
        this.b = b;
        this.c = c;
    }
    
    public int a() {
        return this.a;
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public String b() {
        return this.b;
    }
    
    public String c() {
        return this.c;
    }
    
    public Map<String, String> d() {
        return this.d;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final dq dq = (dq)o;
            if (this.a != dq.a) {
                return false;
            }
            Label_0072: {
                if (this.b != null) {
                    if (this.b.equals(dq.b)) {
                        break Label_0072;
                    }
                }
                else if (dq.b == null) {
                    break Label_0072;
                }
                return false;
            }
            Label_0102: {
                if (this.c != null) {
                    if (this.c.equals(dq.c)) {
                        break Label_0102;
                    }
                }
                else if (dq.c == null) {
                    break Label_0102;
                }
                return false;
            }
            if (this.d != null) {
                if (this.d.equals(dq.d)) {
                    return true;
                }
            }
            else if (dq.d == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int a = this.a;
        int hashCode2;
        if (this.b != null) {
            hashCode2 = this.b.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int hashCode3;
        if (this.c != null) {
            hashCode3 = this.c.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        if (this.d != null) {
            hashCode = this.d.hashCode();
        }
        return (hashCode3 + (hashCode2 + a * 31) * 31) * 31 + hashCode;
    }
    
    @Override
    public String toString() {
        return "PostbackRequest{attemptNumber=" + this.a + ", targetUrl='" + this.b + '\'' + ", backupUrl='" + this.c + '\'' + ", requestBody=" + this.d + '}';
    }
}
