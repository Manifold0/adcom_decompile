// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.model.OfferwallPlacement;
import com.ironsource.mediationsdk.sdk.SegmentListener;
import java.util.HashMap;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import java.util.Iterator;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.mediationsdk.config.ConfigValidationResult;
import com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener;
import com.ironsource.mediationsdk.sdk.InterstitialManagerListener;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.utils.GeneralPropertiesWorker;
import com.ironsource.mediationsdk.events.SuperLooper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InternalOfferwallListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.model.ApplicationEvents;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.utils.CappingManager;
import org.json.JSONException;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import org.json.JSONObject;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import android.text.TextUtils;
import com.ironsource.mediationsdk.model.BannerPlacement;
import android.content.Context;
import java.util.UUID;
import java.util.HashSet;
import java.util.Map;
import com.ironsource.mediationsdk.logger.PublisherLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.ListenersWrapper;
import java.util.List;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Set;
import android.app.Activity;
import com.ironsource.mediationsdk.sdk.IronSourceInterface;

public class IronSourceObject implements IronSourceInterface, OnMediationInitializationListener
{
    private static IronSourceObject sInstance;
    private final String IRONSOURCE_VERSION_STRING;
    private final String KEY_INIT_COUNTER;
    private final String TAG;
    private boolean isDemandOnlyInterstitial;
    private boolean isDemandOnlyRewardedVideo;
    private Activity mActivity;
    private Set<IronSource.AD_UNIT> mAdUnitsToInitialize;
    private String mAppKey;
    private AtomicBoolean mAtomicIsFirstInit;
    private ArrayList<AbstractAdapter> mBannerAdaptersList;
    private BannerManager mBannerManager;
    private IronSourceBannerLayout mBnLayoutToLoad;
    private String mBnPlacementToLoad;
    private Boolean mConsent;
    private ServerResponseWrapper mCurrentServerResponse;
    private boolean mDidInitBanner;
    private boolean mDidInitInterstitial;
    private boolean mDidInitRewardedVideo;
    private String mDynamicUserId;
    private AtomicBoolean mEventManagersInit;
    private int mInitCounter;
    private boolean mInitSucceeded;
    private List<IronSource.AD_UNIT> mInitiatedAdUnits;
    private ArrayList<AbstractAdapter> mInterstitialAdaptersList;
    private InterstitialManager mInterstitialManager;
    private IronSourceSegment mIronSegment;
    private boolean mIsBnLoadBeforeInitCompleted;
    private ListenersWrapper mListenersWrapper;
    private IronSourceLoggerManager mLoggerManager;
    private String mMediationType;
    private AbstractAdapter mOfferwallAdapter;
    private OfferwallManager mOfferwallManager;
    private PublisherLogger mPublisherLogger;
    private Set<IronSource.AD_UNIT> mRequestedAdUnits;
    private ArrayList<AbstractAdapter> mRewardedVideoAdaptersList;
    private RewardedVideoManager mRewardedVideoManager;
    private Map<String, String> mRvServerParams;
    private String mSegment;
    private final Object mServerResponseLocker;
    private String mSessionId;
    private boolean mShouldSendGetInstanceEvent;
    private Integer mUserAge;
    private String mUserGender;
    private String mUserId;
    
    private IronSourceObject() {
        this.TAG = this.getClass().getName();
        this.IRONSOURCE_VERSION_STRING = "!SDK-VERSION-STRING!:com.ironsource:mediationsdk:\u200b6.8.0";
        this.mServerResponseLocker = new Object();
        this.mCurrentServerResponse = null;
        this.mAppKey = null;
        this.mUserId = null;
        this.mUserAge = null;
        this.mUserGender = null;
        this.mSegment = null;
        this.mDynamicUserId = null;
        this.mRvServerParams = null;
        this.mMediationType = null;
        this.mInitSucceeded = false;
        this.mSessionId = null;
        this.mShouldSendGetInstanceEvent = true;
        this.KEY_INIT_COUNTER = "sessionDepth";
        this.mConsent = null;
        this.initializeManagers();
        this.mEventManagersInit = new AtomicBoolean();
        this.mRewardedVideoAdaptersList = new ArrayList<AbstractAdapter>();
        this.mInterstitialAdaptersList = new ArrayList<AbstractAdapter>();
        this.mBannerAdaptersList = new ArrayList<AbstractAdapter>();
        this.mAdUnitsToInitialize = new HashSet<IronSource.AD_UNIT>();
        this.mRequestedAdUnits = new HashSet<IronSource.AD_UNIT>();
        this.isDemandOnlyInterstitial = false;
        this.isDemandOnlyRewardedVideo = false;
        this.mAtomicIsFirstInit = new AtomicBoolean(true);
        this.mInitCounter = 0;
        this.mDidInitRewardedVideo = false;
        this.mDidInitInterstitial = false;
        this.mDidInitBanner = false;
        this.mSessionId = UUID.randomUUID().toString();
        this.mIsBnLoadBeforeInitCompleted = false;
        this.mBnPlacementToLoad = null;
    }
    
