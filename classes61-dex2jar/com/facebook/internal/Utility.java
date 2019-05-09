// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import java.util.Collections;
import android.text.TextUtils;
import java.util.TimeZone;
import android.telephony.TelephonyManager;
import java.util.regex.Pattern;
import java.io.FilenameFilter;
import android.os.StatFs;
import android.os.Parcel;
import android.util.Log;
import java.util.Set;
import java.util.Collection;
import android.view.autofill.AutofillManager;
import android.os.Build$VERSION;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.security.MessageDigest;
import com.facebook.FacebookException;
import org.json.JSONTokener;
import java.lang.reflect.Method;
import com.facebook.AccessToken;
import com.facebook.HttpMethod;
import com.facebook.GraphRequest;
import android.database.Cursor;
import com.facebook.FacebookSdk;
import java.util.Date;
import java.math.BigInteger;
import java.util.Random;
import android.os.Environment;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.File;
import java.io.OutputStream;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.Closeable;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.content.Context;
import java.util.Iterator;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.GraphResponse;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

public final class Utility
{
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
    private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a2";
    private static final int GINGERBREAD_MR1 = 10;
    private static final String HASH_ALGORITHM_MD5 = "MD5";
    private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
    static final String LOG_TAG = "FacebookSDK";
    private static final int REFRESH_TIME_FOR_EXTENDED_DEVICE_INFO_MILLIS = 1800000;
    private static final String URL_SCHEME = "https";
    private static final String UTF8 = "UTF-8";
    private static long availableExternalStorageGB = 0L;
    private static String carrierName;
    private static String deviceTimeZoneName;
    private static String deviceTimezoneAbbreviation;
    private static final String noCarrierConstant = "NoCarrier";
    private static int numCPUCores;
    private static long timestampOfLastCheck;
    private static long totalExternalStorageGB;
    
    static {
        Utility.numCPUCores = 0;
        Utility.timestampOfLastCheck = -1L;
        Utility.totalExternalStorageGB = -1L;
        Utility.availableExternalStorageGB = -1L;
        Utility.deviceTimezoneAbbreviation = "";
        Utility.deviceTimeZoneName = "";
        Utility.carrierName = "NoCarrier";
    }
    
    public static <T> boolean areObjectsEqual(final T t, final T t2) {
        if (t == null) {
            return t2 == null;
        }
        return t.equals(t2);
    }
    
    public static <T> ArrayList<T> arrayList(final T... array) {
        final ArrayList<T> list = new ArrayList<T>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        return list;
    }
    
    public static <T> List<T> asListNoNulls(final T... array) {
        final ArrayList<T> list = new ArrayList<T>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final T t = array[i];
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }
    
    public static JSONObject awaitGetGraphMeRequestWithCache(final String s) {
        final JSONObject profileInformation = ProfileInformationCache.getProfileInformation(s);
        if (profileInformation != null) {
            return profileInformation;
        }
        final GraphResponse executeAndWait = getGraphMeRequestWithCache(s).executeAndWait();
        if (executeAndWait.getError() != null) {
            return null;
        }
        return executeAndWait.getJSONObject();
    }
    
    public static Uri buildUri(final String s, String s2, final Bundle bundle) {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("https");
        uri$Builder.authority(s);
        uri$Builder.path(s2);
        if (bundle != null) {
            final Iterator<String> iterator = bundle.keySet().iterator();
            while (iterator.hasNext()) {
                s2 = iterator.next();
                final Object value = bundle.get(s2);
                if (value instanceof String) {
                    uri$Builder.appendQueryParameter(s2, (String)value);
                }
            }
        }
        return uri$Builder.build();
    }
    
    public static void clearCaches(final Context context) {
        ImageDownloader.clearCache(context);
    }
    
