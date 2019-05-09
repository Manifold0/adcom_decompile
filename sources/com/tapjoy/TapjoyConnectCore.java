package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.places.model.PlaceFields;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.C2999y;
import com.tapjoy.internal.aq;
import com.tapjoy.internal.bm;
import com.tapjoy.internal.bs;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.dc;
import com.tapjoy.internal.dx;
import com.tapjoy.internal.ed;
import com.tapjoy.internal.ee;
import com.tapjoy.internal.ek;
import com.tapjoy.internal.er;
import com.tapjoy.internal.er.C2909a;
import com.tapjoy.internal.ev;
import com.tapjoy.internal.fd;
import com.tapjoy.internal.fh;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.gf;
import com.tapjoy.internal.gq;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import org.w3c.dom.Document;

public class TapjoyConnectCore {
    /* renamed from: A */
    private static float f7032A = 1.0f;
    /* renamed from: B */
    private static int f7033B = 1;
    /* renamed from: C */
    private static String f7034C = "";
    /* renamed from: D */
    private static String f7035D = "";
    public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0f;
    /* renamed from: E */
    private static String f7036E = "";
    /* renamed from: F */
    private static String f7037F = "";
    /* renamed from: G */
    private static String f7038G = "";
    /* renamed from: H */
    private static String f7039H = "";
    /* renamed from: I */
    private static String f7040I = "";
    /* renamed from: J */
    private static String f7041J = "";
    /* renamed from: K */
    private static String f7042K = "";
    /* renamed from: L */
    private static String f7043L = "";
    /* renamed from: M */
    private static String f7044M = "";
    /* renamed from: N */
    private static String f7045N = "native";
    /* renamed from: O */
    private static String f7046O = "";
    /* renamed from: P */
    private static String f7047P = "";
    /* renamed from: Q */
    private static float f7048Q = 1.0f;
    /* renamed from: R */
    private static boolean f7049R = false;
    /* renamed from: S */
    private static String f7050S = "";
    /* renamed from: T */
    private static String f7051T = "";
    /* renamed from: U */
    private static String f7052U = "";
    /* renamed from: V */
    private static String f7053V = "";
    /* renamed from: W */
    private static String f7054W = null;
    /* renamed from: Z */
    private static long f7055Z = 0;
    /* renamed from: a */
    protected static int f7056a = 0;
    private static Integer aA;
    private static Long aB;
    private static Long aC;
    private static Long aD;
    private static String aE;
    private static Integer aF;
    private static Double aG;
    private static Double aH;
    private static Long aI;
    private static Integer aJ;
    private static Integer aK;
    private static Integer aL;
    private static String aM;
    private static String aN;
    private static String aO;
    private static boolean ab;
    private static PackageManager ac;
    private static Hashtable ae = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    private static String af = "";
    private static Map ag = new ConcurrentHashMap();
    private static String ah;
    private static String ai;
    private static String aj;
    private static String ak;
    private static Integer al;
    private static String am;
    private static String an;
    private static Long ao;
    private static String ap;
    private static Integer aq;
    private static Integer ar;
    private static String as;
    private static String at;
    private static String au;
    private static String av;
    private static String aw;
    private static Set ax;
    private static Integer ay;
    private static Integer az;
    /* renamed from: b */
    protected static int f7057b = 0;
    /* renamed from: c */
    protected static String f7058c = "";
    /* renamed from: d */
    protected static boolean f7059d;
    /* renamed from: e */
    protected static String f7060e = "";
    /* renamed from: f */
    protected static String f7061f = "";
    /* renamed from: g */
    private static Context f7062g = null;
    /* renamed from: h */
    private static String f7063h = null;
    /* renamed from: i */
    private static TapjoyConnectCore f7064i = null;
    /* renamed from: j */
    private static TapjoyURLConnection f7065j = null;
    /* renamed from: k */
    private static TJConnectListener f7066k = null;
    /* renamed from: l */
    private static TJSetUserIDListener f7067l = null;
    /* renamed from: m */
    private static Vector f7068m = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
    /* renamed from: n */
    private static String f7069n = "";
    /* renamed from: o */
    private static String f7070o = "";
    /* renamed from: p */
    private static String f7071p = "";
    /* renamed from: q */
    private static String f7072q = "";
    /* renamed from: r */
    private static String f7073r = "";
    /* renamed from: s */
    private static String f7074s = "";
    /* renamed from: t */
    private static String f7075t = "";
    /* renamed from: u */
    private static String f7076u = "";
    /* renamed from: v */
    private static String f7077v = "";
    /* renamed from: w */
    private static String f7078w = "";
    /* renamed from: x */
    private static String f7079x = "";
    /* renamed from: y */
    private static String f7080y = "";
    /* renamed from: z */
    private static int f7081z = 1;
    /* renamed from: X */
    private long f7082X = 0;
    /* renamed from: Y */
    private boolean f7083Y = false;
    private boolean aa = false;
    private TapjoyGpsHelper ad;

    /* renamed from: com.tapjoy.TapjoyConnectCore$1 */
    class C28181 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TapjoyConnectCore f7029a;

        C28181(TapjoyConnectCore tapjoyConnectCore) {
            this.f7029a = tapjoyConnectCore;
        }

