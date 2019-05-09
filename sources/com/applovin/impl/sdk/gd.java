package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import com.adjust.sdk.Constants;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

public class gd extends AppLovinSdkUtils {
    /* renamed from: a */
    private static final char[] f2566a = "0123456789abcdef".toCharArray();
    /* renamed from: b */
    private static final char[] f2567b = "-'".toCharArray();

    /* renamed from: a */
    public static double m2928a(long j) {
        return ((double) j) / 1000.0d;
    }

    /* renamed from: a */
    public static float m2929a(float f) {
        return 1000.0f * f;
    }

    /* renamed from: a */
    public static int m2930a(Context context) {
        if (context != null) {
            Resources resources = context.getResources();
            if (resources != null) {
                Configuration configuration = resources.getConfiguration();
                if (configuration != null) {
                    return configuration.orientation;
                }
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m2931a(String str, int i) {
        return m2956d(str) ? Integer.parseInt(str) : i;
    }

    /* renamed from: a */
    public static int m2932a(JSONObject jSONObject) {
        int a = bu.m2385a(jSONObject, "video_completion_percent", -1, null);
        return (a < 0 || a > 100) ? 95 : a;
    }

    /* renamed from: a */
    public static Activity m2933a(View view, AppLovinSdk appLovinSdk) {
        if (view == null) {
            return null;
        }
        int i = 0;
        while (i < 1000) {
            int i2 = i + 1;
            try {
                Context context = view.getContext();
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                ViewParent parent = view.getParent();
                if (!(parent instanceof View)) {
                    return null;
                }
                view = (View) parent;
                i = i2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("AppLovinUtils", "Encountered error while retrieving activity from view", th);
            }
        }
        return null;
    }

    /* renamed from: a */
    public static Bitmap m2934a(Context context, int i, int i2) {
        int i3 = 1;
        Bitmap bitmap = null;
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2 = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), i);
            if (options.outHeight > i2 || options.outWidth > i2) {
                i3 = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log(((double) i2) / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
            }
            new Options().inSampleSize = i3;
            bitmap = BitmapFactory.decodeResource(context.getResources(), i);
            try {
                fileInputStream.close();
                fileInputStream2.close();
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            try {
                fileInputStream.close();
                fileInputStream2.close();
            } catch (Exception e3) {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
                fileInputStream2.close();
            } catch (Exception e4) {
            }
            throw th;
        }
        return bitmap;
    }

    /* renamed from: a */
    public static Bitmap m2935a(File file, int i) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        Throwable th;
        int i2 = 1;
        FileInputStream fileInputStream4 = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            fileInputStream = new FileInputStream(file);
            try {
                BitmapFactory.decodeStream(fileInputStream, null, options);
                fileInputStream.close();
                if (options.outHeight > i || options.outWidth > i) {
                    i2 = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log(((double) i) / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
                }
                Options options2 = new Options();
                options2.inSampleSize = i2;
                InputStream fileInputStream5 = new FileInputStream(file);
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream5, null, options2);
                    fileInputStream5.close();
                    try {
                        fileInputStream.close();
                        fileInputStream5.close();
                        return decodeStream;
                    } catch (Exception e) {
                        return decodeStream;
                    }
                } catch (Exception e2) {
                    InputStream inputStream = fileInputStream5;
                    fileInputStream2 = fileInputStream;
                    try {
                        fileInputStream2.close();
                        fileInputStream3.close();
                    } catch (Exception e3) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    InputStream inputStream2 = fileInputStream5;
                    try {
                        fileInputStream.close();
                        fileInputStream4.close();
                    } catch (Exception e4) {
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileInputStream3 = null;
                fileInputStream2 = fileInputStream;
                fileInputStream2.close();
                fileInputStream3.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream.close();
                fileInputStream4.close();
                throw th;
            }
        } catch (Exception e6) {
            fileInputStream3 = null;
            fileInputStream2 = null;
            fileInputStream2.close();
            fileInputStream3.close();
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            fileInputStream.close();
            fileInputStream4.close();
            throw th;
        }
    }

    /* renamed from: a */
    public static AppLovinAd m2936a(AppLovinAd appLovinAd, AppLovinSdk appLovinSdk) {
        if (!(appLovinAd instanceof aq)) {
            return appLovinAd;
        }
        aq aqVar = (aq) appLovinAd;
        AppLovinAd dequeueAd = ((AppLovinAdServiceImpl) appLovinSdk.getAdService()).dequeueAd(aqVar.mo3997t());
        appLovinSdk.getLogger().mo4172d("AppLovinUtils", "Dequeued ad for dummy ad: " + dequeueAd);
        if (dequeueAd == null) {
            return dequeueAd;
        }
        aqVar.m2274a(dequeueAd);
        ((C1227q) dequeueAd).m1774a(aqVar);
        return dequeueAd;
    }

    /* renamed from: a */
    public static String m2937a(String str) {
        return (str == null || str.length() <= 4) ? "NOKEY" : str.substring(str.length() - 4);
    }

    /* renamed from: a */
    public static String m2938a(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return m2939a(str, (Integer) appLovinSdkImpl.get(ea.f2425v), (String) appLovinSdkImpl.get(ea.f2424u));
    }

    /* renamed from: a */
    private static String m2939a(String str, Integer num, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("No algorithm specified");
        } else if (str == null || str.length() < 1) {
            return "";
        } else {
            if (str2.length() < 1 || ParametersKeys.ORIENTATION_NONE.equals(str2)) {
                return str;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(str2);
                instance.update(str.getBytes("UTF-8"));
                str = m2942a(instance.digest());
                return (str == null || num.intValue() <= 0) ? str : str.substring(0, Math.min(num.intValue(), str.length()));
            } catch (Throwable e) {
                throw new RuntimeException("Unknown algorithm \"" + str2 + "\"", e);
            } catch (Throwable e2) {
                throw new RuntimeException("Programming error: UTF-8 is not know encoding", e2);
            }
        }
    }

