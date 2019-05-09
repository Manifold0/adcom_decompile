// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.support.annotation.Nullable;
import android.content.pm.ResolveInfo;
import java.util.UUID;
import android.text.TextUtils;
import com.facebook.FacebookSdk;
import android.content.Intent;
import com.facebook.login.DefaultAudience;
import android.content.Context;
import com.facebook.FacebookOperationCanceledException;
import android.os.Bundle;
import com.facebook.FacebookException;
import java.util.Iterator;
import android.net.Uri;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;
import java.util.List;

public final class NativeProtocol
{
    public static final String ACTION_APPINVITE_DIALOG = "com.facebook.platform.action.request.APPINVITES_DIALOG";
    public static final String ACTION_CAMERA_EFFECT = "com.facebook.platform.action.request.CAMERA_EFFECT";
    public static final String ACTION_FEED_DIALOG = "com.facebook.platform.action.request.FEED_DIALOG";
    public static final String ACTION_LIKE_DIALOG = "com.facebook.platform.action.request.LIKE_DIALOG";
    public static final String ACTION_MESSAGE_DIALOG = "com.facebook.platform.action.request.MESSAGE_DIALOG";
    public static final String ACTION_OGACTIONPUBLISH_DIALOG = "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    public static final String ACTION_OGMESSAGEPUBLISH_DIALOG = "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    public static final String AUDIENCE_EVERYONE = "everyone";
    public static final String AUDIENCE_FRIENDS = "friends";
    public static final String AUDIENCE_ME = "only_me";
    public static final String BRIDGE_ARG_ACTION_ID_STRING = "action_id";
    public static final String BRIDGE_ARG_APP_NAME_STRING = "app_name";
    public static final String BRIDGE_ARG_ERROR_BUNDLE = "error";
    public static final String BRIDGE_ARG_ERROR_CODE = "error_code";
    public static final String BRIDGE_ARG_ERROR_DESCRIPTION = "error_description";
    public static final String BRIDGE_ARG_ERROR_JSON = "error_json";
    public static final String BRIDGE_ARG_ERROR_SUBCODE = "error_subcode";
    public static final String BRIDGE_ARG_ERROR_TYPE = "error_type";
    private static final String CONTENT_SCHEME = "content://";
    public static final String ERROR_APPLICATION_ERROR = "ApplicationError";
    public static final String ERROR_NETWORK_ERROR = "NetworkError";
    public static final String ERROR_PERMISSION_DENIED = "PermissionDenied";
    public static final String ERROR_PROTOCOL_ERROR = "ProtocolError";
    public static final String ERROR_SERVICE_DISABLED = "ServiceDisabled";
    public static final String ERROR_UNKNOWN_ERROR = "UnknownError";
    public static final String ERROR_USER_CANCELED = "UserCanceled";
    public static final String EXTRA_ACCESS_TOKEN = "com.facebook.platform.extra.ACCESS_TOKEN";
    public static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
    public static final String EXTRA_APPLICATION_NAME = "com.facebook.platform.extra.APPLICATION_NAME";
    public static final String EXTRA_ARGS_PROFILE = "com.facebook.platform.extra.PROFILE";
    public static final String EXTRA_ARGS_PROFILE_FIRST_NAME = "com.facebook.platform.extra.PROFILE_FIRST_NAME";
    public static final String EXTRA_ARGS_PROFILE_LAST_NAME = "com.facebook.platform.extra.PROFILE_LAST_NAME";
    public static final String EXTRA_ARGS_PROFILE_LINK = "com.facebook.platform.extra.PROFILE_LINK";
    public static final String EXTRA_ARGS_PROFILE_MIDDLE_NAME = "com.facebook.platform.extra.PROFILE_MIDDLE_NAME";
    public static final String EXTRA_ARGS_PROFILE_NAME = "com.facebook.platform.extra.PROFILE_NAME";
    public static final String EXTRA_ARGS_PROFILE_USER_ID = "com.facebook.platform.extra.PROFILE_USER_ID";
    public static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
    public static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
    public static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
    public static final String EXTRA_GET_INSTALL_DATA_PACKAGE = "com.facebook.platform.extra.INSTALLDATA_PACKAGE";
    public static final String EXTRA_GRAPH_API_VERSION = "com.facebook.platform.extra.GRAPH_API_VERSION";
    public static final String EXTRA_LOGGER_REF = "com.facebook.platform.extra.LOGGER_REF";
    public static final String EXTRA_PERMISSIONS = "com.facebook.platform.extra.PERMISSIONS";
    public static final String EXTRA_PROTOCOL_ACTION = "com.facebook.platform.protocol.PROTOCOL_ACTION";
    public static final String EXTRA_PROTOCOL_BRIDGE_ARGS = "com.facebook.platform.protocol.BRIDGE_ARGS";
    public static final String EXTRA_PROTOCOL_CALL_ID = "com.facebook.platform.protocol.CALL_ID";
    public static final String EXTRA_PROTOCOL_METHOD_ARGS = "com.facebook.platform.protocol.METHOD_ARGS";
    public static final String EXTRA_PROTOCOL_METHOD_RESULTS = "com.facebook.platform.protocol.RESULT_ARGS";
    public static final String EXTRA_PROTOCOL_VERSION = "com.facebook.platform.protocol.PROTOCOL_VERSION";
    static final String EXTRA_PROTOCOL_VERSIONS = "com.facebook.platform.extra.PROTOCOL_VERSIONS";
    public static final String EXTRA_TOAST_DURATION_MS = "com.facebook.platform.extra.EXTRA_TOAST_DURATION_MS";
    public static final String EXTRA_USER_ID = "com.facebook.platform.extra.USER_ID";
    private static final String FACEBOOK_PROXY_AUTH_ACTIVITY = "com.facebook.katana.ProxyAuth";
    public static final String FACEBOOK_PROXY_AUTH_APP_ID_KEY = "client_id";
    public static final String FACEBOOK_PROXY_AUTH_E2E_KEY = "e2e";
    public static final String FACEBOOK_PROXY_AUTH_PERMISSIONS_KEY = "scope";
    public static final String FACEBOOK_SDK_VERSION_KEY = "facebook_sdk_version";
    private static final String FACEBOOK_TOKEN_REFRESH_ACTIVITY = "com.facebook.katana.platform.TokenRefreshService";
    public static final String IMAGE_URL_KEY = "url";
    public static final String IMAGE_USER_GENERATED_KEY = "user_generated";
    static final String INTENT_ACTION_PLATFORM_ACTIVITY = "com.facebook.platform.PLATFORM_ACTIVITY";
    static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
    private static final List<Integer> KNOWN_PROTOCOL_VERSIONS;
    public static final int MESSAGE_GET_ACCESS_TOKEN_REPLY = 65537;
    public static final int MESSAGE_GET_ACCESS_TOKEN_REQUEST = 65536;
    public static final int MESSAGE_GET_AK_SEAMLESS_TOKEN_REPLY = 65545;
    public static final int MESSAGE_GET_AK_SEAMLESS_TOKEN_REQUEST = 65544;
    public static final int MESSAGE_GET_INSTALL_DATA_REPLY = 65541;
    public static final int MESSAGE_GET_INSTALL_DATA_REQUEST = 65540;
    public static final int MESSAGE_GET_LIKE_STATUS_REPLY = 65543;
    public static final int MESSAGE_GET_LIKE_STATUS_REQUEST = 65542;
    public static final int MESSAGE_GET_LOGIN_STATUS_REPLY = 65547;
    public static final int MESSAGE_GET_LOGIN_STATUS_REQUEST = 65546;
    static final int MESSAGE_GET_PROTOCOL_VERSIONS_REPLY = 65539;
    static final int MESSAGE_GET_PROTOCOL_VERSIONS_REQUEST = 65538;
    public static final int NO_PROTOCOL_AVAILABLE = -1;
    public static final String OPEN_GRAPH_CREATE_OBJECT_KEY = "fbsdk:create_object";
    private static final String PLATFORM_PROVIDER = ".provider.PlatformProvider";
    private static final String PLATFORM_PROVIDER_VERSIONS = ".provider.PlatformProvider/versions";
    private static final String PLATFORM_PROVIDER_VERSION_COLUMN = "version";
    public static final int PROTOCOL_VERSION_20121101 = 20121101;
    public static final int PROTOCOL_VERSION_20130502 = 20130502;
    public static final int PROTOCOL_VERSION_20130618 = 20130618;
    public static final int PROTOCOL_VERSION_20131107 = 20131107;
    public static final int PROTOCOL_VERSION_20140204 = 20140204;
    public static final int PROTOCOL_VERSION_20140324 = 20140324;
    public static final int PROTOCOL_VERSION_20140701 = 20140701;
    public static final int PROTOCOL_VERSION_20141001 = 20141001;
    public static final int PROTOCOL_VERSION_20141028 = 20141028;
    public static final int PROTOCOL_VERSION_20141107 = 20141107;
    public static final int PROTOCOL_VERSION_20141218 = 20141218;
    public static final int PROTOCOL_VERSION_20160327 = 20160327;
    public static final int PROTOCOL_VERSION_20170213 = 20170213;
    public static final int PROTOCOL_VERSION_20170411 = 20170411;
    public static final int PROTOCOL_VERSION_20170417 = 20170417;
    public static final int PROTOCOL_VERSION_20171115 = 20171115;
    public static final String RESULT_ARGS_ACCESS_TOKEN = "access_token";
    public static final String RESULT_ARGS_DIALOG_COMPLETE_KEY = "didComplete";
    public static final String RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY = "completionGesture";
    public static final String RESULT_ARGS_EXPIRES_SECONDS_SINCE_EPOCH = "expires_seconds_since_epoch";
    public static final String RESULT_ARGS_PERMISSIONS = "permissions";
    public static final String RESULT_ARGS_SIGNED_REQUEST = "signed request";
    public static final String STATUS_ERROR_CODE = "com.facebook.platform.status.ERROR_CODE";
    public static final String STATUS_ERROR_DESCRIPTION = "com.facebook.platform.status.ERROR_DESCRIPTION";
    public static final String STATUS_ERROR_JSON = "com.facebook.platform.status.ERROR_JSON";
    public static final String STATUS_ERROR_SUBCODE = "com.facebook.platform.status.ERROR_SUBCODE";
    public static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
    private static final String TAG;
    public static final String WEB_DIALOG_ACTION = "action";
    public static final String WEB_DIALOG_IS_FALLBACK = "is_fallback";
    public static final String WEB_DIALOG_PARAMS = "params";
    public static final String WEB_DIALOG_URL = "url";
    private static final Map<String, List<NativeAppInfo>> actionToAppInfoMap;
    private static final List<NativeAppInfo> effectCameraAppInfoList;
    private static final List<NativeAppInfo> facebookAppInfoList;
    private static final AtomicBoolean protocolVersionsAsyncUpdating;
    
