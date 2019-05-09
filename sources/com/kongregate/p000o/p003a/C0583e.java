package com.kongregate.p000o.p003a;

import com.kongregate.android.internal.util.C0562j;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.kongregate.o.a.e */
public class C0583e {
    /* renamed from: a */
    public static final String f802a = "add";
    /* renamed from: b */
    public static final String f803b = "max";
    /* renamed from: c */
    public static final String f804c = "min";
    /* renamed from: d */
    public static final String f805d = "replace";
    /* renamed from: e */
    private String f806e;
    /* renamed from: f */
    private Map<String, Object> f807f = new HashMap();

    public C0583e(String str) {
        this.f806e = str;
    }

    /* renamed from: a */
    public void m861a(String str, Number number, String str2) {
        C0562j.m753a("EventTracker updating numeric field: " + str + " : " + number + " : " + str2);
        Object obj = this.f807f.get(str);
        Number valueOf = obj instanceof Number ? (Number) obj : Integer.valueOf(0);
        if (f802a.equals(str2)) {
            if ((obj instanceof Double) || (number instanceof Double)) {
                this.f807f.put(str, Double.valueOf(valueOf.doubleValue() + number.doubleValue()));
            } else {
                this.f807f.put(str, Long.valueOf(valueOf.longValue() + number.longValue()));
            }
        } else if (f803b.equals(str2)) {
            if (number.doubleValue() > valueOf.doubleValue()) {
                this.f807f.put(str, number);
            } else {
                this.f807f.put(str, valueOf);
            }
        } else if (f804c.equals(str2)) {
            if (number.doubleValue() < valueOf.doubleValue()) {
                this.f807f.put(str, number);
            } else {
                this.f807f.put(str, valueOf);
            }
        } else if (f805d.equals(str2)) {
            this.f807f.put(str, number);
        } else {
            C0562j.m759c("unrecognized operator for number: " + str2);
        }
    }

    /* renamed from: a */
    public void m862a(String str, Object obj) {
        C0562j.m753a("EventTracker replace field: " + str + " : " + obj);
        this.f807f.put(str, obj);
    }

    /* renamed from: b */
    public void m864b(String str, Object obj) {
        if (this.f807f.containsKey(str)) {
            C0562j.m753a("EventTracker field already set, not updating: " + str);
            return;
        }
        C0562j.m753a("EventTracker setting field: " + str + " : " + obj);
        this.f807f.put(str, obj);
    }

    /* renamed from: a */
    public Map<String, Object> m860a() {
        C0562j.m753a("EventTracker: reseting event: " + this.f806e);
        Map hashMap = new HashMap(this.f807f);
        this.f807f.clear();
        return hashMap;
    }

    /* renamed from: b */
    public String m863b() {
        return this.f806e;
    }
}
