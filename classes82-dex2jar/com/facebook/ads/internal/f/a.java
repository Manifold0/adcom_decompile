// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.f;

import org.json.JSONException;
import com.facebook.ads.internal.w.h.b;
import java.util.HashMap;
import java.util.Map;
import android.support.annotation.Nullable;
import android.content.SharedPreferences$Editor;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import android.text.TextUtils;
import java.util.List;
import android.content.Context;
import android.content.SharedPreferences;

public class a
{
    private static final String[] a;
    private static a b;
    private final SharedPreferences c;
    
    static {
        a = new String[] { "hide_ad", "hide_ad_description", "hide_ad_follow_up_heading", "hide_ad_options", "report_ad", "report_ad_description", "report_ad_follow_up_heading", "report_ad_options", "manage_ad_preferences", "finished_hide_ad", "finished_report_ad", "finished_description", "why_am_i_seeing_this", "ad_choices_uri", "manage_ad_preferences_uri" };
    }
    
    private a(final Context context) {
        this.c = context.getApplicationContext().getSharedPreferences(com.facebook.ads.internal.w.f.a.a("com.facebook.ads.AD_REPORTING_CONFIG", context), 0);
    }
    
    public static long a(final Context context) {
        return o(context).c.getLong("last_updated_timestamp", 0L);
    }
    
    private String a(String string, final String s) {
        string = this.c.getString(string, s);
        if (string == null || string.equals("null")) {
            return s;
        }
        return string;
    }
    
    private static List<c> a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s) || s.equalsIgnoreCase("null")) {
            return new ArrayList<c>();
        }
        final JSONArray jsonArray = new JSONArray(s);
        final ArrayList<c> list = new ArrayList<c>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject jsonObject = (JSONObject)jsonArray.get(i);
            final c c = new c(jsonObject.getInt("option_value"), jsonObject.getString("option_text"), jsonObject.optString("children_heading"));
            final Iterator<c> iterator = a(jsonObject.optString("children_options")).iterator();
            while (iterator.hasNext()) {
                c.a(iterator.next());
            }
            list.add(c);
        }
        return list;
    }
    
    private void a() {
        final SharedPreferences$Editor edit = this.c.edit();
        edit.putLong("last_updated_timestamp", 0L);
        edit.apply();
    }
    
    public static void a(final Context context, @Nullable final String s) {
        final Map<String, String> b = b(context, s);
        if (b == null || b.size() != com.facebook.ads.internal.f.a.a.length) {
            return;
        }
        final SharedPreferences$Editor edit = o(context).c.edit();
        final String[] a = com.facebook.ads.internal.f.a.a;
        for (int length = a.length, i = 0; i < length; ++i) {
            final String s2 = a[i];
            edit.putString(s2, (String)b.get(s2));
        }
        edit.putLong("last_updated_timestamp", System.currentTimeMillis());
        edit.apply();
    }
    
    public static boolean a(final Context context, final boolean b) {
        boolean b2;
        if (b) {
            b2 = com.facebook.ads.internal.r.a.O(context);
        }
        else {
            b2 = com.facebook.ads.internal.r.a.P(context);
        }
        return b2 && a(context) > 0L;
    }
    
    public static String b(final Context context) {
        return o(context).a("hide_ad", "Hide Ad");
    }
    
    @Nullable
    private static Map<String, String> b(final Context context, @Nullable final String s) {
        if (s != null && !s.isEmpty() && !s.equals("[]")) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(s);
                final String[] a = com.facebook.ads.internal.f.a.a;
                for (int length = a.length, i = 0; i < length; ++i) {
                    final String s2 = a[i];
                    if (!jsonObject.has(s2)) {
                        return null;
                    }
                    hashMap.put(s2, jsonObject.getString(s2));
                }
                if (a(jsonObject.getString("report_ad_options")).size() == 0) {
                    com.facebook.ads.internal.w.h.a.b(context, "reporting", com.facebook.ads.internal.w.h.b.M, new Exception("No report ad options"));
                    return null;
                }
            }
            catch (JSONException ex) {
                com.facebook.ads.internal.w.h.a.b(context, "reporting", com.facebook.ads.internal.w.h.b.L, (Exception)ex);
                return null;
            }
            if (a(jsonObject.getString("hide_ad_options")).size() == 0) {
                com.facebook.ads.internal.w.h.a.b(context, "reporting", com.facebook.ads.internal.w.h.b.O, new Exception("No hide ad options"));
                return null;
            }
            return hashMap;
        }
        return null;
    }
    
    public static String c(final Context context) {
        return o(context).a("hide_ad_description", "See fewer ads like this");
    }
    
    public static c d(final Context context) {
        final c c = new c(o(context).a("hide_ad_follow_up_heading", "Help us understand what is happening. Why don't you want to see this?"));
        try {
            final Iterator<c> iterator = a(o(context).a("hide_ad_options", "")).iterator();
            while (iterator.hasNext()) {
                c.a(iterator.next());
            }
        }
        catch (JSONException ex) {
            o(context).a();
            com.facebook.ads.internal.w.h.a.b(context, "reporting", com.facebook.ads.internal.w.h.b.P, (Exception)ex);
        }
        return c;
    }
    
    public static String e(final Context context) {
        return o(context).a("report_ad", "Report Ad");
    }
    
    public static String f(final Context context) {
        return o(context).a("report_ad_description", " Mark ad as offensive or inappropriate");
    }
    
    public static c g(final Context context) {
        final c c = new c(o(context).a("report_ad_follow_up_heading", "Help us understand what is happening. Why is this inappropriate?"));
        try {
            final Iterator<c> iterator = a(o(context).a("report_ad_options", "")).iterator();
            while (iterator.hasNext()) {
                c.a(iterator.next());
            }
        }
        catch (JSONException ex) {
            o(context).a();
            com.facebook.ads.internal.w.h.a.b(context, "reporting", com.facebook.ads.internal.w.h.b.N, (Exception)ex);
        }
        return c;
    }
    
    public static String h(final Context context) {
        return o(context).a("manage_ad_preferences", "Manage ad preferences");
    }
    
    public static String i(final Context context) {
        return o(context).a("finished_hide_ad", "Ad hidden.");
    }
    
    public static String j(final Context context) {
        return o(context).a("finished_report_ad", "Ad reported.");
    }
    
    public static String k(final Context context) {
        return o(context).a("finished_description", "Your submission is now being reviewed.");
    }
    
    public static String l(final Context context) {
        return o(context).a("why_am_i_seeing_this", "Why am I seeing this?");
    }
    
    public static String m(final Context context) {
        return o(context).a("ad_choices_uri", "");
    }
    
    public static String n(final Context context) {
        return o(context).a("manage_ad_preferences_uri", "");
    }
    
    private static a o(final Context context) {
        Label_0029: {
            if (com.facebook.ads.internal.f.a.b != null) {
                break Label_0029;
            }
            synchronized (a.class) {
                if (com.facebook.ads.internal.f.a.b == null) {
                    com.facebook.ads.internal.f.a.b = new a(context);
                }
                return com.facebook.ads.internal.f.a.b;
            }
        }
    }
}
