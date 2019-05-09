// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.utils;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.os.Environment;
import java.util.Iterator;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.environment.DeviceStatus;
import android.content.Context;
import java.math.BigInteger;
import java.lang.reflect.InvocationTargetException;
import android.os.IBinder;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.View;
import android.os.Build$VERSION;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.zip.GZIPInputStream;
import java.io.InputStream;
import android.util.TypedValue;
import android.content.res.Resources;
import java.util.concurrent.atomic.AtomicInteger;

public class SDKUtils
{
    private static final String TAG;
    private static String mAdvertisingId;
    private static String mControllerConfig;
    private static String mControllerUrl;
    private static int mDebugMode;
    private static boolean mIsLimitedTrackingEnabled;
    private static String mUserGroup;
    private static final AtomicInteger sNextGeneratedId;
    
    static {
        TAG = SDKUtils.class.getSimpleName();
        SDKUtils.mIsLimitedTrackingEnabled = true;
        SDKUtils.mDebugMode = 0;
        SDKUtils.mUserGroup = "";
        sNextGeneratedId = new AtomicInteger(1);
    }
    
    public static int convertDpToPx(final int n) {
        return (int)TypedValue.applyDimension(0, (float)n, Resources.getSystem().getDisplayMetrics());
    }
    
    public static int convertPxToDp(final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, Resources.getSystem().getDisplayMetrics());
    }
    
    public static String convertStreamToString(final InputStream inputStream, final boolean b, String s, String string) throws IOException {
        InputStream inputStream2 = inputStream;
        if (b) {
            inputStream2 = new GZIPInputStream(inputStream);
        }
        String line = null;
        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(s, string)));
        while (true) {
            try {
                s = (String)new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));
                final StringBuilder sb2;
                Label_0129: {
                    try {
                        final StringBuilder sb = new StringBuilder();
                        while (true) {
                            line = ((BufferedReader)s).readLine();
                            if (line == null) {
                                break Label_0129;
                            }
                            sb.append(line);
                            sb.append("\n");
                        }
                    }
                    finally {}
                    if (s != null) {
                        ((BufferedReader)s).close();
                    }
                    inputStream2.close();
                    if (b) {
                        inputStream.close();
                    }
                    bufferedWriter.close();
                    throw sb2;
                }
                bufferedWriter.write(sb2.toString());
                string = sb2.toString();
                if (s != null) {
                    ((BufferedReader)s).close();
                }
                inputStream2.close();
                if (b) {
                    inputStream.close();
                }
                bufferedWriter.close();
                return string;
            }
            finally {
                s = line;
                continue;
            }
            break;
        }
    }
    
    public static String createErrorMessage(final String s, final String s2) {
        return String.format("%s Failure occurred during initiation at: %s", s, s2);
    }
    
    public static int dpToPx(final long n) {
        return (int)(n * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }
    
    public static String encodeString(String replace) {
        try {
            replace = URLEncoder.encode(replace, "UTF-8").replace("+", "%20");
            return replace;
        }
        catch (UnsupportedEncodingException ex) {
            return "";
        }
    }
    
    public static byte[] encrypt(final String s) {
        MessageDigest messageDigest = null;
        MessageDigest instance = null;
        while (true) {
            try {
                final MessageDigest messageDigest2 = messageDigest = (instance = MessageDigest.getInstance("SHA-1"));
                messageDigest2.reset();
                instance = messageDigest2;
                messageDigest = messageDigest2;
                messageDigest2.update(s.getBytes("UTF-8"));
                instance = messageDigest2;
                if (instance != null) {
                    return instance.digest();
                }
            }
            catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
                continue;
            }
            catch (UnsupportedEncodingException ex2) {
                ex2.printStackTrace();
                instance = messageDigest;
                continue;
            }
            break;
        }
        return null;
    }
    
    public static int generateViewId() {
        if (Build$VERSION.SDK_INT < 17) {
            return generateViewIdForOldOS();
        }
        return View.generateViewId();
    }
    
    private static int generateViewIdForOldOS() {
        int value;
        int n;
        do {
            value = SDKUtils.sNextGeneratedId.get();
            if ((n = value + 1) > 16777215) {
                n = 1;
            }
        } while (!SDKUtils.sNextGeneratedId.compareAndSet(value, n));
        return value;
    }
    
    public static int getActivityUIFlags(final boolean b) {
        int n = 0;
        if (Build$VERSION.SDK_INT >= 14) {
            n = 2;
        }
        int n2 = n;
        if (Build$VERSION.SDK_INT >= 16) {
            n2 = (n | 0x704);
        }
        int n3 = n2;
        if (Build$VERSION.SDK_INT >= 19) {
            n3 = n2;
            if (b) {
                n3 = (n2 | 0x1000);
            }
        }
        return n3;
    }
    
    public static String getAdvertiserId() {
        return SDKUtils.mAdvertisingId;
    }
    
    public static String getControllerConfig() {
        return SDKUtils.mControllerConfig;
    }
    
    public static JSONObject getControllerConfigAsJSONObject() {
        final String controllerConfig = getControllerConfig();
        try {
            return new JSONObject(controllerConfig);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return new JSONObject();
        }
    }
    
    public static String getControllerUrl() {
        if (!TextUtils.isEmpty((CharSequence)SDKUtils.mControllerUrl)) {
            return SDKUtils.mControllerUrl;
        }
        return "";
    }
    
    public static Long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    public static int getDebugMode() {
        return SDKUtils.mDebugMode;
    }
    
    public static String getFileName(String encode) {
        final String[] split = encode.split(File.separator);
        encode = split[split.length - 1].split("\\?")[0];
        try {
            encode = URLEncoder.encode(encode, "UTF-8");
            return encode;
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Object getIInAppBillingServiceClass(final IBinder binder) {
        final Object o = null;
        try {
            return Class.forName("com.android.vending.billing.IInAppBillingService$Stub").getMethod("asInterface", IBinder.class).invoke(null, binder);
        }
        catch (ClassNotFoundException ex) {
            Object invoke = o;
            if (ex == null) {
                return invoke;
            }
            if (ex.getMessage() != null) {
                Logger.i(SDKUtils.TAG, ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            invoke = o;
            if (ex.getCause() != null) {
                Logger.i(SDKUtils.TAG, ex.getClass().getSimpleName() + ": " + ex.getCause());
                return null;
            }
            return invoke;
        }
        catch (NoSuchMethodException ex2) {
            Object invoke = o;
            if (ex2 == null) {
                return invoke;
            }
            if (ex2.getMessage() != null) {
                Logger.i(SDKUtils.TAG, ex2.getClass().getSimpleName() + ": " + ex2.getMessage());
            }
            invoke = o;
            if (ex2.getCause() != null) {
                Logger.i(SDKUtils.TAG, ex2.getClass().getSimpleName() + ": " + ex2.getCause());
                return null;
            }
            return invoke;
        }
        catch (IllegalAccessException ex3) {
            Object invoke = o;
            if (ex3 == null) {
                return invoke;
            }
            if (ex3.getMessage() != null) {
                Logger.i(SDKUtils.TAG, ex3.getClass().getSimpleName() + ": " + ex3.getMessage());
            }
            invoke = o;
            if (ex3.getCause() != null) {
                Logger.i(SDKUtils.TAG, ex3.getClass().getSimpleName() + ": " + ex3.getCause());
                return null;
            }
            return invoke;
        }
        catch (IllegalArgumentException ex4) {
            Object invoke = o;
            if (ex4 == null) {
                return invoke;
            }
            if (ex4.getMessage() != null) {
                Logger.i(SDKUtils.TAG, ex4.getClass().getSimpleName() + ": " + ex4.getMessage());
            }
            invoke = o;
            if (ex4.getCause() != null) {
                Logger.i(SDKUtils.TAG, ex4.getClass().getSimpleName() + ": " + ex4.getCause());
                return null;
            }
            return invoke;
        }
        catch (InvocationTargetException ex5) {
            Object invoke = o;
            if (ex5 == null) {
                return invoke;
            }
            if (ex5.getMessage() != null) {
                Logger.i(SDKUtils.TAG, ex5.getClass().getSimpleName() + ": " + ex5.getMessage());
            }
            invoke = o;
            if (ex5.getCause() != null) {
                Logger.i(SDKUtils.TAG, ex5.getClass().getSimpleName() + ": " + ex5.getCause());
                return null;
            }
            return invoke;
        }
        finally {
            if (false) {
                throw new NullPointerException();
            }
        }
    }
    
    public static String getMD5(String s) {
        try {
            for (s = new BigInteger(1, MessageDigest.getInstance("MD5").digest(s.getBytes())).toString(16); s.length() < 32; s = "0" + s) {}
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        return s;
    }
    
    public static JSONObject getOrientation(final Context context) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orientation", (Object)translateOrientation(DeviceStatus.getDeviceOrientation(context)));
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
    
    public static SSAEnums.ProductType getProductType(final String s) {
        Enum<SSAEnums.ProductType> rewardedVideo = null;
        if (s.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
            rewardedVideo = SSAEnums.ProductType.RewardedVideo;
        }
        else {
            if (s.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
                return SSAEnums.ProductType.Interstitial;
            }
            if (s.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
                return SSAEnums.ProductType.OfferWall;
            }
        }
        return (SSAEnums.ProductType)rewardedVideo;
    }
    
    public static String getSDKVersion() {
        return "5.58";
    }
    
    public static String getTesterParameters() {
        return SDKUtils.mUserGroup;
    }
    
    public static String getValueFromJsonObject(String string, final String s) {
        try {
            string = new JSONObject(string).getString(s);
            return string;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static boolean isApplicationVisible(final Context context) {
        final String packageName = context.getPackageName();
        for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : ((ActivityManager)context.getSystemService("activity")).getRunningAppProcesses()) {
            if (activityManager$RunningAppProcessInfo.processName.equalsIgnoreCase(packageName) && activityManager$RunningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isExternalStorageAvailable() {
        final String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }
    
    public static boolean isLimitAdTrackingEnabled() {
        return SDKUtils.mIsLimitedTrackingEnabled;
    }
    
    public static void loadGoogleAdvertiserInfo(final Context context) {
        try {
            final String[] advertisingIdInfo = DeviceStatus.getAdvertisingIdInfo(context);
            SDKUtils.mAdvertisingId = advertisingIdInfo[0];
            SDKUtils.mIsLimitedTrackingEnabled = Boolean.valueOf(advertisingIdInfo[1]);
        }
        catch (Exception ex) {
            if (ex == null) {
                return;
            }
            if (ex.getMessage() != null) {
                Logger.i(SDKUtils.TAG, ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            if (ex.getCause() != null) {
                Logger.i(SDKUtils.TAG, ex.getClass().getSimpleName() + ": " + ex.getCause());
            }
        }
        finally {
            if (false) {
                throw new NullPointerException();
            }
        }
    }
    
    public static int pxToDp(final long n) {
        return (int)(n / Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }
    
    public static String queryingPurchasedItems(final Object p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   org/json/JSONArray.<init>:()V
        //     7: astore_3       
        //     8: aload_0        
        //     9: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    12: ldc_w           "getPurchases"
        //    15: iconst_4       
        //    16: anewarray       Ljava/lang/Class;
        //    19: dup            
        //    20: iconst_0       
        //    21: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //    24: aastore        
        //    25: dup            
        //    26: iconst_1       
        //    27: ldc             Ljava/lang/String;.class
        //    29: aastore        
        //    30: dup            
        //    31: iconst_2       
        //    32: ldc             Ljava/lang/String;.class
        //    34: aastore        
        //    35: dup            
        //    36: iconst_3       
        //    37: ldc             Ljava/lang/String;.class
        //    39: aastore        
        //    40: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    43: aload_0        
        //    44: iconst_4       
        //    45: anewarray       Ljava/lang/Object;
        //    48: dup            
        //    49: iconst_0       
        //    50: iconst_3       
        //    51: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    54: aastore        
        //    55: dup            
        //    56: iconst_1       
        //    57: aload_1        
        //    58: aastore        
        //    59: dup            
        //    60: iconst_2       
        //    61: ldc_w           "inapp"
        //    64: aastore        
        //    65: dup            
        //    66: iconst_3       
        //    67: aconst_null    
        //    68: aastore        
        //    69: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    72: astore          6
        //    74: aload           6
        //    76: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    79: ldc_w           "getInt"
        //    82: iconst_1       
        //    83: anewarray       Ljava/lang/Class;
        //    86: dup            
        //    87: iconst_0       
        //    88: ldc             Ljava/lang/String;.class
        //    90: aastore        
        //    91: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    94: astore_0       
        //    95: aload           6
        //    97: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   100: ldc_w           "getStringArrayList"
        //   103: iconst_1       
        //   104: anewarray       Ljava/lang/Class;
        //   107: dup            
        //   108: iconst_0       
        //   109: ldc             Ljava/lang/String;.class
        //   111: aastore        
        //   112: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   115: astore          4
        //   117: aload           6
        //   119: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   122: ldc_w           "getString"
        //   125: iconst_1       
        //   126: anewarray       Ljava/lang/Class;
        //   129: dup            
        //   130: iconst_0       
        //   131: ldc             Ljava/lang/String;.class
        //   133: aastore        
        //   134: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   137: astore          5
        //   139: aload_0        
        //   140: aload           6
        //   142: iconst_1       
        //   143: anewarray       Ljava/lang/Object;
        //   146: dup            
        //   147: iconst_0       
        //   148: ldc_w           "RESPONSE_CODE"
        //   151: aastore        
        //   152: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   155: checkcast       Ljava/lang/Integer;
        //   158: invokevirtual   java/lang/Integer.intValue:()I
        //   161: ifne            371
        //   164: aload           4
        //   166: aload           6
        //   168: iconst_1       
        //   169: anewarray       Ljava/lang/Object;
        //   172: dup            
        //   173: iconst_0       
        //   174: ldc_w           "INAPP_PURCHASE_ITEM_LIST"
        //   177: aastore        
        //   178: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   181: checkcast       Ljava/util/ArrayList;
        //   184: astore_0       
        //   185: aload           4
        //   187: aload           6
        //   189: iconst_1       
        //   190: anewarray       Ljava/lang/Object;
        //   193: dup            
        //   194: iconst_0       
        //   195: ldc_w           "INAPP_PURCHASE_DATA_LIST"
        //   198: aastore        
        //   199: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   202: checkcast       Ljava/util/ArrayList;
        //   205: astore_1       
        //   206: aload           4
        //   208: aload           6
        //   210: iconst_1       
        //   211: anewarray       Ljava/lang/Object;
        //   214: dup            
        //   215: iconst_0       
        //   216: ldc_w           "INAPP_DATA_SIGNATURE_LIST"
        //   219: aastore        
        //   220: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   223: checkcast       Ljava/util/ArrayList;
        //   226: astore          4
        //   228: aload           5
        //   230: aload           6
        //   232: iconst_1       
        //   233: anewarray       Ljava/lang/Object;
        //   236: dup            
        //   237: iconst_0       
        //   238: ldc_w           "INAPP_CONTINUATION_TOKEN"
        //   241: aastore        
        //   242: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   245: checkcast       Ljava/lang/String;
        //   248: astore          5
        //   250: iconst_0       
        //   251: istore_2       
        //   252: iload_2        
        //   253: aload_1        
        //   254: invokevirtual   java/util/ArrayList.size:()I
        //   257: if_icmpge       371
        //   260: aload_1        
        //   261: iload_2        
        //   262: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   265: checkcast       Ljava/lang/String;
        //   268: astore          5
        //   270: aload           4
        //   272: iload_2        
        //   273: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   276: checkcast       Ljava/lang/String;
        //   279: astore          7
        //   281: aload_0        
        //   282: iload_2        
        //   283: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   286: checkcast       Ljava/lang/String;
        //   289: astore          6
        //   291: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   294: aload           5
        //   296: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   299: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   302: aload           7
        //   304: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   307: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   310: aload           6
        //   312: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   315: new             Lorg/json/JSONObject;
        //   318: dup            
        //   319: invokespecial   org/json/JSONObject.<init>:()V
        //   322: astore          6
        //   324: aload           6
        //   326: ldc_w           "purchaseData"
        //   329: aload           5
        //   331: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   334: pop            
        //   335: aload           6
        //   337: ldc_w           "signature"
        //   340: aload           5
        //   342: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   345: pop            
        //   346: aload           6
        //   348: ldc_w           "sku"
        //   351: aload           5
        //   353: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   356: pop            
        //   357: aload_3        
        //   358: aload           6
        //   360: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   363: pop            
        //   364: iload_2        
        //   365: iconst_1       
        //   366: iadd           
        //   367: istore_2       
        //   368: goto            252
        //   371: iconst_0       
        //   372: ifeq            383
        //   375: new             Ljava/lang/NullPointerException;
        //   378: dup            
        //   379: invokespecial   java/lang/NullPointerException.<init>:()V
        //   382: athrow         
        //   383: aload_3        
        //   384: invokevirtual   org/json/JSONArray.toString:()Ljava/lang/String;
        //   387: areturn        
        //   388: astore_0       
        //   389: aload_0        
        //   390: ifnull          383
        //   393: aload_0        
        //   394: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   397: ifnull          439
        //   400: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   403: new             Ljava/lang/StringBuilder;
        //   406: dup            
        //   407: invokespecial   java/lang/StringBuilder.<init>:()V
        //   410: aload_0        
        //   411: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   414: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   420: ldc_w           ": "
        //   423: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   426: aload_0        
        //   427: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   430: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   433: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   436: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   439: aload_0        
        //   440: invokevirtual   java/lang/Exception.getCause:()Ljava/lang/Throwable;
        //   443: ifnull          383
        //   446: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   449: new             Ljava/lang/StringBuilder;
        //   452: dup            
        //   453: invokespecial   java/lang/StringBuilder.<init>:()V
        //   456: aload_0        
        //   457: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   460: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   463: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   466: ldc_w           ": "
        //   469: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   472: aload_0        
        //   473: invokevirtual   java/lang/Exception.getCause:()Ljava/lang/Throwable;
        //   476: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   479: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   482: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   485: goto            383
        //   488: astore_0       
        //   489: aload_0        
        //   490: ifnull          383
        //   493: aload_0        
        //   494: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   497: ifnull          539
        //   500: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   503: new             Ljava/lang/StringBuilder;
        //   506: dup            
        //   507: invokespecial   java/lang/StringBuilder.<init>:()V
        //   510: aload_0        
        //   511: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   514: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   517: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   520: ldc_w           ": "
        //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   526: aload_0        
        //   527: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   530: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   533: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   536: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   539: aload_0        
        //   540: invokevirtual   java/lang/Exception.getCause:()Ljava/lang/Throwable;
        //   543: ifnull          383
        //   546: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   549: new             Ljava/lang/StringBuilder;
        //   552: dup            
        //   553: invokespecial   java/lang/StringBuilder.<init>:()V
        //   556: aload_0        
        //   557: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   560: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   563: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   566: ldc_w           ": "
        //   569: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   572: aload_0        
        //   573: invokevirtual   java/lang/Exception.getCause:()Ljava/lang/Throwable;
        //   576: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   579: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   582: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   585: goto            383
        //   588: astore_0       
        //   589: aload_0        
        //   590: ifnull          383
        //   593: aload_0        
        //   594: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   597: ifnull          639
        //   600: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   603: new             Ljava/lang/StringBuilder;
        //   606: dup            
        //   607: invokespecial   java/lang/StringBuilder.<init>:()V
        //   610: aload_0        
        //   611: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   614: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   617: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   620: ldc_w           ": "
        //   623: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   626: aload_0        
        //   627: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   630: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   633: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   636: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   639: aload_0        
        //   640: invokevirtual   java/lang/Exception.getCause:()Ljava/lang/Throwable;
        //   643: ifnull          383
        //   646: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   649: new             Ljava/lang/StringBuilder;
        //   652: dup            
        //   653: invokespecial   java/lang/StringBuilder.<init>:()V
        //   656: aload_0        
        //   657: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   660: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   663: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   666: ldc_w           ": "
        //   669: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   672: aload_0        
        //   673: invokevirtual   java/lang/Exception.getCause:()Ljava/lang/Throwable;
        //   676: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   679: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   682: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   685: goto            383
        //   688: astore_0       
        //   689: aload_0        
        //   690: ifnull          383
        //   693: aload_0        
        //   694: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   697: ifnull          739
        //   700: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   703: new             Ljava/lang/StringBuilder;
        //   706: dup            
        //   707: invokespecial   java/lang/StringBuilder.<init>:()V
        //   710: aload_0        
        //   711: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   714: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   717: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   720: ldc_w           ": "
        //   723: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   726: aload_0        
        //   727: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   730: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   733: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   736: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   739: aload_0        
        //   740: invokevirtual   java/lang/Exception.getCause:()Ljava/lang/Throwable;
        //   743: ifnull          383
        //   746: getstatic       com/ironsource/sdk/utils/SDKUtils.TAG:Ljava/lang/String;
        //   749: new             Ljava/lang/StringBuilder;
        //   752: dup            
        //   753: invokespecial   java/lang/StringBuilder.<init>:()V
        //   756: aload_0        
        //   757: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   760: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //   763: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   766: ldc_w           ": "
        //   769: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   772: aload_0        
        //   773: invokevirtual   java/lang/Exception.getCause:()Ljava/lang/Throwable;
        //   776: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   779: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   782: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   785: goto            383
        //   788: astore_0       
        //   789: iconst_0       
        //   790: ifeq            801
        //   793: new             Ljava/lang/NullPointerException;
        //   796: dup            
        //   797: invokespecial   java/lang/NullPointerException.<init>:()V
        //   800: athrow         
        //   801: aload_0        
        //   802: athrow         
        //   803: astore          5
        //   805: goto            364
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  8      250    388    488    Ljava/lang/NoSuchMethodException;
        //  8      250    488    588    Ljava/lang/IllegalAccessException;
        //  8      250    588    688    Ljava/lang/IllegalArgumentException;
        //  8      250    688    788    Ljava/lang/reflect/InvocationTargetException;
        //  8      250    788    803    Any
        //  252    324    388    488    Ljava/lang/NoSuchMethodException;
        //  252    324    488    588    Ljava/lang/IllegalAccessException;
        //  252    324    588    688    Ljava/lang/IllegalArgumentException;
        //  252    324    688    788    Ljava/lang/reflect/InvocationTargetException;
        //  252    324    788    803    Any
        //  324    364    803    808    Lorg/json/JSONException;
        //  324    364    388    488    Ljava/lang/NoSuchMethodException;
        //  324    364    488    588    Ljava/lang/IllegalAccessException;
        //  324    364    588    688    Ljava/lang/IllegalArgumentException;
        //  324    364    688    788    Ljava/lang/reflect/InvocationTargetException;
        //  324    364    788    803    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0364:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void setControllerConfig(final String mControllerConfig) {
        SDKUtils.mControllerConfig = mControllerConfig;
    }
    
    public static void setControllerUrl(final String mControllerUrl) {
        SDKUtils.mControllerUrl = mControllerUrl;
    }
    
    public static void setDebugMode(final int mDebugMode) {
        SDKUtils.mDebugMode = mDebugMode;
    }
    
    public static void setTesterParameters(final String mUserGroup) {
        SDKUtils.mUserGroup = mUserGroup;
    }
    
    public static void showNoInternetDialog(final Context context) {
        new AlertDialog$Builder(context).setMessage((CharSequence)"No Internet Connection").setPositiveButton((CharSequence)"Ok", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        }).show();
    }
    
    public static String translateOrientation(final int n) {
        switch (n) {
            default: {
                return "none";
            }
            case 2: {
                return "landscape";
            }
            case 1: {
                return "portrait";
            }
        }
    }
    
    public static String translateRequestedOrientation(final int n) {
        switch (n) {
            default: {
                return "none";
            }
            case 0:
            case 6:
            case 8:
            case 11: {
                return "landscape";
            }
            case 1:
            case 7:
            case 9:
            case 12: {
                return "portrait";
            }
        }
    }
}
