// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.io.ObjectInputStream;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.HashMap;
import java.security.MessageDigest;
import java.util.Map;
import android.content.res.Configuration;
import android.text.TextUtils;
import java.util.List;
import java.util.ArrayList;
import android.telephony.TelephonyManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.provider.Settings$Secure;
import android.content.ContentResolver;
import android.net.ConnectivityManager;
import java.util.UUID;
import java.math.BigInteger;
import android.content.Context;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class Util
{
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'Z";
    public static final DecimalFormat SecondsDisplayFormat;
    public static final SimpleDateFormat dateFormatter;
    private static final String fieldReadErrorMessage = "Unable to read '%s' field in migration device with message (%s)";
    
    static {
        SecondsDisplayFormat = new DecimalFormat("0.0");
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'Z", Locale.US);
    }
    
    public static boolean checkPermission(final Context context, final String s) {
        return context.checkCallingOrSelfPermission(s) == 0;
    }
    
    public static String convertToHex(final byte[] array) {
        return formatString("%0" + (array.length << 1) + "x", new BigInteger(1, array));
    }
    
    protected static String createUuid() {
        return UUID.randomUUID().toString();
    }
    
    public static boolean equalBoolean(final Boolean b, final Boolean b2) {
        return equalObject(b, b2);
    }
    
    public static boolean equalEnum(final Enum enum1, final Enum enum2) {
        return equalObject(enum1, enum2);
    }
    
    public static boolean equalInt(final Integer n, final Integer n2) {
        return equalObject(n, n2);
    }
    
    public static boolean equalLong(final Long n, final Long n2) {
        return equalObject(n, n2);
    }
    
    public static boolean equalObject(final Object o, final Object o2) {
        if (o == null || o2 == null) {
            return o == null && o2 == null;
        }
        return o.equals(o2);
    }
    
    public static boolean equalString(final String s, final String s2) {
        return equalObject(s, s2);
    }
    
    public static boolean equalsDouble(final Double n, final Double n2) {
        if (n == null || n2 == null) {
            if (n != null || n2 != null) {
                return false;
            }
        }
        else if (Double.doubleToLongBits(n) != Double.doubleToLongBits(n2)) {
            return false;
        }
        return true;
    }
    
    public static String formatString(final String s, final Object... array) {
        return String.format(Locale.US, s, array);
    }
    
    public static String getAndroidId(final Context context) {
        return Reflection.getAndroidId(context);
    }
    
    public static int getConnectivityType(final Context context) {
        try {
            return ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo().getType();
        }
        catch (Exception ex) {
            getLogger().warn("Couldn't read connectivity type (%s)", ex.getMessage());
            return -1;
        }
    }
    
    public static String getCpuAbi() {
        return Reflection.getCpuAbi();
    }
    
    public static String getFireAdvertisingId(final ContentResolver contentResolver) {
        if (contentResolver == null) {
            return null;
        }
        try {
            return Settings$Secure.getString(contentResolver, "advertising_id");
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Boolean getFireTrackingEnabled(final ContentResolver contentResolver) {
        try {
            return Settings$Secure.getInt(contentResolver, "limit_ad_tracking") == 0;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void getGoogleAdId(final Context context, final OnDeviceIdsRead onDeviceIdsRead) {
        final ILogger logger = AdjustFactory.getLogger();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            logger.debug("GoogleAdId being read in the background", new Object[0]);
            final String playAdId = getPlayAdId(context);
            logger.debug("GoogleAdId read " + playAdId, new Object[0]);
            onDeviceIdsRead.onGoogleAdIdRead(playAdId);
            return;
        }
        logger.debug("GoogleAdId being read in the foreground", new Object[0]);
        new AsyncTask<Context, Void, String>() {
            protected String doInBackground(final Context... array) {
                final ILogger logger = AdjustFactory.getLogger();
                final String playAdId = Util.getPlayAdId(array[0]);
                logger.debug("GoogleAdId read " + playAdId, new Object[0]);
                return playAdId;
            }
            
            protected void onPostExecute(final String s) {
                AdjustFactory.getLogger();
                onDeviceIdsRead.onGoogleAdIdRead(s);
            }
        }.execute((Object[])new Context[] { context });
    }
    
    public static String getIMEI(final TelephonyManager telephonyManager) {
        return Reflection.getImei(telephonyManager);
    }
    
    public static String getIMEI(final TelephonyManager telephonyManager, final int n) {
        return Reflection.getImei(telephonyManager, n);
    }
    
    public static String getIMEIs(final TelephonyManager telephonyManager) {
        final ArrayList<String> list = new ArrayList<String>();
        tryAddToStringList(list, getIMEI(telephonyManager, 0));
        for (int n = 1; n < 10 && tryAddToStringList(list, getIMEI(telephonyManager, n)); ++n) {}
        tryAddToStringList(list, getIMEI(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join((CharSequence)",", (Iterable)list);
    }
    
    public static Locale getLocale(final Configuration configuration) {
        final Locale localeFromLocaleList = Reflection.getLocaleFromLocaleList(configuration);
        if (localeFromLocaleList != null) {
            return localeFromLocaleList;
        }
        return Reflection.getLocaleFromField(configuration);
    }
    
    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }
    
    public static String getMEID(final TelephonyManager telephonyManager) {
        return Reflection.getMeid(telephonyManager);
    }
    
    public static String getMEID(final TelephonyManager telephonyManager, final int n) {
        return Reflection.getMeid(telephonyManager, n);
    }
    
    public static String getMEIDs(final TelephonyManager telephonyManager) {
        final ArrayList<String> list = new ArrayList<String>();
        tryAddToStringList(list, getMEID(telephonyManager, 0));
        for (int n = 1; n < 10 && tryAddToStringList(list, getMEID(telephonyManager, n)); ++n) {}
        tryAddToStringList(list, getMEID(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join((CharSequence)",", (Iterable)list);
    }
    
    public static String getMacAddress(final Context context) {
        return Reflection.getMacAddress(context);
    }
    
    public static String getMcc(final Context context) {
        try {
            final String networkOperator = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperator();
            if (TextUtils.isEmpty((CharSequence)networkOperator)) {
                AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MCC", new Object[0]);
                return null;
            }
            return networkOperator.substring(0, 3);
        }
        catch (Exception ex) {
            AdjustFactory.getLogger().warn("Couldn't return mcc", new Object[0]);
            return null;
        }
    }
    
    public static String getMnc(final Context context) {
        try {
            final String networkOperator = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperator();
            if (TextUtils.isEmpty((CharSequence)networkOperator)) {
                AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MNC", new Object[0]);
                return null;
            }
            return networkOperator.substring(3);
        }
        catch (Exception ex) {
            AdjustFactory.getLogger().warn("Couldn't return mnc", new Object[0]);
            return null;
        }
    }
    
    public static int getNetworkType(final Context context) {
        try {
            return ((TelephonyManager)context.getSystemService("phone")).getNetworkType();
        }
        catch (Exception ex) {
            getLogger().warn("Couldn't read network type (%s)", ex.getMessage());
            return -1;
        }
    }
    
    public static String getPlayAdId(final Context context) {
        return Reflection.getPlayAdId(context);
    }
    
    public static Map<String, String> getPluginKeys(final Context context) {
        return Reflection.getPluginKeys(context);
    }
    
    public static String getReasonString(final String s, final Throwable t) {
        if (t != null) {
            return formatString("%s: %s", s, t);
        }
        return formatString("%s", s);
    }
    
    public static String[] getSupportedAbis() {
        return Reflection.getSupportedAbis();
    }
    
    public static String getTelephonyId(final TelephonyManager telephonyManager) {
        return Reflection.getTelephonyId(telephonyManager);
    }
    
    public static String getTelephonyId(final TelephonyManager telephonyManager, final int n) {
        return Reflection.getTelephonyId(telephonyManager, n);
    }
    
    public static String getTelephonyIds(final TelephonyManager telephonyManager) {
        final ArrayList<String> list = new ArrayList<String>();
        tryAddToStringList(list, getTelephonyId(telephonyManager, 0));
        for (int n = 1; n < 10 && tryAddToStringList(list, getTelephonyId(telephonyManager, n)); ++n) {}
        tryAddToStringList(list, getTelephonyId(telephonyManager, Integer.MAX_VALUE));
        return TextUtils.join((CharSequence)",", (Iterable)list);
    }
    
    public static String getVmInstructionSet() {
        return Reflection.getVmInstructionSet();
    }
    
    public static long getWaitingTime(final int n, final BackoffStrategy backoffStrategy) {
        if (n < backoffStrategy.minRetries) {
            return 0L;
        }
        return (long)(Math.min((long)Math.pow(2.0, n - backoffStrategy.minRetries) * backoffStrategy.milliSecondMultiplier, backoffStrategy.maxWait) * randomInRange(backoffStrategy.minRange, backoffStrategy.maxRange));
    }
    
    public static String hash(String convertToHex, final String s) {
        try {
            final byte[] bytes = convertToHex.getBytes("UTF-8");
            final MessageDigest instance = MessageDigest.getInstance(s);
            instance.update(bytes, 0, bytes.length);
            convertToHex = convertToHex(instance.digest());
            return convertToHex;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static int hashBoolean(final Boolean b) {
        if (b == null) {
            return 0;
        }
        return b.hashCode();
    }
    
    public static int hashEnum(final Enum enum1) {
        if (enum1 == null) {
            return 0;
        }
        return enum1.hashCode();
    }
    
    public static int hashLong(final Long n) {
        if (n == null) {
            return 0;
        }
        return n.hashCode();
    }
    
    public static int hashObject(final Object o) {
        if (o == null) {
            return 0;
        }
        return o.hashCode();
    }
    
    public static int hashString(final String s) {
        if (s == null) {
            return 0;
        }
        return s.hashCode();
    }
    
    public static Boolean isPlayTrackingEnabled(final Context context) {
        return Reflection.isPlayTrackingEnabled(context);
    }
    
    public static boolean isValidParameter(final String s, final String s2, final String s3) {
        if (s == null) {
            getLogger().error("%s parameter %s is missing", s3, s2);
            return false;
        }
        if (s.equals("")) {
            getLogger().error("%s parameter %s is empty", s3, s2);
            return false;
        }
        return true;
    }
    
    public static String md5(final String s) {
        return hash(s, "MD5");
    }
    
    public static Map<String, String> mergeParameters(final Map<String, String> map, final Map<String, String> map2, final String s) {
        if (map == null) {
            return map2;
        }
        if (map2 == null) {
            return map;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(map);
        final ILogger logger = getLogger();
        for (final Map.Entry<String, String> entry : map2.entrySet()) {
            final String s2 = hashMap.put(entry.getKey(), entry.getValue());
            if (s2 != null) {
                logger.warn("Key %s with value %s from %s parameter was replaced by value %s", entry.getKey(), s2, s, entry.getValue());
            }
        }
        return hashMap;
    }
    
    public static String quote(final String s) {
        String s2;
        if (s == null) {
            s2 = null;
        }
        else {
            s2 = s;
            if (Pattern.compile("\\s").matcher(s).find()) {
                return formatString("'%s'", s);
            }
        }
        return s2;
    }
    
    private static double randomInRange(final double n, final double n2) {
        return new Random().nextDouble() * (n2 - n) + n;
    }
    
    public static boolean readBooleanField(final ObjectInputStream.GetField getField, final String s, final boolean b) {
        try {
            return getField.get(s, b);
        }
        catch (Exception ex) {
            getLogger().debug("Unable to read '%s' field in migration device with message (%s)", s, ex.getMessage());
            return b;
        }
    }
    
    public static int readIntField(final ObjectInputStream.GetField getField, final String s, final int n) {
        try {
            return getField.get(s, n);
        }
        catch (Exception ex) {
            getLogger().debug("Unable to read '%s' field in migration device with message (%s)", s, ex.getMessage());
            return n;
        }
    }
    
    public static long readLongField(final ObjectInputStream.GetField getField, final String s, final long n) {
        try {
            return getField.get(s, n);
        }
        catch (Exception ex) {
            getLogger().debug("Unable to read '%s' field in migration device with message (%s)", s, ex.getMessage());
            return n;
        }
    }
    
    public static <T> T readObject(final Context p0, final String p1, final String p2, final Class<T> p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          7
        //     3: aconst_null    
        //     4: astore          5
        //     6: aconst_null    
        //     7: astore          10
        //     9: aconst_null    
        //    10: astore          11
        //    12: aconst_null    
        //    13: astore          9
        //    15: aconst_null    
        //    16: astore          8
        //    18: aconst_null    
        //    19: astore          12
        //    21: aload           10
        //    23: astore          4
        //    25: aload           9
        //    27: astore          6
        //    29: aload_0        
        //    30: aload_1        
        //    31: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    34: astore_0       
        //    35: aload_0        
        //    36: astore          7
        //    38: aload           7
        //    40: astore          5
        //    42: aload           10
        //    44: astore          4
        //    46: aload           9
        //    48: astore          6
        //    50: new             Ljava/io/BufferedInputStream;
        //    53: dup            
        //    54: aload_0        
        //    55: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    58: astore_0       
        //    59: aload_0        
        //    60: astore          7
        //    62: aload           7
        //    64: astore          5
        //    66: aload           10
        //    68: astore          4
        //    70: aload           9
        //    72: astore          6
        //    74: new             Ljava/io/ObjectInputStream;
        //    77: dup            
        //    78: aload_0        
        //    79: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    82: astore          13
        //    84: aload           13
        //    86: astore          9
        //    88: aload           12
        //    90: astore_0       
        //    91: aload           9
        //    93: astore          5
        //    95: aload           10
        //    97: astore          4
        //    99: aload           11
        //   101: astore_1       
        //   102: aload_3        
        //   103: aload           13
        //   105: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //   108: invokevirtual   java/lang/Class.cast:(Ljava/lang/Object;)Ljava/lang/Object;
        //   111: astore_3       
        //   112: aload_3        
        //   113: astore_0       
        //   114: aload           9
        //   116: astore          5
        //   118: aload_3        
        //   119: astore          4
        //   121: aload_3        
        //   122: astore_1       
        //   123: aload_3        
        //   124: astore          8
        //   126: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   129: ldc_w           "Read %s: %s"
        //   132: iconst_2       
        //   133: anewarray       Ljava/lang/Object;
        //   136: dup            
        //   137: iconst_0       
        //   138: aload_2        
        //   139: aastore        
        //   140: dup            
        //   141: iconst_1       
        //   142: aload_3        
        //   143: aastore        
        //   144: invokeinterface com/adjust/sdk/ILogger.debug:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   149: aload_3        
        //   150: astore          4
        //   152: aload           9
        //   154: astore          5
        //   156: aload           5
        //   158: ifnull          168
        //   161: aload           5
        //   163: invokeinterface java/io/Closeable.close:()V
        //   168: aload           4
        //   170: areturn        
        //   171: astore_1       
        //   172: aload           9
        //   174: astore          5
        //   176: aload_0        
        //   177: astore          4
        //   179: aload           9
        //   181: astore          7
        //   183: aload_0        
        //   184: astore          6
        //   186: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   189: ldc_w           "Failed to find %s class (%s)"
        //   192: iconst_2       
        //   193: anewarray       Ljava/lang/Object;
        //   196: dup            
        //   197: iconst_0       
        //   198: aload_2        
        //   199: aastore        
        //   200: dup            
        //   201: iconst_1       
        //   202: aload_1        
        //   203: invokevirtual   java/lang/ClassNotFoundException.getMessage:()Ljava/lang/String;
        //   206: aastore        
        //   207: invokeinterface com/adjust/sdk/ILogger.error:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   212: aload           9
        //   214: astore          5
        //   216: aload_0        
        //   217: astore          4
        //   219: goto            156
        //   222: astore_0       
        //   223: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   226: ldc_w           "%s file not found"
        //   229: iconst_1       
        //   230: anewarray       Ljava/lang/Object;
        //   233: dup            
        //   234: iconst_0       
        //   235: aload_2        
        //   236: aastore        
        //   237: invokeinterface com/adjust/sdk/ILogger.debug:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   242: goto            156
        //   245: astore_0       
        //   246: aload           9
        //   248: astore          5
        //   250: aload_1        
        //   251: astore          4
        //   253: aload           9
        //   255: astore          7
        //   257: aload_1        
        //   258: astore          6
        //   260: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   263: ldc_w           "Failed to cast %s object (%s)"
        //   266: iconst_2       
        //   267: anewarray       Ljava/lang/Object;
        //   270: dup            
        //   271: iconst_0       
        //   272: aload_2        
        //   273: aastore        
        //   274: dup            
        //   275: iconst_1       
        //   276: aload_0        
        //   277: invokevirtual   java/lang/ClassCastException.getMessage:()Ljava/lang/String;
        //   280: aastore        
        //   281: invokeinterface com/adjust/sdk/ILogger.error:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   286: aload           9
        //   288: astore          5
        //   290: aload_1        
        //   291: astore          4
        //   293: goto            156
        //   296: astore_0       
        //   297: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   300: ldc_w           "Failed to open %s file for reading (%s)"
        //   303: iconst_2       
        //   304: anewarray       Ljava/lang/Object;
        //   307: dup            
        //   308: iconst_0       
        //   309: aload_2        
        //   310: aastore        
        //   311: dup            
        //   312: iconst_1       
        //   313: aload_0        
        //   314: aastore        
        //   315: invokeinterface com/adjust/sdk/ILogger.error:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   320: aload           7
        //   322: astore          5
        //   324: aload           6
        //   326: astore          4
        //   328: goto            156
        //   331: astore_0       
        //   332: aload           9
        //   334: astore          5
        //   336: aload           8
        //   338: astore          4
        //   340: aload           9
        //   342: astore          7
        //   344: aload           8
        //   346: astore          6
        //   348: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   351: ldc_w           "Failed to read %s object (%s)"
        //   354: iconst_2       
        //   355: anewarray       Ljava/lang/Object;
        //   358: dup            
        //   359: iconst_0       
        //   360: aload_2        
        //   361: aastore        
        //   362: dup            
        //   363: iconst_1       
        //   364: aload_0        
        //   365: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   368: aastore        
        //   369: invokeinterface com/adjust/sdk/ILogger.error:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   374: aload           9
        //   376: astore          5
        //   378: aload           8
        //   380: astore          4
        //   382: goto            156
        //   385: astore_0       
        //   386: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   389: ldc_w           "Failed to close %s file for reading (%s)"
        //   392: iconst_2       
        //   393: anewarray       Ljava/lang/Object;
        //   396: dup            
        //   397: iconst_0       
        //   398: aload_2        
        //   399: aastore        
        //   400: dup            
        //   401: iconst_1       
        //   402: aload_0        
        //   403: aastore        
        //   404: invokeinterface com/adjust/sdk/ILogger.error:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   409: aload           4
        //   411: areturn        
        //    Signature:
        //  <T:Ljava/lang/Object;>(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class<TT;>;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  29     35     222    245    Ljava/io/FileNotFoundException;
        //  29     35     296    331    Ljava/lang/Exception;
        //  50     59     222    245    Ljava/io/FileNotFoundException;
        //  50     59     296    331    Ljava/lang/Exception;
        //  74     84     222    245    Ljava/io/FileNotFoundException;
        //  74     84     296    331    Ljava/lang/Exception;
        //  102    112    171    222    Ljava/lang/ClassNotFoundException;
        //  102    112    245    296    Ljava/lang/ClassCastException;
        //  102    112    331    385    Ljava/lang/Exception;
        //  102    112    222    245    Ljava/io/FileNotFoundException;
        //  126    149    171    222    Ljava/lang/ClassNotFoundException;
        //  126    149    245    296    Ljava/lang/ClassCastException;
        //  126    149    331    385    Ljava/lang/Exception;
        //  126    149    222    245    Ljava/io/FileNotFoundException;
        //  161    168    385    412    Ljava/lang/Exception;
        //  186    212    222    245    Ljava/io/FileNotFoundException;
        //  186    212    296    331    Ljava/lang/Exception;
        //  260    286    222    245    Ljava/io/FileNotFoundException;
        //  260    286    296    331    Ljava/lang/Exception;
        //  348    374    222    245    Ljava/io/FileNotFoundException;
        //  348    374    296    331    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0168:
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
    
    public static <T> T readObjectField(final ObjectInputStream.GetField getField, final String s, final T t) {
        try {
            return (T)getField.get(s, t);
        }
        catch (Exception ex) {
            getLogger().debug("Unable to read '%s' field in migration device with message (%s)", s, ex.getMessage());
            return t;
        }
    }
    
    public static String readStringField(final ObjectInputStream.GetField getField, final String s, final String s2) {
        return readObjectField(getField, s, s2);
    }
    
    public static void runInBackground(final Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            runnable.run();
            return;
        }
        new AsyncTask<Object, Void, Void>() {
            protected Void doInBackground(final Object... array) {
                ((Runnable)array[0]).run();
                return null;
            }
        }.execute(new Object[] { runnable });
    }
    
    public static String sha1(final String s) {
        return hash(s, "SHA-1");
    }
    
    public static String sha256(final String s) {
        return hash(s, "SHA-256");
    }
    
    public static boolean tryAddToStringList(final List<String> list, final String s) {
        return s != null && !list.contains(s) && list.add(s);
    }
    
    public static <T> void writeObject(final T p0, final Context p1, final String p2, final String p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_1        
        //     4: aload_2        
        //     5: iconst_0       
        //     6: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //     9: astore_1       
        //    10: aload_1        
        //    11: astore          4
        //    13: new             Ljava/io/BufferedOutputStream;
        //    16: dup            
        //    17: aload_1        
        //    18: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    21: astore_1       
        //    22: aload_1        
        //    23: astore          4
        //    25: new             Ljava/io/ObjectOutputStream;
        //    28: dup            
        //    29: aload_1        
        //    30: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    33: astore_2       
        //    34: aload_2        
        //    35: astore_1       
        //    36: aload_1        
        //    37: astore          4
        //    39: aload_2        
        //    40: aload_0        
        //    41: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    44: aload_1        
        //    45: astore          4
        //    47: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //    50: ldc_w           "Wrote %s: %s"
        //    53: iconst_2       
        //    54: anewarray       Ljava/lang/Object;
        //    57: dup            
        //    58: iconst_0       
        //    59: aload_3        
        //    60: aastore        
        //    61: dup            
        //    62: iconst_1       
        //    63: aload_0        
        //    64: aastore        
        //    65: invokeinterface com/adjust/sdk/ILogger.debug:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    70: aload_1        
        //    71: ifnull          80
        //    74: aload_1        
        //    75: invokeinterface java/io/Closeable.close:()V
        //    80: return         
        //    81: astore_0       
        //    82: aload_1        
        //    83: astore          4
        //    85: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //    88: ldc_w           "Failed to serialize %s"
        //    91: iconst_1       
        //    92: anewarray       Ljava/lang/Object;
        //    95: dup            
        //    96: iconst_0       
        //    97: aload_3        
        //    98: aastore        
        //    99: invokeinterface com/adjust/sdk/ILogger.error:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   104: goto            70
        //   107: astore_0       
        //   108: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   111: ldc_w           "Failed to open %s for writing (%s)"
        //   114: iconst_2       
        //   115: anewarray       Ljava/lang/Object;
        //   118: dup            
        //   119: iconst_0       
        //   120: aload_3        
        //   121: aastore        
        //   122: dup            
        //   123: iconst_1       
        //   124: aload_0        
        //   125: aastore        
        //   126: invokeinterface com/adjust/sdk/ILogger.error:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   131: aload           4
        //   133: astore_1       
        //   134: goto            70
        //   137: astore_0       
        //   138: invokestatic    com/adjust/sdk/Util.getLogger:()Lcom/adjust/sdk/ILogger;
        //   141: ldc_w           "Failed to close %s file for writing (%s)"
        //   144: iconst_2       
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: iconst_0       
        //   150: aload_3        
        //   151: aastore        
        //   152: dup            
        //   153: iconst_1       
        //   154: aload_0        
        //   155: aastore        
        //   156: invokeinterface com/adjust/sdk/ILogger.error:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   161: return         
        //    Signature:
        //  <T:Ljava/lang/Object;>(TT;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  3      10     107    137    Ljava/lang/Exception;
        //  13     22     107    137    Ljava/lang/Exception;
        //  25     34     107    137    Ljava/lang/Exception;
        //  39     44     81     107    Ljava/io/NotSerializableException;
        //  39     44     107    137    Ljava/lang/Exception;
        //  47     70     81     107    Ljava/io/NotSerializableException;
        //  47     70     107    137    Ljava/lang/Exception;
        //  74     80     137    162    Ljava/lang/Exception;
        //  85     104    107    137    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0080:
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
}
