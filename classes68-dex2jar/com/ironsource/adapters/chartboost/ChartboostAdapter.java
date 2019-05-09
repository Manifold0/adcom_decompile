// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.adapters.chartboost;

import java.util.Iterator;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.ChartboostDelegate;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.logger.IronSourceLogger$IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import android.text.TextUtils;
import org.json.JSONObject;
import com.ironsource.mediationsdk.IntegrationData;
import com.chartboost.sdk.Chartboost;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import java.util.concurrent.ConcurrentHashMap;
import android.app.Activity;
import com.ironsource.mediationsdk.AbstractAdapter;

class ChartboostAdapter extends AbstractAdapter
{
    private static final String GitHash = "63e9f72bf";
    private static final String VERSION = "4.1.8";
    private final String AD_LOCATION;
    private final String APP_ID;
    private final String APP_SIGNATURE;
    private Activity mActivity;
    private Boolean mAlreadyCalledInit;
    private Boolean mConsentCollectingUserData;
    private CbDelegate mDelegate;
    private boolean mDidInitSuccessfully;
    private ConcurrentHashMap<String, InterstitialSmashListener> mLocationToIsListener;
    private ConcurrentHashMap<String, RewardedVideoSmashListener> mLocationToRvListener;
    
    private ChartboostAdapter(final String s) {
        super(s);
        this.mConsentCollectingUserData = null;
        this.mAlreadyCalledInit = false;
        this.mDidInitSuccessfully = false;
        this.APP_ID = "appID";
        this.APP_SIGNATURE = "appSignature";
        this.AD_LOCATION = "adLocation";
        this.mLocationToIsListener = new ConcurrentHashMap<String, InterstitialSmashListener>();
        this.mLocationToRvListener = new ConcurrentHashMap<String, RewardedVideoSmashListener>();
    }
    
