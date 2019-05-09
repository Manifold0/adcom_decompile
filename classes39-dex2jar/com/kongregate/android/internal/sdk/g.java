// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import android.util.Log;
import java.io.File;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

class g extends SQLiteOpenHelper
{
    private Context a;
    
    g(final Context a) {
        super(a, "kongregate_sdk", (SQLiteDatabase$CursorFactory)null, 1);
        this.a = a;
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE statistic_cache   (   name TEXT UNIQUE NOT NULL,   min_value INTEGER NOT NULL,   max_value INTEGER NOT NULL,   add_value INTEGER NOT NULL,   replace_value INTEGER NOT NULL   ) ; ");
        this.onUpgrade(sqLiteDatabase, 1, 1);
    }
    
    public void onOpen(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.rawQuery("PRAGMA foreign_keys=0", (String[])null).close();
        sqLiteDatabase.rawQuery("PRAGMA journal_mode=truncate", (String[])null).close();
        sqLiteDatabase.rawQuery("PRAGMA journal_size_limit=51200", (String[])null).close();
        sqLiteDatabase.rawQuery("PRAGMA cache_size=" + 512000L / sqLiteDatabase.getPageSize(), (String[])null).close();
        try {
            final File databasePath = this.a.getDatabasePath("kongregate_sdk");
            if (databasePath != null) {
                final File file = new File(databasePath.getAbsolutePath() + "-journal");
                if (file.exists()) {
                    Runtime.getRuntime().exec("chmod 660 " + file.getAbsolutePath()).waitFor();
                    Log.i("KongSDK", "Journal permissions set!");
                }
            }
        }
        catch (Exception ex) {
            Log.w("KongSDK", "Exception while trying to set permissions on DB journal file", (Throwable)ex);
        }
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        if (n == n2) {}
    }
}
