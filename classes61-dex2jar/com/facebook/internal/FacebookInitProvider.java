// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.database.Cursor;
import android.util.Log;
import com.facebook.FacebookSdk;
import android.content.ContentValues;
import android.net.Uri;
import android.content.ContentProvider;

public final class FacebookInitProvider extends ContentProvider
{
    private static final String TAG;
    
    static {
        TAG = FacebookInitProvider.class.getSimpleName();
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
        try {
            FacebookSdk.sdkInitialize(this.getContext());
            return false;
        }
        catch (Exception ex) {
            Log.i(FacebookInitProvider.TAG, "Failed to auto initialize the Facebook SDK", (Throwable)ex);
            return false;
        }
    }
    
    public Cursor query(final Uri uri, final String[] array, final String s, final String[] array2, final String s2) {
        return null;
    }
    
    public int update(final Uri uri, final ContentValues contentValues, final String s, final String[] array) {
        return 0;
    }
}
