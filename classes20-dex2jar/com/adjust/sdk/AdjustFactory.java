// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import android.content.Context;
import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class AdjustFactory
{
    private static IActivityHandler activityHandler;
    private static IAttributionHandler attributionHandler;
    private static HttpsURLConnection httpsURLConnection;
    private static ILogger logger;
    private static long maxDelayStart;
    private static IPackageHandler packageHandler;
    private static BackoffStrategy packageHandlerBackoffStrategy;
    private static IRequestHandler requestHandler;
    private static BackoffStrategy sdkClickBackoffStrategy;
    private static ISdkClickHandler sdkClickHandler;
    private static long sessionInterval;
    private static long subsessionInterval;
    private static long timerInterval;
    private static long timerStart;
    
    static {
        AdjustFactory.packageHandler = null;
        AdjustFactory.requestHandler = null;
        AdjustFactory.attributionHandler = null;
        AdjustFactory.activityHandler = null;
        AdjustFactory.logger = null;
        AdjustFactory.httpsURLConnection = null;
        AdjustFactory.sdkClickHandler = null;
        AdjustFactory.timerInterval = -1L;
        AdjustFactory.timerStart = -1L;
        AdjustFactory.sessionInterval = -1L;
        AdjustFactory.subsessionInterval = -1L;
        AdjustFactory.sdkClickBackoffStrategy = null;
        AdjustFactory.packageHandlerBackoffStrategy = null;
        AdjustFactory.maxDelayStart = -1L;
    }
    
    public static IActivityHandler getActivityHandler(final AdjustConfig adjustConfig) {
        if (AdjustFactory.activityHandler == null) {
            return ActivityHandler.getInstance(adjustConfig);
        }
        AdjustFactory.activityHandler.init(adjustConfig);
        return AdjustFactory.activityHandler;
    }
    
    public static IAttributionHandler getAttributionHandler(final IActivityHandler activityHandler, final ActivityPackage activityPackage, final boolean b) {
        if (AdjustFactory.attributionHandler == null) {
            return new AttributionHandler(activityHandler, activityPackage, b);
        }
        AdjustFactory.attributionHandler.init(activityHandler, activityPackage, b);
        return AdjustFactory.attributionHandler;
    }
    
    public static HttpsURLConnection getHttpsURLConnection(final URL url) throws IOException {
        if (AdjustFactory.httpsURLConnection == null) {
            return (HttpsURLConnection)url.openConnection();
        }
        return AdjustFactory.httpsURLConnection;
    }
    
    public static ILogger getLogger() {
        if (AdjustFactory.logger == null) {
            AdjustFactory.logger = new Logger();
        }
        return AdjustFactory.logger;
    }
    
    public static long getMaxDelayStart() {
        if (AdjustFactory.maxDelayStart == -1L) {
            return 10000L;
        }
        return AdjustFactory.maxDelayStart;
    }
    
    public static IPackageHandler getPackageHandler(final IActivityHandler activityHandler, final Context context, final boolean b) {
        if (AdjustFactory.packageHandler == null) {
            return new PackageHandler(activityHandler, context, b);
        }
        AdjustFactory.packageHandler.init(activityHandler, context, b);
        return AdjustFactory.packageHandler;
    }
    
    public static BackoffStrategy getPackageHandlerBackoffStrategy() {
        if (AdjustFactory.packageHandlerBackoffStrategy == null) {
            return BackoffStrategy.LONG_WAIT;
        }
        return AdjustFactory.packageHandlerBackoffStrategy;
    }
    
    public static IRequestHandler getRequestHandler(final IPackageHandler packageHandler) {
        if (AdjustFactory.requestHandler == null) {
            return new RequestHandler(packageHandler);
        }
        AdjustFactory.requestHandler.init(packageHandler);
        return AdjustFactory.requestHandler;
    }
    
    public static BackoffStrategy getSdkClickBackoffStrategy() {
        if (AdjustFactory.sdkClickBackoffStrategy == null) {
            return BackoffStrategy.SHORT_WAIT;
        }
        return AdjustFactory.sdkClickBackoffStrategy;
    }
    
    public static ISdkClickHandler getSdkClickHandler(final IActivityHandler activityHandler, final boolean b) {
        if (AdjustFactory.sdkClickHandler == null) {
            return new SdkClickHandler(activityHandler, b);
        }
        AdjustFactory.sdkClickHandler.init(activityHandler, b);
        return AdjustFactory.sdkClickHandler;
    }
    
    public static long getSessionInterval() {
        if (AdjustFactory.sessionInterval == -1L) {
            return 1800000L;
        }
        return AdjustFactory.sessionInterval;
    }
    
    public static long getSubsessionInterval() {
        if (AdjustFactory.subsessionInterval == -1L) {
            return 1000L;
        }
        return AdjustFactory.subsessionInterval;
    }
    
    public static long getTimerInterval() {
        if (AdjustFactory.timerInterval == -1L) {
            return 60000L;
        }
        return AdjustFactory.timerInterval;
    }
    
    public static long getTimerStart() {
        if (AdjustFactory.timerStart == -1L) {
            return 60000L;
        }
        return AdjustFactory.timerStart;
    }
    
    public static void setActivityHandler(final IActivityHandler activityHandler) {
        AdjustFactory.activityHandler = activityHandler;
    }
    
    public static void setAttributionHandler(final IAttributionHandler attributionHandler) {
        AdjustFactory.attributionHandler = attributionHandler;
    }
    
    public static void setHttpsURLConnection(final HttpsURLConnection httpsURLConnection) {
        AdjustFactory.httpsURLConnection = httpsURLConnection;
    }
    
    public static void setLogger(final ILogger logger) {
        AdjustFactory.logger = logger;
    }
    
    public static void setPackageHandler(final IPackageHandler packageHandler) {
        AdjustFactory.packageHandler = packageHandler;
    }
    
    public static void setPackageHandlerBackoffStrategy(final BackoffStrategy packageHandlerBackoffStrategy) {
        AdjustFactory.packageHandlerBackoffStrategy = packageHandlerBackoffStrategy;
    }
    
    public static void setRequestHandler(final IRequestHandler requestHandler) {
        AdjustFactory.requestHandler = requestHandler;
    }
    
    public static void setSdkClickBackoffStrategy(final BackoffStrategy sdkClickBackoffStrategy) {
        AdjustFactory.sdkClickBackoffStrategy = sdkClickBackoffStrategy;
    }
    
    public static void setSdkClickHandler(final ISdkClickHandler sdkClickHandler) {
        AdjustFactory.sdkClickHandler = sdkClickHandler;
    }
    
    public static void setSessionInterval(final long sessionInterval) {
        AdjustFactory.sessionInterval = sessionInterval;
    }
    
    public static void setSubsessionInterval(final long subsessionInterval) {
        AdjustFactory.subsessionInterval = subsessionInterval;
    }
    
    public static void setTimerInterval(final long timerInterval) {
        AdjustFactory.timerInterval = timerInterval;
    }
    
    public static void setTimerStart(final long timerStart) {
        AdjustFactory.timerStart = timerStart;
    }
    
    public static class URLGetConnection
    {
        HttpsURLConnection httpsURLConnection;
        URL url;
        
        URLGetConnection(final HttpsURLConnection httpsURLConnection, final URL url) {
            this.httpsURLConnection = httpsURLConnection;
            this.url = url;
        }
    }
}
