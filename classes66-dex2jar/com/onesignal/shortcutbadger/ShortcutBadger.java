// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger;

import android.content.pm.ResolveInfo;
import java.util.Iterator;
import com.onesignal.shortcutbadger.impl.DefaultBadger;
import android.content.Intent;
import android.os.Build;
import android.app.Notification;
import android.util.Log;
import android.content.Context;
import com.onesignal.shortcutbadger.impl.EverythingMeHomeBadger;
import com.onesignal.shortcutbadger.impl.VivoHomeBadger;
import com.onesignal.shortcutbadger.impl.ZukHomeBadger;
import com.onesignal.shortcutbadger.impl.SamsungHomeBadger;
import com.onesignal.shortcutbadger.impl.OPPOHomeBader;
import com.onesignal.shortcutbadger.impl.HuaweiHomeBadger;
import com.onesignal.shortcutbadger.impl.AsusHomeBadger;
import com.onesignal.shortcutbadger.impl.SonyHomeBadger;
import com.onesignal.shortcutbadger.impl.NovaHomeBadger;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import com.onesignal.shortcutbadger.impl.ApexHomeBadger;
import com.onesignal.shortcutbadger.impl.AdwHomeBadger;
import java.util.LinkedList;
import android.content.ComponentName;
import java.util.List;

public final class ShortcutBadger
{
    private static final List<Class<? extends Badger>> BADGERS;
    private static final String LOG_TAG = "ShortcutBadger";
    private static final int SUPPORTED_CHECK_ATTEMPTS = 3;
    private static ComponentName sComponentName;
    private static final Object sCounterSupportedLock;
    private static volatile Boolean sIsBadgeCounterSupported;
    private static Badger sShortcutBadger;
    
    static {
        BADGERS = new LinkedList<Class<? extends Badger>>();
        sCounterSupportedLock = new Object();
        ShortcutBadger.BADGERS.add(AdwHomeBadger.class);
        ShortcutBadger.BADGERS.add(ApexHomeBadger.class);
        ShortcutBadger.BADGERS.add(NewHtcHomeBadger.class);
        ShortcutBadger.BADGERS.add(NovaHomeBadger.class);
        ShortcutBadger.BADGERS.add(SonyHomeBadger.class);
        ShortcutBadger.BADGERS.add(AsusHomeBadger.class);
        ShortcutBadger.BADGERS.add(HuaweiHomeBadger.class);
        ShortcutBadger.BADGERS.add(OPPOHomeBader.class);
        ShortcutBadger.BADGERS.add(SamsungHomeBadger.class);
        ShortcutBadger.BADGERS.add(ZukHomeBadger.class);
        ShortcutBadger.BADGERS.add(VivoHomeBadger.class);
        ShortcutBadger.BADGERS.add(EverythingMeHomeBadger.class);
    }
    
    private ShortcutBadger() {
    }
    
    public static boolean applyCount(final Context context, final int n) {
        try {
            applyCountOrThrow(context, n);
            return true;
        }
        catch (ShortcutBadgeException ex) {
            if (Log.isLoggable("ShortcutBadger", 3)) {
                Log.d("ShortcutBadger", "Unable to execute badge", (Throwable)ex);
            }
            return false;
        }
    }
    
    public static void applyCountOrThrow(final Context context, final int n) throws ShortcutBadgeException {
        if (ShortcutBadger.sShortcutBadger == null && !initBadger(context)) {
            throw new ShortcutBadgeException("No default launcher available");
        }
        try {
            ShortcutBadger.sShortcutBadger.executeBadge(context, ShortcutBadger.sComponentName, n);
        }
        catch (Exception ex) {
            throw new ShortcutBadgeException("Unable to execute badge", ex);
        }
    }
    
    public static void applyNotification(final Context context, final Notification notification, final int n) {
        if (!Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            return;
        }
        try {
            final Object value = notification.getClass().getDeclaredField("extraNotification").get(notification);
            value.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(value, n);
        }
        catch (Exception ex) {
            if (Log.isLoggable("ShortcutBadger", 3)) {
                Log.d("ShortcutBadger", "Unable to execute badge", (Throwable)ex);
            }
        }
    }
    
