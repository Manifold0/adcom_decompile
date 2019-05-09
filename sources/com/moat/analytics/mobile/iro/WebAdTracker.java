package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.support.annotation.UiThread;

@UiThread
public interface WebAdTracker {
    void removeListener();

    @Deprecated
    void setActivity(Activity activity);

    void setListener(TrackerListener trackerListener);

    void startTracking();

    void stopTracking();
}
