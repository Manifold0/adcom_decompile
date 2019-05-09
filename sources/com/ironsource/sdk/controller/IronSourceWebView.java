package com.ironsource.sdk.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.environment.ConnectivityService;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.environment.LocationService;
import com.ironsource.environment.LocationService.ISLocationListener;
import com.ironsource.environment.UrlHandler;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.Constants.ErrorCodes;
import com.ironsource.sdk.constants.Constants.ForceClosePosition;
import com.ironsource.sdk.constants.Constants.JSMethods;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.ironsource.sdk.constants.LocationConst;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.AdUnitsState;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.ProductParameters;
import com.ironsource.sdk.data.SSABCParameters;
import com.ironsource.sdk.data.SSAEnums.ControllerState;
import com.ironsource.sdk.data.SSAEnums.DebugMode;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import com.ironsource.sdk.data.SSAFile;
import com.ironsource.sdk.data.SSAObj;
import com.ironsource.sdk.listeners.OnGenericFunctionListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import com.ironsource.sdk.listeners.internals.DSAdProductListener;
import com.ironsource.sdk.listeners.internals.DSInterstitialListener;
import com.ironsource.sdk.listeners.internals.DSRewardedVideoListener;
import com.ironsource.sdk.precache.DownloadManager;
import com.ironsource.sdk.precache.DownloadManager.OnPreCacheCompletion;
import com.ironsource.sdk.utils.DeviceProperties;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.IronSourceStorageUtils;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import com.tonyodev.fetch.FetchConst;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IronSourceWebView extends WebView implements OnPreCacheCompletion, DownloadListener {
    public static String APP_IDS = "appIds";
    public static int DISPLAY_WEB_VIEW_INTENT = 0;
    public static String EXTERNAL_URL = "external_url";
    public static String IS_INSTALLED = "isInstalled";
    public static String IS_STORE = "is_store";
    public static String IS_STORE_CLOSE = "is_store_close";
    private static String JSON_KEY_FAIL = "fail";
    private static String JSON_KEY_SUCCESS = "success";
    public static int OPEN_URL_INTENT = 1;
    public static String REQUEST_ID = "requestId";
    public static String RESULT = "result";
    public static String SECONDARY_WEB_VIEW = "secondary_web_view";
    public static String WEBVIEW_TYPE = "webview_type";
    public static int mDebugMode = 0;
    private final String GENERIC_MESSAGE = "We're sorry, some error occurred. we will investigate it";
    private String PUB_TAG = "IronSource";
    private String TAG = IronSourceWebView.class.getSimpleName();
    private DownloadManager downloadManager;
    private Boolean isKitkatAndAbove = null;
    private boolean isRemoveCloseEventHandler;
    private String mCacheDirectory;
    private OnWebViewChangeListener mChangeListener;
    private CountDownTimer mCloseEventTimer;
    private BroadcastReceiver mConnectionReceiver = new C07247();
    private ArrayList<String> mControllerCommandsQueue;
    private String mControllerKeyPressed = "interrupt";
    private FrameLayout mControllerLayout;
    private ControllerState mControllerState = ControllerState.None;
    Context mCurrentActivityContext;
    private View mCustomView;
    private CustomViewCallback mCustomViewCallback;
    private FrameLayout mCustomViewContainer;
    private DSInterstitialListener mDSInterstitialListener;
    private DSRewardedVideoListener mDSRewardedVideoListener;
    private DemandSourceManager mDemandSourceManager;
    private boolean mGlobalControllerTimeFinish;
    private CountDownTimer mGlobalControllerTimer;
    private int mHiddenForceCloseHeight = 50;
    private String mHiddenForceCloseLocation = ForceClosePosition.TOP_RIGHT;
    private int mHiddenForceCloseWidth = 50;
    private String mISAppKey;
    private String mISUserId;
    private boolean mIsActivityThemeTranslucent = false;
    private boolean mIsImmersive = false;
    private CountDownTimer mLoadControllerTimer;
    private MOATJSAdapter mMoatJsAdapter;
    private String mOWAppKey;
    private String mOWCreditsAppKey;
    private boolean mOWCreditsMiss;
    private String mOWCreditsUserId;
    private Map<String, String> mOWExtraParameters;
    private String mOWUserId;
    private boolean mOWmiss;
    private OnGenericFunctionListener mOnGenericFunctionListener;
    private OnOfferWallListener mOnOfferWallListener;
    private String mOrientationState;
    private PermissionsJSAdapter mPermissionsJsAdapter;
    private ProductParametersCollection mProductParametersCollection = new ProductParametersCollection();
    private String mRVAppKey;
    private String mRVUserId;
    private String mRequestParameters;
    private AdUnitsState mSavedState;
    private Object mSavedStateLocker = new Object();
    private State mState;
    Handler mUiHandler;
    private Uri mUri;
    private VideoEventsListener mVideoEventsListener;
    private ChromeClient mWebChromeClient;

    /* renamed from: com.ironsource.sdk.controller.IronSourceWebView$7 */
    class C07247 extends BroadcastReceiver {
        C07247() {
        }

        public void onReceive(Context context, Intent intent) {
            if (IronSourceWebView.this.mControllerState == ControllerState.Ready) {
                String networkType = ParametersKeys.ORIENTATION_NONE;
                if (ConnectivityService.isConnectedWifi(context)) {
                    networkType = "wifi";
                } else if (ConnectivityService.isConnectedMobile(context)) {
                    networkType = ConnectivityService.NETWORK_TYPE_3G;
                }
                IronSourceWebView.this.deviceStatusChanged(networkType);
            }
        }
    }

    private class ChromeClient extends WebChromeClient {
        private ChromeClient() {
        }

        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView childView = new WebView(view.getContext());
            childView.setWebChromeClient(this);
            childView.setWebViewClient(new FrameBustWebViewClient());
            resultMsg.obj.setWebView(childView);
            resultMsg.sendToTarget();
            Logger.m1212i("onCreateWindow", "onCreateWindow");
            return true;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Logger.m1212i("MyApplication", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return true;
        }

        public void onShowCustomView(View view, CustomViewCallback callback) {
            Logger.m1212i("Test", "onShowCustomView");
            IronSourceWebView.this.setVisibility(8);
            if (IronSourceWebView.this.mCustomView != null) {
                Logger.m1212i("Test", "mCustomView != null");
                callback.onCustomViewHidden();
                return;
            }
            Logger.m1212i("Test", "mCustomView == null");
            IronSourceWebView.this.mCustomViewContainer.addView(view);
            IronSourceWebView.this.mCustomView = view;
            IronSourceWebView.this.mCustomViewCallback = callback;
            IronSourceWebView.this.mCustomViewContainer.setVisibility(0);
        }

        public View getVideoLoadingProgressView() {
            FrameLayout frameLayout = new FrameLayout(IronSourceWebView.this.getCurrentActivityContext());
            frameLayout.setLayoutParams(new LayoutParams(-1, -1));
            return frameLayout;
        }

        public void onHideCustomView() {
            Logger.m1212i("Test", "onHideCustomView");
            if (IronSourceWebView.this.mCustomView != null) {
                IronSourceWebView.this.mCustomView.setVisibility(8);
                IronSourceWebView.this.mCustomViewContainer.removeView(IronSourceWebView.this.mCustomView);
                IronSourceWebView.this.mCustomView = null;
                IronSourceWebView.this.mCustomViewContainer.setVisibility(8);
                IronSourceWebView.this.mCustomViewCallback.onCustomViewHidden();
                IronSourceWebView.this.setVisibility(0);
            }
        }
    }

    private class FrameBustWebViewClient extends WebViewClient {
        private FrameBustWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Context ctx = IronSourceWebView.this.getCurrentActivityContext();
            Intent intent = new Intent(ctx, OpenUrlActivity.class);
            intent.putExtra(IronSourceWebView.EXTERNAL_URL, url);
            intent.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, false);
            ctx.startActivity(intent);
            return true;
        }
    }

    public class JSInterface {
        volatile int udiaResults = 0;

        /* renamed from: com.ironsource.sdk.controller.IronSourceWebView$JSInterface$1 */
        class C07261 implements Runnable {
            C07261() {
            }

            public void run() {
                if (VERSION.SDK_INT >= 16) {
                    try {
                        IronSourceWebView.this.getSettings().setAllowFileAccessFromFileURLs(false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /* renamed from: com.ironsource.sdk.controller.IronSourceWebView$JSInterface$2 */
        class C07272 implements Runnable {
            C07272() {
            }

            public void run() {
                if (VERSION.SDK_INT >= 21) {
                    IronSourceWebView.this.getSettings().setMixedContentMode(0);
                }
            }
        }

        class JSCallbackTask {
            JSCallbackTask() {
            }

            void sendMessage(boolean isSuccess, String callbackFunction, String messageData) {
                SSAObj res = new SSAObj();
                res.put(isSuccess ? IronSourceWebView.JSON_KEY_SUCCESS : IronSourceWebView.JSON_KEY_FAIL, callbackFunction);
                res.put("data", messageData);
                IronSourceWebView.this.responseBack(res.toString(), isSuccess, null, null);
            }

            void sendMessage(boolean isSuccess, String callbackFunction, SSAObj messageData) {
                messageData.put(isSuccess ? IronSourceWebView.JSON_KEY_SUCCESS : IronSourceWebView.JSON_KEY_FAIL, callbackFunction);
                IronSourceWebView.this.responseBack(messageData.toString(), isSuccess, null, null);
            }
        }

        public JSInterface(Context context) {
        }

        @JavascriptInterface
        public void initController(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "initController(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            if (ssaObj.containsKey(ParametersKeys.STAGE)) {
                String stage = ssaObj.getString(ParametersKeys.STAGE);
                if (ParametersKeys.READY.equalsIgnoreCase(stage)) {
                    handleControllerStageReady();
                } else if (ParametersKeys.LOADED.equalsIgnoreCase(stage)) {
                    handleControllerStageLoaded();
                } else if (ParametersKeys.FAILED.equalsIgnoreCase(stage)) {
                    handleControllerStageFailed();
                } else {
                    Logger.m1212i(IronSourceWebView.this.TAG, "No STAGE mentioned! Should not get here!");
                }
                IronSourceWebView.this.runOnUiThread(new C07261());
            }
        }

        void handleControllerStageLoaded() {
            IronSourceWebView.this.mControllerState = ControllerState.Loaded;
        }

        void handleControllerStageReady() {
            IronSourceWebView.this.mControllerState = ControllerState.Ready;
            IronSourceWebView.this.mGlobalControllerTimer.cancel();
            IronSourceWebView.this.mLoadControllerTimer.cancel();
            IronSourceWebView.this.invokePendingCommands();
            for (DemandSource demandSource : IronSourceWebView.this.mDemandSourceManager.getDemandSources(ProductType.RewardedVideo)) {
                if (demandSource.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.initRewardedVideo(IronSourceWebView.this.mRVAppKey, IronSourceWebView.this.mRVUserId, demandSource, IronSourceWebView.this.mDSRewardedVideoListener);
                }
            }
            for (DemandSource demandSource2 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(ProductType.Interstitial)) {
                if (demandSource2.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.initInterstitial(IronSourceWebView.this.mISAppKey, IronSourceWebView.this.mISUserId, demandSource2, IronSourceWebView.this.mDSInterstitialListener);
                }
            }
            if (IronSourceWebView.this.mOWmiss) {
                IronSourceWebView.this.initOfferWall(IronSourceWebView.this.mOWAppKey, IronSourceWebView.this.mOWUserId, IronSourceWebView.this.mOWExtraParameters, IronSourceWebView.this.mOnOfferWallListener);
            }
            if (IronSourceWebView.this.mOWCreditsMiss) {
                IronSourceWebView.this.getOfferWallCredits(IronSourceWebView.this.mOWCreditsAppKey, IronSourceWebView.this.mOWCreditsUserId, IronSourceWebView.this.mOnOfferWallListener);
            }
            IronSourceWebView.this.restoreState(IronSourceWebView.this.mSavedState);
        }

        void handleControllerStageFailed() {
            IronSourceWebView.this.mControllerState = ControllerState.Failed;
            for (DemandSource demandSource : IronSourceWebView.this.mDemandSourceManager.getDemandSources(ProductType.RewardedVideo)) {
                if (demandSource.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.sendProductErrorMessage(ProductType.RewardedVideo, demandSource.getDemandSourceName());
                }
            }
            for (DemandSource demandSource2 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(ProductType.Interstitial)) {
                if (demandSource2.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.sendProductErrorMessage(ProductType.Interstitial, demandSource2.getDemandSourceName());
                }
            }
            if (IronSourceWebView.this.mOWmiss) {
                IronSourceWebView.this.sendProductErrorMessage(ProductType.OfferWall, null);
            }
            if (IronSourceWebView.this.mOWCreditsMiss) {
                IronSourceWebView.this.sendProductErrorMessage(ProductType.OfferWallCredits, null);
            }
        }

        @JavascriptInterface
        public void alert(String message) {
        }

        @JavascriptInterface
        public void getDeviceStatus(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getDeviceStatus(" + value + ")");
            String successFunToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
            String failFunToCall = IronSourceWebView.this.extractFailFunctionToCall(value);
            Object[] resultArr = new Object[2];
            resultArr = IronSourceWebView.this.getDeviceParams(IronSourceWebView.this.getContext());
            String params = resultArr[0];
            String funToCall = null;
            if (((Boolean) resultArr[1]).booleanValue()) {
                if (!TextUtils.isEmpty(failFunToCall)) {
                    funToCall = failFunToCall;
                }
            } else if (!TextUtils.isEmpty(successFunToCall)) {
                funToCall = successFunToCall;
            }
            if (!TextUtils.isEmpty(funToCall)) {
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, params, JSMethods.ON_GET_DEVICE_STATUS_SUCCESS, JSMethods.ON_GET_DEVICE_STATUS_FAIL));
            }
        }

        @JavascriptInterface
        public void setMixedContentAlwaysAllow(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setMixedContentAlwaysAllow(" + value + ")");
            IronSourceWebView.this.runOnUiThread(new C07272());
        }

        @JavascriptInterface
        public void setAllowFileAccessFromFileURLs(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setAllowFileAccessFromFileURLs(" + value + ")");
            final boolean allow = new SSAObj(value).getBoolean(ParametersKeys.ALLOW_FILE_ACCESS);
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                public void run() {
                    if (VERSION.SDK_INT >= 16) {
                        try {
                            IronSourceWebView.this.getSettings().setAllowFileAccessFromFileURLs(allow);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        @JavascriptInterface
        public void getControllerConfig(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getControllerConfig(" + value + ")");
            String successFunToCall = new SSAObj(value).getString(IronSourceWebView.JSON_KEY_SUCCESS);
            if (!TextUtils.isEmpty(successFunToCall)) {
                String params = SDKUtils.getControllerConfig();
                String testerParameters = SDKUtils.getTesterParameters();
                if (areTesterParametersValid(testerParameters)) {
                    try {
                        params = addTesterParametersToConfig(params, testerParameters);
                    } catch (JSONException e) {
                        Logger.m1208d(IronSourceWebView.this.TAG, "getControllerConfig Error while parsing Tester AB Group parameters");
                    }
                }
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(successFunToCall, params));
            }
        }

        @JavascriptInterface
        public String addTesterParametersToConfig(String originalConfiguration, String testerParameters) throws JSONException {
            JSONObject config = new JSONObject(originalConfiguration);
            JSONObject testerParams = new JSONObject(testerParameters);
            config.putOpt("testerABGroup", testerParams.get("testerABGroup"));
            config.putOpt("testFriendlyName", testerParams.get("testFriendlyName"));
            return config.toString();
        }

        @JavascriptInterface
        public boolean areTesterParametersValid(String testerParameters) {
            if (!(TextUtils.isEmpty(testerParameters) || testerParameters.contains("-1"))) {
                try {
                    JSONObject testerParams = new JSONObject(testerParameters);
                    if (!(testerParams.getString("testerABGroup").isEmpty() || testerParams.getString("testFriendlyName").isEmpty())) {
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        @JavascriptInterface
        public void getApplicationInfo(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getApplicationInfo(" + value + ")");
            String successFunToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
            String failFunToCall = IronSourceWebView.this.extractFailFunctionToCall(value);
            SSAObj ssaObj = new SSAObj(value);
            String funToCall = null;
            Object[] resultArr = new Object[2];
            resultArr = IronSourceWebView.this.getApplicationParams(ssaObj.getString(ParametersKeys.PRODUCT_TYPE), ssaObj.getString("demandSourceName"));
            String params = resultArr[0];
            if (((Boolean) resultArr[1]).booleanValue()) {
                if (!TextUtils.isEmpty(failFunToCall)) {
                    funToCall = failFunToCall;
                }
            } else if (!TextUtils.isEmpty(successFunToCall)) {
                funToCall = successFunToCall;
            }
            if (!TextUtils.isEmpty(funToCall)) {
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, params, JSMethods.ON_GET_APPLICATION_INFO_SUCCESS, JSMethods.ON_GET_APPLICATION_INFO_FAIL));
            }
        }

        @JavascriptInterface
        public void checkInstalledApps(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "checkInstalledApps(" + value + ")");
            String successFunToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
            String failFunToCall = IronSourceWebView.this.extractFailFunctionToCall(value);
            String funToCall = null;
            SSAObj ssaObj = new SSAObj(value);
            Object[] resultArr = IronSourceWebView.this.getAppsStatus(ssaObj.getString(IronSourceWebView.APP_IDS), ssaObj.getString(IronSourceWebView.REQUEST_ID));
            String params = resultArr[0];
            if (((Boolean) resultArr[1]).booleanValue()) {
                if (!TextUtils.isEmpty(failFunToCall)) {
                    funToCall = failFunToCall;
                }
            } else if (!TextUtils.isEmpty(successFunToCall)) {
                funToCall = successFunToCall;
            }
            if (!TextUtils.isEmpty(funToCall)) {
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, params, JSMethods.ON_CHECK_INSTALLED_APPS_SUCCESS, JSMethods.ON_CHECK_INSTALLED_APPS_FAIL));
            }
        }

        @JavascriptInterface
        public void saveFile(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "saveFile(" + value + ")");
            SSAFile ssaFile = new SSAFile(value);
            if (DeviceStatus.getAvailableMemorySizeInMegaBytes(IronSourceWebView.this.mCacheDirectory) <= 0) {
                IronSourceWebView.this.responseBack(value, false, DownloadManager.NO_DISK_SPACE, null);
            } else if (!SDKUtils.isExternalStorageAvailable()) {
                IronSourceWebView.this.responseBack(value, false, DownloadManager.STORAGE_UNAVAILABLE, null);
            } else if (IronSourceStorageUtils.isFileCached(IronSourceWebView.this.mCacheDirectory, ssaFile)) {
                IronSourceWebView.this.responseBack(value, false, DownloadManager.FILE_ALREADY_EXIST, null);
            } else if (ConnectivityService.isConnected(IronSourceWebView.this.getContext())) {
                IronSourceWebView.this.responseBack(value, true, null, null);
                String lastUpdateTimeObj = ssaFile.getLastUpdateTime();
                if (lastUpdateTimeObj != null) {
                    String lastUpdateTimeStr = String.valueOf(lastUpdateTimeObj);
                    if (!TextUtils.isEmpty(lastUpdateTimeStr)) {
                        String folder;
                        String path = ssaFile.getPath();
                        if (path.contains("/")) {
                            String[] splitArr = ssaFile.getPath().split("/");
                            folder = splitArr[splitArr.length - 1];
                        } else {
                            folder = path;
                        }
                        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(folder, lastUpdateTimeStr);
                    }
                }
                IronSourceWebView.this.downloadManager.downloadFile(ssaFile);
            } else {
                IronSourceWebView.this.responseBack(value, false, DownloadManager.NO_NETWORK_CONNECTION, null);
            }
        }

        @JavascriptInterface
        public void adUnitsReady(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "adUnitsReady(" + value + ")");
            final String demandSourceName = new SSAObj(value).getString("demandSourceName");
            final AdUnitsReady adUnitsReady = new AdUnitsReady(value);
            if (adUnitsReady.isNumOfAdUnitsExist()) {
                IronSourceWebView.this.responseBack(value, true, null, null);
                final String product = adUnitsReady.getProductType();
                if (IronSourceWebView.this.shouldNotifyDeveloper(product)) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        public void run() {
                            int adUnits = Integer.parseInt(adUnitsReady.getNumOfAdUnits());
                            if (!product.equalsIgnoreCase(ProductType.RewardedVideo.toString())) {
                                return;
                            }
                            if (adUnits > 0) {
                                Log.d(IronSourceWebView.this.TAG, "onRVInitSuccess()");
                                IronSourceWebView.this.mDSRewardedVideoListener.onAdProductInitSuccess(ProductType.RewardedVideo, demandSourceName, adUnitsReady);
                                return;
                            }
                            IronSourceWebView.this.mDSRewardedVideoListener.onRVNoMoreOffers(demandSourceName);
                        }
                    });
                    return;
                }
                return;
            }
            IronSourceWebView.this.responseBack(value, false, ErrorCodes.NUM_OF_AD_UNITS_DO_NOT_EXIST, null);
        }

        @JavascriptInterface
        public void deleteFolder(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "deleteFolder(" + value + ")");
            SSAFile file = new SSAFile(value);
            if (IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, file.getPath())) {
                IronSourceWebView.this.responseBack(value, IronSourceStorageUtils.deleteFolder(IronSourceWebView.this.mCacheDirectory, file.getPath()), null, null);
                return;
            }
            IronSourceWebView.this.responseBack(value, false, ErrorCodes.FOLDER_NOT_EXIST_MSG, "1");
        }

        @JavascriptInterface
        public void deleteFile(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "deleteFile(" + value + ")");
            SSAFile file = new SSAFile(value);
            if (IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, file.getPath())) {
                IronSourceWebView.this.responseBack(value, IronSourceStorageUtils.deleteFile(IronSourceWebView.this.mCacheDirectory, file.getPath(), file.getFile()), null, null);
                return;
            }
            IronSourceWebView.this.responseBack(value, false, ErrorCodes.FILE_NOT_EXIST_MSG, "1");
        }

        @JavascriptInterface
        public void displayWebView(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "displayWebView(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            SSAObj ssaObj = new SSAObj(value);
            boolean display = ((Boolean) ssaObj.get("display")).booleanValue();
            String productType = ssaObj.getString(ParametersKeys.PRODUCT_TYPE);
            boolean isStandaloneView = ssaObj.getBoolean(ParametersKeys.IS_STANDALONE_VIEW);
            String demandSourceName = ssaObj.getString("demandSourceName");
            boolean isRewardedVideo = false;
            if (display) {
                IronSourceWebView.this.mIsImmersive = ssaObj.getBoolean(ParametersKeys.IMMERSIVE);
                IronSourceWebView.this.mIsActivityThemeTranslucent = ssaObj.getBoolean(ParametersKeys.ACTIVITY_THEME_TRANSLUCENT);
                if (IronSourceWebView.this.getState() != State.Display) {
                    IronSourceWebView.this.setState(State.Display);
                    Logger.m1212i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
                    Context context = IronSourceWebView.this.getCurrentActivityContext();
                    String orientation = IronSourceWebView.this.getOrientationState();
                    int rotation = DeviceStatus.getApplicationRotation(context);
                    if (isStandaloneView) {
                        ControllerView controllerView = new ControllerView(context);
                        controllerView.addView(IronSourceWebView.this.mControllerLayout);
                        controllerView.showInterstitial(IronSourceWebView.this);
                        return;
                    }
                    Intent intent;
                    if (IronSourceWebView.this.mIsActivityThemeTranslucent) {
                        intent = new Intent(context, InterstitialActivity.class);
                    } else {
                        intent = new Intent(context, ControllerActivity.class);
                    }
                    if (ProductType.RewardedVideo.toString().equalsIgnoreCase(productType)) {
                        if (ParametersKeys.ORIENTATION_APPLICATION.equals(orientation)) {
                            orientation = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                        }
                        isRewardedVideo = true;
                        intent.putExtra(ParametersKeys.PRODUCT_TYPE, ProductType.RewardedVideo.toString());
                        IronSourceWebView.this.mSavedState.adOpened(ProductType.RewardedVideo.ordinal());
                        IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(demandSourceName);
                    } else if (ProductType.OfferWall.toString().equalsIgnoreCase(productType)) {
                        intent.putExtra(ParametersKeys.PRODUCT_TYPE, ProductType.OfferWall.toString());
                        IronSourceWebView.this.mSavedState.adOpened(ProductType.OfferWall.ordinal());
                    } else if (ProductType.Interstitial.toString().equalsIgnoreCase(productType) && ParametersKeys.ORIENTATION_APPLICATION.equals(orientation)) {
                        orientation = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                    }
                    if (isRewardedVideo && IronSourceWebView.this.shouldNotifyDeveloper(ProductType.RewardedVideo.toString())) {
                        IronSourceWebView.this.mDSRewardedVideoListener.onAdProductOpen(ProductType.RewardedVideo, demandSourceName);
                    }
                    intent.setFlags(DriveFile.MODE_WRITE_ONLY);
                    intent.putExtra(ParametersKeys.IMMERSIVE, IronSourceWebView.this.mIsImmersive);
                    intent.putExtra(ParametersKeys.ORIENTATION_SET_FLAG, orientation);
                    intent.putExtra(ParametersKeys.ROTATION_SET_FLAG, rotation);
                    context.startActivity(intent);
                    return;
                }
                Logger.m1212i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
                return;
            }
            IronSourceWebView.this.setState(State.Gone);
            IronSourceWebView.this.closeWebView();
        }

        @JavascriptInterface
        public void getOrientation(String value) {
            String funToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
            String params = SDKUtils.getOrientation(IronSourceWebView.this.getCurrentActivityContext()).toString();
            if (!TextUtils.isEmpty(funToCall)) {
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, params, JSMethods.ON_GET_ORIENTATION_SUCCESS, JSMethods.ON_GET_ORIENTATION_FAIL));
            }
        }

        @JavascriptInterface
        public void setOrientation(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setOrientation(" + value + ")");
            String orientation = new SSAObj(value).getString("orientation");
            IronSourceWebView.this.setOrientationState(orientation);
            int rotation = DeviceStatus.getApplicationRotation(IronSourceWebView.this.getCurrentActivityContext());
            if (IronSourceWebView.this.mChangeListener != null) {
                IronSourceWebView.this.mChangeListener.onOrientationChanged(orientation, rotation);
            }
        }

        @JavascriptInterface
        public void getCachedFilesMap(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getCachedFilesMap(" + value + ")");
            String funToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
            if (!TextUtils.isEmpty(funToCall)) {
                SSAObj ssaObj = new SSAObj(value);
                if (ssaObj.containsKey("path")) {
                    String mapPath = (String) ssaObj.get("path");
                    if (IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, mapPath)) {
                        IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, IronSourceStorageUtils.getCachedFilesMap(IronSourceWebView.this.mCacheDirectory, mapPath), JSMethods.ON_GET_CACHED_FILES_MAP_SUCCESS, JSMethods.ON_GET_CACHED_FILES_MAP_FAIL));
                        return;
                    }
                    IronSourceWebView.this.responseBack(value, false, ErrorCodes.PATH_FILE_DOES_NOT_EXIST_ON_DISK, null);
                    return;
                }
                IronSourceWebView.this.responseBack(value, false, ErrorCodes.PATH_KEY_DOES_NOT_EXIST, null);
            }
        }

        private void callJavaScriptFunction(String funToCall, String params) {
            if (!TextUtils.isEmpty(funToCall)) {
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, params));
            }
        }

        @JavascriptInterface
        public void getDemandSourceState(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getMediationState(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            String demandSourceName = ssaObj.getString("demandSourceName");
            String product = ssaObj.getString(ParametersKeys.PRODUCT_TYPE);
            if (product != null && demandSourceName != null) {
                try {
                    ProductType productType = SDKUtils.getProductType(product);
                    if (productType != null) {
                        String funToCall;
                        DemandSource demandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(productType, demandSourceName);
                        JSONObject obj = new JSONObject();
                        obj.put(ParametersKeys.PRODUCT_TYPE, product);
                        obj.put("demandSourceName", demandSourceName);
                        if (demandSource == null || demandSource.isMediationState(-1)) {
                            funToCall = IronSourceWebView.this.extractFailFunctionToCall(value);
                        } else {
                            funToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
                            obj.put("state", demandSource.getMediationState());
                        }
                        callJavaScriptFunction(funToCall, obj.toString());
                    }
                } catch (Exception ex) {
                    IronSourceWebView.this.responseBack(value, false, ex.getMessage(), null);
                    ex.printStackTrace();
                }
            }
        }

        @JavascriptInterface
        public void adCredited(String value) {
            String appKey;
            String userId;
            Log.d(IronSourceWebView.this.PUB_TAG, "adCredited(" + value + ")");
            SSAObj sSAObj = new SSAObj(value);
            String creditsStr = sSAObj.getString(ParametersKeys.CREDITS);
            final int credits = creditsStr != null ? Integer.parseInt(creditsStr) : 0;
            String totalCreditsStr = sSAObj.getString(ParametersKeys.TOTAL);
            final int totalCredits = totalCreditsStr != null ? Integer.parseInt(totalCreditsStr) : 0;
            final String demandSourceName = sSAObj.getString("demandSourceName");
            final String product = sSAObj.getString(ParametersKeys.PRODUCT_TYPE);
            boolean totalCreditsFlag = false;
            String latestCompeltionsTime = null;
            boolean md5Signature = false;
            if (sSAObj.getBoolean("externalPoll")) {
                appKey = IronSourceWebView.this.mOWCreditsAppKey;
                userId = IronSourceWebView.this.mOWCreditsUserId;
            } else {
                appKey = IronSourceWebView.this.mOWAppKey;
                userId = IronSourceWebView.this.mOWUserId;
            }
            if (product.equalsIgnoreCase(ProductType.OfferWall.toString())) {
                if (!sSAObj.isNull(InAppPurchaseMetaData.KEY_SIGNATURE)) {
                    if (!sSAObj.isNull("timestamp")) {
                        if (!sSAObj.isNull("totalCreditsFlag")) {
                            if (sSAObj.getString(InAppPurchaseMetaData.KEY_SIGNATURE).equalsIgnoreCase(SDKUtils.getMD5(totalCreditsStr + appKey + userId))) {
                                md5Signature = true;
                            } else {
                                IronSourceWebView.this.responseBack(value, false, "Controller signature is not equal to SDK signature", null);
                            }
                            totalCreditsFlag = sSAObj.getBoolean("totalCreditsFlag");
                            latestCompeltionsTime = sSAObj.getString("timestamp");
                        }
                    }
                }
                IronSourceWebView.this.responseBack(value, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
                return;
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(product)) {
                final boolean mTotalCreditsFlag = totalCreditsFlag;
                final String mlatestCompeltionsTime = latestCompeltionsTime;
                final boolean mMd5Signature = md5Signature;
                final String str = value;
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (product.equalsIgnoreCase(ProductType.RewardedVideo.toString())) {
                            IronSourceWebView.this.mDSRewardedVideoListener.onRVAdCredited(demandSourceName, credits);
                        } else if (!product.equalsIgnoreCase(ProductType.OfferWall.toString()) || !mMd5Signature || !IronSourceWebView.this.mOnOfferWallListener.onOWAdCredited(credits, totalCredits, mTotalCreditsFlag) || TextUtils.isEmpty(mlatestCompeltionsTime)) {
                        } else {
                            if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setLatestCompeltionsTime(mlatestCompeltionsTime, appKey, userId)) {
                                IronSourceWebView.this.responseBack(str, true, null, null);
                            } else {
                                IronSourceWebView.this.responseBack(str, false, "Time Stamp could not be stored", null);
                            }
                        }
                    }
                });
            }
        }

        @JavascriptInterface
        public void removeCloseEventHandler(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "removeCloseEventHandler(" + value + ")");
            if (IronSourceWebView.this.mCloseEventTimer != null) {
                IronSourceWebView.this.mCloseEventTimer.cancel();
            }
            IronSourceWebView.this.isRemoveCloseEventHandler = true;
        }

        @JavascriptInterface
        public void onGetDeviceStatusSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetDeviceStatusSuccess(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_GET_DEVICE_STATUS_SUCCESS, value);
        }

        @JavascriptInterface
        public void onGetDeviceStatusFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetDeviceStatusFail(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_GET_DEVICE_STATUS_FAIL, value);
        }

        @JavascriptInterface
        public void onInitRewardedVideoSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onInitRewardedVideoSuccess(" + value + ")");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSSABCParameters(new SSABCParameters(value));
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_INIT_REWARDED_VIDEO_SUCCESS, value);
        }

        @JavascriptInterface
        public void onInitRewardedVideoFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onInitRewardedVideoFail(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            final String message = ssaObj.getString(ParametersKeys.ERR_MSG);
            final String demandSourceName = ssaObj.getString("demandSourceName");
            DemandSource demandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(ProductType.RewardedVideo, demandSourceName);
            if (demandSource != null) {
                demandSource.setDemandSourceInitState(3);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        String toSend = message;
                        if (toSend == null) {
                            toSend = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onRVInitFail(message:" + toSend + ")");
                        IronSourceWebView.this.mDSRewardedVideoListener.onAdProductInitFailed(ProductType.RewardedVideo, demandSourceName, toSend);
                    }
                });
            }
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_INIT_REWARDED_VIDEO_FAIL, value);
        }

        @JavascriptInterface
        public void onGetApplicationInfoSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetApplicationInfoSuccess(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_GET_APPLICATION_INFO_SUCCESS, value);
        }

        @JavascriptInterface
        public void onGetApplicationInfoFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetApplicationInfoFail(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_GET_APPLICATION_INFO_FAIL, value);
        }

        @JavascriptInterface
        public void onShowRewardedVideoSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onShowRewardedVideoSuccess(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_SHOW_REWARDED_VIDEO_SUCCESS, value);
        }

        @JavascriptInterface
        public void onShowRewardedVideoFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onShowRewardedVideoFail(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            final String message = ssaObj.getString(ParametersKeys.ERR_MSG);
            final String demandSourceName = ssaObj.getString("demandSourceName");
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        String toSend = message;
                        if (toSend == null) {
                            toSend = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onRVShowFail(message:" + message + ")");
                        IronSourceWebView.this.mDSRewardedVideoListener.onRVShowFail(demandSourceName, toSend);
                    }
                });
            }
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_SHOW_REWARDED_VIDEO_FAIL, value);
        }

        @JavascriptInterface
        public void onGetCachedFilesMapSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetCachedFilesMapSuccess(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_GET_CACHED_FILES_MAP_SUCCESS, value);
        }

        @JavascriptInterface
        public void onGetCachedFilesMapFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetCachedFilesMapFail(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_GET_CACHED_FILES_MAP_FAIL, value);
        }

        @JavascriptInterface
        public void onShowOfferWallSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onShowOfferWallSuccess(" + value + ")");
            IronSourceWebView.this.mSavedState.adOpened(ProductType.OfferWall.ordinal());
            final String placementId = SDKUtils.getValueFromJsonObject(value, "placementId");
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        IronSourceWebView.this.mOnOfferWallListener.onOWShowSuccess(placementId);
                    }
                });
            }
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_SHOW_OFFER_WALL_SUCCESS, value);
        }

        @JavascriptInterface
        public void onShowOfferWallFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onShowOfferWallFail(" + value + ")");
            final String message = new SSAObj(value).getString(ParametersKeys.ERR_MSG);
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        String toSend = message;
                        if (toSend == null) {
                            toSend = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mOnOfferWallListener.onOWShowFail(toSend);
                    }
                });
            }
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_SHOW_OFFER_WALL_FAIL, value);
        }

        @JavascriptInterface
        public void onInitInterstitialSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess()");
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_INIT_INTERSTITIAL_SUCCESS, "true");
            final String demandSourceName = new SSAObj(value).getString("demandSourceName");
            if (TextUtils.isEmpty(demandSourceName)) {
                Logger.m1212i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
            } else if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d(IronSourceWebView.this.TAG, "onInterstitialInitSuccess()");
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductInitSuccess(ProductType.Interstitial, demandSourceName, null);
                    }
                });
            }
        }

        @JavascriptInterface
        public void onInitInterstitialFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onInitInterstitialFail(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            final String message = ssaObj.getString(ParametersKeys.ERR_MSG);
            final String demandSourceName = ssaObj.getString("demandSourceName");
            if (TextUtils.isEmpty(demandSourceName)) {
                Logger.m1212i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
                return;
            }
            DemandSource demandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(ProductType.Interstitial, demandSourceName);
            if (demandSource != null) {
                demandSource.setDemandSourceInitState(3);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        String toSend = message;
                        if (toSend == null) {
                            toSend = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onInterstitialInitFail(message:" + toSend + ")");
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductInitFailed(ProductType.Interstitial, demandSourceName, toSend);
                    }
                });
            }
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_INIT_INTERSTITIAL_FAIL, value);
        }

        private void setInterstitialAvailability(String demandSourceName, boolean isAvailable) {
            DemandSource demandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(ProductType.Interstitial, demandSourceName);
            if (demandSource != null) {
                demandSource.setAvailabilityState(isAvailable);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.Interstitial.toString())) {
                IronSourceWebView.this.toastingErrMsg(JSMethods.ON_INTERSTITIAL_AVAILABILITY, String.valueOf(isAvailable + " with demand " + demandSourceName));
            }
        }

        @JavascriptInterface
        public void adClicked(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "adClicked(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            String productType = ssaObj.getString(ParametersKeys.PRODUCT_TYPE);
            final String demandSourceName = ssaObj.getString("demandSourceName");
            if (!TextUtils.isEmpty(demandSourceName)) {
                final ProductType eProductType = IronSourceWebView.this.getStringProductTypeAsEnum(productType);
                final DSAdProductListener listener = IronSourceWebView.this.getAdProductListenerByProductType(eProductType);
                if (eProductType != null && listener != null) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        public void run() {
                            listener.onAdProductClick(eProductType, demandSourceName);
                        }
                    });
                }
            }
        }

        @JavascriptInterface
        public void onShowInterstitialSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess(" + value + ")");
            IronSourceWebView.this.responseBack(value, true, null, null);
            final String demandSourceName = new SSAObj(value).getString("demandSourceName");
            if (TextUtils.isEmpty(demandSourceName)) {
                Logger.m1212i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess called with no demand");
                return;
            }
            IronSourceWebView.this.mSavedState.adOpened(ProductType.Interstitial.ordinal());
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(demandSourceName);
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductOpen(ProductType.Interstitial, demandSourceName);
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialShowSuccess(demandSourceName);
                    }
                });
                IronSourceWebView.this.toastingErrMsg(JSMethods.ON_SHOW_INTERSTITIAL_SUCCESS, value);
            }
            setInterstitialAvailability(demandSourceName, false);
        }

        @JavascriptInterface
        public void onInitOfferWallSuccess(String value) {
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_INIT_OFFERWALL_SUCCESS, "true");
            IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(true);
            if (IronSourceWebView.this.mSavedState.reportInitOfferwall()) {
                IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
                if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.OfferWall.toString())) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Log.d(IronSourceWebView.this.TAG, "onOfferWallInitSuccess()");
                            IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitSuccess();
                        }
                    });
                }
            }
        }

        @JavascriptInterface
        public void onInitOfferWallFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onInitOfferWallFail(" + value + ")");
            IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(false);
            final String message = new SSAObj(value).getString(ParametersKeys.ERR_MSG);
            if (IronSourceWebView.this.mSavedState.reportInitOfferwall()) {
                IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
                if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.OfferWall.toString())) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        public void run() {
                            String toSend = message;
                            if (toSend == null) {
                                toSend = "We're sorry, some error occurred. we will investigate it";
                            }
                            Log.d(IronSourceWebView.this.TAG, "onOfferWallInitFail(message:" + toSend + ")");
                            IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(toSend);
                        }
                    });
                }
            }
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_INIT_OFFERWALL_FAIL, value);
        }

        @JavascriptInterface
        public void onLoadInterstitialSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onLoadInterstitialSuccess(" + value + ")");
            final String demandSourceName = new SSAObj(value).getString("demandSourceName");
            setInterstitialAvailability(demandSourceName, true);
            IronSourceWebView.this.responseBack(value, true, null, null);
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadSuccess(demandSourceName);
                    }
                });
            }
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_LOAD_INTERSTITIAL_SUCCESS, "true");
        }

        @JavascriptInterface
        public void onLoadInterstitialFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onLoadInterstitialFail(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            final String message = ssaObj.getString(ParametersKeys.ERR_MSG);
            final String demandSourceName = ssaObj.getString("demandSourceName");
            IronSourceWebView.this.responseBack(value, true, null, null);
            if (!TextUtils.isEmpty(demandSourceName)) {
                if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.Interstitial.toString())) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        public void run() {
                            String toSend = message;
                            if (toSend == null) {
                                toSend = "We're sorry, some error occurred. we will investigate it";
                            }
                            IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadFailed(demandSourceName, toSend);
                        }
                    });
                }
                IronSourceWebView.this.toastingErrMsg(JSMethods.ON_LOAD_INTERSTITIAL_FAIL, "true");
            }
        }

        @JavascriptInterface
        public void onShowInterstitialFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onShowInterstitialFail(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            final String message = ssaObj.getString(ParametersKeys.ERR_MSG);
            final String demandSourceName = ssaObj.getString("demandSourceName");
            IronSourceWebView.this.responseBack(value, true, null, null);
            if (!TextUtils.isEmpty(demandSourceName)) {
                setInterstitialAvailability(demandSourceName, false);
                if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.Interstitial.toString())) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        public void run() {
                            String toSend = message;
                            if (toSend == null) {
                                toSend = "We're sorry, some error occurred. we will investigate it";
                            }
                            IronSourceWebView.this.mDSInterstitialListener.onInterstitialShowFailed(demandSourceName, toSend);
                        }
                    });
                }
                IronSourceWebView.this.toastingErrMsg(JSMethods.ON_SHOW_INTERSTITIAL_FAIL, value);
            }
        }

        @JavascriptInterface
        public void onGenericFunctionSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGenericFunctionSuccess(" + value + ")");
            if (IronSourceWebView.this.mOnGenericFunctionListener == null) {
                Logger.m1208d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
                return;
            }
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                public void run() {
                    IronSourceWebView.this.mOnGenericFunctionListener.onGFSuccess();
                }
            });
            IronSourceWebView.this.responseBack(value, true, null, null);
        }

        @JavascriptInterface
        public void onGenericFunctionFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGenericFunctionFail(" + value + ")");
            if (IronSourceWebView.this.mOnGenericFunctionListener == null) {
                Logger.m1208d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
                return;
            }
            final String message = new SSAObj(value).getString(ParametersKeys.ERR_MSG);
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                public void run() {
                    IronSourceWebView.this.mOnGenericFunctionListener.onGFFail(message);
                }
            });
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_GENERIC_FUNCTION_FAIL, value);
        }

        @JavascriptInterface
        public void createCalendarEvent(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "createCalendarEvent(" + value + ")");
        }

        @JavascriptInterface
        public void openUrl(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "openUrl(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            String url = ssaObj.getString("url");
            String method = ssaObj.getString("method");
            Context context = IronSourceWebView.this.getCurrentActivityContext();
            try {
                if (method.equalsIgnoreCase(ParametersKeys.EXTERNAL_BROWSER)) {
                    UrlHandler.openUrl(context, url);
                } else if (method.equalsIgnoreCase(ParametersKeys.WEB_VIEW)) {
                    intent = new Intent(context, OpenUrlActivity.class);
                    intent.putExtra(IronSourceWebView.EXTERNAL_URL, url);
                    intent.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
                    intent.putExtra(ParametersKeys.IMMERSIVE, IronSourceWebView.this.mIsImmersive);
                    context.startActivity(intent);
                } else if (method.equalsIgnoreCase("store")) {
                    intent = new Intent(context, OpenUrlActivity.class);
                    intent.putExtra(IronSourceWebView.EXTERNAL_URL, url);
                    intent.putExtra(IronSourceWebView.IS_STORE, true);
                    intent.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
                    context.startActivity(intent);
                }
            } catch (Exception ex) {
                IronSourceWebView.this.responseBack(value, false, ex.getMessage(), null);
                ex.printStackTrace();
            }
        }

        @JavascriptInterface
        public void setForceClose(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setForceClose(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            String width = ssaObj.getString("width");
            String hight = ssaObj.getString("height");
            IronSourceWebView.this.mHiddenForceCloseWidth = Integer.parseInt(width);
            IronSourceWebView.this.mHiddenForceCloseHeight = Integer.parseInt(hight);
            IronSourceWebView.this.mHiddenForceCloseLocation = ssaObj.getString(ParametersKeys.POSITION);
        }

        @JavascriptInterface
        public void setBackButtonState(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setBackButtonState(" + value + ")");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setBackButtonState(new SSAObj(value).getString("state"));
        }

        @JavascriptInterface
        public void setStoreSearchKeys(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setStoreSearchKeys(" + value + ")");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSearchKeys(value);
        }

        @JavascriptInterface
        public void setWebviewBackgroundColor(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setWebviewBackgroundColor(" + value + ")");
            IronSourceWebView.this.setWebviewBackground(value);
        }

        @JavascriptInterface
        public void toggleUDIA(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "toggleUDIA(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            if (ssaObj.containsKey(ParametersKeys.TOGGLE)) {
                int toggle = Integer.parseInt(ssaObj.getString(ParametersKeys.TOGGLE));
                if (toggle != 0) {
                    String binaryToggle = Integer.toBinaryString(toggle);
                    if (TextUtils.isEmpty(binaryToggle)) {
                        IronSourceWebView.this.responseBack(value, false, ErrorCodes.FIALED_TO_CONVERT_TOGGLE, null);
                        return;
                    } else if (binaryToggle.toCharArray()[3] == '0') {
                        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(true);
                        return;
                    } else {
                        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(false);
                        return;
                    }
                }
                return;
            }
            IronSourceWebView.this.responseBack(value, false, ErrorCodes.TOGGLE_KEY_DOES_NOT_EXIST, null);
        }

        @JavascriptInterface
        public void getUDIA(String value) {
            this.udiaResults = 0;
            Logger.m1212i(IronSourceWebView.this.TAG, "getUDIA(" + value + ")");
            String funToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
            SSAObj ssaObj = new SSAObj(value);
            if (ssaObj.containsKey(ParametersKeys.GET_BY_FLAG)) {
                int getByFlag = Integer.parseInt(ssaObj.getString(ParametersKeys.GET_BY_FLAG));
                if (getByFlag != 0) {
                    String binaryToggle = Integer.toBinaryString(getByFlag);
                    if (TextUtils.isEmpty(binaryToggle)) {
                        IronSourceWebView.this.responseBack(value, false, ErrorCodes.FIALED_TO_CONVERT_GET_BY_FLAG, null);
                        return;
                    }
                    JSONObject jsObj;
                    char[] binaryToggleArr = new StringBuilder(binaryToggle).reverse().toString().toCharArray();
                    JSONArray jsArr = new JSONArray();
                    if (binaryToggleArr[3] == '0') {
                        jsObj = new JSONObject();
                        try {
                            jsObj.put("sessions", IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSessions());
                            IronSourceSharedPrefHelper.getSupersonicPrefHelper().deleteSessions();
                            jsArr.put(jsObj);
                        } catch (JSONException e) {
                        }
                    }
                    if (binaryToggleArr[2] == '1') {
                        this.udiaResults++;
                        Location location = LocationService.getLastLocation(IronSourceWebView.this.getContext());
                        if (location != null) {
                            jsObj = new JSONObject();
                            try {
                                jsObj.put(LocationConst.LATITUDE, location.getLatitude());
                                jsObj.put(LocationConst.LONGITUDE, location.getLongitude());
                                jsArr.put(jsObj);
                                this.udiaResults--;
                                sendResults(funToCall, jsArr);
                                Logger.m1212i(IronSourceWebView.this.TAG, "done location");
                                return;
                            } catch (JSONException e2) {
                                return;
                            }
                        }
                        this.udiaResults--;
                        return;
                    }
                    return;
                }
                return;
            }
            IronSourceWebView.this.responseBack(value, false, ErrorCodes.GET_BY_FLAG_KEY_DOES_NOT_EXIST, null);
        }

        private void sendResults(String funToCall, JSONArray jsArr) {
            Logger.m1212i(IronSourceWebView.this.TAG, "sendResults: " + this.udiaResults);
            if (this.udiaResults <= 0) {
                injectGetUDIA(funToCall, jsArr);
            }
        }

        @JavascriptInterface
        public void onUDIASuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onUDIASuccess(" + value + ")");
        }

        @JavascriptInterface
        public void onUDIAFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onUDIAFail(" + value + ")");
        }

        @JavascriptInterface
        public void onGetUDIASuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetUDIASuccess(" + value + ")");
        }

        @JavascriptInterface
        public void onGetUDIAFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetUDIAFail(" + value + ")");
        }

        @JavascriptInterface
        public void setUserUniqueId(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setUserUniqueId(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            if (ssaObj.containsKey(ParametersKeys.USER_UNIQUE_ID) && ssaObj.containsKey(ParametersKeys.PRODUCT_TYPE)) {
                if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUniqueId(ssaObj.getString(ParametersKeys.USER_UNIQUE_ID), ssaObj.getString(ParametersKeys.PRODUCT_TYPE))) {
                    IronSourceWebView.this.responseBack(value, true, null, null);
                    return;
                } else {
                    IronSourceWebView.this.responseBack(value, false, ErrorCodes.SET_USER_UNIQUE_ID_FAILED, null);
                    return;
                }
            }
            IronSourceWebView.this.responseBack(value, false, ErrorCodes.UNIQUE_ID_OR_PRODUCT_TYPE_DOES_NOT_EXIST, null);
        }

        @JavascriptInterface
        public void getUserUniqueId(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getUserUniqueId(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            if (ssaObj.containsKey(ParametersKeys.PRODUCT_TYPE)) {
                String funToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
                if (!TextUtils.isEmpty(funToCall)) {
                    String productType = ssaObj.getString(ParametersKeys.PRODUCT_TYPE);
                    IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, IronSourceWebView.this.parseToJson(ParametersKeys.USER_UNIQUE_ID, IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId(productType), ParametersKeys.PRODUCT_TYPE, productType, null, null, null, null, null, false), JSMethods.ON_GET_USER_UNIQUE_ID_SUCCESS, JSMethods.ON_GET_USER_UNIQUE_ID_FAIL));
                    return;
                }
                return;
            }
            IronSourceWebView.this.responseBack(value, false, ErrorCodes.PRODUCT_TYPE_DOES_NOT_EXIST, null);
        }

        @JavascriptInterface
        public void getAppsInstallTime(String value) {
            String dataToSend;
            boolean failed = true;
            String funToCall = null;
            try {
                dataToSend = DeviceStatus.getAppsInstallTime(IronSourceWebView.this.getContext(), Boolean.parseBoolean(new SSAObj(value).getString(ParametersKeys.INCLUDE_SYSTEM_APPS))).toString();
                failed = false;
            } catch (Exception e) {
                Logger.m1212i(IronSourceWebView.this.TAG, "getAppsInstallTime failed(" + e.getLocalizedMessage() + ")");
                dataToSend = e.getLocalizedMessage();
            }
            if (failed) {
                String failFunToCall = IronSourceWebView.this.extractFailFunctionToCall(value);
                if (!TextUtils.isEmpty(failFunToCall)) {
                    funToCall = failFunToCall;
                }
            } else {
                String successFunToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
                if (!TextUtils.isEmpty(successFunToCall)) {
                    funToCall = successFunToCall;
                }
            }
            if (!TextUtils.isEmpty(funToCall)) {
                try {
                    dataToSend = URLDecoder.decode(dataToSend, Charset.defaultCharset().name());
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, dataToSend));
            }
        }

        @JavascriptInterface
        public void onGetUserUniqueIdSuccess(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetUserUniqueIdSuccess(" + value + ")");
        }

        @JavascriptInterface
        public void onGetUserUniqueIdFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetUserUniqueIdFail(" + value + ")");
        }

        private void injectGetUDIA(String funToCall, JSONArray jsonArr) {
            if (!TextUtils.isEmpty(funToCall)) {
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, jsonArr.toString(), JSMethods.ON_GET_UDIA_SUCCESS, JSMethods.ON_GET_UDIA_FAIL));
            }
        }

        @JavascriptInterface
        public void onOfferWallGeneric(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onOfferWallGeneric(" + value + ")");
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.OfferWall.toString())) {
                IronSourceWebView.this.mOnOfferWallListener.onOWGeneric("", "");
            }
        }

        @JavascriptInterface
        public void setUserData(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "setUserData(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            if (!ssaObj.containsKey(ParametersKeys.KEY)) {
                IronSourceWebView.this.responseBack(value, false, ErrorCodes.KEY_DOES_NOT_EXIST, null);
            } else if (ssaObj.containsKey("value")) {
                String mKey = ssaObj.getString(ParametersKeys.KEY);
                String mValue = ssaObj.getString("value");
                if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserData(mKey, mValue)) {
                    IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(IronSourceWebView.this.extractSuccessFunctionToCall(value), IronSourceWebView.this.parseToJson(mKey, mValue, null, null, null, null, null, null, null, false)));
                    return;
                }
                IronSourceWebView.this.responseBack(value, false, "SetUserData failed writing to shared preferences", null);
            } else {
                IronSourceWebView.this.responseBack(value, false, ErrorCodes.VALUE_DOES_NOT_EXIST, null);
            }
        }

        @JavascriptInterface
        public void getUserData(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getUserData(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            if (ssaObj.containsKey(ParametersKeys.KEY)) {
                String failFunToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
                String mKey = ssaObj.getString(ParametersKeys.KEY);
                IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(failFunToCall, IronSourceWebView.this.parseToJson(mKey, IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUserData(mKey), null, null, null, null, null, null, null, false)));
                return;
            }
            IronSourceWebView.this.responseBack(value, false, ErrorCodes.KEY_DOES_NOT_EXIST, null);
        }

        @JavascriptInterface
        public void onGetUserCreditsFail(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onGetUserCreditsFail(" + value + ")");
            final String message = new SSAObj(value).getString(ParametersKeys.ERR_MSG);
            if (IronSourceWebView.this.shouldNotifyDeveloper(ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        String toSend = message;
                        if (toSend == null) {
                            toSend = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(toSend);
                    }
                });
            }
            IronSourceWebView.this.responseBack(value, true, null, null);
            IronSourceWebView.this.toastingErrMsg(JSMethods.ON_GET_USER_CREDITS_FAILED, value);
        }

        @JavascriptInterface
        public void onAdWindowsClosed(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "onAdWindowsClosed(" + value + ")");
            IronSourceWebView.this.mSavedState.adClosed();
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(null);
            SSAObj ssaObj = new SSAObj(value);
            String product = ssaObj.getString(ParametersKeys.PRODUCT_TYPE);
            final String demandSourceName = ssaObj.getString("demandSourceName");
            final ProductType type = IronSourceWebView.this.getStringProductTypeAsEnum(product);
            Log.d(IronSourceWebView.this.PUB_TAG, "onAdClosed() with type " + type);
            if (IronSourceWebView.this.shouldNotifyDeveloper(product) && product != null) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (type == ProductType.RewardedVideo || type == ProductType.Interstitial) {
                            DSAdProductListener listener = IronSourceWebView.this.getAdProductListenerByProductType(type);
                            if (listener != null) {
                                listener.onAdProductClose(type, demandSourceName);
                            }
                        } else if (type == ProductType.OfferWall) {
                            IronSourceWebView.this.mOnOfferWallListener.onOWAdClosed();
                        }
                    }
                });
            }
        }

        @JavascriptInterface
        public void onVideoStatusChanged(String value) {
            Log.d(IronSourceWebView.this.TAG, "onVideoStatusChanged(" + value + ")");
            SSAObj ssaObj = new SSAObj(value);
            String product = ssaObj.getString(ParametersKeys.PRODUCT_TYPE);
            if (IronSourceWebView.this.mVideoEventsListener != null && !TextUtils.isEmpty(product) && ProductType.RewardedVideo.toString().equalsIgnoreCase(product)) {
                String status = ssaObj.getString("status");
                if (ParametersKeys.VIDEO_STATUS_STARTED.equalsIgnoreCase(status)) {
                    IronSourceWebView.this.mVideoEventsListener.onVideoStarted();
                } else if ("paused".equalsIgnoreCase(status)) {
                    IronSourceWebView.this.mVideoEventsListener.onVideoPaused();
                } else if ("playing".equalsIgnoreCase(status)) {
                    IronSourceWebView.this.mVideoEventsListener.onVideoResumed();
                } else if (ParametersKeys.VIDEO_STATUS_ENDED.equalsIgnoreCase(status)) {
                    IronSourceWebView.this.mVideoEventsListener.onVideoEnded();
                } else if ("stopped".equalsIgnoreCase(status)) {
                    IronSourceWebView.this.mVideoEventsListener.onVideoStopped();
                } else {
                    Logger.m1212i(IronSourceWebView.this.TAG, "onVideoStatusChanged: unknown status: " + status);
                }
            }
        }

        @JavascriptInterface
        public void postAdEventNotification(String value) {
            try {
                Logger.m1212i(IronSourceWebView.this.TAG, "postAdEventNotification(" + value + ")");
                SSAObj sSAObj = new SSAObj(value);
                String eventName = sSAObj.getString("eventName");
                if (TextUtils.isEmpty(eventName)) {
                    IronSourceWebView.this.responseBack(value, false, ErrorCodes.EVENT_NAME_DOES_NOT_EXIST, null);
                    return;
                }
                String demandSourceName = sSAObj.getString(ParametersKeys.NOTIFICATION_DEMAND_SOURCE_NAME);
                final JSONObject extData = (JSONObject) sSAObj.get(ParametersKeys.EXTRA_DATA);
                String productType = sSAObj.getString(ParametersKeys.PRODUCT_TYPE);
                ProductType type = IronSourceWebView.this.getStringProductTypeAsEnum(productType);
                if (IronSourceWebView.this.shouldNotifyDeveloper(productType)) {
                    String funToCall = IronSourceWebView.this.extractSuccessFunctionToCall(value);
                    if (!TextUtils.isEmpty(funToCall)) {
                        IronSourceWebView.this.injectJavascript(IronSourceWebView.this.generateJSToInject(funToCall, IronSourceWebView.this.parseToJson(ParametersKeys.PRODUCT_TYPE, productType, "eventName", eventName, "demandSourceName", demandSourceName, null, null, null, false), JSMethods.POST_AD_EVENT_NOTIFICATION_SUCCESS, JSMethods.POST_AD_EVENT_NOTIFICATION_FAIL));
                    }
                    final ProductType productType2 = type;
                    final String str = demandSourceName;
                    final String str2 = eventName;
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (productType2 == ProductType.Interstitial || productType2 == ProductType.RewardedVideo) {
                                DSAdProductListener listener = IronSourceWebView.this.getAdProductListenerByProductType(productType2);
                                if (listener != null) {
                                    listener.onAdProductEventNotificationReceived(productType2, str, str2, extData);
                                }
                            } else if (productType2 == ProductType.OfferWall) {
                                IronSourceWebView.this.mOnOfferWallListener.onOfferwallEventNotificationReceived(str2, extData);
                            }
                        }
                    });
                    return;
                }
                IronSourceWebView.this.responseBack(value, false, ErrorCodes.PRODUCT_TYPE_DOES_NOT_EXIST, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void moatAPI(final String value) {
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        Logger.m1212i(IronSourceWebView.this.TAG, "moatAPI(" + value + ")");
                        IronSourceWebView.this.mMoatJsAdapter.call(new SSAObj(value).toString(), new JSCallbackTask(), IronSourceWebView.this.getWebview());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.m1212i(IronSourceWebView.this.TAG, "moatAPI failed with exception " + e.getMessage());
                    }
                }
            });
        }

        @JavascriptInterface
        public void permissionsAPI(String value) {
            try {
                Logger.m1212i(IronSourceWebView.this.TAG, "permissionsAPI(" + value + ")");
                IronSourceWebView.this.mPermissionsJsAdapter.call(new SSAObj(value).toString(), new JSCallbackTask());
            } catch (Exception e) {
                e.printStackTrace();
                Logger.m1212i(IronSourceWebView.this.TAG, "permissionsAPI failed with exception " + e.getMessage());
            }
        }

        @JavascriptInterface
        public void getDeviceVolume(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getDeviceVolume(" + value + ")");
            try {
                float currVolume = DeviceProperties.getInstance(IronSourceWebView.this.getCurrentActivityContext()).getDeviceVolume(IronSourceWebView.this.getCurrentActivityContext());
                SSAObj ssaObj = new SSAObj(value);
                ssaObj.put(RequestParameters.DEVICE_VOLUME, String.valueOf(currVolume));
                IronSourceWebView.this.responseBack(ssaObj.toString(), true, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void locationServicesEnabled(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "locationServicesEnabled(" + value + ")");
            try {
                boolean locationServicesEnabled = LocationService.locationServicesEnabled(IronSourceWebView.this.getContext());
                SSAObj ssaObj = new SSAObj(value);
                ssaObj.put("status", String.valueOf(locationServicesEnabled));
                IronSourceWebView.this.responseBack(ssaObj.toString(), true, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void getDeviceLocation(String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getDeviceLocation(" + value + ")");
            try {
                IronSourceWebView.this.responseBack(IronSourceWebView.this.createLocationObject(value, LocationService.getLastLocation(IronSourceWebView.this.getContext())).toString(), true, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void getDevicePreciseLocation(final String value) {
            Logger.m1212i(IronSourceWebView.this.TAG, "getDevicePreciseLocation(" + value + ")");
            try {
                LocationService.getPreciseLocation(IronSourceWebView.this.getContext(), new ISLocationListener() {
                    public void onLocationChanged(Location location) {
                        IronSourceWebView.this.responseBack(IronSourceWebView.this.createLocationObject(value, location).toString(), true, null, null);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public enum State {
        Display,
        Gone
    }

    private class SupersonicWebViewTouchListener implements OnTouchListener {
        private SupersonicWebViewTouchListener() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == 1) {
                float xTouch = event.getX();
                float yTouch = event.getY();
                Logger.m1212i(IronSourceWebView.this.TAG, "X:" + ((int) xTouch) + " Y:" + ((int) yTouch));
                int width = DeviceStatus.getDeviceWidth();
                int height = DeviceStatus.getDeviceHeight();
                Logger.m1212i(IronSourceWebView.this.TAG, "Width:" + width + " Height:" + height);
                int boundsTouchAreaX = SDKUtils.dpToPx((long) IronSourceWebView.this.mHiddenForceCloseWidth);
                int boundsTouchAreaY = SDKUtils.dpToPx((long) IronSourceWebView.this.mHiddenForceCloseHeight);
                int actualTouchX = 0;
                int actualTouchY = 0;
                if (ForceClosePosition.TOP_RIGHT.equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    actualTouchX = width - ((int) xTouch);
                    actualTouchY = (int) yTouch;
                } else if (ForceClosePosition.TOP_LEFT.equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    actualTouchX = (int) xTouch;
                    actualTouchY = (int) yTouch;
                } else if (ForceClosePosition.BOTTOM_RIGHT.equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    actualTouchX = width - ((int) xTouch);
                    actualTouchY = height - ((int) yTouch);
                } else if (ForceClosePosition.BOTTOM_LEFT.equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    actualTouchX = (int) xTouch;
                    actualTouchY = height - ((int) yTouch);
                }
                if (actualTouchX <= boundsTouchAreaX && actualTouchY <= boundsTouchAreaY) {
                    IronSourceWebView.this.isRemoveCloseEventHandler = false;
                    if (IronSourceWebView.this.mCloseEventTimer != null) {
                        IronSourceWebView.this.mCloseEventTimer.cancel();
                    }
                    IronSourceWebView.this.mCloseEventTimer = new CountDownTimer(FetchConst.DEFAULT_ON_UPDATE_INTERVAL, 500) {
                        public void onTick(long millisUntilFinished) {
                            Logger.m1212i(IronSourceWebView.this.TAG, "Close Event Timer Tick " + millisUntilFinished);
                        }

                        public void onFinish() {
                            Logger.m1212i(IronSourceWebView.this.TAG, "Close Event Timer Finish");
                            if (IronSourceWebView.this.isRemoveCloseEventHandler) {
                                IronSourceWebView.this.isRemoveCloseEventHandler = false;
                            } else {
                                IronSourceWebView.this.engageEnd("forceClose");
                            }
                        }
                    }.start();
                }
            }
            return false;
        }
    }

    private class ViewClient extends WebViewClient {
        private ViewClient() {
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Logger.m1212i("onPageStarted", url);
            super.onPageStarted(view, url, favicon);
        }

        public void onPageFinished(WebView view, String url) {
            Logger.m1212i("onPageFinished", url);
            if (url.contains("adUnit") || url.contains("index.html")) {
                IronSourceWebView.this.pageFinished();
            }
            super.onPageFinished(view, url);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Logger.m1212i("onReceivedError", failingUrl + " " + description);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logger.m1212i("shouldOverrideUrlLoading", url);
            try {
                if (IronSourceWebView.this.handleSearchKeysURLs(url)) {
                    IronSourceWebView.this.interceptedUrlToStore();
                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            Logger.m1212i("shouldInterceptRequest", url);
            boolean mraidCall = false;
            try {
                if (new URL(url).getFile().contains("mraid.js")) {
                    mraidCall = true;
                }
            } catch (MalformedURLException e) {
            }
            if (mraidCall) {
                String filePath = "file://" + IronSourceWebView.this.mCacheDirectory + File.separator + "mraid.js";
                try {
                    FileInputStream fis = new FileInputStream(new File(filePath));
                    return new WebResourceResponse("text/javascript", "UTF-8", getClass().getResourceAsStream(filePath));
                } catch (FileNotFoundException e2) {
                }
            }
            return super.shouldInterceptRequest(view, url);
        }
    }

    private Map<String, String> getExtraParamsByProduct(ProductType type) {
        if (type == ProductType.OfferWall) {
            return this.mOWExtraParameters;
        }
        return null;
    }

    public IronSourceWebView(Context context, DemandSourceManager demandSourceManager) {
        super(context.getApplicationContext());
        Logger.m1212i(this.TAG, "C'tor");
        this.mControllerCommandsQueue = new ArrayList();
        this.mCacheDirectory = initializeCacheDirectory(context.getApplicationContext());
        this.mCurrentActivityContext = context;
        this.mDemandSourceManager = demandSourceManager;
        initLayout(this.mCurrentActivityContext);
        this.mSavedState = new AdUnitsState();
        this.downloadManager = getDownloadManager();
        this.downloadManager.setOnPreCacheCompletion(this);
        this.mWebChromeClient = new ChromeClient();
        setWebViewClient(new ViewClient());
        setWebChromeClient(this.mWebChromeClient);
        setWebViewSettings();
        addJavascriptInterface(createJSInterface(context), Constants.JAVASCRIPT_INTERFACE_NAME);
        setDownloadListener(this);
        setOnTouchListener(new SupersonicWebViewTouchListener());
        this.mUiHandler = createMainThreadHandler();
    }

    JSInterface createJSInterface(Context context) {
        return new JSInterface(context);
    }

    Handler createMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

    DownloadManager getDownloadManager() {
        return DownloadManager.getInstance(this.mCacheDirectory);
    }

    String initializeCacheDirectory(Context context) {
        return IronSourceStorageUtils.initializeCacheDirectory(context.getApplicationContext());
    }

    public void addMoatJSInterface(MOATJSAdapter moatjsAdapter) {
        this.mMoatJsAdapter = moatjsAdapter;
    }

    public void addPermissionsJSInterface(PermissionsJSAdapter permissionsJSAdapter) {
        this.mPermissionsJsAdapter = permissionsJSAdapter;
    }

    public void notifyLifeCycle(String productType, String event) {
        injectJavascript(generateJSToInject(JSMethods.ON_NATIVE_LIFE_CYCLE_EVENT, parseToJson(ParametersKeys.LIFE_CYCLE_EVENT, event, ParametersKeys.PRODUCT_TYPE, productType, null, null, null, null, null, false)));
    }

    private void initLayout(Context context) {
        LayoutParams coverScreenParams = new LayoutParams(-1, -1);
        this.mControllerLayout = new FrameLayout(context);
        this.mCustomViewContainer = new FrameLayout(context);
        this.mCustomViewContainer.setLayoutParams(new LayoutParams(-1, -1));
        this.mCustomViewContainer.setVisibility(8);
        FrameLayout mContentView = new FrameLayout(context);
        mContentView.setLayoutParams(new LayoutParams(-1, -1));
        mContentView.addView(this);
        this.mControllerLayout.addView(this.mCustomViewContainer, coverScreenParams);
        this.mControllerLayout.addView(mContentView);
    }

    private void setWebViewSettings() {
        WebSettings s = getSettings();
        s.setLoadWithOverviewMode(true);
        s.setUseWideViewPort(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        if (VERSION.SDK_INT >= 16) {
            try {
                getSettings().setAllowFileAccessFromFileURLs(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        s.setBuiltInZoomControls(false);
        s.setJavaScriptEnabled(true);
        s.setSupportMultipleWindows(true);
        s.setJavaScriptCanOpenWindowsAutomatically(true);
        s.setGeolocationEnabled(true);
        s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
        s.setDomStorageEnabled(true);
        try {
            setDisplayZoomControls(s);
            setMediaPlaybackJellyBean(s);
        } catch (Throwable e2) {
            Logger.m1210e(this.TAG, "setWebSettings - " + e2.toString());
        }
    }

    private void setDisplayZoomControls(WebSettings s) {
        if (VERSION.SDK_INT > 11) {
            s.setDisplayZoomControls(false);
        }
    }

    public WebBackForwardList saveState(Bundle outState) {
        return super.saveState(outState);
    }

    @SuppressLint({"NewApi"})
    private void setMediaPlaybackJellyBean(WebSettings s) {
        if (VERSION.SDK_INT >= 17) {
            s.setMediaPlaybackRequiresUserGesture(false);
        }
    }

    @SuppressLint({"NewApi"})
    private void setWebDebuggingEnabled() {
        if (VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
    }

    public void downloadController() {
        IronSourceStorageUtils.deleteFile(this.mCacheDirectory, "", Constants.MOBILE_CONTROLLER_HTML);
        String controllerUrl = SDKUtils.getControllerUrl();
        SSAFile indexHtml = new SSAFile(controllerUrl, "");
        this.mGlobalControllerTimer = new CountDownTimer(200000, 1000) {
            public void onTick(long millisUntilFinished) {
                Logger.m1212i(IronSourceWebView.this.TAG, "Global Controller Timer Tick " + millisUntilFinished);
            }

            public void onFinish() {
                Logger.m1212i(IronSourceWebView.this.TAG, "Global Controller Timer Finish");
                IronSourceWebView.this.mGlobalControllerTimeFinish = true;
            }
        }.start();
        if (this.downloadManager.isMobileControllerThreadLive()) {
            Logger.m1212i(this.TAG, "Download Mobile Controller: already alive");
            return;
        }
        Logger.m1212i(this.TAG, "Download Mobile Controller: " + controllerUrl);
        this.downloadManager.downloadMobileControllerFile(indexHtml);
    }

    public void setDebugMode(int debugMode) {
        mDebugMode = debugMode;
    }

    public int getDebugMode() {
        return mDebugMode;
    }

    private boolean shouldNotifyDeveloper(String product) {
        boolean shouldNotify = false;
        if (TextUtils.isEmpty(product)) {
            Logger.m1208d(this.TAG, "Trying to trigger a listener - no product was found");
            return false;
        }
        if (product.equalsIgnoreCase(ProductType.Interstitial.toString())) {
            shouldNotify = this.mDSInterstitialListener != null;
        } else if (product.equalsIgnoreCase(ProductType.RewardedVideo.toString())) {
            shouldNotify = this.mDSRewardedVideoListener != null;
        } else if (product.equalsIgnoreCase(ProductType.OfferWall.toString()) || product.equalsIgnoreCase(ProductType.OfferWallCredits.toString())) {
            shouldNotify = this.mOnOfferWallListener != null;
        }
        if (!shouldNotify) {
            Logger.m1208d(this.TAG, "Trying to trigger a listener - no listener was found for product " + product);
        }
        return shouldNotify;
    }

    public void setOrientationState(String orientation) {
        this.mOrientationState = orientation;
    }

    public String getOrientationState() {
        return this.mOrientationState;
    }

    private void invokePendingCommands() {
        while (this.mControllerCommandsQueue.size() > 0) {
            injectJavascript((String) this.mControllerCommandsQueue.get(0));
            this.mControllerCommandsQueue.remove(0);
        }
    }

    private SSAObj createLocationObject(String value, Location location) {
        SSAObj ssaObj = new SSAObj(value);
        if (location != null) {
            ssaObj.put(LocationConst.PROVIDER, location.getProvider());
            ssaObj.put(LocationConst.LATITUDE, Double.toString(location.getLatitude()));
            ssaObj.put(LocationConst.LONGITUDE, Double.toString(location.getLongitude()));
            ssaObj.put(LocationConst.ALTITUDE, Double.toString(location.getAltitude()));
            ssaObj.put(LocationConst.TIME, Long.toString(location.getTime()));
            ssaObj.put(LocationConst.ACCURACY, Float.toString(location.getAccuracy()));
            ssaObj.put(LocationConst.BEARING, Float.toString(location.getBearing()));
            ssaObj.put(LocationConst.SPEED, Float.toString(location.getSpeed()));
        } else {
            ssaObj.put("error", "location data is not available");
        }
        return ssaObj;
    }

    private DSAdProductListener getAdProductListenerByProductType(ProductType type) {
        if (type == ProductType.Interstitial) {
            return this.mDSInterstitialListener;
        }
        if (type == ProductType.RewardedVideo) {
            return this.mDSRewardedVideoListener;
        }
        return null;
    }

    private ProductType getStringProductTypeAsEnum(String productType) {
        if (TextUtils.isEmpty(productType)) {
            return null;
        }
        if (productType.equalsIgnoreCase(ProductType.Interstitial.toString())) {
            return ProductType.Interstitial;
        }
        if (productType.equalsIgnoreCase(ProductType.RewardedVideo.toString())) {
            return ProductType.RewardedVideo;
        }
        if (productType.equalsIgnoreCase(ProductType.OfferWall.toString())) {
            return ProductType.OfferWall;
        }
        return null;
    }

    public static void setEXTERNAL_URL(String EXTERNAL_URL) {
        EXTERNAL_URL = EXTERNAL_URL;
    }

    public void setVideoEventsListener(VideoEventsListener listener) {
        this.mVideoEventsListener = listener;
    }

    public void removeVideoEventsListener() {
        this.mVideoEventsListener = null;
    }

    private void setWebviewBackground(String value) {
        String keyColor = new SSAObj(value).getString(ParametersKeys.COLOR);
        int bgColor = 0;
        if (!"transparent".equalsIgnoreCase(keyColor)) {
            bgColor = Color.parseColor(keyColor);
        }
        setBackgroundColor(bgColor);
    }

    public void load(int loadAttemp) {
        try {
            loadUrl("about:blank");
        } catch (Throwable e) {
            Logger.m1210e(this.TAG, "WebViewController:: load: " + e.toString());
            new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank"});
        }
        String controllerPath = "file://" + this.mCacheDirectory + File.separator + Constants.MOBILE_CONTROLLER_HTML;
        if (new File(this.mCacheDirectory + File.separator + Constants.MOBILE_CONTROLLER_HTML).exists()) {
            JSONObject configObject = SDKUtils.getControllerConfigAsJSONObject();
            setWebDebuggingEnabled(configObject);
            this.mRequestParameters = getRequestParameters(configObject);
            String controllerPathWithParams = controllerPath + "?" + this.mRequestParameters;
            final int i = loadAttemp;
            this.mLoadControllerTimer = new CountDownTimer(50000, 1000) {
                public void onTick(long millisUntilFinished) {
                    Logger.m1212i(IronSourceWebView.this.TAG, "Loading Controller Timer Tick " + millisUntilFinished);
                }

                public void onFinish() {
                    Logger.m1212i(IronSourceWebView.this.TAG, "Loading Controller Timer Finish");
                    if (i == 3) {
                        IronSourceWebView.this.mGlobalControllerTimer.cancel();
                        for (DemandSource demandSource : IronSourceWebView.this.mDemandSourceManager.getDemandSources(ProductType.RewardedVideo)) {
                            if (demandSource.getDemandSourceInitState() == 1) {
                                IronSourceWebView.this.sendProductErrorMessage(ProductType.RewardedVideo, demandSource.getDemandSourceName());
                            }
                        }
                        for (DemandSource demandSource2 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(ProductType.Interstitial)) {
                            if (demandSource2.getDemandSourceInitState() == 1) {
                                IronSourceWebView.this.sendProductErrorMessage(ProductType.Interstitial, demandSource2.getDemandSourceName());
                            }
                        }
                        if (IronSourceWebView.this.mOWmiss) {
                            IronSourceWebView.this.sendProductErrorMessage(ProductType.OfferWall, null);
                        }
                        if (IronSourceWebView.this.mOWCreditsMiss) {
                            IronSourceWebView.this.sendProductErrorMessage(ProductType.OfferWallCredits, null);
                            return;
                        }
                        return;
                    }
                    IronSourceWebView.this.load(2);
                }
            }.start();
            try {
                loadUrl(controllerPathWithParams);
            } catch (Throwable e2) {
                Logger.m1210e(this.TAG, "WebViewController:: load: " + e2.toString());
                new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath"});
            }
            Logger.m1212i(this.TAG, "load(): " + controllerPathWithParams);
            return;
        }
        Logger.m1212i(this.TAG, "load(): Mobile Controller HTML Does not exist");
        new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem"});
    }

    private void setWebDebuggingEnabled(JSONObject configObject) {
        if (configObject.optBoolean("inspectWebview")) {
            setWebDebuggingEnabled();
        }
    }

    private void initProduct(String applicationKey, String userId, ProductType type, DemandSource demandSource, String action) {
        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(applicationKey)) {
            triggerOnControllerInitProductFail("User id or Application key are missing", type, demandSource.getDemandSourceName());
        } else if (this.mControllerState == ControllerState.Ready) {
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setApplicationKey(applicationKey, type);
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserID(userId, type);
            injectJavascript(createInitProductJSMethod(type, demandSource));
        } else {
            setMissProduct(type, demandSource);
            if (this.mControllerState == ControllerState.Failed) {
                triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(action, ErrorCodes.InitiatingController), type, demandSource.getDemandSourceName());
            } else if (this.mGlobalControllerTimeFinish) {
                downloadController();
            }
        }
    }

    public void initRewardedVideo(String applicationKey, String userId, DemandSource demandSource, DSRewardedVideoListener listener) {
        this.mRVAppKey = applicationKey;
        this.mRVUserId = userId;
        this.mProductParametersCollection.setProductParameters(ProductType.RewardedVideo, applicationKey, userId);
        this.mDSRewardedVideoListener = listener;
        this.mSavedState.setRVAppKey(applicationKey);
        this.mSavedState.setRVUserId(userId);
        initProduct(applicationKey, userId, ProductType.RewardedVideo, demandSource, ErrorCodes.InitRV);
    }

    public void initInterstitial(String applicationKey, String userId, DemandSource demandSource, DSInterstitialListener listener) {
        this.mISAppKey = applicationKey;
        this.mISUserId = userId;
        this.mProductParametersCollection.setProductParameters(ProductType.Interstitial, applicationKey, userId);
        this.mDSInterstitialListener = listener;
        this.mSavedState.setInterstitialAppKey(this.mISAppKey);
        this.mSavedState.setInterstitialUserId(this.mISUserId);
        initProduct(this.mISAppKey, this.mISUserId, ProductType.Interstitial, demandSource, ErrorCodes.InitIS);
    }

    public void loadInterstitial(final String demandSourceName) {
        Map<String, String> productParamsMap = new HashMap();
        if (!TextUtils.isEmpty(demandSourceName)) {
            productParamsMap.put("demandSourceName", demandSourceName);
        }
        String params = flatMapToJsonAsString(productParamsMap);
        if (!isInterstitialAdAvailable(demandSourceName)) {
            this.mSavedState.setReportLoadInterstitial(demandSourceName, true);
            injectJavascript(generateJSToInject(JSMethods.LOAD_INTERSTITIAL, params, JSMethods.ON_LOAD_INTERSTITIAL_SUCCESS, JSMethods.ON_LOAD_INTERSTITIAL_FAIL));
        } else if (shouldNotifyDeveloper(ProductType.Interstitial.toString())) {
            runOnUiThread(new Runnable() {
                public void run() {
                    IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadSuccess(demandSourceName);
                }
            });
        }
    }

    public boolean isInterstitialAdAvailable(String demandSourceName) {
        DemandSource demandSource = this.mDemandSourceManager.getDemandSourceByName(ProductType.Interstitial, demandSourceName);
        return demandSource != null && demandSource.getAvailabilityState();
    }

    public void showInterstitial(JSONObject showParams) {
        injectJavascript(createShowProductJSMethod(ProductType.Interstitial, showParams));
    }

    public void initOfferWall(String applicationKey, String userId, Map<String, String> extraParameters, OnOfferWallListener listener) {
        this.mOWAppKey = applicationKey;
        this.mOWUserId = userId;
        this.mProductParametersCollection.setProductParameters(ProductType.OfferWall, applicationKey, userId);
        this.mOWExtraParameters = extraParameters;
        this.mOnOfferWallListener = listener;
        this.mSavedState.setOfferWallExtraParams(this.mOWExtraParameters);
        this.mSavedState.setOfferwallReportInit(true);
        initProduct(this.mOWAppKey, this.mOWUserId, ProductType.OfferWall, null, ErrorCodes.InitOW);
    }

    public void showOfferWall(Map<String, String> extraParameters) {
        this.mOWExtraParameters = extraParameters;
        injectJavascript(generateJSToInject(JSMethods.SHOW_OFFER_WALL, JSMethods.ON_SHOW_OFFER_WALL_SUCCESS, JSMethods.ON_SHOW_OFFER_WALL_FAIL));
    }

    public void getOfferWallCredits(String applicationKey, String userId, OnOfferWallListener listener) {
        this.mOWCreditsAppKey = applicationKey;
        this.mOWCreditsUserId = userId;
        this.mProductParametersCollection.setProductParameters(ProductType.OfferWallCredits, applicationKey, userId);
        this.mOnOfferWallListener = listener;
        initProduct(this.mOWCreditsAppKey, this.mOWCreditsUserId, ProductType.OfferWallCredits, null, ErrorCodes.ShowOWCredits);
    }

    public void updateConsentInfo(JSONObject consentParams) {
        injectJavascript(JSMethods.UPDATE_CONSENT_INFO, generateJSToInject(JSMethods.UPDATE_CONSENT_INFO, consentParams != null ? consentParams.toString() : null));
    }

    private String createInitProductJSMethod(ProductType type, DemandSource demandSource) {
        String script = "";
        if (type == ProductType.RewardedVideo || type == ProductType.Interstitial || type == ProductType.OfferWall) {
            Map<String, String> productParamsMap = new HashMap();
            ProductParameters productParameters = this.mProductParametersCollection.getProductParameters(type);
            if (productParameters != null) {
                productParamsMap.put(RequestParameters.APPLICATION_KEY, productParameters.appKey);
                productParamsMap.put(RequestParameters.APPLICATION_USER_ID, productParameters.userId);
            }
            if (demandSource != null) {
                if (demandSource.getExtraParams() != null) {
                    productParamsMap.putAll(demandSource.getExtraParams());
                }
                productParamsMap.put("demandSourceName", demandSource.getDemandSourceName());
            } else if (getExtraParamsByProduct(type) != null) {
                productParamsMap.putAll(getExtraParamsByProduct(type));
            }
            String params = flatMapToJsonAsString(productParamsMap);
            JSMethods method = JSMethods.getInitMethodByProduct(type);
            return generateJSToInject(method.methodName, params, method.successCallbackName, method.failureCallbackName);
        } else if (type != ProductType.OfferWallCredits) {
            return script;
        } else {
            return generateJSToInject(JSMethods.GET_USER_CREDITS, parseToJson(ParametersKeys.PRODUCT_TYPE, ParametersKeys.OFFER_WALL, RequestParameters.APPLICATION_KEY, this.mOWCreditsAppKey, RequestParameters.APPLICATION_USER_ID, this.mOWCreditsUserId, null, null, null, false), "null", JSMethods.ON_GET_USER_CREDITS_FAILED);
        }
    }

    private String createShowProductJSMethod(ProductType type, JSONObject showParams) {
        String script = "";
        Map<String, String> paramsMap = new HashMap();
        paramsMap.put(RequestParameters.SESSION_DEPTH, Integer.toString(showParams.optInt(RequestParameters.SESSION_DEPTH)));
        String demandSourceName = showParams.optString("demandSourceName");
        DemandSource demandSource = this.mDemandSourceManager.getDemandSourceByName(type, demandSourceName);
        if (demandSource != null) {
            if (demandSource.getExtraParams() != null) {
                paramsMap.putAll(demandSource.getExtraParams());
            }
            if (!TextUtils.isEmpty(demandSourceName)) {
                paramsMap.put("demandSourceName", demandSourceName);
            }
        } else if (getExtraParamsByProduct(type) != null) {
            paramsMap.putAll(getExtraParamsByProduct(type));
        }
        String params = flatMapToJsonAsString(paramsMap);
        JSMethods method = JSMethods.getShowMethodByProduct(type);
        return generateJSToInject(method.methodName, params, method.successCallbackName, method.failureCallbackName);
    }

    private String flatMapToJsonAsString(Map<String, String> params) {
        JSONObject jsObj = new JSONObject();
        if (params != null) {
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, String> pairs = (Entry) it.next();
                try {
                    jsObj.putOpt((String) pairs.getKey(), SDKUtils.encodeString((String) pairs.getValue()));
                } catch (JSONException e) {
                    Logger.m1212i(this.TAG, "flatMapToJsonAsStringfailed " + e.toString());
                }
                it.remove();
            }
        }
        return jsObj.toString();
    }

    void setMissProduct(ProductType type, DemandSource demandSource) {
        if (type == ProductType.RewardedVideo || type == ProductType.Interstitial) {
            if (demandSource != null) {
                demandSource.setDemandSourceInitState(1);
            }
        } else if (type == ProductType.OfferWall) {
            this.mOWmiss = true;
        } else if (type == ProductType.OfferWallCredits) {
            this.mOWCreditsMiss = true;
        }
        Logger.m1212i(this.TAG, "setMissProduct(" + type + ")");
    }

    private void triggerOnControllerInitProductFail(final String message, final ProductType type, final String demandSourceName) {
        if (shouldNotifyDeveloper(type.toString())) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (ProductType.RewardedVideo == type || ProductType.Interstitial == type) {
                        if (!TextUtils.isEmpty(demandSourceName)) {
                            DSAdProductListener listener = IronSourceWebView.this.getAdProductListenerByProductType(type);
                            Log.d(IronSourceWebView.this.TAG, "onAdProductInitFailed (message:" + message + ")(" + type + ")");
                            if (listener != null) {
                                listener.onAdProductInitFailed(type, demandSourceName, message);
                            }
                        }
                    } else if (ProductType.OfferWall == type) {
                        IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(message);
                    } else if (ProductType.OfferWallCredits == type) {
                        IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(message);
                    }
                }
            });
        }
    }

    public void showRewardedVideo(JSONObject showParams) {
        injectJavascript(createShowProductJSMethod(ProductType.RewardedVideo, showParams));
    }

    public void assetCached(String file, String path) {
        injectJavascript(generateJSToInject(JSMethods.ASSET_CACHED, parseToJson(ParametersKeys.FILE, file, "path", path, null, null, null, null, null, false)));
    }

    public void assetCachedFailed(String file, String path, String errorMsg) {
        injectJavascript(generateJSToInject(JSMethods.ASSET_CACHED_FAILED, parseToJson(ParametersKeys.FILE, file, "path", path, ParametersKeys.ERR_MSG, errorMsg, null, null, null, false)));
    }

    public void enterBackground() {
        if (this.mControllerState == ControllerState.Ready) {
            injectJavascript(generateJSToInject(JSMethods.ENTER_BACKGROUND));
        }
    }

    public void enterForeground() {
        if (this.mControllerState == ControllerState.Ready) {
            injectJavascript(generateJSToInject(JSMethods.ENTER_FOREGROUND));
        }
    }

    public void viewableChange(boolean visibility, String webview) {
        injectJavascript(generateJSToInject(JSMethods.VIEWABLE_CHANGE, parseToJson(ParametersKeys.WEB_VIEW, webview, null, null, null, null, null, null, ParametersKeys.IS_VIEWABLE, visibility)));
    }

    public void nativeNavigationPressed(String action) {
        injectJavascript(generateJSToInject(JSMethods.NATIVE_NAVIGATION_PRESSED, parseToJson("action", action, null, null, null, null, null, null, null, false)));
    }

    public void pageFinished() {
        injectJavascript(generateJSToInject(JSMethods.PAGE_FINISHED));
    }

    public void interceptedUrlToStore() {
        injectJavascript(generateJSToInject(JSMethods.INTERCEPTED_URL_TO_STORE));
    }

    private void injectJavascript(String command, String script) {
        if (isControllerStateReady() || !controllerCommandSupportsQueue(command)) {
            injectJavascript(script);
        } else {
            this.mControllerCommandsQueue.add(script);
        }
    }

    private boolean isControllerStateReady() {
        return ControllerState.Ready.equals(this.mControllerState);
    }

    private void injectJavascript(String script) {
        if (!TextUtils.isEmpty(script)) {
            String catchClosure = "empty";
            if (getDebugMode() == DebugMode.MODE_0.getValue()) {
                catchClosure = "console.log(\"JS exeption: \" + JSON.stringify(e));";
            } else if (getDebugMode() >= DebugMode.MODE_1.getValue() && getDebugMode() <= DebugMode.MODE_3.getValue()) {
                catchClosure = "console.log(\"JS exeption: \" + JSON.stringify(e));";
            }
            final StringBuilder scriptBuilder = new StringBuilder();
            scriptBuilder.append("try{").append(script).append("}catch(e){").append(catchClosure).append("}");
            final String url = "javascript:" + scriptBuilder.toString();
            runOnUiThread(new Runnable() {
                public void run() {
                    Logger.m1212i(IronSourceWebView.this.TAG, url);
                    try {
                        if (IronSourceWebView.this.isKitkatAndAbove != null) {
                            if (IronSourceWebView.this.isKitkatAndAbove.booleanValue()) {
                                IronSourceWebView.this.evaluateJavascriptKitKat(scriptBuilder.toString());
                            } else {
                                IronSourceWebView.this.loadUrl(url);
                            }
                        } else if (VERSION.SDK_INT >= 19) {
                            IronSourceWebView.this.evaluateJavascriptKitKat(scriptBuilder.toString());
                            IronSourceWebView.this.isKitkatAndAbove = Boolean.valueOf(true);
                        } else {
                            IronSourceWebView.this.loadUrl(url);
                            IronSourceWebView.this.isKitkatAndAbove = Boolean.valueOf(false);
                        }
                    } catch (NoSuchMethodError e) {
                        Logger.m1210e(IronSourceWebView.this.TAG, "evaluateJavascrip NoSuchMethodError: SDK version=" + VERSION.SDK_INT + " " + e);
                        IronSourceWebView.this.loadUrl(url);
                        IronSourceWebView.this.isKitkatAndAbove = Boolean.valueOf(false);
                    } catch (Throwable t) {
                        Logger.m1210e(IronSourceWebView.this.TAG, "injectJavascript: " + t.toString());
                        new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=injectJavaScript"});
                    }
                }
            });
        }
    }

    @SuppressLint({"NewApi"})
    private void evaluateJavascriptKitKat(String script) {
        evaluateJavascript(script, null);
    }

    private boolean controllerCommandSupportsQueue(String command) {
        ArrayList<String> supportedCommand = new ArrayList();
        supportedCommand.add(JSMethods.UPDATE_CONSENT_INFO);
        return supportedCommand.contains(command);
    }

    public Context getCurrentActivityContext() {
        return this.mCurrentActivityContext.getBaseContext();
    }

    private String getRequestParameters(JSONObject configObject) {
        DeviceProperties properties = DeviceProperties.getInstance(getContext());
        StringBuilder builder = new StringBuilder();
        String sdkVer = DeviceProperties.getSupersonicSdkVersion();
        if (!TextUtils.isEmpty(sdkVer)) {
            builder.append(RequestParameters.SDK_VERSION).append(RequestParameters.EQUAL).append(sdkVer).append(RequestParameters.AMPERSAND);
        }
        String osType = properties.getDeviceOsType();
        if (!TextUtils.isEmpty(osType)) {
            builder.append(RequestParameters.DEVICE_OS).append(RequestParameters.EQUAL).append(osType);
        }
        Uri downloadUri = Uri.parse(SDKUtils.getControllerUrl());
        if (downloadUri != null) {
            String scheme = downloadUri.getScheme() + ":";
            String host = downloadUri.getHost();
            int port = downloadUri.getPort();
            if (port != -1) {
                host = host + ":" + port;
            }
            builder.append(RequestParameters.AMPERSAND).append(RequestParameters.PROTOCOL).append(RequestParameters.EQUAL).append(scheme);
            builder.append(RequestParameters.AMPERSAND).append("domain").append(RequestParameters.EQUAL).append(host);
            if (configObject.keys().hasNext()) {
                try {
                    String config = new JSONObject(configObject, new String[]{RequestParameters.IS_SECURED, RequestParameters.APPLICATION_KEY}).toString();
                    if (!TextUtils.isEmpty(config)) {
                        builder.append(RequestParameters.AMPERSAND).append(RequestParameters.CONTROLLER_CONFIG).append(RequestParameters.EQUAL).append(config);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            builder.append(RequestParameters.AMPERSAND).append("debug").append(RequestParameters.EQUAL).append(getDebugMode());
        }
        return builder.toString();
    }

    private void closeWebView() {
        if (this.mChangeListener != null) {
            this.mChangeListener.onCloseRequested();
        }
    }

    private WebView getWebview() {
        return this;
    }

    private void responseBack(String value, boolean result, String errorMessage, String errorCode) {
        SSAObj ssaObj = new SSAObj(value);
        String success = ssaObj.getString(JSON_KEY_SUCCESS);
        String fail = ssaObj.getString(JSON_KEY_FAIL);
        String funToCall = null;
        if (result) {
            if (!TextUtils.isEmpty(success)) {
                funToCall = success;
            }
        } else if (!TextUtils.isEmpty(fail)) {
            funToCall = fail;
        }
        if (!TextUtils.isEmpty(funToCall)) {
            if (!TextUtils.isEmpty(errorMessage)) {
                try {
                    value = new JSONObject(value).put(ParametersKeys.ERR_MSG, errorMessage).toString();
                } catch (JSONException e) {
                }
            }
            if (!TextUtils.isEmpty(errorCode)) {
                try {
                    value = new JSONObject(value).put(ParametersKeys.ERR_CODE, errorCode).toString();
                } catch (JSONException e2) {
                }
            }
            injectJavascript(generateJSToInject(funToCall, value));
        }
    }

    private String extractSuccessFunctionToCall(String jsonStr) {
        return new SSAObj(jsonStr).getString(JSON_KEY_SUCCESS);
    }

    private String extractFailFunctionToCall(String jsonStr) {
        return new SSAObj(jsonStr).getString(JSON_KEY_FAIL);
    }

    private String parseToJson(String key1, String value1, String key2, String value2, String key3, String value3, String key4, String value4, String key5, boolean value5) {
        JSONObject jsObj = new JSONObject();
        try {
            if (!(TextUtils.isEmpty(key1) || TextUtils.isEmpty(value1))) {
                jsObj.put(key1, SDKUtils.encodeString(value1));
            }
            if (!(TextUtils.isEmpty(key2) || TextUtils.isEmpty(value2))) {
                jsObj.put(key2, SDKUtils.encodeString(value2));
            }
            if (!(TextUtils.isEmpty(key3) || TextUtils.isEmpty(value3))) {
                jsObj.put(key3, SDKUtils.encodeString(value3));
            }
            if (!(TextUtils.isEmpty(key4) || TextUtils.isEmpty(value4))) {
                jsObj.put(key4, SDKUtils.encodeString(value4));
            }
            if (!TextUtils.isEmpty(key5)) {
                jsObj.put(key5, value5);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute(new String[]{Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName()});
        }
        return jsObj.toString();
    }

    private String mapToJson(Map<String, String> map) {
        JSONObject jsObj = new JSONObject();
        if (!(map == null || map.isEmpty())) {
            for (String key : map.keySet()) {
                try {
                    jsObj.put(key, SDKUtils.encodeString((String) map.get(key)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsObj.toString();
    }

    private Object[] getDeviceParams(Context context) {
        boolean fail = false;
        DeviceProperties deviceProperties = DeviceProperties.getInstance(context);
        JSONObject jsObj = new JSONObject();
        try {
            StringBuilder key;
            jsObj.put(RequestParameters.APP_ORIENTATION, SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(getCurrentActivityContext())));
            String deviceOem = deviceProperties.getDeviceOem();
            if (deviceOem != null) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.DEVICE_OEM), SDKUtils.encodeString(deviceOem));
            }
            String deviceModel = deviceProperties.getDeviceModel();
            if (deviceModel != null) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.DEVICE_MODEL), SDKUtils.encodeString(deviceModel));
            } else {
                fail = true;
            }
            SDKUtils.loadGoogleAdvertiserInfo(context);
            String advertiserId = SDKUtils.getAdvertiserId();
            Boolean isLAT = Boolean.valueOf(SDKUtils.isLimitAdTrackingEnabled());
            if (!TextUtils.isEmpty(advertiserId)) {
                Logger.m1212i(this.TAG, "add AID and LAT");
                jsObj.put(RequestParameters.isLAT, isLAT);
                jsObj.put(RequestParameters.DEVICE_IDS + RequestParameters.LEFT_BRACKETS + RequestParameters.AID + RequestParameters.RIGHT_BRACKETS, SDKUtils.encodeString(advertiserId));
            }
            String deviceOSType = deviceProperties.getDeviceOsType();
            if (deviceOSType != null) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.DEVICE_OS), SDKUtils.encodeString(deviceOSType));
            } else {
                fail = true;
            }
            String deviceOSVersion = deviceProperties.getDeviceOsVersion();
            if (deviceOSVersion != null) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.DEVICE_OS_VERSION), deviceOSVersion.replaceAll("[^0-9/.]", ""));
            } else {
                fail = true;
            }
            String deviceApiLevel = String.valueOf(deviceProperties.getDeviceApiLevel());
            if (deviceApiLevel != null) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.DEVICE_API_LEVEL), deviceApiLevel);
            } else {
                fail = true;
            }
            String ssaSDKVersion = DeviceProperties.getSupersonicSdkVersion();
            if (ssaSDKVersion != null) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.SDK_VERSION), SDKUtils.encodeString(ssaSDKVersion));
            }
            if (deviceProperties.getDeviceCarrier() != null && deviceProperties.getDeviceCarrier().length() > 0) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.MOBILE_CARRIER), SDKUtils.encodeString(deviceProperties.getDeviceCarrier()));
            }
            String connectionType = ConnectivityService.getConnectionType(context);
            if (TextUtils.isEmpty(connectionType)) {
                fail = true;
            } else {
                jsObj.put(SDKUtils.encodeString(RequestParameters.CONNECTION_TYPE), SDKUtils.encodeString(connectionType));
            }
            String deviceLanguage = context.getResources().getConfiguration().locale.getLanguage();
            if (!TextUtils.isEmpty(deviceLanguage)) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.DEVICE_LANGUAGE), SDKUtils.encodeString(deviceLanguage.toUpperCase()));
            }
            if (SDKUtils.isExternalStorageAvailable()) {
                long freeDiskSize = DeviceStatus.getAvailableMemorySizeInMegaBytes(this.mCacheDirectory);
                jsObj.put(SDKUtils.encodeString(RequestParameters.DISK_FREE_SIZE), SDKUtils.encodeString(String.valueOf(freeDiskSize)));
            } else {
                fail = true;
            }
            String width = String.valueOf(DeviceStatus.getDeviceWidth());
            if (TextUtils.isEmpty(width)) {
                fail = true;
            } else {
                key = new StringBuilder();
                key.append(SDKUtils.encodeString(RequestParameters.DEVICE_SCREEN_SIZE)).append(RequestParameters.LEFT_BRACKETS).append(SDKUtils.encodeString("width")).append(RequestParameters.RIGHT_BRACKETS);
                jsObj.put(key.toString(), SDKUtils.encodeString(width));
            }
            String height = String.valueOf(DeviceStatus.getDeviceHeight());
            key = new StringBuilder();
            key.append(SDKUtils.encodeString(RequestParameters.DEVICE_SCREEN_SIZE)).append(RequestParameters.LEFT_BRACKETS).append(SDKUtils.encodeString("height")).append(RequestParameters.RIGHT_BRACKETS);
            jsObj.put(key.toString(), SDKUtils.encodeString(height));
            String packageName = ApplicationContext.getPackageName(getContext());
            if (!TextUtils.isEmpty(packageName)) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.PACKAGE_NAME), SDKUtils.encodeString(packageName));
            }
            String scaleStr = String.valueOf(DeviceStatus.getDeviceDensity());
            if (!TextUtils.isEmpty(scaleStr)) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.DEVICE_SCREEN_SCALE), SDKUtils.encodeString(scaleStr));
            }
            String rootStr = String.valueOf(DeviceStatus.isRootedDevice());
            if (!TextUtils.isEmpty(rootStr)) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.IS_ROOT_DEVICE), SDKUtils.encodeString(rootStr));
            }
            float deviceVolume = DeviceProperties.getInstance(context).getDeviceVolume(context);
            if (!TextUtils.isEmpty(rootStr)) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.DEVICE_VOLUME), (double) deviceVolume);
            }
            Context ctx = getCurrentActivityContext();
            if (VERSION.SDK_INT >= 19 && (ctx instanceof Activity)) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.IMMERSIVE), DeviceStatus.isImmersiveSupported((Activity) ctx));
            }
            jsObj.put(SDKUtils.encodeString(RequestParameters.BATTERY_LEVEL), DeviceStatus.getBatteryLevel(ctx));
            jsObj.put(SDKUtils.encodeString(RequestParameters.NETWORK_MCC), ConnectivityService.getNetworkMCC(ctx));
            jsObj.put(SDKUtils.encodeString(RequestParameters.NETWORK_MNC), ConnectivityService.getNetworkMNC(ctx));
            jsObj.put(SDKUtils.encodeString(RequestParameters.PHONE_TYPE), ConnectivityService.getPhoneType(ctx));
            jsObj.put(SDKUtils.encodeString(RequestParameters.SIM_OPERATOR), SDKUtils.encodeString(ConnectivityService.getSimOperator(ctx)));
            jsObj.put(SDKUtils.encodeString("lastUpdateTime"), ApplicationContext.getLastUpdateTime(ctx));
            jsObj.put(SDKUtils.encodeString(RequestParameters.FIRST_INSTALL_TIME), ApplicationContext.getFirstInstallTime(ctx));
            jsObj.put(SDKUtils.encodeString(RequestParameters.APPLICATION_VERSION_NAME), SDKUtils.encodeString(ApplicationContext.getApplicationVersionName(ctx)));
            String installerPackageName = ApplicationContext.getInstallerPackageName(ctx);
            if (!TextUtils.isEmpty(installerPackageName)) {
                jsObj.put(SDKUtils.encodeString(RequestParameters.INSTALLER_PACKAGE_NAME), SDKUtils.encodeString(installerPackageName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute(new String[]{Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName()});
        }
        return new Object[]{jsObj.toString(), Boolean.valueOf(fail)};
    }

    private Object[] getApplicationParams(String productType, String demandSourceName) {
        boolean fail = false;
        JSONObject jsObj = new JSONObject();
        String appKey = "";
        String userId = "";
        Map<String, String> productExtraParams = null;
        if (TextUtils.isEmpty(productType)) {
            fail = true;
        } else {
            ProductType type = getStringProductTypeAsEnum(productType);
            if (type == ProductType.RewardedVideo || type == ProductType.Interstitial) {
                ProductParameters productParameters = this.mProductParametersCollection.getProductParameters(type);
                appKey = productParameters.appKey;
                userId = productParameters.userId;
                DemandSource demandSource = this.mDemandSourceManager.getDemandSourceByName(type, demandSourceName);
                if (demandSource != null) {
                    productExtraParams = demandSource.getExtraParams();
                    productExtraParams.put("demandSourceName", demandSourceName);
                }
            } else if (type == ProductType.OfferWall) {
                appKey = this.mOWAppKey;
                userId = this.mOWUserId;
                productExtraParams = this.mOWExtraParameters;
            }
            try {
                jsObj.put(ParametersKeys.PRODUCT_TYPE, productType);
            } catch (JSONException e) {
                e.printStackTrace();
                new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=noProductType"});
            }
        }
        if (TextUtils.isEmpty(userId)) {
            fail = true;
        } else {
            try {
                jsObj.put(SDKUtils.encodeString(RequestParameters.APPLICATION_USER_ID), SDKUtils.encodeString(userId));
            } catch (JSONException e2) {
                e2.printStackTrace();
                new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=encodeAppUserId"});
            }
        }
        if (TextUtils.isEmpty(appKey)) {
            fail = true;
        } else {
            try {
                jsObj.put(SDKUtils.encodeString(RequestParameters.APPLICATION_KEY), SDKUtils.encodeString(appKey));
            } catch (JSONException e22) {
                e22.printStackTrace();
                new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=encodeAppKey"});
            }
        }
        if (!(productExtraParams == null || productExtraParams.isEmpty())) {
            for (Entry<String, String> entry : productExtraParams.entrySet()) {
                if (((String) entry.getKey()).equalsIgnoreCase("sdkWebViewCache")) {
                    setWebviewCache((String) entry.getValue());
                }
                try {
                    jsObj.put(SDKUtils.encodeString((String) entry.getKey()), SDKUtils.encodeString((String) entry.getValue()));
                } catch (JSONException e222) {
                    e222.printStackTrace();
                    new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=extraParametersToJson"});
                }
            }
        }
        return new Object[]{jsObj.toString(), Boolean.valueOf(fail)};
    }

    private Object[] getAppsStatus(String appIds, String requestId) {
        boolean fail = false;
        JSONObject result = new JSONObject();
        try {
            if (!TextUtils.isEmpty(appIds)) {
                if (!appIds.equalsIgnoreCase("null")) {
                    if (!TextUtils.isEmpty(requestId)) {
                        if (!requestId.equalsIgnoreCase("null")) {
                            List<ApplicationInfo> packages = DeviceStatus.getInstalledApplications(getContext());
                            JSONArray appIdsArray = new JSONArray(appIds);
                            JSONObject bundleIds = new JSONObject();
                            for (int i = 0; i < appIdsArray.length(); i++) {
                                String appId = appIdsArray.getString(i).trim();
                                if (!TextUtils.isEmpty(appId)) {
                                    JSONObject isInstalled = new JSONObject();
                                    boolean found = false;
                                    for (ApplicationInfo packageInfo : packages) {
                                        if (appId.equalsIgnoreCase(packageInfo.packageName)) {
                                            isInstalled.put(IS_INSTALLED, true);
                                            bundleIds.put(appId, isInstalled);
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        isInstalled.put(IS_INSTALLED, false);
                                        bundleIds.put(appId, isInstalled);
                                    }
                                }
                            }
                            result.put(RESULT, bundleIds);
                            result.put(REQUEST_ID, requestId);
                            return new Object[]{result.toString(), Boolean.valueOf(fail)};
                        }
                    }
                    fail = true;
                    result.put("error", "requestId is null or empty");
                    return new Object[]{result.toString(), Boolean.valueOf(fail)};
                }
            }
            fail = true;
            result.put("error", "appIds is null or empty");
        } catch (Exception e) {
            fail = true;
        }
        return new Object[]{result.toString(), Boolean.valueOf(fail)};
    }

    public void onFileDownloadSuccess(SSAFile file) {
        if (file.getFile().contains(Constants.MOBILE_CONTROLLER_HTML)) {
            load(1);
        } else {
            assetCached(file.getFile(), file.getPath());
        }
    }

    public void onFileDownloadFail(SSAFile file) {
        if (file.getFile().contains(Constants.MOBILE_CONTROLLER_HTML)) {
            this.mGlobalControllerTimer.cancel();
            for (DemandSource demandSource : this.mDemandSourceManager.getDemandSources(ProductType.RewardedVideo)) {
                if (demandSource.getDemandSourceInitState() == 1) {
                    sendProductErrorMessage(ProductType.RewardedVideo, demandSource.getDemandSourceName());
                }
            }
            for (DemandSource demandSource2 : this.mDemandSourceManager.getDemandSources(ProductType.Interstitial)) {
                if (demandSource2.getDemandSourceInitState() == 1) {
                    sendProductErrorMessage(ProductType.Interstitial, demandSource2.getDemandSourceName());
                }
            }
            if (this.mOWmiss) {
                sendProductErrorMessage(ProductType.OfferWall, null);
            }
            if (this.mOWCreditsMiss) {
                sendProductErrorMessage(ProductType.OfferWallCredits, null);
                return;
            }
            return;
        }
        assetCachedFailed(file.getFile(), file.getPath(), file.getErrMsg());
    }

    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        Logger.m1212i(this.TAG, url + " " + mimetype);
    }

    private void toastingErrMsg(final String methodName, String value) {
        final String message = new SSAObj(value).getString(ParametersKeys.ERR_MSG);
        if (!TextUtils.isEmpty(message)) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (IronSourceWebView.this.getDebugMode() == DebugMode.MODE_3.getValue()) {
                        Toast.makeText(IronSourceWebView.this.getCurrentActivityContext(), methodName + " : " + message, 1).show();
                    }
                }
            });
        }
    }

    public void setControllerKeyPressed(String value) {
        this.mControllerKeyPressed = value;
    }

    public String getControllerKeyPressed() {
        String keyPressed = this.mControllerKeyPressed;
        setControllerKeyPressed("interrupt");
        return keyPressed;
    }

    public void deviceStatusChanged(String networkType) {
        injectJavascript(generateJSToInject(JSMethods.DEVICE_STATUS_CHANGED, parseToJson(RequestParameters.CONNECTION_TYPE, networkType, null, null, null, null, null, null, null, false)));
    }

    public void engageEnd(String action) {
        if (action.equals("forceClose")) {
            closeWebView();
        }
        injectJavascript(generateJSToInject(JSMethods.ENGAGE_END, parseToJson("action", action, null, null, null, null, null, null, null, false)));
    }

    public void registerConnectionReceiver(Context context) {
        context.registerReceiver(this.mConnectionReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public void unregisterConnectionReceiver(Context context) {
        try {
            context.unregisterReceiver(this.mConnectionReceiver);
        } catch (IllegalArgumentException e) {
        } catch (Exception e1) {
            Log.e(this.TAG, "unregisterConnectionReceiver - " + e1);
            new IronSourceAsyncHttpRequestTask().execute(new String[]{Constants.NATIVE_EXCEPTION_BASE_URL + e1.getStackTrace()[0].getMethodName()});
        }
    }

    public void pause() {
        if (VERSION.SDK_INT > 10) {
            try {
                onPause();
            } catch (Throwable e) {
                Logger.m1212i(this.TAG, "WebViewController: pause() - " + e);
                new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause"});
            }
        }
    }

    public void resume() {
        if (VERSION.SDK_INT > 10) {
            try {
                onResume();
            } catch (Throwable e) {
                Logger.m1212i(this.TAG, "WebViewController: onResume() - " + e);
                new IronSourceAsyncHttpRequestTask().execute(new String[]{"https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume"});
            }
        }
    }

    public void setOnWebViewControllerChangeListener(OnWebViewChangeListener listener) {
        this.mChangeListener = listener;
    }

    public FrameLayout getLayout() {
        return this.mControllerLayout;
    }

    public boolean inCustomView() {
        return this.mCustomView != null;
    }

    public void hideCustomView() {
        this.mWebChromeClient.onHideCustomView();
    }

    private void setWebviewCache(String value) {
        if (value.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            getSettings().setCacheMode(2);
        } else {
            getSettings().setCacheMode(-1);
        }
    }

    public boolean handleSearchKeysURLs(String url) throws Exception {
        List<String> searchKeys = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
        if (searchKeys != null) {
            try {
                if (!searchKeys.isEmpty()) {
                    for (String key : searchKeys) {
                        if (url.contains(key)) {
                            UrlHandler.openUrl(getCurrentActivityContext(), url);
                            return true;
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public void setState(State state) {
        this.mState = state;
    }

    public State getState() {
        return this.mState;
    }

    private void sendProductErrorMessage(ProductType type, String demnadSourceName) {
        String action = "";
        switch (type) {
            case RewardedVideo:
                action = ErrorCodes.InitRV;
                break;
            case Interstitial:
                action = ErrorCodes.InitIS;
                break;
            case OfferWall:
                action = ErrorCodes.InitOW;
                break;
            case OfferWallCredits:
                action = ErrorCodes.ShowOWCredits;
                break;
        }
        triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(action, ErrorCodes.InitiatingController), type, demnadSourceName);
    }

    public void destroy() {
        super.destroy();
        if (this.downloadManager != null) {
            this.downloadManager.release();
        }
        if (this.mConnectionReceiver != null) {
            this.mConnectionReceiver = null;
        }
        this.mUiHandler = null;
        this.mCurrentActivityContext = null;
    }

    private String generateJSToInject(String funToCall) {
        StringBuilder script = new StringBuilder();
        script.append("SSA_CORE.SDKController.runFunction('").append(funToCall).append("');");
        return script.toString();
    }

    private String generateJSToInject(String funToCall, String parameters) {
        StringBuilder script = new StringBuilder();
        script.append("SSA_CORE.SDKController.runFunction('").append(funToCall).append("?parameters=").append(parameters).append("');");
        return script.toString();
    }

    private String generateJSToInject(String funToCall, String successFunc, String failFunc) {
        StringBuilder script = new StringBuilder();
        script.append("SSA_CORE.SDKController.runFunction('").append(funToCall).append("','").append(successFunc).append("','").append(failFunc).append("');");
        return script.toString();
    }

    private String generateJSToInject(String funToCall, String parameters, String successFunc, String failFunc) {
        StringBuilder script = new StringBuilder();
        script.append("SSA_CORE.SDKController.runFunction('").append(funToCall).append("?parameters=").append(parameters).append("','").append(successFunc).append("','").append(failFunc).append("');");
        return script.toString();
    }

    public AdUnitsState getSavedState() {
        return this.mSavedState;
    }

    public void restoreState(AdUnitsState state) {
        synchronized (this.mSavedStateLocker) {
            if (state.shouldRestore() && this.mControllerState.equals(ControllerState.Ready)) {
                String demandSourceName;
                Log.d(this.TAG, "restoreState(state:" + state + ")");
                int lastAd = state.getDisplayedProduct();
                if (lastAd != -1) {
                    ProductType type;
                    DSAdProductListener listener;
                    if (lastAd == ProductType.RewardedVideo.ordinal()) {
                        Log.d(this.TAG, "onRVAdClosed()");
                        type = ProductType.RewardedVideo;
                        demandSourceName = state.getDisplayedDemandSourceName();
                        listener = getAdProductListenerByProductType(type);
                        if (!(listener == null || TextUtils.isEmpty(demandSourceName))) {
                            listener.onAdProductClose(type, demandSourceName);
                        }
                    } else if (lastAd == ProductType.Interstitial.ordinal()) {
                        Log.d(this.TAG, "onInterstitialAdClosed()");
                        type = ProductType.Interstitial;
                        demandSourceName = state.getDisplayedDemandSourceName();
                        listener = getAdProductListenerByProductType(type);
                        if (!(listener == null || TextUtils.isEmpty(demandSourceName))) {
                            listener.onAdProductClose(type, demandSourceName);
                        }
                    } else if (lastAd == ProductType.OfferWall.ordinal()) {
                        Log.d(this.TAG, "onOWAdClosed()");
                        if (this.mOnOfferWallListener != null) {
                            this.mOnOfferWallListener.onOWAdClosed();
                        }
                    }
                    state.adOpened(-1);
                    state.setDisplayedDemandSourceName(null);
                } else {
                    Log.d(this.TAG, "No ad was opened");
                }
                String appKey = state.getInterstitialAppKey();
                String userId = state.getInterstitialUserId();
                for (DemandSource demandSource : this.mDemandSourceManager.getDemandSources(ProductType.Interstitial)) {
                    if (demandSource.getDemandSourceInitState() == 2) {
                        Log.d(this.TAG, "initInterstitial(appKey:" + appKey + ", userId:" + userId + ", demandSource:" + demandSource.getDemandSourceName() + ")");
                        initInterstitial(appKey, userId, demandSource, this.mDSInterstitialListener);
                    }
                }
                appKey = state.getRVAppKey();
                userId = state.getRVUserId();
                for (DemandSource demandSource2 : this.mDemandSourceManager.getDemandSources(ProductType.RewardedVideo)) {
                    if (demandSource2.getDemandSourceInitState() == 2) {
                        demandSourceName = demandSource2.getDemandSourceName();
                        Log.d(this.TAG, "onRVNoMoreOffers()");
                        this.mDSRewardedVideoListener.onRVNoMoreOffers(demandSourceName);
                        Log.d(this.TAG, "initRewardedVideo(appKey:" + appKey + ", userId:" + userId + ", demandSource:" + demandSourceName + ")");
                        initRewardedVideo(appKey, userId, demandSource2, this.mDSRewardedVideoListener);
                    }
                }
                state.setShouldRestore(false);
            }
            this.mSavedState = state;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        if (this.mChangeListener.onBackButtonPressed()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    void runOnUiThread(Runnable task) {
        this.mUiHandler.post(task);
    }
}
