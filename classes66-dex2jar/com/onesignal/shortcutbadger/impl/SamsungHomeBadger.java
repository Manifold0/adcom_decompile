// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.impl;

import java.util.Arrays;
import java.util.List;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import android.content.ContentResolver;
import android.database.Cursor;
import com.onesignal.shortcutbadger.util.CloseHelper;
import android.net.Uri;
import android.content.Context;
import android.content.ContentValues;
import android.content.ComponentName;
import android.os.Build$VERSION;
import com.onesignal.shortcutbadger.Badger;

public class SamsungHomeBadger implements Badger
{
    private static final String[] CONTENT_PROJECTION;
    private static final String CONTENT_URI = "content://com.sec.badge/apps?notify=true";
    private DefaultBadger defaultBadger;
    
    static {
        CONTENT_PROJECTION = new String[] { "_id", "class" };
    }
    
    public SamsungHomeBadger() {
        if (Build$VERSION.SDK_INT >= 21) {
            this.defaultBadger = new DefaultBadger();
        }
    }
    
    private ContentValues getContentValues(final ComponentName componentName, final int n, final boolean b) {
        final ContentValues contentValues = new ContentValues();
        if (b) {
            contentValues.put("package", componentName.getPackageName());
            contentValues.put("class", componentName.getClassName());
        }
        contentValues.put("badgecount", Integer.valueOf(n));
        return contentValues;
    }
    
    @Override
    public void executeBadge(Context context, final ComponentName componentName, final int n) throws ShortcutBadgeException {
        if (this.defaultBadger != null && this.defaultBadger.isSupported(context)) {
            this.defaultBadger.executeBadge(context, componentName, n);
            return;
        }
        final Uri parse = Uri.parse("content://com.sec.badge/apps?notify=true");
        final ContentResolver contentResolver = context.getContentResolver();
        context = null;
        try {
            final Object query = contentResolver.query(parse, SamsungHomeBadger.CONTENT_PROJECTION, "package=?", new String[] { componentName.getPackageName() }, (String)null);
            if (query != null) {
                context = (Context)query;
                final String className = componentName.getClassName();
                boolean b = false;
                while (true) {
                    context = (Context)query;
                    if (!((Cursor)query).moveToNext()) {
                        break;
                    }
                    context = (Context)query;
                    final int int1 = ((Cursor)query).getInt(0);
                    context = (Context)query;
                    contentResolver.update(parse, this.getContentValues(componentName, n, false), "_id=?", new String[] { String.valueOf(int1) });
                    context = (Context)query;
                    if (!className.equals(((Cursor)query).getString(((Cursor)query).getColumnIndex("class")))) {
                        continue;
                    }
                    b = true;
                }
                if (!b) {
                    context = (Context)query;
                    contentResolver.insert(parse, this.getContentValues(componentName, n, true));
                }
            }
        }
        finally {
            CloseHelper.close((Cursor)context);
        }
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.sec.android.app.launcher", "com.sec.android.app.twlauncher");
    }
}