    private static void clearCookiesForDomain(final Context context, final String s) {
        CookieSyncManager.createInstance(context).sync();
        final CookieManager instance = CookieManager.getInstance();
        final String cookie = instance.getCookie(s);
        if (cookie == null) {
            return;
        }
        final String[] split = cookie.split(";");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String[] split2 = split[i].split("=");
            if (split2.length > 0) {
                instance.setCookie(s, split2[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
            }
        }
        instance.removeExpiredCookie();
    }
    
    public static void clearFacebookCookies(final Context context) {
        clearCookiesForDomain(context, "facebook.com");
        clearCookiesForDomain(context, ".facebook.com");
        clearCookiesForDomain(context, "https://facebook.com");
        clearCookiesForDomain(context, "https://.facebook.com");
    }
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static String coerceValueIfNullOrEmpty(final String s, final String s2) {
        if (isNullOrEmpty(s)) {
            return s2;
        }
        return s;
    }
    
    private static long convertBytesToGB(final double n) {
        return Math.round(n / 1.073741824E9);
    }
    
    static Map<String, Object> convertJSONObjectToHashMap(final JSONObject jsonObject) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final JSONArray names = jsonObject.names();
        int n = 0;
    Label_0067_Outer:
        while (true) {
            if (n >= names.length()) {
                return hashMap;
            }
            while (true) {
                try {
                    final String string = names.getString(n);
                    Object o2;
                    final Object o = o2 = jsonObject.get(string);
                    if (o instanceof JSONObject) {
                        o2 = convertJSONObjectToHashMap((JSONObject)o);
                    }
                    hashMap.put(string, o2);
                    ++n;
                    continue Label_0067_Outer;
                }
                catch (JSONException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public static int copyAndCloseInputStream(final InputStream p0, final OutputStream p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: iconst_0       
        //     4: istore_2       
        //     5: new             Ljava/io/BufferedInputStream;
        //     8: dup            
        //     9: aload_0        
        //    10: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    13: astore          4
        //    15: sipush          8192
        //    18: newarray        B
        //    20: astore          5
        //    22: aload           4
        //    24: aload           5
        //    26: invokevirtual   java/io/BufferedInputStream.read:([B)I
        //    29: istore_3       
        //    30: iload_3        
        //    31: iconst_m1      
        //    32: if_icmpeq       50
        //    35: aload_1        
        //    36: aload           5
        //    38: iconst_0       
        //    39: iload_3        
        //    40: invokevirtual   java/io/OutputStream.write:([BII)V
        //    43: iload_2        
        //    44: iload_3        
        //    45: iadd           
        //    46: istore_2       
        //    47: goto            22
        //    50: aload           4
        //    52: ifnull          60
        //    55: aload           4
        //    57: invokevirtual   java/io/BufferedInputStream.close:()V
        //    60: aload_0        
        //    61: ifnull          68
        //    64: aload_0        
        //    65: invokevirtual   java/io/InputStream.close:()V
        //    68: iload_2        
        //    69: ireturn        
        //    70: astore          4
        //    72: aload           5
        //    74: astore_1       
        //    75: aload_1        
        //    76: ifnull          83
        //    79: aload_1        
        //    80: invokevirtual   java/io/BufferedInputStream.close:()V
        //    83: aload_0        
        //    84: ifnull          91
        //    87: aload_0        
        //    88: invokevirtual   java/io/InputStream.close:()V
        //    91: aload           4
        //    93: athrow         
        //    94: astore          5
        //    96: aload           4
        //    98: astore_1       
        //    99: aload           5
        //   101: astore          4
        //   103: goto            75
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  5      15     70     75     Any
        //  15     22     94     106    Any
        //  22     30     94     106    Any
        //  35     43     94     106    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
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
    
    public static void deleteDirectory(final File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    deleteDirectory(listFiles[i]);
                }
            }
        }
        file.delete();
    }
    
    public static void disconnectQuietly(final URLConnection urlConnection) {
        if (urlConnection != null && urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection)urlConnection).disconnect();
        }
    }
    
    private static boolean externalStorageExists() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
    
    public static <T> List<T> filter(final List<T> list, final Predicate<T> predicate) {
        if (list == null) {
            return null;
        }
        final ArrayList<T> list2 = new ArrayList<T>();
        for (final T next : list) {
            if (predicate.apply(next)) {
                list2.add(next);
            }
        }
        ArrayList<T> list3 = list2;
        if (list2.size() == 0) {
            list3 = null;
        }
        return list3;
    }
    
    public static String generateRandomString(final int n) {
        return new BigInteger(n * 5, new Random()).toString(32);
    }
    
