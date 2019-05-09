// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.constants;

import com.ironsource.sdk.data.SSAEnums;

public class Constants
{
    static final String ACTION_BRAND_CONNECT_AD_COMPLETE = "com.supersonicads.sdk.android.actions.ACTION_BRAND_CONNECT_AD_COMPLETE";
    static final String ACTION_BRAND_CONNECT_NO_MORE_OFFERS = "com.supersonicads.sdk.android.actions.ACTION_BRAND_CONNECT_NO_MORE_OFFERS";
    static final String ACTIVITY_TYPE = "activity_type";
    static final String ACTIVITY_TYPE_BRAND_CONNECT = "activity_type_brand_connect";
    static final String ACTIVITY_TYPE_OFFER_WALL = "activity_type_offer_wall";
    public static final String BACKGROUND_TIMEOUT_BROADCAST_RECIVER = "com.supersonicads.sdk.android.BackgroundTimeout";
    public static final int CONTROLLER_DEBUG_MODE = 0;
    public static final String CONTROLLER_URL = "mobileSDKController/mobileController.html";
    static final boolean ENABLE_PORTRAIT = false;
    public static final String JAVASCRIPT_INTERFACE_NAME = "Android";
    static final String KEY_ACTIVITY_DATA_ACTION = "key_activity_data_action";
    static final String KEY_ACTIVITY_DATA_URL = "activity_data_url";
    public static final String MAIN_ACTIVITY = "main_activity";
    public static final String MOBILE_CONTROLLER_HTML = "mobileController.html";
    public static final String NATIVE_EXCEPTION_BASE_URL = "https://www.supersonicads.com/mobile/sdk5/log?method=";
    public static final String PLACEMENT_ID = "placementId";
    public static final String PREFERENCES_KEY_INIT_BRAND_CONNECT_APPLICATION_KEY = "preferences_key_init_brand_connect_application_key";
    public static final String PREFERENCES_KEY_INIT_BRAND_CONNECT_APPLICATION_USER_ID = "preferences_key_init_brand_connect_application_user_id";
    public static final String PREFERENCES_KEY_INIT_TIME = "preferences_key_init_time";
    public static final String PREFERENCES_KEY_MAIN_OR_WEBVIEW = "preferences_key_main_or_webview";
    public static final String PREFERENCES_KEY_REFRESH_INTERVAL = "preferences_key_refresh_interval";
    public static final String PREFERENCES_KEY_SETTINGS_IS_TABLET_FULL_SCREEN = "preferences_key_settings_is_tablet_full_screen";
    public static final String PREFERENCES_KEY_SETTINGS_REFRESH_INTERVAL = "preferences_key_settings_refresh_interval";
    public static final String PREFERENCES_NAME = "com.supersonicads.sdk.android";
    public static final String RESTORED_STATE = "state";
    public static final String SDK_VERSION = "5.58";
    public static final String STR_EMPTY = "";
    static final boolean VIDEO_TEXT_CLICKALBLE = false;
    public static final String WEB_VIEW_ACTIVITY = "web_view_activity";
    
    public class ControllerParameters
    {
        public static final int GLOBAL_RUNTIME = 200000;
        public static final int LOAD_ATTEMPS = 3;
        public static final int LOAD_RUNTIME = 50000;
        public static final int SECOND = 1000;
    }
    
