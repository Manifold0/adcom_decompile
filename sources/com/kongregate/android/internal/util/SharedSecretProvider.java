package com.kongregate.android.internal.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import java.util.LinkedList;
import net.hockeyapp.android.LoginActivity;

public abstract class SharedSecretProvider extends ContentProvider {
    public static final String READ_PERMISSION_2 = "com.kongregate.permission.ReadSharedData2";
    public static final String READ_PERMISSION_DEPRECATED = "com.kongregate.permission.ReadSharedData";
    static final String[] SHARED_DATA_COLUMNS = new String[]{LoginActivity.EXTRA_SECRET, "file_postfix"};

    public boolean onCreate() {
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        C0562j.m756b("query shared secret provider");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("kongregate_shared_secret", 0);
        CharSequence string = sharedPreferences.getString(LoginActivity.EXTRA_SECRET, null);
        String string2 = sharedPreferences.getString("file_postfix", null);
        if (StringUtils.m580a(string) || StringUtils.m580a(string)) {
            C0562j.m756b("provider does not have shared secret");
            return null;
        }
        Cursor matrixCursor = new MatrixCursor(SHARED_DATA_COLUMNS, 1);
        Iterable linkedList = new LinkedList();
        linkedList.add(string);
        linkedList.add(string2);
        matrixCursor.addRow(linkedList);
        return matrixCursor;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
