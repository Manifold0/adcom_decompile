package com.vungle.warren.network;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.sdk.constants.LocationConst;
import com.moat.analytics.mobile.vng.MoatAnalytics;
import com.moat.analytics.mobile.vng.MoatOptions;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.Storage;
import com.vungle.warren.error.VungleError;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.JsonUtil;
import com.vungle.warren.ui.VungleActivity;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VungleApiClient {
    private static String BASE_URL = "https://ads.api.vungle.com/";
    private static String HEADER_UA = (MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "VungleAmazon/6.3.17" : "VungleDroid/6.3.17");
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
    private Map<String, Long> retryAfterDataMap = new ConcurrentHashMap();
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

    /* renamed from: com.vungle.warren.network.VungleApiClient$1 */
    class C01371 implements Interceptor {
        C01371() {
        }

        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String urlPath = request.url().encodedPath();
            Long retryExpireTime = (Long) VungleApiClient.this.retryAfterDataMap.get(urlPath);
            if (retryExpireTime != null) {
                long newRetryAfter = TimeUnit.MILLISECONDS.toSeconds(retryExpireTime.longValue() - System.currentTimeMillis());
                if (newRetryAfter > 0) {
                    return new Builder().request(request).addHeader("Retry-After", "" + newRetryAfter).code(TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL).protocol(Protocol.HTTP_1_1).message("Server is busy").body(ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"Error\":\"Retry-After\"}")).build();
                }
                VungleApiClient.this.retryAfterDataMap.remove(urlPath);
            }
            Response response = chain.proceed(request);
            if (response == null) {
                return response;
            }
            int responseCode = response.code();
            if (responseCode != 429 && responseCode != TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL && responseCode != IronSourceError.ERROR_CODE_USING_CACHED_CONFIGURATION && responseCode != 503) {
                return response;
            }
            String retryAfterTimeStr = response.headers().get("Retry-After");
            if (TextUtils.isEmpty(retryAfterTimeStr)) {
                return response;
            }
            try {
                long retryAfterTimeValue = Long.parseLong(retryAfterTimeStr);
                if (retryAfterTimeValue <= 0) {
                    return response;
                }
                VungleApiClient.this.retryAfterDataMap.put(urlPath, Long.valueOf((1000 * retryAfterTimeValue) + System.currentTimeMillis()));
                return response;
            } catch (NumberFormatException e) {
                Log.d(VungleApiClient.TAG, "Retry-After value is not an valid value");
                return response;
            }
        }
    }

    private static class AdvertisingIDTask extends AsyncTask<Void, Void, String> {
        private AdvertisingIDTask() {
        }

        protected String doInBackground(Void... voids) {
            String advertId = null;
            try {
                if (VungleApiClient.MANUFACTURER_AMAZON.equals(Build.MANUFACTURER)) {
                    try {
                        ContentResolver cr = ((Context) VungleApiClient._instance.context.get()).getContentResolver();
                        VungleApiClient._instance.limitAdTracking = Secure.getInt(cr, "limit_ad_tracking") == 1;
                        advertId = Secure.getString(cr, TapjoyConstants.TJC_ADVERTISING_ID);
                        VungleApiClient._instance.addAdvertIdInCookie(advertId);
                        return advertId;
                    } catch (SettingNotFoundException ex) {
                        Log.w(VungleApiClient.TAG, "Error getting Amazon advertising info", ex);
                        return null;
                    }
                }
                try {
                    Info idInfo = AdvertisingIdClient.getAdvertisingIdInfo((Context) VungleApiClient._instance.context.get());
                    if (idInfo == null) {
                        return null;
                    }
                    advertId = idInfo.getId();
                    VungleApiClient._instance.limitAdTracking = idInfo.isLimitAdTrackingEnabled();
                    VungleApiClient._instance.deviceBody.addProperty("ifa", advertId);
                    VungleApiClient._instance.addAdvertIdInCookie(advertId);
                    return advertId;
                } catch (NoClassDefFoundError ex2) {
                    Log.e(VungleApiClient.TAG, "Play services Not available: " + ex2.getLocalizedMessage());
                    advertId = Secure.getString(((Context) VungleApiClient._instance.context.get()).getContentResolver(), TapjoyConstants.TJC_ADVERTISING_ID);
                    VungleApiClient._instance.addAdvertIdInCookie(advertId);
                    return advertId;
                }
            } catch (Exception e) {
                Log.e(VungleApiClient.TAG, "Cannot load Advertising ID");
                return advertId;
            }
        }
    }

    public enum WrapperFramework {
        admob,
        air,
        cocos2dx,
        corona,
        dfp,
        heyzap,
        marmalade,
        mopub,
        unity,
        fyber,
        ironsource,
        upsight,
        appodeal,
        aerserv,
        adtoapp,
        tapdaq,
        none
    }

    private VungleApiClient() {
        client = new OkHttpClient.Builder().addInterceptor(new C01371()).build();
        api = (VungleApi) new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build().create(VungleApi.class);
    }

    public static void updateIMEI(String imei, boolean shouldTransmit) {
        _instance.userImei = imei;
        _instance.shouldTransmitIMEI = shouldTransmit;
    }

    public static void addWrapperInfo(WrapperFramework wrapperFramework, String wrapperFrameworkVersion) {
        if (wrapperFramework != null && wrapperFramework != WrapperFramework.none) {
            HEADER_UA += ";" + wrapperFramework;
            if (wrapperFrameworkVersion != null && !wrapperFrameworkVersion.isEmpty()) {
                HEADER_UA += "/" + wrapperFrameworkVersion;
            }
        }
    }

    public static synchronized void init(Context context, String appID, String cacheDir, Storage storage) {
        synchronized (VungleApiClient.class) {
            if (_instance == null) {
                _instance = new VungleApiClient();
                _instance.storage = storage;
                _instance.context = new WeakReference(context);
                _instance.shouldTransmitIMEI = false;
                _instance.cacheDir = cacheDir;
                JsonObject app = new JsonObject();
                app.addProperty("id", appID);
                app.addProperty(String.BUNDLE, context.getPackageName());
                try {
                    app.addProperty("ver", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
                } catch (NameNotFoundException e) {
                    app.addProperty("ver", "1.0");
                }
                JsonObject device = new JsonObject();
                device.addProperty("make", Build.MANUFACTURER);
                device.addProperty("model", Build.MODEL);
                device.addProperty("osv", VERSION.RELEASE);
                device.addProperty("carrier", ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkOperatorName());
                device.addProperty("os", MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon" : TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
                DisplayMetrics dm = new DisplayMetrics();
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(dm);
                device.addProperty("w", Integer.valueOf(dm.widthPixels));
                device.addProperty("h", Integer.valueOf(dm.heightPixels));
                JsonObject ext = new JsonObject();
                ext.add("vungle", new JsonObject());
                device.add("ext", ext);
                if (VERSION.SDK_INT >= 17) {
                    _instance.uaString = WebSettings.getDefaultUserAgent(context);
                    device.addProperty("ua", _instance.uaString);
                    _instance.deviceBody = device;
                } else {
                    if (Looper.getMainLooper() == Looper.myLooper()) {
                        _instance.uaString = new WebView(context.getApplicationContext()).getSettings().getUserAgentString();
                        device.addProperty("ua", _instance.uaString);
                        _instance.deviceBody = device;
                    } else {
                        _instance.deviceBody = device;
                        try {
                            _instance.uaString = System.getProperty("http.agent");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        final CountDownLatch latch = new CountDownLatch(1);
                        final Context context2 = context;
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                VungleApiClient._instance.deviceBody.addProperty("ua", new WebView(context2.getApplicationContext()).getSettings().getUserAgentString());
                                latch.countDown();
                            }
                        });
                        try {
                            latch.await();
                        } catch (InterruptedException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                if (context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                    Location location = null;
                    LocationManager locationManager = (LocationManager) context.getSystemService(PlaceFields.LOCATION);
                    List<String> providers = locationManager.getProviders(true);
                    if (providers != null) {
                        for (String provider : providers) {
                            Location locationFromProvider = locationManager.getLastKnownLocation(provider);
                            if (locationFromProvider != null && (location == null || locationFromProvider.getAccuracy() < location.getAccuracy())) {
                                location = locationFromProvider;
                            }
                        }
                        if (location != null) {
                            JsonObject locationJsonObj = new JsonObject();
                            locationJsonObj.addProperty(LocationConst.ACCURACY, String.valueOf(location.getAccuracy()));
                            locationJsonObj.addProperty(LocationConst.LATITUDE, String.valueOf(location.getLatitude()));
                            locationJsonObj.addProperty(LocationConst.LONGITUDE, String.valueOf(location.getLongitude()));
                            locationJsonObj.addProperty(LocationConst.SPEED, String.valueOf(location.getSpeed()));
                            locationJsonObj.addProperty("timestamp", Long.valueOf(location.getTime()));
                            _instance.location = locationJsonObj;
                        }
                    }
                } else {
                    Log.d(TAG, "Location permission was not granted, location information will not be included");
                }
                new AdvertisingIDTask().execute(new Void[0]);
                _instance.appBody = app;
            }
        }
    }

    public static void config(@NonNull final Callback<JsonObject> callback) {
        try {
            JsonObject body = new JsonObject();
            body.add("device", _instance.getDeviceBody());
            body.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
            body.add("user", _instance.getUserBody());
            JsonObject request = new JsonObject();
            request.addProperty("is_auto_cached_enforced", Boolean.valueOf(false));
            body.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, request);
            VungleApiClient vungleApiClient = _instance;
            api.config(HEADER_UA, body).enqueue(new Callback<JsonObject>() {
                public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        JsonObject jsonObject = (JsonObject) response.body();
                        Log.d(VungleApiClient.TAG, "Config Response: " + jsonObject);
                        if (JsonUtil.hasNonNull(jsonObject, "sleep")) {
                            Log.e(VungleApiClient.TAG, "Error Initializing Vungle. Please try again. " + (JsonUtil.hasNonNull(jsonObject, String.VIDEO_INFO) ? jsonObject.get(String.VIDEO_INFO).getAsString() : ""));
                            callback.onFailure(call, new VungleException(3));
                            return;
                        } else if (JsonUtil.hasNonNull(jsonObject, "endpoints")) {
                            JsonObject endpoints = jsonObject.getAsJsonObject("endpoints");
                            HttpUrl newUrl = HttpUrl.parse(endpoints.get("new").getAsString());
                            HttpUrl adsUrl = HttpUrl.parse(endpoints.get("ads").getAsString());
                            HttpUrl willPlayAdUrl = HttpUrl.parse(endpoints.get("will_play_ad").getAsString());
                            HttpUrl reportAdUrl = HttpUrl.parse(endpoints.get("report_ad").getAsString());
                            HttpUrl reportIncentivized = HttpUrl.parse(endpoints.get("ri").getAsString());
                            VungleApiClient._instance.newEndpoint = newUrl.toString();
                            VungleApiClient._instance.requestAdEndpoint = adsUrl.toString();
                            VungleApiClient._instance.willPlayAdEndpoint = willPlayAdUrl.toString();
                            VungleApiClient._instance.reportAdEndpoint = reportAdUrl.toString();
                            VungleApiClient._instance.riEndpoint = reportIncentivized.toString();
                            JsonObject willPlayAd = jsonObject.getAsJsonObject("will_play_ad");
                            VungleApiClient._instance.willPlayAdTimeout = willPlayAd.get("request_timeout").getAsInt();
                            VungleApiClient._instance.willPlayAdEnabled = willPlayAd.get(String.ENABLED).getAsBoolean();
                            VungleApiClient._instance.enableMoat = jsonObject.getAsJsonObject("viewability").get("moat").getAsBoolean();
                            callback.onResponse(call, response);
                            if (VungleApiClient._instance.willPlayAdEnabled) {
                                Log.v(VungleApiClient.TAG, "willPlayAd is enabled, generating a timeout client.");
                                VungleApiClient._instance;
                                VungleApiClient._instance.timeoutApi = (VungleApi) new Retrofit.Builder().client(VungleApiClient.client.newBuilder().readTimeout((long) VungleApiClient._instance.willPlayAdTimeout, TimeUnit.MILLISECONDS).build()).addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.vungle.com/").build().create(VungleApi.class);
                            }
                            if (VungleApiClient.getMoatEnabled()) {
                                MoatOptions options = new MoatOptions();
                                options.disableAdIdCollection = true;
                                options.disableLocationServices = true;
                                options.loggingEnabled = true;
                                MoatAnalytics.getInstance().start(options, (Application) ((Context) VungleApiClient._instance.context.get()).getApplicationContext());
                                return;
                            }
                            return;
                        } else {
                            Log.e(VungleApiClient.TAG, "Error Initializing Vungle. Please try again. ");
                            callback.onFailure(call, new VungleException(3));
                            return;
                        }
                    }
                    callback.onResponse(call, response);
                }

                public void onFailure(Call<JsonObject> call, Throwable throwable) {
                    Log.e(VungleApiClient.TAG, "Failed to configure.", throwable);
                    callback.onFailure(call, throwable);
                }
            });
        } catch (IllegalStateException notInitialized) {
            callback.onFailure(null, notInitialized);
        }
    }

    public static Call<JsonObject> reportNew() throws IllegalStateException {
        if (_instance.newEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        HashMap<String, String> query = new HashMap(2);
        query.put("app_id", _instance.appBody.get("id").getAsString());
        query.put("ifa", _instance.getAdvertIdFromCookie());
        VungleApiClient vungleApiClient = _instance;
        return api.reportNew(HEADER_UA, _instance.newEndpoint, query);
    }

    public static Call<JsonObject> requestAd(String placement, boolean isHeaderBiddingEnable) throws IllegalStateException {
        if (_instance.requestAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        JsonObject body = new JsonObject();
        body.add("device", _instance.getDeviceBody());
        body.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
        body.add("user", _instance.getUserBody());
        JsonObject request = new JsonObject();
        JsonArray placementsArray = new JsonArray();
        placementsArray.add(placement);
        request.add("placements", placementsArray);
        request.addProperty("header_bidding", Boolean.valueOf(isHeaderBiddingEnable));
        body.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, request);
        VungleApiClient vungleApiClient = _instance;
        return api.ads(HEADER_UA, _instance.requestAdEndpoint, body);
    }

    public static Call<JsonObject> willPlayAd(String placementID, boolean autoCached, String adToken) throws IllegalStateException, VungleError {
        if (_instance.willPlayAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        } else if (_instance.willPlayAdEnabled) {
            JsonObject body = new JsonObject();
            body.add("device", _instance.getDeviceBody());
            body.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
            body.add("user", _instance.getUserBody());
            JsonObject request = new JsonObject();
            JsonObject placement = new JsonObject();
            placement.addProperty("reference_id", placementID);
            placement.addProperty("is_auto_cached", Boolean.valueOf(autoCached));
            request.add(VungleActivity.PLACEMENT_EXTRA, placement);
            request.addProperty("ad_token", adToken);
            body.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, request);
            return _instance.timeoutApi.willPlayAd(HEADER_UA, _instance.willPlayAdEndpoint, body);
        } else {
            throw new VungleError(6);
        }
    }

    public static Call<JsonObject> reportAd(JsonObject request) {
        if (_instance.reportAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        JsonObject body = new JsonObject();
        body.add("device", _instance.getDeviceBody());
        body.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
        body.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, request);
        body.add("user", _instance.getUserBody());
        VungleApiClient vungleApiClient = _instance;
        return api.reportAd(HEADER_UA, _instance.reportAdEndpoint, body);
    }

    public static Call<JsonObject> ri(JsonObject request) {
        if (_instance.riEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        JsonObject body = new JsonObject();
        body.add("device", _instance.getDeviceBody());
        body.add(TapjoyConstants.TJC_APP_PLACEMENT, _instance.appBody);
        body.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, request);
        VungleApiClient vungleApiClient = _instance;
        return api.ri(HEADER_UA, _instance.riEndpoint, body);
    }

    public static void pingTPAT(final String url) {
        Callback callback = new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
            }

            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Log.e(VungleApiClient.TAG, "Failed to ping TPAT Url : " + url);
            }
        };
        if (TextUtils.isEmpty(url) || HttpUrl.parse(url) == null) {
            callback.onFailure(null, new IllegalArgumentException("Malformed Url"));
            return;
        }
        String finalUrl;
        if (TextUtils.isEmpty(_instance.userImei) || !_instance.shouldTransmitIMEI) {
            finalUrl = url;
        } else {
            finalUrl = url.replace("%imei%", _instance.userImei);
        }
        VungleApiClient vungleApiClient = _instance;
        api.pingTPAT(_instance.uaString, finalUrl).enqueue(callback);
    }

    @SuppressLint({"HardwareIds"})
    private JsonObject getDeviceBody() throws IllegalStateException {
        if (this.context.get() == null) {
            throw new IllegalStateException("Context is null, SDK not initialized");
        }
        int i;
        String batteryState;
        JsonObject android = new JsonObject();
        String advertisingID = _instance.getAdvertIdFromCookie();
        if (advertisingID != null) {
            android.addProperty(MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon_advertising_id" : "gaid", advertisingID);
            this.deviceBody.addProperty("ifa", advertisingID);
            JsonObject jsonObject = this.deviceBody;
            String str = "lmt";
            if (this.limitAdTracking) {
                i = 1;
            } else {
                i = 0;
            }
            jsonObject.addProperty(str, Integer.valueOf(i));
        } else {
            this.deviceBody.addProperty("ifa", Secure.getString(((Context) this.context.get()).getContentResolver(), TapjoyConstants.TJC_ANDROID_ID));
            this.deviceBody.addProperty("lmt", Integer.valueOf(0));
        }
        new AdvertisingIDTask().execute(new Void[0]);
        boolean isGooglePlayServicesAvailable = false;
        for (PackageInfo packageInfo : ((Context) _instance.context.get()).getPackageManager().getInstalledPackages(128)) {
            if (packageInfo.packageName.equalsIgnoreCase("com.google.android.gms")) {
                isGooglePlayServicesAvailable = true;
            }
        }
        this.deviceBody.addProperty("is_google_play_services_available", Boolean.valueOf(isGooglePlayServicesAvailable));
        Intent batteryStatus = ((Context) this.context.get()).registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int level = batteryStatus.getIntExtra("level", -1);
        int scale = batteryStatus.getIntExtra("scale", -1);
        if (level > 0 && scale > 0) {
            android.addProperty("battery_level", Float.valueOf(((float) level) / ((float) scale)));
        }
        int status = batteryStatus.getIntExtra("status", -1);
        if (status == -1) {
            batteryState = "UNKNOWN";
        } else if (status == 2 || status == 5) {
            switch (batteryStatus.getIntExtra("plugged", -1)) {
                case 1:
                    batteryState = "BATTERY_PLUGGED_AC";
                    break;
                case 2:
                    batteryState = "BATTERY_PLUGGED_USB";
                    break;
                case 4:
                    batteryState = "BATTERY_PLUGGED_WIRELESS";
                    break;
                default:
                    batteryState = "BATTERY_PLUGGED_OTHERS";
                    break;
            }
        } else {
            batteryState = "NOT_CHARGING";
        }
        android.addProperty("battery_state", batteryState);
        if (VERSION.SDK_INT >= 21) {
            PowerManager powerManager = (PowerManager) ((Context) this.context.get()).getSystemService("power");
            String str2 = "battery_saver_enabled";
            i = (powerManager == null || !powerManager.isPowerSaveMode()) ? 0 : 1;
            android.addProperty(str2, Integer.valueOf(i));
        }
        if (PermissionChecker.checkCallingOrSelfPermission((Context) _instance.context.get(), "android.permission.ACCESS_NETWORK_STATE") == 0) {
            String connectionType = "NONE";
            String connectionTypeDetail = "NONE";
            ConnectivityManager cm = (ConnectivityManager) ((Context) this.context.get()).getSystemService("connectivity");
            if (cm != null) {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info != null) {
                    switch (info.getType()) {
                        case 0:
                            connectionType = "MOBILE";
                            connectionTypeDetail = getConnectionTypeDetail(info.getSubtype());
                            break;
                        case 1:
                        case 6:
                            connectionType = "WIFI";
                            connectionTypeDetail = "WIFI";
                            break;
                        case 7:
                            connectionType = "BLUETOOTH";
                            connectionTypeDetail = "BLUETOOTH";
                            break;
                        case 9:
                            connectionType = "ETHERNET";
                            connectionTypeDetail = "ETHERNET";
                            break;
                        default:
                            connectionType = "UNKNOWN";
                            connectionTypeDetail = "UNKNOWN";
                            break;
                    }
                }
            }
            android.addProperty(TapjoyConstants.TJC_CONNECTION_TYPE, connectionType);
            android.addProperty("connection_type_detail", connectionTypeDetail);
            if (VERSION.SDK_INT >= 24) {
                if (cm.isActiveNetworkMetered()) {
                    String dataSaverStatus;
                    switch (cm.getRestrictBackgroundStatus()) {
                        case 1:
                            dataSaverStatus = "DISABLED";
                            break;
                        case 2:
                            dataSaverStatus = "WHITELISTED";
                            break;
                        case 3:
                            dataSaverStatus = "ENABLED";
                            break;
                        default:
                            dataSaverStatus = "UNKNOWN";
                            break;
                    }
                    android.addProperty("data_saver_status", dataSaverStatus);
                    android.addProperty("network_metered", Integer.valueOf(1));
                } else {
                    android.addProperty("data_saver_status", "NOT_APPLICABLE");
                    android.addProperty("network_metered", Integer.valueOf(0));
                }
            }
        }
        android.addProperty("locale", Locale.getDefault().toString());
        android.addProperty("language", Locale.getDefault().getLanguage());
        android.addProperty("time_zone", TimeZone.getDefault().getID());
        if (this.context.get() != null) {
            boolean isTV;
            AudioManager audio = (AudioManager) ((Context) this.context.get()).getSystemService("audio");
            if (audio != null) {
                int max = audio.getStreamMaxVolume(3);
                int current = audio.getStreamVolume(3);
                android.addProperty("volume_level", Float.valueOf(((float) current) / ((float) max)));
                android.addProperty("sound_enabled", Integer.valueOf(current > 0 ? 1 : 0));
            }
            File file = new File(this.cacheDir);
            boolean cacheDirExists = file.exists() && file.isDirectory();
            if (!cacheDirExists) {
                if (!file.exists()) {
                    cacheDirExists = file.mkdir();
                } else if (!file.isDirectory() && file.delete()) {
                    cacheDirExists = file.mkdir();
                }
            }
            if (cacheDirExists) {
                StatFs statFs = new StatFs(this.cacheDir);
                long bytesAvailable = -1;
                if (VERSION.SDK_INT >= 26) {
                    StorageManager storageManager = (StorageManager) ((Context) this.context.get()).getSystemService(StorageManager.class);
                    if (storageManager != null) {
                        try {
                            bytesAvailable = storageManager.getCacheQuotaBytes(storageManager.getUuidForPath(file));
                        } catch (IOException e) {
                            Log.e(TAG, "Unable to check available bytes");
                        }
                    }
                } else {
                    bytesAvailable = VERSION.SDK_INT >= 18 ? statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong() : (long) (statFs.getBlockSize() * statFs.getAvailableBlocks());
                }
                android.addProperty("storage_bytes_available", Long.valueOf(bytesAvailable));
            }
            if (MANUFACTURER_AMAZON.equals(Build.MANUFACTURER)) {
                String AMAZON_FEATURE_FIRE_TV = "amazon.hardware.fire_tv";
                isTV = ((Context) this.context.get()).getApplicationContext().getPackageManager().hasSystemFeature("amazon.hardware.fire_tv");
            } else if (VERSION.SDK_INT >= 23) {
                isTV = ((UiModeManager) ((Context) this.context.get()).getSystemService("uimode")).getCurrentModeType() == 4;
            } else {
                String FEATURE_ANDROID_TV = "com.google.android.tv";
                String FEATURE_HW_TOUCHSCREEN = "android.hardware.touchscreen";
                isTV = ((Context) this.context.get()).getApplicationContext().getPackageManager().hasSystemFeature("com.google.android.tv") || !((Context) this.context.get()).getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen");
            }
            android.addProperty("is_tv", Boolean.valueOf(isTV));
            android.addProperty("os_api_level", Integer.valueOf(VERSION.SDK_INT));
            boolean canInstallNonMarket = false;
            try {
                if (VERSION.SDK_INT < 26) {
                    canInstallNonMarket = Secure.getInt(((Context) this.context.get()).getContentResolver(), "install_non_market_apps") == 1;
                } else if (((Context) this.context.get()).checkCallingOrSelfPermission("android.permission.REQUEST_INSTALL_PACKAGES") == 0) {
                    canInstallNonMarket = ((Context) this.context.get()).getApplicationContext().getPackageManager().canRequestPackageInstalls();
                }
            } catch (Throwable e2) {
                Log.e(TAG, "isInstallNonMarketAppsEnabled Settings not found", e2);
            }
            android.addProperty("is_sideload_enabled", Boolean.valueOf(canInstallNonMarket));
        } else {
            android.addProperty("volume_level", Integer.valueOf(0));
            android.addProperty("sound_enabled", Integer.valueOf(0));
        }
        android.addProperty("sd_card_available", Integer.valueOf(Environment.getExternalStorageState().equals("mounted") ? 1 : 0));
        android.addProperty("os_name", Build.FINGERPRINT);
        android.addProperty("vduid", "");
        if (_instance.location != null) {
            android.add(PlaceFields.LOCATION, _instance.location);
        }
        this.deviceBody.getAsJsonObject("ext").getAsJsonObject("vungle").add(MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon" : TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE, android);
        return this.deviceBody;
    }

    private String getConnectionTypeDetail(int type) {
        switch (type) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "EVDO_0";
            case 6:
                return "EVDO_A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "IDEN";
            case 12:
                return "EVDO_B";
            case 13:
                return "LTE";
            case 14:
                return "EHPRD";
            case 15:
                return "HSPAP";
            case 16:
                return "GSM";
            case 17:
                return "TD_SCDMA";
            case 18:
                return "IWLAN";
            default:
                return "UNKNOWN";
        }
    }

    private JsonObject getUserBody() {
        String status;
        String source;
        long timestamp;
        String messageVersion;
        if (this.userBody == null) {
            this.userBody = new JsonObject();
        }
        Cookie consentCookie = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (consentCookie != null) {
            status = consentCookie.getString("consent_status");
            source = consentCookie.getString("consent_source");
            timestamp = consentCookie.getLong("timestamp").longValue();
            messageVersion = consentCookie.getString("consent_message_version");
        } else {
            status = "unknown";
            source = "no_interaction";
            timestamp = 0;
            messageVersion = "";
        }
        JsonObject gdpr = new JsonObject();
        gdpr.addProperty("consent_status", status);
        gdpr.addProperty("consent_source", source);
        gdpr.addProperty("consent_timestamp", Long.valueOf(timestamp));
        String str = "consent_message_version";
        if (TextUtils.isEmpty(messageVersion)) {
            messageVersion = "";
        }
        gdpr.addProperty(str, messageVersion);
        this.userBody.add("gdpr", gdpr);
        return this.userBody;
    }

    public static boolean getMoatEnabled() {
        return _instance.enableMoat && VERSION.SDK_INT >= 16;
    }

    private String getAdvertIdFromCookie() {
        Cookie cookie = (Cookie) this.storage.load(Cookie.GOOGLE_AD_ID_COOKIE, Cookie.class);
        if (cookie == null) {
            return null;
        }
        return cookie.getString("advertId");
    }

    private void addAdvertIdInCookie(String advertId) {
        Cookie advertIdCookie = new Cookie(Cookie.GOOGLE_AD_ID_COOKIE);
        advertIdCookie.putValue("advertId", advertId);
        this.storage.save(advertIdCookie);
    }

    public static long getRetryAfterHeaderValue(retrofit2.Response<JsonObject> response) {
        try {
            return Long.parseLong(response.headers().get("Retry-After")) * 1000;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
