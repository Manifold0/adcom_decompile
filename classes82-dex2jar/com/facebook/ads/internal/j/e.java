// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.j;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.text.TextUtils;
import com.facebook.ads.internal.r.a;
import java.util.Locale;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class e extends SQLiteOpenHelper
{
    public static final String a;
    private final d b;
    
    static {
        a = e.class.getSimpleName();
    }
    
    public e(final Context context, final d b) {
        final String format = String.format(Locale.US, "ads%s.db", "");
        String format2;
        if (!com.facebook.ads.internal.r.a.Q(context)) {
            format2 = format;
        }
        else {
            final String packageName = context.getPackageName();
            final String a = com.facebook.ads.internal.w.f.a.a(context);
            format2 = format;
            if (!packageName.equals(a)) {
                format2 = format;
                if (!TextUtils.isEmpty((CharSequence)a)) {
                    format2 = String.format(Locale.US, "ads%s.db", '_' + a);
                }
            }
        }
        super(context, format2, (SQLiteDatabase$CursorFactory)null, 4);
        if (b == null) {
            throw new IllegalArgumentException("AdDatabaseHelper can not be null");
        }
        this.b = b;
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        final g[] c = this.b.c();
        for (int length = c.length, i = 0; i < length; ++i) {
            c[i].a(sqLiteDatabase);
        }
    }
    
    public void onDowngrade(final SQLiteDatabase sqLiteDatabase, int i, int length) {
        final g[] c = this.b.c();
        g g;
        for (length = c.length, i = 0; i < length; ++i) {
            g = c[i];
            g.b(sqLiteDatabase);
            g.a(sqLiteDatabase);
        }
    }
    
    public void onOpen(final SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
        if (!sqLiteDatabase.isReadOnly()) {
            sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        if (n == 2 && n2 >= 3) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS crashes");
        }
        if (n <= 3 && n2 >= 4) {
            final b i = c.i;
            sqLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN " + i.b + " " + i.c + " DEFAULT 0");
        }
    }
}
