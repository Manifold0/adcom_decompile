// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.app.Notification;
import android.support.v4.app.NotificationCompat$Builder;
import android.graphics.Color;
import android.app.Notification$Style;
import android.app.Notification$BigTextStyle;
import android.app.Notification$Builder;
import android.app.NotificationChannel;
import com.google.android.gms.R$string;
import android.app.NotificationManager;
import com.google.android.gms.common.util.zzq;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.res.Resources;
import java.util.MissingFormatArgumentException;
import org.json.JSONException;
import org.json.JSONArray;
import android.util.Log;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Process;
import android.app.KeyguardManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Bundle;
import android.content.Context;

final class zza
{
    static zza zzhzb;
    private final Context mContext;
    private String zzhzc;
    
    private zza(final Context context) {
        this.mContext = context.getApplicationContext();
    }
    
    private final Bundle zzaui() {
        ApplicationInfo applicationInfo = null;
        while (true) {
            try {
                applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    return applicationInfo.metaData;
                }
                return Bundle.EMPTY;
            }
            catch (PackageManager$NameNotFoundException ex) {
                continue;
            }
            break;
        }
    }
    
    static zza zzdg(final Context context) {
        synchronized (zza.class) {
            if (zza.zzhzb == null) {
                zza.zzhzb = new zza(context);
            }
            return zza.zzhzb;
        }
    }
    
    static boolean zzdh(final Context context) {
        if (((KeyguardManager)context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        final int myPid = Process.myPid();
        final List runningAppProcesses = ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                if (activityManager$RunningAppProcessInfo.pid == myPid) {
                    return activityManager$RunningAppProcessInfo.importance == 100;
                }
            }
        }
        return false;
    }
    
    static String zze(final Bundle bundle, final String s) {
        String s2;
        if ((s2 = bundle.getString(s)) == null) {
            s2 = bundle.getString(s.replace("gcm.n.", "gcm.notification."));
        }
        return s2;
    }
    
    private final String zzf(final Bundle bundle, String s) {
        final String zze = zze(bundle, s);
        if (!TextUtils.isEmpty((CharSequence)zze)) {
            return zze;
        }
        final String value = String.valueOf(s);
        final String value2 = String.valueOf("_loc_key");
        String concat;
        if (value2.length() != 0) {
            concat = value.concat(value2);
        }
        else {
            concat = new String(value);
        }
        final String zze2 = zze(bundle, concat);
        if (TextUtils.isEmpty((CharSequence)zze2)) {
            return null;
        }
        final Resources resources = this.mContext.getResources();
        final int identifier = resources.getIdentifier(zze2, "string", this.mContext.getPackageName());
        if (identifier == 0) {
            final String value3 = String.valueOf(s);
            s = String.valueOf("_loc_key");
            String concat2;
            if (s.length() != 0) {
                concat2 = value3.concat(s);
            }
            else {
                concat2 = new String(value3);
            }
            final String substring = concat2.substring(6);
            Log.w("GcmNotification", new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(zze2).length()).append(substring).append(" resource not found: ").append(zze2).append(" Default value will be used.").toString());
            return null;
        }
        final String value4 = String.valueOf(s);
        final String value5 = String.valueOf("_loc_args");
        String concat3;
        if (value5.length() != 0) {
            concat3 = value4.concat(value5);
        }
        else {
            concat3 = new String(value4);
        }
        final String zze3 = zze(bundle, concat3);
        if (TextUtils.isEmpty((CharSequence)zze3)) {
            return resources.getString(identifier);
        }
        try {
            final JSONArray jsonArray = new JSONArray(zze3);
            final String[] array = new String[jsonArray.length()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = (String)jsonArray.opt(i);
            }
            return resources.getString(identifier, (Object[])array);
        }
        catch (JSONException ex2) {
            final String value6 = String.valueOf(s);
            s = String.valueOf("_loc_args");
            if (s.length() == 0) {
                goto Label_0428;
            }
            final String substring2 = value6.concat(s).substring(6);
            Log.w("GcmNotification", new StringBuilder(String.valueOf(substring2).length() + 41 + String.valueOf(zze3).length()).append("Malformed ").append(substring2).append(": ").append(zze3).append("  Default value will be used.").toString());
        }
        catch (MissingFormatArgumentException ex) {
            Log.w("GcmNotification", new StringBuilder(String.valueOf(zze2).length() + 58 + String.valueOf(zze3).length()).append("Missing format argument for ").append(zze2).append(": ").append(zze3).append(" Default value will be used.").toString(), (Throwable)ex);
            goto Label_0426;
        }
    }
    
    static void zzr(final Bundle bundle) {
        final Bundle bundle2 = new Bundle();
        final Iterator iterator = bundle.keySet().iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next();
            final String string = bundle.getString(s);
            String replace = s;
            if (s.startsWith("gcm.notification.")) {
                replace = s.replace("gcm.notification.", "gcm.n.");
            }
            if (replace.startsWith("gcm.n.")) {
                if (!"gcm.n.e".equals(replace)) {
                    bundle2.putString(replace.substring(6), string);
                }
                iterator.remove();
            }
        }
        final String string2 = bundle2.getString("sound2");
        if (string2 != null) {
            bundle2.remove("sound2");
            bundle2.putString("sound", string2);
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle("notification", bundle2);
        }
    }
    
    private final PendingIntent zzt(final Bundle bundle) {
        final String zze = zze(bundle, "gcm.n.click_action");
        Intent launchIntentForPackage;
        if (!TextUtils.isEmpty((CharSequence)zze)) {
            launchIntentForPackage = new Intent(zze);
            launchIntentForPackage.setPackage(this.mContext.getPackageName());
            launchIntentForPackage.setFlags(268435456);
        }
        else {
            launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
            if (launchIntentForPackage == null) {
                Log.w("GcmNotification", "No activity found to launch app");
                return null;
            }
        }
        final Bundle bundle2 = new Bundle(bundle);
        GcmListenerService.zzq(bundle2);
        launchIntentForPackage.putExtras(bundle2);
        for (final String s : bundle2.keySet()) {
            if (s.startsWith("gcm.n.") || s.startsWith("gcm.notification.")) {
                launchIntentForPackage.removeExtra(s);
            }
        }
        return PendingIntent.getActivity(this.mContext, (int)SystemClock.uptimeMillis(), launchIntentForPackage, 1073741824);
    }
    
    final boolean zzs(final Bundle bundle) {
        String channelId = null;
        CharSequence charSequence = this.zzf(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager());
        }
        final String zzf = this.zzf(bundle, "gcm.n.body");
        final String zze = zze(bundle, "gcm.n.icon");
        while (true) {
            Label_0468: {
                if (TextUtils.isEmpty((CharSequence)zze)) {
                    break Label_0468;
                }
                final Resources resources = this.mContext.getResources();
                int n = resources.getIdentifier(zze, "drawable", this.mContext.getPackageName());
                if (n == 0) {
                    n = resources.getIdentifier(zze, "mipmap", this.mContext.getPackageName());
                    if (n == 0) {
                        Log.w("GcmNotification", new StringBuilder(String.valueOf(zze).length() + 57).append("Icon resource ").append(zze).append(" not found. Notification will use app icon.").toString());
                        break Label_0468;
                    }
                }
                final String zze2 = zze(bundle, "gcm.n.color");
                final String zze3 = zze(bundle, "gcm.n.sound2");
                Uri uri;
                if (TextUtils.isEmpty((CharSequence)zze3)) {
                    uri = null;
                }
                else if (!"default".equals(zze3) && this.mContext.getResources().getIdentifier(zze3, "raw", this.mContext.getPackageName()) != 0) {
                    final String packageName = this.mContext.getPackageName();
                    uri = Uri.parse(new StringBuilder(String.valueOf("android.resource://").length() + 5 + String.valueOf(packageName).length() + String.valueOf(zze3).length()).append("android.resource://").append(packageName).append("/raw/").append(zze3).toString());
                }
                else {
                    uri = RingtoneManager.getDefaultUri(2);
                }
                final PendingIntent zzt = this.zzt(bundle);
                Notification notification;
                if (zzq.isAtLeastO() && this.mContext.getApplicationInfo().targetSdkVersion > 25) {
                    final String zze4 = zze(bundle, "gcm.n.android_channel_id");
                    Label_0170: {
                        if (zzq.isAtLeastO()) {
                            final NotificationManager notificationManager = (NotificationManager)this.mContext.getSystemService((Class)NotificationManager.class);
                            if (!TextUtils.isEmpty((CharSequence)zze4)) {
                                if (notificationManager.getNotificationChannel(zze4) != null) {
                                    channelId = zze4;
                                    break Label_0170;
                                }
                                Log.w("GcmNotification", new StringBuilder(String.valueOf(zze4).length() + 122).append("Notification Channel requested (").append(zze4).append(") has not been created by the app. Manifest configuration, or default, value will be used.").toString());
                            }
                            if (this.zzhzc != null) {
                                channelId = this.zzhzc;
                            }
                            else {
                                this.zzhzc = this.zzaui().getString("com.google.android.gms.gcm.default_notification_channel_id");
                                if (!TextUtils.isEmpty((CharSequence)this.zzhzc)) {
                                    if (notificationManager.getNotificationChannel(this.zzhzc) != null) {
                                        channelId = this.zzhzc;
                                        break Label_0170;
                                    }
                                    Log.w("GcmNotification", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                                }
                                else {
                                    Log.w("GcmNotification", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                                }
                                if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                                    notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", (CharSequence)this.mContext.getString(R$string.gcm_fallback_notification_channel_label), 3));
                                }
                                this.zzhzc = "fcm_fallback_notification_channel";
                                channelId = this.zzhzc;
                            }
                        }
                    }
                    final Notification$Builder setSmallIcon = new Notification$Builder(this.mContext).setAutoCancel(true).setSmallIcon(n);
                    if (!TextUtils.isEmpty(charSequence)) {
                        setSmallIcon.setContentTitle(charSequence);
                    }
                    if (!TextUtils.isEmpty((CharSequence)zzf)) {
                        setSmallIcon.setContentText((CharSequence)zzf);
                        setSmallIcon.setStyle((Notification$Style)new Notification$BigTextStyle().bigText((CharSequence)zzf));
                    }
                    if (!TextUtils.isEmpty((CharSequence)zze2)) {
                        setSmallIcon.setColor(Color.parseColor(zze2));
                    }
                    if (uri != null) {
                        setSmallIcon.setSound(uri);
                    }
                    if (zzt != null) {
                        setSmallIcon.setContentIntent(zzt);
                    }
                    if (channelId != null) {
                        setSmallIcon.setChannelId(channelId);
                    }
                    notification = setSmallIcon.build();
                }
                else {
                    final NotificationCompat$Builder setSmallIcon2 = new NotificationCompat$Builder(this.mContext).setAutoCancel(true).setSmallIcon(n);
                    if (!TextUtils.isEmpty(charSequence)) {
                        setSmallIcon2.setContentTitle(charSequence);
                    }
                    if (!TextUtils.isEmpty((CharSequence)zzf)) {
                        setSmallIcon2.setContentText((CharSequence)zzf);
                    }
                    if (!TextUtils.isEmpty((CharSequence)zze2)) {
                        setSmallIcon2.setColor(Color.parseColor(zze2));
                    }
                    if (uri != null) {
                        setSmallIcon2.setSound(uri);
                    }
                    if (zzt != null) {
                        setSmallIcon2.setContentIntent(zzt);
                    }
                    notification = setSmallIcon2.build();
                }
                final String zze5 = zze(bundle, "gcm.n.tag");
                if (Log.isLoggable("GcmNotification", 3)) {
                    Log.d("GcmNotification", "Showing notification");
                }
                final NotificationManager notificationManager2 = (NotificationManager)this.mContext.getSystemService("notification");
                String string = zze5;
                if (TextUtils.isEmpty((CharSequence)zze5)) {
                    string = new StringBuilder(37).append("GCM-Notification:").append(SystemClock.uptimeMillis()).toString();
                }
                notificationManager2.notify(string, 0, notification);
                return true;
            }
            int n;
            if ((n = this.mContext.getApplicationInfo().icon) == 0) {
                n = 17301651;
            }
            continue;
        }
    }
}
