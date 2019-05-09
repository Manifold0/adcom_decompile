// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

import java.util.Iterator;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanSettings$Builder;
import com.facebook.internal.Validate;
import android.os.Build$VERSION;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import android.os.Handler;
import android.os.Looper;
import android.bluetooth.le.ScanRecord;
import android.util.Log;
import com.facebook.FacebookSdk;
import android.bluetooth.le.ScanResult;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.BluetoothAdapter;
import android.annotation.TargetApi;

@TargetApi(21)
public class BleScannerImpl implements BleScanner
{
    private static final byte[] EDDYSTONE_PREFIX;
    private static final byte[] GRAVITY_PREFIX;
    private static final byte[] IBEACON_PREFIX;
    private static final String TAG = "BleScannerImpl";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private Context context;
    private int errorCode;
    private boolean isScanInProgress;
    private LocationPackageRequestParams params;
    private ScanCallBackImpl scanCallBack;
    private final List<BluetoothScanResult> scanResults;
    
    static {
        IBEACON_PREFIX = fromHexString("ff4c000215");
        EDDYSTONE_PREFIX = fromHexString("16aafe");
        GRAVITY_PREFIX = fromHexString("17ffab01");
    }
    
    BleScannerImpl(final Context context, final LocationPackageRequestParams params) {
        this.scanResults = new ArrayList<BluetoothScanResult>();
        this.context = context;
        this.params = params;
    }
    
