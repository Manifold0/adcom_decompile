package com.kongregate.android.internal.sdk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;

/* renamed from: com.kongregate.android.internal.sdk.g */
class C0502g extends SQLiteOpenHelper {
    /* renamed from: a */
    private Context f517a;

    C0502g(Context context) {
        super(context, "kongregate_sdk", null, 1);
        this.f517a = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE statistic_cache   (   name TEXT UNIQUE NOT NULL,   min_value INTEGER NOT NULL,   max_value INTEGER NOT NULL,   add_value INTEGER NOT NULL,   replace_value INTEGER NOT NULL   ) ; ");
        onUpgrade(sQLiteDatabase, 1, 1);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != i2) {
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.rawQuery("PRAGMA foreign_keys=0", null).close();
        sQLiteDatabase.rawQuery("PRAGMA journal_mode=truncate", null).close();
        sQLiteDatabase.rawQuery("PRAGMA journal_size_limit=51200", null).close();
        sQLiteDatabase.rawQuery("PRAGMA cache_size=" + (512000 / sQLiteDatabase.getPageSize()), null).close();
        try {
            File databasePath = this.f517a.getDatabasePath("kongregate_sdk");
            if (databasePath != null) {
                File file = new File(databasePath.getAbsolutePath() + "-journal");
                if (file.exists()) {
                    Runtime.getRuntime().exec("chmod 660 " + file.getAbsolutePath()).waitFor();
                    Log.i(C0498e.f484a, "Journal permissions set!");
                }
            }
        } catch (Throwable e) {
            Log.w(C0498e.f484a, "Exception while trying to set permissions on DB journal file", e);
        }
    }
}
