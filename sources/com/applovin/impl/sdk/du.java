package com.applovin.impl.sdk;

import java.util.Map;

public class du {
    /* renamed from: a */
    private final String f2354a;
    /* renamed from: b */
    private final Map<String, String> f2355b;
    /* renamed from: c */
    private final long f2356c;
    /* renamed from: d */
    private final String f2357d;

    public du(String str, Map<String, String> map, long j, String str2) {
        this.f2354a = str;
        this.f2355b = map;
        this.f2356c = j;
        this.f2357d = str2;
    }

    /* renamed from: a */
    public String m2627a() {
        return this.f2354a;
    }

    /* renamed from: b */
    public Map<String, String> m2628b() {
        return this.f2355b;
    }

    /* renamed from: c */
    public long m2629c() {
        return this.f2356c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        if (r6 != r7) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r7 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r6.getClass();
        r3 = r7.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r7 = (com.applovin.impl.sdk.du) r7;
        r2 = r6.f2356c;
        r4 = r7.f2356c;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 != 0) goto L_0x0005;
    L_0x001c:
        r2 = r6.f2354a;
        if (r2 == 0) goto L_0x0049;
    L_0x0020:
        r2 = r6.f2354a;
        r3 = r7.f2354a;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x002a:
        r2 = r6.f2355b;
        if (r2 == 0) goto L_0x004e;
    L_0x002e:
        r2 = r6.f2355b;
        r3 = r7.f2355b;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0005;
    L_0x0038:
        r2 = r6.f2357d;
        if (r2 == 0) goto L_0x0053;
    L_0x003c:
        r2 = r6.f2357d;
        r3 = r7.f2357d;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0047;
    L_0x0046:
        r0 = r1;
    L_0x0047:
        r1 = r0;
        goto L_0x0005;
    L_0x0049:
        r2 = r7.f2354a;
        if (r2 == 0) goto L_0x002a;
    L_0x004d:
        goto L_0x0005;
    L_0x004e:
        r2 = r7.f2355b;
        if (r2 == 0) goto L_0x0038;
    L_0x0052:
        goto L_0x0005;
    L_0x0053:
        r2 = r7.f2357d;
        if (r2 != 0) goto L_0x0046;
    L_0x0057:
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.du.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.f2355b != null ? this.f2355b.hashCode() : 0) + ((this.f2354a != null ? this.f2354a.hashCode() : 0) * 31)) * 31) + ((int) (this.f2356c ^ (this.f2356c >>> 32)))) * 31;
        if (this.f2357d != null) {
            i = this.f2357d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "SdkEvent{eventType='" + this.f2354a + '\'' + ", parameters=" + this.f2355b + ", creationTsMillis=" + this.f2356c + ", uniqueIdentifier='" + this.f2357d + '\'' + '}';
    }
}
