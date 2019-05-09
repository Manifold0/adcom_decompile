package com.facebook.ads.internal.p045n;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Base64OutputStream;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.ads.internal.p025w.p026b.C2566b;
import com.facebook.ads.internal.p025w.p026b.C2574f;
import com.facebook.ads.internal.p025w.p026b.C2574f.C2573a;
import com.facebook.ads.internal.p025w.p026b.C2578h;
import com.facebook.ads.internal.p025w.p026b.C2579i;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2585o;
import com.facebook.ads.internal.p025w.p026b.C2595u;
import com.facebook.ads.internal.p025w.p026b.C2597v;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import com.facebook.ads.internal.p038g.C2002b;
import com.facebook.ads.internal.p042l.C2042a;
import com.facebook.ads.internal.p042l.C2043b;
import com.facebook.ads.internal.p046v.p047a.C2138a;
import com.facebook.ads.internal.p046v.p047a.C2138a.C2055a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.settings.AdSdkVersion;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.tapjoy.TapjoyConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.DeflaterOutputStream;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.n.d */
public class C2057d {
    /* renamed from: a */
    private static final AtomicBoolean f4607a = new AtomicBoolean();
    /* renamed from: b */
    private static final AtomicInteger f4608b = new AtomicInteger(0);
    /* renamed from: c */
    private static String f4609c = null;
    /* renamed from: d */
    private static final C2573a f4610d = C2574f.m6634a();
    @Nullable
    /* renamed from: e */
    private static String f4611e = null;
    /* renamed from: f */
    private final Context f4612f;
    /* renamed from: g */
    private final C2043b f4613g;

    public C2057d(Context context, boolean z) {
        this.f4612f = context;
        this.f4613g = new C2043b(context);
        C2057d.m5012a(context, z);
    }

