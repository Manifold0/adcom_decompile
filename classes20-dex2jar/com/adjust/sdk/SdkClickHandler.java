// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.lang.ref.WeakReference;

public class SdkClickHandler implements ISdkClickHandler
{
    private static final double MILLISECONDS_TO_SECONDS_DIVISOR = 1000.0;
    private static final String SCHEDULED_EXECUTOR_SOURCE = "SdkClickHandler";
    private static final String SOURCE_INSTALL_REFERRER = "install_referrer";
    private static final String SOURCE_REFTAG = "reftag";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private BackoffStrategy backoffStrategy;
    private ILogger logger;
    private List<ActivityPackage> packageQueue;
    private boolean paused;
    private CustomScheduledExecutor scheduledExecutor;
    
    public SdkClickHandler(final IActivityHandler activityHandler, final boolean b) {
        this.init(activityHandler, b);
        this.logger = AdjustFactory.getLogger();
        this.backoffStrategy = AdjustFactory.getSdkClickBackoffStrategy();
        this.scheduledExecutor = new CustomScheduledExecutor("SdkClickHandler", false);
    }
    
    private void logErrorMessageI(final ActivityPackage activityPackage, final String s, final Throwable t) {
        this.logger.error(Util.formatString("%s. (%s)", activityPackage.getFailureMessage(), Util.getReasonString(s, t)), new Object[0]);
    }
    
    private void retrySendingI(final ActivityPackage activityPackage) {
        this.logger.error("Retrying sdk_click package for the %d time", activityPackage.increaseRetries());
        this.sendSdkClick(activityPackage);
    }
    
