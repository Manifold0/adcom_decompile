// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import java.util.regex.Matcher;
import android.content.res.Configuration;
import android.app.Activity;
import java.lang.ref.WeakReference;
import android.util.Patterns;
import android.os.Build;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import java.util.Iterator;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Map;
import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import net.hockeyapp.android.R$string;
import android.annotation.SuppressLint;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import android.annotation.TargetApi;
import android.os.Build$VERSION;
import android.app.Notification$Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;
import java.text.DateFormat;

public class Util
{
    public static final String APP_IDENTIFIER_KEY = "net.hockeyapp.android.appIdentifier";
    public static final int APP_IDENTIFIER_LENGTH = 32;
    public static final String APP_IDENTIFIER_PATTERN = "[0-9a-f]+";
    private static final String APP_SECRET_KEY = "net.hockeyapp.android.appSecret";
    private static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL;
    private static final char[] HEX_ARRAY;
    public static final String LOG_IDENTIFIER = "HockeyApp";
    public static final String PREFS_FEEDBACK_TOKEN = "net.hockeyapp.android.prefs_feedback_token";
    public static final String PREFS_KEY_FEEDBACK_TOKEN = "net.hockeyapp.android.prefs_key_feedback_token";
    public static final String PREFS_KEY_NAME_EMAIL_SUBJECT = "net.hockeyapp.android.prefs_key_name_email";
    public static final String PREFS_NAME_EMAIL_SUBJECT = "net.hockeyapp.android.prefs_name_email";
    private static final String SDK_VERSION_KEY = "net.hockeyapp.android.sdkVersion";
    private static final Pattern appIdentifierPattern;
    