    public class ErrorCodes
    {
        public static final String DownloadMobileController = "Download Mobile Controller";
        public static final String EVENT_NAME_DOES_NOT_EXIST = "eventName does not exist";
        public static final String FIALED_TO_CONVERT_GET_BY_FLAG = "fialed to convert getByFlag";
        public static final String FIALED_TO_CONVERT_TOGGLE = "fialed to convert toggle";
        public static final String FILE_NOT_EXIST_CODE = "1";
        public static final String FILE_NOT_EXIST_MSG = "File not exist";
        public static final String FOLDER_NOT_EXIST_CODE = "1";
        public static final String FOLDER_NOT_EXIST_MSG = "Folder not exist";
        public static final String GET_APPS_INSTALL_TIME = "100";
        public static final String GET_BY_FLAG_KEY_DOES_NOT_EXIST = "getByFlag key does not exist";
        public static final String GetCachedFilesMap = "Get Cached Files Map";
        public static final String GetCachedFilesMapTimeOut = "Get Cached Files Map Time Out";
        public static final String GetDeviceStatus = "Get Device Status";
        public static final String GetDeviceStatusTimeOut = "Get Device Status Time Out";
        public static final String InitIS = "Init IS";
        public static final String InitOW = "Init OW";
        public static final String InitRV = "Init RV";
        public static final String InitiatingController = "Initiating Controller";
        public static final String KEY_DOES_NOT_EXIST = "key does not exist";
        public static final String LoadingMobileController = "Loading Mobile Controller";
        public static final String NUM_OF_AD_UNITS_DO_NOT_EXIST = "Num Of Ad Units Do Not Exist";
        public static final String PATH_FILE_DOES_NOT_EXIST_ON_DISK = "path file does not exist on disk";
        public static final String PATH_KEY_DOES_NOT_EXIST = "path key does not exist";
        public static final String PRODUCT_TYPE_DOES_NOT_EXIST = "productType does not exist";
        public static final String SET_USER_UNIQUE_ID_FAILED = "setUserUniqueId failed";
        public static final String ShowOW = "Show OW";
        public static final String ShowOWCredits = "Show OW Credits";
        public static final String TOGGLE_KEY_DOES_NOT_EXIST = "toggle key does not exist";
        public static final String UNIQUE_ID_OR_PRODUCT_TYPE_DOES_NOT_EXIST = "uniqueId or productType does not exist";
        public static final String VALUE_DOES_NOT_EXIST = "value does not exist";
    }
    
    public class ForceClosePosition
    {
        public static final String BOTTOM_LEFT = "bottom-left";
        public static final String BOTTOM_RIGHT = "bottom-right";
        public static final int HEIGHT = 50;
        public static final String TOP_LEFT = "top-left";
        public static final String TOP_RIGHT = "top-right";
        public static final int WIDTH = 50;
    }
    
