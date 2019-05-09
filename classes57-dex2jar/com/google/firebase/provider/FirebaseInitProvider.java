// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.provider;

import android.database.Cursor;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import android.content.ContentValues;
import android.support.annotation.Nullable;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import android.content.pm.ProviderInfo;
import android.content.Context;
import com.google.firebase.annotations.PublicApi;
import android.content.ContentProvider;

@PublicApi
public class FirebaseInitProvider extends ContentProvider
{
    public void attachInfo(final Context context, final ProviderInfo providerInfo) {
        Preconditions.checkNotNull((Object)providerInfo, (Object)"FirebaseInitProvider ProviderInfo cannot be null.");
        if ("com.google.firebase.firebaseinitprovider".equals(providerInfo.authority)) {
            throw new IllegalStateException("Incorrect provider authority in manifest. Most likely due to a missing applicationId variable in application's build.gradle.");
        }
        super.attachInfo(context, providerInfo);
    }
    
    public int delete(final Uri uri, final String s, final String[] array) {
        return 0;
    }
    
    @Nullable
    public String getType(final Uri uri) {
        return null;
    }
    
    @Nullable
    public Uri insert(final Uri uri, final ContentValues contentValues) {
        return null;
    }
    
    public boolean onCreate() {
        if (FirebaseApp.initializeApp(this.getContext()) == null) {
            Log.i("FirebaseInitProvider", "FirebaseApp initialization unsuccessful");
        }
        else {
            Log.i("FirebaseInitProvider", "FirebaseApp initialization successful");
        }
        return false;
    }
    
    @Nullable
    public Cursor query(final Uri uri, final String[] array, final String s, final String[] array2, final String s2) {
        return null;
    }
    
    public int update(final Uri uri, final ContentValues contentValues, final String s, final String[] array) {
        return 0;
    }
}
