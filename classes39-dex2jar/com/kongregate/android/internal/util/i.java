// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import org.json.JSONException;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class i
{
    private static final ThreadLocal<DateFormat> a;
    
    static {
        a = new ThreadLocal<DateFormat>() {
            protected SimpleDateFormat a() {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z", Locale.US);
                simpleDateFormat.setCalendar(Calendar.getInstance(Locale.US));
                return simpleDateFormat;
            }
        };
    }
    
    public static int a(final JSONObject jsonObject, final String s, final int n) {
        if (jsonObject == null) {
            return n;
        }
        final String optString = jsonObject.optString(s, (String)null);
        if (optString == null) {
            return jsonObject.optInt(s, n);
        }
        try {
            return Integer.parseInt(optString, 10);
        }
        catch (NumberFormatException ex) {
            j.c("Failed to parse number: " + optString, ex);
            return n;
        }
    }
    
    public static String a(final long n) {
        return i.a.get().format(new Date(n));
    }
    
    public static String a(final Map<String, Object> map, final boolean b) {
        Map<String, Object> map2 = map;
        if (b) {
            map2 = new HashMap<String, Object>(map);
            a(map2);
        }
        try {
            final JSONObject jsonObject = new JSONObject((Map)map2);
            return jsonObject.toString();
        }
        catch (NullPointerException ex) {
            j.c("Exception creating json from analytic fields", ex);
            final JSONObject jsonObject = new JSONObject();
            return jsonObject.toString();
        }
    }
    
    public static String a(final JSONObject jsonObject, final String s, final String s2) {
        return a(jsonObject, s, null, s2);
    }
    
    public static String a(final JSONObject jsonObject, final String s, final String s2, final String s3) {
        if (jsonObject == null || jsonObject.length() == 0) {
            j.c("key " + s + " not found in null or empty json object: " + s3);
            return s2;
        }
        final String optString = jsonObject.optString(s);
        if (optString == null) {
            j.c("key " + s + " not found in json object: " + s3);
            return s2;
        }
        return optString;
    }
    
    public static Date a(final String s) {
        if (s == null || s.length() == 0) {
            j.c("Invalid date: " + s);
            return null;
        }
        try {
            return i.a.get().parse(s);
        }
        catch (ParseException ex) {
            j.c("exception parsing date: [" + s + "]", ex);
            return null;
        }
    }
    
    public static Date a(final JSONObject jsonObject, final String s) {
        if (jsonObject == null) {
            return null;
        }
        return a(jsonObject.optString(s));
    }
    
    public static List<Long> a(final JSONArray jsonArray) {
        int i = 0;
        if (jsonArray == null) {
            return new ArrayList<Long>(0);
        }
        final ArrayList<Long> list = new ArrayList<Long>(jsonArray.length());
        while (i < jsonArray.length()) {
            list.add(jsonArray.optLong(i));
            ++i;
        }
        return list;
    }
    
    public static JSONObject a(final String s, JSONObject jsonObject) {
        final JSONObject c = c(s);
        if (c != null) {
            jsonObject = c;
        }
        return jsonObject;
    }
    
    public static JSONObject a(final byte[] array) {
        if (array != null) {
            return c(new String(array));
        }
        return null;
    }
    
    public static void a(final Map<String, Object> map) {
        for (final String s : map.keySet()) {
            if (!a(map.get(s))) {
                map.put(s, null);
            }
        }
    }
    
    public static boolean a(final Object o) {
        if (o != null) {
            if (o instanceof Double) {
                if (((Double)o).isInfinite() || ((Double)o).isNaN()) {
                    j.c("JSON does not allow non-finite numbers.");
                    return false;
                }
            }
            else if (o instanceof Float && (((Float)o).isInfinite() || ((Float)o).isNaN())) {
                j.c("JSON does not allow non-finite numbers.");
                return false;
            }
        }
        return true;
    }
    
    public static long b(final String s) {
        final Date a = a(s);
        if (a == null) {
            return 0L;
        }
        return a.getTime();
    }
    
    public static long b(final JSONObject jsonObject, final String s) {
        if (jsonObject == null) {
            return 0L;
        }
        return b(jsonObject.optString(s));
    }
    
    public static List<String> b(final JSONArray jsonArray) {
        int i = 0;
        if (jsonArray == null) {
            return new ArrayList<String>(0);
        }
        final ArrayList<String> list = new ArrayList<String>(jsonArray.length());
        while (i < jsonArray.length()) {
            list.add(jsonArray.optString(i));
            ++i;
        }
        return list;
    }
    
    public static JSONArray b(final byte[] array) {
        if (array != null) {
            return d(new String(array));
        }
        return null;
    }
    
    public static String c(final JSONObject jsonObject, final String s) {
        if (jsonObject.isNull(s)) {
            return "";
        }
        return jsonObject.optString(s, "");
    }
    
    public static JSONObject c(final String s) {
        try {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                return new JSONObject(s);
            }
            return null;
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    public static JSONArray d(final String s) {
        try {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                return new JSONArray(s);
            }
            return null;
        }
        catch (JSONException ex) {
            return null;
        }
    }
}
