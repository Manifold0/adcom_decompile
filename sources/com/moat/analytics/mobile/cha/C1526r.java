package com.moat.analytics.mobile.cha;

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
import com.chartboost.sdk.impl.C1426b;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.cha.r */
final class C1526r {
    /* renamed from: ʻ */
    private static int f3581 = 1;
    /* renamed from: ˊ */
    private static C1525e f3582 = null;
    /* renamed from: ˋ */
    private static C1524d f3583 = null;
    /* renamed from: ˎ */
    private static int f3584 = 0;
    /* renamed from: ˏ */
    private static String f3585;
    /* renamed from: ॱ */
    private static int[] f3586 = new int[]{-39340411, 1646369784, -593413711, -1069164445, -50787683, -1327220997, 423245644, -742130253, 54775946, -495304555, 1880137505, 1742082653, 65717847, 1497802820, 828947133, -614454858, 941569790, -1897799303};

    /* renamed from: com.moat.analytics.mobile.cha.r$d */
    static class C1524d {
        /* renamed from: ʽ */
        boolean f3571;
        /* renamed from: ˊ */
        boolean f3572;
        /* renamed from: ˋ */
        boolean f3573;
        /* renamed from: ˎ */
        String f3574;
        /* renamed from: ˏ */
        String f3575;
        /* renamed from: ॱ */
        Integer f3576;