    private static String formatPayload(final byte[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return toHexString(array, getPayloadLength(array));
    }
    
    private static byte[] fromHexString(final String s) {
        final int length = s.length();
        final byte[] array = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return array;
    }
    
    private static int getPayloadLength(final byte[] array) {
        byte b;
        for (int i = 0; i < array.length; i += b + 1) {
            b = array[i];
            if (b == 0) {
                return i;
            }
            if (b < 0) {
                return array.length;
            }
        }
        return array.length;
    }
    
    private static boolean isAdvPacketBeacon(final byte[] array, final int n) {
        return isArrayContained(array, n + 1, BleScannerImpl.IBEACON_PREFIX) || isArrayContained(array, n + 1, BleScannerImpl.EDDYSTONE_PREFIX) || isArrayContained(array, n, BleScannerImpl.GRAVITY_PREFIX) || isAltBeacon(array, n);
    }
    
    private static boolean isAltBeacon(final byte[] array, int n) {
        boolean b2;
        final boolean b = b2 = false;
        if (n + 5 < array.length) {
            final byte b3 = array[n];
            final byte b4 = array[n + 1];
            final byte b5 = array[n + 4];
            n = array[n + 5];
            b2 = b;
            if (b3 == 27) {
                b2 = b;
                if (b4 == -1) {
                    b2 = b;
                    if (b5 == -66) {
                        b2 = b;
                        if (n == -84) {
                            b2 = true;
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    private static boolean isArrayContained(final byte[] array, final int n, final byte[] array2) {
        final int length = array2.length;
        if (n + length <= array.length) {
            for (int i = 0; i < length; ++i) {
                if (array[n + i] != array2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private static boolean isBeacon(final byte[] array) {
        if (array != null) {
            int n;
            for (int i = 0, length = array.length; i < length; i += n) {
                final byte b = array[i];
                if (b <= 0) {
                    break;
                }
                n = b + 1;
                if (i + n > length) {
                    break;
                }
                if (isAdvPacketBeacon(array, i)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void logException(final String s, final Exception ex) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e("BleScannerImpl", s, (Throwable)ex);
        }
    }
    
    private static BluetoothScanResult newBluetoothScanResult(final ScanResult scanResult) {
        final ScanRecord scanRecord = scanResult.getScanRecord();
        if (isBeacon(scanRecord.getBytes())) {
            return new BluetoothScanResult(formatPayload(scanRecord.getBytes()), scanResult.getRssi(), scanResult.getTimestampNanos());
        }
        return null;
    }
    
    private static String toHexString(final byte[] array, int i) {
        final StringBuffer sb = new StringBuffer();
        int length;
        if (i < 0 || (length = i) > array.length) {
            length = array.length;
        }
        for (i = 0; i < length; ++i) {
            sb.append(String.format("%02x", array[i]));
        }
        return sb.toString();
    }
    
    private void waitForMainLooper(final long n) {
        try {
            final Object o = new Object();
            synchronized (o) {
                new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        try {
                            synchronized (o) {
                                o.notify();
                            }
                        }
                        catch (Exception ex) {
                            logException("Exception waiting for main looper", ex);
                        }
                    }
                });
                o.wait(n);
            }
        }
        catch (Exception ex) {
            logException("Exception waiting for main looper", ex);
        }
    }
    
    @Override
    public int getErrorCode() {
        synchronized (this) {
            return this.errorCode;
        }
    }
    
    @Override
    public List<BluetoothScanResult> getScanResults() {
        synchronized (this) {
            synchronized (this.scanResults) {
                final int bluetoothMaxScanResults = this.params.getBluetoothMaxScanResults();
                ArrayList<Object> list;
                if (this.scanResults.size() > bluetoothMaxScanResults) {
                    list = (ArrayList<Object>)new ArrayList<BluetoothScanResult>(bluetoothMaxScanResults);
                    Collections.sort(this.scanResults, new Comparator<BluetoothScanResult>() {
                        @Override
                        public int compare(final BluetoothScanResult bluetoothScanResult, final BluetoothScanResult bluetoothScanResult2) {
                            return bluetoothScanResult2.rssi - bluetoothScanResult.rssi;
                        }
                    });
                    list.addAll(this.scanResults.subList(0, bluetoothMaxScanResults));
                }
                else {
                    list = (ArrayList<Object>)new ArrayList<BluetoothScanResult>(this.scanResults.size());
                    list.addAll(this.scanResults);
                }
                return (List<BluetoothScanResult>)list;
            }
        }
    }
    
    @Override
    public void initAndCheckEligibility() throws ScannerException {
        synchronized (this) {
            if (Build$VERSION.SDK_INT < 21) {
                throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
            }
        }
        if (!Validate.hasBluetoothPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        }
        if (!Validate.hasLocationPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        }
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (this.bluetoothAdapter == null || !this.bluetoothAdapter.isEnabled()) {
            throw new ScannerException(ScannerException.Type.DISABLED);
        }
        this.bluetoothLeScanner = this.bluetoothAdapter.getBluetoothLeScanner();
        if (this.bluetoothLeScanner == null) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR);
        }
    }
    // monitorexit(this)
    
    @Override
    public void startScanning() throws ScannerException {
        synchronized (this) {
            if (this.isScanInProgress) {
                throw new ScannerException(ScannerException.Type.SCAN_ALREADY_IN_PROGRESS);
            }
        }
        this.scanCallBack = new ScanCallBackImpl();
        this.isScanInProgress = true;
        this.errorCode = 0;
        synchronized (this.scanResults) {
            this.scanResults.clear();
            // monitorexit(this.scanResults)
            if (this.bluetoothLeScanner == null) {
                throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR);
            }
        }
        try {
            final ScanSettings$Builder scanSettings$Builder = new ScanSettings$Builder();
            scanSettings$Builder.setScanMode(2);
            scanSettings$Builder.setReportDelay(0L);
            this.bluetoothLeScanner.startScan((List)null, scanSettings$Builder.build(), (ScanCallback)this.scanCallBack);
            this.isScanInProgress = true;
        }
        // monitorexit(this)
        catch (Exception ex) {
            throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR);
        }
    }
    
    @Override
    public void stopScanning() {
        synchronized (this) {
            this.bluetoothLeScanner.flushPendingScanResults((ScanCallback)this.scanCallBack);
            this.bluetoothLeScanner.stopScan((ScanCallback)this.scanCallBack);
            this.waitForMainLooper(this.params.getBluetoothFlushResultsTimeoutMs());
            this.isScanInProgress = false;
        }
    }
    
    private class ScanCallBackImpl extends ScanCallback
    {
        public void onBatchScanResults(final List<ScanResult> list) {
            super.onBatchScanResults((List)list);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    final Iterator<ScanResult> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        final BluetoothScanResult access$400 = newBluetoothScanResult(iterator.next());
                        if (access$400 != null) {
                            BleScannerImpl.this.scanResults.add(access$400);
                        }
                    }
                }
            }
            catch (Exception ex) {
                logException("Exception in ble scan callback", ex);
                return;
            }
        }
        // monitorexit(list2)
        
        public void onScanFailed(final int n) {
            super.onScanFailed(n);
            BleScannerImpl.this.errorCode = n;
        }
        
        public void onScanResult(final int n, final ScanResult scanResult) {
            super.onScanResult(n, scanResult);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    final BluetoothScanResult access$400 = newBluetoothScanResult(scanResult);
                    if (access$400 != null) {
                        BleScannerImpl.this.scanResults.add(access$400);
                    }
                }
            }
            catch (Exception ex) {
                logException("Exception in ble scan callback", ex);
            }
        }
    }
}
