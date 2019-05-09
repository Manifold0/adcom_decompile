package com.applovin.impl.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.places.model.PlaceFields;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class ah {
    /* renamed from: e */
    private static String f2020e;
    /* renamed from: f */
    private static String f2021f;
    /* renamed from: g */
    private static int f2022g;
    /* renamed from: a */
    private final AppLovinSdkImpl f2023a;
    /* renamed from: b */
    private final AppLovinLogger f2024b;
    /* renamed from: c */
    private final Context f2025c;
    /* renamed from: d */
    private final Map<Class, Object> f2026d;

    ah(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f2023a = appLovinSdkImpl;
        this.f2024b = appLovinSdkImpl.getLogger();
        this.f2025c = appLovinSdkImpl.getApplicationContext();
        this.f2026d = Collections.synchronizedMap(new HashMap());
    }

    /* renamed from: a */
    private am m2250a(am amVar) {
        String o;
        if (amVar == null) {
            amVar = new am();
        }
        amVar.f2061w = ac.m2209a(this.f2025c);
        amVar.f2062x = ac.m2213b(this.f2025c);
        if (((Boolean) this.f2023a.get(ea.cp)).booleanValue()) {
            amVar.f2056r = m2258i();
        } else {
            amVar.f2056r = null;
        }
        if (((Boolean) this.f2023a.get(ea.co)).booleanValue()) {
            amVar.f2055q = m2260k();
        }
        try {
            AudioManager audioManager = (AudioManager) this.f2025c.getSystemService("audio");
            if (audioManager != null) {
                amVar.f2057s = (int) (((float) audioManager.getStreamVolume(3)) * ((Float) this.f2023a.get(ea.cv)).floatValue());
            }
        } catch (Throwable th) {
            this.f2024b.mo4174e("DataCollector", "Unable to collect volume", th);
        }
        if (((Boolean) this.f2023a.get(ea.cy)).booleanValue()) {
            if (f2020e == null) {
                o = m2264o();
                if (AppLovinSdkUtils.isValidString(o)) {
                    f2020e = o;
                } else {
                    f2020e = "";
                }
            }
            if (AppLovinSdkUtils.isValidString(f2020e)) {
                amVar.f2058t = f2020e;
            }
        }
        o = (String) this.f2023a.getSettingsManager().m2666a(ea.cm);
        if (f2021f == null || !o.equalsIgnoreCase(f2021f)) {
            try {
                f2021f = o;
                PackageInfo packageInfo = this.f2025c.getPackageManager().getPackageInfo(o, 0);
                amVar.f2054p = packageInfo.versionCode;
                f2022g = packageInfo.versionCode;
            } catch (Throwable th2) {
                f2022g = 0;
            }
        } else {
            amVar.f2054p = f2022g;
        }
        return amVar;
    }

    /* renamed from: a */
    static boolean m2251a(String str, Context context) {
        if (str == null) {
            throw new IllegalArgumentException("No permission name specified");
        } else if (context != null) {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } else {
            throw new IllegalArgumentException("No context specified");
        }
    }

    /* renamed from: a */
    private boolean m2252a(String str, ec<String> ecVar) {
        for (String startsWith : aa.m2193a((String) this.f2023a.get((ec) ecVar))) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private String m2254b(String str) {
        int length = str.length();
        int[] iArr = new int[]{11, 12, 10, 3, 2, 1, 15, 10, 15, 14};
        int length2 = iArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = str.charAt(i);
            for (int i2 = length2 - 1; i2 >= 0; i2--) {
                cArr[i] = (char) (cArr[i] ^ iArr[i2]);
            }
        }
        String str2 = new String(cArr);
        return str2 != null ? str2 : "";
    }

    /* renamed from: f */
    private String m2255f() {
        String str = ParametersKeys.ORIENTATION_NONE;
        try {
            int a = gd.m2930a(this.f2025c);
            return a == 1 ? "portrait" : a == 2 ? "landscape" : str;
        } catch (Throwable th) {
            this.f2023a.getLogger().mo4174e("DataCollector", "Encountered error while attempting to collect application orientation", th);
            return str;
        }
    }

    /* renamed from: g */
    private aj m2256g() {
        try {
            ContentResolver contentResolver = this.f2025c.getContentResolver();
            String string = Secure.getString(contentResolver, TapjoyConstants.TJC_ADVERTISING_ID);
            aj ajVar = new aj();
            if (string == null) {
                string = "";
            }
            ajVar.f2031b = string;
            ajVar.f2030a = Secure.getInt(contentResolver, "limit_ad_tracking") != 0;
            return ajVar;
        } catch (Throwable e) {
            this.f2024b.mo4174e("DataCollector", "Unable to determine if FireOS limited ad tracking is turned on", e);
            return null;
        } catch (Throwable e2) {
            this.f2024b.mo4174e("DataCollector", "Unable to collect FireOS IDFA", e2);
            return null;
        }
    }

    /* renamed from: h */
    private aj m2257h() {
        try {
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (cls != null) {
                Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.f2025c});
                if (invoke != null) {
                    Class cls2 = invoke.getClass();
                    Object invoke2 = cls2.getMethod(RequestParameters.isLAT, (Class[]) null).invoke(invoke, (Object[]) null);
                    String str = (String) cls2.getMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
                    String str2 = str == null ? "" : str;
                    aj ajVar = new aj();
                    ajVar.f2030a = ((Boolean) invoke2).booleanValue();
                    ajVar.f2031b = str2;
                    return ajVar;
                }
            }
        } catch (Throwable e) {
            this.f2024b.userError("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e);
        } catch (Throwable e2) {
            this.f2024b.mo4174e("DataCollector", "Could not collect Google Advertising ID - this will negatively impact your eCPMs! Please integrate the Google Play Services SDK into your application. More info can be found online at http://developer.android.com/google/play-services/setup.html. If you're sure you've integrated the SDK and are still seeing this message, you may need to add a ProGuard exception: -keep public class com.google.android.gms.** { public protected *; }", e2);
        }
        return new aj();
    }

    /* renamed from: i */
    private al m2258i() {
        int i = -1;
        try {
            al alVar = new al();
            Intent registerReceiver = this.f2025c.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver != null ? registerReceiver.getIntExtra("level", -1) : -1;
            int intExtra2 = registerReceiver != null ? registerReceiver.getIntExtra("scale", -1) : -1;
            if (intExtra <= 0 || intExtra2 <= 0) {
                alVar.f2038b = -1;
            } else {
                alVar.f2038b = (int) ((((float) intExtra) / ((float) intExtra2)) * 100.0f);
            }
            if (registerReceiver != null) {
                i = registerReceiver.getIntExtra("status", -1);
            }
            alVar.f2037a = i;
            return alVar;
        } catch (Throwable th) {
            this.f2024b.mo4174e("DataCollector", "Unable to collect battery info", th);
            return null;
        }
    }

    /* renamed from: j */
    private double m2259j() {
        return ((double) Math.round((((double) TimeZone.getDefault().getOffset(new Date().getTime())) * 10.0d) / 3600000.0d)) / 10.0d;
    }

    /* renamed from: k */
    private boolean m2260k() {
        try {
            return m2261l() || m2262m();
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: l */
    private boolean m2261l() {
        String str = "lz}$blpz";
        str = Build.TAGS;
        return str != null && str.contains(m2254b("lz}$blpz"));
    }

    /* renamed from: m */
    private boolean m2262m() {
        String[] strArr = new String[]{"&zpz}ld&hyy&Z|yl{|zl{'hyb", "&zk`g&z|", "&zpz}ld&k`g&z|", "&zpz}ld&qk`g&z|", "&mh}h&efjhe&qk`g&z|", "&mh}h&efjhe&k`g&z|", "&zpz}ld&zm&qk`g&z|", "&zpz}ld&k`g&oh`ezhol&z|", "&mh}h&efjhe&z|"};
        for (String b : strArr) {
            if (new File(m2254b(b)).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: n */
    private boolean m2263n() {
        return m2252a(Build.DEVICE, ea.cr) || m2252a(Build.HARDWARE, ea.cq) || m2252a(Build.MANUFACTURER, ea.cs) || m2252a(Build.MODEL, ea.ct);
    }

    /* renamed from: o */
    private String m2264o() {
        AtomicReference atomicReference = new AtomicReference();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Handler(this.f2025c.getMainLooper()).post(new ai(this, atomicReference, countDownLatch));
        try {
            countDownLatch.await(((Long) this.f2023a.get(ea.cz)).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
        }
        return (String) atomicReference.get();
    }

    /* renamed from: a */
    am m2265a() {
        Object obj = this.f2026d.get(am.class);
        if (obj != null) {
            return m2250a((am) obj);
        }
        am amVar = new am();
        amVar.f2049k = Locale.getDefault();
        amVar.f2039a = Build.MODEL;
        amVar.f2040b = VERSION.RELEASE;
        amVar.f2041c = m2267b();
        amVar.f2042d = Build.MANUFACTURER;
        amVar.f2043e = Build.BRAND;
        amVar.f2044f = Build.HARDWARE;
        amVar.f2046h = VERSION.SDK_INT;
        amVar.f2045g = Build.DEVICE;
        amVar.f2050l = m2255f();
        amVar.f2053o = m2259j();
        amVar.f2059u = m2263n();
        try {
            amVar.f2060v = ((SensorManager) this.f2025c.getSystemService("sensor")).getDefaultSensor(4) != null;
        } catch (Throwable th) {
            this.f2024b.mo4174e("DataCollector", "Unable to retrieve gyroscope availability", th);
        }
        if (m2266a("android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = (TelephonyManager) this.f2025c.getSystemService(PlaceFields.PHONE);
            if (telephonyManager != null) {
                amVar.f2047i = telephonyManager.getSimCountryIso().toUpperCase(Locale.ENGLISH);
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                try {
                    amVar.f2048j = URLEncoder.encode(networkOperatorName, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    amVar.f2048j = networkOperatorName;
                }
            }
        }
        try {
            DisplayMetrics displayMetrics = this.f2025c.getResources().getDisplayMetrics();
            amVar.f2051m = displayMetrics.density;
            amVar.f2052n = displayMetrics.densityDpi;
        } catch (Throwable th2) {
        }
        this.f2026d.put(am.class, amVar);
        return m2250a(amVar);
    }

    /* renamed from: a */
    boolean m2266a(String str) {
        return m2251a(str, this.f2025c);
    }

    /* renamed from: b */
    String m2267b() {
        return this.f2023a.isFireOS() ? "fireos" : TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
    }

    /* renamed from: c */
    am m2268c() {
        return m2250a(null);
    }

    /* renamed from: d */
    ak m2269d() {
        String installerPackageName;
        PackageInfo packageInfo;
        PackageInfo packageInfo2;
        ak akVar;
        Object obj = this.f2026d.get(ak.class);
        if (obj != null) {
            return (ak) obj;
        }
        ApplicationInfo applicationInfo = this.f2025c.getApplicationInfo();
        long lastModified = new File(applicationInfo.sourceDir).lastModified();
        PackageManager packageManager = this.f2025c.getPackageManager();
        try {
            PackageInfo packageInfo3 = packageManager.getPackageInfo(this.f2025c.getPackageName(), 0);
            try {
                installerPackageName = packageManager.getInstallerPackageName(applicationInfo.packageName);
                packageInfo = packageInfo3;
            } catch (NameNotFoundException e) {
                packageInfo2 = packageInfo3;
                packageInfo = packageInfo2;
                installerPackageName = null;
                akVar = new ak();
                akVar.f2034c = applicationInfo.packageName;
                if (installerPackageName == null) {
                    installerPackageName = "";
                }
                akVar.f2035d = installerPackageName;
                akVar.f2036e = lastModified;
                akVar.f2032a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
                akVar.f2033b = packageInfo != null ? "" : packageInfo.versionName;
                this.f2026d.put(ak.class, akVar);
                return akVar;
            }
        } catch (NameNotFoundException e2) {
            packageInfo2 = null;
            packageInfo = packageInfo2;
            installerPackageName = null;
            akVar = new ak();
            akVar.f2034c = applicationInfo.packageName;
            if (installerPackageName == null) {
                installerPackageName = "";
            }
            akVar.f2035d = installerPackageName;
            akVar.f2036e = lastModified;
            akVar.f2032a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
            if (packageInfo != null) {
            }
            akVar.f2033b = packageInfo != null ? "" : packageInfo.versionName;
            this.f2026d.put(ak.class, akVar);
            return akVar;
        }
        akVar = new ak();
        akVar.f2034c = applicationInfo.packageName;
        if (installerPackageName == null) {
            installerPackageName = "";
        }
        akVar.f2035d = installerPackageName;
        akVar.f2036e = lastModified;
        akVar.f2032a = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        if (packageInfo != null) {
        }
        akVar.f2033b = packageInfo != null ? "" : packageInfo.versionName;
        this.f2026d.put(ak.class, akVar);
        return akVar;
    }

    /* renamed from: e */
    aj m2270e() {
        aj h;
        if (this.f2023a.isFireOS()) {
            aj g = m2256g();
            h = g == null ? ((Boolean) this.f2023a.get(ea.ch)).booleanValue() ? m2257h() : new aj() : g;
        } else {
            h = m2257h();
        }
        if (!((Boolean) this.f2023a.get(ea.bL)).booleanValue()) {
            return new aj();
        }
        if (!h.f2030a || ((Boolean) this.f2023a.get(ea.bK)).booleanValue()) {
            return h;
        }
        h.f2031b = "";
        return h;
    }
}
