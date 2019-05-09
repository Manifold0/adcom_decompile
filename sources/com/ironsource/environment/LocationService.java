package com.ironsource.environment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.facebook.places.model.PlaceFields;
import java.util.List;

public class LocationService {
    private static String TAG = LocationService.class.getSimpleName();

    public interface ISLocationListener {
        void onLocationChanged(Location location);
    }

    public static Location getLastLocation(Context context) {
        Location bestLocation = null;
        if (ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            try {
                List<String> providers;
                LocationManager locationManager = (LocationManager) context.getSystemService(PlaceFields.LOCATION);
                if (locationManager != null) {
                    providers = locationManager.getAllProviders();
                } else {
                    providers = null;
                }
                if (providers != null) {
                    for (String provider : providers) {
                        try {
                            Location location = locationManager.getLastKnownLocation(provider);
                            if (location != null && location.getTime() > Long.MIN_VALUE) {
                                bestLocation = location;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (Exception e2) {
            }
            return bestLocation;
        }
        Log.d(TAG, "Location Permission Not Granted (ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION)");
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public static void getPreciseLocation(Context context, final ISLocationListener isLocationListener) {
        if (!ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION")) {
            Log.d(TAG, "Location Permission Not Granted (ACCESS_FINE_LOCATION)");
            if (isLocationListener != null) {
                isLocationListener.onLocationChanged(null);
                return;
            }
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(PlaceFields.LOCATION);
            if (isLocationListener == null || locationManager.isProviderEnabled("gps")) {
                locationManager.requestSingleUpdate(new Criteria(), new LocationListener() {
                    public void onLocationChanged(Location location) {
                        Log.d("LocationService", "onLocationChanged " + location.getProvider());
                        if (isLocationListener != null) {
                            isLocationListener.onLocationChanged(location);
                        }
                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        Log.d("LocationService", "onStatusChanged " + provider);
                    }

                    public void onProviderEnabled(String provider) {
                        Log.d("LocationService", "onProviderEnabled " + provider);
                    }

                    public void onProviderDisabled(String provider) {
                        Log.d("LocationService", "onProviderDisabled " + provider);
                    }
                }, Looper.myLooper());
                return;
            }
            Log.d(TAG, "GPS Provider is turned off");
            isLocationListener.onLocationChanged(null);
        } catch (Exception e) {
            if (isLocationListener != null) {
                isLocationListener.onLocationChanged(null);
            }
        }
    }

    public static boolean locationServicesEnabled(Context context) {
        try {
            if (ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                LocationManager locationManager = (LocationManager) context.getSystemService(PlaceFields.LOCATION);
                boolean gps_enabled = false;
                boolean network_enabled = false;
                try {
                    gps_enabled = locationManager.isProviderEnabled("gps");
                } catch (Exception e) {
                }
                try {
                    network_enabled = locationManager.isProviderEnabled("network");
                } catch (Exception e2) {
                }
                if (gps_enabled || network_enabled) {
                    return true;
                }
                return false;
            }
            Log.d(TAG, "Location Permission Not Granted (ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION)");
            return false;
        } catch (Exception e3) {
            return false;
        }
    }
}