    /* renamed from: a */
    private static String m5008a(Context context, String str, String str2) {
        Class cls = Class.forName(str);
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(str2)});
        declaredConstructor.setAccessible(true);
        try {
            String str3 = (String) cls.getMethod("getUserAgentString", new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{context, null}), new Object[0]);
            return str3;
        } finally {
            declaredConstructor.setAccessible(false);
        }
    }

    /* renamed from: a */
    public static String m5009a(C2043b c2043b, Context context, boolean z) {
        return C2057d.m5014b(context, z) + " [FBAN/AudienceNetworkForAndroid;FBSN/" + Constants.JAVASCRIPT_INTERFACE_NAME + ";FBSV/" + C2043b.f4560a + ";FBAB/" + c2043b.m4958f() + ";FBAV/" + c2043b.m4959g() + ";FBBV/" + c2043b.m4960h() + ";FBVS/" + AdSdkVersion.BUILD + ";FBLC/" + Locale.getDefault().toString() + RequestParameters.RIGHT_BRACKETS;
    }

    @WorkerThread
    /* renamed from: a */
    public static void m5011a(Context context) {
        if (context != null) {
            final Context applicationContext = context.getApplicationContext();
            C2138a.m5453a(new C2055a() {
                /* renamed from: a */
                public Map<String, String> mo5466a() {
                    Map<String, String> hashMap = new HashMap();
                    if (!C2002b.f4432c) {
                        hashMap.put("X-FB-Pool-Routing-Token", new C2057d(applicationContext, true).m5018a());
                    }
                    return hashMap;
                }
            });
        }
    }

    /* renamed from: a */
    private static void m5012a(final Context context, boolean z) {
        if (f4608b.compareAndSet(0, 1)) {
            try {
                C2585o.m6651a();
                final SharedPreferences sharedPreferences = context.getSharedPreferences(C2616a.m6730a("FBAdPrefs", context), 0);
                final String str = "AFP;" + new C2043b(context).m4959g();
                f4609c = sharedPreferences.getString(str, null);
                Object futureTask = new FutureTask(new Callable<Boolean>() {
                    /* renamed from: a */
                    public Boolean m5004a() {
                        C2057d.f4609c = C2057d.m5013b(context, context.getPackageName());
                        sharedPreferences.edit().putString(str, C2057d.f4609c).apply();
                        C2057d.f4608b.set(2);
                        return Boolean.valueOf(true);
                    }

                    public /* synthetic */ Object call() {
                        return m5004a();
                    }
                });
                Executors.newSingleThreadExecutor().submit(futureTask);
                if (z) {
                    futureTask.get();
                }
            } catch (Exception e) {
                f4608b.set(0);
            }
        }
    }

    @Nullable
    /* renamed from: b */
    private static String m5013b(Context context, String str) {
        try {
            return C2579i.m6640a(new File(context.getPackageManager().getApplicationInfo(str, 0).sourceDir));
        } catch (Exception e) {
            if (f4607a.compareAndSet(false, true)) {
                C2625a.m6741b(context.getApplicationContext(), MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C2626b.f6510A, e);
            }
            return null;
        }
    }

    @Nullable
    /* renamed from: b */
    private static String m5014b(Context context, boolean z) {
        if (context == null) {
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        if (z) {
            return System.getProperty("http.agent");
        }
        if (f4611e != null) {
            return f4611e;
        }
        synchronized (C2057d.class) {
            if (f4611e != null) {
                String str = f4611e;
                return str;
            }
            if (VERSION.SDK_INT >= 17) {
                try {
                    f4611e = WebSettings.getDefaultUserAgent(context);
                    str = f4611e;
                    return str;
                } catch (Exception e) {
                }
            }
            try {
                f4611e = C2057d.m5008a(context, "android.webkit.WebSettings", "android.webkit.WebView");
            } catch (Exception e2) {
                try {
                    f4611e = C2057d.m5008a(context, "android.webkit.WebSettingsClassic", "android.webkit.WebViewClassic");
                } catch (Exception e3) {
                    WebView webView = new WebView(context.getApplicationContext());
                    f4611e = webView.getSettings().getUserAgentString();
                    webView.destroy();
                }
            }
            return f4611e;
        }
    }

    /* renamed from: b */
    public static Map<String, String> m5015b(Context context) {
        try {
            return new C2057d(context, false).m5019b();
        } catch (Throwable th) {
            C2625a.m6739a(th);
            return new HashMap();
        }
    }

    @WorkerThread
    /* renamed from: a */
    public String m5018a() {
        ByteArrayOutputStream byteArrayOutputStream;
        Base64OutputStream base64OutputStream;
        Throwable e;
        DeflaterOutputStream deflaterOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        Base64OutputStream base64OutputStream2 = null;
        C2057d.m5012a(this.f4612f, true);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                base64OutputStream = new Base64OutputStream(byteArrayOutputStream, 0);
            } catch (IOException e2) {
                e = e2;
                deflaterOutputStream = null;
                byteArrayOutputStream2 = byteArrayOutputStream;
                try {
                    throw new RuntimeException("Failed to build user token", e);
                } catch (Throwable th) {
                    e = th;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    base64OutputStream = base64OutputStream2;
                    if (deflaterOutputStream != null) {
                        try {
                            deflaterOutputStream.close();
                        } catch (IOException e3) {
                            throw e;
                        }
                    }
                    if (base64OutputStream != null) {
                        base64OutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                deflaterOutputStream = null;
                base64OutputStream = null;
                if (deflaterOutputStream != null) {
                    deflaterOutputStream.close();
                }
                if (base64OutputStream != null) {
                    base64OutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw e;
            }
            try {
                deflaterOutputStream = new DeflaterOutputStream(base64OutputStream);
                try {
                    Map b = m5019b();
                    if (TextUtils.isEmpty(C2002b.f4431b)) {
                        C2002b.m4828a(this.f4612f);
                        C2057d.m5011a(this.f4612f);
                    }
                    b.put("IDFA", C2002b.f4431b);
                    b.put("USER_AGENT", C2057d.m5009a(this.f4613g, this.f4612f, false));
                    deflaterOutputStream.write(new JSONObject(b).toString().getBytes());
                    deflaterOutputStream.close();
                    String replaceAll = byteArrayOutputStream.toString().replaceAll("\n", "");
                    if (deflaterOutputStream != null) {
                        try {
                            deflaterOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    if (base64OutputStream != null) {
                        base64OutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    return replaceAll;
                } catch (IOException e5) {
                    e = e5;
                    base64OutputStream2 = base64OutputStream;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    throw new RuntimeException("Failed to build user token", e);
                } catch (Throwable th3) {
                    e = th3;
                    if (deflaterOutputStream != null) {
                        deflaterOutputStream.close();
                    }
                    if (base64OutputStream != null) {
                        base64OutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw e;
                }
            } catch (IOException e6) {
                e = e6;
                deflaterOutputStream = null;
                base64OutputStream2 = base64OutputStream;
                byteArrayOutputStream2 = byteArrayOutputStream;
                throw new RuntimeException("Failed to build user token", e);
            } catch (Throwable th4) {
                e = th4;
                deflaterOutputStream = null;
                if (deflaterOutputStream != null) {
                    deflaterOutputStream.close();
                }
                if (base64OutputStream != null) {
                    base64OutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw e;
            }
        } catch (IOException e7) {
            e = e7;
            deflaterOutputStream = null;
            byteArrayOutputStream2 = null;
            throw new RuntimeException("Failed to build user token", e);
        } catch (Throwable th5) {
            e = th5;
            deflaterOutputStream = null;
            base64OutputStream = null;
            byteArrayOutputStream = null;
            if (deflaterOutputStream != null) {
                deflaterOutputStream.close();
            }
            if (base64OutputStream != null) {
                base64OutputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw e;
        }
    }

    /* renamed from: b */
    public Map<String, String> m5019b() {
        C2057d.m5012a(this.f4612f, false);
        C2042a.m4945a(this.f4612f);
        Map<String, String> hashMap = new HashMap();
        hashMap.put("SDK", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        hashMap.put("SDK_VERSION", AdSdkVersion.BUILD);
        hashMap.put("LOCALE", Locale.getDefault().toString());
        float f = C2600x.f6420b;
        int i = this.f4612f.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.f4612f.getResources().getDisplayMetrics().heightPixels;
        hashMap.put("DENSITY", String.valueOf(f));
        hashMap.put("SCREEN_WIDTH", String.valueOf((int) (((float) i) / f)));
        hashMap.put("SCREEN_HEIGHT", String.valueOf((int) (((float) i2) / f)));
        hashMap.put("ATTRIBUTION_ID", C2002b.f4430a);
        hashMap.put("ID_SOURCE", C2002b.f4433d);
        hashMap.put("OS", Constants.JAVASCRIPT_INTERFACE_NAME);
        hashMap.put("OSVERS", C2043b.f4560a);
        hashMap.put("BUNDLE", this.f4613g.m4958f());
        hashMap.put("APPNAME", this.f4613g.m4956d());
        hashMap.put("APPVERS", this.f4613g.m4959g());
        hashMap.put("APPBUILD", String.valueOf(this.f4613g.m4960h()));
        hashMap.put("CARRIER", this.f4613g.m4955c());
        hashMap.put("MAKE", this.f4613g.m4953a());
        hashMap.put("MODEL", this.f4613g.m4954b());
        hashMap.put("ROOTED", String.valueOf(f4610d.f6339d));
        hashMap.put("INSTALLER", this.f4613g.m4957e());
        hashMap.put("SDK_CAPABILITY", C2566b.m6618b());
        hashMap.put("NETWORK_TYPE", String.valueOf(C2595u.m6664a(this.f4612f).f6394g));
        hashMap.put("SESSION_TIME", C2597v.m6667a(C2585o.m6652b()));
        hashMap.put("SESSION_ID", C2585o.m6653c());
        if (f4609c != null) {
            hashMap.put("AFP", f4609c);
        }
        String a = C2574f.m6635a(this.f4612f);
        if (a != null) {
            hashMap.put("ASHAS", a);
        }
        hashMap.put("UNITY", String.valueOf(C2578h.m6638a(this.f4612f)));
        a = AdInternalSettings.getMediationService();
        if (a != null) {
            hashMap.put("MEDIATION_SERVICE", a);
        }
        hashMap.put("ACCESSIBILITY_ENABLED", String.valueOf(this.f4613g.m4961i()));
        if (this.f4613g.m4962j() != -1) {
            hashMap.put("APP_MIN_SDK_VERSION", String.valueOf(this.f4613g.m4962j()));
        }
        hashMap.put("VALPARAMS", C2052b.m4999a(this.f4612f));
        hashMap.put("ANALOG", C2581k.m6645a(C2042a.m4944a()));
        hashMap.put("PROCESS", C2053c.m5002a(this.f4612f));
        return hashMap;
    }
}
