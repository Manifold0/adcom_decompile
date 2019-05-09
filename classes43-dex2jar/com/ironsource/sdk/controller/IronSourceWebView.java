// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import java.net.MalformedURLException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.net.URL;
import android.webkit.WebResourceResponse;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.ironsource.sdk.data.SSABCParameters;
import com.ironsource.environment.LocationService;
import com.ironsource.sdk.data.AdUnitsReady;
import android.webkit.JavascriptInterface;
import android.webkit.WebView$WebViewTransport;
import android.os.Message;
import android.webkit.ConsoleMessage;
import android.webkit.WebBackForwardList;
import android.os.Bundle;
import android.content.IntentFilter;
import android.view.KeyEvent;
import com.ironsource.environment.UrlHandler;
import android.content.MutableContextWrapper;
import com.ironsource.sdk.data.SSAFile;
import com.ironsource.sdk.utils.IronSourceStorageUtils;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import android.graphics.Color;
import android.webkit.WebSettings;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import android.app.Activity;
import android.os.Build$VERSION;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.sdk.utils.DeviceProperties;
import java.util.Iterator;
import java.util.List;
import android.content.pm.ApplicationInfo;
import org.json.JSONArray;
import com.ironsource.environment.DeviceStatus;
import org.json.JSONException;
import com.ironsource.sdk.utils.SDKUtils;
import android.annotation.SuppressLint;
import android.webkit.ValueCallback;
import android.text.TextUtils;
import org.json.JSONObject;
import com.ironsource.sdk.data.ProductParameters;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.SSAObj;
import android.location.Location;
import com.ironsource.sdk.listeners.internals.DSAdProductListener;
import android.view.View$OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.environment.ConnectivityService;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import com.ironsource.sdk.data.AdUnitsState;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnGenericFunctionListener;
import java.util.Map;
import com.ironsource.sdk.listeners.internals.DSRewardedVideoListener;
import com.ironsource.sdk.listeners.internals.DSInterstitialListener;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.view.View;
import android.content.Context;
import com.ironsource.sdk.data.SSAEnums;
import android.widget.FrameLayout;
import java.util.ArrayList;
import android.content.BroadcastReceiver;
import android.os.CountDownTimer;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import android.webkit.DownloadListener;
import com.ironsource.sdk.precache.DownloadManager;
import android.webkit.WebView;

public class IronSourceWebView extends WebView implements OnPreCacheCompletion, DownloadListener
{
    public static String APP_IDS;
    public static int DISPLAY_WEB_VIEW_INTENT;
    public static String EXTERNAL_URL;
    public static String IS_INSTALLED;
    public static String IS_STORE;
    public static String IS_STORE_CLOSE;
    private static String JSON_KEY_FAIL;
    private static String JSON_KEY_SUCCESS;
    public static int OPEN_URL_INTENT;
    public static String REQUEST_ID;
    public static String RESULT;
    public static String SECONDARY_WEB_VIEW;
    public static String WEBVIEW_TYPE;
    public static int mDebugMode;
    private final String GENERIC_MESSAGE;
    private String PUB_TAG;
    private String TAG;
    private DownloadManager downloadManager;
    private Boolean isKitkatAndAbove;
    private boolean isRemoveCloseEventHandler;
    private String mCacheDirectory;
    private OnWebViewChangeListener mChangeListener;
    private CountDownTimer mCloseEventTimer;
    private BroadcastReceiver mConnectionReceiver;
    private ArrayList<String> mControllerCommandsQueue;
    private String mControllerKeyPressed;
    private FrameLayout mControllerLayout;
    private SSAEnums.ControllerState mControllerState;
    Context mCurrentActivityContext;
    private View mCustomView;
    private WebChromeClient$CustomViewCallback mCustomViewCallback;
    private FrameLayout mCustomViewContainer;
    private DSInterstitialListener mDSInterstitialListener;
    private DSRewardedVideoListener mDSRewardedVideoListener;
    private DemandSourceManager mDemandSourceManager;
    private boolean mGlobalControllerTimeFinish;
    private CountDownTimer mGlobalControllerTimer;
    private int mHiddenForceCloseHeight;
    private String mHiddenForceCloseLocation;
    private int mHiddenForceCloseWidth;
    private String mISAppKey;
    private String mISUserId;
    private boolean mIsActivityThemeTranslucent;
    private boolean mIsImmersive;
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
    private ProductParametersCollection mProductParametersCollection;
    private String mRVAppKey;
    private String mRVUserId;
    private String mRequestParameters;
    private AdUnitsState mSavedState;
    private Object mSavedStateLocker;
    private State mState;
    Handler mUiHandler;
    private Uri mUri;
    private VideoEventsListener mVideoEventsListener;
    private ChromeClient mWebChromeClient;
    
    static {
        IronSourceWebView.mDebugMode = 0;
        IronSourceWebView.IS_STORE = "is_store";
        IronSourceWebView.IS_STORE_CLOSE = "is_store_close";
        IronSourceWebView.WEBVIEW_TYPE = "webview_type";
        IronSourceWebView.EXTERNAL_URL = "external_url";
        IronSourceWebView.SECONDARY_WEB_VIEW = "secondary_web_view";
        IronSourceWebView.DISPLAY_WEB_VIEW_INTENT = 0;
        IronSourceWebView.OPEN_URL_INTENT = 1;
        IronSourceWebView.APP_IDS = "appIds";
        IronSourceWebView.REQUEST_ID = "requestId";
        IronSourceWebView.IS_INSTALLED = "isInstalled";
        IronSourceWebView.RESULT = "result";
        IronSourceWebView.JSON_KEY_SUCCESS = "success";
        IronSourceWebView.JSON_KEY_FAIL = "fail";
    }
    