    public static String getActivityName(final Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return "unknown";
        }
        return context.getClass().getSimpleName();
    }
    
    public static Date getBundleLongAsDate(final Bundle bundle, final String s, final Date date) {
        if (bundle != null) {
            final Object value = bundle.get(s);
            long n = 0L;
            Label_0027: {
                if (value instanceof Long) {
                    n = (long)value;
                }
                else {
                    if (value instanceof String) {
                        try {
                            n = Long.parseLong((String)value);
                            break Label_0027;
                        }
                        catch (NumberFormatException ex) {
                            return null;
                        }
                        return new Date(date.getTime() + 1000L * n);
                    }
                    return null;
                }
            }
            if (n == 0L) {
                return new Date(Long.MAX_VALUE);
            }
            return new Date(date.getTime() + 1000L * n);
        }
        return null;
    }
    
    public static long getContentSize(final Uri uri) {
        Cursor query = null;
        try {
            final Cursor cursor = query = FacebookSdk.getApplicationContext().getContentResolver().query(uri, (String[])null, (String)null, (String[])null, (String)null);
            final int columnIndex = cursor.getColumnIndex("_size");
            query = cursor;
            cursor.moveToFirst();
            query = cursor;
            return cursor.getLong(columnIndex);
        }
        finally {
            if (query != null) {
                query.close();
            }
        }
    }
    
    private static GraphRequest getGraphMeRequestWithCache(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
        bundle.putString("access_token", s);
        return new GraphRequest(null, "me", bundle, HttpMethod.GET, null);
    }
    
    public static void getGraphMeRequestWithCacheAsync(final String s, final GraphMeRequestWithCacheCallback graphMeRequestWithCacheCallback) {
        final JSONObject profileInformation = ProfileInformationCache.getProfileInformation(s);
        if (profileInformation != null) {
            graphMeRequestWithCacheCallback.onSuccess(profileInformation);
            return;
        }
        final GraphRequest.Callback callback = new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse graphResponse) {
                if (graphResponse.getError() != null) {
                    graphMeRequestWithCacheCallback.onFailure(graphResponse.getError().getException());
                    return;
                }
                ProfileInformationCache.putProfileInformation(s, graphResponse.getJSONObject());
                graphMeRequestWithCacheCallback.onSuccess(graphResponse.getJSONObject());
            }
        };
        final GraphRequest graphMeRequestWithCache = getGraphMeRequestWithCache(s);
        graphMeRequestWithCache.setCallback((GraphRequest.Callback)callback);
        graphMeRequestWithCache.executeAsync();
    }
    
    public static String getMetadataApplicationId(final Context context) {
        Validate.notNull(context, "context");
        FacebookSdk.sdkInitialize(context);
        return FacebookSdk.getApplicationId();
    }
    
    public static Method getMethodQuietly(final Class<?> clazz, final String s, final Class<?>... array) {
        try {
            return clazz.getMethod(s, array);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    public static Method getMethodQuietly(final String s, final String s2, final Class<?>... array) {
        try {
            return getMethodQuietly(Class.forName(s), s2, array);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static Object getStringPropertyAsJSON(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        Object o2;
        final Object o = o2 = jsonObject.opt(s);
        if (o != null) {
            o2 = o;
            if (o instanceof String) {
                o2 = new JSONTokener((String)o).nextValue();
            }
        }
        if (o2 == null || o2 instanceof JSONObject || o2 instanceof JSONArray) {
            return o2;
        }
        if (s2 != null) {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.putOpt(s2, o2);
            return jsonObject2;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }
    
    public static String getUriString(final Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }
    
    public static PermissionsPair handlePermissionResponse(final JSONObject jsonObject) throws JSONException {
        final JSONArray jsonArray = jsonObject.getJSONObject("permissions").getJSONArray("data");
        final ArrayList list = new ArrayList<String>(jsonArray.length());
        final ArrayList list2 = new ArrayList<String>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject optJSONObject = jsonArray.optJSONObject(i);
            final String optString = optJSONObject.optString("permission");
            if (optString != null && !optString.equals("installed")) {
                final String optString2 = optJSONObject.optString("status");
                if (optString2 != null) {
                    if (optString2.equals("granted")) {
                        list.add(optString);
                    }
                    else if (optString2.equals("declined")) {
                        list2.add(optString);
                    }
                }
            }
        }
        return new PermissionsPair((List<String>)list, (List<String>)list2);
    }
    
    public static boolean hasSameId(final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (jsonObject != null && jsonObject2 != null && jsonObject.has("id") && jsonObject2.has("id")) {
            if (jsonObject.equals(jsonObject2)) {
                return true;
            }
            final String optString = jsonObject.optString("id");
            final String optString2 = jsonObject2.optString("id");
            if (optString != null && optString2 != null) {
                return optString.equals(optString2);
            }
        }
        return false;
    }
    
    private static String hashBytes(final MessageDigest messageDigest, final byte[] array) {
        messageDigest.update(array);
        final byte[] digest = messageDigest.digest();
        final StringBuilder sb = new StringBuilder();
        for (int length = digest.length, i = 0; i < length; ++i) {
            final byte b = digest[i];
            sb.append(Integer.toHexString(b >> 4 & 0xF));
            sb.append(Integer.toHexString(b >> 0 & 0xF));
        }
        return sb.toString();
    }
    
    public static <T> HashSet<T> hashSet(final T... array) {
        final HashSet<T> set = new HashSet<T>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            set.add(array[i]);
        }
        return set;
    }
    
    private static String hashWithAlgorithm(final String s, final String s2) {
        return hashWithAlgorithm(s, s2.getBytes());
    }
    
    private static String hashWithAlgorithm(final String s, final byte[] array) {
        try {
            return hashBytes(MessageDigest.getInstance(s), array);
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    public static int[] intersectRanges(final int[] array, final int[] array2) {
        if (array == null) {
            return array2;
        }
        if (array2 == null) {
            return array;
        }
        final int[] array3 = new int[array.length + array2.length];
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4;
        while (true) {
            n4 = n;
            if (n2 >= array.length) {
                break;
            }
            n4 = n;
            if (n3 >= array2.length) {
                break;
            }
            int n5 = Integer.MIN_VALUE;
            final int n6 = Integer.MAX_VALUE;
            final int n7 = array[n2];
            int n8 = Integer.MAX_VALUE;
            final int n9 = array2[n3];
            int n10 = Integer.MAX_VALUE;
            if (n2 < array.length - 1) {
                n8 = array[n2 + 1];
            }
            if (n3 < array2.length - 1) {
                n10 = array2[n3 + 1];
            }
            int n11;
            int n12;
            if (n7 < n9) {
                if (n8 > n9) {
                    n5 = n9;
                    if (n8 > n10) {
                        n8 = n10;
                        n11 = n3 + 2;
                        n12 = n2;
                    }
                    else {
                        n12 = n2 + 2;
                        n11 = n3;
                    }
                }
                else {
                    n12 = n2 + 2;
                    n11 = n3;
                    n8 = n6;
                }
            }
            else if (n10 > n7) {
                n5 = n7;
                if (n10 > n8) {
                    n12 = n2 + 2;
                    n11 = n3;
                }
                else {
                    n8 = n10;
                    n11 = n3 + 2;
                    n12 = n2;
                }
            }
            else {
                n11 = n3 + 2;
                n12 = n2;
                n8 = n6;
            }
            n2 = n12;
            n3 = n11;
            if (n5 == Integer.MIN_VALUE) {
                continue;
            }
            final int n13 = n + 1;
            array3[n] = n5;
            if (n8 == Integer.MAX_VALUE) {
                n4 = n13;
                break;
            }
            n = n13 + 1;
            array3[n13] = n8;
            n2 = n12;
            n3 = n11;
        }
        return Arrays.copyOf(array3, n4);
    }
    
    public static Object invokeMethodQuietly(Object invoke, final Method method, final Object... array) {
        try {
            invoke = method.invoke(invoke, array);
            return invoke;
        }
        catch (IllegalAccessException ex) {
            return null;
        }
        catch (InvocationTargetException ex2) {
            return null;
        }
    }
    
    public static boolean isAutofillAvailable(final Context context) {
        if (Build$VERSION.SDK_INT >= 26) {
            final AutofillManager autofillManager = (AutofillManager)context.getSystemService((Class)AutofillManager.class);
            if (autofillManager != null && autofillManager.isAutofillSupported() && autofillManager.isEnabled()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isContentUri(final Uri uri) {
        return uri != null && "content".equalsIgnoreCase(uri.getScheme());
    }
    
    public static boolean isCurrentAccessToken(final AccessToken accessToken) {
        return accessToken != null && accessToken.equals(AccessToken.getCurrentAccessToken());
    }
    
    public static boolean isFileUri(final Uri uri) {
        return uri != null && "file".equalsIgnoreCase(uri.getScheme());
    }
    
    public static boolean isNullOrEmpty(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static <T> boolean isNullOrEmpty(final Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }
    
    public static <T> boolean isSubset(final Collection<T> collection, final Collection<T> collection2) {
        boolean b = false;
        if (collection2 == null || collection2.size() == 0) {
            if (collection == null || collection.size() == 0) {
                b = true;
            }
            return b;
        }
        final HashSet set = new HashSet((Collection<? extends E>)collection2);
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!set.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isWebUri(final Uri uri) {
        return uri != null && ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme()) || "fbstaging".equalsIgnoreCase(uri.getScheme()));
    }
    
    public static Set<String> jsonArrayToSet(final JSONArray jsonArray) throws JSONException {
        final HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            set.add(jsonArray.getString(i));
        }
        return set;
    }
    
    public static List<String> jsonArrayToStringList(final JSONArray jsonArray) throws JSONException {
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
    
    public static void logd(final String s, final Exception ex) {
        if (FacebookSdk.isDebugEnabled() && s != null && ex != null) {
            Log.d(s, ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
    
    public static void logd(final String s, final String s2) {
        if (FacebookSdk.isDebugEnabled() && s != null && s2 != null) {
            Log.d(s, s2);
        }
    }
    
    public static void logd(final String s, final String s2, final Throwable t) {
        if (FacebookSdk.isDebugEnabled() && !isNullOrEmpty(s)) {
            Log.d(s, s2, t);
        }
    }
    
    public static <T, K> List<K> map(final List<T> list, final Mapper<T, K> mapper) {
        if (list == null) {
            return null;
        }
        final ArrayList<K> list2 = new ArrayList<K>();
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            final K apply = mapper.apply(iterator.next());
            if (apply != null) {
                list2.add(apply);
            }
        }
        ArrayList<K> list3 = list2;
        if (list2.size() == 0) {
            list3 = null;
        }
        return list3;
    }
    
    public static String md5hash(final String s) {
        return hashWithAlgorithm("MD5", s);
    }
    
    public static boolean mustFixWindowParamsForAutofill(final Context context) {
        return isAutofillAvailable(context);
    }
    
    public static Bundle parseUrlQueryString(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1       
        //     2: new             Landroid/os/Bundle;
        //     5: dup            
        //     6: invokespecial   android/os/Bundle.<init>:()V
        //     9: astore_3       
        //    10: aload_0        
        //    11: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    14: ifne            112
        //    17: aload_0        
        //    18: ldc_w           "&"
        //    21: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    24: astore_0       
        //    25: aload_0        
        //    26: arraylength    
        //    27: istore_2       
        //    28: iload_1        
        //    29: iload_2        
        //    30: if_icmpge       112
        //    33: aload_0        
        //    34: iload_1        
        //    35: aaload         
        //    36: ldc             "="
        //    38: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    41: astore          4
        //    43: aload           4
        //    45: arraylength    
        //    46: iconst_2       
        //    47: if_icmpne       75
        //    50: aload_3        
        //    51: aload           4
        //    53: iconst_0       
        //    54: aaload         
        //    55: ldc             "UTF-8"
        //    57: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    60: aload           4
        //    62: iconst_1       
        //    63: aaload         
        //    64: ldc             "UTF-8"
        //    66: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    69: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    72: goto            114
        //    75: aload           4
        //    77: arraylength    
        //    78: iconst_1       
        //    79: if_icmpne       114
        //    82: aload_3        
        //    83: aload           4
        //    85: iconst_0       
        //    86: aaload         
        //    87: ldc             "UTF-8"
        //    89: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    92: ldc             ""
        //    94: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    97: goto            114
        //   100: astore          4
        //   102: ldc             "FacebookSDK"
        //   104: aload           4
        //   106: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   109: goto            114
        //   112: aload_3        
        //   113: areturn        
        //   114: iload_1        
        //   115: iconst_1       
        //   116: iadd           
        //   117: istore_1       
        //   118: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  43     72     100    112    Ljava/io/UnsupportedEncodingException;
        //  75     97     100    112    Ljava/io/UnsupportedEncodingException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    public static void putCommaSeparatedStringList(final Bundle bundle, final String s, final List<String> list) {
        if (list != null) {
            final StringBuilder sb = new StringBuilder();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
                sb.append(",");
            }
            String substring = "";
            if (sb.length() > 0) {
                substring = sb.substring(0, sb.length() - 1);
            }
            bundle.putString(s, substring);
        }
    }
    
    public static boolean putJSONValueInBundle(final Bundle bundle, final String s, final Object o) {
        if (o == null) {
            bundle.remove(s);
        }
        else if (o instanceof Boolean) {
            bundle.putBoolean(s, (boolean)o);
        }
        else if (o instanceof boolean[]) {
            bundle.putBooleanArray(s, (boolean[])o);
        }
        else if (o instanceof Double) {
            bundle.putDouble(s, (double)o);
        }
        else if (o instanceof double[]) {
            bundle.putDoubleArray(s, (double[])o);
        }
        else if (o instanceof Integer) {
            bundle.putInt(s, (int)o);
        }
        else if (o instanceof int[]) {
            bundle.putIntArray(s, (int[])o);
        }
        else if (o instanceof Long) {
            bundle.putLong(s, (long)o);
        }
        else if (o instanceof long[]) {
            bundle.putLongArray(s, (long[])o);
        }
        else if (o instanceof String) {
            bundle.putString(s, (String)o);
        }
        else if (o instanceof JSONArray) {
            bundle.putString(s, o.toString());
        }
        else {
            if (!(o instanceof JSONObject)) {
                return false;
            }
            bundle.putString(s, o.toString());
        }
        return true;
    }
    
    public static void putNonEmptyString(final Bundle bundle, final String s, final String s2) {
        if (!isNullOrEmpty(s2)) {
            bundle.putString(s, s2);
        }
    }
    
    public static void putUri(final Bundle bundle, final String s, final Uri uri) {
        if (uri != null) {
            putNonEmptyString(bundle, s, uri.toString());
        }
    }
    
    public static String readStreamToString(final InputStream p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_2       
        //     2: aconst_null    
        //     3: astore_3       
        //     4: new             Ljava/io/BufferedInputStream;
        //     7: dup            
        //     8: aload_0        
        //     9: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    12: astore_0       
        //    13: new             Ljava/io/InputStreamReader;
        //    16: dup            
        //    17: aload_0        
        //    18: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    21: astore_2       
        //    22: new             Ljava/lang/StringBuilder;
        //    25: dup            
        //    26: invokespecial   java/lang/StringBuilder.<init>:()V
        //    29: astore_3       
        //    30: sipush          2048
        //    33: newarray        C
        //    35: astore          4
        //    37: aload_2        
        //    38: aload           4
        //    40: invokevirtual   java/io/InputStreamReader.read:([C)I
        //    43: istore_1       
        //    44: iload_1        
        //    45: iconst_m1      
        //    46: if_icmpeq       78
        //    49: aload_3        
        //    50: aload           4
        //    52: iconst_0       
        //    53: iload_1        
        //    54: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //    57: pop            
        //    58: goto            37
        //    61: astore          4
        //    63: aload_2        
        //    64: astore_3       
        //    65: aload           4
        //    67: astore_2       
        //    68: aload_0        
        //    69: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    72: aload_3        
        //    73: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    76: aload_2        
        //    77: athrow         
        //    78: aload_3        
        //    79: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    82: astore_3       
        //    83: aload_0        
        //    84: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    87: aload_2        
        //    88: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    91: aload_3        
        //    92: areturn        
        //    93: astore          4
        //    95: aload_2        
        //    96: astore_0       
        //    97: aload           4
        //    99: astore_2       
        //   100: goto            68
        //   103: astore_2       
        //   104: goto            68
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  4      13     93     103    Any
        //  13     22     103    107    Any
        //  22     37     61     68     Any
        //  37     44     61     68     Any
        //  49     58     61     68     Any
        //  78     83     61     68     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
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
    
    public static Map<String, String> readStringMapFromParcel(final Parcel parcel) {
        final int int1 = parcel.readInt();
        Map<String, String> map;
        if (int1 < 0) {
            map = null;
        }
        else {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            int n = 0;
            while (true) {
                map = hashMap;
                if (n >= int1) {
                    break;
                }
                hashMap.put(parcel.readString(), parcel.readString());
                ++n;
            }
        }
        return map;
    }
    
    private static void refreshAvailableExternalStorage() {
        try {
            if (externalStorageExists()) {
                final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                Utility.availableExternalStorageGB = statFs.getAvailableBlocks() * (long)statFs.getBlockSize();
            }
            Utility.availableExternalStorageGB = convertBytesToGB((double)Utility.availableExternalStorageGB);
        }
        catch (Exception ex) {}
    }
    
    private static int refreshBestGuessNumberOfCPUCores() {
        if (Utility.numCPUCores > 0) {
            return Utility.numCPUCores;
        }
        while (true) {
            try {
                final File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(final File file, final String s) {
                        return Pattern.matches("cpu[0-9]+", s);
                    }
                });
                if (listFiles != null) {
                    Utility.numCPUCores = listFiles.length;
                }
                if (Utility.numCPUCores <= 0) {
                    Utility.numCPUCores = Math.max(Runtime.getRuntime().availableProcessors(), 1);
                }
                return Utility.numCPUCores;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    private static void refreshCarrierName(final Context context) {
        if (!Utility.carrierName.equals("NoCarrier")) {
            return;
        }
        try {
            Utility.carrierName = ((TelephonyManager)context.getSystemService("phone")).getNetworkOperatorName();
        }
        catch (Exception ex) {}
    }
    
    private static void refreshPeriodicExtendedDeviceInfo(final Context context) {
        if (Utility.timestampOfLastCheck == -1L || System.currentTimeMillis() - Utility.timestampOfLastCheck >= 1800000L) {
            Utility.timestampOfLastCheck = System.currentTimeMillis();
            refreshTimezone();
            refreshCarrierName(context);
            refreshTotalExternalStorage();
            refreshAvailableExternalStorage();
        }
    }
    
    private static void refreshTimezone() {
        try {
            final TimeZone default1 = TimeZone.getDefault();
            Utility.deviceTimezoneAbbreviation = default1.getDisplayName(default1.inDaylightTime(new Date()), 0);
            Utility.deviceTimeZoneName = default1.getID();
        }
        catch (Exception ex) {}
    }
    
    private static void refreshTotalExternalStorage() {
        try {
            if (externalStorageExists()) {
                final StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                Utility.totalExternalStorageGB = statFs.getBlockCount() * (long)statFs.getBlockSize();
            }
            Utility.totalExternalStorageGB = convertBytesToGB((double)Utility.totalExternalStorageGB);
        }
        catch (Exception ex) {}
    }
    
    public static String safeGetStringFromResponse(final JSONObject jsonObject, final String s) {
        if (jsonObject != null) {
            return jsonObject.optString(s, "");
        }
        return "";
    }
    
    public static void setAppEventAttributionParameters(final JSONObject jsonObject, final AttributionIdentifiers attributionIdentifiers, final String s, final boolean b) throws JSONException {
        final boolean b2 = true;
        if (attributionIdentifiers != null && attributionIdentifiers.getAttributionId() != null) {
            jsonObject.put("attribution", (Object)attributionIdentifiers.getAttributionId());
        }
        if (attributionIdentifiers != null && attributionIdentifiers.getAndroidAdvertiserId() != null) {
            jsonObject.put("advertiser_id", (Object)attributionIdentifiers.getAndroidAdvertiserId());
            jsonObject.put("advertiser_tracking_enabled", !attributionIdentifiers.isTrackingLimited());
        }
        if (attributionIdentifiers != null && attributionIdentifiers.getAndroidInstallerPackage() != null) {
            jsonObject.put("installer_package", (Object)attributionIdentifiers.getAndroidInstallerPackage());
        }
        jsonObject.put("anon_id", (Object)s);
        jsonObject.put("application_tracking_enabled", !b && b2);
    }
    
    public static void setAppEventExtendedDeviceInfoParameters(final JSONObject p0, final Context p1) throws JSONException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   org/json/JSONArray.<init>:()V
        //     7: astore          15
        //     9: aload           15
        //    11: ldc             "a2"
        //    13: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    16: pop            
        //    17: aload_1        
        //    18: invokestatic    com/facebook/internal/Utility.refreshPeriodicExtendedDeviceInfo:(Landroid/content/Context;)V
        //    21: aload_1        
        //    22: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    25: astore          16
        //    27: iconst_m1      
        //    28: istore          8
        //    30: ldc             ""
        //    32: astore          13
        //    34: iload           8
        //    36: istore          7
        //    38: aload_1        
        //    39: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    42: aload           16
        //    44: iconst_0       
        //    45: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    48: astore          14
        //    50: iload           8
        //    52: istore          7
        //    54: aload           14
        //    56: getfield        android/content/pm/PackageInfo.versionCode:I
        //    59: istore          8
        //    61: iload           8
        //    63: istore          7
        //    65: aload           14
        //    67: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //    70: astore          14
        //    72: aload           14
        //    74: astore          13
        //    76: iload           8
        //    78: istore          7
        //    80: aload           15
        //    82: aload           16
        //    84: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //    87: pop            
        //    88: aload           15
        //    90: iload           7
        //    92: invokevirtual   org/json/JSONArray.put:(I)Lorg/json/JSONArray;
        //    95: pop            
        //    96: aload           15
        //    98: aload           13
        //   100: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   103: pop            
        //   104: aload           15
        //   106: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //   109: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   112: pop            
        //   113: aload           15
        //   115: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //   118: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   121: pop            
        //   122: aload_1        
        //   123: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //   126: invokevirtual   android/content/res/Resources.getConfiguration:()Landroid/content/res/Configuration;
        //   129: getfield        android/content/res/Configuration.locale:Ljava/util/Locale;
        //   132: astore          13
        //   134: aload           15
        //   136: new             Ljava/lang/StringBuilder;
        //   139: dup            
        //   140: invokespecial   java/lang/StringBuilder.<init>:()V
        //   143: aload           13
        //   145: invokevirtual   java/util/Locale.getLanguage:()Ljava/lang/String;
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   151: ldc_w           "_"
        //   154: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   157: aload           13
        //   159: invokevirtual   java/util/Locale.getCountry:()Ljava/lang/String;
        //   162: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   168: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   171: pop            
        //   172: aload           15
        //   174: getstatic       com/facebook/internal/Utility.deviceTimezoneAbbreviation:Ljava/lang/String;
        //   177: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   180: pop            
        //   181: aload           15
        //   183: getstatic       com/facebook/internal/Utility.carrierName:Ljava/lang/String;
        //   186: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   189: pop            
        //   190: iconst_0       
        //   191: istore          9
        //   193: iconst_0       
        //   194: istore          11
        //   196: iconst_0       
        //   197: istore          10
        //   199: iconst_0       
        //   200: istore          12
        //   202: dconst_0       
        //   203: dstore          4
        //   205: iload           10
        //   207: istore          8
        //   209: iload           9
        //   211: istore          7
        //   213: aload_1        
        //   214: ldc_w           "window"
        //   217: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   220: checkcast       Landroid/view/WindowManager;
        //   223: astore_1       
        //   224: dload           4
        //   226: dstore_2       
        //   227: iload           12
        //   229: istore          8
        //   231: iload           11
        //   233: istore          7
        //   235: aload_1        
        //   236: ifnull          342
        //   239: iload           10
        //   241: istore          8
        //   243: iload           9
        //   245: istore          7
        //   247: aload_1        
        //   248: invokeinterface android/view/WindowManager.getDefaultDisplay:()Landroid/view/Display;
        //   253: astore_1       
        //   254: iload           10
        //   256: istore          8
        //   258: iload           9
        //   260: istore          7
        //   262: new             Landroid/util/DisplayMetrics;
        //   265: dup            
        //   266: invokespecial   android/util/DisplayMetrics.<init>:()V
        //   269: astore          13
        //   271: iload           10
        //   273: istore          8
        //   275: iload           9
        //   277: istore          7
        //   279: aload_1        
        //   280: aload           13
        //   282: invokevirtual   android/view/Display.getMetrics:(Landroid/util/DisplayMetrics;)V
        //   285: iload           10
        //   287: istore          8
        //   289: iload           9
        //   291: istore          7
        //   293: aload           13
        //   295: getfield        android/util/DisplayMetrics.widthPixels:I
        //   298: istore          9
        //   300: iload           10
        //   302: istore          8
        //   304: iload           9
        //   306: istore          7
        //   308: aload           13
        //   310: getfield        android/util/DisplayMetrics.heightPixels:I
        //   313: istore          10
        //   315: iload           10
        //   317: istore          8
        //   319: iload           9
        //   321: istore          7
        //   323: aload           13
        //   325: getfield        android/util/DisplayMetrics.density:F
        //   328: fstore          6
        //   330: fload           6
        //   332: f2d            
        //   333: dstore_2       
        //   334: iload           9
        //   336: istore          7
        //   338: iload           10
        //   340: istore          8
        //   342: aload           15
        //   344: iload           7
        //   346: invokevirtual   org/json/JSONArray.put:(I)Lorg/json/JSONArray;
        //   349: pop            
        //   350: aload           15
        //   352: iload           8
        //   354: invokevirtual   org/json/JSONArray.put:(I)Lorg/json/JSONArray;
        //   357: pop            
        //   358: aload           15
        //   360: ldc_w           "%.2f"
        //   363: iconst_1       
        //   364: anewarray       Ljava/lang/Object;
        //   367: dup            
        //   368: iconst_0       
        //   369: dload_2        
        //   370: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   373: aastore        
        //   374: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   377: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   380: pop            
        //   381: aload           15
        //   383: invokestatic    com/facebook/internal/Utility.refreshBestGuessNumberOfCPUCores:()I
        //   386: invokevirtual   org/json/JSONArray.put:(I)Lorg/json/JSONArray;
        //   389: pop            
        //   390: aload           15
        //   392: getstatic       com/facebook/internal/Utility.totalExternalStorageGB:J
        //   395: invokevirtual   org/json/JSONArray.put:(J)Lorg/json/JSONArray;
        //   398: pop            
        //   399: aload           15
        //   401: getstatic       com/facebook/internal/Utility.availableExternalStorageGB:J
        //   404: invokevirtual   org/json/JSONArray.put:(J)Lorg/json/JSONArray;
        //   407: pop            
        //   408: aload           15
        //   410: getstatic       com/facebook/internal/Utility.deviceTimeZoneName:Ljava/lang/String;
        //   413: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   416: pop            
        //   417: aload_0        
        //   418: ldc_w           "extinfo"
        //   421: aload           15
        //   423: invokevirtual   org/json/JSONArray.toString:()Ljava/lang/String;
        //   426: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   429: pop            
        //   430: return         
        //   431: astore          13
        //   433: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //   436: astore          13
        //   438: goto            134
        //   441: astore_1       
        //   442: dload           4
        //   444: dstore_2       
        //   445: goto            342
        //   448: astore          14
        //   450: goto            80
        //    Exceptions:
        //  throws org.json.JSONException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  38     50     448    453    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  54     61     448    453    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  65     72     448    453    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  122    134    431    441    Ljava/lang/Exception;
        //  213    224    441    448    Ljava/lang/Exception;
        //  247    254    441    448    Ljava/lang/Exception;
        //  262    271    441    448    Ljava/lang/Exception;
        //  279    285    441    448    Ljava/lang/Exception;
        //  293    300    441    448    Ljava/lang/Exception;
        //  308    315    441    448    Ljava/lang/Exception;
        //  323    330    441    448    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0342:
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
    
    public static String sha1hash(final String s) {
        return hashWithAlgorithm("SHA-1", s);
    }
    
    public static String sha1hash(final byte[] array) {
        return hashWithAlgorithm("SHA-1", array);
    }
    
    public static boolean stringsEqualOrEmpty(final String s, final String s2) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        final boolean empty2 = TextUtils.isEmpty((CharSequence)s2);
        return (empty && empty2) || (!empty && !empty2 && s.equals(s2));
    }
    
    public static JSONArray tryGetJSONArrayFromResponse(final JSONObject jsonObject, final String s) {
        if (jsonObject != null) {
            return jsonObject.optJSONArray(s);
        }
        return null;
    }
    
    public static JSONObject tryGetJSONObjectFromResponse(final JSONObject jsonObject, final String s) {
        if (jsonObject != null) {
            return jsonObject.optJSONObject(s);
        }
        return null;
    }
    
    public static <T> Collection<T> unmodifiableCollection(final T... array) {
        return Collections.unmodifiableCollection((Collection<? extends T>)Arrays.asList(array));
    }
    
    public static void writeStringMapToParcel(final Parcel parcel, final Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
        }
        else {
            parcel.writeInt(map.size());
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                parcel.writeString((String)entry.getKey());
                parcel.writeString((String)entry.getValue());
            }
        }
    }
    
    public interface GraphMeRequestWithCacheCallback
    {
        void onFailure(final FacebookException p0);
        
        void onSuccess(final JSONObject p0);
    }
    
    public interface Mapper<T, K>
    {
        K apply(final T p0);
    }
    
    public static class PermissionsPair
    {
        List<String> declinedPermissions;
        List<String> grantedPermissions;
        
        public PermissionsPair(final List<String> grantedPermissions, final List<String> declinedPermissions) {
            this.grantedPermissions = grantedPermissions;
            this.declinedPermissions = declinedPermissions;
        }
        
        public List<String> getDeclinedPermissions() {
            return this.declinedPermissions;
        }
        
        public List<String> getGrantedPermissions() {
            return this.grantedPermissions;
        }
    }
    
    public interface Predicate<T>
    {
        boolean apply(final T p0);
    }
}