    public static class JSMethods
    {
        public static final String AD_CREDITED = "adCredited";
        public static final String AD_UNITS_READY = "adUnitsReady";
        public static final String ASSET_CACHED = "assetCached";
        public static final String ASSET_CACHED_FAILED = "assetCachedFailed";
        public static final String DELETE_FILE = "deleteFile";
        public static final String DELETE_FILE_FAILED = "deleteFileFailed";
        public static final String DELETE_FOLDER = "deleteFolder";
        public static final String DELETE_FOLDER_FAILED = "deleteFolderFailed";
        public static final String DEVICE_STATUS_CHANGED = "deviceStatusChanged";
        public static final String DISPLAY_WEBVIEW = "displayWebView";
        public static final String ENGAGE_END = "engageEnd";
        public static final String ENTER_BACKGROUND = "enterBackground";
        public static final String ENTER_FOREGROUND = "enterForeground";
        public static final String FORCE_SHOW_INTERSTITIAL = "forceShowInterstitial";
        public static final String GET_USER_CREDITS = "getUserCredits";
        public static final String GET_USER_DATA = "getUserData";
        public static final String INIT_CONTROLLER = "initController";
        public static final String INIT_INTERSTITIAL = "initInterstitial";
        public static final String INIT_OFFERWALL = "initOfferWall";
        public static final String INIT_REWARDED_VIDEO = "initRewardedVideo";
        public static final String INTERCEPTED_URL_TO_STORE = "interceptedUrlToStore";
        public static final String LOAD_INTERSTITIAL = "loadInterstitial";
        public static final String NATIVE_NAVIGATION_PRESSED = "nativeNavigationPressed";
        public static final String ON_ALL_WINDOWS_CLOSED = "onAdWindowsClosed";
        public static final String ON_CHECK_INSTALLED_APPS_FAIL = "onCheckInstalledAppsFail";
        public static final String ON_CHECK_INSTALLED_APPS_SUCCESS = "onCheckInstalledAppsSuccess";
        public static final String ON_GENERIC_FUNCTION_FAIL = "onGenericFunctionFail";
        public static final String ON_GENERIC_FUNCTION_SUCCESS = "onGenericFunctionSuccess";
        public static final String ON_GET_APPLICATION_INFO_FAIL = "onGetApplicationInfoFail";
        public static final String ON_GET_APPLICATION_INFO_SUCCESS = "onGetApplicationInfoSuccess";
        public static final String ON_GET_CACHED_FILES_MAP_FAIL = "onGetCachedFilesMapFail";
        public static final String ON_GET_CACHED_FILES_MAP_SUCCESS = "onGetCachedFilesMapSuccess";
        public static final String ON_GET_DEVICE_STATUS_FAIL = "onGetDeviceStatusFail";
        public static final String ON_GET_DEVICE_STATUS_SUCCESS = "onGetDeviceStatusSuccess";
        public static final String ON_GET_ORIENTATION_FAIL = "onGetOrientationFail";
        public static final String ON_GET_ORIENTATION_SUCCESS = "onGetOrientationSuccess";
        public static final String ON_GET_UDIA_FAIL = "onGetUDIAFail";
        public static final String ON_GET_UDIA_SUCCESS = "onGetUDIASuccess";
        public static final String ON_GET_USER_CREDITS_FAILED = "onGetUserCreditsFail";
        public static final String ON_GET_USER_UNIQUE_ID_FAIL = "onGetUserUniqueIdFail";
        public static final String ON_GET_USER_UNIQUE_ID_SUCCESS = "onGetUserUniqueIdSuccess";
        public static final String ON_INIT_INTERSTITIAL_FAIL = "onInitInterstitialFail";
        public static final String ON_INIT_INTERSTITIAL_SUCCESS = "onInitInterstitialSuccess";
        public static final String ON_INIT_OFFERWALL_FAIL = "onInitOfferWallFail";
        public static final String ON_INIT_OFFERWALL_SUCCESS = "onInitOfferWallSuccess";
        public static final String ON_INIT_REWARDED_VIDEO_FAIL = "onInitRewardedVideoFail";
        public static final String ON_INIT_REWARDED_VIDEO_SUCCESS = "onInitRewardedVideoSuccess";
        public static final String ON_INTERSTITIAL_AD_CLICKED = "onInterstitialAdClicked";
        public static final String ON_INTERSTITIAL_AVAILABILITY = "onInterstitialAvailability";
        public static final String ON_LOAD_INTERSTITIAL_FAIL = "onLoadInterstitialFail";
        public static final String ON_LOAD_INTERSTITIAL_SUCCESS = "onLoadInterstitialSuccess";
        public static final String ON_NATIVE_LIFE_CYCLE_EVENT = "onNativeLifeCycleEvent";
        public static final String ON_SHOW_INTERSTITIAL_FAIL = "onShowInterstitialFail";
        public static final String ON_SHOW_INTERSTITIAL_SUCCESS = "onShowInterstitialSuccess";
        public static final String ON_SHOW_OFFER_WALL_FAIL = "onShowOfferWallFail";
        public static final String ON_SHOW_OFFER_WALL_SUCCESS = "onShowOfferWallSuccess";
        public static final String ON_SHOW_REWARDED_VIDEO_FAIL = "onShowRewardedVideoFail";
        public static final String ON_SHOW_REWARDED_VIDEO_SUCCESS = "onShowRewardedVideoSuccess";
        public static final String ON_UDIA_FAIL = "onUDIAFail";
        public static final String ON_UDIA_SUCCESS = "onUDIASuccess";
        public static final String PAGE_FINISHED = "pageFinished";
        public static final String POST_AD_EVENT_NOTIFICATION_FAIL = "postAdEventNotificationFail";
        public static final String POST_AD_EVENT_NOTIFICATION_SUCCESS = "postAdEventNotificationSuccess";
        public static final String REDIRECT_TO_FILE = "redirectToFile";
        public static final String SAVE_FILE = "saveFile";
        public static final String SAVE_FILE_FAILED = "saveFileFailed";
        public static final String SHOW_INTERSTITIAL = "showInterstitial";
        public static final String SHOW_OFFER_WALL = "showOfferWall";
        public static final String SHOW_REWARDED_VIDEO = "showRewardedVideo";
        public static final String UPDATE_CONSENT_INFO = "updateConsentInfo";
        public static final String VIEWABLE_CHANGE = "viewableChange";
        public String failureCallbackName;
        public String methodName;
        public String successCallbackName;
        
