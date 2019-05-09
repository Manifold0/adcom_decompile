package com.facebook.places.internal;

import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.places.internal.ScannerException.Type;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class LocationPackageManager {
    private static final String TAG = "LocationPackageManager";

    public interface Listener {
        void onLocationPackage(LocationPackage locationPackage);
    }

    public static void requestLocationPackage(final LocationPackageRequestParams requestParams, final Listener listener) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r11 = this;
                r3 = new com.facebook.places.internal.LocationPackage;
                r3.<init>();
                r4 = 0;
                r7 = 0;
                r0 = 0;
                r9 = r2;	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9 = r9.isLocationScanEnabled();	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                if (r9 == 0) goto L_0x002a;
            L_0x0010:
                r9 = com.facebook.FacebookSdk.getApplicationContext();	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r10 = r2;	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r5 = com.facebook.places.internal.ScannerFactory.newLocationScanner(r9, r10);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r5.initAndCheckEligibility();	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9 = r2;	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r4 = com.facebook.places.internal.LocationPackageManager.newLocationScanFuture(r5, r9);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9 = com.facebook.FacebookSdk.getExecutor();	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9.execute(r4);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
            L_0x002a:
                r9 = r2;	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9 = r9.isWifiScanEnabled();	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                if (r9 == 0) goto L_0x003f;
            L_0x0032:
                r9 = r2;	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r7 = com.facebook.places.internal.LocationPackageManager.newWifiScanFuture(r9);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9 = com.facebook.FacebookSdk.getExecutor();	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9.execute(r7);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
            L_0x003f:
                r9 = r2;	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9 = r9.isBluetoothScanEnabled();	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                if (r9 == 0) goto L_0x0054;
            L_0x0047:
                r9 = r2;	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r0 = com.facebook.places.internal.LocationPackageManager.newBluetoothScanFuture(r9);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9 = com.facebook.FacebookSdk.getExecutor();	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                r9.execute(r0);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
            L_0x0054:
                if (r0 == 0) goto L_0x0064;
            L_0x0056:
                r1 = r0.get();	 Catch:{ Exception -> 0x008e, ScannerException -> 0x0095 }
                r1 = (com.facebook.places.internal.LocationPackage) r1;	 Catch:{ Exception -> 0x008e, ScannerException -> 0x0095 }
                r9 = r1.ambientBluetoothLe;	 Catch:{ Exception -> 0x008e, ScannerException -> 0x0095 }
                r3.ambientBluetoothLe = r9;	 Catch:{ Exception -> 0x008e, ScannerException -> 0x0095 }
                r9 = r1.isBluetoothScanningEnabled;	 Catch:{ Exception -> 0x008e, ScannerException -> 0x0095 }
                r3.isBluetoothScanningEnabled = r9;	 Catch:{ Exception -> 0x008e, ScannerException -> 0x0095 }
            L_0x0064:
                if (r7 == 0) goto L_0x0078;
            L_0x0066:
                r8 = r7.get();	 Catch:{ Exception -> 0x00a0, ScannerException -> 0x0095 }
                r8 = (com.facebook.places.internal.LocationPackage) r8;	 Catch:{ Exception -> 0x00a0, ScannerException -> 0x0095 }
                r9 = r8.isWifiScanningEnabled;	 Catch:{ Exception -> 0x00a0, ScannerException -> 0x0095 }
                r3.isWifiScanningEnabled = r9;	 Catch:{ Exception -> 0x00a0, ScannerException -> 0x0095 }
                r9 = r8.connectedWifi;	 Catch:{ Exception -> 0x00a0, ScannerException -> 0x0095 }
                r3.connectedWifi = r9;	 Catch:{ Exception -> 0x00a0, ScannerException -> 0x0095 }
                r9 = r8.ambientWifi;	 Catch:{ Exception -> 0x00a0, ScannerException -> 0x0095 }
                r3.ambientWifi = r9;	 Catch:{ Exception -> 0x00a0, ScannerException -> 0x0095 }
            L_0x0078:
                if (r4 == 0) goto L_0x0088;
            L_0x007a:
                r6 = r4.get();	 Catch:{ Exception -> 0x00ae, ScannerException -> 0x0095 }
                r6 = (com.facebook.places.internal.LocationPackage) r6;	 Catch:{ Exception -> 0x00ae, ScannerException -> 0x0095 }
                r9 = r6.locationError;	 Catch:{ Exception -> 0x00ae, ScannerException -> 0x0095 }
                r3.locationError = r9;	 Catch:{ Exception -> 0x00ae, ScannerException -> 0x0095 }
                r9 = r6.location;	 Catch:{ Exception -> 0x00ae, ScannerException -> 0x0095 }
                r3.location = r9;	 Catch:{ Exception -> 0x00ae, ScannerException -> 0x0095 }
            L_0x0088:
                r9 = r3;
                r9.onLocationPackage(r3);
                return;
            L_0x008e:
                r2 = move-exception;
                r9 = "Exception scanning for bluetooth beacons";
                com.facebook.places.internal.LocationPackageManager.logException(r9, r2);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                goto L_0x0064;
            L_0x0095:
                r2 = move-exception;
                r9 = "Exception scanning for locations";
                com.facebook.places.internal.LocationPackageManager.logException(r9, r2);
                r9 = r2.type;
                r3.locationError = r9;
                goto L_0x0088;
            L_0x00a0:
                r2 = move-exception;
                r9 = "Exception scanning for wifi access points";
                com.facebook.places.internal.LocationPackageManager.logException(r9, r2);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                goto L_0x0078;
            L_0x00a7:
                r2 = move-exception;
                r9 = "Exception requesting a location package";
                com.facebook.places.internal.LocationPackageManager.logException(r9, r2);
                goto L_0x0088;
            L_0x00ae:
                r2 = move-exception;
                r9 = "Exception getting location";
                com.facebook.places.internal.LocationPackageManager.logException(r9, r2);	 Catch:{ ScannerException -> 0x0095, Exception -> 0x00a7 }
                goto L_0x0088;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.places.internal.LocationPackageManager.1.run():void");
            }
        });
    }

    private static FutureTask<LocationPackage> newLocationScanFuture(final LocationScanner locationScanner, LocationPackageRequestParams requestParams) {
        return new FutureTask(new Callable<LocationPackage>() {
            public LocationPackage call() throws Exception {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    locationPackage.location = locationScanner.getLocation();
                } catch (ScannerException e) {
                    locationPackage.locationError = e.type;
                    LocationPackageManager.logException("Exception while getting location", e);
                } catch (Exception e2) {
                    locationPackage.locationError = Type.UNKNOWN_ERROR;
                }
                return locationPackage;
            }
        });
    }

    private static FutureTask<LocationPackage> newBluetoothScanFuture(final LocationPackageRequestParams requestParams) {
        return new FutureTask(new Callable<LocationPackage>() {
            public LocationPackage call() throws Exception {
                LocationPackage locationPackage = new LocationPackage();
                BleScanner bleScanner;
                try {
                    bleScanner = ScannerFactory.newBleScanner(FacebookSdk.getApplicationContext(), requestParams);
                    bleScanner.initAndCheckEligibility();
                    bleScanner.startScanning();
                    try {
                        Thread.sleep(requestParams.getBluetoothScanDurationMs());
                    } catch (Exception e) {
                    }
                    bleScanner.stopScanning();
                    if (bleScanner.getErrorCode() == 0) {
                        locationPackage.ambientBluetoothLe = bleScanner.getScanResults();
                        locationPackage.isBluetoothScanningEnabled = true;
                    } else {
                        if (FacebookSdk.isDebugEnabled()) {
                            Log.d(LocationPackageManager.TAG, String.format("Bluetooth LE scan failed with error: %d", new Object[]{Integer.valueOf(errorCode)}));
                        }
                        locationPackage.isBluetoothScanningEnabled = false;
                    }
                } catch (Exception e2) {
                    LocationPackageManager.logException("Exception scanning for bluetooth beacons", e2);
                    locationPackage.isBluetoothScanningEnabled = false;
                } catch (Throwable th) {
                    bleScanner.stopScanning();
                }
                return locationPackage;
            }
        });
    }

    private static FutureTask<LocationPackage> newWifiScanFuture(final LocationPackageRequestParams requestParams) {
        return new FutureTask(new Callable<LocationPackage>() {
            public LocationPackage call() throws Exception {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    WifiScanner wifiScanner = ScannerFactory.newWifiScanner(FacebookSdk.getApplicationContext(), requestParams);
                    wifiScanner.initAndCheckEligibility();
                    locationPackage.connectedWifi = wifiScanner.getConnectedWifi();
                    locationPackage.isWifiScanningEnabled = wifiScanner.isWifiScanningEnabled();
                    if (locationPackage.isWifiScanningEnabled) {
                        locationPackage.ambientWifi = wifiScanner.getWifiScans();
                    }
                } catch (Exception e) {
                    LocationPackageManager.logException("Exception scanning for wifi access points", e);
                    locationPackage.isWifiScanningEnabled = false;
                }
                return locationPackage;
            }
        });
    }

    private static void logException(String message, Throwable throwable) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e(TAG, message, throwable);
        }
    }
}
