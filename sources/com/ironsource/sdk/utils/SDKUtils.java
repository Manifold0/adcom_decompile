package com.ironsource.sdk.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import com.adjust.sdk.Constants;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import com.unity.purchasing.googleplay.IabHelper;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SDKUtils {
    private static final String TAG = SDKUtils.class.getSimpleName();
    private static String mAdvertisingId;
    private static String mControllerConfig;
    private static String mControllerUrl;
    private static int mDebugMode = 0;
    private static boolean mIsLimitedTrackingEnabled = true;
    private static String mUserGroup = "";
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /* renamed from: com.ironsource.sdk.utils.SDKUtils$1 */
    static class C07431 implements OnClickListener {
        C07431() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void loadGoogleAdvertiserInfo(android.content.Context r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0075 in list [B:4:0x0019]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1364497552.run(Unknown Source)
*/
        /*
        r2 = 0;
        r0 = com.ironsource.environment.DeviceStatus.getAdvertisingIdInfo(r7);	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        r3 = 0;	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        r3 = r0[r3];	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        mAdvertisingId = r3;	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        r3 = 1;	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        r3 = r0[r3];	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        r3 = r3.booleanValue();	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        mIsLimitedTrackingEnabled = r3;	 Catch:{ Exception -> 0x0076, all -> 0x00d7 }
        if (r2 == 0) goto L_0x0075;
    L_0x0019:
        r3 = r2.getMessage();
        if (r3 == 0) goto L_0x0047;
    L_0x001f:
        r3 = TAG;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r2.getClass();
        r5 = r5.getSimpleName();
        r4 = r4.append(r5);
        r5 = ": ";
        r4 = r4.append(r5);
        r5 = r2.getMessage();
        r4 = r4.append(r5);
        r4 = r4.toString();
        com.ironsource.sdk.utils.Logger.m1212i(r3, r4);
    L_0x0047:
        r3 = r2.getCause();
        if (r3 == 0) goto L_0x0075;
    L_0x004d:
        r3 = TAG;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r2.getClass();
        r5 = r5.getSimpleName();
        r4 = r4.append(r5);
        r5 = ": ";
        r4 = r4.append(r5);
        r5 = r2.getCause();
        r4 = r4.append(r5);
        r4 = r4.toString();
        com.ironsource.sdk.utils.Logger.m1212i(r3, r4);
    L_0x0075:
        return;
    L_0x0076:
        r1 = move-exception;
        r2 = r1;
        if (r2 == 0) goto L_0x0075;
    L_0x007a:
        r3 = r2.getMessage();
        if (r3 == 0) goto L_0x00a8;
    L_0x0080:
        r3 = TAG;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r2.getClass();
        r5 = r5.getSimpleName();
        r4 = r4.append(r5);
        r5 = ": ";
        r4 = r4.append(r5);
        r5 = r2.getMessage();
        r4 = r4.append(r5);
        r4 = r4.toString();
        com.ironsource.sdk.utils.Logger.m1212i(r3, r4);
    L_0x00a8:
        r3 = r2.getCause();
        if (r3 == 0) goto L_0x0075;
    L_0x00ae:
        r3 = TAG;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r2.getClass();
        r5 = r5.getSimpleName();
        r4 = r4.append(r5);
        r5 = ": ";
        r4 = r4.append(r5);
        r5 = r2.getCause();
        r4 = r4.append(r5);
        r4 = r4.toString();
        com.ironsource.sdk.utils.Logger.m1212i(r3, r4);
        goto L_0x0075;
    L_0x00d7:
        r3 = move-exception;
        if (r2 == 0) goto L_0x0136;
    L_0x00da:
        r4 = r2.getMessage();
        if (r4 == 0) goto L_0x0108;
    L_0x00e0:
        r4 = TAG;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r2.getClass();
        r6 = r6.getSimpleName();
        r5 = r5.append(r6);
        r6 = ": ";
        r5 = r5.append(r6);
        r6 = r2.getMessage();
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.ironsource.sdk.utils.Logger.m1212i(r4, r5);
    L_0x0108:
        r4 = r2.getCause();
        if (r4 == 0) goto L_0x0136;
    L_0x010e:
        r4 = TAG;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r2.getClass();
        r6 = r6.getSimpleName();
        r5 = r5.append(r6);
        r6 = ": ";
        r5 = r5.append(r6);
        r6 = r2.getCause();
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.ironsource.sdk.utils.Logger.m1212i(r4, r5);
    L_0x0136:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ironsource.sdk.utils.SDKUtils.loadGoogleAdvertiserInfo(android.content.Context):void");
    }

    public static String getFileName(String url) {
        String[] assetSplit = url.split(File.separator);
        String encodedlFileName = null;
        try {
            encodedlFileName = URLEncoder.encode(assetSplit[assetSplit.length - 1].split("\\?")[0], "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedlFileName;
    }

    public static int pxToDp(long px) {
        return (int) ((((float) px) / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int dpToPx(long dp) {
        return (int) ((((float) dp) * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int convertPxToDp(int pixels) {
        return (int) TypedValue.applyDimension(1, (float) pixels, Resources.getSystem().getDisplayMetrics());
    }

    public static int convertDpToPx(int dp) {
        return (int) TypedValue.applyDimension(0, (float) dp, Resources.getSystem().getDisplayMetrics());
    }

    public static String translateRequestedOrientation(int orientation) {
        String result = ParametersKeys.ORIENTATION_NONE;
        switch (orientation) {
            case 0:
            case 6:
            case 8:
            case 11:
                return "landscape";
            case 1:
            case 7:
            case 9:
            case 12:
                return "portrait";
            default:
                return result;
        }
    }

    public static String translateOrientation(int orientation) {
        String strOrientation = ParametersKeys.ORIENTATION_NONE;
        switch (orientation) {
            case 1:
                return "portrait";
            case 2:
                return "landscape";
            default:
                return strOrientation;
        }
    }

    public static JSONObject getOrientation(Context context) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("orientation", translateOrientation(DeviceStatus.getDeviceOrientation(context)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String createErrorMessage(String action, String stage) {
        return String.format("%s Failure occurred during initiation at: %s", new Object[]{action, stage});
    }

    public static Long getCurrentTimeMillis() {
        return Long.valueOf(System.currentTimeMillis());
    }

    public static boolean isApplicationVisible(Context context) {
        String packageName = context.getPackageName();
        for (RunningAppProcessInfo appProcess : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (appProcess.processName.equalsIgnoreCase(packageName) && appProcess.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public static void showNoInternetDialog(Context context) {
        new Builder(context).setMessage("No Internet Connection").setPositiveButton("Ok", new C07431()).show();
    }

    public static byte[] encrypt(String x) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(Constants.SHA1);
            digest.reset();
            digest.update(x.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (digest != null) {
            return digest.digest();
        }
        return null;
    }

    public static String convertStreamToString(InputStream is, boolean isGzipEnabled, String dir, String fileName) throws IOException {
        Throwable th;
        InputStream cleanedIs = is;
        if (isGzipEnabled) {
            cleanedIs = new GZIPInputStream(is);
        }
        BufferedReader reader = null;
        Writer writer = new BufferedWriter(new FileWriter(new File(dir, fileName)));
        try {
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(cleanedIs, "UTF-8"));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = reader2.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                    sb.append("\n");
                }
                writer.write(sb.toString());
                String stringBuilder = sb.toString();
                if (reader2 != null) {
                    reader2.close();
                }
                cleanedIs.close();
                if (isGzipEnabled) {
                    is.close();
                }
                writer.close();
                return stringBuilder;
            } catch (Throwable th2) {
                th = th2;
                reader = reader2;
                if (reader != null) {
                    reader.close();
                }
                cleanedIs.close();
                if (isGzipEnabled) {
                    is.close();
                }
                writer.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (reader != null) {
                reader.close();
            }
            cleanedIs.close();
            if (isGzipEnabled) {
                is.close();
            }
            writer.close();
            throw th;
        }
    }

    public static String encodeString(String value) {
        String encodedString = "";
        try {
            encodedString = URLEncoder.encode(value, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
        }
        return encodedString;
    }

    public static String getMD5(String input) {
        try {
            String hashtext = new BigInteger(1, MessageDigest.getInstance(Constants.MD5).digest(input.getBytes())).toString(16);
            while (hashtext.length() < 32) {
                hashtext = AppEventsConstants.EVENT_PARAM_VALUE_NO + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAdvertiserId() {
        return mAdvertisingId;
    }

    public static boolean isLimitAdTrackingEnabled() {
        return mIsLimitedTrackingEnabled;
    }

    public static Object getIInAppBillingServiceClass(IBinder binder) {
        Object object = null;
        Exception exception = null;
        try {
            object = Class.forName("com.android.vending.billing.IInAppBillingService$Stub").getMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{binder});
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e) {
            exception = e;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e2) {
            exception = e2;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e22) {
            exception = e22;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e222) {
            exception = e222;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e2222) {
            exception = e2222;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Throwable th) {
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        }
        return object;
    }

    public static String queryingPurchasedItems(Object object, String pckName) {
        JSONArray purchases = new JSONArray();
        Exception exception = null;
        try {
            Object mBundleClass = object.getClass().getMethod("getPurchases", new Class[]{Integer.TYPE, String.class, String.class, String.class}).invoke(object, new Object[]{Integer.valueOf(3), pckName, "inapp", null});
            Method mGetIntmethod = mBundleClass.getClass().getMethod("getInt", new Class[]{String.class});
            Method mGetStringArrayListMethod = mBundleClass.getClass().getMethod("getStringArrayList", new Class[]{String.class});
            Method mGetStringMethod = mBundleClass.getClass().getMethod("getString", new Class[]{String.class});
            if (((Integer) mGetIntmethod.invoke(mBundleClass, new Object[]{"RESPONSE_CODE"})).intValue() == 0) {
                ArrayList<String> ownedSkus = (ArrayList) mGetStringArrayListMethod.invoke(mBundleClass, new Object[]{IabHelper.RESPONSE_INAPP_ITEM_LIST});
                ArrayList<String> purchaseDataList = (ArrayList) mGetStringArrayListMethod.invoke(mBundleClass, new Object[]{IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST});
                ArrayList<String> signatureList = (ArrayList) mGetStringArrayListMethod.invoke(mBundleClass, new Object[]{IabHelper.RESPONSE_INAPP_SIGNATURE_LIST});
                String continuationToken = (String) mGetStringMethod.invoke(mBundleClass, new Object[]{IabHelper.INAPP_CONTINUATION_TOKEN});
                for (int i = 0; i < purchaseDataList.size(); i++) {
                    String purchaseData = (String) purchaseDataList.get(i);
                    String signature = (String) signatureList.get(i);
                    String sku = (String) ownedSkus.get(i);
                    Logger.m1212i(TAG, purchaseData);
                    Logger.m1212i(TAG, signature);
                    Logger.m1212i(TAG, sku);
                    JSONObject item = new JSONObject();
                    try {
                        item.put("purchaseData", purchaseData);
                        item.put(InAppPurchaseMetaData.KEY_SIGNATURE, purchaseData);
                        item.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, purchaseData);
                        purchases.put(item);
                    } catch (JSONException e) {
                    }
                }
            }
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e2) {
            exception = e2;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e22) {
            exception = e22;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e222) {
            exception = e222;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Exception e2222) {
            exception = e2222;
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        } catch (Throwable th) {
            if (exception != null) {
                if (exception.getMessage() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getMessage());
                }
                if (exception.getCause() != null) {
                    Logger.m1212i(TAG, exception.getClass().getSimpleName() + ": " + exception.getCause());
                }
            }
        }
        return purchases.toString();
    }

    public static String getControllerUrl() {
        if (TextUtils.isEmpty(mControllerUrl)) {
            return "";
        }
        return mControllerUrl;
    }

    public static String getSDKVersion() {
        return com.ironsource.sdk.constants.Constants.SDK_VERSION;
    }

    public static void setControllerUrl(String url) {
        mControllerUrl = url;
    }

    public static String getControllerConfig() {
        return mControllerConfig;
    }

    public static void setControllerConfig(String config) {
        mControllerConfig = config;
    }

    public static int getDebugMode() {
        return mDebugMode;
    }

    public static void setDebugMode(int debugMode) {
        mDebugMode = debugMode;
    }

    public static String getValueFromJsonObject(String json, String key) {
        try {
            return new JSONObject(json).getString(key);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return "mounted".equals(state) || "mounted_ro".equals(state);
    }

    public static int getActivityUIFlags(boolean isImmersive) {
        int uiFlags = 0;
        if (VERSION.SDK_INT >= 14) {
            uiFlags = 2;
        }
        if (VERSION.SDK_INT >= 16) {
            uiFlags |= 1796;
        }
        if (VERSION.SDK_INT < 19 || !isImmersive) {
            return uiFlags;
        }
        return uiFlags | 4096;
    }

    private static int generateViewIdForOldOS() {
        int result;
        int newValue;
        do {
            result = sNextGeneratedId.get();
            newValue = result + 1;
            if (newValue > ViewCompat.MEASURED_SIZE_MASK) {
                newValue = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(result, newValue));
        return result;
    }

    public static int generateViewId() {
        if (VERSION.SDK_INT < 17) {
            return generateViewIdForOldOS();
        }
        return View.generateViewId();
    }

    public static JSONObject getControllerConfigAsJSONObject() {
        try {
            return new JSONObject(getControllerConfig());
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static ProductType getProductType(String product) {
        if (product.equalsIgnoreCase(ProductType.RewardedVideo.toString())) {
            return ProductType.RewardedVideo;
        }
        if (product.equalsIgnoreCase(ProductType.Interstitial.toString())) {
            return ProductType.Interstitial;
        }
        if (product.equalsIgnoreCase(ProductType.OfferWall.toString())) {
            return ProductType.OfferWall;
        }
        return null;
    }

    public static void setTesterParameters(String testerParameters) {
        mUserGroup = testerParameters;
    }

    public static String getTesterParameters() {
        return mUserGroup;
    }
}
