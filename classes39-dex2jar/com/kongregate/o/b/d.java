// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.b;

import com.kongregate.android.internal.util.StringUtils;
import org.json.JSONObject;
import android.database.sqlite.SQLiteDatabase;
import com.kongregate.o.e.a;
import android.content.ContentValues;
import com.kongregate.o.e.b;
import com.kongregate.android.internal.util.j;
import org.json.JSONArray;
import android.provider.BaseColumns;

public class d implements BaseColumns
{
    public static final String a = "statistics";
    public static final String b = "game_id";
    public static final String c = "application_id";
    public static final String d = "name";
    public static final String e = "display_name";
    public static final String f = "description";
    public static final String g = "stat_type";
    public static final String h = "display_in_table";
    
    public static void a(final long n, final JSONArray jsonArray) {
        if (jsonArray == null) {
            j.c("null JSON passed into updateStatistics");
            return;
        }
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    final SQLiteDatabase a = com.kongregate.o.e.b.a();
                    a.beginTransaction();
                    while (true) {
                        Label_0210: {
                            try {
                                final ContentValues contentValues = new ContentValues();
                                for (int i = 0; i < jsonArray.length(); ++i) {
                                    contentValues.clear();
                                    final JSONObject optJSONObject = jsonArray.optJSONObject(i);
                                    if (optJSONObject != null) {
                                        contentValues.put("_id", Long.valueOf(optJSONObject.optLong("id", 0L)));
                                        contentValues.put("game_id", Long.valueOf(n));
                                        contentValues.put("name", optJSONObject.optString("name", ""));
                                        contentValues.put("description", optJSONObject.optString("description"));
                                        contentValues.put("stat_type", optJSONObject.optString("stat_type", com.kongregate.o.b.d.a.a.toString()));
                                        if (!optJSONObject.optBoolean("display_in_table", false)) {
                                            break Label_0210;
                                        }
                                        final int n = 1;
                                        contentValues.put("display_in_table", Integer.valueOf(n));
                                        contentValues.put("display_name", optJSONObject.optString("display_name"));
                                    }
                                    com.kongregate.o.e.a.a(com.kongregate.o.e.b.a(), "statistics", contentValues);
                                }
                                com.kongregate.o.b.e.b(a);
                                a.setTransactionSuccessful();
                                return;
                            }
                            finally {
                                a.endTransaction();
                            }
                        }
                        final int n = 0;
                        continue;
                    }
                }
            }
        });
    }
    
    public static boolean a(final a a, final long n, final long n2) {
        boolean b = true;
        switch (d$2.a[a.ordinal()]) {
            default: {
                b = false;
                break;
            }
            case 1: {
                if (n2 == com.kongregate.o.b.d.a.d.a()) {
                    return false;
                }
                break;
            }
            case 2: {
                if (n2 <= n) {
                    return false;
                }
                break;
            }
            case 3: {
                if (n2 >= n) {
                    return false;
                }
                break;
            }
            case 4: {
                if (n == n2) {
                    return false;
                }
                break;
            }
        }
        return b;
    }
    
    public enum a
    {
        a, 
        b, 
        c, 
        d;
        
        public static a a(final String s) {
            if (StringUtils.a((CharSequence)s)) {
                return a.a;
            }
            if (s.equalsIgnoreCase("Max")) {
                return a.a;
            }
            if (s.equalsIgnoreCase("Add")) {
                return a.d;
            }
            if (s.equalsIgnoreCase("Min")) {
                return a.b;
            }
            if (s.equalsIgnoreCase("Replace")) {
                return a.c;
            }
            return a.a;
        }
        
        public long a() {
            switch (d$2.a[this.ordinal()]) {
                default: {
                    return 0L;
                }
                case 3: {
                    return Long.MAX_VALUE;
                }
            }
        }
        
        public long a(final long n, final long n2) {
            long n3 = 0L;
            switch (d$2.a[this.ordinal()]) {
                default: {
                    throw new IllegalStateException("Invalid statistic type");
                }
                case 1: {
                    n3 = n2 + n;
                    break;
                }
                case 2:
                case 3:
                case 4: {
                    n3 = n2;
                    if (!com.kongregate.o.b.d.a(this, n, n2)) {
                        return n;
                    }
                    break;
                }
            }
            return n3;
        }
    }
}
