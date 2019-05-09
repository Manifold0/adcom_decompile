// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.e;

import java.io.File;
import android.database.SQLException;
import com.kongregate.android.internal.util.j;
import com.kongregate.o.f.a;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class c extends SQLiteOpenHelper
{
    protected final Context a;
    protected final String b;
    protected final int c;
    
    public c(final Context a, final String b, final SQLiteDatabase$CursorFactory sqLiteDatabase$CursorFactory, final int c) {
        super(a, b, sqLiteDatabase$CursorFactory, c);
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    protected void a(final SQLiteDatabase sqLiteDatabase) {
    }
    
    protected void a(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
    }
    
    public SQLiteDatabase getReadableDatabase() throws a {
        // monitorenter(this)
        try {
            final SQLiteDatabase readableDatabase = super.getReadableDatabase();
            if (readableDatabase != null) {
                return readableDatabase;
            }
            throw new a("Returned database was null!");
        }
        catch (SQLException ex) {
            try {
                j.d("Couldn't open database!", (Throwable)ex);
                throw new a((Throwable)ex);
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public SQLiteDatabase getWritableDatabase() throws a {
        // monitorenter(this)
        try {
            final SQLiteDatabase writableDatabase = super.getWritableDatabase();
            if (writableDatabase != null) {
                return writableDatabase;
            }
            throw new a("Returned database was null!");
        }
        catch (SQLException ex) {
            try {
                j.d("Couldn't open database!", (Throwable)ex);
                throw new a((Throwable)ex);
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        j.b("Creating database (" + this.b + ")");
        this.a(sqLiteDatabase);
        this.onUpgrade(sqLiteDatabase, 1, this.c);
    }
    
    public void onDowngrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        j.c("Performing database downgrade (" + n2 + "<" + n + ") -- performing DB reset");
        com.kongregate.o.e.a.a(this, sqLiteDatabase);
        this.a(sqLiteDatabase, 1, n2);
    }
    
    public void onOpen(final SQLiteDatabase sqLiteDatabase) {
        synchronized (this) {
            super.onOpen(sqLiteDatabase);
            j.a("Database opened, version=" + sqLiteDatabase.getVersion());
            com.kongregate.o.e.b.a(sqLiteDatabase);
            if (!sqLiteDatabase.isReadOnly()) {
                com.kongregate.o.e.b.b(sqLiteDatabase);
            }
            try {
                final File databasePath = this.a.getDatabasePath(this.b);
                if (databasePath != null) {
                    final File file = new File(databasePath.getAbsolutePath() + "-journal");
                    if (file.exists()) {
                        Runtime.getRuntime().exec("chmod 660 " + file.getAbsolutePath()).waitFor();
                        j.b("Journal permissions set!");
                    }
                }
            }
            catch (Exception ex) {
                j.c("Exception while trying to set permissions on DB journal file", ex);
            }
        }
    }
    
    public final void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        j.b("Upgrading database from version " + n + " to " + n2);
        int n3 = n;
        if (n2 < n) {
            j.c("New database version < oldVersion (" + n2 + "<" + n + ") -- performing DB reset");
            com.kongregate.o.e.a.a(this, sqLiteDatabase);
            n3 = 1;
        }
        this.a(sqLiteDatabase, n3, n2);
        com.kongregate.o.e.b.c(sqLiteDatabase);
    }
}
