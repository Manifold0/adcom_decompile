// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.devicerequests.internal;

import android.net.nsd.NsdServiceInfo;
import com.facebook.internal.SmartLoginOption;
import com.facebook.internal.FetchedAppSettingsManager;
import android.os.Build$VERSION;
import org.json.JSONException;
import android.os.Build;
import org.json.JSONObject;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.WriterException;
import android.graphics.Bitmap$Config;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import java.util.EnumMap;
import com.google.zxing.EncodeHintType;
import android.graphics.Bitmap;
import android.annotation.TargetApi;
import com.facebook.FacebookSdk;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager$RegistrationListener;
import java.util.HashMap;

public class DeviceRequestsHelper
{
    static final String DEVICE_INFO_DEVICE = "device";
    static final String DEVICE_INFO_MODEL = "model";
    public static final String DEVICE_INFO_PARAM = "device_info";
    static final String SDK_FLAVOR = "android";
    static final String SDK_HEADER = "fbsdk";
    static final String SERVICE_TYPE = "_fb._tcp.";
    private static HashMap<String, NsdManager$RegistrationListener> deviceRequestsListeners;
    
    static {
        DeviceRequestsHelper.deviceRequestsListeners = new HashMap<String, NsdManager$RegistrationListener>();
    }
    
    public static void cleanUpAdvertisementService(final String s) {
        cleanUpAdvertisementServiceImpl(s);
    }
    
    @TargetApi(16)
    private static void cleanUpAdvertisementServiceImpl(final String s) {
        final NsdManager$RegistrationListener nsdManager$RegistrationListener = DeviceRequestsHelper.deviceRequestsListeners.get(s);
        if (nsdManager$RegistrationListener != null) {
            ((NsdManager)FacebookSdk.getApplicationContext().getSystemService("servicediscovery")).unregisterService(nsdManager$RegistrationListener);
            DeviceRequestsHelper.deviceRequestsListeners.remove(s);
        }
    }
    
    public static Bitmap generateQRCode(final String s) {
        while (true) {
            final Bitmap bitmap = null;
            final EnumMap<EncodeHintType, Integer> enumMap = new EnumMap<EncodeHintType, Integer>(EncodeHintType.class);
            enumMap.put(EncodeHintType.MARGIN, 2);
            Bitmap bitmap2 = bitmap;
            BitMatrix encode;
            int height;
            int width;
            int[] array;
            int n = 0;
            Bitmap bitmap3;
            int n2;
            int n3;
            Label_0119_Outer:Label_0169_Outer:
            while (true) {
            Label_0169:
                while (true) {
                Label_0094:
                    while (true) {
                        while (true) {
                            try {
                                encode = new MultiFormatWriter().encode(s, BarcodeFormat.QR_CODE, 200, 200, (Map)enumMap);
                                bitmap2 = bitmap;
                                height = encode.getHeight();
                                bitmap2 = bitmap;
                                width = encode.getWidth();
                                bitmap2 = bitmap;
                                array = new int[height * width];
                                n = 0;
                                break Label_0158;
                                bitmap2 = bitmap;
                                bitmap3 = (bitmap2 = Bitmap.createBitmap(width, height, Bitmap$Config.ARGB_8888));
                                bitmap3.setPixels(array, 0, width, 0, 0, width, height);
                                return bitmap3;
                                while (true) {
                                    bitmap2 = bitmap;
                                    n3 = -16777216;
                                    break Label_0169;
                                    continue Label_0169_Outer;
                                }
                            }
                            // iftrue(Label_0186:, !encode.get(n2, n))
                            // iftrue(Label_0191:, n2 >= width)
                            catch (WriterException ex) {
                                return bitmap2;
                            }
                            if (n < height) {
                                n2 = 0;
                                continue Label_0094;
                            }
                            continue Label_0169_Outer;
                        }
                        array[n * width + n2] = n3;
                        ++n2;
                        continue Label_0094;
                    }
                    Label_0186: {
                        n3 = -1;
                    }
                    continue Label_0169;
                }
                Label_0191: {
                    ++n;
                }
                continue Label_0119_Outer;
            }
        }
    }
    
    public static String getDeviceInfo() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("device", (Object)Build.DEVICE);
            jsonObject.put("model", (Object)Build.MODEL);
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            return jsonObject.toString();
        }
    }
    
    public static boolean isAvailable() {
        return Build$VERSION.SDK_INT >= 16 && FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId()).getSmartLoginOptions().contains(SmartLoginOption.Enabled);
    }
    
    public static boolean startAdvertisementService(final String s) {
        return isAvailable() && startAdvertisementServiceImpl(s);
    }
    
    @TargetApi(16)
    private static boolean startAdvertisementServiceImpl(final String s) {
        if (DeviceRequestsHelper.deviceRequestsListeners.containsKey(s)) {
            return true;
        }
        final String format = String.format("%s_%s_%s", "fbsdk", String.format("%s-%s", "android", FacebookSdk.getSdkVersion().replace('.', '|')), s);
        final NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setServiceType("_fb._tcp.");
        nsdServiceInfo.setServiceName(format);
        nsdServiceInfo.setPort(80);
        final NsdManager nsdManager = (NsdManager)FacebookSdk.getApplicationContext().getSystemService("servicediscovery");
        final NsdManager$RegistrationListener nsdManager$RegistrationListener = (NsdManager$RegistrationListener)new NsdManager$RegistrationListener() {
            public void onRegistrationFailed(final NsdServiceInfo nsdServiceInfo, final int n) {
                DeviceRequestsHelper.cleanUpAdvertisementService(s);
            }
            
            public void onServiceRegistered(final NsdServiceInfo nsdServiceInfo) {
                if (!format.equals(nsdServiceInfo.getServiceName())) {
                    DeviceRequestsHelper.cleanUpAdvertisementService(s);
                }
            }
            
            public void onServiceUnregistered(final NsdServiceInfo nsdServiceInfo) {
            }
            
            public void onUnregistrationFailed(final NsdServiceInfo nsdServiceInfo, final int n) {
            }
        };
        DeviceRequestsHelper.deviceRequestsListeners.put(s, (NsdManager$RegistrationListener)nsdManager$RegistrationListener);
        nsdManager.registerService(nsdServiceInfo, 1, (NsdManager$RegistrationListener)nsdManager$RegistrationListener);
        return true;
    }
}
