package com.ironsource.sdk.analytics.moat;

import android.app.Application;
import android.webkit.WebView;
import com.moat.analytics.mobile.iro.MoatAnalytics;
import com.moat.analytics.mobile.iro.MoatFactory;
import com.moat.analytics.mobile.iro.MoatOptions;
import com.moat.analytics.mobile.iro.TrackerListener;
import com.moat.analytics.mobile.iro.WebAdTracker;
import org.json.JSONObject;

public class MOATManager {
    private static final String autoTrackGMAInterstitials = "autoTrackGMAInterstitials";
    private static final String disableAdIdCollection = "disableAdIdCollection";
    private static final String disableLocationService = "disableLocationServices";
    private static final String loggingEnabled = "loggingEnabled";
    private static EventsListener mEventsListener;
    private static TrackerListener moatCallbacks = new C07111();
    private static WebAdTracker webAdTracker;

    /* renamed from: com.ironsource.sdk.analytics.moat.MOATManager$1 */
    static class C07111 implements TrackerListener {
        C07111() {
        }

        public void onTrackingStarted(String s) {
            if (MOATManager.mEventsListener != null) {
                MOATManager.mEventsListener.onTrackingStarted(s);
            }
        }

        public void onTrackingFailedToStart(String s) {
            if (MOATManager.mEventsListener != null) {
                MOATManager.mEventsListener.onTrackingFailedToStart(s);
            }
        }

        public void onTrackingStopped(String s) {
            if (MOATManager.mEventsListener != null) {
                MOATManager.mEventsListener.onTrackingStopped(s);
            }
        }
    }

    public interface EventsListener extends TrackerListener {
    }

    public static void setEventListener(EventsListener eventsListener) {
        mEventsListener = eventsListener;
    }

    public static void init(Application application) throws Exception {
        initWithOptions(null, application);
    }

    public static void initWithOptions(JSONObject options, Application application) throws Exception {
        MoatOptions o = null;
        if (options != null && options.length() > 0) {
            o = createMoatOptions(options);
        }
        MoatAnalytics.getInstance().start(o, application);
    }

    private static MoatOptions createMoatOptions(JSONObject options) {
        MoatOptions mo = new MoatOptions();
        mo.loggingEnabled = options.optBoolean(loggingEnabled);
        mo.autoTrackGMAInterstitials = options.optBoolean(autoTrackGMAInterstitials);
        mo.disableAdIdCollection = options.optBoolean(disableAdIdCollection);
        mo.disableLocationServices = options.optBoolean(disableLocationService);
        return mo;
    }

    public static void createAdTracker(WebView webView) throws Exception {
        webAdTracker = MoatFactory.create().createWebAdTracker(webView);
    }

    public static void startTracking() throws Exception {
        if (webAdTracker != null) {
            webAdTracker.setListener(moatCallbacks);
            webAdTracker.startTracking();
        }
    }

    public static void stopTracking() throws Exception {
        if (webAdTracker != null) {
            webAdTracker.stopTracking();
        }
    }
}
