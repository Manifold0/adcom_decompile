// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.network;

import android.location.Location;
import java.util.Iterator;
import java.util.List;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.content.ContentResolver;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.provider.Settings$SettingNotFoundException;
import android.provider.Settings$Secure;
import android.os.AsyncTask;
import com.vungle.warren.error.VungleError;
import com.google.gson.JsonArray;
import java.util.HashMap;
import android.os.Handler;
import java.util.concurrent.CountDownLatch;
import android.webkit.WebView;
import android.os.Looper;
import android.content.pm.PackageManager$NameNotFoundException;
import android.location.LocationManager;
import android.webkit.WebSettings;
import android.view.WindowManager;
import android.util.DisplayMetrics;
import android.telephony.TelephonyManager;
import android.os.Build$VERSION;
import android.annotation.SuppressLint;
import android.app.Application;
import com.moat.analytics.mobile.vng.MoatAnalytics;
import com.moat.analytics.mobile.vng.MoatOptions;
import okhttp3.HttpUrl;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.JsonUtil;
import retrofit2.Call;
import com.google.gson.JsonElement;
import android.support.annotation.NonNull;
import retrofit2.Callback;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.model.Cookie;
import retrofit2.Converter$Factory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit$Builder;
import java.io.IOException;
import okhttp3.Request;
import android.util.Log;
import android.text.TextUtils;
import okhttp3.ResponseBody;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response$Builder;
import java.util.concurrent.TimeUnit;
import okhttp3.Response;
import okhttp3.Interceptor$Chain;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient$Builder;
import java.util.concurrent.ConcurrentHashMap;
import android.os.Build;
import com.vungle.warren.Storage;
import java.util.Map;
import android.content.Context;
import java.lang.ref.WeakReference;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;

public class VungleApiClient
{
    private static String BASE_URL;
    private static String HEADER_UA;
    public static final String MANUFACTURER_AMAZON = "Amazon";
    private static final String TAG = "VungleApiClient";
    private static VungleApiClient _instance;
    private static VungleApi api;
    private static OkHttpClient client;
    private JsonObject appBody;
    private String cacheDir;
    private WeakReference<Context> context;
    private JsonObject deviceBody;
    private boolean enableMoat;
    private boolean limitAdTracking;
    private JsonObject location;
    private String newEndpoint;
    private String reportAdEndpoint;
    private String requestAdEndpoint;
    private Map<String, Long> retryAfterDataMap;
    private String riEndpoint;
    private boolean shouldTransmitIMEI;
    private Storage storage;
    private VungleApi timeoutApi;
    private String uaString;
    private JsonObject userBody;
    private String userImei;
    private boolean willPlayAdEnabled;
    private String willPlayAdEndpoint;
    private int willPlayAdTimeout;
    
    static {
        String header_UA;
        if ("Amazon".equals(Build.MANUFACTURER)) {
            header_UA = "VungleAmazon/6.3.17";
        }
        else {
            header_UA = "VungleDroid/6.3.17";
        }
        VungleApiClient.HEADER_UA = header_UA;
        VungleApiClient.BASE_URL = "https://ads.api.vungle.com/";
    }
    
