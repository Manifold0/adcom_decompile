// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.SupplicantState;
import android.text.TextUtils;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.concurrent.TimeUnit;
import com.facebook.internal.Validate;
import java.util.Iterator;
import android.os.SystemClock;
import java.util.Collection;
import android.os.Build$VERSION;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.net.wifi.ScanResult;
import java.util.List;
import android.net.wifi.WifiManager;
import android.content.Context;

public class WifiScannerImpl implements WifiScanner
{
    private static final String SSID_NOMAP = "_nomap";
    private static final String SSID_OPTOUT = "_optout";
    private ScanResultBroadcastReceiver broadcastReceiver;
    private Context context;
    private final LocationPackageRequestParams params;
    private final Object scanLock;
    private WifiManager wifiManager;
    
    WifiScannerImpl(final Context context, final LocationPackageRequestParams params) {
        this.scanLock = new Object();
        this.context = context;
        this.params = params;
    }
    
    private static void filterResults(final List<ScanResult> list, final int n) {
        if (list.size() > n) {
            Collections.sort((List<Object>)list, (Comparator<? super Object>)new Comparator<ScanResult>() {
                @Override
                public int compare(final ScanResult scanResult, final ScanResult scanResult2) {
                    return scanResult2.level - scanResult.level;
                }
            });
            list.subList(n, list.size()).clear();
        }
    }
    
    private static List<ScanResult> filterWifiScanResultsByMaxAge(final List<ScanResult> list, final long n) {
        final ArrayList<ScanResult> list2 = new ArrayList<ScanResult>();
        if (list != null) {
            if (Build$VERSION.SDK_INT < 17) {
                list2.addAll((Collection<?>)list);
            }
            else {
                final long elapsedRealtime = SystemClock.elapsedRealtime();
                for (final ScanResult scanResult : list) {
                    long n2;
                    if ((n2 = elapsedRealtime - scanResult.timestamp / 1000L) < 0L) {
                        n2 = System.currentTimeMillis() - scanResult.timestamp;
                    }
                    if (n2 < n) {
                        list2.add(scanResult);
                    }
                }
            }
        }
        return list2;
    }
    
    private List<WifiScanResult> getActiveScanResults() throws ScannerException {
        Object o2;
        final Object o = o2 = null;
        try {
            if (!Validate.hasChangeWifiStatePermission(this.context)) {
                return (List<WifiScanResult>)o2;
            }
            this.registerBroadcastReceiver();
            final boolean startScan = this.wifiManager.startScan();
            o2 = o;
            if (!startScan) {
                return (List<WifiScanResult>)o2;
            }
            try {
                o2 = this.scanLock;
                synchronized (o2) {
                    this.scanLock.wait(this.params.getWifiScanTimeoutMs());
                    // monitorexit(o2)
                    o2 = this.getCachedScanResults();
                    return (List<WifiScanResult>)o2;
                }
            }
            catch (InterruptedException ex) {}
        }
        catch (Exception ex2) {
            return null;
        }
        finally {
            this.unregisterBroadcastReceiver();
        }
    }
    
