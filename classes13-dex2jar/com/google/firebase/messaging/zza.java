// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.messaging;

import android.app.Notification;
import java.util.List;
import android.support.v4.app.NotificationCompat$Style;
import android.support.v4.app.NotificationCompat$BigTextStyle;
import android.support.v4.app.NotificationCompat$Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import com.google.firebase.iid.zzav;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.media.RingtoneManager;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Process;
import com.google.android.gms.common.util.PlatformVersion;
import android.app.KeyguardManager;
import android.support.v4.content.ContextCompat;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.content.res.Resources;
import java.util.MissingFormatArgumentException;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONArray;
import android.text.TextUtils;
import android.annotation.TargetApi;
import android.content.res.Resources$NotFoundException;
import android.util.Log;
import android.content.res.Resources$Theme;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.os.Build$VERSION;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.Iterator;
import android.content.Intent;
import android.os.SystemClock;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicInteger;

final class zza
{
    private static final AtomicInteger zzdn;
    private Bundle zzdo;
    private final Context zzx;
    
    static {
        zzdn = new AtomicInteger((int)SystemClock.elapsedRealtime());
    }
    
    public zza(final Context context) {
        this.zzx = context.getApplicationContext();
    }
    
    static String zza(final Bundle bundle, final String s) {
        String s2;
        if ((s2 = bundle.getString(s)) == null) {
            s2 = bundle.getString(s.replace("gcm.n.", "gcm.notification."));
        }
        return s2;
    }
    
    private static void zza(final Intent intent, final Bundle bundle) {
        for (final String s : bundle.keySet()) {
            if (s.startsWith("google.c.a.") || s.equals("from")) {
                intent.putExtra(s, bundle.getString(s));
            }
        }
    }
    
    private final Bundle zzas() {
        if (this.zzdo != null) {
            return this.zzdo;
        }
        ApplicationInfo applicationInfo = null;
        while (true) {
            try {
                applicationInfo = this.zzx.getPackageManager().getApplicationInfo(this.zzx.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    return this.zzdo = applicationInfo.metaData;
                }
                return Bundle.EMPTY;
            }
            catch (PackageManager$NameNotFoundException ex) {
                continue;
            }
            break;
        }
    }
    
    static String zzb(final Bundle bundle, String s) {
        s = String.valueOf(s);
        final String value = String.valueOf("_loc_key");
        if (value.length() != 0) {
            s = s.concat(value);
        }
        else {
            s = new String(s);
        }
        return zza(bundle, s);
    }
    
    @TargetApi(26)
    private final boolean zzb(final int n) {
        if (Build$VERSION.SDK_INT == 26) {
            try {
                if (this.zzx.getResources().getDrawable(n, (Resources$Theme)null) instanceof AdaptiveIconDrawable) {
                    Log.e("FirebaseMessaging", new StringBuilder(77).append("Adaptive icons cannot be used in notifications. Ignoring icon id: ").append(n).toString());
                    return false;
                }
            }
            catch (Resources$NotFoundException ex) {
                return false;
            }
        }
        return true;
    }
    