        private C1524d() {
            this.f3574 = "_unknown_";
            this.f3575 = "_unknown_";
            this.f3576 = Integer.valueOf(-1);
            this.f3573 = false;
            this.f3572 = false;
            this.f3571 = false;
            try {
                Context ˏ = C1526r.m3866();
                if (ˏ != null) {
                    this.f3571 = true;
                    TelephonyManager telephonyManager = (TelephonyManager) ˏ.getSystemService(PlaceFields.PHONE);
                    this.f3574 = telephonyManager.getSimOperatorName();
                    this.f3575 = telephonyManager.getNetworkOperatorName();
                    this.f3576 = Integer.valueOf(telephonyManager.getPhoneType());
                    this.f3573 = C1526r.m3857();
                    this.f3572 = C1526r.m3862(ˏ);
                }
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.r$e */
    static class C1525e {
        /* renamed from: ˊ */
        private String f3577;
        /* renamed from: ˋ */
        private String f3578;
        /* renamed from: ˏ */
        private boolean f3579;
        /* renamed from: ॱ */
        private String f3580;

        private C1525e() {
            this.f3579 = false;
            this.f3577 = "_unknown_";
            this.f3578 = "_unknown_";
            this.f3580 = "_unknown_";
            try {
                Context ˏ = C1526r.m3866();
                if (ˏ != null) {
                    this.f3579 = true;
                    PackageManager packageManager = ˏ.getPackageManager();
                    this.f3578 = ˏ.getPackageName();
                    this.f3577 = packageManager.getApplicationLabel(ˏ.getApplicationInfo()).toString();
                    this.f3580 = packageManager.getInstallerPackageName(this.f3578);
                    return;
                }
                C1487a.m3715(3, "Util", this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }

        /* renamed from: ˎ */
        final String m3855() {
            return this.f3577;
        }

        /* renamed from: ˋ */
        final String m3854() {
            return this.f3578;
        }

        /* renamed from: ॱ */
        final String m3856() {
            return this.f3580 != null ? this.f3580 : "_unknown_";
        }
    }

    C1526r() {
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    /* renamed from: ॱ */
    static double m3867() {
        try {
            AudioManager audioManager = (AudioManager) C1492c.m3748().getSystemService(C1526r.m3864(new int[]{-1741845568, 995393484, -1443163044, -1832527325}, 5).intern());
            return ((double) C1526r.m3858()) / ((double) audioManager.getStreamMaxVolume(3));
        } catch (Exception e) {
            C1518o.m3840(e);
            return 0.0d;
        }
    }

    /* renamed from: ʼ */
    private static int m3858() {
        try {
            return ((AudioManager) C1492c.m3748().getSystemService(C1526r.m3864(new int[]{-1741845568, 995393484, -1443163044, -1832527325}, 5).intern())).getStreamVolume(3);
        } catch (Exception e) {
            C1518o.m3840(e);
            return 0;
        }
    }

    /* renamed from: ˎ */
    static void m3865(final Application application) {
        try {
            AsyncTask.execute(new Runnable() {
                public final void run() {
                    try {
                        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(application);
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            C1487a.m3715(3, "Util", this, "User has limited ad tracking");
                            return;
                        }
                        C1526r.f3585 = advertisingIdInfo.getId();
                        C1487a.m3715(3, "Util", this, "Retrieved Advertising ID = " + C1526r.f3585);
                    } catch (Exception e) {
                        C1518o.m3840(e);
                    }
                }
            });
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    /* renamed from: ˎ */
    static String m3863() {
        return f3585;
    }

    /* renamed from: ˏ */
    static Context m3866() {
        WeakReference weakReference = ((C1495f) MoatAnalytics.getInstance()).f3470;
        if (weakReference != null) {
            return (Context) weakReference.get();
        }
        return null;
    }

    /* renamed from: ˊ */
    static C1525e m3860() {
        if (f3582 == null || !f3582.f3579) {
            f3582 = new C1525e();
        }
        return f3582;
    }

    /* renamed from: ˎ */
    private static String m3864(int[] iArr, int i) {
        char[] cArr = new char[4];
        char[] cArr2 = new char[(iArr.length << 1)];
        int[] iArr2 = (int[]) f3586.clone();
        int i2 = 0;
        while (true) {
            switch (i2 >= iArr.length) {
                case true:
                    return new String(cArr2, 0, i);
                default:
                    cArr[0] = iArr[i2] >>> 16;
                    cArr[1] = (char) iArr[i2];
                    cArr[2] = iArr[i2 + 1] >>> 16;
                    cArr[3] = (char) iArr[i2 + 1];
                    C1426b.m3474a(cArr, iArr2, false);
                    cArr2[i2 << 1] = cArr[0];
                    cArr2[(i2 << 1) + 1] = cArr[1];
                    cArr2[(i2 << 1) + 2] = cArr[2];
                    cArr2[(i2 << 1) + 3] = cArr[3];
                    i2 += 2;
            }
        }
    }

    /* renamed from: ˋ */
    static C1524d m3861() {
        if (f3583 == null || !f3583.f3571) {
            f3583 = new C1524d();
        }
        return f3583;
    }

    /* renamed from: ˋ */
    static boolean m3862(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* renamed from: ʻ */
    static /* synthetic */ boolean m3857() {
        Context context;
        int i;
        WeakReference weakReference = ((C1495f) MoatAnalytics.getInstance()).f3470;
        if (weakReference != null) {
            context = (Context) weakReference.get();
        } else {
            context = null;
        }
        if (context == null) {
            i = 0;
        } else {
            i = 1;
        }
        switch (i) {
            case 0:
                i = 0;
                break;
            default:
                Object obj;
                i = f3581 + 27;
                f3584 = i % 128;
                if (i % 2 != 0) {
                }
                if (VERSION.SDK_INT < 17) {
                    obj = 22;
                } else {
                    obj = 19;
                }
                switch (obj) {
                    case 22:
                        i = Secure.getInt(context.getContentResolver(), C1526r.m3864(new int[]{-474338915, -1244865125, 562481890, 44523707, -1306238932, 74746991}, 11).intern(), 0);
                        break;
                    default:
                        i = f3581 + 87;
                        f3584 = i % 128;
                        if (i % 2 != 0) {
                            i = Global.getInt(context.getContentResolver(), C1526r.m3864(new int[]{-474338915, -1244865125, 562481890, 44523707, -1306238932, 74746991}, 11).intern(), 0);
                            break;
                        }
                        i = Global.getInt(context.getContentResolver(), C1526r.m3864(new int[]{-474338915, -1244865125, 562481890, 44523707, -1306238932, 74746991}, 11).intern(), 0);
                }
        }
        if (i != 1) {
            i = 0;
        } else {
            i = 1;
        }
        switch (i) {
            case 1:
                i = f3584 + 33;
                f3581 = i % 128;
                if (i % 2 == 0) {
                }
                return true;
            default:
                return false;
        }
    }
}
