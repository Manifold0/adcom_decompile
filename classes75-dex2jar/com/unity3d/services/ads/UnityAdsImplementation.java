// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads;

import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.IUnityServicesListener;
import android.app.Activity;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.ads.placement.Placement;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.services.UnityServices;
import com.unity3d.ads.UnityAds;

public final class UnityAdsImplementation
{
    public static boolean getDebugMode() {
        return UnityServices.getDebugMode();
    }
    
    public static IUnityAdsListener getListener() {
        return AdsProperties.getListener();
    }
    
    public static UnityAds.PlacementState getPlacementState() {
        if (isSupported() && isInitialized()) {
            return Placement.getPlacementState();
        }
        return UnityAds.PlacementState.NOT_AVAILABLE;
    }
    
    public static UnityAds.PlacementState getPlacementState(final String s) {
        if (isSupported() && isInitialized() && s != null) {
            return Placement.getPlacementState(s);
        }
        return UnityAds.PlacementState.NOT_AVAILABLE;
    }
    
    public static String getVersion() {
        return UnityServices.getVersion();
    }
    
    private static void handleShowError(final String s, final UnityAds.UnityAdsError unityAdsError, String string) {
        string = "Unity Ads show failed: " + string;
        DeviceLog.error(string);
        final IUnityAdsListener listener = AdsProperties.getListener();
        if (listener != null) {
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listener.onUnityAdsError(unityAdsError, string);
                    if (s != null) {
                        listener.onUnityAdsFinish(s, UnityAds.FinishState.ERROR);
                        return;
                    }
                    listener.onUnityAdsFinish("", UnityAds.FinishState.ERROR);
                }
            });
        }
    }
    
    public static void initialize(final Activity activity, final String s, final IUnityAdsListener unityAdsListener) {
        initialize(activity, s, unityAdsListener, false);
    }
    
    public static void initialize(final Activity activity, final String s, final IUnityAdsListener listener, final boolean b) {
        DeviceLog.entered();
        AdsProperties.setListener(listener);
        UnityServices.initialize(activity, s, new IUnityServicesListener() {
            @Override
            public void onUnityServicesError(final UnityServices.UnityServicesError unityServicesError, final String s) {
                if (unityServicesError == UnityServices.UnityServicesError.INIT_SANITY_CHECK_FAIL) {
                    listener.onUnityAdsError(UnityAds.UnityAdsError.INIT_SANITY_CHECK_FAIL, s);
                }
                else if (unityServicesError == UnityServices.UnityServicesError.INVALID_ARGUMENT) {
                    listener.onUnityAdsError(UnityAds.UnityAdsError.INVALID_ARGUMENT, s);
                }
            }
        }, b);
    }
    
    public static boolean isInitialized() {
        return UnityServices.isInitialized();
    }
    
    public static boolean isReady() {
        return isSupported() && isInitialized() && Placement.isReady();
    }
    
    public static boolean isReady(final String s) {
        return isSupported() && isInitialized() && s != null && Placement.isReady(s);
    }
    
    public static boolean isSupported() {
        return UnityServices.isSupported();
    }
    
    public static void setDebugMode(final boolean debugMode) {
        UnityServices.setDebugMode(debugMode);
    }
    
    public static void setListener(final IUnityAdsListener listener) {
        AdsProperties.setListener(listener);
    }
    
    public static void show(final Activity activity) {
        if (Placement.getDefaultPlacement() != null) {
            show(activity, Placement.getDefaultPlacement());
            return;
        }
        handleShowError("", UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads default placement is not initialized");
    }
    
    public static void show(final Activity activity, final String s) {
        if (activity == null) {
            handleShowError(s, UnityAds.UnityAdsError.INVALID_ARGUMENT, "Activity must not be null");
            return;
        }
        if (isReady(s)) {
            DeviceLog.info("Unity Ads opening new ad unit for placement " + s);
            ClientProperties.setActivity(activity);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     1: getfield        com/unity3d/services/ads/UnityAdsImplementation$2.val$activity:Landroid/app/Activity;
                    //     4: astore_1       
                    //     5: aload_0        
                    //     6: getfield        com/unity3d/services/ads/UnityAdsImplementation$2.val$activity:Landroid/app/Activity;
                    //     9: astore_2       
                    //    10: aload_1        
                    //    11: ldc             "window"
                    //    13: invokevirtual   android/app/Activity.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
                    //    16: checkcast       Landroid/view/WindowManager;
                    //    19: invokeinterface android/view/WindowManager.getDefaultDisplay:()Landroid/view/Display;
                    //    24: astore_2       
                    //    25: new             Lorg/json/JSONObject;
                    //    28: dup            
                    //    29: invokespecial   org/json/JSONObject.<init>:()V
                    //    32: astore_1       
                    //    33: aload_1        
                    //    34: ldc             "requestedOrientation"
                    //    36: aload_0        
                    //    37: getfield        com/unity3d/services/ads/UnityAdsImplementation$2.val$activity:Landroid/app/Activity;
                    //    40: invokevirtual   android/app/Activity.getRequestedOrientation:()I
                    //    43: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
                    //    46: pop            
                    //    47: new             Lorg/json/JSONObject;
                    //    50: dup            
                    //    51: invokespecial   org/json/JSONObject.<init>:()V
                    //    54: astore_3       
                    //    55: aload_3        
                    //    56: ldc             "rotation"
                    //    58: aload_2        
                    //    59: invokevirtual   android/view/Display.getRotation:()I
                    //    62: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
                    //    65: pop            
                    //    66: getstatic       android/os/Build$VERSION.SDK_INT:I
                    //    69: bipush          13
                    //    71: if_icmplt       145
                    //    74: new             Landroid/graphics/Point;
                    //    77: dup            
                    //    78: invokespecial   android/graphics/Point.<init>:()V
                    //    81: astore          4
                    //    83: aload_2        
                    //    84: aload           4
                    //    86: invokevirtual   android/view/Display.getSize:(Landroid/graphics/Point;)V
                    //    89: aload_3        
                    //    90: ldc             "width"
                    //    92: aload           4
                    //    94: getfield        android/graphics/Point.x:I
                    //    97: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
                    //   100: pop            
                    //   101: aload_3        
                    //   102: ldc             "height"
                    //   104: aload           4
                    //   106: getfield        android/graphics/Point.y:I
                    //   109: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
                    //   112: pop            
                    //   113: aload_1        
                    //   114: ldc             "display"
                    //   116: aload_3        
                    //   117: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                    //   120: pop            
                    //   121: aload_0        
                    //   122: getfield        com/unity3d/services/ads/UnityAdsImplementation$2.val$placementId:Ljava/lang/String;
                    //   125: aload_1        
                    //   126: invokestatic    com/unity3d/services/ads/adunit/AdUnitOpen.open:(Ljava/lang/String;Lorg/json/JSONObject;)Z
                    //   129: ifne            144
                    //   132: aload_0        
                    //   133: getfield        com/unity3d/services/ads/UnityAdsImplementation$2.val$placementId:Ljava/lang/String;
                    //   136: getstatic       com/unity3d/ads/UnityAds$UnityAdsError.INTERNAL_ERROR:Lcom/unity3d/ads/UnityAds$UnityAdsError;
                    //   139: ldc             "Webapp timeout, shutting down Unity Ads"
                    //   141: invokestatic    com/unity3d/services/ads/UnityAdsImplementation.access$000:(Ljava/lang/String;Lcom/unity3d/ads/UnityAds$UnityAdsError;Ljava/lang/String;)V
                    //   144: return         
                    //   145: aload_3        
                    //   146: ldc             "width"
                    //   148: aload_2        
                    //   149: invokevirtual   android/view/Display.getWidth:()I
                    //   152: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
                    //   155: pop            
                    //   156: aload_3        
                    //   157: ldc             "height"
                    //   159: aload_2        
                    //   160: invokevirtual   android/view/Display.getHeight:()I
                    //   163: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
                    //   166: pop            
                    //   167: goto            113
                    //   170: astore_2       
                    //   171: ldc             "JSON error while constructing show options"
                    //   173: aload_2        
                    //   174: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
                    //   177: goto            121
                    //   180: astore_1       
                    //   181: ldc             "Could not get callback method"
                    //   183: aload_1        
                    //   184: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
                    //   187: aload_0        
                    //   188: getfield        com/unity3d/services/ads/UnityAdsImplementation$2.val$placementId:Ljava/lang/String;
                    //   191: getstatic       com/unity3d/ads/UnityAds$UnityAdsError.SHOW_ERROR:Lcom/unity3d/ads/UnityAds$UnityAdsError;
                    //   194: ldc             "Could not get com.unity3d.ads.properties.showCallback method"
                    //   196: invokestatic    com/unity3d/services/ads/UnityAdsImplementation.access$000:(Ljava/lang/String;Lcom/unity3d/ads/UnityAds$UnityAdsError;Ljava/lang/String;)V
                    //   199: return         
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                             
                    //  -----  -----  -----  -----  ---------------------------------
                    //  33     113    170    180    Lorg/json/JSONException;
                    //  113    121    170    180    Lorg/json/JSONException;
                    //  121    144    180    200    Ljava/lang/NoSuchMethodException;
                    //  145    167    170    180    Lorg/json/JSONException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0121:
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
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                    //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
            }).start();
            return;
        }
        if (!isSupported()) {
            handleShowError(s, UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads is not supported for this device");
            return;
        }
        if (!isInitialized()) {
            handleShowError(s, UnityAds.UnityAdsError.NOT_INITIALIZED, "Unity Ads is not initialized");
            return;
        }
        handleShowError(s, UnityAds.UnityAdsError.SHOW_ERROR, "Placement \"" + s + "\" is not ready");
    }
}
