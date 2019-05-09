// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.util.concurrent.Callable;
import android.text.TextUtils;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.concurrent.Future;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import java.util.Map;
import android.os.Environment;
import java.util.concurrent.Executors;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.Vector;
import android.content.Context;

public class TapjoyCache
{
    public static final String CACHE_DIRECTORY_NAME = "Tapjoy/Cache/";
    public static final int CACHE_LIMIT = -1;
    private static TapjoyCache a;
    public static boolean unit_test_mode;
    private Context b;
    private TapjoyCacheMap c;
    private Vector d;
    private ExecutorService e;
    private File f;
    
    static {
        TapjoyCache.unit_test_mode = false;
        TapjoyCache.a = null;
    }
    
    public TapjoyCache(final Context b) {
        if (TapjoyCache.a == null || TapjoyCache.unit_test_mode) {
            TapjoyCache.a = this;
            this.b = b;
            this.c = new TapjoyCacheMap(b, -1);
            this.d = new Vector();
            this.e = Executors.newFixedThreadPool(5);
            if (Environment.getExternalStorageDirectory() != null) {
                TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory(), "tapjoy"));
                TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory(), "tjcache/tmp/"));
            }
            this.f = new File(this.b.getFilesDir() + "/Tapjoy/Cache/");
            if (!this.f.exists()) {
                if (this.f.mkdirs()) {
                    TapjoyLog.d("TapjoyCache", "Created directory at: " + this.f.getPath());
                }
                else {
                    TapjoyLog.e("TapjoyCache", "Error initalizing cache");
                    TapjoyCache.a = null;
                }
            }
            this.a();
        }
    }
    
    private void a() {
        final SharedPreferences sharedPreferences = this.b.getSharedPreferences("tapjoyCacheData", 0);
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        for (final Map.Entry<K, Object> entry : sharedPreferences.getAll().entrySet()) {
            final File file = new File((String)entry.getKey());
            if (file.exists() && file.isFile()) {
                final TapjoyCachedAssetData fromRawJSONString = TapjoyCachedAssetData.fromRawJSONString(entry.getValue().toString());
                if (fromRawJSONString != null) {
                    TapjoyLog.d("TapjoyCache", "Loaded Asset: " + fromRawJSONString.getAssetURL());
                    final String b = b(fromRawJSONString.getAssetURL());
                    if (b != null && !"".equals(b) && b.length() > 0) {
                        if (fromRawJSONString.getTimeOfDeathInSeconds() < System.currentTimeMillis() / 1000L) {
                            TapjoyLog.d("TapjoyCache", "Asset expired, removing from cache: " + fromRawJSONString.getAssetURL());
                            if (fromRawJSONString.getLocalFilePath() == null || fromRawJSONString.getLocalFilePath().length() <= 0) {
                                continue;
                            }
                            TapjoyUtil.deleteFileOrDirectory(new File(fromRawJSONString.getLocalFilePath()));
                        }
                        else {
                            this.c.put(b, fromRawJSONString);
                        }
                    }
                    else {
                        TapjoyLog.e("TapjoyCache", "Removing asset because deserialization failed.");
                        edit.remove((String)entry.getKey()).commit();
                    }
                }
                else {
                    TapjoyLog.e("TapjoyCache", "Removing asset because deserialization failed.");
                    edit.remove((String)entry.getKey()).commit();
                }
            }
            else {
                TapjoyLog.d("TapjoyCache", "Removing reference to missing asset: " + (String)entry.getKey());
                edit.remove((String)entry.getKey()).commit();
            }
        }
    }
    
    private static String b(String file) {
        String string = file;
        if (file.startsWith("//")) {
            string = "http:" + file;
        }
        try {
            file = new URL(string).getFile();
            return file;
        }
        catch (MalformedURLException ex) {
            TapjoyLog.e("TapjoyCache", "Invalid URL " + string);
            return "";
        }
    }
    
    public static TapjoyCache getInstance() {
        return TapjoyCache.a;
    }
    
    public static void setInstance(final TapjoyCache a) {
        TapjoyCache.a = a;
    }
    
    public Future cacheAssetFromJSONObject(final JSONObject jsonObject) {
        try {
            return this.cacheAssetFromURL(jsonObject.getString("url"), jsonObject.optString("offerId"), jsonObject.optLong("timeToLive"));
        }
        catch (JSONException ex) {
            TapjoyLog.e("TapjoyCache", "Required parameters to cache an asset from JSON is not present");
            return null;
        }
    }
    
    public Future cacheAssetFromURL(final String s, final String s2, final long n) {
        URL url;
        try {
            url = new URL(s);
            if (this.d.contains(b(s))) {
                TapjoyLog.d("TapjoyCache", "URL is already in the process of being cached: " + s);
                return null;
            }
        }
        catch (MalformedURLException ex) {
            TapjoyLog.d("TapjoyCache", "Invalid cache assetURL");
            return null;
        }
        return this.startCachingThread(url, s2, n);
    }
    
    public void cacheAssetGroup(final JSONArray jsonArray, final TJCacheListener tjCacheListener) {
        if (jsonArray != null && jsonArray.length() > 0) {
            new Thread() {
                @Override
                public final void run() {
                    TapjoyLog.d("TapjoyCache", "Starting to cache asset group size of " + jsonArray.length());
                    final ArrayList<Future<Boolean>> list = new ArrayList<Future<Boolean>>();
                    final int n = 1;
                    int i = 0;
                Label_0087_Outer:
                    while (i < jsonArray.length()) {
                        while (true) {
                            try {
                                final Future cacheAssetFromJSONObject = TapjoyCache.this.cacheAssetFromJSONObject(jsonArray.getJSONObject(i));
                                if (cacheAssetFromJSONObject != null) {
                                    list.add(cacheAssetFromJSONObject);
                                }
                                ++i;
                                continue Label_0087_Outer;
                            }
                            catch (JSONException ex3) {
                                TapjoyLog.e("TapjoyCache", "Failed to load JSON object from JSONArray");
                                continue;
                            }
                            break;
                        }
                        break;
                    }
                    final Iterator<Object> iterator = list.iterator();
                    int n2 = n;
                    while (iterator.hasNext()) {
                        while (true) {
                            final Future<Boolean> future = iterator.next();
                            while (true) {
                                Label_0251: {
                                    try {
                                        if (future.get()) {
                                            break Label_0251;
                                        }
                                        n2 = 2;
                                    }
                                    catch (InterruptedException ex) {
                                        TapjoyLog.e("TapjoyCache", "Caching thread failed: " + ex.toString());
                                        n2 = 2;
                                    }
                                    catch (ExecutionException ex2) {
                                        TapjoyLog.e("TapjoyCache", "Caching thread failed: " + ex2.toString());
                                        n2 = 2;
                                    }
                                    break;
                                }
                                continue;
                            }
                        }
                    }
                    TapjoyLog.d("TapjoyCache", "Finished caching group");
                    if (tjCacheListener != null) {
                        tjCacheListener.onCachingComplete(n2);
                    }
                }
            }.start();
        }
        else if (tjCacheListener != null) {
            tjCacheListener.onCachingComplete(1);
        }
    }
    
    public String cachedAssetsToJSON() {
        final Iterator<Map.Entry<Object, Object>> iterator = this.c.entrySet().iterator();
        final JSONObject jsonObject = new JSONObject();
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            try {
                jsonObject.put(entry.getKey().toString(), (Object)entry.getValue().toRawJSONString());
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return jsonObject.toString();
    }
    
    public void clearTapjoyCache() {
        TapjoyLog.d("TapjoyCache", "Cleaning Tapjoy cache!");
        TapjoyUtil.deleteFileOrDirectory(this.f);
        if (this.f.mkdirs()) {
            TapjoyLog.d("TapjoyCache", "Created new cache directory at: " + this.f.getPath());
        }
        this.c = new TapjoyCacheMap(this.b, -1);
    }
    
    public TapjoyCacheMap getCachedData() {
        return this.c;
    }
    
    public TapjoyCachedAssetData getCachedDataForURL(String b) {
        b = b(b);
        if (b != "") {
            return this.c.get(b);
        }
        return null;
    }
    
    public String getCachedOfferIDs() {
        String join = "";
        final ArrayList<String> list = new ArrayList<String>();
        if (this.c != null) {
            final Iterator<Map.Entry<Object, Object>> iterator = this.c.entrySet().iterator();
            while (iterator.hasNext()) {
                final String offerId = iterator.next().getValue().getOfferId();
                if (offerId != null && offerId.length() != 0 && !list.contains(offerId)) {
                    list.add(offerId);
                }
            }
            join = TextUtils.join((CharSequence)",", (Iterable)list);
        }
        return join;
    }
    
    public String getPathOfCachedURL(final String s) {
        final String b = b(s);
        String localURL = s;
        if (b != "") {
            localURL = s;
            if (this.c.containsKey(b)) {
                final TapjoyCachedAssetData tapjoyCachedAssetData = this.c.get(b);
                if (!new File(tapjoyCachedAssetData.getLocalFilePath()).exists()) {
                    getInstance().removeAssetFromCache(s);
                    return s;
                }
                localURL = tapjoyCachedAssetData.getLocalURL();
            }
        }
        return localURL;
    }
    
    public boolean isURLCached(final String s) {
        return this.c.get(b(s)) != null;
    }
    
    public boolean isURLDownloading(String b) {
        boolean b3;
        final boolean b2 = b3 = false;
        if (this.d != null) {
            b = b(b);
            b3 = b2;
            if (b != "") {
                b3 = b2;
                if (this.d.contains(b)) {
                    b3 = true;
                }
            }
        }
        return b3;
    }
    
    public void printCacheInformation() {
        TapjoyLog.d("TapjoyCache", "------------- Cache Data -------------");
        TapjoyLog.d("TapjoyCache", "Number of files in cache: " + this.c.size());
        TapjoyLog.d("TapjoyCache", "Cache Size: " + TapjoyUtil.fileOrDirectorySize(this.f));
        TapjoyLog.d("TapjoyCache", "--------------------------------------");
    }
    
    public boolean removeAssetFromCache(String b) {
        b = b(b);
        return b != "" && this.c.remove(b) != null;
    }
    
    public Future startCachingThread(final URL url, final String s, final long n) {
        if (url != null) {
            return this.e.submit((Callable<Object>)new CacheAssetThread(url, s, n));
        }
        return null;
    }
    
    public class CacheAssetThread implements Callable
    {
        private URL b;
        private String c;
        private long d;
        
        public CacheAssetThread(final URL b, final String c, final long d) {
            this.b = b;
            this.c = c;
            this.d = d;
            if (this.d <= 0L) {
                this.d = 86400L;
            }
            TapjoyCache.this.d.add(b(this.b.toString()));
        }
        
        @Override
        public Boolean call() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: astore          7
            //     3: aconst_null    
            //     4: astore          4
            //     6: aconst_null    
            //     7: astore          5
            //     9: aconst_null    
            //    10: astore_3       
            //    11: aload_0        
            //    12: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.b:Ljava/net/URL;
            //    15: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
            //    18: invokestatic    com/tapjoy/TapjoyCache.a:(Ljava/lang/String;)Ljava/lang/String;
            //    21: astore          8
            //    23: aload_0        
            //    24: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //    27: invokestatic    com/tapjoy/TapjoyCache.b:(Lcom/tapjoy/TapjoyCache;)Lcom/tapjoy/TapjoyCacheMap;
            //    30: aload           8
            //    32: invokevirtual   com/tapjoy/TapjoyCacheMap.containsKey:(Ljava/lang/Object;)Z
            //    35: ifeq            182
            //    38: new             Ljava/io/File;
            //    41: dup            
            //    42: aload_0        
            //    43: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //    46: invokestatic    com/tapjoy/TapjoyCache.b:(Lcom/tapjoy/TapjoyCache;)Lcom/tapjoy/TapjoyCacheMap;
            //    49: aload           8
            //    51: invokevirtual   com/tapjoy/TapjoyCacheMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
            //    54: checkcast       Lcom/tapjoy/TapjoyCachedAssetData;
            //    57: invokevirtual   com/tapjoy/TapjoyCachedAssetData.getLocalFilePath:()Ljava/lang/String;
            //    60: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
            //    63: invokevirtual   java/io/File.exists:()Z
            //    66: ifeq            173
            //    69: aload_0        
            //    70: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.d:J
            //    73: lconst_0       
            //    74: lcmp           
            //    75: ifeq            149
            //    78: aload_0        
            //    79: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //    82: invokestatic    com/tapjoy/TapjoyCache.b:(Lcom/tapjoy/TapjoyCache;)Lcom/tapjoy/TapjoyCacheMap;
            //    85: aload           8
            //    87: invokevirtual   com/tapjoy/TapjoyCacheMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
            //    90: checkcast       Lcom/tapjoy/TapjoyCachedAssetData;
            //    93: aload_0        
            //    94: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.d:J
            //    97: invokevirtual   com/tapjoy/TapjoyCachedAssetData.resetTimeToLive:(J)V
            //   100: ldc             "TapjoyCache"
            //   102: new             Ljava/lang/StringBuilder;
            //   105: dup            
            //   106: ldc             "Reseting time to live for "
            //   108: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   111: aload_0        
            //   112: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.b:Ljava/net/URL;
            //   115: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
            //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   121: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   124: invokestatic    com/tapjoy/TapjoyLog.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   127: aload_0        
            //   128: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //   131: invokestatic    com/tapjoy/TapjoyCache.a:(Lcom/tapjoy/TapjoyCache;)Ljava/util/Vector;
            //   134: aload           8
            //   136: invokevirtual   java/util/Vector.remove:(Ljava/lang/Object;)Z
            //   139: pop            
            //   140: iconst_1       
            //   141: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   144: astore          4
            //   146: aload           4
            //   148: areturn        
            //   149: aload_0        
            //   150: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //   153: invokestatic    com/tapjoy/TapjoyCache.b:(Lcom/tapjoy/TapjoyCache;)Lcom/tapjoy/TapjoyCacheMap;
            //   156: aload           8
            //   158: invokevirtual   com/tapjoy/TapjoyCacheMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
            //   161: checkcast       Lcom/tapjoy/TapjoyCachedAssetData;
            //   164: ldc2_w          86400
            //   167: invokevirtual   com/tapjoy/TapjoyCachedAssetData.resetTimeToLive:(J)V
            //   170: goto            100
            //   173: invokestatic    com/tapjoy/TapjoyCache.getInstance:()Lcom/tapjoy/TapjoyCache;
            //   176: aload           8
            //   178: invokevirtual   com/tapjoy/TapjoyCache.removeAssetFromCache:(Ljava/lang/String;)Z
            //   181: pop            
            //   182: invokestatic    java/lang/System.currentTimeMillis:()J
            //   185: pop2           
            //   186: new             Ljava/io/File;
            //   189: dup            
            //   190: new             Ljava/lang/StringBuilder;
            //   193: dup            
            //   194: invokespecial   java/lang/StringBuilder.<init>:()V
            //   197: aload_0        
            //   198: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //   201: invokestatic    com/tapjoy/TapjoyCache.c:(Lcom/tapjoy/TapjoyCache;)Ljava/io/File;
            //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   207: ldc             "/"
            //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   212: aload           8
            //   214: invokestatic    com/tapjoy/TapjoyUtil.SHA256:(Ljava/lang/String;)Ljava/lang/String;
            //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   220: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   223: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
            //   226: astore          9
            //   228: ldc             "TapjoyCache"
            //   230: new             Ljava/lang/StringBuilder;
            //   233: dup            
            //   234: ldc             "Downloading and caching asset from: "
            //   236: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   239: aload_0        
            //   240: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.b:Ljava/net/URL;
            //   243: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   246: ldc             " to "
            //   248: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   251: aload           9
            //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
            //   256: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   259: invokestatic    com/tapjoy/TapjoyLog.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   262: aload_0        
            //   263: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.b:Ljava/net/URL;
            //   266: invokestatic    com/tapjoy/internal/em.a:(Ljava/net/URL;)Ljava/net/URLConnection;
            //   269: astore_2       
            //   270: aload_2        
            //   271: sipush          15000
            //   274: invokevirtual   java/net/URLConnection.setConnectTimeout:(I)V
            //   277: aload_2        
            //   278: sipush          30000
            //   281: invokevirtual   java/net/URLConnection.setReadTimeout:(I)V
            //   284: aload_2        
            //   285: invokevirtual   java/net/URLConnection.connect:()V
            //   288: aload_2        
            //   289: instanceof      Ljava/net/HttpURLConnection;
            //   292: ifeq            443
            //   295: aload_2        
            //   296: checkcast       Ljava/net/HttpURLConnection;
            //   299: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
            //   302: istore_1       
            //   303: iload_1        
            //   304: sipush          200
            //   307: if_icmpeq       443
            //   310: new             Ljava/io/IOException;
            //   313: dup            
            //   314: new             Ljava/lang/StringBuilder;
            //   317: dup            
            //   318: ldc             "Unexpected response code: "
            //   320: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   323: iload_1        
            //   324: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   327: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   330: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
            //   333: athrow         
            //   334: astore          4
            //   336: aconst_null    
            //   337: astore_2       
            //   338: ldc             "TapjoyCache"
            //   340: new             Lcom/tapjoy/TapjoyErrorMessage;
            //   343: dup            
            //   344: getstatic       com/tapjoy/TapjoyErrorMessage$ErrorType.NETWORK_ERROR:Lcom/tapjoy/TapjoyErrorMessage$ErrorType;
            //   347: new             Ljava/lang/StringBuilder;
            //   350: dup            
            //   351: ldc             "Network timeout during caching: "
            //   353: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   356: aload           4
            //   358: invokevirtual   java/net/SocketTimeoutException.toString:()Ljava/lang/String;
            //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   364: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   367: invokespecial   com/tapjoy/TapjoyErrorMessage.<init>:(Lcom/tapjoy/TapjoyErrorMessage$ErrorType;Ljava/lang/String;)V
            //   370: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Lcom/tapjoy/TapjoyErrorMessage;)V
            //   373: aload_0        
            //   374: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //   377: invokestatic    com/tapjoy/TapjoyCache.a:(Lcom/tapjoy/TapjoyCache;)Ljava/util/Vector;
            //   380: aload           8
            //   382: invokevirtual   java/util/Vector.remove:(Ljava/lang/Object;)Z
            //   385: pop            
            //   386: aload           9
            //   388: invokestatic    com/tapjoy/TapjoyUtil.deleteFileOrDirectory:(Ljava/io/File;)V
            //   391: iconst_0       
            //   392: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   395: astore          5
            //   397: aload_3        
            //   398: ifnull          405
            //   401: aload_3        
            //   402: invokevirtual   java/io/BufferedInputStream.close:()V
            //   405: aload           5
            //   407: astore          4
            //   409: aload_2        
            //   410: ifnull          146
            //   413: aload_2        
            //   414: invokevirtual   java/io/BufferedOutputStream.close:()V
            //   417: aload           5
            //   419: areturn        
            //   420: astore_2       
            //   421: aload           5
            //   423: areturn        
            //   424: astore_2       
            //   425: aload_0        
            //   426: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //   429: invokestatic    com/tapjoy/TapjoyCache.a:(Lcom/tapjoy/TapjoyCache;)Ljava/util/Vector;
            //   432: aload           8
            //   434: invokevirtual   java/util/Vector.remove:(Ljava/lang/Object;)Z
            //   437: pop            
            //   438: iconst_0       
            //   439: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   442: areturn        
            //   443: new             Ljava/io/BufferedInputStream;
            //   446: dup            
            //   447: aload_2        
            //   448: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
            //   451: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
            //   454: astore_2       
            //   455: aload           5
            //   457: astore          4
            //   459: aload_2        
            //   460: astore          5
            //   462: new             Ljava/io/BufferedOutputStream;
            //   465: dup            
            //   466: new             Ljava/io/FileOutputStream;
            //   469: dup            
            //   470: aload           9
            //   472: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
            //   475: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
            //   478: astore_3       
            //   479: aload_2        
            //   480: aload_3        
            //   481: invokestatic    com/tapjoy/TapjoyUtil.writeFileToDevice:(Ljava/io/BufferedInputStream;Ljava/io/OutputStream;)V
            //   484: aload_2        
            //   485: invokevirtual   java/io/BufferedInputStream.close:()V
            //   488: aload_3        
            //   489: invokevirtual   java/io/BufferedOutputStream.close:()V
            //   492: new             Lcom/tapjoy/TapjoyCachedAssetData;
            //   495: dup            
            //   496: aload_0        
            //   497: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.b:Ljava/net/URL;
            //   500: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
            //   503: aload           9
            //   505: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
            //   508: aload_0        
            //   509: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.d:J
            //   512: invokespecial   com/tapjoy/TapjoyCachedAssetData.<init>:(Ljava/lang/String;Ljava/lang/String;J)V
            //   515: astore_2       
            //   516: aload_0        
            //   517: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.c:Ljava/lang/String;
            //   520: ifnull          531
            //   523: aload_2        
            //   524: aload_0        
            //   525: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.c:Ljava/lang/String;
            //   528: invokevirtual   com/tapjoy/TapjoyCachedAssetData.setOfferID:(Ljava/lang/String;)V
            //   531: aload_0        
            //   532: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //   535: invokestatic    com/tapjoy/TapjoyCache.b:(Lcom/tapjoy/TapjoyCache;)Lcom/tapjoy/TapjoyCacheMap;
            //   538: aload           8
            //   540: aload_2        
            //   541: invokevirtual   com/tapjoy/TapjoyCacheMap.put:(Ljava/lang/String;Lcom/tapjoy/TapjoyCachedAssetData;)Lcom/tapjoy/TapjoyCachedAssetData;
            //   544: pop            
            //   545: aload_0        
            //   546: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //   549: invokestatic    com/tapjoy/TapjoyCache.a:(Lcom/tapjoy/TapjoyCache;)Ljava/util/Vector;
            //   552: aload           8
            //   554: invokevirtual   java/util/Vector.remove:(Ljava/lang/Object;)Z
            //   557: pop            
            //   558: ldc             "TapjoyCache"
            //   560: new             Ljava/lang/StringBuilder;
            //   563: dup            
            //   564: ldc             "----- Download complete -----"
            //   566: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   569: aload_2        
            //   570: invokevirtual   com/tapjoy/TapjoyCachedAssetData.toString:()Ljava/lang/String;
            //   573: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   576: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   579: invokestatic    com/tapjoy/TapjoyLog.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   582: iconst_1       
            //   583: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   586: areturn        
            //   587: astore          6
            //   589: aconst_null    
            //   590: astore_2       
            //   591: aload           7
            //   593: astore_3       
            //   594: aload_3        
            //   595: astore          4
            //   597: aload_2        
            //   598: astore          5
            //   600: ldc             "TapjoyCache"
            //   602: new             Ljava/lang/StringBuilder;
            //   605: dup            
            //   606: ldc             "Error caching asset: "
            //   608: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   611: aload           6
            //   613: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
            //   616: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   619: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   622: invokestatic    com/tapjoy/TapjoyLog.e:(Ljava/lang/String;Ljava/lang/String;)V
            //   625: aload_3        
            //   626: astore          4
            //   628: aload_2        
            //   629: astore          5
            //   631: aload_0        
            //   632: getfield        com/tapjoy/TapjoyCache$CacheAssetThread.a:Lcom/tapjoy/TapjoyCache;
            //   635: invokestatic    com/tapjoy/TapjoyCache.a:(Lcom/tapjoy/TapjoyCache;)Ljava/util/Vector;
            //   638: aload           8
            //   640: invokevirtual   java/util/Vector.remove:(Ljava/lang/Object;)Z
            //   643: pop            
            //   644: aload_3        
            //   645: astore          4
            //   647: aload_2        
            //   648: astore          5
            //   650: aload           9
            //   652: invokestatic    com/tapjoy/TapjoyUtil.deleteFileOrDirectory:(Ljava/io/File;)V
            //   655: iconst_0       
            //   656: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   659: astore          5
            //   661: aload_2        
            //   662: ifnull          669
            //   665: aload_2        
            //   666: invokevirtual   java/io/BufferedInputStream.close:()V
            //   669: aload           5
            //   671: astore          4
            //   673: aload_3        
            //   674: ifnull          146
            //   677: aload_3        
            //   678: invokevirtual   java/io/BufferedOutputStream.close:()V
            //   681: aload           5
            //   683: areturn        
            //   684: astore_2       
            //   685: aload           5
            //   687: areturn        
            //   688: astore_3       
            //   689: aconst_null    
            //   690: astore_2       
            //   691: aload_2        
            //   692: ifnull          699
            //   695: aload_2        
            //   696: invokevirtual   java/io/BufferedInputStream.close:()V
            //   699: aload           4
            //   701: ifnull          709
            //   704: aload           4
            //   706: invokevirtual   java/io/BufferedOutputStream.close:()V
            //   709: aload_3        
            //   710: athrow         
            //   711: astore_2       
            //   712: goto            488
            //   715: astore_2       
            //   716: goto            492
            //   719: astore_3       
            //   720: goto            405
            //   723: astore_2       
            //   724: goto            669
            //   727: astore_2       
            //   728: goto            699
            //   731: astore_2       
            //   732: goto            709
            //   735: astore_3       
            //   736: aload           5
            //   738: astore_2       
            //   739: goto            691
            //   742: astore          5
            //   744: aload_3        
            //   745: astore          4
            //   747: aload           5
            //   749: astore_3       
            //   750: goto            691
            //   753: astore          4
            //   755: aload_3        
            //   756: astore          5
            //   758: aload           4
            //   760: astore_3       
            //   761: aload_2        
            //   762: astore          4
            //   764: aload           5
            //   766: astore_2       
            //   767: goto            691
            //   770: astore          6
            //   772: aload           7
            //   774: astore_3       
            //   775: goto            594
            //   778: astore          6
            //   780: goto            594
            //   783: astore          4
            //   785: aconst_null    
            //   786: astore          5
            //   788: aload_2        
            //   789: astore_3       
            //   790: aload           5
            //   792: astore_2       
            //   793: goto            338
            //   796: astore          4
            //   798: aload_2        
            //   799: astore          5
            //   801: aload_3        
            //   802: astore_2       
            //   803: aload           5
            //   805: astore_3       
            //   806: goto            338
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  186    228    424    443    Ljava/lang/Exception;
            //  262    303    334    338    Ljava/net/SocketTimeoutException;
            //  262    303    587    594    Ljava/lang/Exception;
            //  262    303    688    691    Any
            //  310    334    334    338    Ljava/net/SocketTimeoutException;
            //  310    334    587    594    Ljava/lang/Exception;
            //  310    334    688    691    Any
            //  338    391    753    770    Any
            //  401    405    719    723    Ljava/io/IOException;
            //  413    417    420    424    Ljava/io/IOException;
            //  443    455    334    338    Ljava/net/SocketTimeoutException;
            //  443    455    587    594    Ljava/lang/Exception;
            //  443    455    688    691    Any
            //  462    479    783    796    Ljava/net/SocketTimeoutException;
            //  462    479    770    778    Ljava/lang/Exception;
            //  462    479    735    742    Any
            //  479    484    796    809    Ljava/net/SocketTimeoutException;
            //  479    484    778    783    Ljava/lang/Exception;
            //  479    484    742    753    Any
            //  484    488    711    715    Ljava/io/IOException;
            //  488    492    715    719    Ljava/io/IOException;
            //  600    625    735    742    Any
            //  631    644    735    742    Any
            //  650    655    735    742    Any
            //  665    669    723    727    Ljava/io/IOException;
            //  677    681    684    688    Ljava/io/IOException;
            //  695    699    727    731    Ljava/io/IOException;
            //  704    709    731    735    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 388, Size: 388
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
            //     at java.util.ArrayList.get(ArrayList.java:429)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
}
