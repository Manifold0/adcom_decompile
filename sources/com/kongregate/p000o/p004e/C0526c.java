package com.kongregate.p000o.p004e;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.p000o.p008f.C0638a;
import java.io.File;

/* renamed from: com.kongregate.o.e.c */
public class C0526c extends SQLiteOpenHelper {
    /* renamed from: a */
    protected final Context f667a;
    /* renamed from: b */
    protected final String f668b;
    /* renamed from: c */
    protected final int f669c;

    public C0526c(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.f667a = context;
        this.f668b = str;
        this.f669c = i;
    }

    public synchronized SQLiteDatabase getReadableDatabase() throws C0638a {
        SQLiteDatabase readableDatabase;
        try {
            readableDatabase = super.getReadableDatabase();
            if (readableDatabase == null) {
                throw new C0638a("Returned database was null!");
            }
        } catch (Throwable e) {
            C0562j.m762d("Couldn't open database!", e);
            throw new C0638a(e);
        }
        return readableDatabase;
    }

    public synchronized SQLiteDatabase getWritableDatabase() throws C0638a {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = super.getWritableDatabase();
            if (writableDatabase == null) {
                throw new C0638a("Returned database was null!");
            }
        } catch (Throwable e) {
            C0562j.m762d("Couldn't open database!", e);
            throw new C0638a(e);
        }
        return writableDatabase;
    }

    public synchronized void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        C0562j.m753a("Database opened, version=" + sQLiteDatabase.getVersion());
        C0637b.m1046a(sQLiteDatabase);
        if (!sQLiteDatabase.isReadOnly()) {
            C0637b.m1049b(sQLiteDatabase);
        }
        try {
            File databasePath = this.f667a.getDatabasePath(this.f668b);
            if (databasePath != null) {
                File file = new File(databasePath.getAbsolutePath() + "-journal");
                if (file.exists()) {
                    Runtime.getRuntime().exec("chmod 660 " + file.getAbsolutePath()).waitFor();
                    C0562j.m756b("Journal permissions set!");
                }
            }
        } catch (Throwable e) {
            C0562j.m760c("Exception while trying to set permissions on DB journal file", e);
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        C0562j.m756b("Creating database (" + this.f668b + ")");
        mo1229a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, this.f669c);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C0562j.m756b("Upgrading database from version " + i + " to " + i2);
        if (i2 < i) {
            C0562j.m759c("New database version < oldVersion (" + i2 + "<" + i + ") -- performing DB reset");
            C0636a.m1041a(this, sQLiteDatabase);
            i = 1;
        }
        mo1230a(sQLiteDatabase, i, i2);
        C0637b.m1050c(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C0562j.m759c("Performing database downgrade (" + i2 + "<" + i + ") -- performing DB reset");
        C0636a.m1041a(this, sQLiteDatabase);
        mo1230a(sQLiteDatabase, 1, i2);
    }

    /* renamed from: a */
    protected void mo1230a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    /* renamed from: a */
    protected void mo1229a(SQLiteDatabase sQLiteDatabase) {
    }
}
