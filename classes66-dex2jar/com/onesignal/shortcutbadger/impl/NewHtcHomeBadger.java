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

public class NewHtcHomeBadger implements Badger
{
    public static final String COUNT = "count";
    public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
    public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";
    public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
    public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
    public static final String PACKAGENAME = "packagename";
    
    @Override
    public void executeBadge(final Context context, final ComponentName componentName, final int n) throws ShortcutBadgeException {
        final Intent intent = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
        intent.putExtra("com.htc.launcher.extra.COMPONENT", componentName.flattenToShortString());
        intent.putExtra("com.htc.launcher.extra.COUNT", n);
        final Intent intent2 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
        intent2.putExtra("packagename", componentName.getPackageName());
        intent2.putExtra("count", n);
        if (BroadcastHelper.canResolveBroadcast(context, intent) || BroadcastHelper.canResolveBroadcast(context, intent2)) {
            context.sendBroadcast(intent);
            context.sendBroadcast(intent2);
            return;
        }
        throw new ShortcutBadgeException("unable to resolve intent: " + intent2.toString());
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.htc.launcher");
    }
}
