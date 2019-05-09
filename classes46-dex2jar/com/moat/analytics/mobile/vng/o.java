// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.concurrent.TimeUnit;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import java.util.concurrent.Executors;
import android.location.Location;
import android.location.LocationManager;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import android.location.LocationListener;

class o implements LocationListener
{
    private static o a;
    private ScheduledExecutorService b;
    private ScheduledFuture<?> c;
    private ScheduledFuture<?> d;
    private LocationManager e;
    private boolean f;
    private Location g;
    private boolean h;
    
    static {
        o.a = null;
    }
    
    private o() {
        try {
            this.f = ((k)MoatAnalytics.getInstance()).c;
            if (this.f) {
                p.a(3, "LocationManager", this, "Moat location services disabled");
                return;
            }
            this.b = Executors.newScheduledThreadPool(1);
            this.e = (LocationManager)com.moat.analytics.mobile.vng.a.a().getSystemService("location");
            if (this.e.getAllProviders().size() == 0) {
                p.a(3, "LocationManager", this, "Device has no location providers");
                return;
            }
        }
        catch (Exception ex) {
            m.a(ex);
            return;
        }
        this.e();
    }
    
    static o a() {
        if (o.a == null) {
            o.a = new o();
        }
        return o.a;
    }
    
    private void a(final boolean b) {
        try {
            p.a(3, "LocationManager", this, "stopping location fetch");
            this.h();
            this.i();
            if (b) {
                this.k();
                return;
            }
            this.j();
        }
        catch (Exception ex) {
            m.a(ex);
        }
    }
    
    private static boolean a(final Location location) {
        return location != null && (location.getLatitude() != 0.0 || location.getLongitude() != 0.0) && location.getAccuracy() >= 0.0f && b(location) < 600.0f;
    }
    
    static boolean a(final Location location, final Location location2) {
        return location == location2 || (location != null && location2 != null && location.getTime() == location2.getTime());
    }
    
    private static boolean a(final String s) {
        return ContextCompat.checkSelfPermission(com.moat.analytics.mobile.vng.a.a().getApplicationContext(), s) == 0;
    }
    
    private static float b(final Location location) {
        return (float)((System.currentTimeMillis() - location.getTime()) / 1000L);
    }
    
    private static Location b(final Location location, final Location location2) {
        final boolean a = a(location);
        final boolean a2 = a(location2);
        Location location3;
        if (!a) {
            if (a2) {
                return location2;
            }
            location3 = null;
        }
        else {
            location3 = location;
            if (a2) {
                location3 = location;
                if (location.getAccuracy() >= location.getAccuracy()) {
                    return location2;
                }
            }
        }
        return location3;
    }
    
    private void e() {
        try {
            if (this.f) {
                return;
            }
            if (this.e == null) {
                return;
            }
            if (this.h) {
                p.a(3, "LocationManager", this, "already updating location");
            }
            p.a(3, "LocationManager", this, "starting location fetch");
            final Location b = this.b();
            if (b != null) {
                p.a(3, "LocationManager", this, "Have a valid location, won't fetch = " + b.toString());
                this.k();
                return;
            }
        }
        catch (Exception ex) {
            m.a(ex);
            return;
        }
        this.g();
    }
    
    private Location f() {
        try {
            final boolean l = this.l();
            final boolean m = this.m();
            if (l && m) {
                return b(this.e.getLastKnownLocation("gps"), this.e.getLastKnownLocation("network"));
            }
            if (l) {
                return this.e.getLastKnownLocation("gps");
            }
            if (m) {
                return this.e.getLastKnownLocation("network");
            }
        }
        catch (SecurityException ex) {
            m.a(ex);
        }
        return null;
    }
    
    private void g() {
        try {
            if (!this.h) {
                p.a(3, "LocationManager", this, "Attempting to start update");
                if (this.l()) {
                    p.a(3, "LocationManager", this, "start updating gps location");
                    this.e.requestLocationUpdates("gps", 0L, 0.0f, (LocationListener)this, Looper.getMainLooper());
                    this.h = true;
                }
                if (this.m()) {
                    p.a(3, "LocationManager", this, "start updating network location");
                    this.e.requestLocationUpdates("network", 0L, 0.0f, (LocationListener)this, Looper.getMainLooper());
                    this.h = true;
                }
                if (this.h) {
                    this.i();
                    this.d = this.b.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                p.a(3, "LocationManager", this, "fetchTimedOut");
                                o.this.a(true);
                            }
                            catch (Exception ex) {
                                m.a(ex);
                            }
                        }
                    }, 60L, TimeUnit.SECONDS);
                }
            }
        }
        catch (SecurityException ex) {
            m.a(ex);
        }
    }
    
    private void h() {
        try {
            p.a(3, "LocationManager", this, "Stopping to update location");
            if (n() && this.e != null) {
                this.e.removeUpdates((LocationListener)this);
                this.h = false;
            }
        }
        catch (SecurityException ex) {
            m.a(ex);
        }
    }
    
    private void i() {
        if (this.d != null && !this.d.isCancelled()) {
            this.d.cancel(true);
            this.d = null;
        }
    }
    
    private void j() {
        if (this.c != null && !this.c.isCancelled()) {
            this.c.cancel(true);
            this.c = null;
        }
    }
    
    private void k() {
        float max = 600.0f;
        p.a(3, "LocationManager", this, "Resetting fetch timer");
        this.j();
        final Location b = this.b();
        if (b != null) {
            max = Math.max(600.0f - b(b), 0.0f);
        }
        this.c = this.b.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    p.a(3, "LocationManager", this, "fetchTimerCompleted");
                    o.this.e();
                }
                catch (Exception ex) {
                    m.a(ex);
                }
            }
        }, (long)max, TimeUnit.SECONDS);
    }
    
    private boolean l() {
        return a("android.permission.ACCESS_FINE_LOCATION") && this.e.getProvider("gps") != null && this.e.isProviderEnabled("gps");
    }
    
    private boolean m() {
        return n() && this.e.getProvider("network") != null && this.e.isProviderEnabled("network");
    }
    
    private static boolean n() {
        return a("android.permission.ACCESS_FINE_LOCATION") || a("android.permission.ACCESS_COARSE_LOCATION");
    }
    
    @Nullable
    Location b() {
        if (this.f || this.e == null) {
            return null;
        }
        try {
            return this.g = b(this.g, this.f());
        }
        catch (Exception ex) {
            m.a(ex);
            return null;
        }
    }
    
    void c() {
        this.e();
    }
    
    void d() {
        this.a(false);
    }
    
    public void onLocationChanged(final Location location) {
        try {
            p.a(3, "LocationManager", this, "Received an updated location = " + location.toString());
            final float b = b(location);
            if (location.hasAccuracy() && location.getAccuracy() <= 100.0f && b < 600.0f) {
                this.g = b(this.g, location);
                p.a(3, "LocationManager", this, "fetchCompleted");
                this.a(true);
            }
        }
        catch (Exception ex) {
            m.a(ex);
        }
    }
    
    public void onProviderDisabled(final String s) {
    }
    
    public void onProviderEnabled(final String s) {
    }
    
    public void onStatusChanged(final String s, final int n, final Bundle bundle) {
    }
}
