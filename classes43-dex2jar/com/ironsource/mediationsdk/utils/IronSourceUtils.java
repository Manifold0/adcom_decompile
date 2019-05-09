// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.utils;

import android.content.SharedPreferences$Editor;
import java.util.Iterator;
import java.security.NoSuchAlgorithmException;
import com.ironsource.mediationsdk.BannerSmash;
import com.ironsource.mediationsdk.AbstractSmash;
import org.json.JSONException;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.StringTokenizer;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import android.net.NetworkInfo;
import android.net.NetworkCapabilities;
import android.os.Build$VERSION;
import android.net.ConnectivityManager;
import android.content.Context;
import android.util.Base64;
import com.ironsource.mediationsdk.logger.ThreadExceptionHandler;

public class IronSourceUtils
{
    private static final String ADAPTER_VERSION_KEY = "providerAdapterVersion";
    private static final String DEFAULT_IS_EVENTS_FORMATTER_TYPE = "default_is_events_formatter_type";
    private static final String DEFAULT_IS_EVENTS_URL = "default_is_events_url";
    private static final String DEFAULT_IS_OPT_OUT_EVENTS = "default_is_opt_out_events";
    private static final String DEFAULT_RV_EVENTS_FORMATTER_TYPE = "default_rv_events_formatter_type";
    private static final String DEFAULT_RV_EVENTS_URL = "default_rv_events_url";
    private static final String DEFAULT_RV_OPT_OUT_EVENTS = "default_rv_opt_out_events";
    private static final String GENERAL_PROPERTIES = "general_properties";
    public static final String KEY = "C38FB23A402222A0C17D34A92F971D1F";
    private static final String LAST_RESPONSE = "last_response";
    private static final String NETWORK_INSTANCE_KEY = "networkInstance";
    private static final String PROVIDER_KEY = "provider";
    private static final String PROVIDER_PRIORITY = "providerPriority";
    private static final String SDK_VERSION = "6.8.0";
    private static final String SDK_VERSION_KEY = "providerSDKVersion";
    private static final String SHARED_PREFERENCES_NAME = "Mediation_Shared_Preferences";
    private static final String SUB_PROVIDER_ID_KEY = "spId";
    private static String mAbt;
    private static int serr;
    
    static {
        IronSourceUtils.serr = 1;
        IronSourceUtils.mAbt = "";
    }
    
