package com.moat.analytics.mobile.vng;

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

/* renamed from: com.moat.analytics.mobile.vng.o */
class C0857o implements LocationListener {
    /* renamed from: a */
    private static C0857o f1445a = null;
    /* renamed from: b */
    private ScheduledExecutorService f1446b;
    /* renamed from: c */
    private ScheduledFuture<?> f1447c;
    /* renamed from: d */
    private ScheduledFuture<?> f1448d;
    /* renamed from: e */
    private LocationManager f1449e;
    /* renamed from: f */
    private boolean f1450f;
    /* renamed from: g */
    private Location f1451g;
    /* renamed from: h */
    private boolean f1452h;

    /* renamed from: com.moat.analytics.mobile.vng.o$1 */
    class C08551 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0857o f1443a;

        C08551(C0857o c0857o) {
            this.f1443a = c0857o;
        }

        public void run() {
            try {
                C0858p.m1577a(3, "LocationManager", (Object) this, "fetchTimedOut");
                this.f1443a.m1557a(true);
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.o$2 */
    class C08562 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0857o f1444a;

        C08562(C0857o c0857o) {
            this.f1444a = c0857o;
        }

        public void run() {
            try {
                C0858p.m1577a(3, "LocationManager", (Object) this, "fetchTimerCompleted");
                this.f1444a.m1563e();
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
    }

    private C0857o() {
        try {
            this.f1450f = ((C0847k) MoatAnalytics.getInstance()).f1425c;
            if (this.f1450f) {
                C0858p.m1577a(3, "LocationManager", (Object) this, "Moat location services disabled");
                return;
            }
            this.f1446b = Executors.newScheduledThreadPool(1);
            this.f1449e = (LocationManager) C0821a.m1439a().getSystemService(PlaceFields.LOCATION);
            if (this.f1449e.getAllProviders().size() == 0) {
                C0858p.m1577a(3, "LocationManager", (Object) this, "Device has no location providers");
            } else {
                m1563e();
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: a */
    static C0857o m1554a() {
        if (f1445a == null) {
            f1445a = new C0857o();
        }
        return f1445a;
    }

    /* renamed from: a */
    private void m1557a(boolean z) {
        try {
            C0858p.m1577a(3, "LocationManager", (Object) this, "stopping location fetch");
            m1566h();
            m1567i();
            if (z) {
                m1569k();
            } else {
                m1568j();
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: a */
    private static boolean m1558a(Location location) {
        return location == null ? false : !(location.getLatitude() == 0.0d && location.getLongitude() == 0.0d) && location.getAccuracy() >= 0.0f && C0857o.m1561b(location) < 600.0f;
    }

    /* renamed from: a */
    static boolean m1559a(Location location, Location location2) {
        return location == location2 ? true : (location == null || location2 == null || location.getTime() != location2.getTime()) ? false : true;
    }

    /* renamed from: a */
    private static boolean m1560a(String str) {
        return ContextCompat.checkSelfPermission(C0821a.m1439a().getApplicationContext(), str) == 0;
    }

    /* renamed from: b */
    private static float m1561b(Location location) {
        return (float) ((System.currentTimeMillis() - location.getTime()) / 1000);
    }

    /* renamed from: b */
    private static Location m1562b(Location location, Location location2) {
        boolean a = C0857o.m1558a(location);
        boolean a2 = C0857o.m1558a(location2);
        return !a ? !a2 ? null : location2 : (!a2 || location.getAccuracy() < location.getAccuracy()) ? location : location2;
    }

    /* renamed from: e */
    private void m1563e() {
        try {
            if (!this.f1450f && this.f1449e != null) {
                if (this.f1452h) {
                    C0858p.m1577a(3, "LocationManager", (Object) this, "already updating location");
                }
                C0858p.m1577a(3, "LocationManager", (Object) this, "starting location fetch");
                Location b = m1573b();
                if (b != null) {
                    C0858p.m1577a(3, "LocationManager", (Object) this, "Have a valid location, won't fetch = " + b.toString());
                    m1569k();
                    return;
                }
                m1565g();
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: f */
    private Location m1564f() {
        try {
            boolean l = m1570l();
            boolean m = m1571m();
            return (l && m) ? C0857o.m1562b(this.f1449e.getLastKnownLocation("gps"), this.f1449e.getLastKnownLocation("network")) : l ? this.f1449e.getLastKnownLocation("gps") : m ? this.f1449e.getLastKnownLocation("network") : null;
        } catch (Exception e) {
            C0849m.m1543a(e);
            return null;
        }
    }

    /* renamed from: g */
    private void m1565g() {
        try {
            if (!this.f1452h) {
                C0858p.m1577a(3, "LocationManager", (Object) this, "Attempting to start update");
                if (m1570l()) {
                    C0858p.m1577a(3, "LocationManager", (Object) this, "start updating gps location");
                    this.f1449e.requestLocationUpdates("gps", 0, 0.0f, this, Looper.getMainLooper());
                    this.f1452h = true;
                }
                if (m1571m()) {
                    C0858p.m1577a(3, "LocationManager", (Object) this, "start updating network location");
                    this.f1449e.requestLocationUpdates("network", 0, 0.0f, this, Looper.getMainLooper());
                    this.f1452h = true;
                }
                if (this.f1452h) {
                    m1567i();
                    this.f1448d = this.f1446b.schedule(new C08551(this), 60, TimeUnit.SECONDS);
                }
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: h */
    private void m1566h() {
        try {
            C0858p.m1577a(3, "LocationManager", (Object) this, "Stopping to update location");
            if (C0857o.m1572n() && this.f1449e != null) {
                this.f1449e.removeUpdates(this);
                this.f1452h = false;
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: i */
    private void m1567i() {
        if (this.f1448d != null && !this.f1448d.isCancelled()) {
            this.f1448d.cancel(true);
            this.f1448d = null;
        }
    }

    /* renamed from: j */
    private void m1568j() {
        if (this.f1447c != null && !this.f1447c.isCancelled()) {
            this.f1447c.cancel(true);
            this.f1447c = null;
        }
    }

    /* renamed from: k */
    private void m1569k() {
        float f = 600.0f;
        C0858p.m1577a(3, "LocationManager", (Object) this, "Resetting fetch timer");
        m1568j();
        Location b = m1573b();
        if (b != null) {
            f = Math.max(600.0f - C0857o.m1561b(b), 0.0f);
        }
        this.f1447c = this.f1446b.schedule(new C08562(this), (long) f, TimeUnit.SECONDS);
    }

    /* renamed from: l */
    private boolean m1570l() {
        return C0857o.m1560a("android.permission.ACCESS_FINE_LOCATION") && this.f1449e.getProvider("gps") != null && this.f1449e.isProviderEnabled("gps");
    }

    /* renamed from: m */
    private boolean m1571m() {
        return C0857o.m1572n() && this.f1449e.getProvider("network") != null && this.f1449e.isProviderEnabled("network");
    }

    /* renamed from: n */
    private static boolean m1572n() {
        return C0857o.m1560a("android.permission.ACCESS_FINE_LOCATION") || C0857o.m1560a("android.permission.ACCESS_COARSE_LOCATION");
    }

    @Nullable
    /* renamed from: b */
    Location m1573b() {
        Location location = null;
        if (!(this.f1450f || this.f1449e == null)) {
            try {
                this.f1451g = C0857o.m1562b(this.f1451g, m1564f());
                location = this.f1451g;
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
        return location;
    }

    /* renamed from: c */
    void m1574c() {
        m1563e();
    }

    /* renamed from: d */
    void m1575d() {
        m1557a(false);
    }

    public void onLocationChanged(Location location) {
        try {
            C0858p.m1577a(3, "LocationManager", (Object) this, "Received an updated location = " + location.toString());
            float b = C0857o.m1561b(location);
            if (location.hasAccuracy() && location.getAccuracy() <= 100.0f && b < 600.0f) {
                this.f1451g = C0857o.m1562b(this.f1451g, location);
                C0858p.m1577a(3, "LocationManager", (Object) this, "fetchCompleted");
                m1557a(true);
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
