// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.impl;

import java.util.Arrays;
import java.util.List;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import android.content.Intent;
import android.content.ComponentName;
import android.content.Context;
import com.onesignal.shortcutbadger.Badger;

@Deprecated
public class LGHomeBadger implements Badger
{
    private static final String INTENT_ACTION = "android.intent.action.BADGE_COUNT_UPDATE";
    private static final String INTENT_EXTRA_ACTIVITY_NAME = "badge_count_class_name";
    private static final String INTENT_EXTRA_BADGE_COUNT = "badge_count";
    private static final String INTENT_EXTRA_PACKAGENAME = "badge_count_package_name";
    
    @Override
    public void executeBadge(final Context context, final ComponentName componentName, final int n) throws ShortcutBadgeException {
        final Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", n);
        intent.putExtra("badge_count_package_name", componentName.getPackageName());
        intent.putExtra("badge_count_class_name", componentName.getClassName());
        if (BroadcastHelper.canResolveBroadcast(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.lge.launcher", "com.lge.launcher2");
    }
}
