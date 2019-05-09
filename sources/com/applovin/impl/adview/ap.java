package com.applovin.impl.adview;

class ap {
    /* renamed from: a */
    private final String f1774a;
    /* renamed from: b */
    private final ao f1775b;
    /* renamed from: c */
    private final long f1776c;

    private ap(String str, long j, ao aoVar) {
        this.f1774a = str;
        this.f1776c = j;
        this.f1775b = aoVar;
    }

    /* renamed from: a */
    private String m2019a() {
        return this.f1774a;
    }

    /* renamed from: b */
    private long m2021b() {
        return this.f1776c;
    }

    /* renamed from: c */
    private ao m2024c() {
        return this.f1775b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ap)) {
            return false;
        }
        ap apVar = (ap) obj;
        return this.f1774a != null ? this.f1774a.equalsIgnoreCase(apVar.f1774a) : apVar.f1774a == null;
    }

    public int hashCode() {
        return this.f1774a != null ? this.f1774a.hashCode() : 0;
    }

    public String toString() {
        return "CountdownProxy{identifier='" + this.f1774a + '\'' + ", countdownStepMillis=" + this.f1776c + '}';
    }
}
