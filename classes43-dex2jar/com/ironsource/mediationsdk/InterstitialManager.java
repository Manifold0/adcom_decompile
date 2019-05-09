// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import java.util.List;
import com.ironsource.mediationsdk.logger.IronSourceError;
import android.content.Context;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import android.app.Activity;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.logger.LogListener;
import org.json.JSONObject;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import android.util.Log;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import java.util.Iterator;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import java.util.concurrent.ConcurrentHashMap;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.ListenersWrapper;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Map;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.utils.DailyCappingListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialApi;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialManagerListener;
import com.ironsource.mediationsdk.sdk.InterstitialManagerListener;
import com.ironsource.mediationsdk.sdk.InterstitialApi;

class InterstitialManager extends AbstractAdUnitManager implements InterstitialApi, InterstitialManagerListener, OnMediationInitializationListener, RewardedInterstitialManagerListener, RewardedInterstitialApi, DailyCappingListener
{
    private final String TAG;
    private CallbackThrotteler mCallbackThrotteler;
    private InterstitialPlacement mCurrentPlacement;
    private boolean mDidCallLoadInterstitial;
    private boolean mDidFinishToInitInterstitial;
    private ISDemandOnlyInterstitialListener mISDemandOnlyInterstitialListener;
    private Map<String, InterstitialSmash> mInstanceIdToSmashMap;
    private CopyOnWriteArraySet<String> mInstancesToLoad;
    private ListenersWrapper mInterstitialListenersWrapper;
    private boolean mIsLoadInterstitialInProgress;
    private RewardedInterstitialListener mRewardedInterstitialListenerWrapper;
    private boolean mShouldSendAdReadyEvent;
    
    InterstitialManager() {
        this.TAG = this.getClass().getName();
        this.mInstancesToLoad = new CopyOnWriteArraySet<String>();
        this.mInstanceIdToSmashMap = new ConcurrentHashMap<String, InterstitialSmash>();
        this.mCallbackThrotteler = new CallbackThrotteler();
        this.mShouldSendAdReadyEvent = false;
        this.mIsLoadInterstitialInProgress = false;
        this.mDidCallLoadInterstitial = false;
        this.mDailyCappingManager = new DailyCappingManager("interstitial", this);
    }
    
