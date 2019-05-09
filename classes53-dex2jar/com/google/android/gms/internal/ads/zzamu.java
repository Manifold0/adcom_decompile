// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.UUID;
import android.os.Build;
import java.lang.annotation.Annotation;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.Locale;
import java.security.MessageDigest;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import android.annotation.TargetApi;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import android.content.ContentResolver;
import android.provider.Settings$Secure;
import android.view.Display;
import android.view.WindowManager;
import java.net.HttpURLConnection;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import java.util.Iterator;
import android.net.Uri$Builder;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import android.os.Build$VERSION;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;
import android.support.annotation.Nullable;
import java.util.StringTokenizer;
import android.util.TypedValue;
import android.util.DisplayMetrics;
import android.content.Context;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import android.os.Looper;
import android.os.Handler;

@zzadh
public final class zzamu
{
    private static final String zzcup;
    private static final String zzcuq;
    private static final String zzcur;
    private static final String zzcus;
    private static final String zzcut;
    private static final String zzcuu;
    public static final Handler zzsy;
    
    static {
        zzsy = new Handler(Looper.getMainLooper());
        zzcup = AdView.class.getName();
        zzcuq = InterstitialAd.class.getName();
        zzcur = PublisherAdView.class.getName();
        zzcus = PublisherInterstitialAd.class.getName();
        zzcut = SearchAdView.class.getName();
        zzcuu = AdLoader.class.getName();
    }
    
    public static int zza(final Context context, final int n) {
        return zza(context.getResources().getDisplayMetrics(), n);
    }
    
