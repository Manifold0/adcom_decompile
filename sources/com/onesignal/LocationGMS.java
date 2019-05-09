package com.onesignal;

import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.onesignal.OneSignal.LOG_LEVEL;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class LocationGMS {
    private static final long BACKGROUND_UPDATE_TIME_MS = 570000;
    private static final long FOREGROUND_UPDATE_TIME_MS = 270000;
    private static final long TIME_BACKGROUND_SEC = 600;
    private static final long TIME_FOREGROUND_SEC = 300;
    private static Context classContext;
    private static Thread fallbackFailThread;
    private static boolean locationCoarse;
    private static LocationHandlerThread locationHandlerThread;
    private static ConcurrentHashMap<CALLBACK_TYPE, LocationHandler> locationHandlers = new ConcurrentHashMap();
    static LocationUpdateListener locationUpdateListener;
    private static GoogleApiClientCompatProxy mGoogleApiClient;
    private static Location mLastLocation;
    static String requestPermission;
    protected static final Object syncLock = new C13181();

    /* renamed from: com.onesignal.LocationGMS$1 */
    static class C13181 {
        C13181() {
        }
    }

    /* renamed from: com.onesignal.LocationGMS$2 */
    static class C13192 implements Runnable {
        C13192() {
        }

        public void run() {
            try {
                Thread.sleep((long) LocationGMS.getApiFallbackWait());
                OneSignal.Log(LOG_LEVEL.WARN, "Location permission exists but GoogleApiClient timed out. Maybe related to mismatch google-play aar versions.");
                LocationGMS.fireFailedComplete();
                LocationGMS.scheduleUpdate(LocationGMS.classContext);
            } catch (InterruptedException e) {
            }
        }
    }

    enum CALLBACK_TYPE {
        STARTUP,
        PROMPT_LOCATION,
        SYNC_SERVICE
    }

    static class FusedLocationApiWrapper {
        FusedLocationApiWrapper() {
        }

        static void requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
            try {
                synchronized (LocationGMS.syncLock) {
                    if (googleApiClient.isConnected()) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
                    }
                }
            } catch (Throwable t) {
                OneSignal.Log(LOG_LEVEL.WARN, "FusedLocationApi.requestLocationUpdates failed!", t);
            }
        }

        static Location getLastLocation(GoogleApiClient googleApiClient) {
            synchronized (LocationGMS.syncLock) {
                if (googleApiClient.isConnected()) {
                    Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    return lastLocation;
                }
                return null;
            }
        }
    }

    private static class GoogleApiClientListener implements ConnectionCallbacks, OnConnectionFailedListener {
        private GoogleApiClientListener() {
        }

        public void onConnected(Bundle bundle) {
            synchronized (LocationGMS.syncLock) {
                PermissionsActivity.answered = false;
                if (LocationGMS.mLastLocation == null) {
                    LocationGMS.mLastLocation = FusedLocationApiWrapper.getLastLocation(LocationGMS.mGoogleApiClient.realInstance());
                    if (LocationGMS.mLastLocation != null) {
                        LocationGMS.fireCompleteForLocation(LocationGMS.mLastLocation);
                    }
                }
                LocationGMS.locationUpdateListener = new LocationUpdateListener(LocationGMS.mGoogleApiClient.realInstance());
            }
        }

        public void onConnectionSuspended(int i) {
            LocationGMS.fireFailedComplete();
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            LocationGMS.fireFailedComplete();
        }
    }

    interface LocationHandler {
        void complete(LocationPoint locationPoint);

        CALLBACK_TYPE getType();
    }

    private static class LocationHandlerThread extends HandlerThread {
        Handler mHandler = new Handler(getLooper());

        LocationHandlerThread() {
            super("OSH_LocationHandlerThread");
            start();
        }
    }

    static class LocationPoint {
        Float accuracy;
        Boolean bg;
        Double lat;
        Double log;
        Long timeStamp;
        Integer type;

        LocationPoint() {
        }
    }

    static class LocationUpdateListener implements LocationListener {
        private GoogleApiClient mGoogleApiClient;

        LocationUpdateListener(GoogleApiClient googleApiClient) {
            this.mGoogleApiClient = googleApiClient;
            long updateInterval = LocationGMS.BACKGROUND_UPDATE_TIME_MS;
            if (OneSignal.isForeground()) {
                updateInterval = LocationGMS.FOREGROUND_UPDATE_TIME_MS;
            }
            FusedLocationApiWrapper.requestLocationUpdates(this.mGoogleApiClient, LocationRequest.create().setFastestInterval(updateInterval).setInterval(updateInterval).setMaxWaitTime((long) (((double) updateInterval) * 1.5d)).setPriority(102), this);
        }

        public void onLocationChanged(Location location) {
            LocationGMS.mLastLocation = location;
            OneSignal.Log(LOG_LEVEL.INFO, "Location Change Detected");
        }
    }

    LocationGMS() {
    }

    static boolean scheduleUpdate(Context context) {
        if (!hasLocationPermission(context) || !OneSignal.shareLocation) {
            return false;
        }
        OneSignalSyncServiceUtils.scheduleLocationUpdateTask(context, (1000 * (OneSignal.isForeground() ? TIME_FOREGROUND_SEC : TIME_BACKGROUND_SEC)) - (System.currentTimeMillis() - getLastLocationTime()));
        return true;
    }

    private static void setLastLocationTime(long time) {
        OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_LOCATION_TIME", time);
    }

    private static long getLastLocationTime() {
        return OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_LOCATION_TIME", -600000);
    }

    private static boolean hasLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    static void getLocation(Context context, boolean promptLocation, LocationHandler handler) {
        classContext = context;
        locationHandlers.put(handler.getType(), handler);
        if (OneSignal.shareLocation) {
            int locationCoarsePermission = -1;
            int locationFinePermission = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
            if (locationFinePermission == -1) {
                locationCoarsePermission = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
                locationCoarse = true;
            }
            if (VERSION.SDK_INT < 23) {
                if (locationFinePermission == 0 || locationCoarsePermission == 0) {
                    startGetLocation();
                    return;
                } else {
                    handler.complete(null);
                    return;
                }
            } else if (locationFinePermission != 0) {
                try {
                    List<String> permissionList = Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
                    if (permissionList.contains("android.permission.ACCESS_FINE_LOCATION")) {
                        requestPermission = "android.permission.ACCESS_FINE_LOCATION";
                    } else if (permissionList.contains("android.permission.ACCESS_COARSE_LOCATION") && locationCoarsePermission != 0) {
                        requestPermission = "android.permission.ACCESS_COARSE_LOCATION";
                    }
                    if (requestPermission != null && promptLocation) {
                        PermissionsActivity.startPrompt();
                        return;
                    } else if (locationCoarsePermission == 0) {
                        startGetLocation();
                        return;
                    } else {
                        fireFailedComplete();
                        return;
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                    return;
                }
            } else {
                startGetLocation();
                return;
            }
        }
        fireFailedComplete();
    }

    static void startGetLocation() {
        if (fallbackFailThread == null) {
            try {
                synchronized (syncLock) {
                    startFallBackThread();
                    if (locationHandlerThread == null) {
                        locationHandlerThread = new LocationHandlerThread();
                    }
                    if (mGoogleApiClient == null || mLastLocation == null) {
                        GoogleApiClientListener googleApiClientListener = new GoogleApiClientListener();
                        mGoogleApiClient = new GoogleApiClientCompatProxy(new Builder(classContext).addApi(LocationServices.API).addConnectionCallbacks(googleApiClientListener).addOnConnectionFailedListener(googleApiClientListener).setHandler(locationHandlerThread.mHandler).build());
                        mGoogleApiClient.connect();
                    } else if (mLastLocation != null) {
                        fireCompleteForLocation(mLastLocation);
                    }
                }
            } catch (Throwable t) {
                OneSignal.Log(LOG_LEVEL.WARN, "Location permission exists but there was an error initializing: ", t);
                fireFailedComplete();
            }
        }
    }

    private static int getApiFallbackWait() {
        return 30000;
    }

    private static void startFallBackThread() {
        fallbackFailThread = new Thread(new C13192(), "OS_GMS_LOCATION_FALLBACK");
        fallbackFailThread.start();
    }

    static void fireFailedComplete() {
        PermissionsActivity.answered = false;
        synchronized (syncLock) {
            if (mGoogleApiClient != null) {
                mGoogleApiClient.disconnect();
            }
            mGoogleApiClient = null;
        }
        fireComplete(null);
    }

    private static void fireComplete(LocationPoint point) {
        HashMap<CALLBACK_TYPE, LocationHandler> _locationHandlers = new HashMap();
        synchronized (LocationGMS.class) {
            _locationHandlers.putAll(locationHandlers);
            locationHandlers.clear();
            Thread _fallbackFailThread = fallbackFailThread;
        }
        for (CALLBACK_TYPE type : _locationHandlers.keySet()) {
            ((LocationHandler) _locationHandlers.get(type)).complete(point);
        }
        if (!(_fallbackFailThread == null || Thread.currentThread().equals(_fallbackFailThread))) {
            _fallbackFailThread.interrupt();
        }
        if (_fallbackFailThread == fallbackFailThread) {
            synchronized (LocationGMS.class) {
                if (_fallbackFailThread == fallbackFailThread) {
                    fallbackFailThread = null;
                }
            }
        }
        setLastLocationTime(System.currentTimeMillis());
    }

    private static void fireCompleteForLocation(Location location) {
        int i = 0;
        LocationPoint point = new LocationPoint();
        point.accuracy = Float.valueOf(location.getAccuracy());
        point.bg = Boolean.valueOf(!OneSignal.isForeground());
        if (!locationCoarse) {
            i = 1;
        }
        point.type = Integer.valueOf(i);
        point.timeStamp = Long.valueOf(location.getTime());
        if (locationCoarse) {
            point.lat = Double.valueOf(new BigDecimal(location.getLatitude()).setScale(7, RoundingMode.HALF_UP).doubleValue());
            point.log = Double.valueOf(new BigDecimal(location.getLongitude()).setScale(7, RoundingMode.HALF_UP).doubleValue());
        } else {
            point.lat = Double.valueOf(location.getLatitude());
            point.log = Double.valueOf(location.getLongitude());
        }
        fireComplete(point);
        scheduleUpdate(classContext);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void onFocusChange() {
        /*
        r2 = syncLock;
        monitor-enter(r2);
        r1 = mGoogleApiClient;	 Catch:{ all -> 0x002f }
        if (r1 == 0) goto L_0x0013;
    L_0x0007:
        r1 = mGoogleApiClient;	 Catch:{ all -> 0x002f }
        r1 = r1.realInstance();	 Catch:{ all -> 0x002f }
        r1 = r1.isConnected();	 Catch:{ all -> 0x002f }
        if (r1 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r2);	 Catch:{ all -> 0x002f }
    L_0x0014:
        return;
    L_0x0015:
        r1 = mGoogleApiClient;	 Catch:{ all -> 0x002f }
        r0 = r1.realInstance();	 Catch:{ all -> 0x002f }
        r1 = locationUpdateListener;	 Catch:{ all -> 0x002f }
        if (r1 == 0) goto L_0x0026;
    L_0x001f:
        r1 = com.google.android.gms.location.LocationServices.FusedLocationApi;	 Catch:{ all -> 0x002f }
        r3 = locationUpdateListener;	 Catch:{ all -> 0x002f }
        r1.removeLocationUpdates(r0, r3);	 Catch:{ all -> 0x002f }
    L_0x0026:
        r1 = new com.onesignal.LocationGMS$LocationUpdateListener;	 Catch:{ all -> 0x002f }
        r1.<init>(r0);	 Catch:{ all -> 0x002f }
        locationUpdateListener = r1;	 Catch:{ all -> 0x002f }
        monitor-exit(r2);	 Catch:{ all -> 0x002f }
        goto L_0x0014;
    L_0x002f:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x002f }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.LocationGMS.onFocusChange():void");
    }
}
