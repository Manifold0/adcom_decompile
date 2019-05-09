// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.analytics.moat;

import com.moat.analytics.mobile.iro.MoatAnalytics;
import android.app.Application;
import com.moat.analytics.mobile.iro.MoatOptions;
import org.json.JSONObject;
import com.moat.analytics.mobile.iro.MoatFactory;
import android.webkit.WebView;
import com.moat.analytics.mobile.iro.WebAdTracker;
import com.moat.analytics.mobile.iro.TrackerListener;

public class MOATManager
{
    private static final String autoTrackGMAInterstitials = "autoTrackGMAInterstitials";
    private static final String disableAdIdCollection = "disableAdIdCollection";
    private static final String disableLocationService = "disableLocationServices";
    private static final String loggingEnabled = "loggingEnabled";
    private static EventsListener mEventsListener;
    private static TrackerListener moatCallbacks;
    private static WebAdTracker webAdTracker;
    
    static {
        MOATManager.moatCallbacks = new TrackerListener() {
            @Override
            public void onTrackingFailedToStart(final String s) {
                if (MOATManager.mEventsListener != null) {
                    MOATManager.mEventsListener.onTrackingFailedToStart(s);
                }
            }
            
            @Override
            public void onTrackingStarted(final String s) {
                if (MOATManager.mEventsListener != null) {
                    MOATManager.mEventsListener.onTrackingStarted(s);
                }
            }
            
            @Override
            public void onTrackingStopped(final String s) {
                if (MOATManager.mEventsListener != null) {
                    MOATManager.mEventsListener.onTrackingStopped(s);
                }
            }
        };
    }
    
    public static void createAdTracker(final WebView webView) throws Exception {
        MOATManager.webAdTracker = MoatFactory.create().createWebAdTracker(webView);
    }
    
    private static MoatOptions createMoatOptions(final JSONObject jsonObject) {
        final MoatOptions moatOptions = new MoatOptions();
        moatOptions.loggingEnabled = jsonObject.optBoolean("loggingEnabled");
        moatOptions.autoTrackGMAInterstitials = jsonObject.optBoolean("autoTrackGMAInterstitials");
        moatOptions.disableAdIdCollection = jsonObject.optBoolean("disableAdIdCollection");
        moatOptions.disableLocationServices = jsonObject.optBoolean("disableLocationServices");
        return moatOptions;
    }
    
    public static void init(final Application application) throws Exception {
        initWithOptions(null, application);
    }
    
    public static void initWithOptions(final JSONObject jsonObject, final Application application) throws Exception {
        MoatOptions moatOptions = null;
        if (jsonObject != null) {
            moatOptions = moatOptions;
            if (jsonObject.length() > 0) {
                moatOptions = createMoatOptions(jsonObject);
            }
        }
        MoatAnalytics.getInstance().start(moatOptions, application);
    }
    
    public static void setEventListener(final EventsListener mEventsListener) {
        MOATManager.mEventsListener = mEventsListener;
    }
    
    public static void startTracking() throws Exception {
        if (MOATManager.webAdTracker != null) {
            MOATManager.webAdTracker.setListener(MOATManager.moatCallbacks);
            MOATManager.webAdTracker.startTracking();
        }
    }
    
    public static void stopTracking() throws Exception {
        if (MOATManager.webAdTracker != null) {
            MOATManager.webAdTracker.stopTracking();
        }
    }
    
    public interface EventsListener extends TrackerListener
    {
    }
}