    public static int zza(final DisplayMetrics displayMetrics, final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, displayMetrics);
    }
    
    @Nullable
    @VisibleForTesting
    public static String zza(final StackTraceElement[] array, String string) {
        while (true) {
            for (int n = 0; n + 1 < array.length; ++n) {
                final StackTraceElement stackTraceElement = array[n];
                final String className = stackTraceElement.getClassName();
                if ("loadAd".equalsIgnoreCase(stackTraceElement.getMethodName()) && (zzamu.zzcup.equalsIgnoreCase(className) || zzamu.zzcuq.equalsIgnoreCase(className) || zzamu.zzcur.equalsIgnoreCase(className) || zzamu.zzcus.equalsIgnoreCase(className) || zzamu.zzcut.equalsIgnoreCase(className) || zzamu.zzcuu.equalsIgnoreCase(className))) {
                    final String className2 = array[n + 1].getClassName();
                    if (string != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(string, ".");
                        final StringBuilder sb = new StringBuilder();
                        int n2 = 2;
                        if (stringTokenizer.hasMoreElements()) {
                            sb.append(stringTokenizer.nextToken());
                            while (n2 > 0 && stringTokenizer.hasMoreElements()) {
                                sb.append(".").append(stringTokenizer.nextToken());
                                --n2;
                            }
                            string = sb.toString();
                        }
                        if (className2 != null && !className2.contains(string)) {
                            return className2;
                        }
                    }
                    return null;
                }
            }
            final String className2 = null;
            continue;
        }
    }
    
    public static void zza(final Context context, @Nullable final String s, String s2, final Bundle bundle, final boolean b, final zzamx zzamx) {
        if (b) {
            Context applicationContext;
            if ((applicationContext = context.getApplicationContext()) == null) {
                applicationContext = context;
            }
            bundle.putString("os", Build$VERSION.RELEASE);
            bundle.putString("api", String.valueOf(Build$VERSION.SDK_INT));
            bundle.putString("appid", applicationContext.getPackageName());
            String string;
            if ((string = s) == null) {
                string = new StringBuilder(20).append(GoogleApiAvailabilityLight.getInstance().getApkVersion(context)).append(".12451000").toString();
            }
            bundle.putString("js", string);
        }
        final Uri$Builder appendQueryParameter = new Uri$Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", s2);
        final Iterator<String> iterator = bundle.keySet().iterator();
        while (iterator.hasNext()) {
            s2 = iterator.next();
            appendQueryParameter.appendQueryParameter(s2, bundle.getString(s2));
        }
        zzamx.zzcz(appendQueryParameter.toString());
    }
    
    private final void zza(final ViewGroup viewGroup, final zzjn zzjn, final String text, int zza, final int backgroundColor) {
        if (viewGroup.getChildCount() != 0) {
            return;
        }
        final Context context = viewGroup.getContext();
        final TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setText((CharSequence)text);
        textView.setTextColor(zza);
        textView.setBackgroundColor(backgroundColor);
        final FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(zza);
        zza = zza(context, 3);
        frameLayout.addView((View)textView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(zzjn.widthPixels - zza, zzjn.heightPixels - zza, 17));
        viewGroup.addView((View)frameLayout, zzjn.widthPixels, zzjn.heightPixels);
    }
    
    public static void zza(final boolean b, final HttpURLConnection httpURLConnection, @Nullable final String s) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setReadTimeout(60000);
        if (s != null) {
            httpURLConnection.setRequestProperty("User-Agent", s);
        }
        httpURLConnection.setUseCaches(false);
    }
    
    public static int zzb(final Context context, final int n) {
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return zzb(displayMetrics, n);
    }
    
    public static int zzb(final DisplayMetrics displayMetrics, final int n) {
        return Math.round(n / displayMetrics.density);
    }
    
    public static String zzbc(final Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        String string;
        if (contentResolver == null) {
            string = null;
        }
        else {
            string = Settings$Secure.getString(contentResolver, "android_id");
        }
        if (string == null || zzsg()) {
            string = "emulator";
        }
        return zzde(string);
    }
    
    @Nullable
    public static String zzbd(final Context context) {
        final ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null) {
            return null;
        }
        return Settings$Secure.getString(contentResolver, "android_id");
    }
    
    public static boolean zzbe(final Context context) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12451000) == 0;
    }
    
    public static int zzbf(final Context context) {
        return DynamiteModule.getRemoteVersion(context, "com.google.android.gms.ads.dynamite");
    }
    
    public static int zzbg(final Context context) {
        return DynamiteModule.getLocalVersion(context, "com.google.android.gms.ads.dynamite");
    }
    
    public static boolean zzbh(final Context context) {
        final int googlePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12451000);
        return googlePlayServicesAvailable == 0 || googlePlayServicesAvailable == 2;
    }
    
    public static boolean zzbi(final Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if ((int)(displayMetrics.heightPixels / displayMetrics.density) < 600) {
                return true;
            }
        }
        return false;
    }
    
    @TargetApi(17)
    public static boolean zzbj(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        int n = 0;
        int n2 = 0;
        Label_0049: {
            if (!PlatformVersion.isAtLeastJellyBeanMR1()) {
                try {
                    n = (int)Display.class.getMethod("getRawHeight", (Class<?>[])new Class[0]).invoke(defaultDisplay, new Object[0]);
                    n2 = (int)Display.class.getMethod("getRawWidth", (Class<?>[])new Class[0]).invoke(defaultDisplay, new Object[0]);
                    break Label_0049;
                }
                catch (Exception ex) {
                    return false;
                }
                return false;
            }
            defaultDisplay.getRealMetrics(displayMetrics);
            n = displayMetrics.heightPixels;
            n2 = displayMetrics.widthPixels;
        }
        defaultDisplay.getMetrics(displayMetrics);
        final int heightPixels = displayMetrics.heightPixels;
        final int widthPixels = displayMetrics.widthPixels;
        if (heightPixels == n && widthPixels == n2) {
            return true;
        }
        return false;
    }
    
    public static int zzbk(final Context context) {
        final int identifier = context.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
    
    public static Throwable zzc(Throwable cause) {
        if (zzkb.zzik().zzd(zznk.zzauj)) {
            return cause;
        }
        final LinkedList<Throwable> list = new LinkedList<Throwable>();
        while (cause != null) {
            list.push(cause);
            cause = cause.getCause();
        }
        Throwable t = null;
        while (!list.isEmpty()) {
            final Throwable t2 = list.pop();
            final StackTraceElement[] stackTrace = t2.getStackTrace();
            final ArrayList<StackTraceElement> list2 = new ArrayList<StackTraceElement>();
            list2.add(new StackTraceElement(t2.getClass().getName(), "<filtered>", "<filtered>", 1));
            final int length = stackTrace.length;
            int i = 0;
            boolean b = false;
            while (i < length) {
                final StackTraceElement stackTraceElement = stackTrace[i];
                if (zzdf(stackTraceElement.getClassName())) {
                    list2.add(stackTraceElement);
                    b = true;
                }
                else {
                    final String className = stackTraceElement.getClassName();
                    int n;
                    if (!TextUtils.isEmpty((CharSequence)className) && (className.startsWith("android.") || className.startsWith("java."))) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    if (n != 0) {
                        list2.add(stackTraceElement);
                    }
                    else {
                        list2.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                    }
                }
                ++i;
            }
            if (b) {
                if (t == null) {
                    t = new Throwable(t2.getMessage());
                }
                else {
                    t = new Throwable(t2.getMessage(), t);
                }
                t.setStackTrace(list2.toArray(new StackTraceElement[0]));
            }
        }
        return t;
    }
    
    public static String zzde(final String s) {
        final String s2 = null;
        int n = 0;
        while (true) {
            String format = s2;
            if (n >= 2) {
                return format;
            }
            try {
                final MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(s.getBytes());
                format = String.format(Locale.US, "%032X", new BigInteger(1, instance.digest()));
                return format;
            }
            catch (NoSuchAlgorithmException ex) {
                ++n;
            }
            catch (ArithmeticException ex2) {
                return null;
            }
        }
    }
    
    @VisibleForTesting
    public static boolean zzdf(String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        if (s.startsWith(zzkb.zzik().zzd(zznk.zzauk))) {
            return true;
        }
        try {
            return Class.forName(s).isAnnotationPresent(zzadh.class);
        }
        catch (Exception ex) {
            s = String.valueOf(s);
            if (s.length() != 0) {
                s = "Fail to check class type for class ".concat(s);
            }
            else {
                s = new String("Fail to check class type for class ");
            }
            zzane.zza(s, ex);
            return false;
        }
    }
    
    public static boolean zzsg() {
        return Build.DEVICE.startsWith("generic");
    }
    
    public static boolean zzsh() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
    
    public static String zzsi() {
        final UUID randomUUID = UUID.randomUUID();
        final byte[] byteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        final byte[] byteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String s = new BigInteger(1, byteArray).toString();
        int n = 0;
    Label_0100_Outer:
        while (true) {
            if (n >= 2) {
                return s;
            }
            while (true) {
                try {
                    final MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(byteArray);
                    instance.update(byteArray2);
                    final byte[] array = new byte[8];
                    System.arraycopy(instance.digest(), 0, array, 0, 8);
                    s = new BigInteger(1, array).toString();
                    ++n;
                    continue Label_0100_Outer;
                }
                catch (NoSuchAlgorithmException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public final void zza(final Context context, @Nullable final String s, final String s2, final Bundle bundle, final boolean b) {
        zza(context, null, s2, bundle, true, new zzamv(this));
    }
    
    public final void zza(final ViewGroup viewGroup, final zzjn zzjn, final String s) {
        this.zza(viewGroup, zzjn, s, -16777216, -1);
    }
    
    public final void zza(final ViewGroup viewGroup, final zzjn zzjn, final String s, final String s2) {
        zzane.zzdk(s2);
        this.zza(viewGroup, zzjn, s, -65536, -16777216);
    }
}
