// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.impl;

import java.util.Arrays;
import java.util.List;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import android.net.Uri;
import android.content.ContentValues;
import android.content.ComponentName;
import android.content.Context;
import com.onesignal.shortcutbadger.Badger;

public class EverythingMeHomeBadger implements Badger
{
    private static final String COLUMN_ACTIVITY_NAME = "activity_name";
    private static final String COLUMN_COUNT = "count";
    private static final String COLUMN_PACKAGE_NAME = "package_name";
    private static final String CONTENT_URI = "content://me.everything.badger/apps";
    
    @Override
    public void executeBadge(final Context context, final ComponentName componentName, final int n) throws ShortcutBadgeException {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("package_name", componentName.getPackageName());
        contentValues.put("activity_name", componentName.getClassName());
        contentValues.put("count", Integer.valueOf(n));
        context.getContentResolver().insert(Uri.parse("content://me.everything.badger/apps"), contentValues);
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("me.everything.launcher");
    }
}
