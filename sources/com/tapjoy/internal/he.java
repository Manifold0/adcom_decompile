package com.tapjoy.internal;

public final class he {
    /* renamed from: a */
    public float f8061a;
    /* renamed from: b */
    public int f8062b;

    /* renamed from: a */
    public static he m8028a(String str) {
        if (ct.m7339c(str)) {
            return null;
        }
        try {
            he heVar = new he();
            int length = str.length();
            char charAt = str.charAt(length - 1);
            if (charAt == 'w') {
                heVar.f8061a = Float.valueOf(str.substring(0, length - 1)).floatValue();
                heVar.f8062b = 1;
            } else if (charAt == 'h') {
                heVar.f8061a = Float.valueOf(str.substring(0, length - 1)).floatValue();
                heVar.f8062b = 2;
            } else {
                heVar.f8061a = Float.valueOf(str).floatValue();
                heVar.f8062b = 0;
            }
            return heVar;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /* renamed from: a */
    public final float m8029a(float f, float f2) {
        if (this.f8062b == 1) {
            return (this.f8061a * f) / 100.0f;
        }
        if (this.f8062b == 2) {
            return (this.f8061a * f2) / 100.0f;
        }
        return this.f8061a;
    }
}