    /* renamed from: a */
    public static String m2940a(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return str2.replace("{PLACEMENT}", m2953c(str));
    }

    /* renamed from: a */
    static String m2941a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(RequestParameters.AMPERSAND);
            }
            stringBuilder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m2942a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("No data specified");
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i * 2] = f2566a[(bArr[i] & 240) >>> 4];
            cArr[(i * 2) + 1] = f2566a[bArr[i] & 15];
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static void m2943a(AppLovinAdLoadListener appLovinAdLoadListener, C1287n c1287n, int i, AppLovinSdk appLovinSdk) {
        if (appLovinAdLoadListener != null) {
            try {
                if (appLovinAdLoadListener instanceof at) {
                    ((at) appLovinAdLoadListener).mo4133a(c1287n, i);
                } else {
                    appLovinAdLoadListener.failedToReceiveAd(i);
                }
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("AppLovinUtils", "Unable process a failure to receive an ad", th);
            }
        }
    }

    /* renamed from: a */
    public static boolean m2944a() {
        Context staticApplicationContext = AppLovinSdkImpl.getStaticApplicationContext();
        if (staticApplicationContext == null) {
            return false;
        }
        Bundle d = m2955d(staticApplicationContext);
        return d != null && d.containsKey("applovin.sdk.verbose_logging");
    }

    /* renamed from: a */
    public static boolean m2945a(int i, int i2) {
        return (i & i2) != 0;
    }

    /* renamed from: a */
    public static boolean m2946a(an anVar, Context context, AppLovinSdkImpl appLovinSdkImpl) {
        return anVar != null && (anVar.mo4001b() || anVar.mo4002d() == null || appLovinSdkImpl.getFileManager().m2304a(anVar.mo4002d().getLastPathSegment(), context));
    }

    /* renamed from: a */
    public static boolean m2947a(AppLovinAd appLovinAd, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinAd == null) {
            appLovinSdkImpl.getLogger().userError("AppLovinUtils", "Failing ad display - ad is null.");
            return false;
        } else if (((C1227q) appLovinAd).mo3995m() == C1288o.DIRECT) {
            return true;
        } else {
            if (ag.m2242a(appLovinSdkImpl.getApplicationContext(), appLovinSdkImpl) || ((Boolean) appLovinSdkImpl.get(ea.cG)).booleanValue()) {
                return true;
            }
            appLovinSdkImpl.getLogger().userError("AppLovinUtils", "Failing ad display due to no internet connection.");
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m2948a(String str, List<String> list) {
        for (String startsWith : list) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static long m2949b(float f) {
        return (long) Math.round(f);
    }

    /* renamed from: b */
    public static String m2950b(String str) {
        return m2939a(str, Integer.valueOf(-1), Constants.SHA1);
    }

    /* renamed from: b */
    public static boolean m2951b(Context context) {
        if (context == null) {
            context = AppLovinSdkImpl.getStaticApplicationContext();
        }
        if (context == null) {
            return false;
        }
        Bundle d = m2955d(context);
        return d != null && d.getBoolean("applovin.sdk.test_ads", false);
    }

    /* renamed from: c */
    public static long m2952c(float f) {
        return m2949b(m2929a(f));
    }

    /* renamed from: c */
    public static String m2953c(String str) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable e) {
            throw new UnsupportedOperationException(e);
        }
    }

    /* renamed from: c */
    public static boolean m2954c(Context context) {
        if (context == null) {
            context = AppLovinSdkImpl.getStaticApplicationContext();
        }
        if (context == null) {
            return false;
        }
        Bundle d = m2955d(context);
        return d != null && d.getBoolean("applovin.sdk.verbose_logging", false);
    }

    /* renamed from: d */
    public static Bundle m2955d(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (Throwable e) {
            Log.e(AppLovinLogger.SDK_TAG, "Unable to retrieve application metadata", e);
            return null;
        }
    }

    /* renamed from: d */
    public static boolean m2956d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        char charAt = str.charAt(0);
        if (charAt == '-' || charAt == '+') {
            int i = 1;
        } else {
            boolean z = false;
        }
        int length = str.length();
        if (i == 1 && length == 1) {
            return false;
        }
        while (i < length) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* renamed from: e */
    public static int m2957e(String str) {
        return m2931a(str, 0);
    }
}
