package com.facebook.ads.internal.p040j;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import com.facebook.ads.internal.p050r.C2078a;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.j.e */
public class C2031e extends SQLiteOpenHelper {
    /* renamed from: a */
    public static final String f4519a = C2031e.class.getSimpleName();
    /* renamed from: b */
    private final C2030d f4520b;

    public C2031e(Context context, C2030d c2030d) {
        String format = String.format(Locale.US, "ads%s.db", new Object[]{""});
        if (C2078a.m5078Q(context)) {
            String packageName = context.getPackageName();
            Object a = C2616a.m6729a(context);
            if (!(packageName.equals(a) || TextUtils.isEmpty(a))) {
                format = String.format(Locale.US, "ads%s.db", new Object[]{'_' + a});
            }
        }
        super(context, format, null, 4);
        if (c2030d == null) {
            throw new IllegalArgumentException("AdDatabaseHelper can not be null");
        }
        this.f4520b = c2030d;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        for (C2024g a : this.f4520b.m4912c()) {
            a.m4882a(sQLiteDatabase);
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        for (C2024g c2024g : this.f4520b.m4912c()) {
            c2024g.m4883b(sQLiteDatabase);
            c2024g.m4882a(sQLiteDatabase);
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 2 && i2 >= 3) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS crashes");
        }
        if (i <= 3 && i2 >= 4) {
            C2023b c2023b = C2025c.f4495i;
            sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN " + c2023b.f4484b + " " + c2023b.f4485c + " DEFAULT 0");
        }
    }
}