    static {
        appIdentifierPattern = Pattern.compile("[0-9a-f]+", 2);
        HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat;
            }
        };
    }
    
    private static Notification buildNotificationPreHoneycomb(final Context context, final PendingIntent pendingIntent, final String s, final String s2, final int n) {
        final Notification notification = new Notification(n, (CharSequence)"", System.currentTimeMillis());
        try {
            notification.getClass().getMethod("setLatestEventInfo", Context.class, CharSequence.class, CharSequence.class, PendingIntent.class).invoke(notification, context, s, s2, pendingIntent);
            return notification;
        }
        catch (Exception ex) {
            return notification;
        }
    }
    
    @TargetApi(11)
    private static Notification buildNotificationWithBuilder(final Context context, final PendingIntent contentIntent, final String contentTitle, final String contentText, final int smallIcon) {
        final Notification$Builder setSmallIcon = new Notification$Builder(context).setContentTitle((CharSequence)contentTitle).setContentText((CharSequence)contentText).setContentIntent(contentIntent).setSmallIcon(smallIcon);
        if (Build$VERSION.SDK_INT < 16) {
            return setSmallIcon.getNotification();
        }
        return setSmallIcon.build();
    }
    
    public static boolean classExists(final String s) {
        boolean b = false;
        try {
            if (Class.forName(s) != null) {
                b = true;
            }
            return b;
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
    }
    
    public static String convertAppIdentifierToGuid(String string) throws IllegalArgumentException {
        final String s = null;
        try {
            final String sanitizeAppIdentifier = sanitizeAppIdentifier(string);
            string = s;
            if (sanitizeAppIdentifier != null) {
                final StringBuffer sb = new StringBuffer(sanitizeAppIdentifier);
                sb.insert(20, '-');
                sb.insert(16, '-');
                sb.insert(12, '-');
                sb.insert(8, '-');
                string = sb.toString();
            }
            return string;
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
    }
    
    public static Notification createNotification(final Context context, final PendingIntent pendingIntent, final String s, final String s2, final int n) {
        if (isNotificationBuilderSupported()) {
            return buildNotificationWithBuilder(context, pendingIntent, s, s2, n);
        }
        return buildNotificationPreHoneycomb(context, pendingIntent, s, s2, n);
    }
    
    public static String dateToISO8601(final Date date) {
        Date date2 = date;
        if (date == null) {
            date2 = new Date();
        }
        return Util.DATE_FORMAT_THREAD_LOCAL.get().format(date2);
    }
    
    public static String encodeParam(String encode) {
        try {
            encode = URLEncoder.encode(encode, "UTF-8");
            return encode;
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    @SuppressLint({ "NewApi" })
    public static Boolean fragmentsSupported() {
        try {
            return Build$VERSION.SDK_INT >= 11 && classExists("android.app.Fragment");
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            return false;
        }
    }
    
    public static String getAppIdentifier(final Context context) {
        return getManifestString(context, "net.hockeyapp.android.appIdentifier");
    }
    
    public static String getAppName(final Context context) {
        if (context == null) {
            return "";
        }
        final PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        while (true) {
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
                if (applicationInfo != null) {
                    return (String)packageManager.getApplicationLabel(applicationInfo);
                }
                return context.getString(R$string.hockeyapp_crash_dialog_app_name_fallback);
            }
            catch (PackageManager$NameNotFoundException ex) {
                continue;
            }
            break;
        }
    }
    
    public static String getAppSecret(final Context context) {
        return getManifestString(context, "net.hockeyapp.android.appSecret");
    }
    
    private static Bundle getBundle(final Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public static String getFormString(final Map<String, String> map) throws UnsupportedEncodingException {
        final ArrayList<String> list = new ArrayList<String>();
        for (final String s : map.keySet()) {
            list.add(URLEncoder.encode(s, "UTF-8") + "=" + URLEncoder.encode(map.get(s), "UTF-8"));
        }
        return TextUtils.join((CharSequence)"&", (Iterable)list);
    }
    
    public static String getManifestString(final Context context, final String s) {
        return getBundle(context).getString(s);
    }
    
    public static String getSdkVersionFromManifest(final Context context) {
        return getManifestString(context, "net.hockeyapp.android.sdkVersion");
    }
    
    public static boolean isConnectedToNetwork(final Context context) {
        final boolean b = false;
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getApplicationContext().getSystemService("connectivity");
        boolean b2 = b;
        if (connectivityManager != null) {
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            b2 = b;
            if (activeNetworkInfo != null) {
                b2 = b;
                if (activeNetworkInfo.isConnected()) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static boolean isEmulator() {
        return Build.BRAND.equalsIgnoreCase("generic");
    }
    
    public static boolean isNotificationBuilderSupported() {
        return Build$VERSION.SDK_INT >= 11 && classExists("android.app.Notification.Builder");
    }
    
    public static final boolean isValidEmail(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && Patterns.EMAIL_ADDRESS.matcher(s).matches();
    }
    
    public static Boolean runsOnTablet(final WeakReference<Activity> weakReference) {
        boolean b = false;
        if (weakReference != null) {
            final Activity activity = weakReference.get();
            if (activity != null) {
                final Configuration configuration = activity.getResources().getConfiguration();
                if ((configuration.screenLayout & 0xF) == 0x3 || (configuration.screenLayout & 0xF) == 0x4) {
                    b = true;
                }
                return b;
            }
        }
        return false;
    }
    
    public static String sanitizeAppIdentifier(String trim) throws IllegalArgumentException {
        if (trim == null) {
            throw new IllegalArgumentException("App ID must not be null.");
        }
        trim = trim.trim();
        final Matcher matcher = Util.appIdentifierPattern.matcher(trim);
        if (trim.length() != 32) {
            throw new IllegalArgumentException("App ID length must be 32 characters.");
        }
        if (!matcher.matches()) {
            throw new IllegalArgumentException("App ID must match regex pattern /[0-9a-f]+/i");
        }
        return trim;
    }
    
    public static boolean sessionTrackingSupported() {
        return Build$VERSION.SDK_INT >= 14;
    }
}
