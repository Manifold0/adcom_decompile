package com.applovin.impl.sdk;

import java.util.Map;

final class dq {
    /* renamed from: a */
    private int f2345a;
    /* renamed from: b */
    private String f2346b;
    /* renamed from: c */
    private String f2347c;
    /* renamed from: d */
    private Map<String, String> f2348d;

    dq(String str, Map<String, String> map, int i, String str2) {
        this.f2345a = i;
        this.f2348d = map;
        this.f2346b = str;
        this.f2347c = str2;
    }

    /* renamed from: a */
    public int m2614a() {
        return this.f2345a;
    }

    /* renamed from: a */
    public void m2615a(int i) {
        this.f2345a = i;
    }

    /* renamed from: b */
    public String m2616b() {
        return this.f2346b;
    }

    /* renamed from: c */
    public String m2617c() {
        return this.f2347c;
    }

    /* renamed from: d */
    public Map<String, String> m2618d() {
        return this.f2348d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        dq dqVar = (dq) obj;
        if (this.f2345a != dqVar.f2345a) {
            return false;
        }
        if (this.f2346b == null ? dqVar.f2346b != null : !this.f2346b.equals(dqVar.f2346b)) {
            return false;
        }
        if (this.f2347c == null ? dqVar.f2347c != null : !this.f2347c.equals(dqVar.f2347c)) {
            return false;
        }
        if (this.f2348d != null) {
            if (this.f2348d.equals(dqVar.f2348d)) {
                return true;
            }
        } else if (dqVar.f2348d == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f2347c != null ? this.f2347c.hashCode() : 0) + (((this.f2346b != null ? this.f2346b.hashCode() : 0) + (this.f2345a * 31)) * 31)) * 31;
        if (this.f2348d != null) {
            i = this.f2348d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "PostbackRequest{attemptNumber=" + this.f2345a + ", targetUrl='" + this.f2346b + '\'' + ", backupUrl='" + this.f2347c + '\'' + ", requestBody=" + this.f2348d + '}';
    }
}
