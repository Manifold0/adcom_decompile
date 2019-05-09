package com.tapjoy.internal;

import java.util.Collections;
import java.util.List;

public final class ds {
    /* renamed from: a */
    public static List m7476a() {
        return new dt(Collections.emptyList());
    }

    /* renamed from: a */
    public static List m7477a(String str, List list) {
        if (list == null) {
            throw new NullPointerException(str + " == null");
        }
        if (list instanceof dt) {
            list = ((dt) list).f7379a;
        }
        if (list == Collections.emptyList() || (list instanceof dr)) {
            return list;
        }
        dr drVar = new dr(list);
        if (!drVar.contains(null)) {
            return drVar;
        }
        throw new IllegalArgumentException(str + ".contains(null)");
    }

    /* renamed from: a */
    public static boolean m7478a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    public static IllegalStateException m7475a(Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = objArr.length;
        String str = "";
        for (int i = 0; i < length; i += 2) {
            if (objArr[i] == null) {
                if (stringBuilder.length() > 0) {
                    str = "s";
                }
                stringBuilder.append("\n  ");
                stringBuilder.append(objArr[i + 1]);
            }
        }
        throw new IllegalStateException("Required field" + str + " not set:" + stringBuilder);
    }
}
