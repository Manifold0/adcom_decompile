// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.regex.PatternSyntaxException;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.view.WindowManager$LayoutParams;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.support.annotation.NonNull;
import android.os.Parcelable;
import android.os.Debug;
import android.os.Debug$MemoryInfo;
import java.util.UUID;
import android.os.Build;
import java.util.Locale;
import android.os.Build$VERSION;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import java.io.FileOutputStream;
import java.util.ArrayList;
import android.os.IBinder;
import java.util.HashMap;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import java.net.HttpURLConnection;
import com.google.android.gms.common.util.PlatformVersion;
import android.support.annotation.Nullable;
import android.content.pm.ApplicationInfo;
import android.view.Window;
import android.graphics.Bitmap;
import android.os.PowerManager;
import android.os.Process;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.ActivityManager$RunningTaskInfo;
import android.app.ActivityManager;
import android.app.AlertDialog$Builder;
import android.webkit.WebView;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.app.Activity;
import java.util.Arrays;
import java.util.Map;
import com.google.android.gms.common.util.CrashUtils;
import java.util.List;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.content.Intent;
import java.util.Iterator;
import java.util.Collection;
import java.io.IOException;
import java.io.InputStreamReader;
import android.view.ViewParent;
import org.json.JSONException;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.widget.PopupWindow;
import android.view.View;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.internal.zzbv;
import android.os.Bundle;
import android.os.Looper;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;
import android.os.Handler;

@zzadh
public final class zzakk
{
    public static final Handler zzcrm;
    private final Object mLock;
    @GuardedBy("mLock")
    private String zzcpq;
    private boolean zzcrn;
    private boolean zzcro;
    private boolean zzcrp;
    @GuardedBy("this")
    private Pattern zzcrq;
    @GuardedBy("this")
    private Pattern zzcrr;
    
    static {
        zzcrm = new zzakc(Looper.getMainLooper());
    }
    
    public zzakk() {
        this.mLock = new Object();
        this.zzcrn = true;
        this.zzcro = false;
        this.zzcrp = false;
    }
    
    @VisibleForTesting
    public static Bundle zza(final zzgk zzgk) {
        if (zzgk != null && ((boolean)zzkb.zzik().zzd(zznk.zzawq) || (boolean)zzkb.zzik().zzd(zznk.zzaws)) && (!zzbv.zzeo().zzqh().zzqu() || !zzbv.zzeo().zzqh().zzqw())) {
            if (zzgk.zzha()) {
                zzgk.wakeup();
            }
            final zzge zzgy = zzgk.zzgy();
            String s;
            String zzgo;
            String s2;
            if (zzgy != null) {
                s = zzgy.getSignature();
                zzgo = zzgy.zzgo();
                s2 = zzgy.zzgp();
                if (s != null) {
                    zzbv.zzeo().zzqh().zzcn(s);
                }
                if (s2 != null) {
                    zzbv.zzeo().zzqh().zzco(s2);
                }
            }
            else {
                s = zzbv.zzeo().zzqh().zzqv();
                s2 = zzbv.zzeo().zzqh().zzqx();
                zzgo = null;
            }
            final Bundle bundle = new Bundle(1);
            if (s2 != null && (boolean)zzkb.zzik().zzd(zznk.zzaws) && !zzbv.zzeo().zzqh().zzqw()) {
                bundle.putString("v_fp_vertical", s2);
            }
            if (s != null && (boolean)zzkb.zzik().zzd(zznk.zzawq) && !zzbv.zzeo().zzqh().zzqu()) {
                bundle.putString("fingerprint", s);
                if (!s.equals(zzgo)) {
                    bundle.putString("v_fp", zzgo);
                }
            }
            if (!bundle.isEmpty()) {
                return bundle;
            }
        }
        return null;
    }
    
    public static DisplayMetrics zza(final WindowManager windowManager) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
    
    public static PopupWindow zza(final View view, final int n, final int n2, final boolean b) {
        return new PopupWindow(view, n, n2, false);
    }
    
