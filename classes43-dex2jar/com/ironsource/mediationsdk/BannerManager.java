// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.logger.IronSourceError;
import android.content.Context;
import com.ironsource.mediationsdk.utils.CappingManager;
import java.util.List;
import java.util.TimerTask;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.Iterator;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.model.ProviderSettings;
import java.util.regex.Pattern;
import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import android.util.Log;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import org.json.JSONObject;
import java.util.concurrent.CopyOnWriteArrayList;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;
import com.ironsource.mediationsdk.model.BannerPlacement;
import android.app.Activity;
import com.ironsource.mediationsdk.sdk.BannerManagerListener;

public class BannerManager implements BannerManagerListener
{
    private static final int ADAPTER_MIN_MAJOR_VERSION = 4;
    private static final int ADAPTER_MIN_MINOR_VERSION = 3;
    private BannerSmash mActiveSmash;
    private Activity mActivity;
    private String mAppKey;
    private BannerPlacement mCurrentPlacement;
    AtomicBoolean mDidImplementOnPause;
    AtomicBoolean mDidImplementOnResume;
    private IronSourceBannerLayout mIronsourceBanner;
    private Boolean mIsInForeground;
    private Timer mIterationTimer;
    private IronSourceLoggerManager mLoggerManager;
    private long mReloadInterval;
    private Timer mReloadTimer;
    private final CopyOnWriteArrayList<BannerSmash> mSmashArray;
    private BANNER_STATE mState;
    private String mUserId;
    
    public BannerManager() {
        this.mSmashArray = new CopyOnWriteArrayList<BannerSmash>();
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
        this.mState = BANNER_STATE.NOT_INITIATED;
        this.mIsInForeground = true;
        this.mDidImplementOnPause = new AtomicBoolean();
        this.mDidImplementOnResume = new AtomicBoolean();
    }
    
