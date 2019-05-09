package com.ironsource.mediationsdk;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.BannerPlacement;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.BannerManagerListener;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.vungle.warren.ui.VungleActivity;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class BannerManager implements BannerManagerListener {
    private static final int ADAPTER_MIN_MAJOR_VERSION = 4;
    private static final int ADAPTER_MIN_MINOR_VERSION = 3;
    private BannerSmash mActiveSmash;
    private Activity mActivity;
    private String mAppKey;
    private BannerPlacement mCurrentPlacement;
    AtomicBoolean mDidImplementOnPause = new AtomicBoolean();
    AtomicBoolean mDidImplementOnResume = new AtomicBoolean();
    private IronSourceBannerLayout mIronsourceBanner;
    private Boolean mIsInForeground = Boolean.valueOf(true);
    private Timer mIterationTimer;
    private IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();
    private long mReloadInterval;
    private Timer mReloadTimer;
    private final CopyOnWriteArrayList<BannerSmash> mSmashArray = new CopyOnWriteArrayList();
    private BANNER_STATE mState = BANNER_STATE.NOT_INITIATED;
    private String mUserId;

    /* renamed from: com.ironsource.mediationsdk.BannerManager$1 */
    class C06771 extends TimerTask {
        C06771() {
        }

        public void run() {
            BannerManager.this.onReloadTimer();
        }
    }

    /* renamed from: com.ironsource.mediationsdk.BannerManager$2 */
    class C06782 extends TimerTask {
        C06782() {
        }

        public void run() {
            BannerManager.this.sendMediationEvent(IronSourceConstants.BN_RELOAD);
            BannerManager.this.loadNextSmash();
        }
    }

    private enum BANNER_STATE {
        NOT_INITIATED,
        READY_TO_LOAD,
        FIRST_LOAD_IN_PROGRESS,
        LOAD_IN_PROGRESS,
        RELOAD_IN_PROGRESS
    }

    public synchronized void setConsent(boolean consent) {
        synchronized (this.mSmashArray) {
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                ((BannerSmash) it.next()).setConsent(consent);
            }
        }
    }

    private boolean isValidBannerVersion(String version) {
        try {
            String[] arr = version.split(Pattern.quote("."));
            if ((arr == null || arr.length >= 2) && Integer.parseInt(arr[0]) >= 4 && Integer.parseInt(arr[1]) >= 3) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized void initBannerManager(List<ProviderSettings> adaptersConfigs, Activity activity, String appKey, String userId, long timeout, int reloadInterval) {
        debugLog("initBannerManager(appKey: " + appKey + ", userId: " + userId + ")");
        this.mAppKey = appKey;
        this.mUserId = userId;
        this.mActivity = activity;
        this.mReloadInterval = (long) reloadInterval;
        for (int i = 0; i < adaptersConfigs.size(); i++) {
            ProviderSettings config = (ProviderSettings) adaptersConfigs.get(i);
            AbstractAdapter adapter = loadAdapter(config);
            if (adapter == null || !isValidBannerVersion(adapter.getVersion())) {
                debugLog(config.getProviderInstanceName() + " can't load adapter or wrong version");
            } else {
                this.mSmashArray.add(new BannerSmash(this, config, adapter, timeout, i + 1));
            }
        }
        this.mCurrentPlacement = null;
        setState(BANNER_STATE.READY_TO_LOAD);
    }

    public synchronized IronSourceBannerLayout createBanner(Activity activity, ISBannerSize size) {
        return new IronSourceBannerLayout(activity, size);
    }

    public synchronized void loadBanner(IronSourceBannerLayout banner, BannerPlacement placement) {
        Object[][] objArr;
        try {
            if (this.mState != BANNER_STATE.READY_TO_LOAD || BannerCallbackThrottler.getInstance().hasPendingInvocation()) {
                this.mLoggerManager.log(IronSourceTag.API, "A banner is already loaded", 3);
            } else {
                setState(BANNER_STATE.FIRST_LOAD_IN_PROGRESS);
                this.mIronsourceBanner = banner;
                this.mCurrentPlacement = placement;
                sendMediationEvent(3001);
                if (CappingManager.isBnPlacementCapped(this.mActivity, placement.getPlacementName())) {
                    BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(banner, new IronSourceError(IronSourceError.ERROR_BN_LOAD_PLACEMENT_CAPPED, "placement " + placement.getPlacementName() + " is capped"));
                    objArr = new Object[1][];
                    objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(IronSourceError.ERROR_BN_LOAD_PLACEMENT_CAPPED)};
                    sendMediationEvent(IronSourceConstants.BN_CALLBACK_LOAD_ERROR, objArr);
                    setState(BANNER_STATE.READY_TO_LOAD);
                } else {
                    synchronized (this.mSmashArray) {
                        Iterator it = this.mSmashArray.iterator();
                        while (it.hasNext()) {
                            ((BannerSmash) it.next()).setReadyToLoad(true);
                        }
                        BannerSmash smash = (BannerSmash) this.mSmashArray.get(0);
                        sendProviderEvent(3002, smash);
                        smash.loadBanner(banner, this.mActivity, this.mAppKey, this.mUserId);
                    }
                }
            }
        } catch (Exception e) {
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(banner, new IronSourceError(IronSourceError.ERROR_BN_LOAD_EXCEPTION, "loadBanner() failed " + e.getMessage()));
            String message = e.getMessage();
            message = message.substring(0, Math.min(message.length(), 100));
            objArr = new Object[2][];
            objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(IronSourceError.ERROR_BN_LOAD_EXCEPTION)};
            objArr[1] = new Object[]{IronSourceConstants.ERROR_CODE_MESSAGE_KEY, message};
            sendMediationEvent(IronSourceConstants.BN_CALLBACK_LOAD_ERROR, objArr);
            setState(BANNER_STATE.READY_TO_LOAD);
        }
    }

    public synchronized void destroyBanner(IronSourceBannerLayout banner) {
        if (banner == null) {
            this.mLoggerManager.log(IronSourceTag.API, "destroyBanner banner cannot be null", 3);
        } else if (banner.isDestroyed()) {
            this.mLoggerManager.log(IronSourceTag.API, "Banner is already destroyed and can't be used anymore. Please create a new one using IronSource.createBanner API", 3);
        } else {
            sendMediationEvent(IronSourceConstants.BN_DESTROY);
            stopReloadTimer();
            stopIterationTimer();
            banner.destroyBanner();
            this.mIronsourceBanner = null;
            this.mCurrentPlacement = null;
            if (this.mActiveSmash != null) {
                sendProviderEvent(IronSourceConstants.BN_INSTANCE_DESTROY, this.mActiveSmash);
                this.mActiveSmash.destroyBanner();
                this.mActiveSmash = null;
            }
            setState(BANNER_STATE.READY_TO_LOAD);
        }
    }

    private void errorLog(String text) {
        this.mLoggerManager.log(IronSourceTag.INTERNAL, "BannerManager " + text, 3);
    }

    private void debugLog(String text) {
        this.mLoggerManager.log(IronSourceTag.INTERNAL, "BannerManager " + text, 0);
    }

    private void setState(BANNER_STATE state) {
        this.mState = state;
        debugLog("state=" + state.name());
    }

    private void callbackLog(String text, BannerSmash smash) {
        this.mLoggerManager.log(IronSourceTag.ADAPTER_CALLBACK, "BannerManager " + text + " " + smash.getName(), 0);
    }

    private void bindView(BannerSmash smash, View adView, LayoutParams frameLayoutParams) {
        this.mActiveSmash = smash;
        this.mIronsourceBanner.addViewWithFrameLayoutParams(adView, frameLayoutParams);
    }

    public void onBannerAdLoaded(BannerSmash smash, View adView, LayoutParams frameLayoutParams) {
        callbackLog("onBannerAdLoaded", smash);
        if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
            sendProviderEvent(3005, smash);
            bindView(smash, adView, frameLayoutParams);
            CappingManager.incrementBnShowCounter(this.mActivity, this.mCurrentPlacement.getPlacementName());
            if (CappingManager.isBnPlacementCapped(this.mActivity, this.mCurrentPlacement.getPlacementName())) {
                sendMediationEvent(IronSourceConstants.BN_PLACEMENT_CAPPED);
            }
            this.mIronsourceBanner.sendBannerAdLoaded(smash);
            sendMediationEvent(IronSourceConstants.BN_CALLBACK_LOAD_SUCCESS);
            setState(BANNER_STATE.RELOAD_IN_PROGRESS);
            startReloadTimer();
        } else if (this.mState == BANNER_STATE.LOAD_IN_PROGRESS) {
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD_SUCCESS, smash);
            bindView(smash, adView, frameLayoutParams);
            setState(BANNER_STATE.RELOAD_IN_PROGRESS);
            startReloadTimer();
        }
    }

    public void onBannerAdLoadFailed(IronSourceError error, BannerSmash smash) {
        callbackLog("onBannerAdLoadFailed " + error.getErrorMessage(), smash);
        if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS || this.mState == BANNER_STATE.LOAD_IN_PROGRESS) {
            Object[][] objArr = new Object[1][];
            objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_LOAD_ERROR, smash, objArr);
            if (!loadNextSmash()) {
                if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
                    BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mIronsourceBanner, new IronSourceError(IronSourceError.ERROR_BN_LOAD_NO_FILL, "No ads to show"));
                    objArr = new Object[1][];
                    objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(IronSourceError.ERROR_BN_LOAD_NO_FILL)};
                    sendMediationEvent(IronSourceConstants.BN_CALLBACK_LOAD_ERROR, objArr);
                    setState(BANNER_STATE.READY_TO_LOAD);
                    return;
                }
                sendMediationEvent(IronSourceConstants.BN_RELOAD_FAILED);
                resetIteration();
                startIterationTimer();
                return;
            }
            return;
        }
        debugLog("onBannerAdLoadFailed " + smash.getName() + " wrong state=" + this.mState.name());
    }

    public void onBannerAdReloaded(BannerSmash smash) {
        callbackLog("onBannerAdReloaded", smash);
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            debugLog("onBannerAdReloaded " + smash.getName() + " wrong state=" + this.mState.name());
            return;
        }
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD_SUCCESS, smash);
        startReloadTimer();
    }

    public void onBannerAdReloadFailed(IronSourceError error, BannerSmash smash) {
        callbackLog("onBannerAdReloadFailed " + error.getErrorMessage(), smash);
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            debugLog("onBannerAdReloadFailed " + smash.getName() + " wrong state=" + this.mState.name());
            return;
        }
        Object[][] objArr = new Object[1][];
        objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(error.getErrorCode())};
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD_ERROR, smash, objArr);
        setState(BANNER_STATE.LOAD_IN_PROGRESS);
        if (!loadNextSmash()) {
            sendMediationEvent(IronSourceConstants.BN_RELOAD_FAILED);
            resetIteration();
            startIterationTimer();
        }
    }

    public void onBannerAdClicked(BannerSmash smash) {
        callbackLog("onBannerAdClicked", smash);
        sendMediationEvent(IronSourceConstants.BN_CALLBACK_CLICK);
        this.mIronsourceBanner.sendBannerAdClicked();
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_CLICK, smash);
    }

    public void onBannerAdScreenDismissed(BannerSmash smash) {
        callbackLog("onBannerAdScreenDismissed", smash);
        sendMediationEvent(IronSourceConstants.BN_CALLBACK_DISMISS_SCREEN);
        this.mIronsourceBanner.sendBannerAdScreenDismissed();
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_DISMISS_SCREEN, smash);
    }

    public void onBannerAdScreenPresented(BannerSmash smash) {
        callbackLog("onBannerAdScreenPresented", smash);
        sendMediationEvent(IronSourceConstants.BN_CALLBACK_PRESENT_SCREEN);
        this.mIronsourceBanner.sendBannerAdScreenPresented();
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_PRESENT_SCREEN, smash);
    }

    public void onBannerAdLeftApplication(BannerSmash smash) {
        callbackLog("onBannerAdLeftApplication", smash);
        sendMediationEvent(IronSourceConstants.BN_CALLBACK_LEAVE_APP, (Object[][]) null);
        this.mIronsourceBanner.sendBannerAdLeftApplication();
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_LEAVE_APP, smash, (Object[][]) null);
    }

    private void sendMediationEvent(int eventId) {
        sendMediationEvent(eventId, (Object[][]) null);
    }

    private void sendMediationEvent(int eventId, Object[][] keyVals) {
        int i = 0;
        JSONObject data = IronSourceUtils.getMediationAdditionalData(false);
        try {
            if (this.mIronsourceBanner != null) {
                addEventSizeFields(data, this.mIronsourceBanner.getSize());
            }
            if (this.mCurrentPlacement != null) {
                data.put(VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacement.getPlacementName());
            }
            if (keyVals != null) {
                int length = keyVals.length;
                while (i < length) {
                    Object[] pair = keyVals[i];
                    data.put(pair[0].toString(), pair[1]);
                    i++;
                }
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "sendMediationEvent " + Log.getStackTraceString(e), 3);
        }
        InterstitialEventsManager.getInstance().log(new EventData(eventId, data));
    }

    private void sendProviderEvent(int eventId, BannerSmash smash) {
        sendProviderEvent(eventId, smash, (Object[][]) null);
    }

    private void addEventSizeFields(JSONObject data, ISBannerSize size) {
        try {
            String description = size.getDescription();
            int i = -1;
            switch (description.hashCode()) {
                case -387072689:
                    if (description.equals("RECTANGLE")) {
                        i = 2;
                        break;
                    }
                    break;
                case 72205083:
                    if (description.equals("LARGE")) {
                        i = 1;
                        break;
                    }
                    break;
                case 79011241:
                    if (description.equals("SMART")) {
                        i = 3;
                        break;
                    }
                    break;
                case 1951953708:
                    if (description.equals("BANNER")) {
                        i = 0;
                        break;
                    }
                    break;
                case 1999208305:
                    if (description.equals("CUSTOM")) {
                        i = 4;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    data.put("bannerAdSize", 1);
                    return;
                case 1:
                    data.put("bannerAdSize", 2);
                    return;
                case 2:
                    data.put("bannerAdSize", 3);
                    return;
                case 3:
                    data.put("bannerAdSize", 5);
                    return;
                case 4:
                    data.put("bannerAdSize", 6);
                    data.put("custom_banner_size", size.getWidth() + "x" + size.getHeight());
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "sendProviderEvent " + Log.getStackTraceString(e), 3);
        }
        this.mLoggerManager.log(IronSourceTag.INTERNAL, "sendProviderEvent " + Log.getStackTraceString(e), 3);
    }

    private void sendProviderEvent(int eventId, BannerSmash smash, Object[][] keyVals) {
        JSONObject data = IronSourceUtils.getProviderAdditionalData(smash);
        try {
            if (this.mIronsourceBanner != null) {
                addEventSizeFields(data, this.mIronsourceBanner.getSize());
            }
            if (this.mCurrentPlacement != null) {
                data.put(VungleActivity.PLACEMENT_EXTRA, this.mCurrentPlacement.getPlacementName());
            }
            if (keyVals != null) {
                for (Object[] pair : keyVals) {
                    data.put(pair[0].toString(), pair[1]);
                }
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceTag.INTERNAL, "sendProviderEvent " + Log.getStackTraceString(e), 3);
        }
        InterstitialEventsManager.getInstance().log(new EventData(eventId, data));
    }

    private void resetIteration() {
        synchronized (this.mSmashArray) {
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                ((BannerSmash) it.next()).setReadyToLoad(true);
            }
        }
    }

    private boolean loadNextSmash() {
        boolean z;
        synchronized (this.mSmashArray) {
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                BannerSmash smash = (BannerSmash) it.next();
                if (smash.isReadyToLoad() && this.mActiveSmash != smash) {
                    if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
                        sendProviderEvent(3002, smash);
                    } else {
                        sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD, smash);
                    }
                    smash.loadBanner(this.mIronsourceBanner, this.mActivity, this.mAppKey, this.mUserId);
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public void onPause(Activity activity) {
        synchronized (this.mSmashArray) {
            this.mIsInForeground = Boolean.valueOf(false);
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                ((BannerSmash) it.next()).onPause(activity);
            }
        }
    }

    public void onResume(Activity activity) {
        synchronized (this.mSmashArray) {
            this.mIsInForeground = Boolean.valueOf(true);
            Iterator it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                ((BannerSmash) it.next()).onResume(activity);
            }
        }
    }

    private void startReloadTimer() {
        try {
            stopReloadTimer();
            this.mReloadTimer = new Timer();
            this.mReloadTimer.schedule(new C06771(), this.mReloadInterval * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopReloadTimer() {
        if (this.mReloadTimer != null) {
            this.mReloadTimer.cancel();
            this.mReloadTimer = null;
        }
    }

    private void onReloadTimer() {
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            debugLog("onReloadTimer wrong state=" + this.mState.name());
        } else if (this.mIsInForeground.booleanValue()) {
            sendMediationEvent(IronSourceConstants.BN_RELOAD);
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD, this.mActiveSmash);
            this.mActiveSmash.reloadBanner();
        } else {
            Object[][] objArr = new Object[1][];
            objArr[0] = new Object[]{IronSourceConstants.ERROR_CODE_KEY, Integer.valueOf(IronSourceError.ERROR_BN_RELOAD_SKIP_BACKGROUND)};
            sendMediationEvent(IronSourceConstants.BN_SKIP_RELOAD, objArr);
            startReloadTimer();
        }
    }

    private void startIterationTimer() {
        try {
            stopIterationTimer();
            this.mIterationTimer = new Timer();
            this.mIterationTimer.schedule(new C06782(), this.mReloadInterval * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopIterationTimer() {
        if (this.mIterationTimer != null) {
            this.mIterationTimer.cancel();
            this.mIterationTimer = null;
        }
    }

    private AbstractAdapter getLoadedAdapterOrFetchByReflection(String name, String reflectionName) {
        try {
            AbstractAdapter providerAdapter = IronSourceObject.getInstance().getExistingAdapter(name);
            if (providerAdapter != null) {
                debugLog("using previously loaded " + name);
                return providerAdapter;
            }
            debugLog("loading " + name + " with reflection");
            Class<?> mAdapterClass = Class.forName("com.ironsource.adapters." + reflectionName.toLowerCase() + "." + reflectionName + "Adapter");
            return (AbstractAdapter) mAdapterClass.getMethod(IronSourceConstants.START_ADAPTER, new Class[]{String.class}).invoke(mAdapterClass, new Object[]{name});
        } catch (Exception ex) {
            errorLog("getLoadedAdapterOrFetchByReflection " + ex.getMessage());
            return null;
        }
    }

    private AbstractAdapter loadAdapter(ProviderSettings config) {
        String name = config.getProviderInstanceName();
        String reflectionName = config.isMultipleInstances() ? config.getProviderTypeForReflection() : config.getProviderName();
        debugLog("loadAdapter(" + name + ")");
        try {
            AbstractAdapter providerAdapter = getLoadedAdapterOrFetchByReflection(name, reflectionName);
            if (providerAdapter == null) {
                return null;
            }
            IronSourceObject.getInstance().addToBannerAdaptersList(providerAdapter);
            providerAdapter.setLogListener(this.mLoggerManager);
            return providerAdapter;
        } catch (Throwable e) {
            errorLog("loadAdapter(" + name + ") " + e.getMessage());
            return null;
        }
    }
}