    public IronSourceWebView(final Context mCurrentActivityContext, final DemandSourceManager mDemandSourceManager) {
        super(mCurrentActivityContext.getApplicationContext());
        this.TAG = IronSourceWebView.class.getSimpleName();
        this.PUB_TAG = "IronSource";
        this.GENERIC_MESSAGE = "We're sorry, some error occurred. we will investigate it";
        this.mControllerKeyPressed = "interrupt";
        this.mHiddenForceCloseWidth = 50;
        this.mHiddenForceCloseHeight = 50;
        this.mHiddenForceCloseLocation = "top-right";
        this.mControllerState = SSAEnums.ControllerState.None;
        this.isKitkatAndAbove = null;
        this.mSavedStateLocker = new Object();
        this.mIsImmersive = false;
        this.mIsActivityThemeTranslucent = false;
        this.mProductParametersCollection = new ProductParametersCollection();
        this.mConnectionReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (IronSourceWebView.this.mControllerState == SSAEnums.ControllerState.Ready) {
                    String s = "none";
                    if (ConnectivityService.isConnectedWifi(context)) {
                        s = "wifi";
                    }
                    else if (ConnectivityService.isConnectedMobile(context)) {
                        s = "3g";
                    }
                    IronSourceWebView.this.deviceStatusChanged(s);
                }
            }
        };
        Logger.i(this.TAG, "C'tor");
        this.mControllerCommandsQueue = new ArrayList<String>();
        this.mCacheDirectory = this.initializeCacheDirectory(mCurrentActivityContext.getApplicationContext());
        this.mCurrentActivityContext = mCurrentActivityContext;
        this.mDemandSourceManager = mDemandSourceManager;
        this.initLayout(this.mCurrentActivityContext);
        this.mSavedState = new AdUnitsState();
        (this.downloadManager = this.getDownloadManager()).setOnPreCacheCompletion((DownloadManager.OnPreCacheCompletion)this);
        this.mWebChromeClient = new ChromeClient();
        this.setWebViewClient((WebViewClient)new ViewClient());
        this.setWebChromeClient((WebChromeClient)this.mWebChromeClient);
        this.setWebViewSettings();
        this.addJavascriptInterface((Object)this.createJSInterface(mCurrentActivityContext), "Android");
        this.setDownloadListener((DownloadListener)this);
        this.setOnTouchListener((View$OnTouchListener)new SupersonicWebViewTouchListener());
        this.mUiHandler = this.createMainThreadHandler();
    }
    
    private void closeWebView() {
        if (this.mChangeListener != null) {
            this.mChangeListener.onCloseRequested();
        }
    }
    
    private boolean controllerCommandSupportsQueue(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("updateConsentInfo");
        return list.contains(s);
    }
    
    private String createInitProductJSMethod(final SSAEnums.ProductType productType, final DemandSource demandSource) {
        final String s = "";
        String generateJSToInject;
        if (productType == SSAEnums.ProductType.RewardedVideo || productType == SSAEnums.ProductType.Interstitial || productType == SSAEnums.ProductType.OfferWall) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            final ProductParameters productParameters = this.mProductParametersCollection.getProductParameters(productType);
            if (productParameters != null) {
                hashMap.put("applicationKey", productParameters.appKey);
                hashMap.put("applicationUserId", productParameters.userId);
            }
            if (demandSource != null) {
                if (demandSource.getExtraParams() != null) {
                    hashMap.putAll((Map<?, ?>)demandSource.getExtraParams());
                }
                hashMap.put("demandSourceName", demandSource.getDemandSourceName());
            }
            else if (this.getExtraParamsByProduct(productType) != null) {
                hashMap.putAll((Map<?, ?>)this.getExtraParamsByProduct(productType));
            }
            final String flatMapToJsonAsString = this.flatMapToJsonAsString(hashMap);
            final Constants.JSMethods initMethodByProduct = Constants.JSMethods.getInitMethodByProduct(productType);
            generateJSToInject = this.generateJSToInject(initMethodByProduct.methodName, flatMapToJsonAsString, initMethodByProduct.successCallbackName, initMethodByProduct.failureCallbackName);
        }
        else {
            generateJSToInject = s;
            if (productType == SSAEnums.ProductType.OfferWallCredits) {
                return this.generateJSToInject("getUserCredits", this.parseToJson("productType", "OfferWall", "applicationKey", this.mOWCreditsAppKey, "applicationUserId", this.mOWCreditsUserId, null, null, null, false), "null", "onGetUserCreditsFail");
            }
        }
        return generateJSToInject;
    }
    
    private SSAObj createLocationObject(final String s, final Location location) {
        final SSAObj ssaObj = new SSAObj(s);
        if (location != null) {
            ssaObj.put("provider", location.getProvider());
            ssaObj.put("latitude", Double.toString(location.getLatitude()));
            ssaObj.put("longitude", Double.toString(location.getLongitude()));
            ssaObj.put("altitude", Double.toString(location.getAltitude()));
            ssaObj.put("time", Long.toString(location.getTime()));
            ssaObj.put("accuracy", Float.toString(location.getAccuracy()));
            ssaObj.put("bearing", Float.toString(location.getBearing()));
            ssaObj.put("speed", Float.toString(location.getSpeed()));
            return ssaObj;
        }
        ssaObj.put("error", "location data is not available");
        return ssaObj;
    }
    
    private String createShowProductJSMethod(final SSAEnums.ProductType productType, final JSONObject jsonObject) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("sessionDepth", Integer.toString(jsonObject.optInt("sessionDepth")));
        final String optString = jsonObject.optString("demandSourceName");
        final DemandSource demandSourceByName = this.mDemandSourceManager.getDemandSourceByName(productType, optString);
        if (demandSourceByName != null) {
            if (demandSourceByName.getExtraParams() != null) {
                hashMap.putAll((Map<?, ?>)demandSourceByName.getExtraParams());
            }
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                hashMap.put("demandSourceName", optString);
            }
        }
        else if (this.getExtraParamsByProduct(productType) != null) {
            hashMap.putAll((Map<?, ?>)this.getExtraParamsByProduct(productType));
        }
        final String flatMapToJsonAsString = this.flatMapToJsonAsString(hashMap);
        final Constants.JSMethods showMethodByProduct = Constants.JSMethods.getShowMethodByProduct(productType);
        return this.generateJSToInject(showMethodByProduct.methodName, flatMapToJsonAsString, showMethodByProduct.successCallbackName, showMethodByProduct.failureCallbackName);
    }
    
    @SuppressLint({ "NewApi" })
    private void evaluateJavascriptKitKat(final String s) {
        this.evaluateJavascript(s, (ValueCallback)null);
    }
    
    private String extractFailFunctionToCall(final String s) {
        return new SSAObj(s).getString(IronSourceWebView.JSON_KEY_FAIL);
    }
    
    private String extractSuccessFunctionToCall(final String s) {
        return new SSAObj(s).getString(IronSourceWebView.JSON_KEY_SUCCESS);
    }
    
    private String flatMapToJsonAsString(Map<String, String> iterator) {
        final JSONObject jsonObject = new JSONObject();
        if (iterator != null) {
            iterator = ((Map<Object, Object>)iterator).entrySet().iterator();
        Label_0069_Outer:
            while (iterator.hasNext()) {
                final Map.Entry<Object, Object> entry = iterator.next();
                while (true) {
                    try {
                        jsonObject.putOpt((String)entry.getKey(), (Object)SDKUtils.encodeString(entry.getValue()));
                        iterator.remove();
                        continue Label_0069_Outer;
                    }
                    catch (JSONException ex) {
                        Logger.i(this.TAG, "flatMapToJsonAsStringfailed " + ex.toString());
                        continue;
                    }
                    break;
                }
                break;
            }
        }
        return jsonObject.toString();
    }
    
    private String generateJSToInject(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SSA_CORE.SDKController.runFunction('").append(s).append("');");
        return sb.toString();
    }
    
    private String generateJSToInject(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SSA_CORE.SDKController.runFunction('").append(s).append("?parameters=").append(s2).append("');");
        return sb.toString();
    }
    
    private String generateJSToInject(final String s, final String s2, final String s3) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SSA_CORE.SDKController.runFunction('").append(s).append("','").append(s2).append("','").append(s3).append("');");
        return sb.toString();
    }
    
    private String generateJSToInject(final String s, final String s2, final String s3, final String s4) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SSA_CORE.SDKController.runFunction('").append(s).append("?parameters=").append(s2).append("','").append(s3).append("','").append(s4).append("');");
        return sb.toString();
    }
    
    private DSAdProductListener getAdProductListenerByProductType(final SSAEnums.ProductType productType) {
        if (productType == SSAEnums.ProductType.Interstitial) {
            return this.mDSInterstitialListener;
        }
        if (productType == SSAEnums.ProductType.RewardedVideo) {
            return this.mDSRewardedVideoListener;
        }
        return null;
    }
    
    private Object[] getApplicationParams(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_3       
        //     2: new             Lorg/json/JSONObject;
        //     5: dup            
        //     6: invokespecial   org/json/JSONObject.<init>:()V
        //     9: astore          9
        //    11: ldc_w           ""
        //    14: astore          4
        //    16: ldc_w           ""
        //    19: astore          6
        //    21: aconst_null    
        //    22: astore          7
        //    24: aconst_null    
        //    25: astore          5
        //    27: aload_1        
        //    28: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    31: ifne            392
        //    34: aload_0        
        //    35: aload_1        
        //    36: invokespecial   com/ironsource/sdk/controller/IronSourceWebView.getStringProductTypeAsEnum:(Ljava/lang/String;)Lcom/ironsource/sdk/data/SSAEnums$ProductType;
        //    39: astore          10
        //    41: aload           10
        //    43: getstatic       com/ironsource/sdk/data/SSAEnums$ProductType.RewardedVideo:Lcom/ironsource/sdk/data/SSAEnums$ProductType;
        //    46: if_acmpeq       57
        //    49: aload           10
        //    51: getstatic       com/ironsource/sdk/data/SSAEnums$ProductType.Interstitial:Lcom/ironsource/sdk/data/SSAEnums$ProductType;
        //    54: if_acmpne       334
        //    57: aload_0        
        //    58: getfield        com/ironsource/sdk/controller/IronSourceWebView.mProductParametersCollection:Lcom/ironsource/sdk/controller/ProductParametersCollection;
        //    61: aload           10
        //    63: invokevirtual   com/ironsource/sdk/controller/ProductParametersCollection.getProductParameters:(Lcom/ironsource/sdk/data/SSAEnums$ProductType;)Lcom/ironsource/sdk/data/ProductParameters;
        //    66: astore          4
        //    68: aload           4
        //    70: getfield        com/ironsource/sdk/data/ProductParameters.appKey:Ljava/lang/String;
        //    73: astore          7
        //    75: aload           4
        //    77: getfield        com/ironsource/sdk/data/ProductParameters.userId:Ljava/lang/String;
        //    80: astore          8
        //    82: aload_0        
        //    83: getfield        com/ironsource/sdk/controller/IronSourceWebView.mDemandSourceManager:Lcom/ironsource/sdk/controller/DemandSourceManager;
        //    86: aload           10
        //    88: aload_2        
        //    89: invokevirtual   com/ironsource/sdk/controller/DemandSourceManager.getDemandSourceByName:(Lcom/ironsource/sdk/data/SSAEnums$ProductType;Ljava/lang/String;)Lcom/ironsource/sdk/data/DemandSource;
        //    92: astore          10
        //    94: aload           7
        //    96: astore          4
        //    98: aload           8
        //   100: astore          6
        //   102: aload           10
        //   104: ifnull          134
        //   107: aload           10
        //   109: invokevirtual   com/ironsource/sdk/data/DemandSource.getExtraParams:()Ljava/util/Map;
        //   112: astore          5
        //   114: aload           5
        //   116: ldc_w           "demandSourceName"
        //   119: aload_2        
        //   120: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   125: pop            
        //   126: aload           8
        //   128: astore          6
        //   130: aload           7
        //   132: astore          4
        //   134: aload           9
        //   136: ldc_w           "productType"
        //   139: aload_1        
        //   140: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   143: pop            
        //   144: aload           6
        //   146: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   149: ifne            430
        //   152: aload           9
        //   154: ldc_w           "applicationUserId"
        //   157: invokestatic    com/ironsource/sdk/utils/SDKUtils.encodeString:(Ljava/lang/String;)Ljava/lang/String;
        //   160: aload           6
        //   162: invokestatic    com/ironsource/sdk/utils/SDKUtils.encodeString:(Ljava/lang/String;)Ljava/lang/String;
        //   165: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   168: pop            
        //   169: aload           4
        //   171: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   174: ifne            464
        //   177: aload           9
        //   179: ldc_w           "applicationKey"
        //   182: invokestatic    com/ironsource/sdk/utils/SDKUtils.encodeString:(Ljava/lang/String;)Ljava/lang/String;
        //   185: aload           4
        //   187: invokestatic    com/ironsource/sdk/utils/SDKUtils.encodeString:(Ljava/lang/String;)Ljava/lang/String;
        //   190: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   193: pop            
        //   194: aload           5
        //   196: ifnull          469
        //   199: aload           5
        //   201: invokeinterface java/util/Map.isEmpty:()Z
        //   206: ifne            469
        //   209: aload           5
        //   211: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   216: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   221: astore_1       
        //   222: aload_1        
        //   223: invokeinterface java/util/Iterator.hasNext:()Z
        //   228: ifeq            469
        //   231: aload_1        
        //   232: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   237: checkcast       Ljava/util/Map$Entry;
        //   240: astore_2       
        //   241: aload_2        
        //   242: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   247: checkcast       Ljava/lang/String;
        //   250: ldc_w           "sdkWebViewCache"
        //   253: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   256: ifeq            272
        //   259: aload_0        
        //   260: aload_2        
        //   261: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   266: checkcast       Ljava/lang/String;
        //   269: invokespecial   com/ironsource/sdk/controller/IronSourceWebView.setWebviewCache:(Ljava/lang/String;)V
        //   272: aload           9
        //   274: aload_2        
        //   275: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   280: checkcast       Ljava/lang/String;
        //   283: invokestatic    com/ironsource/sdk/utils/SDKUtils.encodeString:(Ljava/lang/String;)Ljava/lang/String;
        //   286: aload_2        
        //   287: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   292: checkcast       Ljava/lang/String;
        //   295: invokestatic    com/ironsource/sdk/utils/SDKUtils.encodeString:(Ljava/lang/String;)Ljava/lang/String;
        //   298: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   301: pop            
        //   302: goto            222
        //   305: astore_2       
        //   306: aload_2        
        //   307: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   310: new             Lcom/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask;
        //   313: dup            
        //   314: invokespecial   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.<init>:()V
        //   317: iconst_1       
        //   318: anewarray       Ljava/lang/String;
        //   321: dup            
        //   322: iconst_0       
        //   323: ldc_w           "https://www.supersonicads.com/mobile/sdk5/log?method=extraParametersToJson"
        //   326: aastore        
        //   327: invokevirtual   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   330: pop            
        //   331: goto            222
        //   334: aload           10
        //   336: getstatic       com/ironsource/sdk/data/SSAEnums$ProductType.OfferWall:Lcom/ironsource/sdk/data/SSAEnums$ProductType;
        //   339: if_acmpne       134
        //   342: aload_0        
        //   343: getfield        com/ironsource/sdk/controller/IronSourceWebView.mOWAppKey:Ljava/lang/String;
        //   346: astore          4
        //   348: aload_0        
        //   349: getfield        com/ironsource/sdk/controller/IronSourceWebView.mOWUserId:Ljava/lang/String;
        //   352: astore          6
        //   354: aload_0        
        //   355: getfield        com/ironsource/sdk/controller/IronSourceWebView.mOWExtraParameters:Ljava/util/Map;
        //   358: astore          5
        //   360: goto            134
        //   363: astore_1       
        //   364: aload_1        
        //   365: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   368: new             Lcom/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask;
        //   371: dup            
        //   372: invokespecial   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.<init>:()V
        //   375: iconst_1       
        //   376: anewarray       Ljava/lang/String;
        //   379: dup            
        //   380: iconst_0       
        //   381: ldc_w           "https://www.supersonicads.com/mobile/sdk5/log?method=noProductType"
        //   384: aastore        
        //   385: invokevirtual   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   388: pop            
        //   389: goto            144
        //   392: iconst_1       
        //   393: istore_3       
        //   394: aload           7
        //   396: astore          5
        //   398: goto            144
        //   401: astore_1       
        //   402: aload_1        
        //   403: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   406: new             Lcom/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask;
        //   409: dup            
        //   410: invokespecial   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.<init>:()V
        //   413: iconst_1       
        //   414: anewarray       Ljava/lang/String;
        //   417: dup            
        //   418: iconst_0       
        //   419: ldc_w           "https://www.supersonicads.com/mobile/sdk5/log?method=encodeAppUserId"
        //   422: aastore        
        //   423: invokevirtual   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   426: pop            
        //   427: goto            169
        //   430: iconst_1       
        //   431: istore_3       
        //   432: goto            169
        //   435: astore_1       
        //   436: aload_1        
        //   437: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   440: new             Lcom/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask;
        //   443: dup            
        //   444: invokespecial   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.<init>:()V
        //   447: iconst_1       
        //   448: anewarray       Ljava/lang/String;
        //   451: dup            
        //   452: iconst_0       
        //   453: ldc_w           "https://www.supersonicads.com/mobile/sdk5/log?method=encodeAppKey"
        //   456: aastore        
        //   457: invokevirtual   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   460: pop            
        //   461: goto            194
        //   464: iconst_1       
        //   465: istore_3       
        //   466: goto            194
        //   469: iconst_2       
        //   470: anewarray       Ljava/lang/Object;
        //   473: dup            
        //   474: iconst_0       
        //   475: aload           9
        //   477: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   480: aastore        
        //   481: dup            
        //   482: iconst_1       
        //   483: iload_3        
        //   484: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   487: aastore        
        //   488: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  134    144    363    392    Lorg/json/JSONException;
        //  152    169    401    430    Lorg/json/JSONException;
        //  177    194    435    464    Lorg/json/JSONException;
        //  272    302    305    334    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 223, Size: 223
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
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
    
    private Object[] getAppsStatus(final String s, final String s2) {
        boolean b = false;
        final JSONObject jsonObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty((CharSequence)s) && !s.equalsIgnoreCase("null")) {
                if (!TextUtils.isEmpty((CharSequence)s2) && !s2.equalsIgnoreCase("null")) {
                    final List<ApplicationInfo> installedApplications = DeviceStatus.getInstalledApplications(this.getContext());
                    final JSONArray jsonArray = new JSONArray(s);
                    final JSONObject jsonObject2 = new JSONObject();
                    String trim;
                    JSONObject jsonObject3;
                    int n;
                    Iterator<ApplicationInfo> iterator;
                    int n2;
                    Label_0180_Outer:Label_0286:
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        trim = jsonArray.getString(i).trim();
                        if (!TextUtils.isEmpty((CharSequence)trim)) {
                            jsonObject3 = new JSONObject();
                            n = 0;
                            iterator = installedApplications.iterator();
                            while (true) {
                                do {
                                    n2 = n;
                                    if (iterator.hasNext()) {
                                        continue Label_0180_Outer;
                                    }
                                    if (n2 == 0) {
                                        jsonObject3.put(IronSourceWebView.IS_INSTALLED, false);
                                        jsonObject2.put(trim, (Object)jsonObject3);
                                    }
                                    continue Label_0286;
                                } while (!trim.equalsIgnoreCase(iterator.next().packageName));
                                jsonObject3.put(IronSourceWebView.IS_INSTALLED, true);
                                jsonObject2.put(trim, (Object)jsonObject3);
                                n2 = 1;
                                continue;
                            }
                        }
                    }
                    jsonObject.put(IronSourceWebView.RESULT, (Object)jsonObject2);
                    jsonObject.put(IronSourceWebView.REQUEST_ID, (Object)s2);
                }
                else {
                    b = true;
                    jsonObject.put("error", (Object)"requestId is null or empty");
                }
            }
            else {
                b = true;
                jsonObject.put("error", (Object)"appIds is null or empty");
            }
            return new Object[] { jsonObject.toString(), b };
        }
        catch (Exception ex) {
            b = true;
            return new Object[] { jsonObject.toString(), b };
        }
    }
    
    private Object[] getDeviceParams(Context currentActivityContext) {
        final int n = 0;
        int n2 = 0;
        final DeviceProperties instance = DeviceProperties.getInstance(currentActivityContext);
        final JSONObject jsonObject = new JSONObject();
        int n3 = n;
        try {
            jsonObject.put("appOrientation", (Object)SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(this.getCurrentActivityContext())));
            n3 = n;
            final String deviceOem = instance.getDeviceOem();
            if (deviceOem != null) {
                n3 = n;
                jsonObject.put(SDKUtils.encodeString("deviceOEM"), (Object)SDKUtils.encodeString(deviceOem));
            }
            n3 = n;
            final String deviceModel = instance.getDeviceModel();
            if (deviceModel != null) {
                n3 = n;
                jsonObject.put(SDKUtils.encodeString("deviceModel"), (Object)SDKUtils.encodeString(deviceModel));
            }
            else {
                n2 = 1;
            }
            n3 = n2;
            SDKUtils.loadGoogleAdvertiserInfo(currentActivityContext);
            n3 = n2;
            final String advertiserId = SDKUtils.getAdvertiserId();
            n3 = n2;
            final boolean limitAdTrackingEnabled = SDKUtils.isLimitAdTrackingEnabled();
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)advertiserId)) {
                n3 = n2;
                Logger.i(this.TAG, "add AID and LAT");
                n3 = n2;
                jsonObject.put("isLimitAdTrackingEnabled", (Object)limitAdTrackingEnabled);
                n3 = n2;
                jsonObject.put("deviceIds" + "[" + "AID" + "]", (Object)SDKUtils.encodeString(advertiserId));
            }
            n3 = n2;
            final String deviceOsType = instance.getDeviceOsType();
            if (deviceOsType != null) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("deviceOs"), (Object)SDKUtils.encodeString(deviceOsType));
            }
            else {
                n2 = 1;
            }
            n3 = n2;
            final String deviceOsVersion = instance.getDeviceOsVersion();
            if (deviceOsVersion != null) {
                n3 = n2;
                final String replaceAll = deviceOsVersion.replaceAll("[^0-9/.]", "");
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("deviceOSVersion"), (Object)replaceAll);
            }
            else {
                n2 = 1;
            }
            n3 = n2;
            final String value = String.valueOf(instance.getDeviceApiLevel());
            if (value != null) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("deviceApiLevel"), (Object)value);
            }
            else {
                n2 = 1;
            }
            n3 = n2;
            final String supersonicSdkVersion = DeviceProperties.getSupersonicSdkVersion();
            if (supersonicSdkVersion != null) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("SDKVersion"), (Object)SDKUtils.encodeString(supersonicSdkVersion));
            }
            n3 = n2;
            if (instance.getDeviceCarrier() != null) {
                n3 = n2;
                if (instance.getDeviceCarrier().length() > 0) {
                    n3 = n2;
                    jsonObject.put(SDKUtils.encodeString("mobileCarrier"), (Object)SDKUtils.encodeString(instance.getDeviceCarrier()));
                }
            }
            n3 = n2;
            final String connectionType = ConnectivityService.getConnectionType(currentActivityContext);
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)connectionType)) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("connectionType"), (Object)SDKUtils.encodeString(connectionType));
            }
            else {
                n2 = 1;
            }
            n3 = n2;
            final String language = currentActivityContext.getResources().getConfiguration().locale.getLanguage();
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)language)) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("deviceLanguage"), (Object)SDKUtils.encodeString(language.toUpperCase()));
            }
            n3 = n2;
            if (SDKUtils.isExternalStorageAvailable()) {
                n3 = n2;
                final long availableMemorySizeInMegaBytes = DeviceStatus.getAvailableMemorySizeInMegaBytes(this.mCacheDirectory);
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("diskFreeSize"), (Object)SDKUtils.encodeString(String.valueOf(availableMemorySizeInMegaBytes)));
            }
            else {
                n2 = 1;
            }
            n3 = n2;
            final String value2 = String.valueOf(DeviceStatus.getDeviceWidth());
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)value2)) {
                n3 = n2;
                final StringBuilder sb = new StringBuilder();
                n3 = n2;
                sb.append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("width")).append("]");
                n3 = n2;
                jsonObject.put(sb.toString(), (Object)SDKUtils.encodeString(value2));
            }
            else {
                n2 = 1;
            }
            n3 = n2;
            final int deviceHeight = DeviceStatus.getDeviceHeight();
            n3 = n2;
            final StringBuilder sb2 = new StringBuilder();
            n3 = n2;
            sb2.append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("height")).append("]");
            n3 = n2;
            jsonObject.put(sb2.toString(), (Object)SDKUtils.encodeString(String.valueOf(deviceHeight)));
            n3 = n2;
            final String packageName = ApplicationContext.getPackageName(this.getContext());
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)packageName)) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("bundleId"), (Object)SDKUtils.encodeString(packageName));
            }
            n3 = n2;
            final String value3 = String.valueOf(DeviceStatus.getDeviceDensity());
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)value3)) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("deviceScreenScale"), (Object)SDKUtils.encodeString(value3));
            }
            n3 = n2;
            final String value4 = String.valueOf(DeviceStatus.isRootedDevice());
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)value4)) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("unLocked"), (Object)SDKUtils.encodeString(value4));
            }
            n3 = n2;
            final float deviceVolume = DeviceProperties.getInstance(currentActivityContext).getDeviceVolume(currentActivityContext);
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)value4)) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("deviceVolume"), (double)deviceVolume);
            }
            n3 = n2;
            currentActivityContext = this.getCurrentActivityContext();
            n3 = n2;
            if (Build$VERSION.SDK_INT >= 19) {
                n3 = n2;
                if (currentActivityContext instanceof Activity) {
                    n3 = n2;
                    jsonObject.put(SDKUtils.encodeString("immersiveMode"), DeviceStatus.isImmersiveSupported((Activity)currentActivityContext));
                }
            }
            n3 = n2;
            jsonObject.put(SDKUtils.encodeString("batteryLevel"), DeviceStatus.getBatteryLevel(currentActivityContext));
            n3 = n2;
            jsonObject.put(SDKUtils.encodeString("mcc"), ConnectivityService.getNetworkMCC(currentActivityContext));
            n3 = n2;
            jsonObject.put(SDKUtils.encodeString("mnc"), ConnectivityService.getNetworkMNC(currentActivityContext));
            n3 = n2;
            jsonObject.put(SDKUtils.encodeString("phoneType"), ConnectivityService.getPhoneType(currentActivityContext));
            n3 = n2;
            jsonObject.put(SDKUtils.encodeString("simOperator"), (Object)SDKUtils.encodeString(ConnectivityService.getSimOperator(currentActivityContext)));
            n3 = n2;
            jsonObject.put(SDKUtils.encodeString("lastUpdateTime"), ApplicationContext.getLastUpdateTime(currentActivityContext));
            n3 = n2;
            jsonObject.put(SDKUtils.encodeString("firstInstallTime"), ApplicationContext.getFirstInstallTime(currentActivityContext));
            n3 = n2;
            jsonObject.put(SDKUtils.encodeString("appVersion"), (Object)SDKUtils.encodeString(ApplicationContext.getApplicationVersionName(currentActivityContext)));
            n3 = n2;
            final String installerPackageName = ApplicationContext.getInstallerPackageName(currentActivityContext);
            int n4 = n2;
            n3 = n2;
            if (!TextUtils.isEmpty((CharSequence)installerPackageName)) {
                n3 = n2;
                jsonObject.put(SDKUtils.encodeString("installerPackageName"), (Object)SDKUtils.encodeString(installerPackageName));
                n4 = n2;
            }
            return new Object[] { jsonObject.toString(), Boolean.valueOf((boolean)(n4 != 0)) };
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + ex.getStackTrace()[0].getMethodName() });
            final int n4 = n3;
            return new Object[] { jsonObject.toString(), Boolean.valueOf((boolean)(n4 != 0)) };
        }
    }
    
    private Map<String, String> getExtraParamsByProduct(final SSAEnums.ProductType productType) {
        if (productType == SSAEnums.ProductType.OfferWall) {
            return this.mOWExtraParameters;
        }
        return null;
    }
    
    private String getRequestParameters(final JSONObject jsonObject) {
        final DeviceProperties instance = DeviceProperties.getInstance(this.getContext());
        final StringBuilder sb = new StringBuilder();
        final String supersonicSdkVersion = DeviceProperties.getSupersonicSdkVersion();
        if (!TextUtils.isEmpty((CharSequence)supersonicSdkVersion)) {
            sb.append("SDKVersion").append("=").append(supersonicSdkVersion).append("&");
        }
        final String deviceOsType = instance.getDeviceOsType();
        if (!TextUtils.isEmpty((CharSequence)deviceOsType)) {
            sb.append("deviceOs").append("=").append(deviceOsType);
        }
        final Uri parse = Uri.parse(SDKUtils.getControllerUrl());
        Label_0319: {
            if (parse == null) {
                break Label_0319;
            }
            final String string = parse.getScheme() + ":";
            final String host = parse.getHost();
            final int port = parse.getPort();
            String string2 = host;
            if (port != -1) {
                string2 = host + ":" + port;
            }
            sb.append("&").append("protocol").append("=").append(string);
            sb.append("&").append("domain").append("=").append(string2);
            while (true) {
                if (!jsonObject.keys().hasNext()) {
                    break Label_0291;
                }
                try {
                    final String string3 = new JSONObject(jsonObject, new String[] { "isSecured", "applicationKey" }).toString();
                    if (!TextUtils.isEmpty((CharSequence)string3)) {
                        sb.append("&").append("controllerConfig").append("=").append(string3);
                    }
                    sb.append("&").append("debug").append("=").append(this.getDebugMode());
                    return sb.toString();
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }
    
    private SSAEnums.ProductType getStringProductTypeAsEnum(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (s.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
                return SSAEnums.ProductType.Interstitial;
            }
            if (s.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
                return SSAEnums.ProductType.RewardedVideo;
            }
            if (s.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
                return SSAEnums.ProductType.OfferWall;
            }
        }
        return null;
    }
    
    private WebView getWebview() {
        return this;
    }
    
    private void initLayout(final Context context) {
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1);
        this.mControllerLayout = new FrameLayout(context);
        (this.mCustomViewContainer = new FrameLayout(context)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.mCustomViewContainer.setVisibility(8);
        final FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        frameLayout.addView((View)this);
        this.mControllerLayout.addView((View)this.mCustomViewContainer, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
        this.mControllerLayout.addView((View)frameLayout);
    }
    
    private void initProduct(final String s, final String s2, final SSAEnums.ProductType productType, final DemandSource demandSource, final String s3) {
        if (TextUtils.isEmpty((CharSequence)s2) || TextUtils.isEmpty((CharSequence)s)) {
            this.triggerOnControllerInitProductFail("User id or Application key are missing", productType, demandSource.getDemandSourceName());
        }
        else {
            if (this.mControllerState == SSAEnums.ControllerState.Ready) {
                IronSourceSharedPrefHelper.getSupersonicPrefHelper().setApplicationKey(s, productType);
                IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserID(s2, productType);
                this.injectJavascript(this.createInitProductJSMethod(productType, demandSource));
                return;
            }
            this.setMissProduct(productType, demandSource);
            if (this.mControllerState == SSAEnums.ControllerState.Failed) {
                this.triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(s3, "Initiating Controller"), productType, demandSource.getDemandSourceName());
                return;
            }
            if (this.mGlobalControllerTimeFinish) {
                this.downloadController();
            }
        }
    }
    
    private void injectJavascript(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return;
        }
        final String s2 = "empty";
        String s3;
        if (this.getDebugMode() == SSAEnums.DebugMode.MODE_0.getValue()) {
            s3 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
        }
        else {
            s3 = s2;
            if (this.getDebugMode() >= SSAEnums.DebugMode.MODE_1.getValue()) {
                s3 = s2;
                if (this.getDebugMode() <= SSAEnums.DebugMode.MODE_3.getValue()) {
                    s3 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("try{").append(s).append("}catch(e){").append(s3).append("}");
        this.runOnUiThread(new Runnable() {
            final /* synthetic */ String val$url = "javascript:" + sb.toString();
            
            @Override
            public void run() {
                Logger.i(IronSourceWebView.this.TAG, this.val$url);
                try {
                    if (IronSourceWebView.this.isKitkatAndAbove != null) {
                        if (IronSourceWebView.this.isKitkatAndAbove) {
                            IronSourceWebView.this.evaluateJavascriptKitKat(sb.toString());
                            return;
                        }
                        IronSourceWebView.this.loadUrl(this.val$url);
                        return;
                    }
                }
                catch (Throwable t) {
                    Logger.e(IronSourceWebView.this.TAG, "injectJavascript: " + t.toString());
                    new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=injectJavaScript" });
                    return;
                }
                if (Build$VERSION.SDK_INT >= 19) {
                    try {
                        IronSourceWebView.this.evaluateJavascriptKitKat(sb.toString());
                        IronSourceWebView.this.isKitkatAndAbove = true;
                        return;
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip NoSuchMethodError: SDK version=" + Build$VERSION.SDK_INT + " " + noSuchMethodError);
                        IronSourceWebView.this.loadUrl(this.val$url);
                        IronSourceWebView.this.isKitkatAndAbove = false;
                        return;
                    }
                    catch (Throwable t2) {
                        Logger.e(IronSourceWebView.this.TAG, "evaluateJavascrip Exception: SDK version=" + Build$VERSION.SDK_INT + " " + t2);
                        IronSourceWebView.this.loadUrl(this.val$url);
                        IronSourceWebView.this.isKitkatAndAbove = false;
                        return;
                    }
                }
                IronSourceWebView.this.loadUrl(this.val$url);
                IronSourceWebView.this.isKitkatAndAbove = false;
            }
        });
    }
    
    private void injectJavascript(final String s, final String s2) {
        if (!this.isControllerStateReady() && this.controllerCommandSupportsQueue(s)) {
            this.mControllerCommandsQueue.add(s2);
            return;
        }
        this.injectJavascript(s2);
    }
    
    private void invokePendingCommands() {
        while (this.mControllerCommandsQueue.size() > 0) {
            this.injectJavascript(this.mControllerCommandsQueue.get(0));
            this.mControllerCommandsQueue.remove(0);
        }
    }
    
    private boolean isControllerStateReady() {
        return SSAEnums.ControllerState.Ready.equals(this.mControllerState);
    }
    
    private String mapToJson(final Map<String, String> map) {
        final JSONObject jsonObject = new JSONObject();
        if (map != null && !map.isEmpty()) {
            for (final String s : map.keySet()) {
                final String s2 = map.get(s);
                try {
                    jsonObject.put(s, (Object)SDKUtils.encodeString(s2));
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return jsonObject.toString();
    }
    
    private String parseToJson(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final boolean b) {
        final JSONObject jsonObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty((CharSequence)s) && !TextUtils.isEmpty((CharSequence)s2)) {
                jsonObject.put(s, (Object)SDKUtils.encodeString(s2));
            }
            if (!TextUtils.isEmpty((CharSequence)s3) && !TextUtils.isEmpty((CharSequence)s4)) {
                jsonObject.put(s3, (Object)SDKUtils.encodeString(s4));
            }
            if (!TextUtils.isEmpty((CharSequence)s5) && !TextUtils.isEmpty((CharSequence)s6)) {
                jsonObject.put(s5, (Object)SDKUtils.encodeString(s6));
            }
            if (!TextUtils.isEmpty((CharSequence)s7) && !TextUtils.isEmpty((CharSequence)s8)) {
                jsonObject.put(s7, (Object)SDKUtils.encodeString(s8));
            }
            if (!TextUtils.isEmpty((CharSequence)s9)) {
                jsonObject.put(s9, b);
            }
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + ex.getStackTrace()[0].getMethodName() });
            return jsonObject.toString();
        }
    }
    
    private void responseBack(String string, final boolean b, final String s, final String s2) {
        final SSAObj ssaObj = new SSAObj((String)string);
        Object o = ssaObj.getString(IronSourceWebView.JSON_KEY_SUCCESS);
        final String string2 = ssaObj.getString(IronSourceWebView.JSON_KEY_FAIL);
        CharSequence charSequence = null;
        Label_0131: {
            if (!b) {
                break Label_0131;
            }
            if (!TextUtils.isEmpty((CharSequence)o)) {
                charSequence = (CharSequence)o;
            }
        Label_0087_Outer:
            while (true) {
                if (TextUtils.isEmpty(charSequence)) {
                    return;
                }
                o = string;
                while (true) {
                    if (TextUtils.isEmpty((CharSequence)s)) {
                        break Label_0087;
                    }
                    try {
                        o = new JSONObject((String)string).put("errMsg", (Object)s).toString();
                        string = (JSONException)o;
                        Label_0119: {
                            if (TextUtils.isEmpty((CharSequence)s2)) {
                                break Label_0119;
                            }
                            try {
                                string = (JSONException)new JSONObject((String)o).put("errCode", (Object)s2).toString();
                                this.injectJavascript(this.generateJSToInject((String)charSequence, (String)string));
                                return;
                                charSequence = string2;
                                continue Label_0087_Outer;
                            }
                            // iftrue(Label_0049:, TextUtils.isEmpty((CharSequence)string2))
                            catch (JSONException string) {
                                string = (JSONException)o;
                            }
                        }
                    }
                    catch (JSONException ex) {
                        o = string;
                        continue;
                    }
                    break;
                }
                break;
            }
        }
    }
    
    private void sendProductErrorMessage(final SSAEnums.ProductType productType, final String s) {
        String s2 = "";
        switch (productType) {
            case RewardedVideo: {
                s2 = "Init RV";
                break;
            }
            case Interstitial: {
                s2 = "Init IS";
                break;
            }
            case OfferWall: {
                s2 = "Init OW";
                break;
            }
            case OfferWallCredits: {
                s2 = "Show OW Credits";
                break;
            }
        }
        this.triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(s2, "Initiating Controller"), productType, s);
    }
    
    private void setDisplayZoomControls(final WebSettings webSettings) {
        if (Build$VERSION.SDK_INT > 11) {
            webSettings.setDisplayZoomControls(false);
        }
    }
    
    public static void setEXTERNAL_URL(final String external_URL) {
        IronSourceWebView.EXTERNAL_URL = external_URL;
    }
    
    @SuppressLint({ "NewApi" })
    private void setMediaPlaybackJellyBean(final WebSettings webSettings) {
        if (Build$VERSION.SDK_INT >= 17) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
    }
    
    @SuppressLint({ "NewApi" })
    private void setWebDebuggingEnabled() {
        if (Build$VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
    }
    
    private void setWebDebuggingEnabled(final JSONObject jsonObject) {
        if (jsonObject.optBoolean("inspectWebview")) {
            this.setWebDebuggingEnabled();
        }
    }
    
    private void setWebViewSettings() {
        final WebSettings settings = this.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        while (true) {
            if (Build$VERSION.SDK_INT < 16) {
                break Label_0041;
            }
            try {
                this.getSettings().setAllowFileAccessFromFileURLs(true);
                settings.setBuiltInZoomControls(false);
                settings.setJavaScriptEnabled(true);
                settings.setSupportMultipleWindows(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setGeolocationEnabled(true);
                settings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
                settings.setDomStorageEnabled(true);
                final IronSourceWebView ironSourceWebView = this;
                final WebSettings webSettings = settings;
                ironSourceWebView.setDisplayZoomControls(webSettings);
                final IronSourceWebView ironSourceWebView2 = this;
                final WebSettings webSettings2 = settings;
                ironSourceWebView2.setMediaPlaybackJellyBean(webSettings2);
                return;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
        try {
            final IronSourceWebView ironSourceWebView = this;
            final WebSettings webSettings = settings;
            ironSourceWebView.setDisplayZoomControls(webSettings);
            final IronSourceWebView ironSourceWebView2 = this;
            final WebSettings webSettings2 = settings;
            ironSourceWebView2.setMediaPlaybackJellyBean(webSettings2);
        }
        catch (Throwable t) {
            Logger.e(this.TAG, "setWebSettings - " + t.toString());
        }
    }
    
    private void setWebviewBackground(String string) {
        string = new SSAObj(string).getString("color");
        int color = 0;
        if (!"transparent".equalsIgnoreCase(string)) {
            color = Color.parseColor(string);
        }
        this.setBackgroundColor(color);
    }
    
    private void setWebviewCache(final String s) {
        if (s.equalsIgnoreCase("0")) {
            this.getSettings().setCacheMode(2);
            return;
        }
        this.getSettings().setCacheMode(-1);
    }
    
    private boolean shouldNotifyDeveloper(final String s) {
        boolean b = false;
        if (TextUtils.isEmpty((CharSequence)s)) {
            Logger.d(this.TAG, "Trying to trigger a listener - no product was found");
            return false;
        }
        if (s.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            b = (this.mDSInterstitialListener != null);
        }
        else if (s.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
            b = (this.mDSRewardedVideoListener != null);
        }
        else if (s.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()) || s.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString())) {
            b = (this.mOnOfferWallListener != null);
        }
        if (!b) {
            Logger.d(this.TAG, "Trying to trigger a listener - no listener was found for product " + s);
        }
        return b;
    }
    
    private void toastingErrMsg(final String s, String string) {
        string = new SSAObj(string).getString("errMsg");
        if (!TextUtils.isEmpty((CharSequence)string)) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (IronSourceWebView.this.getDebugMode() == SSAEnums.DebugMode.MODE_3.getValue()) {
                        Toast.makeText(IronSourceWebView.this.getCurrentActivityContext(), (CharSequence)(s + " : " + string), 1).show();
                    }
                }
            });
        }
    }
    
    private void triggerOnControllerInitProductFail(final String s, final SSAEnums.ProductType productType, final String s2) {
        if (this.shouldNotifyDeveloper(productType.toString())) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (SSAEnums.ProductType.RewardedVideo == productType || SSAEnums.ProductType.Interstitial == productType) {
                        if (!TextUtils.isEmpty((CharSequence)s2)) {
                            final DSAdProductListener access$5700 = IronSourceWebView.this.getAdProductListenerByProductType(productType);
                            Log.d(IronSourceWebView.this.TAG, "onAdProductInitFailed (message:" + s + ")(" + productType + ")");
                            if (access$5700 != null) {
                                access$5700.onAdProductInitFailed(productType, s2, s);
                            }
                        }
                    }
                    else {
                        if (SSAEnums.ProductType.OfferWall == productType) {
                            IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(s);
                            return;
                        }
                        if (SSAEnums.ProductType.OfferWallCredits == productType) {
                            IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(s);
                        }
                    }
                }
            });
        }
    }
    
    public void addMoatJSInterface(final MOATJSAdapter mMoatJsAdapter) {
        this.mMoatJsAdapter = mMoatJsAdapter;
    }
    
    public void addPermissionsJSInterface(final PermissionsJSAdapter mPermissionsJsAdapter) {
        this.mPermissionsJsAdapter = mPermissionsJsAdapter;
    }
    
    public void assetCached(final String s, final String s2) {
        this.injectJavascript(this.generateJSToInject("assetCached", this.parseToJson("file", s, "path", s2, null, null, null, null, null, false)));
    }
    
    public void assetCachedFailed(final String s, final String s2, final String s3) {
        this.injectJavascript(this.generateJSToInject("assetCachedFailed", this.parseToJson("file", s, "path", s2, "errMsg", s3, null, null, null, false)));
    }
    
    JSInterface createJSInterface(final Context context) {
        return new JSInterface(context);
    }
    
    Handler createMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
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
    
    public void deviceStatusChanged(final String s) {
        this.injectJavascript(this.generateJSToInject("deviceStatusChanged", this.parseToJson("connectionType", s, null, null, null, null, null, null, null, false)));
    }
    
    public void downloadController() {
        IronSourceStorageUtils.deleteFile(this.mCacheDirectory, "", "mobileController.html");
        final String controllerUrl = SDKUtils.getControllerUrl();
        final SSAFile ssaFile = new SSAFile(controllerUrl, "");
        this.mGlobalControllerTimer = new CountDownTimer(200000L, 1000L) {
            public void onFinish() {
                Logger.i(IronSourceWebView.this.TAG, "Global Controller Timer Finish");
                IronSourceWebView.this.mGlobalControllerTimeFinish = true;
            }
            
            public void onTick(final long n) {
                Logger.i(IronSourceWebView.this.TAG, "Global Controller Timer Tick " + n);
            }
        }.start();
        if (!this.downloadManager.isMobileControllerThreadLive()) {
            Logger.i(this.TAG, "Download Mobile Controller: " + controllerUrl);
            this.downloadManager.downloadMobileControllerFile(ssaFile);
            return;
        }
        Logger.i(this.TAG, "Download Mobile Controller: already alive");
    }
    
    public void engageEnd(final String s) {
        if (s.equals("forceClose")) {
            this.closeWebView();
        }
        this.injectJavascript(this.generateJSToInject("engageEnd", this.parseToJson("action", s, null, null, null, null, null, null, null, false)));
    }
    
    public void enterBackground() {
        if (this.mControllerState == SSAEnums.ControllerState.Ready) {
            this.injectJavascript(this.generateJSToInject("enterBackground"));
        }
    }
    
    public void enterForeground() {
        if (this.mControllerState == SSAEnums.ControllerState.Ready) {
            this.injectJavascript(this.generateJSToInject("enterForeground"));
        }
    }
    
    public String getControllerKeyPressed() {
        final String mControllerKeyPressed = this.mControllerKeyPressed;
        this.setControllerKeyPressed("interrupt");
        return mControllerKeyPressed;
    }
    
    public Context getCurrentActivityContext() {
        return ((MutableContextWrapper)this.mCurrentActivityContext).getBaseContext();
    }
    
    public int getDebugMode() {
        return IronSourceWebView.mDebugMode;
    }
    
    DownloadManager getDownloadManager() {
        return DownloadManager.getInstance(this.mCacheDirectory);
    }
    
    public FrameLayout getLayout() {
        return this.mControllerLayout;
    }
    
    public void getOfferWallCredits(final String mowCreditsAppKey, final String mowCreditsUserId, final OnOfferWallListener mOnOfferWallListener) {
        this.mOWCreditsAppKey = mowCreditsAppKey;
        this.mOWCreditsUserId = mowCreditsUserId;
        this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.OfferWallCredits, mowCreditsAppKey, mowCreditsUserId);
        this.mOnOfferWallListener = mOnOfferWallListener;
        this.initProduct(this.mOWCreditsAppKey, this.mOWCreditsUserId, SSAEnums.ProductType.OfferWallCredits, null, "Show OW Credits");
    }
    
    public String getOrientationState() {
        return this.mOrientationState;
    }
    
    public AdUnitsState getSavedState() {
        return this.mSavedState;
    }
    
    public State getState() {
        return this.mState;
    }
    
    public boolean handleSearchKeysURLs(final String s) throws Exception {
        final List<String> searchKeys = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
        if (searchKeys != null) {
            try {
                if (!searchKeys.isEmpty()) {
                    final Iterator<String> iterator = searchKeys.iterator();
                    Block_5: {
                        while (iterator.hasNext()) {
                            if (s.contains(iterator.next())) {
                                break Block_5;
                            }
                        }
                        return false;
                    }
                    UrlHandler.openUrl(this.getCurrentActivityContext(), s);
                    return true;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public void hideCustomView() {
        this.mWebChromeClient.onHideCustomView();
    }
    
    public boolean inCustomView() {
        return this.mCustomView != null;
    }
    
    public void initInterstitial(final String misAppKey, final String misUserId, final DemandSource demandSource, final DSInterstitialListener mdsInterstitialListener) {
        this.mISAppKey = misAppKey;
        this.mISUserId = misUserId;
        this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.Interstitial, misAppKey, misUserId);
        this.mDSInterstitialListener = mdsInterstitialListener;
        this.mSavedState.setInterstitialAppKey(this.mISAppKey);
        this.mSavedState.setInterstitialUserId(this.mISUserId);
        this.initProduct(this.mISAppKey, this.mISUserId, SSAEnums.ProductType.Interstitial, demandSource, "Init IS");
    }
    
    public void initOfferWall(final String mowAppKey, final String mowUserId, final Map<String, String> mowExtraParameters, final OnOfferWallListener mOnOfferWallListener) {
        this.mOWAppKey = mowAppKey;
        this.mOWUserId = mowUserId;
        this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.OfferWall, mowAppKey, mowUserId);
        this.mOWExtraParameters = mowExtraParameters;
        this.mOnOfferWallListener = mOnOfferWallListener;
        this.mSavedState.setOfferWallExtraParams(this.mOWExtraParameters);
        this.mSavedState.setOfferwallReportInit(true);
        this.initProduct(this.mOWAppKey, this.mOWUserId, SSAEnums.ProductType.OfferWall, null, "Init OW");
    }
    
    public void initRewardedVideo(final String s, final String s2, final DemandSource demandSource, final DSRewardedVideoListener mdsRewardedVideoListener) {
        this.mRVAppKey = s;
        this.mRVUserId = s2;
        this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.RewardedVideo, s, s2);
        this.mDSRewardedVideoListener = mdsRewardedVideoListener;
        this.mSavedState.setRVAppKey(s);
        this.mSavedState.setRVUserId(s2);
        this.initProduct(s, s2, SSAEnums.ProductType.RewardedVideo, demandSource, "Init RV");
    }
    
    String initializeCacheDirectory(final Context context) {
        return IronSourceStorageUtils.initializeCacheDirectory(context.getApplicationContext());
    }
    
    public void interceptedUrlToStore() {
        this.injectJavascript(this.generateJSToInject("interceptedUrlToStore"));
    }
    
    public boolean isInterstitialAdAvailable(final String s) {
        final DemandSource demandSourceByName = this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, s);
        return demandSourceByName != null && demandSourceByName.getAvailabilityState();
    }
    
    public void load(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc_w           "about:blank"
        //     4: invokevirtual   com/ironsource/sdk/controller/IronSourceWebView.loadUrl:(Ljava/lang/String;)V
        //     7: new             Ljava/lang/StringBuilder;
        //    10: dup            
        //    11: invokespecial   java/lang/StringBuilder.<init>:()V
        //    14: ldc_w           "file://"
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: aload_0        
        //    21: getfield        com/ironsource/sdk/controller/IronSourceWebView.mCacheDirectory:Ljava/lang/String;
        //    24: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    27: getstatic       java/io/File.separator:Ljava/lang/String;
        //    30: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    33: ldc_w           "mobileController.html"
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    39: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    42: astore_2       
        //    43: new             Ljava/io/File;
        //    46: dup            
        //    47: new             Ljava/lang/StringBuilder;
        //    50: dup            
        //    51: invokespecial   java/lang/StringBuilder.<init>:()V
        //    54: aload_0        
        //    55: getfield        com/ironsource/sdk/controller/IronSourceWebView.mCacheDirectory:Ljava/lang/String;
        //    58: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    61: getstatic       java/io/File.separator:Ljava/lang/String;
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: ldc_w           "mobileController.html"
        //    70: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    73: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    76: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    79: invokevirtual   java/io/File.exists:()Z
        //    82: ifeq            296
        //    85: invokestatic    com/ironsource/sdk/utils/SDKUtils.getControllerConfigAsJSONObject:()Lorg/json/JSONObject;
        //    88: astore_3       
        //    89: aload_0        
        //    90: aload_3        
        //    91: invokespecial   com/ironsource/sdk/controller/IronSourceWebView.setWebDebuggingEnabled:(Lorg/json/JSONObject;)V
        //    94: aload_0        
        //    95: aload_0        
        //    96: aload_3        
        //    97: invokespecial   com/ironsource/sdk/controller/IronSourceWebView.getRequestParameters:(Lorg/json/JSONObject;)Ljava/lang/String;
        //   100: putfield        com/ironsource/sdk/controller/IronSourceWebView.mRequestParameters:Ljava/lang/String;
        //   103: new             Ljava/lang/StringBuilder;
        //   106: dup            
        //   107: invokespecial   java/lang/StringBuilder.<init>:()V
        //   110: aload_2        
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: ldc_w           "?"
        //   117: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   120: aload_0        
        //   121: getfield        com/ironsource/sdk/controller/IronSourceWebView.mRequestParameters:Ljava/lang/String;
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   130: astore_2       
        //   131: aload_0        
        //   132: new             Lcom/ironsource/sdk/controller/IronSourceWebView$2;
        //   135: dup            
        //   136: aload_0        
        //   137: ldc2_w          50000
        //   140: ldc2_w          1000
        //   143: iload_1        
        //   144: invokespecial   com/ironsource/sdk/controller/IronSourceWebView$2.<init>:(Lcom/ironsource/sdk/controller/IronSourceWebView;JJI)V
        //   147: invokevirtual   com/ironsource/sdk/controller/IronSourceWebView$2.start:()Landroid/os/CountDownTimer;
        //   150: putfield        com/ironsource/sdk/controller/IronSourceWebView.mLoadControllerTimer:Landroid/os/CountDownTimer;
        //   153: aload_0        
        //   154: aload_2        
        //   155: invokevirtual   com/ironsource/sdk/controller/IronSourceWebView.loadUrl:(Ljava/lang/String;)V
        //   158: aload_0        
        //   159: getfield        com/ironsource/sdk/controller/IronSourceWebView.TAG:Ljava/lang/String;
        //   162: new             Ljava/lang/StringBuilder;
        //   165: dup            
        //   166: invokespecial   java/lang/StringBuilder.<init>:()V
        //   169: ldc_w           "load(): "
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: aload_2        
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   182: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   185: return         
        //   186: astore_2       
        //   187: aload_0        
        //   188: getfield        com/ironsource/sdk/controller/IronSourceWebView.TAG:Ljava/lang/String;
        //   191: new             Ljava/lang/StringBuilder;
        //   194: dup            
        //   195: invokespecial   java/lang/StringBuilder.<init>:()V
        //   198: ldc_w           "WebViewController:: load: "
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: aload_2        
        //   205: invokevirtual   java/lang/Throwable.toString:()Ljava/lang/String;
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   214: invokestatic    com/ironsource/sdk/utils/Logger.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   217: new             Lcom/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask;
        //   220: dup            
        //   221: invokespecial   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.<init>:()V
        //   224: iconst_1       
        //   225: anewarray       Ljava/lang/String;
        //   228: dup            
        //   229: iconst_0       
        //   230: ldc_w           "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank"
        //   233: aastore        
        //   234: invokevirtual   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   237: pop            
        //   238: goto            7
        //   241: astore_3       
        //   242: aload_0        
        //   243: getfield        com/ironsource/sdk/controller/IronSourceWebView.TAG:Ljava/lang/String;
        //   246: new             Ljava/lang/StringBuilder;
        //   249: dup            
        //   250: invokespecial   java/lang/StringBuilder.<init>:()V
        //   253: ldc_w           "WebViewController:: load: "
        //   256: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   259: aload_3        
        //   260: invokevirtual   java/lang/Throwable.toString:()Ljava/lang/String;
        //   263: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   269: invokestatic    com/ironsource/sdk/utils/Logger.e:(Ljava/lang/String;Ljava/lang/String;)V
        //   272: new             Lcom/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask;
        //   275: dup            
        //   276: invokespecial   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.<init>:()V
        //   279: iconst_1       
        //   280: anewarray       Ljava/lang/String;
        //   283: dup            
        //   284: iconst_0       
        //   285: ldc_w           "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath"
        //   288: aastore        
        //   289: invokevirtual   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   292: pop            
        //   293: goto            158
        //   296: aload_0        
        //   297: getfield        com/ironsource/sdk/controller/IronSourceWebView.TAG:Ljava/lang/String;
        //   300: ldc_w           "load(): Mobile Controller HTML Does not exist"
        //   303: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
        //   306: new             Lcom/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask;
        //   309: dup            
        //   310: invokespecial   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.<init>:()V
        //   313: iconst_1       
        //   314: anewarray       Ljava/lang/String;
        //   317: dup            
        //   318: iconst_0       
        //   319: ldc_w           "https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem"
        //   322: aastore        
        //   323: invokevirtual   com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   326: pop            
        //   327: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      7      186    241    Ljava/lang/Throwable;
        //  153    158    241    296    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0158:
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
    
    public void loadInterstitial(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (!TextUtils.isEmpty((CharSequence)s)) {
            hashMap.put("demandSourceName", s);
        }
        final String flatMapToJsonAsString = this.flatMapToJsonAsString(hashMap);
        if (!this.isInterstitialAdAvailable(s)) {
            this.mSavedState.setReportLoadInterstitial(s, true);
            this.injectJavascript(this.generateJSToInject("loadInterstitial", flatMapToJsonAsString, "onLoadInterstitialSuccess", "onLoadInterstitialFail"));
        }
        else if (this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadSuccess(s);
                }
            });
        }
    }
    
    public void nativeNavigationPressed(final String s) {
        this.injectJavascript(this.generateJSToInject("nativeNavigationPressed", this.parseToJson("action", s, null, null, null, null, null, null, null, false)));
    }
    
    public void notifyLifeCycle(final String s, final String s2) {
        this.injectJavascript(this.generateJSToInject("onNativeLifeCycleEvent", this.parseToJson("lifeCycleEvent", s2, "productType", s, null, null, null, null, null, false)));
    }
    
    public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
        Logger.i(this.TAG, s + " " + s4);
    }
    
    public void onFileDownloadFail(final SSAFile ssaFile) {
        if (ssaFile.getFile().contains("mobileController.html")) {
            this.mGlobalControllerTimer.cancel();
            for (final DemandSource demandSource : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo)) {
                if (demandSource.getDemandSourceInitState() == 1) {
                    this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, demandSource.getDemandSourceName());
                }
            }
            for (final DemandSource demandSource2 : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial)) {
                if (demandSource2.getDemandSourceInitState() == 1) {
                    this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, demandSource2.getDemandSourceName());
                }
            }
            if (this.mOWmiss) {
                this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
            }
            if (this.mOWCreditsMiss) {
                this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
            }
            return;
        }
        this.assetCachedFailed(ssaFile.getFile(), ssaFile.getPath(), ssaFile.getErrMsg());
    }
    
    public void onFileDownloadSuccess(final SSAFile ssaFile) {
        if (ssaFile.getFile().contains("mobileController.html")) {
            this.load(1);
            return;
        }
        this.assetCached(ssaFile.getFile(), ssaFile.getPath());
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            return this.mChangeListener.onBackButtonPressed() || super.onKeyDown(n, keyEvent);
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public void pageFinished() {
        this.injectJavascript(this.generateJSToInject("pageFinished"));
    }
    
    public void pause() {
        if (Build$VERSION.SDK_INT <= 10) {
            return;
        }
        try {
            this.onPause();
        }
        catch (Throwable t) {
            Logger.i(this.TAG, "WebViewController: pause() - " + t);
            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause" });
        }
    }
    
    public void registerConnectionReceiver(final Context context) {
        context.registerReceiver(this.mConnectionReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
    
    public void removeVideoEventsListener() {
        this.mVideoEventsListener = null;
    }
    
    public void restoreState(final AdUnitsState mSavedState) {
    Label_0145_Outer:
        while (true) {
        Label_0408:
            while (true) {
                Label_0394: {
                    while (true) {
                        final int displayedProduct;
                        synchronized (this.mSavedStateLocker) {
                            if (!mSavedState.shouldRestore() || !this.mControllerState.equals(SSAEnums.ControllerState.Ready)) {
                                break;
                            }
                            Log.d(this.TAG, "restoreState(state:" + mSavedState + ")");
                            displayedProduct = mSavedState.getDisplayedProduct();
                            if (displayedProduct == -1) {
                                break Label_0394;
                            }
                            if (displayedProduct == SSAEnums.ProductType.RewardedVideo.ordinal()) {
                                Log.d(this.TAG, "onRVAdClosed()");
                                final SSAEnums.ProductType rewardedVideo = SSAEnums.ProductType.RewardedVideo;
                                final String displayedDemandSourceName = mSavedState.getDisplayedDemandSourceName();
                                final DSAdProductListener adProductListenerByProductType = this.getAdProductListenerByProductType(rewardedVideo);
                                if (adProductListenerByProductType != null && !TextUtils.isEmpty((CharSequence)displayedDemandSourceName)) {
                                    adProductListenerByProductType.onAdProductClose(rewardedVideo, displayedDemandSourceName);
                                }
                                mSavedState.adOpened(-1);
                                mSavedState.setDisplayedDemandSourceName(null);
                                final String interstitialAppKey = mSavedState.getInterstitialAppKey();
                                final String interstitialUserId = mSavedState.getInterstitialUserId();
                                for (final DemandSource demandSource : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial)) {
                                    if (demandSource.getDemandSourceInitState() == 2) {
                                        Log.d(this.TAG, "initInterstitial(appKey:" + interstitialAppKey + ", userId:" + interstitialUserId + ", demandSource:" + demandSource.getDemandSourceName() + ")");
                                        this.initInterstitial(interstitialAppKey, interstitialUserId, demandSource, this.mDSInterstitialListener);
                                    }
                                }
                                break Label_0408;
                            }
                        }
                        if (displayedProduct == SSAEnums.ProductType.Interstitial.ordinal()) {
                            Log.d(this.TAG, "onInterstitialAdClosed()");
                            final SSAEnums.ProductType interstitial = SSAEnums.ProductType.Interstitial;
                            final String displayedDemandSourceName2 = mSavedState.getDisplayedDemandSourceName();
                            final DSAdProductListener adProductListenerByProductType2 = this.getAdProductListenerByProductType(interstitial);
                            if (adProductListenerByProductType2 != null && !TextUtils.isEmpty((CharSequence)displayedDemandSourceName2)) {
                                adProductListenerByProductType2.onAdProductClose(interstitial, displayedDemandSourceName2);
                                continue Label_0145_Outer;
                            }
                            continue Label_0145_Outer;
                        }
                        else {
                            if (displayedProduct != SSAEnums.ProductType.OfferWall.ordinal()) {
                                continue Label_0145_Outer;
                            }
                            Log.d(this.TAG, "onOWAdClosed()");
                            if (this.mOnOfferWallListener != null) {
                                this.mOnOfferWallListener.onOWAdClosed();
                                continue Label_0145_Outer;
                            }
                            continue Label_0145_Outer;
                        }
                        break;
                    }
                }
                Log.d(this.TAG, "No ad was opened");
                continue;
            }
            final String rvAppKey = mSavedState.getRVAppKey();
            final String rvUserId = mSavedState.getRVUserId();
            for (final DemandSource demandSource2 : this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo)) {
                if (demandSource2.getDemandSourceInitState() == 2) {
                    final String demandSourceName = demandSource2.getDemandSourceName();
                    Log.d(this.TAG, "onRVNoMoreOffers()");
                    this.mDSRewardedVideoListener.onRVNoMoreOffers(demandSourceName);
                    Log.d(this.TAG, "initRewardedVideo(appKey:" + rvAppKey + ", userId:" + rvUserId + ", demandSource:" + demandSourceName + ")");
                    this.initRewardedVideo(rvAppKey, rvUserId, demandSource2, this.mDSRewardedVideoListener);
                }
            }
            mSavedState.setShouldRestore(false);
            break;
        }
        this.mSavedState = mSavedState;
    }
    // monitorexit(o)
    
    public void resume() {
        if (Build$VERSION.SDK_INT <= 10) {
            return;
        }
        try {
            this.onResume();
        }
        catch (Throwable t) {
            Logger.i(this.TAG, "WebViewController: onResume() - " + t);
            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume" });
        }
    }
    
    void runOnUiThread(final Runnable runnable) {
        this.mUiHandler.post(runnable);
    }
    
    public WebBackForwardList saveState(final Bundle bundle) {
        return super.saveState(bundle);
    }
    
    public void setControllerKeyPressed(final String mControllerKeyPressed) {
        this.mControllerKeyPressed = mControllerKeyPressed;
    }
    
    public void setDebugMode(final int mDebugMode) {
        IronSourceWebView.mDebugMode = mDebugMode;
    }
    
    void setMissProduct(final SSAEnums.ProductType productType, final DemandSource demandSource) {
        if (productType == SSAEnums.ProductType.RewardedVideo || productType == SSAEnums.ProductType.Interstitial) {
            if (demandSource != null) {
                demandSource.setDemandSourceInitState(1);
            }
        }
        else if (productType == SSAEnums.ProductType.OfferWall) {
            this.mOWmiss = true;
        }
        else if (productType == SSAEnums.ProductType.OfferWallCredits) {
            this.mOWCreditsMiss = true;
        }
        Logger.i(this.TAG, "setMissProduct(" + productType + ")");
    }
    
    public void setOnWebViewControllerChangeListener(final OnWebViewChangeListener mChangeListener) {
        this.mChangeListener = mChangeListener;
    }
    
    public void setOrientationState(final String mOrientationState) {
        this.mOrientationState = mOrientationState;
    }
    
    public void setState(final State mState) {
        this.mState = mState;
    }
    
    public void setVideoEventsListener(final VideoEventsListener mVideoEventsListener) {
        this.mVideoEventsListener = mVideoEventsListener;
    }
    
    public void showInterstitial(final JSONObject jsonObject) {
        this.injectJavascript(this.createShowProductJSMethod(SSAEnums.ProductType.Interstitial, jsonObject));
    }
    
    public void showOfferWall(final Map<String, String> mowExtraParameters) {
        this.mOWExtraParameters = mowExtraParameters;
        this.injectJavascript(this.generateJSToInject("showOfferWall", "onShowOfferWallSuccess", "onShowOfferWallFail"));
    }
    
    public void showRewardedVideo(final JSONObject jsonObject) {
        this.injectJavascript(this.createShowProductJSMethod(SSAEnums.ProductType.RewardedVideo, jsonObject));
    }
    
    public void unregisterConnectionReceiver(final Context context) {
        try {
            context.unregisterReceiver(this.mConnectionReceiver);
        }
        catch (Exception ex) {
            Log.e(this.TAG, "unregisterConnectionReceiver - " + ex);
            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + ex.getStackTrace()[0].getMethodName() });
        }
        catch (IllegalArgumentException ex2) {}
    }
    
    public void updateConsentInfo(final JSONObject jsonObject) {
        String string;
        if (jsonObject != null) {
            string = jsonObject.toString();
        }
        else {
            string = null;
        }
        this.injectJavascript("updateConsentInfo", this.generateJSToInject("updateConsentInfo", string));
    }
    
    public void viewableChange(final boolean b, final String s) {
        this.injectJavascript(this.generateJSToInject("viewableChange", this.parseToJson("webview", s, null, null, null, null, null, null, "isViewable", b)));
    }
    
    private class ChromeClient extends WebChromeClient
    {
        public View getVideoLoadingProgressView() {
            final FrameLayout frameLayout = new FrameLayout(IronSourceWebView.this.getCurrentActivityContext());
            frameLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
            return (View)frameLayout;
        }
        
        public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
            Logger.i("MyApplication", consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return true;
        }
        
        public boolean onCreateWindow(WebView webView, final boolean b, final boolean b2, final Message message) {
            webView = new WebView(webView.getContext());
            webView.setWebChromeClient((WebChromeClient)this);
            webView.setWebViewClient((WebViewClient)new FrameBustWebViewClient());
            ((WebView$WebViewTransport)message.obj).setWebView(webView);
            message.sendToTarget();
            Logger.i("onCreateWindow", "onCreateWindow");
            return true;
        }
        
        public void onHideCustomView() {
            Logger.i("Test", "onHideCustomView");
            if (IronSourceWebView.this.mCustomView == null) {
                return;
            }
            IronSourceWebView.this.mCustomView.setVisibility(8);
            IronSourceWebView.this.mCustomViewContainer.removeView(IronSourceWebView.this.mCustomView);
            IronSourceWebView.this.mCustomView = null;
            IronSourceWebView.this.mCustomViewContainer.setVisibility(8);
            IronSourceWebView.this.mCustomViewCallback.onCustomViewHidden();
            IronSourceWebView.this.setVisibility(0);
        }
        
        public void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
            Logger.i("Test", "onShowCustomView");
            IronSourceWebView.this.setVisibility(8);
            if (IronSourceWebView.this.mCustomView != null) {
                Logger.i("Test", "mCustomView != null");
                webChromeClient$CustomViewCallback.onCustomViewHidden();
                return;
            }
            Logger.i("Test", "mCustomView == null");
            IronSourceWebView.this.mCustomViewContainer.addView(view);
            IronSourceWebView.this.mCustomView = view;
            IronSourceWebView.this.mCustomViewCallback = webChromeClient$CustomViewCallback;
            IronSourceWebView.this.mCustomViewContainer.setVisibility(0);
        }
    }
    
    private class FrameBustWebViewClient extends WebViewClient
    {
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            final Context currentActivityContext = IronSourceWebView.this.getCurrentActivityContext();
            final Intent intent = new Intent(currentActivityContext, (Class)OpenUrlActivity.class);
            intent.putExtra(IronSourceWebView.EXTERNAL_URL, s);
            intent.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, false);
            currentActivityContext.startActivity(intent);
            return true;
        }
    }
    
    public class JSInterface
    {
        volatile int udiaResults;
        
        public JSInterface(final Context context) {
            this.udiaResults = 0;
        }
        
        private void callJavaScriptFunction(String access$4200, final String s) {
            if (!TextUtils.isEmpty((CharSequence)access$4200)) {
                access$4200 = IronSourceWebView.this.generateJSToInject(access$4200, s);
                IronSourceWebView.this.injectJavascript(access$4200);
            }
        }
        
        private void injectGetUDIA(String access$3900, final JSONArray jsonArray) {
            if (!TextUtils.isEmpty((CharSequence)access$3900)) {
                access$3900 = IronSourceWebView.this.generateJSToInject(access$3900, jsonArray.toString(), "onGetUDIASuccess", "onGetUDIAFail");
                IronSourceWebView.this.injectJavascript(access$3900);
            }
        }
        
        private void sendResults(final String s, final JSONArray jsonArray) {
            Logger.i(IronSourceWebView.this.TAG, "sendResults: " + this.udiaResults);
            if (this.udiaResults <= 0) {
                this.injectGetUDIA(s, jsonArray);
            }
        }
        
        private void setInterstitialAvailability(final String s, final boolean availabilityState) {
            final DemandSource demandSourceByName = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, s);
            if (demandSourceByName != null) {
                demandSourceByName.setAvailabilityState(availabilityState);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.toastingErrMsg("onInterstitialAvailability", String.valueOf(availabilityState + " with demand " + s));
            }
        }
        
        @JavascriptInterface
        public void adClicked(String string) {
            Logger.i(IronSourceWebView.this.TAG, "adClicked(" + string + ")");
            final SSAObj ssaObj = new SSAObj(string);
            final String string2 = ssaObj.getString("productType");
            string = ssaObj.getString("demandSourceName");
            if (!TextUtils.isEmpty((CharSequence)string)) {
                final SSAEnums.ProductType access$5600 = IronSourceWebView.this.getStringProductTypeAsEnum(string2);
                final DSAdProductListener access$5601 = IronSourceWebView.this.getAdProductListenerByProductType(access$5600);
                if (access$5600 != null && access$5601 != null) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            access$5601.onAdProductClick(access$5600, string);
                        }
                    });
                }
            }
        }
        
        @JavascriptInterface
        public void adCredited(final String s) {
            Log.d(IronSourceWebView.this.PUB_TAG, "adCredited(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("credits");
            int int1;
            if (string != null) {
                int1 = Integer.parseInt(string);
            }
            else {
                int1 = 0;
            }
            final String string2 = ssaObj.getString("total");
            int int2;
            if (string2 != null) {
                int2 = Integer.parseInt(string2);
            }
            else {
                int2 = 0;
            }
            final String string3 = ssaObj.getString("demandSourceName");
            final String string4 = ssaObj.getString("productType");
            final boolean boolean1 = ssaObj.getBoolean("externalPoll");
            boolean boolean2 = false;
            String string5 = null;
            boolean b = false;
            final boolean b2 = false;
            String s2;
            String s3;
            if (boolean1) {
                s2 = IronSourceWebView.this.mOWCreditsAppKey;
                s3 = IronSourceWebView.this.mOWCreditsUserId;
            }
            else {
                s2 = IronSourceWebView.this.mOWAppKey;
                s3 = IronSourceWebView.this.mOWUserId;
            }
            Label_0302: {
                if (!string4.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
                    break Label_0302;
                }
                if (!ssaObj.isNull("signature") && !ssaObj.isNull("timestamp") && !ssaObj.isNull("totalCreditsFlag")) {
                    if (ssaObj.getString("signature").equalsIgnoreCase(SDKUtils.getMD5(string2 + s2 + s3))) {
                        b = true;
                    }
                    else {
                        IronSourceWebView.this.responseBack(s, false, "Controller signature is not equal to SDK signature", null);
                        b = b2;
                    }
                    boolean2 = ssaObj.getBoolean("totalCreditsFlag");
                    string5 = ssaObj.getString("timestamp");
                    break Label_0302;
                }
                IronSourceWebView.this.responseBack(s, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
                return;
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(string4)) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (string4.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
                            IronSourceWebView.this.mDSRewardedVideoListener.onRVAdCredited(string3, int1);
                        }
                        else if (string4.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()) && b && IronSourceWebView.this.mOnOfferWallListener.onOWAdCredited(int1, int2, boolean2) && !TextUtils.isEmpty((CharSequence)string5)) {
                            if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setLatestCompeltionsTime(string5, s2, s3)) {
                                IronSourceWebView.this.responseBack(s, true, null, null);
                                return;
                            }
                            IronSourceWebView.this.responseBack(s, false, "Time Stamp could not be stored", null);
                        }
                    }
                });
            }
        }
        
        @JavascriptInterface
        public void adUnitsReady(String productType) {
            Logger.i(IronSourceWebView.this.TAG, "adUnitsReady(" + productType + ")");
            final String string = new SSAObj(productType).getString("demandSourceName");
            final AdUnitsReady adUnitsReady = new AdUnitsReady(productType);
            if (!adUnitsReady.isNumOfAdUnitsExist()) {
                IronSourceWebView.this.responseBack(productType, false, "Num Of Ad Units Do Not Exist", null);
            }
            else {
                IronSourceWebView.this.responseBack(productType, true, null, null);
                productType = adUnitsReady.getProductType();
                if (IronSourceWebView.this.shouldNotifyDeveloper(productType)) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final int int1 = Integer.parseInt(adUnitsReady.getNumOfAdUnits());
                            if (productType.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
                                if (int1 <= 0) {
                                    IronSourceWebView.this.mDSRewardedVideoListener.onRVNoMoreOffers(string);
                                    return;
                                }
                                Log.d(IronSourceWebView.this.TAG, "onRVInitSuccess()");
                                IronSourceWebView.this.mDSRewardedVideoListener.onAdProductInitSuccess(SSAEnums.ProductType.RewardedVideo, string, adUnitsReady);
                            }
                        }
                    });
                }
            }
        }
        
        @JavascriptInterface
        public String addTesterParametersToConfig(final String s, final String s2) throws JSONException {
            final JSONObject jsonObject = new JSONObject(s);
            final JSONObject jsonObject2 = new JSONObject(s2);
            jsonObject.putOpt("testerABGroup", jsonObject2.get("testerABGroup"));
            jsonObject.putOpt("testFriendlyName", jsonObject2.get("testFriendlyName"));
            return jsonObject.toString();
        }
        
        @JavascriptInterface
        public void alert(final String s) {
        }
        
        @JavascriptInterface
        public boolean areTesterParametersValid(final String s) {
            if (!TextUtils.isEmpty((CharSequence)s) && !s.contains("-1")) {
                try {
                    final JSONObject jsonObject = new JSONObject(s);
                    if (!jsonObject.getString("testerABGroup").isEmpty() && !jsonObject.getString("testFriendlyName").isEmpty()) {
                        return true;
                    }
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        }
        
        @JavascriptInterface
        public void checkInstalledApps(String s) {
            Logger.i(IronSourceWebView.this.TAG, "checkInstalledApps(" + s + ")");
            final String access$3600 = IronSourceWebView.this.extractSuccessFunctionToCall(s);
            final String access$3601 = IronSourceWebView.this.extractFailFunctionToCall(s);
            final String s2 = null;
            final SSAObj ssaObj = new SSAObj(s);
            s = ssaObj.getString(IronSourceWebView.APP_IDS);
            final Object[] access$3602 = IronSourceWebView.this.getAppsStatus(s, ssaObj.getString(IronSourceWebView.REQUEST_ID));
            final String s3 = (String)access$3602[0];
            if (access$3602[1]) {
                s = s2;
                if (!TextUtils.isEmpty((CharSequence)access$3601)) {
                    s = access$3601;
                }
            }
            else {
                s = s2;
                if (!TextUtils.isEmpty((CharSequence)access$3600)) {
                    s = access$3600;
                }
            }
            if (!TextUtils.isEmpty((CharSequence)s)) {
                s = IronSourceWebView.this.generateJSToInject(s, s3, "onCheckInstalledAppsSuccess", "onCheckInstalledAppsFail");
                IronSourceWebView.this.injectJavascript(s);
            }
        }
        
        @JavascriptInterface
        public void createCalendarEvent(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "createCalendarEvent(" + s + ")");
        }
        
        @JavascriptInterface
        public void deleteFile(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "deleteFile(" + s + ")");
            final SSAFile ssaFile = new SSAFile(s);
            if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, ssaFile.getPath())) {
                IronSourceWebView.this.responseBack(s, false, "File not exist", "1");
                return;
            }
            IronSourceWebView.this.responseBack(s, IronSourceStorageUtils.deleteFile(IronSourceWebView.this.mCacheDirectory, ssaFile.getPath(), ssaFile.getFile()), null, null);
        }
        
        @JavascriptInterface
        public void deleteFolder(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "deleteFolder(" + s + ")");
            final SSAFile ssaFile = new SSAFile(s);
            if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, ssaFile.getPath())) {
                IronSourceWebView.this.responseBack(s, false, "Folder not exist", "1");
                return;
            }
            IronSourceWebView.this.responseBack(s, IronSourceStorageUtils.deleteFolder(IronSourceWebView.this.mCacheDirectory, ssaFile.getPath()), null, null);
        }
        
        @JavascriptInterface
        public void displayWebView(String s) {
            Logger.i(IronSourceWebView.this.TAG, "displayWebView(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            final SSAObj ssaObj = new SSAObj(s);
            final boolean booleanValue = (boolean)ssaObj.get("display");
            final String string = ssaObj.getString("productType");
            final boolean boolean1 = ssaObj.getBoolean("standaloneView");
            final String string2 = ssaObj.getString("demandSourceName");
            final int n = 0;
            if (!booleanValue) {
                IronSourceWebView.this.setState(State.Gone);
                IronSourceWebView.this.closeWebView();
                return;
            }
            IronSourceWebView.this.mIsImmersive = ssaObj.getBoolean("immersive");
            IronSourceWebView.this.mIsActivityThemeTranslucent = ssaObj.getBoolean("activityThemeTranslucent");
            if (IronSourceWebView.this.getState() == State.Display) {
                Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
                return;
            }
            IronSourceWebView.this.setState(State.Display);
            Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
            final Context currentActivityContext = IronSourceWebView.this.getCurrentActivityContext();
            final String orientationState = IronSourceWebView.this.getOrientationState();
            final int applicationRotation = DeviceStatus.getApplicationRotation(currentActivityContext);
            if (boolean1) {
                final ControllerView controllerView = new ControllerView(currentActivityContext);
                controllerView.addView((View)IronSourceWebView.this.mControllerLayout);
                controllerView.showInterstitial(IronSourceWebView.this);
                return;
            }
            Intent intent;
            if (IronSourceWebView.this.mIsActivityThemeTranslucent) {
                intent = new Intent(currentActivityContext, (Class)InterstitialActivity.class);
            }
            else {
                intent = new Intent(currentActivityContext, (Class)ControllerActivity.class);
            }
            int n2;
            if (SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(string)) {
                s = orientationState;
                if ("application".equals(orientationState)) {
                    s = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                }
                n2 = 1;
                intent.putExtra("productType", SSAEnums.ProductType.RewardedVideo.toString());
                IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.RewardedVideo.ordinal());
                IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(string2);
            }
            else if (SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(string)) {
                intent.putExtra("productType", SSAEnums.ProductType.OfferWall.toString());
                IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
                n2 = n;
                s = orientationState;
            }
            else {
                n2 = n;
                s = orientationState;
                if (SSAEnums.ProductType.Interstitial.toString().equalsIgnoreCase(string)) {
                    n2 = n;
                    s = orientationState;
                    if ("application".equals(orientationState)) {
                        s = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                        n2 = n;
                    }
                }
            }
            if (n2 != 0 && IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.mDSRewardedVideoListener.onAdProductOpen(SSAEnums.ProductType.RewardedVideo, string2);
            }
            intent.setFlags(536870912);
            intent.putExtra("immersive", IronSourceWebView.this.mIsImmersive);
            intent.putExtra("orientation_set_flag", s);
            intent.putExtra("rotation_set_flag", applicationRotation);
            currentActivityContext.startActivity(intent);
        }
        
        @JavascriptInterface
        public void getApplicationInfo(String access$3900) {
            Logger.i(IronSourceWebView.this.TAG, "getApplicationInfo(" + access$3900 + ")");
            final String access$3901 = IronSourceWebView.this.extractSuccessFunctionToCall(access$3900);
            final String access$3902 = IronSourceWebView.this.extractFailFunctionToCall(access$3900);
            final SSAObj ssaObj = new SSAObj(access$3900);
            final String string = ssaObj.getString("productType");
            final String string2 = ssaObj.getString("demandSourceName");
            access$3900 = null;
            final Object[] array = new Object[2];
            final Object[] access$3903 = IronSourceWebView.this.getApplicationParams(string, string2);
            final String s = (String)access$3903[0];
            if (access$3903[1]) {
                if (!TextUtils.isEmpty((CharSequence)access$3902)) {
                    access$3900 = access$3902;
                }
            }
            else if (!TextUtils.isEmpty((CharSequence)access$3901)) {
                access$3900 = access$3901;
            }
            if (!TextUtils.isEmpty((CharSequence)access$3900)) {
                access$3900 = IronSourceWebView.this.generateJSToInject(access$3900, s, "onGetApplicationInfoSuccess", "onGetApplicationInfoFail");
                IronSourceWebView.this.injectJavascript(access$3900);
            }
        }
        
        @JavascriptInterface
        public void getAppsInstallTime(final String p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: aload_1        
            //     5: invokespecial   com/ironsource/sdk/data/SSAObj.<init>:(Ljava/lang/String;)V
            //     8: astore_3       
            //     9: iconst_1       
            //    10: istore_2       
            //    11: aconst_null    
            //    12: astore          4
            //    14: aload_3        
            //    15: ldc_w           "systemApps"
            //    18: invokevirtual   com/ironsource/sdk/data/SSAObj.getString:(Ljava/lang/String;)Ljava/lang/String;
            //    21: astore_3       
            //    22: aload_0        
            //    23: getfield        com/ironsource/sdk/controller/IronSourceWebView$JSInterface.this$0:Lcom/ironsource/sdk/controller/IronSourceWebView;
            //    26: invokevirtual   com/ironsource/sdk/controller/IronSourceWebView.getContext:()Landroid/content/Context;
            //    29: aload_3        
            //    30: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //    33: invokestatic    com/ironsource/environment/DeviceStatus.getAppsInstallTime:(Landroid/content/Context;Z)Lorg/json/JSONObject;
            //    36: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
            //    39: astore_3       
            //    40: iconst_0       
            //    41: istore_2       
            //    42: iload_2        
            //    43: ifeq            158
            //    46: aload_0        
            //    47: getfield        com/ironsource/sdk/controller/IronSourceWebView$JSInterface.this$0:Lcom/ironsource/sdk/controller/IronSourceWebView;
            //    50: aload_1        
            //    51: invokestatic    com/ironsource/sdk/controller/IronSourceWebView.access$3700:(Lcom/ironsource/sdk/controller/IronSourceWebView;Ljava/lang/String;)Ljava/lang/String;
            //    54: astore          5
            //    56: aload           4
            //    58: astore_1       
            //    59: aload           5
            //    61: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
            //    64: ifne            70
            //    67: aload           5
            //    69: astore_1       
            //    70: aload_1        
            //    71: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
            //    74: ifne            110
            //    77: aload_3        
            //    78: invokestatic    java/nio/charset/Charset.defaultCharset:()Ljava/nio/charset/Charset;
            //    81: invokevirtual   java/nio/charset/Charset.name:()Ljava/lang/String;
            //    84: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
            //    87: astore          4
            //    89: aload           4
            //    91: astore_3       
            //    92: aload_0        
            //    93: getfield        com/ironsource/sdk/controller/IronSourceWebView$JSInterface.this$0:Lcom/ironsource/sdk/controller/IronSourceWebView;
            //    96: aload_1        
            //    97: aload_3        
            //    98: invokestatic    com/ironsource/sdk/controller/IronSourceWebView.access$4200:(Lcom/ironsource/sdk/controller/IronSourceWebView;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
            //   101: astore_1       
            //   102: aload_0        
            //   103: getfield        com/ironsource/sdk/controller/IronSourceWebView$JSInterface.this$0:Lcom/ironsource/sdk/controller/IronSourceWebView;
            //   106: aload_1        
            //   107: invokestatic    com/ironsource/sdk/controller/IronSourceWebView.access$4000:(Lcom/ironsource/sdk/controller/IronSourceWebView;Ljava/lang/String;)V
            //   110: return         
            //   111: astore_3       
            //   112: aload_0        
            //   113: getfield        com/ironsource/sdk/controller/IronSourceWebView$JSInterface.this$0:Lcom/ironsource/sdk/controller/IronSourceWebView;
            //   116: invokestatic    com/ironsource/sdk/controller/IronSourceWebView.access$300:(Lcom/ironsource/sdk/controller/IronSourceWebView;)Ljava/lang/String;
            //   119: new             Ljava/lang/StringBuilder;
            //   122: dup            
            //   123: invokespecial   java/lang/StringBuilder.<init>:()V
            //   126: ldc_w           "getAppsInstallTime failed("
            //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   132: aload_3        
            //   133: invokevirtual   java/lang/Exception.getLocalizedMessage:()Ljava/lang/String;
            //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   139: ldc             ")"
            //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   144: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   147: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
            //   150: aload_3        
            //   151: invokevirtual   java/lang/Exception.getLocalizedMessage:()Ljava/lang/String;
            //   154: astore_3       
            //   155: goto            42
            //   158: aload_0        
            //   159: getfield        com/ironsource/sdk/controller/IronSourceWebView$JSInterface.this$0:Lcom/ironsource/sdk/controller/IronSourceWebView;
            //   162: aload_1        
            //   163: invokestatic    com/ironsource/sdk/controller/IronSourceWebView.access$3600:(Lcom/ironsource/sdk/controller/IronSourceWebView;Ljava/lang/String;)Ljava/lang/String;
            //   166: astore          5
            //   168: aload           4
            //   170: astore_1       
            //   171: aload           5
            //   173: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
            //   176: ifne            70
            //   179: aload           5
            //   181: astore_1       
            //   182: goto            70
            //   185: astore          4
            //   187: aload           4
            //   189: invokevirtual   java/io/UnsupportedEncodingException.printStackTrace:()V
            //   192: goto            92
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                  
            //  -----  -----  -----  -----  --------------------------------------
            //  14     40     111    158    Ljava/lang/Exception;
            //  77     89     185    195    Ljava/io/UnsupportedEncodingException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
        
        @JavascriptInterface
        public void getCachedFilesMap(String s) {
            Logger.i(IronSourceWebView.this.TAG, "getCachedFilesMap(" + s + ")");
            final String access$3600 = IronSourceWebView.this.extractSuccessFunctionToCall(s);
            if (!TextUtils.isEmpty((CharSequence)access$3600)) {
                final SSAObj ssaObj = new SSAObj(s);
                if (!ssaObj.containsKey("path")) {
                    IronSourceWebView.this.responseBack(s, false, "path key does not exist", null);
                }
                else {
                    final String s2 = (String)ssaObj.get("path");
                    if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, s2)) {
                        IronSourceWebView.this.responseBack(s, false, "path file does not exist on disk", null);
                        return;
                    }
                    s = IronSourceStorageUtils.getCachedFilesMap(IronSourceWebView.this.mCacheDirectory, s2);
                    s = IronSourceWebView.this.generateJSToInject(access$3600, s, "onGetCachedFilesMapSuccess", "onGetCachedFilesMapFail");
                    IronSourceWebView.this.injectJavascript(s);
                }
            }
        }
        
        @JavascriptInterface
        public void getControllerConfig(String s) {
            Logger.i(IronSourceWebView.this.TAG, "getControllerConfig(" + s + ")");
            final String string = new SSAObj(s).getString(IronSourceWebView.JSON_KEY_SUCCESS);
            if (TextUtils.isEmpty((CharSequence)string)) {
                return;
            }
            final String controllerConfig = SDKUtils.getControllerConfig();
            final String testerParameters = SDKUtils.getTesterParameters();
            s = controllerConfig;
            while (true) {
                if (!this.areTesterParametersValid(testerParameters)) {
                    break Label_0085;
                }
                try {
                    s = this.addTesterParametersToConfig(controllerConfig, testerParameters);
                    s = IronSourceWebView.this.generateJSToInject(string, s);
                    IronSourceWebView.this.injectJavascript(s);
                }
                catch (JSONException ex) {
                    Logger.d(IronSourceWebView.this.TAG, "getControllerConfig Error while parsing Tester AB Group parameters");
                    s = controllerConfig;
                    continue;
                }
                break;
            }
        }
        
        @JavascriptInterface
        public void getDemandSourceState(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "getMediationState(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("demandSourceName");
            final String string2 = ssaObj.getString("productType");
            if (string2 != null && string != null) {
                try {
                    final SSAEnums.ProductType productType = SDKUtils.getProductType(string2);
                    if (productType != null) {
                        final DemandSource demandSourceByName = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(productType, string);
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("productType", (Object)string2);
                        jsonObject.put("demandSourceName", (Object)string);
                        String s2;
                        if (demandSourceByName != null && !demandSourceByName.isMediationState(-1)) {
                            s2 = IronSourceWebView.this.extractSuccessFunctionToCall(s);
                            jsonObject.put("state", demandSourceByName.getMediationState());
                        }
                        else {
                            s2 = IronSourceWebView.this.extractFailFunctionToCall(s);
                        }
                        this.callJavaScriptFunction(s2, jsonObject.toString());
                    }
                }
                catch (Exception ex) {
                    IronSourceWebView.this.responseBack(s, false, ex.getMessage(), null);
                    ex.printStackTrace();
                }
            }
        }
        
        @JavascriptInterface
        public void getDeviceLocation(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "getDeviceLocation(" + s + ")");
            try {
                IronSourceWebView.this.responseBack(IronSourceWebView.this.createLocationObject(s, LocationService.getLastLocation(IronSourceWebView.this.getContext())).toString(), true, null, null);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        @JavascriptInterface
        public void getDevicePreciseLocation(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "getDevicePreciseLocation(" + s + ")");
            try {
                LocationService.getPreciseLocation(IronSourceWebView.this.getContext(), (LocationService.ISLocationListener)new LocationService.ISLocationListener() {
                    @Override
                    public void onLocationChanged(final Location location) {
                        IronSourceWebView.this.responseBack(IronSourceWebView.this.createLocationObject(s, location).toString(), true, null, null);
                    }
                });
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        @JavascriptInterface
        public void getDeviceStatus(String access$3900) {
            Logger.i(IronSourceWebView.this.TAG, "getDeviceStatus(" + access$3900 + ")");
            final String access$3901 = IronSourceWebView.this.extractSuccessFunctionToCall(access$3900);
            final String access$3902 = IronSourceWebView.this.extractFailFunctionToCall(access$3900);
            final Object[] array = new Object[2];
            final Object[] access$3903 = IronSourceWebView.this.getDeviceParams(IronSourceWebView.this.getContext());
            final String s = (String)access$3903[0];
            final boolean booleanValue = (boolean)access$3903[1];
            access$3900 = null;
            if (booleanValue) {
                if (!TextUtils.isEmpty((CharSequence)access$3902)) {
                    access$3900 = access$3902;
                }
            }
            else if (!TextUtils.isEmpty((CharSequence)access$3901)) {
                access$3900 = access$3901;
            }
            if (!TextUtils.isEmpty((CharSequence)access$3900)) {
                access$3900 = IronSourceWebView.this.generateJSToInject(access$3900, s, "onGetDeviceStatusSuccess", "onGetDeviceStatusFail");
                IronSourceWebView.this.injectJavascript(access$3900);
            }
        }
        
        @JavascriptInterface
        public void getDeviceVolume(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "getDeviceVolume(" + s + ")");
            try {
                final float deviceVolume = DeviceProperties.getInstance(IronSourceWebView.this.getCurrentActivityContext()).getDeviceVolume(IronSourceWebView.this.getCurrentActivityContext());
                final SSAObj ssaObj = new SSAObj(s);
                ssaObj.put("deviceVolume", String.valueOf(deviceVolume));
                IronSourceWebView.this.responseBack(ssaObj.toString(), true, null, null);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        @JavascriptInterface
        public void getOrientation(String s) {
            s = IronSourceWebView.this.extractSuccessFunctionToCall(s);
            final String string = SDKUtils.getOrientation(IronSourceWebView.this.getCurrentActivityContext()).toString();
            if (!TextUtils.isEmpty((CharSequence)s)) {
                s = IronSourceWebView.this.generateJSToInject(s, string, "onGetOrientationSuccess", "onGetOrientationFail");
                IronSourceWebView.this.injectJavascript(s);
            }
        }
        
        @JavascriptInterface
        public void getUDIA(String ex) {
            this.udiaResults = 0;
            Logger.i(IronSourceWebView.this.TAG, "getUDIA(" + (String)ex + ")");
            final String access$3600 = IronSourceWebView.this.extractSuccessFunctionToCall((String)ex);
            final SSAObj ssaObj = new SSAObj((String)ex);
            if (!ssaObj.containsKey("getByFlag")) {
                IronSourceWebView.this.responseBack((String)ex, false, "getByFlag key does not exist", null);
            }
            else {
                final int int1 = Integer.parseInt(ssaObj.getString("getByFlag"));
                if (int1 != 0) {
                    final String binaryString = Integer.toBinaryString(int1);
                    if (TextUtils.isEmpty((CharSequence)binaryString)) {
                        IronSourceWebView.this.responseBack((String)ex, false, "fialed to convert getByFlag", null);
                        return;
                    }
                    Object o = new StringBuilder(binaryString).reverse().toString().toCharArray();
                    ex = (JSONException)new JSONArray();
                    while (true) {
                        if (o[3] != 48) {
                            break Label_0202;
                        }
                        final JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("sessions", (Object)IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSessions());
                            IronSourceSharedPrefHelper.getSupersonicPrefHelper().deleteSessions();
                            ((JSONArray)ex).put((Object)jsonObject);
                            if (o[2] == 49) {
                                ++this.udiaResults;
                                o = LocationService.getLastLocation(IronSourceWebView.this.getContext());
                                if (o != null) {
                                    final JSONObject jsonObject2 = new JSONObject();
                                    try {
                                        jsonObject2.put("latitude", ((Location)o).getLatitude());
                                        jsonObject2.put("longitude", ((Location)o).getLongitude());
                                        ((JSONArray)ex).put((Object)jsonObject2);
                                        --this.udiaResults;
                                        this.sendResults(access$3600, (JSONArray)ex);
                                        Logger.i(IronSourceWebView.this.TAG, "done location");
                                        return;
                                    }
                                    catch (JSONException ex) {
                                        return;
                                    }
                                }
                                --this.udiaResults;
                            }
                        }
                        catch (JSONException ex2) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        @JavascriptInterface
        public void getUserData(String s) {
            Logger.i(IronSourceWebView.this.TAG, "getUserData(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            if (!ssaObj.containsKey("key")) {
                IronSourceWebView.this.responseBack(s, false, "key does not exist", null);
                return;
            }
            s = IronSourceWebView.this.extractSuccessFunctionToCall(s);
            final String string = ssaObj.getString("key");
            s = IronSourceWebView.this.generateJSToInject(s, IronSourceWebView.this.parseToJson(string, IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUserData(string), null, null, null, null, null, null, null, false));
            IronSourceWebView.this.injectJavascript(s);
        }
        
        @JavascriptInterface
        public void getUserUniqueId(String s) {
            Logger.i(IronSourceWebView.this.TAG, "getUserUniqueId(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            if (!ssaObj.containsKey("productType")) {
                IronSourceWebView.this.responseBack(s, false, "productType does not exist", null);
            }
            else {
                s = IronSourceWebView.this.extractSuccessFunctionToCall(s);
                if (!TextUtils.isEmpty((CharSequence)s)) {
                    final String string = ssaObj.getString("productType");
                    s = IronSourceWebView.this.generateJSToInject(s, IronSourceWebView.this.parseToJson("userUniqueId", IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId(string), "productType", string, null, null, null, null, null, false), "onGetUserUniqueIdSuccess", "onGetUserUniqueIdFail");
                    IronSourceWebView.this.injectJavascript(s);
                }
            }
        }
        
        void handleControllerStageFailed() {
            IronSourceWebView.this.mControllerState = SSAEnums.ControllerState.Failed;
            for (final DemandSource demandSource : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo)) {
                if (demandSource.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, demandSource.getDemandSourceName());
                }
            }
            for (final DemandSource demandSource2 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial)) {
                if (demandSource2.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, demandSource2.getDemandSourceName());
                }
            }
            if (IronSourceWebView.this.mOWmiss) {
                IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
            }
            if (IronSourceWebView.this.mOWCreditsMiss) {
                IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
            }
        }
        
        void handleControllerStageLoaded() {
            IronSourceWebView.this.mControllerState = SSAEnums.ControllerState.Loaded;
        }
        
        void handleControllerStageReady() {
            IronSourceWebView.this.mControllerState = SSAEnums.ControllerState.Ready;
            IronSourceWebView.this.mGlobalControllerTimer.cancel();
            IronSourceWebView.this.mLoadControllerTimer.cancel();
            IronSourceWebView.this.invokePendingCommands();
            for (final DemandSource demandSource : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo)) {
                if (demandSource.getDemandSourceInitState() == 1) {
                    IronSourceWebView.this.initRewardedVideo(IronSourceWebView.this.mRVAppKey, IronSourceWebView.this.mRVUserId, demandSource, IronSourceWebView.this.mDSRewardedVideoListener);
                }
            }
            for (final DemandSource demandSource2 : IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial)) {
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
        
        @JavascriptInterface
        public void initController(String string) {
            Logger.i(IronSourceWebView.this.TAG, "initController(" + string + ")");
            final SSAObj ssaObj = new SSAObj(string);
            if (ssaObj.containsKey("stage")) {
                string = ssaObj.getString("stage");
                if ("ready".equalsIgnoreCase(string)) {
                    this.handleControllerStageReady();
                }
                else if ("loaded".equalsIgnoreCase(string)) {
                    this.handleControllerStageLoaded();
                }
                else if ("failed".equalsIgnoreCase(string)) {
                    this.handleControllerStageFailed();
                }
                else {
                    Logger.i(IronSourceWebView.this.TAG, "No STAGE mentioned! Should not get here!");
                }
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (Build$VERSION.SDK_INT < 16) {
                            return;
                        }
                        try {
                            IronSourceWebView.this.getSettings().setAllowFileAccessFromFileURLs(false);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        }
        
        @JavascriptInterface
        public void locationServicesEnabled(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "locationServicesEnabled(" + s + ")");
            try {
                final boolean locationServicesEnabled = LocationService.locationServicesEnabled(IronSourceWebView.this.getContext());
                final SSAObj ssaObj = new SSAObj(s);
                ssaObj.put("status", String.valueOf(locationServicesEnabled));
                IronSourceWebView.this.responseBack(ssaObj.toString(), true, null, null);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        @JavascriptInterface
        public void moatAPI(final String s) {
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Logger.i(IronSourceWebView.this.TAG, "moatAPI(" + s + ")");
                        IronSourceWebView.this.mMoatJsAdapter.call(new SSAObj(s).toString(), new JSCallbackTask(), IronSourceWebView.this.getWebview());
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.i(IronSourceWebView.this.TAG, "moatAPI failed with exception " + ex.getMessage());
                    }
                }
            });
        }
        
        @JavascriptInterface
        public void onAdWindowsClosed(String string) {
            Logger.i(IronSourceWebView.this.TAG, "onAdWindowsClosed(" + string + ")");
            IronSourceWebView.this.mSavedState.adClosed();
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(null);
            final SSAObj ssaObj = new SSAObj(string);
            string = ssaObj.getString("productType");
            final String string2 = ssaObj.getString("demandSourceName");
            final SSAEnums.ProductType access$5600 = IronSourceWebView.this.getStringProductTypeAsEnum(string);
            Log.d(IronSourceWebView.this.PUB_TAG, "onAdClosed() with type " + access$5600);
            if (IronSourceWebView.this.shouldNotifyDeveloper(string) && string != null) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (access$5600 == SSAEnums.ProductType.RewardedVideo || access$5600 == SSAEnums.ProductType.Interstitial) {
                            final DSAdProductListener access$5700 = IronSourceWebView.this.getAdProductListenerByProductType(access$5600);
                            if (access$5700 != null) {
                                access$5700.onAdProductClose(access$5600, string2);
                            }
                        }
                        else if (access$5600 == SSAEnums.ProductType.OfferWall) {
                            IronSourceWebView.this.mOnOfferWallListener.onOWAdClosed();
                        }
                    }
                });
            }
        }
        
        @JavascriptInterface
        public void onGenericFunctionFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGenericFunctionFail(" + s + ")");
            if (IronSourceWebView.this.mOnGenericFunctionListener == null) {
                Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
                return;
            }
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                final /* synthetic */ String val$message = new SSAObj(s).getString("errMsg");
                
                @Override
                public void run() {
                    IronSourceWebView.this.mOnGenericFunctionListener.onGFFail(this.val$message);
                }
            });
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onGenericFunctionFail", s);
        }
        
        @JavascriptInterface
        public void onGenericFunctionSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGenericFunctionSuccess(" + s + ")");
            if (IronSourceWebView.this.mOnGenericFunctionListener == null) {
                Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
                return;
            }
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    IronSourceWebView.this.mOnGenericFunctionListener.onGFSuccess();
                }
            });
            IronSourceWebView.this.responseBack(s, true, null, null);
        }
        
        @JavascriptInterface
        public void onGetApplicationInfoFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetApplicationInfoFail(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onGetApplicationInfoFail", s);
        }
        
        @JavascriptInterface
        public void onGetApplicationInfoSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetApplicationInfoSuccess(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onGetApplicationInfoSuccess", s);
        }
        
        @JavascriptInterface
        public void onGetCachedFilesMapFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetCachedFilesMapFail(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onGetCachedFilesMapFail", s);
        }
        
        @JavascriptInterface
        public void onGetCachedFilesMapSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetCachedFilesMapSuccess(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onGetCachedFilesMapSuccess", s);
        }
        
        @JavascriptInterface
        public void onGetDeviceStatusFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetDeviceStatusFail(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onGetDeviceStatusFail", s);
        }
        
        @JavascriptInterface
        public void onGetDeviceStatusSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetDeviceStatusSuccess(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onGetDeviceStatusSuccess", s);
        }
        
        @JavascriptInterface
        public void onGetUDIAFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUDIAFail(" + s + ")");
        }
        
        @JavascriptInterface
        public void onGetUDIASuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUDIASuccess(" + s + ")");
        }
        
        @JavascriptInterface
        public void onGetUserCreditsFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUserCreditsFail(" + s + ")");
            final String string = new SSAObj(s).getString("errMsg");
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String val$message;
                        if ((val$message = string) == null) {
                            val$message = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mOnOfferWallListener.onGetOWCreditsFailed(val$message);
                    }
                });
            }
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onGetUserCreditsFail", s);
        }
        
        @JavascriptInterface
        public void onGetUserUniqueIdFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUserUniqueIdFail(" + s + ")");
        }
        
        @JavascriptInterface
        public void onGetUserUniqueIdSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onGetUserUniqueIdSuccess(" + s + ")");
        }
        
        @JavascriptInterface
        public void onInitInterstitialFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialFail(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("errMsg");
            final String string2 = ssaObj.getString("demandSourceName");
            if (TextUtils.isEmpty((CharSequence)string2)) {
                Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
                return;
            }
            final DemandSource demandSourceByName = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, string2);
            if (demandSourceByName != null) {
                demandSourceByName.setDemandSourceInitState(3);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String val$message;
                        if ((val$message = string) == null) {
                            val$message = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onInterstitialInitFail(message:" + val$message + ")");
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductInitFailed(SSAEnums.ProductType.Interstitial, string2, val$message);
                    }
                });
            }
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onInitInterstitialFail", s);
        }
        
        @JavascriptInterface
        public void onInitInterstitialSuccess(String string) {
            Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess()");
            IronSourceWebView.this.toastingErrMsg("onInitInterstitialSuccess", "true");
            string = new SSAObj(string).getString("demandSourceName");
            if (TextUtils.isEmpty((CharSequence)string)) {
                Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
            }
            else if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(IronSourceWebView.this.TAG, "onInterstitialInitSuccess()");
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductInitSuccess(SSAEnums.ProductType.Interstitial, string, null);
                    }
                });
            }
        }
        
        @JavascriptInterface
        public void onInitOfferWallFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onInitOfferWallFail(" + s + ")");
            IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(false);
            final String string = new SSAObj(s).getString("errMsg");
            if (IronSourceWebView.this.mSavedState.reportInitOfferwall()) {
                IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
                if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String val$message;
                            if ((val$message = string) == null) {
                                val$message = "We're sorry, some error occurred. we will investigate it";
                            }
                            Log.d(IronSourceWebView.this.TAG, "onOfferWallInitFail(message:" + val$message + ")");
                            IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitFail(val$message);
                        }
                    });
                }
            }
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onInitOfferWallFail", s);
        }
        
        @JavascriptInterface
        public void onInitOfferWallSuccess(final String s) {
            IronSourceWebView.this.toastingErrMsg("onInitOfferWallSuccess", "true");
            IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(true);
            if (IronSourceWebView.this.mSavedState.reportInitOfferwall()) {
                IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
                if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(IronSourceWebView.this.TAG, "onOfferWallInitSuccess()");
                            IronSourceWebView.this.mOnOfferWallListener.onOfferwallInitSuccess();
                        }
                    });
                }
            }
        }
        
        @JavascriptInterface
        public void onInitRewardedVideoFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoFail(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("errMsg");
            final String string2 = ssaObj.getString("demandSourceName");
            final DemandSource demandSourceByName = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, string2);
            if (demandSourceByName != null) {
                demandSourceByName.setDemandSourceInitState(3);
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String val$message;
                        if ((val$message = string) == null) {
                            val$message = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onRVInitFail(message:" + val$message + ")");
                        IronSourceWebView.this.mDSRewardedVideoListener.onAdProductInitFailed(SSAEnums.ProductType.RewardedVideo, string2, val$message);
                    }
                });
            }
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onInitRewardedVideoFail", s);
        }
        
        @JavascriptInterface
        public void onInitRewardedVideoSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoSuccess(" + s + ")");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSSABCParameters(new SSABCParameters(s));
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onInitRewardedVideoSuccess", s);
        }
        
        @JavascriptInterface
        public void onLoadInterstitialFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialFail(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("errMsg");
            final String string2 = ssaObj.getString("demandSourceName");
            IronSourceWebView.this.responseBack(s, true, null, null);
            if (TextUtils.isEmpty((CharSequence)string2)) {
                return;
            }
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String val$message;
                        if ((val$message = string) == null) {
                            val$message = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadFailed(string2, val$message);
                    }
                });
            }
            IronSourceWebView.this.toastingErrMsg("onLoadInterstitialFail", "true");
        }
        
        @JavascriptInterface
        public void onLoadInterstitialSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialSuccess(" + s + ")");
            final String string = new SSAObj(s).getString("demandSourceName");
            this.setInterstitialAvailability(string, true);
            IronSourceWebView.this.responseBack(s, true, null, null);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialLoadSuccess(string);
                    }
                });
            }
            IronSourceWebView.this.toastingErrMsg("onLoadInterstitialSuccess", "true");
        }
        
        @JavascriptInterface
        public void onOfferWallGeneric(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onOfferWallGeneric(" + s + ")");
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.mOnOfferWallListener.onOWGeneric("", "");
            }
        }
        
        @JavascriptInterface
        public void onShowInterstitialFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialFail(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("errMsg");
            final String string2 = ssaObj.getString("demandSourceName");
            IronSourceWebView.this.responseBack(s, true, null, null);
            if (TextUtils.isEmpty((CharSequence)string2)) {
                return;
            }
            this.setInterstitialAvailability(string2, false);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String val$message;
                        if ((val$message = string) == null) {
                            val$message = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialShowFailed(string2, val$message);
                    }
                });
            }
            IronSourceWebView.this.toastingErrMsg("onShowInterstitialFail", s);
        }
        
        @JavascriptInterface
        public void onShowInterstitialSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            final String string = new SSAObj(s).getString("demandSourceName");
            if (TextUtils.isEmpty((CharSequence)string)) {
                Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess called with no demand");
                return;
            }
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.Interstitial.ordinal());
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(string);
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        IronSourceWebView.this.mDSInterstitialListener.onAdProductOpen(SSAEnums.ProductType.Interstitial, string);
                        IronSourceWebView.this.mDSInterstitialListener.onInterstitialShowSuccess(string);
                    }
                });
                IronSourceWebView.this.toastingErrMsg("onShowInterstitialSuccess", s);
            }
            this.setInterstitialAvailability(string, false);
        }
        
        @JavascriptInterface
        public void onShowOfferWallFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallFail(" + s + ")");
            final String string = new SSAObj(s).getString("errMsg");
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String val$message;
                        if ((val$message = string) == null) {
                            val$message = "We're sorry, some error occurred. we will investigate it";
                        }
                        IronSourceWebView.this.mOnOfferWallListener.onOWShowFail(val$message);
                    }
                });
            }
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onShowOfferWallFail", s);
        }
        
        @JavascriptInterface
        public void onShowOfferWallSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallSuccess(" + s + ")");
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
            final String valueFromJsonObject = SDKUtils.getValueFromJsonObject(s, "placementId");
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        IronSourceWebView.this.mOnOfferWallListener.onOWShowSuccess(valueFromJsonObject);
                    }
                });
            }
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onShowOfferWallSuccess", s);
        }
        
        @JavascriptInterface
        public void onShowRewardedVideoFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoFail(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("errMsg");
            final String string2 = ssaObj.getString("demandSourceName");
            if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
                IronSourceWebView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String val$message;
                        if ((val$message = string) == null) {
                            val$message = "We're sorry, some error occurred. we will investigate it";
                        }
                        Log.d(IronSourceWebView.this.TAG, "onRVShowFail(message:" + string + ")");
                        IronSourceWebView.this.mDSRewardedVideoListener.onRVShowFail(string2, val$message);
                    }
                });
            }
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onShowRewardedVideoFail", s);
        }
        
        @JavascriptInterface
        public void onShowRewardedVideoSuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoSuccess(" + s + ")");
            IronSourceWebView.this.responseBack(s, true, null, null);
            IronSourceWebView.this.toastingErrMsg("onShowRewardedVideoSuccess", s);
        }
        
        @JavascriptInterface
        public void onUDIAFail(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onUDIAFail(" + s + ")");
        }
        
        @JavascriptInterface
        public void onUDIASuccess(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "onUDIASuccess(" + s + ")");
        }
        
        @JavascriptInterface
        public void onVideoStatusChanged(String string) {
            Log.d(IronSourceWebView.this.TAG, "onVideoStatusChanged(" + string + ")");
            final SSAObj ssaObj = new SSAObj(string);
            final String string2 = ssaObj.getString("productType");
            if (IronSourceWebView.this.mVideoEventsListener != null && !TextUtils.isEmpty((CharSequence)string2) && SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(string2)) {
                string = ssaObj.getString("status");
                if ("started".equalsIgnoreCase(string)) {
                    IronSourceWebView.this.mVideoEventsListener.onVideoStarted();
                }
                else {
                    if ("paused".equalsIgnoreCase(string)) {
                        IronSourceWebView.this.mVideoEventsListener.onVideoPaused();
                        return;
                    }
                    if ("playing".equalsIgnoreCase(string)) {
                        IronSourceWebView.this.mVideoEventsListener.onVideoResumed();
                        return;
                    }
                    if ("ended".equalsIgnoreCase(string)) {
                        IronSourceWebView.this.mVideoEventsListener.onVideoEnded();
                        return;
                    }
                    if ("stopped".equalsIgnoreCase(string)) {
                        IronSourceWebView.this.mVideoEventsListener.onVideoStopped();
                        return;
                    }
                    Logger.i(IronSourceWebView.this.TAG, "onVideoStatusChanged: unknown status: " + string);
                }
            }
        }
        
        @JavascriptInterface
        public void openUrl(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "openUrl(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("url");
            final String string2 = ssaObj.getString("method");
            final Context currentActivityContext = IronSourceWebView.this.getCurrentActivityContext();
            try {
                if (string2.equalsIgnoreCase("external_browser")) {
                    UrlHandler.openUrl(currentActivityContext, string);
                    return;
                }
                if (string2.equalsIgnoreCase("webview")) {
                    final Intent intent = new Intent(currentActivityContext, (Class)OpenUrlActivity.class);
                    intent.putExtra(IronSourceWebView.EXTERNAL_URL, string);
                    intent.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
                    intent.putExtra("immersive", IronSourceWebView.this.mIsImmersive);
                    currentActivityContext.startActivity(intent);
                    return;
                }
            }
            catch (Exception ex) {
                IronSourceWebView.this.responseBack(s, false, ex.getMessage(), null);
                ex.printStackTrace();
                return;
            }
            if (string2.equalsIgnoreCase("store")) {
                final Intent intent2 = new Intent(currentActivityContext, (Class)OpenUrlActivity.class);
                intent2.putExtra(IronSourceWebView.EXTERNAL_URL, string);
                intent2.putExtra(IronSourceWebView.IS_STORE, true);
                intent2.putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
                currentActivityContext.startActivity(intent2);
            }
        }
        
        @JavascriptInterface
        public void permissionsAPI(final String s) {
            try {
                Logger.i(IronSourceWebView.this.TAG, "permissionsAPI(" + s + ")");
                IronSourceWebView.this.mPermissionsJsAdapter.call(new SSAObj(s).toString(), new JSCallbackTask());
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Logger.i(IronSourceWebView.this.TAG, "permissionsAPI failed with exception " + ex.getMessage());
            }
        }
        
        @JavascriptInterface
        public void postAdEventNotification(String s) {
            try {
                Logger.i(IronSourceWebView.this.TAG, "postAdEventNotification(" + s + ")");
                final SSAObj ssaObj = new SSAObj(s);
                final String string = ssaObj.getString("eventName");
                if (TextUtils.isEmpty((CharSequence)string)) {
                    IronSourceWebView.this.responseBack(s, false, "eventName does not exist", null);
                    return;
                }
                final String string2 = ssaObj.getString("dsName");
                final JSONObject jsonObject = (JSONObject)ssaObj.get("extData");
                final String string3 = ssaObj.getString("productType");
                final SSAEnums.ProductType access$5600 = IronSourceWebView.this.getStringProductTypeAsEnum(string3);
                if (IronSourceWebView.this.shouldNotifyDeveloper(string3)) {
                    s = IronSourceWebView.this.extractSuccessFunctionToCall(s);
                    if (!TextUtils.isEmpty((CharSequence)s)) {
                        s = IronSourceWebView.this.generateJSToInject(s, IronSourceWebView.this.parseToJson("productType", string3, "eventName", string, "demandSourceName", string2, null, null, null, false), "postAdEventNotificationSuccess", "postAdEventNotificationFail");
                        IronSourceWebView.this.injectJavascript(s);
                    }
                    IronSourceWebView.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (access$5600 == SSAEnums.ProductType.Interstitial || access$5600 == SSAEnums.ProductType.RewardedVideo) {
                                final DSAdProductListener access$5700 = IronSourceWebView.this.getAdProductListenerByProductType(access$5600);
                                if (access$5700 != null) {
                                    access$5700.onAdProductEventNotificationReceived(access$5600, string2, string, jsonObject);
                                }
                            }
                            else if (access$5600 == SSAEnums.ProductType.OfferWall) {
                                IronSourceWebView.this.mOnOfferWallListener.onOfferwallEventNotificationReceived(string, jsonObject);
                            }
                        }
                    });
                    return;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            IronSourceWebView.this.responseBack(s, false, "productType does not exist", null);
        }
        
        @JavascriptInterface
        public void removeCloseEventHandler(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "removeCloseEventHandler(" + s + ")");
            if (IronSourceWebView.this.mCloseEventTimer != null) {
                IronSourceWebView.this.mCloseEventTimer.cancel();
            }
            IronSourceWebView.this.isRemoveCloseEventHandler = true;
        }
        
        @JavascriptInterface
        public void saveFile(String s) {
            Logger.i(IronSourceWebView.this.TAG, "saveFile(" + s + ")");
            final SSAFile ssaFile = new SSAFile(s);
            if (DeviceStatus.getAvailableMemorySizeInMegaBytes(IronSourceWebView.this.mCacheDirectory) <= 0L) {
                IronSourceWebView.this.responseBack(s, false, "no_disk_space", null);
                return;
            }
            if (!SDKUtils.isExternalStorageAvailable()) {
                IronSourceWebView.this.responseBack(s, false, "sotrage_unavailable", null);
                return;
            }
            if (IronSourceStorageUtils.isFileCached(IronSourceWebView.this.mCacheDirectory, ssaFile)) {
                IronSourceWebView.this.responseBack(s, false, "file_already_exist", null);
                return;
            }
            if (!ConnectivityService.isConnected(IronSourceWebView.this.getContext())) {
                IronSourceWebView.this.responseBack(s, false, "no_network_connection", null);
                return;
            }
            IronSourceWebView.this.responseBack(s, true, null, null);
            s = ssaFile.getLastUpdateTime();
            if (s != null) {
                final String value = String.valueOf(s);
                if (!TextUtils.isEmpty((CharSequence)value)) {
                    s = ssaFile.getPath();
                    if (s.contains("/")) {
                        final String[] split = ssaFile.getPath().split("/");
                        s = split[split.length - 1];
                    }
                    IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(s, value);
                }
            }
            IronSourceWebView.this.downloadManager.downloadFile(ssaFile);
        }
        
        @JavascriptInterface
        public void setAllowFileAccessFromFileURLs(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "setAllowFileAccessFromFileURLs(" + s + ")");
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                final /* synthetic */ boolean val$allow = new SSAObj(s).getBoolean("allowFileAccess");
                
                @Override
                public void run() {
                    if (Build$VERSION.SDK_INT < 16) {
                        return;
                    }
                    try {
                        IronSourceWebView.this.getSettings().setAllowFileAccessFromFileURLs(this.val$allow);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        
        @JavascriptInterface
        public void setBackButtonState(String string) {
            Logger.i(IronSourceWebView.this.TAG, "setBackButtonState(" + string + ")");
            string = new SSAObj(string).getString("state");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setBackButtonState(string);
        }
        
        @JavascriptInterface
        public void setForceClose(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "setForceClose(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            final String string = ssaObj.getString("width");
            final String string2 = ssaObj.getString("height");
            IronSourceWebView.this.mHiddenForceCloseWidth = Integer.parseInt(string);
            IronSourceWebView.this.mHiddenForceCloseHeight = Integer.parseInt(string2);
            IronSourceWebView.this.mHiddenForceCloseLocation = ssaObj.getString("position");
        }
        
        @JavascriptInterface
        public void setMixedContentAlwaysAllow(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "setMixedContentAlwaysAllow(" + s + ")");
            IronSourceWebView.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (Build$VERSION.SDK_INT >= 21) {
                        IronSourceWebView.this.getSettings().setMixedContentMode(0);
                    }
                }
            });
        }
        
        @JavascriptInterface
        public void setOrientation(String string) {
            Logger.i(IronSourceWebView.this.TAG, "setOrientation(" + string + ")");
            string = new SSAObj(string).getString("orientation");
            IronSourceWebView.this.setOrientationState(string);
            final int applicationRotation = DeviceStatus.getApplicationRotation(IronSourceWebView.this.getCurrentActivityContext());
            if (IronSourceWebView.this.mChangeListener != null) {
                IronSourceWebView.this.mChangeListener.onOrientationChanged(string, applicationRotation);
            }
        }
        
        @JavascriptInterface
        public void setStoreSearchKeys(final String searchKeys) {
            Logger.i(IronSourceWebView.this.TAG, "setStoreSearchKeys(" + searchKeys + ")");
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSearchKeys(searchKeys);
        }
        
        @JavascriptInterface
        public void setUserData(String s) {
            Logger.i(IronSourceWebView.this.TAG, "setUserData(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            if (!ssaObj.containsKey("key")) {
                IronSourceWebView.this.responseBack(s, false, "key does not exist", null);
                return;
            }
            if (!ssaObj.containsKey("value")) {
                IronSourceWebView.this.responseBack(s, false, "value does not exist", null);
                return;
            }
            final String string = ssaObj.getString("key");
            final String string2 = ssaObj.getString("value");
            if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserData(string, string2)) {
                s = IronSourceWebView.this.extractSuccessFunctionToCall(s);
                s = IronSourceWebView.this.generateJSToInject(s, IronSourceWebView.this.parseToJson(string, string2, null, null, null, null, null, null, null, false));
                IronSourceWebView.this.injectJavascript(s);
                return;
            }
            IronSourceWebView.this.responseBack(s, false, "SetUserData failed writing to shared preferences", null);
        }
        
        @JavascriptInterface
        public void setUserUniqueId(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "setUserUniqueId(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            if (!ssaObj.containsKey("userUniqueId") || !ssaObj.containsKey("productType")) {
                IronSourceWebView.this.responseBack(s, false, "uniqueId or productType does not exist", null);
                return;
            }
            if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUniqueId(ssaObj.getString("userUniqueId"), ssaObj.getString("productType"))) {
                IronSourceWebView.this.responseBack(s, true, null, null);
                return;
            }
            IronSourceWebView.this.responseBack(s, false, "setUserUniqueId failed", null);
        }
        
        @JavascriptInterface
        public void setWebviewBackgroundColor(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "setWebviewBackgroundColor(" + s + ")");
            IronSourceWebView.this.setWebviewBackground(s);
        }
        
        @JavascriptInterface
        public void toggleUDIA(final String s) {
            Logger.i(IronSourceWebView.this.TAG, "toggleUDIA(" + s + ")");
            final SSAObj ssaObj = new SSAObj(s);
            if (!ssaObj.containsKey("toggle")) {
                IronSourceWebView.this.responseBack(s, false, "toggle key does not exist", null);
            }
            else {
                final int int1 = Integer.parseInt(ssaObj.getString("toggle"));
                if (int1 != 0) {
                    final String binaryString = Integer.toBinaryString(int1);
                    if (TextUtils.isEmpty((CharSequence)binaryString)) {
                        IronSourceWebView.this.responseBack(s, false, "fialed to convert toggle", null);
                        return;
                    }
                    if (binaryString.toCharArray()[3] == '0') {
                        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(true);
                        return;
                    }
                    IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(false);
                }
            }
        }
        
        class JSCallbackTask
        {
            void sendMessage(final boolean b, final String s, final SSAObj ssaObj) {
                String s2;
                if (b) {
                    s2 = IronSourceWebView.JSON_KEY_SUCCESS;
                }
                else {
                    s2 = IronSourceWebView.JSON_KEY_FAIL;
                }
                ssaObj.put(s2, s);
                IronSourceWebView.this.responseBack(ssaObj.toString(), b, null, null);
            }
            
            void sendMessage(final boolean b, final String s, final String s2) {
                final SSAObj ssaObj = new SSAObj();
                String s3;
                if (b) {
                    s3 = IronSourceWebView.JSON_KEY_SUCCESS;
                }
                else {
                    s3 = IronSourceWebView.JSON_KEY_FAIL;
                }
                ssaObj.put(s3, s);
                ssaObj.put("data", s2);
                IronSourceWebView.this.responseBack(ssaObj.toString(), b, null, null);
            }
        }
    }
    
    public enum State
    {
        Display, 
        Gone;
    }
    
    private class SupersonicWebViewTouchListener implements View$OnTouchListener
    {
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                Logger.i(IronSourceWebView.this.TAG, "X:" + (int)x + " Y:" + (int)y);
                final int deviceWidth = DeviceStatus.getDeviceWidth();
                final int deviceHeight = DeviceStatus.getDeviceHeight();
                Logger.i(IronSourceWebView.this.TAG, "Width:" + deviceWidth + " Height:" + deviceHeight);
                final int dpToPx = SDKUtils.dpToPx(IronSourceWebView.this.mHiddenForceCloseWidth);
                final int dpToPx2 = SDKUtils.dpToPx(IronSourceWebView.this.mHiddenForceCloseHeight);
                int n = 0;
                int n2 = 0;
                if ("top-right".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    n = deviceWidth - (int)x;
                    n2 = (int)y;
                }
                else if ("top-left".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    n = (int)x;
                    n2 = (int)y;
                }
                else if ("bottom-right".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    n = deviceWidth - (int)x;
                    n2 = deviceHeight - (int)y;
                }
                else if ("bottom-left".equalsIgnoreCase(IronSourceWebView.this.mHiddenForceCloseLocation)) {
                    n = (int)x;
                    n2 = deviceHeight - (int)y;
                }
                if (n <= dpToPx && n2 <= dpToPx2) {
                    IronSourceWebView.this.isRemoveCloseEventHandler = false;
                    if (IronSourceWebView.this.mCloseEventTimer != null) {
                        IronSourceWebView.this.mCloseEventTimer.cancel();
                    }
                    IronSourceWebView.this.mCloseEventTimer = new CountDownTimer(2000L, 500L) {
                        public void onFinish() {
                            Logger.i(IronSourceWebView.this.TAG, "Close Event Timer Finish");
                            if (IronSourceWebView.this.isRemoveCloseEventHandler) {
                                IronSourceWebView.this.isRemoveCloseEventHandler = false;
                                return;
                            }
                            IronSourceWebView.this.engageEnd("forceClose");
                        }
                        
                        public void onTick(final long n) {
                            Logger.i(IronSourceWebView.this.TAG, "Close Event Timer Tick " + n);
                        }
                    }.start();
                }
            }
            return false;
        }
    }
    
    private class ViewClient extends WebViewClient
    {
        public void onPageFinished(final WebView webView, final String s) {
            Logger.i("onPageFinished", s);
            if (s.contains("adUnit") || s.contains("index.html")) {
                IronSourceWebView.this.pageFinished();
            }
            super.onPageFinished(webView, s);
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            Logger.i("onPageStarted", s);
            super.onPageStarted(webView, s, bitmap);
        }
        
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            Logger.i("onReceivedError", s2 + " " + s);
            super.onReceivedError(webView, n, s, s2);
        }
        
        public WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
            Logger.i("shouldInterceptRequest", s);
            boolean b = false;
            while (true) {
                try {
                    if (new URL(s).getFile().contains("mraid.js")) {
                        b = true;
                    }
                    if (b) {
                        final String string = "file://" + IronSourceWebView.this.mCacheDirectory + File.separator + "mraid.js";
                        final File file = new File(string);
                        try {
                            new FileInputStream(file);
                            return new WebResourceResponse("text/javascript", "UTF-8", this.getClass().getResourceAsStream(string));
                        }
                        catch (FileNotFoundException ex) {}
                    }
                    return super.shouldInterceptRequest(webView, s);
                }
                catch (MalformedURLException ex2) {
                    continue;
                }
                break;
            }
        }
        
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            Logger.i("shouldOverrideUrlLoading", s);
            try {
                if (IronSourceWebView.this.handleSearchKeysURLs(s)) {
                    IronSourceWebView.this.interceptedUrlToStore();
                    return true;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return super.shouldOverrideUrlLoading(webView, s);
        }
    }
}
