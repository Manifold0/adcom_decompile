package com.kongregate.android.internal.sdk;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p006c.C0626d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kongregate.android.internal.sdk.b */
public class C0487b {
    /* renamed from: a */
    public static Map<String, Object> m423a(String str) {
        JSONObject a = C0561i.m740a(str, new JSONObject());
        Map hashMap = new HashMap();
        Iterator keys = a.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            hashMap.put(str2, C0487b.m420a(a, str2));
        }
        return hashMap;
    }

    /* renamed from: a */
    private static Object m420a(JSONObject jSONObject, String str) {
        try {
            Object obj = jSONObject.get(str);
            if (!(obj instanceof JSONArray)) {
                return obj;
            }
            JSONArray jSONArray = (JSONArray) obj;
            Object obj2 = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                obj2[i] = jSONArray.getString(i);
            }
            return obj2;
        } catch (Throwable e) {
            C0562j.m759c("unable to parse api setting " + str);
            C0626d.m966a(e);
            return null;
        }
    }

    /* renamed from: a */
    public static String m421a(Map<String, Object> map, String str) {
        return C0487b.m422a((Map) map, str, null);
    }

    /* renamed from: a */
    public static String m422a(Map<String, Object> map, String str, String str2) {
        if (map == null || StringUtils.m580a((CharSequence) str)) {
            return str2;
        }
        Object obj = map.get(str);
        if (obj instanceof String) {
            return String.valueOf(obj);
        }
        return str2;
    }

    /* renamed from: a */
    public static boolean m425a(Map<String, Object> map, String str, boolean z) {
        if (map == null || StringUtils.m580a((CharSequence) str)) {
            return z;
        }
        Object obj = map.get(str);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return z;
    }

    /* renamed from: b */
    public static int m426b(Map<String, Object> map, String str) {
        return ((Integer) C0487b.m419a(map, str, Integer.class, Integer.valueOf(0))).intValue();
    }

    /* renamed from: c */
    public static double m427c(Map<String, Object> map, String str) {
        return ((Number) C0487b.m419a(map, str, Number.class, Double.valueOf(0.0d))).doubleValue();
    }

    /* renamed from: d */
    public static String[] m428d(Map<String, Object> map, String str) {
        if (map == null || StringUtils.m580a((CharSequence) str)) {
            return new String[0];
        }
        Object obj = map.get(str);
        if (obj instanceof String) {
            if (((String) obj).trim().startsWith(RequestParameters.LEFT_BRACKETS)) {
                return (String[]) C0561i.m746b(C0561i.m750d((String) obj)).toArray(new String[0]);
            }
            return new String[]{(String) obj};
        } else if (obj instanceof String[]) {
            return (String[]) obj;
        } else {
            return new String[0];
        }
    }

    /* renamed from: a */
    public static boolean m424a(String str, String str2) {
        if (str == null || str2 == null) {
            C0562j.m759c("hasSDKVersion: invalid version format: " + str + " or " + str2);
            return false;
        }
        String[] split = str.split(Pattern.quote("."));
        String[] split2 = str2.split(Pattern.quote("."));
        Integer valueOf = Integer.valueOf(0);
        Integer valueOf2 = Integer.valueOf(0);
        Integer num = valueOf;
        int i = 0;
        while (i < split.length) {
            try {
                int parseInt;
                Integer valueOf3 = Integer.valueOf(Integer.parseInt(split[i]));
                if (i < split2.length) {
                    parseInt = Integer.parseInt(split2[i]);
                } else {
                    parseInt = 0;
                }
                num = Integer.valueOf(parseInt);
                if (valueOf3 == num) {
                    i++;
                    valueOf2 = num;
                    num = valueOf3;
                } else if (valueOf3.intValue() <= num.intValue()) {
                    return false;
                } else {
                    return true;
                }
            } catch (NumberFormatException e) {
                C0562j.m759c("hasSDKVersion: invalid version format: " + str + " or " + str2);
                return false;
            }
        }
        if (num.intValue() < valueOf2.intValue()) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private static <T> T m419a(Map<String, Object> map, String str, Class<T> cls, T t) {
        if (map == null || StringUtils.m580a((CharSequence) str)) {
            return t;
        }
        T t2 = map.get(str);
        if (t2 == null || !cls.isAssignableFrom(t2.getClass())) {
            return t;
        }
        return t2;
    }
}
