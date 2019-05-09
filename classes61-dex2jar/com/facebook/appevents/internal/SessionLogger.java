// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents.internal;

import java.util.Locale;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.AccessToken;
import android.os.Bundle;
import android.content.Context;

class SessionLogger
{
    private static final long[] INACTIVE_SECONDS_QUANTA;
    private static final String TAG;
    
    static {
        TAG = SessionLogger.class.getCanonicalName();
        INACTIVE_SECONDS_QUANTA = new long[] { 300000L, 900000L, 1800000L, 3600000L, 21600000L, 43200000L, 86400000L, 172800000L, 259200000L, 604800000L, 1209600000L, 1814400000L, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L };
    }
    
    private static int getQuantaIndex(final long n) {
        int n2;
        for (n2 = 0; n2 < SessionLogger.INACTIVE_SECONDS_QUANTA.length && SessionLogger.INACTIVE_SECONDS_QUANTA[n2] < n; ++n2) {}
        return n2;
    }
    
    public static void logActivateApp(final Context context, final String s, final SourceApplicationInfo sourceApplicationInfo, final String s2) {
        String string;
        if (sourceApplicationInfo != null) {
            string = sourceApplicationInfo.toString();
        }
        else {
            string = "Unclassified";
        }
        final Bundle bundle = new Bundle();
        bundle.putString("fb_mobile_launch_source", string);
        final InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(s, s2, null);
        internalAppEventsLogger.logEvent("fb_mobile_activate_app", bundle);
        if (AppEventsLogger.getFlushBehavior() != AppEventsLogger.FlushBehavior.EXPLICIT_ONLY) {
            internalAppEventsLogger.flush();
        }
    }
    
    private static void logClockSkewEvent() {
        Logger.log(LoggingBehavior.APP_EVENTS, SessionLogger.TAG, "Clock skew detected");
    }
    
    public static void logDeactivateApp(final Context context, final String s, final SessionInfo sessionInfo, final String s2) {
        Long n;
        if ((n = sessionInfo.getDiskRestoreTime() - sessionInfo.getSessionLastEventTime()) < 0L) {
            n = 0L;
            logClockSkewEvent();
        }
        Long n2;
        if ((n2 = sessionInfo.getSessionLength()) < 0L) {
            logClockSkewEvent();
            n2 = 0L;
        }
        final Bundle bundle = new Bundle();
        bundle.putInt("fb_mobile_app_interruptions", sessionInfo.getInterruptionCount());
        bundle.putString("fb_mobile_time_between_sessions", String.format(Locale.ROOT, "session_quanta_%d", getQuantaIndex(n)));
        final SourceApplicationInfo sourceApplicationInfo = sessionInfo.getSourceApplicationInfo();
        String string;
        if (sourceApplicationInfo != null) {
            string = sourceApplicationInfo.toString();
        }
        else {
            string = "Unclassified";
        }
        bundle.putString("fb_mobile_launch_source", string);
        bundle.putLong("_logTime", sessionInfo.getSessionLastEventTime() / 1000L);
        new InternalAppEventsLogger(s, s2, null).logEvent("fb_mobile_deactivate_app", (double)(n2 / 1000L), bundle);
    }
}
