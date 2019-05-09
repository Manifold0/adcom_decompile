// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.content.SharedPreferences;
import java.util.LinkedList;
import android.database.MatrixCursor;
import android.database.Cursor;
import android.content.ContentValues;
import android.net.Uri;
import android.content.ContentProvider;

public abstract class SharedSecretProvider extends ContentProvider
{
    public static final String READ_PERMISSION_2 = "com.kongregate.permission.ReadSharedData2";
    public static final String READ_PERMISSION_DEPRECATED = "com.kongregate.permission.ReadSharedData";
    static final String[] SHARED_DATA_COLUMNS;
    
    static {
        SHARED_DATA_COLUMNS = new String[] { "secret", "file_postfix" };
    }
    
    public int delete(final Uri uri, final String s, final String[] array) {
        return 0;
    }
    
    public String getType(final Uri uri) {
        return null;
    }
    
    public Uri insert(final Uri uri, final ContentValues contentValues) {
        return null;
    }
    
    public boolean onCreate() {
        return false;
    }
    
    public Cursor query(final Uri uri, final String[] array, final String s, final String[] array2, final String s2) {
        j.b("query shared secret provider");
        final SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("kongregate_shared_secret", 0);
        final String string = sharedPreferences.getString("secret", (String)null);
        final String string2 = sharedPreferences.getString("file_postfix", (String)null);
        if (StringUtils.a((CharSequence)string) || StringUtils.a((CharSequence)string)) {
            j.b("provider does not have shared secret");
            return null;
        }
        final MatrixCursor matrixCursor = new MatrixCursor(SharedSecretProvider.SHARED_DATA_COLUMNS, 1);
        final LinkedList<String> list = new LinkedList<String>();
        list.add(string);
        list.add(string2);
        matrixCursor.addRow((Iterable)list);
        return (Cursor)matrixCursor;
    }
    
    public int update(final Uri uri, final ContentValues contentValues, final String s, final String[] array) {
        return 0;
    }
}
