// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

import android.os.Bundle;
import java.util.ArrayList;
import com.facebook.internal.Validate;
import java.util.Iterator;
import android.os.HandlerThread;
import android.location.LocationManager;
import android.location.Location;
import java.util.List;
import android.content.Context;
import android.location.LocationListener;

public class LocationScannerImpl implements LocationScanner, LocationListener
{
    private static final float MIN_DISTANCE_BETWEEN_UPDATES = 0.0f;
    private static final long MIN_TIME_BETWEEN_UPDATES = 100L;
    private Context context;
    private List<String> enabledProviders;
    private Location freshLocation;
    private LocationManager locationManager;
    private LocationPackageRequestParams params;
    private final Object scanLock;
    
    public LocationScannerImpl(final Context context, final LocationPackageRequestParams params) {
        this.scanLock = new Object();
        this.context = context;
        this.params = params;
        this.locationManager = (LocationManager)context.getSystemService("location");
    }
    
    private Location getFreshLocation() throws ScannerException {
        this.freshLocation = null;
        final HandlerThread handlerThread = new HandlerThread("LocationScanner");
        try {
            handlerThread.start();
            final Iterator<String> iterator = this.enabledProviders.iterator();
            while (iterator.hasNext()) {
                this.locationManager.requestLocationUpdates((String)iterator.next(), 100L, 0.0f, (LocationListener)this, handlerThread.getLooper());
            }
        }
        finally {
            this.locationManager.removeUpdates((LocationListener)this);
            handlerThread.quit();
        }
        while (true) {
            try {
                synchronized (this.scanLock) {
                    this.scanLock.wait(this.params.getLocationRequestTimeoutMs());
                    // monitorexit(this.scanLock)
                    this.locationManager.removeUpdates((LocationListener)this);
                    handlerThread.quit();
                    if (this.freshLocation == null) {
                        throw new ScannerException(ScannerException.Type.TIMEOUT);
                    }
                    return this.freshLocation;
                }
            }
            catch (Exception o) {
                continue;
            }
            break;
        }
        return this.freshLocation;
    }
    
    private Location getLastLocation(final String s) {
        final Location lastKnownLocation = this.locationManager.getLastKnownLocation(s);
        if (lastKnownLocation != null && System.currentTimeMillis() - lastKnownLocation.getTime() < this.params.getLastLocationMaxAgeMs()) {
            return lastKnownLocation;
        }
        return null;
    }
    
    @Override
    public Location getLocation() throws ScannerException {
        final Iterator<String> iterator = this.enabledProviders.iterator();
        while (iterator.hasNext()) {
            final Location lastLocation = this.getLastLocation(iterator.next());
            if (lastLocation != null) {
                return lastLocation;
            }
        }
        return this.getFreshLocation();
    }
    
    @Override
    public void initAndCheckEligibility() throws ScannerException {
        if (!Validate.hasLocationPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        }
        this.enabledProviders = new ArrayList<String>(this.params.getLocationProviders().length);
        final String[] locationProviders = this.params.getLocationProviders();
        for (int length = locationProviders.length, i = 0; i < length; ++i) {
            final String s = locationProviders[i];
            if (this.locationManager.isProviderEnabled(s)) {
                this.enabledProviders.add(s);
            }
        }
        if (this.enabledProviders.isEmpty()) {
            throw new ScannerException(ScannerException.Type.DISABLED);
        }
    }
    
    public void onLocationChanged(final Location freshLocation) {
        if (this.freshLocation == null && freshLocation.getAccuracy() < this.params.getLocationMaxAccuracyMeters()) {
            synchronized (this.scanLock) {
                this.freshLocation = freshLocation;
                this.scanLock.notify();
            }
        }
    }
    
    public void onProviderDisabled(final String s) {
    }
    
    public void onProviderEnabled(final String s) {
    }
    
    public void onStatusChanged(final String s, final int n, final Bundle bundle) {
    }
}