    static {
        TAG = NativeProtocol.class.getName();
        facebookAppInfoList = buildFacebookAppList();
        effectCameraAppInfoList = buildEffectCameraAppInfoList();
        actionToAppInfoMap = buildActionToAppInfoMap();
        protocolVersionsAsyncUpdating = new AtomicBoolean(false);
        KNOWN_PROTOCOL_VERSIONS = Arrays.asList(20170417, 20160327, 20141218, 20141107, 20141028, 20141001, 20140701, 20140324, 20140204, 20131107, 20130618, 20130502, 20121101);
    }
    
    private static Map<String, List<NativeAppInfo>> buildActionToAppInfoMap() {
        final HashMap<String, ArrayList<MessengerAppInfo>> hashMap = (HashMap<String, ArrayList<MessengerAppInfo>>)new HashMap<String, List<MessengerAppInfo>>();
        final ArrayList<MessengerAppInfo> list = new ArrayList<MessengerAppInfo>();
        list.add(new MessengerAppInfo());
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", (List<MessengerAppInfo>)NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.APPINVITES_DIALOG", NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", list);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", list);
        hashMap.put("com.facebook.platform.action.request.CAMERA_EFFECT", NativeProtocol.effectCameraAppInfoList);
        return (Map<String, List<NativeAppInfo>>)hashMap;
    }
    
    private static List<NativeAppInfo> buildEffectCameraAppInfoList() {
        final ArrayList<NativeAppInfo> list = new ArrayList<NativeAppInfo>(buildFacebookAppList());
        list.add(0, (NativeAppInfo)new EffectTestAppInfo());
        return list;
    }
    
    private static List<NativeAppInfo> buildFacebookAppList() {
        final ArrayList<WakizashiAppInfo> list = (ArrayList<WakizashiAppInfo>)new ArrayList<NativeAppInfo>();
        list.add(new KatanaAppInfo());
        list.add(new WakizashiAppInfo());
        return (List<NativeAppInfo>)list;
    }
    
    private static Uri buildPlatformProviderVersionURI(final NativeAppInfo nativeAppInfo) {
        return Uri.parse("content://" + nativeAppInfo.getPackage() + ".provider.PlatformProvider/versions");
    }
    
    public static int computeLatestAvailableVersionFromVersionSpec(final TreeSet<Integer> set, int min, final int[] array) {
        int n = array.length - 1;
        final Iterator<Integer> descendingIterator = set.descendingIterator();
        int n2 = -1;
        while (descendingIterator.hasNext()) {
            final int intValue = descendingIterator.next();
            final int max = Math.max(n2, intValue);
            int n3;
            for (n3 = n; n3 >= 0 && array[n3] > intValue; --n3) {}
            if (n3 < 0) {
                break;
            }
            n2 = max;
            n = n3;
            if (array[n3] == intValue) {
                if (n3 % 2 == 0) {
                    min = Math.min(max, min);
                }
                else {
                    min = -1;
                }
                return min;
            }
        }
        return -1;
    }
    
    public static Bundle createBundleForException(final FacebookException ex) {
        Bundle bundle;
        if (ex == null) {
            bundle = null;
        }
        else {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("error_description", ex.toString());
            bundle = bundle2;
            if (ex instanceof FacebookOperationCanceledException) {
                bundle2.putString("error_type", "UserCanceled");
                return bundle2;
            }
        }
        return bundle;
    }
    
    public static Intent createFacebookLiteIntent(final Context context, final String s, final Collection<String> collection, final String s2, final boolean b, final boolean b2, final DefaultAudience defaultAudience, final String s3) {
        final FBLiteAppInfo fbLiteAppInfo = new FBLiteAppInfo();
        return validateActivityIntent(context, createNativeAppIntent((NativeAppInfo)fbLiteAppInfo, s, collection, s2, b, b2, defaultAudience, s3), (NativeAppInfo)fbLiteAppInfo);
    }
    
    private static Intent createNativeAppIntent(final NativeAppInfo nativeAppInfo, final String s, final Collection<String> collection, final String s2, final boolean b, final boolean b2, final DefaultAudience defaultAudience, final String s3) {
        final String loginActivity = nativeAppInfo.getLoginActivity();
        if (loginActivity == null) {
            return null;
        }
        final Intent putExtra = new Intent().setClassName(nativeAppInfo.getPackage(), loginActivity).putExtra("client_id", s);
        putExtra.putExtra("facebook_sdk_version", FacebookSdk.getSdkVersion());
        if (!Utility.isNullOrEmpty((Collection<Object>)collection)) {
            putExtra.putExtra("scope", TextUtils.join((CharSequence)",", (Iterable)collection));
        }
        if (!Utility.isNullOrEmpty(s2)) {
            putExtra.putExtra("e2e", s2);
        }
        putExtra.putExtra("state", s3);
        putExtra.putExtra("response_type", "token,signed_request");
        putExtra.putExtra("return_scopes", "true");
        if (b2) {
            putExtra.putExtra("default_audience", defaultAudience.getNativeProtocolAudience());
        }
        putExtra.putExtra("legacy_override", FacebookSdk.getGraphApiVersion());
        putExtra.putExtra("auth_type", "rerequest");
        return putExtra;
    }
    
    public static Intent createPlatformActivityIntent(final Context context, final String s, final String s2, final ProtocolVersionQueryResult protocolVersionQueryResult, final Bundle bundle) {
        if (protocolVersionQueryResult == null) {
            return null;
        }
        final NativeAppInfo access$700 = protocolVersionQueryResult.nativeAppInfo;
        if (access$700 == null) {
            return null;
        }
        final Intent validateActivityIntent = validateActivityIntent(context, new Intent().setAction("com.facebook.platform.PLATFORM_ACTIVITY").setPackage(access$700.getPackage()).addCategory("android.intent.category.DEFAULT"), access$700);
        if (validateActivityIntent == null) {
            return null;
        }
        setupProtocolRequestIntent(validateActivityIntent, s, s2, protocolVersionQueryResult.protocolVersion, bundle);
        return validateActivityIntent;
    }
    
    public static Intent createPlatformServiceIntent(final Context context) {
        for (final NativeAppInfo nativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent validateServiceIntent = validateServiceIntent(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(nativeAppInfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeAppInfo);
            if (validateServiceIntent != null) {
                return validateServiceIntent;
            }
        }
        return null;
    }
    
    public static Intent createProtocolResultIntent(Intent intent, final Bundle bundle, final FacebookException ex) {
        final UUID callIdFromIntent = getCallIdFromIntent(intent);
        if (callIdFromIntent == null) {
            intent = null;
        }
        else {
            final Intent intent2 = new Intent();
            intent2.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", getProtocolVersionFromIntent(intent));
            final Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", callIdFromIntent.toString());
            if (ex != null) {
                bundle2.putBundle("error", createBundleForException(ex));
            }
            intent2.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            intent = intent2;
            if (bundle != null) {
                intent2.putExtra("com.facebook.platform.protocol.RESULT_ARGS", bundle);
                return intent2;
            }
        }
        return intent;
    }
    
    public static Intent createProxyAuthIntent(final Context context, final String s, final Collection<String> collection, final String s2, final boolean b, final boolean b2, final DefaultAudience defaultAudience, final String s3) {
        for (final NativeAppInfo nativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent validateActivityIntent = validateActivityIntent(context, createNativeAppIntent(nativeAppInfo, s, collection, s2, b, b2, defaultAudience, s3), nativeAppInfo);
            if (validateActivityIntent != null) {
                return validateActivityIntent;
            }
        }
        return null;
    }
    
    public static Intent createTokenRefreshIntent(final Context context) {
        for (final NativeAppInfo nativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent validateServiceIntent = validateServiceIntent(context, new Intent().setClassName(nativeAppInfo.getPackage(), "com.facebook.katana.platform.TokenRefreshService"), nativeAppInfo);
            if (validateServiceIntent != null) {
                return validateServiceIntent;
            }
        }
        return null;
    }
    
    private static TreeSet<Integer> fetchAllAvailableProtocolVersionsForAppInfo(final NativeAppInfo p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/TreeSet.<init>:()V
        //     7: astore          5
        //     9: invokestatic    com/facebook/FacebookSdk.getApplicationContext:()Landroid/content/Context;
        //    12: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    15: astore          6
        //    17: aload_0        
        //    18: invokestatic    com/facebook/internal/NativeProtocol.buildPlatformProviderVersionURI:(Lcom/facebook/internal/NativeProtocol$NativeAppInfo;)Landroid/net/Uri;
        //    21: astore          7
        //    23: aconst_null    
        //    24: astore_2       
        //    25: aconst_null    
        //    26: astore_3       
        //    27: aload_3        
        //    28: astore_1       
        //    29: invokestatic    com/facebook/FacebookSdk.getApplicationContext:()Landroid/content/Context;
        //    32: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    35: astore          4
        //    37: aload_3        
        //    38: astore_1       
        //    39: new             Ljava/lang/StringBuilder;
        //    42: dup            
        //    43: invokespecial   java/lang/StringBuilder.<init>:()V
        //    46: aload_0        
        //    47: invokevirtual   com/facebook/internal/NativeProtocol$NativeAppInfo.getPackage:()Ljava/lang/String;
        //    50: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    53: ldc             ".provider.PlatformProvider"
        //    55: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    58: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    61: astore          8
        //    63: aconst_null    
        //    64: astore_0       
        //    65: aload_3        
        //    66: astore_1       
        //    67: aload           4
        //    69: aload           8
        //    71: iconst_0       
        //    72: invokevirtual   android/content/pm/PackageManager.resolveContentProvider:(Ljava/lang/String;I)Landroid/content/pm/ProviderInfo;
        //    75: astore          4
        //    77: aload           4
        //    79: astore_0       
        //    80: aload_0        
        //    81: ifnull          204
        //    84: aload_3        
        //    85: astore_1       
        //    86: aload           6
        //    88: aload           7
        //    90: iconst_1       
        //    91: anewarray       Ljava/lang/String;
        //    94: dup            
        //    95: iconst_0       
        //    96: ldc_w           "version"
        //    99: aastore        
        //   100: aconst_null    
        //   101: aconst_null    
        //   102: aconst_null    
        //   103: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   106: astore_0       
        //   107: aload_0        
        //   108: astore_2       
        //   109: aload_0        
        //   110: ifnull          204
        //   113: aload_0        
        //   114: astore_1       
        //   115: aload_0        
        //   116: astore_2       
        //   117: aload_0        
        //   118: invokeinterface android/database/Cursor.moveToNext:()Z
        //   123: ifeq            204
        //   126: aload_0        
        //   127: astore_1       
        //   128: aload           5
        //   130: aload_0        
        //   131: aload_0        
        //   132: ldc_w           "version"
        //   135: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   140: invokeinterface android/database/Cursor.getInt:(I)I
        //   145: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   148: invokevirtual   java/util/TreeSet.add:(Ljava/lang/Object;)Z
        //   151: pop            
        //   152: goto            113
        //   155: astore_0       
        //   156: aload_1        
        //   157: ifnull          166
        //   160: aload_1        
        //   161: invokeinterface android/database/Cursor.close:()V
        //   166: aload_0        
        //   167: athrow         
        //   168: astore          4
        //   170: aload_3        
        //   171: astore_1       
        //   172: getstatic       com/facebook/internal/NativeProtocol.TAG:Ljava/lang/String;
        //   175: ldc_w           "Failed to query content resolver."
        //   178: aload           4
        //   180: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   183: pop            
        //   184: goto            80
        //   187: aload_3        
        //   188: astore_1       
        //   189: getstatic       com/facebook/internal/NativeProtocol.TAG:Ljava/lang/String;
        //   192: ldc_w           "Failed to query content resolver."
        //   195: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   198: pop            
        //   199: aconst_null    
        //   200: astore_0       
        //   201: goto            107
        //   204: aload_2        
        //   205: ifnull          214
        //   208: aload_2        
        //   209: invokeinterface android/database/Cursor.close:()V
        //   214: aload           5
        //   216: areturn        
        //   217: astore_0       
        //   218: goto            187
        //   221: astore_0       
        //   222: goto            187
        //   225: astore_0       
        //   226: goto            187
        //    Signature:
        //  (Lcom/facebook/internal/NativeProtocol$NativeAppInfo;)Ljava/util/TreeSet<Ljava/lang/Integer;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  29     37     155    168    Any
        //  39     63     155    168    Any
        //  67     77     168    187    Ljava/lang/RuntimeException;
        //  67     77     155    168    Any
        //  86     107    225    229    Ljava/lang/NullPointerException;
        //  86     107    217    221    Ljava/lang/SecurityException;
        //  86     107    221    225    Ljava/lang/IllegalArgumentException;
        //  86     107    155    168    Any
        //  117    126    155    168    Any
        //  128    152    155    168    Any
        //  172    184    155    168    Any
        //  189    199    155    168    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 119, Size: 119
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
    
    public static Bundle getBridgeArgumentsFromIntent(final Intent intent) {
        if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
            return null;
        }
        return intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
    }
    
    public static UUID getCallIdFromIntent(final Intent intent) {
        if (intent != null) {
            final int protocolVersionFromIntent = getProtocolVersionFromIntent(intent);
            final String s = null;
            Label_0051: {
                if (!isVersionCompatibleWithBucketedIntent(protocolVersionFromIntent)) {
                    break Label_0051;
                }
                final Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
                String s2 = s;
                if (bundleExtra != null) {
                    s2 = bundleExtra.getString("action_id");
                }
                while (true) {
                    if (s2 == null) {
                        return null;
                    }
                    try {
                        return UUID.fromString(s2);
                        s2 = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        return null;
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    public static Bundle getErrorDataFromResultIntent(final Intent intent) {
        if (!isErrorResult(intent)) {
            return null;
        }
        final Bundle bridgeArgumentsFromIntent = getBridgeArgumentsFromIntent(intent);
        if (bridgeArgumentsFromIntent != null) {
            return bridgeArgumentsFromIntent.getBundle("error");
        }
        return intent.getExtras();
    }
    
    public static FacebookException getExceptionFromErrorData(final Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String s;
        if ((s = bundle.getString("error_type")) == null) {
            s = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String s2;
        if ((s2 = bundle.getString("error_description")) == null) {
            s2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (s != null && s.equalsIgnoreCase("UserCanceled")) {
            return new FacebookOperationCanceledException(s2);
        }
        return new FacebookException(s2);
    }
    
    public static ProtocolVersionQueryResult getLatestAvailableProtocolVersionForAction(final String s, final int[] array) {
        return getLatestAvailableProtocolVersionForAppInfoList(NativeProtocol.actionToAppInfoMap.get(s), array);
    }
    
    private static ProtocolVersionQueryResult getLatestAvailableProtocolVersionForAppInfoList(final List<NativeAppInfo> list, final int[] array) {
        updateAllAvailableProtocolVersionsAsync();
        if (list == null) {
            return ProtocolVersionQueryResult.createEmpty();
        }
        for (final NativeAppInfo nativeAppInfo : list) {
            final int computeLatestAvailableVersionFromVersionSpec = computeLatestAvailableVersionFromVersionSpec(nativeAppInfo.getAvailableVersions(), getLatestKnownVersion(), array);
            if (computeLatestAvailableVersionFromVersionSpec != -1) {
                return ProtocolVersionQueryResult.create(nativeAppInfo, computeLatestAvailableVersionFromVersionSpec);
            }
        }
        return ProtocolVersionQueryResult.createEmpty();
    }
    
    public static int getLatestAvailableProtocolVersionForService(final int n) {
        return getLatestAvailableProtocolVersionForAppInfoList(NativeProtocol.facebookAppInfoList, new int[] { n }).getProtocolVersion();
    }
    
    public static final int getLatestKnownVersion() {
        return NativeProtocol.KNOWN_PROTOCOL_VERSIONS.get(0);
    }
    
    public static Bundle getMethodArgumentsFromIntent(final Intent intent) {
        if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
            return intent.getExtras();
        }
        return intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
    }
    
    public static int getProtocolVersionFromIntent(final Intent intent) {
        return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
    }
    
    public static Bundle getSuccessResultsFromIntent(final Intent intent) {
        final int protocolVersionFromIntent = getProtocolVersionFromIntent(intent);
        final Bundle extras = intent.getExtras();
        if (!isVersionCompatibleWithBucketedIntent(protocolVersionFromIntent) || extras == null) {
            return extras;
        }
        return extras.getBundle("com.facebook.platform.protocol.RESULT_ARGS");
    }
    
    public static boolean isErrorResult(final Intent intent) {
        final Bundle bridgeArgumentsFromIntent = getBridgeArgumentsFromIntent(intent);
        if (bridgeArgumentsFromIntent != null) {
            return bridgeArgumentsFromIntent.containsKey("error");
        }
        return intent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
    }
    
    public static boolean isVersionCompatibleWithBucketedIntent(final int n) {
        return NativeProtocol.KNOWN_PROTOCOL_VERSIONS.contains(n) && n >= 20140701;
    }
    
    public static void setupProtocolRequestIntent(final Intent intent, final String s, final String s2, final int n, final Bundle bundle) {
        final String applicationId = FacebookSdk.getApplicationId();
        final String applicationName = FacebookSdk.getApplicationName();
        intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", n).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", s2).putExtra("com.facebook.platform.extra.APPLICATION_ID", applicationId);
        if (isVersionCompatibleWithBucketedIntent(n)) {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", s);
            Utility.putNonEmptyString(bundle2, "app_name", applicationName);
            intent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            Bundle bundle3;
            if (bundle == null) {
                bundle3 = new Bundle();
            }
            else {
                bundle3 = bundle;
            }
            intent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", bundle3);
            return;
        }
        intent.putExtra("com.facebook.platform.protocol.CALL_ID", s);
        if (!Utility.isNullOrEmpty(applicationName)) {
            intent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", applicationName);
        }
        intent.putExtras(bundle);
    }
    
    public static void updateAllAvailableProtocolVersionsAsync() {
        if (!NativeProtocol.protocolVersionsAsyncUpdating.compareAndSet(false, true)) {
            return;
        }
        FacebookSdk.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Iterator<NativeAppInfo> iterator = NativeProtocol.facebookAppInfoList.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().fetchAvailableVersions(true);
                    }
                }
                finally {
                    NativeProtocol.protocolVersionsAsyncUpdating.set(false);
                }
                NativeProtocol.protocolVersionsAsyncUpdating.set(false);
            }
        });
    }
    
    static Intent validateActivityIntent(final Context context, Intent intent, final NativeAppInfo nativeAppInfo) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null) {
                return null;
            }
            if (!FacebookSignatureValidator.validateSignature(context, resolveActivity.activityInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
    
    static Intent validateServiceIntent(final Context context, Intent intent, final NativeAppInfo nativeAppInfo) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null) {
                return null;
            }
            if (!FacebookSignatureValidator.validateSignature(context, resolveService.serviceInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
    
    private static class EffectTestAppInfo extends NativeAppInfo
    {
        static final String EFFECT_TEST_APP_PACKAGE = "com.facebook.arstudio.player";
        
        @Override
        protected String getLoginActivity() {
            return null;
        }
        
        @Override
        protected String getPackage() {
            return "com.facebook.arstudio.player";
        }
    }
    
    private static class FBLiteAppInfo extends NativeAppInfo
    {
        static final String FACEBOOK_LITE_ACTIVITY = "com.facebook.lite.platform.LoginGDPDialogActivity";
        static final String FBLITE_PACKAGE = "com.facebook.lite";
        
        @Override
        protected String getLoginActivity() {
            return "com.facebook.lite.platform.LoginGDPDialogActivity";
        }
        
        @Override
        protected String getPackage() {
            return "com.facebook.lite";
        }
    }
    
    private static class KatanaAppInfo extends NativeAppInfo
    {
        static final String KATANA_PACKAGE = "com.facebook.katana";
        
        @Override
        protected String getLoginActivity() {
            return "com.facebook.katana.ProxyAuth";
        }
        
        @Override
        protected String getPackage() {
            return "com.facebook.katana";
        }
    }
    
    private static class MessengerAppInfo extends NativeAppInfo
    {
        static final String MESSENGER_PACKAGE = "com.facebook.orca";
        
        @Override
        protected String getLoginActivity() {
            return null;
        }
        
        @Override
        protected String getPackage() {
            return "com.facebook.orca";
        }
    }
    
    private abstract static class NativeAppInfo
    {
        private TreeSet<Integer> availableVersions;
        
        private void fetchAvailableVersions(final boolean b) {
            // monitorenter(this)
            Label_0013: {
                if (b) {
                    break Label_0013;
                }
                try {
                    if (this.availableVersions == null) {
                        this.availableVersions = fetchAllAvailableProtocolVersionsForAppInfo(this);
                    }
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        
        public TreeSet<Integer> getAvailableVersions() {
            if (this.availableVersions == null) {
                this.fetchAvailableVersions(false);
            }
            return this.availableVersions;
        }
        
        protected abstract String getLoginActivity();
        
        protected abstract String getPackage();
    }
    
    public static class ProtocolVersionQueryResult
    {
        private NativeAppInfo nativeAppInfo;
        private int protocolVersion;
        
        private ProtocolVersionQueryResult() {
        }
        
        public static ProtocolVersionQueryResult create(final NativeAppInfo nativeAppInfo, final int protocolVersion) {
            final ProtocolVersionQueryResult protocolVersionQueryResult = new ProtocolVersionQueryResult();
            protocolVersionQueryResult.nativeAppInfo = nativeAppInfo;
            protocolVersionQueryResult.protocolVersion = protocolVersion;
            return protocolVersionQueryResult;
        }
        
        public static ProtocolVersionQueryResult createEmpty() {
            final ProtocolVersionQueryResult protocolVersionQueryResult = new ProtocolVersionQueryResult();
            protocolVersionQueryResult.protocolVersion = -1;
            return protocolVersionQueryResult;
        }
        
        @Nullable
        public NativeAppInfo getAppInfo() {
            return this.nativeAppInfo;
        }
        
        public int getProtocolVersion() {
            return this.protocolVersion;
        }
    }
    
    private static class WakizashiAppInfo extends NativeAppInfo
    {
        static final String WAKIZASHI_PACKAGE = "com.facebook.wakizashi";
        
        @Override
        protected String getLoginActivity() {
            return "com.facebook.katana.ProxyAuth";
        }
        
        @Override
        protected String getPackage() {
            return "com.facebook.wakizashi";
        }
    }
}
