// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.ContentValues;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import java.io.File;
import android.database.sqlite.SQLiteDatabase;
import java.io.Closeable;

public final class i extends ay implements bc, Closeable
{
    private SQLiteDatabase a;
    private final bi b;
    private int c;
    
    public i(final File file, final bi b) {
        this.a = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase$CursorFactory)null);
        this.b = b;
        Label_0067: {
            if (this.a.getVersion() == 1) {
                break Label_0067;
            }
            this.a.beginTransaction();
            try {
                this.a.execSQL("CREATE TABLE IF NOT EXISTS List(value BLOB)");
                this.a.setVersion(1);
                this.a.setTransactionSuccessful();
                this.a.endTransaction();
                this.c = this.a();
            }
            finally {
                this.a.endTransaction();
            }
        }
    }
    
    private int a() {
        Cursor rawQuery = null;
        try {
            final Cursor cursor = rawQuery = this.a.rawQuery("SELECT COUNT(1) FROM List", (String[])null);
            if (cursor.moveToNext()) {
                rawQuery = cursor;
                return cursor.getInt(0);
            }
            return 0;
        }
        finally {
            a(rawQuery);
        }
    }
    
    private static Cursor a(final Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
    
    @Override
    public final Object a(final int n) {
        Object rawQuery = null;
        if (n < 0 || n >= this.c) {
            throw new IndexOutOfBoundsException();
        }
        try {
            final Throwable t = (Throwable)(rawQuery = this.a.rawQuery("SELECT value FROM List ORDER BY rowid LIMIT " + n + ",1", (String[])null));
            if (((Cursor)t).moveToNext()) {
                rawQuery = t;
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(((Cursor)t).getBlob(0));
                try {
                    final Object b = this.b.b(byteArrayInputStream);
                    rawQuery = t;
                    dc.a(byteArrayInputStream);
                    return b;
                }
                catch (IOException rawQuery) {
                    throw new IllegalStateException((Throwable)rawQuery);
                }
                finally {
                    rawQuery = t;
                    dc.a(byteArrayInputStream);
                    rawQuery = t;
                }
            }
        }
        finally {
            a((Cursor)rawQuery);
        }
        throw new NoSuchElementException();
    }
    
    @Override
    public final void b(final int n) {
        Cursor cursor = null;
        if (n <= 0 || n > this.c) {
            throw new IndexOutOfBoundsException();
        }
        if (n == this.c) {
            this.clear();
            return;
        }
        Label_0181: {
            Cursor cursor2;
            try {
                final Cursor rawQuery;
                cursor2 = (rawQuery = this.a.rawQuery("SELECT rowid FROM List ORDER BY rowid LIMIT " + (n - 1) + ",1", (String[])null));
                final boolean b = rawQuery.moveToNext();
                if (!b) {
                    break Label_0181;
                }
                final Cursor cursor3 = cursor2;
                final int n2 = 0;
                final long n3 = cursor3.getLong(n2);
                final Cursor cursor4 = cursor2;
                cursor4.close();
                final i i = this;
                final SQLiteDatabase sqLiteDatabase = i.a;
                final String s = "List";
                final String s2 = "rowid <= ";
                final StringBuilder sb = new StringBuilder(s2);
                final long n4 = n3;
                final StringBuilder sb2 = sb.append(n4);
                final String s3 = sb2.toString();
                final String[] array = null;
                final int n5 = sqLiteDatabase.delete(s, s3, array);
                final i j = this;
                final i k = this;
                final int n6 = k.c;
                final int n7 = n5;
                final int n8 = n6 - n7;
                j.c = n8;
                final int n9 = n5;
                final int n10 = n;
                if (n9 != n10) {
                    final String s4 = "Try to delete ";
                    final StringBuilder sb3 = new StringBuilder(s4);
                    final int n11 = n;
                    final StringBuilder sb4 = sb3.append(n11);
                    final String s5 = ", but deleted ";
                    final StringBuilder sb5 = sb4.append(s5);
                    final int n12 = n5;
                    final StringBuilder sb6 = sb5.append(n12);
                    final String s6 = sb6.toString();
                    throw new IllegalStateException(s6);
                }
                break Label_0181;
            }
            finally {
                final Cursor cursor5;
                cursor2 = cursor5;
            }
            while (true) {
                try {
                    final Cursor rawQuery = cursor2;
                    final boolean b = rawQuery.moveToNext();
                    if (!b) {
                        throw new IllegalStateException();
                    }
                    final Cursor cursor3 = cursor2;
                    final int n2 = 0;
                    final long n3 = cursor3.getLong(n2);
                    final Cursor cursor4 = cursor2;
                    cursor4.close();
                    final i i = this;
                    final SQLiteDatabase sqLiteDatabase = i.a;
                    final String s = "List";
                    final String s2 = "rowid <= ";
                    final StringBuilder sb = new StringBuilder(s2);
                    final long n4 = n3;
                    final StringBuilder sb2 = sb.append(n4);
                    final String s3 = sb2.toString();
                    final String[] array = null;
                    final int n5 = sqLiteDatabase.delete(s, s3, array);
                    final i j = this;
                    final i k = this;
                    final int n6 = k.c;
                    final int n7 = n5;
                    final int n8 = n6 - n7;
                    j.c = n8;
                    final int n9 = n5;
                    final int n10 = n;
                    if (n9 != n10) {
                        final String s4 = "Try to delete ";
                        final StringBuilder sb3 = new StringBuilder(s4);
                        final int n11 = n;
                        final StringBuilder sb4 = sb3.append(n11);
                        final String s5 = ", but deleted ";
                        final StringBuilder sb5 = sb4.append(s5);
                        final int n12 = n5;
                        final StringBuilder sb6 = sb5.append(n12);
                        final String s6 = sb6.toString();
                        throw new IllegalStateException(s6);
                    }
                    break Label_0181;
                    a(cursor);
                    throw cursor2;
                }
                finally {
                    cursor = cursor2;
                    final Cursor cursor6;
                    cursor2 = cursor6;
                    continue;
                }
                break;
            }
        }
        a(null);
    }
    
    @Override
    public final void clear() {
        this.a.delete("List", "1", (String[])null);
        this.c = 0;
    }
    
    @Override
    public final void close() {
        if (this.a != null) {
            this.a.close();
            this.a = null;
        }
    }
    
    @Override
    protected final void finalize() {
        this.close();
        super.finalize();
    }
    
    @Override
    public final boolean offer(final Object o) {
        cs.a(o);
        Object o2 = new ByteArrayOutputStream();
        try {
            this.b.a((OutputStream)o2, o);
            final byte[] byteArray = ((ByteArrayOutputStream)o2).toByteArray();
            dc.a((Closeable)o2);
            o2 = new ContentValues();
            ((ContentValues)o2).put("value", byteArray);
            if (this.a.insert("List", (String)null, (ContentValues)o2) == -1L) {
                return false;
            }
        }
        catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
        finally {
            dc.a((Closeable)o2);
        }
        ++this.c;
        return true;
    }
    
    @Override
    public final Object peek() {
        if (this.c > 0) {
            return this.a(0);
        }
        return null;
    }
    
    @Override
    public final Object poll() {
        if (this.c > 0) {
            final Object peek = this.peek();
            this.b(1);
            return peek;
        }
        return null;
    }
    
    @Override
    public final int size() {
        return this.c;
    }
}
