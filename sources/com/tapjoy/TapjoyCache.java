package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.TextUtils;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.em;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TapjoyCache {
    public static final String CACHE_DIRECTORY_NAME = "Tapjoy/Cache/";
    public static final int CACHE_LIMIT = -1;
    /* renamed from: a */
    private static TapjoyCache f7013a = null;
    public static boolean unit_test_mode = false;
    /* renamed from: b */
    private Context f7014b;
    /* renamed from: c */
    private TapjoyCacheMap f7015c;
    /* renamed from: d */
    private Vector f7016d;
    /* renamed from: e */
    private ExecutorService f7017e;
    /* renamed from: f */
    private File f7018f;

    public class CacheAssetThread implements Callable {
        /* renamed from: a */
        final /* synthetic */ TapjoyCache f7009a;
        /* renamed from: b */
        private URL f7010b;
        /* renamed from: c */
        private String f7011c;
        /* renamed from: d */
        private long f7012d;

        public CacheAssetThread(TapjoyCache this$0, URL assetURL, String offerId, long timeToLive) {
            this.f7009a = this$0;
            this.f7010b = assetURL;
            this.f7011c = offerId;
            this.f7012d = timeToLive;
            if (this.f7012d <= 0) {
                this.f7012d = 86400;
            }
            this$0.f7016d.add(TapjoyCache.m7091b(this.f7010b.toString()));
        }

        public Boolean call() {
            BufferedInputStream bufferedInputStream;
            SocketTimeoutException e;
            BufferedInputStream bufferedInputStream2;
            Boolean valueOf;
            Throwable th;
            Exception e2;
            BufferedOutputStream bufferedOutputStream = null;
            String a = TapjoyCache.m7091b(this.f7010b.toString());
            if (this.f7009a.f7015c.containsKey(a)) {
                if (new File(((TapjoyCachedAssetData) this.f7009a.f7015c.get(a)).getLocalFilePath()).exists()) {
                    if (this.f7012d != 0) {
                        ((TapjoyCachedAssetData) this.f7009a.f7015c.get(a)).resetTimeToLive(this.f7012d);
                    } else {
                        ((TapjoyCachedAssetData) this.f7009a.f7015c.get(a)).resetTimeToLive(86400);
                    }
                    TapjoyLog.m7126d("TapjoyCache", "Reseting time to live for " + this.f7010b.toString());
                    this.f7009a.f7016d.remove(a);
                    return Boolean.valueOf(true);
                }
                TapjoyCache.getInstance().removeAssetFromCache(a);
            }
            System.currentTimeMillis();
            try {
                File file = new File(this.f7009a.f7018f + "/" + TapjoyUtil.SHA256(a));
                TapjoyLog.m7126d("TapjoyCache", "Downloading and caching asset from: " + this.f7010b + " to " + file);
                BufferedOutputStream bufferedOutputStream2;
                try {
                    URLConnection a2 = em.m7652a(this.f7010b);
                    a2.setConnectTimeout(15000);
                    a2.setReadTimeout(30000);
                    a2.connect();
                    if (a2 instanceof HttpURLConnection) {
                        int responseCode = ((HttpURLConnection) a2).getResponseCode();
                        if (responseCode != 200) {
                            throw new IOException("Unexpected response code: " + responseCode);
                        }
                    }
                    bufferedInputStream = new BufferedInputStream(a2.getInputStream());
                    try {
                        bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
                        try {
                            TapjoyUtil.writeFileToDevice(bufferedInputStream, bufferedOutputStream2);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e3) {
                            }
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException e4) {
                            }
                            TapjoyCachedAssetData tapjoyCachedAssetData = new TapjoyCachedAssetData(this.f7010b.toString(), file.getAbsolutePath(), this.f7012d);
                            if (this.f7011c != null) {
                                tapjoyCachedAssetData.setOfferID(this.f7011c);
                            }
                            this.f7009a.f7015c.put(a, tapjoyCachedAssetData);
                            this.f7009a.f7016d.remove(a);
                            TapjoyLog.m7126d("TapjoyCache", "----- Download complete -----" + tapjoyCachedAssetData.toString());
                            return Boolean.valueOf(true);
                        } catch (SocketTimeoutException e5) {
                            e = e5;
                            bufferedInputStream2 = bufferedInputStream;
                            try {
                                TapjoyLog.m7127e("TapjoyCache", new TapjoyErrorMessage(ErrorType.NETWORK_ERROR, "Network timeout during caching: " + e.toString()));
                                this.f7009a.f7016d.remove(a);
                                TapjoyUtil.deleteFileOrDirectory(file);
                                valueOf = Boolean.valueOf(false);
                                if (bufferedInputStream2 != null) {
                                    try {
                                        bufferedInputStream2.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                if (bufferedOutputStream2 != null) {
                                    return valueOf;
                                }
                                try {
                                    bufferedOutputStream2.close();
                                    return valueOf;
                                } catch (IOException e7) {
                                    return valueOf;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedInputStream = bufferedInputStream2;
                                bufferedOutputStream = bufferedOutputStream2;
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e8) {
                                    }
                                }
                                if (bufferedOutputStream != null) {
                                    try {
                                        bufferedOutputStream.close();
                                    } catch (IOException e9) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e10) {
                            e2 = e10;
                            bufferedOutputStream = bufferedOutputStream2;
                            try {
                                TapjoyLog.m7128e("TapjoyCache", "Error caching asset: " + e2.toString());
                                this.f7009a.f7016d.remove(a);
                                TapjoyUtil.deleteFileOrDirectory(file);
                                valueOf = Boolean.valueOf(false);
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e11) {
                                    }
                                }
                                if (bufferedOutputStream != null) {
                                    return valueOf;
                                }
                                try {
                                    bufferedOutputStream.close();
                                    return valueOf;
                                } catch (IOException e12) {
                                    return valueOf;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (bufferedOutputStream != null) {
                                    bufferedOutputStream.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            bufferedOutputStream = bufferedOutputStream2;
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (SocketTimeoutException e13) {
                        e = e13;
                        bufferedOutputStream2 = null;
                        bufferedInputStream2 = bufferedInputStream;
                        TapjoyLog.m7127e("TapjoyCache", new TapjoyErrorMessage(ErrorType.NETWORK_ERROR, "Network timeout during caching: " + e.toString()));
                        this.f7009a.f7016d.remove(a);
                        TapjoyUtil.deleteFileOrDirectory(file);
                        valueOf = Boolean.valueOf(false);
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (bufferedOutputStream2 != null) {
                            return valueOf;
                        }
                        bufferedOutputStream2.close();
                        return valueOf;
                    } catch (Exception e14) {
                        e2 = e14;
                        TapjoyLog.m7128e("TapjoyCache", "Error caching asset: " + e2.toString());
                        this.f7009a.f7016d.remove(a);
                        TapjoyUtil.deleteFileOrDirectory(file);
                        valueOf = Boolean.valueOf(false);
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (bufferedOutputStream != null) {
                            return valueOf;
                        }
                        bufferedOutputStream.close();
                        return valueOf;
                    }
                } catch (SocketTimeoutException e15) {
                    e = e15;
                    bufferedOutputStream2 = null;
                    TapjoyLog.m7127e("TapjoyCache", new TapjoyErrorMessage(ErrorType.NETWORK_ERROR, "Network timeout during caching: " + e.toString()));
                    this.f7009a.f7016d.remove(a);
                    TapjoyUtil.deleteFileOrDirectory(file);
                    valueOf = Boolean.valueOf(false);
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        return valueOf;
                    }
                    bufferedOutputStream2.close();
                    return valueOf;
                } catch (Exception e16) {
                    e2 = e16;
                    bufferedInputStream = null;
                    TapjoyLog.m7128e("TapjoyCache", "Error caching asset: " + e2.toString());
                    this.f7009a.f7016d.remove(a);
                    TapjoyUtil.deleteFileOrDirectory(file);
                    valueOf = Boolean.valueOf(false);
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        return valueOf;
                    }
                    bufferedOutputStream.close();
                    return valueOf;
                } catch (Throwable th5) {
                    th = th5;
                    bufferedInputStream = null;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e17) {
                this.f7009a.f7016d.remove(a);
                return Boolean.valueOf(false);
            }
        }
    }

    public TapjoyCache(Context applicationContext) {
        if (f7013a == null || unit_test_mode) {
            f7013a = this;
            this.f7014b = applicationContext;
            this.f7015c = new TapjoyCacheMap(applicationContext, -1);
            this.f7016d = new Vector();
            this.f7017e = Executors.newFixedThreadPool(5);
            if (Environment.getExternalStorageDirectory() != null) {
                TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory(), "tapjoy"));
                TapjoyUtil.deleteFileOrDirectory(new File(Environment.getExternalStorageDirectory(), "tjcache/tmp/"));
            }
            this.f7018f = new File(this.f7014b.getFilesDir() + "/Tapjoy/Cache/");
            if (!this.f7018f.exists()) {
                if (this.f7018f.mkdirs()) {
                    TapjoyLog.m7126d("TapjoyCache", "Created directory at: " + this.f7018f.getPath());
                } else {
                    TapjoyLog.m7128e("TapjoyCache", "Error initalizing cache");
                    f7013a = null;
                }
            }
            m7089a();
        }
    }

    /* renamed from: a */
    private void m7089a() {
        SharedPreferences sharedPreferences = this.f7014b.getSharedPreferences(TapjoyConstants.PREF_TAPJOY_CACHE, 0);
        Editor edit = sharedPreferences.edit();
        for (Entry entry : sharedPreferences.getAll().entrySet()) {
            File file = new File((String) entry.getKey());
            if (file.exists() && file.isFile()) {
                TapjoyCachedAssetData fromRawJSONString = TapjoyCachedAssetData.fromRawJSONString(entry.getValue().toString());
                if (fromRawJSONString != null) {
                    TapjoyLog.m7126d("TapjoyCache", "Loaded Asset: " + fromRawJSONString.getAssetURL());
                    String b = m7091b(fromRawJSONString.getAssetURL());
                    if (b == null || "".equals(b) || b.length() <= 0) {
                        TapjoyLog.m7128e("TapjoyCache", "Removing asset because deserialization failed.");
                        edit.remove((String) entry.getKey()).commit();
                    } else if (fromRawJSONString.getTimeOfDeathInSeconds() < System.currentTimeMillis() / 1000) {
                        TapjoyLog.m7126d("TapjoyCache", "Asset expired, removing from cache: " + fromRawJSONString.getAssetURL());
                        if (fromRawJSONString.getLocalFilePath() != null && fromRawJSONString.getLocalFilePath().length() > 0) {
                            TapjoyUtil.deleteFileOrDirectory(new File(fromRawJSONString.getLocalFilePath()));
                        }
                    } else {
                        this.f7015c.put(b, fromRawJSONString);
                    }
                } else {
                    TapjoyLog.m7128e("TapjoyCache", "Removing asset because deserialization failed.");
                    edit.remove((String) entry.getKey()).commit();
                }
            } else {
                TapjoyLog.m7126d("TapjoyCache", "Removing reference to missing asset: " + ((String) entry.getKey()));
                edit.remove((String) entry.getKey()).commit();
            }
        }
    }

    public void cacheAssetGroup(final JSONArray assetGroup, final TJCacheListener tapjoyCacheListener) {
        if (assetGroup != null && assetGroup.length() > 0) {
            new Thread(this) {
                /* renamed from: c */
                final /* synthetic */ TapjoyCache f7008c;

                public final void run() {
                    int i;
                    TapjoyLog.m7126d("TapjoyCache", "Starting to cache asset group size of " + assetGroup.length());
                    List<Future> arrayList = new ArrayList();
                    int i2 = 1;
                    for (i = 0; i < assetGroup.length(); i++) {
                        try {
                            Future cacheAssetFromJSONObject = this.f7008c.cacheAssetFromJSONObject(assetGroup.getJSONObject(i));
                            if (cacheAssetFromJSONObject != null) {
                                arrayList.add(cacheAssetFromJSONObject);
                            }
                        } catch (JSONException e) {
                            TapjoyLog.m7128e("TapjoyCache", "Failed to load JSON object from JSONArray");
                        }
                    }
                    for (Future future : arrayList) {
                        try {
                            if (((Boolean) future.get()).booleanValue()) {
                                i = i2;
                            } else {
                                i = 2;
                            }
                            i2 = i;
                        } catch (InterruptedException e2) {
                            TapjoyLog.m7128e("TapjoyCache", "Caching thread failed: " + e2.toString());
                            i2 = 2;
                        } catch (ExecutionException e3) {
                            TapjoyLog.m7128e("TapjoyCache", "Caching thread failed: " + e3.toString());
                            i2 = 2;
                        }
                    }
                    TapjoyLog.m7126d("TapjoyCache", "Finished caching group");
                    if (tapjoyCacheListener != null) {
                        tapjoyCacheListener.onCachingComplete(i2);
                    }
                }
            }.start();
        } else if (tapjoyCacheListener != null) {
            tapjoyCacheListener.onCachingComplete(1);
        }
    }

    public Future cacheAssetFromJSONObject(JSONObject assetData) {
        try {
            String string = assetData.getString("url");
            Long.valueOf(86400);
            return cacheAssetFromURL(string, assetData.optString(TapjoyConstants.TJC_PLACEMENT_OFFER_ID), Long.valueOf(assetData.optLong(TapjoyConstants.TJC_TIME_TO_LIVE)).longValue());
        } catch (JSONException e) {
            TapjoyLog.m7128e("TapjoyCache", "Required parameters to cache an asset from JSON is not present");
            return null;
        }
    }

    public Future cacheAssetFromURL(String assetURLString, String offerId, long timeToLive) {
        try {
            URL url = new URL(assetURLString);
            if (!this.f7016d.contains(m7091b(assetURLString))) {
                return startCachingThread(url, offerId, timeToLive);
            }
            TapjoyLog.m7126d("TapjoyCache", "URL is already in the process of being cached: " + assetURLString);
            return null;
        } catch (MalformedURLException e) {
            TapjoyLog.m7126d("TapjoyCache", "Invalid cache assetURL");
            return null;
        }
    }

    /* renamed from: b */
    private static String m7091b(String str) {
        if (str.startsWith("//")) {
            str = "http:" + str;
        }
        try {
            return new URL(str).getFile();
        } catch (MalformedURLException e) {
            TapjoyLog.m7128e("TapjoyCache", "Invalid URL " + str);
            return "";
        }
    }

    public Future startCachingThread(URL assetURL, String offerId, long timeToLive) {
        if (assetURL != null) {
            return this.f7017e.submit(new CacheAssetThread(this, assetURL, offerId, timeToLive));
        }
        return null;
    }

    public void clearTapjoyCache() {
        TapjoyLog.m7126d("TapjoyCache", "Cleaning Tapjoy cache!");
        TapjoyUtil.deleteFileOrDirectory(this.f7018f);
        if (this.f7018f.mkdirs()) {
            TapjoyLog.m7126d("TapjoyCache", "Created new cache directory at: " + this.f7018f.getPath());
        }
        this.f7015c = new TapjoyCacheMap(this.f7014b, -1);
    }

    public boolean removeAssetFromCache(String url) {
        Object b = m7091b(url);
        return (b == "" || this.f7015c.remove(b) == null) ? false : true;
    }

    public boolean isURLDownloading(String url) {
        if (this.f7016d == null) {
            return false;
        }
        String b = m7091b(url);
        if (b == "" || !this.f7016d.contains(b)) {
            return false;
        }
        return true;
    }

    public boolean isURLCached(String url) {
        return this.f7015c.get(m7091b(url)) != null;
    }

    public TapjoyCachedAssetData getCachedDataForURL(String url) {
        String b = m7091b(url);
        if (b != "") {
            return (TapjoyCachedAssetData) this.f7015c.get(b);
        }
        return null;
    }

    public TapjoyCacheMap getCachedData() {
        return this.f7015c;
    }

    public String getPathOfCachedURL(String url) {
        String b = m7091b(url);
        if (b == "" || !this.f7015c.containsKey(b)) {
            return url;
        }
        TapjoyCachedAssetData tapjoyCachedAssetData = (TapjoyCachedAssetData) this.f7015c.get(b);
        if (new File(tapjoyCachedAssetData.getLocalFilePath()).exists()) {
            return tapjoyCachedAssetData.getLocalURL();
        }
        getInstance().removeAssetFromCache(url);
        return url;
    }

    public String cachedAssetsToJSON() {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : this.f7015c.entrySet()) {
            try {
                jSONObject.put(((String) entry.getKey()).toString(), ((TapjoyCachedAssetData) entry.getValue()).toRawJSONString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public String getCachedOfferIDs() {
        String str = "";
        Iterable arrayList = new ArrayList();
        if (this.f7015c == null) {
            return str;
        }
        for (Entry value : this.f7015c.entrySet()) {
            str = ((TapjoyCachedAssetData) value.getValue()).getOfferId();
            if (!(str == null || str.length() == 0 || arrayList.contains(str))) {
                arrayList.add(str);
            }
        }
        return TextUtils.join(",", arrayList);
    }

    public void printCacheInformation() {
        TapjoyLog.m7126d("TapjoyCache", "------------- Cache Data -------------");
        TapjoyLog.m7126d("TapjoyCache", "Number of files in cache: " + this.f7015c.size());
        TapjoyLog.m7126d("TapjoyCache", "Cache Size: " + TapjoyUtil.fileOrDirectorySize(this.f7018f));
        TapjoyLog.m7126d("TapjoyCache", "--------------------------------------");
    }

    public static TapjoyCache getInstance() {
        return f7013a;
    }

    public static void setInstance(TapjoyCache cache) {
        f7013a = cache;
    }
}