    public static void createAndStartWorker(final Runnable runnable, final String s) {
        final Thread thread = new Thread(runnable, s);
        thread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new ThreadExceptionHandler());
        thread.start();
    }
    
    public static String getAbt() {
        return IronSourceUtils.mAbt;
    }
    
    public static String getBase64Auth(String string, final String s) {
        string = string + ":" + s;
        return "Basic " + Base64.encodeToString(string.getBytes(), 10);
    }
    
    static boolean getBooleanFromSharedPrefs(final Context context, final String s, final boolean b) {
        return context.getSharedPreferences("Mediation_Shared_Preferences", 0).getBoolean(s, b);
    }
    
    public static String getConnectionType(final Context context) {
        if (context == null) {
            return "none";
        }
        final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "none";
        }
        if (Build$VERSION.SDK_INT >= 23) {
            final NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities == null) {
                return "none";
            }
            if (networkCapabilities.hasTransport(1)) {
                return "wifi";
            }
            if (networkCapabilities.hasTransport(0)) {
                return "cellular";
            }
            return "none";
        }
        else {
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return "none";
            }
            if (activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return "wifi";
            }
            if (activeNetworkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
                return "cellular";
            }
            return "none";
        }
    }
    
    public static int getCurrentTimestamp() {
        return (int)(System.currentTimeMillis() / 1000L);
    }
    
    public static String getDefaultEventsFormatterType(final Context context, final String s, final String s2) {
        synchronized (IronSourceUtils.class) {
            try {
                return context.getSharedPreferences("Mediation_Shared_Preferences", 0).getString(getDefaultFormatterTypeByEventType(s), s2);
            }
            catch (Exception ex) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getDefaultEventsFormatterType(eventType: " + s + ", defaultFormatterType:" + s2 + ")", ex);
                return s2;
            }
        }
    }
    
    public static String getDefaultEventsURL(final Context context, final String s, final String s2) {
        synchronized (IronSourceUtils.class) {
            try {
                return context.getSharedPreferences("Mediation_Shared_Preferences", 0).getString(getDefaultEventsUrlByEventType(s), s2);
            }
            catch (Exception ex) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getDefaultEventsURL(eventType: " + s + ", defaultEventsURL:" + s2 + ")", ex);
                return s2;
            }
        }
    }
    
    private static String getDefaultEventsUrlByEventType(final String s) {
        if ("IS".equals(s)) {
            return "default_is_events_url";
        }
        if ("RV".equals(s)) {
            return "default_rv_events_url";
        }
        return "";
    }
    
    private static String getDefaultFormatterTypeByEventType(final String s) {
        if ("IS".equals(s)) {
            return "default_is_events_formatter_type";
        }
        if ("RV".equals(s)) {
            return "default_rv_events_formatter_type";
        }
        return "";
    }
    
    public static int[] getDefaultOptOutEvents(final Context context, final String s) {
        // monitorenter(IronSourceUtils.class)
        ArrayList<Integer> list = null;
        int[] array2;
        int[] array = array2 = null;
        Label_0148: {
            try {
                final String string = context.getSharedPreferences("Mediation_Shared_Preferences", 0).getString(getDefaultOptOutEventsByEventType(s), (String)null);
                array2 = array;
                if (TextUtils.isEmpty((CharSequence)string)) {
                    return;
                }
                array2 = array;
                final StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
                array2 = array;
                list = new ArrayList<Integer>();
                while (true) {
                    array2 = array;
                    if (!stringTokenizer.hasMoreTokens()) {
                        break Label_0148;
                    }
                    array2 = array;
                    list.add(Integer.parseInt(stringTokenizer.nextToken()));
                }
            }
            catch (Exception ex) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getDefaultOptOutEvents(eventType: " + s + ")", ex);
                int[] array3 = array2;
                Label_0143: {
                    return array3;
                }
                // iftrue(Label_0143:, n >= array.length)
                while (true) {
                    while (true) {
                        final int n;
                        array[n] = list.get(n);
                        ++n;
                        array3 = array;
                        continue;
                    }
                    array = new int[list.size()];
                    int n = 0;
                    continue;
                }
            }
            finally {
            }
            // monitorexit(IronSourceUtils.class)
        }
    }
    
    private static String getDefaultOptOutEventsByEventType(final String s) {
        if ("IS".equals(s)) {
            return "default_is_opt_out_events";
        }
        if ("RV".equals(s)) {
            return "default_rv_opt_out_events";
        }
        return "";
    }
    
    public static JSONObject getGeneralProperties(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: monitorenter   
        //     3: new             Lorg/json/JSONObject;
        //     6: dup            
        //     7: invokespecial   org/json/JSONObject.<init>:()V
        //    10: astore_1       
        //    11: aload_0        
        //    12: ifnonnull       20
        //    15: ldc             Lcom/ironsource/mediationsdk/utils/IronSourceUtils;.class
        //    17: monitorexit    
        //    18: aload_1        
        //    19: areturn        
        //    20: aload_0        
        //    21: ldc             "Mediation_Shared_Preferences"
        //    23: iconst_0       
        //    24: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //    27: ldc             "general_properties"
        //    29: aload_1        
        //    30: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //    33: invokeinterface android/content/SharedPreferences.getString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    38: astore_0       
        //    39: new             Lorg/json/JSONObject;
        //    42: dup            
        //    43: aload_0        
        //    44: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    47: astore_0       
        //    48: aload_0        
        //    49: astore_1       
        //    50: goto            15
        //    53: astore_0       
        //    54: ldc             Lcom/ironsource/mediationsdk/utils/IronSourceUtils;.class
        //    56: monitorexit    
        //    57: aload_0        
        //    58: athrow         
        //    59: astore_0       
        //    60: aload_1        
        //    61: astore_0       
        //    62: goto            48
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  3      11     53     59     Any
        //  20     39     53     59     Any
        //  39     48     59     65     Lorg/json/JSONException;
        //  39     48     53     59     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    static int getIntFromSharedPrefs(final Context context, final String s, final int n) {
        return context.getSharedPreferences("Mediation_Shared_Preferences", 0).getInt(s, n);
    }
    
    public static String getLastResponse(final Context context) {
        return context.getSharedPreferences("Mediation_Shared_Preferences", 0).getString("last_response", "");
    }
    
    static long getLongFromSharedPrefs(final Context context, final String s, final long n) {
        return context.getSharedPreferences("Mediation_Shared_Preferences", 0).getLong(s, n);
    }
    
    public static String getMD5(final String s) {
        String s3;
        while (true) {
            while (true) {
                Label_0085: {
                    try {
                        String s2 = new BigInteger(1, MessageDigest.getInstance("MD5").digest(s.getBytes())).toString(16);
                        while (true) {
                            s3 = s2;
                            if (s2.length() >= 32) {
                                break;
                            }
                            s2 = "0" + s2;
                        }
                    }
                    catch (Throwable t) {
                        if (s != null) {
                            break Label_0085;
                        }
                        IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getMD5(input:null)", t);
                        s3 = "";
                    }
                    break;
                }
                final Throwable t;
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getMD5(input:" + s + ")", t);
                continue;
            }
        }
        return s3;
    }
    
    public static JSONObject getMediationAdditionalData(final boolean b) {
        final JSONObject jsonObject = new JSONObject();
        Label_0022: {
            if (!b) {
                break Label_0022;
            }
            try {
                jsonObject.put("networkInstance", (Object)"true");
                jsonObject.put("provider", (Object)"Mediation");
                return jsonObject;
            }
            catch (JSONException ex) {
                return jsonObject;
            }
        }
    }
    
    public static JSONObject getProviderAdditionalData(final AbstractSmash abstractSmash, final boolean b) {
        final JSONObject jsonObject = new JSONObject();
        Label_0022: {
            if (!b) {
                break Label_0022;
            }
            try {
                jsonObject.put("networkInstance", (Object)"true");
                jsonObject.put("spId", (Object)abstractSmash.getSubProviderId());
                jsonObject.put("provider", (Object)abstractSmash.getAdSourceNameForEvents());
                jsonObject.put("providerSDKVersion", (Object)abstractSmash.getAdapter().getCoreSDKVersion());
                jsonObject.put("providerAdapterVersion", (Object)abstractSmash.getAdapter().getVersion());
                jsonObject.put("providerPriority", abstractSmash.getProviderPriority());
                return jsonObject;
            }
            catch (Exception ex) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getProviderAdditionalData(adapter: " + abstractSmash.getName() + ")", ex);
                return jsonObject;
            }
        }
    }
    
    public static JSONObject getProviderAdditionalData(final BannerSmash bannerSmash) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("spId", (Object)bannerSmash.getSubProviderId());
            jsonObject.put("provider", (Object)bannerSmash.getAdSourceNameForEvents());
            jsonObject.put("providerSDKVersion", (Object)bannerSmash.getAdapter().getCoreSDKVersion());
            jsonObject.put("providerAdapterVersion", (Object)bannerSmash.getAdapter().getVersion());
            jsonObject.put("providerPriority", bannerSmash.getProviderPriority());
            return jsonObject;
        }
        catch (Exception ex) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:getProviderAdditionalData(adapter: " + bannerSmash.getName() + ")", ex);
            return jsonObject;
        }
    }
    
    public static String getSDKVersion() {
        return "6.8.0";
    }
    
    private static String getSHA256(final String s) {
        try {
            return String.format("%064x", new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(s.getBytes())));
        }
        catch (NoSuchAlgorithmException ex) {
            if (s == null) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getSHA256(input:null)", ex);
            }
            else {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getSHA256(input:" + s + ")", ex);
            }
            return "";
        }
    }
    
    public static int getSerr() {
        return IronSourceUtils.serr;
    }
    
    static String getStringFromSharedPrefs(final Context context, final String s, final String s2) {
        return context.getSharedPreferences("Mediation_Shared_Preferences", 0).getString(s, s2);
    }
    
    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }
    
    public static String getTransId(final String s) {
        return getSHA256(s);
    }
    
    public static boolean isNetworkConnected(final Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
    public static JSONObject mergeJsons(final JSONObject jsonObject, final JSONObject jsonObject2) {
        Label_0016: {
            if (jsonObject != null || jsonObject2 != null) {
                if (jsonObject == null) {
                    return jsonObject2;
                }
                break Label_0016;
            }
            try {
                return new JSONObject();
                // iftrue(Label_0071:, jsonObject2 == null)
                // iftrue(Label_0025:, jsonObject.has(s))
                while (true) {
                Label_0025:
                    while (true) {
                        Block_5: {
                            break Block_5;
                            final String s;
                            jsonObject.put(s, jsonObject2.get(s));
                            break Label_0025;
                        }
                        final Iterator keys = jsonObject2.keys();
                        break Label_0025;
                        final String s = keys.next();
                        continue;
                    }
                    continue;
                }
            }
            // iftrue(Label_0071:, !keys.hasNext())
            catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        Label_0071: {
            return jsonObject;
        }
    }
    
    static void saveBooleanToSharedPrefs(final Context context, final String s, final boolean b) {
        final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
        edit.putBoolean(s, b);
        edit.apply();
    }
    
    public static void saveDefaultEventsFormatterType(final Context context, final String s, final String s2) {
        synchronized (IronSourceUtils.class) {
            try {
                final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
                edit.putString(getDefaultFormatterTypeByEventType(s), s2);
                edit.commit();
            }
            catch (Exception ex) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultEventsFormatterType(eventType: " + s + ", formatterType:" + s2 + ")", ex);
            }
        }
    }
    
    public static void saveDefaultEventsURL(final Context context, final String s, final String s2) {
        synchronized (IronSourceUtils.class) {
            try {
                final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
                edit.putString(getDefaultEventsUrlByEventType(s), s2);
                edit.commit();
            }
            catch (Exception ex) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultEventsURL(eventType: " + s + ", eventsUrl:" + s2 + ")", ex);
            }
        }
    }
    
    public static void saveDefaultOptOutEvents(final Context context, final String s, final int[] array) {
        synchronized (IronSourceUtils.class) {
            try {
                final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
                String string = null;
                if (array != null) {
                    final StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < array.length; ++i) {
                        sb.append(array[i]).append(",");
                    }
                    string = sb.toString();
                }
                edit.putString(getDefaultOptOutEventsByEventType(s), string);
                edit.commit();
            }
            catch (Exception ex) {
                IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultOptOutEvents(eventType: " + s + ", optOutEvents:" + array + ")", ex);
            }
        }
    }
    
    static void saveGeneralProperties(final Context context, final JSONObject jsonObject) {
        // monitorenter(IronSourceUtils.class)
        if (context != null && jsonObject != null) {
            try {
                final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
                edit.putString("general_properties", jsonObject.toString());
                edit.apply();
            }
            finally {
            }
            // monitorexit(IronSourceUtils.class)
        }
    }
    // monitorexit(IronSourceUtils.class)
    
    static void saveIntToSharedPrefs(final Context context, final String s, final int n) {
        final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
        edit.putInt(s, n);
        edit.apply();
    }
    
    public static void saveLastResponse(final Context context, final String s) {
        synchronized (IronSourceUtils.class) {
            final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
            edit.putString("last_response", s);
            edit.apply();
        }
    }
    
    static void saveLongToSharedPrefs(final Context context, final String s, final long n) {
        final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
        edit.putLong(s, n);
        edit.apply();
    }
    
    static void saveStringToSharedPrefs(final Context context, final String s, final String s2) {
        final SharedPreferences$Editor edit = context.getSharedPreferences("Mediation_Shared_Preferences", 0).edit();
        edit.putString(s, s2);
        edit.apply();
    }
    
    public static void sendAutomationLog(String string) {
        synchronized (IronSourceUtils.class) {
            string = "automation_log:" + Long.toString(System.currentTimeMillis()) + "text:" + string;
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, string, 1);
        }
    }
    
    static void setABT(final String mAbt) {
        IronSourceUtils.mAbt = mAbt;
    }
    
    private static void setSerr(final int serr) {
        IronSourceUtils.serr = serr;
    }
}
