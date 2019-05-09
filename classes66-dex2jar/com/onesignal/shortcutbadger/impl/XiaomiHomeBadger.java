// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.impl;

import java.util.Arrays;
import java.util.List;
import java.io.Serializable;
import java.lang.reflect.Field;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import android.os.Build;
import android.content.ComponentName;
import android.annotation.TargetApi;
import android.app.Notification;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import android.app.Notification$Builder;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.content.pm.ResolveInfo;
import com.onesignal.shortcutbadger.Badger;

@Deprecated
public class XiaomiHomeBadger implements Badger
{
    public static final String EXTRA_UPDATE_APP_COMPONENT_NAME = "android.intent.extra.update_application_component_name";
    public static final String EXTRA_UPDATE_APP_MSG_TEXT = "android.intent.extra.update_application_message_text";
    public static final String INTENT_ACTION = "android.intent.action.APPLICATION_MESSAGE_UPDATE";
    private ResolveInfo resolveInfo;
    
    @TargetApi(16)
    private void tryNewMiuiBadge(final Context context, final int n) throws ShortcutBadgeException {
        if (this.resolveInfo == null) {
            final Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            this.resolveInfo = context.getPackageManager().resolveActivity(intent, 65536);
        }
        if (this.resolveInfo == null) {
            return;
        }
        final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
        final Notification build = new Notification$Builder(context).setContentTitle((CharSequence)"").setContentText((CharSequence)"").setSmallIcon(this.resolveInfo.getIconResource()).build();
        try {
            final Object value = build.getClass().getDeclaredField("extraNotification").get(build);
            value.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(value, n);
            notificationManager.notify(0, build);
        }
        catch (Exception ex) {
            throw new ShortcutBadgeException("not able to set badge", ex);
        }
    }
    
    @Override
    public void executeBadge(final Context context, ComponentName value, final int n) throws ShortcutBadgeException {
        while (true) {
            try {
                final Object instance = Class.forName("android.app.MiuiNotification").newInstance();
                final Field declaredField = instance.getClass().getDeclaredField("messageCount");
                declaredField.setAccessible(true);
                Label_0066: {
                    if (n != 0) {
                        break Label_0066;
                    }
                    Serializable value2 = "";
                    try {
                        declaredField.set(instance, String.valueOf(value2));
                        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
                            this.tryNewMiuiBadge(context, n);
                        }
                        return;
                        value2 = n;
                    }
                    catch (Exception ex) {
                        declaredField.set(instance, n);
                    }
                }
            }
            catch (Exception ex2) {
                final Intent intent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
                intent.putExtra("android.intent.extra.update_application_component_name", value.getPackageName() + "/" + value.getClassName());
                if (n == 0) {
                    value = (ComponentName)"";
                }
                else {
                    value = (ComponentName)Integer.valueOf(n);
                }
                intent.putExtra("android.intent.extra.update_application_message_text", String.valueOf(value));
                if (BroadcastHelper.canResolveBroadcast(context, intent)) {
                    context.sendBroadcast(intent);
                    continue;
                }
                continue;
            }
            break;
        }
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.miui.miuilite", "com.miui.home", "com.miui.miuihome", "com.miui.miuihome2", "com.miui.mihome", "com.miui.mihome2", "com.i.miui.launcher");
    }
}