    private List<WifiScanResult> getCachedScanResults() throws ScannerException {
        ArrayList list;
        while (true) {
            while (true) {
                WifiScanResult wifiScanResult = null;
                Label_0172: {
                    try {
                        final List<ScanResult> filterWifiScanResultsByMaxAge = filterWifiScanResultsByMaxAge(this.wifiManager.getScanResults(), this.params.getWifiScanMaxAgeMs());
                        filterResults(filterWifiScanResultsByMaxAge, this.params.getWifiMaxScanResults());
                        list = new ArrayList<WifiScanResult>(filterWifiScanResultsByMaxAge.size());
                        for (final ScanResult scanResult : filterWifiScanResultsByMaxAge) {
                            if (!isWifiSsidBlacklisted(scanResult.SSID)) {
                                wifiScanResult = new WifiScanResult();
                                wifiScanResult.bssid = scanResult.BSSID;
                                wifiScanResult.ssid = scanResult.SSID;
                                wifiScanResult.rssi = scanResult.level;
                                wifiScanResult.frequency = scanResult.frequency;
                                if (Build$VERSION.SDK_INT < 17) {
                                    break Label_0172;
                                }
                                wifiScanResult.timestampMs = TimeUnit.MICROSECONDS.toMillis(scanResult.timestamp);
                                list.add(wifiScanResult);
                            }
                        }
                        break;
                    }
                    catch (Exception ex) {
                        throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR, ex);
                    }
                }
                wifiScanResult.timestampMs = SystemClock.elapsedRealtime();
                continue;
            }
        }
        return (List<WifiScanResult>)list;
    }
    
    private boolean isWifiScanningAlwaysOn() {
        return Build$VERSION.SDK_INT >= 18 && this.wifiManager.isScanAlwaysAvailable();
    }
    
    private static boolean isWifiSsidBlacklisted(final String s) {
        return s != null && (s.endsWith("_nomap") || s.contains("_optout"));
    }
    
    private void registerBroadcastReceiver() {
        if (this.broadcastReceiver != null) {
            this.unregisterBroadcastReceiver();
        }
        this.broadcastReceiver = new ScanResultBroadcastReceiver();
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.context.registerReceiver((BroadcastReceiver)this.broadcastReceiver, intentFilter);
    }
    
    private void unregisterBroadcastReceiver() {
        if (this.broadcastReceiver == null) {
            return;
        }
        while (true) {
            try {
                this.context.unregisterReceiver((BroadcastReceiver)this.broadcastReceiver);
                this.broadcastReceiver = null;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public WifiScanResult getConnectedWifi() throws ScannerException {
        try {
            final WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty((CharSequence)connectionInfo.getBSSID()) && connectionInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                if (!isWifiSsidBlacklisted(connectionInfo.getSSID())) {
                    final WifiScanResult wifiScanResult = new WifiScanResult();
                    wifiScanResult.bssid = connectionInfo.getBSSID();
                    wifiScanResult.ssid = connectionInfo.getSSID();
                    wifiScanResult.rssi = connectionInfo.getRssi();
                    wifiScanResult.timestampMs = SystemClock.elapsedRealtime();
                    final WifiScanResult wifiScanResult2 = wifiScanResult;
                    if (Build$VERSION.SDK_INT >= 21) {
                        wifiScanResult.frequency = connectionInfo.getFrequency();
                        return wifiScanResult;
                    }
                    return wifiScanResult2;
                }
            }
        }
        catch (Exception ex) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR, ex);
        }
        return null;
    }
    
    @Override
    public List<WifiScanResult> getWifiScans() throws ScannerException {
        while (true) {
            // monitorenter(this)
            List<WifiScanResult> cachedScanResults = null;
            while (true) {
                try {
                    if (!this.params.isWifiActiveScanForced()) {
                        cachedScanResults = this.getCachedScanResults();
                    }
                    if (cachedScanResults != null) {
                        if (!cachedScanResults.isEmpty()) {
                            final int n = 0;
                            if (!this.params.isWifiActiveScanForced()) {
                                List<WifiScanResult> activeScanResults = cachedScanResults;
                                if (!this.params.isWifiActiveScanAllowed()) {
                                    return activeScanResults;
                                }
                                activeScanResults = cachedScanResults;
                                if (n == 0) {
                                    return activeScanResults;
                                }
                            }
                            return this.getActiveScanResults();
                        }
                    }
                }
                finally {
                }
                // monitorexit(this)
                final int n = 1;
                continue;
            }
        }
    }
    
    @Override
    public void initAndCheckEligibility() throws ScannerException {
        if (!this.context.getPackageManager().hasSystemFeature("android.hardware.wifi")) {
            throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
        }
        if (!Validate.hasWiFiPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        }
        if (this.wifiManager == null) {
            this.wifiManager = (WifiManager)this.context.getSystemService("wifi");
        }
        if (!this.isWifiScanningAlwaysOn() && !this.wifiManager.isWifiEnabled()) {
            throw new ScannerException(ScannerException.Type.DISABLED);
        }
    }
    
    @Override
    public boolean isWifiScanningEnabled() {
        try {
            this.initAndCheckEligibility();
            if (Validate.hasLocationPermission(this.context)) {
                return true;
            }
        }
        catch (ScannerException ex) {}
        return false;
    }
    
    private class ScanResultBroadcastReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if (intent == null || !"android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                return;
            }
            synchronized (WifiScannerImpl.this.scanLock) {
                WifiScannerImpl.this.scanLock.notify();
                // monitorexit(WifiScannerImpl.access$100(this.this$0))
                WifiScannerImpl.this.unregisterBroadcastReceiver();
            }
        }
    }
}
