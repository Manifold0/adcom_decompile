package com.facebook.ads.internal.p046v.p047a;

/* renamed from: com.facebook.ads.internal.v.a.k */
public class C2148k extends C2145l {
    public C2148k(String str, C2152p c2152p) {
        super(str, null);
        this.b = C2147j.POST;
        this.a = str;
        this.c = "application/x-www-form-urlencoded;charset=UTF-8";
        if (c2152p != null) {
            this.d = c2152p.m5514b();
        }
    }

    public C2148k(String str, C2152p c2152p, String str2, byte[] bArr) {
        super(str, c2152p);
        this.b = C2147j.POST;
        this.c = str2;
        this.d = bArr;
    }
}
