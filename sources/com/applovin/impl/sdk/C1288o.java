package com.applovin.impl.sdk;

/* renamed from: com.applovin.impl.sdk.o */
public enum C1288o {
    NONE("NONE"),
    DIRECT("DIRECT"),
    INDIRECT("INDIRECT");
    
    /* renamed from: d */
    private final String f2611d;

    private C1288o(String str) {
        this.f2611d = str;
    }

    /* renamed from: a */
    public static C1288o m3065a(String str) {
        return DIRECT.toString().equalsIgnoreCase(str) ? DIRECT : INDIRECT.toString().equalsIgnoreCase(str) ? INDIRECT : NONE;
    }

    public String toString() {
        return this.f2611d;
    }
}
