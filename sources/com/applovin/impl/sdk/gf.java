package com.applovin.impl.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class gf {
    /* renamed from: a */
    public static final gf f2570a = new gf();
    /* renamed from: b */
    protected String f2571b;
    /* renamed from: c */
    protected final List<gf> f2572c;
    /* renamed from: d */
    private final gf f2573d;
    /* renamed from: e */
    private final String f2574e;
    /* renamed from: f */
    private final Map<String, String> f2575f;

    private gf() {
        this.f2573d = null;
        this.f2574e = "";
        this.f2575f = Collections.emptyMap();
        this.f2571b = "";
        this.f2572c = Collections.emptyList();
    }

    public gf(String str, Map<String, String> map, gf gfVar) {
        this.f2573d = gfVar;
        this.f2574e = str;
        this.f2575f = Collections.unmodifiableMap(map);
        this.f2572c = new ArrayList();
    }

    /* renamed from: a */
    public String m2972a() {
        return this.f2574e;
    }

    /* renamed from: a */
    public List<gf> m2973a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified.");
        }
        List<gf> arrayList = new ArrayList(this.f2572c.size());
        for (gf gfVar : this.f2572c) {
            if (str.equalsIgnoreCase(gfVar.m2972a())) {
                arrayList.add(gfVar);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public gf m2974b(String str) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified.");
        }
        for (gf gfVar : this.f2572c) {
            if (str.equalsIgnoreCase(gfVar.m2972a())) {
                return gfVar;
            }
        }
        return null;
    }

    /* renamed from: b */
    public Map<String, String> m2975b() {
        return this.f2575f;
    }

    /* renamed from: c */
    public gf m2976c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified.");
        }
        if (this.f2572c.size() > 0) {
            List arrayList = new ArrayList();
            arrayList.add(this);
            while (!arrayList.isEmpty()) {
                gf gfVar = (gf) arrayList.get(0);
                arrayList.remove(0);
                if (str.equalsIgnoreCase(gfVar.m2972a())) {
                    return gfVar;
                }
                arrayList.addAll(gfVar.m2978d());
            }
        }
        return null;
    }

    /* renamed from: c */
    public String m2977c() {
        return this.f2571b;
    }

    /* renamed from: d */
    public List<gf> m2978d() {
        return Collections.unmodifiableList(this.f2572c);
    }

    public String toString() {
        return "XmlNode{, elementName='" + this.f2574e + '\'' + ", text='" + this.f2571b + '\'' + ", attributes=" + this.f2575f + '}';
    }
}
