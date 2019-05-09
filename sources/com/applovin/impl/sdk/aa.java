package com.applovin.impl.sdk;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class aa {
    /* renamed from: a */
    static String m2191a(Collection<String> collection, int i) {
        return m2192a(collection, ",", i);
    }

    /* renamed from: a */
    static String m2192a(Collection<String> collection, String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("No glue specified");
        } else if (collection == null || collection.size() < 1) {
            return "";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            int i2 = 0;
            for (String str2 : collection) {
                if (i2 >= i) {
                    break;
                }
                i2++;
                stringBuilder.append(str2).append(str);
            }
            if (stringBuilder.length() > str.length()) {
                stringBuilder.setLength(stringBuilder.length() - str.length());
            }
            return stringBuilder.toString();
        }
    }

    /* renamed from: a */
    public static List<String> m2193a(String str) {
        return m2194a(str, ",\\s*");
    }

    /* renamed from: a */
    public static List<String> m2194a(String str, String str2) {
        return TextUtils.isEmpty(str) ? Collections.emptyList() : Arrays.asList(str.split(str2));
    }

    /* renamed from: a */
    static List<String> m2195a(List<String> list) {
        if (list == null) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (String trim : list) {
            CharSequence trim2 = trim.trim();
            if (!TextUtils.isEmpty(trim2)) {
                arrayList.add(trim2);
            }
        }
        return arrayList;
    }
}