    public static String zza(final Context context, View view, final zzjn zzjn) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzaxi)) {
            return null;
        }
        while (true) {
            while (true) {
                Label_0224: {
                    try {
                        final JSONObject jsonObject = new JSONObject();
                        final JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("width", zzjn.width);
                        jsonObject2.put("height", zzjn.height);
                        jsonObject.put("size", (Object)jsonObject2);
                        jsonObject.put("activity", (Object)zzap(context));
                        if (!zzjn.zzarc) {
                            final JSONArray jsonArray = new JSONArray();
                            while (view != null) {
                                final ViewParent parent = view.getParent();
                                if (parent != null) {
                                    int indexOfChild = -1;
                                    if (parent instanceof ViewGroup) {
                                        indexOfChild = ((ViewGroup)parent).indexOfChild(view);
                                    }
                                    final JSONObject jsonObject3 = new JSONObject();
                                    jsonObject3.put("type", (Object)((ViewGroup)parent).getClass().getName());
                                    jsonObject3.put("index_of_child", indexOfChild);
                                    jsonArray.put((Object)jsonObject3);
                                }
                                if (parent == null || !(parent instanceof View)) {
                                    break Label_0224;
                                }
                                view = (View)parent;
                            }
                            if (jsonArray.length() > 0) {
                                jsonObject.put("parents", (Object)jsonArray);
                            }
                        }
                        return jsonObject.toString();
                    }
                    catch (JSONException ex) {
                        zzakb.zzc("Fail to get view hierarchy json", (Throwable)ex);
                        return null;
                    }
                }
                view = null;
                continue;
            }
        }
    }
    
    public static String zza(final InputStreamReader inputStreamReader) throws IOException {
        final StringBuilder sb = new StringBuilder(8192);
        final char[] array = new char[2048];
        while (true) {
            final int read = inputStreamReader.read(array);
            if (read == -1) {
                break;
            }
            sb.append(array, 0, read);
        }
        return sb.toString();
    }
    
    private final JSONArray zza(final Collection<?> collection) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.zza(jsonArray, iterator.next());
        }
        return jsonArray;
    }
    
    public static void zza(final Context context, final Intent intent) {
        try {
            context.startActivity(intent);
        }
        catch (Throwable t) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }
    
    @TargetApi(18)
    public static void zza(final Context context, final Uri uri) {
        try {
            final Intent intent = new Intent("android.intent.action.VIEW", uri);
            final Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            if (zzkb.zzik().zzd(zznk.zzbdy)) {
                zzb(context, intent);
            }
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            final String string = uri.toString();
            zzakb.zzck(new StringBuilder(String.valueOf(string).length() + 26).append("Opening ").append(string).append(" in a new browser.").toString());
        }
        catch (ActivityNotFoundException ex) {
            zzakb.zzb("No browser is found.", (Throwable)ex);
        }
    }
    
    public static void zza(final Context context, final String s, final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            new zzami(context, s, iterator.next()).zznt();
        }
    }
    
    public static void zza(final Context context, final Throwable t) {
        if (context != null) {
            while (true) {
                try {
                    final int booleanValue = ((boolean)zzkb.zzik().zzd(zznk.zzaui)) ? 1 : 0;
                    if (booleanValue != 0) {
                        CrashUtils.addDynamiteErrorToDropBox(context, t);
                    }
                }
                catch (IllegalStateException ex) {
                    final int booleanValue = 0;
                    continue;
                }
                break;
            }
        }
    }
    
    private final void zza(final JSONArray jsonArray, final Object o) throws JSONException {
        if (o instanceof Bundle) {
            jsonArray.put((Object)this.zzf((Bundle)o));
            return;
        }
        if (o instanceof Map) {
            jsonArray.put((Object)this.zzn((Map<String, ?>)o));
            return;
        }
        if (o instanceof Collection) {
            jsonArray.put((Object)this.zza((Collection<?>)o));
            return;
        }
        if (o instanceof Object[]) {
            final Object[] array = (Object[])o;
            final JSONArray jsonArray2 = new JSONArray();
            for (int length = array.length, i = 0; i < length; ++i) {
                this.zza(jsonArray2, array[i]);
            }
            jsonArray.put((Object)jsonArray2);
            return;
        }
        jsonArray.put(o);
    }
    
    private final void zza(final JSONObject jsonObject, String s, final Object o) throws JSONException {
        if (o instanceof Bundle) {
            jsonObject.put(s, (Object)this.zzf((Bundle)o));
            return;
        }
        if (o instanceof Map) {
            jsonObject.put(s, (Object)this.zzn((Map<String, ?>)o));
            return;
        }
        if (o instanceof Collection) {
            if (s == null) {
                s = "null";
            }
            jsonObject.put(s, (Object)this.zza((Collection<?>)o));
            return;
        }
        if (o instanceof Object[]) {
            jsonObject.put(s, (Object)this.zza(Arrays.asList((Object[])o)));
            return;
        }
        jsonObject.put(s, o);
    }
    
    @TargetApi(24)
    public static boolean zza(final Activity activity, final Configuration configuration) {
        final boolean b = false;
        zzkb.zzif();
        final int zza = zzamu.zza((Context)activity, configuration.screenHeightDp);
        final int zza2 = zzamu.zza((Context)activity, configuration.screenWidthDp);
        final DisplayMetrics zza3 = zza((WindowManager)activity.getApplicationContext().getSystemService("window"));
        final int heightPixels = zza3.heightPixels;
        final int widthPixels = zza3.widthPixels;
        final int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize;
        if (identifier > 0) {
            dimensionPixelSize = activity.getResources().getDimensionPixelSize(identifier);
        }
        else {
            dimensionPixelSize = 0;
        }
        final int n = (int)zzkb.zzik().zzd(zznk.zzbek) * (int)Math.round(activity.getResources().getDisplayMetrics().density + 0.5);
        boolean b2 = b;
        if (zzb(heightPixels, dimensionPixelSize + zza, n)) {
            b2 = b;
            if (zzb(widthPixels, zza2, n)) {
                b2 = true;
            }
        }
        return b2;
    }
    
    public static boolean zza(final ClassLoader classLoader, final Class<?> clazz, final String s) {
        try {
            return clazz.isAssignableFrom(Class.forName(s, false, classLoader));
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public static boolean zzaj(final Context context) {
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        final PackageManager packageManager = context.getPackageManager();
        ResolveInfo resolveActivity;
        try {
            resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                zzakb.zzdk("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
                return false;
            }
        }
        catch (Exception ex) {
            zzakb.zzc("Could not verify that com.google.android.gms.ads.AdActivity is declared in AndroidManifest.xml", (Throwable)ex);
            zzbv.zzeo().zza(ex, "AdUtil.hasAdActivity");
            return false;
        }
        boolean b;
        if ((resolveActivity.activityInfo.configChanges & 0x10) == 0x0) {
            zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboard"));
            b = false;
        }
        else {
            b = true;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x20) == 0x0) {
            zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboardHidden"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x80) == 0x0) {
            zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "orientation"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x100) == 0x0) {
            zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenLayout"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x200) == 0x0) {
            zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "uiMode"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x400) == 0x0) {
            zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenSize"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x800) == 0x0) {
            zzakb.zzdk(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "smallestScreenSize"));
            return false;
        }
        return b;
    }
    
    @VisibleForTesting
    protected static String zzam(final Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        }
        catch (Throwable t) {
            return zzrg();
        }
    }
    
    public static AlertDialog$Builder zzan(final Context context) {
        return new AlertDialog$Builder(context);
    }
    
    public static zzmw zzao(final Context context) {
        return new zzmw(context);
    }
    
    private static String zzap(final Context context) {
        try {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            final List runningTasks = activityManager.getRunningTasks(1);
            if (runningTasks != null && !runningTasks.isEmpty()) {
                final ActivityManager$RunningTaskInfo activityManager$RunningTaskInfo = runningTasks.get(0);
                if (activityManager$RunningTaskInfo != null && activityManager$RunningTaskInfo.topActivity != null) {
                    return activityManager$RunningTaskInfo.topActivity.getClassName();
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static boolean zzaq(final Context context) {
        while (true) {
            boolean b = false;
            Label_0133: {
                try {
                    final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
                    final KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
                    if (activityManager != null) {
                        if (keyguardManager != null) {
                            final List runningAppProcesses = activityManager.getRunningAppProcesses();
                            if (runningAppProcesses == null) {
                                return false;
                            }
                            for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                                if (Process.myPid() == activityManager$RunningAppProcessInfo.pid) {
                                    if (activityManager$RunningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                                        final PowerManager powerManager = (PowerManager)context.getSystemService("power");
                                        b = (powerManager != null && powerManager.isScreenOn());
                                        break Label_0133;
                                    }
                                    break;
                                }
                            }
                            return false;
                        }
                    }
                }
                catch (Throwable t) {
                    return false;
                }
                return false;
            }
            if (b) {
                return true;
            }
            return false;
        }
    }
    
    public static Bitmap zzar(final Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        try {
            if (!(boolean)zzkb.zzik().zzd(zznk.zzbbh)) {
                return zzu(((Activity)context).getWindow().getDecorView());
            }
            final Window window = ((Activity)context).getWindow();
            if (window != null) {
                return zzv(window.getDecorView().getRootView());
            }
        }
        catch (RuntimeException ex) {
            zzakb.zzb("Fail to capture screen shot", (Throwable)ex);
        }
        return null;
    }
    
    public static int zzas(final Context context) {
        final ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }
    
    @Nullable
    private static KeyguardManager zzat(final Context context) {
        final Object systemService = context.getSystemService("keyguard");
        if (systemService != null && systemService instanceof KeyguardManager) {
            return (KeyguardManager)systemService;
        }
        return null;
    }
    
    @TargetApi(16)
    public static boolean zzau(final Context context) {
        if (context != null && PlatformVersion.isAtLeastJellyBean()) {
            final KeyguardManager zzat = zzat(context);
            if (zzat != null && zzat.isKeyguardLocked()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean zzav(final Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        }
        catch (ClassNotFoundException ex) {
            return true;
        }
        catch (Throwable t) {
            zzakb.zzb("Error loading class.", t);
            zzbv.zzeo().zza(t, "AdUtil.isLiteSdk");
            return false;
        }
    }
    
    public static WebResourceResponse zzb(final HttpURLConnection httpURLConnection) throws IOException {
        zzbv.zzek();
        final String contentType = httpURLConnection.getContentType();
        if (TextUtils.isEmpty((CharSequence)contentType)) {
            final String trim = "";
        }
        else {
            final String trim = contentType.split(";")[0].trim();
        }
        zzbv.zzek();
        final String contentType2 = httpURLConnection.getContentType();
    Label_0102:
        while (true) {
            Label_0261: {
                if (TextUtils.isEmpty((CharSequence)contentType2)) {
                    break Label_0261;
                }
                final String[] split = contentType2.split(";");
                if (split.length != 1) {
                    for (int i = 1; i < split.length; ++i) {
                        if (split[i].trim().startsWith("charset")) {
                            final String[] split2 = split[i].trim().split("=");
                            if (split2.length > 1) {
                                final String trim2 = split2[1].trim();
                                break Label_0102;
                            }
                        }
                    }
                }
                break Label_0261;
                final Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                final HashMap hashMap = new HashMap<String, String>(headerFields.size());
                for (final Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                    if (entry.getKey() != null && entry.getValue() != null && entry.getValue().size() > 0) {
                        hashMap.put((String)entry.getKey(), (String)entry.getValue().get(0));
                    }
                }
                final String trim;
                String trim2 = null;
                return zzbv.zzem().zza(trim, trim2, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), (Map<String, String>)hashMap, httpURLConnection.getInputStream());
            }
            final String trim2 = "";
            continue Label_0102;
        }
    }
    
    @TargetApi(18)
    public static void zzb(final Context context, final Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle extras;
            if (intent.getExtras() != null) {
                extras = intent.getExtras();
            }
            else {
                extras = new Bundle();
            }
            extras.putBinder("android.support.customtabs.extra.SESSION", (IBinder)null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }
    
    @VisibleForTesting
    private static boolean zzb(final int n, final int n2, final int n3) {
        return Math.abs(n - n2) <= n3;
    }
    
    public static String zzcu(final String s) {
        return Uri.parse(s).buildUpon().query((String)null).build().toString();
    }
    
    public static int zzcv(String value) {
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException ex) {
            value = String.valueOf(ex);
            zzakb.zzdk(new StringBuilder(String.valueOf(value).length() + 22).append("Could not parse value:").append(value).toString());
            return 0;
        }
    }
    
    public static boolean zzcw(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }
    
    public static void zzd(final Context context, final String s, final String s2) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s2);
        zza(context, s, list);
    }
    
    public static void zzd(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
            return;
        }
        zzaki.zzb(runnable);
    }
    
    public static void zze(final Context context, final String s, final String s2) {
        try {
            final FileOutputStream openFileOutput = context.openFileOutput(s, 0);
            openFileOutput.write(s2.getBytes("UTF-8"));
            openFileOutput.close();
        }
        catch (Exception ex) {
            zzakb.zzb("Error writing to file in internal storage.", (Throwable)ex);
        }
    }
    
    @Nullable
    public static WebResourceResponse zzf(Context ex, final String s, final String s2) {
        try {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("User-Agent", zzbv.zzek().zzm((Context)ex, s));
            hashMap.put("Cache-Control", "max-stale=3600");
            ex = (ExecutionException)new zzalt((Context)ex).zzc(s2, hashMap).get(60L, TimeUnit.SECONDS);
            if (ex != null) {
                ex = (ExecutionException)new WebResourceResponse("application/javascript", "UTF-8", (InputStream)new ByteArrayInputStream(((String)ex).getBytes("UTF-8")));
                return (WebResourceResponse)ex;
            }
            goto Label_0109;
        }
        catch (InterruptedException ex2) {}
        catch (ExecutionException ex) {
            goto Label_0102;
        }
        catch (IOException ex) {
            goto Label_0102;
        }
        catch (TimeoutException ex) {
            goto Label_0102;
        }
    }
    
    private final JSONObject zzf(final Bundle bundle) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            this.zza(jsonObject, s, bundle.get(s));
        }
        return jsonObject;
    }
    
    public static int[] zzf(final Activity activity) {
        final Window window = activity.getWindow();
        if (window != null) {
            final View viewById = window.findViewById(16908290);
            if (viewById != null) {
                return new int[] { viewById.getWidth(), viewById.getHeight() };
            }
        }
        return zzrj();
    }
    
    public static Map<String, String> zzg(final Uri uri) {
        if (uri == null) {
            return null;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : zzbv.zzem().zzh(uri)) {
            hashMap.put(s, uri.getQueryParameter(s));
        }
        return hashMap;
    }
    
    public static boolean zzl(final Context context, final String s) {
        return Wrappers.packageManager(context).checkPermission(s, context.getPackageName()) == 0;
    }
    
    public static String zzn(final Context context, final String s) {
        try {
            return new String(IOUtils.readInputStreamFully((InputStream)context.openFileInput(s), true), "UTF-8");
        }
        catch (IOException ex) {
            zzakb.zzck("Error reading from internal storage.");
            return "";
        }
    }
    
    private static String zzrg() {
        final StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build$VERSION.RELEASE != null) {
            sb.append(" ").append(Build$VERSION.RELEASE);
        }
        sb.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/").append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }
    
    public static String zzrh() {
        return UUID.randomUUID().toString();
    }
    
    public static String zzri() {
        final String manufacturer = Build.MANUFACTURER;
        final String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return new StringBuilder(String.valueOf(manufacturer).length() + 1 + String.valueOf(model).length()).append(manufacturer).append(" ").append(model).toString();
    }
    
    private static int[] zzrj() {
        return new int[] { 0, 0 };
    }
    
    public static Bundle zzrk() {
        final Bundle bundle = new Bundle();
        try {
            if (zzkb.zzik().zzd(zznk.zzavm)) {
                final Debug$MemoryInfo debug$MemoryInfo = new Debug$MemoryInfo();
                Debug.getMemoryInfo(debug$MemoryInfo);
                bundle.putParcelable("debug_memory_info", (Parcelable)debug$MemoryInfo);
            }
            if (zzkb.zzik().zzd(zznk.zzavn)) {
                final Runtime runtime = Runtime.getRuntime();
                bundle.putLong("runtime_free_memory", runtime.freeMemory());
                bundle.putLong("runtime_max_memory", runtime.maxMemory());
                bundle.putLong("runtime_total_memory", runtime.totalMemory());
            }
            bundle.putInt("web_view_count", zzbv.zzeo().zzqg());
            return bundle;
        }
        catch (Exception ex) {
            zzakb.zzc("Unable to gather memory stats", (Throwable)ex);
            return bundle;
        }
    }
    
    public static Bitmap zzs(final View view) {
        view.setDrawingCacheEnabled(true);
        final Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }
    
    public static Bitmap zzt(final View view) {
        Bitmap zzv;
        if (view == null) {
            zzv = null;
        }
        else if ((zzv = zzv(view)) == null) {
            return zzu(view);
        }
        return zzv;
    }
    
    private static Bitmap zzu(@NonNull final View view) {
        try {
            final int width = view.getWidth();
            final int height = view.getHeight();
            if (width == 0 || height == 0) {
                zzakb.zzdk("Width or height of view is zero");
                return null;
            }
            final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap$Config.RGB_565);
            final Canvas canvas = new Canvas(bitmap);
            view.layout(0, 0, width, height);
            view.draw(canvas);
            return bitmap;
        }
        catch (RuntimeException ex) {
            zzakb.zzb("Fail to capture the webview", (Throwable)ex);
            return null;
        }
    }
    
    private static Bitmap zzv(@NonNull final View p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   android/view/View.isDrawingCacheEnabled:()Z
        //     4: istore_1       
        //     5: aload_0        
        //     6: iconst_1       
        //     7: invokevirtual   android/view/View.setDrawingCacheEnabled:(Z)V
        //    10: aload_0        
        //    11: invokevirtual   android/view/View.getDrawingCache:()Landroid/graphics/Bitmap;
        //    14: astore_2       
        //    15: aload_2        
        //    16: ifnull          47
        //    19: aload_2        
        //    20: invokestatic    android/graphics/Bitmap.createBitmap:(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
        //    23: astore_2       
        //    24: aload_0        
        //    25: iload_1        
        //    26: invokevirtual   android/view/View.setDrawingCacheEnabled:(Z)V
        //    29: aload_2        
        //    30: areturn        
        //    31: astore_0       
        //    32: aconst_null    
        //    33: astore_2       
        //    34: ldc_w           "Fail to capture the web view"
        //    37: aload_0        
        //    38: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    41: aload_2        
        //    42: areturn        
        //    43: astore_0       
        //    44: goto            34
        //    47: aconst_null    
        //    48: astore_2       
        //    49: goto            24
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      15     31     34     Ljava/lang/RuntimeException;
        //  19     24     31     34     Ljava/lang/RuntimeException;
        //  24     29     43     47     Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean zzw(View rootView) {
        rootView = rootView.getRootView();
        while (true) {
            Label_0032: {
                if (rootView == null) {
                    break Label_0032;
                }
                final Context context = rootView.getContext();
                if (!(context instanceof Activity)) {
                    break Label_0032;
                }
                final Activity activity = (Activity)context;
                if (activity == null) {
                    return false;
                }
                final Window window = activity.getWindow();
                WindowManager$LayoutParams attributes;
                if (window == null) {
                    attributes = null;
                }
                else {
                    attributes = window.getAttributes();
                }
                return attributes != null && (attributes.flags & 0x80000) != 0x0;
            }
            final Activity activity = null;
            continue;
        }
    }
    
    public static int zzx(@Nullable final View view) {
        if (view == null) {
            return -1;
        }
        ViewParent viewParent;
        for (viewParent = view.getParent(); viewParent != null && !(viewParent instanceof AdapterView); viewParent = viewParent.getParent()) {}
        if (viewParent == null) {
            return -1;
        }
        return ((AdapterView)viewParent).getPositionForView(view);
    }
    
    public final JSONObject zza(@Nullable final Bundle bundle, JSONObject zzf) {
        zzf = null;
        if (bundle == null) {
            return zzf;
        }
        try {
            zzf = this.zzf(bundle);
            return zzf;
        }
        catch (JSONException ex) {
            zzakb.zzb("Error converting Bundle to JSON", (Throwable)ex);
            return null;
        }
    }
    
    public final void zza(final Context context, final String s, final WebSettings webSettings) {
        webSettings.setUserAgentString(this.zzm(context, s));
    }
    
    public final void zza(final Context context, @Nullable final String s, final String s2, final Bundle bundle, final boolean b) {
        if (b) {
            zzbv.zzek();
            bundle.putString("device", zzri());
            bundle.putString("eids", TextUtils.join((CharSequence)",", (Iterable)zznk.zzjb()));
        }
        zzkb.zzif();
        zzamu.zza(context, s, s2, bundle, b, (zzamx)new zzakn(this, context, s));
    }
    
    public final void zza(final Context context, final String s, final boolean b, final HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", this.zzm(context, s));
        httpURLConnection.setUseCaches(false);
    }
    
    public final void zza(final Context context, final List<String> list) {
        if (!(context instanceof Activity) || TextUtils.isEmpty((CharSequence)zzbfw.zzbn(context))) {
            return;
        }
        if (list == null) {
            zzakb.v("Cannot ping urls: empty list.");
            return;
        }
        if (!zzoh.zzh(context)) {
            zzakb.v("Cannot ping url because custom tabs is not supported");
            return;
        }
        final zzoh zzoh = new zzoh();
        zzoh.zza(new zzakl(this, list, zzoh, context));
        zzoh.zzd((Activity)context);
    }
    
    public final boolean zza(final View view, final Context context) {
        PowerManager powerManager = null;
        final Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            powerManager = (PowerManager)applicationContext.getSystemService("power");
        }
        return this.zza(view, powerManager, zzat(context));
    }
    
    public final boolean zza(final View view, final PowerManager powerManager, final KeyguardManager keyguardManager) {
        boolean b;
        if (zzbv.zzek().zzcrn || (keyguardManager == null || !keyguardManager.inKeyguardRestrictedInputMode()) || ((boolean)zzkb.zzik().zzd(zznk.zzazu) && zzw(view))) {
            b = true;
        }
        else {
            b = false;
        }
        if (view.getVisibility() == 0 && view.isShown()) {
            boolean b2;
            if (powerManager == null || powerManager.isScreenOn()) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            if (b2 && b && (!(boolean)zzkb.zzik().zzd(zznk.zzazs) || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect()))) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean zzak(final Context context) {
        if (this.zzcro) {
            return false;
        }
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver((BroadcastReceiver)new zzakp(this, null), intentFilter);
        return this.zzcro = true;
    }
    
    public final boolean zzal(final Context context) {
        if (this.zzcrp) {
            return false;
        }
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver((BroadcastReceiver)new zzako(this, null), intentFilter);
        return this.zzcrp = true;
    }
    
    public final void zzb(final Context context, final String s, final String s2, final Bundle bundle, final boolean b) {
        if (zzkb.zzik().zzd(zznk.zzazx)) {
            this.zza(context, s, s2, bundle, b);
        }
    }
    
    public final boolean zzcx(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        try {
            synchronized (this) {
                if (this.zzcrq == null || !((String)zzkb.zzik().zzd(zznk.zzaxo)).equals(this.zzcrq.pattern())) {
                    this.zzcrq = Pattern.compile((String)zzkb.zzik().zzd(zznk.zzaxo));
                }
                return this.zzcrq.matcher(s).matches();
            }
        }
        catch (PatternSyntaxException ex) {
            return false;
        }
    }
    
    public final boolean zzcy(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        try {
            synchronized (this) {
                if (this.zzcrr == null || !((String)zzkb.zzik().zzd(zznk.zzaxp)).equals(this.zzcrr.pattern())) {
                    this.zzcrr = Pattern.compile((String)zzkb.zzik().zzd(zznk.zzaxp));
                }
                return this.zzcrr.matcher(s).matches();
            }
        }
        catch (PatternSyntaxException ex) {
            return false;
        }
    }
    
    public final int[] zzg(final Activity activity) {
        final int[] zzf = zzf(activity);
        zzkb.zzif();
        final int zzb = zzamu.zzb((Context)activity, zzf[0]);
        zzkb.zzif();
        return new int[] { zzb, zzamu.zzb((Context)activity, zzf[1]) };
    }
    
    public final int[] zzh(final Activity activity) {
        final Window window = activity.getWindow();
        while (true) {
            Label_0077: {
                if (window == null) {
                    break Label_0077;
                }
                final View viewById = window.findViewById(16908290);
                if (viewById == null) {
                    break Label_0077;
                }
                final int[] zzrj = { viewById.getTop(), viewById.getBottom() };
                zzkb.zzif();
                final int zzb = zzamu.zzb((Context)activity, zzrj[0]);
                zzkb.zzif();
                return new int[] { zzb, zzamu.zzb((Context)activity, zzrj[1]) };
            }
            final int[] zzrj = zzrj();
            continue;
        }
    }
    
    public final String zzm(final Context ex, final String s) {
        synchronized (this.mLock) {
            if (this.zzcpq != null) {
                return this.zzcpq;
            }
            if (s == null) {
                return zzrg();
            }
        }
        while (true) {
            try {
                this.zzcpq = zzbv.zzem().getDefaultUserAgent((Context)ex);
                Label_0176: {
                    if (TextUtils.isEmpty((CharSequence)this.zzcpq)) {
                        zzkb.zzif();
                        Label_0168: {
                            if (!zzamu.zzsh()) {
                                this.zzcpq = null;
                                zzakk.zzcrm.post((Runnable)new zzakm(this, (Context)ex));
                                while (this.zzcpq == null) {
                                    try {
                                        this.mLock.wait();
                                        continue;
                                    }
                                    catch (InterruptedException ex2) {
                                        this.zzcpq = zzrg();
                                        final String value = String.valueOf(this.zzcpq);
                                        String concat;
                                        if (value.length() != 0) {
                                            concat = "Interrupted, use default user agent: ".concat(value);
                                        }
                                        else {
                                            concat = new String("Interrupted, use default user agent: ");
                                        }
                                        zzakb.zzdk(concat);
                                        continue;
                                    }
                                    break Label_0168;
                                }
                                break Label_0176;
                            }
                        }
                        this.zzcpq = zzam((Context)ex);
                    }
                }
                final String value2 = String.valueOf(this.zzcpq);
                this.zzcpq = new StringBuilder(String.valueOf(value2).length() + 10 + String.valueOf(s).length()).append(value2).append(" (Mobile; ").append(s).toString();
                try {
                    if (Wrappers.packageManager((Context)ex).isCallerInstantApp()) {
                        this.zzcpq = String.valueOf(this.zzcpq).concat(";aia");
                    }
                    this.zzcpq = String.valueOf(this.zzcpq).concat(")");
                    // monitorexit(o)
                    return this.zzcpq;
                }
                catch (Exception ex) {
                    zzbv.zzeo().zza(ex, "AdUtil.getUserAgent");
                }
            }
            catch (Exception ex3) {
                continue;
            }
            break;
        }
    }
    
    public final JSONObject zzn(final Map<String, ?> map) throws JSONException {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            for (final String s : map.keySet()) {
                this.zza(jsonObject, s, map.get(s));
            }
        }
        catch (ClassCastException ex) {
            final String value = String.valueOf(ex.getMessage());
            String concat;
            if (value.length() != 0) {
                concat = "Could not convert map to JSON: ".concat(value);
            }
            else {
                concat = new String("Could not convert map to JSON: ");
            }
            throw new JSONException(concat);
        }
        return jsonObject;
    }
}
