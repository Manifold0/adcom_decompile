package com.facebook.places.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WifiScannerImpl implements WifiScanner {
    private static final String SSID_NOMAP = "_nomap";
    private static final String SSID_OPTOUT = "_optout";
    private ScanResultBroadcastReceiver broadcastReceiver;
    private Context context;
    private final LocationPackageRequestParams params;
    private final Object scanLock = new Object();
    private WifiManager wifiManager;

    /* renamed from: com.facebook.places.internal.WifiScannerImpl$1 */
    static class C01821 implements Comparator<ScanResult> {
        C01821() {
        }

        public int compare(ScanResult lhs, ScanResult rhs) {
            return rhs.level - lhs.level;
        }
    }

    private class ScanResultBroadcastReceiver extends BroadcastReceiver {
        private ScanResultBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                synchronized (WifiScannerImpl.this.scanLock) {
                    WifiScannerImpl.this.scanLock.notify();
                }
                WifiScannerImpl.this.unregisterBroadcastReceiver();
            }
        }
    }

    WifiScannerImpl(Context context, LocationPackageRequestParams params) {
        this.context = context;
        this.params = params;
    }

    public void initAndCheckEligibility() throws ScannerException {
        if (!this.context.getPackageManager().hasSystemFeature("android.hardware.wifi")) {
            throw new ScannerException(Type.NOT_SUPPORTED);
        } else if (Validate.hasWiFiPermission(this.context)) {
            if (this.wifiManager == null) {
                this.wifiManager = (WifiManager) this.context.getSystemService("wifi");
            }
            if (!isWifiScanningAlwaysOn() && !this.wifiManager.isWifiEnabled()) {
                throw new ScannerException(Type.DISABLED);
            }
        } else {
            throw new ScannerException(Type.PERMISSION_DENIED);
        }
    }

    public WifiScanResult getConnectedWifi() throws ScannerException {
        try {
            WifiInfo wifiInfo = this.wifiManager.getConnectionInfo();
            if (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getBSSID()) || wifiInfo.getSupplicantState() != SupplicantState.COMPLETED || isWifiSsidBlacklisted(wifiInfo.getSSID())) {
                return null;
            }
            WifiScanResult wifiScanResult = new WifiScanResult();
            wifiScanResult.bssid = wifiInfo.getBSSID();
            wifiScanResult.ssid = wifiInfo.getSSID();
            wifiScanResult.rssi = wifiInfo.getRssi();
            wifiScanResult.timestampMs = SystemClock.elapsedRealtime();
            if (VERSION.SDK_INT < 21) {
                return wifiScanResult;
            }
            wifiScanResult.frequency = wifiInfo.getFrequency();
            return wifiScanResult;
        } catch (Exception e) {
            throw new ScannerException(Type.UNKNOWN_ERROR, e);
        }
    }

    public boolean isWifiScanningEnabled() {
        try {
            initAndCheckEligibility();
            if (Validate.hasLocationPermission(this.context)) {
                return true;
            }
        } catch (ScannerException e) {
        }
        return false;
    }

    private boolean isWifiScanningAlwaysOn() {
        if (VERSION.SDK_INT >= 18) {
            return this.wifiManager.isScanAlwaysAvailable();
        }
        return false;
    }

    private List<WifiScanResult> getCachedScanResults() throws ScannerException {
        try {
            List<ScanResult> scanResults = filterWifiScanResultsByMaxAge(this.wifiManager.getScanResults(), this.params.getWifiScanMaxAgeMs());
            filterResults(scanResults, this.params.getWifiMaxScanResults());
            List<WifiScanResult> wifiScanResults = new ArrayList(scanResults.size());
            for (ScanResult scanResult : scanResults) {
                if (!isWifiSsidBlacklisted(scanResult.SSID)) {
                    WifiScanResult wifiScanResult = new WifiScanResult();
                    wifiScanResult.bssid = scanResult.BSSID;
                    wifiScanResult.ssid = scanResult.SSID;
                    wifiScanResult.rssi = scanResult.level;
                    wifiScanResult.frequency = scanResult.frequency;
                    if (VERSION.SDK_INT >= 17) {
                        wifiScanResult.timestampMs = TimeUnit.MICROSECONDS.toMillis(scanResult.timestamp);
                    } else {
                        wifiScanResult.timestampMs = SystemClock.elapsedRealtime();
                    }
                    wifiScanResults.add(wifiScanResult);
                }
            }
            return wifiScanResults;
        } catch (Exception e) {
            throw new ScannerException(Type.UNKNOWN_ERROR, e);
        }
    }

    private static boolean isWifiSsidBlacklisted(String ssid) {
        if (ssid == null || (!ssid.endsWith(SSID_NOMAP) && !ssid.contains(SSID_OPTOUT))) {
            return false;
        }
        return true;
    }

    private static void filterResults(List<ScanResult> scanResults, int maxResults) {
        if (scanResults.size() > maxResults) {
            Collections.sort(scanResults, new C01821());
            scanResults.subList(maxResults, scanResults.size()).clear();
        }
    }

    private static List<ScanResult> filterWifiScanResultsByMaxAge(List<ScanResult> scanResults, long maxAgeMs) {
        List<ScanResult> filtered = new ArrayList();
        if (scanResults != null) {
            if (VERSION.SDK_INT < 17) {
                filtered.addAll(scanResults);
            } else {
                long nowSinceBootMs = SystemClock.elapsedRealtime();
                for (ScanResult result : scanResults) {
                    long ageMs = nowSinceBootMs - (result.timestamp / 1000);
                    if (ageMs < 0) {
                        ageMs = System.currentTimeMillis() - result.timestamp;
                    }
                    if (ageMs < maxAgeMs) {
                        filtered.add(result);
                    }
                }
            }
        }
        return filtered;
    }

    public synchronized List<WifiScanResult> getWifiScans() throws ScannerException {
        List<WifiScanResult> wifiScanResults;
        wifiScanResults = null;
        if (!this.params.isWifiActiveScanForced()) {
            wifiScanResults = getCachedScanResults();
        }
        boolean isListEmpty = wifiScanResults == null || wifiScanResults.isEmpty();
        if (this.params.isWifiActiveScanForced() || (this.params.isWifiActiveScanAllowed() && isListEmpty)) {
            wifiScanResults = getActiveScanResults();
        }
        return wifiScanResults;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.facebook.places.internal.WifiScanResult> getActiveScanResults() throws com.facebook.places.internal.ScannerException {
        /*
        r6 = this;
        r1 = 0;
        r2 = r6.context;	 Catch:{ Exception -> 0x0030, all -> 0x0035 }
        r2 = com.facebook.internal.Validate.hasChangeWifiStatePermission(r2);	 Catch:{ Exception -> 0x0030, all -> 0x0035 }
        if (r2 == 0) goto L_0x0027;
    L_0x0009:
        r6.registerBroadcastReceiver();	 Catch:{ Exception -> 0x0030, all -> 0x0035 }
        r2 = r6.wifiManager;	 Catch:{ Exception -> 0x0030, all -> 0x0035 }
        r0 = r2.startScan();	 Catch:{ Exception -> 0x0030, all -> 0x0035 }
        if (r0 == 0) goto L_0x0027;
    L_0x0014:
        r3 = r6.scanLock;	 Catch:{ InterruptedException -> 0x002e }
        monitor-enter(r3);	 Catch:{ InterruptedException -> 0x002e }
        r2 = r6.scanLock;	 Catch:{ all -> 0x002b }
        r4 = r6.params;	 Catch:{ all -> 0x002b }
        r4 = r4.getWifiScanTimeoutMs();	 Catch:{ all -> 0x002b }
        r2.wait(r4);	 Catch:{ all -> 0x002b }
        monitor-exit(r3);	 Catch:{ all -> 0x002b }
    L_0x0023:
        r1 = r6.getCachedScanResults();	 Catch:{ Exception -> 0x0030, all -> 0x0035 }
    L_0x0027:
        r6.unregisterBroadcastReceiver();
    L_0x002a:
        return r1;
    L_0x002b:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002b }
        throw r2;	 Catch:{ InterruptedException -> 0x002e }
    L_0x002e:
        r2 = move-exception;
        goto L_0x0023;
    L_0x0030:
        r2 = move-exception;
        r6.unregisterBroadcastReceiver();
        goto L_0x002a;
    L_0x0035:
        r2 = move-exception;
        r6.unregisterBroadcastReceiver();
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.places.internal.WifiScannerImpl.getActiveScanResults():java.util.List<com.facebook.places.internal.WifiScanResult>");
    }

    private void registerBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            unregisterBroadcastReceiver();
        }
        this.broadcastReceiver = new ScanResultBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.context.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    private void unregisterBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            try {
                this.context.unregisterReceiver(this.broadcastReceiver);
            } catch (Exception e) {
            }
            this.broadcastReceiver = null;
        }
    }
}
