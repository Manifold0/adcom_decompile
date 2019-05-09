// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.j;

import android.database.Cursor;
import android.support.annotation.WorkerThread;
import org.json.JSONObject;
import android.content.ContentValues;
import java.util.UUID;
import java.util.Map;

public class c extends g
{
    public static final b a;
    public static final b b;
    public static final b c;
    public static final b d;
    public static final b e;
    public static final b f;
    public static final b g;
    public static final b h;
    public static final b i;
    public static final b[] j;
    private static final String l;
    
    static {
        a = new b(0, "event_id", "TEXT PRIMARY KEY");
        b = new b(1, "token_id", "TEXT REFERENCES tokens ON UPDATE CASCADE ON DELETE RESTRICT");
        c = new b(2, "priority", "INTEGER");
        d = new b(3, "type", "TEXT");
        e = new b(4, "time", "REAL");
        f = new b(5, "session_time", "REAL");
        g = new b(6, "session_id", "TEXT");
        h = new b(7, "data", "TEXT");
        i = new b(8, "attempt", "INTEGER");
        j = new b[] { com.facebook.ads.internal.j.c.a, com.facebook.ads.internal.j.c.b, com.facebook.ads.internal.j.c.c, com.facebook.ads.internal.j.c.d, com.facebook.ads.internal.j.c.e, com.facebook.ads.internal.j.c.f, com.facebook.ads.internal.j.c.g, com.facebook.ads.internal.j.c.h, com.facebook.ads.internal.j.c.i };
        l = com.facebook.ads.internal.j.g.a("events", com.facebook.ads.internal.j.c.j);
    }
    
    public c(final d d) {
        super(d);
    }
    
    @Override
    public String a() {
        return "events";
    }
    
    @WorkerThread
    String a(String string, final int n, String b, final double n2, final double n3, final String s, final Map<String, String> map) {
        final String string2 = UUID.randomUUID().toString();
        final ContentValues contentValues = new ContentValues(9);
        contentValues.put(com.facebook.ads.internal.j.c.a.b, string2);
        contentValues.put(com.facebook.ads.internal.j.c.b.b, string);
        contentValues.put(com.facebook.ads.internal.j.c.c.b, Integer.valueOf(n));
        contentValues.put(com.facebook.ads.internal.j.c.d.b, b);
        contentValues.put(com.facebook.ads.internal.j.c.e.b, Double.valueOf(n2));
        contentValues.put(com.facebook.ads.internal.j.c.f.b, Double.valueOf(n3));
        contentValues.put(com.facebook.ads.internal.j.c.g.b, s);
        b = com.facebook.ads.internal.j.c.h.b;
        if (map != null) {
            string = new JSONObject((Map)map).toString();
        }
        else {
            string = null;
        }
        contentValues.put(b, string);
        contentValues.put(com.facebook.ads.internal.j.c.i.b, Integer.valueOf(0));
        this.f().insertOrThrow("events", (String)null, contentValues);
        return string2;
    }
    
    boolean a(final String s) {
        return this.f().delete("events", com.facebook.ads.internal.j.c.a.b + " = ?", new String[] { s }) > 0;
    }
    
    @Override
    public b[] b() {
        return com.facebook.ads.internal.j.c.j;
    }
    
    @WorkerThread
    Cursor c() {
        return this.f().rawQuery("SELECT count(*) FROM events", (String[])null);
    }
    
    @WorkerThread
    Cursor d() {
        return this.f().rawQuery(com.facebook.ads.internal.j.c.l, (String[])null);
    }
}