        public final void run() {
            this.f7029a.ad.loadAdvertisingId();
            if (this.f7029a.ad.isGooglePlayServicesAvailable() && this.f7029a.ad.isGooglePlayManifestConfigured()) {
                TapjoyConnectCore.f7057b = this.f7029a.ad.getDeviceGooglePlayServicesVersion();
                TapjoyConnectCore.f7056a = this.f7029a.ad.getPackagedGooglePlayServicesVersion();
            }
            if (this.f7029a.ad.isAdIdAvailable()) {
                boolean z;
                TapjoyConnectCore.f7059d = this.f7029a.ad.isAdTrackingEnabled();
                TapjoyConnectCore.f7058c = this.f7029a.ad.getAdvertisingId();
                gc a = gc.m7831a();
                String str = TapjoyConnectCore.f7058c;
                if (TapjoyConnectCore.f7059d) {
                    z = false;
                } else {
                    z = true;
                }
                gf gfVar = a.f7854f;
                String a2 = gfVar.f7894c.f7927A.m8219a();
                gfVar.f7893b.f7610q = str;
                gfVar.f7893b.f7611r = Boolean.valueOf(z);
                gfVar.f7894c.f7927A.m8220a(str);
                gfVar.f7894c.f7928B.m8202a(z);
                gq.m7984a(str, z);
                if (!(ct.m7339c(a2) || str.equals(a2))) {
                    gfVar.f7894c.m7973a(false);
                }
            }
            if (TapjoyConnectCore.m7119m()) {
                TapjoyLog.m7129i("TapjoyConnect", "Disabling persistent IDs. Do this only if you are not using Tapjoy to manage currency.");
            }
            this.f7029a.completeConnectCall();
        }
    }

    /* renamed from: com.tapjoy.TapjoyConnectCore$2 */
    static class C28192 implements Runnable {
        C28192() {
        }

        public final void run() {
            TapjoyLog.m7129i("TapjoyConnect", "Setting userID to " + TapjoyConnectCore.f7034C);
            TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.f7065j.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_USER_ID_URL_PATH, TapjoyConnectCore.getURLParams());
            boolean z = false;
            if (responseFromURL.response != null) {
                z = TapjoyConnectCore.m7102a(responseFromURL.response);
            }
            TapjoyConnectCore.m7100a(z);
        }
    }

    public class PPAThread implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TapjoyConnectCore f7030a;
        /* renamed from: b */
        private Map f7031b;

        public PPAThread(TapjoyConnectCore this$0, Map urlParams) {
            this.f7030a = this$0;
            this.f7031b = urlParams;
        }

        public void run() {
            TapjoyHttpURLResponse responseFromURL = TapjoyConnectCore.f7065j.getResponseFromURL(TapjoyConnectCore.getHostURL() + TapjoyConstants.TJC_CONNECT_URL_PATH, null, null, this.f7031b);
            if (responseFromURL.response != null) {
                TapjoyConnectCore.m7107c(responseFromURL.response);
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ boolean m7102a(String str) {
        Document buildDocument = TapjoyUtil.buildDocument(str);
        if (buildDocument != null) {
            String nodeTrimValue = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("PackageNames"));
            if (nodeTrimValue != null && nodeTrimValue.length() > 0) {
                List vector = new Vector();
                int i = 0;
                while (true) {
                    int indexOf = nodeTrimValue.indexOf(44, i);
                    if (indexOf == -1) {
                        break;
                    }
                    TapjoyLog.m7126d("TapjoyConnect", "parse: " + nodeTrimValue.substring(i, indexOf).trim());
                    vector.add(nodeTrimValue.substring(i, indexOf).trim());
                    i = indexOf + 1;
                }
                TapjoyLog.m7126d("TapjoyConnect", "parse: " + nodeTrimValue.substring(i).trim());
                vector.add(nodeTrimValue.substring(i).trim());
                m7098a(vector);
            }
            String nodeTrimValue2 = TapjoyUtil.getNodeTrimValue(buildDocument.getElementsByTagName("Success"));
            if (nodeTrimValue2 == null || !nodeTrimValue2.equals("true")) {
                return false;
            }
        }
        return true;
    }

    public static TapjoyConnectCore getInstance() {
        return f7064i;
    }

    public static void requestTapjoyConnect(Context applicationContext, String apiKey) {
        requestTapjoyConnect(applicationContext, apiKey, null);
    }

    public static void requestTapjoyConnect(Context applicationContext, String apiKey, Hashtable flags) {
        requestTapjoyConnect(applicationContext, apiKey, flags, null);
    }

    public static void requestTapjoyConnect(Context applicationContext, String apiKey, Hashtable flags, TJConnectListener tapjoyConnectListener) {
        try {
            er erVar = new er(apiKey);
            if (erVar.f7686a != C2909a.SDK_ANDROID) {
                throw new IllegalArgumentException("The given API key was not for Android.");
            }
            f7063h = apiKey;
            f7077v = erVar.f7687b;
            f7043L = erVar.f7688c;
            f7044M = erVar.f7689d;
            if (flags != null) {
                ae.putAll(flags);
                fd.m7717b().m7712a(flags);
            }
            gc.m7832a(applicationContext).f7858j = apiKey;
            f7066k = tapjoyConnectListener;
            f7064i = new TapjoyConnectCore(applicationContext);
        } catch (IllegalArgumentException e) {
            throw new TapjoyIntegrationException(e.getMessage());
        }
    }

    public TapjoyConnectCore(Context applicationContext) {
        f7062g = applicationContext;
        fh.m7730a().m7731a(applicationContext);
        fd.m7716a().m7718a(applicationContext);
        f7065j = new TapjoyURLConnection();
        ac = f7062g.getPackageManager();
        this.ad = new TapjoyGpsHelper(f7062g);
        try {
            if (ae == null) {
                ae = new Hashtable();
            }
            m7115i();
            int identifier = f7062g.getResources().getIdentifier("raw/tapjoy_config", null, f7062g.getPackageName());
            Properties properties = new Properties();
            try {
                properties.load(f7062g.getResources().openRawResource(identifier));
                m7099a(properties);
            } catch (Exception e) {
            }
            if (ct.m7339c(getConnectFlagValue("unit_test_mode"))) {
                m7116j();
            }
            String string = Secure.getString(f7062g.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
            f7069n = string;
            if (string != null) {
                f7069n = f7069n.toLowerCase();
            }
            f7078w = ac.getPackageInfo(f7062g.getPackageName(), 0).versionName;
            f7075t = TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
            f7035D = TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE;
            f7073r = Build.MODEL;
            f7074s = Build.MANUFACTURER;
            f7076u = VERSION.RELEASE;
            f7079x = TapjoyConstants.TJC_LIBRARY_VERSION_NUMBER;
            f7080y = TapjoyConstants.TJC_BRIDGE_VERSION_NUMBER;
            try {
                if (VERSION.SDK_INT > 3) {
                    TapjoyDisplayMetricsUtil tapjoyDisplayMetricsUtil = new TapjoyDisplayMetricsUtil(f7062g);
                    f7081z = tapjoyDisplayMetricsUtil.getScreenDensityDPI();
                    f7032A = tapjoyDisplayMetricsUtil.getScreenDensityScale();
                    f7033B = tapjoyDisplayMetricsUtil.getScreenLayoutSize();
                }
            } catch (Exception e2) {
                TapjoyLog.m7128e("TapjoyConnect", "Error getting screen density/dimensions/layout: " + e2.toString());
            }
            if (m7111e("android.permission.ACCESS_WIFI_STATE")) {
                try {
                    WifiManager wifiManager = (WifiManager) f7062g.getSystemService("wifi");
                    if (wifiManager != null) {
                        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                        if (connectionInfo != null) {
                            string = connectionInfo.getMacAddress();
                            f7071p = string;
                            if (string != null) {
                                f7071p = f7071p.replace(":", "").toLowerCase();
                            }
                        }
                    }
                } catch (Exception e22) {
                    TapjoyLog.m7128e("TapjoyConnect", "Error getting device mac address: " + e22.toString());
                }
            } else {
                TapjoyLog.m7126d("TapjoyConnect", "*** ignore macAddress");
            }
            TelephonyManager telephonyManager = (TelephonyManager) f7062g.getSystemService(PlaceFields.PHONE);
            if (telephonyManager != null) {
                f7036E = telephonyManager.getNetworkOperatorName();
                f7037F = telephonyManager.getNetworkCountryIso();
                string = telephonyManager.getNetworkOperator();
                if (string != null && (string.length() == 5 || string.length() == 6)) {
                    f7038G = string.substring(0, 3);
                    f7039H = string.substring(3);
                }
            }
            SharedPreferences sharedPreferences = f7062g.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
            String string2 = sharedPreferences.getString(TapjoyConstants.PREF_INSTALL_ID, "");
            f7072q = string2;
            if (string2 == null || f7072q.length() == 0) {
                try {
                    f7072q = TapjoyUtil.SHA256(UUID.randomUUID().toString() + System.currentTimeMillis());
                    Editor edit = sharedPreferences.edit();
                    edit.putString(TapjoyConstants.PREF_INSTALL_ID, f7072q);
                    edit.commit();
                } catch (Exception e222) {
                    TapjoyLog.m7128e("TapjoyConnect", "Error generating install id: " + e222.toString());
                }
            }
            if (getConnectFlagValue(TapjoyConnectFlag.STORE_NAME) != null && getConnectFlagValue(TapjoyConnectFlag.STORE_NAME).length() > 0) {
                f7042K = getConnectFlagValue(TapjoyConnectFlag.STORE_NAME);
                if (!new ArrayList(Arrays.asList(TapjoyConnectFlag.STORE_ARRAY)).contains(f7042K)) {
                    TapjoyLog.m7131w("TapjoyConnect", "Warning -- undefined STORE_NAME: " + f7042K);
                }
            }
            try {
                boolean z;
                string = f7042K;
                Intent intent = new Intent("android.intent.action.VIEW");
                if (string.length() <= 0) {
                    intent.setData(Uri.parse("market://details"));
                    if (ac.queryIntentActivities(intent, 0).size() > 0) {
                        z = true;
                    }
                    z = false;
                } else if (string.equals(TapjoyConnectFlag.STORE_GFAN)) {
                    z = m7109d("com.mappn.gfan");
                } else {
                    if (string.equals(TapjoyConnectFlag.STORE_SKT)) {
                        z = m7109d("com.skt.skaf.TSCINSTALL");
                    }
                    z = false;
                }
                f7049R = z;
            } catch (Exception e2222) {
                TapjoyLog.m7128e("TapjoyConnect", "Error trying to detect store intent on devicee: " + e2222.toString());
            }
            m7113g();
            if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS).length() > 0) {
                f7061f = getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS);
            }
            if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK).length() > 0) {
                f7060e = getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK);
            }
            if (getConnectFlagValue(TapjoyConnectFlag.USER_ID) != null && getConnectFlagValue(TapjoyConnectFlag.USER_ID).length() > 0) {
                TapjoyLog.m7129i("TapjoyConnect", "Setting userID to: " + getConnectFlagValue(TapjoyConnectFlag.USER_ID));
                setUserID(getConnectFlagValue(TapjoyConnectFlag.USER_ID), null);
            }
            f7047P = TapjoyUtil.getRedirectDomain(getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL));
            if (ae != null) {
                m7114h();
            }
            callConnect();
            this.aa = true;
        } catch (NameNotFoundException e3) {
            throw new TapjoyException(e3.getMessage());
        } catch (TapjoyIntegrationException e4) {
            TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, e4.getMessage()));
            m7108d();
            ev.f7701b.notifyObservers(Boolean.FALSE);
        } catch (TapjoyException e5) {
            TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, e5.getMessage()));
            m7108d();
            ev.f7701b.notifyObservers(Boolean.FALSE);
        }
    }

    /* renamed from: d */
    private static void m7108d() {
        if (!ct.m7339c(f7044M)) {
            gc.m7831a().m7839a(f7062g, f7063h, TapjoyConstants.TJC_LIBRARY_VERSION_NUMBER, TapjoyConfig.TJC_ANALYTICS_SERVICE_URL, f7044M, f7043L);
        }
        if (f7066k != null) {
            f7066k.onConnectFailure();
        }
    }

    public void callConnect() {
        fetchAdvertisingID();
    }

    public void fetchAdvertisingID() {
        new Thread(new C28181(this)).start();
    }

    public void appPause() {
        this.f7083Y = true;
    }

    public void appResume() {
        if (this.f7083Y) {
            m7120n();
            this.f7083Y = false;
        }
    }

    public static Map getURLParams() {
        Map genericURLParams = getGenericURLParams();
        genericURLParams.putAll(getTimeStampAndVerifierParams());
        return genericURLParams;
    }

    public static Map getGenericURLParams() {
        Map e = m7110e();
        TapjoyUtil.safePut(e, "app_id", f7077v, true);
        return e;
    }

    /* renamed from: e */
    private static Map m7110e() {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        Map hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PLUGIN, f7045N, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_SDK_TYPE, f7046O, true);
        TapjoyUtil.safePut(hashMap3, "app_id", f7077v, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_LIBRARY_VERSION, f7079x, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_LIBRARY_REVISION, TapjoyRevision.GIT_REVISION, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_BRIDGE_VERSION, f7080y, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_APP_VERSION_NAME, f7078w, true);
        hashMap2.putAll(hashMap3);
        hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_NAME, f7073r, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PLATFORM, f7035D, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_OS_VERSION_NAME, f7076u, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_MANUFACTURER, f7074s, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_TYPE_NAME, f7075t, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_SCREEN_LAYOUT_SIZE, f7033B, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_STORE_NAME, f7042K, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_STORE_VIEW, String.valueOf(f7049R), true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_CARRIER_NAME, f7036E, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_CARRIER_COUNTRY_CODE, f7037F, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_MOBILE_NETWORK_CODE, f7039H, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_MOBILE_COUNTRY_CODE, f7038G, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, Locale.getDefault().getCountry(), true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_LANGUAGE, Locale.getDefault().getLanguage(), true);
        f7040I = getConnectionType();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_CONNECTION_TYPE, f7040I, true);
        f7041J = getConnectionSubType();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_CONNECTION_SUBTYPE, f7041J, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_SCREEN_DENSITY, f7081z, true);
        hashMap2.putAll(hashMap3);
        hashMap3 = new HashMap();
        if (m7118l()) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ADVERTISING_ID, f7058c, true);
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_AD_TRACKING_ENABLED, String.valueOf(f7059d), true);
        }
        if (!m7119m()) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ANDROID_ID, f7069n, true);
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_MAC_ADDRESS, f7071p, true);
        }
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_INSTALL_ID, f7072q, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_USER_ID, f7034C, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ADVERTISING_ID_CHECK_DISABLED, f7060e, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PERSISTENT_ID_DISABLED, f7061f, true);
        if (f7056a != 0) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_PACKAGED_GOOGLE_PLAY_SERVICES_VERSION, Integer.toString(f7056a), true);
        }
        if (f7057b != 0) {
            TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_DEVICE_GOOGLE_PLAY_SERVICES_VERSION, Integer.toString(f7057b), true);
        }
        if (f7070o == null || f7070o.length() == 0 || System.currentTimeMillis() - f7055Z > TapjoyConstants.SESSION_ID_INACTIVITY_TIME) {
            f7070o = m7120n();
        } else {
            f7055Z = System.currentTimeMillis();
        }
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_SESSION_ID, f7070o, true);
        hashMap2.putAll(hashMap3);
        hashMap3 = new HashMap();
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_APP_GROUP_ID, f7050S, true);
        TapjoyUtil.safePut(hashMap3, "store", f7051T, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_ANALYTICS_API_KEY, f7052U, true);
        TapjoyUtil.safePut(hashMap3, TapjoyConstants.TJC_MANAGED_DEVICE_ID, f7053V, true);
        hashMap2.putAll(hashMap3);
        fh a = fh.m7730a();
        Map hashMap4 = new HashMap();
        if (a.f7758a != null) {
            TapjoyUtil.safePut(hashMap4, "gdpr", a.f7758a.booleanValue() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO, true);
        }
        if (!aq.m7169a(a.f7759b)) {
            TapjoyUtil.safePut(hashMap4, "cgdpr", a.f7759b, true);
        }
        hashMap2.putAll(hashMap4);
        if (!(TapjoyCache.getInstance() == null || TapjoyCache.getInstance().getCachedOfferIDs() == null || TapjoyCache.getInstance().getCachedOfferIDs().length() <= 0)) {
            TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_CACHED_OFFERS, TapjoyCache.getInstance().getCachedOfferIDs(), true);
        }
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_CURRENCY_MULTIPLIER, Float.toString(f7048Q), true);
        hashMap.putAll(hashMap2);
        hashMap3 = new HashMap();
        m7113g();
        hashMap2 = new HashMap();
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_ANALYTICS_ID, ah, true);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_PACKAGE_ID, ai, true);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_PACKAGE_SIGN, aj, true);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_DEVICE_DISPLAY_DENSITY, aJ);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_DEVICE_DISPLAY_WIDTH, aK);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_DEVICE_DISPLAY_HEIGHT, aL);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_DEVICE_COUNTRY_SIM, aM, true);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_DEVICE_TIMEZONE, aN, true);
        hashMap3.putAll(hashMap2);
        hashMap2 = new HashMap();
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_PACKAGE_VERSION, ak, true);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_PACKAGE_REVISION, al);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_PACKAGE_DATA_VERSION, am, true);
        TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_INSTALLER, an, true);
        if (ct.m7339c(f7042K)) {
            TapjoyUtil.safePut(hashMap2, TapjoyConstants.TJC_STORE_NAME, aO, true);
        }
        hashMap3.putAll(hashMap2);
        hashMap3.putAll(m7112f());
        hashMap.putAll(hashMap3);
        return hashMap;
    }

    public static Map getTimeStampAndVerifierParams() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String a = m7095a(currentTimeMillis);
        Map hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, "timestamp", String.valueOf(currentTimeMillis), true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_VERIFIER, a, true);
        return hashMap;
    }

    /* renamed from: f */
    private static Map m7112f() {
        Map hashMap = new HashMap();
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_INSTALLED, ao);
        TapjoyUtil.safePut(hashMap, "referrer", ap, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_LEVEL, aq);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_FRIEND_COUNT, ar);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_1, as, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_2, at, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_3, au, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_4, av, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_VARIABLE_5, aw, true);
        int i = 0;
        for (String safePut : ax) {
            int i2 = i + 1;
            TapjoyUtil.safePut(hashMap, "user_tags[" + i + RequestParameters.RIGHT_BRACKETS, safePut, true);
            i = i2;
        }
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_WEEKLY_FREQUENCY, ay);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_USER_MONTHLY_FREQUENCY, az);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_TOTAL_COUNT, aA);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_TOTAL_LENGTH, aB);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_LAST_AT, aC);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_SESSION_LAST_LENGTH, aD);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_CURRENCY, aE, true);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_TOTAL_COUNT, aF);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_TOTAL_PRICE, aG);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_LAST_PRICE, aH);
        TapjoyUtil.safePut(hashMap, TapjoyConstants.TJC_PURCHASE_LAST_AT, aI);
        return hashMap;
    }

    /* renamed from: g */
    private static void m7113g() {
        ee a = gc.m7832a(f7062g).m7838a(true);
        ed edVar = a.f7536d;
        ah = edVar.f7516h;
        ai = edVar.f7526r;
        aj = edVar.f7527s;
        aJ = edVar.f7521m;
        aK = edVar.f7522n;
        aL = edVar.f7523o;
        aM = edVar.f7529u;
        aN = edVar.f7525q;
        dx dxVar = a.f7537e;
        ak = dxVar.f7408e;
        al = dxVar.f7409f;
        am = dxVar.f7410g;
        an = dxVar.f7411h;
        aO = dxVar.f7412i;
        ek ekVar = a.f7538f;
        ao = ekVar.f7654s;
        ap = ekVar.f7655t;
        aq = ekVar.f7645J;
        ar = ekVar.f7646K;
        as = ekVar.f7647L;
        at = ekVar.f7648M;
        au = ekVar.f7649N;
        av = ekVar.f7650O;
        aw = ekVar.f7651P;
        ax = new HashSet(ekVar.f7652Q);
        ay = ekVar.f7656u;
        az = ekVar.f7657v;
        aA = ekVar.f7659x;
        aB = ekVar.f7660y;
        aC = ekVar.f7661z;
        aD = ekVar.f7636A;
        aE = ekVar.f7637B;
        aF = ekVar.f7638C;
        aG = ekVar.f7639D;
        aH = ekVar.f7641F;
        aI = ekVar.f7640E;
    }

    /* renamed from: h */
    private static void m7114h() {
        TapjoyLog.m7129i("TapjoyConnect", "Connect Flags:");
        TapjoyLog.m7129i("TapjoyConnect", "--------------------");
        for (Entry entry : ae.entrySet()) {
            TapjoyLog.m7129i("TapjoyConnect", "key: " + ((String) entry.getKey()) + ", value: " + Uri.encode(entry.getValue().toString()));
        }
        TapjoyLog.m7129i("TapjoyConnect", "hostURL: [" + getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL) + RequestParameters.RIGHT_BRACKETS);
        TapjoyLog.m7129i("TapjoyConnect", "redirectDomain: [" + f7047P + RequestParameters.RIGHT_BRACKETS);
        TapjoyLog.m7129i("TapjoyConnect", "--------------------");
    }

    /* renamed from: i */
    private static void m7115i() {
        try {
            if (ac != null) {
                ApplicationInfo applicationInfo = ac.getApplicationInfo(f7062g.getPackageName(), 128);
                if (applicationInfo == null || applicationInfo.metaData == null) {
                    TapjoyLog.m7126d("TapjoyConnect", "No metadata present.");
                    return;
                }
                for (String str : TapjoyConnectFlag.FLAG_ARRAY) {
                    String string = applicationInfo.metaData.getString("tapjoy." + str);
                    if (string != null) {
                        TapjoyLog.m7126d("TapjoyConnect", "Found manifest flag: " + str + ", " + string);
                        m7097a(str, string);
                    }
                }
                TapjoyLog.m7126d("TapjoyConnect", "Metadata successfully loaded");
            }
        } catch (Exception e) {
            TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error reading manifest meta-data -- " + e.toString()));
        }
    }

    /* renamed from: a */
    private static void m7099a(Properties properties) {
        Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            try {
                String str = (String) keys.nextElement();
                m7097a(str, (String) properties.get(str));
            } catch (ClassCastException e) {
                TapjoyLog.m7128e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
            }
        }
    }

    /* renamed from: j */
    private void m7116j() {
        String copyTextFromJarIntoString;
        int indexOf;
        try {
            List<ActivityInfo> asList = Arrays.asList(ac.getPackageInfo(f7062g.getPackageName(), 1).activities);
            if (asList != null) {
                for (ActivityInfo activityInfo : asList) {
                    if (f7068m.contains(activityInfo.name)) {
                        indexOf = f7068m.indexOf(activityInfo.name);
                        Class.forName((String) f7068m.get(indexOf));
                        Vector vector = new Vector();
                        if ((activityInfo.configChanges & 128) != 128) {
                            vector.add("orientation");
                        }
                        if ((activityInfo.configChanges & 32) != 32) {
                            vector.add("keyboardHidden");
                        }
                        if (vector.size() == 0) {
                            if (VERSION.SDK_INT >= 13 && (activityInfo.configChanges & 1024) != 1024) {
                                TapjoyLog.m7131w("TapjoyConnect", "WARNING -- screenSize property is not specified in manifest configChanges for " + ((String) f7068m.get(indexOf)));
                            }
                            if (VERSION.SDK_INT < 11 || !activityInfo.name.equals("com.tapjoy.TJAdUnitActivity") || (activityInfo.flags & 512) == 512) {
                                f7068m.remove(indexOf);
                            } else {
                                throw new TapjoyIntegrationException("'hardwareAccelerated' property not specified in manifest for " + ((String) f7068m.get(indexOf)));
                            }
                        } else if (vector.size() == 1) {
                            throw new TapjoyIntegrationException(vector.toString() + " property is not specified in manifest configChanges for " + ((String) f7068m.get(indexOf)));
                        } else {
                            throw new TapjoyIntegrationException(vector.toString() + " properties are not specified in manifest configChanges for " + ((String) f7068m.get(indexOf)));
                        }
                    }
                }
            }
            if (f7068m.size() == 0) {
                m7117k();
                try {
                    try {
                        Class.forName("com.tapjoy.TJAdUnitJSBridge").getMethod(String.CLOSE_REQUESTED, new Class[]{Boolean.class});
                        String str = (String) TapjoyUtil.getResource("mraid.js");
                        if (str == null) {
                            copyTextFromJarIntoString = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", f7062g);
                        } else {
                            copyTextFromJarIntoString = str;
                        }
                        if (copyTextFromJarIntoString == null) {
                            try {
                                InputStream openRawResource = f7062g.getResources().openRawResource(f7062g.getResources().getIdentifier("mraid", "raw", f7062g.getPackageName()));
                                byte[] bArr = new byte[openRawResource.available()];
                                openRawResource.read(bArr);
                                str = new String(bArr);
                                try {
                                    TapjoyUtil.setResource("mraid.js", str);
                                } catch (Exception e) {
                                }
                            } catch (Exception e2) {
                                str = copyTextFromJarIntoString;
                            }
                        } else {
                            str = copyTextFromJarIntoString;
                        }
                        if (str == null) {
                            throw new TapjoyIntegrationException("ClassNotFoundException: mraid.js was not found.");
                        } else if (getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK) == null || !getConnectFlagValue(TapjoyConnectFlag.DISABLE_ADVERTISING_ID_CHECK).equals("true")) {
                            this.ad.checkGooglePlayIntegration();
                        } else {
                            TapjoyLog.m7129i("TapjoyConnect", "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services.");
                        }
                    } catch (NoSuchMethodException e3) {
                        throw new TapjoyIntegrationException("Try configuring Proguard or other code obfuscators to ignore com.tapjoy classes. Visit http://dev.tapjoy.comfor more information.");
                    }
                } catch (ClassNotFoundException e4) {
                    throw new TapjoyIntegrationException("ClassNotFoundException: com.tapjoy.TJAdUnitJSBridge was not found.");
                }
            } else if (f7068m.size() == 1) {
                throw new TapjoyIntegrationException("Missing " + f7068m.size() + " dependency class in manifest: " + f7068m.toString());
            } else {
                throw new TapjoyIntegrationException("Missing " + f7068m.size() + " dependency classes in manifest: " + f7068m.toString());
            }
        } catch (ClassNotFoundException e5) {
            throw new TapjoyIntegrationException("[ClassNotFoundException] Could not find dependency class " + ((String) f7068m.get(indexOf)));
        } catch (NameNotFoundException e6) {
            throw new TapjoyIntegrationException("NameNotFoundException: Could not find package.");
        }
    }

    /* renamed from: k */
    private static void m7117k() {
        int i = 0;
        Vector vector = new Vector();
        for (String str : TapjoyConstants.dependencyPermissions) {
            if (!m7111e(str)) {
                vector.add(str);
            }
        }
        if (vector.size() == 0) {
            Vector vector2 = new Vector();
            String[] strArr = TapjoyConstants.optionalPermissions;
            int length = strArr.length;
            while (i < length) {
                String str2 = strArr[i];
                if (!m7111e(str2)) {
                    vector2.add(str2);
                }
                i++;
            }
            if (vector2.size() == 0) {
                return;
            }
            if (vector2.size() == 1) {
                TapjoyLog.m7131w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
            } else {
                TapjoyLog.m7131w("TapjoyConnect", "WARNING -- " + vector2.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
            }
        } else if (vector.size() == 1) {
            throw new TapjoyIntegrationException("Missing 1 permission in manifest: " + vector.toString());
        } else {
            throw new TapjoyIntegrationException("Missing " + vector.size() + " permissions in manifest: " + vector.toString());
        }
    }

    /* renamed from: l */
    private static boolean m7118l() {
        return f7058c != null && f7058c.length() > 0;
    }

    /* renamed from: m */
    private static boolean m7119m() {
        return m7118l() && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS) != null && getConnectFlagValue(TapjoyConnectFlag.DISABLE_PERSISTENT_IDS).equals("true");
    }

    /* renamed from: a */
    private static boolean m7103a(String str, boolean z) {
        IOException e;
        Throwable th;
        RuntimeException e2;
        Closeable closeable = null;
        Closeable b;
        try {
            b = bs.m7244b(str);
            try {
                Map d = b.m7257d();
                String a = ct.m7337a((String) d.get(TapjoyConstants.TJC_APP_GROUP_ID));
                String a2 = ct.m7337a((String) d.get("store"));
                String a3 = ct.m7337a((String) d.get(TapjoyConstants.TJC_ANALYTICS_API_KEY));
                String a4 = ct.m7337a((String) d.get(TapjoyConstants.TJC_MANAGED_DEVICE_ID));
                String a5 = ct.m7337a((String) d.get(TapjoyConstants.TJC_PACKAGE_NAMES));
                Object obj = d.get("cache_max_age");
                er erVar = new er(a3);
                if (erVar.f7686a != C2909a.RPC_ANALYTICS) {
                    throw new IOException("Invalid analytics_api_key");
                }
                String str2 = erVar.f7687b;
                if (str2.regionMatches(13, "-8000-8000-", 0, 11)) {
                    String str3;
                    String stringBuffer = new StringBuffer().append(str2.substring(0, 8)).append(str2.substring(24, 30)).append(str2.substring(9, 13)).append(str2.substring(30)).toString();
                    String str4 = erVar.f7688c;
                    if (a == null) {
                        str3 = stringBuffer;
                    } else {
                        str3 = a;
                    }
                    gc.m7831a().m7839a(f7062g, a3, TapjoyConstants.TJC_LIBRARY_VERSION_NUMBER, TapjoyConfig.TJC_ANALYTICS_SERVICE_URL, stringBuffer, str4);
                    f7050S = str3;
                    f7051T = a2;
                    f7052U = a3;
                    f7053V = a4;
                    List arrayList = new ArrayList();
                    if (a5 != null) {
                        for (String trim : a5.split(",")) {
                            String trim2 = trim2.trim();
                            if (trim2.length() > 0) {
                                arrayList.add(trim2);
                            }
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        m7098a(arrayList);
                    }
                    b.close();
                    if (!z) {
                        try {
                            long parseLong;
                            if (obj instanceof String) {
                                try {
                                    parseLong = Long.parseLong(((String) obj).trim());
                                } catch (NumberFormatException e3) {
                                    parseLong = 0;
                                }
                            } else {
                                if (obj instanceof Number) {
                                    try {
                                        parseLong = ((Number) obj).longValue();
                                    } catch (NumberFormatException e4) {
                                    }
                                }
                                parseLong = 0;
                            }
                            if (parseLong <= 0) {
                                TapjoyAppSettings.getInstance().removeConnectResult();
                            } else {
                                TapjoyAppSettings.getInstance().saveConnectResultAndParams(str, m7122p(), (parseLong * 1000) + C2999y.m8233b());
                            }
                            fd a6 = fd.m7716a();
                            Object obj2 = d.get(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS);
                            if (obj2 instanceof Map) {
                                try {
                                    a6.f7731a.m7713a((Map) obj2);
                                    a6.m7719c().edit().putString(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS, bm.m7204a(obj2)).apply();
                                } catch (Exception e5) {
                                }
                            } else if (obj2 == null) {
                                a6.f7731a.m7713a(null);
                                a6.m7719c().edit().remove(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS).apply();
                            }
                        } catch (IOException e6) {
                            e = e6;
                            closeable = null;
                            try {
                                TapjoyLog.m7130v("TapjoyConnect", e.getMessage());
                                dc.m7357a(closeable);
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                b = closeable;
                                dc.m7357a(b);
                                throw th;
                            }
                        } catch (RuntimeException e7) {
                            e2 = e7;
                            b = null;
                            try {
                                TapjoyLog.m7130v("TapjoyConnect", e2.getMessage());
                                dc.m7357a(b);
                                return false;
                            } catch (Throwable th3) {
                                th = th3;
                                dc.m7357a(b);
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            b = null;
                            dc.m7357a(b);
                            throw th;
                        }
                    }
                    dc.m7357a(null);
                    return true;
                }
                throw new IllegalArgumentException("The given UUID did not come from 5Rocks.");
            } catch (IOException e8) {
                e = e8;
                closeable = b;
                TapjoyLog.m7130v("TapjoyConnect", e.getMessage());
                dc.m7357a(closeable);
                return false;
            } catch (RuntimeException e9) {
                e2 = e9;
                TapjoyLog.m7130v("TapjoyConnect", e2.getMessage());
                dc.m7357a(b);
                return false;
            }
        } catch (IOException e10) {
            e = e10;
            TapjoyLog.m7130v("TapjoyConnect", e.getMessage());
            dc.m7357a(closeable);
            return false;
        } catch (RuntimeException e11) {
            e2 = e11;
            b = null;
            TapjoyLog.m7130v("TapjoyConnect", e2.getMessage());
            dc.m7357a(b);
            return false;
        } catch (Throwable th5) {
            th = th5;
            b = null;
            dc.m7357a(b);
            throw th;
        }
    }

    /* renamed from: a */
    private static synchronized void m7098a(List list) {
        synchronized (TapjoyConnectCore.class) {
            af = "";
            for (ApplicationInfo applicationInfo : ac.getInstalledApplications(0)) {
                if ((applicationInfo.flags & 1) != 1 && list.contains(applicationInfo.packageName)) {
                    TapjoyLog.m7126d("TapjoyConnect", "MATCH: installed packageName: " + applicationInfo.packageName);
                    if (af.length() > 0) {
                        af += ",";
                    }
                    af += applicationInfo.packageName;
                }
            }
        }
    }

    /* renamed from: c */
    private static boolean m7107c(String str) {
        IOException e;
        Throwable th;
        RuntimeException e2;
        Closeable b;
        try {
            b = bs.m7244b(str);
            try {
                if (b.m7253a()) {
                    b.mo6204s();
                    TapjoyLog.m7126d("TapjoyConnect", "Successfully sent completed Pay-Per-Action to Tapjoy server.");
                    b.close();
                    dc.m7357a(null);
                    return true;
                }
                b.close();
                dc.m7357a(null);
                TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
                return false;
            } catch (IOException e3) {
                e = e3;
                try {
                    TapjoyLog.m7130v("TapjoyConnect", e.getMessage());
                    dc.m7357a(b);
                    TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    dc.m7357a(b);
                    throw th;
                }
            } catch (RuntimeException e4) {
                e2 = e4;
                TapjoyLog.m7130v("TapjoyConnect", e2.getMessage());
                dc.m7357a(b);
                TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
                return false;
            }
        } catch (IOException e5) {
            e = e5;
            b = null;
            TapjoyLog.m7130v("TapjoyConnect", e.getMessage());
            dc.m7357a(b);
            TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
            return false;
        } catch (RuntimeException e6) {
            e2 = e6;
            b = null;
            TapjoyLog.m7130v("TapjoyConnect", e2.getMessage());
            dc.m7357a(b);
            TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Completed Pay-Per-Action call failed."));
            return false;
        } catch (Throwable th3) {
            th = th3;
            b = null;
            dc.m7357a(b);
            throw th;
        }
    }

    public void release() {
        f7064i = null;
        f7065j = null;
        TapjoyLog.m7126d("TapjoyConnect", "Releasing core static instance.");
    }

    public static String getAppID() {
        return f7077v;
    }

    public static String getUserID() {
        return f7034C;
    }

    public static String getHostURL() {
        return getConnectFlagValue(TapjoyConnectFlag.SERVICE_URL);
    }

    public static String getPlacementURL() {
        return getConnectFlagValue(TapjoyConnectFlag.PLACEMENT_URL);
    }

    public static String getConnectURL() {
        return TapjoyConfig.TJC_CONNECT_SERVICE_URL;
    }

    public static String getRedirectDomain() {
        return f7047P;
    }

    public static String getCarrierName() {
        return f7036E;
    }

    public static String getConnectionType() {
        String str = "";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) f7062g.getSystemService("connectivity");
            if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                switch (connectivityManager.getActiveNetworkInfo().getType()) {
                    case 1:
                    case 6:
                        str = "wifi";
                        break;
                    default:
                        str = TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE;
                        break;
                }
                TapjoyLog.m7126d("TapjoyConnect", "connectivity: " + connectivityManager.getActiveNetworkInfo().getType());
                TapjoyLog.m7126d("TapjoyConnect", "connection_type: " + str);
            }
            return str;
        } catch (Exception e) {
            Exception exception = e;
            String str2 = str;
            TapjoyLog.m7128e("TapjoyConnect", "getConnectionType error: " + exception.toString());
            return str2;
        }
    }

    public static String getConnectionSubType() {
        Exception e;
        String str = "";
        String subtypeName;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) f7062g.getSystemService("connectivity");
            if (connectivityManager == null) {
                return str;
            }
            subtypeName = connectivityManager.getActiveNetworkInfo().getSubtypeName();
            try {
                TapjoyLog.m7126d("TapjoyConnect", "connection_sub_type: " + subtypeName);
                return subtypeName;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            subtypeName = str;
            e = exception;
            TapjoyLog.m7128e("TapjoyConnect", "getConnectionSubType error: " + e.toString());
            return subtypeName;
        }
    }

    /* renamed from: d */
    private static boolean m7109d(String str) {
        for (ApplicationInfo applicationInfo : ac.getInstalledApplications(0)) {
            if (applicationInfo.packageName.startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: n */
    private static String m7120n() {
        String SHA256;
        Exception e;
        TapjoyLog.m7129i("TapjoyConnect", "generating sessionID...");
        try {
            SHA256 = TapjoyUtil.SHA256((System.currentTimeMillis() / 1000) + f7077v);
            try {
                f7055Z = System.currentTimeMillis();
            } catch (Exception e2) {
                e = e2;
                TapjoyLog.m7128e("TapjoyConnect", "unable to generate session id: " + e.toString());
                return SHA256;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            SHA256 = null;
            e = exception;
            TapjoyLog.m7128e("TapjoyConnect", "unable to generate session id: " + e.toString());
            return SHA256;
        }
        return SHA256;
    }

    public static Context getContext() {
        return f7062g;
    }

    /* renamed from: o */
    private static String m7121o() {
        Object obj = 1;
        if (m7119m()) {
            return f7058c;
        }
        Object obj2;
        if (f7071p == null || f7071p.length() <= 0) {
            obj2 = null;
        } else {
            obj2 = 1;
        }
        if (obj2 != null) {
            return f7071p;
        }
        if (m7118l()) {
            return f7058c;
        }
        if (f7069n == null || f7069n.length() <= 0) {
            obj = null;
        }
        if (obj != null) {
            return f7069n;
        }
        TapjoyLog.m7128e("TapjoyConnect", "Error -- no valid device identifier");
        return null;
    }

    /* renamed from: a */
    private static String m7095a(long j) {
        String str = "";
        try {
            str = TapjoyUtil.SHA256(f7077v + ":" + m7121o() + ":" + j + ":" + f7043L);
        } catch (Exception e) {
            TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error in computing verifier value -- " + e.toString()));
        }
        return str;
    }

    public static String getAwardCurrencyVerifier(long time, int amount, String guid) {
        String str = "";
        try {
            str = TapjoyUtil.SHA256(f7077v + ":" + m7121o() + ":" + time + ":" + f7043L + ":" + amount + ":" + guid);
        } catch (Exception e) {
            TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error in computing awardCurrencyVerifier -- " + e.toString()));
        }
        return str;
    }

    /* renamed from: a */
    private static String m7096a(long j, String str) {
        String str2 = "";
        try {
            str2 = TapjoyUtil.SHA256(f7077v + ":" + m7121o() + ":" + j + ":" + f7043L + ":" + str);
        } catch (Exception e) {
            TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error in computing packageNamesVerifier -- " + e.toString()));
        }
        return str2;
    }

    public boolean isInitialized() {
        return this.aa;
    }

    public static void setPlugin(String name) {
        f7045N = name;
    }

    public static void setSDKType(String name) {
        f7046O = name;
    }

    public static void setUserID(String id, TJSetUserIDListener listener) {
        f7034C = id;
        f7067l = listener;
        TapjoyLog.m7126d("TapjoyConnect", "URL parameters: " + getURLParams());
        new Thread(new C28192()).start();
    }

    public static void viewDidClose(String contentViewId) {
        TapjoyLog.m7126d("TapjoyConnect", "viewDidClose: " + contentViewId);
        ag.remove(contentViewId);
        ev.f7704e.notifyObservers();
    }

    public static void viewWillOpen(String contentViewId, int viewType) {
        TapjoyLog.m7126d("TapjoyConnect", "viewWillOpen: " + contentViewId);
        ag.put(contentViewId, Integer.valueOf(viewType));
    }

    public static boolean isViewOpen() {
        TapjoyLog.m7126d("TapjoyConnect", "isViewOpen: " + ag.size());
        return !ag.isEmpty();
    }

    public static boolean isFullScreenViewOpen() {
        for (Integer intValue : ag.values()) {
            switch (intValue.intValue()) {
                case 1:
                case 2:
                    return true;
                default:
            }
        }
        return false;
    }

    public static void setViewShowing(boolean showing) {
        if (showing) {
            ag.put("", Integer.valueOf(1));
        } else {
            ag.clear();
        }
    }

    /* renamed from: a */
    private static void m7097a(String str, String str2) {
        if ((str.equals(TapjoyConnectFlag.SERVICE_URL) || str.equals(TapjoyConnectFlag.PLACEMENT_URL)) && !str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        ae.put(str, str2);
    }

    /* renamed from: e */
    private static boolean m7111e(String str) {
        if (ac.checkPermission(str, f7062g.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    public void actionComplete(String actionID) {
        TapjoyLog.m7129i("TapjoyConnect", "actionComplete: " + actionID);
        Map e = m7110e();
        TapjoyUtil.safePut(e, "app_id", actionID, true);
        e.putAll(getTimeStampAndVerifierParams());
        TapjoyLog.m7126d("TapjoyConnect", "PPA URL parameters: " + e);
        new Thread(new PPAThread(this, e)).start();
    }

    public void completeConnectCall() {
        boolean z;
        TapjoyHttpURLResponse responseFromURL;
        TapjoyLog.m7126d("TapjoyConnect", "starting connect call...");
        String str = TapjoyConfig.TJC_CONNECT_SERVICE_URL;
        if (getHostURL() != TapjoyConfig.TJC_SERVICE_URL) {
            str = getHostURL();
        }
        if (!isConnected()) {
            String connectResult = TapjoyAppSettings.getInstance().getConnectResult(m7122p(), C2999y.m8233b());
            if (connectResult != null && m7103a(connectResult, true)) {
                TapjoyLog.m7129i("TapjoyConnect", "Connect using stored connect result");
                ab = true;
                if (f7066k != null) {
                    f7066k.onConnectSuccess();
                }
                ev.f7700a.notifyObservers();
                z = true;
                responseFromURL = f7065j.getResponseFromURL(str + TapjoyConstants.TJC_CONNECT_URL_PATH, null, null, getURLParams());
                if (responseFromURL == null && responseFromURL.statusCode == 200) {
                    if (m7103a(responseFromURL.response, false)) {
                        TapjoyLog.m7129i("TapjoyConnect", "Successfully connected to Tapjoy");
                        ab = true;
                        for (Entry entry : getGenericURLParams().entrySet()) {
                            TapjoyLog.m7126d("TapjoyConnect", ((String) entry.getKey()) + ": " + ((String) entry.getValue()));
                        }
                        if (!z) {
                            if (f7066k != null) {
                                f7066k.onConnectSuccess();
                            }
                            ev.f7700a.notifyObservers();
                        }
                        ev.f7701b.notifyObservers(Boolean.TRUE);
                    } else {
                        if (!z) {
                            m7108d();
                        }
                        ev.f7701b.notifyObservers(Boolean.FALSE);
                    }
                    if (af.length() > 0) {
                        Map genericURLParams = getGenericURLParams();
                        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_PACKAGE_NAMES, af, true);
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        String a = m7096a(currentTimeMillis, af);
                        TapjoyUtil.safePut(genericURLParams, "timestamp", String.valueOf(currentTimeMillis), true);
                        TapjoyUtil.safePut(genericURLParams, TapjoyConstants.TJC_VERIFIER, a, true);
                        responseFromURL = new TapjoyURLConnection().getResponseFromURL(getHostURL() + TapjoyConstants.TJC_SDK_LESS_CONNECT, genericURLParams);
                        if (responseFromURL != null && responseFromURL.statusCode == 200) {
                            TapjoyLog.m7126d("TapjoyConnect", "Successfully pinged sdkless api.");
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (!z) {
                    m7108d();
                }
                ev.f7701b.notifyObservers(Boolean.FALSE);
            }
        }
        z = false;
        responseFromURL = f7065j.getResponseFromURL(str + TapjoyConstants.TJC_CONNECT_URL_PATH, null, null, getURLParams());
        if (responseFromURL == null) {
        }
        if (z) {
            m7108d();
        }
        ev.f7701b.notifyObservers(Boolean.FALSE);
    }

    public void setCurrencyMultiplier(float multiplier) {
        TapjoyLog.m7129i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + multiplier);
        f7048Q = multiplier;
    }

    public float getCurrencyMultiplier() {
        return f7048Q;
    }

    public static String getConnectFlagValue(String key) {
        String str = "";
        if (ae == null || ae.get(key) == null) {
            return str;
        }
        return ae.get(key).toString();
    }

    public static String getSecretKey() {
        return f7043L;
    }

    public static String getAndroidID() {
        return f7069n;
    }

    public static String getSha1MacAddress() {
        String str = null;
        try {
            str = TapjoyUtil.SHA1(f7071p);
        } catch (Exception e) {
            TapjoyLog.m7128e("TapjoyConnect", "Error generating sha1 of macAddress: " + e.toString());
        }
        return str;
    }

    public static String getMacAddress() {
        return f7071p;
    }

    public static float getDeviceScreenDensityScale() {
        return f7032A;
    }

    public static String getSupportURL(String currencyId) {
        if (currencyId == null) {
            currencyId = f7077v;
        }
        return getHostURL() + "support_requests/new?currency_id=" + currencyId + "&app_id=" + f7077v + "&udid=" + f7053V + "&language_code=" + Locale.getDefault().getLanguage();
    }

    public static boolean isConnected() {
        return ab;
    }

    public static boolean isUnitTestMode() {
        return getConnectFlagValue("unit_test_mode") == "true";
    }

    /* renamed from: p */
    private static String m7122p() {
        String str = f7077v + f7078w + f7079x + f7058c + f7072q;
        try {
            str = TapjoyUtil.SHA1(str);
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: a */
    static /* synthetic */ void m7100a(boolean z) {
        if (z) {
            TapjoyLog.m7129i("TapjoyConnect", "Set userID is successful");
            if (f7067l != null) {
                f7067l.onSetUserIDSuccess();
                return;
            }
            return;
        }
        String str = "Failed to set userID";
        TapjoyLog.m7127e("TapjoyConnect", new TapjoyErrorMessage(ErrorType.SDK_ERROR, str));
        if (f7067l != null) {
            f7067l.onSetUserIDFailure(str);
        }
    }
}
