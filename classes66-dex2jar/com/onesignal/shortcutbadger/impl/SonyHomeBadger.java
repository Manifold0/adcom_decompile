// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.impl;

import java.util.Arrays;
import java.util.List;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import android.content.ContentResolver;
import android.os.Looper;
import android.content.Intent;
import android.content.Context;
import android.content.ContentValues;
import android.content.ComponentName;
import android.content.AsyncQueryHandler;
import android.net.Uri;
import com.onesignal.shortcutbadger.Badger;

public class SonyHomeBadger implements Badger
{
    private static final String INTENT_ACTION = "com.sonyericsson.home.action.UPDATE_BADGE";
    private static final String INTENT_EXTRA_ACTIVITY_NAME = "com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME";
    private static final String INTENT_EXTRA_MESSAGE = "com.sonyericsson.home.intent.extra.badge.MESSAGE";
    private static final String INTENT_EXTRA_PACKAGE_NAME = "com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME";
    private static final String INTENT_EXTRA_SHOW_MESSAGE = "com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE";
    private static final String PROVIDER_COLUMNS_ACTIVITY_NAME = "activity_name";
    private static final String PROVIDER_COLUMNS_BADGE_COUNT = "badge_count";
    private static final String PROVIDER_COLUMNS_PACKAGE_NAME = "package_name";
    private static final String PROVIDER_CONTENT_URI = "content://com.sonymobile.home.resourceprovider/badge";
    private static final String SONY_HOME_PROVIDER_NAME = "com.sonymobile.home.resourceprovider";
    private final Uri BADGE_CONTENT_URI;
    private AsyncQueryHandler mQueryHandler;
    
    public SonyHomeBadger() {
        this.BADGE_CONTENT_URI = Uri.parse("content://com.sonymobile.home.resourceprovider/badge");
    }
    
    private ContentValues createContentValues(final int n, final ComponentName componentName) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("badge_count", Integer.valueOf(n));
        contentValues.put("package_name", componentName.getPackageName());
        contentValues.put("activity_name", componentName.getClassName());
        return contentValues;
    }
    
    private static void executeBadgeByBroadcast(final Context context, final ComponentName componentName, final int n) {
        final Intent intent = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", componentName.getPackageName());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", componentName.getClassName());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(n));
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", n > 0);
        context.sendBroadcast(intent);
    }
    
    private void executeBadgeByContentProvider(final Context context, final ComponentName componentName, final int n) {
        if (n < 0) {
            return;
        }
        final ContentValues contentValues = this.createContentValues(n, componentName);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            if (this.mQueryHandler == null) {
                this.mQueryHandler = new AsyncQueryHandler(context.getApplicationContext().getContentResolver()) {};
            }
            this.insertBadgeAsync(contentValues);
            return;
        }
        this.insertBadgeSync(context, contentValues);
    }
    
    private void insertBadgeAsync(final ContentValues contentValues) {
        this.mQueryHandler.startInsert(0, (Object)null, this.BADGE_CONTENT_URI, contentValues);
    }
    
    private void insertBadgeSync(final Context context, final ContentValues contentValues) {
        context.getApplicationContext().getContentResolver().insert(this.BADGE_CONTENT_URI, contentValues);
    }
    
    private static boolean sonyBadgeContentProviderExists(final Context context) {
        boolean b = false;
        if (context.getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) != null) {
            b = true;
        }
        return b;
    }
    
    @Override
    public void executeBadge(final Context context, final ComponentName componentName, final int n) throws ShortcutBadgeException {
        if (sonyBadgeContentProviderExists(context)) {
            this.executeBadgeByContentProvider(context, componentName, n);
            return;
        }
        executeBadgeByBroadcast(context, componentName, n);
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.sonyericsson.home", "com.sonymobile.home");
    }
}
