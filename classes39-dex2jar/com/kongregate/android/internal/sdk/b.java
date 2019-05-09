// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.HashMap;
import com.kongregate.android.internal.util.i;
import org.json.JSONException;
import com.kongregate.o.c.d;
import com.kongregate.android.internal.util.j;
import org.json.JSONArray;
import org.json.JSONObject;
import com.kongregate.android.internal.util.StringUtils;
import java.util.Map;

public class b
{
    private static <T> T a(final Map<String, Object> map, final String s, final Class<T> clazz, final T t) {
        if (map != null && !StringUtils.a((CharSequence)s)) {
            final T value = map.get(s);
            if (value != null && clazz.isAssignableFrom(value.getClass())) {
                return value;
            }
        }
        return t;
    }
    
    private static Object a(final JSONObject jsonObject, final String s) {
        Object o = null;
        try {
            String[] value;
            final Object o2 = o = (value = (String[])jsonObject.get(s));
            if (o2 instanceof JSONArray) {
                o = o2;
                final JSONArray jsonArray = (JSONArray)o2;
                o = o2;
                value = new String[jsonArray.length()];
                int n = 0;
                while (true) {
                    o = o2;
                    if (n >= jsonArray.length()) {
                        break;
                    }
                    o = o2;
                    value[n] = jsonArray.getString(n);
                    ++n;
                }
            }
            return value;
        }
        catch (JSONException ex) {
            j.c("unable to parse api setting " + s);
            d.a((Throwable)ex);
            return o;
        }
    }
    
    public static String a(final Map<String, Object> map, final String s) {
        return a(map, s, null);
    }
    
    public static String a(final Map<String, Object> map, final String s, final String s2) {
        if (map != null && !StringUtils.a((CharSequence)s)) {
            final Object value = map.get(s);
            if (value instanceof String) {
                return String.valueOf(value);
            }
        }
        return s2;
    }
    
    public static Map<String, Object> a(final String s) {
        final JSONObject a = i.a(s, new JSONObject());
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final Iterator keys = a.keys();
        while (keys.hasNext()) {
            final String s2 = keys.next();
            hashMap.put(s2, a(a, s2));
        }
        return hashMap;
    }
    
    public static boolean a(final String s, final String s2) {
        boolean b = true;
        if (s == null || s2 == null) {
            j.c("hasSDKVersion: invalid version format: " + s + " or " + s2);
            b = false;
        }
        else {
            final String[] split = s.split(Pattern.quote("."));
            final String[] split2 = s2.split(Pattern.quote("."));
            Integer n = 0;
            Integer n2 = 0;
            int i = 0;
            Label_0197: {
                try {
                    Block_6: {
                        while (i < split.length) {
                            n2 = Integer.parseInt(split[i]);
                            int int1;
                            if (i < split2.length) {
                                int1 = Integer.parseInt(split2[i]);
                            }
                            else {
                                int1 = 0;
                            }
                            n = int1;
                            if (n2 != n) {
                                break Block_6;
                            }
                            ++i;
                        }
                        break Label_0197;
                    }
                    if (n2 <= n) {
                        return false;
                    }
                    return b;
                }
                catch (NumberFormatException ex) {
                    j.c("hasSDKVersion: invalid version format: " + s + " or " + s2);
                    return false;
                }
            }
            if (n2 < n) {
                return false;
            }
        }
        return b;
    }
    
    public static boolean a(final Map<String, Object> map, final String s, final boolean b) {
        if (map != null && !StringUtils.a((CharSequence)s)) {
            final Boolean value = map.get(s);
            if (value instanceof Boolean) {
                return value;
            }
        }
        return b;
    }
    
    public static int b(final Map<String, Object> map, final String s) {
        return a(map, s, Integer.class, 0);
    }
    
    public static double c(final Map<String, Object> map, final String s) {
        return a(map, s, Number.class, 0.0).doubleValue();
    }
    
    public static String[] d(final Map<String, Object> map, final String s) {
        if (map == null || StringUtils.a((CharSequence)s)) {
            return new String[0];
        }
        final String value = map.get(s);
        if (value instanceof String) {
            if (value.trim().startsWith("[")) {
                return i.b(i.d(value)).toArray(new String[0]);
            }
            return new String[] { value };
        }
        else {
            if (value instanceof String[]) {
                return (String[])(Object)value;
            }
            return new String[0];
        }
    }
}