    private void changeStateToInitiated() {
        synchronized (this) {
            for (final AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                    abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
                }
            }
        }
    }
    // monitorexit(this)
    
    private void changeStateToInitiatedForInstanceId(final String s) {
        synchronized (this) {
            for (final AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getSubProviderId().equals(s) && (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE)) {
                    abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
                    break;
                }
            }
        }
    }
    
    private void completeAdapterShow(final AbstractSmash abstractSmash) {
        if (!abstractSmash.isMediationAvailable()) {
            this.startNextAdapter();
            this.completeIterationRound();
            return;
        }
        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
    }
    
    private void completeIterationRound() {
        if (this.isIterationRoundComplete()) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Reset Iteration", 0);
            for (final AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.EXHAUSTED) {
                    abstractSmash.completeIteration();
                }
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "End of Reset Iteration", 0);
        }
    }
    
    private boolean isIterationRoundComplete() {
        for (final AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INIT_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                return false;
            }
        }
        return true;
    }
    
    private void loadAdapterAndSendEvent(final InterstitialSmash interstitialSmash) {
        synchronized (this) {
            if (this.mIsInISDemandOnlyMode) {
                this.logMediationEvent(22, null);
            }
            this.logProviderEvent(22, interstitialSmash, null);
            interstitialSmash.loadInterstitial();
        }
    }
    
    private void logMediationEvent(final int n, final Object[][] array) {
        int i = 0;
        final JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(this.mIsInISDemandOnlyMode);
        if (array != null) {
            try {
                while (i < array.length) {
                    final Object[] array2 = array[i];
                    mediationAdditionalData.put(array2[0].toString(), array2[1]);
                    ++i;
                }
            }
            catch (Exception ex) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "InterstitialManager logMediationEvent " + Log.getStackTraceString((Throwable)ex), 3);
            }
        }
        InterstitialEventsManager.getInstance().log(new EventData(n, mediationAdditionalData));
    }
    
    private void logProviderEvent(final int n, AbstractSmash providerAdditionalData, final Object[][] array) {
        int i = 0;
        providerAdditionalData = (AbstractSmash)IronSourceUtils.getProviderAdditionalData(providerAdditionalData, this.mIsInISDemandOnlyMode);
        if (array != null) {
            try {
                while (i < array.length) {
                    final Object[] array2 = array[i];
                    ((JSONObject)providerAdditionalData).put(array2[0].toString(), array2[1]);
                    ++i;
                }
            }
            catch (Exception ex) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "InterstitialManager logProviderEvent " + Log.getStackTraceString((Throwable)ex), 3);
            }
        }
        InterstitialEventsManager.getInstance().log(new EventData(n, (JSONObject)providerAdditionalData));
    }
    
    private void sendShowChanceEvent(final AbstractSmash abstractSmash, String s, final boolean b) {
        final Object[] array = { "placement", s };
        if (b) {
            s = "true";
        }
        else {
            s = "false";
        }
        this.logProviderEvent(219, abstractSmash, new Object[][] { array, { "status", s } });
    }
    
    private void sendShowChanceEvents(AbstractSmash abstractSmash, final int n, final String s) {
        this.sendShowChanceEvent(abstractSmash, s, true);
        if (!this.mIsInISDemandOnlyMode) {
            for (int n2 = 0; n2 < this.mSmashArray.size() && n2 < n; ++n2) {
                abstractSmash = this.mSmashArray.get(n2);
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                    this.sendShowChanceEvent(abstractSmash, s, false);
                }
            }
        }
    }
    
    private int smashesCount(final AbstractSmash.MEDIATION_STATE... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/ironsource/mediationsdk/InterstitialManager.mSmashArray:Ljava/util/concurrent/CopyOnWriteArrayList;
        //     4: astore          6
        //     6: aload           6
        //     8: monitorenter   
        //     9: iconst_0       
        //    10: istore          4
        //    12: aload_0        
        //    13: getfield        com/ironsource/mediationsdk/InterstitialManager.mSmashArray:Ljava/util/concurrent/CopyOnWriteArrayList;
        //    16: invokevirtual   java/util/concurrent/CopyOnWriteArrayList.iterator:()Ljava/util/Iterator;
        //    19: astore          7
        //    21: aload           7
        //    23: invokeinterface java/util/Iterator.hasNext:()Z
        //    28: ifeq            87
        //    31: aload           7
        //    33: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    38: checkcast       Lcom/ironsource/mediationsdk/AbstractSmash;
        //    41: astore          8
        //    43: aload_1        
        //    44: arraylength    
        //    45: istore          5
        //    47: iconst_0       
        //    48: istore_3       
        //    49: iload           4
        //    51: istore_2       
        //    52: iload_2        
        //    53: istore          4
        //    55: iload_3        
        //    56: iload           5
        //    58: if_icmpge       21
        //    61: aload_1        
        //    62: iload_3        
        //    63: aaload         
        //    64: astore          9
        //    66: iload_2        
        //    67: istore          4
        //    69: aload           8
        //    71: invokevirtual   com/ironsource/mediationsdk/AbstractSmash.getMediationState:()Lcom/ironsource/mediationsdk/AbstractSmash$MEDIATION_STATE;
        //    74: aload           9
        //    76: if_acmpne       99
        //    79: iload_2        
        //    80: iconst_1       
        //    81: iadd           
        //    82: istore          4
        //    84: goto            99
        //    87: aload           6
        //    89: monitorexit    
        //    90: iload           4
        //    92: ireturn        
        //    93: astore_1       
        //    94: aload           6
        //    96: monitorexit    
        //    97: aload_1        
        //    98: athrow         
        //    99: iload_3        
        //   100: iconst_1       
        //   101: iadd           
        //   102: istore_3       
        //   103: iload           4
        //   105: istore_2       
        //   106: goto            52
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  12     21     93     99     Any
        //  21     47     93     99     Any
        //  69     79     93     99     Any
        //  87     90     93     99     Any
        //  94     97     93     99     Any
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
    
    private AbstractAdapter startAdapter(InterstitialSmash customParams) {
        synchronized (this) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":startAdapter(" + ((AbstractSmash)customParams).getName() + ")", 1);
            try {
                final AbstractAdapter loadedAdapterOrFetchByReflection = this.getLoadedAdapterOrFetchByReflection((AbstractSmash)customParams);
                if (loadedAdapterOrFetchByReflection == null) {
                    customParams = null;
                }
                else {
                    IronSourceObject.getInstance().addToISAdaptersList(loadedAdapterOrFetchByReflection);
                    loadedAdapterOrFetchByReflection.setLogListener(this.mLoggerManager);
                    ((AbstractSmash)customParams).setAdapterForSmash(loadedAdapterOrFetchByReflection);
                    ((AbstractSmash)customParams).setMediationState(AbstractSmash.MEDIATION_STATE.INIT_PENDING);
                    if (this.mRewardedInterstitialListenerWrapper != null) {
                        ((InterstitialSmash)customParams).setRewardedInterstitialManagerListener(this);
                    }
                    this.setCustomParams((AbstractSmash)customParams);
                    ((InterstitialSmash)customParams).initInterstitial(this.mActivity, this.mAppKey, this.mUserId);
                    customParams = loadedAdapterOrFetchByReflection;
                }
                return (AbstractAdapter)customParams;
            }
            catch (Throwable t) {
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":startAdapter(" + ((AbstractSmash)customParams).getName() + ")", t);
                ((AbstractSmash)customParams).setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ErrorBuilder.buildInitFailedError(((AbstractSmash)customParams).getName() + " initialization failed - please verify that required dependencies are in you build path.", "Interstitial").toString(), 2);
                customParams = null;
                return (AbstractAdapter)customParams;
            }
            return (AbstractAdapter)customParams;
        }
    }
    
    private AbstractAdapter startNextAdapter() {
        AbstractAdapter abstractAdapter = null;
        int n = 0;
        int n3;
        AbstractAdapter abstractAdapter2;
        for (int n2 = 0; n2 < this.mSmashArray.size() && abstractAdapter == null; ++n2, n = n3, abstractAdapter = abstractAdapter2) {
            if (this.mSmashArray.get(n2).getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || this.mSmashArray.get(n2).getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED || this.mSmashArray.get(n2).getMediationState() == AbstractSmash.MEDIATION_STATE.INIT_PENDING || this.mSmashArray.get(n2).getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING) {
                n3 = n + 1;
                abstractAdapter2 = abstractAdapter;
                if (n3 >= this.mSmartLoadAmount) {
                    break;
                }
            }
            else {
                n3 = n;
                abstractAdapter2 = abstractAdapter;
                if (this.mSmashArray.get(n2).getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_INITIATED) {
                    final AbstractAdapter startAdapter = this.startAdapter(this.mSmashArray.get(n2));
                    n3 = n;
                    if ((abstractAdapter2 = startAdapter) == null) {
                        this.mSmashArray.get(n2).setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
                        abstractAdapter2 = startAdapter;
                        n3 = n;
                    }
                }
            }
        }
        return abstractAdapter;
    }
    
    @Override
    public void initInterstitial(final Activity mActivity, String mAppKey, final String mUserId) {
        while (true) {
            Label_0389: {
                final int n2;
                Label_0380: {
                Label_0104_Outer:
                    while (true) {
                    Label_0229:
                        while (true) {
                            Label_0187: {
                                synchronized (this) {
                                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":initInterstitial(appKey: " + mAppKey + ", userId: " + mUserId + ")", 1);
                                    this.mAppKey = mAppKey;
                                    this.mUserId = mUserId;
                                    this.mActivity = mActivity;
                                    if (this.mIsInISDemandOnlyMode) {
                                        this.mSmartLoadAmount = this.mSmashArray.size();
                                        final Iterator<AbstractSmash> iterator = new CopyOnWriteArrayList<AbstractSmash>(this.mSmashArray).iterator();
                                        while (iterator.hasNext()) {
                                            mAppKey = (String)iterator.next();
                                            if (((AbstractSmash)mAppKey).getNameForReflection().equals("SupersonicAds")) {
                                                break Label_0187;
                                            }
                                            this.mSmashArray.remove(mAppKey);
                                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ((AbstractSmash)mAppKey).getAdSourceNameForEvents() + " has been removed from the IS waterfall due to demand only mode", 1);
                                        }
                                        break;
                                    }
                                    break Label_0229;
                                }
                            }
                            if (this.startAdapter((InterstitialSmash)mAppKey) == null) {
                                ((AbstractSmash)mAppKey).setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
                                continue;
                            }
                            this.mInstanceIdToSmashMap.put(((AbstractSmash)mAppKey).getSubProviderId(), (InterstitialSmash)mAppKey);
                            continue;
                        }
                        this.mDailyCappingManager.setContext((Context)this.mActivity);
                        int n = 0;
                        for (final AbstractSmash abstractSmash : this.mSmashArray) {
                            if (this.mDailyCappingManager.shouldSendCapReleasedEvent(abstractSmash)) {
                                this.logProviderEvent(250, abstractSmash, new Object[][] { { "status", "false" } });
                            }
                            if (this.mDailyCappingManager.isCapped(abstractSmash)) {
                                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                                ++n;
                                continue Label_0104_Outer;
                            }
                            continue Label_0104_Outer;
                            while (true) {
                                if (n2 >= this.mSmartLoadAmount || this.startNextAdapter() == null) {
                                    break Label_0104_Outer;
                                }
                                break Label_0380;
                            }
                        }
                        if (n == this.mSmashArray.size()) {
                            this.mDidFinishToInitInterstitial = true;
                        }
                        break Label_0389;
                    }
                    return;
                }
                ++n2;
                continue;
            }
            int n2 = 0;
            continue;
        }
    }
    // monitorexit(this)
    
    @Override
    public boolean isInterstitialReady() {
        final boolean b = false;
        synchronized (this) {
            boolean b2;
            if (this.mShouldTrackNetworkState && this.mActivity != null && !IronSourceUtils.isNetworkConnected((Context)this.mActivity)) {
                b2 = b;
            }
            else {
                final Iterator<AbstractSmash> iterator = this.mSmashArray.iterator();
                AbstractSmash abstractSmash;
                do {
                    b2 = b;
                    if (!iterator.hasNext()) {
                        return b2;
                    }
                    abstractSmash = iterator.next();
                } while (abstractSmash.getMediationState() != AbstractSmash.MEDIATION_STATE.AVAILABLE || !((InterstitialSmash)abstractSmash).isInterstitialReady());
                b2 = true;
            }
            return b2;
        }
    }
    
    public boolean isInterstitialReady(final String s) {
        final boolean b = false;
        synchronized (this) {
            boolean b2;
            if (this.mShouldTrackNetworkState && this.mActivity != null && !IronSourceUtils.isNetworkConnected((Context)this.mActivity)) {
                b2 = b;
            }
            else {
                final Iterator<AbstractSmash> iterator = this.mSmashArray.iterator();
                AbstractSmash abstractSmash;
                do {
                    b2 = b;
                    if (!iterator.hasNext()) {
                        return b2;
                    }
                    abstractSmash = iterator.next();
                } while (!abstractSmash.getSubProviderId().equals(s));
                b2 = b;
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                    final boolean interstitialReady = ((InterstitialSmash)abstractSmash).isInterstitialReady();
                    b2 = b;
                    if (interstitialReady) {
                        b2 = true;
                    }
                }
            }
            return b2;
        }
    }
    
    @Override
    public void loadInterstitial() {
        while (true) {
            Label_0187: {
                synchronized (this) {
                    try {
                        if (this.mIsLoadInterstitialInProgress || this.mCallbackThrotteler.hasPendingInvocation()) {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Load Interstitial is already in progress", 1);
                        }
                        else {
                            if (MediationInitializer.getInstance().getCurrentInitStatus() != EInitStatus.NOT_INIT) {
                                break Label_0187;
                            }
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
                        }
                        return;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        final IronSourceError buildLoadFailedError = ErrorBuilder.buildLoadFailedError("loadInterstitial exception " + ex.getMessage());
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, buildLoadFailedError.getErrorMessage(), 3);
                        this.mCallbackThrotteler.onInterstitialAdLoadFailed(buildLoadFailedError);
                        if (this.mShouldSendAdReadyEvent) {
                            this.mShouldSendAdReadyEvent = false;
                            this.logMediationEvent(227, new Object[][] { { "errorCode", buildLoadFailedError.getErrorCode() } });
                        }
                        return;
                    }
                }
            }
            final MediationInitializer.EInitStatus eInitStatus;
            if (eInitStatus == EInitStatus.INIT_IN_PROGRESS) {
                if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    return;
                }
                this.logMediationEvent(22, null);
                this.mDidCallLoadInterstitial = true;
                this.mShouldSendAdReadyEvent = true;
            }
            else {
                if (eInitStatus == EInitStatus.INIT_FAILED) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    return;
                }
                if (this.mSmashArray.size() == 0) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "the server response does not contain interstitial data", 3);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("the server response does not contain interstitial data", "Interstitial"));
                    return;
                }
                this.logMediationEvent(22, null);
                this.mShouldSendAdReadyEvent = true;
                this.changeStateToInitiated();
                if (this.smashesCount(AbstractSmash.MEDIATION_STATE.INITIATED) != 0) {
                    this.mDidCallLoadInterstitial = true;
                    this.mIsLoadInterstitialInProgress = true;
                    int n = 0;
                    for (final AbstractSmash abstractSmash : this.mSmashArray) {
                        if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED) {
                            abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
                            this.loadAdapterAndSendEvent((InterstitialSmash)abstractSmash);
                            if (++n >= this.mSmartLoadAmount) {
                                return;
                            }
                            continue;
                        }
                    }
                    return;
                }
                if (!this.mDidFinishToInitInterstitial) {
                    this.mDidCallLoadInterstitial = true;
                    return;
                }
                final IronSourceError buildGenericError = ErrorBuilder.buildGenericError("no ads to load");
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, buildGenericError.getErrorMessage(), 1);
                this.mCallbackThrotteler.onInterstitialAdLoadFailed(buildGenericError);
                this.logMediationEvent(227, new Object[][] { { "errorCode", buildGenericError.getErrorCode() } });
                this.mShouldSendAdReadyEvent = false;
            }
        }
    }
    
    public void loadInterstitial(final String s) {
        while (true) {
            Object o = null;
            Label_0123: {
                synchronized (this) {
                    try {
                        if (this.mCallbackThrotteler.hasPendingInvocation(s)) {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Load Interstitial for " + s + " is already in progress", 1);
                        }
                        else {
                            o = MediationInitializer.getInstance().getCurrentInitStatus();
                            if (o != EInitStatus.NOT_INIT) {
                                break Label_0123;
                            }
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
                        }
                        return;
                    }
                    catch (Exception ex) {
                        final IronSourceError buildLoadFailedError = ErrorBuilder.buildLoadFailedError("loadInterstitial exception");
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, buildLoadFailedError.getErrorMessage(), 3);
                        this.mCallbackThrotteler.onInterstitialAdLoadFailed(buildLoadFailedError);
                        return;
                    }
                }
            }
            if (o == EInitStatus.INIT_IN_PROGRESS) {
                final String s2;
                if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(s2, ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    return;
                }
                this.mInstancesToLoad.add(s2);
            }
            else {
                final String s2;
                if (o == EInitStatus.INIT_FAILED) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(s2, ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    return;
                }
                if (!this.mInstanceIdToSmashMap.containsKey(s2)) {
                    o = ErrorBuilder.buildNonExistentInstanceError("Interstitial");
                    this.mCallbackThrotteler.onInterstitialAdLoadFailed(s2, (IronSourceError)o);
                    this.logMediationEvent(22, null);
                    this.logMediationEvent(227, new Object[][] { { "errorCode", ((IronSourceError)o).getErrorCode() } });
                    return;
                }
                o = this.mInstanceIdToSmashMap.get(s2);
                if (((AbstractSmash)o).getMediationState() == AbstractSmash.MEDIATION_STATE.INIT_PENDING) {
                    this.mInstancesToLoad.add(s2);
                    return;
                }
                ((AbstractSmash)o).setMediationState(AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
                this.loadAdapterAndSendEvent((InterstitialSmash)o);
            }
        }
    }
    
    @Override
    public void onDailyCapReleased() {
        if (this.mSmashArray != null) {
            for (final AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY) {
                    this.logProviderEvent(250, abstractSmash, new Object[][] { { "status", "false" } });
                    if (abstractSmash.isCappedPerSession()) {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION);
                    }
                    else if (abstractSmash.isExhausted()) {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.EXHAUSTED);
                    }
                    else {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
                    }
                }
            }
        }
    }
    
    @Override
    public void onInitFailed(final String s) {
        if (this.mIsInISDemandOnlyMode) {
            final Iterator<String> iterator = this.mInstancesToLoad.iterator();
            while (iterator.hasNext()) {
                this.mCallbackThrotteler.onInterstitialAdLoadFailed(iterator.next(), ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            }
            this.mInstancesToLoad.clear();
        }
        else if (this.mDidCallLoadInterstitial) {
            this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            this.mDidCallLoadInterstitial = false;
            this.mIsLoadInterstitialInProgress = false;
        }
    }
    
    @Override
    public void onInitSuccess(final List<IronSource.AD_UNIT> list, final boolean b) {
    }
    
    @Override
    public void onInterstitialAdClicked(final InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdClicked()", 1);
        this.logProviderEvent(28, interstitialSmash, null);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdClicked(interstitialSmash.getSubProviderId());
            return;
        }
        this.mInterstitialListenersWrapper.onInterstitialAdClicked();
    }
    
    @Override
    public void onInterstitialAdClosed(final InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdClosed()", 1);
        this.verifyOnPauseOnResume();
        this.logProviderEvent(26, interstitialSmash, null);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdClosed(interstitialSmash.getSubProviderId());
            return;
        }
        this.mInterstitialListenersWrapper.onInterstitialAdClosed();
    }
    
    @Override
    public void onInterstitialAdLoadFailed(final IronSourceError ironSourceError, final InterstitialSmash interstitialSmash) {
        while (true) {
            int smashesCount = 0;
            Label_0237: {
                synchronized (this) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdLoadFailed(" + ironSourceError + ")", 1);
                    this.logProviderEvent(227, interstitialSmash, new Object[][] { { "errorCode", ironSourceError.getErrorCode() } });
                    if (this.mIsInISDemandOnlyMode) {
                        this.mCallbackThrotteler.onInterstitialAdLoadFailed(interstitialSmash.getSubProviderId(), ironSourceError);
                        this.logMediationEvent(227, new Object[][] { { "errorCode", ironSourceError.getErrorCode() } });
                    }
                    else {
                        interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE);
                        smashesCount = this.smashesCount(AbstractSmash.MEDIATION_STATE.AVAILABLE, AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
                        if (smashesCount < this.mSmartLoadAmount) {
                            Block_9: {
                                for (final AbstractSmash abstractSmash : this.mSmashArray) {
                                    if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED) {
                                        break Block_9;
                                    }
                                }
                                break Label_0237;
                            }
                            final AbstractSmash abstractSmash;
                            abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
                            this.loadAdapterAndSendEvent((InterstitialSmash)abstractSmash);
                        }
                    }
                    return;
                }
            }
            if (this.startNextAdapter() == null && this.mDidCallLoadInterstitial && this.smashesCount(AbstractSmash.MEDIATION_STATE.INIT_PENDING) + smashesCount == 0) {
                this.completeIterationRound();
                this.mIsLoadInterstitialInProgress = false;
                final IronSourceError ironSourceError2;
                this.mCallbackThrotteler.onInterstitialAdLoadFailed(ironSourceError2);
                this.logMediationEvent(227, new Object[][] { { "errorCode", ironSourceError2.getErrorCode() } });
            }
        }
    }
    
    @Override
    public void onInterstitialAdOpened(final InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdOpened()", 1);
        this.logProviderEvent(25, interstitialSmash, null);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdOpened(interstitialSmash.getSubProviderId());
            return;
        }
        this.mInterstitialListenersWrapper.onInterstitialAdOpened();
    }
    
    @Override
    public void onInterstitialAdReady(final InterstitialSmash interstitialSmash) {
        synchronized (this) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdReady()", 1);
            this.logProviderEvent(27, interstitialSmash, new Object[][] { { "status", "true" } });
            if (this.mIsInISDemandOnlyMode) {
                interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.AVAILABLE);
                this.mISDemandOnlyInterstitialListener.onInterstitialAdReady(interstitialSmash.getSubProviderId());
                this.logMediationEvent(27, new Object[][] { { "status", "true" } });
            }
            else {
                interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.AVAILABLE);
                this.mIsLoadInterstitialInProgress = false;
                if (this.mShouldSendAdReadyEvent) {
                    this.mShouldSendAdReadyEvent = false;
                    this.mInterstitialListenersWrapper.onInterstitialAdReady();
                    this.logMediationEvent(27, new Object[][] { { "status", "true" } });
                }
            }
        }
    }
    
    @Override
    public void onInterstitialAdRewarded(final InterstitialSmash interstitialSmash) {
        this.logProviderEvent(290, interstitialSmash, null);
        if (this.mRewardedInterstitialListenerWrapper != null) {
            this.mRewardedInterstitialListenerWrapper.onInterstitialAdRewarded();
        }
    }
    
    @Override
    public void onInterstitialAdShowFailed(final IronSourceError ironSourceError, final InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdShowFailed(" + ironSourceError + ")", 1);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(interstitialSmash.getSubProviderId(), ironSourceError);
            return;
        }
        this.completeAdapterShow(interstitialSmash);
        final Iterator<AbstractSmash> iterator = this.mSmashArray.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                this.mDidCallLoadInterstitial = true;
                this.showInterstitial(this.mCurrentPlacement.getPlacementName());
                return;
            }
        }
        this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ironSourceError);
    }
    
    @Override
    public void onInterstitialAdShowSucceeded(final InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdShowSucceeded()", 1);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdShowSucceeded(interstitialSmash.getSubProviderId());
            return;
        }
        boolean b = false;
        for (final AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                b = true;
                this.completeAdapterShow(abstractSmash);
            }
        }
        if (!b && (interstitialSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION || interstitialSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.EXHAUSTED || interstitialSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY)) {
            this.completeIterationRound();
        }
        this.changeStateToInitiated();
        this.mInterstitialListenersWrapper.onInterstitialAdShowSucceeded();
    }
    
    @Override
    public void onInterstitialAdVisible(final InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdVisible()", 1);
        this.logProviderEvent(31, interstitialSmash, new Object[][] { { "placement", this.mCurrentPlacement.getPlacementName() } });
    }
    
    @Override
    public void onInterstitialInitFailed(final IronSourceError ironSourceError, final InterstitialSmash interstitialSmash) {
        while (true) {
            Label_0351: {
                synchronized (this) {
                    try {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialInitFailed(" + ironSourceError + ")", 1);
                        if (this.mIsInISDemandOnlyMode) {
                            final String subProviderId = interstitialSmash.getSubProviderId();
                            if (this.mInstancesToLoad.contains(subProviderId)) {
                                this.mInstancesToLoad.remove(subProviderId);
                                this.mCallbackThrotteler.onInterstitialAdLoadFailed(subProviderId, ErrorBuilder.buildGenericError("no ads to show"));
                                this.logMediationEvent(227, new Object[][] { { "errorCode", 510 } });
                                this.logProviderEvent(227, interstitialSmash, new Object[][] { { "errorCode", 510 } });
                            }
                        }
                        else {
                            if (this.smashesCount(AbstractSmash.MEDIATION_STATE.INIT_FAILED) < this.mSmashArray.size()) {
                                break Label_0351;
                            }
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, "Smart Loading - initialization failed - no adapters are initiated and no more left to init, error: " + ironSourceError.getErrorMessage(), 2);
                            if (this.mDidCallLoadInterstitial) {
                                this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildGenericError("no ads to show"));
                                this.logMediationEvent(227, new Object[][] { { "errorCode", 510 } });
                                this.mShouldSendAdReadyEvent = false;
                            }
                            this.mDidFinishToInitInterstitial = true;
                        }
                        return;
                    }
                    catch (Exception ex) {
                        this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onInterstitialInitFailed(error:" + ironSourceError + ", " + "provider:" + interstitialSmash.getName() + ")", ex);
                        return;
                    }
                }
            }
            if (this.startNextAdapter() == null && this.mDidCallLoadInterstitial && this.smashesCount(AbstractSmash.MEDIATION_STATE.INIT_FAILED, AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE, AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION, AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY, AbstractSmash.MEDIATION_STATE.EXHAUSTED) >= this.mSmashArray.size()) {
                this.mCallbackThrotteler.onInterstitialAdLoadFailed(ErrorBuilder.buildGenericError("no ads to show"));
                this.logMediationEvent(227, new Object[][] { { "errorCode", 510 } });
                this.mShouldSendAdReadyEvent = false;
            }
            this.completeIterationRound();
        }
    }
    
    @Override
    public void onInterstitialInitSuccess(final InterstitialSmash interstitialSmash) {
        synchronized (this) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + " :onInterstitialInitSuccess()", 1);
            this.mDidFinishToInitInterstitial = true;
            if (this.mIsInISDemandOnlyMode) {
                final String subProviderId = interstitialSmash.getSubProviderId();
                if (this.mInstancesToLoad.contains(subProviderId)) {
                    this.mInstancesToLoad.remove(subProviderId);
                    this.loadInterstitial(subProviderId);
                }
            }
            else if (this.mDidCallLoadInterstitial && this.smashesCount(AbstractSmash.MEDIATION_STATE.AVAILABLE, AbstractSmash.MEDIATION_STATE.LOAD_PENDING) < this.mSmartLoadAmount) {
                interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
                this.loadAdapterAndSendEvent(interstitialSmash);
            }
        }
    }
    
    @Override
    public void onStillInProgressAfter15Secs() {
        if (this.mIsInISDemandOnlyMode) {
            final Iterator<String> iterator = this.mInstancesToLoad.iterator();
            while (iterator.hasNext()) {
                this.mCallbackThrotteler.onInterstitialAdLoadFailed(iterator.next(), ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            }
            this.mInstancesToLoad.clear();
        }
        else if (this.mDidCallLoadInterstitial) {
            final IronSourceError buildInitFailedError = ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial");
            this.mCallbackThrotteler.onInterstitialAdLoadFailed(buildInitFailedError);
            this.mDidCallLoadInterstitial = false;
            this.mIsLoadInterstitialInProgress = false;
            if (this.mShouldSendAdReadyEvent) {
                this.logMediationEvent(227, new Object[][] { { "errorCode", buildInitFailedError.getErrorCode() } });
                this.mShouldSendAdReadyEvent = false;
            }
        }
    }
    
    void setCurrentPlacement(final InterstitialPlacement mCurrentPlacement) {
        this.mCurrentPlacement = mCurrentPlacement;
    }
    
    public void setISDemandOnlyInterstitialListener(final ISDemandOnlyInterstitialListener isDemandOnlyInterstitialListener) {
        this.mISDemandOnlyInterstitialListener = isDemandOnlyInterstitialListener;
        this.mCallbackThrotteler.setISDemandOnlyInterstitialListener(isDemandOnlyInterstitialListener);
    }
    
    @Override
    public void setInterstitialListener(final InterstitialListener interstitialListener) {
        this.mInterstitialListenersWrapper = (ListenersWrapper)interstitialListener;
        this.mCallbackThrotteler.setInterstitialListener(interstitialListener);
    }
    
    @Override
    public void setRewardedInterstitialListener(final RewardedInterstitialListener mRewardedInterstitialListenerWrapper) {
        this.mRewardedInterstitialListenerWrapper = mRewardedInterstitialListenerWrapper;
    }
    
    @Override
    void shouldTrackNetworkState(final Context context, final boolean mShouldTrackNetworkState) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, this.TAG + " Should Track Network State: " + mShouldTrackNetworkState, 0);
        this.mShouldTrackNetworkState = mShouldTrackNetworkState;
    }
    
    @Override
    public void showInterstitial(final String s) {
        if (this.mShouldTrackNetworkState && this.mActivity != null && !IronSourceUtils.isNetworkConnected((Context)this.mActivity)) {
            this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError("Interstitial"));
        }
        else {
            if (!this.mDidCallLoadInterstitial) {
                this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", "showInterstitial failed - You need to load interstitial before showing it"));
                return;
            }
            int i = 0;
            while (i < this.mSmashArray.size()) {
                final AbstractSmash abstractSmash = this.mSmashArray.get(i);
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                    CappingManager.incrementShowCounter((Context)this.mActivity, this.mCurrentPlacement);
                    this.logProviderEvent(23, abstractSmash, new Object[][] { { "placement", s } });
                    this.sendShowChanceEvents(abstractSmash, i, s);
                    ((InterstitialSmash)abstractSmash).showInterstitial();
                    this.mDailyCappingManager.increaseShowCounter(abstractSmash);
                    if (this.mDailyCappingManager.isCapped(abstractSmash)) {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                        this.logProviderEvent(250, abstractSmash, new Object[][] { { "status", "true" } });
                    }
                    this.mDidCallLoadInterstitial = false;
                    if (!abstractSmash.isMediationAvailable()) {
                        this.startNextAdapter();
                    }
                    return;
                }
                else {
                    ++i;
                }
            }
            this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", "showInterstitial failed - No adapters ready to show"));
        }
    }
    
    public void showInterstitial(final String s, final String s2) {
        if (this.mShouldTrackNetworkState && this.mActivity != null && !IronSourceUtils.isNetworkConnected((Context)this.mActivity)) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(s, ErrorBuilder.buildNoInternetConnectionShowFailError("Interstitial"));
            return;
        }
        boolean b = false;
        for (int i = 0; i < this.mSmashArray.size(); ++i) {
            final AbstractSmash abstractSmash = this.mSmashArray.get(i);
            if (abstractSmash.getSubProviderId().equals(s)) {
                b = true;
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                    CappingManager.incrementShowCounter((Context)this.mActivity, this.mCurrentPlacement);
                    this.logProviderEvent(23, abstractSmash, new Object[][] { { "placement", s2 } });
                    this.sendShowChanceEvents(abstractSmash, i, s2);
                    ((InterstitialSmash)abstractSmash).showInterstitial();
                    this.changeStateToInitiatedForInstanceId(s);
                    return;
                }
            }
        }
        if (!b) {
            this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(s, ErrorBuilder.buildNonExistentInstanceError("no ads to show"));
            return;
        }
        this.mISDemandOnlyInterstitialListener.onInterstitialAdShowFailed(s, ErrorBuilder.buildShowFailedError("Interstitial", "no ads to show"));
    }
}
