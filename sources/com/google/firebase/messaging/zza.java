package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.drive.DriveFile;
import com.google.firebase.iid.zzav;
import com.onesignal.OneSignalDbContract.NotificationTable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class zza {
    private static final AtomicInteger zzdn = new AtomicInteger((int) SystemClock.elapsedRealtime());
    private Bundle zzdo;
    private final Context zzx;

    public zza(Context context) {
        this.zzx = context.getApplicationContext();
    }

    static boolean zzf(Bundle bundle) {
        return "1".equals(zza(bundle, "gcm.n.e")) || zza(bundle, "gcm.n.icon") != null;
    }

    static String zza(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return bundle.getString(str.replace("gcm.n.", "gcm.notification."));
        }
        return string;
    }

    static String zzb(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_key");
        return zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
    }

    static Object[] zzc(Bundle bundle, String str) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf("_loc_args");
        String zza = zza(bundle, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(zza);
            Object[] objArr = new String[jSONArray.length()];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = jSONArray.opt(i);
            }
            return objArr;
        } catch (JSONException e) {
            valueOf = "FirebaseMessaging";
            String valueOf3 = String.valueOf(str);
            valueOf2 = String.valueOf("_loc_args");
            valueOf2 = (valueOf2.length() != 0 ? valueOf3.concat(valueOf2) : new String(valueOf3)).substring(6);
            Log.w(valueOf, new StringBuilder((String.valueOf(valueOf2).length() + 41) + String.valueOf(zza).length()).append("Malformed ").append(valueOf2).append(": ").append(zza).append("  Default value will be used.").toString());
            return null;
        }
    }

    @Nullable
    static Uri zzg(@NonNull Bundle bundle) {
        Object zza = zza(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(zza)) {
            zza = zza(bundle, "gcm.n.link");
        }
        if (TextUtils.isEmpty(zza)) {
            return null;
        }
        return Uri.parse(zza);
    }

    final boolean zzh(Bundle bundle) {
        if ("1".equals(zza(bundle, "gcm.n.noui"))) {
            return true;
        }
        Object obj;
        CharSequence zzd;
        CharSequence charSequence;
        CharSequence zzd2;
        String zza;
        Resources resources;
        int identifier;
        int i;
        Integer zzl;
        String zzi;
        Uri uri;
        Object zza2;
        Uri zzg;
        Intent launchIntentForPackage;
        Intent intent;
        Bundle bundle2;
        Parcelable activity;
        boolean equals;
        PendingIntent zza3;
        PendingIntent pendingIntent;
        Parcelable parcelable;
        String zza4;
        Builder smallIcon;
        Notification build;
        String zza5;
        NotificationManager notificationManager;
        if (!((KeyguardManager) this.zzx.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            if (!PlatformVersion.isAtLeastLollipop()) {
                SystemClock.sleep(10);
            }
            int myPid = Process.myPid();
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.zzx.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == myPid) {
                        obj = runningAppProcessInfo.importance == 100 ? 1 : null;
                        if (obj != null) {
                            return false;
                        }
                        zzd = zzd(bundle, "gcm.n.title");
                        if (TextUtils.isEmpty(zzd)) {
                            charSequence = zzd;
                        } else {
                            charSequence = this.zzx.getApplicationInfo().loadLabel(this.zzx.getPackageManager());
                        }
                        zzd2 = zzd(bundle, "gcm.n.body");
                        zza = zza(bundle, "gcm.n.icon");
                        if (!TextUtils.isEmpty(zza)) {
                            resources = this.zzx.getResources();
                            identifier = resources.getIdentifier(zza, "drawable", this.zzx.getPackageName());
                            if (identifier == 0 && zzb(identifier)) {
                                i = identifier;
                                zzl = zzl(zza(bundle, "gcm.n.color"));
                                zzi = zzi(bundle);
                                if (TextUtils.isEmpty(zzi)) {
                                    uri = null;
                                } else {
                                    if (!"default".equals(zzi)) {
                                    }
                                    uri = RingtoneManager.getDefaultUri(2);
                                }
                                zza2 = zza(bundle, "gcm.n.click_action");
                                if (TextUtils.isEmpty(zza2)) {
                                    zzg = zzg(bundle);
                                    if (zzg == null) {
                                        launchIntentForPackage = this.zzx.getPackageManager().getLaunchIntentForPackage(this.zzx.getPackageName());
                                        if (launchIntentForPackage == null) {
                                            Log.w("FirebaseMessaging", "No activity found to launch app");
                                        }
                                        intent = launchIntentForPackage;
                                    } else {
                                        launchIntentForPackage = new Intent("android.intent.action.VIEW");
                                        launchIntentForPackage.setPackage(this.zzx.getPackageName());
                                        launchIntentForPackage.setData(zzg);
                                        intent = launchIntentForPackage;
                                    }
                                } else {
                                    launchIntentForPackage = new Intent(zza2);
                                    launchIntentForPackage.setPackage(this.zzx.getPackageName());
                                    launchIntentForPackage.setFlags(DriveFile.MODE_READ_ONLY);
                                    intent = launchIntentForPackage;
                                }
                                if (intent != null) {
                                    intent.addFlags(67108864);
                                    bundle2 = new Bundle(bundle);
                                    FirebaseMessagingService.zzj(bundle2);
                                    intent.putExtras(bundle2);
                                    for (String zzi2 : bundle2.keySet()) {
                                        if (!zzi2.startsWith("gcm.n.")) {
                                        }
                                        intent.removeExtra(zzi2);
                                    }
                                    activity = PendingIntent.getActivity(this.zzx, zzdn.incrementAndGet(), intent, 1073741824);
                                } else {
                                    activity = null;
                                }
                                if (bundle != null) {
                                    equals = "1".equals(bundle.getString("google.c.a.e"));
                                } else {
                                    equals = false;
                                }
                                if (equals) {
                                    launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                                    zza(launchIntentForPackage, bundle);
                                    launchIntentForPackage.putExtra("pending_intent", activity);
                                    zza3 = zzav.zza(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
                                    launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                                    zza(launchIntentForPackage, bundle);
                                    pendingIntent = zza3;
                                    zza3 = zzav.zza(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
                                } else {
                                    parcelable = activity;
                                    zza3 = null;
                                }
                                zza4 = zza(bundle, "gcm.n.android_channel_id");
                                if (PlatformVersion.isAtLeastO()) {
                                }
                                zzi2 = null;
                                smallIcon = new Builder(this.zzx).setAutoCancel(true).setSmallIcon(i);
                                if (!TextUtils.isEmpty(charSequence)) {
                                    smallIcon.setContentTitle(charSequence);
                                }
                                if (!TextUtils.isEmpty(zzd2)) {
                                    smallIcon.setContentText(zzd2);
                                    smallIcon.setStyle(new BigTextStyle().bigText(zzd2));
                                }
                                if (zzl != null) {
                                    smallIcon.setColor(zzl.intValue());
                                }
                                if (uri != null) {
                                    smallIcon.setSound(uri);
                                }
                                if (pendingIntent != null) {
                                    smallIcon.setContentIntent(pendingIntent);
                                }
                                if (zza3 != null) {
                                    smallIcon.setDeleteIntent(zza3);
                                }
                                if (zzi2 != null) {
                                    smallIcon.setChannelId(zzi2);
                                }
                                build = smallIcon.build();
                                zza5 = zza(bundle, "gcm.n.tag");
                                if (Log.isLoggable("FirebaseMessaging", 3)) {
                                    Log.d("FirebaseMessaging", "Showing notification");
                                }
                                notificationManager = (NotificationManager) this.zzx.getSystemService(NotificationTable.TABLE_NAME);
                                if (TextUtils.isEmpty(zza5)) {
                                    zza5 = "FCM-Notification:" + SystemClock.uptimeMillis();
                                }
                                notificationManager.notify(zza5, 0, build);
                                return true;
                            }
                            identifier = resources.getIdentifier(zza, "mipmap", this.zzx.getPackageName());
                            if (identifier == 0 && zzb(identifier)) {
                                i = identifier;
                                zzl = zzl(zza(bundle, "gcm.n.color"));
                                zzi2 = zzi(bundle);
                                if (TextUtils.isEmpty(zzi2)) {
                                    uri = null;
                                } else if ("default".equals(zzi2) || this.zzx.getResources().getIdentifier(zzi2, "raw", this.zzx.getPackageName()) == 0) {
                                    uri = RingtoneManager.getDefaultUri(2);
                                } else {
                                    String packageName = this.zzx.getPackageName();
                                    uri = Uri.parse(new StringBuilder((String.valueOf(packageName).length() + 24) + String.valueOf(zzi2).length()).append("android.resource://").append(packageName).append("/raw/").append(zzi2).toString());
                                }
                                zza2 = zza(bundle, "gcm.n.click_action");
                                if (TextUtils.isEmpty(zza2)) {
                                    launchIntentForPackage = new Intent(zza2);
                                    launchIntentForPackage.setPackage(this.zzx.getPackageName());
                                    launchIntentForPackage.setFlags(DriveFile.MODE_READ_ONLY);
                                    intent = launchIntentForPackage;
                                } else {
                                    zzg = zzg(bundle);
                                    if (zzg == null) {
                                        launchIntentForPackage = new Intent("android.intent.action.VIEW");
                                        launchIntentForPackage.setPackage(this.zzx.getPackageName());
                                        launchIntentForPackage.setData(zzg);
                                        intent = launchIntentForPackage;
                                    } else {
                                        launchIntentForPackage = this.zzx.getPackageManager().getLaunchIntentForPackage(this.zzx.getPackageName());
                                        if (launchIntentForPackage == null) {
                                            Log.w("FirebaseMessaging", "No activity found to launch app");
                                        }
                                        intent = launchIntentForPackage;
                                    }
                                }
                                if (intent != null) {
                                    activity = null;
                                } else {
                                    intent.addFlags(67108864);
                                    bundle2 = new Bundle(bundle);
                                    FirebaseMessagingService.zzj(bundle2);
                                    intent.putExtras(bundle2);
                                    for (String zzi22 : bundle2.keySet()) {
                                        if (zzi22.startsWith("gcm.n.") || zzi22.startsWith("gcm.notification.")) {
                                            intent.removeExtra(zzi22);
                                        }
                                    }
                                    activity = PendingIntent.getActivity(this.zzx, zzdn.incrementAndGet(), intent, 1073741824);
                                }
                                if (bundle != null) {
                                    equals = false;
                                } else {
                                    equals = "1".equals(bundle.getString("google.c.a.e"));
                                }
                                if (equals) {
                                    launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                                    zza(launchIntentForPackage, bundle);
                                    launchIntentForPackage.putExtra("pending_intent", activity);
                                    zza3 = zzav.zza(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
                                    launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                                    zza(launchIntentForPackage, bundle);
                                    pendingIntent = zza3;
                                    zza3 = zzav.zza(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
                                } else {
                                    parcelable = activity;
                                    zza3 = null;
                                }
                                zza4 = zza(bundle, "gcm.n.android_channel_id");
                                if (PlatformVersion.isAtLeastO() || this.zzx.getApplicationInfo().targetSdkVersion < 26) {
                                    zzi22 = null;
                                } else {
                                    notificationManager = (NotificationManager) this.zzx.getSystemService(NotificationManager.class);
                                    if (!TextUtils.isEmpty(zza4)) {
                                        if (notificationManager.getNotificationChannel(zza4) != null) {
                                            zzi22 = zza4;
                                        } else {
                                            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(zza4).length() + 122).append("Notification Channel requested (").append(zza4).append(") has not been created by the app. Manifest configuration, or default, value will be used.").toString());
                                        }
                                    }
                                    zza4 = zzas().getString("com.google.firebase.messaging.default_notification_channel_id");
                                    if (TextUtils.isEmpty(zza4)) {
                                        Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                                    } else if (notificationManager.getNotificationChannel(zza4) != null) {
                                        zzi22 = zza4;
                                    } else {
                                        Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                                    }
                                    if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                                        notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", this.zzx.getString(C0971R.string.fcm_fallback_notification_channel_label), 3));
                                    }
                                    zzi22 = "fcm_fallback_notification_channel";
                                }
                                smallIcon = new Builder(this.zzx).setAutoCancel(true).setSmallIcon(i);
                                if (TextUtils.isEmpty(charSequence)) {
                                    smallIcon.setContentTitle(charSequence);
                                }
                                if (TextUtils.isEmpty(zzd2)) {
                                    smallIcon.setContentText(zzd2);
                                    smallIcon.setStyle(new BigTextStyle().bigText(zzd2));
                                }
                                if (zzl != null) {
                                    smallIcon.setColor(zzl.intValue());
                                }
                                if (uri != null) {
                                    smallIcon.setSound(uri);
                                }
                                if (pendingIntent != null) {
                                    smallIcon.setContentIntent(pendingIntent);
                                }
                                if (zza3 != null) {
                                    smallIcon.setDeleteIntent(zza3);
                                }
                                if (zzi22 != null) {
                                    smallIcon.setChannelId(zzi22);
                                }
                                build = smallIcon.build();
                                zza5 = zza(bundle, "gcm.n.tag");
                                if (Log.isLoggable("FirebaseMessaging", 3)) {
                                    Log.d("FirebaseMessaging", "Showing notification");
                                }
                                notificationManager = (NotificationManager) this.zzx.getSystemService(NotificationTable.TABLE_NAME);
                                if (TextUtils.isEmpty(zza5)) {
                                    zza5 = "FCM-Notification:" + SystemClock.uptimeMillis();
                                }
                                notificationManager.notify(zza5, 0, build);
                                return true;
                            }
                            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(zza).length() + 61).append("Icon resource ").append(zza).append(" not found. Notification will use default icon.").toString());
                        }
                        identifier = zzas().getInt("com.google.firebase.messaging.default_notification_icon", 0);
                        if (identifier == 0 || !zzb(identifier)) {
                            identifier = this.zzx.getApplicationInfo().icon;
                        }
                        if (identifier == 0 || !zzb(identifier)) {
                            identifier = 17301651;
                        }
                        i = identifier;
                        zzl = zzl(zza(bundle, "gcm.n.color"));
                        zzi22 = zzi(bundle);
                        if (TextUtils.isEmpty(zzi22)) {
                            uri = null;
                        } else {
                            if ("default".equals(zzi22)) {
                            }
                            uri = RingtoneManager.getDefaultUri(2);
                        }
                        zza2 = zza(bundle, "gcm.n.click_action");
                        if (TextUtils.isEmpty(zza2)) {
                            launchIntentForPackage = new Intent(zza2);
                            launchIntentForPackage.setPackage(this.zzx.getPackageName());
                            launchIntentForPackage.setFlags(DriveFile.MODE_READ_ONLY);
                            intent = launchIntentForPackage;
                        } else {
                            zzg = zzg(bundle);
                            if (zzg == null) {
                                launchIntentForPackage = new Intent("android.intent.action.VIEW");
                                launchIntentForPackage.setPackage(this.zzx.getPackageName());
                                launchIntentForPackage.setData(zzg);
                                intent = launchIntentForPackage;
                            } else {
                                launchIntentForPackage = this.zzx.getPackageManager().getLaunchIntentForPackage(this.zzx.getPackageName());
                                if (launchIntentForPackage == null) {
                                    Log.w("FirebaseMessaging", "No activity found to launch app");
                                }
                                intent = launchIntentForPackage;
                            }
                        }
                        if (intent != null) {
                            activity = null;
                        } else {
                            intent.addFlags(67108864);
                            bundle2 = new Bundle(bundle);
                            FirebaseMessagingService.zzj(bundle2);
                            intent.putExtras(bundle2);
                            for (String zzi222 : bundle2.keySet()) {
                                if (zzi222.startsWith("gcm.n.")) {
                                }
                                intent.removeExtra(zzi222);
                            }
                            activity = PendingIntent.getActivity(this.zzx, zzdn.incrementAndGet(), intent, 1073741824);
                        }
                        if (bundle != null) {
                            equals = false;
                        } else {
                            equals = "1".equals(bundle.getString("google.c.a.e"));
                        }
                        if (equals) {
                            launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
                            zza(launchIntentForPackage, bundle);
                            launchIntentForPackage.putExtra("pending_intent", activity);
                            zza3 = zzav.zza(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
                            launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
                            zza(launchIntentForPackage, bundle);
                            pendingIntent = zza3;
                            zza3 = zzav.zza(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
                        } else {
                            parcelable = activity;
                            zza3 = null;
                        }
                        zza4 = zza(bundle, "gcm.n.android_channel_id");
                        if (PlatformVersion.isAtLeastO()) {
                        }
                        zzi222 = null;
                        smallIcon = new Builder(this.zzx).setAutoCancel(true).setSmallIcon(i);
                        if (TextUtils.isEmpty(charSequence)) {
                            smallIcon.setContentTitle(charSequence);
                        }
                        if (TextUtils.isEmpty(zzd2)) {
                            smallIcon.setContentText(zzd2);
                            smallIcon.setStyle(new BigTextStyle().bigText(zzd2));
                        }
                        if (zzl != null) {
                            smallIcon.setColor(zzl.intValue());
                        }
                        if (uri != null) {
                            smallIcon.setSound(uri);
                        }
                        if (pendingIntent != null) {
                            smallIcon.setContentIntent(pendingIntent);
                        }
                        if (zza3 != null) {
                            smallIcon.setDeleteIntent(zza3);
                        }
                        if (zzi222 != null) {
                            smallIcon.setChannelId(zzi222);
                        }
                        build = smallIcon.build();
                        zza5 = zza(bundle, "gcm.n.tag");
                        if (Log.isLoggable("FirebaseMessaging", 3)) {
                            Log.d("FirebaseMessaging", "Showing notification");
                        }
                        notificationManager = (NotificationManager) this.zzx.getSystemService(NotificationTable.TABLE_NAME);
                        if (TextUtils.isEmpty(zza5)) {
                            zza5 = "FCM-Notification:" + SystemClock.uptimeMillis();
                        }
                        notificationManager.notify(zza5, 0, build);
                        return true;
                    }
                }
            }
        }
        obj = null;
        if (obj != null) {
            return false;
        }
        zzd = zzd(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(zzd)) {
            charSequence = zzd;
        } else {
            charSequence = this.zzx.getApplicationInfo().loadLabel(this.zzx.getPackageManager());
        }
        zzd2 = zzd(bundle, "gcm.n.body");
        zza = zza(bundle, "gcm.n.icon");
        if (TextUtils.isEmpty(zza)) {
            resources = this.zzx.getResources();
            identifier = resources.getIdentifier(zza, "drawable", this.zzx.getPackageName());
            if (identifier == 0) {
            }
            identifier = resources.getIdentifier(zza, "mipmap", this.zzx.getPackageName());
            if (identifier == 0) {
            }
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(zza).length() + 61).append("Icon resource ").append(zza).append(" not found. Notification will use default icon.").toString());
        }
        identifier = zzas().getInt("com.google.firebase.messaging.default_notification_icon", 0);
        identifier = this.zzx.getApplicationInfo().icon;
        identifier = 17301651;
        i = identifier;
        zzl = zzl(zza(bundle, "gcm.n.color"));
        zzi222 = zzi(bundle);
        if (TextUtils.isEmpty(zzi222)) {
            if ("default".equals(zzi222)) {
            }
            uri = RingtoneManager.getDefaultUri(2);
        } else {
            uri = null;
        }
        zza2 = zza(bundle, "gcm.n.click_action");
        if (TextUtils.isEmpty(zza2)) {
            zzg = zzg(bundle);
            if (zzg == null) {
                launchIntentForPackage = this.zzx.getPackageManager().getLaunchIntentForPackage(this.zzx.getPackageName());
                if (launchIntentForPackage == null) {
                    Log.w("FirebaseMessaging", "No activity found to launch app");
                }
                intent = launchIntentForPackage;
            } else {
                launchIntentForPackage = new Intent("android.intent.action.VIEW");
                launchIntentForPackage.setPackage(this.zzx.getPackageName());
                launchIntentForPackage.setData(zzg);
                intent = launchIntentForPackage;
            }
        } else {
            launchIntentForPackage = new Intent(zza2);
            launchIntentForPackage.setPackage(this.zzx.getPackageName());
            launchIntentForPackage.setFlags(DriveFile.MODE_READ_ONLY);
            intent = launchIntentForPackage;
        }
        if (intent != null) {
            intent.addFlags(67108864);
            bundle2 = new Bundle(bundle);
            FirebaseMessagingService.zzj(bundle2);
            intent.putExtras(bundle2);
            for (String zzi2222 : bundle2.keySet()) {
                if (zzi2222.startsWith("gcm.n.")) {
                }
                intent.removeExtra(zzi2222);
            }
            activity = PendingIntent.getActivity(this.zzx, zzdn.incrementAndGet(), intent, 1073741824);
        } else {
            activity = null;
        }
        if (bundle != null) {
            equals = "1".equals(bundle.getString("google.c.a.e"));
        } else {
            equals = false;
        }
        if (equals) {
            parcelable = activity;
            zza3 = null;
        } else {
            launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN");
            zza(launchIntentForPackage, bundle);
            launchIntentForPackage.putExtra("pending_intent", activity);
            zza3 = zzav.zza(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
            launchIntentForPackage = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS");
            zza(launchIntentForPackage, bundle);
            pendingIntent = zza3;
            zza3 = zzav.zza(this.zzx, zzdn.incrementAndGet(), launchIntentForPackage, 1073741824);
        }
        zza4 = zza(bundle, "gcm.n.android_channel_id");
        if (PlatformVersion.isAtLeastO()) {
        }
        zzi2222 = null;
        smallIcon = new Builder(this.zzx).setAutoCancel(true).setSmallIcon(i);
        if (TextUtils.isEmpty(charSequence)) {
            smallIcon.setContentTitle(charSequence);
        }
        if (TextUtils.isEmpty(zzd2)) {
            smallIcon.setContentText(zzd2);
            smallIcon.setStyle(new BigTextStyle().bigText(zzd2));
        }
        if (zzl != null) {
            smallIcon.setColor(zzl.intValue());
        }
        if (uri != null) {
            smallIcon.setSound(uri);
        }
        if (pendingIntent != null) {
            smallIcon.setContentIntent(pendingIntent);
        }
        if (zza3 != null) {
            smallIcon.setDeleteIntent(zza3);
        }
        if (zzi2222 != null) {
            smallIcon.setChannelId(zzi2222);
        }
        build = smallIcon.build();
        zza5 = zza(bundle, "gcm.n.tag");
        if (Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }
        notificationManager = (NotificationManager) this.zzx.getSystemService(NotificationTable.TABLE_NAME);
        if (TextUtils.isEmpty(zza5)) {
            zza5 = "FCM-Notification:" + SystemClock.uptimeMillis();
        }
        notificationManager.notify(zza5, 0, build);
        return true;
    }

    private final String zzd(Bundle bundle, String str) {
        Object zza = zza(bundle, str);
        if (!TextUtils.isEmpty(zza)) {
            return zza;
        }
        String zzb = zzb(bundle, str);
        if (TextUtils.isEmpty(zzb)) {
            return null;
        }
        Resources resources = this.zzx.getResources();
        int identifier = resources.getIdentifier(zzb, "string", this.zzx.getPackageName());
        if (identifier == 0) {
            String str2 = "FirebaseMessaging";
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf("_loc_key");
            valueOf2 = (valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).substring(6);
            Log.w(str2, new StringBuilder((String.valueOf(valueOf2).length() + 49) + String.valueOf(zzb).length()).append(valueOf2).append(" resource not found: ").append(zzb).append(" Default value will be used.").toString());
            return null;
        }
        Object[] zzc = zzc(bundle, str);
        if (zzc == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, zzc);
        } catch (Throwable e) {
            valueOf = Arrays.toString(zzc);
            Log.w("FirebaseMessaging", new StringBuilder((String.valueOf(zzb).length() + 58) + String.valueOf(valueOf).length()).append("Missing format argument for ").append(zzb).append(": ").append(valueOf).append(" Default value will be used.").toString(), e);
            return null;
        }
    }

    @TargetApi(26)
    private final boolean zzb(int i) {
        if (VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if (!(this.zzx.getResources().getDrawable(i, null) instanceof AdaptiveIconDrawable)) {
                return true;
            }
            Log.e("FirebaseMessaging", "Adaptive icons cannot be used in notifications. Ignoring icon id: " + i);
            return false;
        } catch (NotFoundException e) {
            return false;
        }
    }

    private final Integer zzl(String str) {
        Integer num = null;
        if (VERSION.SDK_INT >= 21) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    num = Integer.valueOf(Color.parseColor(str));
                } catch (IllegalArgumentException e) {
                    Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 54).append("Color ").append(str).append(" not valid. Notification will use default color.").toString());
                }
            }
            int i = zzas().getInt("com.google.firebase.messaging.default_notification_color", 0);
            if (i != 0) {
                try {
                    num = Integer.valueOf(ContextCompat.getColor(this.zzx, i));
                } catch (NotFoundException e2) {
                    Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
                }
            }
        }
        return num;
    }

    static String zzi(Bundle bundle) {
        String zza = zza(bundle, "gcm.n.sound2");
        if (TextUtils.isEmpty(zza)) {
            return zza(bundle, "gcm.n.sound");
        }
        return zza;
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    private final Bundle zzas() {
        if (this.zzdo != null) {
            return this.zzdo;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.zzx.getPackageManager().getApplicationInfo(this.zzx.getPackageName(), 128);
        } catch (NameNotFoundException e) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.zzdo = applicationInfo.metaData;
        return this.zzdo;
    }
}