    private static boolean initBadger(Context resolveActivity) {
        final Intent launchIntentForPackage = resolveActivity.getPackageManager().getLaunchIntentForPackage(resolveActivity.getPackageName());
        if (launchIntentForPackage == null) {
            Log.e("ShortcutBadger", "Unable to find launch intent for package " + resolveActivity.getPackageName());
        }
        else {
            ShortcutBadger.sComponentName = launchIntentForPackage.getComponent();
            final Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            resolveActivity = (Context)resolveActivity.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity != null && !((ResolveInfo)resolveActivity).activityInfo.name.toLowerCase().contains("resolver")) {
                final String packageName = ((ResolveInfo)resolveActivity).activityInfo.packageName;
                final Iterator<Class<Badger>> iterator = (Iterator<Class<Badger>>)ShortcutBadger.BADGERS.iterator();
            Label_0151_Outer:
                while (true) {
                    Label_0174: {
                        if (!iterator.hasNext()) {
                            break Label_0174;
                        }
                        final Class<Badger> clazz = iterator.next();
                        resolveActivity = null;
                        while (true) {
                            try {
                                resolveActivity = (Context)clazz.newInstance();
                                if (resolveActivity != null && ((Badger)resolveActivity).getSupportLaunchers().contains(packageName)) {
                                    ShortcutBadger.sShortcutBadger = (Badger)resolveActivity;
                                    if (ShortcutBadger.sShortcutBadger == null) {
                                        if (Build.MANUFACTURER.equalsIgnoreCase("ZUK")) {
                                            ShortcutBadger.sShortcutBadger = new ZukHomeBadger();
                                        }
                                        else if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
                                            ShortcutBadger.sShortcutBadger = new OPPOHomeBader();
                                        }
                                        else if (Build.MANUFACTURER.equalsIgnoreCase("VIVO")) {
                                            ShortcutBadger.sShortcutBadger = new VivoHomeBadger();
                                        }
                                        else {
                                            ShortcutBadger.sShortcutBadger = new DefaultBadger();
                                        }
                                    }
                                    return true;
                                }
                                continue Label_0151_Outer;
                            }
                            catch (Exception ex) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isBadgeCounterSupported(final Context context) {
        Label_0157: {
            if (ShortcutBadger.sIsBadgeCounterSupported != null) {
                break Label_0157;
            }
            synchronized (ShortcutBadger.sCounterSupportedLock) {
                Label_0155: {
                    if (ShortcutBadger.sIsBadgeCounterSupported != null) {
                        break Label_0155;
                    }
                    String message = null;
                    int n = 0;
                    while (true) {
                        Label_0116: {
                            if (n >= 3) {
                                break Label_0116;
                            }
                            try {
                                Log.i("ShortcutBadger", "Checking if platform supports badge counters, attempt " + String.format("%d/%d.", n + 1, 3));
                                if (initBadger(context)) {
                                    ShortcutBadger.sShortcutBadger.executeBadge(context, ShortcutBadger.sComponentName, 0);
                                    ShortcutBadger.sIsBadgeCounterSupported = true;
                                    Log.i("ShortcutBadger", "Badge counter is supported in this platform.");
                                    if (ShortcutBadger.sIsBadgeCounterSupported == null) {
                                        Log.w("ShortcutBadger", "Badge counter seems not supported for this platform: " + message);
                                        ShortcutBadger.sIsBadgeCounterSupported = false;
                                    }
                                    // monitorexit(ShortcutBadger.sCounterSupportedLock)
                                    return ShortcutBadger.sIsBadgeCounterSupported;
                                }
                                message = "Failed to initialize the badge counter.";
                                ++n;
                                continue;
                            }
                            catch (Exception ex) {
                                message = ex.getMessage();
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    
    public static boolean removeCount(final Context context) {
        return applyCount(context, 0);
    }
    
    public static void removeCountOrThrow(final Context context) throws ShortcutBadgeException {
        applyCountOrThrow(context, 0);
    }
}
