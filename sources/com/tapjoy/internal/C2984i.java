package com.tapjoy.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.util.NoSuchElementException;

/* renamed from: com.tapjoy.internal.i */
public final class C2984i extends ay implements bc, Closeable {
    /* renamed from: a */
    private SQLiteDatabase f8197a;
    /* renamed from: b */
    private final bi f8198b;
    /* renamed from: c */
    private int f8199c;

    public C2984i(File file, bi biVar) {
        this.f8197a = SQLiteDatabase.openOrCreateDatabase(file, null);
        this.f8198b = biVar;
        if (this.f8197a.getVersion() != 1) {
            this.f8197a.beginTransaction();
            try {
                this.f8197a.execSQL("CREATE TABLE IF NOT EXISTS List(value BLOB)");
                this.f8197a.setVersion(1);
                this.f8197a.setTransactionSuccessful();
            } finally {
                this.f8197a.endTransaction();
            }
        }
        this.f8199c = m8154a();
    }

    protected final void finalize() {
        close();
        super.finalize();
    }

    public final void close() {
        if (this.f8197a != null) {
            this.f8197a.close();
            this.f8197a = null;
        }
    }

    /* renamed from: a */
    private int m8154a() {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = this.f8197a.rawQuery("SELECT COUNT(1) FROM List", null);
            if (cursor.moveToNext()) {
                i = cursor.getInt(0);
            } else {
                C2984i.m8155a(cursor);
            }
            return i;
        } finally {
            C2984i.m8155a(cursor);
        }
    }

    public final int size() {
        return this.f8199c;
    }

    public final void clear() {
        this.f8197a.delete("List", "1", null);
        this.f8199c = 0;
    }

    public final boolean offer(Object element) {
        cs.m7336a(element);
        Closeable byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.f8198b.mo6321a(byteArrayOutputStream, element);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            dc.m7357a(byteArrayOutputStream);
            ContentValues contentValues = new ContentValues();
            contentValues.put("value", toByteArray);
            if (this.f8197a.insert("List", null, contentValues) == -1) {
                return false;
            }
            this.f8199c++;
            return true;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        } catch (Throwable th) {
            dc.m7357a(byteArrayOutputStream);
        }
    }

    public final Object poll() {
        if (this.f8199c <= 0) {
            return null;
        }
        Object peek = peek();
        mo6183b(1);
        return peek;
    }

    public final Object peek() {
        if (this.f8199c > 0) {
            return mo6182a(0);
        }
        return null;
    }

    /* renamed from: a */
    public final Object mo6182a(int i) {
        Cursor cursor = null;
        if (i < 0 || i >= this.f8199c) {
            throw new IndexOutOfBoundsException();
        }
        try {
            cursor = this.f8197a.rawQuery("SELECT value FROM List ORDER BY rowid LIMIT " + i + ",1", null);
            if (cursor.moveToNext()) {
                Closeable byteArrayInputStream = new ByteArrayInputStream(cursor.getBlob(0));
                try {
                    Object b = this.f8198b.mo6322b(byteArrayInputStream);
                    dc.m7357a(byteArrayInputStream);
                    return b;
                } catch (Throwable e) {
                    throw new IllegalStateException(e);
                } catch (Throwable th) {
                    dc.m7357a(byteArrayInputStream);
                }
            } else {
                throw new NoSuchElementException();
            }
        } finally {
            C2984i.m8155a(cursor);
        }
    }

    /* renamed from: b */
    public final void mo6183b(int i) {
        Throwable th;
        Cursor cursor = null;
        if (i <= 0 || i > this.f8199c) {
            throw new IndexOutOfBoundsException();
        } else if (i == this.f8199c) {
            clear();
        } else {
            try {
                Cursor rawQuery = this.f8197a.rawQuery("SELECT rowid FROM List ORDER BY rowid LIMIT " + (i - 1) + ",1", null);
                try {
                    if (rawQuery.moveToNext()) {
                        long j = rawQuery.getLong(0);
                        rawQuery.close();
                        int delete = this.f8197a.delete("List", "rowid <= " + j, null);
                        this.f8199c -= delete;
                        if (delete != i) {
                            throw new IllegalStateException("Try to delete " + i + ", but deleted " + delete);
                        }
                        C2984i.m8155a(null);
                        return;
                    }
                    throw new IllegalStateException();
                } catch (Throwable th2) {
                    th = th2;
                    cursor = rawQuery;
                    C2984i.m8155a(cursor);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                C2984i.m8155a(cursor);
                throw th;
            }
        }
    }

    /* renamed from: a */
    private static Cursor m8155a(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}
