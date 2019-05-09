package com.kongregate.android.internal.util;

import android.text.TextUtils;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.kongregate.android.internal.util.i */
public class C0561i {
    /* renamed from: a */
    private static final ThreadLocal<DateFormat> f752a = new C05601();

    /* renamed from: com.kongregate.android.internal.util.i$1 */
    static class C05601 extends ThreadLocal<DateFormat> {
        C05601() {
        }

        protected /* synthetic */ Object initialValue() {
            return m731a();
        }

        /* renamed from: a */
        protected SimpleDateFormat m731a() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z", Locale.US);
            simpleDateFormat.setCalendar(GregorianCalendar.getInstance(Locale.US));
            return simpleDateFormat;
        }
    }

    /* renamed from: a */
    public static Date m738a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        return C0561i.m737a(jSONObject.optString(str));
    }

    /* renamed from: a */
    public static Date m737a(String str) {
        if (str == null || str.length() == 0) {
            C0562j.m759c("Invalid date: " + str);
            return null;
        }
        try {
            return ((DateFormat) f752a.get()).parse(str);
        } catch (Throwable e) {
            C0562j.m760c("exception parsing date: [" + str + RequestParameters.RIGHT_BRACKETS, e);
            return null;
        }
    }

    /* renamed from: a */
    public static String m733a(long j) {
        return ((DateFormat) f752a.get()).format(new Date(j));
    }

    /* renamed from: b */
    public static long m745b(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return 0;
        }
        return C0561i.m744b(jSONObject.optString(str));
    }

    /* renamed from: b */
    public static long m744b(String str) {
        Date a = C0561i.m737a(str);
        if (a == null) {
            return 0;
        }
        return a.getTime();
    }

    /* renamed from: a */
    public static int m732a(JSONObject jSONObject, String str, int i) {
        if (jSONObject == null) {
            return i;
        }
        String optString = jSONObject.optString(str, null);
        if (optString == null) {
            return jSONObject.optInt(str, i);
        }
        try {
            return Integer.parseInt(optString, 10);
        } catch (Throwable e) {
            C0562j.m760c("Failed to parse number: " + optString, e);
            return i;
        }
    }

    /* renamed from: c */
    public static String m748c(JSONObject jSONObject, String str) {
        if (jSONObject.isNull(str)) {
            return "";
        }
        return jSONObject.optString(str, "");
    }

    /* renamed from: a */
    public static JSONObject m741a(byte[] bArr) {
        if (bArr != null) {
            return C0561i.m749c(new String(bArr));
        }
        return null;
    }

    /* renamed from: b */
    public static JSONArray m747b(byte[] bArr) {
        if (bArr != null) {
            return C0561i.m750d(new String(bArr));
        }
        return null;
    }

    /* renamed from: c */
    public static JSONObject m749c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return new JSONObject(str);
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m740a(String str, JSONObject jSONObject) {
        JSONObject c = C0561i.m749c(str);
        if (c != null) {
            return c;
        }
        return jSONObject;
    }

    /* renamed from: d */
    public static JSONArray m750d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return new JSONArray(str);
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static List<Long> m739a(JSONArray jSONArray) {
        int i = 0;
        if (jSONArray == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        while (i < jSONArray.length()) {
            arrayList.add(Long.valueOf(jSONArray.optLong(i)));
            i++;
        }
        return arrayList;
    }

    /* renamed from: b */
    public static List<String> m746b(JSONArray jSONArray) {
        int i = 0;
        if (jSONArray == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        while (i < jSONArray.length()) {
            arrayList.add(jSONArray.optString(i));
            i++;
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m735a(JSONObject jSONObject, String str, String str2) {
        return C0561i.m736a(jSONObject, str, null, str2);
    }

    /* renamed from: a */
    public static String m736a(JSONObject jSONObject, String str, String str2, String str3) {
        if (jSONObject == null || jSONObject.length() == 0) {
            C0562j.m759c("key " + str + " not found in null or empty json object: " + str3);
            return str2;
        }
        String optString = jSONObject.optString(str);
        if (optString != null) {
            return optString;
        }
        C0562j.m759c("key " + str + " not found in json object: " + str3);
        return str2;
    }

    /* renamed from: a */
    public static String m734a(Map<String, Object> map, boolean z) {
        JSONObject jSONObject;
        if (z) {
            Map<String, Object> hashMap = new HashMap(map);
            C0561i.m742a((Map) hashMap);
            map = hashMap;
        }
        try {
            jSONObject = new JSONObject(map);
        } catch (Throwable e) {
            C0562j.m760c("Exception creating json from analytic fields", e);
            jSONObject = new JSONObject();
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    public static void m742a(Map<String, Object> map) {
        for (String str : map.keySet()) {
            if (!C0561i.m743a(map.get(str))) {
                map.put(str, null);
            }
        }
    }

    /* renamed from: a */
    public static boolean m743a(Object obj) {
        if (obj != null) {
            if (obj instanceof Double) {
                if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                    C0562j.m759c("JSON does not allow non-finite numbers.");
                    return false;
                }
            } else if ((obj instanceof Float) && (((Float) obj).isInfinite() || ((Float) obj).isNaN())) {
                C0562j.m759c("JSON does not allow non-finite numbers.");
                return false;
            }
        }
        return true;
    }
}