    private void sendNextSdkClick() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                SdkClickHandler.this.sendNextSdkClickI();
            }
        });
    }
    
    private void sendNextSdkClickI() {
        if (this.paused || this.packageQueue.isEmpty()) {
            return;
        }
        final ActivityPackage activityPackage = this.packageQueue.remove(0);
        final int retries = activityPackage.getRetries();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SdkClickHandler.this.sendSdkClickI(activityPackage);
                SdkClickHandler.this.sendNextSdkClick();
            }
        };
        if (retries <= 0) {
            runnable.run();
            return;
        }
        final long waitingTime = Util.getWaitingTime(retries, this.backoffStrategy);
        this.logger.verbose("Waiting for %s seconds before retrying sdk_click for the %d time", Util.SecondsDisplayFormat.format(waitingTime / 1000.0), retries);
        this.scheduledExecutor.schedule(runnable, waitingTime, TimeUnit.MILLISECONDS);
    }
    
    private void sendSdkClickI(final ActivityPackage activityPackage) {
        final IActivityHandler activityHandler = this.activityHandlerWeakRef.get();
        final String s = activityPackage.getParameters().get("source");
        boolean b;
        if (s != null && s.equals("reftag")) {
            b = true;
        }
        else {
            b = false;
        }
        final String s2 = activityPackage.getParameters().get("raw_referrer");
        if (b && new SharedPreferencesManager(activityHandler.getContext()).getRawReferrer(s2, activityPackage.getClickTimeInMilliseconds()) == null) {
            return;
        }
        if (s == null || !s.equals("install_referrer")) {
            goto Label_0234;
        }
        if (true) {
            activityPackage.getClickTimeInSeconds();
            activityPackage.getInstallBeginTimeInSeconds();
            final String s3 = activityPackage.getParameters().get("referrer");
        }
        final String string = "https://app.adjust.com" + activityPackage.getPath();
        try {
            if (((SdkClickResponseData)UtilNetworking.createPOSTHttpsURLConnection(string, activityPackage, this.packageQueue.size() - 1)).jsonResponse == null) {
                this.retrySendingI(activityPackage);
                return;
            }
            goto Label_0239;
        }
        catch (UnsupportedEncodingException ex) {
            this.logErrorMessageI(activityPackage, "Sdk_click failed to encode parameters", ex);
        }
        catch (SocketTimeoutException ex2) {
            this.logErrorMessageI(activityPackage, "Sdk_click request timed out. Will retry later", ex2);
            this.retrySendingI(activityPackage);
        }
        catch (IOException ex3) {
            this.logErrorMessageI(activityPackage, "Sdk_click request failed. Will retry later", ex3);
            this.retrySendingI(activityPackage);
        }
        catch (Throwable t) {
            this.logErrorMessageI(activityPackage, "Sdk_click runtime exception", t);
        }
    }
    
    @Override
    public void init(final IActivityHandler activityHandler, final boolean b) {
        this.paused = !b;
        this.packageQueue = new ArrayList<ActivityPackage>();
        this.activityHandlerWeakRef = new WeakReference<IActivityHandler>(activityHandler);
    }
    
    @Override
    public void pauseSending() {
        this.paused = true;
    }
    
    @Override
    public void resumeSending() {
        this.paused = false;
        this.sendNextSdkClick();
    }
    
    @Override
    public void sendReftagReferrers() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                SharedPreferencesManager sharedPreferencesManager = null;
                JSONArray rawReferrerArray = null;
                boolean b = false;
                Label_0178: {
                    while (true) {
                        final IActivityHandler activityHandler = (IActivityHandler)SdkClickHandler.this.activityHandlerWeakRef.get();
                        sharedPreferencesManager = new SharedPreferencesManager(activityHandler.getContext());
                        while (true) {
                            int n = 0;
                            Label_0190: {
                                try {
                                    rawReferrerArray = sharedPreferencesManager.getRawReferrerArray();
                                    b = false;
                                    n = 0;
                                    if (n >= rawReferrerArray.length()) {
                                        break Label_0178;
                                    }
                                    final JSONArray jsonArray = rawReferrerArray.getJSONArray(n);
                                    if (jsonArray.optInt(2, -1) != 0) {
                                        break Label_0190;
                                    }
                                    final String optString = jsonArray.optString(0, (String)null);
                                    final long optLong = jsonArray.optLong(1, -1L);
                                    jsonArray.put(2, 1);
                                    b = true;
                                    SdkClickHandler.this.sendSdkClick(PackageFactory.buildReftagSdkClickPackage(optString, optLong, activityHandler.getActivityState(), activityHandler.getAdjustConfig(), activityHandler.getDeviceInfo(), activityHandler.getSessionParameters()));
                                    break Label_0190;
                                }
                                catch (JSONException ex) {
                                    SdkClickHandler.this.logger.error("Send saved raw referrers error (%s)", ex.getMessage());
                                }
                                break;
                            }
                            ++n;
                            continue;
                        }
                    }
                    return;
                }
                if (b) {
                    sharedPreferencesManager.saveRawReferrerArray(rawReferrerArray);
                }
            }
        });
    }
    
    @Override
    public void sendSdkClick(final ActivityPackage activityPackage) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                SdkClickHandler.this.packageQueue.add(activityPackage);
                SdkClickHandler.this.logger.debug("Added sdk_click %d", SdkClickHandler.this.packageQueue.size());
                SdkClickHandler.this.logger.verbose("%s", activityPackage.getExtendedString());
                SdkClickHandler.this.sendNextSdkClick();
            }
        });
    }
    
    @Override
    public void teardown() {
        this.logger.verbose("SdkClickHandler teardown", new Object[0]);
        while (true) {
            if (this.scheduledExecutor == null) {
                break Label_0030;
            }
            try {
                this.scheduledExecutor.shutdownNow();
                if (this.packageQueue != null) {
                    this.packageQueue.clear();
                }
                if (this.activityHandlerWeakRef != null) {
                    this.activityHandlerWeakRef.clear();
                }
                this.logger = null;
                this.packageQueue = null;
                this.backoffStrategy = null;
                this.scheduledExecutor = null;
            }
            catch (SecurityException ex) {
                continue;
            }
            break;
        }
    }
}
