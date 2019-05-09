package com.moat.analytics.mobile.iro;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.support.annotation.FloatRange;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.iro.p */
final class C0789p {
    /* renamed from: ˊ */
    private static long f1260 = 9141242330850693853L;
    /* renamed from: ˋ */
    private static String f1261;
    /* renamed from: ˏ */
    private static C0787a f1262 = null;
    /* renamed from: ॱ */
    private static C0788b f1263 = null;

    /* renamed from: com.moat.analytics.mobile.iro.p$a */
    static class C0787a {
        /* renamed from: ˊ */
        String f1250;
        /* renamed from: ˋ */
        String f1251;
        /* renamed from: ˎ */
        Integer f1252;
        /* renamed from: ˏ */
        boolean f1253;
        /* renamed from: ॱ */
        boolean f1254;
        /* renamed from: ᐝ */
        boolean f1255;

        private C0787a() {
            this.f1251 = "_unknown_";
            this.f1250 = "_unknown_";
            this.f1252 = Integer.valueOf(-1);
            this.f1253 = false;
            this.f1254 = false;
            this.f1255 = false;
            try {
                Context ˋ = C0789p.m1361();
                if (ˋ != null) {
                    this.f1255 = true;
                    TelephonyManager telephonyManager = (TelephonyManager) ˋ.getSystemService(PlaceFields.PHONE);
                    this.f1251 = telephonyManager.getSimOperatorName();
                    this.f1250 = telephonyManager.getNetworkOperatorName();
                    this.f1252 = Integer.valueOf(telephonyManager.getPhoneType());
                    this.f1253 = C0789p.m1360();
                    this.f1254 = C0789p.m1364(ˋ);
                }
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.p$b */
    static class C0788b {
        /* renamed from: ˋ */
        private String f1256;
        /* renamed from: ˎ */
        private String f1257;
        /* renamed from: ˏ */
        private String f1258;
        /* renamed from: ॱ */
        private boolean f1259;

        private C0788b() {
            this.f1259 = false;
            this.f1257 = "_unknown_";
            this.f1256 = "_unknown_";
            this.f1258 = "_unknown_";
            try {
                Context ˋ = C0789p.m1361();
                if (ˋ != null) {
                    this.f1259 = true;
                    PackageManager packageManager = ˋ.getPackageManager();
                    this.f1256 = ˋ.getPackageName();
                    this.f1257 = packageManager.getApplicationLabel(ˋ.getApplicationInfo()).toString();
                    this.f1258 = packageManager.getInstallerPackageName(this.f1256);
                    return;
                }
                C0756b.m1234(3, "Util", this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }

        /* renamed from: ˏ */
        final String m1354() {
            return this.f1257;
        }

        /* renamed from: ˋ */
        final String m1353() {
            return this.f1256;
        }

        /* renamed from: ॱ */
        final String m1355() {
            return this.f1258 != null ? this.f1258 : "_unknown_";
        }
    }

    C0789p() {
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    /* renamed from: ॱ */
    static double m1366() {
        try {
            AudioManager audioManager = (AudioManager) C0752a.m1226().getSystemService(C0789p.m1367("㇕ꍩ߆嗠殛").intern());
            return ((double) C0789p.m1357()) / ((double) audioManager.getStreamMaxVolume(3));
        } catch (Exception e) {
            C0785o.m1351(e);
            return 0.0d;
        }
    }

    /* renamed from: ʼ */
    private static int m1357() {
        try {
            return ((AudioManager) C0752a.m1226().getSystemService(C0789p.m1367("㇕ꍩ߆嗠殛").intern())).getStreamVolume(3);
        } catch (Exception e) {
            C0785o.m1351(e);
            return 0;
        }
    }

    /* renamed from: ˊ */
    static void m1359(final Application application) {
        try {
            AsyncTask.execute(new Runnable() {
                public final void run() {
                    try {
                        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(application);
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            C0756b.m1234(3, "Util", this, "User has limited ad tracking");
                            return;
                        }
                        C0789p.f1261 = advertisingIdInfo.getId();
                        C0756b.m1234(3, "Util", this, "Retrieved Advertising ID = " + C0789p.f1261);
                    } catch (Exception e) {
                        C0785o.m1351(e);
                    }
                }
            });
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    /* renamed from: ॱ */
    private static String m1367(String str) {
        char[] toCharArray = str.toCharArray();
        char c = toCharArray[0];
        char[] cArr = new char[(toCharArray.length - 1)];
        int i = 1;
        while (true) {
            switch (i < toCharArray.length ? 75 : 11) {
                case 75:
                    cArr[i - 1] = (char) ((int) (((long) (toCharArray[i] ^ (i * c))) ^ f1260));
                    i++;
                default:
                    return new String(cArr);
            }
        }
    }

    /* renamed from: ˏ */
    static String m1365() {
        return f1261;
    }

    /* renamed from: ˋ */
    static Context m1361() {
        WeakReference weakReference = ((C0774j) MoatAnalytics.getInstance()).f1221;
        if (weakReference != null) {
            return (Context) weakReference.get();
        }
        return null;
    }

    /* renamed from: ˎ */
    static C0788b m1362() {
        if (f1263 == null || !f1263.f1259) {
            f1263 = new C0788b();
        }
        return f1263;
    }

    /* renamed from: ˊ */
    static C0787a m1358() {
        if (f1262 == null || !f1262.f1255) {
            f1262 = new C0787a();
        }
        return f1262;
    }

    /* renamed from: ˎ */
    static boolean m1364(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* renamed from: ˊॱ */
    static /* synthetic */ boolean m1360() {
        Context context;
        int i;
        WeakReference weakReference = ((C0774j) MoatAnalytics.getInstance()).f1221;
        if (weakReference != null) {
            context = (Context) weakReference.get();
        } else {
            context = null;
        }
        switch (context == null ? 57 : 88) {
            case 57:
                i = 0;
                break;
            default:
                switch (VERSION.SDK_INT < 17 ? 14 : 74) {
                    case 74:
                        i = Global.getInt(context.getContentResolver(), C0789p.m1367("涓Ｏ䦟?Ⓨ녧ρ涹︧䢚픆⟨").intern(), 0);
                        break;
                    default:
                        i = Secure.getInt(context.getContentResolver(), C0789p.m1367("涓Ｏ䦟?Ⓨ녧ρ涹︧䢚픆⟨").intern(), 0);
                        break;
                }
        }
        switch (i != 1 ? 99 : 47) {
            case 99:
                return false;
            default:
                return true;
        }
    }
}