    public static String getAdapterSDKVersion() {
        try {
            return Chartboost.getSDKVersion();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static IntegrationData getIntegrationData(final Activity activity) {
        final IntegrationData integrationData = new IntegrationData("Chartboost", "4.1.8");
        integrationData.activities = new String[] { "com.chartboost.sdk.CBImpressionActivity" };
        return integrationData;
    }
    
    private String getLocationId(final JSONObject jsonObject) {
        String optString;
        if (TextUtils.isEmpty((CharSequence)(optString = jsonObject.optString("adLocation")))) {
            optString = "Default";
        }
        return optString;
    }
    
    private void init(final Activity activity, final String s, final String s2, final String s3, final String s4) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: monitorenter   
                //     2: aload_0        
                //     3: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //     6: invokestatic    com/ironsource/adapters/chartboost/ChartboostAdapter.access$000:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;)Ljava/lang/Boolean;
                //     9: invokevirtual   java/lang/Boolean.booleanValue:()Z
                //    12: ifne            214
                //    15: aload_0        
                //    16: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //    19: iconst_1       
                //    20: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
                //    23: invokestatic    com/ironsource/adapters/chartboost/ChartboostAdapter.access$002:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;Ljava/lang/Boolean;)Ljava/lang/Boolean;
                //    26: pop            
                //    27: aload_0        
                //    28: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //    31: aload_0        
                //    32: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.val$activity:Landroid/app/Activity;
                //    35: invokestatic    com/ironsource/adapters/chartboost/ChartboostAdapter.access$102:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;Landroid/app/Activity;)Landroid/app/Activity;
                //    38: pop            
                //    39: aload_0        
                //    40: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //    43: new             Lcom/ironsource/adapters/chartboost/ChartboostAdapter$CbDelegate;
                //    46: dup            
                //    47: aload_0        
                //    48: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //    51: aconst_null    
                //    52: invokespecial   com/ironsource/adapters/chartboost/ChartboostAdapter$CbDelegate.<init>:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;Lcom/ironsource/adapters/chartboost/ChartboostAdapter$1;)V
                //    55: invokestatic    com/ironsource/adapters/chartboost/ChartboostAdapter.access$202:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;Lcom/ironsource/adapters/chartboost/ChartboostAdapter$CbDelegate;)Lcom/ironsource/adapters/chartboost/ChartboostAdapter$CbDelegate;
                //    58: pop            
                //    59: aload_0        
                //    60: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //    63: invokestatic    com/ironsource/adapters/chartboost/ChartboostAdapter.access$400:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;)Ljava/lang/Boolean;
                //    66: ifnull          86
                //    69: aload_0        
                //    70: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //    73: aload_0        
                //    74: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //    77: invokestatic    com/ironsource/adapters/chartboost/ChartboostAdapter.access$400:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;)Ljava/lang/Boolean;
                //    80: invokevirtual   java/lang/Boolean.booleanValue:()Z
                //    83: invokevirtual   com/ironsource/adapters/chartboost/ChartboostAdapter.setConsent:(Z)V
                //    86: aload_0        
                //    87: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //    90: invokestatic    com/ironsource/adapters/chartboost/ChartboostAdapter.access$200:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;)Lcom/ironsource/adapters/chartboost/ChartboostAdapter$CbDelegate;
                //    93: invokestatic    com/chartboost/sdk/Chartboost.setDelegate:(Lcom/chartboost/sdk/ChartboostDelegate;)V
                //    96: aload_0        
                //    97: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.val$activity:Landroid/app/Activity;
                //   100: aload_0        
                //   101: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.val$appId:Ljava/lang/String;
                //   104: aload_0        
                //   105: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.val$appSignature:Ljava/lang/String;
                //   108: invokestatic    com/chartboost/sdk/Chartboost.startWithAppId:(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;)V
                //   111: iconst_0       
                //   112: istore_1       
                //   113: aload_0        
                //   114: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //   117: invokestatic    com/ironsource/adapters/chartboost/ChartboostAdapter.access$500:(Lcom/ironsource/adapters/chartboost/ChartboostAdapter;)Z
                //   120: istore_2       
                //   121: iload_2        
                //   122: istore_1       
                //   123: iload_1        
                //   124: ifeq            217
                //   127: getstatic       com/chartboost/sdk/Libraries/CBLogging$Level.ALL:Lcom/chartboost/sdk/Libraries/CBLogging$Level;
                //   130: invokestatic    com/chartboost/sdk/Chartboost.setLoggingLevel:(Lcom/chartboost/sdk/Libraries/CBLogging$Level;)V
                //   133: ldc             "Unity"
                //   135: aload_0        
                //   136: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //   139: invokevirtual   com/ironsource/adapters/chartboost/ChartboostAdapter.getPluginType:()Ljava/lang/String;
                //   142: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   145: ifeq            174
                //   148: aload_0        
                //   149: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //   152: invokevirtual   com/ironsource/adapters/chartboost/ChartboostAdapter.getPluginFrameworkVersion:()Ljava/lang/String;
                //   155: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
                //   158: ifne            174
                //   161: getstatic       com/chartboost/sdk/Chartboost$CBFramework.CBFrameworkUnity:Lcom/chartboost/sdk/Chartboost$CBFramework;
                //   164: aload_0        
                //   165: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.this$0:Lcom/ironsource/adapters/chartboost/ChartboostAdapter;
                //   168: invokevirtual   com/ironsource/adapters/chartboost/ChartboostAdapter.getPluginFrameworkVersion:()Ljava/lang/String;
                //   171: invokestatic    com/chartboost/sdk/Chartboost.setFramework:(Lcom/chartboost/sdk/Chartboost$CBFramework;Ljava/lang/String;)V
                //   174: getstatic       com/chartboost/sdk/Chartboost$CBMediation.CBMediationironSource:Lcom/chartboost/sdk/Chartboost$CBMediation;
                //   177: ldc             "4.1.8"
                //   179: invokestatic    com/chartboost/sdk/Chartboost.setMediation:(Lcom/chartboost/sdk/Chartboost$CBMediation;Ljava/lang/String;)V
                //   182: aload_0        
                //   183: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.val$userId:Ljava/lang/String;
                //   186: invokestatic    com/chartboost/sdk/Chartboost.setCustomId:(Ljava/lang/String;)V
                //   189: iconst_1       
                //   190: invokestatic    com/chartboost/sdk/Chartboost.setAutoCacheAds:(Z)V
                //   193: aload_0        
                //   194: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.val$activity:Landroid/app/Activity;
                //   197: invokestatic    com/chartboost/sdk/Chartboost.onCreate:(Landroid/app/Activity;)V
                //   200: aload_0        
                //   201: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.val$activity:Landroid/app/Activity;
                //   204: invokestatic    com/chartboost/sdk/Chartboost.onStart:(Landroid/app/Activity;)V
                //   207: aload_0        
                //   208: getfield        com/ironsource/adapters/chartboost/ChartboostAdapter$1.val$activity:Landroid/app/Activity;
                //   211: invokestatic    com/chartboost/sdk/Chartboost.onResume:(Landroid/app/Activity;)V
                //   214: aload_0        
                //   215: monitorexit    
                //   216: return         
                //   217: getstatic       com/chartboost/sdk/Libraries/CBLogging$Level.NONE:Lcom/chartboost/sdk/Libraries/CBLogging$Level;
                //   220: invokestatic    com/chartboost/sdk/Chartboost.setLoggingLevel:(Lcom/chartboost/sdk/Libraries/CBLogging$Level;)V
                //   223: goto            133
                //   226: astore_3       
                //   227: aload_0        
                //   228: monitorexit    
                //   229: aload_3        
                //   230: athrow         
                //   231: astore_3       
                //   232: goto            123
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                         
                //  -----  -----  -----  -----  -----------------------------
                //  2      86     226    231    Any
                //  86     111    226    231    Any
                //  113    121    231    235    Ljava/lang/NoSuchMethodError;
                //  113    121    226    231    Any
                //  127    133    226    231    Any
                //  133    174    226    231    Any
                //  174    214    226    231    Any
                //  214    216    226    231    Any
                //  217    223    226    231    Any
                //  227    229    226    231    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0123:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
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
        });
    }
    
    public static ChartboostAdapter startAdapter(final String s) {
        return new ChartboostAdapter(s);
    }
    
    public void fetchRewardedVideo(final JSONObject jsonObject) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " fetchRewardedVideo placementId: <" + this.getLocationId(jsonObject) + ">: Automatic network", 1);
    }
    
    public String getCoreSDKVersion() {
        return Chartboost.getSDKVersion();
    }
    
    public String getVersion() {
        return "4.1.8";
    }
    
    public void initInterstitial(final Activity activity, String locationId, final String s, final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        synchronized (this) {
            if (TextUtils.isEmpty((CharSequence)jsonObject.optString("appID")) || TextUtils.isEmpty((CharSequence)jsonObject.optString("appSignature"))) {
                if (interstitialSmashListener != null) {
                    interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError("Missing params", "Interstitial"));
                }
            }
            else {
                locationId = this.getLocationId(jsonObject);
                this.mLocationToIsListener.put(locationId, interstitialSmashListener);
                this.init(activity, s, "IS", jsonObject.optString("appID"), jsonObject.optString("appSignature"));
                if (this.mDidInitSuccessfully) {
                    interstitialSmashListener.onInterstitialInitSuccess();
                }
            }
        }
    }
    
    public void initRewardedVideo(final Activity activity, String locationId, final String s, final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        synchronized (this) {
            if (TextUtils.isEmpty((CharSequence)jsonObject.optString("appID")) || TextUtils.isEmpty((CharSequence)jsonObject.optString("appSignature"))) {
                if (rewardedVideoSmashListener != null) {
                    rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
                }
            }
            else {
                locationId = this.getLocationId(jsonObject);
                this.mLocationToRvListener.put(locationId, rewardedVideoSmashListener);
                this.init(activity, s, "RV", jsonObject.optString("appID"), jsonObject.optString("appSignature"));
                if (this.mDidInitSuccessfully) {
                    Chartboost.cacheRewardedVideo(locationId);
                }
            }
        }
    }
    
    public boolean isInterstitialReady(final JSONObject jsonObject) {
        return Chartboost.hasInterstitial(this.getLocationId(jsonObject));
    }
    
    public boolean isRewardedVideoAvailable(final JSONObject jsonObject) {
        return Chartboost.hasRewardedVideo(this.getLocationId(jsonObject));
    }
    
    public void loadInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
            final /* synthetic */ String val$locationId = ChartboostAdapter.this.getLocationId(jsonObject);
            
            @Override
            public void run() {
                Chartboost.cacheInterstitial(this.val$locationId);
            }
        });
    }
    
    public void onPause(final Activity activity) {
        if (activity != null) {
            Chartboost.onPause(activity);
            Chartboost.onStop(activity);
        }
    }
    
    public void onResume(final Activity mActivity) {
        if (mActivity != null) {
            Chartboost.onStart(this.mActivity = mActivity);
            Chartboost.onResume(mActivity);
        }
    }
    
    protected void setConsent(final boolean b) {
        while (true) {
            synchronized (this) {
                if (this.mAlreadyCalledInit) {
                    if (b) {
                        Chartboost.setPIDataUseConsent((Context)this.mActivity, Chartboost.CBPIDataUseConsent.YES_BEHAVIORAL);
                    }
                    else {
                        Chartboost.setPIDataUseConsent((Context)this.mActivity, Chartboost.CBPIDataUseConsent.NO_BEHAVIORAL);
                    }
                    return;
                }
            }
            this.mConsentCollectingUserData = b;
        }
    }
    
    public void showInterstitial(final JSONObject jsonObject, final InterstitialSmashListener interstitialSmashListener) {
        final String locationId = this.getLocationId(jsonObject);
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " showInterstitial placementId: <" + locationId + ">", 1);
        if (Chartboost.hasInterstitial(locationId)) {
            Chartboost.showInterstitial(locationId);
        }
        else if (this.mLocationToIsListener.containsKey(locationId)) {
            this.mLocationToIsListener.get(locationId).onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }
    
    public void showRewardedVideo(final JSONObject jsonObject, final RewardedVideoSmashListener rewardedVideoSmashListener) {
        final String locationId = this.getLocationId(jsonObject);
        IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, this.getProviderName() + " showRewardedVideo placementId: <" + locationId + ">", 1);
        if (Chartboost.hasRewardedVideo(locationId)) {
            Chartboost.showRewardedVideo(locationId);
        }
        else {
            Chartboost.cacheRewardedVideo(locationId);
            if (this.mLocationToRvListener.containsKey(locationId)) {
                this.mLocationToRvListener.get(locationId).onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
            }
        }
    }
    
    private class CbDelegate extends ChartboostDelegate
    {
        @Override
        public void didCacheInterstitial(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCacheInterstitial placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToIsListener.get(s).onInterstitialAdReady();
            }
        }
        
        @Override
        public void didCacheRewardedVideo(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCacheRewardedVideo placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToRvListener.get(s).onRewardedVideoAvailabilityChanged(true);
            }
        }
        
        @Override
        public void didClickInterstitial(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.INTERNAL, ChartboostAdapter.this.getProviderName() + " didClickInterstitial placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToIsListener.get(s).onInterstitialAdClicked();
            }
        }
        
        @Override
        public void didClickRewardedVideo(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didClickRewardedVideo placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToRvListener.get(s).onRewardedVideoAdClicked();
            }
        }
        
        @Override
        public void didCloseInterstitial(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCloseInterstitial placementId: <" + s + ">", 1);
        }
        
        @Override
        public void didCloseRewardedVideo(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCloseRewardedVideo placementId: <" + s + ">", 1);
        }
        
        @Override
        public void didCompleteRewardedVideo(final String s, final int n) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didCompleteRewardedVideo placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToRvListener.get(s).onRewardedVideoAdRewarded();
            }
        }
        
        @Override
        public void didDismissInterstitial(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didDismissInterstitial placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToIsListener.get(s).onInterstitialAdClosed();
            }
        }
        
        @Override
        public void didDismissRewardedVideo(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didDismissRewardedVideo placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToRvListener.get(s).onRewardedVideoAdClosed();
            }
        }
        
        @Override
        public void didDisplayInterstitial(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didDisplayInterstitial placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToIsListener.get(s).onInterstitialAdOpened();
                ChartboostAdapter.this.mLocationToIsListener.get(s).onInterstitialAdShowSucceeded();
            }
        }
        
        @Override
        public void didDisplayRewardedVideo(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didDisplayRewardedVideo placementId: <" + s + ">", 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToRvListener.get(s).onRewardedVideoAdOpened();
            }
        }
        
        @Override
        public void didFailToLoadInterstitial(final String s, final CBError.CBImpressionError cbImpressionError) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didFailToLoadInterstitial placementId: <" + s + ">  error: " + cbImpressionError.toString(), 1);
            if (ChartboostAdapter.this.mLocationToIsListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToIsListener.get(s).onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(cbImpressionError.toString()));
            }
        }
        
        @Override
        public void didFailToLoadRewardedVideo(final String s, final CBError.CBImpressionError cbImpressionError) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, "didFailToLoadRewardedVideo placementId: <" + s + "> error: " + cbImpressionError.toString(), 1);
            if (ChartboostAdapter.this.mLocationToRvListener.get(s) != null) {
                ChartboostAdapter.this.mLocationToRvListener.get(s).onRewardedVideoAvailabilityChanged(Chartboost.hasRewardedVideo(s));
            }
        }
        
        @Override
        public void didInitialize() {
            synchronized (this) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " didInitialize", 1);
                ChartboostAdapter.this.mDidInitSuccessfully = true;
                final Iterator iterator = ChartboostAdapter.this.mLocationToRvListener.keySet().iterator();
                while (iterator.hasNext()) {
                    Chartboost.cacheRewardedVideo(iterator.next());
                }
            }
            final Iterator iterator2 = ChartboostAdapter.this.mLocationToIsListener.keySet().iterator();
            while (iterator2.hasNext()) {
                ((InterstitialSmashListener)ChartboostAdapter.this.mLocationToIsListener.get(iterator2.next())).onInterstitialInitSuccess();
            }
        }
        // monitorexit(this)
        
        @Override
        public void willDisplayVideo(final String s) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger$IronSourceTag.ADAPTER_API, ChartboostAdapter.this.getProviderName() + " willDisplayVideo placementId: <" + s + ">", 1);
        }
    }
}
