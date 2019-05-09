package com.moat.analytics.mobile.cha;

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

/* renamed from: com.moat.analytics.mobile.cha.n */
final class C1517n implements LocationListener {
    /* renamed from: ˎ */
    private static C1517n f3546 = null;
    /* renamed from: ʻ */
    private Location f3547;
    /* renamed from: ˊ */
    private ScheduledExecutorService f3548;
    /* renamed from: ˊॱ */
    private boolean f3549;
    /* renamed from: ˋ */
    private ScheduledFuture<?> f3550;
    /* renamed from: ˏ */
    private ScheduledFuture<?> f3551;
    /* renamed from: ॱ */
    private LocationManager f3552;
    /* renamed from: ᐝ */
    private boolean f3553;

    /* renamed from: com.moat.analytics.mobile.cha.n$1 */
    class C15151 implements Runnable {
        /* renamed from: ˏ */
        private /* synthetic */ C1517n f3544;

        C15151(C1517n c1517n) {
            this.f3544 = c1517n;
        }

        public final void run() {
            try {
                C1487a.m3715(3, "LocationManager", this, "fetchTimedOut");
                this.f3544.m3831(true);
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.n$2 */
    class C15162 implements Runnable {
        /* renamed from: ˋ */
        private /* synthetic */ C1517n f3545;

        C15162(C1517n c1517n) {
            this.f3545 = c1517n;
        }

        public final void run() {
            try {
                C1487a.m3715(3, "LocationManager", this, "fetchTimerCompleted");
                this.f3545.m3824();
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }
    }

    /* renamed from: ˏ */
    static C1517n m3826() {
        if (f3546 == null) {
            f3546 = new C1517n();
        }
        return f3546;
    }

    private C1517n() {
        try {
            this.f3549 = ((C1495f) MoatAnalytics.getInstance()).f3471;
            if (this.f3549) {
                C1487a.m3715(3, "LocationManager", this, "Moat location services disabled");
                return;
            }
            this.f3548 = Executors.newScheduledThreadPool(1);
            this.f3552 = (LocationManager) C1492c.m3748().getSystemService(PlaceFields.LOCATION);
            if (this.f3552.getAllProviders().size() == 0) {
                C1487a.m3715(3, "LocationManager", this, "Device has no location providers");
            } else {
                m3824();
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    @Nullable
    /* renamed from: ˊ */
    final Location m3835() {
        if (this.f3549 || this.f3552 == null) {
            return null;
        }
        return this.f3547;
    }

    /* renamed from: ˋ */
    final void m3836() {
        m3824();
    }

    /* renamed from: ॱ */
    final void m3837() {
        m3831(false);
    }

    public final void onLocationChanged(Location location) {
        try {
            C1487a.m3715(3, "LocationManager", this, "Received an updated location = " + location.toString());
            float currentTimeMillis = (float) ((System.currentTimeMillis() - location.getTime()) / 1000);
            if (location.hasAccuracy() && location.getAccuracy() <= 100.0f && currentTimeMillis < 600.0f) {
                this.f3547 = C1517n.m3829(this.f3547, location);
                C1487a.m3715(3, "LocationManager", this, "fetchCompleted");
                m3831(true);
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onProviderDisabled(String str) {
    }

    /* renamed from: ˎ */
    private void m3824() {
        try {
            if (!this.f3549 && this.f3552 != null) {
                if (this.f3553) {
                    C1487a.m3715(3, "LocationManager", this, "already updating location");
                }
                C1487a.m3715(3, "LocationManager", this, "starting location fetch");
                this.f3547 = C1517n.m3829(this.f3547, m3821());
                if (this.f3547 != null) {
                    C1487a.m3715(3, "LocationManager", this, "Have a valid location, won't fetch = " + this.f3547.toString());
                    m3827();
                    return;
                }
                m3819();
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    /* renamed from: ॱ */
    private void m3831(boolean z) {
        try {
            C1487a.m3715(3, "LocationManager", this, "stopping location fetch");
            m3820();
            m3823();
            if (z) {
                m3827();
            } else {
                m3834();
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    /* renamed from: ʽ */
    private Location m3821() {
        try {
            boolean ͺ = m3828();
            boolean ॱˊ = m3833();
            if (ͺ && ॱˊ) {
                return C1517n.m3829(this.f3552.getLastKnownLocation("gps"), this.f3552.getLastKnownLocation("network"));
            }
            if (ͺ) {
                return this.f3552.getLastKnownLocation("gps");
            }
            if (ॱˊ) {
                return this.f3552.getLastKnownLocation("network");
            }
            return null;
        } catch (Exception e) {
            C1518o.m3840(e);
            return null;
        }
    }

    /* renamed from: ʻ */
    private void m3819() {
        try {
            if (!this.f3553) {
                C1487a.m3715(3, "LocationManager", this, "Attempting to start update");
                if (m3828()) {
                    C1487a.m3715(3, "LocationManager", this, "start updating gps location");
                    this.f3552.requestLocationUpdates("gps", 0, 0.0f, this, Looper.getMainLooper());
                    this.f3553 = true;
                }
                if (m3833()) {
                    C1487a.m3715(3, "LocationManager", this, "start updating network location");
                    this.f3552.requestLocationUpdates("network", 0, 0.0f, this, Looper.getMainLooper());
                    this.f3553 = true;
                }
                if (this.f3553) {
                    m3823();
                    this.f3551 = this.f3548.schedule(new C15151(this), 60, TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: ʼ */
    private void m3820() {
        /*
        r5 = this;
        r1 = 1;
        r0 = 0;
        r2 = 3;
        r3 = "LocationManager";
        r4 = "Stopping to update location";
        com.moat.analytics.mobile.cha.C1487a.m3715(r2, r3, r5, r4);	 Catch:{ SecurityException -> 0x0044 }
        r2 = "android.permission.ACCESS_FINE_LOCATION";
        r3 = com.moat.analytics.mobile.cha.C1492c.m3748();	 Catch:{ SecurityException -> 0x0044 }
        r3 = r3.getApplicationContext();	 Catch:{ SecurityException -> 0x0044 }
        r2 = android.support.v4.content.ContextCompat.checkSelfPermission(r3, r2);	 Catch:{ SecurityException -> 0x0044 }
        if (r2 != 0) goto L_0x0040;
    L_0x001a:
        r2 = r1;
    L_0x001b:
        if (r2 != 0) goto L_0x0030;
    L_0x001d:
        r2 = "android.permission.ACCESS_COARSE_LOCATION";
        r3 = com.moat.analytics.mobile.cha.C1492c.m3748();	 Catch:{ SecurityException -> 0x0044 }
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
        r0 = r5.f3552;	 Catch:{ SecurityException -> 0x0044 }
        if (r0 == 0) goto L_0x003f;
    L_0x0037:
        r0 = r5.f3552;	 Catch:{ SecurityException -> 0x0044 }
        r0.removeUpdates(r5);	 Catch:{ SecurityException -> 0x0044 }
        r0 = 0;
        r5.f3553 = r0;	 Catch:{ SecurityException -> 0x0044 }
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
        com.moat.analytics.mobile.cha.C1518o.m3840(r0);
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.cha.n.ʼ():void");
    }

    /* renamed from: ˊॱ */
    private void m3823() {
        if (this.f3551 != null && !this.f3551.isCancelled()) {
            this.f3551.cancel(true);
            this.f3551 = null;
        }
    }

    /* renamed from: ᐝ */
    private void m3834() {
        if (this.f3550 != null && !this.f3550.isCancelled()) {
            this.f3550.cancel(true);
            this.f3550 = null;
        }
    }

    /* renamed from: ˏॱ */
    private void m3827() {
        float f = 600.0f;
        C1487a.m3715(3, "LocationManager", this, "Resetting fetch timer");
        m3834();
        if (this.f3547 != null) {
            f = Math.max(600.0f - ((float) ((System.currentTimeMillis() - this.f3547.getTime()) / 1000)), 0.0f);
        }
        this.f3550 = this.f3548.schedule(new C15162(this), (long) f, TimeUnit.SECONDS);
    }

    /* renamed from: ͺ */
    private boolean m3828() {
        boolean z;
        if (ContextCompat.checkSelfPermission(C1492c.m3748().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.f3552.getProvider("gps") != null && this.f3552.isProviderEnabled("gps")) {
            return true;
        }
        return false;
    }

    /* renamed from: ॱ */
    private static Location m3829(Location location, Location location2) {
        boolean ॱ = C1517n.m3832(location);
        boolean ॱ2 = C1517n.m3832(location2);
        if (ॱ) {
            if (ॱ2) {
                return location.getAccuracy() < location.getAccuracy() ? location : location2;
            } else {
                return location;
            }
        } else if (ॱ2) {
            return location2;
        } else {
            return null;
        }
    }

    /* renamed from: ॱ */
    private static boolean m3832(Location location) {
        if (location == null) {
            return false;
        }
        if ((location.getLatitude() != 0.0d || location.getLongitude() != 0.0d) && location.getAccuracy() >= 0.0f && ((float) ((System.currentTimeMillis() - location.getTime()) / 1000)) < 600.0f) {
            return true;
        }
        return false;
    }

    /* renamed from: ˎ */
    static boolean m3825(Location location, Location location2) {
        if (location == location2) {
            return true;
        }
        if (location == null || location2 == null || location.getTime() != location2.getTime()) {
            return false;
        }
        return true;
    }

    /* renamed from: ॱˊ */
    private boolean m3833() {
        boolean z;
        if (ContextCompat.checkSelfPermission(C1492c.m3748().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            if (ContextCompat.checkSelfPermission(C1492c.m3748().getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                z = false;
                if (z || this.f3552.getProvider("network") == null || !this.f3552.isProviderEnabled("network")) {
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