        public static JSMethods getInitMethodByProduct(final SSAEnums.ProductType productType) {
            final JSMethods jsMethods = new JSMethods();
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                jsMethods.methodName = "initRewardedVideo";
                jsMethods.successCallbackName = "onInitRewardedVideoSuccess";
                jsMethods.failureCallbackName = "onInitRewardedVideoFail";
            }
            else {
                if (productType == SSAEnums.ProductType.Interstitial) {
                    jsMethods.methodName = "initInterstitial";
                    jsMethods.successCallbackName = "onInitInterstitialSuccess";
                    jsMethods.failureCallbackName = "onInitInterstitialFail";
                    return jsMethods;
                }
                if (productType == SSAEnums.ProductType.OfferWall) {
                    jsMethods.methodName = "initOfferWall";
                    jsMethods.successCallbackName = "onInitOfferWallSuccess";
                    jsMethods.failureCallbackName = "onInitOfferWallFail";
                    return jsMethods;
                }
            }
            return jsMethods;
        }
        
        public static JSMethods getShowMethodByProduct(final SSAEnums.ProductType productType) {
            final JSMethods jsMethods = new JSMethods();
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                jsMethods.methodName = "showRewardedVideo";
                jsMethods.successCallbackName = "onShowRewardedVideoSuccess";
                jsMethods.failureCallbackName = "onShowRewardedVideoFail";
            }
            else {
                if (productType == SSAEnums.ProductType.Interstitial) {
                    jsMethods.methodName = "showInterstitial";
                    jsMethods.successCallbackName = "onShowInterstitialSuccess";
                    jsMethods.failureCallbackName = "onShowInterstitialFail";
                    return jsMethods;
                }
                if (productType == SSAEnums.ProductType.OfferWall) {
                    jsMethods.methodName = "showOfferWall";
                    jsMethods.successCallbackName = "onShowOfferWallSuccess";
                    jsMethods.failureCallbackName = "onInitOfferWallFail";
                    return jsMethods;
                }
            }
            return jsMethods;
        }
    }
    
    public class ParametersKeys
    {
        public static final String ACTION = "action";
        public static final String ACTIVITY_THEME_TRANSLUCENT = "activityThemeTranslucent";
        public static final String ALLOW_FILE_ACCESS = "allowFileAccess";
        public static final String AVAILABLE = "available";
        public static final String COLOR = "color";
        public static final String CREDITS = "credits";
        public static final String DATA = "data";
        public static final String DEMAND_SOURCE_NAME = "demandSourceName";
        public static final String DISPLAY = "display";
        public static final String ERR_CODE = "errCode";
        public static final String ERR_MSG = "errMsg";
        public static final String EVENT_NAME = "eventName";
        public static final String EXTERNAL_BROWSER = "external_browser";
        public static final String EXTRA_DATA = "extData";
        public static final String FAILED = "failed";
        public static final String FILE = "file";
        public static final String FORCE_CLOSE = "forceClose";
        public static final String GET_BY_FLAG = "getByFlag";
        public static final String HEIGHT = "height";
        public static final String IMMERSIVE = "immersive";
        public static final String INCLUDE_SYSTEM_APPS = "systemApps";
        public static final String INTERSTITIAL = "Interstitial";
        public static final String IS_STANDALONE_VIEW = "standaloneView";
        public static final String IS_VIEWABLE = "isViewable";
        public static final String KEY = "key";
        public static final String LAST_UPDATE_TIME = "lastUpdateTime";
        public static final String LIFE_CYCLE_EVENT = "lifeCycleEvent";
        public static final String LOADED = "loaded";
        public static final String MAIN = "main";
        public static final String MAP_PATH = "path";
        public static final String METHOD = "method";
        public static final String NOTIFICATION_DEMAND_SOURCE_NAME = "dsName";
        public static final String OFFER_WALL = "OfferWall";
        public static final String ORIENTATION = "orientation";
        public static final String ORIENTATION_APPLICATION = "application";
        public static final String ORIENTATION_DEVICE = "device";
        public static final String ORIENTATION_LANDSCAPE = "landscape";
        public static final String ORIENTATION_NONE = "none";
        public static final String ORIENTATION_PORTRAIT = "portrait";
        public static final String ORIENTATION_SET_FLAG = "orientation_set_flag";
        public static final String PATH = "path";
        public static final String PERMISSION = "permission";
        public static final String POSITION = "position";
        public static final String PRODUCT_TYPE = "productType";
        public static final String READY = "ready";
        public static final String ROTATION_SET_FLAG = "rotation_set_flag";
        public static final String SEARCH_KEYS = "searchKeys";
        public static final String SECONDARY = "secondary";
        public static final String SECONDARY_CLOSE = "secondaryClose";
        public static final String STAGE = "stage";
        public static final String STATE = "state";
        public static final String STORE = "store";
        public static final String STORE_CLOSE = "store_close";
        public static final String TOGGLE = "toggle";
        public static final String TOTAL = "total";
        public static final String TRANSPARENT = "transparent";
        public static final String URL = "url";
        public static final String USER_UNIQUE_ID = "userUniqueId";
        public static final String USE_CLIENT_SIDE_CALLBACKS = "useClientSideCallbacks";
        public static final String VALUE = "value";
        public static final String VIDEO_STATUS = "status";
        public static final String VIDEO_STATUS_ENDED = "ended";
        public static final String VIDEO_STATUS_PAUSED = "paused";
        public static final String VIDEO_STATUS_PLAYING = "playing";
        public static final String VIDEO_STATUS_STARTED = "started";
        public static final String VIDEO_STATUS_STOPPED = "stopped";
        public static final String VIEW = "view";
        public static final String WEB_VIEW = "webview";
        public static final String WIDTH = "width";
    }
    
    public class RequestParameters
    {
        public static final String AID = "AID";
        public static final String AMPERSAND = "&";
        public static final String APPLICATION_KEY = "applicationKey";
        public static final String APPLICATION_USER_ID = "applicationUserId";
        public static final String APPLICATION_VERSION_NAME = "appVersion";
        public static final String APP_ORIENTATION = "appOrientation";
        public static final String BATTERY_LEVEL = "batteryLevel";
        public static final String CONNECTION_TYPE = "connectionType";
        public static final String CONTROLLER_CONFIG = "controllerConfig";
        public static final String DEBUG = "debug";
        public static final String DEMAND_SOURCE_NAME = "demandSourceName";
        public static final String DEVICE_API_LEVEL = "deviceApiLevel";
        public static final String DEVICE_IDS = "deviceIds";
        public static final String DEVICE_LANGUAGE = "deviceLanguage";
        public static final String DEVICE_MODEL = "deviceModel";
        public static final String DEVICE_OEM = "deviceOEM";
        public static final String DEVICE_OS = "deviceOs";
        public static final String DEVICE_OS_VERSION = "deviceOSVersion";
        public static final String DEVICE_SCREEN_SCALE = "deviceScreenScale";
        public static final String DEVICE_SCREEN_SIZE = "deviceScreenSize";
        public static final String DEVICE_VOLUME = "deviceVolume";
        public static final String DISK_FREE_SIZE = "diskFreeSize";
        public static final String DOMAIN = "domain";
        public static final String EQUAL = "=";
        public static final String FIRST_INSTALL_TIME = "firstInstallTime";
        public static final String GDPR_CONSENT_STATUS = "gdprConsentStatus";
        public static final String HEIGHT = "height";
        public static final String IMMERSIVE = "immersiveMode";
        public static final String INSTALLER_PACKAGE_NAME = "installerPackageName";
        public static final String IS_ROOT_DEVICE = "unLocked";
        public static final String IS_SECURED = "isSecured";
        public static final String LAST_UPDATE_TIME = "lastUpdateTime";
        public static final String LEFT_BRACKETS = "[";
        public static final String MOBILE_CARRIER = "mobileCarrier";
        public static final String NETWORK_MCC = "mcc";
        public static final String NETWORK_MNC = "mnc";
        public static final String PACKAGE_NAME = "bundleId";
        public static final String PHONE_TYPE = "phoneType";
        public static final String PROTOCOL = "protocol";
        public static final String RIGHT_BRACKETS = "]";
        public static final String SDK_VERSION = "SDKVersion";
        public static final String SESSION_DEPTH = "sessionDepth";
        public static final String SIM_OPERATOR = "simOperator";
        public static final String WEBVIEW_TYPE = "webviewType";
        public static final String WIDTH = "width";
        public static final String isLAT = "isLimitAdTrackingEnabled";
    }
}