    private void addEventSizeFields(final JSONObject jsonObject, final ISBannerSize isBannerSize) {
        Label_0210: {
            Label_0201: {
                Label_0192: {
                Label_0183:
                    while (true) {
                        while (true) {
                            int n = 0;
                            Label_0256: {
                                try {
                                    final String description = isBannerSize.getDescription();
                                    n = -1;
                                    switch (description.hashCode()) {
                                        case 1951953708: {
                                            if (description.equals("BANNER")) {
                                                n = 0;
                                            }
                                            break Label_0256;
                                        }
                                        case 72205083: {
                                            if (description.equals("LARGE")) {
                                                n = 1;
                                            }
                                            break Label_0256;
                                        }
                                        case -387072689: {
                                            if (description.equals("RECTANGLE")) {
                                                n = 2;
                                            }
                                            break Label_0256;
                                        }
                                        case 79011241: {
                                            if (description.equals("SMART")) {
                                                n = 3;
                                            }
                                            break Label_0256;
                                        }
                                        case 1999208305: {
                                            if (description.equals("CUSTOM")) {
                                                n = 4;
                                            }
                                            break Label_0256;
                                        }
                                        default: {
                                            break Label_0256;
                                        }
                                    }
                                    jsonObject.put("bannerAdSize", 1);
                                    return;
                                }
                                catch (Exception ex) {
                                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "sendProviderEvent " + Log.getStackTraceString((Throwable)ex), 3);
                                    return;
                                }
                                break;
                            }
                            switch (n) {
                                case 0: {
                                    continue;
                                }
                                case 1: {
                                    break Label_0183;
                                }
                                case 2: {
                                    break Label_0192;
                                }
                                case 3: {
                                    break Label_0201;
                                }
                                case 4: {
                                    break Label_0210;
                                }
                                default: {
                                    return;
                                }
                            }
                            break;
                        }
                    }
                    jsonObject.put("bannerAdSize", 2);
                    return;
                }
                jsonObject.put("bannerAdSize", 3);
                return;
            }
            jsonObject.put("bannerAdSize", 5);
            return;
        }
        jsonObject.put("bannerAdSize", 6);
        jsonObject.put("custom_banner_size", (Object)(isBannerSize.getWidth() + "x" + isBannerSize.getHeight()));
    }
    
    private void bindView(final BannerSmash mActiveSmash, final View view, final FrameLayout$LayoutParams frameLayout$LayoutParams) {
        this.mActiveSmash = mActiveSmash;
        this.mIronsourceBanner.addViewWithFrameLayoutParams(view, frameLayout$LayoutParams);
    }
    
    private void callbackLog(final String s, final BannerSmash bannerSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "BannerManager " + s + " " + bannerSmash.getName(), 0);
    }
    
    private void debugLog(final String s) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "BannerManager " + s, 0);
    }
    
    private void errorLog(final String s) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "BannerManager " + s, 3);
    }
    
    private AbstractAdapter getLoadedAdapterOrFetchByReflection(final String s, final String s2) {
        try {
            final AbstractAdapter existingAdapter = IronSourceObject.getInstance().getExistingAdapter(s);
            if (existingAdapter != null) {
                this.debugLog("using previously loaded " + s);
                return existingAdapter;
            }
            this.debugLog("loading " + s + " with reflection");
            final Class<?> forName = Class.forName("com.ironsource.adapters." + s2.toLowerCase() + "." + s2 + "Adapter");
            return (AbstractAdapter)forName.getMethod("startAdapter", String.class).invoke(forName, s);
        }
        catch (Exception ex) {
            this.errorLog("getLoadedAdapterOrFetchByReflection " + ex.getMessage());
            return null;
        }
    }
    
    private boolean isValidBannerVersion(final String s) {
        try {
            final String[] split = s.split(Pattern.quote("."));
            if (split != null && split.length < 2) {
                return false;
            }
            if (Integer.parseInt(split[0]) >= 4 && Integer.parseInt(split[1]) >= 3) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    private AbstractAdapter loadAdapter(final ProviderSettings providerSettings) {
        final String providerInstanceName = providerSettings.getProviderInstanceName();
        Label_0060: {
            if (!providerSettings.isMultipleInstances()) {
                break Label_0060;
            }
            String s = providerSettings.getProviderTypeForReflection();
            while (true) {
                this.debugLog("loadAdapter(" + providerInstanceName + ")");
                try {
                    final AbstractAdapter loadedAdapterOrFetchByReflection = this.getLoadedAdapterOrFetchByReflection(providerInstanceName, s);
                    if (loadedAdapterOrFetchByReflection == null) {
                        return null;
                    }
                    IronSourceObject.getInstance().addToBannerAdaptersList(loadedAdapterOrFetchByReflection);
                    loadedAdapterOrFetchByReflection.setLogListener(this.mLoggerManager);
                    return loadedAdapterOrFetchByReflection;
                    s = providerSettings.getProviderName();
                }
                catch (Throwable t) {
                    this.errorLog("loadAdapter(" + providerInstanceName + ") " + t.getMessage());
                    return null;
                }
            }
        }
    }
    
    private boolean loadNextSmash() {
        Label_0107: {
            synchronized (this.mSmashArray) {
                Block_5: {
                    for (final BannerSmash bannerSmash : this.mSmashArray) {
                        if (bannerSmash.isReadyToLoad() && this.mActiveSmash != bannerSmash) {
                            break Block_5;
                        }
                    }
                    break Label_0107;
                }
                final BannerSmash bannerSmash;
                if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
                    this.sendProviderEvent(3002, bannerSmash);
                }
                else {
                    this.sendProviderEvent(3012, bannerSmash);
                }
                bannerSmash.loadBanner(this.mIronsourceBanner, this.mActivity, this.mAppKey, this.mUserId);
                return true;
            }
        }
        // monitorexit(list)
        return false;
    }
    
    private void onReloadTimer() {
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            this.debugLog("onReloadTimer wrong state=" + this.mState.name());
            return;
        }
        if (this.mIsInForeground) {
            this.sendMediationEvent(3011);
            this.sendProviderEvent(3012, this.mActiveSmash);
            this.mActiveSmash.reloadBanner();
            return;
        }
        this.sendMediationEvent(3200, new Object[][] { { "errorCode", 614 } });
        this.startReloadTimer();
    }
    
    private void resetIteration() {
        synchronized (this.mSmashArray) {
            final Iterator<BannerSmash> iterator = this.mSmashArray.iterator();
            while (iterator.hasNext()) {
                iterator.next().setReadyToLoad(true);
            }
        }
    }
    // monitorexit(list)
    
    private void sendMediationEvent(final int n) {
        this.sendMediationEvent(n, null);
    }
    
    private void sendMediationEvent(final int n, final Object[][] array) {
        int i = 0;
        final JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        try {
            if (this.mIronsourceBanner != null) {
                this.addEventSizeFields(mediationAdditionalData, this.mIronsourceBanner.getSize());
            }
            if (this.mCurrentPlacement != null) {
                mediationAdditionalData.put("placement", (Object)this.mCurrentPlacement.getPlacementName());
            }
            if (array != null) {
                while (i < array.length) {
                    final Object[] array2 = array[i];
                    mediationAdditionalData.put(array2[0].toString(), array2[1]);
                    ++i;
                }
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "sendMediationEvent " + Log.getStackTraceString((Throwable)ex), 3);
        }
        InterstitialEventsManager.getInstance().log(new EventData(n, mediationAdditionalData));
    }
    
    private void sendProviderEvent(final int n, final BannerSmash bannerSmash) {
        this.sendProviderEvent(n, bannerSmash, null);
    }
    
    private void sendProviderEvent(final int n, BannerSmash providerAdditionalData, final Object[][] array) {
        int i = 0;
        providerAdditionalData = (BannerSmash)IronSourceUtils.getProviderAdditionalData(providerAdditionalData);
        try {
            if (this.mIronsourceBanner != null) {
                this.addEventSizeFields((JSONObject)providerAdditionalData, this.mIronsourceBanner.getSize());
            }
            if (this.mCurrentPlacement != null) {
                ((JSONObject)providerAdditionalData).put("placement", (Object)this.mCurrentPlacement.getPlacementName());
            }
            if (array != null) {
                while (i < array.length) {
                    final Object[] array2 = array[i];
                    ((JSONObject)providerAdditionalData).put(array2[0].toString(), array2[1]);
                    ++i;
                }
            }
        }
        catch (Exception ex) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "sendProviderEvent " + Log.getStackTraceString((Throwable)ex), 3);
        }
        InterstitialEventsManager.getInstance().log(new EventData(n, (JSONObject)providerAdditionalData));
    }
    
    private void setState(final BANNER_STATE mState) {
        this.mState = mState;
        this.debugLog("state=" + mState.name());
    }
    
    private void startIterationTimer() {
        try {
            this.stopIterationTimer();
            (this.mIterationTimer = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    BannerManager.this.sendMediationEvent(3011);
                    BannerManager.this.loadNextSmash();
                }
            }, this.mReloadInterval * 1000L);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void startReloadTimer() {
        try {
            this.stopReloadTimer();
            (this.mReloadTimer = new Timer()).schedule(new TimerTask() {
                @Override
                public void run() {
                    BannerManager.this.onReloadTimer();
                }
            }, this.mReloadInterval * 1000L);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void stopIterationTimer() {
        if (this.mIterationTimer != null) {
            this.mIterationTimer.cancel();
            this.mIterationTimer = null;
        }
    }
    
    private void stopReloadTimer() {
        if (this.mReloadTimer != null) {
            this.mReloadTimer.cancel();
            this.mReloadTimer = null;
        }
    }
    
    public IronSourceBannerLayout createBanner(final Activity activity, final ISBannerSize isBannerSize) {
        synchronized (this) {
            return new IronSourceBannerLayout(activity, isBannerSize);
        }
    }
    
    public void destroyBanner(final IronSourceBannerLayout ironSourceBannerLayout) {
        // monitorenter(this)
        Label_0023: {
            if (ironSourceBannerLayout != null) {
                break Label_0023;
            }
            while (true) {
                try {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "destroyBanner banner cannot be null", 3);
                    return;
                    while (true) {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Banner is already destroyed and can't be used anymore. Please create a new one using IronSource.createBanner API", 3);
                        return;
                        continue;
                    }
                }
                // iftrue(Label_0052:, !ironSourceBannerLayout.isDestroyed())
                finally {
                }
                // monitorexit(this)
                Label_0052: {
                    this.sendMediationEvent(3100);
                }
                this.stopReloadTimer();
                this.stopIterationTimer();
                final IronSourceBannerLayout ironSourceBannerLayout2;
                ironSourceBannerLayout2.destroyBanner();
                this.mIronsourceBanner = null;
                this.mCurrentPlacement = null;
                if (this.mActiveSmash != null) {
                    this.sendProviderEvent(3305, this.mActiveSmash);
                    this.mActiveSmash.destroyBanner();
                    this.mActiveSmash = null;
                }
                this.setState(BANNER_STATE.READY_TO_LOAD);
            }
        }
    }
    
    public void initBannerManager(final List<ProviderSettings> list, final Activity mActivity, final String mAppKey, final String mUserId, final long n, int n2) {
        while (true) {
            while (true) {
                Label_0192: {
                    synchronized (this) {
                        this.debugLog("initBannerManager(appKey: " + mAppKey + ", userId: " + mUserId + ")");
                        this.mAppKey = mAppKey;
                        this.mUserId = mUserId;
                        this.mActivity = mActivity;
                        this.mReloadInterval = n2;
                        n2 = 0;
                        if (n2 < list.size()) {
                            final ProviderSettings providerSettings = list.get(n2);
                            final AbstractAdapter loadAdapter = this.loadAdapter(providerSettings);
                            if (loadAdapter != null && this.isValidBannerVersion(loadAdapter.getVersion())) {
                                this.mSmashArray.add(new BannerSmash(this, providerSettings, loadAdapter, n, n2 + 1));
                                break Label_0192;
                            }
                            this.debugLog(providerSettings.getProviderInstanceName() + " can't load adapter or wrong version");
                            break Label_0192;
                        }
                    }
                    break;
                }
                ++n2;
                continue;
            }
        }
        this.mCurrentPlacement = null;
        this.setState(BANNER_STATE.READY_TO_LOAD);
    }
    // monitorexit(this)
    
    public void loadBanner(IronSourceBannerLayout substring, final BannerPlacement mCurrentPlacement) {
        while (true) {
            Label_0291: {
                synchronized (this) {
                    try {
                        if (this.mState != BANNER_STATE.READY_TO_LOAD || BannerCallbackThrottler.getInstance().hasPendingInvocation()) {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "A banner is already loaded", 3);
                        }
                        else {
                            this.setState(BANNER_STATE.FIRST_LOAD_IN_PROGRESS);
                            this.mIronsourceBanner = substring;
                            this.mCurrentPlacement = mCurrentPlacement;
                            this.sendMediationEvent(3001);
                            if (!CappingManager.isBnPlacementCapped((Context)this.mActivity, mCurrentPlacement.getPlacementName())) {
                                break Label_0291;
                            }
                            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(substring, new IronSourceError(604, "placement " + mCurrentPlacement.getPlacementName() + " is capped"));
                            this.sendMediationEvent(3111, new Object[][] { { "errorCode", 604 } });
                            this.setState(BANNER_STATE.READY_TO_LOAD);
                        }
                        return;
                    }
                    catch (Exception ex) {
                        BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(substring, new IronSourceError(605, "loadBanner() failed " + ex.getMessage()));
                        final String message = ex.getMessage();
                        substring = (IronSourceBannerLayout)message.substring(0, Math.min(message.length(), 100));
                        this.sendMediationEvent(3111, new Object[][] { { "errorCode", 605 }, { "errorMessage", substring } });
                        this.setState(BANNER_STATE.READY_TO_LOAD);
                        return;
                    }
                }
            }
            synchronized (this.mSmashArray) {
                final Iterator<BannerSmash> iterator = this.mSmashArray.iterator();
                while (iterator.hasNext()) {
                    iterator.next().setReadyToLoad(true);
                }
            }
            final BannerSmash bannerSmash = this.mSmashArray.get(0);
            this.sendProviderEvent(3002, bannerSmash);
            bannerSmash.loadBanner(substring, this.mActivity, this.mAppKey, this.mUserId);
        }
        // monitorexit(mCurrentPlacement)
    }
    
    @Override
    public void onBannerAdClicked(final BannerSmash bannerSmash) {
        this.callbackLog("onBannerAdClicked", bannerSmash);
        this.sendMediationEvent(3112);
        this.mIronsourceBanner.sendBannerAdClicked();
        this.sendProviderEvent(3008, bannerSmash);
    }
    
    @Override
    public void onBannerAdLeftApplication(final BannerSmash bannerSmash) {
        this.callbackLog("onBannerAdLeftApplication", bannerSmash);
        this.sendMediationEvent(3115, null);
        this.mIronsourceBanner.sendBannerAdLeftApplication();
        this.sendProviderEvent(3304, bannerSmash, null);
    }
    
    @Override
    public void onBannerAdLoadFailed(final IronSourceError ironSourceError, final BannerSmash bannerSmash) {
        this.callbackLog("onBannerAdLoadFailed " + ironSourceError.getErrorMessage(), bannerSmash);
        if (this.mState != BANNER_STATE.FIRST_LOAD_IN_PROGRESS && this.mState != BANNER_STATE.LOAD_IN_PROGRESS) {
            this.debugLog("onBannerAdLoadFailed " + bannerSmash.getName() + " wrong state=" + this.mState.name());
        }
        else {
            this.sendProviderEvent(3300, bannerSmash, new Object[][] { { "errorCode", ironSourceError.getErrorCode() } });
            if (!this.loadNextSmash()) {
                if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
                    BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mIronsourceBanner, new IronSourceError(606, "No ads to show"));
                    this.sendMediationEvent(3111, new Object[][] { { "errorCode", 606 } });
                    this.setState(BANNER_STATE.READY_TO_LOAD);
                    return;
                }
                this.sendMediationEvent(3201);
                this.resetIteration();
                this.startIterationTimer();
            }
        }
    }
    
    @Override
    public void onBannerAdLoaded(final BannerSmash bannerSmash, final View view, final FrameLayout$LayoutParams frameLayout$LayoutParams) {
        this.callbackLog("onBannerAdLoaded", bannerSmash);
        if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
            this.sendProviderEvent(3005, bannerSmash);
            this.bindView(bannerSmash, view, frameLayout$LayoutParams);
            CappingManager.incrementBnShowCounter((Context)this.mActivity, this.mCurrentPlacement.getPlacementName());
            if (CappingManager.isBnPlacementCapped((Context)this.mActivity, this.mCurrentPlacement.getPlacementName())) {
                this.sendMediationEvent(3400);
            }
            this.mIronsourceBanner.sendBannerAdLoaded(bannerSmash);
            this.sendMediationEvent(3110);
            this.setState(BANNER_STATE.RELOAD_IN_PROGRESS);
            this.startReloadTimer();
        }
        else if (this.mState == BANNER_STATE.LOAD_IN_PROGRESS) {
            this.sendProviderEvent(3015, bannerSmash);
            this.bindView(bannerSmash, view, frameLayout$LayoutParams);
            this.setState(BANNER_STATE.RELOAD_IN_PROGRESS);
            this.startReloadTimer();
        }
    }
    
    @Override
    public void onBannerAdReloadFailed(final IronSourceError ironSourceError, final BannerSmash bannerSmash) {
        this.callbackLog("onBannerAdReloadFailed " + ironSourceError.getErrorMessage(), bannerSmash);
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            this.debugLog("onBannerAdReloadFailed " + bannerSmash.getName() + " wrong state=" + this.mState.name());
        }
        else {
            this.sendProviderEvent(3301, bannerSmash, new Object[][] { { "errorCode", ironSourceError.getErrorCode() } });
            this.setState(BANNER_STATE.LOAD_IN_PROGRESS);
            if (!this.loadNextSmash()) {
                this.sendMediationEvent(3201);
                this.resetIteration();
                this.startIterationTimer();
            }
        }
    }
    
    @Override
    public void onBannerAdReloaded(final BannerSmash bannerSmash) {
        this.callbackLog("onBannerAdReloaded", bannerSmash);
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            this.debugLog("onBannerAdReloaded " + bannerSmash.getName() + " wrong state=" + this.mState.name());
            return;
        }
        this.sendProviderEvent(3015, bannerSmash);
        this.startReloadTimer();
    }
    
    @Override
    public void onBannerAdScreenDismissed(final BannerSmash bannerSmash) {
        this.callbackLog("onBannerAdScreenDismissed", bannerSmash);
        this.sendMediationEvent(3114);
        this.mIronsourceBanner.sendBannerAdScreenDismissed();
        this.sendProviderEvent(3303, bannerSmash);
    }
    
    @Override
    public void onBannerAdScreenPresented(final BannerSmash bannerSmash) {
        this.callbackLog("onBannerAdScreenPresented", bannerSmash);
        this.sendMediationEvent(3113);
        this.mIronsourceBanner.sendBannerAdScreenPresented();
        this.sendProviderEvent(3302, bannerSmash);
    }
    
    public void onPause(final Activity activity) {
        synchronized (this.mSmashArray) {
            this.mIsInForeground = false;
            final Iterator<BannerSmash> iterator = this.mSmashArray.iterator();
            while (iterator.hasNext()) {
                iterator.next().onPause(activity);
            }
        }
    }
    // monitorexit(list)
    
    public void onResume(final Activity activity) {
        synchronized (this.mSmashArray) {
            this.mIsInForeground = true;
            final Iterator<BannerSmash> iterator = this.mSmashArray.iterator();
            while (iterator.hasNext()) {
                iterator.next().onResume(activity);
            }
        }
    }
    // monitorexit(list)
    
    public void setConsent(final boolean consent) {
        synchronized (this) {
            synchronized (this.mSmashArray) {
                final Iterator<BannerSmash> iterator = this.mSmashArray.iterator();
                while (iterator.hasNext()) {
                    iterator.next().setConsent(consent);
                }
            }
        }
    }
    // monitorexit(t)
    // monitorexit(this)
    
    private enum BANNER_STATE
    {
        FIRST_LOAD_IN_PROGRESS, 
        LOAD_IN_PROGRESS, 
        NOT_INITIATED, 
        READY_TO_LOAD, 
        RELOAD_IN_PROGRESS;
    }
}
