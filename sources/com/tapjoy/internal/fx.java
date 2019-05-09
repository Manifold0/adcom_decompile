package com.tapjoy.internal;

public final class fx {
    /* renamed from: a */
    public static String m7793a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        return (trim == null || trim.length() == 0) ? null : trim;
    }

    /* renamed from: b */
    public static String m7795b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() != 0) {
            return trim;
        }
        return null;
    }

    /* renamed from: a */
    public static String m7794a(String str, String str2, String str3) {
        if (str == null) {
            fz.m7812a(str2, str3, "must not be null");
            return null;
        } else if (str.length() == 0) {
            fz.m7812a(str2, str3, "must not be empty");
            return null;
        } else {
            String trim = str.trim();
            if (trim.length() != 0) {
                return trim;
            }
            fz.m7812a(str2, str3, "must not be blank");
            return null;
        }
    }
}
