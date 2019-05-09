// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.m;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.List;
import org.json.JSONObject;
import java.util.Date;

public class b
{
    public String a;
    public String b;
    public String c;
    public Date d;
    public boolean e;
    
    public b(final String a, final String b, final String c, final Date d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = (b != null && c != null);
    }
    
    public b(final JSONObject jsonObject) {
        this(jsonObject.optString("url"), jsonObject.optString("key"), jsonObject.optString("value"), new Date(jsonObject.optLong("expiration") * 1000L));
    }
    
    public static List<b> a(String o) {
        if (o == null) {
            return null;
        }
        while (true) {
            try {
                o = new JSONArray((String)o);
                if (o == null) {
                    return null;
                }
            }
            catch (JSONException ex) {
                o = null;
                continue;
            }
            break;
        }
        final ArrayList<b> list = new ArrayList<b>();
        int i = 0;
    Label_0051_Outer:
        while (i < ((JSONArray)o).length()) {
            while (true) {
                try {
                    final JSONObject jsonObject = ((JSONArray)o).getJSONObject(i);
                    if (jsonObject != null) {
                        final b b = new b(jsonObject);
                        if (b != null) {
                            list.add(b);
                        }
                    }
                    ++i;
                    continue Label_0051_Outer;
                }
                catch (JSONException ex2) {
                    final JSONObject jsonObject = null;
                    continue;
                }
                break;
            }
            break;
        }
        return list;
    }
    
    public String a() {
        Date d;
        if ((d = this.d) == null) {
            d = new Date();
            d.setTime(d.getTime() + 3600000L);
        }
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(d);
    }
    
    public boolean b() {
        return this.b != null && this.c != null && this.a != null;
    }
}
