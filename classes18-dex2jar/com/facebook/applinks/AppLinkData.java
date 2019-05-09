// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.applinks;

import java.util.Iterator;
import android.os.Parcelable;
import org.json.JSONArray;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.FacebookException;
import org.json.JSONException;
import android.util.Log;
import android.content.Intent;
import com.facebook.internal.Validate;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import org.json.JSONObject;
import android.os.Bundle;

public class AppLinkData
{
    private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
    private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
    private static final String APPLINK_VERSION_KEY = "version";
    public static final String ARGUMENTS_EXTRAS_KEY = "extras";
    public static final String ARGUMENTS_NATIVE_CLASS_KEY = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String ARGUMENTS_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
    public static final String ARGUMENTS_REFERER_DATA_KEY = "referer_data";
    public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    private static final String BRIDGE_ARGS_METHOD_KEY = "method";
    private static final String BUNDLE_AL_APPLINK_DATA_KEY = "al_applink_data";
    static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
    private static final String DEFERRED_APP_LINK_ARGS_FIELD = "applink_args";
    private static final String DEFERRED_APP_LINK_CLASS_FIELD = "applink_class";
    private static final String DEFERRED_APP_LINK_CLICK_TIME_FIELD = "click_time";
    private static final String DEFERRED_APP_LINK_EVENT = "DEFERRED_APP_LINK";
    private static final String DEFERRED_APP_LINK_PATH = "%s/activities";
    private static final String DEFERRED_APP_LINK_URL_FIELD = "applink_url";
    private static final String EXTRAS_DEEPLINK_CONTEXT_KEY = "deeplink_context";
    private static final String METHOD_ARGS_REF_KEY = "ref";
    private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
    private static final String PROMOTION_CODE_KEY = "promo_code";
    private static final String REFERER_DATA_REF_KEY = "fb_ref";
    private static final String TAG;
    private Bundle argumentBundle;
    private JSONObject arguments;
    private String promotionCode;
    private String ref;
    private Uri targetUri;
    
    static {
        TAG = AppLinkData.class.getCanonicalName();
    }
    
    private AppLinkData() {
    }
    
    public static AppLinkData createFromActivity(final Activity activity) {
        Validate.notNull((Object)activity, "activity");
        final Intent intent = activity.getIntent();
        AppLinkData appLinkData;
        if (intent == null) {
            appLinkData = null;
        }
        else {
            AppLinkData appLinkData2;
            if ((appLinkData2 = createFromAlApplinkData(intent)) == null) {
                appLinkData2 = createFromJson(intent.getStringExtra("com.facebook.platform.APPLINK_ARGS"));
            }
            if ((appLinkData = appLinkData2) == null) {
                return createFromUri(intent.getData());
            }
        }
        return appLinkData;
    }
    
    public static AppLinkData createFromAlApplinkData(final Intent intent) {
        AppLinkData appLinkData;
        if (intent == null) {
            appLinkData = null;
        }
        else {
            final Bundle bundleExtra = intent.getBundleExtra("al_applink_data");
            if (bundleExtra == null) {
                return null;
            }
            final AppLinkData appLinkData2 = new AppLinkData();
            appLinkData2.targetUri = intent.getData();
            if (appLinkData2.targetUri == null) {
                final String string = bundleExtra.getString("target_url");
                if (string != null) {
                    appLinkData2.targetUri = Uri.parse(string);
                }
            }
            appLinkData2.argumentBundle = bundleExtra;
            appLinkData2.arguments = null;
            final Bundle bundle = bundleExtra.getBundle("referer_data");
            if (bundle != null) {
                appLinkData2.ref = bundle.getString("fb_ref");
            }
            final Bundle bundle2 = bundleExtra.getBundle("extras");
            appLinkData = appLinkData2;
            if (bundle2 != null) {
                final String string2 = bundle2.getString("deeplink_context");
                appLinkData = appLinkData2;
                if (string2 != null) {
                    try {
                        final JSONObject jsonObject = new JSONObject(string2);
                        appLinkData = appLinkData2;
                        if (jsonObject.has("promo_code")) {
                            appLinkData2.promotionCode = jsonObject.getString("promo_code");
                            return appLinkData2;
                        }
                    }
                    catch (JSONException ex) {
                        Log.d(AppLinkData.TAG, "Unable to parse deeplink_context JSON", (Throwable)ex);
                        return appLinkData2;
                    }
                }
            }
        }
        return appLinkData;
    }
    
