// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.impl;

import java.util.Arrays;
import java.util.List;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import android.content.Intent;
import android.content.ComponentName;
import android.content.Context;
import com.onesignal.shortcutbadger.Badger;

public class VivoHomeBadger implements Badger
{
    @Override
    public void executeBadge(final Context context, final ComponentName componentName, final int n) throws ShortcutBadgeException {
        final Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
        intent.putExtra("packageName", context.getPackageName());
        intent.putExtra("className", componentName.getClassName());
        intent.putExtra("notificationNum", n);
        context.sendBroadcast(intent);
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.vivo.launcher");
    }
}
