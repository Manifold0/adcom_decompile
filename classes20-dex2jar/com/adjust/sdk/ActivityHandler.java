// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.LinkedHashMap;
import java.util.Map;
import java.io.InputStream;
import java.util.Properties;
import java.util.List;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.ActivityManager;
import android.os.Process;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ActivityHandler implements IActivityHandler
{
    private static final String ACTIVITY_STATE_NAME = "Activity state";
    private static final String ATTRIBUTION_NAME = "Attribution";
    private static long BACKGROUND_TIMER_INTERVAL = 0L;
    private static final String BACKGROUND_TIMER_NAME = "Background timer";
    private static final String DELAY_START_TIMER_NAME = "Delay Start timer";
    private static long FOREGROUND_TIMER_INTERVAL = 0L;
    private static final String FOREGROUND_TIMER_NAME = "Foreground timer";
    private static long FOREGROUND_TIMER_START = 0L;
    private static final String SESSION_CALLBACK_PARAMETERS_NAME = "Session Callback parameters";
    private static long SESSION_INTERVAL = 0L;
    private static final String SESSION_PARAMETERS_NAME = "Session parameters";
    private static final String SESSION_PARTNER_PARAMETERS_NAME = "Session Partner parameters";
    private static long SUBSESSION_INTERVAL = 0L;
    private static final String TIME_TRAVEL = "Time travel!";
    private ActivityState activityState;
    private AdjustConfig adjustConfig;
    private AdjustAttribution attribution;
    private IAttributionHandler attributionHandler;
    private TimerOnce backgroundTimer;
    private TimerOnce delayStartTimer;
    private DeviceInfo deviceInfo;
    private TimerCycle foregroundTimer;
    private InstallReferrer installReferrer;
    private InternalState internalState;
    private ILogger logger;
    private IPackageHandler packageHandler;
    private CustomScheduledExecutor scheduledExecutor;
    private ISdkClickHandler sdkClickHandler;
    private SessionParameters sessionParameters;
    
    private ActivityHandler(final AdjustConfig adjustConfig) {
        this.init(adjustConfig);
        (this.logger = AdjustFactory.getLogger()).lockLogLevel();
        this.scheduledExecutor = new CustomScheduledExecutor("ActivityHandler", false);
        this.internalState = new InternalState();
        this.internalState.enabled = (adjustConfig.startEnabled == null || adjustConfig.startEnabled);
        this.internalState.offline = adjustConfig.startOffline;
        this.internalState.background = true;
        this.internalState.delayStart = false;
        this.internalState.updatePackages = false;
        this.internalState.sessionResponseProcessed = false;
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.initI();
            }
        });
    }
    
    private void backgroundTimerFiredI() {
        if (this.toSendI()) {
            this.packageHandler.sendFirstPackage();
        }
    }
    
    private boolean checkActivityStateI(final ActivityState activityState) {
        if (activityState == null) {
            this.logger.error("Missing activity state", new Object[0]);
            return false;
        }
        return true;
    }
    
    private void checkAttributionStateI() {
        if (this.checkActivityStateI(this.activityState) && (!this.internalState.isFirstLaunch() || !this.internalState.hasSessionResponseNotBeenProcessed()) && (this.attribution == null || this.activityState.askingAttribution)) {
            this.attributionHandler.getAttribution();
        }
    }
    
    private boolean checkEventI(final AdjustEvent adjustEvent) {
        if (adjustEvent == null) {
            this.logger.error("Event missing", new Object[0]);
            return false;
        }
        if (!adjustEvent.isValid()) {
            this.logger.error("Event not initialized correctly", new Object[0]);
            return false;
        }
        return true;
    }
    
    private void checkForInstallReferrerInfo(final SdkClickResponseData sdkClickResponseData) {
        if (!sdkClickResponseData.isInstallReferrer) {
            return;
        }
        this.activityState.clickTime = sdkClickResponseData.clickTime;
        this.activityState.installBegin = sdkClickResponseData.installBegin;
        this.activityState.installReferrer = sdkClickResponseData.installReferrer;
        this.writeActivityStateI();
    }
    
    private boolean checkOrderIdI(final String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        if (this.activityState.findOrderId(s)) {
            this.logger.info("Skipping duplicated order ID '%s'", s);
            return false;
        }
        this.activityState.addOrderId(s);
        this.logger.verbose("Added order ID '%s'", s);
        return true;
    }
    
    private Intent createDeeplinkIntentI(final Uri uri) {
        Intent intent;
        if (this.adjustConfig.deepLinkComponent == null) {
            intent = new Intent("android.intent.action.VIEW", uri);
        }
        else {
            intent = new Intent("android.intent.action.VIEW", uri, this.adjustConfig.context, this.adjustConfig.deepLinkComponent);
        }
        intent.setFlags(268435456);
        intent.setPackage(this.adjustConfig.context.getPackageName());
        return intent;
    }
    
    private void delayStartI() {
        if (!this.internalState.isNotInDelayedStart() && !this.isToUpdatePackagesI()) {
            double doubleValue;
            if (this.adjustConfig.delayStart != null) {
                doubleValue = this.adjustConfig.delayStart;
            }
            else {
                doubleValue = 0.0;
            }
            final long maxDelayStart = AdjustFactory.getMaxDelayStart();
            long n = (long)(1000.0 * doubleValue);
            double n2 = doubleValue;
            if (n > maxDelayStart) {
                n2 = (double)(maxDelayStart / 1000L);
                this.logger.warn("Delay start of %s seconds bigger than max allowed value of %s seconds", Util.SecondsDisplayFormat.format(doubleValue), Util.SecondsDisplayFormat.format(n2));
                n = maxDelayStart;
            }
            this.logger.info("Waiting %s seconds before starting first session", Util.SecondsDisplayFormat.format(n2));
            this.delayStartTimer.startIn(n);
            this.internalState.updatePackages = true;
            if (this.activityState != null) {
                this.activityState.updatePackages = true;
                this.writeActivityStateI();
            }
        }
    }
    
    public static boolean deleteActivityState(final Context context) {
        return context.deleteFile("AdjustIoActivityState");
    }
    
    public static boolean deleteAttribution(final Context context) {
        return context.deleteFile("AdjustAttribution");
    }
    
    public static boolean deleteSessionCallbackParameters(final Context context) {
        return context.deleteFile("AdjustSessionCallbackParameters");
    }
    
    public static boolean deleteSessionPartnerParameters(final Context context) {
        return context.deleteFile("AdjustSessionPartnerParameters");
    }
    
    private void endI() {
        if (!this.toSendI()) {
            this.pauseSendingI();
        }
        if (this.updateActivityStateI(System.currentTimeMillis())) {
            this.writeActivityStateI();
        }
    }
    
    private void foregroundTimerFiredI() {
        if (!this.isEnabledI()) {
            this.stopForegroundTimerI();
        }
        else {
            if (this.toSendI()) {
                this.packageHandler.sendFirstPackage();
            }
            if (this.updateActivityStateI(System.currentTimeMillis())) {
                this.writeActivityStateI();
            }
        }
    }
    
    public static ActivityHandler getInstance(final AdjustConfig adjustConfig) {
        if (adjustConfig == null) {
            AdjustFactory.getLogger().error("AdjustConfig missing", new Object[0]);
        }
        else {
            if (!adjustConfig.isValid()) {
                AdjustFactory.getLogger().error("AdjustConfig not initialized correctly", new Object[0]);
                return null;
            }
            if (adjustConfig.processName != null) {
                final int myPid = Process.myPid();
                final ActivityManager activityManager = (ActivityManager)adjustConfig.context.getSystemService("activity");
                if (activityManager == null) {
                    return null;
                }
                for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                    if (activityManager$RunningAppProcessInfo.pid == myPid) {
                        if (!activityManager$RunningAppProcessInfo.processName.equalsIgnoreCase(adjustConfig.processName)) {
                            AdjustFactory.getLogger().info("Skipping initialization in background process (%s)", activityManager$RunningAppProcessInfo.processName);
                            return null;
                        }
                        break;
                    }
                }
            }
            return new ActivityHandler(adjustConfig);
        }
        return null;
    }
    
    private boolean hasChangedStateI(final boolean b, final boolean b2, final String s, final String s2) {
        if (b != b2) {
            return true;
        }
        if (b) {
            this.logger.debug(s, new Object[0]);
            return false;
        }
        this.logger.debug(s2, new Object[0]);
        return false;
    }
    
    private void initI() {
        ActivityHandler.SESSION_INTERVAL = AdjustFactory.getSessionInterval();
        ActivityHandler.SUBSESSION_INTERVAL = AdjustFactory.getSubsessionInterval();
        ActivityHandler.FOREGROUND_TIMER_INTERVAL = AdjustFactory.getTimerInterval();
        ActivityHandler.FOREGROUND_TIMER_START = AdjustFactory.getTimerStart();
        ActivityHandler.BACKGROUND_TIMER_INTERVAL = AdjustFactory.getTimerInterval();
        this.readAttributionI(this.adjustConfig.context);
        this.readActivityStateI(this.adjustConfig.context);
        this.sessionParameters = new SessionParameters();
        this.readSessionCallbackParametersI(this.adjustConfig.context);
        this.readSessionPartnerParametersI(this.adjustConfig.context);
        if (this.adjustConfig.startEnabled != null) {
            if (this.adjustConfig.preLaunchActionsArray == null) {
                this.adjustConfig.preLaunchActionsArray = new ArrayList<IRunActivityHandler>();
            }
            this.adjustConfig.preLaunchActionsArray.add(new IRunActivityHandler() {
                @Override
                public void run(final ActivityHandler activityHandler) {
                    activityHandler.setEnabledI(ActivityHandler.this.adjustConfig.startEnabled);
                }
            });
        }
        if (this.activityState != null) {
            this.internalState.enabled = this.activityState.enabled;
            this.internalState.updatePackages = this.activityState.updatePackages;
            this.internalState.firstLaunch = false;
        }
        else {
            this.internalState.firstLaunch = true;
        }
        this.readConfigFile(this.adjustConfig.context);
        this.deviceInfo = new DeviceInfo(this.adjustConfig.context, this.adjustConfig.sdkPrefix);
        if (this.adjustConfig.eventBufferingEnabled) {
            this.logger.info("Event buffering is enabled", new Object[0]);
        }
        if (this.deviceInfo.playAdId == null) {
            this.logger.warn("Unable to get Google Play Services Advertising ID at start time", new Object[0]);
            if (this.deviceInfo.macSha1 == null && this.deviceInfo.macShortMd5 == null && this.deviceInfo.androidId == null) {
                this.logger.error("Unable to get any device id's. Please check if Proguard is correctly set with Adjust SDK", new Object[0]);
            }
        }
        else {
            this.logger.info("Google Play Services Advertising ID read correctly at start time", new Object[0]);
        }
        if (this.adjustConfig.defaultTracker != null) {
            this.logger.info("Default tracker: '%s'", this.adjustConfig.defaultTracker);
        }
        if (this.adjustConfig.pushToken != null) {
            this.logger.info("Push token: '%s'", this.adjustConfig.pushToken);
            if (this.activityState != null) {
                this.setPushToken(this.adjustConfig.pushToken, false);
            }
            else {
                new SharedPreferencesManager(this.getContext()).savePushToken(this.adjustConfig.pushToken);
            }
        }
        else if (this.activityState != null) {
            this.setPushToken(new SharedPreferencesManager(this.getContext()).getPushToken(), true);
        }
        this.foregroundTimer = new TimerCycle(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.foregroundTimerFired();
            }
        }, ActivityHandler.FOREGROUND_TIMER_START, ActivityHandler.FOREGROUND_TIMER_INTERVAL, "Foreground timer");
        if (this.adjustConfig.sendInBackground) {
            this.logger.info("Send in background configured", new Object[0]);
            this.backgroundTimer = new TimerOnce(new Runnable() {
                @Override
                public void run() {
                    ActivityHandler.this.backgroundTimerFired();
                }
            }, "Background timer");
        }
        if (this.activityState == null && this.adjustConfig.delayStart != null && this.adjustConfig.delayStart > 0.0) {
            this.logger.info("Delay start configured", new Object[0]);
            this.internalState.delayStart = true;
            this.delayStartTimer = new TimerOnce(new Runnable() {
                @Override
                public void run() {
                    ActivityHandler.this.sendFirstPackages();
                }
            }, "Delay Start timer");
        }
        UtilNetworking.setUserAgent(this.adjustConfig.userAgent);
        this.packageHandler = AdjustFactory.getPackageHandler(this, this.adjustConfig.context, this.toSendI(false));
        this.attributionHandler = AdjustFactory.getAttributionHandler(this, this.getAttributionPackageI(), this.toSendI(false));
        this.sdkClickHandler = AdjustFactory.getSdkClickHandler(this, this.toSendI(true));
        if (this.isToUpdatePackagesI()) {
            this.updatePackagesI();
        }
        this.preLaunchActionsI(this.adjustConfig.preLaunchActionsArray);
        this.installReferrer = new InstallReferrer(this.adjustConfig.context, this);
        this.sendReftagReferrerI();
    }
    
    private boolean isEnabledI() {
        if (this.activityState != null) {
            return this.activityState.enabled;
        }
        return this.internalState.isEnabled();
    }
    
    private boolean isToUpdatePackagesI() {
        if (this.activityState != null) {
            return this.activityState.updatePackages;
        }
        return this.internalState.itHasToUpdatePackages();
    }
    
    private void launchAttributionListenerI(final Handler handler) {
        if (this.adjustConfig.onAttributionChangedListener == null) {
            return;
        }
        handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onAttributionChangedListener != null) {
                    ActivityHandler.this.adjustConfig.onAttributionChangedListener.onAttributionChanged(ActivityHandler.this.attribution);
                }
            }
        });
    }
    
    private void launchAttributionResponseTasksI(final AttributionResponseData attributionResponseData) {
        this.updateAdidI(attributionResponseData.adid);
        final Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (this.updateAttributionI(attributionResponseData.attribution)) {
            this.launchAttributionListenerI(handler);
        }
        this.prepareDeeplinkI(attributionResponseData.deeplink, handler);
    }
    
    private void launchDeeplinkMain(final Intent intent, final Uri uri) {
        int n;
        if (this.adjustConfig.context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            this.logger.error("Unable to open deferred deep link (%s)", uri);
            return;
        }
        this.logger.info("Open deferred deep link (%s)", uri);
        this.adjustConfig.context.startActivity(intent);
    }
    
    private void launchEventResponseTasksI(final EventResponseData eventResponseData) {
        this.updateAdidI(eventResponseData.adid);
        final Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (eventResponseData.success && this.adjustConfig.onEventTrackingSucceededListener != null) {
            this.logger.debug("Launching success event tracking listener", new Object[0]);
            handler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onEventTrackingSucceededListener != null) {
                        ActivityHandler.this.adjustConfig.onEventTrackingSucceededListener.onFinishedEventTrackingSucceeded(eventResponseData.getSuccessResponseData());
                    }
                }
            });
        }
        else if (!eventResponseData.success && this.adjustConfig.onEventTrackingFailedListener != null) {
            this.logger.debug("Launching failed event tracking listener", new Object[0]);
            handler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onEventTrackingFailedListener != null) {
                        ActivityHandler.this.adjustConfig.onEventTrackingFailedListener.onFinishedEventTrackingFailed(eventResponseData.getFailureResponseData());
                    }
                }
            });
        }
    }
    
    private void launchSdkClickResponseTasksI(final SdkClickResponseData sdkClickResponseData) {
        this.updateAdidI(sdkClickResponseData.adid);
        final Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (this.updateAttributionI(sdkClickResponseData.attribution)) {
            this.launchAttributionListenerI(handler);
        }
    }
    
    private void launchSessionResponseListenerI(final SessionResponseData sessionResponseData, final Handler handler) {
        if (sessionResponseData.success && this.adjustConfig.onSessionTrackingSucceededListener != null) {
            this.logger.debug("Launching success session tracking listener", new Object[0]);
            handler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onSessionTrackingSucceededListener != null) {
                        ActivityHandler.this.adjustConfig.onSessionTrackingSucceededListener.onFinishedSessionTrackingSucceeded(sessionResponseData.getSuccessResponseData());
                    }
                }
            });
        }
        else if (!sessionResponseData.success && this.adjustConfig.onSessionTrackingFailedListener != null) {
            this.logger.debug("Launching failed session tracking listener", new Object[0]);
            handler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onSessionTrackingFailedListener != null) {
                        ActivityHandler.this.adjustConfig.onSessionTrackingFailedListener.onFinishedSessionTrackingFailed(sessionResponseData.getFailureResponseData());
                    }
                }
            });
        }
    }
    
    private void launchSessionResponseTasksI(final SessionResponseData sessionResponseData) {
        this.updateAdidI(sessionResponseData.adid);
        final Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (this.updateAttributionI(sessionResponseData.attribution)) {
            this.launchAttributionListenerI(handler);
        }
        if (sessionResponseData.success) {
            new SharedPreferencesManager(this.getContext()).setInstallTracked();
        }
        this.launchSessionResponseListenerI(sessionResponseData, handler);
        this.internalState.sessionResponseProcessed = true;
    }
    
    private void pauseSendingI() {
        this.attributionHandler.pauseSending();
        this.packageHandler.pauseSending();
        if (!this.toSendI(true)) {
            this.sdkClickHandler.pauseSending();
            return;
        }
        this.sdkClickHandler.resumeSending();
    }
    
    private boolean pausedI() {
        return this.pausedI(false);
    }
    
    private boolean pausedI(final boolean b) {
        final boolean b2 = false;
        if (!b) {
            if (!this.internalState.isOffline() && this.isEnabledI()) {
                final boolean b3 = b2;
                if (!this.internalState.isInDelayedStart()) {
                    return b3;
                }
            }
            return true;
        }
        if (!this.internalState.isOffline()) {
            final boolean b3 = b2;
            if (this.isEnabledI()) {
                return b3;
            }
        }
        return true;
    }
    
    private void preLaunchActionsI(final List<IRunActivityHandler> list) {
        if (list != null) {
            final Iterator<IRunActivityHandler> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().run(this);
            }
        }
    }
    
    private void prepareDeeplinkI(final Uri uri, final Handler handler) {
        if (uri == null) {
            return;
        }
        this.logger.info("Deferred deeplink received (%s)", uri);
        handler.post((Runnable)new Runnable() {
            final /* synthetic */ Intent val$deeplinkIntent = ActivityHandler.this.createDeeplinkIntentI(uri);
            
            @Override
            public void run() {
                if (ActivityHandler.this.adjustConfig != null) {
                    boolean launchReceivedDeeplink = true;
                    if (ActivityHandler.this.adjustConfig.onDeeplinkResponseListener != null) {
                        launchReceivedDeeplink = ActivityHandler.this.adjustConfig.onDeeplinkResponseListener.launchReceivedDeeplink(uri);
                    }
                    if (launchReceivedDeeplink) {
                        ActivityHandler.this.launchDeeplinkMain(this.val$deeplinkIntent, uri);
                    }
                }
            }
        });
    }
    
    private void processSessionI() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.activityState == null) {
            this.activityState = new ActivityState();
            final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(this.getContext());
            this.activityState.pushToken = sharedPreferencesManager.getPushToken();
            if (this.internalState.isEnabled()) {
                this.activityState.sessionCount = 1;
                this.transferSessionPackageI(currentTimeMillis);
            }
            this.activityState.resetSessionAttributes(currentTimeMillis);
            this.activityState.enabled = this.internalState.isEnabled();
            this.activityState.updatePackages = this.internalState.itHasToUpdatePackages();
            this.writeActivityStateI();
            sharedPreferencesManager.removePushToken();
            return;
        }
        final long n = currentTimeMillis - this.activityState.lastActivity;
        if (n < 0L) {
            this.logger.error("Time travel!", new Object[0]);
            this.activityState.lastActivity = currentTimeMillis;
            this.writeActivityStateI();
            return;
        }
        if (n > ActivityHandler.SESSION_INTERVAL) {
            this.trackNewSessionI(currentTimeMillis);
            return;
        }
        if (n > ActivityHandler.SUBSESSION_INTERVAL) {
            final ActivityState activityState = this.activityState;
            ++activityState.subsessionCount;
            final ActivityState activityState2 = this.activityState;
            activityState2.sessionLength += n;
            this.activityState.lastActivity = currentTimeMillis;
            this.logger.verbose("Started subsession %d of session %d", this.activityState.subsessionCount, this.activityState.sessionCount);
            this.writeActivityStateI();
            return;
        }
        this.logger.verbose("Time span since last activity too short for a new subsession", new Object[0]);
    }
    
    private void readActivityStateI(final Context context) {
        try {
            this.activityState = Util.readObject(context, "AdjustIoActivityState", "Activity state", ActivityState.class);
        }
        catch (Exception ex) {
            this.logger.error("Failed to read %s file (%s)", "Activity state", ex.getMessage());
            this.activityState = null;
        }
    }
    
    private void readAttributionI(final Context context) {
        try {
            this.attribution = Util.readObject(context, "AdjustAttribution", "Attribution", AdjustAttribution.class);
        }
        catch (Exception ex) {
            this.logger.error("Failed to read %s file (%s)", "Attribution", ex.getMessage());
            this.attribution = null;
        }
    }
    
    private void readConfigFile(final Context context) {
        try {
            final InputStream open = context.getAssets().open("adjust_config.properties");
            final Properties properties = new Properties();
            properties.load(open);
            this.logger.verbose("adjust_config.properties file read and loaded", new Object[0]);
            final String property = properties.getProperty("defaultTracker");
            if (property != null) {
                this.adjustConfig.defaultTracker = property;
            }
        }
        catch (Exception ex) {
            this.logger.debug("%s file not found in this app", ex.getMessage());
        }
    }
    
    private void readOpenUrlI(final Uri uri, final long n) {
        if (this.isEnabledI()) {
            final ActivityPackage buildDeeplinkSdkClickPackage = PackageFactory.buildDeeplinkSdkClickPackage(uri, n, this.activityState, this.adjustConfig, this.deviceInfo, this.sessionParameters);
            if (buildDeeplinkSdkClickPackage != null) {
                this.sdkClickHandler.sendSdkClick(buildDeeplinkSdkClickPackage);
            }
        }
    }
    
    private void readSessionCallbackParametersI(final Context context) {
        try {
            this.sessionParameters.callbackParameters = Util.readObject(context, "AdjustSessionCallbackParameters", "Session Callback parameters", (Class<Map<String, String>>)Map.class);
        }
        catch (Exception ex) {
            this.logger.error("Failed to read %s file (%s)", "Session Callback parameters", ex.getMessage());
            this.sessionParameters.callbackParameters = null;
        }
    }
    
    private void readSessionPartnerParametersI(final Context context) {
        try {
            this.sessionParameters.partnerParameters = Util.readObject(context, "AdjustSessionPartnerParameters", "Session Partner parameters", (Class<Map<String, String>>)Map.class);
        }
        catch (Exception ex) {
            this.logger.error("Failed to read %s file (%s)", "Session Partner parameters", ex.getMessage());
            this.sessionParameters.partnerParameters = null;
        }
    }
    
    private void resumeSendingI() {
        this.attributionHandler.resumeSending();
        this.packageHandler.resumeSending();
        this.sdkClickHandler.resumeSending();
    }
    
    private void sendFirstPackagesI() {
        if (this.internalState.isNotInDelayedStart()) {
            this.logger.info("Start delay expired or never configured", new Object[0]);
            return;
        }
        this.updatePackagesI();
        this.internalState.delayStart = false;
        this.delayStartTimer.cancel();
        this.delayStartTimer = null;
        this.updateHandlersStatusAndSendI();
    }
    
    private void sendInstallReferrerI(final long n, final long n2, final String s) {
        if (this.isEnabledI() && s != null && (n != this.activityState.clickTime || n2 != this.activityState.installBegin || !s.equals(this.activityState.installReferrer))) {
            this.sdkClickHandler.sendSdkClick(PackageFactory.buildInstallReferrerSdkClickPackage(s, n, n2, this.activityState, this.adjustConfig, this.deviceInfo, this.sessionParameters));
        }
    }
    
    private void sendReftagReferrerI() {
        if (!this.isEnabledI()) {
            return;
        }
        this.sdkClickHandler.sendReftagReferrers();
    }
    
    private void setAskingAttributionI(final boolean askingAttribution) {
        this.activityState.askingAttribution = askingAttribution;
        this.writeActivityStateI();
    }
    
    private void setEnabledI(final boolean b) {
        final boolean b2 = true;
        final boolean b3 = true;
        if (!this.hasChangedStateI(this.isEnabledI(), b, "Adjust already enabled", "Adjust already disabled")) {
            return;
        }
        this.internalState.enabled = b;
        if (this.activityState == null) {
            this.updateStatusI(!b && b3, "Handlers will start as paused due to the SDK being disabled", "Handlers will still start as paused", "Handlers will start as active due to the SDK being enabled");
            return;
        }
        if (b) {
            final SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(this.getContext());
            if (!sharedPreferencesManager.getInstallTracked()) {
                this.trackNewSessionI(System.currentTimeMillis());
            }
            final String pushToken = sharedPreferencesManager.getPushToken();
            if (pushToken != null && !pushToken.equals(this.activityState.pushToken)) {
                this.setPushToken(pushToken, true);
            }
            if (sharedPreferencesManager.getRawReferrerArray() != null) {
                this.sendReftagReferrer();
            }
        }
        this.activityState.enabled = b;
        this.writeActivityStateI();
        this.updateStatusI(!b && b2, "Pausing handlers due to SDK being disabled", "Handlers remain paused", "Resuming handlers due to SDK being enabled");
    }
    
    private void setOfflineModeI(final boolean offline) {
        if (!this.hasChangedStateI(this.internalState.isOffline(), offline, "Adjust already in offline mode", "Adjust already in online mode")) {
            return;
        }
        this.internalState.offline = offline;
        if (this.activityState == null) {
            this.updateStatusI(offline, "Handlers will start paused due to SDK being offline", "Handlers will still start as paused", "Handlers will start as active due to SDK being online");
            return;
        }
        this.updateStatusI(offline, "Pausing handlers to put SDK offline mode", "Handlers remain paused", "Resuming handlers to put SDK in online mode");
    }
    
    private void setPushTokenI(final String pushToken) {
        if (!this.checkActivityStateI(this.activityState) || !this.isEnabledI() || pushToken == null || pushToken.equals(this.activityState.pushToken)) {
            return;
        }
        this.activityState.pushToken = pushToken;
        this.writeActivityStateI();
        final ActivityPackage buildInfoPackage = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis()).buildInfoPackage("push");
        this.packageHandler.addPackage(buildInfoPackage);
        new SharedPreferencesManager(this.getContext()).removePushToken();
        if (this.adjustConfig.eventBufferingEnabled) {
            this.logger.info("Buffered event %s", buildInfoPackage.getSuffix());
            return;
        }
        this.packageHandler.sendFirstPackage();
    }
    
    private void startBackgroundTimerI() {
        if (this.backgroundTimer != null && this.toSendI() && this.backgroundTimer.getFireIn() <= 0L) {
            this.backgroundTimer.startIn(ActivityHandler.BACKGROUND_TIMER_INTERVAL);
        }
    }
    
    private void startForegroundTimerI() {
        if (!this.isEnabledI()) {
            return;
        }
        this.foregroundTimer.start();
    }
    
    private void startI() {
        if (this.activityState != null && !this.activityState.enabled) {
            return;
        }
        this.updateHandlersStatusAndSendI();
        this.processSessionI();
        this.checkAttributionStateI();
    }
    
    private void stopBackgroundTimerI() {
        if (this.backgroundTimer == null) {
            return;
        }
        this.backgroundTimer.cancel();
    }
    
    private void stopForegroundTimerI() {
        this.foregroundTimer.suspend();
    }
    
    private void teardownActivityStateS(final boolean b) {
        synchronized (ActivityState.class) {
            if (this.activityState == null) {
                return;
            }
            if (b && this.adjustConfig != null && this.adjustConfig.context != null) {
                deleteActivityState(this.adjustConfig.context);
            }
            this.activityState = null;
        }
    }
    
    private void teardownAllSessionParametersS(final boolean b) {
        synchronized (SessionParameters.class) {
            if (this.sessionParameters == null) {
                return;
            }
            if (b && this.adjustConfig != null && this.adjustConfig.context != null) {
                deleteSessionCallbackParameters(this.adjustConfig.context);
                deleteSessionPartnerParameters(this.adjustConfig.context);
            }
            this.sessionParameters = null;
        }
    }
    
    private void teardownAttributionS(final boolean b) {
        synchronized (AdjustAttribution.class) {
            if (this.attribution == null) {
                return;
            }
            if (b && this.adjustConfig != null && this.adjustConfig.context != null) {
                deleteAttribution(this.adjustConfig.context);
            }
            this.attribution = null;
        }
    }
    
    private boolean toSendI() {
        return this.toSendI(false);
    }
    
    private boolean toSendI(final boolean b) {
        return !this.pausedI(b) && (this.adjustConfig.sendInBackground || this.internalState.isInForeground());
    }
    
    private void trackEventI(final AdjustEvent adjustEvent) {
        if (this.checkActivityStateI(this.activityState) && this.isEnabledI() && this.checkEventI(adjustEvent) && this.checkOrderIdI(adjustEvent.orderId)) {
            final long currentTimeMillis = System.currentTimeMillis();
            final ActivityState activityState = this.activityState;
            ++activityState.eventCount;
            this.updateActivityStateI(currentTimeMillis);
            final ActivityPackage buildEventPackage = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, currentTimeMillis).buildEventPackage(adjustEvent, this.internalState.isInDelayedStart());
            this.packageHandler.addPackage(buildEventPackage);
            if (this.adjustConfig.eventBufferingEnabled) {
                this.logger.info("Buffered event %s", buildEventPackage.getSuffix());
            }
            else {
                this.packageHandler.sendFirstPackage();
            }
            if (this.adjustConfig.sendInBackground && this.internalState.isInBackground()) {
                this.startBackgroundTimerI();
            }
            this.writeActivityStateI();
        }
    }
    
    private void trackNewSessionI(final long n) {
        final long lastActivity = this.activityState.lastActivity;
        final ActivityState activityState = this.activityState;
        ++activityState.sessionCount;
        this.activityState.lastInterval = n - lastActivity;
        this.transferSessionPackageI(n);
        this.activityState.resetSessionAttributes(n);
        this.writeActivityStateI();
    }
    
    private void transferSessionPackageI(final long n) {
        this.packageHandler.addPackage(new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, n).buildSessionPackage(this.internalState.isInDelayedStart()));
        this.packageHandler.sendFirstPackage();
    }
    
    private boolean updateActivityStateI(final long lastActivity) {
        if (this.checkActivityStateI(this.activityState)) {
            final long n = lastActivity - this.activityState.lastActivity;
            if (n <= ActivityHandler.SESSION_INTERVAL) {
                this.activityState.lastActivity = lastActivity;
                if (n < 0L) {
                    this.logger.error("Time travel!", new Object[0]);
                }
                else {
                    final ActivityState activityState = this.activityState;
                    activityState.sessionLength += n;
                    final ActivityState activityState2 = this.activityState;
                    activityState2.timeSpent += n;
                }
                return true;
            }
        }
        return false;
    }
    
    private void updateAdidI(final String adid) {
        if (adid != null && !adid.equals(this.activityState.adid)) {
            this.activityState.adid = adid;
            this.writeActivityStateI();
        }
    }
    
    private void updateHandlersStatusAndSendI() {
        if (!this.toSendI()) {
            this.pauseSendingI();
        }
        else {
            this.resumeSendingI();
            if (this.internalState.isFirstLaunch() && this.internalState.hasSessionResponseNotBeenProcessed()) {
                this.packageHandler.sendFirstPackage();
            }
            if (!this.adjustConfig.eventBufferingEnabled) {
                this.packageHandler.sendFirstPackage();
            }
        }
    }
    
    private void updatePackagesI() {
        this.packageHandler.updatePackages(this.sessionParameters);
        this.internalState.updatePackages = false;
        if (this.activityState != null) {
            this.activityState.updatePackages = false;
            this.writeActivityStateI();
        }
    }
    
    private void updateStatusI(final boolean b, final String s, final String s2, final String s3) {
        if (b) {
            this.logger.info(s, new Object[0]);
        }
        else if (this.pausedI(false)) {
            if (this.pausedI(true)) {
                this.logger.info(s2, new Object[0]);
            }
            else {
                this.logger.info(s2 + ", except the Sdk Click Handler", new Object[0]);
            }
        }
        else {
            this.logger.info(s3, new Object[0]);
        }
        this.updateHandlersStatusAndSendI();
    }
    
    private void writeActivityStateI() {
        synchronized (ActivityState.class) {
            if (this.activityState == null) {
                return;
            }
            Util.writeObject(this.activityState, this.adjustConfig.context, "AdjustIoActivityState", "Activity state");
        }
    }
    
    private void writeAttributionI() {
        synchronized (AdjustAttribution.class) {
            if (this.attribution == null) {
                return;
            }
            Util.writeObject(this.attribution, this.adjustConfig.context, "AdjustAttribution", "Attribution");
        }
    }
    
    private void writeSessionCallbackParametersI() {
        synchronized (SessionParameters.class) {
            if (this.sessionParameters == null) {
                return;
            }
            Util.writeObject(this.sessionParameters.callbackParameters, this.adjustConfig.context, "AdjustSessionCallbackParameters", "Session Callback parameters");
        }
    }
    
    private void writeSessionPartnerParametersI() {
        synchronized (SessionParameters.class) {
            if (this.sessionParameters == null) {
                return;
            }
            Util.writeObject(this.sessionParameters.partnerParameters, this.adjustConfig.context, "AdjustSessionPartnerParameters", "Session Partner parameters");
        }
    }
    
    @Override
    public void addSessionCallbackParameter(final String s, final String s2) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.addSessionCallbackParameterI(s, s2);
            }
        });
    }
    
    public void addSessionCallbackParameterI(final String s, final String s2) {
        if (!Util.isValidParameter(s, "key", "Session Callback") || !Util.isValidParameter(s2, "value", "Session Callback")) {
            return;
        }
        if (this.sessionParameters.callbackParameters == null) {
            this.sessionParameters.callbackParameters = new LinkedHashMap<String, String>();
        }
        final String s3 = this.sessionParameters.callbackParameters.get(s);
        if (s2.equals(s3)) {
            this.logger.verbose("Key %s already present with the same value", s);
            return;
        }
        if (s3 != null) {
            this.logger.warn("Key %s will be overwritten", s);
        }
        this.sessionParameters.callbackParameters.put(s, s2);
        this.writeSessionCallbackParametersI();
    }
    
    @Override
    public void addSessionPartnerParameter(final String s, final String s2) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.addSessionPartnerParameterI(s, s2);
            }
        });
    }
    
    public void addSessionPartnerParameterI(final String s, final String s2) {
        if (!Util.isValidParameter(s, "key", "Session Partner") || !Util.isValidParameter(s2, "value", "Session Partner")) {
            return;
        }
        if (this.sessionParameters.partnerParameters == null) {
            this.sessionParameters.partnerParameters = new LinkedHashMap<String, String>();
        }
        final String s3 = this.sessionParameters.partnerParameters.get(s);
        if (s2.equals(s3)) {
            this.logger.verbose("Key %s already present with the same value", s);
            return;
        }
        if (s3 != null) {
            this.logger.warn("Key %s will be overwritten", s);
        }
        this.sessionParameters.partnerParameters.put(s, s2);
        this.writeSessionPartnerParametersI();
    }
    
    public void backgroundTimerFired() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.backgroundTimerFiredI();
            }
        });
    }
    
    @Override
    public void finishedTrackingActivity(final ResponseData responseData) {
        if (responseData instanceof SessionResponseData) {
            this.attributionHandler.checkSessionResponse((SessionResponseData)responseData);
        }
        else {
            if (responseData instanceof SdkClickResponseData) {
                this.checkForInstallReferrerInfo((SdkClickResponseData)responseData);
                this.attributionHandler.checkSdkClickResponse((SdkClickResponseData)responseData);
                return;
            }
            if (responseData instanceof EventResponseData) {
                this.launchEventResponseTasks((EventResponseData)responseData);
            }
        }
    }
    
    public void foregroundTimerFired() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.foregroundTimerFiredI();
            }
        });
    }
    
    @Override
    public ActivityState getActivityState() {
        return this.activityState;
    }
    
    @Override
    public String getAdid() {
        if (this.activityState == null) {
            return null;
        }
        return this.activityState.adid;
    }
    
    @Override
    public AdjustConfig getAdjustConfig() {
        return this.adjustConfig;
    }
    
    @Override
    public AdjustAttribution getAttribution() {
        return this.attribution;
    }
    
    public ActivityPackage getAttributionPackageI() {
        return new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis()).buildAttributionPackage();
    }
    
    @Override
    public Context getContext() {
        return this.adjustConfig.context;
    }
    
    @Override
    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }
    
    public InternalState getInternalState() {
        return this.internalState;
    }
    
    @Override
    public SessionParameters getSessionParameters() {
        return this.sessionParameters;
    }
    
    @Override
    public void init(final AdjustConfig adjustConfig) {
        this.adjustConfig = adjustConfig;
    }
    
    @Override
    public boolean isEnabled() {
        return this.isEnabledI();
    }
    
    @Override
    public void launchAttributionResponseTasks(final AttributionResponseData attributionResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.launchAttributionResponseTasksI(attributionResponseData);
            }
        });
    }
    
    @Override
    public void launchEventResponseTasks(final EventResponseData eventResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.launchEventResponseTasksI(eventResponseData);
            }
        });
    }
    
    @Override
    public void launchSdkClickResponseTasks(final SdkClickResponseData sdkClickResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.launchSdkClickResponseTasksI(sdkClickResponseData);
            }
        });
    }
    
    @Override
    public void launchSessionResponseTasks(final SessionResponseData sessionResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.launchSessionResponseTasksI(sessionResponseData);
            }
        });
    }
    
    @Override
    public void onPause() {
        this.internalState.background = true;
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.stopForegroundTimerI();
                ActivityHandler.this.startBackgroundTimerI();
                ActivityHandler.this.logger.verbose("Subsession end", new Object[0]);
                ActivityHandler.this.endI();
            }
        });
    }
    
    @Override
    public void onResume() {
        this.internalState.background = false;
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.delayStartI();
                ActivityHandler.this.stopBackgroundTimerI();
                ActivityHandler.this.startForegroundTimerI();
                ActivityHandler.this.logger.verbose("Subsession start", new Object[0]);
                ActivityHandler.this.startI();
            }
        });
    }
    
    @Override
    public void readOpenUrl(final Uri uri, final long n) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.readOpenUrlI(uri, n);
            }
        });
    }
    
    @Override
    public void removeSessionCallbackParameter(final String s) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.removeSessionCallbackParameterI(s);
            }
        });
    }
    
    public void removeSessionCallbackParameterI(final String s) {
        if (!Util.isValidParameter(s, "key", "Session Callback")) {
            return;
        }
        if (this.sessionParameters.callbackParameters == null) {
            this.logger.warn("Session Callback parameters are not set", new Object[0]);
            return;
        }
        if (this.sessionParameters.callbackParameters.remove(s) == null) {
            this.logger.warn("Key %s does not exist", s);
            return;
        }
        this.logger.debug("Key %s will be removed", s);
        this.writeSessionCallbackParametersI();
    }
    
    @Override
    public void removeSessionPartnerParameter(final String s) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.removeSessionPartnerParameterI(s);
            }
        });
    }
    
    public void removeSessionPartnerParameterI(final String s) {
        if (!Util.isValidParameter(s, "key", "Session Partner")) {
            return;
        }
        if (this.sessionParameters.partnerParameters == null) {
            this.logger.warn("Session Partner parameters are not set", new Object[0]);
            return;
        }
        if (this.sessionParameters.partnerParameters.remove(s) == null) {
            this.logger.warn("Key %s does not exist", s);
            return;
        }
        this.logger.debug("Key %s will be removed", s);
        this.writeSessionPartnerParametersI();
    }
    
    @Override
    public void resetSessionCallbackParameters() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.resetSessionCallbackParametersI();
            }
        });
    }
    
    public void resetSessionCallbackParametersI() {
        if (this.sessionParameters.callbackParameters == null) {
            this.logger.warn("Session Callback parameters are not set", new Object[0]);
        }
        this.sessionParameters.callbackParameters = null;
        this.writeSessionCallbackParametersI();
    }
    
    @Override
    public void resetSessionPartnerParameters() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.resetSessionPartnerParametersI();
            }
        });
    }
    
    public void resetSessionPartnerParametersI() {
        if (this.sessionParameters.partnerParameters == null) {
            this.logger.warn("Session Partner parameters are not set", new Object[0]);
        }
        this.sessionParameters.partnerParameters = null;
        this.writeSessionPartnerParametersI();
    }
    
    @Override
    public void sendFirstPackages() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.sendFirstPackagesI();
            }
        });
    }
    
    @Override
    public void sendInstallReferrer(final long n, final long n2, final String s) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.sendInstallReferrerI(n, n2, s);
            }
        });
    }
    
    @Override
    public void sendReftagReferrer() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.sendReftagReferrerI();
            }
        });
    }
    
    @Override
    public void setAskingAttribution(final boolean b) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.setAskingAttributionI(b);
            }
        });
    }
    
    @Override
    public void setEnabled(final boolean b) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.setEnabledI(b);
            }
        });
    }
    
    @Override
    public void setOfflineMode(final boolean b) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                ActivityHandler.this.setOfflineModeI(b);
            }
        });
    }
    
    @Override
    public void setPushToken(final String s, final boolean b) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                if (!b) {
                    new SharedPreferencesManager(ActivityHandler.this.getContext()).savePushToken(s);
                }
                if (ActivityHandler.this.activityState == null) {
                    return;
                }
                ActivityHandler.this.setPushTokenI(s);
            }
        });
    }
    
    @Override
    public void teardown(final boolean b) {
        if (this.backgroundTimer != null) {
            this.backgroundTimer.teardown();
        }
        if (this.foregroundTimer != null) {
            this.foregroundTimer.teardown();
        }
        if (this.delayStartTimer != null) {
            this.delayStartTimer.teardown();
        }
        while (true) {
            if (this.scheduledExecutor == null) {
                break Label_0056;
            }
            try {
                this.scheduledExecutor.shutdownNow();
                if (this.packageHandler != null) {
                    this.packageHandler.teardown(b);
                }
                if (this.attributionHandler != null) {
                    this.attributionHandler.teardown();
                }
                if (this.sdkClickHandler != null) {
                    this.sdkClickHandler.teardown();
                }
                if (this.sessionParameters != null) {
                    if (this.sessionParameters.callbackParameters != null) {
                        this.sessionParameters.callbackParameters.clear();
                    }
                    if (this.sessionParameters.partnerParameters != null) {
                        this.sessionParameters.partnerParameters.clear();
                    }
                }
                this.teardownActivityStateS(b);
                this.teardownAttributionS(b);
                this.teardownAllSessionParametersS(b);
                if (b) {
                    new SharedPreferencesManager(this.getContext()).clear();
                }
                this.packageHandler = null;
                this.logger = null;
                this.foregroundTimer = null;
                this.scheduledExecutor = null;
                this.backgroundTimer = null;
                this.delayStartTimer = null;
                this.internalState = null;
                this.deviceInfo = null;
                this.adjustConfig = null;
                this.attributionHandler = null;
                this.sdkClickHandler = null;
                this.sessionParameters = null;
            }
            catch (SecurityException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void trackEvent(final AdjustEvent adjustEvent) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                if (ActivityHandler.this.activityState == null) {
                    ActivityHandler.this.logger.warn("Event tracked before first activity resumed.\nIf it was triggered in the Application class, it might timestamp or even send an install long before the user opens the app.\nPlease check https://github.com/adjust/android_sdk#can-i-trigger-an-event-at-application-launch for more information.", new Object[0]);
                    ActivityHandler.this.startI();
                }
                ActivityHandler.this.trackEventI(adjustEvent);
            }
        });
    }
    
    @Override
    public boolean updateAttributionI(final AdjustAttribution attribution) {
        if (attribution != null && !attribution.equals(this.attribution)) {
            this.attribution = attribution;
            this.writeAttributionI();
            return true;
        }
        return false;
    }
    
    public class InternalState
    {
        boolean background;
        boolean delayStart;
        boolean enabled;
        boolean firstLaunch;
        boolean offline;
        boolean sessionResponseProcessed;
        boolean updatePackages;
        
        public boolean hasSessionResponseNotBeenProcessed() {
            return !this.sessionResponseProcessed;
        }
        
        public boolean isDisabled() {
            return !this.enabled;
        }
        
        public boolean isEnabled() {
            return this.enabled;
        }
        
        public boolean isFirstLaunch() {
            return this.firstLaunch;
        }
        
        public boolean isInBackground() {
            return this.background;
        }
        
        public boolean isInDelayedStart() {
            return this.delayStart;
        }
        
        public boolean isInForeground() {
            return !this.background;
        }
        
        public boolean isNotFirstLaunch() {
            return !this.firstLaunch;
        }
        
        public boolean isNotInDelayedStart() {
            return !this.delayStart;
        }
        
        public boolean isOffline() {
            return this.offline;
        }
        
        public boolean isOnline() {
            return !this.offline;
        }
        
        public boolean itHasToUpdatePackages() {
            return this.updatePackages;
        }
    }
}