    private static AppLinkData createFromJson(String string) {
        if (string == null) {
            return null;
        }
        try {
            final JSONObject jsonObject = new JSONObject(string);
            string = jsonObject.getString("version");
            if (!jsonObject.getJSONObject("bridge_args").getString("method").equals("applink") || !string.equals("2")) {
                goto Label_0202;
            }
            final AppLinkData appLinkData = new AppLinkData();
            appLinkData.arguments = jsonObject.getJSONObject("method_args");
            if (appLinkData.arguments.has("ref")) {
                appLinkData.ref = appLinkData.arguments.getString("ref");
                if (appLinkData.arguments.has("target_url")) {
                    appLinkData.targetUri = Uri.parse(appLinkData.arguments.getString("target_url"));
                }
                if (appLinkData.arguments.has("extras")) {
                    final JSONObject jsonObject2 = appLinkData.arguments.getJSONObject("extras");
                    if (jsonObject2.has("deeplink_context")) {
                        final JSONObject jsonObject3 = jsonObject2.getJSONObject("deeplink_context");
                        if (jsonObject3.has("promo_code")) {
                            appLinkData.promotionCode = jsonObject3.getString("promo_code");
                        }
                    }
                }
                appLinkData.argumentBundle = toBundle(appLinkData.arguments);
                return appLinkData;
            }
            goto Label_0204;
        }
        catch (JSONException ex) {
            Log.d(AppLinkData.TAG, "Unable to parse AppLink JSON", (Throwable)ex);
        }
        catch (FacebookException ex2) {
            Log.d(AppLinkData.TAG, "Unable to parse AppLink JSON", (Throwable)ex2);
            goto Label_0202;
        }
    }
    
    private static AppLinkData createFromUri(final Uri targetUri) {
        if (targetUri == null) {
            return null;
        }
        final AppLinkData appLinkData = new AppLinkData();
        appLinkData.targetUri = targetUri;
        return appLinkData;
    }
    
    public static void fetchDeferredAppLinkData(final Context context, final CompletionHandler completionHandler) {
        fetchDeferredAppLinkData(context, null, completionHandler);
    }
    