    private void attachAdUnits(final boolean p0, final IronSource.AD_UNIT... p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          4
        //     3: iconst_0       
        //     4: istore          5
        //     6: iconst_0       
        //     7: istore          6
        //     9: aload_0        
        //    10: monitorenter   
        //    11: aload_2        
        //    12: arraylength    
        //    13: istore          7
        //    15: iconst_0       
        //    16: istore_3       
        //    17: iload_3        
        //    18: iload           7
        //    20: if_icmpge       71
        //    23: aload_2        
        //    24: iload_3        
        //    25: aaload         
        //    26: astore          8
        //    28: aload           8
        //    30: getstatic       com/ironsource/mediationsdk/IronSource$AD_UNIT.INTERSTITIAL:Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //    33: invokevirtual   com/ironsource/mediationsdk/IronSource$AD_UNIT.equals:(Ljava/lang/Object;)Z
        //    36: ifeq            47
        //    39: aload_0        
        //    40: iconst_1       
        //    41: putfield        com/ironsource/mediationsdk/IronSourceObject.mDidInitInterstitial:Z
        //    44: goto            579
        //    47: aload           8
        //    49: getstatic       com/ironsource/mediationsdk/IronSource$AD_UNIT.BANNER:Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //    52: invokevirtual   com/ironsource/mediationsdk/IronSource$AD_UNIT.equals:(Ljava/lang/Object;)Z
        //    55: ifeq            579
        //    58: aload_0        
        //    59: iconst_1       
        //    60: putfield        com/ironsource/mediationsdk/IronSourceObject.mDidInitBanner:Z
        //    63: goto            579
        //    66: astore_2       
        //    67: aload_0        
        //    68: monitorexit    
        //    69: aload_2        
        //    70: athrow         
        //    71: invokestatic    com/ironsource/mediationsdk/MediationInitializer.getInstance:()Lcom/ironsource/mediationsdk/MediationInitializer;
        //    74: invokevirtual   com/ironsource/mediationsdk/MediationInitializer.getCurrentInitStatus:()Lcom/ironsource/mediationsdk/MediationInitializer$EInitStatus;
        //    77: astore          8
        //    79: getstatic       com/ironsource/mediationsdk/MediationInitializer$EInitStatus.INIT_FAILED:Lcom/ironsource/mediationsdk/MediationInitializer$EInitStatus;
        //    82: astore          9
        //    84: aload           8
        //    86: aload           9
        //    88: if_acmpne       152
        //    91: aload_0        
        //    92: getfield        com/ironsource/mediationsdk/IronSourceObject.mListenersWrapper:Lcom/ironsource/mediationsdk/sdk/ListenersWrapper;
        //    95: ifnull          149
        //    98: aload_2        
        //    99: arraylength    
        //   100: istore          4
        //   102: iload           6
        //   104: istore_3       
        //   105: iload_3        
        //   106: iload           4
        //   108: if_icmpge       149
        //   111: aload_2        
        //   112: iload_3        
        //   113: aaload         
        //   114: astore          8
        //   116: aload_0        
        //   117: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   120: aload           8
        //   122: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   127: ifne            137
        //   130: aload_0        
        //   131: aload           8
        //   133: iconst_1       
        //   134: invokespecial   com/ironsource/mediationsdk/IronSourceObject.notifyPublisherAboutInitFailed:(Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;Z)V
        //   137: iload_3        
        //   138: iconst_1       
        //   139: iadd           
        //   140: istore_3       
        //   141: goto            105
        //   144: astore_2       
        //   145: aload_2        
        //   146: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   149: aload_0        
        //   150: monitorexit    
        //   151: return         
        //   152: aload_0        
        //   153: getfield        com/ironsource/mediationsdk/IronSourceObject.mInitSucceeded:Z
        //   156: ifne            351
        //   159: iload_1        
        //   160: invokestatic    com/ironsource/mediationsdk/utils/IronSourceUtils.getMediationAdditionalData:(Z)Lorg/json/JSONObject;
        //   163: astore          8
        //   165: iconst_0       
        //   166: istore          5
        //   168: aload_2        
        //   169: arraylength    
        //   170: istore          6
        //   172: iload           4
        //   174: istore_3       
        //   175: iload           5
        //   177: istore          4
        //   179: iload_3        
        //   180: iload           6
        //   182: if_icmpge       295
        //   185: aload_2        
        //   186: iload_3        
        //   187: aaload         
        //   188: astore          9
        //   190: aload_0        
        //   191: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   194: aload           9
        //   196: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   201: ifne            260
        //   204: iconst_1       
        //   205: istore          4
        //   207: aload_0        
        //   208: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   211: aload           9
        //   213: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   218: pop            
        //   219: aload_0        
        //   220: getfield        com/ironsource/mediationsdk/IronSourceObject.mRequestedAdUnits:Ljava/util/Set;
        //   223: aload           9
        //   225: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   230: pop            
        //   231: aload           8
        //   233: aload           9
        //   235: invokevirtual   com/ironsource/mediationsdk/IronSource$AD_UNIT.toString:()Ljava/lang/String;
        //   238: iconst_1       
        //   239: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   242: pop            
        //   243: iload_3        
        //   244: iconst_1       
        //   245: iadd           
        //   246: istore_3       
        //   247: goto            179
        //   250: astore          9
        //   252: aload           9
        //   254: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   257: goto            243
        //   260: aload_0        
        //   261: getfield        com/ironsource/mediationsdk/IronSourceObject.mLoggerManager:Lcom/ironsource/mediationsdk/logger/IronSourceLoggerManager;
        //   264: getstatic       com/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag.API:Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;
        //   267: new             Ljava/lang/StringBuilder;
        //   270: dup            
        //   271: invokespecial   java/lang/StringBuilder.<init>:()V
        //   274: aload           9
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   279: ldc_w           " ad unit has started initializing."
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   288: iconst_3       
        //   289: invokevirtual   com/ironsource/mediationsdk/logger/IronSourceLoggerManager.log:(Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;Ljava/lang/String;I)V
        //   292: goto            243
        //   295: iload           4
        //   297: ifeq            149
        //   300: aload_0        
        //   301: getfield        com/ironsource/mediationsdk/IronSourceObject.mInitCounter:I
        //   304: iconst_1       
        //   305: iadd           
        //   306: istore_3       
        //   307: aload_0        
        //   308: iload_3        
        //   309: putfield        com/ironsource/mediationsdk/IronSourceObject.mInitCounter:I
        //   312: aload           8
        //   314: ldc             "sessionDepth"
        //   316: iload_3        
        //   317: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   320: pop            
        //   321: new             Lcom/ironsource/eventsmodule/EventData;
        //   324: dup            
        //   325: bipush          14
        //   327: aload           8
        //   329: invokespecial   com/ironsource/eventsmodule/EventData.<init>:(ILorg/json/JSONObject;)V
        //   332: astore_2       
        //   333: invokestatic    com/ironsource/mediationsdk/events/RewardedVideoEventsManager.getInstance:()Lcom/ironsource/mediationsdk/events/RewardedVideoEventsManager;
        //   336: aload_2        
        //   337: invokevirtual   com/ironsource/mediationsdk/events/RewardedVideoEventsManager.log:(Lcom/ironsource/eventsmodule/EventData;)V
        //   340: goto            149
        //   343: astore_2       
        //   344: aload_2        
        //   345: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   348: goto            321
        //   351: aload_0        
        //   352: getfield        com/ironsource/mediationsdk/IronSourceObject.mInitiatedAdUnits:Ljava/util/List;
        //   355: ifnull          149
        //   358: iload_1        
        //   359: invokestatic    com/ironsource/mediationsdk/utils/IronSourceUtils.getMediationAdditionalData:(Z)Lorg/json/JSONObject;
        //   362: astore          8
        //   364: iconst_0       
        //   365: istore          4
        //   367: aload_2        
        //   368: arraylength    
        //   369: istore          6
        //   371: iload           5
        //   373: istore_3       
        //   374: iload_3        
        //   375: iload           6
        //   377: if_icmpge       523
        //   380: aload_2        
        //   381: iload_3        
        //   382: aaload         
        //   383: astore          9
        //   385: aload_0        
        //   386: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   389: aload           9
        //   391: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   396: ifne            488
        //   399: iconst_1       
        //   400: istore          4
        //   402: aload_0        
        //   403: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   406: aload           9
        //   408: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   413: pop            
        //   414: aload_0        
        //   415: getfield        com/ironsource/mediationsdk/IronSourceObject.mRequestedAdUnits:Ljava/util/Set;
        //   418: aload           9
        //   420: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   425: pop            
        //   426: aload           8
        //   428: aload           9
        //   430: invokevirtual   com/ironsource/mediationsdk/IronSource$AD_UNIT.toString:()Ljava/lang/String;
        //   433: iconst_1       
        //   434: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   437: pop            
        //   438: aload_0        
        //   439: getfield        com/ironsource/mediationsdk/IronSourceObject.mInitiatedAdUnits:Ljava/util/List;
        //   442: ifnull          478
        //   445: aload_0        
        //   446: getfield        com/ironsource/mediationsdk/IronSourceObject.mInitiatedAdUnits:Ljava/util/List;
        //   449: aload           9
        //   451: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   456: ifeq            478
        //   459: aload_0        
        //   460: aload           9
        //   462: invokespecial   com/ironsource/mediationsdk/IronSourceObject.startAdUnit:(Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;)V
        //   465: goto            586
        //   468: astore          10
        //   470: aload           10
        //   472: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   475: goto            438
        //   478: aload_0        
        //   479: aload           9
        //   481: iconst_0       
        //   482: invokespecial   com/ironsource/mediationsdk/IronSourceObject.notifyPublisherAboutInitFailed:(Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;Z)V
        //   485: goto            586
        //   488: aload_0        
        //   489: getfield        com/ironsource/mediationsdk/IronSourceObject.mLoggerManager:Lcom/ironsource/mediationsdk/logger/IronSourceLoggerManager;
        //   492: getstatic       com/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag.API:Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;
        //   495: new             Ljava/lang/StringBuilder;
        //   498: dup            
        //   499: invokespecial   java/lang/StringBuilder.<init>:()V
        //   502: aload           9
        //   504: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   507: ldc_w           " ad unit has already been initialized"
        //   510: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   513: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   516: iconst_3       
        //   517: invokevirtual   com/ironsource/mediationsdk/logger/IronSourceLoggerManager.log:(Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;Ljava/lang/String;I)V
        //   520: goto            586
        //   523: iload           4
        //   525: ifeq            149
        //   528: aload_0        
        //   529: getfield        com/ironsource/mediationsdk/IronSourceObject.mInitCounter:I
        //   532: iconst_1       
        //   533: iadd           
        //   534: istore_3       
        //   535: aload_0        
        //   536: iload_3        
        //   537: putfield        com/ironsource/mediationsdk/IronSourceObject.mInitCounter:I
        //   540: aload           8
        //   542: ldc             "sessionDepth"
        //   544: iload_3        
        //   545: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   548: pop            
        //   549: new             Lcom/ironsource/eventsmodule/EventData;
        //   552: dup            
        //   553: bipush          14
        //   555: aload           8
        //   557: invokespecial   com/ironsource/eventsmodule/EventData.<init>:(ILorg/json/JSONObject;)V
        //   560: astore_2       
        //   561: invokestatic    com/ironsource/mediationsdk/events/RewardedVideoEventsManager.getInstance:()Lcom/ironsource/mediationsdk/events/RewardedVideoEventsManager;
        //   564: aload_2        
        //   565: invokevirtual   com/ironsource/mediationsdk/events/RewardedVideoEventsManager.log:(Lcom/ironsource/eventsmodule/EventData;)V
        //   568: goto            149
        //   571: astore_2       
        //   572: aload_2        
        //   573: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   576: goto            549
        //   579: iload_3        
        //   580: iconst_1       
        //   581: iadd           
        //   582: istore_3       
        //   583: goto            17
        //   586: iload_3        
        //   587: iconst_1       
        //   588: iadd           
        //   589: istore_3       
        //   590: goto            374
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  11     15     66     71     Any
        //  28     44     66     71     Any
        //  47     63     66     71     Any
        //  71     84     66     71     Any
        //  91     102    144    149    Ljava/lang/Exception;
        //  91     102    66     71     Any
        //  116    137    144    149    Ljava/lang/Exception;
        //  116    137    66     71     Any
        //  145    149    66     71     Any
        //  152    165    66     71     Any
        //  168    172    66     71     Any
        //  190    204    66     71     Any
        //  207    231    66     71     Any
        //  231    243    250    260    Ljava/lang/Exception;
        //  231    243    66     71     Any
        //  252    257    66     71     Any
        //  260    292    66     71     Any
        //  300    321    343    351    Ljava/lang/Exception;
        //  300    321    66     71     Any
        //  321    340    66     71     Any
        //  344    348    66     71     Any
        //  351    364    66     71     Any
        //  367    371    66     71     Any
        //  385    399    66     71     Any
        //  402    426    66     71     Any
        //  426    438    468    478    Ljava/lang/Exception;
        //  426    438    66     71     Any
        //  438    465    66     71     Any
        //  470    475    66     71     Any
        //  478    485    66     71     Any
        //  488    520    66     71     Any
        //  528    549    571    579    Ljava/lang/Exception;
        //  528    549    66     71     Any
        //  549    568    66     71     Any
        //  572    576    66     71     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    private ServerResponseWrapper connectAndGetServerResponse(final Context p0, final String p1, final IResponseListener p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    com/ironsource/mediationsdk/utils/IronSourceUtils.isNetworkConnected:(Landroid/content/Context;)Z
        //     4: ifne            9
        //     7: aconst_null    
        //     8: areturn        
        //     9: aconst_null    
        //    10: astore          7
        //    12: aload_0        
        //    13: aload_1        
        //    14: invokevirtual   com/ironsource/mediationsdk/IronSourceObject.getAdvertiserId:(Landroid/content/Context;)Ljava/lang/String;
        //    17: astore          6
        //    19: aload           6
        //    21: astore          5
        //    23: aload           6
        //    25: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    28: ifeq            50
        //    31: aload_1        
        //    32: invokestatic    com/ironsource/environment/DeviceStatus.getOrGenerateOnceUniqueIdentifier:(Landroid/content/Context;)Ljava/lang/String;
        //    35: astore          5
        //    37: invokestatic    com/ironsource/mediationsdk/logger/IronSourceLoggerManager.getLogger:()Lcom/ironsource/mediationsdk/logger/IronSourceLoggerManager;
        //    40: getstatic       com/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag.INTERNAL:Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;
        //    43: ldc_w           "using custom identifier"
        //    46: iconst_1       
        //    47: invokevirtual   com/ironsource/mediationsdk/logger/IronSourceLoggerManager.log:(Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;Ljava/lang/String;I)V
        //    50: aconst_null    
        //    51: astore          6
        //    53: aload_0        
        //    54: getfield        com/ironsource/mediationsdk/IronSourceObject.mIronSegment:Lcom/ironsource/mediationsdk/IronSourceSegment;
        //    57: ifnull          69
        //    60: aload_0        
        //    61: getfield        com/ironsource/mediationsdk/IronSourceObject.mIronSegment:Lcom/ironsource/mediationsdk/IronSourceSegment;
        //    64: invokevirtual   com/ironsource/mediationsdk/IronSourceSegment.getSegmentData:()Ljava/util/Vector;
        //    67: astore          6
        //    69: aload_0        
        //    70: invokevirtual   com/ironsource/mediationsdk/IronSourceObject.getMediationType:()Ljava/lang/String;
        //    73: astore          8
        //    75: aload_1        
        //    76: aload_0        
        //    77: invokevirtual   com/ironsource/mediationsdk/IronSourceObject.getIronSourceAppKey:()Ljava/lang/String;
        //    80: aload_2        
        //    81: aload           5
        //    83: aload           8
        //    85: aload           6
        //    87: invokestatic    com/ironsource/mediationsdk/server/ServerURL.getCPVProvidersURL:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Vector;)Ljava/lang/String;
        //    90: aload_3        
        //    91: invokestatic    com/ironsource/mediationsdk/server/HttpFunctions.getStringFromURL:(Ljava/lang/String;Lcom/ironsource/mediationsdk/IronSourceObject$IResponseListener;)Ljava/lang/String;
        //    94: astore          5
        //    96: aload           5
        //    98: ifnonnull       103
        //   101: aconst_null    
        //   102: areturn        
        //   103: aload           5
        //   105: astore_3       
        //   106: invokestatic    com/ironsource/mediationsdk/utils/IronSourceUtils.getSerr:()I
        //   109: iconst_1       
        //   110: if_icmpne       147
        //   113: new             Lorg/json/JSONObject;
        //   116: dup            
        //   117: aload           5
        //   119: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   122: ldc_w           "response"
        //   125: aconst_null    
        //   126: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   129: astore_3       
        //   130: aload_3        
        //   131: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   134: ifeq            139
        //   137: aconst_null    
        //   138: areturn        
        //   139: ldc_w           "C38FB23A402222A0C17D34A92F971D1F"
        //   142: aload_3        
        //   143: invokestatic    com/ironsource/mediationsdk/utils/IronSourceAES.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   146: astore_3       
        //   147: new             Lcom/ironsource/mediationsdk/utils/ServerResponseWrapper;
        //   150: dup            
        //   151: aload_1        
        //   152: aload_0        
        //   153: invokevirtual   com/ironsource/mediationsdk/IronSourceObject.getIronSourceAppKey:()Ljava/lang/String;
        //   156: aload_2        
        //   157: aload_3        
        //   158: invokespecial   com/ironsource/mediationsdk/utils/ServerResponseWrapper.<init>:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   161: astore_2       
        //   162: aload_2        
        //   163: invokevirtual   com/ironsource/mediationsdk/utils/ServerResponseWrapper.isValidResponse:()Z
        //   166: istore          4
        //   168: iload           4
        //   170: ifne            175
        //   173: aconst_null    
        //   174: areturn        
        //   175: aload_2        
        //   176: areturn        
        //   177: astore_1       
        //   178: aload           7
        //   180: astore_2       
        //   181: aload_1        
        //   182: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   185: aload_2        
        //   186: areturn        
        //   187: astore_1       
        //   188: goto            181
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     19     177    181    Ljava/lang/Exception;
        //  23     50     177    181    Ljava/lang/Exception;
        //  53     69     177    181    Ljava/lang/Exception;
        //  69     96     177    181    Ljava/lang/Exception;
        //  106    137    177    181    Ljava/lang/Exception;
        //  139    147    177    181    Ljava/lang/Exception;
        //  147    162    177    181    Ljava/lang/Exception;
        //  162    168    187    191    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0175:
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
    
    private BannerPlacement getBannerPlacement(final String s) {
        BannerPlacement bannerPlacement;
        if (TextUtils.isEmpty((CharSequence)s)) {
            bannerPlacement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getDefaultBannerPlacement();
        }
        else if ((bannerPlacement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerPlacement(s)) == null) {
            return this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getDefaultBannerPlacement();
        }
        return bannerPlacement;
    }
    
    private ServerResponseWrapper getCachedResponse(Context buildUsingCachedConfigurationError, final String s) {
        final ServerResponseWrapper serverResponseWrapper = null;
        final String lastResponse = IronSourceUtils.getLastResponse(buildUsingCachedConfigurationError);
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject(lastResponse);
                final String optString = jsonObject.optString("appKey");
                final String optString2 = jsonObject.optString("userId");
                final String optString3 = jsonObject.optString("response");
                ServerResponseWrapper serverResponseWrapper2 = serverResponseWrapper;
                if (!TextUtils.isEmpty((CharSequence)optString)) {
                    serverResponseWrapper2 = serverResponseWrapper;
                    if (!TextUtils.isEmpty((CharSequence)optString2)) {
                        serverResponseWrapper2 = serverResponseWrapper;
                        if (!TextUtils.isEmpty((CharSequence)optString3)) {
                            serverResponseWrapper2 = serverResponseWrapper;
                            if (this.getIronSourceAppKey() != null) {
                                serverResponseWrapper2 = serverResponseWrapper;
                                if (optString.equals(this.getIronSourceAppKey())) {
                                    serverResponseWrapper2 = serverResponseWrapper;
                                    if (optString2.equals(s)) {
                                        serverResponseWrapper2 = new ServerResponseWrapper(buildUsingCachedConfigurationError, optString, optString2, optString3);
                                        buildUsingCachedConfigurationError = (Context)ErrorBuilder.buildUsingCachedConfigurationError(optString, optString2);
                                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ((IronSourceError)buildUsingCachedConfigurationError).toString(), 1);
                                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ((IronSourceError)buildUsingCachedConfigurationError).toString() + ": " + serverResponseWrapper2.toString(), 1);
                                        buildUsingCachedConfigurationError = (Context)new EventData(140, IronSourceUtils.getMediationAdditionalData(false));
                                        RewardedVideoEventsManager.getInstance().log((EventData)buildUsingCachedConfigurationError);
                                    }
                                }
                            }
                        }
                    }
                }
                return serverResponseWrapper2;
            }
            catch (JSONException ex) {
                final JSONObject jsonObject = new JSONObject();
                continue;
            }
            break;
        }
    }
    
    public static IronSourceObject getInstance() {
        synchronized (IronSourceObject.class) {
            if (IronSourceObject.sInstance == null) {
                IronSourceObject.sInstance = new IronSourceObject();
            }
            return IronSourceObject.sInstance;
        }
    }
    
    private CappingManager.ECappingStatus getInterstitialCappingStatus(final String s) {
        if (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations() == null) {
            return CappingManager.ECappingStatus.NOT_CAPPED;
        }
        InterstitialPlacement interstitialPlacement = null;
        while (true) {
            try {
                final InterstitialPlacement interstitialPlacement2 = interstitialPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialPlacement(s);
                if (interstitialPlacement2 == null) {
                    interstitialPlacement = interstitialPlacement2;
                    final InterstitialPlacement defaultInterstitialPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getDefaultInterstitialPlacement();
                    if ((interstitialPlacement = defaultInterstitialPlacement) == null) {
                        interstitialPlacement = defaultInterstitialPlacement;
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found", 3);
                        interstitialPlacement = defaultInterstitialPlacement;
                    }
                }
                if (interstitialPlacement == null) {
                    return CappingManager.ECappingStatus.NOT_CAPPED;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
        return CappingManager.isPlacementCapped((Context)this.mActivity, interstitialPlacement);
    }
    
    private InterstitialPlacement getInterstitialPlacementToShowWithEvent(final String s) {
        InterstitialPlacement interstitialPlacement;
        if ((interstitialPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialPlacement(s)) == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
            if ((interstitialPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getDefaultInterstitialPlacement()) == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                return null;
            }
        }
        final String cappingMessage = this.getCappingMessage(interstitialPlacement.getPlacementName(), this.getInterstitialCappingStatus(interstitialPlacement.getPlacementName()));
        if (!TextUtils.isEmpty((CharSequence)cappingMessage)) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, cappingMessage, 1);
            this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildCappedPerPlacementError("Interstitial", cappingMessage));
            return null;
        }
        return interstitialPlacement;
    }
    
    private Placement getPlacementToShowWithEvent(final String s) {
        Placement placement;
        if ((placement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoPlacement(s)) == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
            if ((placement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement()) == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                return null;
            }
        }
        final String cappingMessage = this.getCappingMessage(placement.getPlacementName(), CappingManager.isPlacementCapped((Context)this.mActivity, placement));
        if (!TextUtils.isEmpty((CharSequence)cappingMessage)) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, cappingMessage, 1);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildCappedPerPlacementError("Rewarded Video", cappingMessage));
            return null;
        }
        return placement;
    }
    
    private CappingManager.ECappingStatus getRewardedVideoCappingStatus(final String s) {
        if (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations() == null) {
            return CappingManager.ECappingStatus.NOT_CAPPED;
        }
        Placement rewardedVideoPlacement = null;
        while (true) {
            try {
                final Placement placement = rewardedVideoPlacement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoPlacement(s);
                if (placement == null) {
                    rewardedVideoPlacement = placement;
                    final Placement defaultRewardedVideoPlacement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
                    if ((rewardedVideoPlacement = defaultRewardedVideoPlacement) == null) {
                        rewardedVideoPlacement = defaultRewardedVideoPlacement;
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found", 3);
                        rewardedVideoPlacement = defaultRewardedVideoPlacement;
                    }
                }
                if (rewardedVideoPlacement == null) {
                    return CappingManager.ECappingStatus.NOT_CAPPED;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
        return CappingManager.isPlacementCapped((Context)this.mActivity, rewardedVideoPlacement);
    }
    
    private void initializeEventsSettings(final ServerResponseWrapper serverResponseWrapper, final Context context) {
        boolean eventsEnabled = false;
        if (this.isRewardedVideoConfigurationsReady()) {
            eventsEnabled = serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().isEventsEnabled();
        }
        boolean eventsEnabled2 = false;
        if (this.isInterstitialConfigurationsReady()) {
            eventsEnabled2 = serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().isEventsEnabled();
        }
        boolean eventsEnabled3 = false;
        if (this.isBannerConfigurationsReady()) {
            eventsEnabled3 = serverResponseWrapper.getConfigurations().getBannerConfigurations().getBannerEventsConfigurations().isEventsEnabled();
        }
        if (eventsEnabled) {
            RewardedVideoEventsManager.getInstance().setFormatterType(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsType(), context);
            RewardedVideoEventsManager.getInstance().setEventsUrl(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsURL(), context);
            RewardedVideoEventsManager.getInstance().setMaxNumberOfEvents(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getMaxNumberOfEvents());
            RewardedVideoEventsManager.getInstance().setMaxEventsPerBatch(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getMaxEventsPerBatch());
            RewardedVideoEventsManager.getInstance().setBackupThreshold(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsBackupThreshold());
            RewardedVideoEventsManager.getInstance().setOptOutEvents(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getOptOutEvents(), context);
            RewardedVideoEventsManager.getInstance().setServerSegmentData(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData());
        }
        else {
            RewardedVideoEventsManager.getInstance().setIsEventsEnabled(false);
        }
        if (eventsEnabled2) {
            InterstitialEventsManager.getInstance().setFormatterType(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsType(), context);
            InterstitialEventsManager.getInstance().setEventsUrl(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsURL(), context);
            InterstitialEventsManager.getInstance().setMaxNumberOfEvents(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getMaxNumberOfEvents());
            InterstitialEventsManager.getInstance().setMaxEventsPerBatch(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getMaxEventsPerBatch());
            InterstitialEventsManager.getInstance().setBackupThreshold(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsBackupThreshold());
            InterstitialEventsManager.getInstance().setOptOutEvents(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getOptOutEvents(), context);
            InterstitialEventsManager.getInstance().setServerSegmentData(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData());
            return;
        }
        if (eventsEnabled3) {
            final ApplicationEvents bannerEventsConfigurations = serverResponseWrapper.getConfigurations().getBannerConfigurations().getBannerEventsConfigurations();
            InterstitialEventsManager.getInstance().setFormatterType(bannerEventsConfigurations.getEventsType(), context);
            InterstitialEventsManager.getInstance().setEventsUrl(bannerEventsConfigurations.getEventsURL(), context);
            InterstitialEventsManager.getInstance().setMaxNumberOfEvents(bannerEventsConfigurations.getMaxNumberOfEvents());
            InterstitialEventsManager.getInstance().setMaxEventsPerBatch(bannerEventsConfigurations.getMaxEventsPerBatch());
            InterstitialEventsManager.getInstance().setBackupThreshold(bannerEventsConfigurations.getEventsBackupThreshold());
            InterstitialEventsManager.getInstance().setOptOutEvents(bannerEventsConfigurations.getOptOutEvents(), context);
            InterstitialEventsManager.getInstance().setServerSegmentData(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData());
            return;
        }
        InterstitialEventsManager.getInstance().setIsEventsEnabled(false);
    }
    
    private void initializeLoggerManager(final ServerResponseWrapper serverResponseWrapper) {
        this.mPublisherLogger.setDebugLevel(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getLoggerConfigurations().getPublisherLoggerLevel());
        this.mLoggerManager.setLoggerDebugLevel("console", serverResponseWrapper.getConfigurations().getApplicationConfigurations().getLoggerConfigurations().getConsoleLoggerLevel());
    }
    
    private void initializeManagers() {
        this.mLoggerManager = IronSourceLoggerManager.getLogger(0);
        this.mPublisherLogger = new PublisherLogger((LogListener)null, 1);
        this.mLoggerManager.addLogger(this.mPublisherLogger);
        this.mListenersWrapper = new ListenersWrapper();
        (this.mRewardedVideoManager = new RewardedVideoManager()).setRewardedVideoListener(this.mListenersWrapper);
        this.mRewardedVideoManager.setISDemandOnlyRewardedVideoListener(this.mListenersWrapper);
        (this.mInterstitialManager = new InterstitialManager()).setInterstitialListener(this.mListenersWrapper);
        this.mInterstitialManager.setRewardedInterstitialListener(this.mListenersWrapper);
        this.mInterstitialManager.setISDemandOnlyInterstitialListener(this.mListenersWrapper);
        (this.mOfferwallManager = new OfferwallManager()).setInternalOfferwallListener(this.mListenersWrapper);
        this.mBannerManager = new BannerManager();
    }
    
    private void initializeSettingsFromServerResponse(final ServerResponseWrapper serverResponseWrapper, final Context context) {
        this.initializeLoggerManager(serverResponseWrapper);
        this.initializeEventsSettings(serverResponseWrapper, context);
    }
    
    private boolean isBannerConfigurationsReady() {
        return this.mCurrentServerResponse != null && this.mCurrentServerResponse.getConfigurations() != null && this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() != null;
    }
    
    private boolean isInterstitialConfigurationsReady() {
        return this.mCurrentServerResponse != null && this.mCurrentServerResponse.getConfigurations() != null && this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations() != null;
    }
    
    private boolean isOfferwallConfigurationsReady() {
        return this.mCurrentServerResponse != null && this.mCurrentServerResponse.getConfigurations() != null && this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations() != null;
    }
    
    private boolean isRewardedVideoConfigurationsReady() {
        return this.mCurrentServerResponse != null && this.mCurrentServerResponse.getConfigurations() != null && this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations() != null;
    }
    
    private void notifyPublisherAboutInitFailed(final IronSource.AD_UNIT ad_UNIT, final boolean b) {
        switch (ad_UNIT) {
            case REWARDED_VIDEO: {
                if (b || this.isRewardedVideoConfigurationsReady() || this.mRequestedAdUnits.contains(ad_UNIT)) {
                    this.mListenersWrapper.onRewardedVideoAvailabilityChanged(false);
                    return;
                }
                break;
            }
            case OFFERWALL: {
                if (b || this.isOfferwallConfigurationsReady() || this.mRequestedAdUnits.contains(ad_UNIT)) {
                    this.mListenersWrapper.onOfferwallAvailable(false);
                    return;
                }
                break;
            }
            case BANNER: {
                if (this.mIsBnLoadBeforeInitCompleted) {
                    this.mIsBnLoadBeforeInitCompleted = false;
                    BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mBnLayoutToLoad, new IronSourceError(602, "Init had failed"));
                    this.mBnLayoutToLoad = null;
                    this.mBnPlacementToLoad = null;
                    return;
                }
                break;
            }
        }
    }
    
    private void prepareEventManagers(final Activity activity) {
        if (this.mEventManagersInit != null && this.mEventManagersInit.compareAndSet(false, true)) {
            SuperLooper.getLooper().post(new GeneralPropertiesWorker(activity.getApplicationContext()));
            InterstitialEventsManager.getInstance().start(activity.getApplicationContext(), this.mIronSegment);
            RewardedVideoEventsManager.getInstance().start(activity.getApplicationContext(), this.mIronSegment);
        }
    }
    
    private void sendIsCappedEvent(String s, final boolean b) {
        if (b) {
            boolean b2 = false;
            JSONObject mediationAdditionalData = null;
            Label_0065: {
                if (!s.equals("Interstitial")) {
                    break Label_0065;
                }
                b2 = this.isDemandOnlyInterstitial;
            Label_0036_Outer:
                while (true) {
                    mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(b2);
                    while (true) {
                        try {
                            mediationAdditionalData.put("reason", 1);
                            if ("Interstitial".equals(s)) {
                                s = (String)new EventData(34, mediationAdditionalData);
                                InterstitialEventsManager.getInstance().log((EventData)s);
                                return;
                            }
                            break;
                            // iftrue(Label_0022:, !s.equals((Object)"Rewarded Video"))
                            b2 = this.isDemandOnlyRewardedVideo;
                            continue Label_0036_Outer;
                        }
                        catch (JSONException ex) {
                            ex.printStackTrace();
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
            if ("Rewarded Video".equals(s)) {
                RewardedVideoEventsManager.getInstance().log(new EventData(20, mediationAdditionalData));
            }
        }
    }
    
    private void startAdUnit(final IronSource.AD_UNIT ad_UNIT) {
        switch (ad_UNIT) {
            default: {}
            case REWARDED_VIDEO: {
                this.startRewardedVideo();
            }
            case INTERSTITIAL: {
                this.startInterstitial();
            }
            case OFFERWALL: {
                this.mOfferwallManager.initOfferwall(this.mActivity, this.getIronSourceAppKey(), this.getIronSourceUserId());
            }
            case BANNER: {
                this.startBanner();
            }
        }
    }
    
    private void startBanner() {
        final long bannerAdaptersSmartLoadTimeout = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerAdaptersSmartLoadTimeout();
        final int bannerRefreshInterval = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerRefreshInterval();
        final ArrayList<ProviderSettings> list = new ArrayList<ProviderSettings>();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getBannerProviderOrder().size(); ++i) {
            final String s = this.mCurrentServerResponse.getProviderOrder().getBannerProviderOrder().get(i);
            if (!TextUtils.isEmpty((CharSequence)s)) {
                final ProviderSettings providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(s);
                if (providerSettings != null) {
                    list.add(providerSettings);
                }
            }
        }
        this.mBannerManager.initBannerManager(list, this.mActivity, this.getIronSourceAppKey(), this.getIronSourceUserId(), bannerAdaptersSmartLoadTimeout, bannerRefreshInterval);
        if (this.mIsBnLoadBeforeInitCompleted) {
            this.mIsBnLoadBeforeInitCompleted = false;
            this.loadBanner(this.mBnLayoutToLoad, this.mBnPlacementToLoad);
            this.mBnLayoutToLoad = null;
            this.mBnPlacementToLoad = null;
        }
    }
    
    private void startInterstitial() {
        if (this.isDemandOnlyInterstitial) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Interstitial started in Demand Only mode", 0);
        }
        final int interstitialAdaptersSmartLoadTimeout = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialAdaptersSmartLoadTimeout();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().size(); ++i) {
            final String s = this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().get(i);
            if (!TextUtils.isEmpty((CharSequence)s)) {
                final ProviderSettings providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(s);
                if (providerSettings != null) {
                    final InterstitialSmash interstitialSmash = new InterstitialSmash(providerSettings, interstitialAdaptersSmartLoadTimeout);
                    if (this.validateSmash(interstitialSmash)) {
                        interstitialSmash.setInterstitialManagerListener(this.mInterstitialManager);
                        interstitialSmash.setProviderPriority(i + 1);
                        this.mInterstitialManager.addSmashToArray(interstitialSmash);
                    }
                }
            }
        }
        if (this.mInterstitialManager.mSmashArray.size() > 0) {
            this.mInterstitialManager.setSmartLoadAmount(this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialAdaptersSmartLoadAmount());
            this.mInterstitialManager.initInterstitial(this.mActivity, this.getIronSourceAppKey(), this.getIronSourceUserId());
            return;
        }
        this.notifyPublisherAboutInitFailed(IronSource.AD_UNIT.INTERSTITIAL, false);
    }
    
    private void startRewardedVideo() {
        if (this.isDemandOnlyRewardedVideo) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Rewarded Video started in Demand Only mode", 0);
        }
        final int rewardedVideoAdaptersSmartLoadTimeout = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoAdaptersSmartLoadTimeout();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().size(); ++i) {
            final String s = this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().get(i);
            if (!TextUtils.isEmpty((CharSequence)s)) {
                final ProviderSettings providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(s);
                if (providerSettings != null) {
                    final RewardedVideoSmash rewardedVideoSmash = new RewardedVideoSmash(providerSettings, rewardedVideoAdaptersSmartLoadTimeout);
                    if (this.validateSmash(rewardedVideoSmash)) {
                        rewardedVideoSmash.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                        rewardedVideoSmash.setProviderPriority(i + 1);
                        this.mRewardedVideoManager.addSmashToArray(rewardedVideoSmash);
                    }
                }
            }
        }
        if (this.mRewardedVideoManager.mSmashArray.size() > 0) {
            this.mRewardedVideoManager.setIsUltraEventsEnabled(this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().isUltraEventsEnabled());
            this.mRewardedVideoManager.setSmartLoadAmount(this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoAdaptersSmartLoadAmount());
            final String rvBackFillProvider = this.mCurrentServerResponse.getRVBackFillProvider();
            if (!TextUtils.isEmpty((CharSequence)rvBackFillProvider)) {
                final ProviderSettings providerSettings2 = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(rvBackFillProvider);
                if (providerSettings2 != null) {
                    final RewardedVideoSmash backfillSmash = new RewardedVideoSmash(providerSettings2, rewardedVideoAdaptersSmartLoadTimeout);
                    if (this.validateSmash(backfillSmash)) {
                        backfillSmash.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                        this.mRewardedVideoManager.setBackfillSmash(backfillSmash);
                    }
                }
            }
            final String rvPremiumProvider = this.mCurrentServerResponse.getRVPremiumProvider();
            if (!TextUtils.isEmpty((CharSequence)rvPremiumProvider)) {
                final ProviderSettings providerSettings3 = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(rvPremiumProvider);
                if (providerSettings3 != null) {
                    final RewardedVideoSmash premiumSmash = new RewardedVideoSmash(providerSettings3, rewardedVideoAdaptersSmartLoadTimeout);
                    if (this.validateSmash(premiumSmash)) {
                        premiumSmash.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                        this.mRewardedVideoManager.setPremiumSmash(premiumSmash);
                    }
                }
            }
            this.mRewardedVideoManager.initRewardedVideo(this.mActivity, this.getIronSourceAppKey(), this.getIronSourceUserId());
            return;
        }
        this.notifyPublisherAboutInitFailed(IronSource.AD_UNIT.REWARDED_VIDEO, false);
    }
    
    private void validateAge(final int n, final ConfigValidationResult configValidationResult) {
        if (n >= 5) {
            if (n <= 120) {
                return;
            }
        }
        try {
            configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("age", "SupersonicAds", "age value should be between 5-120"));
        }
        catch (NumberFormatException ex) {
            configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("age", "SupersonicAds", "age value should be between 5-120"));
        }
    }
    
    private boolean validateAlphanumeric(final String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }
    
    private ConfigValidationResult validateAppKey(final String s) {
        final ConfigValidationResult configValidationResult = new ConfigValidationResult();
        if (s == null) {
            configValidationResult.setInvalid(new IronSourceError(506, "Init Fail - appKey is missing"));
            return configValidationResult;
        }
        if (this.validateLength(s, 5, 10)) {
            if (!this.validateAlphanumeric(s)) {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidCredentialsError("appKey", s, "should contain only english characters and numbers"));
            }
            return configValidationResult;
        }
        configValidationResult.setInvalid(ErrorBuilder.buildInvalidCredentialsError("appKey", s, "length should be between 5-10 characters"));
        return configValidationResult;
    }
    
    private void validateDynamicUserId(final String s, final ConfigValidationResult configValidationResult) {
        if (!this.validateLength(s, 1, 128)) {
            configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("dynamicUserId", "SupersonicAds", "dynamicUserId is invalid, should be between 1-128 chars in length."));
        }
    }
    
    private void validateGender(String trim, final ConfigValidationResult configValidationResult) {
        if (trim == null) {
            return;
        }
        try {
            trim = trim.toLowerCase().trim();
            if (!"male".equals(trim) && !"female".equals(trim) && !"unknown".equals(trim)) {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("gender", "SupersonicAds", "gender value should be one of male/female/unknown."));
            }
        }
        catch (Exception ex) {
            configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("gender", "SupersonicAds", "gender value should be one of male/female/unknown."));
        }
    }
    
    private boolean validateLength(final String s, final int n, final int n2) {
        return s != null && s.length() >= n && s.length() <= n2;
    }
    
    private void validateSegment(final String s, final ConfigValidationResult configValidationResult) {
        if (s == null) {
            return;
        }
        try {
            if (s.length() > 64) {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("segment", "SupersonicAds", "segment value should not exceed 64 characters."));
            }
        }
        catch (Exception ex) {
            configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("segment", "SupersonicAds", "segment value should not exceed 64 characters."));
        }
    }
    
    private boolean validateSmash(final AbstractSmash abstractSmash) {
        return abstractSmash.getMaxAdsPerIteration() >= 1 && abstractSmash.getMaxAdsPerSession() >= 1;
    }
    
    void addOWAdapter(final AbstractAdapter mOfferwallAdapter) {
        synchronized (this) {
            this.mOfferwallAdapter = mOfferwallAdapter;
        }
    }
    
    void addToBannerAdaptersList(final AbstractAdapter abstractAdapter) {
        synchronized (this) {
            if (this.mBannerAdaptersList != null && abstractAdapter != null && !this.mBannerAdaptersList.contains(abstractAdapter)) {
                this.mBannerAdaptersList.add(abstractAdapter);
            }
        }
    }
    
    void addToISAdaptersList(final AbstractAdapter abstractAdapter) {
        synchronized (this) {
            if (this.mInterstitialAdaptersList != null && abstractAdapter != null && !this.mInterstitialAdaptersList.contains(abstractAdapter)) {
                this.mInterstitialAdaptersList.add(abstractAdapter);
            }
        }
    }
    
    void addToRVAdaptersList(final AbstractAdapter abstractAdapter) {
        synchronized (this) {
            if (this.mRewardedVideoAdaptersList != null && abstractAdapter != null && !this.mRewardedVideoAdaptersList.contains(abstractAdapter)) {
                this.mRewardedVideoAdaptersList.add(abstractAdapter);
            }
        }
    }
    
    @Override
    public void clearRewardedVideoServerParameters() {
        this.mRvServerParams = null;
    }
    
    public IronSourceBannerLayout createBanner(final Activity activity, final ISBannerSize isBannerSize) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "createBanner()", 1);
        if (activity == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "createBanner() : Activity cannot be null", 3);
            return null;
        }
        return this.mBannerManager.createBanner(activity, isBannerSize);
    }
    
    public void destroyBanner(final IronSourceBannerLayout ironSourceBannerLayout) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "destroyBanner()", 1);
        try {
            this.mBannerManager.destroyBanner(ironSourceBannerLayout);
        }
        catch (Throwable t) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "destroyBanner()", t);
        }
    }
    
    @Override
    public String getAdvertiserId(final Context context) {
        try {
            final String[] advertisingIdInfo = DeviceStatus.getAdvertisingIdInfo(context);
            if (advertisingIdInfo.length > 0 && advertisingIdInfo[0] != null) {
                return advertisingIdInfo[0];
            }
        }
        catch (Exception ex) {
            return "";
        }
        return "";
    }
    
    public Integer getAge() {
        synchronized (this) {
            return this.mUserAge;
        }
    }
    
    HashSet<String> getAllSettingsForProvider(final String s, final String s2) {
        if (this.mCurrentServerResponse == null) {
            return new HashSet<String>();
        }
        return this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettingsByReflectionName(s, s2);
    }
    
    String getCappingMessage(final String s, final CappingManager.ECappingStatus eCappingStatus) {
        if (eCappingStatus == null) {
            return null;
        }
        switch (eCappingStatus) {
            default: {
                return null;
            }
            case CAPPED_PER_DELIVERY: {
                return "Placement " + s + " is capped by disabled delivery";
            }
            case CAPPED_PER_COUNT: {
                return "Placement " + s + " has reached its capping limit";
            }
            case CAPPED_PER_PACE: {
                return "Placement " + s + " has reached its limit as defined per pace";
            }
        }
    }
    
    Boolean getConsent() {
        return this.mConsent;
    }
    
    ServerResponseWrapper getCurrentServerResponse() {
        return this.mCurrentServerResponse;
    }
    
    String getDynamicUserId() {
        synchronized (this) {
            return this.mDynamicUserId;
        }
    }
    
    AbstractAdapter getExistingAdapter(final String s) {
        synchronized (this) {
            while (true) {
                try {
                    if (this.mRewardedVideoAdaptersList != null) {
                        for (final AbstractAdapter abstractAdapter : this.mRewardedVideoAdaptersList) {
                            if (abstractAdapter.getProviderName().equals(s)) {
                                return abstractAdapter;
                            }
                        }
                    }
                    Label_0058: {
                        break Label_0058;
                    }
                    if (this.mInterstitialAdaptersList != null) {
                        for (final AbstractAdapter abstractAdapter2 : this.mInterstitialAdaptersList) {
                            if (abstractAdapter2.getProviderName().equals(s)) {
                                return abstractAdapter2;
                            }
                        }
                    }
                    if (this.mBannerAdaptersList != null) {
                        for (final AbstractAdapter abstractAdapter3 : this.mBannerAdaptersList) {
                            if (abstractAdapter3.getProviderName().equals(s)) {
                                return abstractAdapter3;
                            }
                        }
                    }
                    if (this.mOfferwallAdapter != null && this.mOfferwallAdapter.getProviderName().equals(s)) {
                        return this.mOfferwallAdapter;
                    }
                }
                catch (Exception ex) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "getExistingAdapter exception: " + ex, 1);
                }
                return null;
            }
        }
    }
    
    public String getGender() {
        synchronized (this) {
            return this.mUserGender;
        }
    }
    
    @Override
    public InterstitialPlacement getInterstitialPlacementInfo(final String s) {
        InterstitialPlacement interstitialPlacement = null;
        try {
            final InterstitialPlacement interstitialPlacement2 = interstitialPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialPlacement(s);
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "getPlacementInfo(placement: " + s + "):" + interstitialPlacement2, 1);
            return interstitialPlacement2;
        }
        catch (Exception ex) {
            return interstitialPlacement;
        }
    }
    
    public String getIronSourceAppKey() {
        synchronized (this) {
            return this.mAppKey;
        }
    }
    
    public String getIronSourceUserId() {
        synchronized (this) {
            return this.mUserId;
        }
    }
    
    String getMediationSegment() {
        synchronized (this) {
            return this.mSegment;
        }
    }
    
    public String getMediationType() {
        synchronized (this) {
            return this.mMediationType;
        }
    }
    
    @Override
    public void getOfferwallCredits() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "getOfferwallCredits()", 1);
        try {
            this.mOfferwallManager.getOfferwallCredits();
        }
        catch (Throwable t) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "getOfferwallCredits()", t);
        }
    }
    
    @Override
    public Placement getRewardedVideoPlacementInfo(final String s) {
        Placement rewardedVideoPlacement = null;
        try {
            final Placement placement = rewardedVideoPlacement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoPlacement(s);
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "getPlacementInfo(placement: " + s + "):" + placement, 1);
            return placement;
        }
        catch (Exception ex) {
            return rewardedVideoPlacement;
        }
    }
    
    Map<String, String> getRvServerParams() {
        synchronized (this) {
            return this.mRvServerParams;
        }
    }
    
    ServerResponseWrapper getServerResponse(final Context context, final String s, final IResponseListener responseListener) {
        synchronized (this.mServerResponseLocker) {
            if (this.mCurrentServerResponse != null) {
                return new ServerResponseWrapper(this.mCurrentServerResponse);
            }
            final ServerResponseWrapper connectAndGetServerResponse = this.connectAndGetServerResponse(context, s, responseListener);
            ServerResponseWrapper cachedResponse = null;
            Label_0078: {
                if (connectAndGetServerResponse != null) {
                    cachedResponse = connectAndGetServerResponse;
                    if (connectAndGetServerResponse.isValidResponse()) {
                        break Label_0078;
                    }
                }
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "Null or invalid response. Trying to get cached response", 0);
                cachedResponse = this.getCachedResponse(context, s);
            }
            if (cachedResponse != null) {
                this.mCurrentServerResponse = cachedResponse;
                IronSourceUtils.saveLastResponse(context, cachedResponse.toString());
                this.initializeSettingsFromServerResponse(this.mCurrentServerResponse, context);
            }
            InterstitialEventsManager.getInstance().setHasServerResponse(true);
            RewardedVideoEventsManager.getInstance().setHasServerResponse(true);
            return cachedResponse;
        }
    }
    
    public String getSessionId() {
        synchronized (this) {
            return this.mSessionId;
        }
    }
    
    public void init(final Activity p0, final String p1, final boolean p2, final IronSource.AD_UNIT... p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          6
        //     3: aload_0        
        //     4: monitorenter   
        //     5: aload_0        
        //     6: getfield        com/ironsource/mediationsdk/IronSourceObject.mAtomicIsFirstInit:Ljava/util/concurrent/atomic/AtomicBoolean;
        //     9: ifnull          527
        //    12: aload_0        
        //    13: getfield        com/ironsource/mediationsdk/IronSourceObject.mAtomicIsFirstInit:Ljava/util/concurrent/atomic/AtomicBoolean;
        //    16: iconst_1       
        //    17: iconst_0       
        //    18: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.compareAndSet:(ZZ)Z
        //    21: ifeq            527
        //    24: aload           4
        //    26: ifnull          35
        //    29: aload           4
        //    31: arraylength    
        //    32: ifne            156
        //    35: invokestatic    com/ironsource/mediationsdk/IronSource$AD_UNIT.values:()[Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //    38: astore          8
        //    40: aload           8
        //    42: arraylength    
        //    43: istore          7
        //    45: iconst_0       
        //    46: istore          5
        //    48: iload           5
        //    50: iload           7
        //    52: if_icmpge       83
        //    55: aload           8
        //    57: iload           5
        //    59: aaload         
        //    60: astore          9
        //    62: aload_0        
        //    63: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //    66: aload           9
        //    68: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    73: pop            
        //    74: iload           5
        //    76: iconst_1       
        //    77: iadd           
        //    78: istore          5
        //    80: goto            48
        //    83: aload_0        
        //    84: iconst_1       
        //    85: putfield        com/ironsource/mediationsdk/IronSourceObject.mDidInitRewardedVideo:Z
        //    88: aload_0        
        //    89: iconst_1       
        //    90: putfield        com/ironsource/mediationsdk/IronSourceObject.mDidInitInterstitial:Z
        //    93: aload_0        
        //    94: iconst_1       
        //    95: putfield        com/ironsource/mediationsdk/IronSourceObject.mDidInitBanner:Z
        //    98: aload_0        
        //    99: getfield        com/ironsource/mediationsdk/IronSourceObject.mLoggerManager:Lcom/ironsource/mediationsdk/logger/IronSourceLoggerManager;
        //   102: getstatic       com/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag.API:Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;
        //   105: new             Ljava/lang/StringBuilder;
        //   108: dup            
        //   109: invokespecial   java/lang/StringBuilder.<init>:()V
        //   112: ldc_w           "init(appKey:"
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: aload_2        
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: ldc_w           ")"
        //   125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   128: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   131: iconst_1       
        //   132: invokevirtual   com/ironsource/mediationsdk/logger/IronSourceLoggerManager.log:(Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;Ljava/lang/String;I)V
        //   135: aload_1        
        //   136: ifnonnull       253
        //   139: aload_0        
        //   140: getfield        com/ironsource/mediationsdk/IronSourceObject.mLoggerManager:Lcom/ironsource/mediationsdk/logger/IronSourceLoggerManager;
        //   143: getstatic       com/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag.API:Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;
        //   146: ldc_w           "Init Fail - provided activity is null"
        //   149: iconst_2       
        //   150: invokevirtual   com/ironsource/mediationsdk/logger/IronSourceLoggerManager.log:(Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;Ljava/lang/String;I)V
        //   153: aload_0        
        //   154: monitorexit    
        //   155: return         
        //   156: aload           4
        //   158: arraylength    
        //   159: istore          7
        //   161: iconst_0       
        //   162: istore          5
        //   164: iload           5
        //   166: iload           7
        //   168: if_icmpge       98
        //   171: aload           4
        //   173: iload           5
        //   175: aaload         
        //   176: astore          8
        //   178: aload_0        
        //   179: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   182: aload           8
        //   184: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   189: pop            
        //   190: aload_0        
        //   191: getfield        com/ironsource/mediationsdk/IronSourceObject.mRequestedAdUnits:Ljava/util/Set;
        //   194: aload           8
        //   196: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   201: pop            
        //   202: aload           8
        //   204: getstatic       com/ironsource/mediationsdk/IronSource$AD_UNIT.INTERSTITIAL:Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //   207: invokevirtual   com/ironsource/mediationsdk/IronSource$AD_UNIT.equals:(Ljava/lang/Object;)Z
        //   210: ifeq            218
        //   213: aload_0        
        //   214: iconst_1       
        //   215: putfield        com/ironsource/mediationsdk/IronSourceObject.mDidInitInterstitial:Z
        //   218: aload           8
        //   220: getstatic       com/ironsource/mediationsdk/IronSource$AD_UNIT.BANNER:Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //   223: invokevirtual   com/ironsource/mediationsdk/IronSource$AD_UNIT.equals:(Ljava/lang/Object;)Z
        //   226: ifeq            234
        //   229: aload_0        
        //   230: iconst_1       
        //   231: putfield        com/ironsource/mediationsdk/IronSourceObject.mDidInitBanner:Z
        //   234: aload           8
        //   236: getstatic       com/ironsource/mediationsdk/IronSource$AD_UNIT.REWARDED_VIDEO:Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //   239: invokevirtual   com/ironsource/mediationsdk/IronSource$AD_UNIT.equals:(Ljava/lang/Object;)Z
        //   242: ifeq            559
        //   245: aload_0        
        //   246: iconst_1       
        //   247: putfield        com/ironsource/mediationsdk/IronSourceObject.mDidInitRewardedVideo:Z
        //   250: goto            559
        //   253: aload_0        
        //   254: aload_1        
        //   255: putfield        com/ironsource/mediationsdk/IronSourceObject.mActivity:Landroid/app/Activity;
        //   258: aload_0        
        //   259: aload_1        
        //   260: invokespecial   com/ironsource/mediationsdk/IronSourceObject.prepareEventManagers:(Landroid/app/Activity;)V
        //   263: aload_0        
        //   264: aload_2        
        //   265: invokespecial   com/ironsource/mediationsdk/IronSourceObject.validateAppKey:(Ljava/lang/String;)Lcom/ironsource/mediationsdk/config/ConfigValidationResult;
        //   268: astore          8
        //   270: aload           8
        //   272: invokevirtual   com/ironsource/mediationsdk/config/ConfigValidationResult.isValid:()Z
        //   275: ifeq            341
        //   278: aload_0        
        //   279: aload_2        
        //   280: putfield        com/ironsource/mediationsdk/IronSourceObject.mAppKey:Ljava/lang/String;
        //   283: aload_0        
        //   284: getfield        com/ironsource/mediationsdk/IronSourceObject.mShouldSendGetInstanceEvent:Z
        //   287: ifeq            468
        //   290: iload_3        
        //   291: invokestatic    com/ironsource/mediationsdk/utils/IronSourceUtils.getMediationAdditionalData:(Z)Lorg/json/JSONObject;
        //   294: astore          8
        //   296: aload           4
        //   298: ifnull          418
        //   301: aload           4
        //   303: arraylength    
        //   304: istore          7
        //   306: iload           6
        //   308: istore          5
        //   310: iload           5
        //   312: iload           7
        //   314: if_icmpge       418
        //   317: aload           8
        //   319: aload           4
        //   321: iload           5
        //   323: aaload         
        //   324: invokevirtual   com/ironsource/mediationsdk/IronSource$AD_UNIT.toString:()Ljava/lang/String;
        //   327: iconst_1       
        //   328: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   331: pop            
        //   332: iload           5
        //   334: iconst_1       
        //   335: iadd           
        //   336: istore          5
        //   338: goto            310
        //   341: aload_0        
        //   342: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   345: getstatic       com/ironsource/mediationsdk/IronSource$AD_UNIT.REWARDED_VIDEO:Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //   348: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   353: ifeq            364
        //   356: aload_0        
        //   357: getfield        com/ironsource/mediationsdk/IronSourceObject.mListenersWrapper:Lcom/ironsource/mediationsdk/sdk/ListenersWrapper;
        //   360: iconst_0       
        //   361: invokevirtual   com/ironsource/mediationsdk/sdk/ListenersWrapper.onRewardedVideoAvailabilityChanged:(Z)V
        //   364: aload_0        
        //   365: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   368: getstatic       com/ironsource/mediationsdk/IronSource$AD_UNIT.OFFERWALL:Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //   371: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   376: ifeq            392
        //   379: aload_0        
        //   380: getfield        com/ironsource/mediationsdk/IronSourceObject.mListenersWrapper:Lcom/ironsource/mediationsdk/sdk/ListenersWrapper;
        //   383: iconst_0       
        //   384: aload           8
        //   386: invokevirtual   com/ironsource/mediationsdk/config/ConfigValidationResult.getIronSourceError:()Lcom/ironsource/mediationsdk/logger/IronSourceError;
        //   389: invokevirtual   com/ironsource/mediationsdk/sdk/ListenersWrapper.onOfferwallAvailable:(ZLcom/ironsource/mediationsdk/logger/IronSourceError;)V
        //   392: invokestatic    com/ironsource/mediationsdk/logger/IronSourceLoggerManager.getLogger:()Lcom/ironsource/mediationsdk/logger/IronSourceLoggerManager;
        //   395: getstatic       com/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag.API:Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;
        //   398: aload           8
        //   400: invokevirtual   com/ironsource/mediationsdk/config/ConfigValidationResult.getIronSourceError:()Lcom/ironsource/mediationsdk/logger/IronSourceError;
        //   403: invokevirtual   com/ironsource/mediationsdk/logger/IronSourceError.toString:()Ljava/lang/String;
        //   406: iconst_1       
        //   407: invokevirtual   com/ironsource/mediationsdk/logger/IronSourceLoggerManager.log:(Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;Ljava/lang/String;I)V
        //   410: goto            153
        //   413: astore_1       
        //   414: aload_0        
        //   415: monitorexit    
        //   416: aload_1        
        //   417: athrow         
        //   418: aload_0        
        //   419: getfield        com/ironsource/mediationsdk/IronSourceObject.mInitCounter:I
        //   422: iconst_1       
        //   423: iadd           
        //   424: istore          5
        //   426: aload_0        
        //   427: iload           5
        //   429: putfield        com/ironsource/mediationsdk/IronSourceObject.mInitCounter:I
        //   432: aload           8
        //   434: ldc             "sessionDepth"
        //   436: iload           5
        //   438: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   441: pop            
        //   442: new             Lcom/ironsource/eventsmodule/EventData;
        //   445: dup            
        //   446: bipush          14
        //   448: aload           8
        //   450: invokespecial   com/ironsource/eventsmodule/EventData.<init>:(ILorg/json/JSONObject;)V
        //   453: astore          8
        //   455: invokestatic    com/ironsource/mediationsdk/events/RewardedVideoEventsManager.getInstance:()Lcom/ironsource/mediationsdk/events/RewardedVideoEventsManager;
        //   458: aload           8
        //   460: invokevirtual   com/ironsource/mediationsdk/events/RewardedVideoEventsManager.log:(Lcom/ironsource/eventsmodule/EventData;)V
        //   463: aload_0        
        //   464: iconst_0       
        //   465: putfield        com/ironsource/mediationsdk/IronSourceObject.mShouldSendGetInstanceEvent:Z
        //   468: aload_0        
        //   469: getfield        com/ironsource/mediationsdk/IronSourceObject.mAdUnitsToInitialize:Ljava/util/Set;
        //   472: getstatic       com/ironsource/mediationsdk/IronSource$AD_UNIT.INTERSTITIAL:Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;
        //   475: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   480: ifeq            493
        //   483: invokestatic    com/ironsource/mediationsdk/MediationInitializer.getInstance:()Lcom/ironsource/mediationsdk/MediationInitializer;
        //   486: aload_0        
        //   487: getfield        com/ironsource/mediationsdk/IronSourceObject.mInterstitialManager:Lcom/ironsource/mediationsdk/InterstitialManager;
        //   490: invokevirtual   com/ironsource/mediationsdk/MediationInitializer.addMediationInitializationListener:(Lcom/ironsource/mediationsdk/MediationInitializer$OnMediationInitializationListener;)V
        //   493: invokestatic    com/ironsource/mediationsdk/MediationInitializer.getInstance:()Lcom/ironsource/mediationsdk/MediationInitializer;
        //   496: aload_0        
        //   497: invokevirtual   com/ironsource/mediationsdk/MediationInitializer.addMediationInitializationListener:(Lcom/ironsource/mediationsdk/MediationInitializer$OnMediationInitializationListener;)V
        //   500: invokestatic    com/ironsource/mediationsdk/MediationInitializer.getInstance:()Lcom/ironsource/mediationsdk/MediationInitializer;
        //   503: aload_1        
        //   504: aload_2        
        //   505: aload_0        
        //   506: getfield        com/ironsource/mediationsdk/IronSourceObject.mUserId:Ljava/lang/String;
        //   509: aload           4
        //   511: invokevirtual   com/ironsource/mediationsdk/MediationInitializer.init:(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/String;[Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;)V
        //   514: goto            153
        //   517: astore          9
        //   519: aload           9
        //   521: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   524: goto            442
        //   527: aload           4
        //   529: ifnull          542
        //   532: aload_0        
        //   533: iload_3        
        //   534: aload           4
        //   536: invokespecial   com/ironsource/mediationsdk/IronSourceObject.attachAdUnits:(Z[Lcom/ironsource/mediationsdk/IronSource$AD_UNIT;)V
        //   539: goto            153
        //   542: aload_0        
        //   543: getfield        com/ironsource/mediationsdk/IronSourceObject.mLoggerManager:Lcom/ironsource/mediationsdk/logger/IronSourceLoggerManager;
        //   546: getstatic       com/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag.API:Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;
        //   549: ldc_w           "Multiple calls to init without ad units are not allowed"
        //   552: iconst_3       
        //   553: invokevirtual   com/ironsource/mediationsdk/logger/IronSourceLoggerManager.log:(Lcom/ironsource/mediationsdk/logger/IronSourceLogger$IronSourceTag;Ljava/lang/String;I)V
        //   556: goto            153
        //   559: iload           5
        //   561: iconst_1       
        //   562: iadd           
        //   563: istore          5
        //   565: goto            164
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      24     413    418    Any
        //  29     35     413    418    Any
        //  35     45     413    418    Any
        //  62     74     413    418    Any
        //  83     98     413    418    Any
        //  98     135    413    418    Any
        //  139    153    413    418    Any
        //  156    161    413    418    Any
        //  178    218    413    418    Any
        //  218    234    413    418    Any
        //  234    250    413    418    Any
        //  253    296    413    418    Any
        //  301    306    517    527    Ljava/lang/Exception;
        //  301    306    413    418    Any
        //  317    332    517    527    Ljava/lang/Exception;
        //  317    332    413    418    Any
        //  341    364    413    418    Any
        //  364    392    413    418    Any
        //  392    410    413    418    Any
        //  418    442    517    527    Ljava/lang/Exception;
        //  418    442    413    418    Any
        //  442    468    413    418    Any
        //  468    493    413    418    Any
        //  493    514    413    418    Any
        //  519    524    413    418    Any
        //  532    539    413    418    Any
        //  542    556    413    418    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0310:
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
    
    public void initISDemandOnly(final Activity activity, final String s, final IronSource.AD_UNIT... array) {
        while (true) {
            final ArrayList<IronSource.AD_UNIT> list;
            Label_0059: {
                synchronized (this) {
                    list = new ArrayList<IronSource.AD_UNIT>();
                    if (array == null) {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Cannot initialized demand only mode: No ad units selected", 3);
                    }
                    else {
                        if (array.length > 0) {
                            break Label_0059;
                        }
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Cannot initialized demand only mode: No ad units selected", 3);
                    }
                    return;
                }
            }
            for (int length = array.length, i = 0; i < length; ++i) {
                final IronSource.AD_UNIT ad_UNIT = array[i];
                if (ad_UNIT.equals(IronSource.AD_UNIT.BANNER) || ad_UNIT.equals(IronSource.AD_UNIT.OFFERWALL)) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ad_UNIT + " ad unit cannot be initialized in demand only mode", 3);
                }
                else {
                    if (ad_UNIT.equals(IronSource.AD_UNIT.INTERSTITIAL)) {
                        if (this.mDidInitInterstitial) {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ad_UNIT + " ad unit has already been initialized", 3);
                        }
                        else {
                            this.mDidInitInterstitial = true;
                            this.isDemandOnlyInterstitial = true;
                            this.mInterstitialManager.mIsInISDemandOnlyMode = true;
                            if (!list.contains(ad_UNIT)) {
                                list.add(ad_UNIT);
                            }
                        }
                    }
                    if (ad_UNIT.equals(IronSource.AD_UNIT.REWARDED_VIDEO)) {
                        if (this.mDidInitRewardedVideo) {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ad_UNIT + " ad unit has already been initialized", 3);
                        }
                        else {
                            this.mDidInitRewardedVideo = true;
                            this.isDemandOnlyRewardedVideo = true;
                            this.mRewardedVideoManager.mIsInISDemandOnlyMode = true;
                            if (!list.contains(ad_UNIT)) {
                                list.add(ad_UNIT);
                            }
                        }
                    }
                }
            }
            if (list.size() > 0) {
                final Activity activity2;
                this.init(activity2, s, true, (IronSource.AD_UNIT[])list.toArray(new IronSource.AD_UNIT[list.size()]));
            }
        }
    }
    
    @Override
    public void initInterstitial(final Activity activity, final String s, final String s2) {
    }
    
    @Override
    public void initOfferwall(final Activity activity, final String s, final String s2) {
    }
    
    @Override
    public void initRewardedVideo(final Activity activity, final String s, final String s2) {
    }
    
    boolean isBannerPlacementCapped(final String s) {
        if (this.mCurrentServerResponse != null && this.mCurrentServerResponse.getConfigurations() != null && this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() != null) {
            InterstitialPlacement bannerPlacement = null;
            try {
                final BannerPlacement bannerPlacement2 = (BannerPlacement)(bannerPlacement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerPlacement(s));
                if (bannerPlacement2 == null) {
                    bannerPlacement = bannerPlacement2;
                    final BannerPlacement defaultBannerPlacement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getDefaultBannerPlacement();
                    if ((bannerPlacement = defaultBannerPlacement) == null) {
                        bannerPlacement = defaultBannerPlacement;
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Banner default placement was not found", 3);
                        return false;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (bannerPlacement != null) {
                return CappingManager.isBnPlacementCapped((Context)this.mActivity, bannerPlacement.getPlacementName());
            }
        }
        return false;
    }
    
    public boolean isISDemandOnlyInterstitialReady(final String s) {
        boolean interstitialReady;
        final boolean b = interstitialReady = false;
        int n;
        try {
            if (!this.isDemandOnlyInterstitial) {
                interstitialReady = b;
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in mediation mode. Use isInterstitialReady instead", 3);
                return false;
            }
            interstitialReady = b;
            n = ((interstitialReady = this.mInterstitialManager.isInterstitialReady(s)) ? 1 : 0);
            Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(true);
            interstitialReady = (n != 0);
            try {
                ((JSONObject)mediationAdditionalData).put("status", (Object)String.valueOf((boolean)(n != 0)));
                interstitialReady = (n != 0);
                mediationAdditionalData = new EventData(30, (JSONObject)mediationAdditionalData);
                interstitialReady = (n != 0);
                InterstitialEventsManager.getInstance().log((EventData)mediationAdditionalData);
                interstitialReady = (n != 0);
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isISDemandOnlyInterstitialReady(instanceId: " + s + "):" + (boolean)(n != 0), 1);
            }
            catch (JSONException ex) {
                interstitialReady = (n != 0);
                ex.printStackTrace();
            }
        }
        catch (Throwable t) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isISDemandOnlyInterstitialReady(instanceId: " + s + "):" + interstitialReady, 1);
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "isISDemandOnlyInterstitialReady(instanceId: " + s + ")", t);
            n = 0;
        }
        return n != 0;
    }
    
    public boolean isISDemandOnlyRewardedVideoAvailable(String mediationAdditionalData) {
        boolean rewardedVideoAvailable;
        final boolean b = rewardedVideoAvailable = false;
        int n;
        try {
            if (!this.isDemandOnlyRewardedVideo) {
                rewardedVideoAvailable = b;
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded Video was initialized in mediation mode. Use isRewardedVideoAvailable instead", 3);
                return false;
            }
            rewardedVideoAvailable = b;
            n = ((rewardedVideoAvailable = this.mRewardedVideoManager.isRewardedVideoAvailable(mediationAdditionalData)) ? 1 : 0);
            mediationAdditionalData = (String)IronSourceUtils.getMediationAdditionalData(true);
            rewardedVideoAvailable = (n != 0);
            try {
                ((JSONObject)mediationAdditionalData).put("status", (Object)String.valueOf((boolean)(n != 0)));
                rewardedVideoAvailable = (n != 0);
                mediationAdditionalData = (String)new EventData(18, (JSONObject)mediationAdditionalData);
                rewardedVideoAvailable = (n != 0);
                RewardedVideoEventsManager.getInstance().log((EventData)mediationAdditionalData);
                rewardedVideoAvailable = (n != 0);
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isISDemandOnlyRewardedVideoAvailable():" + (boolean)(n != 0), 1);
            }
            catch (JSONException ex) {
                rewardedVideoAvailable = (n != 0);
                ex.printStackTrace();
            }
        }
        catch (Throwable t) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isISDemandOnlyRewardedVideoAvailable():" + rewardedVideoAvailable, 1);
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "isISDemandOnlyRewardedVideoAvailable()", t);
            n = 0;
        }
        return n != 0;
    }
    
    boolean isInterstitialPlacementCapped(final String s) {
        final boolean b = false;
        final CappingManager.ECappingStatus interstitialCappingStatus = this.getInterstitialCappingStatus(s);
        boolean b2 = b;
        if (interstitialCappingStatus != null) {
            switch (interstitialCappingStatus) {
                default: {
                    b2 = b;
                    break;
                }
                case CAPPED_PER_DELIVERY:
                case CAPPED_PER_COUNT:
                case CAPPED_PER_PACE: {
                    b2 = true;
                    break;
                }
            }
        }
        this.sendIsCappedEvent("Interstitial", b2);
        return b2;
    }
    
    @Override
    public boolean isInterstitialReady() {
        boolean interstitialReady;
        final boolean b = interstitialReady = false;
        int n;
        try {
            if (this.isDemandOnlyInterstitial) {
                interstitialReady = b;
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in demand only mode. Use isISDemandOnlyInterstitialReady instead", 3);
                return false;
            }
            interstitialReady = b;
            n = ((interstitialReady = this.mInterstitialManager.isInterstitialReady()) ? 1 : 0);
            Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
            interstitialReady = (n != 0);
            try {
                ((JSONObject)mediationAdditionalData).put("status", (Object)String.valueOf((boolean)(n != 0)));
                interstitialReady = (n != 0);
                mediationAdditionalData = new EventData(30, (JSONObject)mediationAdditionalData);
                interstitialReady = (n != 0);
                InterstitialEventsManager.getInstance().log((EventData)mediationAdditionalData);
                interstitialReady = (n != 0);
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isInterstitialReady():" + (boolean)(n != 0), 1);
            }
            catch (JSONException ex) {
                interstitialReady = (n != 0);
                ex.printStackTrace();
            }
        }
        catch (Throwable t) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isInterstitialReady():" + interstitialReady, 1);
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "isInterstitialReady()", t);
            n = 0;
        }
        return n != 0;
    }
    
    @Override
    public boolean isOfferwallAvailable() {
        boolean offerwallAvailable = false;
        try {
            if (this.mOfferwallManager != null) {
                offerwallAvailable = this.mOfferwallManager.isOfferwallAvailable();
            }
            return offerwallAvailable;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public boolean isRewardedVideoAvailable() {
        boolean rewardedVideoAvailable;
        final boolean b = rewardedVideoAvailable = false;
        int n;
        try {
            if (this.isDemandOnlyRewardedVideo) {
                rewardedVideoAvailable = b;
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded Video was initialized in demand only mode. Use isISDemandOnlyRewardedVideoAvailable instead", 3);
                return false;
            }
            rewardedVideoAvailable = b;
            n = ((rewardedVideoAvailable = this.mRewardedVideoManager.isRewardedVideoAvailable()) ? 1 : 0);
            Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
            rewardedVideoAvailable = (n != 0);
            try {
                ((JSONObject)mediationAdditionalData).put("status", (Object)String.valueOf((boolean)(n != 0)));
                rewardedVideoAvailable = (n != 0);
                mediationAdditionalData = new EventData(18, (JSONObject)mediationAdditionalData);
                rewardedVideoAvailable = (n != 0);
                RewardedVideoEventsManager.getInstance().log((EventData)mediationAdditionalData);
                rewardedVideoAvailable = (n != 0);
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isRewardedVideoAvailable():" + (boolean)(n != 0), 1);
            }
            catch (JSONException ex) {
                rewardedVideoAvailable = (n != 0);
                ex.printStackTrace();
            }
        }
        catch (Throwable t) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isRewardedVideoAvailable():" + rewardedVideoAvailable, 1);
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "isRewardedVideoAvailable()", t);
            n = 0;
        }
        return n != 0;
    }
    
    boolean isRewardedVideoPlacementCapped(final String s) {
        final boolean b = false;
        final CappingManager.ECappingStatus rewardedVideoCappingStatus = this.getRewardedVideoCappingStatus(s);
        boolean b2 = b;
        if (rewardedVideoCappingStatus != null) {
            switch (rewardedVideoCappingStatus) {
                default: {
                    b2 = b;
                    break;
                }
                case CAPPED_PER_DELIVERY:
                case CAPPED_PER_COUNT:
                case CAPPED_PER_PACE: {
                    b2 = true;
                    break;
                }
            }
        }
        this.sendIsCappedEvent("Rewarded Video", b2);
        return b2;
    }
    
    public void loadBanner(final IronSourceBannerLayout ironSourceBannerLayout) {
        this.loadBanner(ironSourceBannerLayout, "");
    }
    
    public void loadBanner(final IronSourceBannerLayout mBnLayoutToLoad, final String mBnPlacementToLoad) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadBanner(" + mBnPlacementToLoad + ")", 1);
        if (mBnLayoutToLoad == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadBanner can't be called with a null parameter", 1);
            return;
        }
        if (!this.mDidInitBanner) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() must be called before loadBanner()", 3);
            return;
        }
        if (mBnLayoutToLoad.getSize().getDescription().equals("CUSTOM") && (mBnLayoutToLoad.getSize().getWidth() <= 0 || mBnLayoutToLoad.getSize().getHeight() <= 0)) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadBanner: Unsupported banner size. Height and width must be bigger than 0", 3);
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(mBnLayoutToLoad, ErrorBuilder.unsupportedBannerSize(""));
            return;
        }
        final MediationInitializer.EInitStatus currentInitStatus = MediationInitializer.getInstance().getCurrentInitStatus();
        if (currentInitStatus == EInitStatus.INIT_FAILED) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(mBnLayoutToLoad, new IronSourceError(600, "Init had failed"));
            return;
        }
        if (currentInitStatus == EInitStatus.INIT_IN_PROGRESS) {
            if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(mBnLayoutToLoad, new IronSourceError(601, "Init had failed"));
                return;
            }
            this.mBnLayoutToLoad = mBnLayoutToLoad;
            this.mIsBnLoadBeforeInitCompleted = true;
            this.mBnPlacementToLoad = mBnPlacementToLoad;
        }
        else {
            if (this.mCurrentServerResponse == null || this.mCurrentServerResponse.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "No banner configurations found", 3);
                BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(mBnLayoutToLoad, new IronSourceError(615, "No banner configurations found"));
                return;
            }
            this.mBannerManager.loadBanner(mBnLayoutToLoad, this.getBannerPlacement(mBnPlacementToLoad));
        }
    }
    
    public void loadISDemandOnlyInterstitial(final String s) {
        final String string = "loadISDemandOnlyInterstitial(" + s + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, string, 1);
        try {
            if (!this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in mediation mode. Use loadInterstitial instead", 3);
                return;
            }
            if (!this.mDidInitInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
                return;
            }
        }
        catch (Throwable t) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, string, t);
            return;
        }
        this.mInterstitialManager.loadInterstitial(s);
    }
    
    @Override
    public void loadInterstitial() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadInterstitial()", 1);
        try {
            if (this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in demand only mode. Use loadISDemandOnlyInterstitial instead", 3);
                return;
            }
            if (!this.mDidInitInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
                return;
            }
        }
        catch (Throwable t) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "loadInterstitial()", t);
            return;
        }
        this.mInterstitialManager.loadInterstitial();
    }
    
    @Override
    public void onInitFailed(final String s) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "onInitFailed(reason:" + s + ")", 1);
            if (this.mListenersWrapper != null) {
                final Iterator<IronSource.AD_UNIT> iterator = this.mAdUnitsToInitialize.iterator();
                while (iterator.hasNext()) {
                    this.notifyPublisherAboutInitFailed(iterator.next(), true);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void onInitSuccess(final List<IronSource.AD_UNIT> mInitiatedAdUnits, final boolean b) {
        while (true) {
            int n = 0;
            while (true) {
                Label_0164: {
                    IronSource.AD_UNIT ad_UNIT = null;
                    Label_0157: {
                        try {
                            this.mInitiatedAdUnits = mInitiatedAdUnits;
                            this.mInitSucceeded = true;
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "onInitSuccess()", 1);
                            IronSourceUtils.sendAutomationLog("init success");
                            Label_0073: {
                                if (!b) {
                                    break Label_0073;
                                }
                                Object o = IronSourceUtils.getMediationAdditionalData(false);
                                try {
                                    ((JSONObject)o).put("revived", true);
                                    o = new EventData(114, (JSONObject)o);
                                    RewardedVideoEventsManager.getInstance().log((EventData)o);
                                    InterstitialEventsManager.getInstance().triggerEventsSend();
                                    RewardedVideoEventsManager.getInstance().triggerEventsSend();
                                    o = IronSource.AD_UNIT.values();
                                    final int length = ((EventData)o).length;
                                    if (n >= length) {
                                        break;
                                    }
                                    ad_UNIT = o[n];
                                    if (!this.mAdUnitsToInitialize.contains(ad_UNIT)) {
                                        break Label_0164;
                                    }
                                    if (mInitiatedAdUnits.contains(ad_UNIT)) {
                                        this.startAdUnit(ad_UNIT);
                                        break Label_0164;
                                    }
                                    break Label_0157;
                                }
                                catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        break;
                    }
                    this.notifyPublisherAboutInitFailed(ad_UNIT, false);
                }
                ++n;
                continue;
            }
        }
    }
    
    @Override
    public void onPause(final Activity activity) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "onPause()", 1);
            if (this.mRewardedVideoManager != null) {
                this.mRewardedVideoManager.onPause(activity);
            }
            if (this.mInterstitialManager != null) {
                this.mInterstitialManager.onPause(activity);
            }
            if (this.mBannerManager != null) {
                this.mBannerManager.onPause(activity);
            }
        }
        catch (Throwable t) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "onPause()", t);
        }
    }
    
    @Override
    public void onResume(final Activity mActivity) {
        try {
            this.mActivity = mActivity;
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "onResume()", 1);
            if (this.mRewardedVideoManager != null) {
                this.mRewardedVideoManager.onResume(mActivity);
            }
            if (this.mInterstitialManager != null) {
                this.mInterstitialManager.onResume(mActivity);
            }
            if (this.mBannerManager != null) {
                this.mBannerManager.onResume(mActivity);
            }
        }
        catch (Throwable t) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "onResume()", t);
        }
    }
    
    @Override
    public void onStillInProgressAfter15Secs() {
        if (this.mIsBnLoadBeforeInitCompleted) {
            this.mIsBnLoadBeforeInitCompleted = false;
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mBnLayoutToLoad, new IronSourceError(603, "init had failed"));
            this.mBnLayoutToLoad = null;
            this.mBnPlacementToLoad = null;
        }
    }
    
    @Override
    public void removeInterstitialListener() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "removeInterstitialListener()", 1);
        this.mListenersWrapper.setInterstitialListener(null);
    }
    
    @Override
    public void removeOfferwallListener() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "removeOfferwallListener()", 1);
        this.mListenersWrapper.setOfferwallListener(null);
    }
    
    @Override
    public void removeRewardedVideoListener() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "removeRewardedVideoListener()", 1);
        this.mListenersWrapper.setRewardedVideoListener(null);
    }
    
    @Override
    public void setAdaptersDebug(final boolean adaptersDebug) {
        IronSourceLoggerManager.getLogger().setAdaptersDebug(adaptersDebug);
    }
    
    @Override
    public void setAge(final int n) {
        synchronized (this) {
            try {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setAge(age:" + n + ")", 1);
                final ConfigValidationResult configValidationResult = new ConfigValidationResult();
                this.validateAge(n, configValidationResult);
                if (configValidationResult.isValid()) {
                    this.mUserAge = n;
                }
                else {
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResult.getIronSourceError().toString(), 2);
                }
            }
            catch (Exception ex) {
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setAge(age:" + n + ")", ex);
            }
        }
    }
    
    public void setConsent(final boolean b) {
        this.mConsent = b;
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "setConsent : " + b, 1);
        if (this.mRewardedVideoManager != null) {
            this.mRewardedVideoManager.setConsent(b);
        }
        if (this.mInterstitialManager != null) {
            this.mInterstitialManager.setConsent(b);
        }
        if (this.mBannerManager != null) {
            this.mBannerManager.setConsent(b);
        }
        if (this.mOfferwallAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, "Offerwall | setConsent(consent:" + b + ")", 1);
            this.mOfferwallAdapter.setConsent(b);
        }
        int n = 40;
        if (!b) {
            n = 41;
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(n, IronSourceUtils.getMediationAdditionalData(false)));
    }
    
    @Override
    public boolean setDynamicUserId(final String mDynamicUserId) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setDynamicUserId(dynamicUserId:" + mDynamicUserId + ")", 1);
            final ConfigValidationResult configValidationResult = new ConfigValidationResult();
            this.validateDynamicUserId(mDynamicUserId, configValidationResult);
            if (configValidationResult.isValid()) {
                this.mDynamicUserId = mDynamicUserId;
                return true;
            }
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResult.getIronSourceError().toString(), 2);
            return false;
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setDynamicUserId(dynamicUserId:" + mDynamicUserId + ")", ex);
            return false;
        }
    }
    
    @Override
    public void setGender(final String mUserGender) {
        synchronized (this) {
            try {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setGender(gender:" + mUserGender + ")", 1);
                final ConfigValidationResult configValidationResult = new ConfigValidationResult();
                this.validateGender(mUserGender, configValidationResult);
                if (configValidationResult.isValid()) {
                    this.mUserGender = mUserGender;
                }
                else {
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResult.getIronSourceError().toString(), 2);
                }
            }
            catch (Exception ex) {
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setGender(gender:" + mUserGender + ")", ex);
            }
        }
    }
    
    public void setISDemandOnlyInterstitialListener(final ISDemandOnlyInterstitialListener isDemandOnlyInterstitialListener) {
        if (isDemandOnlyInterstitialListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener:null)", 1);
        }
        else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener)", 1);
        }
        this.mListenersWrapper.setISDemandOnlyInterstitialListener(isDemandOnlyInterstitialListener);
    }
    
    void setISDemandOnlyRewardedVideoListener(final ISDemandOnlyRewardedVideoListener isDemandOnlyRewardedVideoListener) {
        if (isDemandOnlyRewardedVideoListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setISDemandOnlyRewardedVideoListener(ISDemandOnlyRewardedVideoListener:null)", 1);
        }
        else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setISDemandOnlyRewardedVideoListener(ISDemandOnlyRewardedVideoListener)", 1);
        }
        this.mListenersWrapper.setISDemandOnlyRewardedVideoListener(isDemandOnlyRewardedVideoListener);
    }
    
    @Override
    public void setInterstitialListener(final InterstitialListener interstitialListener) {
        if (interstitialListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setInterstitialListener(ISListener:null)", 1);
        }
        else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setInterstitialListener(ISListener)", 1);
        }
        this.mListenersWrapper.setInterstitialListener(interstitialListener);
    }
    
    void setIronSourceUserId(final String mUserId) {
        synchronized (this) {
            this.mUserId = mUserId;
        }
    }
    
    @Override
    public void setLogListener(final LogListener logListener) {
        if (logListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setLogListener(LogListener:null)", 1);
            return;
        }
        this.mPublisherLogger.setLogListener(logListener);
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setLogListener(LogListener:" + logListener.getClass().getSimpleName() + ")", 1);
    }
    
    @Override
    public void setMediationSegment(final String mSegment) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setMediationSegment(segment:" + mSegment + ")", 1);
            final ConfigValidationResult configValidationResult = new ConfigValidationResult();
            this.validateSegment(mSegment, configValidationResult);
            if (configValidationResult.isValid()) {
                this.mSegment = mSegment;
                return;
            }
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResult.getIronSourceError().toString(), 2);
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setMediationSegment(segment:" + mSegment + ")", ex);
        }
    }
    
    @Override
    public void setMediationType(final String mMediationType) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, this.TAG + ":setMediationType(mediationType:" + mMediationType + ")", 1);
            if (this.validateLength(mMediationType, 1, 64) && this.validateAlphanumeric(mMediationType)) {
                this.mMediationType = mMediationType;
                return;
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, " mediationType value is invalid - should be alphanumeric and 1-64 chars in length", 1);
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setMediationType(mediationType:" + mMediationType + ")", ex);
        }
    }
    
    @Override
    public void setOfferwallListener(final OfferwallListener offerwallListener) {
        if (offerwallListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setOfferwallListener(OWListener:null)", 1);
        }
        else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setOfferwallListener(OWListener)", 1);
        }
        this.mListenersWrapper.setOfferwallListener(offerwallListener);
    }
    
    @Override
    public void setRewardedInterstitialListener(final RewardedInterstitialListener rewardedInterstitialListener) {
        this.mListenersWrapper.setRewardedInterstitialListener(rewardedInterstitialListener);
    }
    
    @Override
    public void setRewardedVideoListener(final RewardedVideoListener rewardedVideoListener) {
        if (rewardedVideoListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setRewardedVideoListener(RVListener:null)", 1);
        }
        else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setRewardedVideoListener(RVListener)", 1);
        }
        this.mListenersWrapper.setRewardedVideoListener(rewardedVideoListener);
    }
    
    @Override
    public void setRewardedVideoServerParameters(final Map<String, String> map) {
        if (map != null) {
            try {
                if (map.size() == 0) {
                    return;
                }
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setRewardedVideoServerParameters(params:" + map.toString() + ")", 1);
                this.mRvServerParams = new HashMap<String, String>(map);
            }
            catch (Exception ex) {
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setRewardedVideoServerParameters(params:" + map.toString() + ")", ex);
            }
        }
    }
    
    public void setSegment(final IronSourceSegment mIronSegment) {
        if (MediationInitializer.getInstance().getCurrentInitStatus() == EInitStatus.INIT_IN_PROGRESS || MediationInitializer.getInstance().getCurrentInitStatus() == EInitStatus.INITIATED) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "Segments must be set prior to Init. Setting a segment after the init will be ignored", 0);
            return;
        }
        this.mIronSegment = mIronSegment;
    }
    
    void setSegmentListener(final SegmentListener segmentListener) {
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.setSegmentListener(segmentListener);
            MediationInitializer.getInstance().setSegmentListener(this.mListenersWrapper);
        }
    }
    
    @Override
    public void shouldTrackNetworkState(final Context context, final boolean b) {
        if (this.mRewardedVideoManager != null) {
            this.mRewardedVideoManager.shouldTrackNetworkState(context, b);
        }
        if (this.mInterstitialManager != null) {
            this.mInterstitialManager.shouldTrackNetworkState(context, b);
        }
    }
    
    public void showISDemandOnlyInterstitial(final String s) {
        final String string = "showISDemandOnlyInterstitial(" + s + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, string, 1);
        try {
            if (!this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in mediation mode. Use showInterstitial instead", 3);
                return;
            }
            if (!this.isInterstitialConfigurationsReady()) {
                this.mListenersWrapper.onInterstitialAdShowFailed(s, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
                return;
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, string, ex);
            this.mListenersWrapper.onInterstitialAdShowFailed(s, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
            return;
        }
        final InterstitialPlacement defaultInterstitialPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getDefaultInterstitialPlacement();
        if (defaultInterstitialPlacement != null) {
            this.showISDemandOnlyInterstitial(s, defaultInterstitialPlacement.getPlacementName());
        }
    }
    
    public void showISDemandOnlyInterstitial(final String s, String interstitialPlacementToShowWithEvent) {
        final StringBuilder append = new StringBuilder().append("showISDemandOnlyInterstitial(").append(s);
        Label_0071: {
            if (interstitialPlacementToShowWithEvent != null) {
                break Label_0071;
            }
            String s2 = ")";
            while (true) {
                s2 = append.append(s2).toString();
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, s2, 1);
                Label_0157: {
                    try {
                        if (!this.isDemandOnlyInterstitial) {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in mediation mode. Use showInterstitial instead", 3);
                            return;
                        }
                        if (!this.isInterstitialConfigurationsReady()) {
                            this.mListenersWrapper.onInterstitialAdShowFailed(s, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
                            return;
                        }
                        break Label_0157;
                        s2 = " , " + interstitialPlacementToShowWithEvent + ")";
                        continue;
                    }
                    catch (Exception ex) {
                        this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, s2, ex);
                        this.mListenersWrapper.onInterstitialAdShowFailed(s, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
                        return;
                    }
                }
                interstitialPlacementToShowWithEvent = (String)this.getInterstitialPlacementToShowWithEvent(interstitialPlacementToShowWithEvent);
                if (interstitialPlacementToShowWithEvent != null) {
                    break;
                }
                return;
            }
        }
        Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(true);
        while (true) {
            try {
                ((JSONObject)mediationAdditionalData).put("placement", (Object)((InterstitialPlacement)interstitialPlacementToShowWithEvent).getPlacementName());
                mediationAdditionalData = new EventData(23, (JSONObject)mediationAdditionalData);
                InterstitialEventsManager.getInstance().log((EventData)mediationAdditionalData);
                this.mInterstitialManager.setCurrentPlacement((InterstitialPlacement)interstitialPlacementToShowWithEvent);
                this.mInterstitialManager.showInterstitial(s, ((InterstitialPlacement)interstitialPlacementToShowWithEvent).getPlacementName());
            }
            catch (JSONException ex2) {
                ex2.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public void showISDemandOnlyRewardedVideo(final String s) {
        final String string = "showISDemandOnlyRewardedVideo(" + s + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, string, 1);
        try {
            if (!this.isDemandOnlyRewardedVideo) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded Video was initialized in mediation mode. Use showRewardedVideo instead", 3);
                return;
            }
            if (!this.isRewardedVideoConfigurationsReady()) {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(s, ErrorBuilder.buildInitFailedError("showISDemandOnlyRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", "Rewarded Video"));
                return;
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, string, ex);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(s, ErrorBuilder.buildInitFailedError("showISDemandOnlyRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", "Rewarded Video"));
            return;
        }
        final Placement defaultRewardedVideoPlacement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
        if (defaultRewardedVideoPlacement != null) {
            this.showISDemandOnlyRewardedVideo(s, defaultRewardedVideoPlacement.getPlacementName());
        }
    }
    
    public void showISDemandOnlyRewardedVideo(final String s, String placementToShowWithEvent) {
        final StringBuilder append = new StringBuilder().append("showISDemandOnlyRewardedVideo(").append(s);
        Label_0071: {
            if (placementToShowWithEvent != null) {
                break Label_0071;
            }
            String s2 = ")";
            while (true) {
                s2 = append.append(s2).toString();
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, s2, 1);
                Label_0157: {
                    try {
                        if (!this.isDemandOnlyRewardedVideo) {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded Video was initialized in mediation mode. Use showRewardedVideo instead", 3);
                            return;
                        }
                        if (!this.isRewardedVideoConfigurationsReady()) {
                            this.mListenersWrapper.onRewardedVideoAdShowFailed(s, ErrorBuilder.buildInitFailedError("showISDemandOnlyRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", "Rewarded Video"));
                            return;
                        }
                        break Label_0157;
                        s2 = " , " + placementToShowWithEvent + ")";
                        continue;
                    }
                    catch (Exception ex) {
                        this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, s2, ex);
                        this.mListenersWrapper.onRewardedVideoAdShowFailed(s, ErrorBuilder.buildInitFailedError("showISDemandOnlyRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", "Rewarded Video"));
                        return;
                    }
                }
                placementToShowWithEvent = (String)this.getPlacementToShowWithEvent(placementToShowWithEvent);
                if (placementToShowWithEvent != null) {
                    break;
                }
                return;
            }
        }
        Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(true);
        while (true) {
            try {
                ((JSONObject)mediationAdditionalData).put("placement", (Object)((Placement)placementToShowWithEvent).getPlacementName());
                mediationAdditionalData = new EventData(2, (JSONObject)mediationAdditionalData);
                RewardedVideoEventsManager.getInstance().log((EventData)mediationAdditionalData);
                this.mRewardedVideoManager.setCurrentPlacement((Placement)placementToShowWithEvent);
                this.mRewardedVideoManager.showRewardedVideo(s, ((Placement)placementToShowWithEvent).getPlacementName());
            }
            catch (JSONException ex2) {
                ex2.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public void showInterstitial() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "showInterstitial()", 1);
        try {
            if (this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in demand only mode. Use showISDemandOnlyInterstitial instead", 3);
                return;
            }
            if (!this.isInterstitialConfigurationsReady()) {
                this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
                return;
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "showInterstitial()", ex);
            this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
            return;
        }
        final InterstitialPlacement defaultInterstitialPlacement = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getDefaultInterstitialPlacement();
        if (defaultInterstitialPlacement != null) {
            this.showInterstitial(defaultInterstitialPlacement.getPlacementName());
        }
    }
    
    @Override
    public void showInterstitial(String interstitialPlacementToShowWithEvent) {
        final String string = "showInterstitial(" + interstitialPlacementToShowWithEvent + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, string, 1);
        try {
            if (this.isDemandOnlyInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in demand only mode. Use showISDemandOnlyInterstitial instead", 3);
                return;
            }
            if (!this.isInterstitialConfigurationsReady()) {
                this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
                return;
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, string, ex);
            this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
            return;
        }
        interstitialPlacementToShowWithEvent = (String)this.getInterstitialPlacementToShowWithEvent(interstitialPlacementToShowWithEvent);
        if (interstitialPlacementToShowWithEvent != null) {
            Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
            while (true) {
                try {
                    ((JSONObject)mediationAdditionalData).put("placement", (Object)((InterstitialPlacement)interstitialPlacementToShowWithEvent).getPlacementName());
                    mediationAdditionalData = new EventData(23, (JSONObject)mediationAdditionalData);
                    InterstitialEventsManager.getInstance().log((EventData)mediationAdditionalData);
                    this.mInterstitialManager.setCurrentPlacement((InterstitialPlacement)interstitialPlacementToShowWithEvent);
                    this.mInterstitialManager.showInterstitial(((InterstitialPlacement)interstitialPlacementToShowWithEvent).getPlacementName());
                }
                catch (JSONException ex2) {
                    ex2.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void showOfferwall() {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "showOfferwall()", 1);
            if (!this.isOfferwallConfigurationsReady()) {
                this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", "Offerwall"));
                return;
            }
            final OfferwallPlacement defaultOfferwallPlacement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement();
            if (defaultOfferwallPlacement != null) {
                this.showOfferwall(defaultOfferwallPlacement.getPlacementName());
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "showOfferwall()", ex);
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", "Offerwall"));
        }
    }
    
    @Override
    public void showOfferwall(final String s) {
        final String string = "showOfferwall(" + s + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, string, 1);
        OfferwallPlacement offerwallPlacement;
        try {
            if (!this.isOfferwallConfigurationsReady()) {
                this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", "Offerwall"));
                return;
            }
            if ((offerwallPlacement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getOfferwallPlacement(s)) == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
                if ((offerwallPlacement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement()) == null) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                    return;
                }
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, string, ex);
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", "Offerwall"));
            return;
        }
        this.mOfferwallManager.showOfferwall(offerwallPlacement.getPlacementName());
    }
    
    public void showRewardedVideo() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "showRewardedVideo()", 1);
        try {
            if (this.isDemandOnlyRewardedVideo) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded Video was initialized in demand only mode. Use showISDemandOnlyRewardedVideo instead", 3);
                return;
            }
            if (!this.isRewardedVideoConfigurationsReady()) {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", "Rewarded Video"));
                return;
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "showRewardedVideo()", ex);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", "Rewarded Video"));
            return;
        }
        final Placement defaultRewardedVideoPlacement = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
        if (defaultRewardedVideoPlacement != null) {
            this.showRewardedVideo(defaultRewardedVideoPlacement.getPlacementName());
        }
    }
    
    @Override
    public void showRewardedVideo(String placementToShowWithEvent) {
        final String string = "showRewardedVideo(" + placementToShowWithEvent + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, string, 1);
        try {
            if (this.isDemandOnlyRewardedVideo) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded Video was initialized in demand only mode. Use showISDemandOnlyRewardedVideo instead", 3);
                return;
            }
            if (!this.isRewardedVideoConfigurationsReady()) {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", "Rewarded Video"));
                return;
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, string, ex);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", "Rewarded Video"));
            return;
        }
        placementToShowWithEvent = (String)this.getPlacementToShowWithEvent(placementToShowWithEvent);
        if (placementToShowWithEvent != null) {
            Object mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
            while (true) {
                try {
                    ((JSONObject)mediationAdditionalData).put("placement", (Object)((Placement)placementToShowWithEvent).getPlacementName());
                    mediationAdditionalData = new EventData(2, (JSONObject)mediationAdditionalData);
                    RewardedVideoEventsManager.getInstance().log((EventData)mediationAdditionalData);
                    this.mRewardedVideoManager.setCurrentPlacement((Placement)placementToShowWithEvent);
                    this.mRewardedVideoManager.showRewardedVideo(((Placement)placementToShowWithEvent).getPlacementName());
                }
                catch (JSONException ex2) {
                    ex2.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }
    
    public interface IResponseListener
    {
        void onUnrecoverableError(final String p0);
    }
}
