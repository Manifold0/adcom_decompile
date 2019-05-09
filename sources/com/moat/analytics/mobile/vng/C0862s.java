package com.moat.analytics.mobile.vng;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.support.annotation.FloatRange;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

/* renamed from: com.moat.analytics.mobile.vng.s */
class C0862s {
    /* renamed from: a */
    private static String f1457a;

    /* renamed from: com.moat.analytics.mobile.vng.s$a */
    static class C0861a {
        /* renamed from: a */
        private boolean f1454a = false;
        /* renamed from: b */
        private String f1455b = "_unknown_";
        /* renamed from: c */
        private String f1456c = "_unknown_";

        C0861a() {
        }

        /* renamed from: c */
        private void m1583c() {
            try {
                Context c = C0862s.m1590c();
                if (c != null) {
                    PackageManager packageManager = c.getPackageManager();
                    this.f1456c = c.getPackageName();
                    this.f1455b = packageManager.getApplicationLabel(c.getApplicationInfo()).toString();
                    this.f1454a = true;
                    return;
                }
                C0858p.m1577a(3, "Util", (Object) this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }

        /* renamed from: a */
        String m1584a() {
            if (this.f1454a) {
                return this.f1455b;
            }
            m1583c();
            return this.f1455b;
        }

        /* renamed from: b */
        String m1585b() {
            if (this.f1454a) {
                return this.f1456c;
            }
            m1583c();
            return this.f1456c;
        }
    }

    C0862s() {
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    /* renamed from: a */
    static double m1586a() {
        try {
            AudioManager audioManager = (AudioManager) C0821a.m1439a().getSystemService("audio");
            return ((double) C0862s.m1592e()) / ((double) audioManager.getStreamMaxVolume(3));
        } catch (Exception e) {
            C0849m.m1543a(e);
            return 0.0d;
        }
    }

    /* renamed from: a */
    static void m1588a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() {
                public void run() {
                    try {
                        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            C0858p.m1577a(3, "Util", (Object) this, "User has limited ad tracking");
                            return;
                        }
                        C0862s.f1457a = advertisingIdInfo.getId();
                        C0858p.m1577a(3, "Util", (Object) this, "Retrieved Advertising ID = " + C0862s.f1457a);
                    } catch (Exception e) {
                        C0849m.m1543a(e);
                    }
                }
            });
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: b */
    static String m1589b() {
        return f1457a;
    }

    /* renamed from: c */
    static Context m1590c() {
        return (Context) ((C0847k) MoatAnalytics.getInstance()).f1427e.get();
    }

    /* renamed from: e */
    private static int m1592e() {
        try {
            return ((AudioManager) C0821a.m1439a().getSystemService("audio")).getStreamVolume(3);
        } catch (Exception e) {
            C0849m.m1543a(e);
            return 0;
        }
    }
}
