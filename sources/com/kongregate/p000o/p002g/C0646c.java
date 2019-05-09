package com.kongregate.p000o.p002g;

import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0567o;
import com.kongregate.android.internal.util.C0568p;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.g.c */
public class C0646c extends C0567o {
    /* renamed from: b */
    private final byte[] f1028b;
    /* renamed from: c */
    private final int f1029c;

    public C0646c(C0568p c0568p, byte[] bArr, int i) {
        super(c0568p);
        this.f1028b = bArr;
        this.f1029c = i;
    }

    public C0646c(byte[] bArr) {
        this(C0568p.SUCCESS, bArr, 200);
    }

    public C0646c(C0568p c0568p) {
        this(c0568p, null, 0);
    }

    /* renamed from: a */
    public byte[] m1094a() {
        return this.f1028b;
    }

    /* renamed from: b */
    public String m1095b() {
        try {
            return new String(this.f1028b, "UTF-8");
        } catch (Throwable e) {
            C0562j.m762d("Unsupported encoding!", e);
            return null;
        } catch (NullPointerException e2) {
            return null;
        }
    }

    /* renamed from: c */
    public JSONObject m1096c() {
        return C0561i.m741a(this.f1028b);
    }

    /* renamed from: d */
    public JSONArray m1097d() {
        return C0561i.m747b(this.f1028b);
    }

    /* renamed from: e */
    public int m1098e() {
        return this.f1029c;
    }
}
