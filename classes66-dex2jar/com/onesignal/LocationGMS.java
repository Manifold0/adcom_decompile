// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import java.util.List;
import java.util.Arrays;
import android.os.Build$VERSION;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import android.location.Location;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Context;

class LocationGMS
{
    private static final long BACKGROUND_UPDATE_TIME_MS = 570000L;
    private static final long FOREGROUND_UPDATE_TIME_MS = 270000L;
    private static final long TIME_BACKGROUND_SEC = 600L;
    private static final long TIME_FOREGROUND_SEC = 300L;
    private static Context classContext;
    private static Thread fallbackFailThread;
    private static boolean locationCoarse;
    private static LocationHandlerThread locationHandlerThread;
    private static ConcurrentHashMap<CALLBACK_TYPE, LocationHandler> locationHandlers;
    static LocationUpdateListener locationUpdateListener;
    private static GoogleApiClientCompatProxy mGoogleApiClient;
    private static Location mLastLocation;
    static String requestPermission;
    protected static final Object syncLock;
    
    static {
        syncLock = new Object() {};
        LocationGMS.locationHandlers = new ConcurrentHashMap<CALLBACK_TYPE, LocationHandler>();
    }
    
    private static void fireComplete(final LocationPoint locationPoint) {
        final HashMap<CALLBACK_TYPE, LocationHandler> hashMap = new HashMap<CALLBACK_TYPE, LocationHandler>();
        final Thread fallbackFailThread;
        synchronized (LocationGMS.class) {
            hashMap.putAll(LocationGMS.locationHandlers);
            LocationGMS.locationHandlers.clear();
            fallbackFailThread = LocationGMS.fallbackFailThread;
            // monitorexit(LocationGMS.class)
            final Iterator<CALLBACK_TYPE> iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                hashMap.get(iterator.next()).complete(locationPoint);
            }
        }
        if (fallbackFailThread != null && !Thread.currentThread().equals(fallbackFailThread)) {
            fallbackFailThread.interrupt();
        }
        Label_0123: {
            if (fallbackFailThread != LocationGMS.fallbackFailThread) {
                break Label_0123;
            }
            synchronized (LocationGMS.class) {
                if (fallbackFailThread == LocationGMS.fallbackFailThread) {
                    LocationGMS.fallbackFailThread = null;
                }
                // monitorexit(LocationGMS.class)
                setLastLocationTime(System.currentTimeMillis());
            }
        }
    }
    
    private static void fireCompleteForLocation(final Location location) {
        int n = 0;
        final LocationPoint locationPoint = new LocationPoint();
        locationPoint.accuracy = location.getAccuracy();
        locationPoint.bg = !OneSignal.isForeground();
        if (!LocationGMS.locationCoarse) {
            n = 1;
        }
        locationPoint.type = n;
        locationPoint.timeStamp = location.getTime();
        if (LocationGMS.locationCoarse) {
            locationPoint.lat = new BigDecimal(location.getLatitude()).setScale(7, RoundingMode.HALF_UP).doubleValue();
            locationPoint.log = new BigDecimal(location.getLongitude()).setScale(7, RoundingMode.HALF_UP).doubleValue();
        }
        else {
            locationPoint.lat = location.getLatitude();
            locationPoint.log = location.getLongitude();
        }
        fireComplete(locationPoint);
        scheduleUpdate(LocationGMS.classContext);
    }
    
    static void fireFailedComplete() {
        PermissionsActivity.answered = false;
        synchronized (LocationGMS.syncLock) {
            if (LocationGMS.mGoogleApiClient != null) {
                LocationGMS.mGoogleApiClient.disconnect();
            }
            LocationGMS.mGoogleApiClient = null;
            // monitorexit(LocationGMS.syncLock)
            fireComplete(null);
        }
    }
    
    private static int getApiFallbackWait() {
        return 30000;
    }
    
    private static long getLastLocationTime() {
        return OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_LOCATION_TIME", -600000L);
    }
    
    static void getLocation(final Context classContext, final boolean b, final LocationHandler locationHandler) {
        LocationGMS.classContext = classContext;
        LocationGMS.locationHandlers.put(locationHandler.getType(), locationHandler);
        if (!OneSignal.shareLocation) {
            fireFailedComplete();
            return;
        }
        int checkSelfPermission = -1;
        final int checkSelfPermission2 = AndroidSupportV4Compat.ContextCompat.checkSelfPermission(classContext, "android.permission.ACCESS_FINE_LOCATION");
        if (checkSelfPermission2 == -1) {
            checkSelfPermission = AndroidSupportV4Compat.ContextCompat.checkSelfPermission(classContext, "android.permission.ACCESS_COARSE_LOCATION");
            LocationGMS.locationCoarse = true;
        }
        if (Build$VERSION.SDK_INT < 23) {
            if (checkSelfPermission2 != 0 && checkSelfPermission != 0) {
                locationHandler.complete(null);
                return;
            }
            startGetLocation();
        }
        else {
            if (checkSelfPermission2 == 0) {
                startGetLocation();
                return;
            }
            while (true) {
                while (true) {
                    List<String> list;
                    try {
                        list = Arrays.asList(classContext.getPackageManager().getPackageInfo(classContext.getPackageName(), 4096).requestedPermissions);
                        if (list.contains("android.permission.ACCESS_FINE_LOCATION")) {
                            LocationGMS.requestPermission = "android.permission.ACCESS_FINE_LOCATION";
                            if (LocationGMS.requestPermission != null && b) {
                                PermissionsActivity.startPrompt();
                                return;
                            }
                            break;
                        }
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                        return;
                    }
                    if (list.contains("android.permission.ACCESS_COARSE_LOCATION") && checkSelfPermission != 0) {
                        LocationGMS.requestPermission = "android.permission.ACCESS_COARSE_LOCATION";
                        continue;
                    }
                    continue;
                }
            }
            if (checkSelfPermission == 0) {
                startGetLocation();
                return;
            }
            fireFailedComplete();
        }
    }
    
    private static boolean hasLocationPermission(final Context context) {
        return AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || AndroidSupportV4Compat.ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }
    
    static void onFocusChange() {
        synchronized (LocationGMS.syncLock) {
            if (LocationGMS.mGoogleApiClient == null || !LocationGMS.mGoogleApiClient.realInstance().isConnected()) {
                return;
            }
            final GoogleApiClient realInstance = LocationGMS.mGoogleApiClient.realInstance();
            if (LocationGMS.locationUpdateListener != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(realInstance, (LocationListener)LocationGMS.locationUpdateListener);
            }
            LocationGMS.locationUpdateListener = new LocationUpdateListener(realInstance);
        }
    }
    
    static boolean scheduleUpdate(final Context context) {
        if (!hasLocationPermission(context) || !OneSignal.shareLocation) {
            return false;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long lastLocationTime = getLastLocationTime();
        long n;
        if (OneSignal.isForeground()) {
            n = 300L;
        }
        else {
            n = 600L;
        }
        OneSignalSyncServiceUtils.scheduleLocationUpdateTask(context, 1000L * n - (currentTimeMillis - lastLocationTime));
        return true;
    }
    
    private static void setLastLocationTime(final long n) {
        OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_LOCATION_TIME", n);
    }
    
    private static void startFallBackThread() {
        (LocationGMS.fallbackFailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(getApiFallbackWait());
                    OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Location permission exists but GoogleApiClient timed out. Maybe related to mismatch google-play aar versions.");
                    LocationGMS.fireFailedComplete();
                    LocationGMS.scheduleUpdate(LocationGMS.classContext);
                }
                catch (InterruptedException ex) {}
            }
        }, "OS_GMS_LOCATION_FALLBACK")).start();
    }
    
    static void startGetLocation() {
        if (LocationGMS.fallbackFailThread != null) {
            return;
        }
        while (true) {
            try {
                synchronized (LocationGMS.syncLock) {
                    startFallBackThread();
                    if (LocationGMS.locationHandlerThread == null) {
                        LocationGMS.locationHandlerThread = new LocationHandlerThread();
                    }
                    if (LocationGMS.mGoogleApiClient == null || LocationGMS.mLastLocation == null) {
                        final GoogleApiClientListener googleApiClientListener = new GoogleApiClientListener();
                        (LocationGMS.mGoogleApiClient = new GoogleApiClientCompatProxy(new GoogleApiClient$Builder(LocationGMS.classContext).addApi(LocationServices.API).addConnectionCallbacks((GoogleApiClient$ConnectionCallbacks)googleApiClientListener).addOnConnectionFailedListener((GoogleApiClient$OnConnectionFailedListener)googleApiClientListener).setHandler(LocationGMS.locationHandlerThread.mHandler).build())).connect();
                        return;
                    }
                }
            }
            catch (Throwable t) {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Location permission exists but there was an error initializing: ", t);
                fireFailedComplete();
                return;
            }
            if (LocationGMS.mLastLocation != null) {
                fireCompleteForLocation(LocationGMS.mLastLocation);
            }
        }
    }
    
    enum CALLBACK_TYPE
    {
        PROMPT_LOCATION, 
        STARTUP, 
        SYNC_SERVICE;
    }
    
    static class FusedLocationApiWrapper
    {
        static Location getLastLocation(final GoogleApiClient googleApiClient) {
            synchronized (LocationGMS.syncLock) {
                if (googleApiClient.isConnected()) {
                    return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                }
                return null;
            }
        }
        
        static void requestLocationUpdates(final GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener) {
            try {
                synchronized (LocationGMS.syncLock) {
                    if (googleApiClient.isConnected()) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
                    }
                }
            }
            catch (Throwable t) {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "FusedLocationApi.requestLocationUpdates failed!", t);
            }
        }
    }
    
    private static class GoogleApiClientListener implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
    {
        public void onConnected(final Bundle bundle) {
            synchronized (LocationGMS.syncLock) {
                PermissionsActivity.answered = false;
                if (LocationGMS.mLastLocation == null) {
                    LocationGMS.mLastLocation = FusedLocationApiWrapper.getLastLocation(LocationGMS.mGoogleApiClient.realInstance());
                    if (LocationGMS.mLastLocation != null) {
                        fireCompleteForLocation(LocationGMS.mLastLocation);
                    }
                }
                LocationGMS.locationUpdateListener = new LocationUpdateListener(LocationGMS.mGoogleApiClient.realInstance());
            }
        }
        
        public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
            LocationGMS.fireFailedComplete();
        }
        
        public void onConnectionSuspended(final int n) {
            LocationGMS.fireFailedComplete();
        }
    }
    
    interface LocationHandler
    {
        void complete(final LocationPoint p0);
        
        CALLBACK_TYPE getType();
    }
    
    private static class LocationHandlerThread extends HandlerThread
    {
        Handler mHandler;
        
        LocationHandlerThread() {
            super("OSH_LocationHandlerThread");
            this.start();
            this.mHandler = new Handler(this.getLooper());
        }
    }
    
    static class LocationPoint
    {
        Float accuracy;
        Boolean bg;
        Double lat;
        Double log;
        Long timeStamp;
        Integer type;
    }
    
    static class LocationUpdateListener implements LocationListener
    {
        private GoogleApiClient mGoogleApiClient;
        
        LocationUpdateListener(final GoogleApiClient mGoogleApiClient) {
            this.mGoogleApiClient = mGoogleApiClient;
            long n = 570000L;
            if (OneSignal.isForeground()) {
                n = 270000L;
            }
            FusedLocationApiWrapper.requestLocationUpdates(this.mGoogleApiClient, LocationRequest.create().setFastestInterval(n).setInterval(n).setMaxWaitTime((long)(n * 1.5)).setPriority(102), (LocationListener)this);
        }
        
        public void onLocationChanged(final Location location) {
            LocationGMS.mLastLocation = location;
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Location Change Detected");
        }
    }
}