    public static void fetchDeferredAppLinkData(Context applicationContext, final String s, final CompletionHandler completionHandler) {
        Validate.notNull((Object)applicationContext, "context");
        Validate.notNull((Object)completionHandler, "completionHandler");
        String metadataApplicationId = s;
        if (s == null) {
            metadataApplicationId = Utility.getMetadataApplicationId(applicationContext);
        }
        Validate.notNull((Object)metadataApplicationId, "applicationId");
        applicationContext = applicationContext.getApplicationContext();
        FacebookSdk.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                fetchDeferredAppLinkFromServer(applicationContext, metadataApplicationId, completionHandler);
            }
        });
    }
    
    private static void fetchDeferredAppLinkFromServer(final Context p0, final String p1, final CompletionHandler p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   org/json/JSONObject.<init>:()V
        //     7: astore          7
        //     9: aload           7
        //    11: ldc             "event"
        //    13: ldc             "DEFERRED_APP_LINK"
        //    15: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    18: pop            
        //    19: aload           7
        //    21: aload_0        
        //    22: invokestatic    com/facebook/internal/AttributionIdentifiers.getAttributionIdentifiers:(Landroid/content/Context;)Lcom/facebook/internal/AttributionIdentifiers;
        //    25: aload_0        
        //    26: invokestatic    com/facebook/appevents/AppEventsLogger.getAnonymousAppDeviceGUID:(Landroid/content/Context;)Ljava/lang/String;
        //    29: aload_0        
        //    30: invokestatic    com/facebook/FacebookSdk.getLimitEventAndDataUsage:(Landroid/content/Context;)Z
        //    33: invokestatic    com/facebook/internal/Utility.setAppEventAttributionParameters:(Lorg/json/JSONObject;Lcom/facebook/internal/AttributionIdentifiers;Ljava/lang/String;Z)V
        //    36: aload           7
        //    38: invokestatic    com/facebook/FacebookSdk.getApplicationContext:()Landroid/content/Context;
        //    41: invokestatic    com/facebook/internal/Utility.setAppEventExtendedDeviceInfoParameters:(Lorg/json/JSONObject;Landroid/content/Context;)V
        //    44: aload           7
        //    46: ldc_w           "application_package_name"
        //    49: aload_0        
        //    50: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    53: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    56: pop            
        //    57: ldc             "%s/activities"
        //    59: iconst_1       
        //    60: anewarray       Ljava/lang/Object;
        //    63: dup            
        //    64: iconst_0       
        //    65: aload_1        
        //    66: aastore        
        //    67: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    70: astore_1       
        //    71: aconst_null    
        //    72: astore          6
        //    74: aconst_null    
        //    75: astore          5
        //    77: aload           6
        //    79: astore_0       
        //    80: aconst_null    
        //    81: aload_1        
        //    82: aload           7
        //    84: aconst_null    
        //    85: invokestatic    com/facebook/GraphRequest.newPostRequest:(Lcom/facebook/AccessToken;Ljava/lang/String;Lorg/json/JSONObject;Lcom/facebook/GraphRequest$Callback;)Lcom/facebook/GraphRequest;
        //    88: invokevirtual   com/facebook/GraphRequest.executeAndWait:()Lcom/facebook/GraphResponse;
        //    91: invokevirtual   com/facebook/GraphResponse.getJSONObject:()Lorg/json/JSONObject;
        //    94: astore          8
        //    96: aload           5
        //    98: astore_1       
        //    99: aload           8
        //   101: ifnull          365
        //   104: aload           6
        //   106: astore_0       
        //   107: aload           8
        //   109: ldc             "applink_args"
        //   111: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   114: astore          9
        //   116: aload           6
        //   118: astore_0       
        //   119: aload           8
        //   121: ldc             "click_time"
        //   123: ldc2_w          -1
        //   126: invokevirtual   org/json/JSONObject.optLong:(Ljava/lang/String;J)J
        //   129: lstore_3       
        //   130: aload           6
        //   132: astore_0       
        //   133: aload           8
        //   135: ldc             "applink_class"
        //   137: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   140: astore          7
        //   142: aload           6
        //   144: astore_0       
        //   145: aload           8
        //   147: ldc             "applink_url"
        //   149: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   152: astore          8
        //   154: aload           5
        //   156: astore_1       
        //   157: aload           6
        //   159: astore_0       
        //   160: aload           9
        //   162: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   165: ifne            365
        //   168: aload           6
        //   170: astore_0       
        //   171: aload           9
        //   173: invokestatic    com/facebook/applinks/AppLinkData.createFromJson:(Ljava/lang/String;)Lcom/facebook/applinks/AppLinkData;
        //   176: astore          5
        //   178: lload_3        
        //   179: ldc2_w          -1
        //   182: lcmp           
        //   183: ifeq            240
        //   186: aload           5
        //   188: astore_0       
        //   189: aload           5
        //   191: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   194: ifnull          212
        //   197: aload           5
        //   199: astore_0       
        //   200: aload           5
        //   202: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   205: ldc             "com.facebook.platform.APPLINK_TAP_TIME_UTC"
        //   207: lload_3        
        //   208: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //   211: pop            
        //   212: aload           5
        //   214: astore_0       
        //   215: aload           5
        //   217: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   220: ifnull          240
        //   223: aload           5
        //   225: astore_0       
        //   226: aload           5
        //   228: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   231: ldc             "com.facebook.platform.APPLINK_TAP_TIME_UTC"
        //   233: lload_3        
        //   234: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //   237: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   240: aload           7
        //   242: ifnull          298
        //   245: aload           5
        //   247: astore_0       
        //   248: aload           5
        //   250: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   253: ifnull          272
        //   256: aload           5
        //   258: astore_0       
        //   259: aload           5
        //   261: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   264: ldc             "com.facebook.platform.APPLINK_NATIVE_CLASS"
        //   266: aload           7
        //   268: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   271: pop            
        //   272: aload           5
        //   274: astore_0       
        //   275: aload           5
        //   277: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   280: ifnull          298
        //   283: aload           5
        //   285: astore_0       
        //   286: aload           5
        //   288: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   291: ldc             "com.facebook.platform.APPLINK_NATIVE_CLASS"
        //   293: aload           7
        //   295: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   298: aload           5
        //   300: astore_1       
        //   301: aload           8
        //   303: ifnull          365
        //   306: aload           5
        //   308: astore_0       
        //   309: aload           5
        //   311: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   314: ifnull          333
        //   317: aload           5
        //   319: astore_0       
        //   320: aload           5
        //   322: getfield        com/facebook/applinks/AppLinkData.arguments:Lorg/json/JSONObject;
        //   325: ldc             "com.facebook.platform.APPLINK_NATIVE_URL"
        //   327: aload           8
        //   329: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   332: pop            
        //   333: aload           5
        //   335: astore_1       
        //   336: aload           5
        //   338: astore_0       
        //   339: aload           5
        //   341: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   344: ifnull          365
        //   347: aload           5
        //   349: astore_0       
        //   350: aload           5
        //   352: getfield        com/facebook/applinks/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   355: ldc             "com.facebook.platform.APPLINK_NATIVE_URL"
        //   357: aload           8
        //   359: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   362: aload           5
        //   364: astore_1       
        //   365: aload_2        
        //   366: aload_1        
        //   367: invokeinterface com/facebook/applinks/AppLinkData$CompletionHandler.onDeferredAppLinkDataFetched:(Lcom/facebook/applinks/AppLinkData;)V
        //   372: return         
        //   373: astore_0       
        //   374: new             Lcom/facebook/FacebookException;
        //   377: dup            
        //   378: ldc_w           "An error occurred while preparing deferred app link"
        //   381: aload_0        
        //   382: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   385: athrow         
        //   386: astore_0       
        //   387: aload           5
        //   389: astore_0       
        //   390: getstatic       com/facebook/applinks/AppLinkData.TAG:Ljava/lang/String;
        //   393: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   396: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   399: pop            
        //   400: goto            240
        //   403: astore_1       
        //   404: getstatic       com/facebook/applinks/AppLinkData.TAG:Ljava/lang/String;
        //   407: ldc_w           "Unable to fetch deferred applink from server"
        //   410: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;)V
        //   413: aload_0        
        //   414: astore_1       
        //   415: goto            365
        //   418: astore_0       
        //   419: aload           5
        //   421: astore_0       
        //   422: getstatic       com/facebook/applinks/AppLinkData.TAG:Ljava/lang/String;
        //   425: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   428: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   431: pop            
        //   432: goto            298
        //   435: astore_0       
        //   436: aload           5
        //   438: astore_0       
        //   439: getstatic       com/facebook/applinks/AppLinkData.TAG:Ljava/lang/String;
        //   442: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   445: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   448: pop            
        //   449: aload           5
        //   451: astore_1       
        //   452: goto            365
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  9      57     373    386    Lorg/json/JSONException;
        //  80     96     403    418    Ljava/lang/Exception;
        //  107    116    403    418    Ljava/lang/Exception;
        //  119    130    403    418    Ljava/lang/Exception;
        //  133    142    403    418    Ljava/lang/Exception;
        //  145    154    403    418    Ljava/lang/Exception;
        //  160    168    403    418    Ljava/lang/Exception;
        //  171    178    403    418    Ljava/lang/Exception;
        //  189    197    386    403    Lorg/json/JSONException;
        //  189    197    403    418    Ljava/lang/Exception;
        //  200    212    386    403    Lorg/json/JSONException;
        //  200    212    403    418    Ljava/lang/Exception;
        //  215    223    386    403    Lorg/json/JSONException;
        //  215    223    403    418    Ljava/lang/Exception;
        //  226    240    386    403    Lorg/json/JSONException;
        //  226    240    403    418    Ljava/lang/Exception;
        //  248    256    418    435    Lorg/json/JSONException;
        //  248    256    403    418    Ljava/lang/Exception;
        //  259    272    418    435    Lorg/json/JSONException;
        //  259    272    403    418    Ljava/lang/Exception;
        //  275    283    418    435    Lorg/json/JSONException;
        //  275    283    403    418    Ljava/lang/Exception;
        //  286    298    418    435    Lorg/json/JSONException;
        //  286    298    403    418    Ljava/lang/Exception;
        //  309    317    435    455    Lorg/json/JSONException;
        //  309    317    403    418    Ljava/lang/Exception;
        //  320    333    435    455    Lorg/json/JSONException;
        //  320    333    403    418    Ljava/lang/Exception;
        //  339    347    435    455    Lorg/json/JSONException;
        //  339    347    403    418    Ljava/lang/Exception;
        //  350    362    435    455    Lorg/json/JSONException;
        //  350    362    403    418    Ljava/lang/Exception;
        //  390    400    403    418    Ljava/lang/Exception;
        //  422    432    403    418    Ljava/lang/Exception;
        //  439    449    403    418    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 224, Size: 224
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
    
    private static Bundle toBundle(final JSONObject jsonObject) throws JSONException {
        final Bundle bundle = new Bundle();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            final Object value = jsonObject.get(s);
            if (value instanceof JSONObject) {
                bundle.putBundle(s, toBundle((JSONObject)value));
            }
            else if (value instanceof JSONArray) {
                final JSONArray jsonArray = (JSONArray)value;
                if (jsonArray.length() == 0) {
                    bundle.putStringArray(s, new String[0]);
                }
                else {
                    final Object value2 = jsonArray.get(0);
                    if (value2 instanceof JSONObject) {
                        final Bundle[] array = new Bundle[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); ++i) {
                            array[i] = toBundle(jsonArray.getJSONObject(i));
                        }
                        bundle.putParcelableArray(s, (Parcelable[])array);
                    }
                    else {
                        if (value2 instanceof JSONArray) {
                            throw new FacebookException("Nested arrays are not supported.");
                        }
                        final String[] array2 = new String[jsonArray.length()];
                        for (int j = 0; j < jsonArray.length(); ++j) {
                            array2[j] = jsonArray.get(j).toString();
                        }
                        bundle.putStringArray(s, array2);
                    }
                }
            }
            else {
                bundle.putString(s, value.toString());
            }
        }
        return bundle;
    }
    
    public Bundle getArgumentBundle() {
        return this.argumentBundle;
    }
    
    public String getPromotionCode() {
        return this.promotionCode;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public Bundle getRefererData() {
        if (this.argumentBundle != null) {
            return this.argumentBundle.getBundle("referer_data");
        }
        return null;
    }
    
    public Uri getTargetUri() {
        return this.targetUri;
    }
    
    public interface CompletionHandler
    {
        void onDeferredAppLinkDataFetched(final AppLinkData p0);
    }
}
