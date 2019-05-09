package com.moat.analytics.mobile.iro;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import com.facebook.places.model.PlaceFields;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.moat.analytics.mobile.iro.k */
final class C0777k implements LocationListener {
    /* renamed from: ˏ */
    private static C0777k f1228 = null;
    /* renamed from: ʼ */
    private boolean f1229;
    /* renamed from: ʽ */
    private Location f1230;
    /* renamed from: ˊ */
    private ScheduledFuture<?> f1231;
    /* renamed from: ˋ */
    private ScheduledFuture<?> f1232;
    /* renamed from: ˎ */
    private LocationManager f1233;
    /* renamed from: ॱ */
    private ScheduledExecutorService f1234;
    /* renamed from: ᐝ */
    private boolean f1235;

    /* renamed from: com.moat.analytics.mobile.iro.k$1 */
    class C07751 implements Runnable {
        /* renamed from: ˋ */
        private /* synthetic */ C0777k f1226;

        C07751(C0777k c0777k) {
            this.f1226 = c0777k;
        }

        public final void run() {
            try {
                C0756b.m1234(3, "LocationManager", this, "fetchTimerCompleted");
                this.f1226.m1334();
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.k$5 */
    class C07765 implements Runnable {
        /* renamed from: ॱ */
        private /* synthetic */ C0777k f1227;

        C07765(C0777k c0777k) {
            this.f1227 = c0777k;
        }

        public final void run() {
            try {
                C0756b.m1234(3, "LocationManager", this, "fetchTimedOut");
                this.f1227.m1329(true);
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }
    }

    /* renamed from: ˎ */
    static C0777k m1332() {
        if (f1228 == null) {
            f1228 = new C0777k();
        }
        return f1228;
    }

    private C0777k() {
        try {
            this.f1235 = ((C0774j) MoatAnalytics.getInstance()).f1223;
            if (this.f1235) {
                C0756b.m1234(3, "LocationManager", this, "Moat location services disabled");
                return;
            }
            this.f1234 = Executors.newScheduledThreadPool(1);
            this.f1233 = (LocationManager) C0752a.m1226().getSystemService(PlaceFields.LOCATION);
            if (this.f1233.getAllProviders().size() == 0) {
                C0756b.m1234(3, "LocationManager", this, "Device has no location providers");
            } else {
                m1334();
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    @Nullable
    /* renamed from: ॱ */
    final Location m1341() {
        if (this.f1235 || this.f1233 == null) {
            return null;
        }
        return this.f1230;
    }

    /* renamed from: ˋ */
    final void m1340() {
        m1334();
    }

    /* renamed from: ˊ */
    final void m1339() {
        m1329(false);
    }

    public final void onLocationChanged(Location location) {
        try {
            C0756b.m1234(3, "LocationManager", this, "Received an updated location = " + location.toString());
            float currentTimeMillis = (float) ((System.currentTimeMillis() - location.getTime()) / 1000);
            if (location.hasAccuracy() && location.getAccuracy() <= 100.0f && currentTimeMillis < 600.0f) {
                this.f1230 = C0777k.m1326(this.f1230, location);
                C0756b.m1234(3, "LocationManager", this, "fetchCompleted");
                m1329(true);
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onProviderDisabled(String str) {
    }

    /* renamed from: ˏ */
    private void m1334() {
        try {
            if (!this.f1235 && this.f1233 != null) {
                if (this.f1229) {
                    C0756b.m1234(3, "LocationManager", this, "already updating location");
                }
                C0756b.m1234(3, "LocationManager", this, "starting location fetch");
                this.f1230 = C0777k.m1326(this.f1230, m1323());
                if (this.f1230 != null) {
                    C0756b.m1234(3, "LocationManager", this, "Have a valid location, won't fetch = " + this.f1230.toString());
                    m1335();
                    return;
                }
                m1338();
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    /* renamed from: ˋ */
    private void m1329(boolean z) {
        try {
            C0756b.m1234(3, "LocationManager", this, "stopping location fetch");
            m1325();
            m1328();
            if (z) {
                m1335();
            } else {
                m1324();
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    /* renamed from: ʻ */
    private Location m1323() {
        try {
            boolean ˋॱ = m1331();
            boolean ॱˋ = m1337();
            if (ˋॱ && ॱˋ) {
                return C0777k.m1326(this.f1233.getLastKnownLocation("gps"), this.f1233.getLastKnownLocation("network"));
            }
            if (ˋॱ) {
                return this.f1233.getLastKnownLocation("gps");
            }
            if (ॱˋ) {
                return this.f1233.getLastKnownLocation("network");
            }
            return null;
        } catch (Exception e) {
            C0785o.m1351(e);
            return null;
        }
    }

    /* renamed from: ᐝ */
    private void m1338() {
        try {
            if (!this.f1229) {
                C0756b.m1234(3, "LocationManager", this, "Attempting to start update");
                if (m1331()) {
                    C0756b.m1234(3, "LocationManager", this, "start updating gps location");
                    this.f1233.requestLocationUpdates("gps", 0, 0.0f, this, Looper.getMainLooper());
                    this.f1229 = true;
                }
                if (m1337()) {
                    C0756b.m1234(3, "LocationManager", this, "start updating network location");
                    this.f1233.requestLocationUpdates("network", 0, 0.0f, this, Looper.getMainLooper());
                    this.f1229 = true;
                }
                if (this.f1229) {
                    m1328();
                    this.f1232 = this.f1234.schedule(new C07765(this), 60, TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ʽ */
    private void m1325() {
        /*
        r5 = this;
        r1 = 1;
        r0 = 0;
        r2 = 3;
        r3 = "LocationManager";
        r4 = "Stopping to update location";
        com.moat.analytics.mobile.iro.C0756b.m1234(r2, r3, r5, r4);	 Catch:{ SecurityException -> 0x0044 }
        r2 = "android.permission.ACCESS_FINE_LOCATION";
        r3 = com.moat.analytics.mobile.iro.C0752a.m1226();	 Catch:{ SecurityException -> 0x0044 }
        r3 = r3.getApplicationContext();	 Catch:{ SecurityException -> 0x0044 }
        r2 = android.support.v4.content.ContextCompat.checkSelfPermission(r3, r2);	 Catch:{ SecurityException -> 0x0044 }
        if (r2 != 0) goto L_0x0040;
    L_0x001a:
        r2 = r1;
    L_0x001b:
        if (r2 != 0) goto L_0x0030;
    L_0x001d:
        r2 = "android.permission.ACCESS_COARSE_LOCATION";
        r3 = com.moat.analytics.mobile.iro.C0752a.m1226();	 Catch:{ SecurityException -> 0x0044 }
        r3 = r3.getApplicationContext();	 Catch:{ SecurityException -> 0x0044 }
        r2 = android.support.v4.content.ContextCompat.checkSelfPermission(r3, r2);	 Catch:{ SecurityException -> 0x0044 }
        if (r2 != 0) goto L_0x0042;
    L_0x002d:
        r2 = r1;
    L_0x002e:
        if (r2 == 0) goto L_0x0031;
    L_0x0030:
        r0 = r1;
    L_0x0031:
        if (r0 == 0) goto L_0x003f;
    L_0x0033:
        r0 = r5.f1233;	 Catch:{ SecurityException -> 0x0044 }
        if (r0 == 0) goto L_0x003f;
    L_0x0037:
        r0 = r5.f1233;	 Catch:{ SecurityException -> 0x0044 }
        r0.removeUpdates(r5);	 Catch:{ SecurityException -> 0x0044 }
        r0 = 0;
        r5.f1229 = r0;	 Catch:{ SecurityException -> 0x0044 }
    L_0x003f:
        return;
    L_0x0040:
        r2 = r0;
        goto L_0x001b;
    L_0x0042:
        r2 = r0;
        goto L_0x002e;
    L_0x0044:
        r0 = move-exception;
        com.moat.analytics.mobile.iro.C0785o.m1351(r0);
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.iro.k.ʽ():void");
    }

    /* renamed from: ˊॱ */
    private void m1328() {
        if (this.f1232 != null && !this.f1232.isCancelled()) {
            this.f1232.cancel(true);
            this.f1232 = null;
        }
    }

    /* renamed from: ʼ */
    private void m1324() {
        if (this.f1231 != null && !this.f1231.isCancelled()) {
            this.f1231.cancel(true);
            this.f1231 = null;
        }
    }

    /* renamed from: ˏॱ */
    private void m1335() {
        float f = 600.0f;
        C0756b.m1234(3, "LocationManager", this, "Resetting fetch timer");
        m1324();
        if (this.f1230 != null) {
            f = Math.max(600.0f - ((float) ((System.currentTimeMillis() - this.f1230.getTime()) / 1000)), 0.0f);
        }
        this.f1231 = this.f1234.schedule(new C07751(this), (long) f, TimeUnit.SECONDS);
    }

    /* renamed from: ˋॱ */
    private boolean m1331() {
        boolean z;
        if (ContextCompat.checkSelfPermission(C0752a.m1226().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.f1233.getProvider("gps") != null && this.f1233.isProviderEnabled("gps")) {
            return true;
        }
        return false;
    }

    /* renamed from: ˊ */
    private static Location m1326(Location location, Location location2) {
        boolean ˋ = C0777k.m1330(location);
        boolean ˋ2 = C0777k.m1330(location2);
        if (ˋ) {
            if (ˋ2) {
                return location.getAccuracy() < location.getAccuracy() ? location : location2;
            } else {
                return location;
            }
        } else if (ˋ2) {
            return location2;
        } else {
            return null;
        }
    }

    /* renamed from: ˋ */
    private static boolean m1330(Location location) {
        if (location == null) {
            return false;
        }
        if ((location.getLatitude() != 0.0d || location.getLongitude() != 0.0d) && location.getAccuracy() >= 0.0f && ((float) ((System.currentTimeMillis() - location.getTime()) / 1000)) < 600.0f) {
            return true;
        }
        return false;
    }

    /* renamed from: ˎ */
    static boolean m1333(Location location, Location location2) {
        if (location == location2) {
            return true;
        }
        if (location == null || location2 == null || location.getTime() != location2.getTime()) {
            return false;
        }
        return true;
    }

    /* renamed from: ॱˋ */
    private boolean m1337() {
        boolean z;
        if (ContextCompat.checkSelfPermission(C0752a.m1226().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            if (ContextCompat.checkSelfPermission(C0752a.m1226().getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                z = false;
                if (z || this.f1233.getProvider("network") == null || !this.f1233.isProviderEnabled("network")) {
                    return false;
                }
                return true;
            }
        }
        z = true;
        if (z) {
        }
        return false;
    }
}
