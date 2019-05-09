// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import org.json.JSONObject;
import android.net.Uri;
import java.lang.ref.WeakReference;

public class AttributionHandler implements IAttributionHandler
{
    private static final String ATTRIBUTION_TIMER_NAME = "Attribution timer";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private ActivityPackage attributionPackage;
    private ILogger logger;
    private boolean paused;
    private CustomScheduledExecutor scheduledExecutor;
    private TimerOnce timer;
    
    public AttributionHandler(final IActivityHandler activityHandler, final ActivityPackage activityPackage, final boolean b) {
        this.scheduledExecutor = new CustomScheduledExecutor("AttributionHandler", false);
        this.logger = AdjustFactory.getLogger();
        this.timer = new TimerOnce(new Runnable() {
            @Override
            public void run() {
                AttributionHandler.this.sendAttributionRequest();
            }
        }, "Attribution timer");
        this.init(activityHandler, activityPackage, b);
    }
    
    private void checkAttributionI(final IActivityHandler activityHandler, final ResponseData responseData) {
        if (responseData.jsonResponse == null) {
            return;
        }
        final long optLong = responseData.jsonResponse.optLong("ask_in", -1L);
        if (optLong >= 0L) {
            activityHandler.setAskingAttribution(true);
            this.getAttributionI(optLong);
            return;
        }
        activityHandler.setAskingAttribution(false);
        responseData.attribution = AdjustAttribution.fromJson(responseData.jsonResponse.optJSONObject("attribution"), responseData.adid);
    }
    
    private void checkAttributionResponseI(final IActivityHandler activityHandler, final AttributionResponseData attributionResponseData) {
        this.checkAttributionI(activityHandler, attributionResponseData);
        this.checkDeeplinkI(attributionResponseData);
        activityHandler.launchAttributionResponseTasks(attributionResponseData);
    }
    
    private void checkDeeplinkI(final AttributionResponseData attributionResponseData) {
        if (attributionResponseData.jsonResponse != null) {
            final JSONObject optJSONObject = attributionResponseData.jsonResponse.optJSONObject("attribution");
            if (optJSONObject != null) {
                final String optString = optJSONObject.optString("deeplink", (String)null);
                if (optString != null) {
                    attributionResponseData.deeplink = Uri.parse(optString);
                }
            }
        }
    }
    
    private void checkSdkClickResponseI(final IActivityHandler activityHandler, final SdkClickResponseData sdkClickResponseData) {
        this.checkAttributionI(activityHandler, sdkClickResponseData);
        activityHandler.launchSdkClickResponseTasks(sdkClickResponseData);
    }
    
    private void checkSessionResponseI(final IActivityHandler activityHandler, final SessionResponseData sessionResponseData) {
        this.checkAttributionI(activityHandler, sessionResponseData);
        activityHandler.launchSessionResponseTasks(sessionResponseData);
    }
    
    private void getAttributionI(final long n) {
        if (this.timer.getFireIn() > n) {
            return;
        }
        if (n != 0L) {
            this.logger.debug("Waiting to query attribution in %s seconds", Util.SecondsDisplayFormat.format(n / 1000.0));
        }
        this.timer.startIn(n);
    }
    
    private void sendAttributionRequestI() {
        if (this.paused) {
            this.logger.debug("Attribution handler is paused", new Object[0]);
        }
        else {
            this.logger.verbose("%s", this.attributionPackage.getExtendedString());
            try {
                final ResponseData getHttpsURLConnection = UtilNetworking.createGETHttpsURLConnection(this.attributionPackage);
                if (getHttpsURLConnection instanceof AttributionResponseData) {
                    this.checkAttributionResponse((AttributionResponseData)getHttpsURLConnection);
                }
            }
            catch (Exception ex) {
                this.logger.error("Failed to get attribution (%s)", ex.getMessage());
            }
        }
    }
    
    public void checkAttributionResponse(final AttributionResponseData attributionResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                final IActivityHandler activityHandler = (IActivityHandler)AttributionHandler.this.activityHandlerWeakRef.get();
                if (activityHandler == null) {
                    return;
                }
                AttributionHandler.this.checkAttributionResponseI(activityHandler, attributionResponseData);
            }
        });
    }
    
    @Override
    public void checkSdkClickResponse(final SdkClickResponseData sdkClickResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                final IActivityHandler activityHandler = (IActivityHandler)AttributionHandler.this.activityHandlerWeakRef.get();
                if (activityHandler == null) {
                    return;
                }
                AttributionHandler.this.checkSdkClickResponseI(activityHandler, sdkClickResponseData);
            }
        });
    }
    
    @Override
    public void checkSessionResponse(final SessionResponseData sessionResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                final IActivityHandler activityHandler = (IActivityHandler)AttributionHandler.this.activityHandlerWeakRef.get();
                if (activityHandler == null) {
                    return;
                }
                AttributionHandler.this.checkSessionResponseI(activityHandler, sessionResponseData);
            }
        });
    }
    
    @Override
    public void getAttribution() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                AttributionHandler.this.getAttributionI(0L);
            }
        });
    }
    
    @Override
    public void init(final IActivityHandler activityHandler, final ActivityPackage attributionPackage, final boolean b) {
        this.activityHandlerWeakRef = new WeakReference<IActivityHandler>(activityHandler);
        this.attributionPackage = attributionPackage;
        this.paused = !b;
    }
    
    @Override
    public void pauseSending() {
        this.paused = true;
    }
    
    @Override
    public void resumeSending() {
        this.paused = false;
    }
    
    public void sendAttributionRequest() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                AttributionHandler.this.sendAttributionRequestI();
            }
        });
    }
    
    @Override
    public void teardown() {
        this.logger.verbose("AttributionHandler teardown", new Object[0]);
        if (this.timer != null) {
            this.timer.teardown();
        }
        while (true) {
            if (this.scheduledExecutor == null) {
                break Label_0044;
            }
            try {
                this.scheduledExecutor.shutdownNow();
                if (this.activityHandlerWeakRef != null) {
                    this.activityHandlerWeakRef.clear();
                }
                this.scheduledExecutor = null;
                this.activityHandlerWeakRef = null;
                this.logger = null;
                this.attributionPackage = null;
                this.timer = null;
            }
            catch (SecurityException ex) {
                continue;
            }
            break;
        }
    }
}
