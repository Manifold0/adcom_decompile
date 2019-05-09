// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.Map;
import org.json.JSONException;
import android.text.TextUtils;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import android.app.Activity;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.logger.LogListener;
import android.content.Context;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.server.Server;
import org.json.JSONObject;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import android.util.Log;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.Iterator;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import java.util.Arrays;
import java.util.List;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.utils.DailyCappingListener;
import com.ironsource.environment.NetworkStateReceiver;
import com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoApi;

class RewardedVideoManager extends AbstractAdUnitManager implements RewardedVideoApi, RewardedVideoManagerListener, NetworkStateReceiverListener, DailyCappingListener
{
    private final int CAPPED_PER_DAY_REASON;
    private final int CAPPED_PER_SESSION_REASON;
    private final String TAG;
    private Placement mCurrentPlacement;
    private ISDemandOnlyRewardedVideoListener mISDemandOnlyListenersWrapper;
    private boolean mIsUltraEventsEnabled;
    private RewardedVideoListener mListenersWrapper;
    private NetworkStateReceiver mNetworkStateReceiver;
    private boolean mPauseSmartLoadDueToNetworkUnavailability;
    private List<AbstractSmash.MEDIATION_STATE> mStatesToIgnore;
    
    RewardedVideoManager() {
        this.TAG = this.getClass().getSimpleName();
        this.CAPPED_PER_SESSION_REASON = 2;
        this.CAPPED_PER_DAY_REASON = 6;
        this.mPauseSmartLoadDueToNetworkUnavailability = false;
        this.mIsUltraEventsEnabled = false;
        this.mStatesToIgnore = Arrays.asList(AbstractSmash.MEDIATION_STATE.INIT_FAILED, AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION, AbstractSmash.MEDIATION_STATE.EXHAUSTED, AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
        this.mDailyCappingManager = new DailyCappingManager("rewarded_video", this);
    }
    
    private void completeAdapterCap() {
        while (true) {
            Label_0062: {
                synchronized (this) {
                    if (this.loadNextAdapter() == null) {
                        if (this.smashesCount(AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE, AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION, AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY) >= this.mSmashArray.size()) {
                            break Label_0062;
                        }
                        this.completeIterationRound();
                    }
                    return;
                }
            }
            if (this.shouldNotifyAvailabilityChanged(false)) {
                this.notifyAvailabilityChange();
            }
        }
    }
    
    private void completeIterationRound() {
        synchronized (this) {
            if (this.isIterationRoundComplete()) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Reset Iteration", 0);
                boolean b = false;
                for (final AbstractSmash abstractSmash : this.mSmashArray) {
                    if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.EXHAUSTED) {
                        abstractSmash.completeIteration();
                    }
                    if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                        b = true;
                    }
                }
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "End of Reset Iteration", 0);
                if (this.shouldNotifyAvailabilityChanged(b)) {
                    this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState);
                }
            }
        }
    }
    
    private void createAndSendShowCheckAvailabilityEvent(final AbstractSmash abstractSmash, final String s, final boolean b) {
        String s2;
        if (b) {
            s2 = "true";
        }
        else {
            s2 = "false";
        }
        this.logProviderEvent(19, abstractSmash, new Object[][] { { "placement", s }, { "status", s2 } });
    }
    
    private boolean hasAvailableSmash() {
        // monitorenter(this)
        final boolean b = false;
        try {
            final Iterator<AbstractSmash> iterator = this.mSmashArray.iterator();
            do {
                final boolean b2 = b;
                if (iterator.hasNext()) {
                    continue;
                }
                return b2;
            } while (iterator.next().getMediationState() != AbstractSmash.MEDIATION_STATE.AVAILABLE);
            return true;
        }
        finally {
        }
        // monitorexit(this)
    }
    
    private boolean isAllAdaptersInactive() {
        while (true) {
            // monitorenter(this)
            int n = 0;
            while (true) {
                Label_0124: {
                    try {
                        for (final AbstractSmash abstractSmash : this.mSmashArray) {
                            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INIT_FAILED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                                break Label_0124;
                            }
                            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.EXHAUSTED) {
                                break Label_0124;
                            }
                        }
                        return this.mSmashArray.size() == n;
                    }
                    finally {
                    }
                    // monitorexit(this)
                }
                ++n;
                continue;
            }
        }
    }
    
    private boolean isBackFillAvailable() {
        synchronized (this) {
            return this.getBackfillSmash() != null && ((RewardedVideoSmash)this.getBackfillSmash()).isRewardedVideoAvailable();
        }
    }
    
    private boolean isIterationRoundComplete() {
        synchronized (this) {
            for (final AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                    return false;
                }
            }
            return true;
        }
    }
    
    private AbstractAdapter loadNextAdapter() {
        AbstractAdapter abstractAdapter = null;
        int n = 0;
        int n3;
        AbstractAdapter abstractAdapter2;
        for (int n2 = 0; n2 < this.mSmashArray.size() && abstractAdapter == null; ++n2, n = n3, abstractAdapter = abstractAdapter2) {
            if (this.mSmashArray.get(n2).getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || this.mSmashArray.get(n2).getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED) {
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
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "RewardedVideoManager logMediationEvent " + Log.getStackTraceString((Throwable)ex), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(n, mediationAdditionalData));
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
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "RewardedVideoManager logProviderEvent " + Log.getStackTraceString((Throwable)ex), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(n, (JSONObject)providerAdditionalData));
    }
    
    private void notifyAvailabilityChange() {
        while (true) {
            Label_0093: {
                synchronized (this) {
                    if (this.getBackfillSmash() != null && !this.mBackFillInitStarted) {
                        this.mBackFillInitStarted = true;
                        if (this.startAdapter((RewardedVideoSmash)this.getBackfillSmash()) == null) {
                            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState);
                        }
                    }
                    else {
                        if (!this.isBackFillAvailable()) {
                            break Label_0093;
                        }
                        if (this.shouldNotifyAvailabilityChanged(true)) {
                            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState);
                        }
                    }
                    return;
                }
            }
            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState);
        }
    }
    
    private void notifyIsAdAvailableForStatistics() {
        boolean b;
        Iterator<AbstractSmash> iterator;
        boolean b2;
        Iterator<AbstractSmash> iterator2;
        AbstractSmash abstractSmash = null;
        Label_0043_Outer:Label_0079_Outer:
        while (true) {
            // monitorenter(this)
            b = false;
            while (true) {
                Label_0146: {
                    try {
                        iterator = this.mSmashArray.iterator();
                        while (true) {
                            do {
                                b2 = b;
                                if (iterator.hasNext()) {
                                    continue Label_0043_Outer;
                                }
                                this.logMediationEvent(3, new Object[][] { { "status", String.valueOf(b2) } });
                                iterator2 = this.mSmashArray.iterator();
                                while (iterator2.hasNext()) {
                                    abstractSmash = iterator2.next();
                                    if (abstractSmash.getMediationState() != AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                                        break Label_0146;
                                    }
                                    this.logProviderEvent(3, abstractSmash, new Object[][] { { "status", "true" } });
                                }
                                break Label_0043_Outer;
                            } while (iterator.next().getMediationState() != AbstractSmash.MEDIATION_STATE.AVAILABLE);
                            b2 = true;
                            continue Label_0079_Outer;
                        }
                    }
                    finally {
                    }
                    // monitorexit(this)
                }
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED) {
                    this.logProviderEvent(3, abstractSmash, new Object[][] { { "status", "false" } });
                    continue;
                }
                continue;
            }
        }
        if (this.getBackfillSmash() != null && this.getBackfillSmash().getAdapter() != null) {
            final AbstractSmash backfillSmash = this.getBackfillSmash();
            String s;
            if (this.isBackFillAvailable()) {
                s = "true";
            }
            else {
                s = "false";
            }
            this.logProviderEvent(3, backfillSmash, new Object[][] { { "status", s } });
        }
    }
    // monitorexit(this)
    
    private void reportFalseImpressionsOnHigherPriority(final int n, final int n2) {
        for (int n3 = 0; n3 < n && n3 < this.mSmashArray.size(); ++n3) {
            if (!this.mStatesToIgnore.contains(this.mSmashArray.get(n3).getMediationState())) {
                this.reportImpression(((RewardedVideoSmash)this.mSmashArray.get(n3)).getRequestUrl(), false, n2);
            }
        }
    }
    
    private void reportImpression(String s, final boolean b, final int n) {
        // monitorenter(this)
        String s2 = "";
        try {
            s = (s2 = "" + s);
            s = (s2 = s + "&sdkVersion=" + IronSourceUtils.getSDKVersion());
            Server.callAsyncRequestURL(s, b, n);
        }
        catch (Throwable t) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.NETWORK, "reportImpression:(providerURL:" + s2 + ", " + "hit:" + b + ")", t);
        }
        finally {
        }
        // monitorexit(this)
    }
    
    private void sendShowChanceEvent(final AbstractSmash abstractSmash, final String s, final boolean b) {
        String s2;
        if (b) {
            s2 = "true";
        }
        else {
            s2 = "false";
        }
        this.logProviderEvent(119, abstractSmash, new Object[][] { { "placement", s }, { "status", s2 } });
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
    
    private void sendShowCheckAvailabilityEvents(final String s) {
        for (int i = 0; i < this.mSmashArray.size(); ++i) {
            if (this.mSmashArray.get(i).getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                this.createAndSendShowCheckAvailabilityEvent(this.mSmashArray.get(i), s, true);
            }
            else if (this.mSmashArray.get(i).getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                this.createAndSendShowCheckAvailabilityEvent(this.mSmashArray.get(i), s, false);
            }
        }
        if (this.getBackfillSmash() != null && this.getBackfillSmash().getAdapter() != null) {
            this.createAndSendShowCheckAvailabilityEvent(this.getBackfillSmash(), s, this.isBackFillAvailable());
        }
    }
    
    private boolean shouldNotifyAvailabilityChanged(final boolean b) {
        // monitorenter(this)
        final boolean b2 = false;
        try {
            boolean b3;
            if (this.mLastMediationAvailabilityState == null) {
                if (b) {
                    this.mLastMediationAvailabilityState = true;
                    b3 = true;
                }
                else {
                    b3 = b2;
                    if (!this.isBackFillAvailable()) {
                        b3 = b2;
                        if (this.isAllAdaptersInactive()) {
                            this.mLastMediationAvailabilityState = false;
                            b3 = true;
                        }
                    }
                }
            }
            else if (b && !this.mLastMediationAvailabilityState) {
                this.mLastMediationAvailabilityState = true;
                b3 = true;
            }
            else {
                b3 = b2;
                if (!b) {
                    b3 = b2;
                    if (this.mLastMediationAvailabilityState) {
                        b3 = b2;
                        if (!this.hasAvailableSmash()) {
                            b3 = b2;
                            if (!this.isBackFillAvailable()) {
                                this.mLastMediationAvailabilityState = false;
                                b3 = true;
                            }
                        }
                    }
                }
            }
            return b3;
        }
        finally {
        }
        // monitorexit(this)
    }
    
    private boolean shouldNotifyNetworkAvailabilityChanged(final boolean b) {
        final boolean b2 = false;
        if (this.mLastMediationAvailabilityState == null) {
            return false;
        }
        boolean b3;
        if (b && !this.mLastMediationAvailabilityState && this.hasAvailableSmash()) {
            this.mLastMediationAvailabilityState = true;
            b3 = true;
        }
        else {
            b3 = b2;
            if (!b) {
                b3 = b2;
                if (this.mLastMediationAvailabilityState) {
                    this.mLastMediationAvailabilityState = false;
                    b3 = true;
                }
            }
        }
        return b3;
    }
    
    private void showAdapter(final AbstractSmash abstractSmash, final int n) {
        synchronized (this) {
            CappingManager.incrementShowCounter((Context)this.mActivity, this.mCurrentPlacement);
            this.mDailyCappingManager.increaseShowCounter(abstractSmash);
            if (this.mIsUltraEventsEnabled) {
                this.reportImpression(((RewardedVideoSmash)abstractSmash).getRequestUrl(), true, this.mCurrentPlacement.getPlacementId());
                this.reportFalseImpressionsOnHigherPriority(n, this.mCurrentPlacement.getPlacementId());
            }
            this.logProviderEvent(2, abstractSmash, new Object[][] { { "placement", this.mCurrentPlacement.getPlacementName() } });
            this.sendShowChanceEvents(abstractSmash, n, this.mCurrentPlacement.getPlacementName());
            ((RewardedVideoSmash)abstractSmash).showRewardedVideo();
        }
    }
    
    private int smashesCount(final AbstractSmash.MEDIATION_STATE... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/ironsource/mediationsdk/RewardedVideoManager.mSmashArray:Ljava/util/concurrent/CopyOnWriteArrayList;
        //     4: astore          6
        //     6: aload           6
        //     8: monitorenter   
        //     9: iconst_0       
        //    10: istore          4
        //    12: aload_0        
        //    13: getfield        com/ironsource/mediationsdk/RewardedVideoManager.mSmashArray:Ljava/util/concurrent/CopyOnWriteArrayList;
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
    
    private AbstractAdapter startAdapter(RewardedVideoSmash customParams) {
        synchronized (this) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":startAdapter(" + ((AbstractSmash)customParams).getInstanceName() + ")", 1);
            try {
                final AbstractAdapter loadedAdapterOrFetchByReflection = this.getLoadedAdapterOrFetchByReflection((AbstractSmash)customParams);
                if (loadedAdapterOrFetchByReflection == null) {
                    customParams = null;
                }
                else {
                    IronSourceObject.getInstance().addToRVAdaptersList(loadedAdapterOrFetchByReflection);
                    loadedAdapterOrFetchByReflection.setLogListener(this.mLoggerManager);
                    ((AbstractSmash)customParams).setAdapterForSmash(loadedAdapterOrFetchByReflection);
                    ((AbstractSmash)customParams).setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
                    this.setCustomParams((AbstractSmash)customParams);
                    ((RewardedVideoSmash)customParams).initRewardedVideo(this.mActivity, this.mAppKey, this.mUserId);
                    customParams = loadedAdapterOrFetchByReflection;
                }
                return (AbstractAdapter)customParams;
            }
            catch (Throwable t) {
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":startAdapter(" + ((AbstractSmash)customParams).getName() + ")", t);
                ((AbstractSmash)customParams).setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
                if (this.shouldNotifyAvailabilityChanged(false)) {
                    this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState);
                }
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ErrorBuilder.buildInitFailedError(((AbstractSmash)customParams).getName() + " initialization failed - please verify that required dependencies are in you build path.", "Rewarded Video").toString(), 2);
                customParams = null;
                return (AbstractAdapter)customParams;
            }
            return (AbstractAdapter)customParams;
        }
    }
    
    protected void disablePremiumForCurrentSession() {
        synchronized (this) {
            super.disablePremiumForCurrentSession();
            for (final AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.equals(this.getPremiumSmash())) {
                    abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION);
                    this.loadNextAdapter();
                    break;
                }
            }
        }
    }
    
    @Override
    public void initRewardedVideo(final Activity mActivity, String mAppKey, final String mUserId) {
        // monitorexit(this)
        while (true) {
            Label_0341: {
                while (true) {
                Label_0208:
                    while (true) {
                        Label_0187: {
                            synchronized (this) {
                                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":initRewardedVideo(appKey: " + mAppKey + ", userId: " + mUserId + ")", 1);
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
                                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ((AbstractSmash)mAppKey).getAdSourceNameForEvents() + " has been removed from the RV waterfall due to demand only mode", 1);
                                    }
                                    break;
                                }
                                break Label_0208;
                            }
                        }
                        if (this.startAdapter((RewardedVideoSmash)mAppKey) == null) {
                            ((AbstractSmash)mAppKey).setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
                            continue;
                        }
                        continue;
                    }
                    this.mDailyCappingManager.setContext((Context)this.mActivity);
                    int n = 0;
                    for (final AbstractSmash abstractSmash : this.mSmashArray) {
                        if (this.mDailyCappingManager.shouldSendCapReleasedEvent(abstractSmash)) {
                            this.logProviderEvent(150, abstractSmash, new Object[][] { { "status", "false" } });
                        }
                        if (this.mDailyCappingManager.isCapped(abstractSmash)) {
                            abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                            ++n;
                        }
                    }
                    if (n == this.mSmashArray.size()) {
                        this.mListenersWrapper.onRewardedVideoAvailabilityChanged(false);
                        break;
                    }
                    break Label_0341;
                }
                return;
            }
            for (int n2 = 0; n2 < this.mSmartLoadAmount && n2 < this.mSmashArray.size() && this.loadNextAdapter() != null; ++n2) {}
            continue;
        }
    }
    
    @Override
    public boolean isRewardedVideoAvailable() {
        final boolean b = false;
        synchronized (this) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":isRewardedVideoAvailable()", 1);
            boolean b2;
            if (this.mPauseSmartLoadDueToNetworkUnavailability) {
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
                } while (!abstractSmash.isMediationAvailable() || !((RewardedVideoSmash)abstractSmash).isRewardedVideoAvailable());
                b2 = true;
            }
            return b2;
        }
    }
    
    public boolean isRewardedVideoAvailable(final String s) {
        final boolean b = false;
        synchronized (this) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":isRewardedVideoAvailable(instanceId: " + s + ")", 1);
            boolean rewardedVideoAvailable;
            if (this.mPauseSmartLoadDueToNetworkUnavailability) {
                rewardedVideoAvailable = b;
            }
            else {
                final Iterator<AbstractSmash> iterator = this.mSmashArray.iterator();
                AbstractSmash abstractSmash;
                do {
                    rewardedVideoAvailable = b;
                    if (!iterator.hasNext()) {
                        return rewardedVideoAvailable;
                    }
                    abstractSmash = iterator.next();
                } while (!abstractSmash.getSubProviderId().equals(s));
                rewardedVideoAvailable = ((RewardedVideoSmash)abstractSmash).isRewardedVideoAvailable();
            }
            return rewardedVideoAvailable;
        }
    }
    
    @Override
    public void onDailyCapReleased() {
        boolean b = false;
        for (final AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY) {
                this.logProviderEvent(150, abstractSmash, new Object[][] { { "status", "false" } });
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE);
                if (!((RewardedVideoSmash)abstractSmash).isRewardedVideoAvailable() || !abstractSmash.isMediationAvailable()) {
                    continue;
                }
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.AVAILABLE);
                b = true;
            }
        }
        if (b && this.shouldNotifyAvailabilityChanged(true)) {
            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(true);
        }
    }
    
    @Override
    public void onNetworkAvailabilityChanged(final boolean b) {
        boolean mPauseSmartLoadDueToNetworkUnavailability = false;
        if (this.mShouldTrackNetworkState) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Network Availability Changed To: " + b, 0);
            if (this.shouldNotifyNetworkAvailabilityChanged(b)) {
                if (!b) {
                    mPauseSmartLoadDueToNetworkUnavailability = true;
                }
                this.mPauseSmartLoadDueToNetworkUnavailability = mPauseSmartLoadDueToNetworkUnavailability;
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(b);
            }
        }
    }
    
    @Override
    public void onRewardedVideoAdClicked(final RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdClicked()", 1);
        this.logProviderEvent(128, rewardedVideoSmash, new Object[][] { { "placement", this.mCurrentPlacement.getPlacementName() } });
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdClicked(rewardedVideoSmash.getSubProviderId(), this.mCurrentPlacement);
            return;
        }
        this.mListenersWrapper.onRewardedVideoAdClicked(this.mCurrentPlacement);
    }
    
    @Override
    public void onRewardedVideoAdClosed(final RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdClosed()", 1);
        this.verifyOnPauseOnResume();
        this.logProviderEvent(6, rewardedVideoSmash, null);
        this.notifyIsAdAvailableForStatistics();
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdClosed(rewardedVideoSmash.getSubProviderId());
        }
        else {
            this.mListenersWrapper.onRewardedVideoAdClosed();
            for (final AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                    try {
                        if (abstractSmash.getInstanceName().equals(rewardedVideoSmash.getInstanceName())) {
                            continue;
                        }
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, abstractSmash.getInstanceName() + ":reload smash", 1);
                        ((RewardedVideoSmash)abstractSmash).fetchRewardedVideo();
                    }
                    catch (Throwable t) {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, abstractSmash.getInstanceName() + " Failed to call fetchVideo(), " + t.getLocalizedMessage(), 1);
                    }
                }
            }
        }
    }
    
    @Override
    public void onRewardedVideoAdEnded(final RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdEnded()", 1);
        this.logProviderEvent(9, rewardedVideoSmash, null);
        this.mListenersWrapper.onRewardedVideoAdEnded();
    }
    
    @Override
    public void onRewardedVideoAdOpened(final RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdOpened()", 1);
        this.logProviderEvent(5, rewardedVideoSmash, null);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdOpened(rewardedVideoSmash.getSubProviderId());
            return;
        }
        this.mListenersWrapper.onRewardedVideoAdOpened();
    }
    
    @Override
    public void onRewardedVideoAdRewarded(final RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdRewarded()", 1);
        Object providerAdditionalData = IronSourceUtils.getProviderAdditionalData(rewardedVideoSmash, this.mIsInISDemandOnlyMode);
        while (true) {
            try {
                ((JSONObject)providerAdditionalData).put("placement", (Object)this.mCurrentPlacement.getPlacementName());
                ((JSONObject)providerAdditionalData).put("rewardName", (Object)this.mCurrentPlacement.getRewardName());
                ((JSONObject)providerAdditionalData).put("rewardAmount", this.mCurrentPlacement.getRewardAmount());
                providerAdditionalData = new EventData(10, (JSONObject)providerAdditionalData);
                if (!TextUtils.isEmpty((CharSequence)this.mAppKey)) {
                    ((EventData)providerAdditionalData).addToAdditionalData("transId", IronSourceUtils.getTransId("" + Long.toString(((EventData)providerAdditionalData).getTimeStamp()) + this.mAppKey + rewardedVideoSmash.getName()));
                    if (!TextUtils.isEmpty((CharSequence)IronSourceObject.getInstance().getDynamicUserId())) {
                        ((EventData)providerAdditionalData).addToAdditionalData("dynamicUserId", IronSourceObject.getInstance().getDynamicUserId());
                    }
                    final Map<String, String> rvServerParams = IronSourceObject.getInstance().getRvServerParams();
                    if (rvServerParams != null) {
                        for (final String s : rvServerParams.keySet()) {
                            ((EventData)providerAdditionalData).addToAdditionalData("custom_" + s, rvServerParams.get(s));
                        }
                    }
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
        RewardedVideoEventsManager.getInstance().log((EventData)providerAdditionalData);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdRewarded(rewardedVideoSmash.getSubProviderId(), this.mCurrentPlacement);
            return;
        }
        this.mListenersWrapper.onRewardedVideoAdRewarded(this.mCurrentPlacement);
    }
    
    @Override
    public void onRewardedVideoAdShowFailed(final IronSourceError ironSourceError, final RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdShowFailed(" + ironSourceError + ")", 1);
        if (this.mIsInISDemandOnlyMode) {
            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(rewardedVideoSmash.getSubProviderId(), ironSourceError);
            return;
        }
        this.mListenersWrapper.onRewardedVideoAdShowFailed(ironSourceError);
    }
    
    @Override
    public void onRewardedVideoAdStarted(final RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdStarted()", 1);
        this.logProviderEvent(8, rewardedVideoSmash, null);
        this.mListenersWrapper.onRewardedVideoAdStarted();
    }
    
    @Override
    public void onRewardedVideoAdVisible(final RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdVisible()", 1);
        this.logProviderEvent(11, rewardedVideoSmash, new Object[][] { { "placement", this.mCurrentPlacement.getPlacementName() } });
    }
    
    @Override
    public void onRewardedVideoAvailabilityChanged(final boolean b, final RewardedVideoSmash rewardedVideoSmash) {
        while (true) {
            synchronized (this) {
                if (this.mPauseSmartLoadDueToNetworkUnavailability) {
                    return;
                }
                while (true) {
                    try {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAvailabilityChanged(available:" + b + ")", 1);
                        this.logProviderEvent(7, rewardedVideoSmash, new Object[][] { { "status", String.valueOf(b) } });
                        if (this.mIsInISDemandOnlyMode) {
                            this.mISDemandOnlyListenersWrapper.onRewardedVideoAvailabilityChanged(rewardedVideoSmash.getSubProviderId(), b);
                            if (this.shouldNotifyAvailabilityChanged(b)) {
                                this.logMediationEvent(7, new Object[][] { { "status", String.valueOf(b) } });
                            }
                        }
                        else {
                            if (!rewardedVideoSmash.equals(this.getBackfillSmash())) {
                                break;
                            }
                            if (this.shouldNotifyAvailabilityChanged(b)) {
                                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState);
                            }
                        }
                    }
                    catch (Throwable t) {
                        this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onRewardedVideoAvailabilityChanged(available:" + b + ", " + "provider:" + rewardedVideoSmash.getName() + ")", t);
                    }
                    return;
                }
            }
            if (rewardedVideoSmash.equals(this.getPremiumSmash())) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + " is a Premium adapter, canShowPremium: " + this.canShowPremium(), 1);
                if (!this.canShowPremium()) {
                    rewardedVideoSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION);
                    if (this.shouldNotifyAvailabilityChanged(false)) {
                        this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState);
                    }
                    return;
                }
            }
            if (!rewardedVideoSmash.isMediationAvailable() || this.mDailyCappingManager.isCapped(rewardedVideoSmash)) {
                return;
            }
            if (!b) {
                if (this.shouldNotifyAvailabilityChanged(false)) {
                    this.notifyAvailabilityChange();
                }
                this.loadNextAdapter();
                this.completeIterationRound();
                return;
            }
            if (this.shouldNotifyAvailabilityChanged(true)) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState);
            }
        }
    }
    
    void setCurrentPlacement(final Placement mCurrentPlacement) {
        this.mCurrentPlacement = mCurrentPlacement;
    }
    
    public void setISDemandOnlyRewardedVideoListener(final ISDemandOnlyRewardedVideoListener misDemandOnlyListenersWrapper) {
        this.mISDemandOnlyListenersWrapper = misDemandOnlyListenersWrapper;
    }
    
    void setIsUltraEventsEnabled(final boolean mIsUltraEventsEnabled) {
        this.mIsUltraEventsEnabled = mIsUltraEventsEnabled;
    }
    
    @Override
    public void setRewardedVideoListener(final RewardedVideoListener mListenersWrapper) {
        this.mListenersWrapper = mListenersWrapper;
    }
    
    @Override
    void shouldTrackNetworkState(final Context context, final boolean mShouldTrackNetworkState) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, this.TAG + " Should Track Network State: " + mShouldTrackNetworkState, 0);
        this.mShouldTrackNetworkState = mShouldTrackNetworkState;
        if (this.mShouldTrackNetworkState) {
            if (this.mNetworkStateReceiver == null) {
                this.mNetworkStateReceiver = new NetworkStateReceiver(context, (NetworkStateReceiver.NetworkStateReceiverListener)this);
            }
            context.registerReceiver((BroadcastReceiver)this.mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        else if (this.mNetworkStateReceiver != null) {
            context.unregisterReceiver((BroadcastReceiver)this.mNetworkStateReceiver);
        }
    }
    
    @Override
    public void showRewardedVideo(final String s) {
        while (true) {
            while (true) {
                int n3 = 0;
                int n4 = 0;
                int n5 = 0;
                Label_0528: {
                    int n = 0;
                    int n2 = 0;
                    Label_0471: {
                        final RewardedVideoSmash rewardedVideoSmash;
                        Label_0419: {
                            Label_0353: {
                                Label_0231: {
                                    synchronized (this) {
                                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":showRewardedVideo(placementName: " + s + ")", 1);
                                        if (!IronSourceUtils.isNetworkConnected((Context)this.mActivity)) {
                                            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError("Rewarded Video"));
                                        }
                                        else {
                                            this.sendShowCheckAvailabilityEvents(s);
                                            n = 0;
                                            n2 = 0;
                                            n3 = 0;
                                            if (n3 >= this.mSmashArray.size()) {
                                                break Label_0471;
                                            }
                                            final AbstractSmash abstractSmash = this.mSmashArray.get(n3);
                                            if (abstractSmash.getMediationState() != AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                                                break Label_0419;
                                            }
                                            if (!((RewardedVideoSmash)abstractSmash).isRewardedVideoAvailable()) {
                                                break Label_0353;
                                            }
                                            this.showAdapter(abstractSmash, n3);
                                            if (this.mCanShowPremium && !abstractSmash.equals(this.getPremiumSmash())) {
                                                this.disablePremiumForCurrentSession();
                                            }
                                            if (!abstractSmash.isCappedPerSession()) {
                                                break Label_0231;
                                            }
                                            abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION);
                                            this.logProviderEvent(7, abstractSmash, new Object[][] { { "status", "false" }, { "reason", 2 } });
                                            this.completeAdapterCap();
                                        }
                                        return;
                                    }
                                }
                                if (this.mDailyCappingManager.isCapped(rewardedVideoSmash)) {
                                    rewardedVideoSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                                    this.logProviderEvent(7, rewardedVideoSmash, new Object[][] { { "status", "false" }, { "reason", 6 } });
                                    this.logProviderEvent(150, rewardedVideoSmash, new Object[][] { { "status", "true" } });
                                    this.completeAdapterCap();
                                    return;
                                }
                                if (rewardedVideoSmash.isExhausted()) {
                                    this.loadNextAdapter();
                                    this.completeIterationRound();
                                    return;
                                }
                                return;
                            }
                            this.onRewardedVideoAvailabilityChanged(false, rewardedVideoSmash);
                            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.INTERNAL, rewardedVideoSmash.getInstanceName() + " Failed to show video", new Exception("FailedToShowVideoException"));
                            n4 = n;
                            n5 = n2;
                            break Label_0528;
                        }
                        if (rewardedVideoSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION || rewardedVideoSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY) {
                            n4 = n + 1;
                            n5 = n2;
                            break Label_0528;
                        }
                        n4 = n;
                        n5 = n2;
                        if (rewardedVideoSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                            n5 = n2 + 1;
                            n4 = n;
                        }
                        break Label_0528;
                    }
                    if (this.isBackFillAvailable()) {
                        this.showAdapter(this.getBackfillSmash(), this.mSmashArray.size());
                        return;
                    }
                    if (n + n2 == this.mSmashArray.size()) {
                        this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
                        return;
                    }
                    return;
                }
                ++n3;
                int n = n4;
                int n2 = n5;
                continue;
            }
        }
    }
    
    public void showRewardedVideo(final String s, String s2) {
        while (true) {
            while (true) {
                int n2 = 0;
                Label_0485: {
                    int n3 = 0;
                    Label_0442: {
                        Label_0407: {
                            Label_0350: {
                                Label_0291: {
                                    synchronized (this) {
                                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":showRewardedVideo(instanceId: " + s + ", placementName: " + s2 + ")", 1);
                                        if (!IronSourceUtils.isNetworkConnected((Context)this.mActivity)) {
                                            this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(s, ErrorBuilder.buildNoInternetConnectionShowFailError("Rewarded Video"));
                                        }
                                        else {
                                            this.sendShowCheckAvailabilityEvents(s2);
                                            final int n = 0;
                                            n2 = 0;
                                            n3 = n;
                                            if (n2 >= this.mSmashArray.size()) {
                                                break Label_0442;
                                            }
                                            s2 = (String)this.mSmashArray.get(n2);
                                            if (!((AbstractSmash)s2).getSubProviderId().equals(s)) {
                                                break Label_0485;
                                            }
                                            n3 = 1;
                                            if (((AbstractSmash)s2).getMediationState() != AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                                                break Label_0407;
                                            }
                                            if (!((RewardedVideoSmash)s2).isRewardedVideoAvailable()) {
                                                break Label_0350;
                                            }
                                            CappingManager.incrementShowCounter((Context)this.mActivity, this.mCurrentPlacement);
                                            this.logProviderEvent(2, (AbstractSmash)s2, new Object[][] { { "placement", this.mCurrentPlacement.getPlacementName() } });
                                            this.sendShowChanceEvents((AbstractSmash)s2, n2, this.mCurrentPlacement.getPlacementName());
                                            ((RewardedVideoSmash)s2).showRewardedVideo();
                                            if (!((AbstractSmash)s2).isCappedPerSession()) {
                                                break Label_0291;
                                            }
                                            this.logProviderEvent(7, (AbstractSmash)s2, new Object[][] { { "status", "false" }, { "reason", 2 } });
                                            this.onRewardedVideoAvailabilityChanged(false, (RewardedVideoSmash)s2);
                                        }
                                        return;
                                    }
                                }
                                if (this.mDailyCappingManager.isCapped((AbstractSmash)s2)) {
                                    ((AbstractSmash)s2).setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                                    this.logProviderEvent(150, (AbstractSmash)s2, new Object[][] { { "status", "true" } });
                                    this.onRewardedVideoAvailabilityChanged(false, (RewardedVideoSmash)s2);
                                    return;
                                }
                                return;
                            }
                            this.onRewardedVideoAvailabilityChanged(false, (RewardedVideoSmash)s2);
                            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.INTERNAL, ((AbstractSmash)s2).getInstanceName() + " Failed to show video", new Exception("FailedToShowVideoException"));
                            return;
                        }
                        if (((AbstractSmash)s2).getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION) {
                            this.mListenersWrapper.onRewardedVideoAdShowFailed(new IronSourceError(526, "Instance has reached its cap per session"));
                            return;
                        }
                    }
                    if (n3 == 0) {
                        this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(s, ErrorBuilder.buildNonExistentInstanceError("Rewarded Video"));
                        return;
                    }
                    this.mISDemandOnlyListenersWrapper.onRewardedVideoAdShowFailed(s, ErrorBuilder.buildNoAdsToShowError("Rewarded Video"));
                    return;
                }
                ++n2;
                continue;
            }
        }
    }
}
