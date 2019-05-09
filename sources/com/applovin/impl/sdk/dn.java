package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;

public class dn {
    /* renamed from: d */
    private static dn f2333d;
    /* renamed from: a */
    private final Map<an, String> f2334a = new HashMap(1);
    /* renamed from: b */
    private final Map<an, Map<String, String>> f2335b = new HashMap(1);
    /* renamed from: c */
    private final Object f2336c = new Object();

    private dn() {
    }

    /* renamed from: a */
    public static synchronized dn m2591a() {
        dn dnVar;
        synchronized (dn.class) {
            if (f2333d == null) {
                f2333d = new dn();
            }
            dnVar = f2333d;
        }
        return dnVar;
    }

    /* renamed from: a */
    public Map<String, String> m2592a(an anVar) {
        Map<String, String> map;
        synchronized (this.f2336c) {
            map = (Map) this.f2335b.remove(anVar);
        }
        return map;
    }

    /* renamed from: a */
    public void m2593a(an anVar, String str) {
        synchronized (this.f2336c) {
            this.f2334a.put(anVar, str);
        }
    }

    /* renamed from: a */
    public void m2594a(an anVar, Map<String, String> map) {
        synchronized (this.f2336c) {
            this.f2335b.put(anVar, map);
        }
    }

    /* renamed from: b */
    public String m2595b(an anVar) {
        String str;
        synchronized (this.f2336c) {
            str = (String) this.f2334a.remove(anVar);
        }
        return str;
    }
}