    static Object[] zzc(final Bundle bundle, String value) {
        final String value2 = String.valueOf(value);
        final String value3 = String.valueOf("_loc_args");
        String concat;
        if (value3.length() != 0) {
            concat = value2.concat(value3);
        }
        else {
            concat = new String(value2);
        }
        final String zza = zza(bundle, concat);
        Object[] array;
        if (TextUtils.isEmpty((CharSequence)zza)) {
            array = null;
        }
        else {
            try {
                final JSONArray jsonArray = new JSONArray(zza);
                final String[] array2 = new String[jsonArray.length()];
                int n = 0;
                while (true) {
                    array = array2;
                    if (n >= array2.length) {
                        break;
                    }
                    array2[n] = (String)jsonArray.opt(n);
                    ++n;
                }
            }
            catch (JSONException ex) {
                final String value4 = String.valueOf(value);
                value = String.valueOf("_loc_args");
                String concat2;
                if (value.length() != 0) {
                    concat2 = value4.concat(value);
                }
                else {
                    concat2 = new String(value4);
                }
                final String substring = concat2.substring(6);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(substring).length() + 41 + String.valueOf(zza).length()).append("Malformed ").append(substring).append(": ").append(zza).append("  Default value will be used.").toString());
                return null;
            }
        }
        return array;
    }
    
    private final String zzd(Bundle zzc, String s) {
        final String zza = zza(zzc, s);
        if (!TextUtils.isEmpty((CharSequence)zza)) {
            return zza;
        }
        final String zzb = zzb(zzc, s);
        if (TextUtils.isEmpty((CharSequence)zzb)) {
            return null;
        }
        final Resources resources = this.zzx.getResources();
        final int identifier = resources.getIdentifier(zzb, "string", this.zzx.getPackageName());
        if (identifier == 0) {
            final String value = String.valueOf(s);
            s = String.valueOf("_loc_key");
            String concat;
            if (s.length() != 0) {
                concat = value.concat(s);
            }
            else {
                concat = new String(value);
            }
            final String substring = concat.substring(6);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(zzb).length()).append(substring).append(" resource not found: ").append(zzb).append(" Default value will be used.").toString());
            return null;
        }
        zzc = (Bundle)zzc(zzc, s);
        if (zzc == null) {
            return resources.getString(identifier);
        }
        try {
            s = resources.getString(identifier, (Object[])(Object)zzc);
            return s;
        }
        catch (MissingFormatArgumentException ex) {
            final String string = Arrays.toString((Object[])(Object)zzc);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(zzb).length() + 58 + String.valueOf(string).length()).append("Missing format argument for ").append(zzb).append(": ").append(string).append(" Default value will be used.").toString(), (Throwable)ex);
            return null;
        }
    }
    
    static boolean zzf(final Bundle bundle) {
        return "1".equals(zza(bundle, "gcm.n.e")) || zza(bundle, "gcm.n.icon") != null;
    }
    
    @Nullable
    static Uri zzg(@NonNull final Bundle bundle) {
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = zza(bundle, "gcm.n.link_android")))) {
            s = zza(bundle, "gcm.n.link");
        }
        if (!TextUtils.isEmpty((CharSequence)s)) {
            return Uri.parse(s);
        }
        return null;
    }
    
    static String zzi(final Bundle bundle) {
        String s;
        if (TextUtils.isEmpty((CharSequence)(s = zza(bundle, "gcm.n.sound2")))) {
            s = zza(bundle, "gcm.n.sound");
        }
        return s;
    }
    
    private final Integer zzl(final String s) {
        if (Build$VERSION.SDK_INT >= 21) {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                try {
                    return Color.parseColor(s);
                }
                catch (IllegalArgumentException ex) {
                    Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(s).length() + 54).append("Color ").append(s).append(" not valid. Notification will use default color.").toString());
                }
            }
            final int int1 = this.zzas().getInt("com.google.firebase.messaging.default_notification_color", 0);
            if (int1 != 0) {
                try {
                    return ContextCompat.getColor(this.zzx, int1);
                }
                catch (Resources$NotFoundException ex2) {
                    Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
                    return null;
                }
            }
        }
        return null;
    }
    
    final boolean zzh(final Bundle bundle) {
        if ("1".equals(zza(bundle, "gcm.n.noui"))) {
            return true;
        }
    Label_0128:
        while (true) {
            Label_0139: {
                if (((KeyguardManager)this.zzx.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                    break Label_0139;
                }
                if (!PlatformVersion.isAtLeastLollipop()) {
                    SystemClock.sleep(10L);
                }
                final int myPid = Process.myPid();
                final List runningAppProcesses = ((ActivityManager)this.zzx.getSystemService("activity")).getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                        if (activityManager$RunningAppProcessInfo.pid == myPid) {
                            if (activityManager$RunningAppProcessInfo.importance == 100) {
                                final int n = 1;
                                break Label_0128;
                            }
                            final int n = 0;
                            break Label_0128;
                        }
                    }
                }
                break Label_0139;
                int n = 0;
                if (n != 0) {
                    return false;
                }
                CharSequence contentTitle = this.zzd(bundle, "gcm.n.title");
                if (TextUtils.isEmpty(contentTitle)) {
                    contentTitle = this.zzx.getApplicationInfo().loadLabel(this.zzx.getPackageManager());
                }
                final String zzd = this.zzd(bundle, "gcm.n.body");
                final String zza = zza(bundle, "gcm.n.icon");
                while (true) {
                    Label_0783: {
                        if (TextUtils.isEmpty((CharSequence)zza)) {
                            break Label_0783;
                        }
                        final Resources resources = this.zzx.getResources();
                        int smallIcon = resources.getIdentifier(zza, "drawable", this.zzx.getPackageName());
                        if (smallIcon == 0 || !this.zzb(smallIcon)) {
                            smallIcon = resources.getIdentifier(zza, "mipmap", this.zzx.getPackageName());
                            if (smallIcon == 0 || !this.zzb(smallIcon)) {
                                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(zza).length() + 61).append("Icon resource ").append(zza).append(" not found. Notification will use default icon.").toString());
                                break Label_0783;
                            }
                        }
                        final Integer zzl = this.zzl(zza(bundle, "gcm.n.color"));
                        final String zzi = zzi(bundle);
                        Uri sound;
                        if (TextUtils.isEmpty((CharSequence)zzi)) {
                            sound = null;
                        }
                        else if (!"default".equals(zzi) && this.zzx.getResources().getIdentifier(zzi, "raw", this.zzx.getPackageName()) != 0) {
                            final String packageName = this.zzx.getPackageName();
                            sound = Uri.parse(new StringBuilder(String.valueOf(packageName).length() + 24 + String.valueOf(zzi).length()).append("android.resource://").append(packageName).append("/raw/").append(zzi).toString());
                        }
                        else {
                            sound = RingtoneManager.getDefaultUri(2);
                        }
                        final String zza2 = zza(bundle, "gcm.n.click_action");
                        Intent launchIntentForPackage;
                        if (!TextUtils.isEmpty((CharSequence)zza2)) {
                            launchIntentForPackage = new Intent(zza2);
                            launchIntentForPackage.setPackage(this.zzx.getPackageName());
                            launchIntentForPackage.setFlags(268435456);
                        }
                        else {
                            final Uri zzg = zzg(bundle);
                            if (zzg != null) {
                                launchIntentForPackage = new Intent("android.intent.action.VIEW");
                                launchIntentForPackage.setPackage(this.zzx.getPackageName());
                                launchIntentForPackage.setData(zzg);
                            }
                            else {
                                launchIntentForPackage = this.zzx.getPackageManager().getLaunchIntentForPackage(this.zzx.getPackageName());
                                if (launchIntentForPackage == null) {
                                    Log.w("FirebaseMessaging", "No activity found to launch app");
                                }
                            }
                        }
                        PendingIntent activity;
                        if (launchIntentForPackage == null) {
                            activity = null;
                        }
                        else {
                            launchIntentForPackage.addFlags(67108864);
                            final Bundle bundle2 = new Bundle(bundle);
                            FirebaseMessagingService.zzj(bundle2);
                            launchIntentForPackage.putExtras(bundle2);
                            for (final String s : bundle2.keySet()) {
                                if (s.startsWith("gcm.n.") || s.startsWith("gcm.notification.")) {
                                    launchIntentForPackage.removeExtra(s);
                                }
                            }
                            activity = PendingIntent.getActivity(this.zzx, com.google.firebase.messaging.zza.zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
                        }
                        PendingIntent zza3;
                        PendingIntent zza4;
                        if (bundle != null && "1".equals(bundle.getString("google.c.a.e"))) {
                            final Intent intent = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                            zza(intent, bundle);
                            intent.putExtra("pending_intent", (Parcelable)activity);
                            zza3 = zzav.zza(this.zzx, com.google.firebase.messaging.zza.zzdn.incrementAndGet(), intent, 1073741824);
                            final Intent intent2 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                            zza(intent2, bundle);
                            zza4 = zzav.zza(this.zzx, com.google.firebase.messaging.zza.zzdn.incrementAndGet(), intent2, 1073741824);
                        }
                        else {
                            zza4 = null;
                            zza3 = activity;
                        }
                        String channelId = zza(bundle, "gcm.n.android_channel_id");
                        Label_0467: {
                            if (!PlatformVersion.isAtLeastO() || this.zzx.getApplicationInfo().targetSdkVersion < 26) {
                                channelId = null;
                            }
                            else {
                                final NotificationManager notificationManager = (NotificationManager)this.zzx.getSystemService((Class)NotificationManager.class);
                                if (!TextUtils.isEmpty((CharSequence)channelId)) {
                                    if (notificationManager.getNotificationChannel(channelId) != null) {
                                        break Label_0467;
                                    }
                                    Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(channelId).length() + 122).append("Notification Channel requested (").append(channelId).append(") has not been created by the app. Manifest configuration, or default, value will be used.").toString());
                                }
                                channelId = this.zzas().getString("com.google.firebase.messaging.default_notification_channel_id");
                                if (!TextUtils.isEmpty((CharSequence)channelId)) {
                                    if (notificationManager.getNotificationChannel(channelId) != null) {
                                        break Label_0467;
                                    }
                                    Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                                }
                                else {
                                    Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                                }
                                if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                                    notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", (CharSequence)this.zzx.getString(R$string.fcm_fallback_notification_channel_label), 3));
                                }
                                channelId = "fcm_fallback_notification_channel";
                            }
                        }
                        final NotificationCompat$Builder setSmallIcon = new NotificationCompat$Builder(this.zzx).setAutoCancel(true).setSmallIcon(smallIcon);
                        if (!TextUtils.isEmpty(contentTitle)) {
                            setSmallIcon.setContentTitle(contentTitle);
                        }
                        if (!TextUtils.isEmpty((CharSequence)zzd)) {
                            setSmallIcon.setContentText((CharSequence)zzd);
                            setSmallIcon.setStyle((NotificationCompat$Style)new NotificationCompat$BigTextStyle().bigText((CharSequence)zzd));
                        }
                        if (zzl != null) {
                            setSmallIcon.setColor((int)zzl);
                        }
                        if (sound != null) {
                            setSmallIcon.setSound(sound);
                        }
                        if (zza3 != null) {
                            setSmallIcon.setContentIntent(zza3);
                        }
                        if (zza4 != null) {
                            setSmallIcon.setDeleteIntent(zza4);
                        }
                        if (channelId != null) {
                            setSmallIcon.setChannelId(channelId);
                        }
                        final Notification build = setSmallIcon.build();
                        final String zza5 = zza(bundle, "gcm.n.tag");
                        if (Log.isLoggable("FirebaseMessaging", 3)) {
                            Log.d("FirebaseMessaging", "Showing notification");
                        }
                        final NotificationManager notificationManager2 = (NotificationManager)this.zzx.getSystemService("notification");
                        String string = zza5;
                        if (TextUtils.isEmpty((CharSequence)zza5)) {
                            string = new StringBuilder(37).append("FCM-Notification:").append(SystemClock.uptimeMillis()).toString();
                        }
                        notificationManager2.notify(string, 0, build);
                        return true;
                    }
                    final int int1 = this.zzas().getInt("com.google.firebase.messaging.default_notification_icon", 0);
                    int icon = 0;
                    Label_0820: {
                        if (int1 != 0) {
                            icon = int1;
                            if (this.zzb(int1)) {
                                break Label_0820;
                            }
                        }
                        icon = this.zzx.getApplicationInfo().icon;
                    }
                    int n2 = 0;
                    Label_0838: {
                        if (icon != 0) {
                            n2 = icon;
                            if (this.zzb(icon)) {
                                break Label_0838;
                            }
                        }
                        n2 = 17301651;
                    }
                    int smallIcon = n2;
                    continue;
                }
            }
            final int n = 0;
            continue Label_0128;
        }
    }
}