    private VungleApiClient() {
        this.retryAfterDataMap = new ConcurrentHashMap<String, Long>();
        VungleApiClient.client = new OkHttpClient$Builder().addInterceptor((Interceptor)new Interceptor() {
            public Response intercept(final Interceptor$Chain interceptor$Chain) throws IOException {
                final Request request = interceptor$Chain.request();
                final String encodedPath = request.url().encodedPath();
                final Long n = VungleApiClient.this.retryAfterDataMap.get(encodedPath);
                Label_0153: {
                    if (n == null) {
                        break Label_0153;
                    }
                    final long seconds = TimeUnit.MILLISECONDS.toSeconds(n - System.currentTimeMillis());
                    if (seconds <= 0L) {
                        VungleApiClient.this.retryAfterDataMap.remove(encodedPath);
                        break Label_0153;
                    }
                    return new Response$Builder().request(request).addHeader("Retry-After", "" + seconds).code(500).protocol(Protocol.HTTP_1_1).message("Server is busy").body(ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"Error\":\"Retry-After\"}")).build();
                }
                final Response proceed = interceptor$Chain.proceed(request);
                Response build;
                if ((build = proceed) == null) {
                    return build;
                }
                final int code = proceed.code();
                if (code != 429 && code != 500 && code != 502) {
                    build = proceed;
                    if (code != 503) {
                        return build;
                    }
                }
                final String value = proceed.headers().get("Retry-After");
                build = proceed;
                if (TextUtils.isEmpty((CharSequence)value)) {
                    return build;
                }
                try {
                    final long long1 = Long.parseLong(value);
                    build = proceed;
                    if (long1 > 0L) {
                        VungleApiClient.this.retryAfterDataMap.put(encodedPath, 1000L * long1 + System.currentTimeMillis());
                        return proceed;
                    }
                    return build;
                }
                catch (NumberFormatException ex) {
                    Log.d("VungleApiClient", "Retry-After value is not an valid value");
                    return proceed;
                }
            }
        }).build();
        VungleApiClient.api = (VungleApi)new Retrofit$Builder().baseUrl(VungleApiClient.BASE_URL).addConverterFactory((Converter$Factory)GsonConverterFactory.create()).client(VungleApiClient.client).build().create((Class)VungleApi.class);
    }
    
    private void addAdvertIdInCookie(final String s) {
        final Cookie cookie = new Cookie("googleAdId");
        cookie.putValue("advertId", s);
        this.storage.save(cookie);
    }
    
    public static void addWrapperInfo(final WrapperFramework wrapperFramework, final String s) {
        if (wrapperFramework != null && wrapperFramework != WrapperFramework.none) {
            VungleApiClient.HEADER_UA = VungleApiClient.HEADER_UA + ";" + wrapperFramework;
            if (s != null && !s.isEmpty()) {
                VungleApiClient.HEADER_UA = VungleApiClient.HEADER_UA + "/" + s;
            }
        }
    }
    
    public static void config(@NonNull final Callback<JsonObject> callback) {
        try {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.add("device", (JsonElement)VungleApiClient._instance.getDeviceBody());
            jsonObject.add("app", (JsonElement)VungleApiClient._instance.appBody);
            jsonObject.add("user", (JsonElement)VungleApiClient._instance.getUserBody());
            final JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("is_auto_cached_enforced", Boolean.valueOf(false));
            jsonObject.add("request", (JsonElement)jsonObject2);
            final VungleApiClient instance = VungleApiClient._instance;
            VungleApiClient.api.config(VungleApiClient.HEADER_UA, jsonObject).enqueue((Callback)new Callback<JsonObject>() {
                public void onFailure(final Call<JsonObject> call, final Throwable t) {
                    Log.e("VungleApiClient", "Failed to configure.", t);
                    callback.onFailure((Call)call, t);
                }
                
                public void onResponse(final Call<JsonObject> call, final retrofit2.Response<JsonObject> response) {
                    if (!response.isSuccessful()) {
                        callback.onResponse((Call)call, (retrofit2.Response)response);
                    }
                    else {
                        final JsonObject jsonObject = (JsonObject)response.body();
                        Log.d("VungleApiClient", "Config Response: " + jsonObject);
                        if (JsonUtil.hasNonNull((JsonElement)jsonObject, "sleep")) {
                            String asString;
                            if (JsonUtil.hasNonNull((JsonElement)jsonObject, "info")) {
                                asString = jsonObject.get("info").getAsString();
                            }
                            else {
                                asString = "";
                            }
                            Log.e("VungleApiClient", "Error Initializing Vungle. Please try again. " + asString);
                            callback.onFailure((Call)call, (Throwable)new VungleException(3));
                            return;
                        }
                        if (!JsonUtil.hasNonNull((JsonElement)jsonObject, "endpoints")) {
                            Log.e("VungleApiClient", "Error Initializing Vungle. Please try again. ");
                            callback.onFailure((Call)call, (Throwable)new VungleException(3));
                            return;
                        }
                        final JsonObject asJsonObject = jsonObject.getAsJsonObject("endpoints");
                        final HttpUrl parse = HttpUrl.parse(asJsonObject.get("new").getAsString());
                        final HttpUrl parse2 = HttpUrl.parse(asJsonObject.get("ads").getAsString());
                        final HttpUrl parse3 = HttpUrl.parse(asJsonObject.get("will_play_ad").getAsString());
                        final HttpUrl parse4 = HttpUrl.parse(asJsonObject.get("report_ad").getAsString());
                        final HttpUrl parse5 = HttpUrl.parse(asJsonObject.get("ri").getAsString());
                        VungleApiClient._instance.newEndpoint = parse.toString();
                        VungleApiClient._instance.requestAdEndpoint = parse2.toString();
                        VungleApiClient._instance.willPlayAdEndpoint = parse3.toString();
                        VungleApiClient._instance.reportAdEndpoint = parse4.toString();
                        VungleApiClient._instance.riEndpoint = parse5.toString();
                        final JsonObject asJsonObject2 = jsonObject.getAsJsonObject("will_play_ad");
                        VungleApiClient._instance.willPlayAdTimeout = asJsonObject2.get("request_timeout").getAsInt();
                        VungleApiClient._instance.willPlayAdEnabled = asJsonObject2.get("enabled").getAsBoolean();
                        VungleApiClient._instance.enableMoat = jsonObject.getAsJsonObject("viewability").get("moat").getAsBoolean();
                        callback.onResponse((Call)call, (retrofit2.Response)response);
                        if (VungleApiClient._instance.willPlayAdEnabled) {
                            Log.v("VungleApiClient", "willPlayAd is enabled, generating a timeout client.");
                            VungleApiClient._instance;
                            VungleApiClient._instance.timeoutApi = (VungleApi)new Retrofit$Builder().client(VungleApiClient.client.newBuilder().readTimeout((long)VungleApiClient._instance.willPlayAdTimeout, TimeUnit.MILLISECONDS).build()).addConverterFactory((Converter$Factory)GsonConverterFactory.create()).baseUrl("https://api.vungle.com/").build().create((Class)VungleApi.class);
                        }
                        if (VungleApiClient.getMoatEnabled()) {
                            final MoatOptions moatOptions = new MoatOptions();
                            moatOptions.disableAdIdCollection = true;
                            moatOptions.disableLocationServices = true;
                            moatOptions.loggingEnabled = true;
                            MoatAnalytics.getInstance().start(moatOptions, (Application)((Context)VungleApiClient._instance.context.get()).getApplicationContext());
                        }
                    }
                }
            });
        }
        catch (IllegalStateException ex) {
            callback.onFailure((Call)null, (Throwable)ex);
        }
    }
    
    private String getAdvertIdFromCookie() {
        final Cookie cookie = this.storage.load("googleAdId", Cookie.class);
        if (cookie == null) {
            return null;
        }
        return cookie.getString("advertId");
    }
    
    private String getConnectionTypeDetail(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN";
            }
            case 7: {
                return "1xRTT";
            }
            case 4: {
                return "CDMA";
            }
            case 2: {
                return "EDGE";
            }
            case 14: {
                return "EHPRD";
            }
            case 5: {
                return "EVDO_0";
            }
            case 6: {
                return "EVDO_A";
            }
            case 12: {
                return "EVDO_B";
            }
            case 1: {
                return "GPRS";
            }
            case 8: {
                return "HSDPA";
            }
            case 10: {
                return "HSPA";
            }
            case 15: {
                return "HSPAP";
            }
            case 9: {
                return "HSUPA";
            }
            case 11: {
                return "IDEN";
            }
            case 13: {
                return "LTE";
            }
            case 3: {
                return "UMTS";
            }
            case 16: {
                return "GSM";
            }
            case 18: {
                return "IWLAN";
            }
            case 17: {
                return "TD_SCDMA";
            }
        }
    }
    
    @SuppressLint({ "HardwareIds" })
    private JsonObject getDeviceBody() throws IllegalStateException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //     4: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //     7: ifnonnull       21
        //    10: new             Ljava/lang/IllegalStateException;
        //    13: dup            
        //    14: ldc_w           "Context is null, SDK not initialized"
        //    17: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    20: athrow         
        //    21: new             Lcom/google/gson/JsonObject;
        //    24: dup            
        //    25: invokespecial   com/google/gson/JsonObject.<init>:()V
        //    28: astore          13
        //    30: getstatic       com/vungle/warren/network/VungleApiClient._instance:Lcom/vungle/warren/network/VungleApiClient;
        //    33: invokespecial   com/vungle/warren/network/VungleApiClient.getAdvertIdFromCookie:()Ljava/lang/String;
        //    36: astore          10
        //    38: aload           10
        //    40: ifnull          203
        //    43: ldc             "Amazon"
        //    45: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //    48: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    51: ifeq            190
        //    54: ldc_w           "amazon_advertising_id"
        //    57: astore          9
        //    59: aload           13
        //    61: aload           9
        //    63: aload           10
        //    65: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    68: aload_0        
        //    69: getfield        com/vungle/warren/network/VungleApiClient.deviceBody:Lcom/google/gson/JsonObject;
        //    72: ldc_w           "ifa"
        //    75: aload           10
        //    77: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    80: aload_0        
        //    81: getfield        com/vungle/warren/network/VungleApiClient.deviceBody:Lcom/google/gson/JsonObject;
        //    84: astore          9
        //    86: aload_0        
        //    87: getfield        com/vungle/warren/network/VungleApiClient.limitAdTracking:Z
        //    90: ifeq            198
        //    93: iconst_1       
        //    94: istore_1       
        //    95: aload           9
        //    97: ldc_w           "lmt"
        //   100: iload_1        
        //   101: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   104: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   107: new             Lcom/vungle/warren/network/VungleApiClient$AdvertisingIDTask;
        //   110: dup            
        //   111: aconst_null    
        //   112: invokespecial   com/vungle/warren/network/VungleApiClient$AdvertisingIDTask.<init>:(Lcom/vungle/warren/network/VungleApiClient$1;)V
        //   115: iconst_0       
        //   116: anewarray       Ljava/lang/Void;
        //   119: invokevirtual   com/vungle/warren/network/VungleApiClient$AdvertisingIDTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   122: pop            
        //   123: iconst_0       
        //   124: istore_3       
        //   125: getstatic       com/vungle/warren/network/VungleApiClient._instance:Lcom/vungle/warren/network/VungleApiClient;
        //   128: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   131: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   134: checkcast       Landroid/content/Context;
        //   137: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //   140: sipush          128
        //   143: invokevirtual   android/content/pm/PackageManager.getInstalledPackages:(I)Ljava/util/List;
        //   146: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   151: astore          9
        //   153: aload           9
        //   155: invokeinterface java/util/Iterator.hasNext:()Z
        //   160: ifeq            253
        //   163: aload           9
        //   165: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   170: checkcast       Landroid/content/pm/PackageInfo;
        //   173: getfield        android/content/pm/PackageInfo.packageName:Ljava/lang/String;
        //   176: ldc_w           "com.google.android.gms"
        //   179: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   182: ifeq            153
        //   185: iconst_1       
        //   186: istore_3       
        //   187: goto            153
        //   190: ldc_w           "gaid"
        //   193: astore          9
        //   195: goto            59
        //   198: iconst_0       
        //   199: istore_1       
        //   200: goto            95
        //   203: aload_0        
        //   204: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   207: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   210: checkcast       Landroid/content/Context;
        //   213: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //   216: ldc_w           "android_id"
        //   219: invokestatic    android/provider/Settings$Secure.getString:(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
        //   222: astore          9
        //   224: aload_0        
        //   225: getfield        com/vungle/warren/network/VungleApiClient.deviceBody:Lcom/google/gson/JsonObject;
        //   228: ldc_w           "ifa"
        //   231: aload           9
        //   233: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   236: aload_0        
        //   237: getfield        com/vungle/warren/network/VungleApiClient.deviceBody:Lcom/google/gson/JsonObject;
        //   240: ldc_w           "lmt"
        //   243: iconst_0       
        //   244: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   247: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   250: goto            107
        //   253: aload_0        
        //   254: getfield        com/vungle/warren/network/VungleApiClient.deviceBody:Lcom/google/gson/JsonObject;
        //   257: ldc_w           "is_google_play_services_available"
        //   260: iload_3        
        //   261: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   264: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Boolean;)V
        //   267: aload_0        
        //   268: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   271: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   274: checkcast       Landroid/content/Context;
        //   277: aconst_null    
        //   278: new             Landroid/content/IntentFilter;
        //   281: dup            
        //   282: ldc_w           "android.intent.action.BATTERY_CHANGED"
        //   285: invokespecial   android/content/IntentFilter.<init>:(Ljava/lang/String;)V
        //   288: invokevirtual   android/content/Context.registerReceiver:(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
        //   291: astore          9
        //   293: aload           9
        //   295: ldc_w           "level"
        //   298: iconst_m1      
        //   299: invokevirtual   android/content/Intent.getIntExtra:(Ljava/lang/String;I)I
        //   302: istore_1       
        //   303: aload           9
        //   305: ldc_w           "scale"
        //   308: iconst_m1      
        //   309: invokevirtual   android/content/Intent.getIntExtra:(Ljava/lang/String;I)I
        //   312: istore_2       
        //   313: iload_1        
        //   314: ifle            337
        //   317: iload_2        
        //   318: ifle            337
        //   321: aload           13
        //   323: ldc_w           "battery_level"
        //   326: iload_1        
        //   327: i2f            
        //   328: iload_2        
        //   329: i2f            
        //   330: fdiv           
        //   331: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //   334: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   337: aload           9
        //   339: ldc_w           "status"
        //   342: iconst_m1      
        //   343: invokevirtual   android/content/Intent.getIntExtra:(Ljava/lang/String;I)I
        //   346: istore_1       
        //   347: iload_1        
        //   348: iconst_m1      
        //   349: if_icmpne       1182
        //   352: ldc_w           "UNKNOWN"
        //   355: astore          9
        //   357: aload           13
        //   359: ldc_w           "battery_state"
        //   362: aload           9
        //   364: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   367: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   370: bipush          21
        //   372: if_icmplt       423
        //   375: aload_0        
        //   376: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   379: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   382: checkcast       Landroid/content/Context;
        //   385: ldc_w           "power"
        //   388: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   391: checkcast       Landroid/os/PowerManager;
        //   394: astore          9
        //   396: aload           9
        //   398: ifnull          1272
        //   401: aload           9
        //   403: invokevirtual   android/os/PowerManager.isPowerSaveMode:()Z
        //   406: ifeq            1272
        //   409: iconst_1       
        //   410: istore_1       
        //   411: aload           13
        //   413: ldc_w           "battery_saver_enabled"
        //   416: iload_1        
        //   417: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   420: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   423: getstatic       com/vungle/warren/network/VungleApiClient._instance:Lcom/vungle/warren/network/VungleApiClient;
        //   426: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   429: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   432: checkcast       Landroid/content/Context;
        //   435: ldc_w           "android.permission.ACCESS_NETWORK_STATE"
        //   438: invokestatic    android/support/v4/content/PermissionChecker.checkCallingOrSelfPermission:(Landroid/content/Context;Ljava/lang/String;)I
        //   441: ifne            671
        //   444: ldc_w           "NONE"
        //   447: astore          11
        //   449: ldc_w           "NONE"
        //   452: astore          12
        //   454: aload_0        
        //   455: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   458: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   461: checkcast       Landroid/content/Context;
        //   464: ldc_w           "connectivity"
        //   467: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   470: checkcast       Landroid/net/ConnectivityManager;
        //   473: astore          14
        //   475: aload           11
        //   477: astore          9
        //   479: aload           12
        //   481: astore          10
        //   483: aload           14
        //   485: ifnull          578
        //   488: aload           14
        //   490: invokevirtual   android/net/ConnectivityManager.getActiveNetworkInfo:()Landroid/net/NetworkInfo;
        //   493: astore          15
        //   495: aload           11
        //   497: astore          9
        //   499: aload           12
        //   501: astore          10
        //   503: aload           15
        //   505: ifnull          578
        //   508: aload           15
        //   510: invokevirtual   android/net/NetworkInfo.getType:()I
        //   513: tableswitch {
        //                0: 1303
        //                1: 1322
        //                2: 568
        //                3: 568
        //                4: 568
        //                5: 568
        //                6: 1322
        //                7: 1277
        //                8: 568
        //                9: 1290
        //          default: 568
        //        }
        //   568: ldc_w           "UNKNOWN"
        //   571: astore          9
        //   573: ldc_w           "UNKNOWN"
        //   576: astore          10
        //   578: aload           13
        //   580: ldc_w           "connection_type"
        //   583: aload           9
        //   585: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   588: aload           13
        //   590: ldc_w           "connection_type_detail"
        //   593: aload           10
        //   595: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   598: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   601: bipush          24
        //   603: if_icmplt       671
        //   606: aload           14
        //   608: invokevirtual   android/net/ConnectivityManager.isActiveNetworkMetered:()Z
        //   611: ifeq            1359
        //   614: aload           14
        //   616: invokevirtual   android/net/ConnectivityManager.getRestrictBackgroundStatus:()I
        //   619: tableswitch {
        //                2: 1351
        //                3: 1343
        //                4: 1335
        //          default: 644
        //        }
        //   644: ldc_w           "UNKNOWN"
        //   647: astore          9
        //   649: aload           13
        //   651: ldc_w           "data_saver_status"
        //   654: aload           9
        //   656: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   659: aload           13
        //   661: ldc_w           "network_metered"
        //   664: iconst_1       
        //   665: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   668: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   671: aload           13
        //   673: ldc_w           "locale"
        //   676: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //   679: invokevirtual   java/util/Locale.toString:()Ljava/lang/String;
        //   682: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   685: aload           13
        //   687: ldc_w           "language"
        //   690: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //   693: invokevirtual   java/util/Locale.getLanguage:()Ljava/lang/String;
        //   696: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   699: aload           13
        //   701: ldc_w           "time_zone"
        //   704: invokestatic    java/util/TimeZone.getDefault:()Ljava/util/TimeZone;
        //   707: invokevirtual   java/util/TimeZone.getID:()Ljava/lang/String;
        //   710: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   713: aload_0        
        //   714: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   717: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   720: ifnull          1644
        //   723: aload_0        
        //   724: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   727: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   730: checkcast       Landroid/content/Context;
        //   733: ldc_w           "audio"
        //   736: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //   739: checkcast       Landroid/media/AudioManager;
        //   742: astore          9
        //   744: aload           9
        //   746: ifnull          797
        //   749: aload           9
        //   751: iconst_3       
        //   752: invokevirtual   android/media/AudioManager.getStreamMaxVolume:(I)I
        //   755: istore_1       
        //   756: aload           9
        //   758: iconst_3       
        //   759: invokevirtual   android/media/AudioManager.getStreamVolume:(I)I
        //   762: istore_2       
        //   763: aload           13
        //   765: ldc_w           "volume_level"
        //   768: iload_2        
        //   769: i2f            
        //   770: iload_1        
        //   771: i2f            
        //   772: fdiv           
        //   773: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //   776: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   779: iload_2        
        //   780: ifle            1385
        //   783: iconst_1       
        //   784: istore_1       
        //   785: aload           13
        //   787: ldc_w           "sound_enabled"
        //   790: iload_1        
        //   791: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   794: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   797: new             Ljava/io/File;
        //   800: dup            
        //   801: aload_0        
        //   802: getfield        com/vungle/warren/network/VungleApiClient.cacheDir:Ljava/lang/String;
        //   805: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   808: astore          9
        //   810: aload           9
        //   812: invokevirtual   java/io/File.exists:()Z
        //   815: ifeq            1390
        //   818: aload           9
        //   820: invokevirtual   java/io/File.isDirectory:()Z
        //   823: ifeq            1390
        //   826: iconst_1       
        //   827: istore_3       
        //   828: iload_3        
        //   829: istore          4
        //   831: iload_3        
        //   832: ifne            850
        //   835: aload           9
        //   837: invokevirtual   java/io/File.exists:()Z
        //   840: ifne            1395
        //   843: aload           9
        //   845: invokevirtual   java/io/File.mkdir:()Z
        //   848: istore          4
        //   850: iload           4
        //   852: ifeq            938
        //   855: new             Landroid/os/StatFs;
        //   858: dup            
        //   859: aload_0        
        //   860: getfield        com/vungle/warren/network/VungleApiClient.cacheDir:Ljava/lang/String;
        //   863: invokespecial   android/os/StatFs.<init>:(Ljava/lang/String;)V
        //   866: astore          10
        //   868: ldc2_w          -1
        //   871: lstore          7
        //   873: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   876: bipush          26
        //   878: if_icmplt       1445
        //   881: aload_0        
        //   882: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   885: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   888: checkcast       Landroid/content/Context;
        //   891: ldc_w           Landroid/os/storage/StorageManager;.class
        //   894: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/Class;)Ljava/lang/Object;
        //   897: checkcast       Landroid/os/storage/StorageManager;
        //   900: astore          10
        //   902: lload           7
        //   904: lstore          5
        //   906: aload           10
        //   908: ifnull          925
        //   911: aload           10
        //   913: aload           10
        //   915: aload           9
        //   917: invokevirtual   android/os/storage/StorageManager.getUuidForPath:(Ljava/io/File;)Ljava/util/UUID;
        //   920: invokevirtual   android/os/storage/StorageManager.getCacheQuotaBytes:(Ljava/util/UUID;)J
        //   923: lstore          5
        //   925: aload           13
        //   927: ldc_w           "storage_bytes_available"
        //   930: lload           5
        //   932: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   935: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   938: ldc             "Amazon"
        //   940: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //   943: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   946: ifeq            1486
        //   949: aload_0        
        //   950: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //   953: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   956: checkcast       Landroid/content/Context;
        //   959: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //   962: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //   965: ldc_w           "amazon.hardware.fire_tv"
        //   968: invokevirtual   android/content/pm/PackageManager.hasSystemFeature:(Ljava/lang/String;)Z
        //   971: istore_3       
        //   972: aload           13
        //   974: ldc_w           "is_tv"
        //   977: iload_3        
        //   978: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   981: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Boolean;)V
        //   984: aload           13
        //   986: ldc_w           "os_api_level"
        //   989: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   992: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   995: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //   998: iconst_0       
        //   999: istore          4
        //  1001: getstatic       android/os/Build$VERSION.SDK_INT:I
        //  1004: bipush          26
        //  1006: if_icmplt       1590
        //  1009: iload           4
        //  1011: istore_3       
        //  1012: aload_0        
        //  1013: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //  1016: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //  1019: checkcast       Landroid/content/Context;
        //  1022: ldc_w           "android.permission.REQUEST_INSTALL_PACKAGES"
        //  1025: invokevirtual   android/content/Context.checkCallingOrSelfPermission:(Ljava/lang/String;)I
        //  1028: ifne            1051
        //  1031: aload_0        
        //  1032: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //  1035: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //  1038: checkcast       Landroid/content/Context;
        //  1041: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //  1044: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //  1047: invokevirtual   android/content/pm/PackageManager.canRequestPackageInstalls:()Z
        //  1050: istore_3       
        //  1051: aload           13
        //  1053: ldc_w           "is_sideload_enabled"
        //  1056: iload_3        
        //  1057: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1060: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Boolean;)V
        //  1063: invokestatic    android/os/Environment.getExternalStorageState:()Ljava/lang/String;
        //  1066: ldc_w           "mounted"
        //  1069: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1072: ifeq            1671
        //  1075: iconst_1       
        //  1076: istore_1       
        //  1077: aload           13
        //  1079: ldc_w           "sd_card_available"
        //  1082: iload_1        
        //  1083: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1086: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //  1089: aload           13
        //  1091: ldc_w           "os_name"
        //  1094: getstatic       android/os/Build.FINGERPRINT:Ljava/lang/String;
        //  1097: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //  1100: aload           13
        //  1102: ldc_w           "vduid"
        //  1105: ldc_w           ""
        //  1108: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //  1111: getstatic       com/vungle/warren/network/VungleApiClient._instance:Lcom/vungle/warren/network/VungleApiClient;
        //  1114: getfield        com/vungle/warren/network/VungleApiClient.location:Lcom/google/gson/JsonObject;
        //  1117: ifnull          1134
        //  1120: aload           13
        //  1122: ldc_w           "location"
        //  1125: getstatic       com/vungle/warren/network/VungleApiClient._instance:Lcom/vungle/warren/network/VungleApiClient;
        //  1128: getfield        com/vungle/warren/network/VungleApiClient.location:Lcom/google/gson/JsonObject;
        //  1131: invokevirtual   com/google/gson/JsonObject.add:(Ljava/lang/String;Lcom/google/gson/JsonElement;)V
        //  1134: aload_0        
        //  1135: getfield        com/vungle/warren/network/VungleApiClient.deviceBody:Lcom/google/gson/JsonObject;
        //  1138: ldc_w           "ext"
        //  1141: invokevirtual   com/google/gson/JsonObject.getAsJsonObject:(Ljava/lang/String;)Lcom/google/gson/JsonObject;
        //  1144: ldc_w           "vungle"
        //  1147: invokevirtual   com/google/gson/JsonObject.getAsJsonObject:(Ljava/lang/String;)Lcom/google/gson/JsonObject;
        //  1150: astore          10
        //  1152: ldc             "Amazon"
        //  1154: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //  1157: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1160: ifeq            1676
        //  1163: ldc_w           "amazon"
        //  1166: astore          9
        //  1168: aload           10
        //  1170: aload           9
        //  1172: aload           13
        //  1174: invokevirtual   com/google/gson/JsonObject.add:(Ljava/lang/String;Lcom/google/gson/JsonElement;)V
        //  1177: aload_0        
        //  1178: getfield        com/vungle/warren/network/VungleApiClient.deviceBody:Lcom/google/gson/JsonObject;
        //  1181: areturn        
        //  1182: iload_1        
        //  1183: iconst_2       
        //  1184: if_icmpeq       1192
        //  1187: iload_1        
        //  1188: iconst_5       
        //  1189: if_icmpne       1264
        //  1192: aload           9
        //  1194: ldc_w           "plugged"
        //  1197: iconst_m1      
        //  1198: invokevirtual   android/content/Intent.getIntExtra:(Ljava/lang/String;I)I
        //  1201: tableswitch {
        //                2: 1248
        //                3: 1240
        //                4: 1232
        //                5: 1256
        //          default: 1232
        //        }
        //  1232: ldc_w           "BATTERY_PLUGGED_OTHERS"
        //  1235: astore          9
        //  1237: goto            357
        //  1240: ldc_w           "BATTERY_PLUGGED_USB"
        //  1243: astore          9
        //  1245: goto            357
        //  1248: ldc_w           "BATTERY_PLUGGED_AC"
        //  1251: astore          9
        //  1253: goto            357
        //  1256: ldc_w           "BATTERY_PLUGGED_WIRELESS"
        //  1259: astore          9
        //  1261: goto            357
        //  1264: ldc_w           "NOT_CHARGING"
        //  1267: astore          9
        //  1269: goto            357
        //  1272: iconst_0       
        //  1273: istore_1       
        //  1274: goto            411
        //  1277: ldc_w           "BLUETOOTH"
        //  1280: astore          9
        //  1282: ldc_w           "BLUETOOTH"
        //  1285: astore          10
        //  1287: goto            578
        //  1290: ldc_w           "ETHERNET"
        //  1293: astore          9
        //  1295: ldc_w           "ETHERNET"
        //  1298: astore          10
        //  1300: goto            578
        //  1303: ldc_w           "MOBILE"
        //  1306: astore          9
        //  1308: aload_0        
        //  1309: aload           15
        //  1311: invokevirtual   android/net/NetworkInfo.getSubtype:()I
        //  1314: invokespecial   com/vungle/warren/network/VungleApiClient.getConnectionTypeDetail:(I)Ljava/lang/String;
        //  1317: astore          10
        //  1319: goto            578
        //  1322: ldc_w           "WIFI"
        //  1325: astore          9
        //  1327: ldc_w           "WIFI"
        //  1330: astore          10
        //  1332: goto            578
        //  1335: ldc_w           "ENABLED"
        //  1338: astore          9
        //  1340: goto            649
        //  1343: ldc_w           "WHITELISTED"
        //  1346: astore          9
        //  1348: goto            649
        //  1351: ldc_w           "DISABLED"
        //  1354: astore          9
        //  1356: goto            649
        //  1359: aload           13
        //  1361: ldc_w           "data_saver_status"
        //  1364: ldc_w           "NOT_APPLICABLE"
        //  1367: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //  1370: aload           13
        //  1372: ldc_w           "network_metered"
        //  1375: iconst_0       
        //  1376: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1379: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //  1382: goto            671
        //  1385: iconst_0       
        //  1386: istore_1       
        //  1387: goto            785
        //  1390: iconst_0       
        //  1391: istore_3       
        //  1392: goto            828
        //  1395: iload_3        
        //  1396: istore          4
        //  1398: aload           9
        //  1400: invokevirtual   java/io/File.isDirectory:()Z
        //  1403: ifne            850
        //  1406: iload_3        
        //  1407: istore          4
        //  1409: aload           9
        //  1411: invokevirtual   java/io/File.delete:()Z
        //  1414: ifeq            850
        //  1417: aload           9
        //  1419: invokevirtual   java/io/File.mkdir:()Z
        //  1422: istore          4
        //  1424: goto            850
        //  1427: astore          9
        //  1429: ldc             "VungleApiClient"
        //  1431: ldc_w           "Unable to check available bytes"
        //  1434: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1437: pop            
        //  1438: lload           7
        //  1440: lstore          5
        //  1442: goto            925
        //  1445: getstatic       android/os/Build$VERSION.SDK_INT:I
        //  1448: bipush          18
        //  1450: if_icmplt       1469
        //  1453: aload           10
        //  1455: invokevirtual   android/os/StatFs.getBlockSizeLong:()J
        //  1458: aload           10
        //  1460: invokevirtual   android/os/StatFs.getAvailableBlocksLong:()J
        //  1463: lmul           
        //  1464: lstore          5
        //  1466: goto            925
        //  1469: aload           10
        //  1471: invokevirtual   android/os/StatFs.getBlockSize:()I
        //  1474: aload           10
        //  1476: invokevirtual   android/os/StatFs.getAvailableBlocks:()I
        //  1479: imul           
        //  1480: i2l            
        //  1481: lstore          5
        //  1483: goto            925
        //  1486: getstatic       android/os/Build$VERSION.SDK_INT:I
        //  1489: bipush          23
        //  1491: if_icmplt       1530
        //  1494: aload_0        
        //  1495: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //  1498: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //  1501: checkcast       Landroid/content/Context;
        //  1504: ldc_w           "uimode"
        //  1507: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
        //  1510: checkcast       Landroid/app/UiModeManager;
        //  1513: invokevirtual   android/app/UiModeManager.getCurrentModeType:()I
        //  1516: iconst_4       
        //  1517: if_icmpne       1525
        //  1520: iconst_1       
        //  1521: istore_3       
        //  1522: goto            972
        //  1525: iconst_0       
        //  1526: istore_3       
        //  1527: goto            1522
        //  1530: aload_0        
        //  1531: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //  1534: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //  1537: checkcast       Landroid/content/Context;
        //  1540: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //  1543: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //  1546: ldc_w           "com.google.android.tv"
        //  1549: invokevirtual   android/content/pm/PackageManager.hasSystemFeature:(Ljava/lang/String;)Z
        //  1552: ifne            1580
        //  1555: aload_0        
        //  1556: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //  1559: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //  1562: checkcast       Landroid/content/Context;
        //  1565: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //  1568: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //  1571: ldc_w           "android.hardware.touchscreen"
        //  1574: invokevirtual   android/content/pm/PackageManager.hasSystemFeature:(Ljava/lang/String;)Z
        //  1577: ifne            1585
        //  1580: iconst_1       
        //  1581: istore_3       
        //  1582: goto            972
        //  1585: iconst_0       
        //  1586: istore_3       
        //  1587: goto            1582
        //  1590: aload_0        
        //  1591: getfield        com/vungle/warren/network/VungleApiClient.context:Ljava/lang/ref/WeakReference;
        //  1594: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //  1597: checkcast       Landroid/content/Context;
        //  1600: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //  1603: ldc_w           "install_non_market_apps"
        //  1606: invokestatic    android/provider/Settings$Secure.getInt:(Landroid/content/ContentResolver;Ljava/lang/String;)I
        //  1609: istore_1       
        //  1610: iload_1        
        //  1611: iconst_1       
        //  1612: if_icmpne       1620
        //  1615: iconst_1       
        //  1616: istore_3       
        //  1617: goto            1051
        //  1620: iconst_0       
        //  1621: istore_3       
        //  1622: goto            1617
        //  1625: astore          9
        //  1627: ldc             "VungleApiClient"
        //  1629: ldc_w           "isInstallNonMarketAppsEnabled Settings not found"
        //  1632: aload           9
        //  1634: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1637: pop            
        //  1638: iload           4
        //  1640: istore_3       
        //  1641: goto            1051
        //  1644: aload           13
        //  1646: ldc_w           "volume_level"
        //  1649: iconst_0       
        //  1650: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1653: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //  1656: aload           13
        //  1658: ldc_w           "sound_enabled"
        //  1661: iconst_0       
        //  1662: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1665: invokevirtual   com/google/gson/JsonObject.addProperty:(Ljava/lang/String;Ljava/lang/Number;)V
        //  1668: goto            1063
        //  1671: iconst_0       
        //  1672: istore_1       
        //  1673: goto            1077
        //  1676: ldc_w           "android"
        //  1679: astore          9
        //  1681: goto            1168
        //    Exceptions:
        //  throws java.lang.IllegalStateException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                
        //  -----  -----  -----  -----  ----------------------------------------------------
        //  911    925    1427   1445   Ljava/io/IOException;
        //  1001   1009   1625   1644   Landroid/provider/Settings$SettingNotFoundException;
        //  1012   1051   1625   1644   Landroid/provider/Settings$SettingNotFoundException;
        //  1590   1610   1625   1644   Landroid/provider/Settings$SettingNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_1051:
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
    
    public static boolean getMoatEnabled() {
        return VungleApiClient._instance.enableMoat && Build$VERSION.SDK_INT >= 16;
    }
    
    public static long getRetryAfterHeaderValue(final retrofit2.Response<JsonObject> response) {
        final String value = response.headers().get("Retry-After");
        try {
            return Long.parseLong(value) * 1000L;
        }
        catch (NumberFormatException ex) {
            return 0L;
        }
    }
    
    private JsonObject getUserBody() {
        if (this.userBody == null) {
            this.userBody = new JsonObject();
        }
        final Cookie cookie = this.storage.load("consentIsImportantToVungle", Cookie.class);
        String string;
        String string2;
        long longValue;
        String string3;
        if (cookie != null) {
            string = cookie.getString("consent_status");
            string2 = cookie.getString("consent_source");
            longValue = cookie.getLong("timestamp");
            string3 = cookie.getString("consent_message_version");
        }
        else {
            string = "unknown";
            string2 = "no_interaction";
            longValue = 0L;
            string3 = "";
        }
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("consent_status", string);
        jsonObject.addProperty("consent_source", string2);
        jsonObject.addProperty("consent_timestamp", (Number)longValue);
        String s = string3;
        if (TextUtils.isEmpty((CharSequence)string3)) {
            s = "";
        }
        jsonObject.addProperty("consent_message_version", s);
        this.userBody.add("gdpr", (JsonElement)jsonObject);
        return this.userBody;
    }
    
    public static void init(Context providers, String lastKnownLocation, String s, Storage storage) {
        Object o;
        Label_0315_Outer:Label_0368_Outer:
        while (true) {
            while (true) {
            Label_0661:
                while (true) {
                    Label_0687: {
                    Label_0572:
                        while (true) {
                        Label_0443:
                            while (true) {
                                Label_0436: {
                                    synchronized (VungleApiClient.class) {
                                        o = VungleApiClient._instance;
                                        if (o != null) {
                                            return;
                                        }
                                        VungleApiClient._instance = new VungleApiClient();
                                        VungleApiClient._instance.storage = storage;
                                        VungleApiClient._instance.context = new WeakReference<Context>(providers);
                                        VungleApiClient._instance.shouldTransmitIMEI = false;
                                        VungleApiClient._instance.cacheDir = s;
                                        s = (String)new JsonObject();
                                        ((JsonObject)s).addProperty("id", (String)lastKnownLocation);
                                        ((JsonObject)s).addProperty("bundle", providers.getPackageName());
                                        try {
                                            ((JsonObject)s).addProperty("ver", providers.getPackageManager().getPackageInfo(providers.getPackageName(), 0).versionName);
                                            storage = (Storage)new JsonObject();
                                            ((JsonObject)storage).addProperty("make", Build.MANUFACTURER);
                                            ((JsonObject)storage).addProperty("model", Build.MODEL);
                                            ((JsonObject)storage).addProperty("osv", Build$VERSION.RELEASE);
                                            ((JsonObject)storage).addProperty("carrier", ((TelephonyManager)providers.getSystemService("phone")).getNetworkOperatorName());
                                            if (!"Amazon".equals(Build.MANUFACTURER)) {
                                                break Label_0436;
                                            }
                                            lastKnownLocation = (PackageManager$NameNotFoundException)"amazon";
                                            ((JsonObject)storage).addProperty("os", (String)lastKnownLocation);
                                            lastKnownLocation = (PackageManager$NameNotFoundException)new DisplayMetrics();
                                            ((WindowManager)providers.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)lastKnownLocation);
                                            ((JsonObject)storage).addProperty("w", (Number)((DisplayMetrics)lastKnownLocation).widthPixels);
                                            ((JsonObject)storage).addProperty("h", (Number)((DisplayMetrics)lastKnownLocation).heightPixels);
                                            lastKnownLocation = (PackageManager$NameNotFoundException)new JsonObject();
                                            ((JsonObject)lastKnownLocation).add("vungle", (JsonElement)new JsonObject());
                                            ((JsonObject)storage).add("ext", (JsonElement)lastKnownLocation);
                                            if (Build$VERSION.SDK_INT < 17) {
                                                break Label_0443;
                                            }
                                            ((JsonObject)storage).addProperty("ua", VungleApiClient._instance.uaString = WebSettings.getDefaultUserAgent(providers));
                                            VungleApiClient._instance.deviceBody = (JsonObject)storage;
                                            if (providers.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0 && providers.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
                                                break Label_0687;
                                            }
                                            lastKnownLocation = null;
                                            storage = (Storage)providers.getSystemService("location");
                                            providers = (Context)((LocationManager)storage).getProviders(true);
                                            if (providers != null) {
                                                o = ((List<Object>)providers).iterator();
                                                providers = (Context)lastKnownLocation;
                                                while (((Iterator)o).hasNext()) {
                                                    lastKnownLocation = (PackageManager$NameNotFoundException)((LocationManager)storage).getLastKnownLocation((String)((Iterator<String>)o).next());
                                                    if (lastKnownLocation != null && (providers == null || ((Location)lastKnownLocation).getAccuracy() < ((Location)providers).getAccuracy())) {
                                                        break Label_0661;
                                                    }
                                                }
                                                break Label_0572;
                                            }
                                            break Label_0661;
                                        }
                                        catch (PackageManager$NameNotFoundException lastKnownLocation) {
                                            ((JsonObject)s).addProperty("ver", "1.0");
                                        }
                                    }
                                }
                                lastKnownLocation = (PackageManager$NameNotFoundException)"android";
                                continue Label_0315_Outer;
                            }
                            if (Looper.getMainLooper() == Looper.myLooper()) {
                                ((JsonObject)storage).addProperty("ua", VungleApiClient._instance.uaString = new WebView(providers.getApplicationContext()).getSettings().getUserAgentString());
                                VungleApiClient._instance.deviceBody = (JsonObject)storage;
                                continue Label_0368_Outer;
                            }
                            VungleApiClient._instance.deviceBody = (JsonObject)storage;
                            while (true) {
                                try {
                                    VungleApiClient._instance.uaString = System.getProperty("http.agent");
                                    lastKnownLocation = (PackageManager$NameNotFoundException)new CountDownLatch(1);
                                    new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            VungleApiClient._instance.deviceBody.addProperty("ua", new WebView(providers.getApplicationContext()).getSettings().getUserAgentString());
                                            ((CountDownLatch)lastKnownLocation).countDown();
                                        }
                                    });
                                    try {
                                        ((CountDownLatch)lastKnownLocation).await();
                                        continue Label_0368_Outer;
                                    }
                                    catch (InterruptedException lastKnownLocation) {
                                        ((Throwable)lastKnownLocation).printStackTrace();
                                        continue Label_0368_Outer;
                                    }
                                    continue Label_0368_Outer;
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                    continue;
                                }
                                break;
                            }
                            break;
                        }
                        if (providers != null) {
                            lastKnownLocation = (PackageManager$NameNotFoundException)new JsonObject();
                            ((JsonObject)lastKnownLocation).addProperty("accuracy", String.valueOf(((Location)providers).getAccuracy()));
                            ((JsonObject)lastKnownLocation).addProperty("latitude", String.valueOf(((Location)providers).getLatitude()));
                            ((JsonObject)lastKnownLocation).addProperty("longitude", String.valueOf(((Location)providers).getLongitude()));
                            ((JsonObject)lastKnownLocation).addProperty("speed", String.valueOf(((Location)providers).getSpeed()));
                            ((JsonObject)lastKnownLocation).addProperty("timestamp", (Number)((Location)providers).getTime());
                            VungleApiClient._instance.location = (JsonObject)lastKnownLocation;
                        }
                        new AdvertisingIDTask().execute((Object[])new Void[0]);
                        VungleApiClient._instance.appBody = (JsonObject)s;
                        return;
                    }
                    Log.d("VungleApiClient", "Location permission was not granted, location information will not be included");
                    continue Label_0661;
                }
                providers = (Context)lastKnownLocation;
                continue;
            }
        }
    }
    
    public static void pingTPAT(String replace) {
        final Callback<ResponseBody> callback = (Callback<ResponseBody>)new Callback<ResponseBody>() {
            public void onFailure(final Call<ResponseBody> call, final Throwable t) {
                Log.e("VungleApiClient", "Failed to ping TPAT Url : " + replace);
            }
            
            public void onResponse(final Call<ResponseBody> call, final retrofit2.Response<ResponseBody> response) {
            }
        };
        if (!TextUtils.isEmpty((CharSequence)replace) && HttpUrl.parse(replace) != null) {
            if (!TextUtils.isEmpty((CharSequence)VungleApiClient._instance.userImei) && VungleApiClient._instance.shouldTransmitIMEI) {
                replace = replace.replace("%imei%", VungleApiClient._instance.userImei);
            }
            final VungleApiClient instance = VungleApiClient._instance;
            VungleApiClient.api.pingTPAT(VungleApiClient._instance.uaString, replace).enqueue((Callback)callback);
            return;
        }
        ((Callback)callback).onFailure((Call)null, (Throwable)new IllegalArgumentException("Malformed Url"));
    }
    
    public static Call<JsonObject> reportAd(final JsonObject jsonObject) {
        if (VungleApiClient._instance.reportAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        final JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("device", (JsonElement)VungleApiClient._instance.getDeviceBody());
        jsonObject2.add("app", (JsonElement)VungleApiClient._instance.appBody);
        jsonObject2.add("request", (JsonElement)jsonObject);
        jsonObject2.add("user", (JsonElement)VungleApiClient._instance.getUserBody());
        final VungleApiClient instance = VungleApiClient._instance;
        return VungleApiClient.api.reportAd(VungleApiClient.HEADER_UA, VungleApiClient._instance.reportAdEndpoint, jsonObject2);
    }
    
    public static Call<JsonObject> reportNew() throws IllegalStateException {
        if (VungleApiClient._instance.newEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put("app_id", VungleApiClient._instance.appBody.get("id").getAsString());
        hashMap.put("ifa", VungleApiClient._instance.getAdvertIdFromCookie());
        final VungleApiClient instance = VungleApiClient._instance;
        return VungleApiClient.api.reportNew(VungleApiClient.HEADER_UA, VungleApiClient._instance.newEndpoint, hashMap);
    }
    
    public static Call<JsonObject> requestAd(final String s, final boolean b) throws IllegalStateException {
        if (VungleApiClient._instance.requestAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        final JsonObject jsonObject = new JsonObject();
        jsonObject.add("device", (JsonElement)VungleApiClient._instance.getDeviceBody());
        jsonObject.add("app", (JsonElement)VungleApiClient._instance.appBody);
        jsonObject.add("user", (JsonElement)VungleApiClient._instance.getUserBody());
        final JsonObject jsonObject2 = new JsonObject();
        final JsonArray jsonArray = new JsonArray();
        jsonArray.add(s);
        jsonObject2.add("placements", (JsonElement)jsonArray);
        jsonObject2.addProperty("header_bidding", Boolean.valueOf(b));
        jsonObject.add("request", (JsonElement)jsonObject2);
        final VungleApiClient instance = VungleApiClient._instance;
        return VungleApiClient.api.ads(VungleApiClient.HEADER_UA, VungleApiClient._instance.requestAdEndpoint, jsonObject);
    }
    
    public static Call<JsonObject> ri(final JsonObject jsonObject) {
        if (VungleApiClient._instance.riEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        final JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("device", (JsonElement)VungleApiClient._instance.getDeviceBody());
        jsonObject2.add("app", (JsonElement)VungleApiClient._instance.appBody);
        jsonObject2.add("request", (JsonElement)jsonObject);
        final VungleApiClient instance = VungleApiClient._instance;
        return VungleApiClient.api.ri(VungleApiClient.HEADER_UA, VungleApiClient._instance.riEndpoint, jsonObject2);
    }
    
    public static void updateIMEI(final String userImei, final boolean shouldTransmitIMEI) {
        VungleApiClient._instance.userImei = userImei;
        VungleApiClient._instance.shouldTransmitIMEI = shouldTransmitIMEI;
    }
    
    public static Call<JsonObject> willPlayAd(final String s, final boolean b, final String s2) throws IllegalStateException, VungleError {
        if (VungleApiClient._instance.willPlayAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        if (!VungleApiClient._instance.willPlayAdEnabled) {
            throw new VungleError(6);
        }
        final JsonObject jsonObject = new JsonObject();
        jsonObject.add("device", (JsonElement)VungleApiClient._instance.getDeviceBody());
        jsonObject.add("app", (JsonElement)VungleApiClient._instance.appBody);
        jsonObject.add("user", (JsonElement)VungleApiClient._instance.getUserBody());
        final JsonObject jsonObject2 = new JsonObject();
        final JsonObject jsonObject3 = new JsonObject();
        jsonObject3.addProperty("reference_id", s);
        jsonObject3.addProperty("is_auto_cached", Boolean.valueOf(b));
        jsonObject2.add("placement", (JsonElement)jsonObject3);
        jsonObject2.addProperty("ad_token", s2);
        jsonObject.add("request", (JsonElement)jsonObject2);
        return VungleApiClient._instance.timeoutApi.willPlayAd(VungleApiClient.HEADER_UA, VungleApiClient._instance.willPlayAdEndpoint, jsonObject);
    }
    
    private static class AdvertisingIDTask extends AsyncTask<Void, Void, String>
    {
        protected String doInBackground(Void... array) {
            while (true) {
                final Void[] array2 = null;
                final Void[] array3 = null;
                final Void[] array4 = null;
                final String s = null;
                array = array3;
                try {
                    if ("Amazon".equals(Build.MANUFACTURER)) {
                        Object o = array2;
                        array = array3;
                        try {
                            final ContentResolver contentResolver = ((Context)VungleApiClient._instance.context.get()).getContentResolver();
                            o = array2;
                            array = array3;
                            final VungleApiClient access$100 = VungleApiClient._instance;
                            o = array2;
                            array = array3;
                            final boolean b = Settings$Secure.getInt(contentResolver, "limit_ad_tracking") == 1;
                            o = array2;
                            array = array3;
                            access$100.limitAdTracking = b;
                            o = array2;
                            array = array3;
                            final String s2 = (Object)(array = (Void[])(o = Settings$Secure.getString(contentResolver, "advertising_id")));
                            VungleApiClient._instance.addAdvertIdInCookie(s2);
                            return s2;
                        }
                        catch (Settings$SettingNotFoundException ex) {
                            array = (Void[])o;
                            Log.w("VungleApiClient", "Error getting Amazon advertising info", (Throwable)ex);
                            return (String)o;
                        }
                    }
                }
                catch (Exception ex2) {
                    Log.e("VungleApiClient", "Cannot load Advertising ID");
                    return (String)(Object)array;
                }
                array = array3;
                Object o = array4;
                try {
                    final AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo((Context)VungleApiClient._instance.context.get());
                    final String s2 = s;
                    if (advertisingIdInfo != null) {
                        o = array4;
                        final String s3 = (String)(o = advertisingIdInfo.getId());
                        VungleApiClient._instance.limitAdTracking = advertisingIdInfo.isLimitAdTrackingEnabled();
                        o = s3;
                        VungleApiClient._instance.deviceBody.addProperty("ifa", s3);
                        o = s3;
                        VungleApiClient._instance.addAdvertIdInCookie(s3);
                        return s3;
                    }
                    return s2;
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    Log.e("VungleApiClient", "Play services Not available: " + noClassDefFoundError.getLocalizedMessage());
                    final String string = Settings$Secure.getString(((Context)VungleApiClient._instance.context.get()).getContentResolver(), "advertising_id");
                    VungleApiClient._instance.addAdvertIdInCookie(string);
                    return string;
                }
            }
        }
    }
    
    public enum WrapperFramework
    {
        admob, 
        adtoapp, 
        aerserv, 
        air, 
        appodeal, 
        cocos2dx, 
        corona, 
        dfp, 
        fyber, 
        heyzap, 
        ironsource, 
        marmalade, 
        mopub, 
        none, 
        tapdaq, 
        unity, 
        upsight;
    }
}
