// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.b;

import android.database.Cursor;
import com.kongregate.android.internal.util.StringUtils;
import org.json.JSONArray;
import com.kongregate.o.c.d;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.json.JSONObject;
import com.kongregate.o.g.c;
import com.kongregate.o.g.b;
import com.kongregate.android.internal.util.j;
import com.kongregate.o.e.a;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class f implements BaseColumns
{
    public static final String a = "user_badges";
    public static final String b = "user_id";
    public static final String c = "badge_id";
    
    static void a(final SQLiteDatabase sqLiteDatabase, final long n, final long n2) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", Long.valueOf(n));
        contentValues.put("badge_id", Long.valueOf(n2));
        if (com.kongregate.o.e.a.a(sqLiteDatabase, "user_badges", contentValues) > 0L) {
            j.b("Created/updated user_badge, badge_id: " + n2 + ", user_id: " + n);
        }
    }
    
    public static void a(final SQLiteDatabase sqLiteDatabase, final long n, final String s) {
        com.kongregate.o.g.b.a().a("/accounts/" + s + "/user_mobile_badges.json", (b.a)new b.a() {
            @Override
            public void a(final c c, final JSONObject jsonObject) {
                final JSONArray optJSONArray = jsonObject.optJSONArray("badge_ids");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    final LinkedHashSet<Long> set = new LinkedHashSet<Long>();
                    for (int i = 0; i < optJSONArray.length(); ++i) {
                        set.add(optJSONArray.optLong(i));
                    }
                    com.kongregate.o.c.d.a(new Runnable() {
                        @Override
                        public void run() {
                            b(sqLiteDatabase, n, set);
                        }
                    });
                }
            }
        });
    }
    
    private static void b(final SQLiteDatabase sqLiteDatabase, final long n, final Collection<Long> collection) {
        sqLiteDatabase.beginTransaction();
        while (true) {
            try {
                final Cursor query = sqLiteDatabase.query("badges", new String[] { "_id" }, "_id IN (" + StringUtils.a(collection, ",") + ") AND " + "_id" + " NOT IN (SELECT " + "badge_id" + " FROM " + "user_badges" + " WHERE " + "user_id" + "=?)", new String[] { String.valueOf(n) }, (String)null, (String)null, (String)null);
                if (query == null) {
                    sqLiteDatabase.endTransaction();
                    com.kongregate.o.e.a.a(query);
                    return;
                }
                Label_0175: {
                    try {
                        query.moveToFirst();
                        while (!query.isAfterLast()) {
                            a(sqLiteDatabase, n, query.getLong(0));
                            query.moveToNext();
                        }
                        break Label_0175;
                    }
                    finally {}
                    sqLiteDatabase.endTransaction();
                    com.kongregate.o.e.a.a(query);
                    throw;
                }
                sqLiteDatabase.setTransactionSuccessful();
                sqLiteDatabase.endTransaction();
                com.kongregate.o.e.a.a(query);
            }
            finally {
                final Cursor query = null;
                continue;
            }
            break;
        }
    }
}
